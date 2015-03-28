package bzu.security

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
class UserController {
	
	static allowedMethods = [enable: "POST", disable: "POST",
			addRole: "POST", removeRole: "POST"]
	
	def userService

	/**
	 * 激活指定的登录账号
	 */
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
    def enable(Long id) {
		User user = User.get(id)
		userService.setUserEnabled(user, true)
		render template:'accountManage', model:[user:user]
	}

	/**
	 * 禁用指定的登录账号
	 */
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def disable(Long id) {
		User user = User.get(id)
		userService.setUserEnabled(user, false)
		render template:'accountManage', model:[user:user]
	}

	/**
	 * 为指定的登录账号添加权限（角色）
	 */
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def addRole(Long id, String roleName) {
		User user = User.get(id)
		userService.addRole(user, roleName)
		render template:'authoritiesManage', model:[user:user,
			allowedRoles:userService.allowedRoles(user)]
	}
	
	/**
	 * 为指定的登录账号删除权限（角色）
	 */
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def removeRole(Long id, String roleName) {
		User user = User.get(id)
		userService.removeRole(user, roleName)
		render template:'authoritiesManage', model:[user:user,
			allowedRoles:userService.allowedRoles(user)]
	}
}
