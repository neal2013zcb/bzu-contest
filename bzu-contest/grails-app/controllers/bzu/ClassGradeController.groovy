package bzu

import org.springframework.dao.DataIntegrityViolationException

class ClassGradeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [classGradeInstanceList: ClassGrade.list(params), classGradeInstanceTotal: ClassGrade.count()]
    }

    def create() {
        [classGradeInstance: new ClassGrade(params)]
    }

    def save() {
        def classGradeInstance = new ClassGrade(params)
        if (!classGradeInstance.save(flush: true)) {
            render(view: "create", model: [classGradeInstance: classGradeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), classGradeInstance.id])
        redirect(action: "show", id: classGradeInstance.id)
    }

    def show(Long id) {
        def classGradeInstance = ClassGrade.get(id)
        if (!classGradeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), id])
            redirect(action: "list")
            return
        }

        [classGradeInstance: classGradeInstance]
    }

    def edit(Long id) {
        def classGradeInstance = ClassGrade.get(id)
        if (!classGradeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'classGrade.label', default: 'ClassGrade'), id])
            redirect(action: "list")
            return
        }

        [classGradeInstance: classGradeInstance]
    }

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
