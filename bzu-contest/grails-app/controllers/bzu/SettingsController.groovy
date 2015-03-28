package bzu

import org.springframework.security.authentication.encoding.PasswordEncoder;

import bzu.security.User;
import bzu.security.UserService;
import grails.plugins.springsecurity.Secured;
import grails.plugins.springsecurity.SpringSecurityService;

/**
 * 个人设置
 * <ul>
 *   <li>身份信息：显示学号/教师号、姓名、性别、所属机构、验证状态，身份验证前可以更新姓名、性别，通过验证后身份信息不可修改。</li>
 *   <li>账号设置：修改密码，申请权限。</li>
 *   <li>联系方式：更新邮箱、工作电话、家庭电话等联系信息。</li>
 *   <li>通知消息：设置接收消息的类型和方式。</li>
 * </ul>
 * @author zhbo
 *
 */
@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class SettingsController {
	
	static defaultAction = "profile"
	
	static allowedMethods = [updateProfile: "POST", changePassword:"POST", updateContacts:"POST"]

	SpringSecurityService springSecurityService
	UserService userService

	/**
	 * 查看和修改个人基本信息
	 */
	def profile() {
		// 取当前用户的身份信息
		def person = userService.currentPerson
		// 返回当前用户身份信息及类型
		[person:person, userType:userType(person)]
	}
	
	private String userType(Staff staff) {
		return 'staff'
	}
	private String userType(Student student) {
		return 'student'
	}

	/**
	 * 更新个人基本信息
	 */
	def updateProfile() {
		def person = userService.currentPerson
		if(!person.approved) {
			person.properties = params
			if(!person.save(flush:true)) {
				def msg = message(error:person.errors.fieldError)
				displayFlashMessage(text:msg, type:'error')
			}
			displayFlashMessage(text:'更新基本信息成功', type:'info')
		} else {
			displayFlashMessage(text:'您的身份信息已审核，不能修改', type:'error')
		}
		redirect action:'profile'
	}
	
	/**
	 * 查看登录账号和权限
	 */
	def account() {
		def user = userService.currentUser
		def roles = user.authorities*.authority
		def allRoles = allRoles(user.person)
		[user:user, roles:roles, allRoles:allRoles]
	}
	private List allRoles(Staff staff) {
		Constants.Role.STAFF_ROLES
	}
	private List allRoles(Student student) {
		Constants.Role.STUDENT_ROLES
	}
	/**
	 * 修改密码
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 * @param repeatPassword 重复密码
	 */
	def changePassword(String oldPassword, String newPassword, String repeatPassword) {
		// 读取当前用户
		User user = userService.currentUser

		// 检查参数（非空，密码重复正确）
		if(!oldPassword || !newPassword || !repeatPassword || newPassword!=repeatPassword) {
			displayFlashMessage(text:'请正确输入原密码，并输入新密码且重复一次。', type:'error')
			redirect action:'account'
			return
		}
		
		// 检查原密码
		if(! springSecurityService.passwordEncoder.isPasswordValid(user.password, oldPassword, null)) {
			displayFlashMessage(text:'请正确输入原密码，并输入新密码且重复一次。', type:'error')
			redirect action:'account'
			return
		}
		
		// 修改新密码
		user.password = newPassword
		if(! user.save(flush:true)) {
			displayFlashMessage(text:'数据保存失败，修改密码不成功。', type:'error')
			redirect action:'account'
			return
		}
		
		// 修改成功
		displayFlashMessage(text:'成功修改为新密码。', type:'info')
		redirect action:'account'
	}
	
	/**
	 * 查看和修改联系方式
	 */
	def contacts() {
		[person:userService.currentPerson]
	}
	/**
	 * 更新练习方式
	 */
	def updateContacts() {
		def person = userService.currentPerson
		person.properties = params
		if(!person.save(flush:true)) {
			def msg = message(error:person.errors.fieldError)
			displayFlashMessage(text:msg, type:'error')
		}
		displayFlashMessage(text:'成功更新联系方式', type:'info')
		redirect action:'contacts'
	}
	
	/**
	 * 查看和修改接收通知和消息的方式
	 */
	def notifications() {
		// TODO 通知和消息设置
		displayMessage text:'此功能尚未实现', type:'warning'
	}
}
