package bzu.security

import bzu.contest.Person;

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	/**
	 * 本账号所属的个人
	 */
	Person person
	
	static belongsTo = [person:Person]

	static constraints = {
		username nullable: false, blank: false, unique: true, maxSize: 20
		password blank: false, maxSize: 80
		person nullable:false
	}

	static mapping = {
		password column: '`password`'
		version false
		sort 'username'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
