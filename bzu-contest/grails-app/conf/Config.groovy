import fileuploader.UFile;

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'bzu.security.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'bzu.security.UserRole'
grails.plugins.springsecurity.authority.className = 'bzu.security.Role'

// Security of password
grails.plugins.springsecurity.password.algorithm = 'bcrypt'
grails.plugins.springsecurity.password.bcrypt.logrounds = 7
grails.plugins.springsecurity.dao.reflectionSaltSourceProperty = 'username'
grails.plugins.springsecurity.logout.afterLogoutUrl = '/logout/success'

grails.plugins.springsecurity.roleHierarchy = '''
	ROLE_ADMIN > ROLE_USER
	ROLE_DEPARTMENT > ROLE_USER
	ROLE_PROJECT > ROLE_USER
	ROLE_INSPECTOR > ROLE_USER
	ROLE_TEACHER > ROLE_USER
	ROLE_STUDENT > ROLE_USER
'''

// 界面主题
plugin.platformUi.theme.default = 'Bootstrap'

plugin.platformCore.site.name="学科竞赛管理系统"
plugin.platformCore.organization.name='滨州学院'

// 共享的约束
grails.gorm.default.constraints = {
	// 电话号码正则表达式（支持手机号码，3-4位区号，7-8位直播号码，1－4位分机号）
	phone_matches(matches:/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/)
	// QQ号正则表达式（6-10位数字，不以0开头）
	qq_matches(matches:/[1-9]\d{5,9}/)
	// 微信号正则表达式（6-20位字母、数字、下划线、减号）
	weixin_matches(matches:/[A-Za-z_\d\-]{6,20}/)
}

// 各类权限
app.roles = [
'ROLE_USER',		// 注册用户
'ROLE_ADMIN',		// 系统管理
'ROLE_DEPARTMENT',	// 系院管理
'ROLE_PROJECT',		// 项目管理
'ROLE_INSPECTOR',	// 竞赛督导
'ROLE_TEACHER',		// 教师
'ROLE_STUDENT',		// 学生
]

// 校内门户登录网址及返回结果
webPortal.login.url = "http://portal.bzu.edu.cn/loginAction.do?userName=%s&userPass=%s"
webPortal.login.success = "<script>window.top.location.href=\"/index_jg.jsp\"</script>"
// 开发和测试环境下校内门户
webPortal.mock.url = "http://localhost:8080/${appName}/webPortalMock/login?userName=%s&userPass=%s"
webPortal.mock.success = "OK"
webPortal.mock.fail = "FAIL"
webPortal.mock.users = [ '1111':'1111', '2222':'2222', '3333':'3333' ]

// Authentication Providers
grails.plugins.springsecurity.providerNames = [
	'daoAndWebPortalAuthenticationProvider',  // dao and web auth
	'anonymousAuthenticationProvider',
	'rememberMeAuthenticationProvider']

// 上传文件的设置
fileuploader {
	// 默认设置
	//   base : REQUIRED, 上传文件的根目录
	//   maxSize : REQUIRED, 允许上传文件大小的上限
	//   allowedExtensions : REQUIRED, 允许的文件类型，如 [doc,docx,xls,xlsx,pdf,zip,rar,jpg,jpeg,png]
	//   path : 路径，允许用闭包定义子路径规则，默认为领域类名
	base = "${userHome}/upload/${appName}/"
	maxSize = 10 << 20  // 10MB
	allowedExtensions = ['doc','docx','xls','xlsx','pdf','zip','rar','jpg','jpeg','png']
	path = { obj->
		obj.domainClass.logicalPropertyName
	}
	
	// 常用设置
	doc {
		allowedExtensions = ['doc','docx','xls','xlsx','pdf']
	}
	image {
		allowedExtensions = ['jpg','jpeg','png']
	}
	zip {
		allowedExtensions = ['zip','rar']
	}
	
	// 具体领域类属性的设置
	//   base : 上传文件的根目录
	//   maxSize : 允许上传文件大小的上限
	//   allowedExtensions : 允许的文件类型
	//   path : 子路径，允许用闭包定义子路径规则
	//   name : 命名，允许用闭包定义命名规则
	//   ref : 引用其他定义

	projectApplication {
		path = { obj->
			"projectApplication/${obj.id}/"
		}
		allowedExtensions = ['doc','docx','xls','xlsx','pdf']
		application { }
		program { }
		budget { }
	}

}

