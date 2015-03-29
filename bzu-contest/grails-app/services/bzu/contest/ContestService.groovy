package bzu.contest

import grails.plugins.springsecurity.Secured;

class ContestService {
	
	def userService

	/**
	 * 新建赛事，发布者、负责人默认为当前用户，
	 * @param params
	 * @return
	 */
	@Secured(['ROLE_DEPARTMENT','ROLE_PROJECT'])
    Contest create(params) {
		Contest contest = new Contest(params)
		contest.principal = contest.submitter = userService.currentPerson
		contest.approved = false
		contest.approvedBy = null
		contest.dateApproved = null
		return contest
    }
}
