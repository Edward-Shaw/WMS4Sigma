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
	
	$('#modal_remove').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		$("#btn_remove").data('id', button.data('id'));
	});
	
	$('#modal_restore').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		$("#btn_restore").data('id', button.data('id'));
	});
	
	///submit the modify form
	$("#btn_internal_borrowing").click(function(){
		var f = $("#form_internal_borrowing");
		var action = f.attr('action');
		$.post(action, f.serialize() + "&renttype=test&company=sigma", function(data){
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
	
	$("#modal_internal_borrowing").on('show.bs.modal', function(event){
		//获取员工信息
		var url = '<c:url value="/users/list"/>';
		
		$.get(url, function(data, status){
			if(data.code == 0){
				var p = data.result;
				$("#input-manager-list").empty();
				$("#input-manager-list").append($("<option/>").text('选择借用人').attr("value",0));
				
				$(p).each(function(){
					//创建option对象并设置相应的文本值和value值
					var opt = $("<option/>").text(this.name).attr("value", this.id);
					//将option对象添加到匹配的jQuery对象中
					$("#input-manager-list").append(opt);
				});
			}
		}, 'json');
	});
	 
	//下拉框根据状态选择平台
	$("#filter_status").change(function(){
		location.href = '<c:url value="/staff" />?status=' + $("#filter_status").val();
	});
	
	$("#filter_state").val("${empty param.state ? 'NORMAL' : param.state}");
});
</script>

<!-- 器材内借 -->
<div class="modal fade" id="modal_internal_borrowing" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel" style="text-align:center; color:green;"><strong>器材内借</strong></h4>
      </div>
      <div class="modal-body">
      	<form id="form_internal_borrowing"  class="form-horizontal" method="POST" action="./users/rent">
      		<div class="form-group form-group-sm" >
  				<label for="input-cp-list" class="col-sm-2 control-label" >借取人</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="input-manager-list" name="renter">
	  				    <option value="opt">请选择器材借取人</option>
						<c:forEach var="record" items="${users}" varStatus="status">	
							<option value="${record.ID}">${record.NAME}</option>		
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
			<div class="form-group form-group-sm">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >借取日</label>
	  			<div class="col-sm-10">
	  				<input id="in-kakou2" name="renttime" type="text" class="form-control" placeholder="默认为当天">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >预计归还日</label>
	  			<div class="col-sm-10">
	  				<input id="in-kakou2" name="excepttime" type="text" class="form-control" placeholder="请选择预计归还日">
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
      	<form id="form_modify"  class="form-horizontal" method="POST" action="./platforms">
	       	<div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >借取人</label>
	  			<div class="col-sm-10">
	  				<input id="in-model" name="model" type="text" class="form-control" placeholder="请输入借取人">
	  			</div>
			</div>
	       	<div class="form-group form-group-sm">
	  			<label for="in-model" class="col-sm-2 control-label" >借取目的</label>
	  			<div class="col-sm-10">
	  				<input id="in-model" name="model" type="text" class="form-control" placeholder="请输入借取目的">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-assetcode" class="col-sm-2 control-label" >联系电话</label>
	  			<div class="col-sm-10">
	  				<input id="in-assetcode" name="asset_code" type="text" class="form-control" placeholder="请输入借取人联系电话">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >借取日</label>
	  			<div class="col-sm-10">
	  				<input id="in-kakou2" name="kakou2" type="text" class="form-control" placeholder="不填写则默认为当天">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-kakou2" class="col-sm-2 control-label" >预计归还日</label>
	  			<div class="col-sm-10">
	  				<input id="in-kakou2" name="kakou2" type="text" class="form-control" placeholder="请选择预计归还日">
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

<div class="row-fluid form-inline">
				
</div>

<table id="device_table" class="table table-hover table-striped" style="margin-top: 10px;">
<thead>
<tr>
	<th style="width:60px;" >#ID</th>
	<th style="min-width:60px;" >借用人</th>
	<th style="min-width:60px;" >公司</th>
	<th style="min-width:60px;" >职位</th>
	<th style="min-width:60px;" >联系方式</th>
	<th style="min-width:60px;" >借用目的</th>
	<th style="min-width:60px;" >样机号</th>
	<th style="min-width:60px;" >资产编码</th>
	<th style="min-width:60px;" >当前状态</th>
	<th style="min-width:80px;" >借用时间</th>
	<th style="min-width:60px;" >预期归还时间</th>
	<th style="min-width:60px;" >实际归还时间</th>
	<th style="min-width:80px;" >最近一次变更</th>
</tr>
</thead>
<tbody>
<c:forEach var="device" items="${logs}" varStatus="status">
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
	    <td >${device.RENTER}</td>
	    <td >${device.COMPANY}</td>
		<td >${device.POSITION }</td>
		<td >${device.PHONE }</td>
		<td >${device.PURPOSE }</td>
		<td >${device.DEVICE_ID }</td>
		<td >${device.ASSET_CODE }</td>
		<td >	
			<c:if test="${device.STATUS eq 'IN_STORE' }">
				在库
			</c:if>
			<c:if test="${device.STATUS eq 'UNDER_REPAIR' }">
				返修中
			</c:if>
			<c:if test="${device.STATUS eq 'INTERNAL_BORROWING' }">
				内部借调中
			</c:if>
			<c:if test="${device.STATUS eq 'EXTERNAL_BORROWING' }">
				出借中
			</c:if>
			<c:if test="${device.STATUS eq 'SELL_OUT' }">
				已售出
			</c:if>
		</td>
		<td >${device.RENT_TIME }</td>
		<td >${device.EXCEPT_TIME }</td>
		<td >${device.ACTUAL_TIME }</td>
		<td >${device.MODIFY_TIME }</td>
</c:forEach>
</tbody>
</table>
