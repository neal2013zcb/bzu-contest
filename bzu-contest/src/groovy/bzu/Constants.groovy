package bzu

/**
 * 有关常量
 * 
 * @author zhbo
 *
 */
class Constants {
	
	/**
	 * Person 相关常量
	 */
	static interface Person {
		/**
		 * 人员类别（0其他，1学生，2员工，3VIP）。
		 */
		static interface Category {
			/** 其他（未知的） = '0' */
			char UNKNOWN = '0'
			/** 学生 = '1' */
			char STUDENT = '1'
			/** 员工 = '2' */
			char STAFF = '2'
			/** VIP = '3' */
			char VIP = '3'
			/** 所有人员类别 */
			List VALUES = [UNKNOWN, STUDENT, STAFF, VIP]
		}
		/**
		 * 性别（-未指定，M男，F女）
		 */
		static interface Gender {
			/** 未知的 = '3' */
			char UNKNOWN = '-'
			/** VIP = '3' */
			char MALE = 'M'
			/** VIP = '3' */
			char FEMALE = 'F'
			/** 所有性别 */
			List VALUES = [UNKNOWN, MALE, FEMALE]
		}
	}
	
	/**
	 * Department 相关常量
	 */
	static interface Department {
		/**
		 * 单位类别（0其他，1系院，2部门）
		 */
		static interface Category {
			/** 其他（未知的） = '0' */
			char UNKNOWN = '0'
			/** 系院 = '1' */
			char SCHOOL = '1'
			/** 部门 = '2' */
			char DEPARTMENT = '2'
			/** 所有单位类别 */
			List VALUES = [UNKNOWN, SCHOOL, DEPARTMENT]
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
