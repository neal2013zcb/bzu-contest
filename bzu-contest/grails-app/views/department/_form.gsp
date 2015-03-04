<%@ page import="bzu.Department" %>



<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'no', 'error')} required">
	<label for="no">
		<g:message code="department.no.label" default="No" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="no" maxlength="20" pattern="${departmentInstance.constraints.no.matches}" required="" value="${departmentInstance?.no}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="department.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="50" required="" value="${departmentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'shortName', 'error')} required">
	<label for="shortName">
		<g:message code="department.shortName.label" default="Short Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="shortName" maxlength="50" required="" value="${departmentInstance?.shortName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="department.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="category" from="${departmentInstance.constraints.category.inList}" required="" value="${departmentInstance?.category}" valueMessagePrefix="department.category"/>
</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'specialties', 'error')} ">
	<label for="specialties">
		<g:message code="department.specialties.label" default="Specialties" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${departmentInstance?.specialties?}" var="s">
    <li><g:link controller="specialty" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="specialty" action="create" params="['department.id': departmentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'specialty.label', default: 'Specialty')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: departmentInstance, field: 'staffs', 'error')} ">
	<label for="staffs">
		<g:message code="department.staffs.label" default="Staffs" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${departmentInstance?.staffs?}" var="s">
    <li><g:link controller="staff" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="staff" action="create" params="['department.id': departmentInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'staff.label', default: 'Staff')])}</g:link>
</li>
</ul>

</div>

