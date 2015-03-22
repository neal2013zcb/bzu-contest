<%@ page import="bzu.Student" %>



<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'no', 'error')} required">
	<label for="no">
		<g:message code="student.no.label" default="No" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="no" maxlength="20" pattern="${studentInstance.constraints.no.matches}" required="" value="${studentInstance?.no}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="student.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="50" required="" value="${studentInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'gender', 'error')} required">
	<label for="gender">
		<g:message code="student.gender.label" default="Gender" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="gender" from="${studentInstance.constraints.gender.inList}" required="" value="${studentInstance?.gender}" valueMessagePrefix="student.gender"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'workPhone', 'error')} ">
	<label for="workPhone">
		<g:message code="student.workPhone.label" default="Work Phone" />
		
	</label>
	<g:textField name="workPhone" maxlength="20" pattern="${studentInstance.constraints.workPhone.matches}" value="${studentInstance?.workPhone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'homePhone', 'error')} ">
	<label for="homePhone">
		<g:message code="student.homePhone.label" default="Home Phone" />
		
	</label>
	<g:textField name="homePhone" maxlength="20" pattern="${studentInstance.constraints.homePhone.matches}" value="${studentInstance?.homePhone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="student.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" maxlength="50" value="${studentInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'account', 'error')} ">
	<label for="account">
		<g:message code="student.account.label" default="Account" />
		
	</label>
	<g:select id="account" name="account.id" from="${bzu.security.User.list()}" optionKey="id" value="${studentInstance?.account?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: studentInstance, field: 'classGrade', 'error')} required">
	<label for="classGrade">
		<g:message code="student.classGrade.label" default="Class Grade" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="classGrade" name="classGrade.id" from="${bzu.ClassGrade.list()}" optionKey="id" required="" value="${studentInstance?.classGrade?.id}" class="many-to-one"/>
</div>

