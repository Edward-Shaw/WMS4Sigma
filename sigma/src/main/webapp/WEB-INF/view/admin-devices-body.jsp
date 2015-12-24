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
	
	$('#modal_modify').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		var id = button.data('id');
	
		///获取device信息
		var url = '<c:url value="/devices" />/' + id;
		$.get(url, function(data, status){
			if(data.code == 0){
				var p = data.result;
				$("#deviceid").val(p.ID);
				$("#model").val(p.MODEL);
				$("#assetcode").val(p.ASSET_CODE);
				$("#serialnuma").val(p.SERIAL_NUMBER_1);
				$("#serialnumb").val(p.SERIAL_NUMBER_2);
				$("#kakoua").val(p.BAYONET_1);
				$("#kakoub").val(p.BAYONET_2);
				$("#name").val(p.NAME);
				$("#count").val(p.COUNT);
				$("#quality").val(p.QUALITY_GRADE);
			}
		}, 'json');
	});
	
	$('#modal_remove').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		$("#btn_remove").data('id', button.data('id'));
	});
	
	$('#modal_restore').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		$("#btn_restore").data('id', button.data('id'));
	});
	
	///submit the modify form
	$("#btn_modify").click(function(){
		var f = $("#form_modify");
		var action = f.attr('action') + "/" + f.find("#deviceid").val();
		$.post(action, f.serialize(), function(data){
			location.reload();
		}, 'json');
	});
	
	$('#modal_distribute').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		$("#btn_distribute_save").data('id', button.data('id'));
	});
	
	$('#modal_delete').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		$("#btn_delete_save").data('id', button.data('id'));
	});
	
	///submit the modify form
	$("#btn_distribute_save").click(function(){
		var f = $("#form_distribute");		
		var id = $(this).data('id');

		var action = f.attr('action') + "/" + id;
		$.post(action, f.serialize(), function(data){
			location.reload();
		}, 'json');
	});
	
	///submit the modify form
	$("#btn_delete_save").click(function(){
		var f = $("#form_delete");		
		var id = $(this).data('id');

		var action = f.attr('action') + "/" + id;
		$.post(action, f.serialize(), function(data){
			location.reload();
		}, 'json');
	});
	
	$("#create-save-btn").click(function(){
			var f = $("#form_save");
			var action = "./devices/create";
			$.post(action, f.serialize(), function(data){
				$("#modal_create").modal('hide');
			}, 'json').done(function(data){
				if(data.code == 0){
					 alert("样品新增成功！");
				}else{
					alert(data.result.MESSAGE);
				}
				location.reload();						
		});
	 });
	 
	//下拉框根据状态选择平台
	$("#filter_status, #filter_user").change(function(){
		location.href = '<c:url value="/admin" />?status=' + $("#filter_status").val() + "&user=" + $("#filter_user").val();
	});
	
    //回车键相应搜索功能 
	$("#search_serialcode").keyup(function(event){
	 	if(event.keyCode == 13){
	 		location.href = '<c:url value="/admin" />?status=' + 
	 				$("#filter_status").val() + 
	 					"&user=" + $("#filter_user").val()
	 						+ "&serialcode=" + $("#search_serialcode").val();
	 	}
	 });
	
	$("#filter_status").val("${empty param.status ? 'ALL' : param.status}");
	$("#filter_user").val("${empty param.user ? 'ALL' : param.user}");
	$("#search_serialcode").val("${empty param.serialcode ? '' : param.serialcode}");
});
</script>

