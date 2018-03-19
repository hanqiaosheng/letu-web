

function getStatesFn(){
	if($('#checkbox').prop('checked')){
		rule.getStatesFn()
	}else{
		$.alert('请同意用户注册协议')
	}
}


function forgetJs(){
	window.location.href="/html/forgetLoginPwd.html"
}
