<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<title>原材料库存</title>
<link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>"
	type="image/x-icon" />
<link rel="stylesheet"
	href="<c:url value='/css/vendor/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
	<div class="iframe-wrap">
		<div style="height: 45px; ">
			<form id="searchFrom" class="form-horizontal">
				<div class="form-group" style="padding-left: 5px; padding-right: 15px">
					<label class="col-xs-2 control-label text-right">订单编号：</label>
					<div class="col-xs-4">
						<input type="text" name="sId" id="sId" class="form-control"
							value="">
					</div>
					<label class="col-xs-2 control-label text-right">产品编号：</label>
					<div class="col-xs-4">
						<input type="text" name="productNumber" id="productNumber"
							class="form-control" value="">
					</div>
				</div>
			</form>
		</div>
		<div class="iframe-wrap">
			<div class="toobar">
				<button class="btn btn-primary js-search">搜索</button>
				<button class="btn btn-primary js-add">新增生产计划</button>
			</div>
			<table id="production-table"
				class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th>编号</th>
						<th>产品编号</th>
						<th>数量</th>
						<th>状态</th>
						<th>订单日期</th>
						<th>完成日期</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</div>
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
	<script src="<c:url value='/js/business/production/list_main.js'/>"></script>
</body>
</html>
