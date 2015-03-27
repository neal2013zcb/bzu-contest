<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="main"/>
	<title>导入数据</title>
</head>
<body>
<theme:zone name="secondary-navigation">
</theme:zone>
<theme:zone name="body">

<ui:displayMessage />

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span12">
			<ul class="thumbnails">
				<li class="span4">
					<div class="thumbnail">
						<div class="caption">
							<h3 style="color:#191970">
								<i class="fa fa-lg fa-university"></i>
								 单位
							</h3>
							<p>
								校内各系院和各部门。提供单位列表（包括<span style="color:brown"><g:message code="department.no.label"/>，<g:message code="department.name.label"/>，<g:message code="department.shortName.label"/>，<g:message code="department.category.label"/></span>），可以批量导入单位信息。
							</p>
							<p>
								<g:link class="btn btn-primary" action="department">导入</g:link>
								<g:link class="btn" action="list" controller="department">浏览</g:link>
							</p>
						</div>
					</div>
				</li>
				<li class="span4">
					<div class="thumbnail">
						<div class="caption">
							<h3 style="color:#C71585">
								<i class="fa fa-lg fa-graduation-cap"></i>
								专业
							</h3>
							<p>
								各系院开办的各专业。提供专业列表（包括<span style="color:brown"><g:message code="specialty.no.label"/>，<g:message code="specialty.name.label"/>，<g:message code="specialty.shortName.label"/>，<g:message code="specialty.level.label"/>，<g:message code="specialty.department.label"/></span>），可以批量导入专业信息。
							</p>
							<p>
								<g:link class="btn btn-primary" action="specialty">导入</g:link>
								<g:link class="btn" action="list" controller="specialty">浏览</g:link>
							</p>
						</div>
					</div>
				</li>
				<li class="span4">
					<div class="thumbnail">
						<div class="caption">
							<h3 style="color:#698B22">
								<i class="fa fa-lg fa-users"></i>
								班级
							</h3>
							<p>
								各专业各个班级。提供班级列表（<span style="color:brown"><g:message code="classGrade.name.label"/>，<g:message code="classGrade.grade.label"/>，<g:message code="classGrade.classNo.label"/>，<g:message code="classGrade.specialty.label"/></span>），可以批量导入班级信息。
							</p>
							<p>
								<g:link class="btn btn-primary" action="classGrade">导入</g:link>
								<g:link class="btn" action="list" controller="classGrade">浏览</g:link>
							</p>
						</div>
					</div>
				</li>
			</ul>
			<ul class="thumbnails">
				<li class="span4">
					<div class="thumbnail">
						<div class="caption">
							<h3 style="color:#CD853F">
								<i class="fa fa-lg fa-slideshare"></i>
								学生
							</h3>
							<p>
								包括各系院各班级的所有学生。提供学生列表（包括<span style="color:brown">学号，姓名，所在班级</span>），可以批量导入学生信息。
							</p>
							<p>
								<g:link class="btn btn-primary" action="student">导入</g:link>
								<g:link class="btn" action="list" controller="student">浏览</g:link>
							</p>
						</div>
					</div>
				</li>
				<li class="span4">
					<div class="thumbnail">
						<div class="caption">
							<h3 style="color:#8B1A1A">
								<i class="fa fa-lg fa-user"></i>
								教师
							</h3>
							<p>
								包括各单位所有教师。提供教师列表（（包括<span style="color:brown">教师号，姓名，所在单位</span>），可以批量导入教师信息。
							</p>
							<p>
								<g:link class="btn btn-primary" action="staff">导入</g:link>
								<g:link class="btn" action="list" controller="staff">浏览</g:link>
							</p>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</div>

</theme:zone>
</body>
</html>

