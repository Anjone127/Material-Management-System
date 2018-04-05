<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<title>用户管理</title>
<link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>"
	type="image/x-icon" />
<link rel="stylesheet"
	href="<c:url value='/css/vendor/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
	<div style="height: 45px; padding-top: 15px;">
		<form id="searchForm" class="form-horizontal">
			<div class="form-group"
				style="padding-left: 5px; padding-right: 15px">
				<label class="col-xs-2 control-label text-right">编号：</label>
				<div class="col-xs-4">
					<input type="text" name="number" id="number" name="userId"
						class="form-control" value="">
				</div>
				<label class="col-xs-2 control-label text-right">名称：</label>
				<div class="col-xs-4">
					<input type="text" name="name" id="name" class="form-control"
						value="">
				</div>
			</div>
			<div >
				<label class="col-xs-2 control-label text-right">产品种类：</label>
				<div class="col-xs-4">
					<select id="typeSelect" class="form-control">
						<option value="">未分类</option>
						<c:forEach items="${typeList}" var="type">
							<option id="${type.id}" value="${type.id}">${type.name}</option>
						</c:forEach>
					</select>
				</div>
				<div align="right" style="padding-right: 15px">
					<button class="btn btn-primary js-search" id="ff">搜索</button>
					<button class="btn btn-primary js-add" type="button">创建</button>
					<a class="btn btn-primary" href="/info/product/excel">导出</a>
				</div>
			</div>
		</form>
	</div>
	<div style="padding-top: 60px"class="iframe-wrap">
		<table id="product-table"
			class="table table-striped table-bordered table-hover" width="100%">
			<thead>
				<tr>
					<th>编号</th>
					<th>名称</th>
					<th>价格</th>
					<th>产品种类</th>
					<th>描述</th>
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
			root_url : "<c:url value='/' />".split(';')[0]
		};
	</script>
	<script src="<c:url value='/js/lib/require.js'/>"></script>
	<script src="<c:url value='/js/config.js'/>"></script>
	<script src="<c:url value='/js/info/product/list_main.js'/>"></script>
</body>
</html>
