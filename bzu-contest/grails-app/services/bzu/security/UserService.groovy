package bzu.security

import grails.plugins.springsecurity.Secured
import grails.util.Environment;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import bzu.Department;
import bzu.Person;

class UserService {
	
	static transactional = false

	def grailsApplication
	def springSecurityService
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	User getCurrentUser() {
		springSecurityService.currentUser
	}
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	Person getCurrentPerson() {
		currentUser?.person
	}
	
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	Department getCurrentDepartment() {
		currentPerson?.department
	}
	
	// 通过门户网站验证用户名和密码
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def boolean webPortalAuthentication(String username, String password) throws IOException {
		try {
			// 尝试登录门户网站验证密码
			log.info "尝试登录门户网站验证用户 ${username}"
			def loginUrl = grailsApplication.config.webPortal.login.url
			def loginSuccess = grailsApplication.config.webPortal.login.success
			if(Environment.current == Environment.DEVELOPMENT) {
				// 开发环境下使用测试网址
				log.info "[DEVELOPMENT] 尝试登录门户网站验证用户 ${username}"
				loginUrl = grailsApplication.config.webPortal.mock.url
				loginSuccess = grailsApplication.config.webPortal.mock.success
			}
			return (new URL(String.format(loginUrl, username, password)).text
						.indexOf(loginSuccess) != -1)
		} catch (IOException networkEx) {
			// 网络故障，无法访问门户网站
			throw networkEx
		}
	}

	// 更新用户密码
	// 忘记密码时，直接使用门户网站密码登录，系统自动更新密码
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	@Transactional
	def updateUserPassword(String username, String password) {
		User user = User.findByUsername(username)
		if(user) {
			user.password = password
			user.save(flush:true)
			log.info "更新用户密码 ${username}"
		}
	}
}
