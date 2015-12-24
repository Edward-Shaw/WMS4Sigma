<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link href='<c:url value="/statics/css/bootstrap.min.css" />' rel="stylesheet" media="screen">
<link href='<c:url value="/statics/css/bootstrap-datetimepicker.min.css" />' rel="stylesheet" media="screen">

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
		$("#btn_restore").data('status', button.data('status'));
		alert(button.data('status'));
	});
	
	///submit the internal borrowing form
	$("#btn_internal_borrowing").click(function(){
		var f = $("#form_internal_borrowing");
		var id = $(this).data('id');
		var action = f.attr('action') + "/" + id;
		if($("#inputmanagerlist").val() == ""){
			argument_error = true;
			alert("请填写预期归还日期！");
		}
		$.post(action, f.serialize() + "&rent_type=INTERNAL_BORROWING&company=sigma", function(data){
			location.reload();
		}, 'json');
	});
	
	///submit the external borrowing form
	$("#btn_external_borrowing").click(function(){
		var f = $("#form_external_borrowing");
		var id = $(this).data('id');
		var action = f.attr('action') + "/" + id;
		
		var argument_error = false;
		if($("#renter").val() == ""){
			argument_error = true;
			alert("请填写样机借取人！");
		}
		if($("#purpose").val() == ""){
			argument_error = true;
			alert("请填写借取目的！");
		}
		if($("#renttime2").val() == ""){
			argument_error = true;
			alert("请填写借取日期！");
		}
		if($("#expecttime2").val() == ""){
			argument_error = true;
			alert("请填写预期归还日期！");
		}
		
		if(argument_error == false){
			$.post(action, f.serialize() + "&rent_type=EXTERNAL_BORROWING", function(data){
				location.reload();
			}, 'json');	
		}
	});
	
	///submit the repair form
	$("#btn_repair").click(function(){
		var f = $("#form_repair");
		var id = $(this).data('id');
		var action = f.attr('action') + "/" + id;
		
		var argument_error = false;
		if($("#renter3").val() == ""){
			argument_error = true;
			alert("请填写样机返修联系人！");
		}
		if($("#purpose3").val() == ""){
			argument_error = true;
			alert("请填写返修原因！");
		}
		if($("#renttime3").val() == ""){
			argument_error = true;
			alert("请填写返修日期！");
		}
		if($("#expecttime3").val() == ""){
			argument_error = true;
			alert("请填写预期归还日期！");
		}
		
		if(argument_error == false){
			$.post(action, f.serialize() + "&rent_type=UNDER_REPAIR", function(data){
				location.reload();
			}, 'json');	
		}
	});
	
	///submit the in store form
	$("#btn_in_store").click(function(){
		var f = $("#form_in_store");
		var id = $(this).data('id');
		var status = $(this).data('status');
		var action = f.attr('action') + "/" + id;
		
		$.post(action, f.serialize() + "&status=" + status, function(data){
			location.reload();
		}, 'json');
	});
	
	$("#modal_internal_borrowing").on('show.bs.modal', function(event){
		//获取员工信息
		var url = '<c:url value="/users/list"/>';
		var button = $(event.relatedTarget);
		$("#btn_internal_borrowing").data('id', button.data('id'));
		var id = button.data('id');
		$.get(url, function(data, status){
			if(data.code == 0){
				var p = data.result;
				$("#input-manager-list").empty();
				$("#input-manager-list").append($("<option/>").text('选择借用人').attr("value",0));
				$("#deviceid").val(id);
				$(p).each(function(){
					//创建option对象并设置相应的文本值和value值
					var opt = $("<option/>").text(this.name).attr("value", this.name);
					//将option对象添加到匹配的jQuery对象中
					$("#input-manager-list").append(opt);
				});
			}
		}, 'json');
	});
	
	$("#modal_external_borrowing").on('show.bs.modal', function(event){
		var button = $(event.relatedTarget);
		$("#btn_external_borrowing").data('id', button.data('id'));
	});
	
	$("#modal_repair").on('show.bs.modal', function(event){
		var button = $(event.relatedTarget);
		$("#btn_repair").data('id', button.data('id'));
	});
	
	$("#modal_in_store").on('show.bs.modal', function(event){
		var button = $(event.relatedTarget);
		$("#btn_in_store").data('id', button.data('id'));
		$("#btn_in_store").data('status', button.data('status'));
	});
	 
	//下拉框根据状态选择平台
	$("#filter_status").change(function(){
		location.href = '<c:url value="/staff" />?status=' + $("#filter_status").val();
	});
	
	  //回车键相应搜索功能 
	$("#search_serialcode").keyup(function(event){
	 	if(event.keyCode == 13){
	 		location.href = '<c:url value="/staff" />?status=' + 
	 				$("#filter_status").val() + "&serialcode=" + $("#search_serialcode").val();
	 	}
	 });
	
	$("#filter_status").val("${empty param.status ? 'ALL' : param.status}");
	$("#search_serialcode").val("${empty param.serialcode ? '' : param.serialcode}");
});
</script>

