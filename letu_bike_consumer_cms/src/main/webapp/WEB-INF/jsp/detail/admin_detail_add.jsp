<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<link rel="stylesheet" href="assets/dialog.css">
<script type="text/javascript" src="assets/dialog.js"></script>
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">添加管理员</h1>
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
							<form role="form" action="cms/admin/adminAdd.action" onsubmit="return formSub()" method="post">
								<div class="form-group">
									<label>管理员名：</label>
									<div class="form-group">
										<input class="form-control" placeholder="用户名" onblur="checkName()" id="adminUsername" name="adminUsername"  type="text"
											required="required">
									</div>
									<label>姓名：</label>

									<div class="form-group">
										<input class="form-control" placeholder="姓名" type="text"
											   name="adminRealname" required="required">
									</div>

									<label>密码：</label>
									<div class="form-group">
										<input class="form-control" placeholder="密码" type="password"
												name="adminPwd" id="adminPwd" required="required">
									</div>

									<label>确认密码：</label>
									<div class="form-group">
										<input class="form-control" placeholder="再次输入密码" type="password"
											   name="adminPwd2" id="adminPwd2" onblur="checkPwd()"  required="required">
									</div>

									<label>电话：</label>
									<div class="form-group">
										<input class="form-control" id="adminTel" onblur="checkMobile()" maxlength="11" placeholder="电话" type="tel"
											   name="adminTel" required="required">
									</div>

									<label>身份证:</label>
									<div class="">
										<input class="form-control" id="adminIdCard" onblur="checkIDCard()" maxlength="18" minlength="15" placeholder="身份证"
											   type="text" name="adminIdCard" required="required">
									</div>
									<c:choose>
									  <c:when test="${empty level||level==null}">
									  <div class="form-group">
										<label>渠道：</label>
										<select name="adminChannelId" id="channelId" class="form-control">
										<option value="-1">超级管理员</option>
										<c:forEach items="${channelList }" var="channel">
										<option value="${channel.channelId }">${channel.channelName }</option>
										</c:forEach>
										</select>
										</div>
									  </c:when>
									  <c:otherwise>
									  <input type="hidden" value="${level }" name="adminChannelId">
									  </c:otherwise>
									</c:choose>

									<label>邮箱：</label>
									<div class="form-group">
										<input class="form-control" id="adminCreateEmail" onblur="checkEmail()" placeholder="邮箱"  name="adminCreateEmail" required="required">
									</div>

									<div class="form-group">
										<label>角色分配：</label>
										<div class="checkbox">
											<c:forEach items="${roleList }" var="role">
												<label>
													<input type="checkbox" onclick="checkRole(this)"  name="roleId" value="${role.roleId }">${role.roleName }
												</label>
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
<script type="text/javascript">
var uName = false;
var phoneFlag =false;
var emailFlag =false;
var pwdFlag =false;
var idFlag = true;
function checkName(){
	var str = "adminUsername="+$('#adminUsername').val();
	$.ajax({
			type:"post",
			url:"cms/admin/checkAdminName.action",
			dataType:"json",
			data:str,
			success:function(data){
				if(data.message != "success"){
					$.alert("用户名已存在");
					uName = false;
				}else{
					uName = true;
				}
			}
		});
} 

function checkPwd(){
	var pwd = $("#adminPwd").val();
	var pwd2 =$("#adminPwd2").val();
	if(pwd!=pwd2){
		pwdFlag = false;
		$.alert("两次输入密码不一致！");
	}
	
}



//手机验证	
	function checkMobile(){
		var adminTel = $("#adminTel").val();
/* 	    if(isNaN(adminTel)||(adminTel.length!=11)){ 
	        $.alert("手机号码为11位数字！请正确填写！",true,function(){
	        },false,{className:'ui-alert',width:300});
	        phoneFlag =  false; 
	    }  */
	    var reg =/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	    if(!reg.test(adminTel)) 
	    { 
	        $.alert("您的手机号码不是正常号码，请重新输入！");
	        phoneFlag =  false; 
	    } 
	    phoneFlag =  true;
	}

//身份证验证
function checkIDCard() {
    var city={11:"北京",
		12:"天津", 13:"河北", 14:"山西",
		15:"内蒙古", 21:"辽宁", 22:"吉林",
		23:"黑龙江 ",31:"上海",32:"江苏",
		33:"浙江",34:"安徽",35:"福建",
		36:"江西",37:"山东",41:"河南",
		42:"湖北 ",43:"湖南",44:"广东",
		45:"广西",46:"海南",50:"重庆",
		51:"四川",52:"贵州",53:"云南",
		54:"西藏 ",61:"陕西",62:"甘肃",
		63:"青海",64:"宁夏",65:"新疆",
		71:"台湾",81:"香港",82:"澳门",91:"国外 "};
    var idCard = $("#adminIdCard").val();

    var tip = "";

    var reg = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i;
    if(!reg.test(idCard)){
        tip = "身份证号格式错误";
        idFlag = false;
    }else if(!city[code.substr(0,2)]){
        tip = "地址编码错误";
        idFlag = false;
    } else{
        //18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++)
            {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                tip = "校验位错误";
                idFlag =false;
            }
        }
    }
    if(!idFlag) alert(tip);
}

function checkEmail(){
	var email =  $("#adminCreateEmail").val();
	var Reg=/^[_a-z 0-9]+@([_a-z 0-9]+\.)+[a-z 0-9]{2,3}$/;   //正则验证邮箱格式
	if(!Reg.test(email))
	{ 
    $.alert("请正确填写邮箱地址!");
	emailFlag = false;
	}else{
		emailFlag = true;
	}
}

function formSub(){
	 if(!uName){
		 $.alert("用戶名已存在!");
		return false;
	}
	 if(!phoneFlag){
		 $.alert("填写正确的联系方式!");
			return false;
		}
	 if(!emailFlag){
			$.alert("填写正确的邮箱!");
			return false;
		}
	 
	 if(!pwdFlag()){
		 	$.alert("两次密码不一致!");
			return false;
	 }
	 if(!idFlag){
	     $.alert("请填写正确的身份证!");
	 }
	return true;
} 
</script>
<%@include file="../common/buttom.jsp"%>
<link rel="stylesheet" href="assets/selectSearch/select2.min.css">
<script type="text/javascript" src="assets/selectSearch/select2.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#channelId").select2();
    })
</script>
</html>