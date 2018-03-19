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
			_self=this;
			if(type=='yes'){
				$.ajax({
					type:'post',
					data:'modelsId='+obj,
					url:'cms/models/deleteModels.action',
					success:function(data){	
						if(data=="success"){
							window.location.reload();
						}else if(data=="fail"){
							_self.hide();
							$.alert("有车辆绑定该车型，无法删除");
						}
							
					}
				});
			}else if(type=='no'){
				this.hide();
			}
	    }) 	
			
	}
</script>
<!-- <script type="text/javascript">
	    
	    
	    function checkAll(){
	    	if($("#selectAll").is(':checked')){
	    		$(":checkbox").prop("checked",true);
	    	}else{
	    		$(":checkbox").prop("checked",false);
	    	}
	    }
	    function deleteAll(){
	    	var mess = "确认删除吗？";
			$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
				if(type=='yes'){
					$.ajax({
						type:'post',
						data:$('#bikeFixInfoForm').serialize(),
						url:'cms/bikeFixInfo/deleteAllBikeFixInfoDetail.action',
						success:function(data){	
								window.location.reload();
						}
					});
					
				}else if(type=='no'){
					this.hide();
				}
		    }) 	
	    }
    </script> -->
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">车型列表</h1>
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
                           
                            	<form action="cms/models/modelsList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">型号</span>
									<input placeholder="型号" type="text" name="modelsCode" value="${modelsCode }" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">车型</span>
                                    <input placeholder="车型" type="text" name="modelsName" value="${modelsName }" class="form-control">
                                </div>
								<div class="col-md-2">
                                    <span class="">渠道</span>
                                    <select name="channelId" id="channelId" class="form-control">
								<option value="-1">全部渠道</option>
								<c:forEach var="channel" items="${channelList }">
									<option value="${channel.channelId }"  <c:if test="${channel.channelId==channelId }">selected="selected"</c:if>>${channel.channelName }</option>
								</c:forEach>
								</select>
                                </div>                                
                              	</form>
                              	
                            </div>
                            <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                           	<div class="row tables-action-box">
	                              	<div class="col-md-6">
	                              	<shiro:hasPermission name="addModelsButton">
	                                   <a class="btn btn-success" href="cms/models/addModelsJsp.action">添加车型</a>
	                                </shiro:hasPermission>
	                                </div>
                           </div>
                            
                       </div>
                       
                       
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action id="bikeFixInfoForm" method="post">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>车型</th>
											<th>型号</th>
											<th>渠道</th>
											<th>预付款</th>
											<!-- <th>租赁费用</th> -->
											<th>绑定点</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									    <c:forEach var="models" items="${modelsList }">
											<tr class="odd gradeA">
											   
												<td>${models.modelsId }</td>
												<td>${models.modelsName }</td>
												<td>${models.modelsCode }</td>
												<td class="center">${models.channel.channelName }</td>
												<td class="center"><fmt:formatNumber pattern="0.00" value="${models.modelsDeposit }"/></td>
												<%-- <td><fmt:formatNumber pattern="0.00" value="${models.modelsRentPrice }"/></td> --%>
												<td>
												${models.modelsFixedReturnName }
												</td>
												<td class="center">
												<shiro:hasPermission name="modelsUpdateButton">
												<a class="btn btn-primary" href="cms/models/editModelsJsp.action?modelsId=${models.modelsId }">修改</a>
												</shiro:hasPermission>
												<shiro:hasPermission name="modelsDeleteButton ">
												<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${models.modelsId }')">删除</a>
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
	<%@include file="common/maskMap.jsp"%>
	<link rel="stylesheet" href="assets/selectSearch/select2.min.css">
	<script type="text/javascript" src="assets/selectSearch/select2.js"></script>
	<script type="text/javascript">
        $(document).ready(function() {
            $("#channelId").select2();
        })
	</script>
</body>
</html>

