package bzu

import org.codehaus.groovy.grails.commons.DomainClassArtefactHandler;
import org.mindrot.jbcrypt.BCrypt;

import bzu.security.RegisterService;
import grails.plugins.springsecurity.Secured;

/**
 * 导入数据
 * 
 * 管理员导入单位、专业、班级、学生、员工。
 * 
 * @author zhbo
 *
 */
//@Secured(['ROLE_ADMIN'])
class ImportDataController {
	
	static allowedMethods = [importDepartment: "POST", importSpecialty: "POST",
			importClassGrade: "POST", importStudent: "POST", importStaff: "POST",
			undo: "POST"]
	
	RegisterService registerService
	
	/**
	 * 导入数据功能的首页，显示功能简介以及导入各类数据的链接。
	 * 
	 * @return
	 */
    def index() { }
	
	/**
	 * 显示导入单位表单
	 */
    def department() { }
	/**
	 * 导入单位
	 * 
	 * 数据格式：单位编号，单位名称，单位简称，类型（系院，部门，其他）
	 */
	def importDepartment() {
		// 取数据列表
		def text = params.text
		if(!text) {
			// 未提交数据，重新显示提交页面
			displayFlashMessage text:"未提交任何数据", type:'error'
			render view:'department'
			return
		}
		
		// 取默认单位类别
		def defaultCategory = params.category
		
		// 建立便根据类别名称反向查询单位类别值的方法
		def categories = [:]  // 映射：类别名称-->类别值
		def categoryValues = Constants.Department.Category.VALUES // 所有类别值
		categoryValues.each {
			// 类别名称定义在 i18n 中
			categories.put(message(code:"department.category.${it}"), it)
		}
		//  定义执行转换的闭包方法
		def toCategoryValue = { category->
			if(category in categoryValues) return category
			else return categories[category]
		}
		
		// 从提交的数据中提取出单位信息，保存并统提交结果
		// n-提交数，e-错误数，r-提交记录列表, bad-错误记录, err-错误信息
		def result = [n:0, e:0, r:[], bad:[], err:[], params:params]
		// 逐行遍历提交数据
		text.eachLine { record ->
			// 仅考虑非空记录，忽略空记录
			if(record) {
				// 统计提交记录数
				result.n++
				// 分解数据
				def a = record.split()
				// 挑选出格式正确的记录（未指定默认类别时至少4个字段，否则至少3个字段）
				if(a.length>=4 || defaultCategory && a.length>=3) {
					// 未指定单位类别时采用默认类别
					def category = a.length>3 ? a[3] : defaultCategory
					// 必要时将类别名称转换成类别值
					category = toCategoryValue(category)
					// 根据记录中的数据创建 Department 对象
					def obj = new Department(no:a[0], name:a[1], shortName:a[2], category:category)
					// 尝试保存该对象
					if(obj.save()) {
						// 保存成功，添加到提交记录列表中
						result.r << obj
					} else {
						// 保存失败，记录错误数
						result.e++ // 保存失败
						result.bad << record
						result.err << message(error:obj.errors.fieldError)
					}
				} else {
					// 数据不完整，记录错误数
					result.e++ // 数据不完整
					result.bad << record
					result.err << "数据不完整"
				}
			}
		}

		// 提交完毕，重新返回提交页面，显示提交结果
		def message = "提交数据 ${result.n} 个记录，正确导入 ${result.n-result.e} 个，错误 ${result.e} 个。"
		def type = result.n>0 && result.e==0 ? 'info' : result.e==result.n ? 'error' : 'warning' 
		displayFlashMessage(text:message, type:type)
		flash.result = result
		redirect action:'department'
	}
	
