
<div class="control-group ${hasErrors(bean: contestInstance, field: 'name', 'error')} required">
	<label class="control-label" for="name">
		<g:message code="contest.name.label" default="Name" />
		<span class="text-error">*</span>
	</label>
	<div class="controls">
		<g:textField name="name" required="" value="${contestInstance?.name}" class="span4"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: contestInstance, field: 'sponsors', 'error')} required">
	<label class="control-label" for="sponsors">
		<g:message code="contest.sponsors.label" default="Sponsors" />
		<span class="text-error">*</span>
	</label>
	<div class="controls">
		<g:textArea name="sponsors" required="" value="${contestInstance?.sponsors}" rows="2" maxlength="120" class="span4"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: contestInstance, field: 'level', 'error')} required">
	<label class="control-label" for="level">
		<g:message code="contest.level.label" default="Level" />
		<span class="text-error">*</span>
	</label>
	<div class="controls">
		<g:select name="level" from="${contestInstance.constraints.level.inList}" required="" value="${contestInstance?.level}" valueMessagePrefix="contest.level"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: contestInstance, field: 'intro', 'error')} required">
	<label class="control-label" for="intro">
		<g:message code="contest.intro.label" default="Intro" />
		<span class="text-error">*</span>
	</label>
	<div class="controls">
		<g:textArea name="intro" required="" value="${contestInstance?.intro}" class="span4" rows="5" maxlength="250"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: contestInstance, field: 'website', 'error')} ">
	<label class="control-label" for="website">
		<g:message code="contest.website.label" default="Website" />
	</label>
	<div class="controls">
		<g:field type="url" name="website" value="${contestInstance?.website}" class="span4" title="竞赛官网，以 http 等开头"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: contestInstance, field: 'logo', 'error')} ">
	<label class="control-label" for="logo">
		<g:message code="contest.logo.label" default="Logo" />
	</label>
	<div class="controls">
		<g:field type="url" name="logo" value="${contestInstance?.logo}" class="span4" title="复制竞赛 Logo 图的网址粘贴在此"/>
	</div>
</div>

<input type="hidden" name="submitter.id" value="${contestInstance?.submitter?.id}"/>

<div class="control-group ${hasErrors(bean: contestInstance, field: 'principal', 'error')} required">
	<label class="control-label" for="principal">
		<g:message code="contest.principal.label" default="Principal" />
		<span class="text-error">*</span>
	</label>
	<div class="controls">
		<g:select id="principal" name="principal.id" from="${bzu.Staff.list()}" optionKey="id" required="" value="${contestInstance?.principal?.id}" class="span2"/>
		<span class="help-inlie muted">负责本赛事信息维护</span>
	</div>
</div>
