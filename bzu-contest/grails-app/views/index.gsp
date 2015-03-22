<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="home"/>
	<title><p:siteName/></title>
</head>
<body>
<theme:zone name="masthead">
</theme:zone>
<theme:zone name="body">
	<ui:displayMessage/>
</theme:zone>
<theme:zone name="panel1">
		<div id="status" role="complementary">
			<h3>Application Status</h3>
			<ul>
				<li>App version: <g:meta name="app.version"/></li>
				<li>Grails version: <g:meta name="app.grails.version"/></li>
				<li>Groovy version: ${GroovySystem.getVersion()}</li>
				<li>JVM version: ${System.getProperty('java.version')}</li>
				<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
				<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
				<li>Domains: ${grailsApplication.domainClasses.size()}</li>
				<li>Services: ${grailsApplication.serviceClasses.size()}</li>
				<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
			</ul>
			<h3>Installed Plugins</h3>
			<ul>
				<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
					<li>${plugin.name} - ${plugin.version}</li>
				</g:each>
			</ul>
		</div>

</theme:zone>
<theme:zone name="panel2">
			   
			<div id="controller-list" role="navigation">
				<h3>Available Controllers:</h3>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
</theme:zone>
<theme:zone name="panel3">
</theme:zone>
</body>
</html>
