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
			<h1 class="page-header">添加景点</h1>
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
							<form role="form" action="cms/scenicspot/add.action" method="post" enctype="multipart/form-data">
								<div class="form-group">
								    <label>类型：</label><br>
									<div class="form-group">
									   <select id="scenicSpotType" class="form-control" name="scenicSpotType" required="required" onchange="getType(this.value)">
			                     		<option value="0">普通景点</option>
			                     		<option value="1">骑游景点</option>
		                    	       </select>
								    </div>
								    <br>
								    <br>
									<label>标题：</label><br>
									<div class="form-group">
										<input id="scenicSpotName" class="form-control" placeholder="标题"  name="scenicSpotName"  type="text"
											required="required">
								    </div>
								    <br>
								    <br>
								    <label>是否全国显示：</label>
									<input type="radio" name="scenicSpotIsAll" value="1"> 是
								    <input type="radio" checked="checked" name="scenicSpotIsAll" value="0"> 否
								    <br>
								    <br>
									<label>标签（逗号分隔，四字以内）：</label><br>
									<div class="form-group">
										<input id="scenicSpotTag" class="form-control" placeholder="标签"  name="scenicSpotTag"  type="text"
											required="required">
								    </div>
									<br>
									<br>
								   <label>城市：</label><br>
								   <div class="form-group">
									   <select id="scenicSpotCityId" class="form-control" name="scenicSpotCityId" required="required">
			                     		<c:forEach var="city" items="${cityList}">
		                   					<option value="${city.cityId }">
		                     					${city.cityName }
			                     			</option>
			                     		</c:forEach>
		                    	       </select>
								    </div>
									<br>
									<br>
									<br>
									<label>置顶号：</label><br>
									<div class="form-group">
										<input id="scenicSpotTopNum" class="form-control" placeholder="置顶号"  name="scenicSpotTopNum"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									
									<!-- <label>景点距离（km）：</label><br>
									<div class="form-group">
										<input id="scenicSpotDistance" class="form-control" placeholder="景区距离"  name="scenicSpotDistance"  type="text"
											required="required">
								    </div>
									<br>
									<br> -->
									<label id="scImage">景点图片（请上传700像素*260像素的图片）：</label><br>
									<div class="form-group">
										 <div class="uploadpic">
						                        <div class="imgb flex-box">
						                            <img src="" alt="">
						                        </div>
						                        <input class="val" name="fileStr" type="hidden">
						                        <input id="scenicSpotImage" class="fileact none" type="file">
						                    </div>
								    </div>
									<br>
									<br>
									<label>景点详情图片（请上传750像素*400像素的图片）：</label><br>
									<div class="form-group">
										 <div class="uploadpic">
						                        <div class="imgb flex-box">
						                            <img src="" alt="">
						                        </div>
						                        <input class="val" name="detailFileStr" type="hidden">
						                        <input id="scenicSpotDetailImage" class="fileact none" type="file">
						                    </div>
								    </div>
									<br>
									<br>
									<label>景点经纬度：</label><br>
									<div class="form-group">
										<input id="latLng" class="form-control go-back" placeholder="景区经纬度"  name="lngLat"  type="text"
											required="required">
								    </div>
									<br>
									<br>
									<label>景点开放时间：</label><br>
									<div class="form-group">
										<textarea id="scenicSpotOpenTime" name="scenicSpotOpenTime" cols="100" rows="10"></textarea>
								    </div>
									<br>
									<br>
									<label>景点相关政策规定：</label><br>
									<div class="form-group">
										<textarea id="scenicSpotPolicy" name="scenicSpotPolicy" cols="100" rows="10"></textarea>
								    </div>
									<br>
									<br>
									<label>景点温馨提示：</label><br>
									<div class="form-group">
										<textarea id="scenicSpotMsg" name="scenicSpotMsg" cols="100" rows="10"></textarea>
								    </div>
									<br>
									<br>
									<label>景点介绍：</label>
									<div class="form-group" id='brief'>
										<!-- <label class="col-md-2 control-label"></label>
										<div class="col-md-8"> -->
											<script id="editor" type="text/plain" name="scenicSpotContent" style="height: 400px;"></script>
										<!-- </div> -->
									</div>
								</div>
								<div style="text-align: center;">
									<input type="button" onclick="gotoAction()" value="提交" class="btn btn-primary">
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
<script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript">
	var url_img = '${url}';
	var ue = UE.getEditor('editor');
	function gotoAction(){
		var img=new Image();
		var detailImg=new Image();
		var docObj = document.getElementById("scenicSpotImage");  
	    var detaildocObj = document.getElementById("scenicSpotDetailImage");  
		if($("#scenicSpotName").val()==''){
			 $.alert('景点名称不能为空')
			 return;
		}
		if($("#scenicSpotTag").val()==''){
			 $.alert('景点门票标签不能为空')
			 return;
		}
		if($("#scenicSpotTopNum").val()==''){
			 $.alert('置顶号不能为空')
			 return;
		}
		if($("#latLng").val()==''){
			 $.alert('景区经纬度不能为空')
			 return;
		}
		if($("#scenicSpotDistance").val()==''){
			 $.alert('景区距离不能为空')
			 return;
		}
		if($("#scenicSpotImage").val()==''){
			 $.alert('景区图片不能为空')
			 return;
		}
		img.onload = function(){
			if($("#scenicSpotType").val()==0){
				if(img.width > 700){
					  $.alert("景区图片宽度超过限制 请上传宽度小于"+700+"px的图片")
					  return;
				}else{
					if(img.height > 260){
						  $.alert("景区图片高度超过限制 请上传高度小于"+260+"px的图片");
						  return;
					}else{
						if($("#scenicSpotDetailImage").val()==''){
							 $.alert('景区详情图片不能为空')
							 return;
						}
						detailImg.onload = function(){
							if(detailImg.width > 750){
								  $.alert("景区详情图片宽度超过限制 请上传宽度小于"+750+"px的图片")
								  return;
							}else{
								if(detailImg.height > 400){
									  $.alert("景区详情图片高度超过限制 请上传高度小于"+400+"px的图片");
									  return;
								}else{
									if($("#scenicSpotOpenTime").val()==''){
										 $.alert('景区开放时间不能为空')
										 return;
									}
									if($("#scenicSpotPolicy").val()==''){
										 $.alert('景点相关政策规定不能为空')
										 return;
									}
									if($("#scenicSpotMsg").val()==''){
										 $.alert('景点温馨提示不能为空')
										 return;
									}
									
									var editorhtml=ue.getContent()
									if(editorhtml!=''){
										$('form').submit();
								    }else{
								    	$.alert('景点介绍不能为空')
								    }
								}
							}
							
						}
						
						detailImg.src=window.URL.createObjectURL(detaildocObj.files[0]);
						
						
					}
				}
			}else{
				if(img.width > 255){
					  $.alert("景区图片宽度超过限制 请上传宽度小于"+255+"px的图片")
					  return;
				}else{
					if(img.height > 170){
						  $.alert("景区图片高度超过限制 请上传高度小于"+170+"px的图片");
						  return;
					}else{
						if($("#scenicSpotDetailImage").val()==''){
							 $.alert('景区详情图片不能为空')
							 return;
						}
						detailImg.onload = function(){
							if(detailImg.width > 750){
								  $.alert("景区详情图片宽度超过限制 请上传宽度小于"+750+"px的图片")
								  return;
							}else{
								if(detailImg.height > 400){
									  $.alert("景区详情图片高度超过限制 请上传高度小于"+400+"px的图片");
									  return;
								}else{
									if($("#scenicSpotOpenTime").val()==''){
										 $.alert('景区开放时间不能为空')
										 return;
									}
									if($("#scenicSpotPolicy").val()==''){
										 $.alert('景点相关政策规定不能为空')
										 return;
									}
									if($("#scenicSpotMsg").val()==''){
										 $.alert('景点温馨提示不能为空')
										 return;
									}
									
									var editorhtml=ue.getContent()
									if(editorhtml!=''){
										$('form').submit();
								    }else{
								    	$.alert('景点介绍不能为空')
								    }
								}
							}
							
						}
						
						detailImg.src=window.URL.createObjectURL(detaildocObj.files[0]);
						
						
					}
				}
				
			}
		}
			
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
	
	
	function getType(obj){
		if(obj==1){
			$("#scImage").html("景点图片（请上传255像素*170像素的图片）：");
		}else{
			$("#scImage").html("景点图片（请上传700像素*260像素的图片）：");
		}
	}
	</script>
	<script type="text/javascript" src='js/uploadUE.js'></script>
	<%@include file="../common/maskMap.jsp"%>
</html>