package bzu

/**
 * 学生
 * 
 * @author zhbo
 *
 */
class Student {
	/**
	 * 个人信息
	 */
	Person person
	/**
	 * 所在班级
	 */
	ClassGrade classGrade
	/**
	 * 学号
	 * @return
	 */
	String getNo() {
		person.no
	}
	/**
	 * 学生姓名
	 * @return
	 */
	String getName() {
		person.name
	}
	/**
	 * 所学专业
	 * @return
	 */
	Specialty getSpecialty() {
		classGrade.specialty
	}
	/**
	 * 所在系院
	 * @return
	 */
	Department getDepartment() {
		classGrade.specialty.department
	}
	
	static belongsTo = [classGrade:ClassGrade]

    static constraints = {
		person nullable:false, unique:true // 唯一的
		classGrade nullable:false
    }
	
	String toString() {
		person.toString()
	}
}
