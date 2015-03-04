package bzu

/**
 * 部门
 * 
 * @author zhbo
 *
 */
class Department {
	/**
	 * 部门编号（唯一的）
	 */
	String no
	/**
	 * 部门名称（全称）
	 */
	String name
	/**
	 * 部门简称
	 */
	String shortName
	
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
		no nullable:false, blank:false, unique:true, size:3..20
		name nullable:false, blank:false, maxSize:50
		shortName nullable:false, blank:false, maxSize:50
    }
	
	static mapping = {
		sort 'no'
	}
	
	String toString() {
		name
	}
}
