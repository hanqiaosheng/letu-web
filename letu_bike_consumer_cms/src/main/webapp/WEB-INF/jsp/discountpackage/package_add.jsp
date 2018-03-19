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
			<h1 class="page-header">添加套餐</h1>
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
							<form role="form" action="cms/package/add.action" method="post" >
								<div class="form-group">
								   <label>景点门票：</label><br>
								   <div class="form-group">
									   <select id="discountPackageScenicSpotId" class="form-control" name="discountPackageScenicSpotId" required="required">
			                     		<c:forEach var="scenic" items="${scenicSpotList}">
		                   					<option value="${scenic.scenicSpotId }">
		                     					${scenic.scenicSpotName }
			                     			</option>
			                     		</c:forEach>
		                    	       </select>
								    </div>
									<br>
									<br>
									<label>套餐标题：</label><br>
									<div class="form-group">
										<input id="discountPackageName" class="form-control" placeholder="套餐标题"  name="discountPackageName"  type="text"
											required="required">
								    </div>
								    <br>
								    <br>
								    <label>置顶号：</label><br>
									<div class="form-group">
										<input id="discountPackageTopNum" class="form-control" placeholder="置顶号"  name="discountPackageTopNum"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>套餐标签（逗号分隔，四字以内）：</label><br>
									<div class="form-group">
										<input id="discountPackageTag" class="form-control" placeholder="套餐标签"  name="discountPackageTag"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>套餐价格（元）：</label><br>
									<div class="form-group">
										<input id="discountPackagePrice" class="form-control" placeholder="套餐价格"  name="discountPackagePrice"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>套餐优惠价格（元）：</label><br>
									<div class="form-group">
										<input id="discountPackagePreferentialPrice" class="form-control" placeholder="套餐优惠价格"  name="discountPackagePreferentialPrice"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>购买时间限制（天）：</label><br>
									<div class="form-group">
										<input id="discountPackageBuyLimit" class="form-control" placeholder="购买时间限制"  name="discountPackageBuyLimit"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>详细说明：</label>
									<div class="form-group" id='brief'>
										<!-- <label class="col-md-2 control-label"></label>
										<div class="col-md-8"> -->
											<script id="editor" type="text/plain" name="discountPackageDetail" style="height: 400px;"></script>
										<!-- </div> -->
									</div>
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
	function gotoAction(){
		if($("#discountPackageName").val()==''){
			 $.alert('套餐标题不能为空')
			 return;
		}
		if($("#discountPackageTag").val()==''){
			 $.alert('套餐标签不能为空')
			 return;
		}
		if($("#discountPackagePrice").val()==''){
			 $.alert('套餐价格不能为空')
			 return;
		}
		if($("#discountPackagePreferentialPrice").val()==''){
			 $.alert('套餐优惠价格不能为空')
			 return;
		}
		if($("#discountPackageBuyLimit").val()==''){
			 $.alert('购买时间限制不能为空')
			 return;
		}
		
		var editorhtml=ue.getContent()
		if(editorhtml!=''){
	         $('form').submit();
	    }else{
	         $.alert('详细说明不能为空')
	    }
	}
	
	</script>
	<script type="text/javascript" src='js/uploadUE.js'></script>
</html>