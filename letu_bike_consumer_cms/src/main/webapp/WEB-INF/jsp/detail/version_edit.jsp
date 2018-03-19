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
			<h1 class="page-header">修改版本号</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a class="btn btn-danger" href="javascript:history.go(-1)">返回</a>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form  action="cms/app/editVersion.action" method="post"  >
								<input type="hidden" value="${sysParament.sysParamentId }" name="sysParamentId">
								<div class="form-group">
									app平台：${sysParament.sysParamentName }
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="版本号" value="${sysParament.sysParamentValue }" type="text"  name="sysParamentValue" required="required">
								</div>
								<div class="form-group">
									<input type="radio" <c:if test="${sysParament.sysParamentIsUpdate==0 }">checked</c:if> name= "sysParamentIsUpdate" value="0">否 
									<input type="radio" <c:if test="${sysParament.sysParamentIsUpdate==1 }">checked</c:if> name= "sysParamentIsUpdate" value="1">是
								</div>
								<div class="form-group">
								<label>更新内容</label>
								<textarea class="form-control" name="sysParamentUpdateContent" rows="" cols="">${sysParament.sysParamentUpdateContent }</textarea>
								</div>
                           		<div style="text-align: center;">
									<button id="checkForm" type="submit" value="提交" class="btn btn-primary" >提交</button>
								</div>
                            </form>
                       </div>
					</div>
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

	<!-- /.row -->

<%@include file="../common/buttom.jsp"%>
</html>
