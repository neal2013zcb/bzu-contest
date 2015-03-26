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
	 * 办公电话，可选，支持手机和固话及分机格式。
	 */
	String workPhone
	/**
	 * 家庭电话，可选，支持手机和固话及分机格式。。
	 */
	String homePhone
	/**
	 * 电邮，可选。
	 */
	String email
	/**
	 * 已审核
	 */
	boolean approved = false
	/**
	 * 审核人
	 */
	Person verifier = null
	/**
	 * 审核时间
	 */
	Date dateOfApproval = null
	
	/**
	 * 登录账号
	 */
	User account
	
	static hasOne = [account:User]
	
    static constraints = {
		no nullable:false, blank:false, unique:true, maxSize:20, matches:/[A-Za-z0-9_\-]{3,20}/
		name nullable:false, blank:false, maxSize:50
		gender nullable:false, blank:false, maxSize:1, inList:Constants.Person.Gender.VALUES
		workPhone nullable:true, blank:true, maxSize:20, shared:'phone_matches'
		homePhone nullable:true, blank:true, maxSize:20, shared:'phone_matches'
		email nullable:true, blank:true, maxSize:50, email:true
		account nullable:true
		approved nullable:false
		verifier nullable:true
		dateOfApproval nullable:true
    }
	
	static mapping = {
		sort 'no'
		tablePerHierarchy false // 每个子类一个表
	}
	
	/**
	 * 姓名及编号
	 */
	String toString() {
		"${name}[${no}]"
	}
}
