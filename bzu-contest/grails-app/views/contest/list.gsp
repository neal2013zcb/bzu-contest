<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="report"/>
	<title>赛事浏览</title>
</head>
<body>

<theme:zone name="page-header">
</theme:zone>

<theme:zone name="secondary-navigation">
<sec:ifAnyGranted roles="ROLE_PROJECT">
<ul class="nav nav-pills">
  <li>
  	<g:link action="create">新建赛事</g:link>
  </li>
</ul>
</sec:ifAnyGranted>
</theme:zone>

<theme:zone name="body">
	<ui:displayMessage/>
	
<g:each in="${contestInstanceList}" status="i" var="contestInstance">
	<g:render template="contestDisplay" model="[contestInstance:contestInstance]"></g:render>
</g:each>

</theme:zone>
<theme:zone name="pagination">
	<ui:paginate class=" pagination-centered pagination-large" total="${contestInstanceTotal}" params="${params}"/>
</theme:zone>
</body>
</html>
