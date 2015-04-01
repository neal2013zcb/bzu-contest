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