<!-- 器材归还 -->
<div class="modal fade" id="modal_in_store" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel" style="text-align:center; color:green;"><strong>确定该样机已经归还并入库？</strong></h4>
      </div>
      <div class="modal-body">
      	<form id="form_in_store"  class="form-horizontal" method="POST" action="./staff/instore">
      		<div class="input-group" style="margin-left:100px;margin-bottom:20px;display:none">
	  			<span class="input-group-addon" style="width:100px">id</span>
	  			<input id="deviceid" name="id" type="text" class="form-control" placeholder="id" style="width:250px" readonly >
			</div>
			<div class="form-group form-group-sm date form_datetime">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >归还日期</label>
	  			<div class="col-sm-10">
	  				<input id="actualtime" name="actualtime" type="text" class="form-control" placeholder="不填则默认为当天">
	  			</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="btn_in_store" type="button" class="btn btn-primary">入库</button>
      </div>
    </div>
  </div>
</div>

<!-- 器材内借 -->
<div class="modal fade" id="modal_internal_borrowing" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel" style="text-align:center; color:green;"><strong>器材内借</strong></h4>
      </div>
      <div class="modal-body">
      	<form id="form_internal_borrowing"  class="form-horizontal" method="POST" action="./staff/rent">
      		<div class="input-group" style="margin-left:100px;margin-bottom:20px;display:none">
	  			<span class="input-group-addon" style="width:100px">id</span>
	  			<input id="deviceid" name="id" type="text" class="form-control" placeholder="id" style="width:250px" readonly >
			</div>
			<div class="form-group form-group-sm date form_datetime">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >借取日</label>
	  			<div class="col-sm-10">
	  				<input id="renttime" name="renttime" type="text" class="form-control" placeholder="不填写则默认为当天">
	  			</div>
			</div>
			<div class="form-group form-group-sm date form_datetime">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >预计归还日</label>
	  			<div class="col-sm-10">
	  				<input id="expecttime" name="expecttime" type="text" class="form-control" placeholder="请选择预计归还日">
	  			</div>
			</div>
			<div class="form-group form-group-sm" >
  				<label for="input-cp-list" class="col-sm-2 control-label" >借取人</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="input-manager-list" name="renter">
	  				    <option value="opt">请选择器材借取人</option>
						<c:forEach var="record" items="${users}" varStatus="status">	
							<option value="${record.NAME}">${record.NAME}</option>		
						</c:forEach>
					</select>
				</div>
			</div>
	       	<div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >借取目的</label>
	  			<div class="col-sm-10">
	  				<input id="in-model" name="purpose" type="text" class="form-control" placeholder="请输入借取目的">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-assetcode" class="col-sm-2 control-label" >联系电话</label>
	  			<div class="col-sm-10">
	  				<input id="in-assetcode" name="phone" type="text" class="form-control" placeholder="请输入借取人联系电话">
	  			</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="btn_internal_borrowing" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- 器材外借 -->
<div class="modal fade" id="modal_external_borrowing" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel" style="text-align:center; color:red;"><strong>器材外借</strong></h4>
      </div>
      <div class="modal-body">
      	<form id="form_external_borrowing"  class="form-horizontal" method="POST" action="./staff/rent">
      		<div class="input-group" style="margin-left:100px;margin-bottom:20px;display:none">
	  			<span class="input-group-addon" style="width:100px">id</span>
	  			<input id="deviceid" name="id" type="text" class="form-control" placeholder="id" style="width:250px" readonly >
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >借取人</label>
	  			<div class="col-sm-10" id="testtest">
	  				<input id="renter" name="renter" type="text" class="form-control" placeholder="请输入借取人">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >公司</label>
	  			<div class="col-sm-10">
	  				<input id="company" name="company" type="text" class="form-control" placeholder="请输入借取人所在公司">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >职位</label>
	  			<div class="col-sm-10">
	  				<input id="position" name="position" type="text" class="form-control" placeholder="请输入借担任职位">
	  			</div>
			</div>
	       	<div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >借取目的</label>
	  			<div class="col-sm-10">
	  				<input id="purpose" name="purpose" type="text" class="form-control" placeholder="请输入借取目的">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-assetcode" class="col-sm-2 control-label" >联系电话</label>
	  			<div class="col-sm-10">
	  				<input id="phone" name="phone" type="text" class="form-control" placeholder="请输入借取人联系电话">
	  			</div>
			</div>
			<div class="form-group form-group-sm date form_datetime">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >借取日</label>
	  			<div class="col-sm-10">
	  				<input id="renttime2" name="renttime" type="text" class="form-control" placeholder="默认为当天">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >预计归还日</label>
	  			<div class="col-sm-10">
	  				<input id="expecttime2" name="expecttime" type="text" class="form-control" placeholder="请选择预计归还日">
	  			</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="btn_external_borrowing" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- 样机返修  -->
