<!DOCTYPE html>
<%@page import="bzu.Constants"%>
<html>
	<head>
		<theme:layout name="sidebar"/>
		<title>导入单位信息</title>
	</head>
	<body>

<theme:zone name="page-header">
	<legend>导入单位信息</legend>
</theme:zone>

<theme:zone name="secondary-navigation">
	<g:render template="subnav"/>
</theme:zone>

<theme:zone name="body">
<g:set value="${flash.result}" var="result"/>
<g:set value="${flash.result?.r}" var="r"/>
<g:set value="${flash.result?.bad}" var="bad"/>
<g:set value="${flash.result?.err}" var="err"/>

<ui:displayMessage/>

<g:if test="${result?.n>0}">
	<!-- 查看与撤销提交结果 -->
    <div>
		<g:if test="${r}">
		<a id="dialog-review" href="#modal-review" role="button" class="btn btn-success" data-toggle="modal">导入成功 ${result.n-result.e} 记录</a>
		<a id="dialog-review" href="#modal-review" role="button" class="btn btn-danger" data-toggle="modal">撤销本次导入</a>
		</g:if>
		<g:if test="${bad}">
		<a id="dialog-review" href="#modal-review-errors" role="button" class="btn btn-warning" data-toggle="modal">导入错误 ${result.e} 记录</a>
		</g:if>
    </div>
</g:if>

<g:if test="${flash.result?.r}">
	<!-- 预览与撤销 -->
	<ui:form action="undo">
		<input type="hidden" name="domain" value="department"/>
		<input type="hidden" name="ids" value="0"/>
		<g:each in="${r}"><input type="hidden" name="ids" value="${it.id}"/></g:each>

		<!-- 预览数据对话框 -->
		<div id="modal-review" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3 id="myModalLabel">
					导入成功
				</h3>
			</div>
			<div class="modal-body">
			<!-- 预览提交结果 -->
			<ui:table class="table-bordered table-hover table-condensed">
				<thead>
				<ui:tr>
					<ui:th text="department.no.label"/>
					<ui:th text="department.name.label"/>
					<ui:th text="department.shortName.label"/>
					<ui:th text="department.category.label"/>
				</ui:tr>
				</thead>
				<tbody>
					<g:each in="${r}" var="departmentInstance">
					<ui:tr class="success">
						<td>${fieldValue(bean: departmentInstance, field: "no")}</td>
						<td>${fieldValue(bean: departmentInstance, field: "name")}</td>
						<td>${fieldValue(bean: departmentInstance, field: "shortName")}</td>
						<td><g:message code="department.category.${departmentInstance.category}"/></td>
					</ui:tr>
					</g:each>
				</tbody>
			</ui:table>
			</div>
			<div class="modal-footer">
				 <input class="btn btn-danger pull-left" type="submit" value="撤销并删除 ${r.size()} 记录" onclick="return confirm('您确定要删除最近提交的 ${r.size()} 个记录吗？')"/>
				 <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
			</div>
		</div>

	</ui:form>
	
</g:if>

<g:if test="${result?.bad}">
	<!-- 错误提示 -->
	<div id="modal-review-errors" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h3 id="myModalLabel">
				导入数据错误
			</h3>
		</div>
		<div class="modal-body">
			<div class="">
				<table class="table table-bordered table-hover table-condensed ">
				<thead>
					<tr><th>提交数据</th><th>错误</th></tr>
				</thead>
				<tbody>
				<g:each in="${result?.bad}" var="record" status="i">
					<tr class="warning">
						<td>${record}</td><td class="text-error">${err[i]}</td>
					</tr>
				</g:each>
				</tbody>
				</table>
			</div>
		</div>
			<div class="modal-footer">
				 <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true">关闭</button>
			</div>
		</div>
</g:if>

<ui:form action="importDepartment">
	<ui:fieldGroup>
		<ui:field name="text" label="单位信息列表 *" hint="每条记录占一行，依次包含规定字段，并用空白隔开。">
			<ui:fieldInput>
				<table style="color:brown">
					<tr>
						<td class="span2"><g:message code="department.no.label"/></td>
						<td class="span2"><g:message code="department.name.label"/></td>
						<td class="span2"><g:message code="department.shortName.label"/></td>
						<td class="span4"><g:message code="department.category.label"/>（${Constants.Department.Category.VALUES.collect{g.message(code:'department.category.'+it)}.join('/')}）</td>
					</tr>
				</table>
				<blockquote>
				</blockquote>
				<g:textArea name="text" required="" class="span12" rows="5">${result?.bad?.join('\n')}</g:textArea>
			</ui:fieldInput>
		</ui:field>
		<ui:field name="category" label="默认单位类别" hint="省略单位类别的记录，默认为此类别">
			<ui:fieldInput>
				<g:select name="category" from="${Constants.Department.Category.VALUES}" value="${result?.params?.category}" noSelection="['':'']" valueMessagePrefix="department.category"/>
			</ui:fieldInput>
		</ui:field>
	</ui:fieldGroup>
	<ui:actions>
		<ui:button type="submit" mode="primary" class="btn-large" text="提交"/>
	</ui:actions>
</ui:form>

</theme:zone>

<theme:zone name="sidebar">
	<g:render template="links" />
</theme:zone>
		
	</body>
</html>