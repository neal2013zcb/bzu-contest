package bzu

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
class StaffController {
	
	static defaultAction = "manage"
	
	def userService
	
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def manage(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		def query = Staff.where {}
		// 若不是系统管理员，则只列出本单位教师
		if(SpringSecurityUtils.ifNotGranted('ROLE_ADMIN')) {
			Department curDepartment = userService.currentDepartment
			query = query.where { department == curDepartment }
		}
		// 搜索条件
		if(params.q) {
			def qlike = "%${params.q}%"
			displayMessage(text:"搜索 “${params.q}” 的结果：", type:'info')
			query = query.where { name =~ qlike || no =~ qlike }
		}

		[staffInstanceList: query.list(params), staffInstanceTotal: query.count()]
	}
	
}
