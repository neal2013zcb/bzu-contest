package bzu.security

import grails.plugins.springsecurity.Secured;
import grails.util.Environment;

class WebPortalMockController {

	/**
	 * 开发环境下模拟校内门户登录
	 */
	@Secured(['permitAll'])
	def login = {
		// 仅用于开发环境
		if(Environment.current == Environment.DEVELOPMENT) {
			def config = grailsApplication.config.webPortal.mock
			def users = config.users
			def userName = params.userName
			def userPass = params.userPass
			if(userName && userPass && users[userName] == userPass)
				render config.success
			else
				render config.fail
			return
		}
		
		// 不适用于其他环境
		redirect controller:'index'
		return
	}
}