<div class="modal fade" id="modal_repair" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel" style="text-align:center; color:red;"><strong>样品返修</strong></h4>
      </div>
      <div class="modal-body">
      	<form id="form_repair"  class="form-horizontal" method="POST" action="./staff/rent">
      		<div class="input-group" style="margin-left:100px;margin-bottom:20px;display:none">
	  			<span class="input-group-addon" style="width:100px">id</span>
	  			<input id="deviceid" name="id" type="text" class="form-control" placeholder="id" style="width:250px" readonly >
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >返修联系人</label>
	  			<div class="col-sm-10" id="testtest">
	  				<input id="renter3" name="renter" type="text" class="form-control" placeholder="请输入返修联系人">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >返修公司</label>
	  			<div class="col-sm-10">
	  				<input id="company3" name="company" type="text" class="form-control" placeholder="请输入返修公司">
	  			</div>
			</div>
	       	<div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >返修原因</label>
	  			<div class="col-sm-10">
	  				<input id="purpose3" name="purpose" type="text" class="form-control" placeholder="请输入返修原因">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-assetcode" class="col-sm-2 control-label" >联系电话</label>
	  			<div class="col-sm-10">
	  				<input id="phone3" name="phone" type="text" class="form-control" placeholder="请输入维修人联系电话">
	  			</div>
			</div>
			<div class="form-group form-group-sm date form_datetime">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >返修日期</label>
	  			<div class="col-sm-10">
	  				<input id="renttime3" name="renttime" type="text" class="form-control" placeholder="默认为当天">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >预计归还日</label>
	  			<div class="col-sm-10">
	  				<input id="expecttime3" name="expecttime" type="text" class="form-control" placeholder="请选择预计归还日">
	  			</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="btn_repair" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<div class="row-fluid form-inline">
	<div class="input-group input-group-sm">
		<span class="input-group-addon">样机状态</span>
		<select class="form-control" id="filter_status">
			<option value="ALL">选择样机状态</option>
			<option value="ALL">所有</option>
			<option value="IN_STORE">在库</option>
			<option value="EXTERNAL_BORROWING">外借中</option>
			<option value="INTERNAL_BORROWING">内部借调</option>
			<option value="UNDER_REPAIR">返修中</option>
			<option value="SELL_OUT">售出</option>
		</select>
	</div>
	
	<div class="input-group input-group-sm">
		<span class="input-group-addon">序列号</span>
        <input type="text" name="search-serialcode" id="search_serialcode" placeholder="输入序列号并按回车键搜索" class="form-control">
	</div>
	
	<!-- 
	<div class="input-group input-group-sm">
		<span class="input-group-addon">商品编码</span>
		<select class="form-control" id="filter_status">
			<option value="ALL">选择样机状态</option>
			<option value="ALL">所有</option>
			<option value="IN_STORE">在库</option>
			<option value="EXTERNAL_BORROWING">外借中</option>
			<option value="INTERNAL_BORROWING">内部借调</option>
			<option value="UNDER_REPAIR">返修中</option>
			<option value="SELL_OUT">售出</option>
		</select>
	</div>
	
	<div class="input-group input-group-sm">
		<span class="input-group-addon">商品名称</span>
		<select class="form-control" id="filter_status">
			<option value="ALL">选择样机状态</option>
			<option value="ALL">所有</option>
			<option value="IN_STORE">在库</option>
			<option value="EXTERNAL_BORROWING">外借中</option>
			<option value="INTERNAL_BORROWING">内部借调</option>
			<option value="UNDER_REPAIR">返修中</option>
			<option value="SELL_OUT">售出</option>
		</select>
	</div>		
	
	<div class="input-group input-group-sm">
		<span class="input-group-addon">卡口</span>
		<select class="form-control" id="filter_status">
			<option value="ALL">选择样机状态</option>
			<option value="ALL">所有</option>
			<option value="IN_STORE">在库</option>
			<option value="EXTERNAL_BORROWING">外借中</option>
			<option value="INTERNAL_BORROWING">内部借调</option>
			<option value="UNDER_REPAIR">返修中</option>
			<option value="SELL_OUT">售出</option>
		</select>
	</div>
	
	<div class="input-group input-group-sm">
		<span class="input-group-addon">样机状态</span>
		<select class="form-control" id="filter_status">
			<option value="ALL">选择样机状态</option>
			<option value="ALL">所有</option>
			<option value="IN_STORE">在库</option>
			<option value="EXTERNAL_BORROWING">外借中</option>
			<option value="INTERNAL_BORROWING">内部借调</option>
			<option value="UNDER_REPAIR">返修中</option>
			<option value="SELL_OUT">售出</option>
		</select>
	</div>	
	 -->						
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
	<th style="min-width:60px;" >管理</th>
	<th style="text-align: right; min-width:120px;" >操作</th>
