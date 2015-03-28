<!-- 基本信息审核操作 -->
<span id="approved-${person.id}">
<div class="btn-group">
	<a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">
<g:if test="${person.approved}">
	<i class="fa fa-lg fa-check-circle text-success"></i> 已审核
</g:if>
<g:else>
	<i class="fa fa-lg fa-exclamation-circle text-error"></i> 未审核
</g:else>
	</a>
	<ul class="dropdown-menu">
<g:if test="${person.approved}">
		<li><g:remoteLink action="undoApproved" id="${person.id}" controller="person" update="approved-${person.id}"><i class="fa fa-exclamation-circle text-error"></i> 撤消审核</g:remoteLink></li>
</g:if>
<g:else>
		<li><g:remoteLink action="approve" id="${person.id}" controller="person" update="approved-${person.id}"><i class="fa fa-check-circle text-success"></i> 通过审核</g:remoteLink></li>
</g:else>
	</ul>
</div>
</span>
