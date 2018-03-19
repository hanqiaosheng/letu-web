<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>全域骑游</title>
</head>
<script type="text/javascript">
    var host = "https://"+window.location.host; //测试
    var otherFlag = '${otherFlag}';
    var user = '${user}';
    var tel = '${user.userTel}';
    var idCard = '${user.userIdcard}';
    var tourl = '${tourl}';
    var groupId = '${groupId}';
    var couponSchemeId = '${couponSchemeId}';
    if("fromGuide" == otherFlag){
            window.location.href = host + "/html/registFromGuide.html?otherFlag=fromGuide&groupId=" + groupId
                + "&couponSchemeId=" + couponSchemeId+ "&regist=true"
    }else if(user==""||tel==""){
        window.location.href=host+'/html/regist.html';
    }else{
        if(idCard == ""){
            window.location.href=host+'/html/realName.html';
        }else{
            if(tourl!=""){
                window.location.href=host+'/journey_detail.html?platform=h5/#'+tourl;
            }else{
                if("center"==otherFlag){
                    window.location.href=host+'/html/user.html';
                }else{
                    window.location.href=host+'/html/watchbike.html';
                }
            }
        }

    }

</script>
<body>
</body>
</html>