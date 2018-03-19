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
					data:'bikeFixInfoId='+obj,
					url:'cms/bikeFixInfo/deleteBikeFixInfo.action',
					success:function(data){	
							window.location.reload();
					}
				});
			}else if(type=='no'){
				this.hide();
			}
	    }) 	
			
	}
</script>
<script type="text/javascript">
	    
	    
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
	    
	    function checkExcel(){
	    	if($("#pinstructions").val()==""){
	    		$.alert("请上传excel",true,function(){
                },false,{className:'ui-alert',width:300});
	    	}else{
	    		$("#importForm").submit();
	    	}
	    }
    </script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">维护信息</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
					  <%--  <div class="panel-heading clearfix ">
                        <div class="panel-body">
                        	<form id="importForm" action="cms/bikeFixInfo/importBikeFixInfo.action" method="post" enctype="multipart/form-data" 	>
						        	   <div class="col-md-1">
						        	   <shiro:hasPermission name="addBikeMaintainButton">
		                                  <a class="btn btn-success" href="cms/bikeFixInfo/addJsp.action">添加维护信息</a>
		                               </shiro:hasPermission>
                                       </div>
						        		<div class="inputfileli">
								        	<span class="inputfile">
								        	<shiro:hasPermission name="bikeMaintainUploadButton">
			                                	<button class="btn btn-default btn-primary" type="button" onclick="file.init($(this))">上传Excel</button>
			                                </shiro:hasPermission>	
			                                	<input type="file" name="excel" style="display:none" id="pinstructions">
			                                <shiro:hasPermission name="bikeMaintainInputButton">
			                                	<button class="btn btn-success" type="button" onclick="checkExcel()">录入维护信息</button>
		                                	</shiro:hasPermission>
		                                	</span>
	                                	</div>
						       </form>
			        	</div>
                      </div> --%>
                      					
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
                            	<form action="cms/bikeFixInfo/bikeFixInfoList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">车辆编号</span>
                                    <input placeholder="车辆编号" type="text" name="bikecode" value="${bikecode }" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">维护编号</span>
                                    <input placeholder="维护编号" type="text" name="fixBatch" value="${fixBatch }" class="form-control">
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">维护开始时间</span>
                                   	<input placeholder="维护开始时间" id="cStarttime" type="text" name="bikeFixStartTime" value='<fmt:formatDate value="${bikeFixStartTime }" pattern="yyyy-MM-dd"/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="维护开始时间" id="cEndtime" type="text" name="bikeFixStartTimeB" value='<fmt:formatDate value="${bikeFixStartTimeB }" pattern="yyyy-MM-dd"/>' class="form-control">
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">维护结束时间</span>
                                   	<input placeholder="维护结束时间" id="cStarttimeB" type="text" name="bikeFixEndTime" value='<fmt:formatDate value="${bikeFixEndTime }" pattern="yyyy-MM-dd"/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="维护结束时间" id="cEndtimeB" type="text" name="bikeFixEndTimeB" value='<fmt:formatDate value="${bikeFixEndTimeB }" pattern="yyyy-MM-dd"/>' class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">维护人</span>
                                    <input placeholder="维护人" type="text" name="bikeFixPerson" value="${bikeFixPerson }"  class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">维护地点</span>
                                    <input placeholder="维护地点" type="text" name="bikeFixAddress" value="${bikeFixAddress }"  class="form-control go-back">
                                </div>
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action id="bikeFixInfoForm" method="post">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
										    <th></th>
											<th>维护批次编号</th>
											<th>维护人</th>
											<th>维护开始时间</th>
											<th>维护结束时间</th>
											<th>维护地点</th>
											<th>维护车辆编号</th>
											<th>车辆维护详情</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									    <c:forEach var="bikeFixInfo" items="${bikeFixInfoList }">
											<tr class="odd gradeA">
											    <td>
											    <input type="checkbox" name="bikeFixInfoIds" value="${bikeFixInfo.fixId }">
											    </td>
												<td>${bikeFixInfo.fixBatchNumber }</td>
												<td>${bikeFixInfo.fixMan }</td>
												<td class="center"><fmt:formatDate value="${bikeFixInfo.fixStarttime }" pattern="yyyy-MM-dd"/></td>
												<td class="center"><fmt:formatDate value="${bikeFixInfo.fixEndtime }" pattern="yyyy-MM-dd"/></td>
												<td>
												<c:if test="${! empty bikeFixInfo.fixLongitude or ! empty bikeFixInfo.fixAtitude }">
												<a href="cms/bikeFixInfo/bikeFixPosition.action?bikeFixId=${bikeFixInfo.fixId }"><span class="lnglatStr" data-lat="${bikeFixInfo.fixAtitude }" data-lng="${bikeFixInfo.fixLongitude }"></span></a>
												</c:if>
												</td>
												<td>${bikeFixInfo.bike.bikeCode }</td>
												<td><a class="btn btn-info" href="cms/bikeFixInfo/bikeFixInfoDetailList.action?bikeId=${bikeFixInfo.fixBikeId }">详情</a></td>
												<td class="center">
												<shiro:hasPermission name="bikeMaintainDeleteButton">
												<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${bikeFixInfo.fixId }')">删除</a>
												</shiro:hasPermission>
												</td>
											</tr>
									    </c:forEach>
									</tbody>
								</table>
								</form>
								<input  type="checkbox" id="selectAll" onclick="checkAll()" />全选
								<shiro:hasPermission name="bikeMaintainDeleteButton">
								<button class="btn btn-danger" onclick="deleteAll();">批量删除</button>
								</shiro:hasPermission>
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
</body>
</html>

