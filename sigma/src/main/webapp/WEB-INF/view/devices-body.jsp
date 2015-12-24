<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
$(function(){	
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
		var action = f.attr('action') + "/" + f.find("#modify-id").val();
		$.post(action, f.serialize(), function(data){
			location.reload();
		}, 'json');
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
	$("#filter_status").change(function(){
		location.href = '<c:url value="/devices" />?status=' + $("#filter_status").val();
	});
	
	$("#filter_state").val("${empty param.state ? 'NORMAL' : param.state}");
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
  				<label for="input-cp-list" class="col-sm-2 control-label" >管理担当</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="input-manager-list" name="manager">
	  				    <option value="opt">请选择样品管理员</option>
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
      	<form id="form_modify"  class="form-horizontal" method="POST" action="./platforms">
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
  				<label for="input-cp-list" class="col-sm-2 control-label" >管理担当</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="input-manager-list" name="manager">
	  				    <option value="opt">请选择样品管理员</option>
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
        <button id="btn_modify" type="button" class="btn btn-primary">保存</button>
      </div>
    </div>
  </div>
</div>

<!-- remove platform -->
<div class="modal fade" id="modal_remove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">确定要出借该样品吗?</h4>
      </div>
      <div class="modal-body">
      	<p style="text-align:center;">移除该平台将造成该平台无法被访问并无法恢复。<br><br></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="btn_remove" type="button" class="btn btn-danger">移除</button>
      </div>
    </div>
  </div>
</div>

<!-- restore platform -->
<div class="modal fade" id="modal_restore" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">恢复平台</h4>
      </div>
      <div class="modal-body">
      	<p style="text-align:center;">确定要恢复该平台吗?<br><br></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button id="btn_restore" type="button" class="btn btn-success">恢复</button>
      </div>
    </div>
  </div>
</div>

<div class="row-fluid form-inline">
	<button class="btn btn-info btn-sm" type="button" style="float:right;" data-toggle="modal" data-target="#modal_create">新增样机</button>
	
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
		<span class="input-group-addon">管理担当</span>
		<select class="form-control" id="filter_user">
			<option value="ALL">选择管理担当人</option>
			<c:forEach var="record" items="${users}" varStatus="user">	
				<option value="${record.id}">${record.name}</option>		
			</c:forEach>
		</select>
	</div>					
</div>

<table class="table table-hover table-striped" style="margin-top: 10px;">
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
	<th style="text-align: right; min-width:120px;" >操作</th>
</tr>
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
	    <td >${device.ID}</td>
	    <td >${device.MODEL}</td>
	    <td >${device.ASSET_CODE}</td>
		<td >${device.SERIAL_NUMBER_1 }</td>
		<td >${device.SERIAL_NUMBER_2 }</td>
		<td >${device.BAYONET_1 }</td>
		<td >${device.BAYONET_2 }</td>
		<td >${device.NAME }</td>
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
		<td >${device.MANAGER }</td>
		<td style="text-align: right;" >
			<div class="btn-group">
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_modify" data-id="${platform.id}" data-name="${platform.name}">编辑</button>
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_modify" data-id="${platform.id}" data-name="${platform.name}">转售</button>
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_modify" data-id="${platform.id}" data-name="${platform.name}">内借</button>
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_modify" data-id="${platform.id}" data-name="${platform.name}">外借</button>
				<button type="button" 
						class="btn btn-xs btn-${platform.state == 'REMOVED' ? 'success' : 'danger'}" data-toggle="modal" 
						data-target="${platform.state == 'REMOVED' ? '#modal_restore' : '#modal_remove'}" 
						data-id="${platform.id}">${platform.state == 'REMOVED' ? '入库' : '返修' }</button>
			</div>
		</td>
	</tr>
</c:forEach>
</table>