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
			$.ajax({
				url:"${basePath}/cms/fixedReturn/fixedReturnDelete.action?fixedReturnId="+obj,
				type:'post',
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
				<h1 class="page-header">还车点列表</h1>
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
                            	<form action="cms/fixedReturn/fixedReturnList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">还车点名称</span>
                                   <input placeholder="还车点名称" type="text" name="name" value="${name }" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">渠道名</span>
                                    <input placeholder="渠道名" type="text"  name="channelName" value="${channelName }" class="form-control">
                                </div>
                              	</form>
                              		
                           </div>
                           <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                           	<div class="row tables-action-box">
	                              	<div class="col-md-6">
	                                    <a  class="btn btn-success" href="cms/fixedReturn/fixedReturnAddJsp.action">创建还车点</a>
	                                </div>
                           </div>
                       </div>
                       
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTab
							le_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>还车点名称</th>
											<th>渠道名</th>
											<th>简介</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${fixedReturnList }" var="fixedReturn">
											<tr class="odd gradeA">
												<td>${fixedReturn.fixedReturnId }</td>
												<td>${fixedReturn.fixedReturnName }</td>
												<td>${fixedReturn.channel.channelName }</td>
												<td>${fixedReturn.fixedReturnBrief }</td>
												<td class="center">
														<a class="btn btn-info"
														href="cms/fixedReturn/fixedReturnDetail.action?fixedReturnId=${fixedReturn.fixedReturnId }">详情</a>
														<a class="btn btn-primary"
														href="cms/fixedReturn/fixedReturnEditJsp.action?fixedReturnId=${fixedReturn.fixedReturnId }">编辑</a>
														<a class="btn btn-danger"  onclick="checkDel('${fixedReturn.fixedReturnId }')" >删除</a>
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

