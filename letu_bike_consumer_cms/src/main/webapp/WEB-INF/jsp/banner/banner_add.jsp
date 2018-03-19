<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../common/top.jsp"%>
<script type="text/javascript" src="assets/dialog.js"></script>
<script type="text/javascript" src="js/exif.js"></script>
<script type="text/javascript" src="js/megapix-image.js"></script>

<link rel="stylesheet" href="assets/dialog.css">
<%@include file="../common/body.jsp"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">添加横幅</h1>
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
							<form role="form" action="cms/banner/add.action" method="post" enctype="multipart/form-data">
								<div class="form-group">
									<label>横幅标题：</label><br>
									<div class="form-group">
										<input id="bannerName" class="form-control" placeholder="横幅标题"  name="bannerName"  type="text"
											required="required">
								    </div>
								    <br>
								    <br>
									<label>置顶号：</label><br>
									<div class="form-group">
										<input id="bannerTopNum" class="form-control" placeholder="置顶号"  name="bannerTopNum"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>是否全国显示：</label>
									<input type="radio" name="bannerIsAll" value="1"> 是
								    <input type="radio" checked="checked" name="bannerIsAll" value="0"> 否
								    <br>
								    <br>
									<label>所属城市：</label><br>
									<div class="form-group">
									   <select id="bannerCityId" class="form-control" name="bannerCityId" required="required">
			                     		<c:forEach var="city" items="${cityList}">
		                   					<option value="${city.cityId }">
		                     					${city.cityName }
			                     			</option>
			                     		</c:forEach>
		                    	       </select>
								    </div>
									<br>
									<br>
									<label>横幅图片（请上传750像素*400像素的图片）：</label><br>
									<div class="form-group">
										 <div class="uploadpic">
						                        <div class="imgb flex-box">
						                            <img src="" alt="">
						                        </div>
						                        <input class="val" name="bannerImg" type="hidden">
						                        <input id="bannerImage" class="fileact none" type="file">
						                    </div>
								    </div>
									<br>
								</div>
								<div style="text-align: center;">
									<input type="button" onclick="gotoAction();" value="提交" class="btn btn-primary">
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
function gotoAction(){
	var img=new Image();
	var docObj = document.getElementById("bannerImage");  
    var files = document.getElementById("bannerImage").value; 
	if($("#bannerName").val()==''){
		 $.alert('横幅标题不能为空')
		 return;
	}
	if($("#bannerTopNum").val()==''){
		 $.alert('置顶号不能为空')
		 return;
	}
	if($("#bannerImage").val()==''){
		$.alert('图片不能为空')
		 return;
	}
	img.onload = function(){  
		if(img.width > 750){
			 $.alert("图片宽度超过限制 请上传宽度小于"+750+"px的图片");
		}else{
			if(img.height > 400){
				 $.alert("图片高度超过限制 请上传高度小于"+400+"px的图片");
			}else{
				$('form').submit();
			}
		}
	};
	img.src=window.URL.createObjectURL(docObj.files[0]);
}

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

</script>
<%@include file="../common/buttom.jsp"%>
</html>