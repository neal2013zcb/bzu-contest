<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="dialog"/>
	<g:set var="entityName" value="${message(code: 'contest.label', default: 'Contest')}" />
	<title><g:message code="default.edit.label" args="[entityName]" /></title>
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
		<g:hiddenField name="id" value="${contestInstance?.id}" />
		<g:hiddenField name="version" value="${contestInstance?.version}" />
		<ui:fieldGroup>
			<g:render template="form"/>
		</ui:fieldGroup>
		<ui:actions>
			<g:actionSubmit class="btn btn-large btn-primary" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
			<g:actionSubmit class="btn btn-large btn-danger" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
		</ui:actions>
	</ui:form>
</theme:zone>
</body>
</html>