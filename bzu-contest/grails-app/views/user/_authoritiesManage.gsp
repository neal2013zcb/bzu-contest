<!-- 权限管理 user -->
<%@page import="bzu.Constants"%>
<span id="roles-${user.id}">
<g:set var="roles" value="${user.authorities*.authority}"/>
<bzu:ifStaff user="${user}">
	<g:set var="allRoles" value="${Constants.Role.STAFF_ROLES}"/>
	<sec:ifNotGranted roles="ROLE_ADMIN">
		<g:set var="allRoles" value="${allRoles - Constants.Role.HIGH_LEVEL_ROLES}"/>
	</sec:ifNotGranted>
</bzu:ifStaff>
<bzu:ifStudent user="${user}">
<g:set var="allRoles" value="${Constants.Role.STUDENT_ROLES}"/>
</bzu:ifStudent>
<g:each in="${allRoles}">
	<g:set var="hasRole" value="${it in roles}"/>
<div class="btn-group">
<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#" title="${g.message(code:it+'.label')}">
	<i class="fa fa-lg ${hasRole ? 'fa-check-circle text-success' : 'fa-ban text-error'}"></i> ${it[5]}
</a>
<ul class="dropdown-menu">
<g:if test="${hasRole}">
	<li><g:remoteLink action="removeRole" controller="user" id="${user.id}" params="[roleName:it]" update="roles-${user.id}"><i class="fa fa-ban text-error"></i> 撤消 <g:message code="${it}.label"/> 权限</g:remoteLink></li>
</g:if>
<g:else>
	<li><g:remoteLink action="addRole" controller="user" id="${user.id}" params="[roleName:it]" update="roles-${user.id}"><i class="fa fa-check-circle text-success"></i> 授予 <g:message code="${it}.label"/> 权限</g:remoteLink></li>
</g:else>
</ul>
</div>
</g:each>
</span>