</tr>
</thead>
<tbody>
<c:set value="${username }" var="username" />
<c:forEach var="device" items="${devices}" varStatus="status">
	<c:if test="${device.STATUS eq 'IN_STORE' }">
		<tr style = "background-color:#00CD00;">
	</c:if>
	<c:if test="${device.STATUS eq 'INTERNAL_BORROWING' }">
		<tr style = "background-color:#BBFFFF;">
	</c:if>
	<c:if test="${device.STATUS eq 'TEMP_OWN' }">
		<tr style = "background-color:#FFA54F;">
	</c:if>
	<c:if test="${device.STATUS eq 'TEMP_OWN_RELET' }">
		<tr style = "background-color:#FF4500;">
	</c:if>
	<c:if test="${device.STATUS eq 'EXTERNAL_BORROWING' }">
		<tr style = "background-color:#FFFF00;">
	</c:if>
	<c:if test="${device.STATUS eq 'SELL_OUT' }">
		<tr style = "background-color:#CDCDC1;">
	</c:if>
	<c:if test="${device.STATUS eq 'UNDER_REPAIR' }">
		<tr style = "background-color:#CDCDC1;">
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
			<c:if test="${device.STATUS eq 'TEMP_OWN' }">
				内部借入
			</c:if>
			<c:if test="${device.STATUS eq 'TEMP_OWN_RELET' }">
				转借中
			</c:if>
			<c:if test="${device.STATUS eq 'EXTERNAL_BORROWING' }">
				外借
			</c:if>
			<c:if test="${device.STATUS eq 'SELL_OUT' }">
				售出
			</c:if>
		</td>
		<td >${device.MANAGER }</td>
		<td style="text-align: right;" >
			<c:if test="${device.STATUS eq 'TEMP_OWN' or device.STATUS eq 'TEMP_OWN_RELET' }">
				<div class="btn-group">
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_external_borrowing" ${device.STATUS != 'TEMP_OWN' ? 'disabled' : ''} data-id="${device.ID}" data-name="${device.name}">外借</button>
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_in_store" data-status="${device.STATUS}" data-id="${device.ID}" data-name="${device.name}">${device.STATUS == 'TEMP_OWN' ? '归还' : '入库'}</button>
				<!-- <button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_repair" ${device.STATUS != 'TEMP_OWN' ? 'disabled' : ''} data-id="${device.ID}" data-name="${device.name}">返修</button>  -->
				</div>
			</c:if>
			
			<c:if test="${device.STATUS ne 'TEMP_OWN' and device.STATUS ne 'TEMP_OWN_RELET'}">
				<div class="btn-group">
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_internal_borrowing" ${device.STATUS != 'IN_STORE' ? 'disabled' : ''} data-id="${device.ID}" data-name="${device.name}">内借</button>
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_external_borrowing" ${device.STATUS != 'IN_STORE' ? 'disabled' : ''} data-id="${device.ID}" data-name="${device.name}">外借</button>
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_in_store" ${device.STATUS == 'IN_STORE' ? 'disabled' : ''} data-status="${device.STATUS}" data-id="${device.ID}" data-name="${device.name}">入库</button>
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_repair" ${device.STATUS != 'IN_STORE' ? 'disabled' : ''} data-id="${device.ID}" data-name="${device.name}">返修</button>
				</div>
			</c:if>
		</td>
</c:forEach>
</tbody>
</table>

<script type="text/javascript" src='<c:url value="/statics/js/bootstrap-datetimepicker.js" />' charset="UTF-8"></script>
<script type="text/javascript">
//按起始和结束时间获取信息列表
//实现了起始时间小于结束事件的默认设置
$(function(){
    $("#renttime, #renttime2, #renttime3, #expecttime, #expecttime2, #expecttime3, #actualtime").datetimepicker({
    	weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		forceParse: 0,
        showMeridian: 1,
        minView: 2,
        format: "yyyy-mm-dd"
    }).on('changeDate', function(ev){
		
    });
});
</script>