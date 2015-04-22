package bzu.contest

import grails.gorm.DetachedCriteria;
import grails.plugins.springsecurity.Secured;

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;
import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_USER'])
class ContestController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST",
			doApprove:'POST', undoApprove:'POST']
	
	def contestService
	def projectApplicationService

	@Secured(['ROLE_USER'])
    def index(Integer max) {
		redirect action:'list', params:params
    }

	// 浏览赛事
	@Secured(['ROLE_USER'])
    def list(Integer max) {
		// 只列出当前用户可见的赛事
		def query = contestService.listForCurrentUser()
		// 搜索条件
		if(params.q) {
			def qlike = "%${params.q}%"
			displayMessage(text:"搜索 “${params.q}” 的结果：", type:'info')
			query = query.where { name =~ qlike || level == params.q || sponsors =~ qlike || intro =~ qlike }
		}
		// 显示列表
		params.max = Math.min(max ?: 10, 100)
		params.sort = params.sort ?: 'lastUpdated'
		params.order = params.order ?: 'desc'

		cache validFor: 5
		
		render view:'list', model:[contestInstanceList: query.list(params), contestInstanceTotal: query.count()]
	}

	@Secured(['ROLE_PROJECT'])
    def create() {
        [contestInstance: contestService.create(params)]
    }

	@Secured(['ROLE_PROJECT'])
    def save() {
		def contestInstance = new Contest(params)
        if (! contestService.save(contestInstance)) {
            render(view: "create", model: [contestInstance: contestInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'contest.label', default: 'Contest'), contestInstance.id])
		displayFlashMessage text:flash.message, type:'info'
        redirect(action: "show", id: contestInstance.id)
    }

	@Secured(['ROLE_USER'])
    def show(Long id) {
        def contestInstance = contestService.getForCurrentUser(id)
        if (! checkFound(contestInstance)) return

        [contestInstance: contestInstance]
    }

	@Secured(['ROLE_PROJECT'])
    def edit(Long id) {
        def contestInstance = Contest.get(id)
        if (! checkFound(contestInstance)) return
		// 只能由负责人修改
		if(! checkPrincipal(contestInstance)) return

        [contestInstance: contestInstance]
    }
	
	private boolean checkPrincipal(Contest contestInstance) {
		if(contestInstance.principal.id != principal.id) {
			flash.message = "对不起，您不是该赛事负责人。"
			displayFlashMessage text:flash.message, type:'error'
			render view:'show', model:[contestInstance: contestInstance]
			return false
		}
		return true
	}

	@Secured(['ROLE_PROJECT'])
    def update(Long id, Long version) {
        def contestInstance = Contest.get(id)
        if (! checkFound(contestInstance)) return
		// 只能由负责人修改
		if(! checkPrincipal(contestInstance)) return

        if (version != null) {
            if (contestInstance.version > version) {
                contestInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'contest.label', default: 'Contest')] as Object[],
                          "Another user has updated this Contest while you were editing")
                render(view: "edit", model: [contestInstance: contestInstance])
                return
            }
        }

        contestInstance.properties = params

        if (!contestInstance.save(flush: true)) {
            render(view: "edit", model: [contestInstance: contestInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'contest.label', default: 'Contest'), contestInstance.id])
		displayFlashMessage text:flash.message, type:'info'
        redirect(action: "show", id: contestInstance.id)
    }

	// 只有系统管理员作为负责人时可以删除
	// 所以，如果的确想要删除某个赛事，首先将负责人更改为一个具有系统管理权限的人，
	// 然后，系统管理员决定是否删除它。
	@Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        def contestInstance = Contest.get(id)
        if (! checkFound(contestInstance)) return
		// 只能由负责人修改
		if(! checkPrincipal(contestInstance)) return

        try {
            contestInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'contest.label', default: 'Contest'), id])
			displayFlashMessage text:flash.message, type:'info'
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'contest.label', default: 'Contest'), id])
			displayFlashMessage text:flash.message, type:'error'
            redirect(action: "show", id: id)
        }
    }
	
	private boolean checkFound(Contest contestInstance) {
		if (!contestInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'contest.label', default: 'Contest'), contestInstance.id])
			displayFlashMessage text:flash.message, type:'error'
			redirect(action: "list")
			return false
		}
		return true
	}
	
	@Secured(['ROLE_ADMIN'])
	def doApprove(Long id) {
		def contestInstance = Contest.get(id)
		if (! checkFound(contestInstance)) return
		if(! contestService.doApprove(contestInstance)) {
			displayFlashMessage text:"审核赛事失败", type:'error'
		} else {
			displayFlashMessage text:"该赛事成功通过审核", type:'info'
		}
		redirect action:'show', id:id
	}
	
	@Secured(['ROLE_ADMIN'])
	def undoApprove(Long id) {
		def contestInstance = Contest.get(id)
		if (! checkFound(contestInstance)) return
		if(! contestService.undoApprove(contestInstance)) {
			displayFlashMessage text:"撤消审核失败", type:'error'
		} else {
			displayFlashMessage text:"该赛事成功取消审核", type:'info'
		}
		redirect action:'show', id:id
	}
	
	// 申请竞赛项目
	@Secured(['ROLE_PROJECT'])
	def apply(Long id) {
		def contestInstance = Contest.get(id)
		if (! checkFound(contestInstance)) return
		if(! contestInstance.approved) {
			displayFlashMessage text:'该赛事尚未审核，不能申请竞赛项目。', type:'error'
			redirect action:'show', id:id
			return
		}
		def projectApplicationInstance = projectApplicationService.apply(contestInstance)
		render view:"/projectApplication/create", model:[projectApplicationInstance:projectApplicationInstance]
	}
}
