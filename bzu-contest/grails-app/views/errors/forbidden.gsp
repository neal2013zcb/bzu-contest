<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="dialog" />
	<title>拒绝访问</title>
</head>
	<body>
		<theme:zone name="body">

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<p class="text-error lead"><span class="fa fa-ban fa-4x"></span> 对不起，您的访问请求被拒绝。</p>
			<blockquote class="lead text-warning">
			<ui:displayMessage/>
			</blockquote>
			<a class="btn btn-large btn-primary" href="${request.getHeader('referer')}">返回</a>
		</div>
	</div>
</div>
	
		</theme:zone>
	</body>
</html>
