<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<script type="text/javascript">
  function deleteRole(obj){
	  var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				 $.ajax({
						type:'post',
						data:'roleId='+obj,
						url:'cms/role/deleteRole.action',
						success:function(data){
							if(data.message!=null){
								$.confirm(data.message,[{yes:"确定"}],function(type,e){
									if(type=='yes'){
										window.location.reload();
									}
								}) 
							}else{
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
				<h1 class="page-header">角色管理</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form action="cms/role/roleJsp.action" method="post" id="searchForm">
								<input type="hidden" id="pageIndex" name="pageIndex">
							</form>
								<a class="btn btn-success" href="cms/role/roleAddJsp.action">添加</a>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>角色编号</th>
											<th>角色名</th>
											<th>角色状态</th>
											<th>权限数量</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${roleList }" var="role">
											<tr class="odd gradeA">
												<td>${role.roleId }</td>
												<td>${role.roleName }</td>
												<td><c:if test="${role.roleState == 1 }">
													使用中
												</c:if> <c:if test="${role.roleState == 0 }">
													已冻结
												</c:if>
												<c:if test="${role.roleState == 2 }">
													一级和二级渠道无法使用
												</c:if> <c:if test="${role.roleState == 3 }">
													二级渠道无法使用
												</c:if>
												</td>
												<td class="center">${role.roleHasnum }</td>
												<td class="center">
														<a class="btn btn-info"
														href="cms/role/roleDetail.action?roleId=${role.roleId }&flag=1">查看</a>
														<a class="btn btn-primary"
														href="cms/role/roleDetail.action?roleId=${role.roleId }&flag=2">编辑</a>
														<c:if test="${role.roleState == 1 }">
														<a class="btn btn-warning"
															href="cms/role/roleFrozen.action?roleId=${role.roleId }&roleState=0">冻结</a>
														</c:if> <c:if test="${role.roleState == 0 }">
														<a class="btn btn-warning"
															href="cms/role/roleFrozen.action?roleId=${role.roleId }&roleState=1">解冻</a>
														</c:if>
														<a class="btn btn-danger" onclick="deleteRole('${role.roleId }')"
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

