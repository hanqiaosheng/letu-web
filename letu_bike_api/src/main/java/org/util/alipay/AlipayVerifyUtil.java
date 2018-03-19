package org.util.alipay;

import java.util.Map;

/**
 * 支付宝  验证工具
 * @author morning
 *
 */
public class AlipayVerifyUtil {

	public static Boolean verify(Map<String, String> params){
		
		//1.验证签名
		boolean signVeryfy = false;
		if(null!=params.get("sign")){
			signVeryfy = AlipayNotify.getSignVeryfy(params, params.get("sign"));
		}
		
		//2.验证是否是支付宝发来的通知
		String verifyResponse = "false";
		if(null!=params.get("notify_id")){
			verifyResponse = AlipayNotify.verifyResponse(params.get("notify_id"));
		}
		
		if(signVeryfy&&"true".equals(verifyResponse)){
			return true;
		}
		return false;
	}
}
