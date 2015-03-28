package bzu

/**
 * 有关常量
 * 
 * @author zhbo
 *
 */
class Constants {
	
	/**
	 * Role 相关常量
	 */
	static interface Role {
		/** 所有权限 */
		List ROLES = [
			'ROLE_USER',		// 注册用户
			'ROLE_ADMIN',		// 系统管理
			'ROLE_DEPARTMENT',	// 系院管理
			'ROLE_PROJECT',		// 项目管理
			'ROLE_INSPECTOR',	// 竞赛督导
			'ROLE_TEACHER',		// 教师
			'ROLE_STUDENT',		// 学生
		]
		/** 教师的所有权限 */
		List STAFF_ROLES = ['ROLE_USER','ROLE_TEACHER','ROLE_PROJECT','ROLE_DEPARTMENT','ROLE_INSPECTOR','ROLE_ADMIN']
		/** 学生的所有权限 */
		List STUDENT_ROLES = ['ROLE_USER','ROLE_STUDENT']
		/** 高级权限 */
		List HIGH_LEVEL_ROLES = ['ROLE_DEPARTMENT','ROLE_INSPECTOR','ROLE_ADMIN']
	}
	
	/**
	 * Person 相关常量
	 */
	static interface Person {
		/**
		 * 性别（-未指定，M男，F女）
		 */
		static interface Gender {
			/** 未知的 = 'X' */
			String UNKNOWN = 'X'
			/** 男 = 'M' */
			String MALE = 'M'
			/** 女 = 'F' */
			String FEMALE = 'F'
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
			String UNKNOWN = '0'
			/** 系院 = '1' */
			String SCHOOL = '1'
			/** 部门 = '2' */
			String DEPARTMENT = '2'
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
			String JUNIOR = '1'
			/** 本科 = '2' */
			String BACHELOR = '2'
			/** 硕士 = ‘3’ */
			String MASTER = '3'
			/** 博士 = '4' */
			String DOCTOR = '4'
			/** 所有专业层次 */
			List VALUES = [JUNIOR, BACHELOR, MASTER, DOCTOR]
		}
	}
	
	
}
