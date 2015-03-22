<%@ page import="bzu.Person" %>



<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'no', 'error')} required">
	<label for="no">
		<g:message code="person.no.label" default="No" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="no" maxlength="20" pattern="${personInstance.constraints.no.matches}" required="" value="${personInstance?.no}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="person.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="50" required="" value="${personInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'gender', 'error')} required">
	<label for="gender">
		<g:message code="person.gender.label" default="Gender" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="gender" from="${personInstance.constraints.gender.inList}" required="" value="${personInstance?.gender}" valueMessagePrefix="person.gender"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'workPhone', 'error')} ">
	<label for="workPhone">
		<g:message code="person.workPhone.label" default="Work Phone" />
		
	</label>
	<g:textField name="workPhone" maxlength="20" pattern="${personInstance.constraints.workPhone.matches}" value="${personInstance?.workPhone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'homePhone', 'error')} ">
	<label for="homePhone">
		<g:message code="person.homePhone.label" default="Home Phone" />
		
	</label>
	<g:textField name="homePhone" maxlength="20" pattern="${personInstance.constraints.homePhone.matches}" value="${personInstance?.homePhone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="person.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" maxlength="50" value="${personInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'account', 'error')} ">
	<label for="account">
		<g:message code="person.account.label" default="Account" />
		
	</label>
	<g:select id="account" name="account.id" from="${bzu.security.User.list()}" optionKey="id" value="${personInstance?.account?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

