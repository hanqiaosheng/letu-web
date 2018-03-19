
Date.prototype.formatCN=function(format){
	var year=this.getFullYear(),nyear=new Date().getFullYear();
	  if(nyear>year){
		  return this.format('yyyy-MM-dd hh:mm:ss');
	  }
	return this.format('MM-dd hh:mm:ss');
	
//    var news=new Date().getTime();
//    var newsparse=news-this;
//    var num=31536000000;
//    if(newsparse>num){
//        return parseInt(newsparse/num)+'年前';
//    }
//    
//    num=2592000000
//    if(newsparse>num){
//        return parseInt(newsparse/num)+'月前';
//    }
//    num=86400000
//    if(newsparse>num){
//        var val=parseInt(newsparse/num)+'天前';
//        switch (Math.ceil(newsparse/num)) {
//          case 1:
//            val='昨天';
//            break;
//          case 2:
//            val='前天';
//            break;
//          default:
//        }
//        return val;
//    }
//    num=3600000
//    if(newsparse>num){
//        return parseInt(newsparse/num)+'小时前';
//    }
//    num=60000
//    if(newsparse>num){
//        return parseInt(newsparse/num)+'分钟前';
//    }
    
}

/**  
* js时间对象的格式化; 
* eg:format="yyyy-MM-dd hh:mm:ss";   
*/  
Date.prototype.format = function (format) {  
    var o = {  
        "M+": this.getMonth() + 1,  //month   
        "d+": this.getDate(),     //day   
        "h+": this.getHours(),    //hour   
        "m+": this.getMinutes(),  //minute   
        "s+": this.getSeconds(), //second   
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter   
        "S": this.getMilliseconds() //millisecond   
    }  
    var week=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];  
    if (/(y+)/.test(format)) {  
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
    }  
    if (/(w+)/.test(format)){  
        format = format.replace(RegExp.$1, week[this.getDay()]);  
    }  
    for (var k in o) {  
        if (new RegExp("(" + k + ")").test(format)) {  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
        }  
    }  
    return format;  
}  
   
/** 
*js中更改日期  
* y年， m月， d日， h小时， n分钟，s秒  
*/  
Date.prototype.add = function (part, value) {  
    value *= 1;  
    if (isNaN(value)) {  
        value = 0;  
    }  
    switch (part) {  
        case "y":  
            this.setFullYear(this.getFullYear() + value);  
            break;  
        case "m":  
            this.setMonth(this.getMonth() + value);  
            break;  
        case "d":  
            this.setDate(this.getDate() + value);  
            break;  
        case "h":  
            this.setHours(this.getHours() + value);  
            break;  
        case "n":  
            this.setMinutes(this.getMinutes() + value);  
            break;  
        case "s":  
            this.setSeconds(this.getSeconds() + value);  
            break;  
        default:  
   
    }
    return this
}

//alert(new Date().add("m", -1).format('yyyy-MM-dd hh:mm:ss')); //时间格式化使用方法 
$(function(){
    $('.surebtn').on({click:function(){
        if(!$(this).hasClass('btn-disabled')){
            rule.checkstate();//检查所有状态
            if(rule.allright){

                if(typeof(getStatesFn)!='undefined'){
                	var _this=$(this);
                	_this.addClass('btn-disabled')
                	setTimeout(function(){_this.removeClass('btn-disabled');},2000)
                    getStatesFn()
                }else{
                    rule.getStatesFn()
                }
                return
            }
        }  
    }},'a.btn'); 

    $('#getcodeJs img').click(function(){
        this.src=url+'/code.action?t='+new Date().getTime()
    }).trigger('click');
})


