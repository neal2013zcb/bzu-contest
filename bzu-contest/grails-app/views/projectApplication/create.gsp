<%@ page import="bzu.contest.ProjectApplication" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="dialog"/>
	<g:set var="entityName" value="${message(code: 'projectApplication.label', default: 'Project Application')}" />
	<title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>

<theme:zone name="body">

<ui:displayMessage/>

<g:hasErrors bean="${projectApplicationInstance}">
<div class="alert alert-error">
	<button type="button" class="close" data-dismiss="alert">&times;</button>
	<g:renderErrors bean="${projectApplicationInstance}"/>
</div>
</g:hasErrors>

	<ui:form controller="projectApplication" action="save">
		<ui:fieldGroup>
			<g:render template="/projectApplication/form"/>
		</ui:fieldGroup>
		<ui:actions>
			<ui:button kind="submit" mode="primary" action="save" text="default.button.create.label" class="btn-large"/>
		</ui:actions>
	</ui:form>
</theme:zone>
</body>
</html>
