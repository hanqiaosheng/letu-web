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
                    url:"${basePath}/cms/guideGroup/delete.action?guideGroupId="+obj,
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

    function checkCouponFab(obj) {
		var mess = "确认发券吗？";
		$.confirm(mess,[{yes:"确定"},{no:"取消"}],function (type,e) {
		    if(type=='yes'){
		        $.ajax({
					url:"${basePath}/cms/guideGroup/couponFab.action?guideGroupId="+obj,
					type:'post',
					success:function (data) {
						if(data!=""){
						    $.alert("发券成功！");
						    window.location.reload();
                        }
                    }
				})
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
			<h1 class="page-header">旅游团列表</h1>
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
							<form action="cms/guideGroup/list.action" method="post" id="searchForm">
								<input type="hidden" name="pageIndex" id="pageIndex">
								<div class="col-md-2">
									<span class="">旅行社</span>
									<select name="guideGroupAgencyId"  class="form-control">
										<option value="-1">全部</option>
										<c:forEach items="${guideAgencyInfosList}" var="guideAgencyInfo">
											<option value="${guideAgencyInfo.guideAgencyId}">${guideAgencyInfo.guideAgencyName}</option>
										</c:forEach>
									</select>
								</div>
								<div class="col-md-2">
									<span class="">导游电话</span>
									<input placeholder="请输入电话" type="text"  name=""  class="form-control" >
								</div>
								<div class="col-md-2">
									<span class="">景区</span>
									<input placeholder="请输入名字" type="text"  name="guideGroupScenic" class="form-control" >
								</div>
								<%--<div class="col-md-2">--%>
									<%--<span class="">出团状态</span>--%>
									<%--<select name="guideGroupState" class="form-control" id="guideGroupState">--%>
										<%--<option value="-1">全部</option>--%>
										<%--<option value="0" <c:if test="${guideGroupInfos.guideGroupState==0 }">selected</c:if>>未出团</option>--%>
										<%--<option value="1" <c:if test="${guideGroupInfos.guideGroupState==1 }">selected</c:if>>已出团</option>--%>
									<%--</select>--%>
								<%--</div>--%>
								<div class="col-md-2">
									<span class="">发券状态</span>
									<select name="guideGroupCouponState" class="form-control" id="guideGroupCouponState">
										<option value="-1">全部</option>
										<option value="0" <c:if test="${guideGroupInfos.guideGroupCouponState==0 }">selected</c:if>>未发券</option>
										<option value="1" <c:if test="${guideGroupInfos.guideGroupCouponState==1 }">selected</c:if>>已发券</option>
									</select>
								</div>
								<div class="col-md-2">
									<span class="">游客人数</span>
									<input placeholder="" type="text"  name="headCountLeft" value="${headCountLeft}" class="form-control" >
									<span class="line">-</span>
									<input placeholder="" type="text"  name="headCountRight" value="${headCountRight}" class="form-control">
								</div>
								<div class="col-md-2 time-box">
									<span class="tit">出团时间</span>
									<input placeholder="" id="cStarttime" type="text" name="startTime" value='<fmt:formatDate value="${startTime }"/>'  class="form-control">
									<span class="line">-</span>
									<input placeholder="" id="cEndtime" type="text" name="endTime" value='<fmt:formatDate value="${endTime }"/>' class="form-control">
								</div>
							</form>
						</div>
						<br>
						<button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
						<div class="row tables-action-box">
							<div class="col-md-6">
								<shiro:hasPermission name="guideGroupAddButton">
									<a  class="btn btn-success" href="cms/guideGroup/addJsp.action">创建旅游团</a>
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
									<th>ID</th>
									<th>出团时间</th>
									<th>导游</th>
									<th>景区</th>
                                    <th>所属旅行社</th>
									<th>游客人数</th>
									<th>骑行人数</th>
									<th>出团状态</th>
									<th>发券状态</th>
									<th>发券时间</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${guideGroupList}" var="guideGroup">
									<tr class="odd gradeA">
                                        <!--旅游团-->
										<td>${guideGroup.guideGroupId}</td>
										<!--旅游团出团时间-->
										<td><fmt:formatDate value="${guideGroup.guideGroupStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<!--导游姓名-->
										<td>${guideGroup.admin.adminRealname}</td>
										<!--景区-->
										<td>${guideGroup.guideGroupScenic}</td>
                                        <!--所属旅行社-->
                                        <td>${guideGroup.guideAgencyInfos.guideAgencyName}</td>
										<!--游客人数-->
										<td>${guideGroup.guideGroupHeadcount}</td>
										<!--骑行人数-->
										<td>${guideGroup.guideGroupRidingcount}</td>
										<!--出团状态 根据出团状态进行具体显示-->
                                        <%--<td>--%>
											<%--<c:if test="${guideGroup.guideGroupState==constantUtil.GUIDE_GROUP_START}">已出团</c:if>--%>
											<%--<c:if test="${guideGroup.guideGroupState==constantUtil.GUIDE_GROUP_NOT_START}">未出团</c:if>--%>
										<%--</td>--%>
                                        <td>
                                            <c:if test="${curDate.compareTo(guideGroup.guideGroupStartTime)<0}">未出团</c:if>
                                            <c:if test="${curDate.compareTo(guideGroup.guideGroupStartTime)>=0 && curDate.compareTo(guideGroup.guideGroupEndTime)<=0}">出团中</c:if>
                                            <c:if test="${curDate.compareTo(guideGroup.guideGroupEndTime)>0}">已结束</c:if>
                                        </td>
										<!--发券状态-->
										<td>
											<c:if test="${guideGroup.guideGroupCouponState==constantUtil.GUIDE_COUPON_OPEN}">已发券</c:if>
											<c:if test="${guideGroup.guideGroupCouponState==constantUtil.GUIDE_COUPON_CLOSE}">未发券</c:if>
										</td>
										<!--发券时间-->
										<td><fmt:formatDate value="${guideGroup.guideGroupStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<!--备注-->
										<td>${guideGroup.guideGroupRemark}</td>
										<td class="center">
											<shiro:hasPermission name="guideGroupCouponButton">
												<a class="btn btn-info" href="javascript:void(0)" onclick="checkCouponFab('${guideGroup.guideGroupId}',-1)">发券</a>
											</shiro:hasPermission>

											<a class="btn btn-info"
											   href="cms/guideGroup/detail.action?guideGroupId=${guideGroup.guideGroupId}">详情</a>

											<shiro:hasPermission name="guideGroupEditButton">
												<a class="btn btn-primary"
												   href="cms/guideGroup/editJsp.action?guideGroupId=${guideGroup.guideGroupId}">修改</a>
											</shiro:hasPermission>

											<shiro:hasPermission name="guideGroupDelButton">
												<a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${guideGroup.guideGroupId}',-1)" >删除</a>
											</shiro:hasPermission>
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
</body>
</html>