;(function($){
        $.getQueryString=function(name){
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
            var r = window.location.search.substr(1).match(reg); 
            if (r != null) return decodeURI(r[2]); return null; 
        },
        $.enterSend=function(arg){
            arg.main.keyup(function(e) {//回车搜索
                var keycode = (e.keyCode ? e.keyCode : e.which);
                if (keycode == '13') {
                    arg.action()
                }
            }); 
        },
        $.outputmoney=function(number){
            if(number==null){return '-';}
            function outputdollars(number){
                if (number.length <= 3){
                    return (number == '' ? '0' : number);
                }
                else {
                    var mod = number.length % 3;
                    var output = (mod == 0 ? '' : (number.substring(0, mod)));
                    for (i = 0; i < Math.floor(number.length / 3); i++) {
                        if ((mod == 0) && (i == 0))
                            output += number.substring(mod + 3 * i, mod + 3 * i + 3);
                        else
                            output += ',' + number.substring(mod + 3 * i, mod + 3 * i + 3);
                    }
                    return (output);
                }
            }
            function outputcents(amount){
                amount = Math.round(((amount) - Math.floor(amount)) * 100);
                return (amount < 10 ? '.0' + amount : '.' + amount);
            }
            number = String(number).replace(/\,/g, "");
            if(isNaN(number) || number == "")return "";
            number = Math.round(number * 100) / 100;
            if (number < 0)
                return '-' + outputdollars(Math.floor(Math.abs(number) - 0) + '') + outputcents(Math.abs(number) - 0);
            else
                return outputdollars(Math.floor(number - 0) + '') + outputcents(number - 0);        
        }

})($);

