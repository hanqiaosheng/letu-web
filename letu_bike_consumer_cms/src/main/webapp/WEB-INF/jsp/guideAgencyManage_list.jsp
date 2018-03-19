<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5d6fa7d0648f1101f5174c6bc2eb7e32&plugin=AMap.Walking,AMap.Autocomplete,AMap.PlaceSearch"></script>
<link rel="stylesheet" href="map/maskMap.css">
<script type="text/javascript" src="js/search.js"></script>
<link rel="stylesheet" href="assets/selectSearch/select2.min.css">
<script type="text/javascript" src="assets/selectSearch/select2.js"></script>
<%@include file="common/datePicker.jsp"%>
<script type="text/javascript">
    function checkDel(obj){
        var mess = "确认删除吗？";
        $.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
            _self=this;
            if(type=='yes'){
                $.ajax({
                    type:'post',
                    data:'guideAgencyId='+obj,
                    url:'cms/guideAgency/delete.action',
                    success:function(data){
                        if(data!="fail"){
                            window.location.reload();
                        }else{
                            _self.hide();
                            $.alert("删除失败");
                        }
                    }
                });
            }else if(type=='no'){
                this.hide();
            }
        })
    }
</script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#bikeModelsName").select2();
        $("#modelId").select2();
        $("#channelidSelect").select2();
        $("#bikeModelsId").select2();
        //$("#fixedReturnId").select2();
    });
</script>

<body>
<%@include file="common/body.jsp"%>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">管理员列表</h1>
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
                            <form action="cms/guideAgencyManage/list.action" method="post" id="searchForm">
                                <input type="hidden" id="pageIndex" name="pageIndex">
                                <div class="col-md-2">
                                    <span class="">账号</span>
                                    <input placeholder="请输入管理员账号" type="text" name="adminTel"  class="form-control">
                                </div>
                                <div class="col-md-2">
                                    <span class="">姓名</span>
                                    <input placeholder="请输入管理员姓名" type="text" name="adminRealname"  class="form-control">
                                </div>
                            </form>
                        </div>
                        <br>
                        <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                    </div>


                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <form action id="bikeForm" method="post">
                                <input type="hidden" id="bikeState" name="bikeState">
                                <table id="table" class="table table-striped table-bordered table-hover"
                                       id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th></th>
                                        <th>账号</th>
                                        <th>管理员姓名</th>
                                        <th>归属旅行社</th>
                                        <th>查看旅行社</th>
                                        <th width="200px">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${agencyAdmins}" var="agencyAdmin" >
                                        <tr class="odd gradeA">
                                            <td>
                                                <input type="checkbox" name="adminId" value="${agencyAdmin.adminId}">
                                            </td>
                                            <td>${agencyAdmin.adminTel}</td>
                                            <td>${agencyAdmin.adminRealname}</td>
                                            <td>${id_agencys.get(agencyAdmin.adminId)}</td>
                                            <td>${id_look_agencys.get(agencyAdmin.adminId)}</td>
                                            <td class="center">
                                                <a class="btn btn-primary" href="cms/guideAgencyManage/editJsp.action?agencyAdminId=${agencyAdmin.adminId}" >设置</a>
                                                <a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${guideAgency.guideAgencyId }')">注销</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </form>
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
