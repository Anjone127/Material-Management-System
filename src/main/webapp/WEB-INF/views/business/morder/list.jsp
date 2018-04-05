<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<title>产品订单管理</title>
<link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>"
	type="image/x-icon" />
<link rel="stylesheet"
	href="<c:url value='/css/vendor/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
	<div id="morder-div" class="iframe-wrap">
		<div style="height: 45px; padding-top: 15px;">
			<form id="searchForm" class="form-horizontal">
				<div class="form-group" style="padding-left: 5px; padding-right: 15px">
					<label class="col-xs-2 control-label text-right">订单编号：</label>
					<div class="col-xs-4">
						<input type="text" name="number" id="number" class="form-control"
							value="">
					</div>
				</div>
			</form>
		</div>
		<div class="toobar">
			<button class="btn btn-primary js-search" id="ff">搜索</button>
			<button class="btn btn-primary js-add-morder" type="button">&nbsp;创建新订单</button>
		</div>
		<table id="morder-table"
			class="table table-striped table-bordered table-hover" width="100%">
			<thead>
				<tr>
					<th>订单编号</th>
					<th>供应商编号</th>
					<th>总金额</th>
					<th>退款总金额</th>
					<th>状态</th>
					<th>订单时间</th>
					<th>最后操做时间</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="material-div" style="display: none; padding: 15px;">
		<input type="hidden" id="supplierNumber">
		<div class="toobar">
			<button class="btn btn-primary js-back" type="button">&nbsp;返回</button>
		</div>
		<table id="material-table"
			class="table table-striped table-bordered table-hover" width="100%">
			<thead>
				<tr>
					<th>原材料编号</th>
					<th>供应商编号</th>
					<th>名称</th>
					<th>价格</th>
					<th>分类</th>
					<th>描述</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>

	<div id="detail-div" class="iframe-wrap" style="display: none;">
		订单详情： <input type="hidden" id="orderId">
		<table id="order-detail-table"
			class="table table-striped table-bordered table-hover" width="100%">
			<thead>
				<tr>
					<th>原材料编号</th>
					<th>价格</th>
					<th>数量</th>
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
	<script src="<c:url value='/js/business/morder/list_main.js'/>"></script>
</body>
</html>
