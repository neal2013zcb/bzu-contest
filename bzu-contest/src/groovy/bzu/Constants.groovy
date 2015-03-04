package bzu

/**
 * 有关常量
 * 
 * @author zhbo
 *
 */
class Constants {
	
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
