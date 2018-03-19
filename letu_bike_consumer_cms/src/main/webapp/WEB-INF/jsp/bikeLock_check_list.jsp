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
$(function(){
	var failFlag = ${failFlag};
	if(failFlag==1){
		$.alert("锁编号重复，导入失败",true,function(){
			window.location.href="${basePath}cms/lock/list.action?flag=1";
        },false,{className:'ui-alert',width:300}); 
	}
})
</script>
<script type="text/javascript">
function checkDel(obj){
	var mess = "确认删除吗？";
	$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
		if(type=='yes'){
			if(obj!=''){
				$.ajax({
					url:"${basePath}cms/lock/update.action?bikeLockDel=-1&bikeLockId="+obj,
					type:'post',
					success:function(data){
						if(data!=""){
							window.location.reload();
						}else{
							$.confirm('您无权限进行此操作！',[{yes:"确定"},{no:"取消"}],function(type,e){}) 
						}
					}
				});
			}
			else{
				doAll('del');
			}
		}else if(type=='no'){
			this.hide();
		}
    }) 
}

function lockOrUnlock(obj,id){
	if(obj=='lock'){
		$.ajax({
			url:"${basePath}cms/lock/lock.action?bikeLockId="+id,
			type:'post',
			success:function(data){
				if(data!=""){
					$.confirm('关锁指令发送成功，请稍后刷新页面确认已上锁',[{yes:"确定"},{no:"取消"}],function(type,e){
						window.location.reload();
					})
				}else{
					$.confirm('您无权限进行此操作！',[{yes:"确定"},{no:"取消"}],function(type,e){}) 
				}
			}
		});
	}
	else if(obj=='unlock'){
		$.ajax({
			url:"${basePath}cms/lock/unlock.action?bikeLockId="+id,
			type:'post',
			success:function(data){
				if(data!=""){
					$.confirm('开锁指令发送成功，请稍后刷新页面确认已开锁',[{yes:"确定"},{no:"取消"}],function(type,e){
						window.location.reload();
					}); 
				}else{
					$.confirm('您无权限进行此操作！',[{yes:"确定"},{no:"取消"}],function(type,e){}) 
				}
			}
		});
	}
}

