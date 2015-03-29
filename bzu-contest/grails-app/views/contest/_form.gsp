<%@ page import="bzu.contest.Contest" %>



<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="contest.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${contestInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'sponsor', 'error')} required">
	<label for="sponsor">
		<g:message code="contest.sponsor.label" default="Sponsor" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="sponsor" required="" value="${contestInstance?.sponsor}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'level', 'error')} required">
	<label for="level">
		<g:message code="contest.level.label" default="Level" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="level" from="${contestInstance.constraints.level.inList}" required="" value="${contestInstance?.level}" valueMessagePrefix="contest.level"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'intro', 'error')} required">
	<label for="intro">
		<g:message code="contest.intro.label" default="Intro" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="intro" required="" value="${contestInstance?.intro}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'website', 'error')} ">
	<label for="website">
		<g:message code="contest.website.label" default="Website" />
		
	</label>
	<g:field type="url" name="website" value="${contestInstance?.website}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'logo', 'error')} ">
	<label for="logo">
		<g:message code="contest.logo.label" default="Logo" />
		
	</label>
	<g:field type="url" name="logo" value="${contestInstance?.logo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'submitter', 'error')} required">
	<label for="submitter">
		<g:message code="contest.submitter.label" default="Submitter" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="submitter" name="submitter.id" from="${bzu.Staff.list()}" optionKey="id" required="" value="${contestInstance?.submitter?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'principal', 'error')} required">
	<label for="principal">
		<g:message code="contest.principal.label" default="Principal" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="principal" name="principal.id" from="${bzu.Staff.list()}" optionKey="id" required="" value="${contestInstance?.principal?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'approved', 'error')} ">
	<label for="approved">
		<g:message code="contest.approved.label" default="Approved" />
		
	</label>
	<g:checkBox name="approved" value="${contestInstance?.approved}" />
</div>

<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'approvedBy', 'error')} ">
	<label for="approvedBy">
		<g:message code="contest.approvedBy.label" default="Approved By" />
		
	</label>
	<g:select id="approvedBy" name="approvedBy.id" from="${bzu.Staff.list()}" optionKey="id" value="${contestInstance?.approvedBy?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: contestInstance, field: 'dateApproved', 'error')} ">
	<label for="dateApproved">
		<g:message code="contest.dateApproved.label" default="Date Approved" />
		
	</label>
	<g:datePicker name="dateApproved" precision="day"  value="${contestInstance?.dateApproved}" default="none" noSelection="['': '']" />
</div>

