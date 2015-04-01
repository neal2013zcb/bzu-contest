package bzu.contest

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

import bzu.ServiceException;
import grails.gorm.DetachedCriteria;
import grails.plugins.springsecurity.Secured;

class ContestService {
	
	def userService

	/**
	 * 新建赛事，发布者、负责人默认为当前用户，
	 * @param params
	 * @return
	 */
	@Secured(['ROLE_PROJECT'])
    Contest create(params) {
		Contest contest = new Contest(params)
		contest.principal = contest.submitter = userService.currentPerson
		clearApprove(contest)
		return contest
    }
	
	@Secured(['ROLE_PROJECT'])
	Contest save(params) {
		def contest = new Contest(params)
		contest.submitter = userService.currentPerson
		clearApprove(contest)
		contest.save()
	}
	
	private void clearApprove(Contest contest) {
		contest.approved = false
		contest.approvedBy = null
		contest.dateApproved = null
	}
	
	DetachedCriteria<Contest> listForCurrentUser() {
		if(SpringSecurityUtils.ifAnyGranted("ROLE_ADMIN")) {
			return Contest.where {}
		} else if(SpringSecurityUtils.ifAnyGranted("ROLE_PROJECT")) {
			return Contest.where { approved==true || principal.id==userService.currentPerson.id }
		} else if(SpringSecurityUtils.ifAnyGranted("ROLE_USER")) {
			return Contest.where { approved==true }
		} else {
			return null // have no right
		}
	}
	
	Contest getForCurrentUser(Long id) {
		def contest = Contest.get(id)
		if(! contest) return null
		if(userService.hasAdminRole()
				|| userService.hasProjectRole() && isPrincipalOf(contest)
				|| userService.hasUserRole() &&
					contest.approved) {
			return contest
		} else {
			return null // have no right
		}
	}
	
	boolean isPrincipalOf(Contest contest) {
		contest.principal.id == userService.currentPerson.id
	}
	
	boolean doApprove(Contest contest) {
		if(userService.approved && userService.hasAdminRole()) {
			contest.approved = true
			contest.approvedBy = userService.currentPerson
			contest.dateApproved = new Date()
			contest.save()
		} else {
			return false
		}
	}
	
	boolean undoApprove(Contest contest) {
		if(userService.approved && userService.hasAdminRole()) {
			clearApprove(contest)
			contest.save()
		} else {
			return false
		}
	}
}