var  rule={
    getStatesFn:function(){
    	var formData = new FormData();
        var form=$('.g-form');
        $.each(form.find('[name]'),function(i,item){
        	//console.log($(this)[0].tagName)
        	if($(this).attr('type')=='file'){
        		formData.append("file",$(this)[0].files[0]);
        		return true;
        	}
        	formData.append($(this).attr('name'),$(this).val());
        	console.log($(this).attr('name'),$(this).val())
        })
        console.log(formData)
        var formsubmit=$('.surebtn .btn');
        var formtext=formsubmit.html();
        var defaults = {
                dialogmes: null,
                gotourl: '/html/user.html#slide3'
            };
        var options=$.extend(defaults, {
            dialogmes:form.data('dialogmes'),
            gotourl:$.getQueryString('fromurl') || form.data('gotourl')
        })
        function removeDis(){
            setTimeout(function(){formsubmit.removeClass('btn-disabled').html(formtext);},2000)
        };
        $.ajax({
            type: "POST",
            url:url+form.data('posturl'),
            data:formData,
            processData : false, 
            contentType : false,
            headers: {
            	'fromFlag': "2"
            },
            beforeSend: function () {formsubmit.addClass('btn-disabled').html('提交中...');},
            success: function(data) {
            	if("unlogin"==data){
            		$.alert("停留时间过长",false,function(){
                		window.location.href=url+"index/goIndex.action?path_url="+path_url;
                    },3000,{className:'ui-alert',width:300}); 
            	}else if("isblack"==data){
            		$.confirm('您的账号已被拉黑，请联系客服电话0571-87656065<br/>点击—><a href="defriend.html"> 了解详情</a>',[],function(type,e){
            			if(type=='no'){
            				this.hide();	
            		    }
            		    
            		},{width:300,className:'ui-dialog ui-alert showclose'});
            		return;
            	}else if("isdelete"==data){
            		$.confirm('您的账号已被删除，请联系客服电话0571-87656065',[],function(type,e){
            			if(type=='no'){
            				this.hide();	
            		    }
            		    
            		},{width:300,className:'ui-dialog ui-alert showclose'});
            		return;
            	}
            	else{
                removeDis();
                if(data.message=="success"){
                    if(options.dialogmes){
                    	if(options.gotourl=='journey_detail.html?platform=h5/#/user'){
                    		 $.alert(options.dialogmes,true,function(){
                                 location.href=data.data.gotoaction+options.gotourl;
                             },50000,{className:'ui-alert',width:300}); 
                    	}else{
                    		 $.alert(options.dialogmes,true,function(){
                                 location.href=url+options.gotourl;
                             },50000,{className:'ui-alert',width:300}); 
                    	}
                    	
                       
                        return;
                    }
                    window.location.href=url+options.gotourl;
                    
                }else if(data.message=="cango"){
                	$.alert("登录成功",false,function(){
                	window.location.href=url+"/html/watchbike.html";
                	 },1000,{width:300}); 
                }else if(data.message=="nocango"){
                	window.location.href=url+"/html/realName.html";
                }else{
                    var mess;
                    if(data.message=='code'){
                        mess='图片验证码有误';
                    }else if(data.message=='telCode'){
                        mess='短信验证码有误';
                    }else if(data.message=='paypwd'){
                        mess='交易密码错误';
                    }else{
						mess=data.message;
                    }
                    $('#getcodeJs img').attr('src',url+'/code.action?t='+new Date().getTime());
                    $.alert(mess,null,null,1200,{className:'favorpop'},false);
                }
            	}
            },
            error:function(){
                $.alert("系统繁忙，请稍后再试",null,null,1200,{className:'favorpop'},false);
                removeDis();
            }
        });
    },
    allright:true,
    erroralert:function(obj,text) {
        obj.data('group-state',false);

        $.alert(text,null,null,1200,{className:'favorpop'},false);
    },
    success:function(obj) {
        obj.data('group-state',true);
    },
    checkstate:function() {//检查所有状态
        rule.allright=true; 
        $.each($('*[data-group-state]'),function (item) {
        	if($(this).attr('onblur')){
                eval($(this).attr('onblur'));
        	}else{
        		eval($(this).data('isblur'));
        	}
            if(!$(this).data('group-state')){
                rule.allright=false; 
                return false;
            }

        })
        return rule.allright;
    },
    empty:function(obj,mess){//不能为空
        var str=obj.val().replace(/(^\s+)|(\s+$)/g,"");
        if(str==''){
            this.erroralert(obj,mess)
        }else{
            this.success(obj);
        }   
    },
    phone:function(obj,callback) {//手机号校验
        var myReg = /^(13[0-9]|15[012356789]|16[6]|17[0-9]|18[0-9]|19[89]|14[5678])[0-9]{8}$/;
        if (!myReg.test(obj.val())) {
            this.erroralert(obj,'手机号格式不正确')
        }else{
            if(callback){
                callback(obj)
            }else{
                this.success(obj);
            };
        }
    },
    password:function(obj,obj2) {//密码校验
        var myReg = /^[0-9a-zA-Z]{8,20}$/;
        if (!myReg.test(obj.val())) {
            this.erroralert(obj,'密码格式不正确')
        }else{
            this.success(obj);
        }
        if(obj2&&obj2.val()!=''){
            eval(obj2.attr('onblur'));
        }
    },
    tradingPassword:function(obj,obj2,callback) {//交易密码校验
        var myReg = /^[0-9]{6}$/;
        if (!myReg.test(obj.val())) {
            this.erroralert(obj,'交易密码格式不正确')
        }else{
            if(callback){
                callback(obj)
            }else{
                this.success(obj);
            };
        }
        if(obj2&&obj2.val()!=''){
            eval(obj2.attr('onblur'));
        }
    },
    repassword:function(obj,obj2) {//重复密码检验
        if (obj.val()!=obj2.val()) {
            this.erroralert(obj,'两次密码不一致')
        }else{
            this.success(obj);
        }
    },
    idcard:function(obj) {//身份证号校验
        var myReg = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
        if (!myReg.test(obj.val())) {
            this.erroralert(obj,'身份证格式不正确')
        }else{
            this.success(obj);
        }
    },
    email:function(obj) {//邮箱校验
        var myReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if (!myReg.test(obj.val())) {
            this.erroralert(obj,'电子邮件格式不正确')
        }else{
            this.success(obj);
        }
    },
    custom:function(obj,reg,mess,callback){
        var myReg=eval(reg);
        if (!myReg.test(obj.val())) {
            this.erroralert(obj,mess)
        }else{
            if(callback){
                callback(obj)
            }else{
                this.success(obj);
            };
        }
    },
    digitUppercase:function (num) {  
        var strOutput = "";  
        var strUnit = '仟佰拾亿仟佰拾万仟佰拾元角分';
        //num=parseInt(num, 10);
        if(num){
            num += "00";
            var intPos = num.indexOf('.');  
            if (intPos >= 0)  
            num = num.substring(0, intPos) + num.substr(intPos + 1, 2);  
            strUnit = strUnit.substr(strUnit.length - num.length);  
            for (var i=0; i < num.length; i++)  
            strOutput += '零壹贰叁肆伍陆柒捌玖'.substr(num.substr(i,1),1) + strUnit.substr(i,1);  
            return strOutput.replace(/零角零分$/, '整').replace(/零[仟佰拾]/g, '零').replace(/零{2,}/g, '零').replace(/零([亿|万])/g, '$1').replace(/零+元/, '元').replace(/亿零{0,3}万/, '亿').replace(/^元/, "零元");  
        }else{
          return '-';   
        }       
    },
    dateFormatter:function(val,type){
        if(type==1){
            return new Date(val).format('yyyy-MM-dd hh:mm');
        }
        else{
            return new Date(val).format('yyyy-MM-dd');
        }
    }
    
}

