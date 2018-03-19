<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<script type="text/javascript">
	function checkDel(obj){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				location.href="${basePath}cms/admin/deleteAdmin.action?adminId="+obj;
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
	
	function editState(objA,objB){
		if(objB==-1){
			var mess = "确认删除吗？";
		}else if(objB==0){
			var mess = "确认冻结吗？";
		}else if(objB==1){
			var mess = "确认解冻吗？";
		}
		
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				$.ajax({
					type:'post',
					data:'adminId='+objA+'&adminState='+objB,
					url:'cms/admin/frozenAdmin.action',
					success:function(data){
						if(data!=""){
							window.location.reload();
						}
					}
				});
			}else if(type=='no'){
				this.hide();
			}
		
	})
	}
		
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">管理员列表</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading clearfix ">
                            <div class="row">
                                <div class="caption font-red-intense col-md-12">
                                    <i class="fa fa-search font-red-intense"></i>
                                    <span class="caption-subject bold uppercase">查询</span>
                                    <button class="btn btn-default hide-search pull-right" type="button">隐藏</button>
                                </div>
                            </div>
                            <br>
                            <div class="row tables-btn-box">
                            	<form action="cms/admin/adminList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">管理员名</span>
                                    <input placeholder="用户名" type="text"  name="adminname" value="${adminname }" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">角色</span>
                                    <select name="roleId" id="roleId" class="form-control">
									<option value="">全部角色</option>
									<c:forEach items="${roleList }" var="role">
										<option value="${role.roleId }" <c:if test="${role.roleId==roleId }">selected="selected"</c:if>>${role.roleName }</option>
									</c:forEach>
								</select>
                                </div>
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                          <a style="margin-top: 10px" class="btn btn-success" href="cms/admin/gotoAdminAdd.action">添加</a>
                       </div>
                         
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>管理员名</th>
											<th>渠道名</th>
											<th>角色</th>
											<th>姓名</th>
											<th>邮箱</th>
											<th>电话</th>
											<th>创建时间</th>
											<th>状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${adminList }" var="admin">
											<tr class="odd gradeA">
												<td>${admin.adminUsername }</td>
												<td>${admin.channel.channelName }<c:if test="${admin.channel.channelName==null }">超级管理员</c:if></td>
												<td>
												<c:forEach items="${admin.roles }" var="role">
												${role.roleName }&nbsp;&nbsp;&nbsp;&nbsp;
												</c:forEach>
												</td>
												<td>${admin.adminRealname }</td>
												<td>${admin.adminCreateEmail }</td>
												<td class="center">${admin.adminTel }</td>
												<td class="center"><fmt:formatDate value="${admin.adminRegTime }" pattern="yyyy-MM-dd"/></td>
												<td class="center">
												<c:if test="${admin.adminState == 1 }">
													使用中
												</c:if> <c:if test="${admin.adminState == 0 }">
													已冻结
												</c:if>
												</td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/admin/adminDetail.action?adminId=${admin.adminId }&flag=2">查看</a>
													<a class="btn btn-primary"
													href="cms/admin/adminDetail.action?adminId=${admin.adminId }&flag=1">编辑</a>
													<c:if test="${admin.adminState == 1 }">
													<a class="btn btn-warning" onclick="editState('${admin.adminId }','0')"
														href="javascript:void(0)">冻结</a>
													</c:if> <c:if test="${admin.adminState == 0 }">
													<a class="btn btn-warning" onclick="editState('${admin.adminId }','1')"
														href="javascript:void(0)">解冻</a>
													</c:if>	
													<a class="btn btn-danger" onclick="editState('${admin.adminId }','-1')"
														href="javascript:void(0)">删除</a>										
												</td>
											</tr>
											</c:forEach>
									</tbody>
								</table>
							</div>
						<%@include file="common/pageUtil.jsp"%>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
	</div>
	<%@include file="common/buttom.jsp"%>
</body>
</html>

