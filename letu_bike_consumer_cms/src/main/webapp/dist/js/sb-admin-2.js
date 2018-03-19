$(function() {

    $('#side-menu').metisMenu();

});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function() {
    $(window).bind("load resize", function() {
        topOffset = 50;
        width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1) height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    var element = $('ul.nav a').filter(function() {
        return this.href == url || url.href.indexOf(this.href) == 0;
    }).addClass('active').parent().parent().addClass('in').parent();
    if (element.is('li')) {
        element.addClass('active');
    }
});



//通过canvas截取固定大小（宽度为max_width，高自适应）的图片
function resizeMe(img,maxW,maxH,nocheck) {
    var canvas = document.createElement('canvas');
    var width = img.width;
    var height = img.height;
    var max_width = maxW || 640;
    var max_height = maxH || 640;
    var max_size = 300;//k        
    if (width > max_width) {
        height *= max_width / width;
        height = Math.round(height);
        width = max_width;
    }
    if(height > max_height){
        width *= max_height / height;
        width = Math.round(width);
        height = max_height;
    }
    //将图片放入canvas，并重置canvas大小
    if(browser.versions.ios || browser.versions.webApp){
        var mpImg = new MegaPixImage(img);
        mpImg.render(canvas, { width: width, height: height });
    }else{
        canvas.width = width;
        canvas.height = height;
        var ctx = canvas.getContext("2d");
        ctx.drawImage(img, 0, 0, width, height);
    }
    var res, quality = .7,resSize,ratio = 1;
    res = canvas.toDataURL("image/jpeg",quality); // 截取canvas对应的jpg图片，并且画质为70%（默认就是70%，可以改变）

    // Android 2.x、Android 4.1.2、4.3 的 toDataURL 不支持jpeg格式；
    if(res.substr(0,"data:image/png".length) == "data:image/png" || res.substr(0,6) == "data:,"){
        var encoder = new JPEGEncoder();
        res = encoder.encode(canvas.getContext("2d").getImageData(0,0,width,height), quality * 100, true);
    }
    resSize = Math.ceil(res.length/1024); //k
    if(resSize > max_size && !nocheck){
        ratio = Math.ceil(Math.sqrt(max_size/resSize)*100)/100;
        if(ratio >= .9){
            ratio -= .1;
        }
        res = resizeMe(img,max_width*ratio,max_height*ratio,true);
    }
    return res;
}
//判断访问终端
var browser={
    versions:function(){
        var u = navigator.userAgent;
        return {
            trident: u.indexOf('Trident') > -1, //IE内核
            presto: u.indexOf('Presto') > -1, //opera内核
            webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //是否为移动终端
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
            iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
            iPad: u.indexOf('iPad') > -1, //是否iPad
            webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
            weixin: u.toLowerCase().match(/MicroMessenger/i) == 'micromessenger', //是否微信
            qq: u.match(/\sQQ/i) == " qq" //是否QQ
        };
    }()
};
$(function(){
	var _this;
    $('.uploadpic .imgb,.addpic .imgb').click(function() {
        _this=$(this).parent();
        _this.find('.fileact').click();
    })
    $('.fileact').change(function(e) {
        if (e.target.files.length == 0) {
            return
        }
        var file = e.target.files[0];
        if (!/image\/\w+/.test(file.type)) {
            $.alert("请确保文件为图像类型");
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e) {

            var image = new Image();
            image.src = this.result;
            image.onload = function() {
                var resized = resizeMe(image);
                if(!resized){
                    return;
                }
                if(_this.hasClass('addpic')){
                    _this.before('<div class="removepic"><div class="imgb flex-box"><img src="'+resized+'" alt=""><div class="remove">×</div></div><input class="val" name="transferVouchers" value="'+resized+'" type="hidden"></div>');

                }else{
                    _this.find('img').attr('src',resized)
                    _this.find('.val').val(resized) 
                }

                
            }
            
        }
    })
})