var path={
    loginOuts:function(){
        $.ajax({
            type:"post",
            url:url+"user/loginOut",
            dataType:'json',
            success:function(data){
                location.href = "/mobile/indexWX.html";
            }
        })
    },
    gotoaccount:function(){
        var phone  = $.fn.cookie('phone');
        if(null != phone  && "" != phone){
            location.href =  "/mobile/account/allAccount.html";
        }else{
            location.href =  "/mobile/login.html?fromurl=/mobile/account/allAccount.html";
        }
    },
    gotologin:function(back){
        back ? location.href = "/mobile/login.html?fromurl="+location.href : location.href = "/mobile/login.html";
    }
}

var getcode={//获取手机验证码
    wait:60,
    ajax:function(o) {
        
        var obj=$('#phone');
        var _this=this;
        var phone = obj.val();
        eval(obj.attr('onblur'));
        if(obj.data('group-state')){
            var code='';
            if($('#code').length){
                code='&code='+$('#code').val();
            }
            this.time(o); 
            $.ajax({
                url:url+o.data('url')+code,
                type:'post',
                dataType: "json",
                data:{phone:phone},
                success: function(data) {
                    if (data.message == 'success') {
                        $.alert('短信发送成功',null,null,1200,{className:'favorpop'},false);        
                    }else{
                        $.alert(data.message,null,null,1200,{className:'favorpop'},false);  
                    }
                },
                error: function(){
                    $.alert('网络连接失败',null,null,1200,{className:'favorpop'},false);
                }
            });
        }     
    },
    time:function (o) {
        if (this.wait == 0) { 
            o.removeClass('btn-disabled');  
            o.text('获取验证码') 
            this.wait = 60; 
            $.confirm('未收到验证码？再尝试一次吧或联系客服<a href="tel:0571-56231981">0571-56231981</a>',[{'yes':'确定'}],function(type,e){
                if(type=='yes'){
                    this.hide();    
                }  
            },{width:300,className:'ui-dialog ui-alert showclose'});
    		return;
        } else { 
            o.addClass('btn-disabled'); 
            o.text('重新发送(' + this.wait + ')'); 
            
           this.wait--; 
           var _this=this;
            setTimeout(function() { 
                _this.time(o) 
            }, 1000) 
        } 
    } 
}

$(function(){
    $('#getcode').on({click:function(){
        if(!$(this).hasClass('btn-disabled')){
            getcode.ajax($(this))
        }
    }},'a.btn-def');
})


var _addDataonce=true,page_pathname,totalheight = 0;maxnum = 2,range = 50,this_page = 1;;

