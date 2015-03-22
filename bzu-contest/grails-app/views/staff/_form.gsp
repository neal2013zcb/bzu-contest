<%@ page import="bzu.Staff" %>



<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'no', 'error')} required">
	<label for="no">
		<g:message code="staff.no.label" default="No" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="no" maxlength="20" pattern="${staffInstance.constraints.no.matches}" required="" value="${staffInstance?.no}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="staff.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="50" required="" value="${staffInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'gender', 'error')} required">
	<label for="gender">
		<g:message code="staff.gender.label" default="Gender" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="gender" from="${staffInstance.constraints.gender.inList}" required="" value="${staffInstance?.gender}" valueMessagePrefix="staff.gender"/>
</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'workPhone', 'error')} ">
	<label for="workPhone">
		<g:message code="staff.workPhone.label" default="Work Phone" />
		
	</label>
	<g:textField name="workPhone" maxlength="20" pattern="${staffInstance.constraints.workPhone.matches}" value="${staffInstance?.workPhone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'homePhone', 'error')} ">
	<label for="homePhone">
		<g:message code="staff.homePhone.label" default="Home Phone" />
		
	</label>
	<g:textField name="homePhone" maxlength="20" pattern="${staffInstance.constraints.homePhone.matches}" value="${staffInstance?.homePhone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="staff.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" maxlength="50" value="${staffInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'account', 'error')} ">
	<label for="account">
		<g:message code="staff.account.label" default="Account" />
		
	</label>
	<g:select id="account" name="account.id" from="${bzu.security.User.list()}" optionKey="id" value="${staffInstance?.account?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: staffInstance, field: 'department', 'error')} required">
	<label for="department">
		<g:message code="staff.department.label" default="Department" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="department" name="department.id" from="${bzu.Department.list()}" optionKey="id" required="" value="${staffInstance?.department?.id}" class="many-to-one"/>
</div>