	/**
	 * 显示导入专业表单
	 */
	def specialty() { }
	/**
	 * 导入专业（根据专业代码，专业名称，专业简称，专业层次（专科，本科，硕士，博士），所属系院编号/名称/简称）
	 */
	def importSpecialty() {
		// 取数据列表
		def text = params.text
		if(!text) {
			// 未提交数据，重新显示提交页面
			displayFlashMessage text:"未提交任何数据", type:'error'
			render view:'specialty'
			return
		}

		// 取默认专业层次
		def defaultLevel = params.level
		def levels = [:]
		def levelValues = Constants.Specialty.Level.VALUES
		levelValues.each {
			levels.put(message(code:"specialty.level.${it}"), it)
		}
		def toLevelValue = { level->
			if(level in levelValues) return level
			return levels[level]
		}
		
		// 取默认所属系院
		def defaultDepartmentId = params.long('department.id')
		def defaultDepartment = Department.get(defaultDepartmentId)
		def toDepartment = { department->
			if(department instanceof Department) return department
			return Department.where { no==department || name==department || shortName==department }.get()
		}
		
		// 从提交的数据中提分解出专业信息，保存并统提交结果
		// n-提交数，e-错误数，r-提交记录列表, bad-错误记录, err-错误信息
		def result = [n:0, e:0, r:[], bad:[], err:[], params:params]
		text.eachLine { record->
			// 仅考虑非空记录，忽略空记录
			if(record) {
				// 统计提交记录数
				result.n++
				// 分解数据
				def a = record.split()
				// 挑选出格式正确的记录
				// 专业代码，专业名称，专业简称，专业层次（专科，本科，硕士，博士），所属系院编号/名称/简称
				def case1 = a.length >=5
				def case2 = defaultDepartment && a.length >=4
				def case3 = defaultDepartment && defaultLevel && a.length >=3
				if(case1 || case2 || case3) { // 注意三种情况的顺序不能调换
					// 未指定所属系院时采用默认系院
					def department = a.length>4 ? toDepartment(a[4]) : defaultDepartment
					// 未指定专业层次时采用默认层次
					def level = a.length >3 ? a[3] : defaultLevel
					level = toLevelValue(level)  // 转换为层次值
					// 根据记录中的数据创建专业对象
					def obj = new Specialty(no:a[0], name:a[1], shortName:a[2], level:level, department:department)
					// 尝试保存该对象
					if(obj.save()) {
						// 保存成功，添加到提交记录列表中
						result.r << obj
					} else {
						// 保存失败，记录错误数
						result.e++ // 保存失败
						result.bad << record
						result.err << message(error:obj.errors.fieldError)
					}
				} else {
					// 数据不完整，记录错误数
					result.e++ // 数据不完整
					result.bad << record
					result.err << "数据不完整"
				}
			}
		}

		// 提交完毕，重新返回提交页面，显示提交结果
		def message = "提交数据 ${result.n} 个记录，正确导入 ${result.n-result.e} 个，错误 ${result.e} 个。"
		def type = result.n>0 && result.e==0 ? 'info' : result.e==result.n ? 'error' : 'warning'
		displayFlashMessage(text:message, type:type)
		flash.result = result
		redirect action:'specialty'
	}
	
