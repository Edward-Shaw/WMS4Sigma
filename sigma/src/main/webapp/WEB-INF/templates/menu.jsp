<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" scope="request" value="/managersystem"></c:set>

<style>
.cap-menu{
	margin:0 0 0 0;
	}	
.active{
	background-color: AliceBlue;
}
</style>

<script type="text/javascript">
 $(function() {
	$("div.active").removeClass('active');
	$("li div a").each(function(idx, ele){
		if(ele.pathname == window.location.pathname){
			$(ele).closest("div").addClass('active');
			var t = $(ele).closest("div");
			//$(ele).parents().eq(3).collapse(); 
			return false;
		}
	});	 	

}); 
</script>

<div class="col-md-2 cap-menu">
	<div class="accordion" id="accordion-consume">
		<!-- 消费统计 -->
		<div class="accordion-group">
			<div class="accordion-heading">
				 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-consume" href="#accordion-element-consume"><strong>消费统计</strong></a>
			</div>
			<div id="accordion-element-consume" class="accordion-body collapse in" >
				<ul class="nav nav-list">
                	<li><div class="accordion-inner active"><a href="${path}/logs/purchase">详细列表</a></div></li>
					<li><div class="accordion-inner"><a href="${path}/logs/purchase/day">日统计</a></div></li>
					<li><div class="accordion-inner"><a href="${path}/logs/purchase/week">周统计</a></div></li>
					<li><div class="accordion-inner"><a href="${path}/logs/purchase/month">月统计</a></div></li>
				</ul>
			</div>
		</div>
		
		<!-- 充值统计 -->
		<div class="accordion-group">
			<div class="accordion-heading">
				 <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion-recharge" href="#accordion-element-recharge"><strong>充值统计</strong></a>
			</div>
			<div id="accordion-element-recharge" class="accordion-body collapse in">				
				<ul class="nav nav-list">
                	<li><div class="accordion-inner"><a href="${path}/logs/recharge">详细列表</a></div></li>
					<li><div class="accordion-inner"><a href="${path}/logs/recharge/day">日统计</a></div></li>
					<li><div class="accordion-inner"><a href="${path}/logs/recharge/week">周统计</a></div></li>
					<li><div class="accordion-inner"><a href="${path}/logs/recharge/month">月统计</a></div></li>
				</ul>
			</div>
		</div>
	</div>
</div>