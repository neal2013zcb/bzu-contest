package bzu

import grails.plugins.springsecurity.Secured;

import org.springframework.dao.DataIntegrityViolationException

class SpecialtyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def beforeInterceptor = [action: this.&checkDepartment, except: ['index','list','show','create']]
	
	def userService
	
	private checkDepartment() {
		def myDepartmentId = userService.currentDepartment.id
		def depId = params.long('department.id', 0) ?: Specialty.get(params.long('id'))?.department?.id
		if(myDepartmentId != depId) {
			displayFlashMessage text:'对不起，您不能修改其他系院的信息', type:'error'
			redirect controller:'errors', action:'forbidden'
			return false
		}
	}

	@Secured(['ROLE_USER'])
    def index() {
        redirect(action: "list", params: params)
    }

	@Secured(['ROLE_USER'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [specialtyInstanceList: Specialty.list(params), specialtyInstanceTotal: Specialty.count()]
    }

	@Secured(['ROLE_DEPARTMENT'])
    def create() {
        [specialtyInstance: new Specialty(params)]
    }

	@Secured(['ROLE_DEPARTMENT'])
    def save() {
        def specialtyInstance = new Specialty(params)
        if (!specialtyInstance.save(flush: true)) {
            render(view: "create", model: [specialtyInstance: specialtyInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'specialty.label', default: 'Specialty'), specialtyInstance.id])
        redirect(action: "show", id: specialtyInstance.id)
    }

	@Secured(['ROLE_USER'])
    def show(Long id) {
        def specialtyInstance = Specialty.get(id)
        if (!specialtyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'specialty.label', default: 'Specialty'), id])
            redirect(action: "list")
            return
        }

        [specialtyInstance: specialtyInstance]
    }

	@Secured(['ROLE_DEPARTMENT'])
    def edit(Long id) {
        def specialtyInstance = Specialty.get(id)
        if (!specialtyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'specialty.label', default: 'Specialty'), id])
            redirect(action: "list")
            return
        }

        [specialtyInstance: specialtyInstance]
    }

	@Secured(['ROLE_DEPARTMENT'])
    def update(Long id, Long version) {
        def specialtyInstance = Specialty.get(id)
        if (!specialtyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'specialty.label', default: 'Specialty'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (specialtyInstance.version > version) {
                specialtyInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'specialty.label', default: 'Specialty')] as Object[],
                          "Another user has updated this Specialty while you were editing")
                render(view: "edit", model: [specialtyInstance: specialtyInstance])
                return
            }
        }

        specialtyInstance.properties = params

        if (!specialtyInstance.save(flush: true)) {
            render(view: "edit", model: [specialtyInstance: specialtyInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'specialty.label', default: 'Specialty'), specialtyInstance.id])
        redirect(action: "show", id: specialtyInstance.id)
    }

	@Secured(['ROLE_DEPARTMENT'])
    def delete(Long id) {
        def specialtyInstance = Specialty.get(id)
        if (!specialtyInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'specialty.label', default: 'Specialty'), id])
            redirect(action: "list")
            return
        }

        try {
            specialtyInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'specialty.label', default: 'Specialty'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'specialty.label', default: 'Specialty'), id])
            redirect(action: "show", id: id)
        }
    }
}
