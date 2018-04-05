<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<title>原材料</title>
<link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>"
	type="image/x-icon" />
<link rel="stylesheet"
	href="<c:url value='/css/vendor/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
	<div class="container .container-fluid">
		<div class="" style="margin-top: 10px;">
			<form class="form-horizontal" id="materialForm">
				<input type="hidden" id="id" name="id" value="${material.id}">
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">编号：</label>
					<div class="col-xs-9">
						<input type="text" name="number" id="number"
							${material.id eq null?"":"readonly=\"readonly\" "}
							class="form-control" placeholder="请输入编号"
							value="${material.number}" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">供货商编号：</label>
					<div class="col-xs-9">
						<input type="text" name="supplierNumber" id="supplierNumber"
							class="form-control" placeholder="请输入供货商"
							value="${material.supplierNumber}" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">名称：</label>
					<div class="col-xs-9">
						<input type="text" name="name" id="name" class="form-control"
							placeholder="请输入名称" value="${material.name}" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">价格：</label>
					<div class="col-xs-9">
						<input type="text" name="price" id="price" class="form-control"
							placeholder="请输入价格" value="${material.price}" autocomplete="off">
					</div>
				</div>
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">产品分类：</label>
					<div class="col-xs-9">
						<input type="hidden" id="typeidhidden" value="${product.typeid}">
						<select id="type" name="type" class="form-control">
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
				<div class="form-group">
					<label class="col-xs-3 control-label text-right">描述：</label>
					<div class="col-xs-9">
						<input type="text" name="description" id="description"
							class="form-control" placeholder="请输入描述"
							value="${material.description}" autocomplete="off">
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
	<script src="<c:url value='/js/info/material/edit_main.js'/>"></script>
</body>
</html>