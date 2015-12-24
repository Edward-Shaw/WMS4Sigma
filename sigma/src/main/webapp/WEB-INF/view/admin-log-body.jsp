<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
$(function(){
	$(document).ready(function(){
		$('#device_table').DataTable({
			dom:'T<"clear">lfrtip',
			tableTools:{
				"sSwfPath":'<c:url value="/statics/datatables/extensions/TableTools/swf/copy_csv_xls_pdf.swf" />'
			}
		});
	});
	//下拉框根据状态选择平台
	$("#filter_type").change(function(){
		location.href = '<c:url value="/admin/log" />?type=' + $("#filter_type").val();
	});
	
	$("#filter_type").val("${empty param.type ? 'NORMAL' : param.type}");
});
</script>

<table id="device_table" class="table table-hover table-striped" style="margin-top: 10px;">
<thead>
<tr>
	<th style="width:60px;" >#ID</th>
	<th style="min-width:60px;" >型号</th>
	<th style="min-width:60px;" >资产编码</th>
	<th style="min-width:60px;" >序列号Ⅰ</th>
	<th style="min-width:60px;" >序列号Ⅱ</th>
	<th style="min-width:60px;" >卡口Ⅰ</th>
	<th style="min-width:60px;" >卡口Ⅱ</th>
	<th style="min-width:80px;" >商品名称</th>
	<th style="min-width:60px;" >数量</th>
	<th style="min-width:60px;" >状态</th>
	<th style="min-width:60px;" >管理</th>
	<th style="min-width:60px;" >修改时间</th>
	<th style="min-width:60px;" >日志类型</th>
</tr>
</thead>
<tbody>
<c:forEach var="log" items="${logs}" varStatus="status">
	<c:if test="${log.STATUS eq 'IN_STORE' }">
		<tr style = "background-color:#00CD00;">
	</c:if>
	<c:if test="${log.STATUS eq 'UNDER_REPAIR' }">
		<tr style = "background-color:#EE6A50;">
	</c:if>
	<c:if test="${log.STATUS eq 'INTERNAL_BORROWING' }">
		<tr style = "background-color:#BBFFFF;">
	</c:if>
	<c:if test="${log.STATUS eq 'EXTERNAL_BORROWING' }">
		<tr style = "background-color:#FFFF00;">
	</c:if>
	<c:if test="${log.STATUS eq 'TEMP_OWN' }">
		<tr style = "background-color:#FFA54F;">
	</c:if>
	<c:if test="${log.STATUS eq 'TEMP_OWN_RELET' }">
		<tr style = "background-color:#FF4500;">
	</c:if>
	<c:if test="${log.STATUS eq 'SELL_OUT' }">
		<tr style = "background-color:#CDCDC1;">
	</c:if>
	<c:if test="${log.STATUS eq 'SELLOUT' }">
		<tr style = "background-color:#CDCDC1;">
	</c:if>
	<c:if test="${log.STATUS eq 'REMADE' }">
		<tr style = "background-color:#CDCDC1;">
	</c:if>
	    <td >${log.ID}</td>
	    <td >${log.MODEL}</td>
	    <td >${log.ASSET_CODE}</td>
		<td >${log.SERIAL_NUMBER_1 }</td>
		<td >${log.SERIAL_NUMBER_2 }</td>
		<td >${log.BAYONET_1 }</td>
		<td >${log.BAYONET_2 }</td>
		<td >${log.NAME }</td>
		<td >${log.COUNT }</td>
		<td >	
			<c:if test="${log.STATUS eq 'IN_STORE' }">
				在库
			</c:if>
			<c:if test="${log.STATUS eq 'UNDER_REPAIR' }">
				返修中
			</c:if>
			<c:if test="${log.STATUS eq 'INTERNAL_BORROWING' }">
				内借
			</c:if>
			<c:if test="${log.STATUS eq 'TEMP_OWN' }">
				内部借入
			</c:if>
			<c:if test="${log.STATUS eq 'TEMP_OWN_RELET' }">
				转借中
			</c:if>
			<c:if test="${log.STATUS eq 'EXTERNAL_BORROWING' }">
				外借
			</c:if>
			<c:if test="${log.STATUS eq 'SELLOUT' }">
				售出
			</c:if>
			<c:if test="${log.STATUS eq 'REMADE' }">
				翻新
			</c:if>
		</td>
		<td >${log.MANAGER }</td>
		<td >${log.CREATE_TIME }</td>
		<td >${log.LOG_TYPE }</td>
</c:forEach>
</tbody>
</table>