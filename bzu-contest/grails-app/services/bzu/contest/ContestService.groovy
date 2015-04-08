package bzu.contest

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

import bzu.ServiceException;
import bzu.Staff;
import grails.gorm.DetachedCriteria;
import grails.plugins.springsecurity.Secured;

class ContestService {
	
	def userService

	/**
	 * 新建赛事，发布者、负责人默认为当前用户
	 * @param params
	 * @return
	 */
	@Secured(['ROLE_PROJECT'])
    Contest create(params) {
		Contest contest = new Contest(params)
		contest.principal = contest.submitter = userService.currentPerson
		clearApprove(contest)  // 新建赛事处于未审核状态
		return contest
    }
	
	/**
	 * 创建赛事，发布者默认为当前用户
	 * @param params
	 * @return
	 */
	@Secured(['ROLE_PROJECT'])
	Contest save(params) {
		def contest = new Contest(params)
		contest.submitter = userService.currentPerson
		clearApprove(contest)  // 新建赛事处于未审核状态
		contest.save()
	}
	
	// 清空审核信息
	private void clearApprove(Contest contest) {
		contest.approved = false
		contest.approvedBy = null
		contest.dateApproved = null
	}
	
	/**
	 * 根据当前用户权限等情况，返回赛事列表。
	 * <ul>
	 *   <li>系统管理员：可以查看所有赛事</li>
	 *   <li>项目管理员：可以查看已审核的赛事，以及本人负责的赛事</li>
	 *   <li>普通用户：可以查看所有已审核的赛事</li>
	 *   <li>无权限用户：不能查看任何赛事</li>
	 * </ul>
	 * @return 当前用户可见的赛事列表
	 */
	DetachedCriteria<Contest> listForCurrentUser() {
		if(SpringSecurityUtils.ifAnyGranted("ROLE_ADMIN")) {
			return Contest.where {}
		} else if(SpringSecurityUtils.ifAnyGranted("ROLE_PROJECT")) {
			return Contest.where { approved==true || principal.id==userService.currentPerson.id }
		} else if(SpringSecurityUtils.ifAnyGranted("ROLE_USER")) {
			return Contest.where { approved==true }
		} else {
			return Collections.emptyList() // have no right
		}
	}
	
	/**
	 * 根据当前用户情况，查询某项赛事。
	 * <ul>
	 *   <li>系统管理员：可以查看所有赛事</li>
	 *   <li>项目管理员：可以查看已审核的赛事，以及本人负责的赛事</li>
	 *   <li>普通用户：可以查看所有已审核的赛事</li>
	 *   <li>无权限用户：不能查看任何赛事</li>
	 * </ul>
	 * @param id 赛事 ID
	 * @return 该赛事，如果当前用户可见；否则，<code>null</code>
	 */
	Contest getForCurrentUser(Long id) {
		def contest = Contest.get(id)
		if(! contest) return null
		if(userService.hasAdminRole()
				|| userService.hasUserRole() && contest.approved
				|| userService.hasProjectRole() && isPrincipalOf(contest)) {
			return contest
		} else {
			return null // have no right
		}
	}
	
	/**
	 * 判断当前用户是否为指定赛事的负责人
	 * @param contest
	 * @return
	 */
	boolean isPrincipalOf(Contest contest) {
		contest.principal.id == userService.currentPerson.id
	}
	
	/**
	 * 审核通过指定赛事
	 * @param contest
	 * @return <code>true</code>，若成功；否则，<code>false</code>
	 */
	@Secured(['ROLE_ADMIN'])
	boolean doApprove(Contest contest) {
		if(userService.approved && userService.hasAdminRole()) {
			setApprove(contest)
			contest.save()
		} else {
			return false
		}
	}
	
	/**
	 * 取消审核指定赛事
	 * @param contest
	 * @return <code>true</code>，若成功；否则，<code>false</code>
	 */
	@Secured(['ROLE_ADMIN'])
	boolean undoApprove(Contest contest) {
		if(userService.approved && userService.hasAdminRole()) {
			clearApprove(contest)
			contest.save()
		} else {
			return false
		}
	}
	
	// 记录当前用户审核赛事的信息
	private void setApprove(Contest contest) {
		contest.approved = true
		contest.approvedBy = userService.currentPerson
		contest.dateApproved = new Date()
	}
	
	/**
	 * 更改赛事负责人
	 * @param contest 赛事
	 * @param staff 新负责人
	 * @return <code>true</code>，若成功；否则，<code>false</code>
	 */
	@Secured(['ROLE_PROJECT'])
	boolean changePrincipal(Contest contest, Staff staff) {
		if(userService.approved && isPrincipalOf(contest)
				&& userService.hasProjectRole()) {
			contest.principal = staff
			contest.save()
		} else {
			return false
		}
	}
}
