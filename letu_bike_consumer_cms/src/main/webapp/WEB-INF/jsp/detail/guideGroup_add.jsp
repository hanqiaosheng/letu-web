<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/search.js"></script>
<%@include file="../common/body.jsp"%>
<%@include file="../common/datePicker.jsp"%>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">创建/编辑 旅游团</h1>
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
                            <form  role="form" action="cms/guideGroup/${actionName}.action" method="post" enctype="multipart/form-data">
                                <input type="hidden" value="${guideGroup.guideGroupId}" name="guideGroupId">
                                <div class="form-group">

                                    <br>
                                    <label>导入游客</label><br>
                                    <div class="inputfileli">
								        	<span class="inputfile">
                                                <button class="btn btn-default btn-primary" type="button" onclick="file.init($(this))">上传Excel</button>
			                                	<input  type="file" name="excel" style="display:none" id="excel">
		                                	</span>
                                            <button id="checkExcel" class="btn btn-success" type="button" onclick="checkImport()" >检查</button>
                                    </div>
                                    <br>

                                    <label>出团时间</label><br>
                                    <div class="form-group">
                                        <input required="required" placeholder="出团时间" id="cStarttimeB" type="text"
                                         value="<fmt:formatDate value='${guideGroup.guideGroupStartTime}' pattern="yyyy-MM-dd HH:mm:ss"/>"  name="guideGroupStartTime"  class="form-control">
                                    </div>
                                    <br>

                                    <label>结束时间</label><br>
                                    <div class="form-group">
                                        <input required="required" placeholder="结束时间" id="cEndtimeB" type="text"
                                        value="<fmt:formatDate value='${guideGroup.guideGroupEndTime}' pattern="yyyy-MM-dd HH:mm:ss"/>"  name="guideGroupEndTime"  class="form-control">
                                    </div>
                                    <br>


                                    <br>
                                    <label>出团人数</label><br>
                                    <div class="form-group">
                                        <input required="required"  placeholder="出团人数" type="text"
                                               value="${guideGroup.guideGroupHeadcount}" name="guideGroupHeadcount"  class="form-control">
                                    </div>
                                    <br>

                                    <br>
                                    <label>出团景区</label><br>
                                    <div class="form-group">
                                        <input required="required"  placeholder="出团景区" type="text"
                                               value="${guideGroup.guideGroupScenic}" name="guideGroupScenic"  class="form-control">
                                    </div>
                                    <br>

                                    <br>
                                    <label>导游电话</label><br>
                                    <div class="form-group">
                                        <input required="required"  placeholder="请输入电话" type="tel"
                                               value="${guideAdmin.adminTel}" name="guideTel" class="form-control">
                                    </div>
                                    <br>

                                    <br>
                                    <label>选择骑行券方案</label><br>
                                    <div class="form-group">
                                        <select id="guideGroupCouponId" class="form-control"  name="guideGroupCouponId">
                                            <option value="-1">请选择骑行券方案</option>
                                            <c:forEach items="${couponSchemeList}" var="couponScheme">
                                                <option value="${couponScheme.planId}">
                                                    ${couponScheme.planName}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <br>

                                    <br>
                                    <label>备注</label><br>
                                    <div class="form-group">
                                        <textarea name="guideGroupRemark" rows="5" cols="50" class="form-control">
                                            ${guideGroup.guideGroupRemark}
                                        </textarea>
                                    </div>
                                    <br>
                                </div>
                                <div style="text-align: center;">
                                    <input  type="submit" value="提交" class="btn btn-primary">
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
<script type="text/javascript">
    function checkImport() {
        var formData = new FormData();
        formData.append("file",$("#excel")[0].files[0]);
        $.ajax({
                url: "${basePath}/cms/guideGroup/checkImport.action",
                type: 'POST',
                async: false,
                processData: false,
                contentType: false,
                data: formData,
                dataType: "json",
                success : function(data){
                    if(data.message=="success"){
                        $.confirm('用户信息格式无误',[{yes:'确认'}],function () {
                            this.hide();
                        })
                    }else if(data.message=="error"){
                        var mess = "";
                        var errUsers = data.data.errUsers;
                        var errCodes = data.data.errCodes;
                        for(var i=0;i<errUsers.length;i++){
                            var errUser = errUsers[i];
                            var errCode = errCodes[i];
                            if(errCode==0){
                                mess = mess+errUser.userRealname+":"+"手机号格式有误"+"<br>";
                            }else if(errCode==1){
                                mess = mess+errUser.userRealname+":"+"身份证格式有误"+"<br>";
                            }else if(errCode==2){
                                mess = mess+errUser.userRealname+":"+"手机号及身份证格式有误"+"<br>";
                            }
                        }
                        $.confirm(mess,[{yes:'确认'}],function () {
                            this.hide();
                        })
                    }
                }
            }
        )
    }
</script>
</html>

