<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_DEPARTMENT">
<!-- 系统管理（单位，专业，班级，教师，学生） -->
<ul class="nav">
	<li class="dropdown ${controllerName in ['department','specialty','classGrade','staff','student','importData']?'active':''}">
		<a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="fa fa-cogs"></i> 管理 <strong class="caret"></strong></a>
		<ul class="dropdown-menu">
			<li><g:link controller="department"><i class="fa fa-university"></i> 单位</g:link></li>
			<li><g:link controller="specialty"><i class="fa fa-graduation-cap"></i> 专业</g:link></li>
			<li><g:link controller="classGrade"><i class="fa fa-users"></i> 班级</g:link></li>
			<li class="divider"></li>
			<li><g:link controller="student"><i class="fa fa-slideshare"></i> 学生</g:link></li>
			<li><g:link controller="staff"><i class="fa fa-lg fa-user"></i> 教师</g:link></li>
			<li class="divider"></li>
			<li><g:link controller="importData"><i class="fa fa-upload"></i> 导入数据</g:link></li>
		</ul>
	</li>
</ul>
</sec:ifAnyGranted>