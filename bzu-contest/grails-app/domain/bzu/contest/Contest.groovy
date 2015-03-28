package bzu.contest

import bzu.Staff;

/**
 * 赛事
 * 
 * @author zhbo
 *
 */
class Contest {
	/**
	 * 赛事名称
	 */
	String name
	/**
	 * 主办单位
	 */
	String sponsor
	/**
	 * 简介
	 */
	String introduction
	/**
	 * 网址
	 */
	String website
	
	/**
	 * 发布人，单位管理员或系统管理员可以发布赛事
	 */
	Staff submitter
	/**
	 * 负责人，维护赛事信息
	 */
	Staff principal
	/**
	 * 审核人，赛事由系统管理员审核
	 */
	Staff verifier
	
	Date dateCreated
	Date lastUpdated


    static constraints = {
    }
}
