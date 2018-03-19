
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">创建/编辑 旅行社</h1>
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
                            <form role="form"  method="post" enctype="multipart/form-data" action="cms/guideAgency/${actionName}.action">
                                <input type="hidden" value="${guideAgency.guideAgencyId}" name="guideAgencyId">
                                <div class="form-group">
                                    <label>旅行社名称</label><br>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="旅行社名称" value="${guideAgency.guideAgencyName}" name="guideAgencyName"  type="text"
                                               required="required">
                                    </div>
                                    <br>

                                    <br>
                                    <label>选择省份</label><br>
                                    <div class="form-group">
                                        <select id="guideAgencyProvinceId" class="form-control"  name="guideAgencyProvinceId" onchange="selectCity(this.value)" required="required">
                                            <%--<c:if test="${guideAgency.guideAgencyProvinceId} != 1"></c:if>--%>
                                            <c:if test="${curProvince.provinceId==null}">
                                                <option value=-1>选择省份</option>
                                            </c:if>
                                            <c:forEach var="province" items="${provinceList}">
                                                <c:choose>
                                                    <c:when test="${province.provinceId==curProvince.provinceId}">
                                                        <option value="${province.provinceId}" selected = "selected">
                                                                ${province.provinceName}
                                                        </option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="${province.provinceId}">
                                                                ${province.provinceName}
                                                        </option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <br>

                                    <br>
                                    <label>选择城市</label><br>
                                    <div class="form-group">
                                        <select id="guideAgencyCityId" class="form-control" name="guideAgencyCityId" required="required">
                                            <c:choose>
                                                <c:when test="${curCity.cityId==null}">
                                                    <option value=-1>选择城市</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${curCity.cityId}">${curCity.cityName}</option>
                                                </c:otherwise>
                                            </c:choose>

                                        </select>
                                    </div>
                                    <br>

                                    <br>
                                    <label>旅行社负责人</label><br>
                                    <div class="form-group">
                                        <select id="guideAgencyPrincipalId" class="form-control" name="guideAgencyPrincipalId" required="required">
                                            <c:if test="${guideAgency.guideAgencyPrincipalId!=null}">
                                                <option value="${guideAgency.principalAdmin.adminId}">
                                                    名字: ${guideAgency.principalAdmin.adminRealname} 电话: ${guideAgency.principalAdmin.adminTel}
                                                </option>
                                            </c:if>
                                            <c:if test="${guideAgency.guideAgencyPrincipalId==null}">
                                                <option value="">请选择旅行社负责人</option>
                                            </c:if>
                                            <c:forEach var="principalAdmin" items="${principalAdmins}">
                                                <option value="${principalAdmin.adminId}">
                                                    名字: ${principalAdmin.adminRealname} 电话: ${principalAdmin.adminTel}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <br>

                                    <br>
                                    <label>旅行社归属管理员</label><br>
                                    <div class="form-group">
                                        <select id="" class="form-control" name="guideAgencyManagerId" required="required">
                                            <c:if test="${guideAgency.guideAgencyManagerId!=null}">
                                                <option value="${guideAgency.managerAdmin.adminId}">
                                                    名字: ${guideAgency.managerAdmin.adminRealname} 电话: ${guideAgency.managerAdmin.adminTel}
                                                </option>
                                            </c:if>
                                            <c:if test="${guideAgency.guideAgencyManagerId==null}">
                                                <option value="">请选择旅行社归属管理员</option>
                                            </c:if>
                                            <c:forEach var="managerAdmin" items="${managerAdmins}">
                                                <option value="${managerAdmin.adminId}">
                                                    名字: ${managerAdmin.adminRealname} 电话: ${managerAdmin.adminTel}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <br>

                                    <br>
                                    <label>旅行社地址</label><br>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="请输入旅行社地址" value="${guideAgency.guideAgencyAddr}" name="guideAgencyAddr"  type="text"
                                               required="required">
                                    </div>
                                    <br>

                                    <br>
                                    <label>旅行社负责景区</label><br>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="请输入景区" value="${guideAgency.guideAgencyScenic}" name="guideAgencyScenic"  type="text"
                                               required="required">
                                    </div>
                                    <br>

                                    <br>
                                    <label>备注</label><br>
                                    <div class="form-group">
                                        <textarea name="guideAgencyRemark" rows="5" cols="50" class="form-control">${guideAgency.guideAgencyRemark}</textarea>
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
<%@include file="../common/buttom.jsp"%>
<!--js操作函数-->
<script type="text/javascript">
    //省的onchange事件
    function selectCity(provinceId) {
        var selectCity = document.getElementById('guideAgencyCityId');
        //先将选项清除(否则上一次的选项也会在)
        selectCity.options.length=0;
        <c:forEach var="city" items="${cityList}">
            <!--option(text,value)-->
            if("${city.cityOfProvinceId}"==provinceId){
                var option = new Option("${city.cityName}","${city.cityId}");
                if("${guideAgency.guideAgencyCityId}"=="${city.cityId}"){
                    option.selected=true;
                }
                selectCity.options.add(option);
            }
        </c:forEach>
    }
</script>
</html>
