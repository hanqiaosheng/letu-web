<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript">
	function checkDel(obj){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				this.hide();
				location.href="${basePath}cms/student/deleteStu.action?studentId="+obj;
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
	function doSubmit(){
		var stuSchool = $("#stuSchool1").val();
		var stuGraduateTime = $("#stuGraduateTime1").val();
		$("#stuSchool2").val(stuSchool);
		$("#stuGraduateTime2").val(stuGraduateTime);
		$("#form2").submit();
	}
	//群发短信
	function doSendMsg(){
		selected.watch();	
	}
	
	//批量删除
	function deleteAll(){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				this.hide();
				selected.del();
			}else if(type=='no'){
				this.hide();
			}
	    }) 
	}
</script>
<body onload="loadFinish()">
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">学员列表</h1>
			</div>
			<!-- /.col-lg-12 -->
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<form action="cms/student/list.action" method="post" id="form" class="form-inline">
								<input type="hidden" id="dataFlag" name="dataFlag">
								<input type="hidden" id="pageIndex" name="pageIndex">
								<input placeholder="姓名" type="text" name="stuName" value="${stuName }" class="form-control">
								<input placeholder="手机号" type="number" name="telPhone" value="${telPhone }" maxlength="11" class="form-control">
								<select name="stuSchool" id="stuSchool1" class="form-control">
									<option value="">请选择毕业学校</option>
									<c:forEach items="${school }" var="map">
										<option value="${map.key }" <c:if test="${map.key==stuSchool }">selected="selected"</c:if>>${map.value }</option>
									</c:forEach>
								</select>
								<select name="stuGraduateTime" id="stuGraduateTime1" class="form-control">
									<option value="">请选择毕业时间</option>
									<c:forEach items="${graduation }" var="map">
										<option value="${map.key }" <c:if test="${map.key==stuGraduateTime }">selected="selected"</c:if>>${map.value }</option>
									</c:forEach>
								</select>
								
								<select name="stuJobId" id="stuJobId1" class="form-control">
									<option value="">请选择岗位</option>
									<c:forEach items="${stuJob }" var="map">
										<option value="${map.key }" <c:if test="${map.key==stuJobId }">selected="selected"</c:if>>${map.value }</option>
									</c:forEach>
								</select>
								
								<select name="trainId" id="trainId1" class="form-control">
									<option value="">请选择培训机构</option>
									<c:forEach items="${train }" var="map">
										<option value="${map.key }" <c:if test="${map.key==trainId }">selected="selected"</c:if>>${map.value }</option>
									</c:forEach>
								</select>
                                <button class="btn btn-success" type="submit">查询</button>
                                	<a class="btn btn-primary" href="cms/student/list.action?flag=2">导出学员名单</a>
                                	<button type="button" style="display: none" id="sendMsg" class="btn btn-primary" onclick="doSendMsg()">群发短信</button>
                                <input placeholder="短信关键字" type="text" name="content" id="content" class="form-control">
                                <button onclick="deleteAll()" type="button" class="btn btn-success">批量删除</button>
                                <button onclick="searchAll()" class="btn btn-success">查看所有学员</button>
							</form>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTab
							le_wrapper">
								<div class="row">
									<div class="col-sm-6">
										<div class="dataTables_info" id="dataTables-example_info"
												role="status" aria-live="polite">总共<span style="color: red;">${totalCount}</span>条</div>
										</div>
										<div class="col-sm-6">
											<div class="dataTables_paginate paging_simple_numbers"
												id="dataTables-example_paginate">
										</div>
									</div>
								</div>
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>姓名</th>
											<th>性别</th>
											<th>岗位</th>
											<th>联系方式</th>
											<th>毕业学校</th>
											<th>毕业时间</th>
											<th>专业</th>
											<th>培训机构</th>
											<th>操作</th>
											<th>
												<button onclick="selected.allselect()" class="btn btn-success">全选</button> 
												<button onclick="selected.invertselect()" class="btn btn-success">反选</button>
											</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${studentList }" var="student">
											<tr class="odd gradeA">
												<td>${student.stuName }</td>
												<td>
												<c:if test="${student.stuGender == 1 }">男</c:if>
												<c:if test="${student.stuGender == 2 }">女</c:if></td>
												<td>${stuJob[student.stuJob]}</td>
												<td>${student.stuTel}</td>
												<td class="center">${school[student.stuSchool]}</td>
												<td>${graduation[student.stuGraduateTime]}</td>
												<td>${student.stuMajor}</td>
												<td>${train[student.stuTrainingAgency]}</td>
												<td class="center">
														<a class="btn btn-success"
														href="cms/student/updateJsp.action?studentId=${student.stuId}&flag=1">详情</a>
														<a class="btn btn-primary"
														href="cms/student/updateJsp.action?studentId=${student.stuId}&flag=2">修改</a>
														<a class="btn btn-danger" onclick="checkDel(${student.stuId})"
														href="javascript:void(0)">删除</a>
												</td>
												<td>
													<input name="stuCheck" type="checkbox" value="${student.stuTel }">
													<input name="stuCheck2" type="hidden" value="${student.stuId }">
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="dataTables_info" id="dataTables-example_info"
										role="status" aria-live="polite">总共${totalPage}页 ， 当前第${pageIndex}/${totalPage}页</div>
								</div>
								<div class="col-sm-6">
								<div class="dataTables_paginate paging_simple_numbers"
										id="dataTables-example_paginate">
									<div class="pull-right page-box" id="pagediv1">
								       <span class="paginate_button active" onclick="pageChanged('prev')">前一页</span>
								       <span onclick="pageChanged('next')">后一页</span>
								       <input type="number" style="width: 50px" id="pageNum" value="${pageIndex }">
								       <span id="jump" onclick="jump()">GO</span>
								   </div>
								</div>
								</div>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
	</div>
	<script>
	var selected={
   			arr:[],
   			arr2:[],
   			description:'',
            cash:0,
   			watch:function(status){
                selected.each();
                if(this.cash==0){
                	alert('请选择学员')
                    return;
                }
                this.post()
   			},
   			del:function(){
   				selected.each();
                if(this.cash==0){
                	alert('请选择学员')
                    return;
                }
                this.deleteStu();
   			},
   			each:function(){
   				this.arr=[];
                this.cash=0;
   				$.each($('[name="stuCheck"]:checked'),function(){
   					var val=$(this).val();
   					var val2=$(this).next().val();
   					selected.arr.push(val);
   					selected.arr2.push(val2);
                    selected.cash+=1;
   				});
   			},
   			allselect:function(){
   				$.each($('[name="stuCheck"]'),function(){
   					$(this).prop("checked",'true');
   				});
   			},
   			invertselect:function(){
   				$.each($('[name="stuCheck"]'),function(){
   					if($(this).prop("checked")){
   						$(this).prop("checked",false)
   					}else{
   						$(this).prop("checked",true)
   					}
   				});
   			},
   			post:function(status){
   				var content = $("#content").val();
                //进行审核
   				$.ajax({
   					type: "POST",
   					url:'cms/shortmsg/groupSendMsg.action',
   					data:{'stuTel':selected.arr.join(","), 'content':content},
   			    	success: function(data) {
   						if(data.message=="success"){
                            alert('发送中,请稍后....')
   						}
   					},
   					error:function(){
   						alert('数据异常！')
   					}
   			    });
   			},
   			deleteStu:function(){
   				 $.ajax({
   					type: "POST",
   					url:'cms/student/deleteStus.action',
   					data:{'studentIds':selected.arr2.join(",")},
   			    	success: function(data) {
   						if(data.message=="success"){
                            alert('删除成功！');
                            location.reload(true);
   						}else{
                            alert(data.message);
   						}
   					},
   					error:function(){
   						alert('数据异常！')
   					}
   			    });
   			}
   	}
	
	function searchAll(){
		$("#dataFlag").val("all");
		$("#form").submit();
	}
	</script>
	<script type="text/javascript">
	function loadFinish(){
		$("#sendMsg").show();
	}
	
	
	</script>
	<%@include file="common/buttom.jsp"%>
</body>
</html>

