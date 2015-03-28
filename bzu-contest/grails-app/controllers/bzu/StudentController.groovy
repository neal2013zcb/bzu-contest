package bzu

import grails.plugins.springsecurity.Secured;

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
class StudentController {

	static defaultAction = "manage"
	
	def userService
	
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def manage(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def query = Student.where {}
		// 若不是系统管理员，则只列出本单位学生
		if(SpringSecurityUtils.ifNotGranted('ROLE_ADMIN')) {
			Department curDepartment = userService.currentDepartment
			query = query.where { classGrade.specialty.department == curDepartment }
		}
		// 搜索条件
		if(params.q) {
			def qlike = "%${params.q}%"
			displayMessage(text:"搜索 “${params.q}” 的结果：", type:'info')
			query = query.where { name =~ qlike || no =~ qlike }
		}
		
		cache validFor: 5
		
		[studentInstanceList: query.list(params), studentInstanceTotal: query.count()]
	}
}
