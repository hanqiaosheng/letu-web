<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>
<%@include file="../common/datePicker.jsp"%>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">设置旅行社管理员</h1>
        </div>
        <div class="panel-heading">
            <a class="btn btn-danger"
               href="javascript:history.go(-1)">返回</a>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <h3 class="page-header">旅行社管理员信息</h3>
                        </div>
                        <div class="panel-body form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">管理员名字:</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static">${agencyAdmin.adminRealname}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">管理员电话:</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static">${agencyAdmin.adminTel}</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">管理员邮箱:</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static">${agencyAdmin.adminCreateEmail}</p>
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
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <h3 class="page-header">旅行社管理员查看权限</h3>
                        </div>
                        <div class="col-lg-6">
                            <form role="form" action="cms/guideAgencyManage/edit.action?agencyAdminId=${agencyAdmin.adminId}" method="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <div class="form-group">
                                        <div class="checkbox">
                                            <c:forEach items="${guideAgencys}" var="guideAgency" varStatus="varStatus">
                                                <label>
                                                    <c:if test="${lookAgencyIds.contains(guideAgency.guideAgencyId)}">
                                                        <input type="checkbox" checked="checked" name="agencyIds" value="${guideAgency.guideAgencyId}">${guideAgency.guideAgencyName}&nbsp;&nbsp;&nbsp;
                                                    </c:if>
                                                    <c:if test="${!lookAgencyIds.contains(guideAgency.guideAgencyId)}">
                                                        <input type="checkbox"  name="agencyIds" value="${guideAgency.guideAgencyId}">${guideAgency.guideAgencyName}&nbsp;&nbsp;&nbsp;
                                                    </c:if>
                                                </label>
                                                <c:if test="${varStatus.count%4==0}">
                                                    <br>
                                                    <br>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
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
<%--<script  type="text/javascript">--%>
    <%--function addLook(){--%>
        <%--${}--%>
    <%--}--%>
<%--</script>--%>
</html>
