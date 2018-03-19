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
			<h1 class="page-header">编辑管理员</h1>
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
							<form role="form" action="cms/admin/update.action" method="post">
							<input type="hidden" name="adminId" value="${admin.adminId }">
								<div class="form-group">
									<label>用户名：</label>
									<div class="form-group">
										<input class="form-control" placeholder="用户名" readonly='readonly' name="adminUsername" type="text" value="${admin.adminUsername }" onblur="checkName()"
											required="required">
										</div>
										<label>姓名：</label>
										<div class="form-group">
											<input class="form-control" placeholder="姓名" type="text" value="${admin.adminRealname }"
												name="adminRealname" required="required">
										</div>
										<label>密码：</label>
										<div class="form-group">
											<input class="form-control" placeholder="密码" type="password" value="${admin.adminPwd }"
											id="adminPassword" name="adminPassword" required="required">
										</div>
										<c:choose>
											<c:when test="${empty level||level==null}">
												<div class="form-group">
													<label>渠道：</label>
													<select name="adminChannelId"  class="form-control">
														<option value="-1">超级管理员</option>
														<c:forEach items="${channelList }" var="channel">
															<option value="${channel.channelId }" <c:if test="${admin.channel.channelId==channel.channelId }">selected="selected"</c:if>>${channel.channelName }</option>
														</c:forEach>
													</select>
												</div>
											</c:when>
											<c:otherwise>
												<input type="hidden" value="${level }" name="adminChannelId">
											</c:otherwise>
										</c:choose>
										<label>电话：</label>
										<div class="form-group">
											<input class="form-control" maxlength="11" id="mobile"  placeholder="电话" type="tel" value="${admin.adminTel }"
												name="adminTel" required="required">
										</div>
										<div class="form-group">
                                            <label>角色分配：</label>
                                            <div class="checkbox">
                                            	<c:forEach items="${roleList }" var="role">
	                                            	<label>
	                                                    <input type="checkbox" name="roleId" value="${role.roleId }">${role.roleName }
	                                                </label>
                                            	</c:forEach>
                                            </div>
                                        </div>
                                        <div class="form-group" id="companyHtml">
                                        </div>
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
<script type="text/javascript">



</script>
<%@include file="../common/buttom.jsp"%>
<link rel="stylesheet" href="assets/selectSearch/select2.min.css">
<script type="text/javascript" src="assets/selectSearch/select2.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#channelId").select2();
    })
</script>
</html>
