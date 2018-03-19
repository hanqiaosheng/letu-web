<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<script type="text/javascript" src="datepicker/dateinput-ch-ZN.js"></script>
<script type="text/javascript" src="datepicker/jquery-ui.js"></script>
<script language="javascript" src="datepicker/jquery-ui-timepicker-addon.min.js"></script>  
<script language="javascript" src="datepicker/jquery-ui-timepicker-zh-CN.js"></script>  
<link href="datepicker/jquery-ui.css" rel="stylesheet">
<script type="text/javascript">
$(document).ready(function(){  
	$('#cStarttime').datepicker({
		changeMonth: true, 
		changeYear: true, 
		showMonthAfterYear: true,
		dateFormat: "yy-mm-dd",
		onSelect:function(dateText,inst){
           $("#cEndtime").datepicker("option","minDate",dateText);
		}
		
	});
	$('#cEndtime').datepicker({
		changeMonth: true, 
		changeYear: true, 
		showMonthAfterYear: true,
		dateFormat: "yy-mm-dd",
		onSelect:function(dateText,inst){
            $("#cStarttime").datepicker("option","maxDate",dateText);
        }
	});
	$('#cStarttimeB').datepicker({
		changeMonth: true, 
		changeYear: true, 
		showMonthAfterYear: true,
		dateFormat: "yy-mm-dd",
		onSelect:function(dateText,inst){
	        $("#cEndtimeB").datepicker("option","minDate",dateText);
		}
	});
	$('#cEndtimeB').datepicker({
		changeMonth: true, 
		changeYear: true, 
		showMonthAfterYear: true,
		dateFormat: "yy-mm-dd",
		onSelect:function(dateText,inst){
            $("#cStarttimeB").datepicker("option","maxDate",dateText);
        }
	});
	$('#cStarttimeC').datepicker({
		changeMonth: true, 
		changeYear: true, 
		showMonthAfterYear: true,
		dateFormat: "yy-mm-dd",
		onSelect:function(dateText,inst){
	        $("#cEndtimeC").datepicker("option","minDate",dateText);
		}
	});
	$('#cEndtimeC').datepicker({
		changeMonth: true, 
		changeYear: true, 
		showMonthAfterYear: true,
		dateFormat: "yy-mm-dd",
		onSelect:function(dateText,inst){
            $("#cStarttimeC").datepicker("option","maxDate",dateText);
        }
	});
	
	$('#cEndtimeD').datetimepicker({
		changeMonth: true, 
		changeYear: true, 
		showMonthAfterYear: true,
		dateFormat: "yy-mm-dd",
		timeFormat: "HH:mm:ss",
	});
})  
</script>
