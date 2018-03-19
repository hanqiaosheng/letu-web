<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">新增渠道</h1>
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
							<form role="form" action="cms/channel/channelAdd.action"  onsubmit="return formSub()" method="post">
							<div id="channnelStr"> 
							
							
							</div>
							<label>渠道级别：</label>
									<select class="form-control" id="channelLevel" name="channelLevel" onchange="changeChannel()">
									<c:if test="${empty channel }"><option value="1">一级</option></c:if>
									<option value="2">二级</option>
									</select>
								<br>
								<label>上级渠道选择：</label>
									<select class="form-control" id="parentChannel" name="channelParentId">
									
									</select>
								<br>
								<label>渠道名：</label>
								<div class="form-group">
									<input class="form-control"  placeholder="渠道名" id="channelName" name="channelName" onblur="checkName()" type="text"
										required="required">
								</div>
								<br>
								<label>渠道负责人：</label>
								<div class="form-group">
									<input class="form-control" placeholder="渠道负责人" type="text" name=channelChargerName required="required">
								</div>
								<br>
							<%-- 	<label>所属城市：</label>
							    <select class="form-control" id="channelCityId" name="channelCityId">
								<c:forEach var="city" items="${cityList }">
								  <option value="${city.cityId }">${city.cityName }</option>
								</c:forEach>
								</select>
								<br> --%>
								<label>联系方式：</label>
								<div class="form-group">
									<input class="form-control" placeholder="联系方式" type="text" name="channelChargerTel" required="required">
								</div>
								<br>
								<label>简介：</label>
								<div class="form-group">
									<textarea class="form-control" name="channelIntroduction" rows="" cols=""></textarea>
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
<script>
  $(function(){
	  changeChannel();
  })

</script>
<script type="text/javascript">
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
<%@include file="../common/buttom.jsp"%>


</html>
