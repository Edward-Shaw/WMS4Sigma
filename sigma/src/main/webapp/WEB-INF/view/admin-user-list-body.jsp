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
		var name = button.data('name');
		var role = button.data('role');
		var department = button.data('department');

		$("#id").val(id);
		$("#name").val(name);
		$("#role").val(role);
		$("#department").val(department);
	});
	
	$('#modal_remove').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		$("#btn_remove").data('id', button.data('id'));
	});
	
	///submit the modify form
	$("#btn_modify").click(function(){
		var f = $("#form_modify");
		var action = f.attr('action') + "/" + f.find("#id").val();
		$.post(action, f.serialize(), function(data){
			location.reload();
		}, 'json');
	});
	
	$('#modal_delete').on('show.bs.modal', function (event) {
		var button = $(event.relatedTarget);
		$("#btn_delete_save").data('id', button.data('id'));
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
			var action = "./create";
			$.post(action, f.serialize(), function(data){
				$("#modal_create").modal('hide');
			}, 'json').done(function(data){
				if(data.code == 0){
					 alert("用户新增成功！");
				}else{
					alert("新增用户失败！");
				}
				location.reload();						
		});
	 });
});
</script>

<!-- add device -->
<div class="modal fade" id="modal_create" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content" id="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">新增员工</h4>
      </div>
      <div class="modal-body">
      	<form id="form_save"  class="form-horizontal" method="POST" action="./users/create">
			<div class="form-group form-group-sm">
	  			<label for="in-name" class="col-sm-2 control-label" >员工姓名</label>
	  			<div class="col-sm-10">
	  				<input id="in-name" name="name" type="text" class="form-control" placeholder="请输入员工姓名">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-count" class="col-sm-2 control-label" >所属部门</label>
	  			<div class="col-sm-10">
	  				<input id="in-count" name="department" type="text" class="form-control" placeholder="请输入员工所属部门">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-count" class="col-sm-2 control-label" >初始密码</label>
	  			<div class="col-sm-10">
	  				<input id="in-count" name="password" type="text" class="form-control" placeholder="请输入初始密码">
	  			</div>
			</div>
			<div class="form-group form-group-sm" >
  				<label for="input-quality-list" class="col-sm-2 control-label" >担当角色</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="input-quality-list" name="role">
	  				    <option value="opt">请选择员工担当角色</option>	
						<option value="EDITOR">样品管理担当员</option>
						<option value="VISITOR">游客</option>
						<option value="ADMIN">超级管理员</option>
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
        <h4 class="modal-title" id="myModalLabel">修改员工信息</h4>
      </div>
      <div class="modal-body">
      	<form id="form_modify"  class="form-horizontal" method="POST" action="./modify">
	       	<div class="input-group" style="margin-left:100px;margin-bottom:20px;display:none">
	  			<span class="input-group-addon" style="width:100px">id</span>
	  			<input id="id" name="id" type="text" class="form-control" placeholder="id" style="width:250px" readonly >
			</div>
	       	<div class="form-group form-group-sm">
	  			<label for="model" class="col-sm-2 control-label" >员工姓名</label>
	  			<div class="col-sm-10">
	  				<input id="name" name="name" type="text" class="form-control" placeholder="请输入型号">
	  			</div>
			</div>
			<div class="form-group form-group-sm">
	  			<label for="in-name" class="col-sm-2 control-label" >员工所属部门</label>
	  			<div class="col-sm-10">
	  				<input id="department" name="department" type="text" class="form-control" placeholder="请输入商品名称">
	  			</div>
			</div>
			<div class="form-group form-group-sm" >
  				<label for="input-quality-list" class="col-sm-2 control-label" >担当角色</label>
  				<div class="col-sm-10">
	  				<select class="form-control" id="input-quality-list" name="role">
	  				    <option value="opt">请选择员工担当角色</option>	
						<option value="EDITOR">样品管理担当员</option>
						<option value="VISITOR">游客</option>
						<option value="ADMIN">超级管理员</option>
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

<!-- delete device -->
<div class="modal fade" id="modal_delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">删除员工</h4>
      </div>
      <div class="modal-body">
      	<form id="form_delete"  class="form-horizontal" method="POST" action="./delete">
	       	<div class="input-group" style="margin-left:100px;margin-bottom:20px;display:none">
	  			<span class="input-group-addon" style="width:100px">id</span>
	  			<input id="deviceid" name="id" type="text" class="form-control" placeholder="id" style="width:250px" readonly >
			</div>			 
      		<div style="text-align:center;">
  				<span style="text-align:center;">确定要删除该员工在本系统中的账号信息？</span>
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
	<button class="btn btn-info btn-sm" type="button" style="float:right;" data-toggle="modal" data-target="#modal_create">新增员工</button>	
</div>

<table id="device_table" class="table table-hover table-striped" style="margin-top: 10px;">
<thead>
<tr>
	<th></th>
	<th style="width:150px;" >#ID</th>
	<th style="min-width:260px;" >名字</th>
	<th style="min-width:260px;" >部门</th>
	<th style="min-width:260px;" >角色</th>
	<th style="text-align: right; min-width:120px;" >操作</th>
</tr>
</thead>
<tbody>
<c:forEach var="user" items="${users}" varStatus="status">
<tr>
<td></td>
    <td >${user.id}</td>
    <td >${user.name}</td>
    <td >${user.department}</td>
	<td >
		<c:if test="${user.role eq 'EDITOR' }">
			样品担当员
		</c:if>
		<c:if test="${user.role eq 'VISITOR' }">
			无操作权限的员工
		</c:if>
	</td>
	<td style="text-align: right;" >
		<div class="btn-group">
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_modify" data-id="${user.id}" data-name="${user.name}" data-role="${user.role}" data-department="${user.department}">编辑</button>
				<button  type="button" class="btn btn-default btn-xs" data-toggle="modal" data-target="#modal_delete" data-id="${user.id}" data-name="${user.name}">删除</button>
		</div>
	</td>
</c:forEach>
</tbody>
</table>