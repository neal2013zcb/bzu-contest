<!-- 查看权限 roles, allRoles -->
<%@page import="bzu.Constants"%>
<%@page import="bzu.Staff"%>
<ui:form>
	<g:each in="${allRoles}">
		<ui:field label="${it}.label">
		<g:if test="${it in roles}">
			<ui:fieldInput>
				<i class="fa fa-2x fa-toggle-on text-success" title="已具备此权限"></i>
			</ui:fieldInput>
			<ui:fieldHint>
				<span class="offset1 text-success" title=""><g:message code="${it}.text"/></span>
			</ui:fieldHint>
		</g:if>
		<g:else>
			<ui:fieldInput>
				<i class="fa fa-2x fa-toggle-off muted" title="尚无此权限"></i>
			</ui:fieldInput>
			<ui:fieldHint>
				<span class="offset1 muted" title=""><g:message code="${it}.text"/></span>
			</ui:fieldHint>
		</g:else>
		</ui:field>
	</g:each>
</ui:form>