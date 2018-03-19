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
					type:'post',
					data:'inPriceId='+obj,
					url:'cms/insurancePrice/delete.action',
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
				<h1 class="page-header">保险费用列表</h1>
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
                            	<form action="cms/insurancePrice/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">保险费用名称</span>
                                    <input type="text" class="form-control" placeholder="请输入保险费用名称" value="${name}" name="name" >
                                </div>
                              	</form>
                            </div>
                            <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                            <div class="row tables-action-box">
	                              	<div class="col-md-6">
	                              	<shiro:hasPermission name="addInpriceButton">
	                                   <a class="btn btn-success" href="cms/insurancePrice/addJsp.action">添加</a>
	                                </shiro:hasPermission>
	                                </div>
                           </div>
                       </div>
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>保险费用名称</th>
											<th>保险费用</th>
											<th>最近更新时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${insurancePriceList}" var="insurancePrice">
											<tr class="odd gradeA">
												<td>${insurancePrice.inPriceId }</td>
												<td>${insurancePrice.inPriceName }</td>
												<td>${insurancePrice.inPrice }</td>
												<td><fmt:formatDate value='${insurancePrice.inUpdateTime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
												<td class="center">
												<shiro:hasPermission name="editInpriceButton">
												  <a class="btn btn-info" href="cms/insurancePrice/editJsp.action?insurancePriceId=${insurancePrice.inPriceId }">修改</a>
												</shiro:hasPermission>
												<shiro:hasPermission name="delInpriceButton">
												  <a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${insurancePrice.inPriceId }')">删除</a>
												</shiro:hasPermission>
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

