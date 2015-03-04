package bzu

/**
 * 教职员工
 * 
 * @author zhbo
 *
 */
class Staff {
	/**
	 * 个人信息
	 */
	Person person
	/**
	 * 所在单位
	 */
	Department department
	
	static belongsTo = [department:Department]

    static constraints = {
		person nullable:false, unique:true // 唯一的
		department nullable:false
    }
	
	String toString() {
		person.toString()
	}
}
