package fileuploader

import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException;

/**
 * 文件上传与下载的标签
 *
 * @author zhbo
 */
class FileUploaderTagLib {
	
	static namespace = "fileuploader"

	/**
	 * 上传界面
	 * @attr target REQUIRED 上传文件所依附的目标对象
	 * @attr property REQUIRED 上传文件对应目标对象的属性
	 * @attr successController 上传完成后转到此 controller
	 * @attr successAction 上传完成后转到此 action
	 */
	def uploadForm = { attrs->
		def target = attrs.remove('target')
		def property = attrs.remove('property')
		def successController = attrs.successController ?: controllerName
		def successAction = attrs.successAction ?: actionName
		
		def tagBody = """
			<input type='hidden' name='domain' value='${target.domainClass.logicalPropertyName}' />
			<input type='hidden' name='id' value='${target.id}' />
			<input type='hidden' name='property' value='${property}' />
			<input type='hidden' name='successAction' value='${successAction}' />
			<input type='hidden' name='successController' value='${successController}' />
			<input type='file' name='file' />
			<input type='submit' name='submit' value="${g.message(code:'fileupload.upload.label', default:'上传')}" />
		"""
		
		StringBuilder sb = new StringBuilder()
		sb.append g.uploadForm([controller: 'fileUploader', action: 'upload', id:attrs.id], tagBody)
		
		out << sb.toString()
	}
	
	/**
	 * 下载界面
	 * @attr target REQUIRED 待下载文件所依附的目标对象
	 * @attr property REQUIRED 待下载文件对应目标对象的属性
	 * @attr ufile REQUIRED 待下载的文件对象
	 * @attr custom 自定义下载程序（同一 controller 中以 property 命名的 action）
	 */
	def download = { attrs->
		def target = attrs.remove('target')
		def property = attrs.remove('property')
		UFile ufile = attrs.remove('ufile')
		def custom = attrs.remove('custom') != null
		
		String controller = 'fileUploader'
		String action = 'download'
		if(custom) {
			controller = controllerName
			action = property
		}
		params.domain = target.domainClass.logicalPropertyName
		params.property = property
		params.uid = ufile.id
		
		out << g.link([controller:controller, action:action, id:target.id, params:params], ufile.name)
	}
	
	/**
	 * 删除下载文件
	 * @attr target REQUIRED 待删除文件所依附的目标对象
	 * @attr property REQUIRED 待删除文件对应目标对象的属性
	 * @attr ufile REQUIRED 待删除的文件对象
	 */
	def delete = { attrs, body->
		def target = attrs.remove('target')
		def property = attrs.remove('property')
		UFile ufile = attrs.remove('ufile')
		
		params.domain = target.domainClass.logicalPropertyName
		params.property = property
		params.uid = ufile.id
		
		StringBuilder sb = new StringBuilder()
		sb.append("<a href=\"")
		sb.append(g.createLink([controller:'fileUploader', action:'delete', id:target.id, params:params]))
		sb.append("\" ")
		sb.append("onclick=\"return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');\" >")
		sb.append(body())
		sb.append("</a>")
		
		out << sb.toString()
	}
}
