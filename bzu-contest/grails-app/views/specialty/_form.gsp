<%@ page import="bzu.Specialty" %>



<div class="fieldcontain ${hasErrors(bean: specialtyInstance, field: 'no', 'error')} required">
	<label for="no">
		<g:message code="specialty.no.label" default="No" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="no" maxlength="20" required="" value="${specialtyInstance?.no}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: specialtyInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="specialty.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="50" required="" value="${specialtyInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: specialtyInstance, field: 'shortName', 'error')} required">
	<label for="shortName">
		<g:message code="specialty.shortName.label" default="Short Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="shortName" maxlength="50" required="" value="${specialtyInstance?.shortName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: specialtyInstance, field: 'level', 'error')} required">
	<label for="level">
		<g:message code="specialty.level.label" default="Level" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="level" from="${specialtyInstance.constraints.level.inList}" required="" value="${specialtyInstance?.level}" valueMessagePrefix="specialty.level"/>
</div>

<div class="fieldcontain ${hasErrors(bean: specialtyInstance, field: 'department', 'error')} required">
	<label for="department">
		<g:message code="specialty.department.label" default="Department" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="department" name="department.id" from="${bzu.Department.list()}" optionKey="id" required="" value="${specialtyInstance?.department?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: specialtyInstance, field: 'classGrades', 'error')} ">
	<label for="classGrades">
		<g:message code="specialty.classGrades.label" default="Class Grades" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${specialtyInstance?.classGrades?}" var="c">
    <li><g:link controller="classGrade" action="show" id="${c.id}">${c?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="classGrade" action="create" params="['specialty.id': specialtyInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'classGrade.label', default: 'ClassGrade')])}</g:link>
</li>
</ul>

</div>

