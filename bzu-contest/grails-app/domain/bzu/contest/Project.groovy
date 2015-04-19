package bzu.contest

import java.util.Date;

import bzu.Department;
import bzu.Staff;

/**
 * 竞赛项目
 * 
 * @author zhbo
 *
 */
class Project {
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
	 * 竞赛年度
	 */
	int year
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

    static constraints = {
    }
}
