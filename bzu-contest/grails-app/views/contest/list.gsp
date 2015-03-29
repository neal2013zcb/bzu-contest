
<%@ page import="bzu.contest.Contest" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'contest.label', default: 'Contest')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-contest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-contest" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'contest.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="sponsor" title="${message(code: 'contest.sponsor.label', default: 'Sponsor')}" />
					
						<g:sortableColumn property="level" title="${message(code: 'contest.level.label', default: 'Level')}" />
					
						<g:sortableColumn property="intro" title="${message(code: 'contest.intro.label', default: 'Intro')}" />
					
						<g:sortableColumn property="website" title="${message(code: 'contest.website.label', default: 'Website')}" />
					
						<g:sortableColumn property="logo" title="${message(code: 'contest.logo.label', default: 'Logo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${contestInstanceList}" status="i" var="contestInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${contestInstance.id}">${fieldValue(bean: contestInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: contestInstance, field: "sponsor")}</td>
					
						<td>${fieldValue(bean: contestInstance, field: "level")}</td>
					
						<td>${fieldValue(bean: contestInstance, field: "intro")}</td>
					
						<td>${fieldValue(bean: contestInstance, field: "website")}</td>
					
						<td>${fieldValue(bean: contestInstance, field: "logo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${contestInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
