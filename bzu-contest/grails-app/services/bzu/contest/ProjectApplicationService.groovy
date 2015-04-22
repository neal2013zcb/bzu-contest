package bzu.contest

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

import grails.gorm.DetachedCriteria;
import grails.plugins.springsecurity.Secured;
import bzu.Constants;
import bzu.security.UserService;

class ProjectApplicationService {
	
	UserService userService
	
	/**
	 * 申请某一赛事的竞赛项目
	 * 
	 * @param contest 所属赛事
	 * @return 竞赛项目申请
	 */
    ProjectApplication apply(Contest contest) {
		if(contest.approved) {
			// 新建竞赛项目申请
			def projectApp = new ProjectApplication(contest:contest)
			// 从所属赛事中复制基本属性
			projectApp.properties['sponsors','level'] = contest.properties
			// 初始化新申请
			initNew(projectApp)
			// 返回创建的对象
			return projectApp
		} else {
			return null
		}
    }
	
	/**
	 * 新建竞赛项目申请
	 * 
	 * @param params
	 * @return 竞赛项目申请
	 */
	ProjectApplication create(params) {
		// 新建竞赛项目申请对象
		def projectApp = new ProjectApplication(params)
		// 初始化新申请
		initNew(projectApp)
		// 返回创建的对象
		return projectApp
	}
	
	// 初始化新项目申请相关属性（提交者、负责人、状态、审批情况）
	private void initNew(ProjectApplication projectApp) {
		// 提交者为当前用户
		def currentPerson = userService.currentPerson
		projectApp.submitter = currentPerson
		// 负责人默认为当前用户
		projectApp.principal = currentPerson
		// 依托单位为负责人单位
		projectApp.department = currentPerson.department
		// 当前状态为待审批
		projectApp.status = Constants.ProjectApplication.Status.TBD
		// 审批情况为空
		projectApp.approved = false
		projectApp.approvedBy = null
		projectApp.dateApproved = null
	}
	
	// 文件操作相关功能调用 FileUploaderService 相关功能
	// 相关配置在 Config.groovy 的 fileuploader.projectApplication
	
	/**
	 * 创建新的项目申请
	 * 
	 * @param projectApp
	 * @return
	 */
	def save(ProjectApplication projectApp) {
		// 初始化新对象属性
		initNew(projectApp)
		// 保存对象
		return projectApp.save()
	}

	/**
	 * 根据当前用户权限等情况，返回竞赛项目申请列表。
	 * <ul>
	 *   <li>系统管理员：可以查看所有竞赛项目申请</li>
	 *   <li>项目管理员：可以查看本人负责的竞赛项目申请</li>
	 *   <li>系院管理员：可以查看本单位的竞赛项目申请</li>
	 *   <li>其他用户：不能查看任何竞赛项目申请</li>
	 * </ul>
	 * @return 当前用户可见的竞赛项目申请列表
	 */
	DetachedCriteria<ProjectApplication> listForCurrentUser() {
		if(SpringSecurityUtils.ifAnyGranted("ROLE_ADMIN")) {
			return ProjectApplication.where {}
		} else if(SpringSecurityUtils.ifAnyGranted("ROLE_PROJECT")) {
			return ProjectApplication.where { principal.id==userService.currentPerson.id }
		} else if(SpringSecurityUtils.ifAnyGranted("ROLE_DEPARTMENT")) {
			return ProjectApplication.where { department.id == userService.currentDepartment.id }
		} else {
			return Collections.emptyList() // have no right
		}
	}
	
	/**
	 * 根据当前用户情况，查询某项竞赛项目申请。
	 * <ul>
	 *   <li>系统管理员：可以查看所有竞赛项目申请</li>
	 *   <li>项目管理员：可以查看本人负责的竞赛项目申请</li>
	 *   <li>系院管理员：可以查看本单位的竞赛项目申请</li>
	 *   <li>其他用户：不能查看任何竞赛项目申请</li>
	 * </ul>
	 * @param id 竞赛项目申请 ID
	 * @return 该竞赛项目申请，如果当前用户可见；否则，<code>null</code>
	 */
	ProjectApplication getForCurrentUser(Long id) {
		def projectApp = ProjectApplication.get(id)
		if(! projectApp) return null
		if(userService.hasAdminRole()
				|| userService.hasProjectRole() && userService.isPrincipalOf(projectApp)
				|| userService.hasDepartmentRole()
					&& userService.currentDepartment.id==projectApp.department.id) {
			return projectApp
		} else {
			return null // have no right
		}
	}
	
	/**
	 * 判断当前用户是否为指定竞赛项目申请的负责人
	 * @param projectApp
	 * @return
	 */
	boolean isPrincipalOf(ProjectApplication projectApp) {
		userService.isPrincipalOf(projectApp)
	}
	
	@Secured(['ROLE_ADMIN'])
	boolean doFialed(ProjectApplication projectApp) {
		if(userService.approved && userService.hasAdminRole()) {
			projectApp.status = Constants.ProjectApplication.Status.FAILED
			projectApp.approved = false
			projectApp.approvedBy = userService.currentPerson
			projectApp.dateApproved = new Date()
			projectApp.save()
		} else {
			return false
		}
	}
	
	@Secured(['ROLE_ADMIN'])
	boolean doTBD(ProjectApplication projectApp) {
		if(userService.approved && userService.hasAdminRole()) {
			projectApp.status = Constants.ProjectApplication.Status.TBD
			projectApp.approved = false
			projectApp.approvedBy = null
			projectApp.dateApproved = null
			projectApp.save()
		} else {
			return false
		}
	}
	
	// 立项
}
