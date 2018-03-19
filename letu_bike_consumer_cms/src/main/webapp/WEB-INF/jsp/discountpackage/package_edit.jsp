<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">修改横幅</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form role="form" action="cms/package/edit.action" method="post">
							<input type="hidden" value="${discountPackage.discountPackageId }" name="discountPackageId">
								<div class="form-group">
								    <label>景点门票：</label><br>
									<div class="form-group">
										<input class="form-control" value="${scenicSpot.scenicSpotName }" placeholder="景点门票" type="text" readonly="readonly">
								    </div>
								    <br>
								    <br>
									<label>套餐标题：</label><br>
									<div class="form-group">
										<input id="discountPackageName" class="form-control" value="${discountPackage.discountPackageName }" placeholder="套餐标题"  name="discountPackageName"  type="text"
											required="required">
								    </div>
								    <br>
								    <br>
								    <label>置顶号：</label><br>
									<div class="form-group">
										<input id="discountPackageTopNum" value="${discountPackage.discountPackageTopNum }" class="form-control" placeholder="置顶号"  name="discountPackageTopNum"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>套餐标签（逗号分隔）：</label><br>
									<div class="form-group">
										<input id="discountPackageTag" class="form-control" value="${discountPackage.discountPackageTag }" placeholder="套餐标签"  name="discountPackageTag"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>套餐价格（元）：</label><br>
									<div class="form-group">
										<input id="discountPackagePrice" class="form-control" value="${discountPackage.discountPackagePrice }" placeholder="套餐价格"  name="discountPackagePrice"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>套餐优惠价格（元）：</label><br>
									<div class="form-group">
										<input id="discountPackagePreferentialPrice" class="form-control" value="${discountPackage.discountPackagePreferentialPrice }" placeholder="套餐优惠价格"  name="discountPackagePreferentialPrice"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>购买时间限制（天）：</label><br>
									<div class="form-group">
										<input id="discountPackageBuyLimit" class="form-control" value="${discountPackage.discountPackageBuyLimit }" placeholder="购买时间限制"  name="discountPackageBuyLimit"  type="text"
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
									<input type="submit" value="提交" class="btn btn-primary">
									<a class="btn btn-danger" href="javascript:history.go(-1)">返回</a>
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
	    ue.setContent('${discountPackage.discountPackageDetail}', true);
	    
	});
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