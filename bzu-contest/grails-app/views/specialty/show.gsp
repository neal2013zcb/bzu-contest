
<%@ page import="bzu.Specialty" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'specialty.label', default: 'Specialty')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-specialty" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-specialty" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list specialty">
			
				<g:if test="${specialtyInstance?.no}">
				<li class="fieldcontain">
					<span id="no-label" class="property-label"><g:message code="specialty.no.label" default="No" /></span>
					
						<span class="property-value" aria-labelledby="no-label"><g:fieldValue bean="${specialtyInstance}" field="no"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${specialtyInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="specialty.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${specialtyInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${specialtyInstance?.shortName}">
				<li class="fieldcontain">
					<span id="shortName-label" class="property-label"><g:message code="specialty.shortName.label" default="Short Name" /></span>
					
						<span class="property-value" aria-labelledby="shortName-label"><g:fieldValue bean="${specialtyInstance}" field="shortName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${specialtyInstance?.level}">
				<li class="fieldcontain">
					<span id="level-label" class="property-label"><g:message code="specialty.level.label" default="Level" /></span>
					
						<span class="property-value" aria-labelledby="level-label"><g:message code="specialty.level.${specialtyInstance.level}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${specialtyInstance?.department}">
				<li class="fieldcontain">
					<span id="department-label" class="property-label"><g:message code="specialty.department.label" default="Department" /></span>
					
						<span class="property-value" aria-labelledby="department-label"><g:link controller="department" action="show" id="${specialtyInstance?.department?.id}">${specialtyInstance?.department?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${specialtyInstance?.classGrades}">
				<li class="fieldcontain">
					<span id="classGrades-label" class="property-label"><g:message code="specialty.classGrades.label" default="Class Grades" /></span>
					
						<g:each in="${specialtyInstance.classGrades}" var="c">
						<span class="property-value" aria-labelledby="classGrades-label"><g:link controller="classGrade" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${specialtyInstance?.id}" />
					<g:link class="edit" action="edit" id="${specialtyInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