function act(obj){
	$.ajax({
		url:"${basePath}cms/lock/update.action?bikeLockEquipmentState=1&bikeLockId="+obj,
		type:'post',
		success:function(data){
			if(data!=""){
				window.location.reload();
			}else{
				$.confirm('您无权限进行此操作！',[{yes:"确定"},{no:"取消"}],function(type,e){}) 
			}
		}
	});
}
function doAll(obj) {
	if(obj=='del'){
		$.ajax({
			type:'post',
			data:$('#checkAllForm').serialize(),
			url:'cms/lock/update.action?bikeLockDel=-1',
			success:function(data){	
					window.location.reload();
			}
		});
	}else if(obj=='lock'){
		$.ajax({
			type:'post',
			data:$('#checkAllForm').serialize(),
			url:'cms/lock/lock.action',
			success:function(data){	
				$.confirm('关锁指令发送成功，请稍后刷新页面确认已上锁',[{yes:"确定"},{no:"取消"}],function(type,e){
					window.location.reload();
				})
				
			}
		});
	}else if(obj=='unlock'){
		$.ajax({
			type:'post',
			data:$('#checkAllForm').serialize(),
			url:'cms/lock/unlock.action',
			success:function(data){	
				$.confirm('开锁指令发送成功，请稍后刷新页面确认已开锁',[{yes:"确定"},{no:"取消"}],function(type,e){
					window.location.reload();
				}); 
			}
		});
	}else if(obj=='state'){
		$.ajax({
			type:'post',
			data:$('#checkAllForm').serialize(),
			url:'cms/lock/state.action',
			success:function(data){	
					window.location.reload();
			}
		});
	}else if(obj=='gps'){
		$.ajax({
			type:'post',
			data:$('#checkAllForm').serialize(),
			url:'cms/lock/gpss.action',
			success:function(data){	
					window.location.reload();
			}
		});
	}else if(obj=='version'){
		$.ajax({
			type:'post',
			data:$('#checkAllForm').serialize(),
			url:'cms/lock/version.action',
			success:function(data){	
					window.location.reload();
			}
		});
	}
}	
   var selectedOp="${bikeLockInfo.bikeLockStatus}";
   var selectedS="${bikeLockInfo.bikeLockState}"
    $(document).ready(function(){
    	if(selectedOp=='0')
    		$("#selectedState option[value='0']").attr("selected",true);
    	if(selectedOp=='1')
    		$("#selectedState option[value='1']").attr("selected",true);
    	if(selectedOp=='2')
    		$("#selectedState option[value='2']").attr("selected",true);
    	if(selectedS=='0')
    		$("#bikestate option[value='0']").attr("selected",true);
    	if(selectedS=='1')
    		$("#bikestate option[value='1']").attr("selected",true);
   	});
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
				<h1 class="page-header">车锁列表</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
				
					<div class="panel panel-default">
                        <div class="panel-heading clearfix ">
                        <div class="panel-body">
                        	<form id="importForm" action="cms/lock/importLock.action" method="post" enctype="multipart/form-data" 	>
						        		<div class="inputfileli">
								        	<span class="inputfile">
			                                	<button class="btn btn-default btn-primary" type="button" onclick="file.init($(this))">上传Excel</button>
			                                	<input type="file" name="excel" style="display:none" id="pinstructions">
			                                	
		                                	</span>
	                                		<button class="btn btn-success" type="submit" >导入锁</button>
	                                	</div>
	                                	
						        	</form>
			        	</div>
                      </div>
                      <div class="panel-heading clearfix ">
                      		<form action="cms/lock/list.action?flag=1" id="searchForm" method="post">
                        	   <div class="row tables-btn-box">
	                                <div class="col-md-2">
	                                    <span class="">升级文件</span>
	                                    <input type="file" class="form-control" name="upfile" id="upfile">
	                                </div>
	                                <div class="col-md-2">
	                                    <span class="">升级版本</span>
	                                    <input type="number" class="form-control" name="upversion" id="upversion">
	                                </div>
                                </div>
                                <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="up()">升级</button>
                        </div>
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
                            	<input type="hidden" name="pageIndex" id="pageIndex">
                            	<input type="hidden" name="state" value="1">
                                <div class="col-md-2">
                                    <span class="">锁编号</span>
                                    <input type="text" class="form-control" placeholder="请输入锁编号" value="${bikeLockInfo.bikeLockCode }" name="bikeLockCode" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">车辆编号</span>
                                    <input type="text" class="form-control" placeholder="请输入车辆编号" value="${bikeCode }" name="bikeCode" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">车型</span>
                                      <select name="modelsId" class="form-control" id="modelsId">
                                      <option value="-1">请选择车型</option>
                                      <c:forEach items="${modelsList }" var="models">
                                        <option value="${models.modelsId }" <c:if test="${models.modelsId==modelsId }">selected</c:if>>${models.modelsName }</option>
                                      </c:forEach>
                                      </select>
                                </div>
                                <div class="col-md-2">
                                    <span class="">车锁状态</span>
                                    <select name="bikeLockStatus" class="form-control" id="selectedState">
                                    	<option value="-1">全部</option>
										<option value="0">已锁</option>
										<option value="1">未锁</option>
										<option value="2">受阻</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <span class="">在线状态</span>
                                    <select name="bikeLockState" class="form-control" id="bikestate">
                                    	<option value="-1">全部</option>
										<option value="0">未在线</option>
										<option value="1">在线</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <span class="">电量状态</span>
                                    <select name="bikeLockVoltage" class="form-control" id="bikeLockVoltage">
                                    	<option value="-1">全部</option>
										<option value="0" <c:if test="${bikeLockInfo.bikeLockVoltage==0 }">selected</c:if>>正常电量</option>
										<option value="1" <c:if test="${bikeLockInfo.bikeLockVoltage==1 }">selected</c:if>>低电量</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <span class="">车锁版本</span>
                                    <input type="text" class="form-control" placeholder="请输入车辆版本" value="${bikeLockInfo.bikeLockVersion }" name="bikeLockVersion" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">车锁升级状态</span>
                                    <select name="bikeLockUpstate" class="form-control" id="bikeLockUpstate">
                                    	<option value="-1">全部</option>
										<option value="0" <c:if test="${bikeLockInfo.bikeLockUpstate==0 }">selected</c:if>>升级中</option>
										<option value="1" <c:if test="${bikeLockInfo.bikeLockUpstate==1 }">selected</c:if>>升级成功</option>
										<option value="2" <c:if test="${bikeLockInfo.bikeLockUpstate==2 }">selected</c:if>>升级失败</option>
                                    </select>
                                </div>
                                <div class="col-md-2">
                                    <span class="">是否使用电子围栏</span>
                                    <select name="bikeLockIsFence" class="form-control" id="bikeLockIsFence">
                                    	<option value="-1">全部</option>
										<option value="0" <c:if test="${bikeLockInfo.bikeLockIsFence==0 }">selected</c:if>>否</option>
										<option value="1" <c:if test="${bikeLockInfo.bikeLockIsFence==1 }">selected</c:if>>是</option>
                                    </select>
                                </div>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="submit">查询</button>
                            </form>
                        </div>
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action="cms/lock/update.action?bikeLockDel=-1" id="checkAllForm" method="post">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th></th>
											<th>锁编号</th>
											<th>关联车辆编号</th>
											<th>车型</th>
											<th>车锁类型</th>
											<th>电压</th>
											<th>锁状态</th>
											<th>在线状态</th>
											<th>车锁版本</th>
											<th>车锁升级状态</th>
											<th>是否使用电子围栏</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="bikeLockInfo" items="${bikeLockInfoList }">
											<tr class="odd gradeA">
												<td>
												<input type="checkbox" name="bikeLockIdS" value="${bikeLockInfo.bikeLockId}">
												</td>
												<td>${bikeLockInfo.bikeLockCode}</td>
												<td>${bikeLockInfo.bike.bikeCode}</td>
												<td>${bikeLockInfo.bike.models.modelsName}</td>
												<td>
													<c:if test="${bikeLockInfo.bikeLockType==0 || bikeLockInfo.bikeLockType==null}">全自动锁</c:if>
													<c:if test="${bikeLockInfo.bikeLockType==1 }">半自动锁</c:if>
												</td>
												<td>
													<c:if test="${bikeLockInfo.bikeLockVoltage<3.5}"><span style="color:red;">${bikeLockInfo.bikeLockVoltage}V</span></c:if>
													<c:if test="${bikeLockInfo.bikeLockVoltage>=3.5}">${bikeLockInfo.bikeLockVoltage}V</c:if>
												</td>
												<td class="center">
													<c:if test="${bikeLockInfo.bikeLockStatus==0 }">已锁</c:if>
													<c:if test="${bikeLockInfo.bikeLockStatus==1 }">未锁</c:if>
													<c:if test="${bikeLockInfo.bikeLockStatus==2 }">车锁受阻</c:if>
												</td>
												<td>
													<c:if test="${bikeLockInfo.bikeLockState==0 }"><div class="state state-gray">未在线</div></c:if>
													<c:if test="${bikeLockInfo.bikeLockState==1 }"><div class="state state-green">在线</div></c:if>
												</td>
												<td>
													<c:if test="${empty bikeLockInfo.bikeLockVersion }">无</c:if>
													<c:if test="${!empty bikeLockInfo.bikeLockVersion }">${bikeLockInfo.bikeLockVersion }</c:if>
												</td>
												<td class="center">
													<c:if test="${bikeLockInfo.bikeLockUpstate==0 }">升级中</c:if>
													<c:if test="${bikeLockInfo.bikeLockUpstate==1 }">升级成功</c:if>
													<c:if test="${bikeLockInfo.bikeLockUpstate==2 }">升级失败</c:if>
												</td>
												<td class="center">
													<c:if test="${bikeLockInfo.bikeLockIsFence==0 }">否</c:if>
													<c:if test="${bikeLockInfo.bikeLockIsFence==1 }">是</c:if>
												</td>
												<td class="center">
