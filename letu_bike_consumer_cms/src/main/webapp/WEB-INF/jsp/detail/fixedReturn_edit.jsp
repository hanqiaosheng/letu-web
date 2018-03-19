<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript" src="assets/dialog.js"></script>
<link rel="stylesheet" href="assets/dialog.css">
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">修改还车点</h1>
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
							<form role="form" action="cms/fixedReturn/fixedReturnEdit.action" id="form" method="post">
							<input type="hidden" name="fixedReturnId" value="${fixedReturn.fixedReturnId }">
							<label>还车点名字：</label>
								<div class="form-group">
									<input class="form-control"  placeholder="还车点名字" id="fixedReturnName" name="fixedReturnName" value="${fixedReturn.fixedReturnName }" type="text"
										required="required">
								</div>
								<br>
								<label>渠道名 ：</label>
							    <select class="form-control" id="channelId" name="fixedReturnChannelId">
							    <c:forEach var="channel" items="${channelList }">
							    <option value="${channel.channelId }" <c:if test="${channel.channelId==fixedReturn.fixedReturnChannelId}">selected="selected"</c:if>>${channel.channelName }</option>
							    </c:forEach>
							    </select>
							    <br>
								<label>简介：</label>
								<div class="form-group">
									<textarea class="form-control" name="fixedReturnBrief" rows="" cols="">${fixedReturn.fixedReturnBrief }</textarea>
								</div>
								<br>
								<label>管理员号码：</label><a style="color: red">多个号码请用逗号分隔</a>
								<div class="form-group">
									<input class="form-control" placeholder="管理员号码" type="text" id="fixedReturnTel" name="fixedReturnTel" value="${fixedReturn.fixedReturnTel }" required="required">
								</div>
								<br>
								<label>还车点位置：</label>
								<div class="form-group">
									<input class="form-control go-back" placeholder="还车点位置" type="text" id="latlng" name="latlng" value="${fixedReturn.fixedReturnLng },${fixedReturn.fixedReturnLat }" required="required">
								</div>
								<br>
								<label>可用车数量：</label>
								<div class="form-group">
									<input class="form-control" placeholder="可用车数量" type="text" id="fixedReturnBikeNum" name="fixedReturnBikeNum" required="required" value="${fixedReturn.fixedReturnBikeNum }">
								</div>
								<br>
								<label>还车点范围距离（米）：</label>
								<div class="form-group">
									<input class="form-control" placeholder="还车点范围距离（米）" type="text" id="fixedReturnDistance" name="fixedReturnDistance" value="${fixedReturn.fixedReturnDistance }" required="required">
								</div>
								<br>
								<label>还车点介绍内容：</label>
								<div class="form-group" id='brief'>
										<script id="editor" type="text/plain" name="fixedReturnContent" style="height: 400px;"></script>
								</div>
								<div style="text-align: center;">
									<input type="button" onclick="gotoAction()" value="提交" class="btn btn-primary">
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

<%@include file="../common/buttom.jsp"%>
<script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript">
	var url_img = '${url}';
	var ue = UE.getEditor('editor');
	ue.ready(function() {
	    ue.setContent('${fixedReturn.fixedReturnContent}', true);
	    
	});
	function gotoAction(){
		if($("#fixedReturnName").val()==''){
			 $.alert('还车点名字不能为空')
			 return;
		}
		if($("#fixedReturnTel").val()==''){
			 $.alert('管理员号码不能为空')
			 return;
		}
		if($("#latlng").val()==''){
			 $.alert('还车点位置不能为空')
			 return;
		}
		if($("#fixedReturnBikeNum").val()==''){
			 $.alert('可用车数量不能为空')
			 return;
		}
		if($("#fixedReturnDistance").val()==''){
			 $.alert('还车点范围距离不能为空')
			 return;
		}
		var editorhtml=ue.getContent()
		if(editorhtml!=''){
	         $('form').submit();
	    }else{
	         $.alert('还车点介绍内容不能为空')
	    }
	}
	
	</script>
	 <script type="text/javascript" src='js/uploadUE.js'></script>
<%@include file="../common/maskMap.jsp"%>
<link rel="stylesheet" href="assets/selectSearch/select2.min.css">
<script type="text/javascript" src="assets/selectSearch/select2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#channelId").select2();
	})
</script>
</html>
