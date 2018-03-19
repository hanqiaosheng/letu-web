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
			<h1 class="page-header">新增会员</h1>
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
							<form role="form" action="cms/user/addVip.action" id="importForm" onsubmit="return formSub()" method="post">
							<div id="channnelStr"> 
							
							
							</div>
							
								<br>
								
								<br>
								<label>身份证号：</label>
								<div class="form-group">
									<input class="form-control"  placeholder="身份证号" id="userIdcard" name="userIdcard" onblur="checkName()" type="text"
										required="required">
								</div>
								<br>
								<label>手机号：</label>
								<div class="form-group">
									<input class="form-control" placeholder="手机号 " id="userTel" name="userTel" required="required" onblur="checkMobile()" maxlength="11"  type="tel">
								</div>
								
								<br>
								<label>会员类型：</label>
								<input type="radio" checked="checked" name="modelsIsfixedPoint" value="1" onclick="checkIsFixed(1)"> 村民会员
							    <input type="radio"  name="modelsIsfixedPoint" value="0" onclick="checkIsFixed(0)"> 银行年费会员
							    <br>
							    <div id="fixed" style="display: none;">
							    
							    <label>充值金额：</label>
							   <select class="form-control" id="channelLevel" name="accountAvailableBalance" >
									<option value="50">充值金额50元</option>
									<option value="100">充值金额100元</option>
									<option value="200">充值金额200元</option>
								</select>
								</div>
								 <div id="fixed1" style="display: none;">
							    
							    <label>充值金额：</label>
							       <input class="form-control" placeholder="金额" type="text" name="accountAvailableBalance" required="required">
								
								</div> 
								<!-- <label>金额：</label>
								<div class="form-group">
									<input class="form-control" placeholder="金额" type="text" name="accountAvailableBalance" required="required">
								</div> -->
								<br>
								<label>所属渠道 ：</label>
							    <select id="channelVal" class="form-control" name="channelId">
							    <c:forEach var="channel" items="${channelList }">
							    <option value="${channel.channelId }">${channel.channelName}</option>
							    </c:forEach>
							    </select>
							    <br>
								
								<div style="text-align: center;">
									<!-- <input type="submit" value="提交" class="btn btn-primary"> -->
									<button  class="btn btn-primary" type="button" onclick="checkVip()">提交</button>
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

    function checkMobile(){
	var userTel = $("#userTel").val();
 	   /*  if(isNaN(userTel)||(userTel.length!=11)){ 
        $.alert("手机号码为11位数字！请正确填写！",true,function(){
        },false,{className:'ui-alert',width:300});
        phoneFlag =  false; 
    }   */
    var reg =/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if(!reg.test(userTel)) 
    { 
        $.alert("您的手机号码不是正常号码，请重新输入！");
        phoneFlag =  false; 
    } 
    phoneFlag =  true;
}
    
    function checkVip(){
    	var userTel = $("#userTel").val();
    	var userIdcard = $("#userIdcard").val();
    	$.ajax({
			type:"post",
			url:"cms/user/checkVip.action",
			data:'userTel='+userTel+'&userIdcard='+userIdcard,
			dataType:"json",
			async:false,
			success:function(data){
				if(data==1){
					$.alert("用户不存在（确保用户不被删除或拉黑）或用户信息有误");
					}
				if(data==2){
					$.alert("已存在该会员！");
					}
				if(data==0){
					$("#importForm").submit();
				}
				}
			})
    }
    
	function changeChannel(){
		var channelLevel  = $("#channelLevel").val();
		if(2==channelLevel){
			$.ajax({
				type:"post",
				url:"cms/channel/getParentChannel.action",
				dataType:"json",
				success:function(data){
					if(data.message != "success"){
						alert("没有上级渠道");
						 $("#channelLevel").val(1);
					}else{
						var optionHtml  = "";
						var j=0;
						$.each(data.pChannels,function(i,channel){
							optionHtml =optionHtml+"<option value="+channel.channelId+">"+channel.channelName+"</option>";
							j++;
						})							
						$("#parentChannel").append(optionHtml);
					}
				}
			});
			
		
			
		}else{
			$("#parentChannel").empty();
		}
		
	}



	var nameFlag = false;
	function checkName(){
		
		var str = "channelName="+$('#channelName').val();
		$.ajax({
				type:"post",
				url:"cms/channel/checkChannelName.action",
				dataType:"json",
				data:str,
				success:function(data){
					if(data.message != "success"){
						alert("已有重名渠道");
						nameFlag = false;
					}else{
						nameFlag = true;
					}
				}
			});
	}
	

	function formSub(){
		if(!nameFlag){
			alert("该渠道已存在");
			return false;
		}
		
		return true;
	}

</script>
<script type="text/javascript">
  $(function(){
	  checkIsFixed($("[name='modelsIsfixedPoint']:checked").val());
  })
  function checkIsFixed(obj){
	  if(obj==1){
		  $("#fixed").show();
		  $("#fixed1").hide();
	  }else if(obj==0){
		 $("#fixed").hide();
		 $("#fixed1").show();
	  }
  }
</script>
<%@include file="../common/buttom.jsp"%>

<link rel="stylesheet" href="assets/selectSearch/select2.min.css">
<script type="text/javascript" src="assets/selectSearch/select2.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#channelVal").select2();
    })
</script>
</html>
