package fileuploader

import grails.util.Holders;

import java.io.Serializable;

import org.codehaus.groovy.grails.commons.DomainClassArtefactHandler;

import bzu.utils.UserAgentUtils;

class FileUploaderController {
	
	def messageSource
	def fileUploaderService

	def upload(String domain, Long id, String property, String successController, String successAction) {
		// 上传文件
		try {
			def file = request.getFile('file')
			fileUploaderService.attachFile(domain, id, property, file, null, request.locale)
		} catch (FileUploaderServiceException e) {
			flash.message = e.message
		}
		redirect controller:successController ?: domain, action:successAction ?: 'show', id:id
	}
	
	def download(String domain, Long id, String property, Long uid) {
		// 获取关联文件对象
		UFile ufile = fileUploaderService.getAttachedFile(domain, id, property, uid)
		if(!ufile) {
			flash.message = messageSource.getMessage("fileupload.download.nofile", [ufile.id] as Object[], request.locale)
			redirect controller:domain, action:'show', id:id
		} else {
			// 发送文件
			def file = new File(ufile.path)
			if (file.exists()) {
				log.debug "Serving file id=[${ufile.id}] for the ${ufile.downloads} to ${request.remoteAddr}"
				ufile.downloads++
				ufile.save()
				response.setContentType(grailsApplication.config.grails.mime.types[ufile.extension])
				response.setHeader("Content-disposition",
						UserAgentUtils.makeAttachmentFilename(ufile.name, request.getHeader("User-Agent")))
				response.outputStream << file.readBytes()
				response.flushBuffer()
			} else {
				flash.message = messageSource.getMessage("fileupload.download.filenotfound", [ufile.name] as Object[], request.locale)
				redirect controller:domain, action:'show', id:id
			}
		}
	}
	
	def delete(String domain, Long id, String property, Long uid) {
		// 获取关联文件对象
		UFile ufile = fileUploaderService.getAttachedFile(domain, id, property, uid)
		if(!ufile) {
			flash.message = messageSource.getMessage("fileupload.download.nofile", [ufile.id] as Object[], request.locale)
			redirect controller:domain, action:'show', id:id
		} else {
			// 删除文件
			def filename = ufile.name
			fileUploaderService.removeAttachedFile(domain, id, property, uid)
			flash.message = messageSource.getMessage("fileupload.deleted", [filename] as Object[], request.locale)
			redirect controller:domain, action:'show', id:id
		}
	}
}
