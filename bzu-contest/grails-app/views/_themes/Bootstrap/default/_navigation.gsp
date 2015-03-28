<!-- 首页，竞赛 -->
<ul class="nav">
	<li class="${controllerName==null?'active':''}">
		<a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i> 首页</a>
	</li>
	<li class="${controllerName=='contest'?'active':''}">
		<g:link controller="contest"><i class="fa fa-trophy"></i> 竞赛</g:link>
	</li>
</ul>
