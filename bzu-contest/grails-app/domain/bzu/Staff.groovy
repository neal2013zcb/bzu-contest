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
	/**
	 * 员工编号
	 * @return
	 */
	String getNo() {
		person.no
	}
	/**
	 * 员工姓名
	 * @return
	 */
	String getName() {
		person.name
	}
	
	static belongsTo = [department:Department]

    static constraints = {
		person nullable:false, unique:true // 唯一的
		department nullable:false
    }
	
	String toString() {
		person.toString()
	}
}
