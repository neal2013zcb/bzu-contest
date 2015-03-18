<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="home"/>
	<theme:title>用户注册</theme:title>
</head>
<body>

	<theme:zone name="masthead"/>
	
	<theme:zone name="body">

		<ui:displayMessage />

<div class="container-fluid">
	<div class="row-fluid">
		<div class="span6">
			<div class="hero-unit">
				<h2 style="color:orange"><i class="fa fa-user"></i> 学生注册</h2>
				<p>在校学生注册后可以</p>
				<ul style="color:orange">
					<li>查看学科竞赛信息</li>
					<li>参加学科竞赛</li>
					<li>查看个人获奖情况</li>
				</ul>
				<p>
					<g:link class="btn btn-primary btn-large" action="student">学生注册 »</g:link>
				</p>
			</div>
		</div>
		<div class="span6">
			<div class="hero-unit">
				<h2><i class="fa fa-user"></i> 教工注册</h2>
				<p>在校教职工注册后可以</p>
				<ul style="color:green">
					<li>查看学科竞赛信息</li>
					<li>指导学生参赛</li>
					<li>竞赛管理</li>
				</ul>
				<p>
					<g:link class="btn btn-primary btn-large" action="staff">教工注册 »</g:link>
				</p>
			</div>
		</div>
	</div>
</div>

	</theme:zone>
	
	<theme:zone name="panel1"/>
	<theme:zone name="panel2"/>
	<theme:zone name="panel3"/>
</body>
</html>