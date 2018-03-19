<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">密码修改</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a class="btn btn-danger" 
						href="javascript:history.go(-1)">返回</a>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form role="form" action="cms/admin/modifyPwd.action" onsubmit="return formSub()" method="post">
								<div class="form-group">
									<label>修改密码</label>
									<div class="form-group">
										<input class="form-control" placeholder="请输入原密码" name="oldpassword" type="password" id="oldpassword"
											required="required">
										</div>
										<div class="form-group myH1" style="display: none">
											<input class="form-control" placeholder="密码" type="password" id="newpassword"
												name="adminPassword" required="required">
										</div>
										<div class="form-group myH1" style="display: none">
											<input class="form-control" placeholder="再次输入密码" type="password" id="repassword"
												name="adminPassword2" required="required">
										</div>
									</div>
								<div style="text-align: center;">
									<input type="submit" value="提交" class="btn btn-primary">
								</div>
							</form>
						</div>
						<!-- /.col-lg-6 (nested) -->
					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</div>
<script type="text/javascript">
$(document).ready(function() {
    $('#repassword').blur(function(){
    	if($('#repassword').val()!=$('#newpassword').val()){
    		$.alert("两次密码不一致"); 
    	}
    })
    
});
var pwd=0
$('#oldpassword').blur(function(){
	var str = "&oldpassword="+$('#oldpassword').val();
     $.ajax({
			type:"post",
			url:"cms/admin/checkPwd.action",
			dataType:"json",
			data:str,
			success:function(data){
				if(data.message != "success"){
					$.alert("原密码不正确"); 
					pwd = 0;
					$('.myH1').hide();
				}else{
					pwd = 1;
					$('.myH1').show();
				}
			}
		})
})

	function formSub(){
	    	if($('#repassword').val()!=$('#newpassword').val()){
	    		$.alert("两次密码不一致!"); 
	    		return false;
	    	}else if(pwd==0){
	    		$.alert("原密码不正确!");
	    		return false;
	    	}
	    	return true;
	    }

</script>
<%@include file="../common/buttom.jsp"%>
</html>
