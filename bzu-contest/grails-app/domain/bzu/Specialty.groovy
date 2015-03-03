package bzu

/**
 * 专业
 * 
 * @author zhbo
 *
 */
class Specialty {	
	/**
	 * 专业编号（唯一的）
	 */
	String no
	/**
	 * 专业名称（全称）
	 */
	String name
	/**
	 * 专业简称
	 */
	String shortName
	/**
	 * 专业层次（专科，本科，硕士，博士）
	 */
	String level
	/**
	 * 所在部门
	 */
	Department department
	
	static belongsTo = [department:Department]

	static constraints = {
		no nullable:false, blank:false, unique:true, size:3..20
		name nullable:false, blank:false, maxSize:50
		shortName nullable:false, blank:false, maxSize:50
		level nullable:false, blank:false, inList:['专科','本科','硕士','博士'], maxSize:6
		department nullable:false 
	}
	
	static mapping = {
		sort 'no'
	}
	
	String toString() {
		name
	}
}
