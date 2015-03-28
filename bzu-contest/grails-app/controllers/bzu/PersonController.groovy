package bzu

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils;

import grails.plugins.springsecurity.Secured;

@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
class PersonController {

    static allowedMethods = [approve:"POST", undoApproved:"POST"]
	
	def userService
	
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def approve(Long id) {
        def personInstance = Person.get(id)
        userService.doApprove(personInstance)
		render template:'/person/approved', model:[person: personInstance]
	}
	
	@Secured(['ROLE_ADMIN','ROLE_DEPARTMENT'])
	def undoApproved(Long id) {
        def personInstance = Person.get(id)
        userService.undoApprove(personInstance)
        render template:'/person/approved', model:[person: personInstance]
	}
}
