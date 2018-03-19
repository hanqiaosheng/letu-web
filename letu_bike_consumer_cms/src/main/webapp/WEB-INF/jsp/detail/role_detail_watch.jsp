<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">查看角色</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a class="btn btn-danger" href="javascript:history.go(-1)">返回</a>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form role="form" action="cms/admin/add.action" method="post">
							<input name="pCheck" type="hidden" id="pCheck">
							<input name="roleId" type="hidden" value="${role.roleId }">
							<label>角色名：</label>
								<div class="form-group">
									<input class="form-control" placeholder="角色名" name="roleName"
										value="${role.roleName }" readonly="readonly">
								</div>
								<label>角色描述：</label>
								<div class="form-group">
									<input class="form-control" placeholder="角色描述"
										name="roleDescription" value="${role.roleDescription }"
										readonly="readonly">
								</div>
								<div class="form-group">
									<label>权限配置：</label>
									<div class="col-md-8">
                                	<div class="zTreeDemoBackground left">
                               		 <ul id="treeDemo" class="ztree"></ul>
                                </div>
                            </div>
								</div>
							</form>
						</div>
						<!-- /.col-lg-6 (nested) -->
					</div>
					<!-- /.row (nested) -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
</div>
<%@include file="../common/buttom.jsp"%>
<link href="js/tree/ztree.css" rel="stylesheet">
	<script src="js/tree/jquery.ztree.core.min.js"></script>

	<script>
	 var roleId = ${role.roleId};
	 var myData = []; 
		$.ajax({
				async:false,
		 			type:"post",
		 			url:"cms/role/watchTree.action?roleId="+roleId,
		 			dataType:"json",
		 			success:function(data){
		 				myData = data;
		 			},
		 			error:function(){
		 				alert("error");
		 			}
		 		});
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
	            onCheck: checkNode
	        }
		};
		var zNodes =myData;
		
		var code;
		function checkNode(){
			var treeObj=$.fn.zTree.getZTreeObj("treeDemo"),
	        nodes=treeObj.getCheckedNodes(true);
			var mId ="";
	        for(var i=0;i<nodes.length;i++){
	        mId =mId+nodes[i].perId+",";
	        }
	        $("#pCheck").val(mId);
		}
		
		function setCheck() {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			py = $("#py").attr("checked")? "p":"",
			sy = $("#sy").attr("checked")? "s":"",
			pn = $("#pn").attr("checked")? "p":"",
			sn = $("#sn").attr("checked")? "s":"",
			type = { "Y":py + sy, "N":pn + sn};
			zTree.setting.check.chkboxType = { "Y" : "s", "N" : "s" };
			showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
		}
		function showCode(str) {
			if (!code) code = $("#code");
			code.empty();
			code.append("<li>"+str+"</li>");
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			setCheck();
			$("#py").bind("change", setCheck);
			$("#sy").bind("change", setCheck);
			$("#pn").bind("change", setCheck);
			$("#sn").bind("change", setCheck);
		});
	    </script>
</html>
