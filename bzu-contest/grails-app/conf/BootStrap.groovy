import bzu.security.Role;

class BootStrap {
	
	def grailsApplication
	
    def init = { servletContext ->
		// 初始化权限
		grailsApplication.config.app.roles.each {
			if(Role.findByAuthority(it)==null) {
				new Role(authority: it).save(true)
			}
		}
    }
    def destroy = {
    }
}
