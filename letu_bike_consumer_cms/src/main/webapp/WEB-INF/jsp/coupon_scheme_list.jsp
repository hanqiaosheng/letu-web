<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<script type="text/javascript">
    function checkDel(obj){
        var mess = "确认删除吗？";
        $.confirm(mess,[{yes:"确定"},{no:"取消"}],function(type,e){
            if(type=='yes'){
                $.ajax({
                    type:'post',
                    data:'planId='+obj+'&planState=0',
                    url:'cms/couponScheme/updateState.action',
                    success:function(data){
                        if(data=="success"){
                            window.location.reload();
                        }else if(data=="fail"){
                            $.alert("方案已上线，无法删除，请先下线!",true,function(){
                            },false,{className:'ui-alert',width:300});
                        }
                    }
                });
            }else if(type=='no'){
                this.hide();
            }
        })
    }

    function updateState(obja,objb){
        $.ajax({
            type:'post',
            data:'planId='+obja+'&planState='+objb,
            url:'cms/couponScheme/updateState.action',
            success:function(data){
                if(data=="success"){
                    window.location.reload();
                }
            }
        });
    }

    function checkEdit(obj){
        $.ajax({
            type:'post',
            data:'planId='+obj,
            url:'cms/couponScheme/checkState.action',
            success:function(data){
                if(data=="success"){
                    window.location.href="${basePath}/cms/couponScheme/editJsp.action?planId="+obj
                }else if(data=="fail"){
                    $.alert("方案已上线，无法修改，请先下线!",true,function(){
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
            <h1 class="page-header">骑行券方案列表</h1>

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
                            <form action="cms/couponScheme/redeemPlanList.action" method="post" id="searchForm" >
                                <input type="hidden" id="pageIndex" name="pageIndex">
                                <div class="col-md-2">
                                    <span class="">骑行券方案名称</span>
                                    <input placeholder="请输入方案名称" name="planName" type="text" value="${couponSchemeName}" class="form-control">
                                </div>
                            </form>
                        </div>
                        <br>
                        <button class="btn btn-default pull-right search-btn btn-primary" type="button" onclick="doSubmit()">查询</button>
                        <div class="row tables-action-box">
                            <div class="col-md-6">
                                <a class="btn btn-success" href="cms/couponScheme/addJsp.action">添加</a>
                            </div>
                        </div>
                    </div>
                    <!-- /.panel-heading -->


                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover"
                                   id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>骑行券方案名称</th>
                                    <th>骑行券类型</th>
                                    <th>兑换码</th>
                                    <th>可兑换次数</th>
                                    <th>已兑换次数</th>
                                    <th>已使用次数</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${couponSchemeList }" var="couponScheme" varStatus="varStatus">
                                    <tr class="odd gradeA">
                                        <td>${couponScheme.planId }</td>
                                        <td>${couponScheme.planName }</td>
                                        <td>${couponScheme.coupon.couponName }</td>
                                        <td>${couponScheme.planRedeemCode }</td>
                                        <td>${couponScheme.planSurplusNums}</td>
                                        <td>${couponScheme.planRedeemNums-couponScheme.planSurplusNums}</td>
                                        <td>${couponScheme.planUsedNums}</td>
                                        <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${couponScheme.planCreateTime }" /></td>
                                        <td>
                                            <%--<shiro:hasPermission name="couponSchemeQRButton">--%>
                                                <%--<a class="btn btn-danger" href="javascript:void(0)" onclick="makeQR('${couponScheme.planId }')">生成二维码</a>--%>
                                            <%--</shiro:hasPermission>--%>

                                            <a class="btn btn-info" href="cms/couponScheme/detail.action?couponSchemeId=${couponScheme.planId }">详情</a>

                                            <shiro:hasPermission name="couponSchemeEditButton">
                                                <a class="btn btn-primary" href="javascript:void(0)" onclick="checkEdit('${couponScheme.planId }')">修改</a>
                                            </shiro:hasPermission>

                                            <shiro:hasPermission name="couponSchemeDelButton">
                                                <a class="btn btn-danger" href="javascript:void(0)" onclick="checkDel('${couponScheme.planId }')">删除</a>
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


