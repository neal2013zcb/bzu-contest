package bzu

/**
 * 教师
 * 
 * @author zhbo
 *
 */
class Staff extends Person {
	/**
	 * 所在单位
	 */
	Department department
	/**
	 * 办公地点
	 */
	String officeLocation
	
	static belongsTo = [department:Department]

	static mapping = {
		sort 'no'
	}
	
    static constraints = {
		department nullable:false
		officeLocation nullable:true, blank:true, maxSize:50
    }
}
