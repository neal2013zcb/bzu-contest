
<%@ page import="bzu.Student" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-student" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-student" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list student">
			
				<g:if test="${studentInstance?.no}">
				<li class="fieldcontain">
					<span id="no-label" class="property-label"><g:message code="student.no.label" default="No" /></span>
					
						<span class="property-value" aria-labelledby="no-label"><g:fieldValue bean="${studentInstance}" field="no"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="student.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${studentInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.gender}">
				<li class="fieldcontain">
					<span id="gender-label" class="property-label"><g:message code="student.gender.label" default="Gender" /></span>
					
						<span class="property-value" aria-labelledby="gender-label"><g:fieldValue bean="${studentInstance}" field="gender"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.workPhone}">
				<li class="fieldcontain">
					<span id="workPhone-label" class="property-label"><g:message code="student.workPhone.label" default="Work Phone" /></span>
					
						<span class="property-value" aria-labelledby="workPhone-label"><g:fieldValue bean="${studentInstance}" field="workPhone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.homePhone}">
				<li class="fieldcontain">
					<span id="homePhone-label" class="property-label"><g:message code="student.homePhone.label" default="Home Phone" /></span>
					
						<span class="property-value" aria-labelledby="homePhone-label"><g:fieldValue bean="${studentInstance}" field="homePhone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="student.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${studentInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.account}">
				<li class="fieldcontain">
					<span id="account-label" class="property-label"><g:message code="student.account.label" default="Account" /></span>
					
						<span class="property-value" aria-labelledby="account-label"><g:link controller="user" action="show" id="${studentInstance?.account?.id}">${studentInstance?.account?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${studentInstance?.classGrade}">
				<li class="fieldcontain">
					<span id="classGrade-label" class="property-label"><g:message code="student.classGrade.label" default="Class Grade" /></span>
					
						<span class="property-value" aria-labelledby="classGrade-label"><g:link controller="classGrade" action="show" id="${studentInstance?.classGrade?.id}">${studentInstance?.classGrade?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${studentInstance?.id}" />
					<g:link class="edit" action="edit" id="${studentInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
