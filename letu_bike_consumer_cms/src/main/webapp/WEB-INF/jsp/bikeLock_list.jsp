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
					url:"${basePath}cms/lock/update.action?bikeLockDel=-1&bikeLockId="+obj,
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
	
   var selectedOp="${bikeLockInfo.bikeLockStatus}";
    $(document).ready(function(){
    	if(selectedOp=='0')
    		$("#selectedState option[value='0']").attr("selected",true);
    	if(selectedOp=='1')
    		$("#selectedState option[value='1']").attr("selected",true);
   	});
</script>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">车锁列表</h1>
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
                          	<form action="cms/lock/list.action?flag=0" id="searchForm" method="post">
                            <div class="row tables-btn-box">
                            	<input type="hidden" name="pageIndex" id="pageIndex">
                            	<input type="hidden" name="state" value="1">
                                <div class="col-md-2">
                                    <span class="">锁编号</span>
                                    <input type="text" class="form-control" placeholder="请输入锁编号" value="${bikeLockInfo.bikeLockCode }" name="bikeLockImei" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">车锁状态</span>
                                    <select name="bikeLockStatus" class="form-control" id="selectedState">
                                    	<option value="-1">全部</option>
										<option value="0">已锁</option>
										<option value="1">未锁</option>\
                                    </select>
                                </div>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="submit">查询</button>
                            </form>
                        </div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>锁编号</th>
											<th>关联车辆编号</th>
											<th>使用周期</th>
											<th>电压</th>
											<th>锁状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="bikeLockInfo" items="${bikeLockInfoList }">
											<tr class="odd gradeA">
												<td>${bikeLockInfo.bikeLockCode}</td>
												<td>${bikeLockInfo.bikeCode}</td>
												<td>一年</td>
												<td>${bikeLockInfo.bikeLockVoltage}V</td>
												<td class="center">
												<c:if test="${bikeLockInfo.bikeLockStatus==0 }">已锁</c:if>
												<c:if test="${bikeLockInfo.bikeLockStatus==1 }">未锁</c:if>
												</td>
												<td class="center">
												<a class="btn btn-primary" href="cms/lock/toEdit.action?bikeLockId=${bikeLockInfo.bikeLockId}">修改</a>
												<a class="btn btn-danger" href="javascript:void(0)"onclick="checkDel('${bikeLockInfo.bikeLockId}')" >删除</a>
												</td>
											</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
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

