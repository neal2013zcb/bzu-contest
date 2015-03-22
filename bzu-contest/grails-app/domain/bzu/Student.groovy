package bzu

/**
 * 学生
 * 
 * @author zhbo
 *
 */
class Student extends Person {
	/**
	 * 所在班级
	 */
	ClassGrade classGrade
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
		classGrade nullable:false
    }
}
