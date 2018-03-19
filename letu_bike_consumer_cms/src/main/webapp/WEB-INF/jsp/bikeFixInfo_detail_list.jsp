<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<%@include file="common/datePicker.jsp"%>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<script type="text/javascript">
AMap.service('AMap.Geocoder',function(){//回调函数  加载插件   根据经纬获取地址
    //实例化Geocoder
    geocoder = new AMap.Geocoder({
    });
    //TODO: 使用geocoder 对象完成相关功能
  })
  
  //
  var y = '${bike.bikeAtitude }';
  var x = '${bike.bikeLongitude }';
  var lnglatXY = [x,y]
  geocoder.getAddress(lnglatXY, function(status, result) {
      if (status === 'complete' && result.info === 'OK') {
           //获得了有效的地址信息:
           address=result.regeocode.formattedAddress;//.split("市")[1];
           address=address.substring(3,address.length);
           $("#bikeposition").text(address);

      }else{
            //获取地址失败
      }
    }); 
</script>
<script type="text/javascript">
	function checkDel(obj){
		var mess = "确认删除吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
			if(type=='yes'){
				$.ajax({
					type:'post',
					data:'bikeFixInfoId='+obj,
					url:'cms/bikeFixInfo/deleteBikeFixInfoDetail.action',
					success:function(data){	
							window.location.reload();
					}
				});
			}else if(type=='no'){
				this.hide();
			}
	    }) 
				
	}
</script>
<script type="text/javascript">
	    
	    function checkAll(){
	    	if($("#selectAll").is(':checked')){
	    		$(":checkbox").prop("checked",true);
	    	}else{
	    		$(":checkbox").prop("checked",false);
	    	}
	    }
	    function deleteAll(){
	    	var mess = "确认删除吗？";
			$.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
				if(type=='yes'){
					$.ajax({
						type:'post',
						data:$('#bikeFixInfoForm').serialize(),
						url:'cms/bikeFixInfo/deleteAllBikeFixInfoDetail.action',
						success:function(data){	
								window.location.reload();
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
				<h1 class="page-header">车辆维护详情列表</h1>
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
                            	<form action="cms/bikeFixInfo/bikeFixInfoDetailList.action?bikeId=${bike.bikeId }" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">维护编号</span>
                                    <input placeholder="维护编号" type="text" name="fixBatch" value="${fixBatch }" class="form-control">
                                </div>
                                 <div class="col-md-2 time-box">
                                    <span class="tit">维护开始时间</span>
                                   	<input placeholder="维护开始时间" id="cStarttime" type="text" name="bikeFixStartTime" value='<fmt:formatDate value="${bikeFixStartTime }" pattern="yyyy-MM-dd"/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="维护开始时间" id="cEndtime" type="text" name="bikeFixStartTimeB" value='<fmt:formatDate value="${bikeFixStartTimeB }" pattern="yyyy-MM-dd"/>' class="form-control">
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">维护结束时间</span>
                                   	<input placeholder="维护结束时间" id="cStarttimeB" type="text" name="bikeFixEndTime" value='<fmt:formatDate value="${bikeFixEndTime }" pattern="yyyy-MM-dd"/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="维护结束时间" id="cEndtimeB" type="text" name="bikeFixEndTimeB" value='<fmt:formatDate value="${bikeFixEndTimeB }" pattern="yyyy-MM-dd"/>' class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">维护人</span>
                                    <input placeholder="维护人" type="text" name="bikeFixPerson" value="${bikeFixPerson }"  class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">维护地点</span>
                                    <input placeholder="维护地点" type="text" name="bikeFixAddress" value="${bikeFixAddress }"  class="form-control go-back">
                                </div>
                              	</form>
                            </div>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
				        <div class="panel-heading">
						<label>车辆编号：${bike.bikeCode }${dd }</label>
						<label>车辆位置：</label><label id="bikeposition"></label>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
							<form action id="bikeFixInfoForm" method="post">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
										    <th></th>
											<th>维护批次编号</th>
											<th>维护人</th>
											<th>维护开始时间</th>
											<th>维护结束时间</th>
											<th>维护地点</th>
											<th>维护内容</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									 
									    <c:forEach var="bikeFixInfo" items="${bikeFixInfoList }">
											<tr class="odd gradeA">
											    <td>
											    <input type="checkbox" name="bikeFixInfoIds" value="${bikeFixInfo.fixId }">
											    </td>
												<td>${bikeFixInfo.fixBatchNumber }</td>
												<td>${bikeFixInfo.fixMan }</td>
												<td class="center"><fmt:formatDate value="${bikeFixInfo.fixStarttime }" pattern="yyyy-MM-dd"/></td>
												<td class="center"><fmt:formatDate value="${bikeFixInfo.fixEndtime }" pattern="yyyy-MM-dd"/></td>
												<td>
												<c:if test="${! empty bikeFixInfo.fixLongitude or ! empty bikeFixInfo.fixAtitude }">
												<a href="cms/bikeFixInfo/bikeFixPosition.action?bikeFixId=${bikeFixInfo.fixId }"><span class="lnglatStr" data-lat="${bikeFixInfo.fixAtitude }" data-lng="${bikeFixInfo.fixLongitude }"></span></a>
												</c:if>
												</td>
												<td>${bikeFixInfo.fixRemark }</td>
												<td class="center">
												<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${bikeFixInfo.fixId }')">删除</a>
												</td>
											</tr>
									    </c:forEach>
									    
									</tbody>
								</table>
								</form>
								<input  type="checkbox" id="selectAll" onclick="checkAll()" />全选
								<button class="btn btn-danger" onclick="deleteAll();">批量删除</button>
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

