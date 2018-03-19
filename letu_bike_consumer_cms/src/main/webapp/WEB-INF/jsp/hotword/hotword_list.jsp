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
				data:'hotWordId='+obj+'&hotWordState=-1',
				url:'cms/hotword/updateState.action',
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
		data:'hotWordId='+obja+'&hotWordState='+objb,
		url:'cms/hotword/updateState.action',
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
		data:'hotWordId='+obj,
		url:'cms/hotword/checkState.action',
		success:function(data){
			if(data=="success"){
				window.location.href="${basePath}/cms/hotword/editJsp.action?hotWordId="+obj
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
				<h1 class="page-header">热词列表</h1>
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
                            	<form action="cms/hotword/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                              
                                <div class="col-md-2">
                                    <span class="">热搜词</span>
                                    <input placeholder="热搜词" type="text"  name="hotWordName" value="${hotWordName }" class="form-control">
                                </div>
                              	</form>
                              		
                           </div>
                           <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                           	<div class="row tables-action-box">
	                              	<div class="col-md-6">
	                              	<shiro:hasPermission name="hotWordAddButton">
	                                    <a  class="btn btn-success" href="cms/hotword/addJsp.action">添加热词</a>
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
											<th>热搜词语</th>
											<th>置顶号</th>
											<th>状态</th>
											<th>创建时间</th>
											<th>修改时间</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${hotWordList }" var="hotWord">
											<tr class="odd gradeA">
											    <td>${hotWord.hotWordId }</td>
												<td>${hotWord.hotWordName }</td>
												<td>${hotWord.hotWordTopNum }</td>
												<c:if test="${hotWord.hotWordState==1 }">
												<td>未上线</td>
												</c:if>
												<c:if test="${hotWord.hotWordState==2 }">
												<td>已上线</td>
												</c:if>
												<td><fmt:formatDate value="${hotWord.hotWordCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td><fmt:formatDate value="${hotWord.hotWordUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
												<td class="center">
													<a class="btn btn-info"
													href="cms/hotword/detail.action?hotWordId=${hotWord.hotWordId}">详情</a>
													<shiro:hasPermission name="hotWordEditButton">
													<a class="btn btn-primary"
													href="javascript:void(0)" onclick="checkEdit('${hotWord.hotWordId }')">修改</a>
													</shiro:hasPermission>
													<shiro:hasPermission name="hotWordLineButton">
													<c:if test="${hotWord.hotWordState==2 }" >
													<a class="btn btn-primary" href="javascript:void(0)" onclick="updateState('${hotWord.hotWordId }','1')">下线</a>
													</c:if>
													<c:if test="${hotWord.hotWordState==1 }">
													<a class="btn btn-primary" href="javascript:void(0)" onclick="updateState('${hotWord.hotWordId }','2')">上线</a>
													</c:if>
													</shiro:hasPermission>
													<shiro:hasPermission name="hotWordDelButton">
													<a class="btn btn-danger"  onclick="checkDel('${hotWord.hotWordId}')" >删除</a>
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

