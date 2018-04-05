<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<title>产品管理</title>
<link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>"
	type="image/x-icon" />
<link rel="stylesheet"
	href="<c:url value='/css/vendor/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
	<div class="container .container-fluid">
		<div id="product-div" style="margin-top: 10px;">
			<form class="form-horizontal" id="productForm">
				<input type="hidden" id="productId" name="id" value="${product.id}">
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">编号：</label>
					<div class="col-xs-9">
						<input type="text" name="number" id="number"
							${product.id eq null?"":"readonly=\"readonly\" "}
							class="form-control" placeholder="请输入产品编号"
							value="${product.number}" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">名称：</label>
					<div class="col-xs-9">
						<input type="text" name="name" id="name" class="form-control"
							value="${product.name}" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">价格：</label>
					<div class="col-xs-3">
						<input type="text" name="price" id="price" class="form-control"
							value="${product.price}" autocomplete="off">
					</div>
					<label class="col-xs-3 control-label text-right">成本价：</label>
					<div class="col-xs-3">
						<input type="text" name="cost" id="cost" class="form-control"
							readonly="readonly" value="${product.cost}" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">产品分类：</label>
					<div class="col-xs-9">
						<input type="hidden" id="typeidhidden" value="${product.typeid}">
						<select id="typeid" name="typeid" class="form-control">
							<option value="">未分类</option>
							<c:forEach items="${typeList}" var="type">
								<option id="${type.id}" value="${type.id}">
									${type.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">产品描述：</label>
					<div class="col-xs-9">
						<input type="text" name="description" id="description"
							class="form-control" value="${product.description}"
							autocomplete="off">
					</div>
				</div>
				<div class="row text-center">
					<button id="cancel" type="button" class="btn btn-primary">关闭</button>
					<button id="save" type="submit" class="btn btn-primary">保存</button>
					<button type="button" class="btn js-add-material btn-primary">添加零件</button>
					<button type="button" class="js-show-detail btn btn-primary">零件详情</button>
				</div>

			</form>
		</div>

		<div id="detail-div" style="height: 45px; display: none;">
			<div class="js-detail-back iframe-wrap">
				<button class="btn btn-primary js-detail-back">返回</button>
			</div>
			<div class="iframe-wrap">
				<table id="detail-table"
					class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th>名称</th>
							<th>价格（元）</th>
							<th>数量</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>

		<div id="material-div" style="padding-top: 15px;height: 45px; display: none;">
			<form id="searchForm" class="form-horizontal">
				<div>
					<div class="form-group"
						style="padding-left: 5px; padding-right: 15px">
						<label class="col-xs-2 control-label text-right">名称：</label>
						<div class="col-xs-4">
							<input type="text" name="name" id="name" class="form-control"
								value="">
						</div>
						<label class="col-xs-2 control-label text-right">产品种类：</label>
						<div class="col-xs-4">
							<select id="typeSelect" class="form-control">
								<option value="">所有</option>
								<option value="0">处理器</option>
								<option value="1">散热器</option>
								<option value="2">主板</option>
								<option value="3">显卡</option>
								<option value="4">内存</option>
								<option value="5">硬盘</option>
								<option value="6">机箱</option>
								<option value="7">电源</option>
							</select>
						</div>
					</div>
			</form>
			<div class="iframe-wrap">
				<div class="toobar">
					<button class="btn btn-primary js-material-search">搜索</button>
					<button class="btn btn-primary js-material-back">返回</button>
				</div>
				<table id="material-table"
					class="table table-striped table-bordered table-hover" width="100%">
					<thead>
						<tr>
							<th>名称</th>
							<th>价格（元）</th>
							<th>描述</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	<script src="<c:url value='/js/lib/require.js'/>"></script>
	<script src="<c:url value='/js/config.js'/>"></script>
	<script src="<c:url value='/js/info/product/edit_main.js'/>"></script>
</body>
</html>