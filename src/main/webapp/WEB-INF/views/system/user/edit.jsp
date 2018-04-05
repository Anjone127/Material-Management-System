<%--
   Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>用户管理</title>
    <link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>" type="image/x-icon"/>
    <link rel="stylesheet" href="<c:url value='/css/vendor/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
<div class="container .container-fluid">
    <div class="" style="margin-top: 10px;">
        <form class="form-horizontal" id="userForm">
            <input type="hidden" id="id" name="id" value="${user.id}">
				<input type="hidden" id="userRoleId1" value="${user.userRoleId}">
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">用户名：</label>
                <div class="col-xs-9">
                    <input type="text" name="userId" id="userId" ${user.id eq null?" ":"readonly=\"readonly\" "} class="form-control" placeholder="请输入登录用户名" value="${user.userId}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">登录密码：</label>
                <div class="col-xs-9">
                    <input type="text" name="userPassword" id="userPassword" class="form-control" placeholder="${empty user.id?"请输入登录密码":"如需修改密码，请输入新密码"}"
                           value="" autocomplete="off">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">姓名：</label>
                <div class="col-xs-9">
                    <input type="text" name="userName" id="userName" class="form-control" placeholder="请输入姓名"
                           value="${user.userName}" autocomplete="off">
                </div>
            </div>
            
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">身份：</label>
                <div class="col-xs-9">
            <select id="userRoleId" name="userRoleId" class="form-control">
					<option value=""> 未分类</option>
					<c:forEach items="${roleList}" var="role">
						<option id="${role.id}" value="${role.id}"> ${role.name}</option>
					</c:forEach>
				</select>
				 </div>
            </div>
            <div class="row text-center">
                <button id="cancel" type="button" class="btn btn-primary">取消</button>
                <button id="save" type="submit" class="btn btn-primary">保存</button>
            </div>
        </form>
    </div>
</div>
<script>
 window.__FRAMEWORK__ = {
  // 根路径
  root_url: "<c:url value='/' />".split(';')[0]
 };
</script>
	<script src="<c:url value='/js/lib/require.js'/>"></script>
	<script src="<c:url value='/js/config.js'/>"></script>
<script src="<c:url value='/js/system/user/edit_main.js'/>"></script>
</body>
</html>