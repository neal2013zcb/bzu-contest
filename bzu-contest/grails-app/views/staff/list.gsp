<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="report"/>
	<title>教师</title>
</head>
<body>
<theme:zone name="secondary-navigation">
</theme:zone>

<theme:zone name="body">

<ui:table>
	<thead>
		<ui:tr>
			<g:sortableColumn property="no" title="${message(code: 'staff.no.label', default: 'No')}" />
			<g:sortableColumn property="name" title="${message(code: 'staff.name.label', default: 'Name')}" />
			<g:sortableColumn property="gender" title="${message(code: 'staff.gender.label', default: 'Gender')}" />
			<g:sortableColumn property="workPhone" title="${message(code: 'staff.workPhone.label', default: 'Work Phone')}" />
			<g:sortableColumn property="homePhone" title="${message(code: 'staff.homePhone.label', default: 'Home Phone')}" />
			<g:sortableColumn property="email" title="${message(code: 'staff.email.label', default: 'Email')}" />
		</ui:tr>
	</thead>
	<tbody>
	<g:each in="${staffInstanceList}" status="i" var="staffInstance">
		<ui:tr class="tr-hover">
			<td><g:link action="show" id="${staffInstance.id}">${fieldValue(bean: staffInstance, field: "no")}</g:link></td>
			<td>${fieldValue(bean: staffInstance, field: "name")}</td>
			<td>${fieldValue(bean: staffInstance, field: "gender")}</td>
			<td>${fieldValue(bean: staffInstance, field: "workPhone")}</td>
			<td>${fieldValue(bean: staffInstance, field: "homePhone")}</td>
			<td>${fieldValue(bean: staffInstance, field: "email")}</td>
		</ui:tr>
	</g:each>
	</tbody>
</ui:table>
	
</theme:zone>
<theme:zone name="pagination">
	<ui:paginate class=" pagination-centered" total="${staffInstanceTotal}"/>
</theme:zone>
</body>
</html>
