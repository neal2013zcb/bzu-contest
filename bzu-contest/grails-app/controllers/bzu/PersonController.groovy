package bzu

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
class PersonController {

    static allowedMethods = [approve:"POST", undoApproved:"POST"]
	
	def userService
	
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def approve(Long id) {
        def personInstance = Person.get(id)
        if (personInstance) {
			personInstance.approved = true
			personInstance.dateOfApproval = new Date()
			personInstance.verifier = userService.currentPerson
			personInstance.save()
        } else {
        	render "未找到"
        }
		render template:'/person/approved', model:[person: personInstance]
	}
	
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def undoApproved(Long id) {
        def personInstance = Person.get(id)
        if (personInstance) {
			personInstance.approved = false
			personInstance.dateOfApproval = null
			personInstance.verifier = null
			personInstance.save()
        } else {
        	render "未找到"
        }
        render template:'/person/approved', model:[person: personInstance]
	}
}
