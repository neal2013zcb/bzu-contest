package bzu

import grails.plugins.springsecurity.Secured;

import org.springframework.dao.DataIntegrityViolationException

class ClassGradeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def beforeInterceptor = [action: this.&checkDepartment, except: ['index','list','show','create']]
	
	def userService
	
	private checkDepartment() {
		def myDepartmentId = userService.currentDepartment.id
		def sepId = params.long('specialty.id', 0)
		def specialty = sepId ? Specialty.get(sepId) : ClassGrade.get(params.long('id'))?.specialty
		if(myDepartmentId != specialty.department.id) {
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
        [classGradeInstanceList: ClassGrade.list(params), classGradeInstanceTotal: ClassGrade.count()]
    }

	@Secured(['ROLE_DEPARTMENT'])
    def create() {
        [classGradeInstance: new ClassGrade(params)]
    }

	@Secured(['ROLE_DEPARTMENT'])
    def save() {
        def classGradeInstance = new ClassGrade(params)
        if (!classGradeInstance.save(flush: true)) {
            render(view: "create", model: [classGradeInstance: classGradeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), classGradeInstance.id])
        redirect(action: "show", id: classGradeInstance.id)
    }

	@Secured(['ROLE_USER'])
    def show(Long id) {
        def classGradeInstance = ClassGrade.get(id)
        if (!classGradeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), id])
            redirect(action: "list")
            return
        }

        [classGradeInstance: classGradeInstance]
    }

	@Secured(['ROLE_DEPARTMENT'])
    def edit(Long id) {
        def classGradeInstance = ClassGrade.get(id)
        if (!classGradeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), id])
            redirect(action: "list")
            return
        }

        [classGradeInstance: classGradeInstance]
    }

	@Secured(['ROLE_DEPARTMENT'])
    def update(Long id, Long version) {
        def classGradeInstance = ClassGrade.get(id)
        if (!classGradeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (classGradeInstance.version > version) {
                classGradeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'classGrade.label', default: 'ClassGrade')] as Object[],
                          "Another user has updated this ClassGrade while you were editing")
                render(view: "edit", model: [classGradeInstance: classGradeInstance])
                return
            }
        }

        classGradeInstance.properties = params

        if (!classGradeInstance.save(flush: true)) {
            render(view: "edit", model: [classGradeInstance: classGradeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), classGradeInstance.id])
        redirect(action: "show", id: classGradeInstance.id)
    }

	@Secured(['ROLE_DEPARTMENT'])
    def delete(Long id) {
        def classGradeInstance = ClassGrade.get(id)
        if (!classGradeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), id])
            redirect(action: "list")
            return
        }

        try {
            classGradeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), id])
            redirect(action: "show", id: id)
        }
    }
}
