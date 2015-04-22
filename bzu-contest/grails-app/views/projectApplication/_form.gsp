<%@ page import="bzu.contest.ProjectApplication" %>

<div class="control-group ${hasErrors(bean: projectApplicationInstance, field: 'contest', 'error')} required">
	<label class="control-label" for="contest">
		<g:message code="projectApplication.contest.label" default="Contest" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:hiddenField name="contest.id" value="${projectApplicationInstance?.contest?.id}"/>
		<g:link controller="contest" action="show" id="${projectApplicationInstance?.contest?.id}">${projectApplicationInstance?.contest?.name}</g:link>
	</div>
</div>

<div class="control-group ${hasErrors(bean: projectApplicationInstance, field: 'name', 'error')} required">
	<label class="control-label" for="name">
		<g:message code="projectApplication.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls">
	<g:textField name="name" maxlength="100" required="" value="${projectApplicationInstance?.name}" class="span4" placeHolder="如：第X届XX杯...竞赛"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: projectApplicationInstance, field: 'sponsors', 'error')} required">
	<label class="control-label" for="sponsors">
		<g:message code="projectApplication.sponsors.label" default="Sponsors" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:textArea name="sponsors" required="" value="${projectApplicationInstance?.sponsors}" rows="2" maxlength="120" class="span4"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: projectApplicationInstance, field: 'level', 'error')} required">
	<label class="control-label" for="level">
		<g:message code="projectApplication.level.label" default="Level" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls">
	<g:select name="level" from="${projectApplicationInstance.constraints.level.inList}" required="" value="${projectApplicationInstance?.level}" valueMessagePrefix="projectApplication.level"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: projectApplicationInstance, field: 'approach', 'error')} required">
	<label class="control-label" for="approach">
		<g:message code="projectApplication.approach.label" default="Approach" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<g:textArea name="approach" required="" value="${projectApplicationInstance?.approach}" rows="2" maxlength="100" class="span4"/>
	</div>
</div>

<div class="control-group ${hasErrors(bean: projectApplicationInstance, field: 'venues', 'error')} required">
	<label class="control-label" for="venues">
		<g:message code="projectApplication.venues.label" default="Venues" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls">
	<g:textField name="venues" maxlength="100" required="" value="${projectApplicationInstance?.venues}"/>
	</div>
</div>

<link rel="stylesheet" href="${resource(dir: 'css/datetimepicker/css', file: 'datepicker.css')}">
<div class="control-group ${hasErrors(bean: projectApplicationInstance, field: 'startDate', 'error')} required">
	<label for="startDate" class="control-label"><g:message code="projectApplication.startDate.label" default="Start Date"/> *</label>
	<div class="controls">
		<div class="input-prepend">
		<span class="add-on"><i class="fa fa-calendar"></i></span>
		<input type="text" size="16" id="startDate" name="startDate" value="${projectApplicationInstance?.startDate?.format('yyyy-MM-dd')}" readonly required="" class="form_datetime" style="width:8em"/>
		</div>
	</div>
</div>

<div class="control-group ${hasErrors(bean: projectApplicationInstance, field: 'endDate', 'error')} required">
	<label class="control-label" for="endDate">
		<g:message code="projectApplication.endDate.label" default="End Date" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls">
		<div class="input-prepend">
		<span class="add-on"><i class="fa fa-calendar"></i></span>
		<input type="text" size="16" id="endDate" name="endDate" value="${projectApplicationInstance?.endDate?.format('yyyy-MM-dd')}" readonly required="" class="form_datetime" style="width:8em"/>
		</div>
	</div>
</div>

<script src="${resource(dir: 'css/datetimepicker/js', file: 'bootstrap-datepicker.js')}"></script>
<script src="${resource(dir: 'css/datetimepicker/js/locales', file: 'bootstrap-datepicker.zh-CN.js')}"></script>		
<jq:jquery>
$(".form_datetime").datepicker({format: 'yyyy-mm-dd', autoclose:true, language:'zh-CN', todayHighlight:true});
</jq:jquery>

<div class="control-group ${hasErrors(bean: projectApplicationInstance, field: 'department', 'error')} required">
	<label class="control-label" for="department">
		<g:message code="projectApplication.department.label" default="Department" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls">
	<g:hiddenField name="department.id" value="${projectApplicationInstance?.department?.id}"/>
	<g:link controller="department" action="show" id="${projectApplicationInstance?.department?.id}">${projectApplicationInstance?.department?.name}</g:link>
	</div>
</div>

<div class="control-group ${hasErrors(bean: projectApplicationInstance, field: 'principal', 'error')} required">
	<label class="control-label" for="principal">
		<g:message code="projectApplication.principal.label" default="Principal" />
		<span class="required-indicator">*</span>
	</label>
	<div class="controls">
	<g:hiddenField name="principal.id" value="${projectApplicationInstance?.principal?.id}"/>
	<span>${projectApplicationInstance?.principal}</span>
	</div>
</div>
