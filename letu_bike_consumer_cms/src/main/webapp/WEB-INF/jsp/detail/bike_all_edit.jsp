<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/datePicker.jsp"%>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<!-- <script type="text/javascript">
	function changeTel(obj){
		 $.ajax({
			 type:'post',
			 url:'cms/bikeRepair/bikeRepairInfo.action',
			 data:'managerId='+obj.value,
			 success:function(data){
				 $("#managerTel").val(data.managerTel);
			 }
		 });
	 }
</script> -->
<script type="text/javascript">
  function checkLock(obj){
	  var arr = ${bikeArray};
	  var bikeManagerId = $("#bikeManagerId").val();
	  var bikeModelsId = $("#bikeModelsId").val();
	  var bikeManagerTel = $("#bikeManagerTel").val();
	  var bikeManagerName = $("#bikeManagerName").val();
	  
	  if($("#bikeModelsId").val()==-1){
		     $("#tipa").html("请选择车型");
	    	 return false;
	    }else{
	    	 $("#tipa").html("");
	    }
	    if($("#bikeManagerId").val()==-1){
		     $("#tipd").html("请输入负责人");
	    	 return false;
	    }else{
	    	 $("#tipd").html("");
	    } 
    	  $.ajax({
    		  type:'post',
    		  url:'cms/bike/editAllBike.action',
    		  data:'bikeIds='+arr+"&bikeManagerName="+bikeManagerName+'&bikeManagerTel='+bikeManagerTel+'&bikeModelsId='+bikeModelsId,
    		  success:function(data){
    			window.location.href="${basePath}/cms/bike/bikeList.action";
    		  }
    	  })
    return true;
  }
</script>

<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">车辆编辑</h1>
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
							
								
								<label>车型：</label>
								<div class="form-group">
								<select id="bikeModelsId" class="form-control" required="required">
								    <option value="-1">请选择</option>
								    <c:forEach items="${modelsList }" var="models">
										<option value="${models.modelsId }">${models.modelsName }</option>
									</c:forEach>
								</select>
								<span style="color: red;" id="tipa"></span>
								</div>
					
								<label>负责人：</label>
								<div class="form-group">
								<input class="form-control" placeholder="负责人" value="${bike.bikeManagerName }" id="bikeManagerName" type="text" required="required">
								<span style="color: red;" id="tipd"></span>
								</div>
								<label>联系电话：</label>
								<div class="form-group">
									<input class="form-control" placeholder="联系电话" value="${bike.bikeManagerTel }" id="bikeManagerTel" type="text" required="required">
								</div>
								<div style="text-align: center;">
									<input type="button" onclick="return checkLock()" value="提交" class="btn btn-primary">
								</div>
					
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
</html>
