<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<script type="text/javascript">
  function toCheck(objA,objB){
	  $.ajax({
			url:"${basePath}/cms/fixedReturn/toCheck.action",
			type:'post',
			data:"fixedReturnId="+objA+"&state="+objB,
			success:function(data){
				if(data=="success"){
					window.location.reload();
				}
			}
		});
  }
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
				<h1 class="page-header">还车点审核列表</h1>
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
                            	<form action="cms/fixedReturn/fixedCheckList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">发起人名称</span>
                                   <input placeholder="发起人名称" type="text" name="name" value="${name }" class="form-control">
                                </div>
                              	</form>
                              		
                           </div>
                           <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
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
											<th>发起人名称</th>
											<th>定位点</th>
											<th>发起时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${fixedCheckList }" var="fixedReturn" varStatus="varStatus">
											<tr class="odd gradeA">
												<td>${varStatus.index+1 }</td>
												<td>${fixedReturn.fixedReturnOriginator }</td>
												<td>
												<a href="cms/fixedReturn/fixedPosition.action?fixedReturnId=${fixedReturn.fixedReturnId }"><span class="lnglatStr" data-lat="${fixedReturn.fixedReturnLat}" data-lng="${fixedReturn.fixedReturnLng}"></span></a>
												</td>
												<td class="center"><fmt:formatDate value="${fixedReturn.fixedReturnFixedTime }" pattern="yyyy-MM-dd HH:mm"/></td>
												<td class="center">
												        <c:if test="${fixedReturn.fixedReturnState==2 }">
														<a class="btn btn-info"
														href="cms/fixedReturn/perfectDetailJsp.action?fixedReturnId=${fixedReturn.fixedReturnId }">完善</a>
														<a onclick="toCheck('${fixedReturn.fixedReturnId }',3)" class="btn btn-primary">驳回</a>
														</c:if>
														<c:if test="${fixedReturn.fixedReturnState==3 }">
														 <span style="color:blue;">已驳回</span> 
														</c:if>
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
	<%@include file="common/maskMap.jsp"%>
</body>
</html>

