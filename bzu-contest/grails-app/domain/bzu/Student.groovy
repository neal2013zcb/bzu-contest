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
	
	static belongsTo = [classGrade:ClassGrade]

    static constraints = {
		person nullable:false, unique:true // 唯一的
		classGrade nullable:false
    }
	
	String toString() {
		person.toString()
	}
}
