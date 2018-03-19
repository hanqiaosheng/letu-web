<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript" src="js/search.js"></script>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">旅游团信息</h1>
		</div>
		<div class="panel-body form-horizontal">
			<a class="btn btn-danger"
			   href="javascript:history.go(-1)">返回</a>
			<div class="form-group">
				<label class="col-sm-2 control-label">出团日期</label>
				<div class="col-sm-10">
					<p class="form-control-static"><fmt:formatDate value="${guideGroupInfos.guideGroupStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">结束日期</label>
				<div class="col-sm-10">
					<p class="form-control-static"><fmt:formatDate value="${guideGroupInfos.guideGroupEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">出团状态</label>
				<div class="col-sm-10">
					<c:if test="${curDate.compareTo(guideGroupInfos.guideGroupStartTime)<0}">
						<p class="form-control-static">未出团</p>
					</c:if>
					<c:if test="${curDate.compareTo(guideGroupInfos.guideGroupStartTime)>=0 && curDate.compareTo(guideGroup.guideGroupEndTime)<=0}">
						<p class="form-control-static">已出团</p>
					</c:if>
					<c:if test="${curDate.compareTo(guideGroupInfos.guideGroupEndTime)>0}">
						<p class="form-control-static">已结束</p>
					</c:if>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">导游</label>
				<div class="col-sm-10">
					<p class="form-control-static">${adminGuide.adminRealname}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">发券状态</label>
				<div class="col-sm-10">
					<p class="form-control-static">待发券</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">联系电话</label>
				<div class="col-sm-10">
					<p class="form-control-static">${adminGuide.adminTel}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">景区</label>
				<div class="col-sm-10">
					<p class="form-control-static">${guideGroupInfos.guideGroupScenic}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">游客人数</label>
				<div class="col-sm-10">
					<p class="form-control-static">${guideGroupInfos.guideGroupHeadcount}</p>
				</div>
			</div>
		</div>
		<!-- /.col-lg-12 -->
		<!--查询条件-->
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
				<form action="cms/guideGroup/detail.action" method="post" id="searchForm">
					<input type="hidden" name="pageIndex" id="pageIndex">
					<input type="hidden" value="${guideGroupInfos.guideGroupId}" name="guideGroupId">
					<div class="col-md-2">
						<span class="">手机账号</span>
						<input placeholder="请输入游客手机号" type="text"  name="phone"  class="form-control" >
					</div>
					<div class="col-md-2">
						<span class="">身份证</span>
						<input placeholder="请输入身份证" type="text"  name="idCard"  class="form-control" >
					</div>
					<div class="col-md-2">
						<span class="">姓名</span>
						<input placeholder="请输入姓名" type="text"  name="name"  class="form-control" >
					</div>
					<%--<div class="col-md-2 time-box">--%>
						<%--<span class="tit">注册时间</span>--%>
						<%--<input placeholder="" id="cStarttime" type="text" name="startTime" value='<fmt:formatDate value="${startTime }"/>'  class="form-control">--%>
						<%--<span class="line">-</span>--%>
						<%--<input placeholder="" id="cEndtime" type="text" name="endTime" value='<fmt:formatDate value="${endTime }"/>' class="form-control">--%>
					<%--</div>--%>
				</form>
			</div>
			<br>
			<button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
		</div>
		<!--表格-->
		<div class="panel-body">
			<div class="dataTable_wrapper">
				<table class="table table-striped table-bordered table-hover"
					   id="dataTables-example">
					<thead>
					<tr>
						<th>手机账号</th>
						<th>姓名</th>
						<th>身份证</th>
						<th>状态</th>
						<th>开锁方式</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${rentUsers}" var="rentUser">
						<tr class="odd gradeA">
							<td><a href="cms/guideGroup/touristRentInfo.action?userId=${rentUser.user.userId}">${rentUser.user.userTel}</a></td>
							<td>${rentUser.user.userRealname}</td>
							<td>${rentUser.user.userIdcard}</td>
							<%--<td>2018-01-05</td>--%>
							<c:if test="${rentUser.rentState==constantUtil.USER_RENT_OPEN}">
								<td>骑行中</td>
							</c:if>
							<c:if test="${rentUser.rentState==constantUtil.USER_RENT_CLOSE}">
								<td>已完成</td>
							</c:if>

							<c:choose>
								<c:when test="${rentUser.rentUnlockWay==constantUtil.GUIDE_UNLOCK_WAY_GUIDE}">
									<td>代开锁</td>
								</c:when>
								<c:otherwise>
									<td>自开锁</td>
								</c:otherwise>
							</c:choose>

							<td class="center">
								<a class="btn btn-primary"
								   href="cms/guideGroup/editTouristJsp.action?userId=${rentUser.user.userId}">修改</a>
							</td>
						</tr>
					</c:forEach>
					<c:forEach items="${unRentUsers}" var="unRentUser">
						<tr class="odd gradA">
							<td>${unRentUser.userTel}</td>
							<td>${unRentUser.userRealname}</td>
							<td>${unRentUser.userIdcard}</td>
							<td>未骑行</td>
							<td>无</td>
							<td class="center">
								<a class="btn btn-primary"
								   href="cms/guideGroup/editTouristJsp.action?userId=${unRentUser.userId}&guideGroupId=${guideGroupInfos.guideGroupId}">修改</a>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- /.row -->
</div>
<%@include file="../common/buttom.jsp"%>
</html>
