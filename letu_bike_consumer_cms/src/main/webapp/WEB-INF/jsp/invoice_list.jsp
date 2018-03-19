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
				if(obj!=''){
					$.ajax({
						url:"cms/invoice/update.action?invoiceState=-1&invoiceId="+obj,
						type:'post',
						success:function(data){
							if(data!=""){
								window.location.reload();
							}
						}
					});
				}else{
					deleteAll();
				}
				
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
  function send(obj){
		var mess = "确认发货吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				if(obj!=''){
					$.ajax({
						url:"cms/invoice/update.action?invoiceState=2&invoiceId="+obj,
						type:'post',
						success:function(data){
							if(data!=""){
								window.location.reload();
							}
						}
					});
				}else{
					deleteAll();
				}
				
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
  function checkAll() {
  	if($("#checkAllBox").val()=='1'){
  		$(":checkbox").prop("checked",true);
  		$("#checkAllBox").val("0");
  	}else if($("#checkAllBox").val()=='0'){
  		$(":checkbox").prop("checked",false);
  		$("#checkAllBox").val("1");
  	}
	}
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">发票列表</h1>
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
                            	<form action="cms/invoice/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <%-- <div class="col-md-2" >
                                    <span class="">发票类型</span>
                                    <select class="form-control" name="invoiceType" value="${ invoiceType}" id="invoiceType">
                                    <option value="-1">请选择发票类型</option>
                                    <option value="1" <c:if test="${invoice.invoiceType==1 }">selected="selected"</c:if>>电子发票</option>
                                    <option value="2" <c:if test="${invoice.invoiceType==2 }">selected="selected"</c:if>>纸质发票</option>
                                    </select>
                                </div> --%>
                                <div class="col-md-2">
                                    <span class="">状态</span>
                                    <select class="form-control" name="invoiceState" value="${invoiceState }" id="invoiceState">
                                      <option value="-2">请选择状态</option>
                                      <option value="0"<c:if test="${invoice.invoiceState==0 }">selected="selected"</c:if>>未处理</option>
                                      <option value="1"<c:if test="${invoice.invoiceState==1 }">selected="selected"</c:if>>待发货</option>
                                      <option value="2"<c:if test="${invoice.invoiceState==2 }">selected="selected"</c:if>>已发货</option>
                                      <%-- <option value="3"<c:if test="${invoice.invoiceState==3 }">selected="selected"</c:if>>待开票</option>
                                      <option value="4"<c:if test="${invoice.invoiceState==4 }">selected="selected"</c:if>>已开票</option> --%>
                                      <option value="5"<c:if test="${invoice.invoiceState==5 }">selected="selected"</c:if>>未激活</option>
                                      
                                    </select>
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">申请时间</span>
                                   	<input placeholder="开始时间" id="cStarttime" type="text" name="fromTime" value="${invoice.fromTime}"  class="form-control">
                                    <span class="line">-</span><input placeholder="结束时间" id="cEndtime" type="text" name="toTime" value="${invoice.toTime}" class="form-control">
                                </div>  
                              	</form>
                            </div>
                            <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
										   <!--  <th></th> -->
											<th>申请编号</th>
											<th>发票类型</th>
											<th>申请人</th>
											<th>申请时间</th>
											<th>状态</th>
											<th>操作</th>
											<!-- <th>IP地址</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${invoiceList}" var="invoice">
											<tr class="odd gradeA">
											   <%--  <td>
												<input type="checkbox" name="invoiceIds" value="${invoice.invoiceId }">
												</td> --%>
												<td>${invoice.invoiceId }</td>
												<td>
													<c:if test="${invoice.invoiceType==1 }">
														电子发票
													</c:if>
													<c:if test="${invoice.invoiceType==2 }">
														纸质发票
													</c:if>
												</td>
												<td>${invoice.user.userRealname }</td>
												<td class="center"><fmt:formatDate value='${invoice.invoiceApplicationTime }' pattern='yyyy-MM-dd HH:mm:ss'/></td>
												<td>
													<c:if test="${invoice.invoiceState==0 }">
														<span style="color:orange;">未处理</span>
													</c:if>
													<c:if test="${invoice.invoiceState==1 }">
														<span style="color:blue">待发货</span>
													</c:if>
													<c:if test="${invoice.invoiceState==2 }">
														<span style="color:green">已发货</span>
													</c:if>
													<%-- <c:if test="${invoice.invoiceState==3 }">
														待开票
													</c:if>
													<c:if test="${invoice.invoiceState==4 }">
														已开票
													</c:if> --%>
													<c:if test="${invoice.invoiceState==5 }">
														<span style="color:gray;">未激活</span>
													</c:if>
												</td>
												<td class="center">
												<shiro:hasPermission name="invoiceListMng">
													<a class="btn btn-primary"
															href="cms/invoice/detail.action?invoiceId=${invoice.invoiceId }" >详情</a>
												</shiro:hasPermission>				
													<c:if test="${invoice.invoiceType == 2 && invoice.invoiceState ==0}">
												<shiro:hasPermission name="invoiceListMng">	
														<a class="btn btn-warning"
															href="cms/invoice/editJsp.action?invoiceId=${invoice.invoiceId }" onclick="">处理</a>
												</shiro:hasPermission>			
													</c:if>
													<c:if test="${invoice.invoiceType == 2 && invoice.invoiceState ==1}">
												<shiro:hasPermission name="invoiceListMng">	
														<a class="btn btn-warning"
															href="javascript:void(0)" onclick="send('${invoice.invoiceId}')">发货</a>
												</shiro:hasPermission>			
													</c:if> 
													<c:if test="${invoice.invoiceState !=-1}">
												<shiro:hasPermission name="invoiceListMng">
														<a class="btn btn-danger"
															href="javascript:void(0)" onclick="checkDel('${invoice.invoiceId}')">删除</a>
												</shiro:hasPermission>			
													</c:if>
												</td>
												
												<%-- <td>${shortMsg.codeIp }</td> --%>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<%-- <input id="checkAllBox" type="hidden" value="1" />
							    <input  type="checkbox"  onclick="checkAll();" />全选
							    <shiro:hasPermission name="userDeleteButton">
							    <button class="btn btn-danger" onclick="checkDel('');">批量删除</button>
							    </shiro:hasPermission> --%>
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

