package bzu.contest

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
	 * 性别，必填。
	 */
	String gender
	/**
	 * 电话，可选，支持手机和固话及分机格式。
	 */
	String phone
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

    static constraints = {
		no nullable:false, blank:false, unique:true, size:3..20
		name nullable:false, blank:false, maxSize:50
		gender nullable:false, inList:['-','M','F'], maxSize:1
		phone nullable:true, maxSize:20, shared:'phone_matches'
		email nullable:true, email:true, maxSize:50
		qq nullable:true, maxSize:20, shared:'qq_matches'
		weixin nullable:true, maxSize:20, shared:'weixin_matches'
    }
	
	/**
	 * 姓名及编号
	 */
	String toString() {
		"${name}[${no}]"
	}
}
