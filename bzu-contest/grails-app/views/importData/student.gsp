<!DOCTYPE html>
<%@page import="bzu.ClassGrade"%>
<%@page import="bzu.Specialty"%>
<html>
	<head>
		<theme:layout name="sidebar"/>
		<title>导入学生信息</title>
	</head>
	<body>
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
		<input type="hidden" name="domain" value="student"/>
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
					<ui:th text="student.no.label"/>
					<ui:th text="student.name.label"/>
					<ui:th text="student.classGrade.label"/>
				</ui:tr>
				</thead>
				<tbody>
					<g:each in="${r}" var="studentInstance">
					<ui:tr class="success">
						<td>${fieldValue(bean: studentInstance, field: "no")}</td>
						<td>${fieldValue(bean: studentInstance, field: "name")}</td>
						<td>${fieldValue(bean: studentInstance, field: "classGrade")}</td>
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

<ui:form action="importStudent">
	<ui:fieldGroup>
		<ui:field name="text" label="班级信息列表 *" hint="每条记录占一行，依次包含规定字段，并用空白隔开。">
			<ui:fieldInput>
				<table style="color:brown">
					<tr>
						<td class="span1"><g:message code="student.no.label"/></td>
						<td class="span1"><g:message code="student.name.label"/></td>
						<td class="span1"><g:message code="student.classGrade.label"/></td>
					</tr>
				</table>
				<blockquote>
				</blockquote>
				<g:textArea name="text" required="" class="span6" rows="5">${result?.bad?.join('\n')}</g:textArea>
			</ui:fieldInput>
		</ui:field>
		<ui:field name="classGrade" label="默认所在班级" hint="省略所在班级的记录，默认为此班级">
			<ui:fieldInput>
				<g:select name="classGrade.id" from="${ClassGrade.list()}" optionKey="id" value="${result?.params?.classGrade?.id}" noSelection="['':'']"/>
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