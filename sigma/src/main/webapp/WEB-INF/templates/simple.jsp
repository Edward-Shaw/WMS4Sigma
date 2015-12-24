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

<link rel="stylesheet" href="../statics/css/bootstrap.min.css" />

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>

  <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>

  <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.min.js"></script>

<![endif]-->


<script type="text/javascript" src='<c:url value="/statics/js/jquery.js" />'></script> 
<script type="text/javascript" src='<c:url value="/statics/js/bootstrap.min.js" />'></script>

</head>
<body>

<div class="container">
	<tiles:insertAttribute name="header" />

	<tiles:insertAttribute name="body" />
	
	<tiles:insertAttribute name="footer" />
</div>

<script>
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