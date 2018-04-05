<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>原材料</title>
    <link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>" type="image/x-icon"/>
    <link rel="stylesheet" href="<c:url value='/css/vendor/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
<div class="container .container-fluid">
    <div class="" style="margin-top: 10px;">
        <form class="form-horizontal" id="morderForm">
            <input type="hidden" id="id" name="id" value="${morder.id}">
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">订单编号：</label>
                <div class="col-xs-9">
                    <input type="text" name="number" id="number" ${morder.id eq null?"":"readonly=\"readonly\" "} class="form-control" placeholder="请输入订单编号"
                           value="${morder.number}" autocomplete="off">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">供应商编号：</label>
                <div class="col-xs-9">
                    <input type="text" name="supplierNumber" id="supplierNumber" ${morder.id eq null?"":"readonly=\"readonly\" "}  class="form-control" placeholder="请输入供应商编号" value="${morder.supplierNumber}" autocomplete="off">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">收货地址：</label>
                <div class="col-xs-9">
                    <input type="text" name="address" id="address" ${morder.id eq null?"":"readonly=\"readonly\" "}  class="form-control" placeholder="请输入收货地址" value="${morder.address}" autocomplete="off">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label text-right">备注：</label>
                <div class="col-xs-9">
                    <input type="text" name="remark" id="remark" class="form-control" placeholder="请输入备注" value="${morder.remark}" autocomplete="off">
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
<script src="<c:url value='/js/business/morder/edit_main.js'/>"></script>
</body>
</html>