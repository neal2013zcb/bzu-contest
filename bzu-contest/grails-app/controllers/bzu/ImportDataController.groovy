package bzu

import grails.plugins.springsecurity.Secured;

/**
 * 导入数据
 * 
 * 管理员导入单位、专业、班级、学生、员工。
 * 
 * @author zhbo
 *
 */
@Secured(['ROLE_ADMIN'])
class ImportDataController {

	/**
	 * 导入数据功能的首页，显示功能简介以及导入各类数据的链接。
	 * 
	 * @return
	 */
    def index() { }
	
	/**
	 * 导入单位
	 * 
	 * 数据格式：单位编号，单位名称，单位简称，类型（系院，部门，其他）
	 */
	def importDepartment() {
		
	}
	
	/**
	 * 导入专业（根据专业代码，专业名称，专业简称，专业层次（专科，本科，硕士，博士），所属系院编号/名称/简称）
	 */
	def importSpecialty() {
		
	}
	
	/**
	 * 导入班级（根据班级名称，年级，班号，所学专业名称/简称/代码）
	 */
	def importClassGrade() {
		
	}
	
	/**
	 * 导入学生（根据学号，姓名，所在班级名称）
	 */
	def importStudent() {
		
	}
	
	/**
	 * 导入员工（根据工号，姓名，所在单位名称/简称/代码）
	 */
	def importStaff() {
		
	}
}
