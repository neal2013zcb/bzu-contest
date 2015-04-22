package bzu.contest

import org.springframework.dao.DataIntegrityViolationException

class ProjectApplicationController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def projectApplicationService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [projectApplicationInstanceList: ProjectApplication.list(params), projectApplicationInstanceTotal: ProjectApplication.count()]
    }

    def save() {
		params.startDate = params.date('startDate', 'yyyy-MM-dd')
		params.endDate = params.date('endDate', 'yyyy-MM-dd')
        def projectApplicationInstance = projectApplicationService.create(params)
        if (!projectApplicationInstance.save(flush: true)) {
            render(view: "create", model: [projectApplicationInstance: projectApplicationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'projectApplication.label', default: 'ProjectApplication'), projectApplicationInstance.id])
        redirect(action: "show", id: projectApplicationInstance.id)
    }

    def show(Long id) {
        def projectApplicationInstance = ProjectApplication.get(id)
        if (!projectApplicationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectApplication.label', default: 'ProjectApplication'), id])
            redirect(action: "list")
            return
        }

        [projectApplicationInstance: projectApplicationInstance]
    }

    def edit(Long id) {
        def projectApplicationInstance = ProjectApplication.get(id)
        if (!projectApplicationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectApplication.label', default: 'ProjectApplication'), id])
            redirect(action: "list")
            return
        }

        [projectApplicationInstance: projectApplicationInstance]
    }

    def update(Long id, Long version) {
        def projectApplicationInstance = ProjectApplication.get(id)
        if (!projectApplicationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectApplication.label', default: 'ProjectApplication'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (projectApplicationInstance.version > version) {
                projectApplicationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'projectApplication.label', default: 'ProjectApplication')] as Object[],
                          "Another user has updated this ProjectApplication while you were editing")
                render(view: "edit", model: [projectApplicationInstance: projectApplicationInstance])
                return
            }
        }

        projectApplicationInstance.properties = params

        if (!projectApplicationInstance.save(flush: true)) {
            render(view: "edit", model: [projectApplicationInstance: projectApplicationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'projectApplication.label', default: 'ProjectApplication'), projectApplicationInstance.id])
        redirect(action: "show", id: projectApplicationInstance.id)
    }

    def delete(Long id) {
        def projectApplicationInstance = ProjectApplication.get(id)
        if (!projectApplicationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'projectApplication.label', default: 'ProjectApplication'), id])
            redirect(action: "list")
            return
        }

        try {
            projectApplicationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'projectApplication.label', default: 'ProjectApplication'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'projectApplication.label', default: 'ProjectApplication'), id])
            redirect(action: "show", id: id)
        }
    }
}
