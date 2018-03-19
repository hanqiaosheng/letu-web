<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">指南内容修改</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-9">
							<form action="cms/user/guide/edit.action" method="post" id="editorForm" enctype="multipart/form-data">
								<input type="hidden" name=userGuideId value="${userGuide.userGuideId }">
								<div class="form-group" id='brief'>
									<label class="col-md-2 control-label">指南内容：</label>
									<div class="col-md-8">
										<script id="editor" type="text/plain" name="userGuideContent" style="height: 400px;"></script>
									</div>
								</div>
								<br />
								<div class="form-group">
									<label class="col-md-2 control-label"></label>
									<div class="col-md-12">
										<div class="center-box" style="text-align: center;">
											<button type="button" style="width: 100px;" id="confirm" class="btn btn-primary btn-save">确认</button>
											<button type="button" style="width: 100px;" id="resetContent" class="btn btn-danger">重置</button>
										</div>
									</div>
								</div>
								<br />
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
	    ue.setContent('${userGuide.userGuideContent}', true);
	    
	});
	$("#confirm").click(function(){
		var editorhtml=ue.getContent()
        $('#editorForm').submit();
        
	})
    $("#resetContent").click(function(){
	    ue.setContent('',false);
	    	 
	})  
           
	</script>
	 <script type="text/javascript" src='js/uploadUE.js'></script>
</html>
