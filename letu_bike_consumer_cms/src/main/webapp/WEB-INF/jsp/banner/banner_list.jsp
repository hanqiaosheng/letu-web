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
				url:"${basePath}/cms/banner/delete.action?bannerId="+obj,
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
	<%@include file="../common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">横幅列表</h1>
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
                            	<form action="cms/banner/list.action" method="post" id="searchForm">
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
                                    <input placeholder="标题" type="text"  name="bannerName" value="${bannerName }" class="form-control">
                                </div>
                              	</form>
                              		
                           </div>
                           <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                           	<div class="row tables-action-box">
	                              	<div class="col-md-6">
	                              	<shiro:hasPermission name="bannerAddButton">
	                                    <a  class="btn btn-success" href="cms/banner/addJsp.action">添加横幅</a>
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
											<th>所属省份</th>
											<th>所属城市</th>
											<th>是否全国显示</th>
											<th>置顶号</th>
											<th>创建时间</th>
											<th>修改时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${bannerList }" var="banner">
											<tr class="odd gradeA">
											    <td>${banner.bannerId }</td>
												<td>${banner.bannerName }</td>
												<td>${banner.provinceName }</td>
												<td>${banner.cityName }</td>
												<c:if test="${banner.bannerIsAll==0 }">
												<td>否</td>
												</c:if>
												<c:if test="${banner.bannerIsAll==1 }">
												<td>是</td>
												</c:if>
												<td>${banner.bannerTopNum }</td>
												<td><fmt:formatDate value="${banner.bannerCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><fmt:formatDate value="${banner.bannerUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/banner/detail.action?bannerId=${banner.bannerId}">详情</a>
													<shiro:hasPermission name="bannerEditButton">
													<a class="btn btn-primary"
													href="cms/banner/editJsp.action?bannerId=${banner.bannerId}">修改</a>
													</shiro:hasPermission>
													<shiro:hasPermission name="bannerDelButton">
													<a class="btn btn-danger"  onclick="checkDel('${banner.bannerId}',-1)" >删除</a>
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

