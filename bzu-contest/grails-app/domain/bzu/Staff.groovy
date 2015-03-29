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
	
	static belongsTo = [department:Department]

	static mapping = {
		sort 'no'
	}
	
    static constraints = {
		department nullable:false
    }
}
