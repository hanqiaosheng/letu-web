<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">修改用户信息</h1>
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
                            <form role="form"  method="post" enctype="multipart/form-data" action="cms/guideGroup/editTourist.action?guideGroupId=${guideGroupId}">
                                <input type="hidden" value="${user.userId}" name="userId">
                                <div class="form-group">
                                    <label>用户姓名</label><br>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="请输入用户姓名" value="${user.userRealname}" name="userRealname"  type="text"
                                               required="required">
                                    </div>
                                    <br>

                                    <label>用户电话</label><br>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="请输入用户电话" value="${user.userTel}" name="userTel"  type="text"
                                               required="required">
                                    </div>
                                    <br>

                                    <label>用户身份证</label><br>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="请输入用户身份证" value="${user.userIdcard}" name="userIdcard"  type="text"
                                               required="required">
                                    </div>
                                    <br>

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
</html>
