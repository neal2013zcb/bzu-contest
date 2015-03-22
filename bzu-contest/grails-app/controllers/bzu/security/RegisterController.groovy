package bzu.security

import bzu.Staff;
import bzu.Student;

/**
 * 注册员工和学生
 * 
 * @author zhbo
 *
 */
class RegisterController {
	
	/**
	 * 引用 UserService
	 */
	UserService userService
	/**
	 * 引用 RegisterService
	 */
	RegisterService registerService
	
	// 选择员工注册或学生注册
	def index() { }

	// 员工注册
    def staff() { }
	def registerStaff() {
		// 检查参数完整性
		if(!checkParams('staff')) return
		// 通过校内门户验证用户身份
		if(!passWebPortalAuthCheck('staff')) return
		
		// 注册员工
		try {
			Staff staff = registerService.registerStaff(params)
			displayFlashMessage(text:"员工 ${staff} 注册成功", type:'info')
			redirect controller:'login' // 注册成功后转登录界面
			return
		} catch (Exception e) {
			displayFlashMessage(text: e.message, type: 'error')
			render view:'staff', model:params
			return
		}
	}
	
	private boolean checkParams(String failAction) {
		// 检查参数
		if(!params.no) {
			displayFlashMessage(text:"必须填写${failAction=='student' ? '学号' : '教工号'}", type:'error')
			render view:failAction, model:params
			return false
		}
		if(!params.name) {
			displayFlashMessage(text:"必须填写姓名", type:'error')
			render view:failAction, model:params
			return false
		}
		if(!params.password) {
			displayFlashMessage(text:"必须填写密码", type:'error')
			render view:failAction, model:params
			return false
		}
		if(failAction=='student' && !params.classGrade.id) {
			displayFlashMessage(text:"必须填写班级", type:'error')
			render view:failAction, model:params
			return false
		}
		if(failAction=='staff' && !params.department.id) {
			displayFlashMessage(text:"必须填写所在单位", type:'error')
			render view:failAction, model:params
			return false
		}
		return true
	}
	
	/**
	 * 通过校内门户验证身份
	 * 
	 * @return <code>true</code>，若验证成功；<code>false</code>，否则
	 */
	private boolean passWebPortalAuthCheck(String failAction) {
		// 通过校内门户验证用户身份
		try {
			// 尝试登录门户网站验证密码
			if(!userService.webPortalAuthentication(params.no, params.password)) {
				// 网络验证失败，不能继续注册
				displayFlashMessage(text:"对不起，您不是本校学生或教职工", type:'error')
				render view:failAction, model:params
				return false
			}
		} catch (IOException networkEx) {
			// 网络故障，无法访问门户网站
			displayFlashMessage(text:"无法验证您的身份，可能是网络故障，请稍后再试", type:'error')
			render view:failAction, model:params
			return false
		}
		return true
	}
	
	// 学生注册
	def student() { }
	def registerStudent() {
		// 检查参数完整性
		if(!checkParams('student')) return
		// 通过校内门户验证用户身份
		if(!passWebPortalAuthCheck('student')) return
		// 注册员工
		try {
			Student student = registerService.registerStudent(params)
			displayFlashMessage(text:"学生 ${student} 注册成功", type:'info')
			redirect controller:'login' // 注册成功后转登录界面
			return
		} catch (Exception e) {
			displayFlashMessage(text: e.message, type: 'error')
			render view:'student', model:params
			return
		}
	}
}
