<!-- 边栏功能导航 -->
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<g:set var="active" value="${{action-> action==actionName?'active':''}}"/>
		<li class="nav-header">个人设置</li>
		<li class="${active('profile')}"><g:link action="profile"><i class="fa fa-user"></i> 个人信息<i class="fa fa-chevron-right pull-right"></i></g:link></li>
		<li class="${active('contacts')}"><g:link action="contacts"><i class="fa fa-fax"></i> 联系方式<i class="fa fa-chevron-right pull-right"></i></g:link></li>
		<li class="${active('account')}"><g:link action="account"><i class="fa fa-key"></i> 登录账号<i class="fa fa-chevron-right pull-right"></i></g:link></li>
		<li class="${active('notifications')}"><g:link action="notifications"><i class="fa fa-inbox"></i> 通知消息<i class="fa fa-chevron-right pull-right"></i></g:link></li>
	</ul>
</div>
