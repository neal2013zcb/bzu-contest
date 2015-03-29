
<%@ page import="bzu.contest.Contest" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contest.label', default: 'Contest')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-contest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-contest" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list contest">
			
				<g:if test="${contestInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="contest.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${contestInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.sponsor}">
				<li class="fieldcontain">
					<span id="sponsor-label" class="property-label"><g:message code="contest.sponsor.label" default="Sponsor" /></span>
					
						<span class="property-value" aria-labelledby="sponsor-label"><g:fieldValue bean="${contestInstance}" field="sponsor"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.level}">
				<li class="fieldcontain">
					<span id="level-label" class="property-label"><g:message code="contest.level.label" default="Level" /></span>
					
						<span class="property-value" aria-labelledby="level-label"><g:fieldValue bean="${contestInstance}" field="level"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.intro}">
				<li class="fieldcontain">
					<span id="intro-label" class="property-label"><g:message code="contest.intro.label" default="Intro" /></span>
					
						<span class="property-value" aria-labelledby="intro-label"><g:fieldValue bean="${contestInstance}" field="intro"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.website}">
				<li class="fieldcontain">
					<span id="website-label" class="property-label"><g:message code="contest.website.label" default="Website" /></span>
					
						<span class="property-value" aria-labelledby="website-label"><g:fieldValue bean="${contestInstance}" field="website"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.logo}">
				<li class="fieldcontain">
					<span id="logo-label" class="property-label"><g:message code="contest.logo.label" default="Logo" /></span>
					
						<span class="property-value" aria-labelledby="logo-label"><g:fieldValue bean="${contestInstance}" field="logo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.submitter}">
				<li class="fieldcontain">
					<span id="submitter-label" class="property-label"><g:message code="contest.submitter.label" default="Submitter" /></span>
					
						<span class="property-value" aria-labelledby="submitter-label"><g:link controller="staff" action="show" id="${contestInstance?.submitter?.id}">${contestInstance?.submitter?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.principal}">
				<li class="fieldcontain">
					<span id="principal-label" class="property-label"><g:message code="contest.principal.label" default="Principal" /></span>
					
						<span class="property-value" aria-labelledby="principal-label"><g:link controller="staff" action="show" id="${contestInstance?.principal?.id}">${contestInstance?.principal?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.approved}">
				<li class="fieldcontain">
					<span id="approved-label" class="property-label"><g:message code="contest.approved.label" default="Approved" /></span>
					
						<span class="property-value" aria-labelledby="approved-label"><g:formatBoolean boolean="${contestInstance?.approved}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.approvedBy}">
				<li class="fieldcontain">
					<span id="approvedBy-label" class="property-label"><g:message code="contest.approvedBy.label" default="Approved By" /></span>
					
						<span class="property-value" aria-labelledby="approvedBy-label"><g:link controller="staff" action="show" id="${contestInstance?.approvedBy?.id}">${contestInstance?.approvedBy?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.dateApproved}">
				<li class="fieldcontain">
					<span id="dateApproved-label" class="property-label"><g:message code="contest.dateApproved.label" default="Date Approved" /></span>
					
						<span class="property-value" aria-labelledby="dateApproved-label"><g:formatDate date="${contestInstance?.dateApproved}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="contest.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${contestInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${contestInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="contest.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${contestInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${contestInstance?.id}" />
					<g:link class="edit" action="edit" id="${contestInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
