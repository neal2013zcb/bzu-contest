package fileuploader

import java.util.Map;

import grails.util.Holders;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.groovy.grails.commons.DomainClassArtefactHandler;
import org.codehaus.groovy.grails.commons.GrailsDomainClass;
import org.codehaus.groovy.grails.commons.GrailsDomainClassProperty;
import org.springframework.context.MessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

class FileUploaderService {

	MessageSource messageSource

	/**
	 *
	 * @param domainName
	 * @param id
	 * @param propertyName
	 * @param file
	 * @param locale
	 * @return
	 * @throws FileUploaderServiceException
	 */
	def attachFile(String domainName, Serializable id, String propertyName, CommonsMultipartFile file, Locale locale)
	throws FileUploaderServiceException {
		attachFile(getTargetObject(domainName, id), propertyName, file, null, locale)
	}

	/**
	 *
	 * @param domainName
	 * @param id
	 * @param propertyName
	 * @param file
	 * @param name
	 * @param locale
	 * @return
	 * @throws FileUploaderServiceException
	 */
	def attachFile(String domainName, Serializable id, String propertyName, CommonsMultipartFile file, String name, Locale locale)
	throws FileUploaderServiceException {
		attachFile(getTargetObject(domainName, id), propertyName, file, name, locale)
	}

	private Object getTargetObject(String domainName, Serializable id) {
		def app = Holders.grailsApplication
		def targetClazz = app.getArtefactByLogicalPropertyName(DomainClassArtefactHandler.TYPE, domainName)
		def className = targetClazz.clazz.name
		return app.getClassForName(className).get(id)
	}

	/**
	 * 上传文件
	 *
	 * @param target
	 * @param propertyName
	 * @param file
	 * @param name
	 * @param locale
	 * @return
	 * @throws FileUploaderServiceException
	 */
	def attachFile(Object target, String propertyName, CommonsMultipartFile file, String name, Locale locale)
	throws FileUploaderServiceException {
		// check empty file
		if(file.size == 0) {
			def msg = messageSource.getMessage("fileupload.upload.nofile", null, locale)
			log.debug msg
			throw new FileUploaderServiceException(msg)
		}
		
		// get target domain and property
		GrailsDomainClass domain = target.domainClass
		String domainName = domain.logicalPropertyName
		GrailsDomainClassProperty property = domain.getPropertyByName(propertyName)

		// check property, do not overwrite
		if (! property.oneToMany && target[propertyName]!=null) {
			def propertyLabel = messageSource.getMessage("${domainName}.${property.name}.label", null, property.naturalName, locale)
			def msg = messageSource.getMessage("fileupload.upload.already.exists", [propertyLabel] as Object[], locale)
			log.debug msg
			throw new FileUploaderServiceException(msg)
		}

		// get configure
		def fileuploaderCfg = Holders.config.fileuploader
		def config = fileuploaderCfg[domainName][propertyName]

		// check extensions
		config.allowedExtensions = getConfig(fileuploaderCfg, domainName, propertyName, 'allowedExtensions')
		def fileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf('.') + 1).toLowerCase()
		if (!config.allowedExtensions[0].equals("*") && !config.allowedExtensions.contains(fileExtension)) {
			def msg = messageSource.getMessage("fileupload.upload.unauthorizedExtension", [
				fileExtension, config.allowedExtensions] as Object[], locale)
			log.debug msg
			throw new FileUploaderServiceException(msg)
		}

		// check file size
		config.maxSize = getConfig(fileuploaderCfg, domainName, propertyName, 'maxSize')
		if (config.maxSize) { //if maxSize config exists
			def maxSizeInKb = ((int) (config.maxSize / 1024))
			if (file.size > config.maxSize) { //if filesize is bigger than allowed
				log.debug "FileUploader plugin received a file bigger than allowed. Max file size is ${maxSizeInKb} kb"
				def msg = messageSource.getMessage("fileupload.upload.fileBiggerThanAllowed", [maxSizeInKb] as Object[], locale)
				throw new FileUploaderServiceException(msg)
			}
		}

