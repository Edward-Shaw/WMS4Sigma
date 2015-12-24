<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	
	$('#modal_remove').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		$("#btn_remove").data('id', button.data('id'));
	});
	
	$('#modal_restore').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		$("#btn_restore").data('id', button.data('id'));
	});
	
	///remove
	$("#btn_remove").click(function(){
		var id = $(this).data('id');
		$.post('<c:url value="/platforms" />/' + id + '/remove', function(data){
			location.reload();
		}, 'json');
	});
	
	///restore
	$("#btn_restore").click(function(){
		var id = $(this).data('id');
		$.post('<c:url value="/platforms" />/' + id + '/restore', function(data){
			location.reload();
		}, 'json');
	});
	 
	//下拉框根据状态选择平台
	$("#filter_status, #filter_user").change(function(){
		location.href = '<c:url value="/devices" />?status=' + $("#filter_status").val() + "&user=" + $("#filter_user").val();
	});
	
	$("#filter_status").val("${empty param.status ? 'NORMAL' : param.status}");
	$("#filter_user").val("${empty param.user ? 'ALL' : param.user}");
});
</script>

<div class="row-fluid form-inline">
						
</div>

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
	<th style="min-width:50px;" >种类</th>
	<th style="min-width:60px;" >数量</th>
	<th style="min-width:60px;" >状态</th>
	<th style="min-width:60px;" >出借对象</th>
	<th style="min-width:60px;" >出借时间</th>
	<th style="min-width:60px;" >管理</th>
</tr>
</thead>
<tbody>
<c:forEach var="device" items="${devices}" varStatus="status">
	<c:if test="${device.STATUS eq 'IN_STORE' }">
		<tr style = "background-color:#00CD00;">
	</c:if>
	<c:if test="${device.STATUS eq 'UNDER_REPAIR' }">
		<tr style = "background-color:#EE6A50;">
	</c:if>
	<c:if test="${device.STATUS eq 'INTERNAL_BORROWING' }">
		<tr style = "background-color:#BBFFFF;">
	</c:if>
	<c:if test="${device.STATUS eq 'EXTERNAL_BORROWING' }">
		<tr style = "background-color:#FFFF00;">
	</c:if>
	<c:if test="${device.STATUS eq 'SELL_OUT' }">
		<tr style = "background-color:#CDCDC1;">
	</c:if>
	<c:if test="${device.STATUS eq 'TEMP_OWN' }">
		<tr style = "background-color:#FFA54F;">
	</c:if>
	<c:if test="${device.STATUS eq 'TEMP_OWN_RELET' }">
		<tr style = "background-color:#FF4500;">
	</c:if>
	    <td >${device.ID}</td>
	    <td >${device.MODEL}</td>
	    <td >${device.ASSET_CODE}</td>
		<td >${device.SERIAL_NUMBER_1 }</td>
		<td >${device.SERIAL_NUMBER_2 }</td>
		<td >${device.BAYONET_1 }</td>
		<td >${device.BAYONET_2 }</td>
		<td >${device.NAME }</td>
		<td >	
			<c:if test="${device.QUALITY_GRADE eq 'LOW_QUALITY' }">
				水货
			</c:if>
			<c:if test="${device.QUALITY_GRADE eq 'SALES_SAMPLE' }">
				销售样机
			</c:if>
			<c:if test="${device.QUALITY_GRADE eq 'MEDIA_SAMPLE' }">
				媒体样机
			</c:if>
		</td>
		<td >${device.COUNT }</td>
		<td >	
			<c:if test="${device.STATUS eq 'IN_STORE' }">
				在库
			</c:if>
			<c:if test="${device.STATUS eq 'UNDER_REPAIR' }">
				返修中
			</c:if>
			<c:if test="${device.STATUS eq 'INTERNAL_BORROWING' }">
				内借
			</c:if>
			<c:if test="${device.STATUS eq 'EXTERNAL_BORROWING' }">
				外借
			</c:if>
			<c:if test="${device.STATUS eq 'SELL_OUT' }">
				售出
			</c:if>
		</td>
		<td>
			<c:if test="${empty device.RENTER}">
				&nbsp;&nbsp;&nbsp;\
			</c:if>
			<c:if test="${!empty device.RENTER}">
				${device.RENTER }
			</c:if>
		</td>
		<td >			
			<c:if test="${empty device.RENT_TIME}">
				&nbsp;&nbsp;&nbsp;\
			</c:if>
			<c:if test="${!empty device.RENT_TIME}">
				${device.RENT_TIME }
			</c:if>
		</td>
		<td >${device.MANAGER }</td>
</c:forEach>
</tbody>
</table>