<!-- add device -->
<div class="modal fade" id="modal_create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" id="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">新增样机</h4>
      </div>
      <div class="modal-body">
      	<form id="form_save"  class="form-horizontal" method="POST" action="./create">
      	    <div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >型号</label>
	  			<div class="col-sm-10">
	  				<input id="in-model" name="model" type="text" class="form-control" placeholder="请输入型号">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-assetcode" class="col-sm-2 control-label" >资产编码</label>
	  			<div class="col-sm-10">
	  				<input id="in-assetcode" name="asset_code" type="text" class="form-control" placeholder="请输入资产编码">
	  			</div>
			</div>
      		<div class="form-group form-group-sm">
	  			<label for="in-serial-num1" class="col-sm-2 control-label" >序列号Ⅰ</label>
	  			<div class="col-sm-10">
	  				<input id="in-serial-num1" name="serial_num1" type="text" class="form-control" placeholder="请输入序列号">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-serial-num2" class="col-sm-2 control-label" >序列号Ⅱ</label>
	  			<div class="col-sm-10">
	  				<input id="in-serial-num2" name="serial_num2" type="text" class="form-control" placeholder="请输入序列号">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-kakou1" class="col-sm-2 control-label" >卡口Ⅰ</label>
	  			<div class="col-sm-10">
	  				<input id="in-kakou1" name="kakou1" type="text" class="form-control" placeholder="请输入卡口Ⅰ信息">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >卡口Ⅱ</label>
	  			<div class="col-sm-10">
	  				<input id="in-kakou2" name="kakou2" type="text" class="form-control" placeholder="请输入卡口Ⅱ信息">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-name" class="col-sm-2 control-label" >商品名称</label>
	  			<div class="col-sm-10">
	  				<input id="in-name" name="name" type="text" class="form-control" placeholder="请输入商品名称">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-count" class="col-sm-2 control-label" >数量</label>
	  			<div class="col-sm-10">
	  				<input id="in-count" name="count" type="text" class="form-control" placeholder="请输入数量">
	  			</div>
			</div>
			<div class="form-group form-group-sm" >
  				<label for="input-quality-list" class="col-sm-2 control-label" >样机种类</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="input-quality-list" name="quality">
	  				    <option value="opt">请选择样机种类</option>	
						<option value="LOW_QUALITY">水货</option>
						<option value="MEDIA_SAMPLE">媒体样机</option>
						<option value="SALES_SAMPLE">销售样机</option>
					</select>
				</div>
			</div>
      		<div class="form-group form-group-sm" >
  				<label for="input-cp-list" class="col-sm-2 control-label" >管理担当</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="input-manager-list" name="manager">
	  				    <option value="opt">请选择样机管理员</option>
						<c:forEach var="record" items="${users}" varStatus="status">	
							<option value="${record.id}">${record.name}</option>		
						</c:forEach>
					</select>
				</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="create-save-btn" type="button" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>

<!-- modify platform -->
<div class="modal fade" id="modal_modify" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">修改平台信息</h4>
      </div>
      <div class="modal-body">
      	<form id="form_modify"  class="form-horizontal" method="POST" action="./devices">
	       	<div class="input-group" style="margin-left:100px;margin-bottom:20px;display:none">
	  			<span class="input-group-addon" style="width:100px">id</span>
	  			<input id="deviceid" name="id" type="text" class="form-control" placeholder="id" style="width:250px" readonly >
			</div>
	       	<div class="form-group form-group-sm">
	  			<label for="model" class="col-sm-2 control-label" >型号</label>
	  			<div class="col-sm-10">
	  				<input id="model" name="model" type="text" class="form-control" placeholder="请输入型号">
	  			</div>
			</div>
			
			<div class="form-group form-group-sm">
	  			<label for="in-assetcode" class="col-sm-2 control-label" >资产编码</label>
	  			<div class="col-sm-10">
	  				<input id="assetcode" name="assetcode" type="text" class="form-control" placeholder="请输入资产编码">
	  			</div>
			</div>
      		<div class="form-group form-group-sm">
	  			<label for="in-serial-num1" class="col-sm-2 control-label" >序列号Ⅰ</label>
	  			<div class="col-sm-10">
	  				<input id="serialnuma" name="serialnum1" type="text" class="form-control" placeholder="请输入序列号">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-serial-num2" class="col-sm-2 control-label" >序列号Ⅱ</label>
	  			<div class="col-sm-10">
	  				<input id="serialnumb" name="serialnum2" type="text" class="form-control" placeholder="请输入序列号">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-kakou1" class="col-sm-2 control-label" >卡口Ⅰ</label>
	  			<div class="col-sm-10">
	  				<input id="kakoua" name="kakou1" type="text" class="form-control" placeholder="请输入卡口Ⅰ信息">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >卡口Ⅱ</label>
	  			<div class="col-sm-10">
	  				<input id="kakoub" name="kakou2" type="text" class="form-control" placeholder="请输入卡口Ⅱ信息">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-name" class="col-sm-2 control-label" >商品名称</label>
	  			<div class="col-sm-10">
	  				<input id="name" name="name" type="text" class="form-control" placeholder="请输入商品名称">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-count" class="col-sm-2 control-label" >数量</label>
	  			<div class="col-sm-10">
	  				<input id="count" name="count" type="text" class="form-control" placeholder="请输入数量">
	  			</div>
			</div>
			<div class="form-group form-group-sm" >
  				<label for="input-quality-list" class="col-sm-2 control-label" >样机种类</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="quality" name="quality">
	  				    <option value="opt">请选择样机种类</option>
						<option value="LOW_QUALITY">水货</option>
						<option value="MEDIA_SAMPLE">媒体样机</option>
						<option value="SALES_SAMPLE">销售样机</option>
					</select>
				</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="btn_modify" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- distribute device to staff -->