		//base path to save file
		config.base = getConfig(fileuploaderCfg, domainName, propertyName, 'base')
		def path = config.base
		if (!path.endsWith('/')) path = path + "/"
		config.path = getConfig(fileuploaderCfg, domainName, propertyName, 'path')
		path = path + getConfigValue(config.path, target)
		if (!path.endsWith('/')) path = path + "/"

		//sets new path
		if (!new File(path).mkdirs())
			log.info "FileUploader plugin couldn't create directories: [${path}]"
		
		//file name
		if(!name) {
			config.name = getConfig(fileuploaderCfg, domainName, propertyName, 'name')
			name = getConfigValue(config.name, target)
			if(!name) name = file.originalFilename
		}
		path = path + name + "." + fileExtension

		//move file
		log.debug "FileUploader plugin received a ${file.size}b file. Moving to ${path}"
		file.transferTo(new File(path))

		//save it on the database
		def ufile = new UFile()
		ufile.name = name
		ufile.size = file.size
		ufile.extension = fileExtension
		ufile.dateUploaded = new Date()
		ufile.path = path
		ufile.downloads = 0

		// after create
		if (property.oneToMany) {
			def addToMethod = "addTo${propertyName[0].toUpperCase()}${propertyName.substring(1)}"
			target."${addToMethod}"(ufile)
		} else {
			ufile.save()
			target[propertyName] = ufile
		}
		target.save()

		return ufile
	}

	private Object getConfig(Map config, String domain, String prop, String key) {
		// get configuration
		def value = null
		if(domain && prop) {
			// get value
			value = config[domain][prop][key]
			if(value) return value
			// check reference
			def ref = config[domain][prop]['ref']
			if(ref) {
				value = getRefConfig(config, ref as String, key)
				if(value) return value
			}
		}
		// check default value of domain
		if(domain) {
			value = config[domain][key]
			if(value) return value
			// check reference
			def ref = config[domain]['ref']
			if(ref) {
				value = getRefConfig(config, ref, key)
				if(value) return value
			}
		}
		// check default configuration
		value = config[key]
		return value ? value : null
	}

	private Object getRefConfig(Map config, String ref, String key) {
		if(ref) {
			def k = ref.indexOf('.')
			if(k>0) {
				def d = ref.substring(0, k)
				def p = ref.substring(k+1)
				return config[d][p][key]
			} else {
				return config[ref][key]
			}
		} else {
			return null
		}
	}

	private Object getConfigValue(Object value, Object target) {
		value
	}
	private Object getConfigValue(Closure closure, Object target) {
		closure(target)
	}
	
	UFile getAttachedFile(String domainName, Serializable id, String propertyName, Serializable uid) {
		def target = getTargetObject(domainName, id)
		if(!target) return null
		
		UFile ufile = UFile.get(uid)
		if(!ufile) return null
		
		getAttachedFile(target, propertyName, ufile)
	}
	
	UFile getAttachedFile(Object target, String propertyName, UFile ufile) {
		// 取出属性值
		GrailsDomainClassProperty property = target.domainClass.getPropertyByName(propertyName)
		def propValue = target."${propertyName}"
		
		// 若两者关联关系，则返回上传文件对象
		if ((property.oneToMany && (ufile in propValue)) || (propValue?.id == ufile.id)) {
			return ufile
		} else { // 不存在关联关系，返回 null
			return null
		}
	}
	
	def removeAttachedFile(String domainName, Serializable id, String propertyName, Serializable uid) {
		def target = getTargetObject(domainName, id)
		if(!target) return null
		
		UFile ufile = UFile.get(uid)
		if(!ufile) return null
		
		// 考虑目标对象和文件的关系
		if(! getAttachedFile(target, propertyName, ufile)) return null
		
		removeAttachedFile(target, propertyName, ufile)
	}
	
	def removeAttachedFile(Object target, String propertyName, UFile ufile) {
		// get target property
		GrailsDomainClassProperty property = target.domainClass.getPropertyByName(propertyName)
		
		if (property.oneToMany) {
			def removeFromMethod = "removeFrom${propertyName[0].toUpperCase()}${propertyName.substring(1)}"
			target."${removeFromMethod}"(ufile)
		} else {
			target."${propertyName}" = null
		}
		ufile.delete()
		target.save()
	}

}
