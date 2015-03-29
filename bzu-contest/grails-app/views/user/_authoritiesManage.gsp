<!-- 权限管理 user allowedRoles -->
<span id="roles-${user.id}">
<g:set var="roles" value="${user.authorities*.authority}"/>
<g:each in="${allowedRoles}">
	<g:set var="hasRole" value="${it in roles}"/>
<div class="btn-group">
<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#" title="${g.message(code:it+'.label')}">
	<i class="fa fa-lg ${hasRole ? 'fa-check-circle text-success' : 'fa-ban text-error'}"></i> <g:message code="${it}.label"/>
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