	/**
	 * 显示导入班级表单
	 */
	def classGrade() { }
	/**
	 * 导入班级（根据班级名称，年级，班号，所学专业名称/简称/代码）
	 */
	def importClassGrade() {
		// 取数据列表
		def text = params.text
		if(!text) {
			// 未提交数据，重新显示提交页面
			displayFlashMessage text:"未提交任何数据", type:'error'
			render view:'classGrade'
			return
		}

		// 取默认专业
		def defaultSpecialtyId = params.long('specialty.id')
		def defaultSpecialty = Specialty.get(defaultSpecialtyId)
		def toSpecialty = { specialty->
			if(specialty instanceof Specialty) return specialty
			return Specialty.where { no==specialty || name==specialty || shortName==specialty }.get()
		}

		// 从提交的数据中提分解出班级信息，保存并统提交结果
		// n-提交数，e-错误数，r-提交记录列表, bad-错误记录, err-错误信息
		def result = [n:0, e:0, r:[], bad:[], err:[], params:params]
		text.eachLine { record->
			// 仅考虑非空记录，忽略空记录
			if(record) {
				// 统计提交记录数
				result.n++
				// 分解数据
				def a = record.split()
				// 挑选出格式正确的记录
				// 班级名称，年级，班号，所学专业名称/简称/代码
				def case1 = a.length >=4
				def case2 = defaultSpecialty && a.length >=3
				if(case1 || case2) { // 注意两种情况的顺序不能调换
					// 未指定所学专业时采用默认专业
					try {
						def specialty = a.length>3 ? toSpecialty(a[3]) : defaultSpecialty
						// 根据记录中的数据创建班级对象
						def grade = Integer.valueOf(a[1])
						def classNo = Integer.valueOf(a[2])
						def obj = new ClassGrade(name:a[0], grade:grade,
								classNo:classNo, specialty:specialty)
						// 尝试保存该对象
						if(obj.save()) {
							// 保存成功，添加到提交记录列表中
							result.r << obj
						} else {
							// 保存失败，记录错误数
							result.e++ // 保存失败
							result.bad << record
							result.err << message(error:obj.errors.fieldError)
						}
					} catch (NumberFormatException e) {
						result.e++ // 数字格式错误
						result.bad << record
						result.err << "数字格式错误"
					}
				} else {
					// 数据不完整，记录错误数
					result.e++ // 数据不完整
					result.bad << record
					result.err << "数据不完整"
				}
			}
		}

		// 提交完毕，重新返回提交页面，显示提交结果
		def message = "提交数据 ${result.n} 个记录，正确导入 ${result.n-result.e} 个，错误 ${result.e} 个。"
		def type = result.n>0 && result.e==0 ? 'info' : result.e==result.n ? 'error' : 'warning'
		displayFlashMessage(text:message, type:type)
		flash.result = result
		redirect action:'classGrade'
	}
	
	/**
	 * 显示导入学生表单
	 */
	def student() { }
	/**
	 * 导入学生（根据学号，姓名，所在班级名称）
	 */
	def importStudent() {
		// 取数据列表
		def text = params.text
		if(!text) {
			// 未提交数据，重新显示提交页面
			displayFlashMessage text:"未提交任何数据", type:'error'
			render view:'student'
			return
		}
		
		// 取默认班级
		def defaultClassGradeId = params.long('classGrade.id', 0L)
		def defaultClassGrade = ClassGrade.get(defaultClassGradeId)
		def toClassGrade = { classGrade->
			if(classGrade instanceof ClassGrade) return classGrade
			return ClassGrade.where { name==classGrade }.get()
		}

		// 从提交的数据中提分解出学生信息，保存并统提交结果
		// n-提交数，e-错误数，r-提交记录列表, bad-错误记录, err-错误信息
		def result = [n:0, e:0, r:[], bad:[], err:[], params:params]
		text.eachLine { record->
			// 仅考虑非空记录，忽略空记录
			if(record) {
				// 统计提交记录数
				result.n++
				// 分解数据
				def a = record.split()
				// 挑选出格式正确的记录
				// 学号，姓名，所在班级名称
				def case1 = a.length >=3
				def case2 = defaultClassGrade && a.length >=2
				if(case1 || case2) {
					// 未指定所在班级时采用默认班级
					def classGrade = a.length>2 ? toClassGrade(a[2]) : defaultClassGrade
					// 注册学生
					try {
						def obj = registerService.registerStudent([no:a[0], name:a[1],
								password:BCrypt.gensalt(3),   // 设置随机密码
								classGrade:classGrade])
						// 注册成功，添加到提交记录列表中
						result.r << obj
					} catch (ServiceException e) {
						// 注册失败，记录错误数
						result.e++ // 保存失败
						result.bad << record
						result.err << e.message
					}
				} else {
					// 数据不完整，记录错误数
					result.e++ // 数据不完整
					result.bad << record
					result.err << "数据不完整"
				}
			}
		}

		// 提交完毕，重新返回提交页面，显示提交结果
		def message = "提交数据 ${result.n} 个记录，正确导入 ${result.n-result.e} 个，错误 ${result.e} 个。"
		def type = result.n>0 && result.e==0 ? 'info' : result.e==result.n ? 'error' : 'warning'
		displayFlashMessage(text:message, type:type)
		flash.result = result
		redirect action:'student'
	}
	
