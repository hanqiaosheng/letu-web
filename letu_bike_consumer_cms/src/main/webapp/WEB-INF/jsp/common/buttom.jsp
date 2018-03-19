<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    <script type="text/javascript">
$.ajax({
    type:'post',
    url:"cms/webSocket/auditing.action",
    async : false,  
    success:function(data){
 if(data>0){
	$("#bikeFeed").html(data);
 }else{
	 $("#bikeFeed").html("");
 }
}
})

</script>
<script type="text/javascript">
$.ajax({
    type:'post',
    url:"cms/webSocket/feedback.action",
    async : false,  
    success:function(data){
 if(data>0){
	$("#feedback").html(data);
 }else{
	 $("#feedback").html("");
 }
}
})

</script>
<script type="text/javascript">
$.ajax({
    type:'post',
    url:"cms/webSocket/insurance.action",
    async : false,  
    success:function(data){
 if(data>0){
	$("#insurance").html(data);
 }else{
	 $("#insurance").html("");
 }
}
})

</script>
<script type="text/javascript">
$.ajax({
    type:'post',
    url:"cms/webSocket/refund.action",
    async : false,  
    success:function(data){
 if(data>0){
	$("#refund").html(data);
	$("#refund1").html(data);
 }else{
	 $("#refund").html("");
	 $("#refund1").html("");
 }
}
})

</script>
<script type="text/javascript">
$.ajax({
    type:'post',
    url:"cms/webSocket/lock1.action",
    async : false,  
    success:function(data){
 if(data>0){
	$("#lockcount1").html(data);
 }else{
	 $("#lockcount1").html("");
 }
}
})

</script>
<script type="text/javascript">
$.ajax({
    type:'post',
    url:"cms/webSocket/lock2.action",
    async : false,  
    success:function(data){
 if(data>0){
	$("#lockcount2").html(data);
 }else{
	 $("#lockcount2").html("");
 }
}
})

</script>
<script type="text/javascript">
$.ajax({
    type:'post',
    url:"cms/webSocket/invoice.action",
    async : false,  
    success:function(data){
 if(data>0){
	$("#invoicecount").html(data);
	$("#invoice").html(data);
 }else{
	 $("#invoicecount").html("");
	 $("#invoice").html("");
 }
}
})

</script>
<script type="text/javascript">
$.ajax({
    type:'post',
    url:"cms/webSocket/return.action",
    async : false,  
    success:function(data){
 if(data>0){
	$("#fixReturncount").html(data);
	if($("#fixReturncount").html()!=undefined){
		$("#return").html(data);
	}
	
 }else{
	 $("#fixReturncount").html("");
	 $("#return").html("");
 }
}
})

</script>
<script type="text/javascript">
var bikeFeed = $("#bikeFeed").html();
var feedback = $("#feedback").html();
var insurance = $("#insurance").html();
if(bikeFeed==undefined){
	bikeFeed = 0;
}
if(feedback==undefined){
	feedback = 0;
}
if(insurance==undefined){
	insurance = 0;
}
if(Number(bikeFeed)+Number(feedback)+Number(insurance)>0){
	$("#allTip").html(Number(bikeFeed)+Number(feedback)+Number(insurance));
}else{
	$("#allTip").html("");
}

</script>
<script type="text/javascript">
var lockcount1 = $("#lockcount1").html();
var lockcount2 = $("#lockcount2").html();

if(lockcount1==undefined){
	lockcount1 = 0;
}
if(lockcount2==undefined){
	lockcount2 = 0;
}
if((Number(lockcount1)+Number(lockcount2))>0){
    $("#allLock").html(Number(lockcount1)+Number(lockcount2));
}else{
	$("#allLock").html("");
}
</script>
</html>
