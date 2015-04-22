package bzu.contest

import java.util.Date;

import fileuploader.UFile;
import bzu.Constants;
import bzu.Department;
import bzu.Staff;

/**
 * 竞赛项目申请：申请举办一次竞赛。
 * 
 * @see bzu.contest.Contest
 * @see bzu.contest.Project
 * @author zhbo
 */
class ProjectApplication {
	/**
	 * 所属赛事
	 */
	Contest contest
	/**
	 * 竞赛名称
	 */
	String name
	/**
	 * 主办单位
	 */
	String sponsors
	/**
	 * 赛事等级（A—国际竞赛（决赛）类；B—全国竞赛（决赛）类；C—省部级竞赛（决赛）类；D—其他类）
	 */
	String level
	/**
	 * 竞赛形式
	 */
	String approach
	/**
	 * 竞赛地点
	 */
	String venues
	/**
	 * 竞赛开始时间
	 */
	Date startDate
	/**
	 * 竞赛结束时间
	 */
	Date endDate
	
	/**
	 * 依托单位
	 */
	Department department
	/**
	 * 负责人，进行信息维护。
	 */
	Staff principal
	
	/**
	 * 申请书
	 */
	UFile application
	/**
	 * 竞赛方案
	 */
	UFile program
	/**
	 * 竞赛预算
	 */
	UFile budget
	
	/**
	 * 提交人
	 */
	Staff submitter
	/**
	 * 提交时间
	 */
	Date dateCreated

	/**
	 * 申请状态，‘0’：待审批，‘1’：已立项，‘2’：未通过。
	 */
	String status = Constants.ProjectApplication.Status.TBD
	/**
	 * 是否立项
	 */
	boolean approved = false
	/**
	 * 审批人，由系统管理员审批立项。
	 */
	Staff approvedBy
	/**
	 * 审批时间
	 */
	Date dateApproved
	
	static belongsTo = [contest: Contest]
	
    static constraints = {
		contest nullable:false
		name nullable:false, blank:false, unique:true, maxSize:100
		sponsors nullable:false, blank:false, maxSize:120
		level nullable:false, blank:false, maxSize:1, inList:Constants.Contest.Level.VALUES
		approach nullable:false, blank:false, maxSize:100
		venues nullable:false, blank:false, maxSize:100
		startDate nullable:false
		endDate nullable:false
		department nullable:false
		principal nullable:false // current user as default
		submitter nullable:false // MUST be current user
		dateCreated nullable:true // auto time-stamp
		status nullable:false, blank:false, maxSize:1, inList:Constants.ProjectApplication.Status.VALUES
		approved nullable:false
		approvedBy nullable:true
		dateApproved nullable:true
		application nullable:true
		program nullable:true
		budget nullable:true
    }
	
	String toString() {
		name
	}
}
