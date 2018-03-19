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
        <div class="navbar-brand" style="margin-left: 105px">全域智能骑游报表系统欢迎您登录</div>
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
                       <!--  <li><a href="cms/admin/gotoInfo.action"><i class="fa fa-user fa-fw"></i> 用户信息</a>
                        </li>
                        <li><a href="cms/admin/gotoPwd.action"><i class="fa fa-gear fa-fw"></i> 修改密码</a>
                        </li>  -->
                        <li class="divider"></li>
                        <li><a href="report/admin/logout.action"><i class="fa fa-sign-out fa-fw"></i> 注销</a>
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
                        <li>
	                       <a href="#"><i class="fa fa-bar-chart fa-fw"></i> 首页<div id="allTip" class="badge" ></div><span class="fa arrow"></span></a>
	                       <ul class="nav nav-second-level">
	                        <li>
		                       <a href="report/admin/main.action">首页</a>
		                    </li>
		                    <li>
		                       <a href="report/admin/myiframe.action">可视化数据报表</a>
		                    </li>
		                    
	                       </ul>
                        </li>
                        <shiro:hasPermission name="reportMng">
                        	<li>
	                            <a href="#"><i class="fa fa-folder fa-fw"></i> 报表管理<div id="allTip" class="badge" ></div><span class="fa arrow"></span></a>
	                             <ul class="nav nav-second-level">
	                             <shiro:hasPermission name="userRegistReport">
	                             		<li>
		                                    <a href="report/user/list.action">注册用户报表</a>
		                                </li>
		                          </shiro:hasPermission>
		                          <shiro:hasPermission name="bikeRentReport">
	                             		<li>
		                                    <a href="report/bikeRentInfo/bikeRentInfoList.action">车辆骑行报表</a>
		                                </li>
                                        <li>
		                                    <a href="report/bikeRentInfo/rentInfoList.action">租赁信息列表</a>
		                                </li>
		                          </shiro:hasPermission>
		                          <shiro:hasPermission name="totalMoneyReport">
		                                <li>
		                                    <a href="report/moneyWater/totalMoneyList.action">平台总收益报表</a>
		                                </li>
		                          </shiro:hasPermission>  
		                          <shiro:hasPermission name="depositReport">   
		                                <li>
		                                    <a href="report/moneyWater/depositList.action">预付款充值报表</a>
		                                </li>
		                          </shiro:hasPermission>      
		                          <shiro:hasPermission name="deRefundReport">      
		                                <li>
		                                    <a href="report/moneyWater/moneyLogList.action">预付款退款报表<div id="bikeFeed" class="badge" style=" background-color:#fa4f3e; "></div></a>
		                                </li>
		                          </shiro:hasPermission> 
		                          <shiro:hasPermission name="balanceReport">      
		                                <li>
		                                    <a href="report/moneyWater/balanceList.action">余额充值报表<div id="feedback" class="badge" ></div></a>
		                                </li>
		                          </shiro:hasPermission>      
		                          <shiro:hasPermission name="insuranceReport">
		                                 <li>
		                                    <a href="report/moneyWater/insuranceLogList.action">保险费用报表<div id="insurance" class="badge" ></div></a>
		                                </li>
		                          </shiro:hasPermission> 
		                          <shiro:hasPermission name="rentConsumeReport">     
		                          <li>
		                               <a href="report/moneyWater/rentMoneyWater.action">租赁消费报表</a>
		                          </li>
		                          </shiro:hasPermission>
		                          <shiro:hasPermission name="newRentUserReport"> 
		                          <li>
		                               <a href="report/user/newUserList.action">新增租车用户统计报表</a>
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