	/**
	 * 显示导入教工表单
	 */
	def staff() { }
	/**
	 * 导入教工（根据工号，姓名，所在单位名称/简称/代码）
	 */
	def importStaff() {
		// 取数据列表
		def text = params.text
		if(!text) {
			// 未提交数据，重新显示提交页面
			displayFlashMessage text:"未提交任何数据", type:'error'
			render view:'staff'
			return
		}
		
		// 取默认所属系院
		def defaultDepartmentId = params.long('department.id', 0L)
		def defaultDepartment = Department.get(defaultDepartmentId)
		def toDepartment = { department->
			if(department instanceof Department) return department
			return Department.where { no==department || name==department || shortName==department }.get()
		}

		// 从提交的数据中提分解出教工信息，保存并统提交结果
		// n-提交数，e-错误数，r-提交记录列表, bad-错误记录, err-错误信息
		def result = [n:0, e:0, r:[], bad:[], err:[], params:params]
		text.eachLine { record->
			// 仅考虑非空记录，忽略空记录
			if(record) {
				// 统计提交记录数
				result.n++
				// 分解数据
				def a = record.split()
				// 挑选出格式正确的记录
				// 工号，姓名，所在单位名称/简称/代码
				def case1 = a.length >=3
				def case2 = defaultDepartment && a.length >=2
				if(case1 || case2) {
					// 未指定所在单位时采用默认单位
					def department = a.length>2 ? toDepartment(a[2]) : defaultDepartment
					// 注册教工
					try {
						def obj = registerService.registerStaff([no:a[0], name:a[1],
								password:BCrypt.gensalt(3),   // 设置随机密码
								department:department])
						// 注册成功，添加到提交记录列表中
						result.r << obj
					} catch (ServiceException e) {
						// 注册失败，记录错误数
						result.e++ // 保存失败
						result.bad << record
						result.err << e.message
					}
				} else {
					// 数据不完整，记录错误数
					result.e++ // 数据不完整
					result.bad << record
					result.err << "数据不完整"
				}
			}
		}

		// 提交完毕，重新返回提交页面，显示提交结果
		def message = "提交数据 ${result.n} 个记录，正确导入 ${result.n-result.e} 个，错误 ${result.e} 个。"
		def type = result.n>0 && result.e==0 ? 'info' : result.e==result.n ? 'error' : 'warning'
		displayFlashMessage(text:message, type:type)
		flash.result = result
		redirect action:'staff'
	}
	
	/**
	 * 撤销并删除最近提交的记录
	 */
	def undo(String domain) {
		// 检查领域类参数
		if(!(domain in ['department', 'specialty', 'classGrade', 'student', 'staff'])) {
			displayFlashMessage text:"本方法仅用于撤销最近导入的数据", type:'error'
			redirect action:'index'
			return
		}
		// 检查参数 ids
		if(!params.ids) {
			displayFlashMessage text:"没有可以撤销的记录", type:'error'
			redirect action:domain
			return
		}
		// 取出所有待删除对象的 id 并转换为 long 列表
		def ids = params.ids instanceof String ? [ params.long(ids) ] // 参数 ids 是一个数
				: params.ids.collect { Long.valueOf(it) } // 参数 ids 是一个数组
		// 根据领域类名称得到领域类
		def domainClass = grailsApplication.getArtefactByLogicalPropertyName(
				DomainClassArtefactHandler.TYPE, domain).clazz
		// 根据 ids 查询出所有对象并删除之
		def objs = domainClass.getAll(ids).findAll { it!=null } // 只取非空对象
		domainClass.deleteAll(objs)
		// 重新返回导入数据界面
		displayFlashMessage text:"${objs.size()} 个${message(code:domain+'.label')}已删除", type:'info'
		redirect action: domain
	}
}
