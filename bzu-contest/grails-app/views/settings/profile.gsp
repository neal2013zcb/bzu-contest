<!DOCTYPE html>
<%@page import="bzu.ClassGrade"%>
<%@page import="bzu.Department"%>
<html lang="zh-cn">
<head>
	<theme:layout name="sidebar"/>
	<title>基本信息</title>
</head>
<body>
<theme:zone name="secondary-navigation">
</theme:zone>

<theme:zone name="sidebar">
	<g:render template="sidenav"/>
</theme:zone>

<theme:zone name="body">
	
<legend>基本信息</legend>

<ui:displayMessage/>

<g:if test="${person.approved}">
<ui:form>
	<ui:fieldGroup>
		<ui:field label="${userType}.no.label">
			<ui:fieldInput>
				<span class="uneditable-input" data-toggle="tooltip" title="身份标识，不可更改">${person.no}</span>
			</ui:fieldInput>
		</ui:field>
		<ui:field label="${userType}.name.label">
			<ui:fieldInput>
				<span class="uneditable-input" data-toggle="tooltip" title="通过审核，不可更改">${person.name}</span>
			</ui:fieldInput>
		</ui:field>
		<ui:field label="${userType}.gender.label">
			<ui:fieldInput>
				<span class="uneditable-input" data-toggle="tooltip" title="通过审核，不可更改"><g:message code="person.gender.${person.gender}"/></span>
			</ui:fieldInput>
		</ui:field>
<g:if test="${userType=='staff'}">
		<ui:field label="staff.department.label">
			<ui:fieldInput>
				<span class="uneditable-input" data-toggle="tooltip" title="通过审核，不可更改">${person.department}</span>
			</ui:fieldInput>
		</ui:field>
</g:if>
<g:if test="${userType=='student'}">
		<ui:field label="student.classGrade.label">
			<ui:fieldInput>
				<span class="uneditable-input" data-toggle="tooltip" title="通过审核，不可更改">${person.classGrade}</span>
			</ui:fieldInput>
		</ui:field>
</g:if>
	</ui:fieldGroup>
	<ui:message type="info">您的个人信息已经通过审核。</ui:message>
</ui:form>
</g:if>
<g:else>
<ui:form>
	<ui:fieldGroup>
		<ui:field label="${userType}.no.label">
			<ui:fieldInput>
				<span class="uneditable-input" data-toggle="tooltip" title="身份标识，不可更改">${person.no}</span>
			</ui:fieldInput>
		</ui:field>
		<ui:field label="${userType}.name.label" name="name" bean="${person}" required="">
		</ui:field>
		<ui:field label="${userType}.gender.label">
			<ui:fieldInput>
				<g:select name="gender" from="${person.constraints.gender.inList}" required="" value="${person?.gender}" valueMessagePrefix="person.gender"/>
			</ui:fieldInput>
		</ui:field>
<g:if test="${userType=='staff'}">
		<ui:field label="staff.department.label">
			<ui:fieldInput>
				<g:select name="department.id" from="${Department.list(sort:'name')}" optionKey="id" value="${person.department.id}" required=""/>
			</ui:fieldInput>
		</ui:field>
</g:if>
<g:if test="${userType=='student'}">
		<ui:field label="student.classGrade.label">
			<ui:fieldInput>
				<g:select name="classGrade.id" from="${ClassGrade.list(sort:'name', order:'desc')}" optionKey="id" value="${person.classGrade.id}" required=""/>
			</ui:fieldInput>
		</ui:field>
</g:if>
	</ui:fieldGroup>
	<ui:actions>
		<ui:button action="updateProfile" kind="submit" mode="primary" class="span3" text="保存更改"/>
	</ui:actions>
</ui:form>
</g:else>
</theme:zone>
</body>
</html>
