package fileuploader

import org.apache.commons.io.FileUtils;

/**
 * 上传文件
 * 
 * @author zhbo
 */
class UFile implements Serializable {
	String path        // 文件保存路径
	String name        // 文件名
	String extension   // 扩展名
	Date dateUploaded  // 上传日期
	Long size          // 文件大小
	Integer downloads  // 下载次数

    static constraints = {
		path()
		name()
		extension()
		dateUploaded()
		size(min:0L)
		downloads(min:0)
    }
	
	String toString() {
		name
	}
	
	def afterDelete() {
		File file = new File(path)
		if(FileUtils.deleteQuietly(file)) {
			log.debug "file [${path}] deleted"
		} else {
			log.error "could not delete file: ${path}"
		}
	}
}
