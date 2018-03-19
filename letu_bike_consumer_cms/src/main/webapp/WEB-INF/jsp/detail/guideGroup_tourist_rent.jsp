<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<div id="page-wrapper">
    <a class="btn btn-danger"
       href="javascript:history.go(-1)">返回</a>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <h2 class="page-header">游客租赁详情</h2>
                        </div>
                        <div class="panel-body form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">游客姓名：</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static">${bikeRentInfo.user.userRealname}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">车辆编号：</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static">${bikeRentInfo.bike.bikeCode}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">租赁费用：</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static"><fmt:formatNumber minFractionDigits="2" value="${bikeRentInfo.rentPrice }" /></p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">归还时间：</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static"><fmt:formatDate value='${bikeRentInfo.rentEndtime}' pattern='yyyy-MM-dd HH:mm:ss'/></p>
                                </div>
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
