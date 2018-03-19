<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript">
	function checkDel(obj){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				location.href="${basePath}cms/admin/deleteAdmin.action?adminId="+obj;
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
	
	function editState(objA,objB){
		$.ajax({
			type:'post',
			data:'adminId='+objA+'&adminState='+objB,
			url:'cms/admin/frozenAdmin.action',
			success:function(data){
				if(data!=""){
					window.location.reload();
				}
			}
		});
	}
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">体验金赠送</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<!-- /.panel-heading -->
						<div class="panel-body">
							
							<input type="text" placeholder="体验金金额" class="form-control" id="accountBbin" name="accountBbin" required="required">
							
							<input type="button" onclick="gift()" class="btn btn-primary" value="赠送">
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
	</div>
	<%@include file="common/buttom.jsp"%>
	<script type="text/javascript">
	function gift() {
		var accountBbin ="accountBbin="+$("#accountBbin").val();
		$.ajax({
			type:'post',
			url:'cms/activity/bbinGift.action',
			data:accountBbin,
			success:function(data){	
				if("success"==data.message){
					alert("赠送成功");
				}				
			}
		});
	}
	
	
	
	</script>
	
</body>
</html>

