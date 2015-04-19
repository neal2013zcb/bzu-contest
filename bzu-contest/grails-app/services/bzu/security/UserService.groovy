package bzu.security

import grails.plugins.springsecurity.Secured
import grails.util.Environment;

import java.io.IOException;
import java.util.List;

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;
import org.springframework.transaction.annotation.Transactional;

import bzu.Constants;
import bzu.Department;
import bzu.Person;
import bzu.Staff;
import bzu.Student;
import bzu.contest.ProjectApplication;

class UserService {
	
	static transactional = false

	def grailsApplication
	def springSecurityService
	
	/**
	 * 返回当前登录的用户
	 * @return 当前登录用户
	 */
	@Secured(['IS_AUTHENTICATED_REMEMBERED'])
	User getCurrentUser() {
		springSecurityService.currentUser
	}
	
	/**
	 * 返回当前用户个人信息
	 * @return 当前用户个人信息
	 */
	@Secured(['IS_AUTHENTICATED_REMEMBERED'])
	Person getCurrentPerson() {
		currentUser.person
	}
	
	/**
	 * 返回当前用户所在单位
	 * @return 当前用户所在单位
	 */
	@Secured(['IS_AUTHENTICATED_REMEMBERED'])
	Department getCurrentDepartment() {
		itsDepartment(currentUser.person)
	}
	
