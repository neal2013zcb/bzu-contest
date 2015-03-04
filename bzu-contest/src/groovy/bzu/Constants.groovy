package bzu

/**
 * 有关常量
 * 
 * @author zhbo
 *
 */
class Constants {
	
	/**
	 * Department 相关常量
	 */
	static interface Department {
		/**
		 * 单位类别（0其他，1系院，2部门）
		 */
		static interface Category {
			/** 其他 = '0' */
			char NA = '0'
			/** 系院 = '1' */
			char SCHOOL = '1'
			/** 部门 = '2' */
			char DEPARTMENT = '2'
			/** 所有单位类别 */
			List VALUES = [NA, SCHOOL, DEPARTMENT]
		}
	}
	
	/**
	 * Specialty 相关常量
	 */
	static interface Specialty {
		/**
		 * 专业层次
		 */
		static interface Level {
			/** 专科 = ‘1’ */
			char JUNIOR = '1'
			/** 本科 = '2' */
			char BACHELOR = '2'
			/** 硕士 = ‘3’ */
			char MASTER = '3'
			/** 博士 = '4' */
			char DOCTOR = '4'
			/** 所有专业层次 */
			List VALUES = [JUNIOR, BACHELOR, MASTER, DOCTOR]
		}
	}
	
	
}
