package bzu.security

import bzu.Person;
import bzu.ServiceException;
import bzu.Student;
import bzu.Staff;

/**
 * 注册功能
 * 
 * @author zhbo
 *
 */
class RegisterService {
	
	def messageSource

	/**
	 * 教职教师注册。
	 * 根据提供的工号、姓名、单位及个人信息，创建个人信息、登录账号，并分配用户和教师权限。
	 * 
	 * @param params
	 * @return 教师对象，如果注册成功；否则，null
	 */
    Staff registerStaff(Map params) {
		// 创建教师信息
		Staff staff = Staff.findByNo(params.no)
		if(staff == null) {
			staff = new Staff(params)
			if(!staff.save())
				throw new ServiceException("保存教师信息失败：${messageSource.getMessage(staff.errors.fieldError, Locale.getDefault())}")
		}
		
		// 分配登录账号
		if(!staff.account) {
			staff.account = new User(username:params.no, password:params.password, enabled:true)
			if(!staff.save())
				throw new ServiceException('创建登录账号失败')
			// 分配用户和教师权限
			['ROLE_USER', 'ROLE_TEACHER'].each { auth->
				def role = Role.findByAuthority(auth)
				if(role)
					UserRole.create(staff.account, role)
			}
		} else {
			throw new ServiceException('相关登录账号已存在，不能重复注册')
		}
		
		return staff
    }
	
	/**
	 * 学生注册。
	 * 根据提供的学号、姓名、班级及个人信息，创建个人信息、登录账号，并分配用户和学生权限。
	 * 
	 * @param params
	 * @return 学生对象，如果注册成功；否则，null
	 */
	Student registerStudent(Map params) {
		// 创建学生信息
		Student student = Student.findByNo(params.no)
		if(student == null) {
			student = new Student(params)
			if(!student.save())
				throw new ServiceException("保存学生信息失败：${messageSource.getMessage(student.errors.fieldError, Locale.getDefault())}")
		}
		
		// 分配登录账号
		User user = User.findByUsername(params.no)
		if(user == null) {
			student.account = new User(username:params.no, password:params.password, enabled:true)
			if(!student.save())
				throw new ServiceException('创建登录账号失败')
			// 分配用户和学生权限
			['ROLE_USER', 'ROLE_STUDENT'].each { auth->
				def role = Role.findByAuthority(auth)
				if(role)
					UserRole.create(student.account, role)
			}
		} else {
			throw new ServiceException('相关登录账号已存在，不能重复注册')
		}
		
		return student
	}
	
}
