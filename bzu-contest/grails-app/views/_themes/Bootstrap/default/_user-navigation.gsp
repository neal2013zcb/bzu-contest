<nav:menu scope="user"/>
<ul class="nav">
	<sec:ifNotLoggedIn>
 	<li class="${controllerName=='login'?'active':''}"><g:link controller="login" action="auth"><i class="fa fa-lock"></i> 登录</g:link></li>
 	<li class="${controllerName=='register'?'active':''}"><g:link controller="register" action="index"><i class="fa fa-edit"></i> 注册</g:link></li>
	</sec:ifNotLoggedIn>
	<sec:ifLoggedIn>
 	<li class="${controllerName=='settings'?'active':''}" title="个人设置"><g:link controller="settings" action="profile"><i class="fa fa-user"></i> <bzu:user property="name"/></g:link></li>
 	<li><g:link controller="logout" title="安全退出"><i class="fa fa-power-off"></i></g:link></li>
	</sec:ifLoggedIn>
</ul>
