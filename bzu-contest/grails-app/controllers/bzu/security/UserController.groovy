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
		// 系院管理员不能分配特殊权限
		if(SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN') ||
				!(roleName in Constants.Role.HIGH_LEVEL_ROLES)) {
			// 根据用户身份（学生和教师）和权限确定决定是否更新权限
			ifRoleRightForPerson(user.person, roleName, {
				// 分配权限
				Role role = Role.findByAuthority(roleName)
				if(! UserRole.findByUserAndRole(user, role)) {
					UserRole.create(user, role, true)
					reauthenticateIfMe user.username
				}
			})
		}
		render template:'authoritiesManage', model:[user:user]
	}
	private void ifRoleRightForPerson(Staff staff, String roleName, doWhat) {
		if(roleName in Constants.Role.STAFF_ROLES) {
			doWhat()
		}
	}
	private void ifRoleRightForPerson(Student student, String roleName, doWhat) {
		if(roleName in Constants.Role.STUDENT_ROLES) {
			doWhat()
		}
	}
	private void reauthenticateIfMe(String username) {
		if(springSecurityService.principal.username == username) {
			springSecurityService.reauthenticate(username)
		}
	}
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def removeRole(Long id, String roleName) {
		User user = User.get(id)
		// 系院管理员不能撤消特殊权限
		if(SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN') ||
				!(roleName in Constants.Role.HIGH_LEVEL_ROLES)) {
			// 根据用户身份（学生和教师）和权限确定决定是否更新权限
			ifRoleRightForPerson(user.person, roleName, {
				// 撤消权限
				Role role = Role.findByAuthority(roleName)
				UserRole.remove(user, role, true)
				reauthenticateIfMe user.username
			})
		}
		render template:'authoritiesManage', model:[user:user]
	}
}
