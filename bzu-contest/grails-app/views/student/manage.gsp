<!DOCTYPE html>
<%@page import="bzu.Constants"%>
<html lang="zh-cn">
<head>
	<theme:layout name="report"/>
	<title>学生</title>
</head>
<body>
<theme:zone name="secondary-navigation">
</theme:zone>

<theme:zone name="body">

<ui:displayMessage/>

<g:set var="allowedRoles" value="${Constants.Role.STUDENT_ROLES}"/>

<ui:table>
	<thead>
		<ui:tr>
			<g:sortableColumn property="no" params="${params}" title="${message(code: 'student.no.label', default: 'No')}" />
			<g:sortableColumn property="name" params="${params}" title="${message(code: 'student.name.label', default: 'Name')}" />
			<g:sortableColumn property="gender" params="${params}" title="${message(code: 'student.gender.label', default: 'Gender')}" />
			<g:sortableColumn property="classGrade" params="${params}" title="${message(code: 'student.classGrade.label', default: 'Class Grade')}" />
			<g:sortableColumn property="approved" params="${params}" title="${message(code: 'student.approved.label', default: 'Approved')}" />
			<ui:th>账号管理</ui:th>
			<ui:th>权限管理</ui:th>
		</ui:tr>
	</thead>
	<tbody>
	<g:each in="${studentInstanceList}" status="i" var="studentInstance">
		<ui:tr class="tr-hover">
			<td>${fieldValue(bean: studentInstance, field: "no")}</td>
			<td>${fieldValue(bean: studentInstance, field: "name")}</td>
			<td>${fieldValue(bean: studentInstance, field: "gender")}</td>
			<td>${fieldValue(bean: studentInstance, field: "classGrade")}</td>
			<td><g:render template="/person/approved" model="[person:studentInstance]"/></td>
			<td><g:render template="/user/accountManage" model="[user:studentInstance.account]"/></td>
			<td><g:render template="/user/authoritiesManage" model="[user:studentInstance.account, allowedRoles:allowedRoles]"/></td>
		</ui:tr>
	</g:each>
	</tbody>
</ui:table>

</theme:zone>
<theme:zone name="pagination">
	<ui:paginate class=" pagination-centered" total="${studentInstanceTotal}" params="${params}"/>
</theme:zone>
</body>
</html>
