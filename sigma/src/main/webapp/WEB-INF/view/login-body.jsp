<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<script type="text/javascript">

$(document).ready(function(){
	
	if('${msg}' != ""){
		alert('${msg}');
	}
	
	$("#admin_login").submit(function() {
		
		if($("#username").val() == ''){
			alert("请输入用户名！");
			return false;
		}else if($("#password").val() == ''){
			alert("请输入密码！");
			return false;
		}else{
			$.ajax({
		        type : 'POST',
		        url : '${ctx}/login/check',
		        data: 'username=' + $("#username").val() + '&password=' + $("#password").val(),
		        dataType : 'text',
		        //同步
		        async : false,
		        success : function(data) {
		        },
		        error : function() {
		        	
			    }
			});
		}
	});
	
});
</script>

<div style='margin-left:auto; margin-right: auto; width: 300px;'>
	<form class="form-horizontal" id="admin_login" method="post" action="<c:url value="/login/check" />">
		<div class="form-group">
			<label class="control-label" for="title"><h2>SIGMA仓库管理系统</h2></label>
		</div>
		<div class="form-group">
			<label class="control-label" for="username">帐号</label>
			<input type="text" id="username" class="form-control" name="username" placeholder="用户名" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}">
		</div>

		<div class="form-group">
			<label class="control-label" for="password">密码</label>
			<input type="password" id="password" class="form-control" name="password" placeholder="密码" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}">
			<input type="hidden" id="md5password" name="md5password" value="">
		</div>

		<div class="form-group">
			<button class="btn btn-primary">登录</button>
		</div>
	</form>
</div>