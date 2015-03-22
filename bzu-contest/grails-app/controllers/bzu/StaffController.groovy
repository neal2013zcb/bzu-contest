package bzu

import org.springframework.dao.DataIntegrityViolationException

class StaffController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [staffInstanceList: Staff.list(params), staffInstanceTotal: Staff.count()]
    }

    def create() {
        [staffInstance: new Staff(params)]
    }

    def save() {
        def staffInstance = new Staff(params)
        if (!staffInstance.save(flush: true)) {
            render(view: "create", model: [staffInstance: staffInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'staff.label', default: 'Staff'), staffInstance.id])
        redirect(action: "show", id: staffInstance.id)
    }

    def show(Long id) {
        def staffInstance = Staff.get(id)
        if (!staffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'staff.label', default: 'Staff'), id])
            redirect(action: "list")
            return
        }

        [staffInstance: staffInstance]
    }

    def edit(Long id) {
        def staffInstance = Staff.get(id)
        if (!staffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'staff.label', default: 'Staff'), id])
            redirect(action: "list")
            return
        }

        [staffInstance: staffInstance]
    }

    def update(Long id, Long version) {
        def staffInstance = Staff.get(id)
        if (!staffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'staff.label', default: 'Staff'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (staffInstance.version > version) {
                staffInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'staff.label', default: 'Staff')] as Object[],
                          "Another user has updated this Staff while you were editing")
                render(view: "edit", model: [staffInstance: staffInstance])
                return
            }
        }

        staffInstance.properties = params

        if (!staffInstance.save(flush: true)) {
            render(view: "edit", model: [staffInstance: staffInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'staff.label', default: 'Staff'), staffInstance.id])
        redirect(action: "show", id: staffInstance.id)
    }

    def delete(Long id) {
        def staffInstance = Staff.get(id)
        if (!staffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'staff.label', default: 'Staff'), id])
            redirect(action: "list")
            return
        }

        try {
            staffInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'staff.label', default: 'Staff'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'staff.label', default: 'Staff'), id])
            redirect(action: "show", id: id)
        }
    }
}