function addData(arg,callback){
    var _this=this
    _this.prototype={
        getPathName:function(){
            page_pathname=location.pathname.replace(/\/.*\/(.*)\..*/g,'$1');
        },
        getHistory:function(name){
            return localStorage[page_pathname+name] || false;
        },
        setHistory:function(name,str){
            localStorage[page_pathname+name] = str;
        },
        setAllHistory:function (html,res,page,maxnum,more){
            var isTop=document.body.scrollTop;
            _this.prototype.setHistory("isTop", isTop);
            _this.prototype.setHistory("mainBoxHtml", html);
            _this.prototype.setHistory("isRes", res);
            _this.prototype.setHistory("isPage", page);
            _this.prototype.setHistory("isMaxnum", maxnum);
            _this.prototype.setHistory("isMore", more);
            _this.prototype.setHistory("isJump", '1');   
        },
        clearHistory:function(){
            _this.prototype.setHistory("isJump", '0');
        },
        throttle:function(method,context){
            clearTimeout(method.tId);
            method.tId=setTimeout(function(){
                method.call(context);
            },120);
        },
        func:function(){
            _this.prototype.throttle(_this.prototype.myFunc, window);
        },
        ajax:function(arg,_page){
            var _obj=arg.mainbox.next('.loading_more'),_data=$.extend(arg.res,{pageIndex:_page});
                $.ajax({
                url:arg.url,
                type:'post',
                dataType:'json',
                data:_data,
                headers: {
                	'fromFlag': "2"
                },
                beforeSend: function () {_obj.remove();arg.mainbox.after('<div class="loading_more flex-box"><svg class="load-icon" style="margin-right:10px;" width="30" height="30" viewBox="0,0,130,130"><circle cx="65" cy="65" r="61" stroke="#d9d9d9" fill="none" stroke-width="4"></circle><path class="path" id="path" stroke="#95ce2a" fill="none" stroke-width="4" d="M 65 4 A 61 61, 0, 1, 1, 9.075676085688478 40.638760402492565"></path></svg>数据加载中...</div>');_obj=$(".loading_more");},
                success:function(result){
                    arg.success(result,arg.mainbox,arg.isrefresh);
                    arg.isrefresh=false;
                    if(!result.data.totalCount){
                    	if($('#noMessage').length){
                    		_obj.hide();
                        	$('#noMessage').show();
                    	}else{
                            _obj.html('<div class="gray">暂无数据</div>')
                    	}
                    } else{
                        maxnum=result.data.totalPage;
                        if(this_page<maxnum){
                            _obj.html('<svg class="load-icon" style="margin-right:10px;" width="30" height="30" viewBox="0,0,130,130"><circle cx="65" cy="65" r="61" stroke="#d9d9d9" fill="none" stroke-width="4"></circle><path class="path" id="path" stroke="#95ce2a" fill="none" stroke-width="4" d="M 65 4 A 61 61, 0, 1, 1, 9.075676085688478 40.638760402492565"></path></svg>数据加载中...');
                        }else{
                            _obj.html('<div class="gray">已经到底了</div>')

                        } 
                    }
                    if(callback) callback();
                },
                error:function(data){
                	if(arg.error){
                		arg.error(data)
                		return;
                	}
                    $.alert('网络连接失败','','',1200,{className:'favorpop'},false)

                }
            })
        },
        scrolled:function() {
            totalheight = parseFloat($(window).scrollTop()) + parseFloat($(window).height());
            if (totalheight >= ($(document).height() - range) && this_page < maxnum) {
                _this.prototype.func()
            }
        },
        myFunc:function() {
            this_page++;
            _this.prototype.ajax(arg,this_page)
        },
        setclick:function(){
            arg.mainbox.on("click", "a", function () {
                event.preventDefault();
                var href = $(this).attr("href");
                href?location.href = href:null;
                _this.prototype.setAllHistory(arg.mainbox.html(),JSON.stringify(arg.res),this_page,maxnum,arg.mainbox.next().html());    
            })
        },
        init:function(){
            if(_addDataonce){
                if(localStorage[page_pathname+'isJump']=='1'){
                    var isMore;
                    _this.prototype.getHistory('isMore')=="undefined"?isMore='':isMore=_this.prototype.getHistory('isMore');
                    arg.mainbox.html(_this.prototype.getHistory('mainBoxHtml')).after('<div class="loading_more">'+isMore+'</div>');
                    this_page=parseInt(_this.prototype.getHistory('isPage'));
                    var isTop=parseInt(_this.prototype.getHistory('isTop'));
                    $(window).scrollTop(isTop);
                    maxnum=parseInt(_this.prototype.getHistory('isMaxnum'));
                    arg.res=JSON.parse(_this.prototype.getHistory("isRes"));
                    delete arg.res["pageIndex"]; 
                    $(function(){
                        for(var key in arg.res){
                            $.each($('ul li[data-'+key+']'),function(n,v){
                                if($(this).data(key.toLowerCase())==arg.res[key]){
                                    $(this).addClass('active');
                                }else{
                                    $(this).removeClass('active');
                                }
                            })
                            
                        }
                    })
                    
                    _this.prototype.clearHistory()

                }else{
                    _this.prototype.ajax(arg,this_page)
                }

                $(document).scroll(function(){_this.prototype.scrolled()});
                _addDataonce=false; 

            }else{
                
                this_page=1
                _this.prototype.ajax(arg,this_page)
            }

        }

    };

    this.prototype.getPathName();
    this.prototype.setclick();
    this.prototype.init();
    return this;
 
}
$.isLoading={
        show:function (mess,mask){
            var mess=mess||'加载中...'
            var html='<div class="isLoading flex-box" style="background: rgba(0,0,0,.5);padding:5px 15px; border-radius: 6px;height: 40px;text-align: center;position: absolute;top: 50%;left: 50%; margin-left: -75px; margin-top: -20px;color:#eee;position: fixed;z-index: 99999999;top: 50%;left: 50%;"><svg class="load-icon" style="margin-right:10px;" width="30" height="30" viewBox="0,0,130,130"><circle cx="65" cy="65" r="61" stroke="#d9d9d9" fill="none" stroke-width="4"></circle><path class="path" id="path" stroke="#95ce2a" fill="none" stroke-width="4" d="M 65 4 A 61 61, 0, 1, 1, 9.075676085688478 40.638760402492565"></path></svg>'+mess+'</div>'
            if(mask){
                html='<div class="isLoading-mask" style="position: fixed;width: 100%;height: 100%;background: rgba(0,0,0,0);top: 0;left: 0;z-index: 99999999;">'+html+'</div>'
            }
            $('body').append(html)

        },
        hide:function(){
        	$('.isLoading,.isLoading-mask').remove()
        }
    }

