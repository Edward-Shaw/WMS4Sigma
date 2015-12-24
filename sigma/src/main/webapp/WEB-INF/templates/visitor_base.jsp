<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
<meta http-equiv="X-UA-Compatible" content="IE=9" />

<title>SIGMA仓储管理系统 - <tiles:insertAttribute name="title"/></title>

<link rel="stylesheet" href="statics/css/bootstrap.min.css" />

<!-- datatable -->
<link rel="stylesheet" href='<c:url value="/statics/datatables/media/css/jquery.dataTables.css" />'/>
<link rel="stylesheet" href='<c:url value="/statics/datatables/extensions/TableTools/css/dataTables.tableTools.css" />'/>

<!-- <link rel="stylesheet" href="/statics/css/base.css" />  -->

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>

  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>

  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>

<![endif]-->


<script type="text/javascript" src='<c:url value="/statics/js/jquery.js" />'></script> 
<script type="text/javascript" src='<c:url value="/statics/js/bootstrap.min.js" />'></script>

<!-- datatable -->
<script type="text/javascript" src='<c:url value="/statics/datatables/media/js/jquery.dataTables.js" />'></script>
<script type="text/javascript" src='<c:url value="/statics/datatables/extensions/TableTools/js/dataTables.tableTools.js" />'></script>


</head>
<body onload="_on_load()">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">SIGMA-WMS</a>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="<c:url value="/devices" />">所有样机</a></li>
        <!-- <li><a href="<c:url value="/logs" />">历史数据查询</a></li> -->
        <!--
        <li><a href="#apps" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">我的样机<span class="caret"></span></a>
        	<ul class="dropdown-menu" role="menu">
               <li><a href="<c:url value="/apps" />">在库</a></li>
               <li><a href="<c:url value="/apps/releases" />">外借中</a></li>
               <li><a href="<c:url value="/apps/releases" />">内部借调</a></li>
               <li><a href="<c:url value="/apps/releases" />">返修中</a></li>
             </ul>
        </li>
        <li><a href="#users" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">出借历史查询<span class="caret"></span></a>
        	<ul class="dropdown-menu" role="menu">
               <li><a href="<c:url value="/users" />">用户列表</a></li>
               <li><a href="#redirect-to-opensso-ms">权限管理</a></li>
             </ul>
        </li>
         
        <li><a href="<c:url value="/properties" />">道具管理</a></li>
        <li><a href="<c:url value="/providers" />">供应商管理</a></li>
        <li><a href="#records" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">销售记录<span class="caret"></span></a>
        	<ul class="dropdown-menu" role="menu">
               <li><a href="<c:url value="/logs/recharge" />">充值记录</a></li>
               <li><a href="<c:url value="/logs/purchase" />">购买记录</a></li>
               <li><a href="#<c:url value="/logs/voucher" />">点券记录</a></li>
             </ul>
        </li>
        <li><a href="#statistics" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">数据统计<span class="caret"></span></a>
        	<ul class="dropdown-menu" role="menu">
        		<li><a href="<c:url value="/statistics/sales" />">销售统计</a></li>
               	<li><a href="<c:url value="/statistics/users" />">用户流量</a></li>
               	<li><a href="#statistics-clients">接口调用统计</a></li>
             </ul>
        </li>
        <li><a href="#logs" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">日志<span class="caret"></span></a>
        	<ul class="dropdown-menu" role="menu">
        		<li><a href="#1">应用运行记录</a></li>
        		<li><a href="<c:url value="/histories/access" />">接口调用记录</a></li>
        		<li><a href="<c:url value="/apps/datas" />">APPDATA记录</a></li>
        		<li><a href="#1">系统日志</a></li>
        	</ul>
        </li>
         -->
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
         <li><a href="<c:url value="/login" />">登录</a></li>
       </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>

<div class="container">
	<tiles:insertAttribute name="header" />

	<tiles:insertAttribute name="body" />
	
	<tiles:insertAttribute name="footer" />
</div>

<script>
function _on_load(){
	//TODO:增加对用户是否登录的判断
	//alert("${users}");
}
$(function(){
	$("#navbar li a").each(function () {
		var a = $(this);
		var li = a.parent("li");
		if(a.attr('href') == window.location.pathname){
			li.addClass('active');
			if(0 < li.parent(".dropdown-menu").size()){
				li.parent(".dropdown-menu").parent("li").addClass('active');
			}
		}else{
			li.removeClass('active');
		}
	});
});
</script>

</body>
</html>