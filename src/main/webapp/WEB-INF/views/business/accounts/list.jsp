<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <title>原材料库存</title>
    <link rel="shortcut icon" href="<c:url value='/img/favicon.ico'/>" type="image/x-icon"/>
    <link rel="stylesheet" href="<c:url value='/css/vendor/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>">
</head>
<body>
	<div style="height: 45px; padding-top: 15px;">
		<form id="searchFrom" class="form-horizontal">
			 <div class="form-group"  style="padding-right: 15px">
				<label class="col-xs-2 control-label text-right">起始时间：</label>
				<div class="col-xs-4">
					<input type="date" name="stDate" id="stDate" class="form-control" value="">
				</div>
				<label class="col-xs-1 control-label text-right">至：</label>
				<div class="col-xs-4">
					<input type="date" name="endDate" id="endDate" class="form-control" value="">
				</div>
			</div>
			<div class="form-group"  style="padding-right: 15px">
				<label class="col-xs-2 control-label text-right">用户编号：</label>
				<div class="col-xs-4">
					<input type="text" name="peopleNumber" id="peopleNumber" class="form-control" value="">
				</div>
				<label class="col-xs-1 control-label text-right">账单类型：</label>
				<div class="col-xs-4">
					<select id="typeSelect" class="form-control">
						<option value="">所有</option>
						<option value="0">应收</option>
						<option value="1">应付</option>
					</select>
				</div>
			</div>
		</form>
	    <div class="toobar" style="padding-left: 5px; padding-right: 15px">
	    	 <button class="btn btn-primary js-search" >搜索</button>
	    	 <button class="btn btn-primary js-excel" >按条件导出信息</button>
	    </div>
	</div>
	<div class="iframe-wrap">
    <table id="production-table" class="table table-striped table-bordered table-hover" width="100%">
        <thead>
        <tr>
            <th>订单时间</th>
            <th>类型</th>
            <th>用户编号</th>
            <th>用户名称</th>
            <th>订单编号</th>
            <th>总金额</th>
            <th>订单状态</th>
            <th>备注</th>
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
<script src="<c:url value='/js/business/accounts/list_main.js'/>"></script>
</body>
</html>
