package bzu.contest

import grails.plugins.springsecurity.Secured;

import org.springframework.dao.DataIntegrityViolationException

class ContestController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def contestService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [contestInstanceList: Contest.list(params), contestInstanceTotal: Contest.count()]
    }

	@Secured(['ROLE_DEPARTMENT','ROLE_PROJECT'])
    def create() {
		def contestInstance = contestService.create(params)
		println contestInstance.properties
        [contestInstance: contestInstance]
    }

    def save() {
        def contestInstance = new Contest(params)
        if (!contestInstance.save(flush: true)) {
            render(view: "create", model: [contestInstance: contestInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'contest.label', default: 'Contest'), contestInstance.id])
        redirect(action: "show", id: contestInstance.id)
    }

    def show(Long id) {
        def contestInstance = Contest.get(id)
        if (!contestInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contest.label', default: 'Contest'), id])
            redirect(action: "list")
            return
        }

        [contestInstance: contestInstance]
    }

    def edit(Long id) {
        def contestInstance = Contest.get(id)
        if (!contestInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contest.label', default: 'Contest'), id])
            redirect(action: "list")
            return
        }

        [contestInstance: contestInstance]
    }

    def update(Long id, Long version) {
        def contestInstance = Contest.get(id)
        if (!contestInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contest.label', default: 'Contest'), id])
            redirect(action: "list")
            return
        }

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
        redirect(action: "show", id: contestInstance.id)
    }

    def delete(Long id) {
        def contestInstance = Contest.get(id)
        if (!contestInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'contest.label', default: 'Contest'), id])
            redirect(action: "list")
            return
        }

        try {
            contestInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'contest.label', default: 'Contest'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'contest.label', default: 'Contest'), id])
            redirect(action: "show", id: id)
        }
    }
}
