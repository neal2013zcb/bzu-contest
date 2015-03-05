<%@ page import="bzu.ClassGrade" %>



<div class="fieldcontain ${hasErrors(bean: classGradeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="classGrade.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="50" required="" value="${classGradeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: classGradeInstance, field: 'grade', 'error')} required">
	<label for="grade">
		<g:message code="classGrade.grade.label" default="Grade" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="grade" from="${2010..2025}" class="range" required="" value="${fieldValue(bean: classGradeInstance, field: 'grade')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: classGradeInstance, field: 'classNo', 'error')} required">
	<label for="classNo">
		<g:message code="classGrade.classNo.label" default="Class No" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="classNo" from="${1..20}" class="range" required="" value="${fieldValue(bean: classGradeInstance, field: 'classNo')}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: classGradeInstance, field: 'specialty', 'error')} required">
	<label for="specialty">
		<g:message code="classGrade.specialty.label" default="Specialty" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="specialty" name="specialty.id" from="${bzu.Specialty.list()}" optionKey="id" required="" value="${classGradeInstance?.specialty?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: classGradeInstance, field: 'students', 'error')} ">
	<label for="students">
		<g:message code="classGrade.students.label" default="Students" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${classGradeInstance?.students?}" var="s">
    <li><g:link controller="student" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="student" action="create" params="['classGrade.id': classGradeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'student.label', default: 'Student')])}</g:link>
</li>
</ul>

</div>

