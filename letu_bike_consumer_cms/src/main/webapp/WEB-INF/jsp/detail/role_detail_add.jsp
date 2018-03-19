<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">添加角色</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a class="btn btn-danger" 
						href="javascript:history.go(-1)">返回</a>
				</div>
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-6">
							<form role="form" action="cms/role/roleAdd.action" onsubmit="return formSub()" method="post">
							<input name="pCheck" type="hidden" id="pCheck">
							<label>角色名：</label>
								<div class="form-group">
									<input class="form-control" id="rname" placeholder="角色名"  name="roleName" type="text"
										required="required">
								</div>
								<label>角色描述：</label>
								<div class="form-group">
									<input class="form-control" placeholder="角色描述" type="text"
										name="roleDescription" required="required">
								</div>
								<label>角色状态：</label>
								<div class="form-group">
									<select name="roleState" class="form-control">
									 <option value="1">任何渠道可使用</option>
									 <option value="2">一级和二级渠道不可使用</option>
									 <option value="3">二级渠道不可使用</option>
									</select>
								</div>
								<div class="form-group">
									<label>权限配置：</label>
									<div class="col-md-8">
                                	<div class="zTreeDemoBackground left">
                               		 <ul id="treeDemo" class="ztree"></ul>
                                </div>
                            </div>
								</div>
								<br>
								<div style="text-align: center;">
									<input type="submit" value="提交" class="btn btn-primary">
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
	 var rname = 0;
	    $('#rname').blur(function(){
	    		var str = "rname="+$('#rname').val();
	    		$.ajax({
	   	 			type:"post",
	   	 			url:"cms/role/checkRname.action",
	   	 			dataType:"json",
	   	 			data:str,
	   	 			success:function(data){
	   	 				if(data.message != "success"){
	   	 					alert("角色已存在");
	   	 					rname = 0;
	   	 				}else{
	   	 					rname = 1;
	   	 				}
	   	 			}
	   	 		})
	    	})
	    	
	    function formSub(){
	    	 if(rname==0){
	    		alert("角色已存在!");
	    		return false;
	    	}
	    	return true;
	    } 
	
	var myData = []; 
	$.ajax({
			async:false,
	 			type:"post",
	 			url:"cms/role/getTree.action",
	 			dataType:"json",
	 			success:function(data){
	 				myData = data;
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
		zTree.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" };
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
