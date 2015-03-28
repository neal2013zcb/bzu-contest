package bzu.security

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

import bzu.Constants;
import bzu.Person;
import bzu.Staff;
import bzu.Student;
import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
class UserController {
	
	static allowedMethods = [enable: "POST", disable: "POST",
			addRole: "POST", removeRole: "POST"]
	
	def springSecurityService

	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
    def enable(Long id) {
		User user = User.get(id)
		if(user) {
			user.enabled = true
			user.save()
		}
		render template:'accountManage', model:[user:user]
	}
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def disable(Long id) {
		User user = User.get(id)
		if(user) {
			user.enabled = false
			user.save()
		}
		render template:'accountManage', model:[user:user]
	}
	
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def addRole(Long id, String roleName) {
		User user = User.get(id)
		def allowedRoles = allowedRolesOf(user.person)
		if(roleName in allowedRoles) {
			// 分配权限
			Role role = Role.findByAuthority(roleName)
			if(! UserRole.findByUserAndRole(user, role)) {
				UserRole.create(user, role, true)
				reauthenticateIfMe user.username
			}
		}
		render template:'authoritiesManage', model:[user:user, allowedRoles:allowedRoles]
	}
	private List allowedRolesOf(Staff staff) {
		// 系院管理员不能分配特殊权限
		if(SpringSecurityUtils.ifNotGranted('ROLE_ADMIN')) {
			Constants.Role.STAFF_ROLES - Constants.Role.HIGH_LEVEL_ROLES
		} else {
			Constants.Role.STAFF_ROLES
		}
	}
	private List allowedRolesOf(Student student) {
		Constants.Role.STUDENT_ROLES
	}
	private void reauthenticateIfMe(String username) {
		if(springSecurityService.principal.username == username) {
			springSecurityService.reauthenticate(username)
		}
	}
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def removeRole(Long id, String roleName) {
		User user = User.get(id)
		def allowedRoles = allowedRolesOf(user.person)
		if(roleName in allowedRoles) {
			// 撤消权限
			Role role = Role.findByAuthority(roleName)
			UserRole.remove(user, role, true)
			reauthenticateIfMe user.username
		}
		render template:'authoritiesManage', model:[user:user, allowedRoles:allowedRoles]
	}
}
