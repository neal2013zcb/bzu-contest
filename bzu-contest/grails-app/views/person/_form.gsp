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
	<g:select name="gender" from="${personInstance.constraints.gender.inList}" value="${personInstance?.gender}" valueMessagePrefix="person.gender"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'category', 'error')} required">
	<label for="category">
		<g:message code="person.category.label" default="Category" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="category" from="${personInstance.constraints.category.inList}" required="" value="${personInstance?.category}" valueMessagePrefix="person.category"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'officePhone', 'error')} ">
	<label for="officePhone">
		<g:message code="person.officePhone.label" default="Office Phone" />
		
	</label>
	<g:textField name="officePhone" maxlength="20" pattern="${personInstance.constraints.officePhone.matches}" value="${personInstance?.officePhone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'cellPhone', 'error')} ">
	<label for="cellPhone">
		<g:message code="person.cellPhone.label" default="Cell Phone" />
		
	</label>
	<g:textField name="cellPhone" maxlength="20" pattern="${personInstance.constraints.cellPhone.matches}" value="${personInstance?.cellPhone}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="person.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" maxlength="50" value="${personInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'qq', 'error')} ">
	<label for="qq">
		<g:message code="person.qq.label" default="Qq" />
		
	</label>
	<g:textField name="qq" maxlength="20" pattern="${personInstance.constraints.qq.matches}" value="${personInstance?.qq}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personInstance, field: 'weixin', 'error')} ">
	<label for="weixin">
		<g:message code="person.weixin.label" default="Weixin" />
		
	</label>
	<g:textField name="weixin" maxlength="20" pattern="${personInstance.constraints.weixin.matches}" value="${personInstance?.weixin}"/>
</div>