$.runBike={
		timer:null,
		timeout:null,
		show:function (mess,rate,speed){
			console.log("show");
            var mess=mess ||'开锁中';
            var rate=rate || 99;
            var speed=speed || 100;
            var html='<div class="key none"  id="unlock">'+
        		'<img src="images/bg2.jpg" onload="$.runBike.bgload()" class="img1 img-size" /> <img src="images/bike.png" class="img2 img-size" />'+
        		'<div class="bar">'+
        			'<span></span>'+
        		'</div>'+
        		'<span class="text">'+mess+'<span id="percent"></span>%</span> <img src="images/text2.png" class="img3 img-size" />'+
        	'</div>';
            $('body').append(html)
            this.jishi(rate,speed)
            setTimeout(function(){
            	$.runBike.bgload()
            },2000)
//            this.timeout=setTimeout(function(){//超时关闭
//            	console.log("timeout");
//            	$.alert("操作超时，该车可能有故障，请选择稍后再试或联系客服人员",true,function(){
//	     			window.location.reload();
//	             },5000000000,{className:'ui-alert',width:300});
//            	//$.runBike.errorhide()
//            },40000)
        },
        bgload:function(){
        	$('#unlock').show()
        },
        jishi:function(rate,speed){
        	var percent=0;
        	var _this=this;
        	_this.timer=self.setInterval(function(){
    			if(percent<rate){
    				percent++;
    				$('#percent').html(percent);
    				$('#unlock .bar span').css("width",percent+"%");
    			}else{
    				_this.blackout();
    			}
    	    },speed);
        },
        blackout:function(){
        	console.log("blackout");
        	clearInterval(this.timer);
        	
        },
        hide:function(){
        	console.log("hide");
        	this.blackout(); 
        	clearTimeout(this.timeout);
        	$('.key .bar span').css("width","100%");
			$('#percent').html(100);
        	setTimeout(function(){
        		$('#unlock').remove()
        	},200)
        	
        },
        errorhide:function(){
        	console.log("errorhide");
        	this.blackout(); 
        	$('#unlock').remove()	
        }
}


function reLogin(data){
	var loginFlag =data.responseText;
	var otherFlag = "";
	if(-1!=path_url.indexOf('#')){
		path_url = path_url.substr(0,path_url.indexOf('#'));
		otherFlag = path_url.substr(path_url.indexOf('#')+1);
	}
	if("unlogin"==loginFlag){
		$.alert("需要登陆",false,function(){
			window.location.href=url+"/index/goIndex.action?path_url="+path_url+"&otherFlag="+otherFlag;
        },3000,{className:'ui-alert',width:300}); 
	}
	if("isblack"==loginFlag){
		$.confirm('您的账号已被拉黑，请联系客服电话0571-87656065<br/>点击—><a href="defriend.html"> 了解详情</a>',[],function(type,e){
			if(type=='no'){
				this.hide();	
		    }
		    
		},{width:300,className:'ui-dialog ui-alert showclose'});
		return;
		}
	if("isdelete"==loginFlag){
		$.confirm('您的账号已被删除，请联系客服电话0571-87656065',[],function(type,e){
			if(type=='no'){
				this.hide();	
		    }
		    
		},{width:300,className:'ui-dialog ui-alert showclose'});
		return;
		}
	
}