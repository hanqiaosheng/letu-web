<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html>
<html lang="en">
<script type="text/javascript">
function setMenuActive(){
	if(sessionStorage['navlevel1']){//根据缓存展开对应菜单
		$('#side-menu>li').eq(sessionStorage['navlevel1']).addClass('active');
		$('#side-menu .active li').eq(sessionStorage['navlevel2']).find('a').addClass('active');
	}	
}
$(function(){
	$('#page-wrapper').click(function(){//添加本地缓存
		sessionStorage['navlevel1']=$('#side-menu>.active').index();
		sessionStorage['navlevel2']=$('#side-menu .active .active').parent().index();
	})
	$('#side-menu').on('click','li ul a',function(){
		sessionStorage['navlevel1']=$(this).parent().parent().parent().index();
		sessionStorage['navlevel2']=$(this).parent().index();
	})

	$('.navbar-fixed-top .dropdown-user a').click(function(){
		sessionStorage.clear();
	})
	setMenuActive()
})

</script>

<div id="wrapper">
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top main-top" role="navigation" style="margin-bottom: 0">
        <img class="toplogo" alt="" src="images/logo1.png">
        <div class="navbar-brand" style="margin-left: 90px">全域骑游欢迎您登录</div>
            <div class="navbar-header">
           
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
               <!-- <img style="width: 50px;margin-left: 20px" alt="" src="images/logo.jpg">  -->
            </div>
            <!-- /.navbar-header -->
            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <!-- <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-envelope fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a> -->
                    <!-- <ul class="dropdown-menu dropdown-messages">
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <strong>John Smith</strong>
                                    <span class="pull-right text-muted">
                                        <em>Yesterday</em>
                                    </span>
                                </div>
                                <div>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eleifend...</div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>Read All Messages</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul> -->
                    <!-- /.dropdown-messages -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                   <!--  <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-tasks fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a> -->
                    <!-- <ul class="dropdown-menu dropdown-tasks">
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 1</strong>
                                        <span class="pull-right text-muted">40% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                                            <span class="sr-only">40% Complete (success)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 2</strong>
                                        <span class="pull-right text-muted">20% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
                                            <span class="sr-only">20% Complete</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 3</strong>
                                        <span class="pull-right text-muted">60% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                                            <span class="sr-only">60% Complete (warning)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <p>
                                        <strong>Task 4</strong>
                                        <span class="pull-right text-muted">80% Complete</span>
                                    </p>
                                    <div class="progress progress-striped active">
                                        <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
                                            <span class="sr-only">80% Complete (danger)</span>
                                        </div>
                                    </div>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Tasks</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul> -->
                    <!-- /.dropdown-tasks -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <!-- <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a> -->
                    <!-- <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul> -->
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <!-- <li><a href="cms/admin/gotoInfo.action"><i class="fa fa-user fa-fw"></i> 用户信息</a>
                        </li> -->
                        <li><a href="cms/admin/gotoPwd.action"><i class="fa fa-gear fa-fw"></i> 修改密码</a>
                        </li> 
                        <li class="divider"></li>
                        <li><a href="cms/admin/logout.action"><i class="fa fa-sign-out fa-fw"></i> 注销</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <!-- <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div>
                            /input-group
                        </li> -->
                        <shiro:hasPermission name="userMng">
                        	<li>
	                            <a href="#"><i class="fa fa-users fa-fw"></i> 用户管理<div id="allTip" class="badge" ></div><span class="fa arrow"></span></a>
	                             <ul class="nav nav-second-level">
	                             <shiro:hasPermission name="userListMng">
	                             		<li>
		                                    <a href="cms/user/list.action">用户列表</a>
		                                </li>
		                          </shiro:hasPermission>
		                          <shiro:hasPermission name="villagerListMng">
	                             		<li>
		                                    <a href="cms/user/villagerlist.action">会员列表</a>
		                                </li>
		                          </shiro:hasPermission>
		                          <shiro:hasPermission name="accountListMng">
		                                <li>
		                                    <a href="cms/user/account/list.action">账户信息</a>
		                                </li>
		                          </shiro:hasPermission>  
		                          <shiro:hasPermission name="blacklistMng">   
		                                <li>
		                                    <a href="cms/user/blackList.action?userIsblacklist=0">黑名单</a>
		                                </li>
		                          </shiro:hasPermission>      
		                          <shiro:hasPermission name="userFeedbackMng">      
		                                <li>
		                                    <a href="cms/user/feedback/list.action">用户使用车辆反馈<div id="bikeFeed" class="badge" style=" background-color:#fa4f3e; "></div></a>
		                                </li>
		                          </shiro:hasPermission> 
		                          <shiro:hasPermission name="userFeedbackQuestion">      
		                                <li>
		                                    <a href="cms/user/feedback/questionList.action">用户问题反馈<div id="feedback" class="badge" ></div></a>
		                                </li>
		                          </shiro:hasPermission>      
		                          <shiro:hasPermission name="userInsuranceMng">
		                                 <li>
		                                    <a href="cms/user/insurance/list.action">用户保险申请<div id="insurance" class="badge" ></div></a>
		                                </li>
		                          </shiro:hasPermission> 
		                          <shiro:hasPermission name="userGuideMng">     
		                          <li>
		                               <a href="cms/user/guide/list.action">用户指南</a>
		                          </li>
		                          </shiro:hasPermission>
	                             </ul>
	                            <!-- /.nav-second-level -->
                        	</li>
                        	</shiro:hasPermission>
                        	<shiro:hasPermission name="bikeMng">
                        	<li>
	                            <a href="#"><i class="fa fa-edit fa-fw"></i> 车辆管理<div id="return" class="badge" ></div><span class="fa arrow"></span></a>
	                             <ul class="nav nav-second-level">
	                             <shiro:hasPermission name="bikeListMng">
		                            	<li>
		                                    <a href="cms/bike/bikeList.action">车辆列表</a>
		                                </li>
		                          </shiro:hasPermission>       
		                         <shiro:hasPermission name="bikeMaintainMng">       
		                                <li>
		                                    <a href="cms/bikeFixInfo/bikeFixInfoList.action">维护信息</a>
		                                </li>
		                          </shiro:hasPermission>       
		                         <shiro:hasPermission name="modelsListMng">       
		                                <li>
		                                    <a href="cms/models/modelsList.action">车型列表</a>
		                                </li>
                               	  </shiro:hasPermission>
                               	  <shiro:hasPermission name="fixedReturnMng">
                               	        <li>
		                                    <a href="cms/fixedReturn/fixedReturnList.action">还车点列表</a>
		                                </li>
                               	  </shiro:hasPermission>
                               	  <shiro:hasPermission name="fixedCheckMng">
                               	        <li>
		                                    <a href="cms/fixedReturn/fixedCheckList.action">还车点审核列表<div id="fixReturncount" class="badge" ></div></a>
		                                </li>
                               	  </shiro:hasPermission>
                               	  <shiro:hasPermission name="insurancePriceMng">
                               	        <li>
		                                    <a href="cms/insurancePrice/list.action">保险费用列表</a>
		                                </li>
                               	  </shiro:hasPermission>
                               	  <shiro:hasPermission name="qrCodeMng">
                               	   <li>
		                               <a href="cms/bike/bikeQrList.action">二维码导出</a>
		                           </li>
		                           </shiro:hasPermission>
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>
	                        <shiro:hasPermission name="orderMng">
                        	<li>
	                            <a href="#"><i class="fa fa-table fa-fw"></i> 租赁订单管理<span class="fa arrow"></span></a>
	                             <ul class="nav nav-second-level">
	                             <shiro:hasPermission name="orderListMng">
	                                <li>
	                                    <a href="cms/order/orderList.action?flag=0">租赁订单列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                             <shiro:hasPermission name="borrowBikeMng">   
	                                 <li>
	                                    <a href="cms/order/orderList.action?flag=1">借车信息</a>
	                                </li>
	                             </shiro:hasPermission>   
	                             <shiro:hasPermission name="returnBikeMng">   
	                                <li>
	                                    <a href="cms/order/orderList.action?flag=2&rentState=1">还车信息</a>
	                                </li>
	                             </shiro:hasPermission>   
