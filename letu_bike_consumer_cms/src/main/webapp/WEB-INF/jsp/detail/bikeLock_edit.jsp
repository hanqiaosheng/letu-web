<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript">
</script>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">车锁信息修改</h1>
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
							<form  action="cms/lock/edit.action" method="post" >
								<input type="hidden" value="${bikeLockInfo.bikeLockId }" name="bikeLockId">
								<div class="form-group">
									锁编号：${bikeLockInfo.bikeLockCode }
								</div>
								<div class="form-group">
									锁关联车辆编号：<input class="form-control" name="bikeCode" placeholder="锁关联车辆编号" value="${bikeLockInfo.bikeCode }"  type="text">
								</div>
								<div class="form-group">
								 <label>是否使用电子围栏：</label>
									<input type="radio"<c:if test="${bikeLockInfo.bikeLockIsFence==1 }">checked="checked"</c:if> name="bikeLockIsFence" value="1"> 是
							        <input type="radio"<c:if test="${bikeLockInfo.bikeLockIsFence==0 }">checked="checked"</c:if> name="bikeLockIsFence" value="0"> 否
								</div>
                           		<div style="text-align: center;">
									<input type="submit" value="提交" class="btn btn-primary">
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
