<!-- 搜索栏 -->
<g:set var="searchableActions" value="${['staff.manage','student.manage','contest.list']}"/>
<g:set var="searchAction">${controllerName}.${actionName}</g:set>
<g:if test="${searchAction in searchableActions}">
	<!-- 搜索数据 -->
	<ul class="nav">
		<li>
		<form class="form-search navbar-search">
		<div class="input-append">
			<input name="q" value="${params?.q}" class="input-medium search-query" type="text" data-toggle="tooltip" title="在以下数据中搜索" placeholder="搜索以下数据"/>
			<button type="submit" class="btn">搜索</button>
		</div>
		</form>
		</li>
	</ul>
</g:if>
<g:else>
	<!-- 搜索竞赛项目 -->
	<ul class="nav">
		<li>
		<form action="${createLink(controller:'project', action:'list')}" class="form-search navbar-search">
		<div class="input-append">
			<input name="q" value="${params?.q}" class="input-medium search-query" type="text" data-toggle="tooltip" title="搜索学科竞赛项目" placeholder="搜索竞赛项目"/>
			<button type="submit" class="btn">搜索</button>
		</div>
		</form>
		</li>
	</ul>
</g:else>