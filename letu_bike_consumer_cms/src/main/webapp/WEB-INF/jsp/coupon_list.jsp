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
                    url:"cms/couponList/delcouponList.action?couponListId="+obj,
                    type:'post',
                    success:function(data){
                        if(data=="success"){
                            window.location.reload();
                        }else if(data=="fail"){
                            $.alert("代金券使用中，无法删除!",true,function(){
                            },false,{className:'ui-alert',width:300});
                        }
                    }
                });

            }else if(type=='no'){
                this.hide();
            }
        })
    }

    function checkEdit(obj){
        $.ajax({
            type:'post',
            data:'couponId='+obj,
            url:'cms/couponList/checkCoupon.action',
            success:function(data){
                if(data=="success"){
                    window.location.href="${basePath}/cms/couponList/couponListEditJsp.action?couponId="+obj
                }else if(data=="fail"){
                    $.alert("代金券使用中，无法修改!",true,function(){
                    },false,{className:'ui-alert',width:300});
                }
            }
        });
    }
</script>
<body>
<%@include file="common/body.jsp"%>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">骑行券列表</h1>
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
                            <form action="cms/couponList/list.action" method="post" id="searchForm">
                                <input type="hidden" name="pageIndex" id="pageIndex">
                                <div class="col-md-2">
                                    <span class="">骑行券名称</span>
                                    <input type="text" class="form-control" placeholder="请输入骑行券名称" value="${couponName}" name="couponName" >
                                </div>
                            </form>
                        </div>
                        <br>
                        <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                        <div class="row tables-action-box">
                            <div class="col-md-6">
                                <shiro:hasPermission name="newCouponAddButton">
                                    <a class="btn btn-success" href="cms/couponList/addJsp.action">添加</a>
                                </shiro:hasPermission>
                            </div>
                        </div>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <form action="cms/couponList/list.action" id="checkAllForm" method="post">
                                <table class="table table-striped table-bordered table-hover"
                                       id="dataTables-example">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>骑行券名称</th>
                                        <th>骑行券抵用类型</th>
                                        <th>骑行券抵用</th>
                                        <th>是否用押金</th>
                                        <th>有效期</th>
                                        <th>创建时间</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${couponList}" var="coupon">
                                        <tr class="odd gradeA">
                                            <td>${coupon.couponId }</td>
                                            <td>${coupon.couponName }</td>
                                            <td>
                                                <c:if test="${coupon.couponType==0}">抵用金额</c:if>
                                                <c:if test="${coupon.couponType==1}">抵用时间</c:if>
                                            </td>
                                            <td>
                                                <c:if test="${coupon.couponMoney!=null && coupon.couponTime==null}">${coupon.couponMoney}元</c:if>
                                                <c:if test="${coupon.couponMoney==null && coupon.couponTime!=null}">${coupon.couponTime}小时</c:if>
                                            </td>
                                            <td>
                                                <c:if test="${coupon.couponNeedDeposit==1}">需要</c:if>
                                                <c:if test="${coupon.couponNeedDeposit==0}">不需要</c:if>
                                            </td>
                                            <td>
                                                <c:if test="${coupon.couponIsValidity==1}">
                                                    <fmt:formatDate value='${coupon.couponStartTime}' pattern='yyyy-MM-dd'/>---<fmt:formatDate value='${coupon.couponEndTime}' pattern='yyyy-MM-dd'/>
                                                </c:if>
                                                <c:if test="${coupon.couponIsValidity==0}">无期限</c:if>
                                                <c:if test="${coupon.couponIsValidity ==2}">${coupon.couponValidityTime }小时</c:if>
                                            </td>
                                            <td class="center"><fmt:formatDate value='${coupon.couponCreateTime}' pattern='yyyy-MM-dd'/></td>
                                            <td class="center">
                                                <a class="btn btn-success"
                                                   href="cms/couponList/detail.action?couponId=${coupon.couponId }">详情</a>

                                                <shiro:hasPermission name="newCouponEditButton">
                                                    <%--<a class="btn btn-primary"--%>
                                                       <%--href="javascript:void(0)" onclick="checkEdit('${coupon.couponId }')">修改</a>--%>
                                                    <a class="btn btn-primary"
                                                       href="cms/couponList/editJsp.action" >修改</a>
                                                </shiro:hasPermission>

                                                <shiro:hasPermission name="newCouponDelButton">
                                                    <a class="btn btn-danger"
                                                       href="javascript:void(0)" onclick="checkDel('${coupon.couponId }')">删除</a>
                                                </shiro:hasPermission>
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
