package bzu.security

import bzu.Constants;
import bzu.Person;
import bzu.ServiceException;
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
	 * 教职教工注册。
	 * 根据提供的工号、姓名、单位及个人信息，创建个人信息、登录账号，并分配用户和教师权限。
	 * 
	 * @param params
	 * @return 教工对象，如果注册成功；否则，null
	 */
    Staff registerStaff(Map params) {
		// 检查信息完整性
    	Long id = params.long('department.id')
		Department department = Department.get(id)
		if(!params.no || !params.name || !department)
			throw new ServiceException('注册信息不完整，至少包括教工号、姓名、所在机构信息')
		
		// 创建个人信息
		Person person = Person.findByNo(params.no)
		if(person == null) {
			person = new Person(params)
			person.category = Constants.Person.Category.STAFF // 人员类型=教工
			if(!person.save())
				throw new ServiceException('保存个人信息失败')
		} else if(person.category != Constants.Person.Category.STAFF) {
			throw new ServiceException('相关用户信息已经存在，但不是教工，注册失败')
		}
	
		// 创建教工信息
		Staff staff = Staff.findByPerson(person)
			if(staff == null) {
			staff = new Staff(person:person, department:department)
			if(!staff.save())
				throw new ServiceException('创建教工信息失败')
		} else if(staff.department.id != department.id) {
			throw new ServiceException('该教工已经存在，但属于其他单位，注册失败')
		}
		
		// 分配登录账号
		User user = User.findByUsername(params.no)
		if(user == null) {
			person.account = new User(username:params.no, password:params.password, enabled:true)
			if(!person.save())
				throw new ServiceException('创建登录账号失败')
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
		// 检查信息完整性
		Long id = params.long('classGrade.id')
		ClassGrade classGrade = ClassGrade.get(id)
		if(!params.no || !params.name || !classGrade)
			throw new ServiceException('注册信息不完整，至少包括学号、姓名、所在班级信息')
		
		// 创建个人信息
		Person person = Person.findByNo(params.no)
		if(person == null) {
			person = new Person(params)
			person.category = Constants.Person.Category.STUDENT  // 人员类型=学生
			if(!person.save())
				throw new ServiceException('保存个人信息失败')
		} else if(person.category != Constants.Person.Category.STUDENT) {
			throw new ServiceException('相关用户信息已经存在，但不是学生，注册失败')
		}
		
		// 创建学生信息
		Student student = Student.findByPerson(person)
		if(student == null) {
			student = new Student(person:person, classGrade:classGrade)
			if(!student.save())
				throw new ServiceException('创建学生信息失败')
		} else if(student.classGrade.id != classGrade.id) {
			throw new ServiceException('该学生已经存在，但属于其他班级，注册失败')
		}
		
		// 分配登录账号
		User user = User.findByUsername(params.no)
		if(user == null) {
			person.account = new User(username:params.no, password:params.password, enabled:true)
			if(!person.save())
				throw new ServiceException('创建登录账号失败')
		} else {
			throw new ServiceException('相关登录账号已存在，不能重复注册')
		}
		
		return student
	}
	
}
