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

<link rel="stylesheet" href='<c:url value="/statics/css/bootstrap.min.css" />'/>

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
  <div class="container-fluid">
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
        <li><a href="<c:url value="/staff" />">我的样机</a></li>
        <li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">历史数据<span class="caret"></span></a>
        	<ul class="dropdown-menu" role="menu">
               <li><a href="<c:url value="/staff/log" />">出借历史查询</a></li>
               <li><a href="<c:url value="/staff/current" />">当前借出</a></li>
             </ul>
        </li>
        <li><a href="#">欢迎使用样机管理系统：<strong>${username}</strong></a></li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
         <li><a href="<c:url value="/login" />">退出</a></li>
         <li><a href="<c:url value="/user" />">修改密码</a></li>
       </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>

<div class="container-fluid">
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