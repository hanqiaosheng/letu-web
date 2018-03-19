<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<%@include file="common/datePicker.jsp"%>
<body>
	<%@include file="common/body.jsp"%>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">注册短信列表</h1>
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
                            	<form action="cms/sysMsg/shortMessageList.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">手机号</span>
                                    <input type="text" class="form-control" placeholder="请输入手机号" value="${tel}" name="tel" >
                                </div>
                                <div class="col-md-2">
                                    <span class="">验证状态</span>
                                    <select class="form-control" name="codeState">
                                      <option value="-1">全部</option>
                                      <option value="0"<c:if test="${codeState==0 }">selected</c:if>>未验证</option>
                                      <option value="1"<c:if test="${codeState==1 }">selected</c:if>>已验证</option>
                                    </select>
                                </div>
                              	</form>
                            </div>
                            <br>
                            <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                       </div>
						
						
						
						
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables-example">
									<thead>
										<tr>
											<th>编号</th>
											<th>手机号</th>
											<th>验证码内容</th>
											<th>验证状态</th>
											<th>创建时间</th>
											<!-- <th>IP地址</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${codeList}" var="shortMsg">
											<tr class="odd gradeA">
												<td>${shortMsg.codeId }</td>
												<td>${shortMsg.codePhone }</td>
												<td>${shortMsg.codeNum }</td>
												<td>
													<c:if test="${shortMsg.codeState==1 }">
														<span style="color:green;">已验证</span>
													</c:if>
													<c:if test="${shortMsg.codeState==0 }">
														<span style="color:orange;">未验证</span>
													</c:if>
												</td>
												
												<td class="center"><fmt:formatDate value='${shortMsg.codeCreatetime}' pattern='yyyy-MM-dd HH:mm:ss'/></td>
												<%-- <td>${shortMsg.codeIp }</td> --%>
											</tr>
										</c:forEach>
									</tbody>
								</table>
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

