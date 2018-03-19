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
    function exportExcel(){
        $("#exportFlag").val(1);
        $("#searchForm").submit();
    }
    function search(){
        $("#exportFlag").val(0);
        $("#searchForm").submit();
    }
</script>
<body>
<%@include file="common/body.jsp"%>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">骑行券兑换列表</h1>
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
                            <form action="cms/couponUse/list.action" method="post" id="searchForm" >
                                <input type="hidden" id="pageIndex" name="pageIndex">
                                <input type="hidden" id="exportFlag" name="flag">
                                <div class="col-md-2">
                                    <span class="">骑行券方案名称</span>
                                    <input placeholder="兑换方案名称" name="redeemPlanName" type="text" value="${redeemPlanName}" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">兑换码</span>
                                    <input placeholder="兑换码" name="redeemCode" type="text" value="${redeemCode}" class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">用户账号</span>
                                    <input placeholder="用户账号" name="userTel" type="text" value="${userTel}" class="form-control">
                                </div>
                                <div class="col-md-2 time-box">
                                    <span class="tit">兑换时间</span>
                                    <input placeholder="兑换时间" id="cStarttime" type="text" name="startTime" value='<fmt:formatDate value="${startTime }"/>'  class="form-control">
                                    <span class="line">-</span><input placeholder="兑换时间" id="cEndtime" type="text" name="endTime" value='<fmt:formatDate value="${endTime }"/>' class="form-control">
                                </div>
                            </form>
                        </div>
                        <br>
                        <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="search()">查询</button>
                    </div>
                    <!-- /.panel-heading -->


                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover"
                                   id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>骑行券方案名称</th>
                                    <th>兑换码</th>
                                    <th>用户账号</th>
                                    <th>用户名称</th>
                                    <th>兑换时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${couponUseList }" var="couponUse" varStatus="varStatus">
                                    <tr class="odd gradeA">
                                        <td>${couponUse.couponScheme.planName }</td>
                                        <td>${couponUse.couponScheme.planRedeemCode }</td>
                                        <td>${couponUse.user.userTel }</td>
                                        <td>${couponUse.user.userRealname }</td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${couponUse.createTime }" /></td>
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
