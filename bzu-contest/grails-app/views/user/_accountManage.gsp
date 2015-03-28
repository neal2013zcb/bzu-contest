<!-- 账号管理 user -->
<g:if test="${user}">
<span id="enabled-${user.id}">
<div class="btn-group">
	<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">
<g:if test="${user.enabled}">
	<i class="fa fa-lg fa-check-circle text-success"></i> 激活
</g:if>
<g:else>
	<i class="fa fa-lg fa-ban text-error"></i> 禁用
</g:else>
	</a>
	<ul class="dropdown-menu">
<g:if test="${user.enabled}">
		<li><g:remoteLink action="disable" id="${user.id}" controller="user" update="enabled-${user.id}"><i class="fa fa-ban text-error"></i> 禁用账号</g:remoteLink></li>
</g:if>
<g:else>
		<li><g:remoteLink action="enable" id="${user.id}" controller="user" update="enabled-${user.id}"><i class="fa fa-check-circle text-success"></i> 开启账号</g:remoteLink></li>
</g:else>
	</ul>
</div>
</span>
</g:if>
<g:else>
未注册
</g:else>