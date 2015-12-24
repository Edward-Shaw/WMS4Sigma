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
		}else if($("#oldpassword").val() == ''){
			alert("请输入密码！");
			return false;
		}else if($("#newpassword1").val() == ''){
			alert("密码不能为空！");	
			return false;
		}else if($("#newpassword2").val() == ''){
			alert("密码不能为空！");
			return false;
		}else if($("#newpassword1").val() != $("#newpassword2").val()){
			alert("前后密码输入不一致！");
			return false;
		}else if($("#oldpassword").val() == $("#newpassword1").val()){
			alert("新旧密码不能相同！");	
			return false;
		}else{
			$.ajax({
		        type : 'POST',
		        url : '${ctx}/user',
		        data: 'username=' + $("#username").val() + '&oldpassword=' + $("#oldpassword").val() + '&newpassword=' + $("#newpassword1").val(),
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
			<label class="control-label" for="password">当前密码</label>
			<input type="password" id="oldpassword" class="form-control" name="oldpassword" placeholder="请输入您的当前密码" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}">
			<input type="hidden" id="md5password" name="md5password" value="">
		</div>
		
		<div class="form-group">
			<label class="control-label" for="password">新密码</label>
			<input type="password" id="newpassword1" class="form-control" name="newpassword1" placeholder="请输入您的新密码" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}">
			<input type="hidden" id="md5password" name="md5password" value="">
		</div>
		
		<div class="form-group">
			<label class="control-label" for="password">确认密码</label>
			<input type="password" id="newpassword2" class="form-control" name="newpassword2" placeholder="请再次输入新密码" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}">
			<input type="hidden" id="md5password" name="md5password" value="">
		</div>

		<div class="form-group">
			<button class="btn btn-primary" style="align:left">确认</button>
			<button class="btn btn-primary" style="align:left">取消</button>
		</div>
	</form>
</div>