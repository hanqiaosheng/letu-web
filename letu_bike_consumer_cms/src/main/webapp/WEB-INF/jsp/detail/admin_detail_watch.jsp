<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<script type="text/javascript">
	$(function (){
		$("input[name='ar']").each(function(index, data){
			$("input[name='roleId']").each(function(){
				if($(this).val()==$(data).val()){
					$(this).prop('checked',true);
				}
	        });
		})
	})
</script>
<div id="page-wrapper">
	<c:forEach items="${admin.roles}" var="role">
        <input type="hidden" name="ar" value="${role.roleId }"/>
     </c:forEach>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">查看管理员</h1>
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
							<form role="form" action="cms/admin/addAdmin.action" method="post">
								<div class="form-group">
									<label>用户名：</label>
									<div class="form-group">
										<input class="form-control" placeholder="用户名" name="adminUsername" type="text" value="${admin.adminUsername }"
											required="required" readonly="readonly">
										</div>
										<label>姓名：</label>
										<div class="form-group">
											<input class="form-control" placeholder="姓名" type="text" value="${admin.adminRealname }"
												name="adminRealname" required="required" readonly="readonly">
										</div>
										<c:if test="${admin.channel.channelName!=null }">
										<label>渠道：</label>
										<div class="form-group">
											<input class="form-control" placeholder="渠道" type="text" value="${admin.channel.channelName }"
												   name="adminRealname" required="required" readonly="readonly">
										</div>
										</c:if>
										<label>电话：</label>
										<div class="form-group">
											<input class="form-control" placeholder="电话" type="tel" value="${admin.adminTel }"
												name="adminTel" required="required" readonly="readonly">
										</div>
										<div class="form-group">
                                            <label>角色分配：</label>
                                            <div class="checkbox">
                                            	<c:forEach items="${roleList }" var="role">
	                                            	<label>
	                                                    <input type="checkbox" name="roleId" disabled="disabled" value="${role.roleId }" readonly="readonly">${role.roleName }
	                                                </label>
                                            	</c:forEach>
                                            </div>
                                        </div>
                                        </div>
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
