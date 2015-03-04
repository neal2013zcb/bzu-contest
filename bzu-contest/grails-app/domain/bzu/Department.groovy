package bzu

/**
 * 单位
 * 
 * @author zhbo
 *
 */
class Department {
	/**
	 * 单位编号（唯一的）
	 */
	String no
	/**
	 * 单位名称（全称）
	 */
	String name
	/**
	 * 单位简称
	 */
	String shortName
	/**
	 * 类别（1系院，2部门，0其他）
	 */
	String category
	
	/**
	 * 各专业
	 */
	Set<Specialty> specialties
	/**
	 * 员工
	 */
	Set<Staff> staffs
	
	static hasMany = [specialties:Specialty, staffs:Staff]

    static constraints = {
		no nullable:false, blank:false, unique:true, maxSize:20, matches:/[A-Za-z0-9_\-]{3,20}/
		name nullable:false, blank:false, unique:true, maxSize:50
		shortName nullable:false, blank:false, unique:true, maxSize:50
		category nullable:false, blank:false, maxSize:1, inList:Constants.Department.Category.VALUES
    }
	
	static mapping = {
		sort 'no'
	}
	
	String toString() {
		name
	}
}