<%-- 												<c:if test="${bikeLockInfo.bikeLockStatus==0 }"> --%>
<%-- 													<a class="btn btn-primary" href="javascript:void(0)" onclick="lockOrUnlock('unlock','${bikeLockInfo.bikeLockId}');">开锁</a> --%>
<%-- 												</c:if> --%>
<%-- 												<c:if test="${bikeLockInfo.bikeLockStatus==1 }"> --%>
<%-- 													<a class="btn btn-primary" href="javascript:void(0)" onclick="lockOrUnlock('lock','${bikeLockInfo.bikeLockId}');">上锁</a> --%>
<%-- 												</c:if> --%>
												<a class="btn btn-primary" href="cms/lock/toCheck.action?bikeLockId=${bikeLockInfo.bikeLockId }">调试</a>
<%-- 												<a class="btn btn-primary" href="cms/lock/location.action?bikeLockId=${bikeLockInfo.bikeLockId }">定位</a> --%>
<%-- 												<a class="btn btn-primary" href="javascript:void(0)" onclick="act('${bikeLockInfo.bikeLockId }');">激活</a> --%>
												<a class="btn btn-primary" href="cms/lock/toEdit.action?bikeLockId=${bikeLockInfo.bikeLockId}">关联车</a>
												<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${bikeLockInfo.bikeLockId }')">删除</a>
												</td>
											</tr>
									</c:forEach>
									</tbody>
								</table>
								</form>
							<input id="checkAllBox" type="hidden" value="1" />
							<input  type="checkbox"  onclick="checkAll();" />全选
							<button class="btn btn-primary" onclick="doAll('lock')">批量上锁</button>
							<button class="btn btn-primary" onclick="doAll('unlock');">批量开锁</button>
							<button class="btn btn-primary" onclick="doAll('state')">批量获取状态</button>
							<button class="btn btn-primary" onclick="doAll('gps')">批量定位</button>
							<button class="btn btn-danger" onclick="checkDel('');">批量删除</button>
							<button class="btn btn-primary" onclick="doAll('version')">批量获取版本信息</button>
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
<script type="text/javascript">
	function up(){
		$.confirm('确认按搜索条件内容升级锁吗？',[{yes:"确定"},{no:"取消"}],function(type,e){
			if('' == $("#upfile").val()){
				 alert("升级文件不能为空");
				 this.hide();
				 return
			}
			if('' == $("#upversion").val()){
				 alert("升级版本不能为空");
				 this.hide();
				 return
			}
			if(type == "yes"){
				var uploadFormData = new FormData($('#searchForm')[0]);
				 $.ajax({
					 url: 'cms/lock/uplock.action',
					 data: uploadFormData,
					 type: 'post',
					 dataType: 'json',
					 processData: false,
		             contentType: false,
					 success: function(data){
						 if(data.message == "success"){
						 $.confirm('升级程序已提交，等待升级',[{yes:"确定"},{no:"取消"}],function(type,e){
								window.location.reload();
							})
						 }
					 },
					 error: function(){
					 }
				 });
				this.hide();			
			}
		})
	}
</script>
</html>

