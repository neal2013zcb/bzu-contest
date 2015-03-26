<!DOCTYPE html>
<%@page import="bzu.Person"%>
<html lang="zh-cn">
<head>
	<theme:layout name="sidebar"/>
	<title>联系方式</title>
</head>
<body>
<theme:zone name="secondary-navigation">
</theme:zone>

<theme:zone name="sidebar">
	<g:render template="sidenav"/>
</theme:zone>

<theme:zone name="body">
<legend>联系方式</legend>

<ui:displayMessage/>

<ui:form>
	<ui:fieldGroup>
		<ui:field label="person.workPhone.label">
			<ui:fieldInput>
				<g:textField name="workPhone" maxlength="20" pattern="${person.constraints.workPhone.matches}" value="${person?.workPhone}"/>
			</ui:fieldInput>
		</ui:field>
		<ui:field label="person.homePhone.label">
			<ui:fieldInput>
				<g:textField name="homePhone" maxlength="20" pattern="${person.constraints.homePhone.matches}" value="${person?.homePhone}"/>
			</ui:fieldInput>
		</ui:field>
		<ui:field label="person.email.label">
			<ui:fieldInput>
				<g:field type="email" name="email" maxlength="50" value="${person?.email}"/>
			</ui:fieldInput>
		</ui:field>
	</ui:fieldGroup>
	<ui:actions>
		<ui:button action="updateContacts" kind="submit" mode="primary" class="span3" text="保存更改"/>
	</ui:actions>
</ui:form>
</theme:zone>
</body>
</html>