<!-- 	                                <li> -->
<!-- 	                                    <a href="cms/order/rentMoney.action">租赁费用列表</a> -->
<!-- 	                                </li> -->
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>

							<shiro:hasPermission name="guideAgencyMng">
								<li>
									<a href="#"><i class="fa fa-flag fa-fw"></i> 旅行社管理<span class="fa arrow"></span></a>
									<ul class="nav nav-second-level">
                                        <shiro:hasPermission name="guideAgencyManageMng">
                                            <li>
                                                <a href="cms/guideAgencyManage/list.action">管理员列表</a>
                                            </li>
                                        </shiro:hasPermission>
										<shiro:hasPermission name="guideAgencyListMng">
											<li>
												<a href="cms/guideAgency/list.action">旅行社列表</a>
											</li>
										</shiro:hasPermission>
                                        <shiro:hasPermission name="guideGroupMng">
                                            <li>
                                                <a href="#"><i class="fa fa-camera fa-fw"></i> 旅游团管理<span class="fa arrow"></span></a>
                                                <ul class="nav nav-second-level">
                                                    <shiro:hasPermission name="guideGroupListMng">
                                                        <li>
                                                            <a href="cms/guideGroup/list.action">旅游团列表</a>
                                                        </li>
                                                    </shiro:hasPermission>
                                                    <shiro:hasPermission name="guideRidingStatsMng">
                                                        <li>
                                                            <a href="cms/guideGroup/ridingStats.action">骑行统计</a>
                                                        </li>
                                                    </shiro:hasPermission>
                                                </ul>
                                            </li>
                                        </shiro:hasPermission>
									</ul>
								</li>
							</shiro:hasPermission>


							<shiro:hasPermission name="newCouponMng">
								<li>
									<a href="#"><i class="fa fa-compass fa-fw"></i> 骑行券管理<span class="fa arrow"></span></a>
									<ul class="nav nav-second-level">
										<shiro:hasPermission name="couponListMng">
											<li>
												<a href="cms/couponList/list.action">骑行券列表</a>
											</li>
										</shiro:hasPermission>
										<shiro:hasPermission name="couponSchemeMng">
											<li>
												<a href="cms/couponScheme/list.action">骑行券方案列表</a>
											</li>
										</shiro:hasPermission>
										<shiro:hasPermission name="couponUseMng">
											<li>
												<a href="cms/couponUse/list.action">骑行券兑换列表</a>
											</li>
										</shiro:hasPermission>
									</ul>
									<!-- /.nav-second-level -->
								</li>
							</shiro:hasPermission>


	                        <shiro:hasPermission name="activityMng">
                        	<li>
	                            <a href="#"><i class="fa fa-gavel fa-fw"></i> 活动管理<span class="fa arrow"></span></a>
	                             <ul class="nav nav-second-level">
	                             <shiro:hasPermission name="activityListMng">
	                                <li>
	                                    <a href="cms/activity/list.action">活动列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                            </ul>
	                        </li>
	                        </shiro:hasPermission>
	                        
	                        <shiro:hasPermission name="bannerMng">
                        	<li>
	                            <a href="#"><i class="fa fa-photo fa-fw"></i> 横幅管理<span class="fa arrow"></span></a>
	                             <ul class="nav nav-second-level">
	                             <shiro:hasPermission name="bannerListMng">
	                                <li>
	                                    <a href="cms/banner/list.action">横幅列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                            </ul>
	                        </li>
	                        </shiro:hasPermission>
	                        
	                        <shiro:hasPermission name="hotWordMng">
                        	<li>
	                            <a href="#"><i class="fa fa-heart fa-fw"></i> 热词管理<span class="fa arrow"></span></a>
	                             <ul class="nav nav-second-level">
	                             <shiro:hasPermission name="hotWordListMng">
	                                <li>
	                                    <a href="cms/hotword/list.action">热词列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                            </ul>
	                        </li>
	                        </shiro:hasPermission>
	                        
	                        <shiro:hasPermission name="scenicMng">
                        	<li>
	                            <a href="#"><i class="fa fa-institution fa-fw"></i> 景点管理<span class="fa arrow"></span></a>
	                             <ul class="nav nav-second-level">
	                             <shiro:hasPermission name="packageListMng">
	                                <li>
	                                    <a href="cms/scenicspot/sceniclist.action">景点列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                             <shiro:hasPermission name="packageListMng">
	                                <li>
	                                    <a href="cms/package/list.action">套餐列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                            </ul>
	                        </li>
	                        </shiro:hasPermission>
	                        
	                         <shiro:hasPermission name="couponMng">
                        	<li>
	                            <a href="#"><i class="fa fa-compass fa-fw"></i> 代金券管理<span class="fa arrow"></span></a>
	                             <ul class="nav nav-second-level">
	                             <shiro:hasPermission name="cashCouponMng">
	                                <li>
	                                    <a href="cms/cashcoupon/cashCouponList.action">代金券列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                             <shiro:hasPermission name="couponPlanMng">   
	                                 <li>
	                                    <a href="cms/couponplan/couponPlanList.action">代金券方案列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                             <shiro:hasPermission name="redeemPlanMng">   
	                                <li>
	                                    <a href="cms/redeemplan/redeemPlanList.action">兑换方案列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                             <shiro:hasPermission name="redeemRecordMng">   
	                                <li>
	                                    <a href="cms/redeemrecord/redeemRecordList.action">兑换记录列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                             <shiro:hasPermission name="registPlanMng">   
	                                <li>
	                                    <a href="cms/registplan/registPlanList.action">注册方案列表</a>
	                                </li>
	                             </shiro:hasPermission>   
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>
	                        
