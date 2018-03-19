<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<%@include file="common/datePicker.jsp"%>
<script type="text/javascript">
    function checkDel(obj){
        var mess = "确认删除吗？";
        $.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
            if(type=='yes'){
                $.ajax({
                    url:"${basePath}/cms/guideAgency/delete.action?guideAgencyId="+obj,
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
			<h1 class="page-header">旅行社列表</h1>
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
							<form action="cms/guideAgency/list.action" method="post" id="searchForm">
								<input type="hidden" id="pageIndex" name="pageIndex">
								<div class="col-md-2">
									<span class="">旅行社名称</span>
									<input placeholder="旅行社名称" type="text" name="guideAgencyName" value="${guideAgency.guideAgencyName }" class="form-control">
								</div>
							</form>
						</div>
						<br>
						<button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
						<div class="row tables-action-box">
							<div class="col-md-6">
								<shiro:hasPermission name="guideAgencyAddButton">
									<a  class="btn btn-success" href="cms/guideAgency/addJsp.action">添加旅行社</a>
								</shiro:hasPermission>
							</div>
						</div>
					</div>


					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<form action id="bikeForm" method="post">
								<input type="hidden" id="bikeState" name="bikeState">
								<table id="table" class="table table-striped table-bordered table-hover"
									   id="dataTables-example">
									<thead>
									<tr>
										<th></th>
										<th>旅行社名称</th>
										<th>总骑行人次</th>
										<th>负责人</th>
										<th>创建人</th>
										<th>备注</th>
										<th width="200px">操作</th>
									</tr>
									</thead>
									<tbody>
									<c:forEach items="${guideAgencyList }" var="guideAgency" >
										<tr class="odd gradeA">
											<td>
												<input type="checkbox" name="guideAgencyIds" value="${guideAgency.guideAgencyId }">
											</td>
											<td>${guideAgency.guideAgencyName }</td>
											<td>
												<c:choose>
													<c:when test="${guideAgency.guideAgencyRidingCount==null||guideAgency.guideAgencyRidingCount<0}">0</c:when>
													<c:otherwise>${guideAgency.guideAgencyRidingCount}</c:otherwise>
												</c:choose>
											</td>
											<td>${guideAgency.principalAdmin.adminRealname}</td>
											<td>${guideAgency.creatorAdmin.adminRealname}</td>
											<td>${guideAgency.guideAgencyRemark}</td>
											<td class="center">
												<a class="btn btn-info"
												   href="cms/guideAgency/detail.action?guideAgencyId=${guideAgency.guideAgencyId}">详情</a>

												<shiro:hasPermission name="guideAgencyEditButton">
													<a class="btn btn-primary" href="cms/guideAgency/editJsp.action?guideAgencyId=${guideAgency.guideAgencyId }" >修改</a>
												</shiro:hasPermission>

												<shiro:hasPermission name="guideAgencyDelButton">
													<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${guideAgency.guideAgencyId}',-1)">删除</a>
												</shiro:hasPermission>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</form>
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


