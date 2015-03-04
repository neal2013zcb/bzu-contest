package bzu

import bzu.security.User;

/**
 * 个人信息
 * 
 * @author zhbo
 *
 */
class Person {
	/**
	 * 编号（如：学号，工号，教师号等），必填，唯一的，3~20个字符，注册账号时作为用户名。
	 */
	String no
	/**
	 * 姓名，必填，长度50个字符。
	 */
	String name
	/**
	 * 性别（X未知，M男，F女），必填。
	 */
	String gender = Constants.Person.Gender.UNKNOWN
	/**
	 * 类别（0其他，1学生，2员工，3VIP）。
	 */
	String category = Constants.Person.Category.UNKNOWN
	/**
	 * 办公电话，可选，支持手机和固话及分机格式。
	 */
	String officePhone
	/**
	 * 移动电话，可选。
	 */
	String cellPhone
	/**
	 * 电邮，可选。
	 */
	String email
	/**
	 * QQ，可选。
	 */
	String qq
	/**
	 * 微信，可选。
	 */
	String weixin
	/**
	 * 登录账号
	 */
	User account
	
	static hasOne = [account:User]
	
    static constraints = {
		no nullable:false, blank:false, unique:true, size:3..20
		name nullable:false, blank:false, maxSize:50
		gender nullable:false, blank:false, inList:Constants.Person.Gender.VALUES, maxSize:1
		category nullable:false, blank:false, inList:Constants.Person.Category.VALUES, maxSize:1
		officePhone nullable:true, blank:true, maxSize:20, shared:'phone_matches'
		cellPhone nullable:true, blank:true, maxSize:20, shared:'phone_matches'
		email nullable:true, blank:true, email:true, maxSize:50
		qq nullable:true, blank:true, maxSize:20, shared:'qq_matches'
		weixin nullable:true, blank:true, maxSize:20, shared:'weixin_matches'
    }
	
	static mapping = {
		sort 'no'
	}
	
	/**
	 * 姓名及编号
	 */
	String toString() {
		"${name}[${no}]"
	}
}