<%-- 	                        <shiro:hasPermission name="appointmentMng"> --%>
<!--                         	<li> -->
<!-- 	                            <a href="#"><i class="fa fa-bell fa-fw"></i> 预约管理<span class="fa arrow"></span></a> -->
<!-- 	                            <ul class="nav nav-second-level"> -->
<!-- 	                            		<li> -->
<!-- 		                                    <a href="cms/appointment/appointmentList.action">预约列表</a> -->
<!-- 		                                </li> -->
<!-- 	                                	<li> -->
<!-- 		                                    <a href="cms/appointment/appointmentPlanList.action">预约设置</a> -->
<!-- 		                                </li> -->
<!-- 	                            </ul> -->
<!-- 	                            /.nav-second-level -->
<!-- 	                        </li> -->
<%-- 	                        </shiro:hasPermission> --%>
<%-- 	                        <shiro:hasPermission name="rechargeMng"> --%>
<!-- 	                        <li> -->
<!-- 	                            <a href="#"><i class="fa fa-envelope fa-fw"></i> 充值方案管理<span class="fa arrow"></span></a> -->
<!-- 	                            <ul class="nav nav-second-level"> -->
<!-- 	                            		<li> -->
<!-- 		                                    <a href="cms/rechargeplan/rechargePlanList.action">充值方案列表</a> -->
<!-- 		                                </li> -->
<!-- 	                                	<li> -->
<!-- 		                                    <a href="cms/rechargeplan/editpContentJsp.action">优惠内容修改</a> -->
<!-- 		                                </li> -->
<!-- 	                            </ul> -->
<!-- 	                            /.nav-second-level -->
<!-- 	                        </li> -->
<%-- 	                        </shiro:hasPermission> --%>
<%-- 	                        <shiro:hasPermission name="activityMng"> --%>
<!-- 	                        <li> -->
<!-- 	                            <a href="#"><i class="fa fa-envelope fa-fw"></i> 活动管理<span class="fa arrow"></span></a> -->
<!-- 	                            <ul class="nav nav-second-level"> -->
<!-- 	                            		<li> -->
<!-- 		                                    <a href="cms/activity/giftJsp.action">体验金赠送</a> -->
<!-- 		                                </li> -->
<!-- 	                            </ul> -->
<!-- 	                            /.nav-second-level -->
<!-- 	                        </li> -->
<%-- 	                        </shiro:hasPermission> --%>
	                        <shiro:hasPermission name="dailyUser">
	                        <li>
	                            <a href="#"><i class="fa fa-envelope fa-fw"></i> 每日用户统计<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">
	                            <shiro:hasPermission name="dailyAddUserMng">
	                            		<li>
		                                    <a href="cms/user/userCount.action">每日新增用户</a>
		                                </li>
		                        </shiro:hasPermission>        
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>
	                        <shiro:hasPermission name="moneyFlow">
	                         <li>
	                            <a href="#"><i class="fa fa-crop fa-fw"></i> 资金流水<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">
	                            <shiro:hasPermission name="orderFlowMng">
	                                    <li>
			                               <a href="cms/moneyWater/rentCountMoneyWater.action">租赁结算流水</a>
			                            </li>
	                            		<li>
			                               <a href="cms/moneyWater/rentMoneyWater.action">租赁流水</a>
			                            </li>
			                    </shiro:hasPermission>
			                    <shiro:hasPermission name="tRefundFlowMng">        
	                                	<li>
		                                   <a href="cms/moneyWater/moneyLogList.action">充值退款流水</a>
		                                </li> 
		                        </shiro:hasPermission>
		                        <shiro:hasPermission name="insuranceFlowMng">        
		                                <li>
		                                   <a href="cms/moneyWater/insuranceLogList.action">保险费用流水</a>
		                                </li> 
		                        </shiro:hasPermission>        
