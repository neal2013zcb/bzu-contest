
<%@ page import="bzu.Staff" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'staff.label', default: 'Staff')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-staff" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-staff" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list staff">
			
				<g:if test="${staffInstance?.no}">
				<li class="fieldcontain">
					<span id="no-label" class="property-label"><g:message code="staff.no.label" default="No" /></span>
					
						<span class="property-value" aria-labelledby="no-label"><g:fieldValue bean="${staffInstance}" field="no"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${staffInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="staff.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${staffInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${staffInstance?.gender}">
				<li class="fieldcontain">
					<span id="gender-label" class="property-label"><g:message code="staff.gender.label" default="Gender" /></span>
					
						<span class="property-value" aria-labelledby="gender-label"><g:fieldValue bean="${staffInstance}" field="gender"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${staffInstance?.workPhone}">
				<li class="fieldcontain">
					<span id="workPhone-label" class="property-label"><g:message code="staff.workPhone.label" default="Work Phone" /></span>
					
						<span class="property-value" aria-labelledby="workPhone-label"><g:fieldValue bean="${staffInstance}" field="workPhone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${staffInstance?.homePhone}">
				<li class="fieldcontain">
					<span id="homePhone-label" class="property-label"><g:message code="staff.homePhone.label" default="Home Phone" /></span>
					
						<span class="property-value" aria-labelledby="homePhone-label"><g:fieldValue bean="${staffInstance}" field="homePhone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${staffInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="staff.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${staffInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${staffInstance?.account}">
				<li class="fieldcontain">
					<span id="account-label" class="property-label"><g:message code="staff.account.label" default="Account" /></span>
					
						<span class="property-value" aria-labelledby="account-label"><g:link controller="user" action="show" id="${staffInstance?.account?.id}">${staffInstance?.account?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${staffInstance?.department}">
				<li class="fieldcontain">
					<span id="department-label" class="property-label"><g:message code="staff.department.label" default="Department" /></span>
					
						<span class="property-value" aria-labelledby="department-label"><g:link controller="department" action="show" id="${staffInstance?.department?.id}">${staffInstance?.department?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${staffInstance?.id}" />
					<g:link class="edit" action="edit" id="${staffInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
