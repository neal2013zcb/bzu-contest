package bzu.security

import bzu.Person;

/**
 * 登录账号相关的标记
 * 
 * @author zhbo
 *
 */
class UserTagLib {
	
	static namespace = "bzu"
	static returnObjectForTags = ['user']
	
	def userService
	
	def user = { attrs ->
		def property = attrs.property
		if(property=='password') {
			'*'
		} else {
			def user = userService.currentUser
			def person = user.person // need eager fetching in User.person
			user?.properties[property] ?: person?.properties[property]
		}
	}

	def username = {
		out << userService.currentUser?.username
	}
}
