package bzu.security

import bzu.Person;
import bzu.Student;
import bzu.Staff;
import bzu.Department;
import bzu.ClassGrade;

/**
 * 注册功能
 * 
 * @author zhbo
 *
 */
class RegisterService {

	/**
	 * 教职员工注册。
	 * 根据提供的工号、姓名、单位及个人信息，创建个人信息、登录账号，并分配用户和教师权限。
	 * 
	 * @param params
	 * @return 员工对象，如果注册成功；否则，null
	 */
    Staff registerStaff(Map params) {
		// 检查信息完整性
    	Long id = params.long('department.id')
		Department department = Department.get(id)
		if(!params.no || !params.name || !department)
			throw new SecurityException('注册信息不完整，至少包括员工号、姓名、所在机构信息')
		
		// 创建个人信息
		Person person = new Person(params)
		person.category = Constants.Person.Category.STAFF // 人员类型=员工
		if(!person.save())
			throw new SecurityException('保存个人信息失败')

		// 分配登录账号
		person.account = new User(username:params.no, password:params.password, enabled:true)
		if(!person.save())
			throw new SecurityException('创建登录账号失败')
		
		// 创建员工信息
		Staff staff = new Staff(person:person, department:department)
		if(!staff.save())
			throw new SecurityException('创建员工信息失败')
		
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
		// 检查信息完整性
		Long id = params.long('classGrade.id')
		ClassGrade classGrade = ClassGrade.get(id)
		if(!params.no || !params.name || !classGrade)
			throw new SecurityException('注册信息不完整，至少包括学号、姓名、所在班级信息')
		
		// 创建个人信息
		Person person = new Person(params)
		person.category = Constants.Person.Category.STUDENT  // 人员类型=学生
		if(!person.save())
			throw new SecurityException('保存个人信息失败')

		// 分配登录账号
		person.account = new User(username:params.no, password:params.password, enabled:true)
		if(!person.save())
			throw new SecurityException('创建登录账号失败')
	
		// 创建学生信息
		Student student = new Student(person:person, classGrade:classGrade)
		if(!student.save())
			throw new SecurityException('创建学生信息失败')
		
		return student
	}
	
}
