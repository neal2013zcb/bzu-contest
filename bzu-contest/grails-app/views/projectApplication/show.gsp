
<%@ page import="bzu.contest.ProjectApplication" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'projectApplication.label', default: 'ProjectApplication')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-projectApplication" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-projectApplication" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list projectApplication">
			
				<g:if test="${projectApplicationInstance?.contest}">
				<li class="fieldcontain">
					<span id="contest-label" class="property-label"><g:message code="projectApplication.contest.label" default="Contest" /></span>
					
						<span class="property-value" aria-labelledby="contest-label"><g:link controller="contest" action="show" id="${projectApplicationInstance?.contest?.id}">${projectApplicationInstance?.contest?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="projectApplication.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${projectApplicationInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.sponsors}">
				<li class="fieldcontain">
					<span id="sponsors-label" class="property-label"><g:message code="projectApplication.sponsors.label" default="Sponsors" /></span>
					
						<span class="property-value" aria-labelledby="sponsors-label"><g:fieldValue bean="${projectApplicationInstance}" field="sponsors"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.level}">
				<li class="fieldcontain">
					<span id="level-label" class="property-label"><g:message code="projectApplication.level.label" default="Level" /></span>
					
						<span class="property-value" aria-labelledby="level-label"><g:fieldValue bean="${projectApplicationInstance}" field="level"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.approach}">
				<li class="fieldcontain">
					<span id="approach-label" class="property-label"><g:message code="projectApplication.approach.label" default="Approach" /></span>
					
						<span class="property-value" aria-labelledby="approach-label"><g:fieldValue bean="${projectApplicationInstance}" field="approach"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.venues}">
				<li class="fieldcontain">
					<span id="venues-label" class="property-label"><g:message code="projectApplication.venues.label" default="Venues" /></span>
					
						<span class="property-value" aria-labelledby="venues-label"><g:fieldValue bean="${projectApplicationInstance}" field="venues"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.startDate}">
				<li class="fieldcontain">
					<span id="startDate-label" class="property-label"><g:message code="projectApplication.startDate.label" default="Start Date" /></span>
					
						<span class="property-value" aria-labelledby="startDate-label"><g:formatDate date="${projectApplicationInstance?.startDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.endDate}">
				<li class="fieldcontain">
					<span id="endDate-label" class="property-label"><g:message code="projectApplication.endDate.label" default="End Date" /></span>
					
						<span class="property-value" aria-labelledby="endDate-label"><g:formatDate date="${projectApplicationInstance?.endDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.department}">
				<li class="fieldcontain">
					<span id="department-label" class="property-label"><g:message code="projectApplication.department.label" default="Department" /></span>
					
						<span class="property-value" aria-labelledby="department-label"><g:link controller="department" action="show" id="${projectApplicationInstance?.department?.id}">${projectApplicationInstance?.department?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<li class="fieldcontain">
					<span id="application-label" class="property-label"><g:message code="projectApplication.application.label" default="Application" /></span>
					
						<span class="property-value" aria-labelledby="application-label">
					<g:if test="${projectApplicationInstance?.application}">
						<fileuploader:download ufile="${projectApplicationInstance?.application}" property="application" target="${projectApplicationInstance}"/>
						<fileuploader:delete ufile="${projectApplicationInstance?.application}" property="application" target="${projectApplicationInstance}">删除</fileuploader:delete>
					</g:if>
					<g:else>
						<fileuploader:uploadForm property="application" target="${projectApplicationInstance}"/>
					</g:else>
						</span>
				</li>
			
				<li class="fieldcontain">
					<span id="program-label" class="property-label"><g:message code="projectApplication.program.label" default="Program" /></span>
					
						<span class="property-value" aria-labelledby="program-label">
					<g:if test="${projectApplicationInstance?.program}">
						<fileuploader:download ufile="${projectApplicationInstance?.program}" property="program" target="${projectApplicationInstance}"/>
						<fileuploader:delete ufile="${projectApplicationInstance?.program}" property="program" target="${projectApplicationInstance}">删除</fileuploader:delete>
					</g:if>
					<g:else>
						<fileuploader:uploadForm property="program" target="${projectApplicationInstance}"/>
					</g:else>
						</span>
					
				</li>
			
				<li class="fieldcontain">
					<span id="budget-label" class="property-label"><g:message code="projectApplication.budget.label" default="Budget" /></span>
					
						<span class="property-value" aria-labelledby="budget-label">
					<g:if test="${projectApplicationInstance?.budget}">
						<fileuploader:download ufile="${projectApplicationInstance?.budget}" property="budget" target="${projectApplicationInstance}"/>
						<fileuploader:delete ufile="${projectApplicationInstance?.budget}" property="budget" target="${projectApplicationInstance}">删除</fileuploader:delete>
					</g:if>
					<g:else>
						<fileuploader:uploadForm property="budget" target="${projectApplicationInstance}"/>
					</g:else>
						</span>
					
				</li>
			
				<g:if test="${projectApplicationInstance?.principal}">
				<li class="fieldcontain">
					<span id="principal-label" class="property-label"><g:message code="projectApplication.principal.label" default="Principal" /></span>
					
						<span class="property-value" aria-labelledby="principal-label"><g:link controller="staff" action="show" id="${projectApplicationInstance?.principal?.id}">${projectApplicationInstance?.principal?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.submitter}">
				<li class="fieldcontain">
					<span id="submitter-label" class="property-label"><g:message code="projectApplication.submitter.label" default="Submitter" /></span>
					
						<span class="property-value" aria-labelledby="submitter-label"><g:link controller="staff" action="show" id="${projectApplicationInstance?.submitter?.id}">${projectApplicationInstance?.submitter?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="projectApplication.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${projectApplicationInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<li class="fieldcontain">
					<span id="status-label" class="property-label"><g:message code="projectApplication.status.label" default="Status" /></span>
					
						<span class="property-value" aria-labelledby="status-label">
						<g:message code="projectApplication.status.${projectApplicationInstance?.status}"/>
						</span>
					
				</li>
			
				<g:if test="${projectApplicationInstance?.approved}">
				<li class="fieldcontain">
					<span id="approved-label" class="property-label"><g:message code="projectApplication.approved.label" default="Approved" /></span>
					
						<span class="property-value" aria-labelledby="approved-label"><g:formatBoolean boolean="${projectApplicationInstance?.approved}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.approvedBy}">
				<li class="fieldcontain">
					<span id="approvedBy-label" class="property-label"><g:message code="projectApplication.approvedBy.label" default="Approved By" /></span>
					
						<span class="property-value" aria-labelledby="approvedBy-label"><g:link controller="staff" action="show" id="${projectApplicationInstance?.approvedBy?.id}">${projectApplicationInstance?.approvedBy?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${projectApplicationInstance?.dateApproved}">
				<li class="fieldcontain">
					<span id="dateApproved-label" class="property-label"><g:message code="projectApplication.dateApproved.label" default="Date Approved" /></span>
					
						<span class="property-value" aria-labelledby="dateApproved-label"><g:formatDate date="${projectApplicationInstance?.dateApproved}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${projectApplicationInstance?.id}" />
					<g:link class="edit" action="edit" id="${projectApplicationInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
