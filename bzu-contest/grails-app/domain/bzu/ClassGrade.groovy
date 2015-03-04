package bzu

/**
 * 班级
 * 
 * @author zhbo
 *
 */
class ClassGrade {
	/**
	 * 班级名称（简称，唯一的）
	 */
	String name
	/**
	 * 年级
	 */
	Integer grade
	/**
	 * 班号
	 */
	Integer classNo
	/**
	 * 专业
	 */
	Specialty specialty
	
	static belongsTo = [specialty:Specialty]

    static constraints = {
		name nullable:false, blank:false, unique:true, maxSize:50
		grade nullable:false, range:2000..2020
		classNo nullable:false, range:1..20
		specialty nullable:false
    }
	
	String toString() {
		name
	}
}
