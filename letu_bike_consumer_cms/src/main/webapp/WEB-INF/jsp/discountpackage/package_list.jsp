<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<%@include file="../common/datePicker.jsp"%>
<script type="text/javascript">
function checkDel(obj){
	var mess = "确认删除吗？";
	$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
		if(type=='yes'){
			$.ajax({
				type:'post',
				data:'packageId='+obj+'&state=-1',
				url:'cms/package/updateState.action',
				success:function(data){
					if(data=="success"){
						window.location.reload();
					}else if(data=="fail"){
						$.alert("方案已上线，无法删除，请先下线!",true,function(){
			            },false,{className:'ui-alert',width:300});
					}
				}
			});
		}else if(type=='no'){
			this.hide();
		}
    }) 
}

function updateState(obja,objb){
	$.ajax({
		type:'post',
		data:'packageId='+obja+'&state='+objb,
		url:'cms/package/updateState.action',
		success:function(data){
			if(data=="success"){
				window.location.reload();
			}
		}
	});
}

function checkEdit(obj){
	$.ajax({
		type:'post',
		data:'packageId='+obj,
		url:'cms/package/checkState.action',
		success:function(data){
			if(data=="success"){
				window.location.href="${basePath}/cms/package/editJsp.action?packageId="+obj
			}else if(data=="fail"){
				$.alert("已上线，无法修改，请先下线!",true,function(){
	            },false,{className:'ui-alert',width:300});
			}
		}
	});
}
</script>
<body>
	<%@include file="../common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">套餐列表</h1>
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
                            	<form action="cms/package/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                <span class="">所属景点</span>
                                  <select id="scenicId" class="form-control" name="scenicId" required="required">
		                     		<option value="-1">请选择景点</option>
		                     		<c:forEach var="scenic" items="${scenicSpotList}">
	                   					<option value="${scenic.scenicSpotId }" <c:if test="${scenic.scenicSpotId==scenicId }">selected</c:if>>
	                     					${scenic.scenicSpotName }
		                     			</option>
		                     		</c:forEach>
                     	          </select>
                                </div>
	                            <div class="col-md-2">
	                            <span class="">套餐状态</span>
	                               <select id="state" class="form-control" name="state" required="required">
		                     		<option value="-2">请选择状态</option>
		                     		<option value="1"<c:if test="${state==1 }">selected</c:if>>未上线</option>
		                     		<option value="2"<c:if test="${state==2 }">selected</c:if>>已上线</option>
	                    	       </select>
	                            </div>
                                <div class="col-md-2">
                                    <span class="">名称</span>
                                    <input placeholder="名称" type="text"  name="packageName" value="${packageName }" class="form-control">
                                </div>
                              	</form>
                              		
                           </div>
                           <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                           	<div class="row tables-action-box">
	                              	<div class="col-md-6">
	                              	<shiro:hasPermission name="packageAddButton">
	                                    <a  class="btn btn-success" href="cms/package/addJsp.action">添加套餐</a>
	                                </shiro:hasPermission>    
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
											<th>标题</th>
											<th>所属景点</th>
											<th>置顶号</th>
											<th>状态</th>
											<th>标签</th>
											<th>价格</th>
											<th>优惠价格</th>
											<th>创建时间</th>
											<th>修改时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${packageList }" var="discountpackage">
											<tr class="odd gradeA">
											    <td>${discountpackage.discountPackageId }</td>
												<td>${discountpackage.discountPackageName }</td>
												<td>${discountpackage.scenicName }</td>
												<td>${discountpackage.discountPackageTopNum }</td>
												<c:if test="${discountpackage.discountPackageState==1 }">
												<td>未上线</td>
												</c:if>
												<c:if test="${discountpackage.discountPackageState==2 }">
												<td>已上线</td>
												</c:if>
												<td>${discountpackage.discountPackageTag }</td>
												<td><fmt:formatNumber pattern="0.00" value="${discountpackage.discountPackagePrice }"/></td>
												<td><fmt:formatNumber pattern="0.00" value="${discountpackage.discountPackagePreferentialPrice }"/></td>
												<td><fmt:formatDate value="${discountpackage.discountPackageCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><fmt:formatDate value="${discountpackage.discountPackageUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/package/detail.action?packageId=${discountpackage.discountPackageId}">详情</a>
													<shiro:hasPermission name="packageEditButton">
													<a class="btn btn-primary"
													href="javascript:void(0)" onclick="checkEdit('${discountpackage.discountPackageId }')">修改</a>
													</shiro:hasPermission>
													<shiro:hasPermission name="packageLineButton">
													<c:if test="${discountpackage.discountPackageState==2 }" >
													<a class="btn btn-primary" href="javascript:void(0)" onclick="updateState('${discountpackage.discountPackageId }','1')">下线</a>
													</c:if>
													<c:if test="${discountpackage.discountPackageState==1 }">
													<a class="btn btn-primary" href="javascript:void(0)" onclick="updateState('${discountpackage.discountPackageId }','2')">上线</a>
													</c:if>
													</shiro:hasPermission>
													<shiro:hasPermission name="packageDelButton">
													<a class="btn btn-danger"  onclick="checkDel('${discountpackage.discountPackageId}')" >删除</a>
													</shiro:hasPermission>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							
							<%@include file="../common/pageUtil.jsp"%>
							
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
	</div>

	<%@include file="../common/buttom.jsp"%>
</body>
</html>