<!-- 		                                <li> -->
<!-- 		                                    <a href="cms/firstRide/list.action">第一次扫码车辆统计</a> -->
<!-- 		                                </li> -->
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>
	                        <shiro:hasPermission name="invoiceMng">
	                        <li>
	                            <a href="#"><i class="fa fa-lightbulb-o fa-fw"></i> 发票管理<div id="invoice" class="badge" ></div><span class="fa arrow"></span></a> 
	                            <ul class="nav nav-second-level">
	                            <shiro:hasPermission name="invoiceListMng">
	                            		<li>
		                                    <a href="cms/invoice/list.action">发票列表<div id="invoicecount" class="badge" ></div></a>
		                                </li>
		                        </shiro:hasPermission>
	                                	<!-- <li>
		                                    <a href="cms/channel/rentMoney.action">租赁费用列表</a>
		                                </li> -->
	                            </ul>
	                        </li>
	                        </shiro:hasPermission>
	                        <shiro:hasPermission name="channelMng">
	                         <li>
	                            <a href="#"><i class="fa fa-road fa-fw"></i> 渠道管理<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">
	                            <shiro:hasPermission name="channelListMng">
	                            		<li>
		                                    <a href="cms/channel/list.action">渠道列表</a>
		                                </li>
		                        </shiro:hasPermission>
	                                	<!-- <li>
		                                    <a href="cms/channel/rentMoney.action">租赁费用列表</a>
		                                </li> -->
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>
	                        
	                       
	                         <shiro:hasPermission name="messageMng">
	                        <li>
	                            <a href="#"><i class="fa fa-newspaper-o fa-fw"></i> 消息管理<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">
	                            <shiro:hasPermission name="messageListMng">
	                            		<li>
		                                    <a href="cms/sysMsg/list.action">消息列表</a>
		                                </li>
		                        </shiro:hasPermission>
		                         <shiro:hasPermission name="shortmessageListMng">
	                            		<li>
		                                    <a href="cms/sysMsg/shortMessageList.action">短信列表</a>
		                                </li>
		                        </shiro:hasPermission>
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>
	                        <shiro:hasPermission name="refundMng">
	                        <li>
	                            <a href="#"><i class="fa fa-reply fa-fw"></i> 退款管理<div id="refund" class="badge" ></div><span class="fa arrow" ></span></a>
	                            <ul class="nav nav-second-level">
	                            <shiro:hasPermission name="refundListMng">
	                            	    <li>
		                                    <a href="cms/refund/refundList.action">退款列表<div id="refund1" class="badge" ></div></a>
		                                </li>
		                        </shiro:hasPermission>
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>
	                        
	                        <shiro:hasPermission name="lockDebug">
	                        <li>
	                            <a href="#"><i class="fa fa-key fa-fw"></i> 车锁管理<div id="allLock" class="badge" ></div><span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">
	                            		<li>
		                                    <a href="cms/lock/list.action?flag=1">车锁列表<svg style="position: relative;top: 5px;left: 5px;  margin-left: 50px;" viewBox="0 0 1024 1024" width="20" height="20"><defs><style type="text/css"></style></defs><path d="M144.7 684.994h167.18V359.238H144.7v325.756z m773.674-244.313v-81.443c0-44.791-36.65-81.438-81.438-81.438H144.7c-44.791 0-81.438 36.647-81.438 81.438v325.756c0 44.791 36.647 81.443 81.438 81.443h692.236c44.788 0 81.438-36.65 81.438-81.443v-81.438c22.396 0 40.72-18.322 40.72-40.72V481.4c0-22.396-18.324-40.72-40.72-40.72z m-40.719 40.72V684.993c0 22.395-18.323 40.718-40.719 40.718H144.7c-22.396 0-40.72-18.323-40.72-40.718V359.238c0-22.396 18.324-40.72 40.72-40.72h692.236c22.396 0 40.72 18.324 40.72 40.72V481.4z m0 0" p-id="13561" fill="#d81e06"></path></svg><span id="lockcount2" style="color: red; position: relative;bottom:4px;left:5px"></span><div id="refund" class="badge" style="float:right; background-color:#fa4f3e; margin-right:10px;"></div><svg style="position: relative;top: 2px;left: 5px;margin-left: 10px;" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" t="1495610707605" class="icon" style="" viewBox="0 0 1024 1024" version="1.1" p-id="1717" width="20" height="17"><defs><style type="text/css"/></defs><path d="M505.6 659.2c-51.2 0-102.4 19.2-134.4 51.2-12.8 12.8-32 12.8-44.8 0-19.2-12.8-12.8-38.4 0-57.6 32-25.6 76.8-44.8 121.6-57.6l57.6 64zM288 448c-44.8 19.2-83.2 44.8-115.2 76.8-12.8 12.8-12.8 38.4 0 51.2 12.8 12.8 38.4 12.8 44.8 0 38.4-32 76.8-57.6 121.6-76.8L288 448zM128 288C89.6 313.6 44.8 345.6 12.8 384c-12.8 12.8-12.8 38.4 0 51.2 12.8 12.8 32 12.8 44.8 0 38.4-38.4 76.8-70.4 121.6-96L128 288zM992 384c-128-128-300.8-204.8-486.4-204.8-76.8 0-147.2 12.8-211.2 32l57.6 57.6c44.8-12.8 96-19.2 153.6-19.2 172.8 0 326.4 70.4 441.6 179.2 12.8 19.2 32 19.2 44.8 0 12.8-6.4 19.2-32 0-44.8z m-486.4 12.8h-25.6l76.8 76.8c83.2 6.4 166.4 44.8 224 102.4 12.8 12.8 32 12.8 44.8 0 19.2-12.8 19.2-38.4 0-51.2-83.2-76.8-198.4-128-320-128z m0 409.6c-38.4 0-70.4 32-70.4 70.4 0 38.4 32 70.4 70.4 70.4S576 915.2 576 876.8c0-44.8-32-70.4-70.4-70.4z m192-121.6l-64-64-153.6-153.6-64-64-115.2-115.2-57.6-57.6-38.4-38.4c-19.2-12.8-38.4-12.8-51.2 0-12.8 12.8-12.8 38.4 0 51.2l19.2 19.2 51.2 51.2 140.8 140.8 25.6 25.6 288 288c12.8 12.8 38.4 12.8 51.2 0 12.8-12.8 12.8-38.4 0-51.2l-32-32z" fill="#d81e06" p-id="1718"/></svg><span id="lockcount1" style="color: red;position: relative;bottom:4px;left:5px"></span></a>
		                                </li>
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>
<%-- 	                        <shiro:hasPermission name="moneyMng"> --%>
<!-- 	                        <li> -->
<!-- 	                            <a href="#"><i class="fa fa-money fa-fw"></i> 资金流管理<span class="fa arrow"></span></a> -->
<!-- 	                            <ul class="nav nav-second-level"> -->
	                            		
