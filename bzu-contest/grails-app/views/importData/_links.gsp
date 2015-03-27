<!-- 导入各类数据 -->
<div class="well sidebar-nav">
	<ul class="nav nav-list">
		<li class="nav-header">导入数据</li>
		<li class="${actionName=='department'?'active':''}"><g:link action="department"><i class="fa fa-chevron-right pull-right"></i> 导入单位信息</g:link></li>
		<li class="${actionName=='specialty'?'active':''}"><g:link action="specialty"><i class="fa fa-chevron-right pull-right"></i> 导入专业信息</g:link></li>
		<li class="${actionName=='classGrade'?'active':''}"><g:link action="classGrade"><i class="fa fa-chevron-right pull-right"></i> 导入班级信息</g:link></li>
		<li class="${actionName=='student'?'active':''}"><g:link action="student"><i class="fa fa-chevron-right pull-right"></i> 导入学生信息</g:link></li>
		<li class="${actionName=='staff'?'active':''}"><g:link action="staff"><i class="fa fa-chevron-right pull-right"></i> 导入教师信息</g:link></li>
	</ul>
</div>