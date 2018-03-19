$(document).ready(function() {

	$('.hide-search').on('click', function(event) {
		if ($('.tables-btn-box').css('display') === 'none') {
			$('.hide-search').text('隐藏');
		} else {
			$('.hide-search').text('展开');
		}
		$('.tables-btn-box').toggle(300);
		$('.search-btn').toggle(300);
	});
});

// 提交查询表单
function doSubmit() {
	$("#searchForm").submit();
}
function uploadFile() { 
	$("#pinstructions").click(); 
}; 

function addFileNameToInput(){
	var str = $("#pinstructions").val();
	$('#filename').html(str.substring(str.lastIndexOf("\\")+1));
}
var file={
		init:function(obj){
			obj.next().trigger('click').change(function(){
				var str=$(this).val();
				var filename=str.substring(str.lastIndexOf("\\")+1);
				
				$(this).parent().find('span.stat').remove()
				$(this).after('<span class="stat">'+filename+'</span>');
			});
			
		},
		addNode:function(obj){
			obj.after('<div class="inputfileli">'+obj.html()+'</div>');
			obj.next().find('span.stat').remove();
			obj.next().find('input').val('');
		},
		subNode:function(obj,yuan){
			if(!yuan){
				if($('.inputfileli').length==1){$.alert("无法删除最后一个"); return;}
			}
			if(typeof(delprotocol)!='undefined'){
				var id = obj.prev().val();
				delprotocol(id);
			}
			var line=obj.parents('.inputfileli,.fileli');
	        line.remove();
		}
	}
	$.isLoading={
        show:function (mess,mask){
            var mess=mess||'加载中...'
            var html='<div class="isLoading flex-box" style="background: rgba(0,0,0,.5);padding:35px 50px; border-radius: 6px;height: 40px;text-align: center;position: absolute;top: 50%;left: 50%; margin-left: -75px; margin-top: -20px;color:#eee;position: fixed;z-index: 99999999;top: 50%;left: 50%;"><svg class="load-icon" style="margin-right:10px;" width="30" height="30" viewBox="0,0,130,130"><circle cx="65" cy="65" r="61" stroke="#d9d9d9" fill="none" stroke-width="4"></circle><path class="path" id="path" stroke="#95ce2a" fill="none" stroke-width="4" d="M 65 4 A 61 61, 0, 1, 1, 9.075676085688478 40.638760402492565"></path></svg>'+mess+'</div>'
            if(mask){
                html='<div class="isLoading-mask" style="position: fixed;width: 100%;height: 100%;background: rgba(0,0,0,0);top: 0;left: 0;z-index: 99999999;">'+html+'</div>'
            }
            $('body').append(html)

        },
        hide:function(){
        	$('.isLoading,.isLoading-mask').remove()
        }
    }