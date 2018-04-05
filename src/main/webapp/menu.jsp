<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<title>物料管理系统</title>
<link rel="shortcut icon" href="<c:url value='img/favicon.ico'/>"
	type="image/x-icon" />
<link rel="stylesheet" href="css/vendor/layout-default-latest.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body style="overflow: hidden; position: relative">
	<div class="header clearfix">
		<a href="#" class="logo fl"> <img
			src="<c:url value='/img/logo.jpg'/>" alt="" class="fl" />
			<h1 class="fl">
				物料管理系统 <span>——管理端版本V1.0.1</span>
			</h1>
		</a>
		<div class="hd-nav">
			<ul class="clearfix">
				<li><a class="js-logout">退出<i class="xbt-icon log-out"></i></a></li>
			</ul>
		</div>
	</div>
	<div id="container">
		<div class="pane ui-layout-center">
			<div id="page-wrapper">
				<div class="content-tabs mt10">
					<div class="page-tabs J_menuTabs">
						<div class="page-tabs-content">
							<em class="tab-close js-closeAll-tab"><i class="xbt-icon"></i></em>
							<a href="javascript:void(0);" class="active J_menuTab"
								data-id="/admin/dashboard" style="border-left: 0;">首页</a>
						</div>
					</div>
				</div>
				<div id="content-main" class="J_mainContent">
					<iframe class="J_iframe" name="iframe0" width="100%" height="100%"
						src="/index" frameborder="0"></iframe>
				</div>
				<div class="footer">
					版权所有：Anjone<span class="ml40">技术支持：Anjone</span><span class="ml40">客服
						400-XXX-XXXX</span><span class="ml40">技术 XXXX-XXXXXXXX</span>
				</div>
			</div>
		</div>
		<div class="pane ui-layout-west">
			<div class="nano">
				<div class="nano-content">
					<div class="sidebar">
						<div class="sidebar">
							<div class="sidebar-nav">
								<ul id="side-menu" class="metismenu">
									<li class=""><a href="javascript:void(0)"><i
											class="fa fa-edit"></i> <span class="nav-label">基础信息管理</span><span
											class="nav-arrow"></span></a>
										<ul class="nav nav-second-level collapse metis-close"
											style="display: none;">
											<shiro:hasAnyRoles name="admin,purchase,production">
											<li><a class="J_menuItem" href="/info/material/list"
												data-index="0">原材料信息</a></li>
											</shiro:hasAnyRoles>
											<shiro:hasAnyRoles name="admin,purchase,production">
											<li><a class="J_menuItem" href="/info/supplier/list"
												data-index="1">供应商信息</a></li>
											</shiro:hasAnyRoles>
											<shiro:hasAnyRoles name="admin,sale,production">
											<li><a class="J_menuItem" href="/info/customer/list"
												data-index="2">客户信息</a></li>
											</shiro:hasAnyRoles>
											<shiro:hasAnyRoles name="admin,sale,production">
											<li><a class="J_menuItem" href="/info/product/list"
												data-index="3">产品信息</a></li>
											</shiro:hasAnyRoles>
											<shiro:hasAnyRoles name="admin,sale,production">
											<li><a class="J_menuItem" href="/info/producttype/list"
												data-index="4">产品类别</a></li>
											</shiro:hasAnyRoles>
										</ul></li>
									<li class=""><a href="javascript:void(0)"><i
											class="fa fa-edit"></i> <span class="nav-label">库存管理</span><span
											class="nav-arrow"></span></a>
										<ul class="nav nav-second-level collapse metis-close"
											style="display: none;">
											<shiro:hasAnyRoles name="admin,purchase,production">
											<li><a class="J_menuItem"
												href="/manage/materialInventory/list" data-index="0">原材料库存</a></li>
											</shiro:hasAnyRoles>
											<shiro:hasAnyRoles name="admin,sale,production">
											<li><a class="J_menuItem"
												href="/manage/productInventory/list" data-index="1">产品库存</a></li>
											</shiro:hasAnyRoles>
											<shiro:hasAnyRoles name="admin,production">
											<li><a class="J_menuItem"
												href="/manage/lnventoryLog/list" data-index="2">库存变动日志</a></li>
											</shiro:hasAnyRoles>
										</ul></li>
									<li class=""><a href="javascript:void(0)"><i
											class="fa fa-edit"></i> <span class="nav-label">业务管理</span><span
											class="nav-arrow"></span></a>
										<ul class="nav nav-second-level collapse metis-close"
											style="display: none;">
											<shiro:hasAnyRoles name="admin,purchase">
											<li><a class="J_menuItem" href="/business/morder/list"
												data-index="0">订货管理</a></li>
											</shiro:hasAnyRoles>
											<shiro:hasAnyRoles name="admin,production">
											<li><a class="J_menuItem" href="/business/production/list"
												data-index="3">生产管理</a></li>
											</shiro:hasAnyRoles>
											<shiro:hasAnyRoles name="admin,sale">
											<li><a class="J_menuItem" href="/business/porder/list"
												data-index="0">出货管理</a></li>
											</shiro:hasAnyRoles>
											<shiro:hasAnyRoles name="admin,account">
											<li><a class="J_menuItem" href="/business/accounts/list"
												data-index="0">账款管理</a></li>
											</shiro:hasAnyRoles>
										</ul></li>
									<shiro:hasAnyRoles name="admin">  
									<li class=""><a href="javascript:void(0)"><i
											class="fa fa-edit"></i> <span class="nav-label">其他</span><span
											class="nav-arrow"></span></a>
										<ul class="nav nav-second-level collapse metis-close"
											style="display: none;">
											<li><a class="J_menuItem" href="/system/user/list"
												data-index="0">用户管理</a></li>
										</ul></li>
									</shiro:hasAnyRoles>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 通用页面 -->
	<script>
 // 定义全局变量
 window.__FRAMEWORK__ = {
  // 根路径
  root_url: "<c:url value='/' />".split(';')[0]
 };
</script>
	<script src="<c:url value='js/lib/require.js'/>"></script>
	<script src="<c:url value='js/config.js'/>"></script>
	<script src="<c:url value='js/system/index_main.js'/>"></script>
</body>
</html>