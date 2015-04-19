package bzu.contest

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
    def apply(Contest contest) {
		// 新建竞赛项目申请
		def projectApp = new ProjectApplication(contest:contest)
		// 从所属赛事中复制基本属性
		projectApp.properties['name','sponsors','level'] = contest.properties
		// 初始化新申请
		initNew(projectApp)
		// 返回创建的对象
		return projectApp
    }
	
	/**
	 * 新建竞赛项目申请
	 * 
	 * @param params
	 * @return 竞赛项目申请
	 */
	def create(params) {
		// 新建竞赛项目申请对象
		def projectApp = new ProjectApplication()
		// 设置参数
		projectApp.properties = params
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

/*
venues
startDate
endDate
requestAppropriation
department
principal
submitter
dateCreated
status
approved
approvedBy
dateApproved
*/