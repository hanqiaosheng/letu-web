package org.util.weixin;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.util.XmlUtil;
import org.util.weixin.Sha1Util;
import org.utils.http.HttpUtil;

/**
 * 微信支付 工具 
 * 2016.12.21
 * @author morning
 *
 */
public class PayUtil {

	/**
	 * APP支付
	 * 步骤1：
	 * 调用统一下单接口，获取prepay_id
	 *
	 *
	 * 
	 * @param openId
	 * @param order
	 * @param ip
	 * @return
	 */
	public static String getPrepay_idApp(String out_trade_no,String appid,String notify_url,String partner,String partner_key,String ip,Integer total_fee) {
		Map<String, String> params = new HashMap<String, String>();
		// 公众账号ID
		params.put("appid", appid);
		// 附加数据
		params.put("attach", "乐途单车");
		// 商品描述
		params.put("body", "单车订单");
		// 商户号
		params.put("mch_id", partner);
		// 随机字符串
		params.put("nonce_str", getRandomString(16));
		// 通知地址,接收微信支付异步通知回调地址
		params.put("notify_url", notify_url);
		// 商户订单号 商户系统内部的订单号,32个字符内、可包含字母
		params.put("out_trade_no",out_trade_no);
		// 终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
		params.put("spbill_create_ip", ip);
		// 总金额 订单总金额，只能为整数  单位为分
		params.put("total_fee", total_fee + "");
		// 交易类型 取值如下：JSAPI，NATIVE，APP，WAP
		params.put("trade_type", "APP");

		Document doc = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("xml");
		doc.setRootElement(root);
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		//待签名字符串 sign
		StringBuilder sign = new StringBuilder();
		for (String key : keys) {
			String value = params.get(key);
			if (key.equals("attach") || key.equals("body")
					|| key.equals("sign")) {
				XmlUtil.setElementCDATAText(root, "/" + key, value);
			} else {
				XmlUtil.setElementText(root, "/" + key, value);
			}
			sign.append(key + "=" + value + "&");
		}
		sign.append("key=" + partner_key);
		// log.error("加密字符串=" + sign.toString());
		// 签名
		XmlUtil.setElementText(root, "/sign",
				DigestUtils.md5Hex(sign.toString()).toUpperCase());

		// log.error("统计订单调用请求" + doc.asXML());
		String result = HttpUtil.requestForString(
				"https://api.mch.weixin.qq.com/pay/unifiedorder", doc.asXML(),partner,
				false, false);
		// log.error("统计订单调用返回" + result);
		doc = XmlUtil.parseXml(result);
		root = doc.getRootElement();
		String returnCode = XmlUtil.getElementText(root, "return_code",
				"FAIL");// SUCCESS/FAIL
		if (!returnCode.equals("SUCCESS")) {
			return null;
		}
		return XmlUtil.getElementText(root, "prepay_id", null);
	}
	
	
	

