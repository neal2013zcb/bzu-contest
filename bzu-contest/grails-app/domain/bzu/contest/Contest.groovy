package bzu.contest

import bzu.Constants;
import bzu.Staff;

/**
 * 赛事：一项赛事可举办多届，参见竞赛需要申请竞赛项目并立项。
 * 
 * @see bzu.contest.Project
 * @author zhbo
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
	 * 赛事等级（A—国际竞赛（决赛）类；B—全国竞赛（决赛）类；C—省部级竞赛（决赛）类；D—其他类）
	 */
	String level
	/**
	 * 简介
	 */
	String intro
	/**
	 * 网址
	 */
	String website
	/**
	 * 标识
	 */
	String logo
	
	/**
	 * 发布人，单位管理员或系统管理员可以发布赛事，审核之前，可以修改赛事信息
	 */
	Staff submitter
	/**
	 * 发布时间
	 */
	Date dateCreated
	
	/**
	 * 审核，赛事信息经过审核后，只能由负责人或系统管理员修改。
	 */
	boolean approved = false
	/**
	 * 审核人，赛事由系统管理员审核
	 */
	Staff approvedBy
	/**
	 * 审核时间
	 */
	Date dateApproved
	
	/**
	 * 负责人，经审核后负责维护赛事信息，需要时本人或系统管理员可以更换负责人。
	 */
	Staff principal
	/**
	 * 更新时间
	 */
	Date lastUpdated

    static constraints = {
		name nullable:false, blank:false, unique:true
		sponsor nullable:false, blank:false
		level nullable:false, blank:false, maxSize:1, inList:Constants.Contest.Level.VALUES
		intro nullable:false, blank:false
		website nullable:true, blank:true
		logo nullable:true, blank:true
		submitter nullable:false // MUST be current user
		principal nullable:false // current user as default
		approved nullable:false
		approvedBy nullable:true
		dateApproved nullable:true
		dateCreated nullable:true // auto time-stamp
		lastUpdated nullable:true // auto time-stamp
    }
	
	static mapping = {
		level index:'level_idx'
	}
	
	String toString() {
		name
	}
}
