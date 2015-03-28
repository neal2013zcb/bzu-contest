<!-- 用户功能（个人设置，退出登录） -->
<ul class="nav">
	<sec:ifNotLoggedIn>
	<li class="${controllerName=='login'?'active':''}"><g:link controller="login" action="auth"><i class="fa fa-lock"></i> 登录</g:link></li>
 	<li class="${controllerName=='register'?'active':''}"><g:link controller="register" action="index"><i class="fa fa-edit"></i> 注册</g:link></li>
	</sec:ifNotLoggedIn>
	<sec:ifLoggedIn>
	<li class="dropdown ${controllerName=='settings'?'active':''}" title="当前用户个人设置">
		 <a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="fa fa-user text-info"></i> <bzu:user property="name"/> <strong class="caret"></strong></a>
		 <ul class="dropdown-menu">
		 	<li><g:link controller="settings" action="profile"><i class="fa fa-user"></i> 个人信息</g:link></li>
		 	<li><g:link controller="settings" action="contacts"><i class="fa fa-fax"></i> 联系方式</g:link></li>
		 	<li><g:link controller="settings" action="account"><i class="fa fa-key"></i> 登录账号</g:link></li>
		 	<li><g:link controller="settings" action="notifications"><i class="fa fa-inbox"></i> 通知消息</g:link></li>
			<li class="divider"></li>
		 	<li><g:link controller="logout"><i class="fa fa-power-off text-warning"></i> 安全退出</g:link></li>
	 	</ul>
	</li>
	</sec:ifLoggedIn>
</ul>
