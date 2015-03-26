<%@page import="bzu.ClassGrade"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<theme:layout name="dialog"/>
	<title>学生注册</title>
</head>
<body>

	<theme:zone name="body">
	
	<ui:displayMessage/>

  <ui:form class="form-horizontal" action="registerStudent">
    <fieldset>

    	<div class="control-group">
          <label class="control-label" for="no">学号</label>
          <div class="controls">
            <input id="no" name="no" value="${params.no}" placeholder="您的学号" class="input-xlarge" type="text" required="required">
            <p class="help-block">输入您的学号</p>
          </div>
        </div>
        
        <div class="control-group">
          <label class="control-label" for="password">密码</label>
          <div class="controls">
            <input name="password" value="${params.password}" placeholder="校内门户密码" class="input-xlarge" type="password" required="required">
            <p class="help-block">输入校内门户网站密码以验证身份</p>
          </div>
        </div>
        
    	<div class="control-group">
          <label class="control-label" for="name">姓名</label>
          <div class="controls">
            <input name="name" value="${params.name}" placeholder="您的姓名" class="input-xlarge" type="text" required="required">
            <p class="help-block">输入您的真实姓名</p>
          </div>
        </div>

    	<div class="control-group">
          <label class="control-label">所在班级</label>
          <div class="controls">
          	<g:select name="classGrade.id" from="${ClassGrade.list(sort:'name', order:'desc')}" optionKey="id" noSelection="['':'']" value="${params['classGrade.id']}" required=""/>
            <p class="help-block">选择您所在的班级</p>
          </div>

        </div>

    	<div class="control-group">
          <div class="controls">
            <button class="btn btn-primary btn-large">学生注册</button>
          </div>
        </div>

    </fieldset>
  </ui:form>
<jq:jquery>
$('#no').focus();
</jq:jquery>

	</theme:zone>
</body>
</html>