	/**
	 * 通过门户网站验证用户名和密码
	 * @param username 用户名
	 * @param password 密码
	 * @return <code>true</code> 若通过验证；<code>false</code> 否则
	 * @throws IOException
	 */
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def boolean webPortalAuthentication(String username, String password) throws IOException {
		try {
			// 尝试登录门户网站验证密码
			log.info "尝试登录门户网站验证用户 ${username}"
			def loginUrl = grailsApplication.config.webPortal.login.url
			def loginSuccess = grailsApplication.config.webPortal.login.success
			if(Environment.current == Environment.DEVELOPMENT) {
				// 开发环境下使用测试网址
				log.info "尝试登录门户网站验证用户 ${username}"
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

	/**
	 * 更新用户密码。忘记密码时，直接使用门户网站密码登录，系统自动更新密码。
	 * @param username 用户名
	 * @param password 新密码
	 * @return
	 */
	@Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	@Transactional
	def updateUserPassword(String username, String password) {
		User user = User.findByUsername(username)
		if(user) {
			user.password = password
			user.save(flush:true)
			log.info "用户 ${username} 更新密码"
		}
	}

	/**
	 * 返回当前用户的审核状态
	 * @return 当前用户审核状态
	 */
	boolean isApproved() {
		currentPerson?.approved
	}
	
	/**
	 * 判断当前用户是否具有系统管理员权限
	 * @return <code>true</code> 若有；<code>false</code> 否则
	 */
	boolean hasAdminRole() {
		SpringSecurityUtils.ifAnyGranted("ROLE_ADMIN")
	}
	
	/**
	 * 判断当前用户是否具有系院管理权限
	 * @return <code>true</code> 若有；<code>false</code> 否则
	 */
	boolean hasDepartmentRole() {
		SpringSecurityUtils.ifAnyGranted("ROLE_DEPARTMENT")
	}
	
	/**
	 * 判断当前用户是否具有项目管理权限
	 * @return <code>true</code> 若有；<code>false</code> 否则
	 */
	boolean hasProjectRole() {
		SpringSecurityUtils.ifAnyGranted("ROLE_PROJECT")
	}
	
	/**
	 * 判断当前用户是否具有用户权限
	 * @return <code>true</code> 若有；<code>false</code> 否则
	 */
	boolean hasUserRole() {
		SpringSecurityUtils.ifAnyGranted("ROLE_USER")
	}

	/**
	 * 判断当前用户能否对指定用户账号执行管理操作（如：激活，分配权限等）
	 * @param user 指定用户账号
	 * @return <code>true</code> 若能；<code>false</code> 否则
	 */
	boolean canManage(User user) {
		canManage(user.person)
	}
	
	/**
	 * 判断当前用户能否对指定个人信息执行管理操作（如：审核）
	 * @param person 指定个人信息
	 * @return <code>true</code> 若能；<code>false</code> 否则
	 */
	boolean canManage(Person person) {
		isApproved() && ( hasAdminRole() || hasDepartmentRole() && ofSameDepartment(person) )
	}

	/**
	 * 判断当前用户与指定用户是否属于同一个单位
	 * @param user 指定用户
	 * @return <code>true</code> 若属同一单位；<code>false</code> 否则
	 */
	boolean ofSameDepartment(User user) {
		itsDepartment(user.person).id == currentDepartment?.id
	}
	
	/**
	 * 判断当前用户与指定教师是否属于同一个单位
	 * @param staff 指定教师
	 * @return <code>true</code> 若属同一单位；<code>false</code> 否则
	 */
	boolean ofSameDepartment(Person person) {
		itsDepartment(person).id == currentDepartment?.id
	}
	
	// 教师所在单位
	private Department itsDepartment(Staff staff) {
		staff.department
	}
	
	// 学生所在单位
	private Department itsDepartment(Student student) {
		student.department
	}
	
	/**
	 * 判断当前用户是否为指定对象的负责人
	 * @param projectApp
	 * @return
	 */
	boolean isPrincipalOf(Object target) {
		target?.principal?.id == currentPerson.id
	}
	
	// 基于可管理的对象执行一系列操作，操作成功返回 true
	private boolean doIfCanManage(target, whatToDo) {
		canManage(target) ? whatToDo(target) : false
	}
	
	/**
	 * 审核指定的个人信息
	 * @param person 个人信息
	 * @return <code>true</code> 若审核成功；<code>false</code> 否则
	 */
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	@Transactional
	boolean doApprove(Person person) {
		doIfCanManage(person, {
			person.approved = true
			person.dateApproved = new Date()
			person.approvedBy = currentPerson
			person.save()
		})
	}
	
	/**
	 * 撤消对指定个人信息的审核
	 * @param person 个人信息
	 * @return <code>true</code> 若撤消成功；<code>false</code> 否则
	 */
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	@Transactional
	boolean undoApprove(Person person) {
		doIfCanManage(person, {
			person.approved = false
			person.dateApproved = null
			person.approvedBy = null
			person.save()
		})
	}
	
	/**
	 * 激活或禁用指定登录账号
	 * @param usre 指定账号
	 * @param enabled 激活状态
	 * @return <code>true</code> 若操作成功；<code>false</code> 否则
	 */
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	@Transactional
	boolean setUserEnabled(User user, boolean enabled) {
		doIfCanManage(user, {
			user.enabled = enabled
			user.save()
		})
	}
	
	/**
	 * 为指定用户分配权限
	 * @param user 用户
	 * @param roleName 权限名称
	 * @return <code>true</code> 若分配成功；<code>false</code> 否则
	 */
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	@Transactional
	boolean addRole(User user, String roleName) {
		doIfCanManage(user, {
			if(roleName in allowedRoles(user)) {
				// 分配权限
				Role role = Role.findByAuthority(roleName)
				if(! UserRole.findByUserAndRole(user, role)) {
					UserRole.create(user, role, true)
					reauthenticateIfMe user.username
				}
				return true
			}
			return false
		})
	}
	
	/**
	 * 删除指定用户的权限
	 * @param user 用户
	 * @param roleName 权限名称
	 * @return <code>true</code> 若操作成功；<code>false</code> 否则
	 */
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	@Transactional
	boolean removeRole(User user, String roleName) {
		doIfCanManage(user, {
			if(roleName in allowedRoles(user)) {
				// 撤消权限
				Role role = Role.findByAuthority(roleName)
				UserRole.remove(user, role, true)
				reauthenticateIfMe user.username
				return true
			}
			return false
		})
	}
	
	/**
	 * 对指定可以管理的权限
	 * @param staff 教师
	 * @return 允许修改的权限
	 */
	List allowedRoles(User user) {
		allowedRolesOf(user.person)
	}
	
	// 系统管理员可以管理所有权限，系院管理员不能管理高级权限
	private List allowedRolesOf(Staff staff) {
		if(hasAdminRole()) {
			Constants.Role.STAFF_ROLES
		} else {
			Constants.Role.STAFF_ROLES - Constants.Role.HIGH_LEVEL_ROLES
		}
	}
	
	// 系统管理员和系院管理员都可以管理学生的权限
	private List allowedRolesOf(Student student) {
		Constants.Role.STUDENT_ROLES
	}
	
	// 如果修改了自己的权限，应重新进行身份认证
	private void reauthenticateIfMe(String username) {
		if(springSecurityService.principal.username == username) {
			springSecurityService.reauthenticate(username)
		}
	}
}
