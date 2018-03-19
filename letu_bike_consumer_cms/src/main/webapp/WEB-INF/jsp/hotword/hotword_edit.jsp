<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">修改热词</h1>
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
							<form role="form" action="cms/hotword/edit.action" method="post">
							<input type="hidden" value="${hotWord.hotWordId }" name="hotWordId">
								<div class="form-group">
									<label>热搜词语：</label>
									<div class="form-group">
										<input class="form-control" placeholder="热搜词语" value="${hotWord.hotWordName }" name="hotWordName"  type="text"
											required="required">
								    </div>
									<br>
									<label>置顶号：</label>
									<div class="form-group">
										<input class="form-control" placeholder="置顶号" value="${hotWord.hotWordTopNum }" name="hotWordTopNum"  type="text"
											required="required">
								    </div>
									<br>
									<label>热词说明：</label><br>
									<div class="form-group">
										<textarea id="hotWordBrief" name="hotWordBrief" cols="100" rows="10">${hotWord.hotWordBrief }</textarea>
								    </div>
									<br>
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
<%@include file="../common/buttom.jsp"%>
</html>