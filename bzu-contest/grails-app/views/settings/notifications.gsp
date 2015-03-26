<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="sidebar"/>
	<title>通知消息</title>
</head>
<body>
<theme:zone name="secondary-navigation">
</theme:zone>

<theme:zone name="sidebar">
	<g:render template="sidenav"/>
</theme:zone>

<theme:zone name="body">

<legend>通知消息</legend>

<ui:displayMessage/>


</theme:zone>
</body>
</html>
