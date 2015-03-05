
<%@ page import="bzu.Person" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-person" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-person" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list person">
			
				<g:if test="${personInstance?.no}">
				<li class="fieldcontain">
					<span id="no-label" class="property-label"><g:message code="person.no.label" default="No" /></span>
					
						<span class="property-value" aria-labelledby="no-label"><g:fieldValue bean="${personInstance}" field="no"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="person.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${personInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personInstance?.gender}">
				<li class="fieldcontain">
					<span id="gender-label" class="property-label"><g:message code="person.gender.label" default="Gender" /></span>
					
						<span class="property-value" aria-labelledby="gender-label"><g:message code="person.gender.${personInstance.gender}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personInstance?.category}">
				<li class="fieldcontain">
					<span id="category-label" class="property-label"><g:message code="person.category.label" default="Category" /></span>
					
						<span class="property-value" aria-labelledby="category-label"><g:message code="person.category.${personInstance.category}"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personInstance?.officePhone}">
				<li class="fieldcontain">
					<span id="officePhone-label" class="property-label"><g:message code="person.officePhone.label" default="Office Phone" /></span>
					
						<span class="property-value" aria-labelledby="officePhone-label"><g:fieldValue bean="${personInstance}" field="officePhone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personInstance?.cellPhone}">
				<li class="fieldcontain">
					<span id="cellPhone-label" class="property-label"><g:message code="person.cellPhone.label" default="Cell Phone" /></span>
					
						<span class="property-value" aria-labelledby="cellPhone-label"><g:fieldValue bean="${personInstance}" field="cellPhone"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="person.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${personInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personInstance?.qq}">
				<li class="fieldcontain">
					<span id="qq-label" class="property-label"><g:message code="person.qq.label" default="Qq" /></span>
					
						<span class="property-value" aria-labelledby="qq-label"><g:fieldValue bean="${personInstance}" field="qq"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${personInstance?.weixin}">
				<li class="fieldcontain">
					<span id="weixin-label" class="property-label"><g:message code="person.weixin.label" default="Weixin" /></span>
					
						<span class="property-value" aria-labelledby="weixin-label"><g:fieldValue bean="${personInstance}" field="weixin"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${personInstance?.id}" />
					<g:link class="edit" action="edit" id="${personInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
