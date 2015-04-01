<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="dialog"/>
	<g:set var="entityName" value="${message(code: 'contest.label', default: 'Contest')}" />
	<title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>

<theme:zone name="body">

<ui:displayMessage/>

<g:hasErrors bean="${contestInstance}">
<div class="alert alert-error">
	<button type="button" class="close" data-dismiss="alert">&times;</button>
	<g:renderErrors bean="${contestInstance}"/>
</div>
</g:hasErrors>

	<ui:form>
		<ui:fieldGroup>
			<g:render template="form"/>
		</ui:fieldGroup>
		<ui:actions>
			<ui:button kind="submit" mode="primary" action="save" text="default.button.create.label" class="btn-large"/>
		</ui:actions>
	</ui:form>
</theme:zone>
</body>
</html>
