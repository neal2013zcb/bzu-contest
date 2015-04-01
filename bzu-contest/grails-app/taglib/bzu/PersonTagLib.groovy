package bzu

/**
 * 个人信息相关的标记
 * 
 * @author zhbo
 *
 */
class PersonTagLib {
	
	static namespace = "bzu"
	
	def userService
	
	/**
	 * 指定登录账号 user、个人信息 person 或当前用户是教师则输出
	 * 
     * @attr person 个人信息对象
     * @attr user 登录账号
	 */
	def ifStaff = { attrs, body ->
		def person = retrievePerson(attrs)
		if(person && person instanceof Staff) {
			out << body()
		}
	}
	
	/**
	 * 指定登录账号 user、个人信息 person 或当前用户是学生则输出
	 * 
     * @attr person 个人信息对象
     * @attr user 登录账号
	 */
	def ifStudent = { attrs, body ->
		def person = retrievePerson(attrs)
		if(person && person instanceof Student) {
			out << body()
		}
	}
	
	private Person retrievePerson(attrs) {
		attrs.person ?: attrs.user?.person ?: userService.currentPerson
	}
	
	/**
	 * 指定用户的个人信息通过审核
	 * 
     * @attr person 个人信息对象
     * @attr user 登录账号
	 */
	def ifApproved = { attrs, body ->
		def person = retrievePerson(attrs)
		if(person.approved) {
			out << body()
		}
	}
	
	/**
	 * 指定用户的个人信息未通过审核
	 * 
     * @attr person 个人信息对象
     * @attr user 登录账号
	 */
	def ifNotApproved = { attrs, body ->
		def person = retrievePerson(attrs)
		if(! person.approved) {
			out << body()
		}
	}

	/**
	 * 指定用户与当前用户同属一个单位
	 * 
     * @attr person 个人信息对象
     * @attr user 登录账号
	 */
	def ifSameDepartment = { attrs, body ->
		def person = retrievePerson(attrs)
		if(person.department.id == userService.currentDepartment.id) {
			out << body()
		}
	}
	
	/**
	 * 指定用户与当前用户不属一个单位
	 * 
     * @attr person 个人信息对象
     * @attr user 登录账号
	 */
	def ifNotSameDepartment = { attrs, body ->
		def person = retrievePerson(attrs)
		if(person.department.id != userService.currentDepartment.id) {
			out << body()
		}
	}
	
	def ifPrincipal = { attrs, body ->
		def target = attrs.remove('target')
		if(target?.principal?.id == userService.currentPerson.id) {
			out << body()
		}
	}
	
	def ifNotPrincipal = { attrs, body ->
		def target = attrs.remove('target')
		if(target?.principal?.id == userService.currentPerson.id) {
			out << body()
		}
	}
}
