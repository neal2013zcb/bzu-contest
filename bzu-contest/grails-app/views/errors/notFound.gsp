<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="dialog" />
	<title>页面不存在</title>
</head>
	<body>
		<theme:zone name="body">

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<p class="text-error lead"><span class="fa fa-unlink fa-4x"></span> 对不起，请求的页面不存在。</p>
			<blockquote class="text-error">
			<p class="text-warning lead"><span class="fa fa-hand-o-right fa-lg"></span> 可能是输入网址有误，或该对象已删除。</p>
			</blockquote>
			<a class="btn btn-large btn-primary" href="${request.getHeader('referer')}">返回</a>
		</div>
	</div>
</div>
	
		</theme:zone>
	</body>
</html>
