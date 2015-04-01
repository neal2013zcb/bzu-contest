<!-- 赛事展示 contestInstance -->
<div class="media">
	<!-- 右侧显示赛事 Logo 和官网链接 -->
	<div class="pull-right span2 well">
		<!-- 赛事 Logo 和链接 -->
		<div align="center">
		<a href="${contestInstance.website}" target="_blank" title="赛事官网">
			<img class="media-object" src="${contestInstance.logo ?: resource(dir: 'images', file: 'grails-cupsonly-logo-white.svg')}" alt="赛事官网">
		</a>
		</div>
		<!-- 官方网站 -->
		<small>
			<strong>官网：</strong>
				<a href="${contestInstance.website}" target="_blank" title="主办单位官方网站">${fieldValue(bean: contestInstance, field: "website")}</a>
		</small>
	</div>

	<!-- 左侧显示赛事基本信息 -->
	<div class="media-body">
		<!-- 标题及更新时间 -->
		<h4 class="media-heading">
			<g:link action="show" id="${contestInstance.id}">${fieldValue(bean: contestInstance, field: "name")}</g:link>
			<small><g:formatDate date="${contestInstance.lastUpdated}"/></small>
			<span class="pull-right">
		<g:if test="${contestInstance.approved}">
			<i class="fa fa-2x fa-certificate text-success" title="通过审核"></i>
		</g:if><g:else>
			<i class="fa fa-2x fa-exclamation-triangle text-warning" title="尚未审核"></i>
		</g:else>
			</span>
		</h4>
		
		<blockquote>
			<!-- 主办单位 -->
			<small class="text-warning">
				<strong><g:message code="contest.sponsors.label"/>：</strong>${contestInstance.sponsors.replaceAll(/[\n,;，；]/,'，')}<br>
			</small>
			<!-- 赛事层次 -->
			<small class="text-warning">
				<strong><g:message code="contest.level.label"/>：</strong><g:message code="contest.level.${contestInstance.level}"/><br>
			</small>
			<!-- 赛事简介 -->
			<span>${fieldValue(bean: contestInstance, field: "intro")}</span>
			<!-- 负责人信息（姓名，电话，邮件，办公地点） -->
			<small class="text-error">
				<strong><g:message code="contest.principal.label"/>： ${contestInstance.principal.name}</strong>
				 &nbsp;&nbsp; <span title="${message(code:'staff.workPhone.label')}"><i class="fa fa-phone-square"></i> ${contestInstance.principal.workPhone}</span>
				 &nbsp;&nbsp; <span title="${message(code:'staff.email.label')}"><i class="fa fa-envelope"></i> <a class="text-error" href="mailto:${contestInstance.principal.email}">${contestInstance.principal.email}</a></span>
				 &nbsp;&nbsp; <span title="${message(code:'staff.officeLocation.label')}"><i class="fa fa-map-marker"></i> ${contestInstance.principal.officeLocation}</span>
			</small>
		</blockquote>
	</div>
</div>
