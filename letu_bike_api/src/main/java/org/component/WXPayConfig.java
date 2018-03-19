package org.component;

/**
 * 微信支付 
 * @author morning
 *
 */
public class WXPayConfig {

	//微信开放平台审核通过的应用APPID  eg.wxd678efh567hg6787
	public static String appid = "wx86857061a84c5545";
	
	//微信支付分配的商户号   eg.1230000109
	public static String mch_id = "1484808752";
	
	//支付类型
	public static String trade_type = "APP";
	
	//key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
	public static String key = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00";
	
	//微信公众号
	public static String appsecret = "c3cfa1e8d8b3e423f9dcb7a0b6312ecc";
	
}
