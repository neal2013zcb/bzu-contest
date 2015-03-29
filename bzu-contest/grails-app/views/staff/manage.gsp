<!DOCTYPE html>
<%@page import="bzu.Constants"%>
<html lang="zh-cn">
<head>
	<theme:layout name="report"/>
	<title>教师</title>
</head>
<body>
<theme:zone name="secondary-navigation">
</theme:zone>

<theme:zone name="body">

<ui:displayMessage/>

<g:set var="allowedRoles" value="${Constants.Role.STAFF_ROLES}"/>
<sec:ifNotGranted roles="ROLE_ADMIN">
	<g:set var="allowedRoles" value="${allowedRoles - Constants.Role.HIGH_LEVEL_ROLES}"/>
</sec:ifNotGranted>

<ui:table>
	<thead>
		<ui:tr>
			<g:sortableColumn property="no" params="${params}" title="${message(code: 'staff.no.label', default: 'No')}" />
			<g:sortableColumn property="name" params="${params}" title="${message(code: 'staff.name.label', default: 'Name')}" />
			<g:sortableColumn property="gender" params="${params}" title="${message(code: 'staff.gender.label', default: 'Gender')}" />
			<g:sortableColumn property="department" params="${params}" title="${message(code: 'staff.department.label', default: 'Department')}" />
			<ui:th><g:message code="staff.workPhone.label"/></ui:th>
			<ui:th><g:message code="staff.homePhone.label"/></ui:th>
			<ui:th><g:message code="staff.email.label"/></ui:th>
			<ui:th><g:message code="staff.officeLocation.label"/></ui:th>
			<ui:th>信息审核</ui:th>
			<ui:th>账号管理</ui:th>
			<ui:th>权限管理</ui:th>
		</ui:tr>
	</thead>
	<tbody>
	<g:set var="myno" value="${bzu.user(property:'no')}"/>
	<g:each in="${staffInstanceList}" status="i" var="staffInstance">
		<ui:tr class="tr-hover ${staffInstance.no==myno ? 'warning text-success' : ''}">
			<td>${fieldValue(bean: staffInstance, field: "no")}</td>
			<td>${fieldValue(bean: staffInstance, field: "name")}</td>
			<td>${fieldValue(bean: staffInstance, field: "gender")}</td>
			<td>${fieldValue(bean: staffInstance, field: "department")}</td>
			<td>${fieldValue(bean: staffInstance, field: "workPhone")}</td>
			<td>${fieldValue(bean: staffInstance, field: "homePhone")}</td>
			<td>${fieldValue(bean: staffInstance, field: "email")}</td>
			<td>${fieldValue(bean: staffInstance, field: "officeLocation")}</td>
			<td><g:render template="/person/approved" model="[person:staffInstance]"/></td>
			<td><g:render template="/user/accountManage" model="[user:staffInstance.account]"/></td>
			<td><g:render template="/user/authoritiesManage" model="[user:staffInstance.account, allowedRoles:allowedRoles]"/></td>
		</ui:tr>
	</g:each>
	</tbody>
</ui:table>

</theme:zone>
<theme:zone name="pagination">
	<ui:paginate class=" pagination-centered" total="${staffInstanceTotal}" params="${params}"/>
</theme:zone>
</body>
</html>