<div class="modal fade" id="modal_distribute" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">分配样机</h4>
      </div>
      <div class="modal-body">
      	<form id="form_distribute"  class="form-horizontal" method="POST" action="./devices">
	       	<div class="input-group" style="margin-left:100px;margin-bottom:20px;display:none">
	  			<span class="input-group-addon" style="width:100px">id</span>
	  			<input id="deviceid" name="id" type="text" class="form-control" placeholder="id" style="width:250px" readonly >
			</div>			 
      		<div class="form-group form-group-sm" >
  				<label for="input-cp-list" class="col-sm-2 control-label" >管理担当</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="input-manager-list" name="manager">
	  				    <option value="opt">请选择样机管理员</option>
						<c:forEach var="record" items="${users}" varStatus="status">	
							<option value="${record.id}">${record.name}</option>		
						</c:forEach>
					</select>
				</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="btn_distribute_save" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- delete device -->
<div class="modal fade" id="modal_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">删除样机</h4>
      </div>
      <div class="modal-body">
      	<form id="form_delete"  class="form-horizontal" method="POST" action="./devices/delete">
	       	<div class="input-group" style="margin-left:100px;margin-bottom:20px;display:none">
	  			<span class="input-group-addon" style="width:100px">id</span>
	  			<input id="deviceid" name="id" type="text" class="form-control" placeholder="id" style="width:250px" readonly >
			</div>			 
      		<div class="form-group form-group-sm" >
  				<label for="input-cp-list" class="col-sm-2 control-label" >删除原因</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="input-manager-list" name="decrease">
	  				    <option value="opt">请选择删除样机的原因</option>
	  				    <option value="REMADE">翻新</option>
	  				    <option value="SELLOUT">出售</option>
					</select>
				</div>
			</div>
		</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="btn_delete_save" type="button" class="btn btn-primary">确定删除</button>
      </div>
    </div>
  </div>
</div>

<div class="row-fluid form-inline" style="margin-bottom:15px;">
	<button class="btn btn-info btn-sm" type="button" style="float:right;" data-toggle="modal" data-target="#modal_create">新增样机</button>
	
	<div class="input-group input-group-sm">
		<span class="input-group-addon">样机状态</span>
		<select class="form-control" id="filter_status">
			<option value="ALL">选择样机状态</option>
			<option value="IN_STORE">在库</option>
			<option value="EXTERNAL_BORROWING">外借中</option>
			<option value="INTERNAL_BORROWING">内部借调</option>
			<option value="UNDER_REPAIR">返修中</option>
			<option value="SELL_OUT">售出</option>
		</select>
	</div>

	<div class="input-group input-group-sm">
		<span class="input-group-addon">管理担当</span>
		<select class="form-control" id="filter_user">
			<option value="ALL">选择管理担当人</option>
			<c:forEach var="record" items="${users}" varStatus="user">	
				<option value="${record.id}">${record.name}</option>		
			</c:forEach>
		</select>
	</div>
	
	<div class="input-group input-group-sm">
		<span class="input-group-addon">序列号</span>
        <input type="text" name="search-serialcode" id="search_serialcode" placeholder="输入序列号并按回车键搜索" class="form-control">
	</div>			
</div>

<table id="device_table" class="table table-hover table-striped" style="margin-top: 10px;">
<thead>
<tr>
	<th style="width:50px;" >#ID</th>
	<th style="min-width:60px;" >型号</th>
	<th style="min-width:60px;" >资产编码</th>
	<th style="min-width:60px;" >序列号Ⅰ</th>
	<th style="min-width:60px;" >序列号Ⅱ</th>
	<th style="min-width:60px;" >卡口Ⅰ</th>
	<th style="min-width:60px;" >卡口Ⅱ</th>
	<th style="min-width:60px;" >商品名称</th>
	<th style="min-width:50px;" >种类</th>
	<th style="min-width:50px;" >数量</th>
	<th style="min-width:60px;" >状态</th>
	<th style="min-width:60px;" >出借对象</th>
	<th style="min-width:60px;" >出借时间</th>
	<th style="min-width:60px;" >管理</th>
	<th style="text-align: right; min-width:120px;" >操作</th>
</tr>
</thead>
<tbody>
<c:forEach var="device" items="${devices}" varStatus="status">
	<c:if test="${device.STATUS eq 'IN_STORE' }">
		<tr style = "background-color:#00CD00;">
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
		<td style="text-align: right;" >
			<div class="btn-group">
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_modify" data-id="${device.ID}" data-name="${device.NAME}">编辑</button>
				<button id = "distributebtn" type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_distribute" data-id="${device.ID}" data-name="${device.NAME}">分配</button>
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_delete" data-id="${device.ID}" data-name="${device.NAME}">删除</button>
			</div>
		</td>
</c:forEach>
</tbody>
</table>