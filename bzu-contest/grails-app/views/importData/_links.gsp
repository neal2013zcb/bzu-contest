<!-- 导入各类数据 -->
<div class="btn-group btn-group-vertical pull-right">
	<g:link class="btn ${actionName=='department'?'btn-primary':''}" action="department"><i class="fa fa-lg fa-university pull-right"></i> 导入单位信息</g:link>
	<g:link class="btn ${actionName=='specialty'?'btn-primary':''}" action="specialty"><i class="fa fa-lg fa-graduation-cap pull-right"></i> 导入专业信息</g:link>
	<g:link class="btn ${actionName=='classGrade'?'btn-primary':''}" action="classGrade"><i class="fa fa-lg fa-users pull-right"></i> 导入班级信息</g:link>
	<g:link class="btn ${actionName=='student'?'btn-primary':''}" action="student"><i class="fa fa-lg fa-slideshare pull-right"></i> 导入学生信息</g:link>
	<g:link class="btn ${actionName=='staff'?'btn-primary':''}" action="staff"><i class="fa fa-lg fa-user pull-right"></i> 导入教工信息</g:link>
</div>
