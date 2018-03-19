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
				data:'scenicSpotId='+obj+'&state=-1',
				url:'cms/scenicspot/updateState.action',
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
		data:'scenicSpotId='+obja+'&state='+objb,
		url:'cms/scenicspot/updateState.action',
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
		data:'scenicSpotId='+obj,
		url:'cms/scenicspot/checkState.action',
		success:function(data){
			if(data=="success"){
				window.location.href="${basePath}/cms/scenicspot/editJsp.action?scenicSpotId="+obj
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
				<h1 class="page-header">景点列表</h1>
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
                            <div class="row tables-btn-box">
                            	<form action="cms/scenicspot/sceniclist.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                <span class="">选择省份</span>
                                  <select id="provinceId" class="form-control" name="provinceId" required="required">
		                     		<option value="-1">请选择省份</option>
		                     		<c:forEach var="province" items="${provinceList}">
	                   					<option value="${province.provinceId }" <c:if test="${province.provinceId==provinceId }">selected</c:if>>
	                     					${province.provinceName }
		                     			</option>
		                     		</c:forEach>
                     	          </select>
                                </div>
	                            <div class="col-md-2">
	                            <span class="">选择城市</span>
	                               <select id="cityId" class="form-control" name="cityId" required="required">
		                     		<option value="-1">请选择城市</option>
		                     		<c:forEach var="city" items="${cityList}">
	                   					<option value="${city.cityId }" <c:if test="${city.cityId==cityId }">selected</c:if>>
	                     					${city.cityName }
		                     			</option>
		                     		</c:forEach>
	                    	       </select>
	                            </div>
                                <div class="col-md-2">
                                    <span class="">标题</span>
                                    <input placeholder="标题" type="text"  name="scenicName" value="${scenicName }" class="form-control">
                                </div>
                              	</form>
                              		
                           </div>
                           <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                           	<div class="row tables-action-box">
	                              	<div class="col-md-6">
	                              	<shiro:hasPermission name="bannerAddButton">
	                                    <a  class="btn btn-success" href="cms/scenicspot/addJsp.action">添加景点</a>
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
											<th>标题</th>
											<th>省份</th>
											<th>城市</th>
											<th>是否全国显示</th>
											<th>置顶号</th>
											<th>标签</th>
											<th>状态</th>
											<th>所属类型</th>
											<th>创建时间</th>
											<th>修改时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${scenicSpotList }" var="scenic">
											<tr class="odd gradeA">
												<td>${scenic.scenicSpotName }</td>
												<td>${scenic.provinceName }</td>
												<td>${scenic.cityName }</td>
												<c:if test="${scenic.scenicSpotIsAll==0 }">
												<td>否</td>
												</c:if>
												<c:if test="${scenic.scenicSpotIsAll==1 }">
												<td>是</td>
												</c:if>
												<td>${scenic.scenicSpotTopNum }</td>
												<td>${scenic.scenicSpotTag }</td>
												<c:if test="${scenic.scenicSpotState==1 }">
												<td>未上线</td>
												</c:if>
												<c:if test="${scenic.scenicSpotState==2 }">
												<td>已上线</td>
												</c:if>
												<c:if test="${scenic.scenicSpotType==0 }">
												<td>普通景点</td>
												</c:if>
												<c:if test="${scenic.scenicSpotType==1 }">
												<td>骑游景点</td>
												</c:if>
												<td><fmt:formatDate value="${scenic.scenicSpotCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><fmt:formatDate value="${scenic.scenicSpotUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/scenicspot/detail.action?scenicSpotId=${scenic.scenicSpotId}">详情</a>
													<shiro:hasPermission name="bannerEditButton">
													<a class="btn btn-primary"
													href="javascript:void(0)" onclick="checkEdit('${scenic.scenicSpotId }')">修改</a>
													</shiro:hasPermission>
													<shiro:hasPermission name="scenicLineButton">
													<c:if test="${scenic.scenicSpotState==2 }" >
													<a class="btn btn-primary" href="javascript:void(0)" onclick="updateState('${scenic.scenicSpotId }','1')">下线</a>
													</c:if>
													<c:if test="${scenic.scenicSpotState==1 }">
													<a class="btn btn-primary" href="javascript:void(0)" onclick="updateState('${scenic.scenicSpotId }','2')">上线</a>
													</c:if>
													</shiro:hasPermission>
													<shiro:hasPermission name="bannerDelButton">
													<a class="btn btn-danger"  onclick="checkDel('${scenic.scenicSpotId}')" >删除</a>
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

