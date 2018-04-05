<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入</title>
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/vendor/layout-default-latest.css">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/vendor/bootstrap.min.css">
</head>
<body class="">
<div class="container">
<div id="div1"><img src="img/loginbackground1.jpg" /></div>  
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">请登录</h3>
                </div>
                <div class="panel-body">
                    <form id="login-form" action="/login" method="POST">
                            <div class="form-group">
                                <input class="form-control" placeholder="账户名" id="userId" name="userId" autofocus autocomplete="off">
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="密码" id="userPassword" name="userPassword" type="password" autocomplete="off">
                            </div>
                            <input type="submit" value="登录" class="btn btn-primary form-control">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="js/lib/require.js"></script>
<script src="js/config.js"></script>
<script src="js/system/login_main.js"></script>
</body>
</html>