	/**
	 * 微信公众号支付
	 * 步骤1：
	 * 调用统一下单接口，获取prepay_id
	 *
	 *
	 * 
	 * @param openId
	 * @param order
	 * @param ip
	 * @return
	 */
	public static String getPrepay_id(String out_trade_no,String appid,String notify_url,String openId,String partner,String partner_key,String ip,Integer total_fee) {
		Map<String, String> params = new HashMap<String, String>();
		// 公众账号ID
		params.put("appid", appid);
		// 附加数据
		params.put("attach", "单车");
		// 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
		//params.put("device_info", "WEB");
		// 商品描述
		params.put("body", "单车充值订单");
		// 商户号
		params.put("mch_id", partner);
		// 随机字符串
		params.put("nonce_str", getRandomString(16));
		// 通知地址,接收微信支付异步通知回调地址
		params.put("notify_url", notify_url);
		// 用户标识 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
		params.put("openid", openId);
		// 商户订单号 商户系统内部的订单号,32个字符内、可包含字母
		params.put("out_trade_no",out_trade_no);
		// 终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
		params.put("spbill_create_ip", ip);
		// 总金额 订单总金额，只能为整数
		params.put("total_fee", total_fee+"");
		// 交易类型 取值如下：JSAPI，NATIVE，APP，WAP
		params.put("trade_type", "JSAPI");

		Document doc = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("xml");
		doc.setRootElement(root);
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		//待签名字符串 sign
		StringBuilder sign = new StringBuilder();
		for (String key : keys) {
			String value = params.get(key);
			if (key.equals("attach") || key.equals("body")
					|| key.equals("sign")) {
				XmlUtil.setElementCDATAText(root, "/" + key, value);
			} else {
				XmlUtil.setElementText(root, "/" + key, value);
			}
			sign.append(key + "=" + value + "&");
		}
		sign.append("key=" + partner_key);
		// log.error("加密字符串=" + sign.toString());
		// 签名
		XmlUtil.setElementText(root, "/sign",
				DigestUtils.md5Hex(sign.toString()).toUpperCase());

		// log.error("统计订单调用请求" + doc.asXML());
		String result = HttpUtil.requestForString(
				"https://api.mch.weixin.qq.com/pay/unifiedorder", doc.asXML(),partner,
				false, false);
		// log.error("统计订单调用返回" + result);
		doc = XmlUtil.parseXml(result);
		root = doc.getRootElement();
		String returnCode = XmlUtil.getElementText(root, "return_code",
				"FAIL");// SUCCESS/FAIL
		if (!returnCode.equals("SUCCESS")) {
			return null;
		}
		return XmlUtil.getElementText(root, "prepay_id", null);
	}
	
	
	/**
	 * APP支付
	 * 步骤2：
	 * 获取prepay_id后
	 * 按签名规范重新生成签名后，将数据传输给APP。
	 * 参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay
	 * @param length
	 * @return
	 */
	public static SortedMap<Object,Object> generateNewOrderApp(String appid,String partnerid,String prepayid,String partner_key) {
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();  
		parameters.put("appid", appid);
		parameters.put("timestamp", Sha1Util.getTimeStamp());
		parameters.put("noncestr", getRandomString(16));
		parameters.put("partnerid", partnerid); 
		parameters.put("prepayid", prepayid);
		parameters.put("package", "Sign=WXPay");
		//生成签名
	    String sign =  createSign("UTF-8", parameters,partner_key);
	    parameters.put("sign", sign);
		return parameters;
	}
	
	/**
	 * 步骤3：
	 * 查询订单 APP
	 * 并返回解析结果
	 *//*
	public static WXPayResult queryOrder(String appid,String partner,String out_trade_no,String partner_key){
		Map<String, String> params = new HashMap<String, String>();
		// 公众账号ID
		params.put("appid", appid);
		// 商户号
		params.put("mch_id", partner);
		// 随机字符串
		params.put("nonce_str", getRandomString(16));
		// 商户订单号 商户系统内部的订单号,32个字符内、可包含字母
		params.put("out_trade_no",out_trade_no);

		Document doc = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("xml");
		doc.setRootElement(root);
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		//待签名字符串 sign
		StringBuilder sign = new StringBuilder();
		for (String key : keys) {
			String value = params.get(key);
			if (key.equals("attach") || key.equals("body")
					|| key.equals("sign")) {
				XmlUtil.setElementCDATAText(root, "/" + key, value);
			} else {
				XmlUtil.setElementText(root, "/" + key, value);
			}
			sign.append(key + "=" + value + "&");
		}
		sign.append("key=" + partner_key);
		// log.error("加密字符串=" + sign.toString());
		// 签名
		XmlUtil.setElementText(root, "/sign",
				DigestUtils.md5Hex(sign.toString()).toUpperCase());

		// log.error("统计订单调用请求" + doc.asXML());
		String result = HttpUtil.requestForString(
				"https://api.mch.weixin.qq.com/pay/orderquery", doc.asXML(),partner,
				false, false);
		WXPayResult wxPayResult = JdomParseXmlUtils.getWXPayResult(result);
		return wxPayResult;
	}
	*/
	
	
	/**
	 * 辅助
	 * 微信支付签名算法sign
	 * @param characterEncoding
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters,String key){
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			Object v = entry.getValue();
			if(null != v && !"".equals(v)     
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		System.out.println("createSign ...");
		System.out.println("字符串拼接后是："+sb.toString());
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		System.out.println("生成的签名为："+sign);
		return sign;
	}
	
	
	/**
	 * 辅助
	 * 获取随机字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer(
				"1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}
	
	/**
	 * 辅助
	 * 返回给微信的xml
	 * @param return_code
	 * @param return_msg
	 * @return
	 */
	public static String backWeixin(String return_code,String return_msg){
		String xml = "<xml>"
                + "<return_code><![CDATA["+return_code+"]]></return_code>"
				+"<return_msg><![CDATA["+return_msg+"]]></return_msg>"
                + "</xml>";
		return xml;
	}
}

