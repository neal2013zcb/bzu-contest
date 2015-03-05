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
	 * 专业层次（1专科，2本科，3硕士，4博士）
	 */
	String level
	/**
	 * 所属系院
	 */
	Department department
	/**
	 * 各班级
	 */
	Set<ClassGrade> classGrades
	
	static belongsTo = [department:Department]
	
	static hasMany = [classGrades:ClassGrade]

	static constraints = {
		no nullable:false, blank:false, unique:true, size:3..20
		name nullable:false, blank:false, unique:true, maxSize:50
		shortName nullable:false, blank:false, unique:true, maxSize:50
		level nullable:false, blank:false, inList:Constants.Specialty.Level.VALUES, maxSize:1
		department nullable:false 
	}
	
	static mapping = {
		sort 'no'
	}
	
	String toString() {
		name
	}
}