<!-- 	                            </ul> -->
<!-- 	                            /.nav-second-level -->
<!-- 	                        </li> -->
<%-- 	                        </shiro:hasPermission> --%>
	                        <shiro:hasPermission name="adminMng">
	                        <li>
	                            <a href="#"><i class="fa fa-user fa-fw"></i> 管理员管理<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">
	                            		<li>
		                                    <a href="cms/admin/adminList.action">管理员列表</a>
		                                </li>
	                            </ul>
	                            <!-- /.nav-second-level -->
	                        </li>
							</shiro:hasPermission>
	                         <shiro:hasPermission name="systemMng">
	                        <li>
	                            <a href="#"><i class="fa fa-wrench fa-fw"></i> 系统管理<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">
	                            <shiro:hasPermission name="roleManagementMng">
		                                <li>
		                                    <a href="cms/role/roleJsp.action">角色管理</a>
		                                </li>
		                        </shiro:hasPermission>     
		                        <shiro:hasPermission name="editpwdButton">   
		                                <li>
		                                    <a href="cms/admin/gotoPwd.action">密码修改</a>
		                                </li>
		                        </shiro:hasPermission>
		                        <shiro:hasPermission name="versionMng">   
		                         <li>
                                    <a href="cms/app/version.action">APP版本</a>
                                </li>
                                </shiro:hasPermission>
		                        <shiro:hasPermission name="operateLogList">   
		                                <li>
		                                    <a href="cms/operate/list.action">操作日志</a>
		                                </li>
		                        </shiro:hasPermission>
	                            </ul>
	                            
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>
	                        <shiro:hasPermission name="manualMng">
	                        <li>
	                            <a href="#"><i class="fa fa-bars fa-fw"></i> 系统使用手册<span class="fa arrow"></span></a>
	                            <ul class="nav nav-second-level">
	                              <shiro:hasPermission name="customServiceManual">
	                                <li>
	                                    <a href="kefu.pdf">客服使用手册</a>
	                                </li>
	                              </shiro:hasPermission>
	                              <shiro:hasPermission name="operationManual">
	                                <li>
	                                    <a href="yunyin.pdf">运营使用手册</a>
	                                </li>
	                              </shiro:hasPermission>
	                              <shiro:hasPermission name="managerManual">
	                                <li>
	                                    <a href="guanliyuan.pdf">管理员使用手册</a>
	                                </li>
	                              </shiro:hasPermission>
	                              <shiro:hasPermission name="bikeAndLockManual">  
	                                <li>
	                                    <a href="cheliang.pdf">车辆车锁管理员使用手册</a>
	                                </li>
	                              </shiro:hasPermission>  
	                            </ul>
	                            
	                            <!-- /.nav-second-level -->
	                        </li>
	                        </shiro:hasPermission>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
    </div>
    <!-- /#wrapper -->
</html>
