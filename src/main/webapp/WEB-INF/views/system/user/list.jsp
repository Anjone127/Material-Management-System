<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>用户管理</title>
    <link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>" type="image/x-icon"/>
    <link rel="stylesheet" href="<c:url value='/css/vendor/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
	<div style="height: 45px; padding-top: 15px;">
		<form id="searchFrom" class="form-horizontal">
			<div class="form-group" style="padding-left: 5px; padding-right: 15px">
				<label class="col-xs-2 control-label text-right">账号：</label>
				<div class="col-xs-4">
					<input type="text" name="userId" id="userId" name="userId"class="form-control" value="">
				</div>
				<label class="col-xs-2 control-label text-right">姓名：</label>
				<div class="col-xs-4">
					<input type="text" name="userName" id="userName" class="form-control" value="">
				</div>
			</div>
			<div class="form-group" style="padding-left: 5px; padding-right: 15px">
				<label class="col-xs-2 control-label text-right">产品种类：</label> 
				<div class="col-xs-4">
				<select id="typeSelect" class="form-control">
					<option value=""> 身份</option>
					<c:forEach items="${roleList}" var="role">
						<option id="${role.id}" value="${role.id}"> ${role.name}</option>
					</c:forEach>
				</select>
				</div>
			</div>
		</form>
	</div>
	<div class="iframe-wrap">
    <div class="toobar">
    	<button class="btn btn-primary js-search" >搜索</button>
        <button class="btn btn-primary js-add" type="button">&nbsp;创建新用户</button>
    </div>
    <table id="user-table" class="table table-striped table-bordered table-hover" width="100%">
        <thead>
        <tr>
            <th>账号</th>
            <th>姓名</th>
            <th>身份</th>
            <th>操作</th>
        </tr>
        </thead>
    </table>
</div>
<script id="tpl" type="text/x-handlebars-template">
    {{#each func}}
    <button type="button" class="{{this.class}}">{{this.text}}</button>
    {{/each}}
</script>
<script>
 window.__FRAMEWORK__ = {
  // 根路径
  root_url: "<c:url value='/' />".split(';')[0]
 };
</script>
	<script src="<c:url value='/js/lib/require.js'/>"></script>
	<script src="<c:url value='/js/config.js'/>"></script>
<script src="<c:url value='/js/system/user/list_main.js'/>"></script>
</body>
</html>
