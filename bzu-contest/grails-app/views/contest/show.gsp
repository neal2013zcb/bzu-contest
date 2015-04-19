<%@ page import="bzu.contest.Contest" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="dataentry"/>
	<title><g:fieldValue bean="${contestInstance}" field="name"/></title>
</head>
<body>
<theme:zone name="secondary-navigation">
</theme:zone>
<theme:zone name="page-header">
</theme:zone>

<theme:zone name="body">

<ui:displayMessage/>


<div class="container-fluid">
	<div class="row-fluid">
		<div class="span11">
		
<div class="hero-unit">
	
	<div class="pull-right span4 well">
		<div align="center">
		<a href="${contestInstance.website}" target="_blank" title="赛事官网">
			<img class="media-object" src="${contestInstance.logo ?: resource(dir: 'images', file: 'grails-cupsonly-logo-white.svg')}" alt="赛事官网">
		</a>
		</div>
		<small>
			<strong><g:message code="contest.website.label"/>：</strong>
				<a href="${contestInstance.website}" target="_blank" title="主办单位官方网站">${fieldValue(bean: contestInstance, field: "website")}</a>
			<br>
			<strong><g:message code="contest.sponsors.label"/>：</strong><br>
			<blockquote>
				${contestInstance.sponsors.replaceAll(/[\n,;，；]/,'<br/>')}
			</blockquote>
		</small>
	</div>

	<h2><g:fieldValue bean="${contestInstance}" field="name"/></h2>

	<small>
	<ul class="text-warning">
		<li>
		<strong><g:message code="contest.sponsors.label"/>：</strong>${contestInstance.sponsors.replaceAll(/[\n,;，；]/,'，')}
		</li>
		<li>
		<strong><g:message code="contest.level.label"/>：</strong><g:message code="contest.level.${contestInstance.level}"/>
		</li>
	</ul>
	</small>
	
	<p><g:fieldValue bean="${contestInstance}" field="intro"/></p>
	
	<blockquote>
		<small class="text-error">
			<strong><g:message code="contest.principal.label"/>： ${contestInstance.principal.name}</strong>
			 &nbsp;&nbsp; <span title="${message(code:'staff.workPhone.label')}"><i class="fa fa-phone-square"></i> ${contestInstance.principal.workPhone}</span>
			 &nbsp;&nbsp; <span title="${message(code:'staff.email.label')}"><i class="fa fa-envelope"></i> <a class="text-error" href="mailto:${contestInstance.principal.email}">${contestInstance.principal.email}</a></span>
			 &nbsp;&nbsp; <span title="${message(code:'staff.officeLocation.label')}"><i class="fa fa-map-marker"></i> ${contestInstance.principal.officeLocation}</span>
		</small>
	</blockquote>


	<p>
<sec:ifAnyGranted roles="ROLE_PROJECT,ROLE_ADMIN">
<g:form class="form-inline" method="post">
	<g:hiddenField name="id" value="${contestInstance.id}"/>
<sec:ifAnyGranted roles="ROLE_PROJECT">
	<bzu:ifPrincipal target="${contestInstance}">
		<g:link action="edit" id="${contestInstance.id}" class="btn btn-warning">编辑</g:link>
	</bzu:ifPrincipal>
	<g:link action="apply" id="${contestInstance.id}" class="btn btn-primary">申请项目</g:link>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_ADMIN">
	<g:if test="${contestInstance.approved}">
		<g:actionSubmit class="btn btn-warning" value="取消审核" action="undoApprove"/>
	</g:if>
	<g:else>
		<g:actionSubmit class="btn btn-warning" value="审核通过" action="doApprove"/>
	</g:else>
</sec:ifAnyGranted>
</g:form>
</sec:ifAnyGranted>
	</p>
	
</div>
		</div>
	</div>
</div>

<h2>竞赛项目</h2>

</theme:zone>
</body>
</html>
