package org.util.weixin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.util.XmlUtil;
import org.utils.http.HttpUtil;

/**
 * 微信支付、退款
 * @author Administrator
 *
 */
public class WXHttpApi {
	
	
	/**
	 * 通联支付
	 * @return
	 */
	public static String allinpay(){
//		Map<String, String> params = new HashMap<String, String>();
//		//字符集(1 - UTF-8)
//		params.put("inputCharset ", "1");
//		//服务器接受支付结果的后台地址 
//		params.put("receiveUrl", "http://daoren0316.6655.la/weixin/feedback/allinnotify.action");
//		//网关接收支付请求接口版本
//		params.put("version", "1.0");
//		//网关页面显示语言种类
//		params.put("language", "1");
//		//签名类型
//		params.put("signType", "0");
//		//商户号
//		params.put("merchantId", "100020091218001");
//		//订单号
//		params.put("orderNo", "NO20160119101248");
//		//订单金额
//		params.put("orderAmount", "100");
//		//币种
//		params.put("orderCurrency", "0");
//		//接入支付方式
//		params.put("payType", "0");
//		
//		Document doc = DocumentHelper.createDocument();
//		Element root = DocumentHelper.createElement("xml");
//		doc.setRootElement(root);
//		List<String> keys = new ArrayList<String>(params.keySet());
//		Collections.sort(keys);
//		StringBuilder str = new StringBuilder();
//		for (String key : keys) {
//			String value = params.get(key);
//			if (key.equals("attach") || key.equals("body")
//					|| key.equals("sign")) {
//				Dom4jUtil.setElementCDATAText(root, "/" + key, value);
//			} else {
//				Dom4jUtil.setElementText(root, "/" + key, value);
//			}
//			str.append(key + "=" + value + "&");
//		}
//		JSSDK jssdk = new JSSDK();
//		str.append("key=" + "1234567890");
//		// log.error("加密字符串=" + str.toString());
//		// 签名
//		Dom4jUtil.setElementText(root, "/sign",
//				DigestUtils.md5Hex(str.toString()).toUpperCase());
//
//		// log.error("统计订单调用请求" + doc.asXML());
//		String result = HttpUtil.requestForString(
//				"http://ceshi.allinpay.com/gateway/index.do", doc.asXML(),partner,
//				false, false);
//		// log.error("统计订单调用返回" + result);
//		
//		return result;
		return null;
	}
	
	/**
	 * 公众号微信支付
	 * @param uid
	 * @param openId
	 * @param order
	 * @param ip
	 * @return
	 */
	public static Map<String, Object> payMoney(Long uid,String openId,String appid,String domain,String partner,String partner_key,String ip,Double money,Long rentId) {
		/*appid = "wx058b00481ed6372b";
		openId = "oqzy6v3sbsQ6k9rCO2SuGR6Zf-tY";
		partner = "1416999702";
		partner_key = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00";*/
		Map<String, String> params = new HashMap<String, String>();
		// 公众账号ID
		params.put("appid", appid);
		// 附加数据
		params.put("attach", rentId.toString());
		// 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
		//params.put("device_info", "WEB");
		// 商品描述
		params.put("body", "单车充值订单");
		// 商户号
		params.put("mch_id", partner);
		// 随机字符串
		params.put("nonce_str", getRandomString(16));
		// 通知地址,接收微信支付异步通知回调地址
		params.put("notify_url", domain
						+ "payfeedback/notify.action");
		// 用户标识 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
		params.put("openid", openId);
		// 商户订单号 商户系统内部的订单号,32个字符内、可包含字母
		params.put("out_trade_no", uid+Sha1Util.getTimeStamp());
		// 终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
		params.put("spbill_create_ip", ip);
		// 总金额 订单总金额，只能为整数
		params.put("total_fee", (int) (money * 100) + "");
		// 交易类型 取值如下：JSAPI，NATIVE，APP，WAP
		params.put("trade_type", "JSAPI");

		Document doc = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("xml");
		doc.setRootElement(root);
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder str = new StringBuilder();
		for (String key : keys) {
			String value = params.get(key);
			if (key.equals("attach") || key.equals("body")
					|| key.equals("sign")) {
				XmlUtil.setElementCDATAText(root, "/" + key, value);
			} else {
				XmlUtil.setElementText(root, "/" + key, value);
			}
			str.append(key + "=" + value + "&");
		}
		str.append("key=" + partner_key);
		// log.error("加密字符串=" + str.toString());
		// 签名
		XmlUtil.setElementText(root, "/sign",
				DigestUtils.md5Hex(str.toString()).toUpperCase());

		// log.error("统计订单调用请求" + doc.asXML());
		String result = HttpUtil.requestForString(
				"https://api.mch.weixin.qq.com/pay/unifiedorder", doc.asXML(),partner,
				false, false);
		// log.error("统计订单调用返回" + result);
		doc = XmlUtil.parseXml(result);
		root = doc.getRootElement();
		String returnCode = XmlUtil.getElementText(root, "return_code",
				"FAIL");// SUCCESS/FAIL
		String resultCcode = XmlUtil.getElementText(root, "result_code",
				"FAIL");// SUCCESS/FAIL
		if (!returnCode.equals("SUCCESS")&&!resultCcode.equals("SUCCESS")) {
			return null;
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("appId", appid);
		data.put("timeStamp", Sha1Util.getTimeStamp());
		data.put("nonceStr", getRandomString(16));
		data.put("signType", "MD5");
		data.put("package", "prepay_id=" + XmlUtil.getElementText(root, "prepay_id", null));
		List<String> keyss = new ArrayList<String>(data.keySet());
		Collections.sort(keyss);
		StringBuilder str2 = new StringBuilder();
		for (String key : keyss) {
			str2.append(key + "=" + data.get(key) + "&");
		}
		str2.append("key=" + partner_key);
		data.put("paySign", DigestUtils.md5Hex(str2.toString())
				.toUpperCase());
		data.put("state","1");
		return data;
	}
	
	
	/**
	 * 公众号微信支付邮费
	 * @param uid
	 * @param openId
	 * @param order
	 * @param ip
	 * @return
	 */
	public static Map<String, Object> payPostage(Long uid,String openId,String appid,String domain,String partner,String partner_key,String ip,Double money,String rentIdAndInvoiceIdstr) {
		/*appid = "wx058b00481ed6372b";
		openId = "oqzy6v3sbsQ6k9rCO2SuGR6Zf-tY";
		partner = "1416999702";
		partner_key = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00";*/
		Map<String, String> params = new HashMap<String, String>();
		// 公众账号ID
		params.put("appid", appid);
		// 附加数据
		params.put("attach", rentIdAndInvoiceIdstr);
		// 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
		//params.put("device_info", "WEB");
		// 商品描述
		params.put("body", "邮费订单");
		// 商户号
		params.put("mch_id", partner);
		// 随机字符串
		params.put("nonce_str", getRandomString(16));
		// 通知地址,接收微信支付异步通知回调地址
		params.put("notify_url", domain
						+ "payfeedback/payNotify.action");
		// 用户标识 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
		params.put("openid", openId);
		// 商户订单号 商户系统内部的订单号,32个字符内、可包含字母
		params.put("out_trade_no", uid+Sha1Util.getTimeStamp());
		// 终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
		params.put("spbill_create_ip", ip);
		// 总金额 订单总金额，只能为整数
		params.put("total_fee", (int) (money * 100) + "");
		// 交易类型 取值如下：JSAPI，NATIVE，APP，WAP
		params.put("trade_type", "JSAPI");

		Document doc = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("xml");
		doc.setRootElement(root);
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder str = new StringBuilder();
		for (String key : keys) {
			String value = params.get(key);
			if (key.equals("attach") || key.equals("body")
					|| key.equals("sign")) {
				XmlUtil.setElementCDATAText(root, "/" + key, value);
			} else {
				XmlUtil.setElementText(root, "/" + key, value);
			}
			str.append(key + "=" + value + "&");
		}
		str.append("key=" + partner_key);
		// log.error("加密字符串=" + str.toString());
		// 签名
		XmlUtil.setElementText(root, "/sign",
				DigestUtils.md5Hex(str.toString()).toUpperCase());

		// log.error("统计订单调用请求" + doc.asXML());
		String result = HttpUtil.requestForString(
				"https://api.mch.weixin.qq.com/pay/unifiedorder", doc.asXML(),partner,
				false, false);
		// log.error("统计订单调用返回" + result);
		doc = XmlUtil.parseXml(result);
		root = doc.getRootElement();
		String returnCode = XmlUtil.getElementText(root, "return_code",
				"FAIL");// SUCCESS/FAIL
		String resultCcode = XmlUtil.getElementText(root, "result_code",
				"FAIL");// SUCCESS/FAIL
		if (!returnCode.equals("SUCCESS")&&!resultCcode.equals("SUCCESS")) {
			return null;
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("appId", appid);
		data.put("timeStamp", Sha1Util.getTimeStamp());
		data.put("nonceStr", getRandomString(16));
		data.put("signType", "MD5");
		data.put("package", "prepay_id=" + XmlUtil.getElementText(root, "prepay_id", null));
		List<String> keyss = new ArrayList<String>(data.keySet());
		Collections.sort(keyss);
		StringBuilder str2 = new StringBuilder();
		for (String key : keyss) {
			str2.append(key + "=" + data.get(key) + "&");
		}
		str2.append("key=" + partner_key);
		data.put("paySign", DigestUtils.md5Hex(str2.toString())
				.toUpperCase());
		data.put("state","1");
		return data;
	}
	
	/**
	 * 充值
	 * 
	 * @param openId
	 * @param order
	 * @param ip
	 * @return
	 */
	public static Map<String, Object> unifiedOrder(Long uid,String openId,String appid,String domain,String partner,String partner_key,String ip,Double money,Integer flag,Long channelId) {
		
		/*appid = "wx058b00481ed6372b";
		openId = "oqzy6v3sbsQ6k9rCO2SuGR6Zf-tY";
		partner = "1416999702";
		partner_key = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00";*/
		Map<String, String> params = new HashMap<String, String>();
		// 公众账号ID
		params.put("appid", appid);
		// 附加数据
		params.put("attach", flag+","+channelId);
		// 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
		//params.put("device_info", "WEB");
		// 商品描述
		params.put("body", "单车充值订单");
		// 商户号
		params.put("mch_id", partner);
		// 随机字符串
		params.put("nonce_str", getRandomString(16));
		// 通知地址,接收微信支付异步通知回调地址
		params.put("notify_url", domain
						+ "payfeedback/cnotify.action");
		// 用户标识 trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。
		params.put("openid", openId);
		// 商户订单号 商户系统内部的订单号,32个字符内、可包含字母
		params.put("out_trade_no", uid+Sha1Util.getTimeStamp());
		// 终端IP APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
		params.put("spbill_create_ip", ip);
		// 总金额 订单总金额，只能为整数
		params.put("total_fee", (int) (money * 100) + "");
		// 交易类型 取值如下：JSAPI，NATIVE，APP，WAP
		params.put("trade_type", "JSAPI");

		Document doc = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("xml");
		doc.setRootElement(root);
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder str = new StringBuilder();
		for (String key : keys) {
			String value = params.get(key);
			if (key.equals("attach") || key.equals("body")
					|| key.equals("sign")) {
				XmlUtil.setElementCDATAText(root, "/" + key, value);
			} else {
				XmlUtil.setElementText(root, "/" + key, value);
			}
			str.append(key + "=" + value + "&");
		}
		str.append("key=" + partner_key);
		// log.error("加密字符串=" + str.toString());
		// 签名
		XmlUtil.setElementText(root, "/sign",
				DigestUtils.md5Hex(str.toString()).toUpperCase());

		// log.error("统计订单调用请求" + doc.asXML());
		String result = HttpUtil.requestForString(
				"https://api.mch.weixin.qq.com/pay/unifiedorder", doc.asXML(),partner,
				false, false);
		// log.error("统计订单调用返回" + result);
		doc = XmlUtil.parseXml(result);
		root = doc.getRootElement();
		String returnCode = XmlUtil.getElementText(root, "return_code",
				"FAIL");// SUCCESS/FAIL
		String resultCcode = XmlUtil.getElementText(root, "result_code",
				"FAIL");// SUCCESS/FAIL
		if (!returnCode.equals("SUCCESS")&&!resultCcode.equals("SUCCESS")) {
			return null;
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("appId", appid);
		data.put("timeStamp", Sha1Util.getTimeStamp());
		data.put("nonceStr", getRandomString(16));
		data.put("signType", "MD5");
		data.put("package", "prepay_id=" + XmlUtil.getElementText(root, "prepay_id", null));
		List<String> keyss = new ArrayList<String>(data.keySet());
		Collections.sort(keyss);
		StringBuilder str2 = new StringBuilder();
		for (String key : keyss) {
			str2.append(key + "=" + data.get(key) + "&");
		}
		str2.append("key=" + partner_key);
		data.put("paySign", DigestUtils.md5Hex(str2.toString())
				.toUpperCase());
		data.put("state","1");
		return data;
	}

	
	
	/**
	 * 提现
	 * 
	 * @param order
	 * @return
	 */
	public static boolean refundOrder(String openid,String appid,String partner,String partner_key,String ip,BigDecimal money,String tradeNo) {
		Map<String, String> params = new HashMap<String, String>();
		String finalmoney = String.format("%.2f", money);
		finalmoney = finalmoney.replace(".", "");
		int intMoney = Integer.parseInt(finalmoney);
		// 公众账号ID
		params.put("mch_appid", appid);
		// 商户号
		params.put("mchid", partner);
		// 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
		//params.put("device_info", "WEB");
		// 随机字符串
		params.put("nonce_str", getRandomString(16));
		// 商户订单号 商户系统内部的订单号,32个字符内、可包含字母
		params.put("partner_trade_no", tradeNo);
		// openId
		params.put("openid", openid);
		// 检验用户姓名
		params.put("check_name", "NO_CHECK");
		// 订单总金额，单位为分，只能为整数
		params.put("amount", (int) (intMoney * 100) + "");
		// 企业付款描述信息
		params.put("desc", "单车提现");
		// Ip地址
		params.put("spbill_create_ip", ip);

		String result = HttpUtil.requestForString(
				"https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers",
				buildXml(params,partner_key),partner, false, true);
		Document doc = XmlUtil.parseXml(result);
		Element root = doc.getRootElement();
		return XmlUtil.getElementText(root, "return_code", "FAIL").equals(
				"SUCCESS")
				&& XmlUtil.getElementText(root, "result_code", "FAIL")
						.equals("SUCCESS");
	}
	
	private static String buildXml(Map<String, String> params,String partner_key) {
		Document doc = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("xml");
		doc.setRootElement(root);
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuilder str = new StringBuilder();
		for (String key : keys) {
			String value = params.get(key);
			XmlUtil.setElementText(root, "/" + key, value);
			str.append(key + "=" + value + "&");
		}
		str.append("key=" + partner_key);
		// 签名
		XmlUtil.setElementText(root, "/sign",
				DigestUtils.md5Hex(str.toString()).toUpperCase());
		return doc.asXML();
	}
	
	/**
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
	 *  * 微信退款
	 * @param out_refund_no 退款单号
	 * @param out_trade_no 订单号系统内部
	 * @param total_fee  总金额       一般总金额与退款金额一致， 退款金额可以不可以大于总金额
	 * @param refund_fee  退款金额
	 * @param appid   微信公众号appid
	 * @param appsecret 微信公众号appsecret
	 * @param mch_id  微信商户id
	 * @param op_user_id  就是MCHID
	 * @param partnerkey  商户平台上的那个KEY
	 */
	public static boolean wechatRefund(String out_refund_no, String out_trade_no, BigDecimal total_fee, BigDecimal refund_fee,
			String appid, String appsecret, String mch_id, String op_user_id, String partnerkey, String certificate) {
//		out_refund_no = "4004212001201605206058441529";// 退款单号
//		out_trade_no = "1463706822810";// 订单号系统内部
//		total_fee = "2";// 总金额
//		refund_fee = "1";// 退款金额
//		 appid = "wx45aa420e9c08ac54"; //微信公众号appid
//		 appsecret = "82fa1efa97e4eeb83304ad1f6b346cf9"; //微信公众号appsecret
//		 mch_id = "1271874301";  //微信商户id
//		 op_user_id = "1271874301";//就是MCHID
//		 partnerkey = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00";//商户平台上的那个KEY
		String finalmoney = String.format("%.2f", total_fee);
		finalmoney = finalmoney.replace(".", "");
		int intMoney = Integer.parseInt(finalmoney);
		String refundmoney = String.format("%.2f", refund_fee);
		refundmoney = refundmoney.replace(".", "");
		int intReMoney = Integer.parseInt(refundmoney);
		String nonce_str = getRandomString(16);// 随机字符串
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("out_refund_no", out_refund_no);
		packageParams.put("total_fee", intMoney+"");
		packageParams.put("refund_fee", intReMoney+"");
		packageParams.put("op_user_id", op_user_id);
		//packageParams.put("refund_account", "REFUND_SOURCE_RECHARGE_FUNDS");

		RequestHandler reqHandler = new RequestHandler(
				null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		packageParams.put("sign", sign);
		String xml = XmlUtil.toXml(packageParams);
		String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		try {
			String result= ClientCustomSSL.doRefund(createOrderURL, xml,mch_id,certificate);
			System.out.println(result);
			Document doc = XmlUtil.parseXml(result);  //转化为xml
			Element root = doc.getRootElement();
			String str = XmlUtil.getElementText(root, "return_code", "FAIL");
			String resultCode = XmlUtil.getElementText(root, "result_code", "FAIL");
			if(str.equals("SUCCESS")&&resultCode.equals("SUCCESS")){//判断返回值，是否执行成功
				return true;  
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 *  * 微信APP退款
	 * @param out_refund_no 退款单号
	 * @param out_trade_no 订单号系统内部
	 * @param total_fee  总金额       一般总金额与退款金额一致， 退款金额可以不可以大于总金额
	 * @param refund_fee  退款金额
	 * @param appid   微信公众号appid
	 * @param appsecret 微信公众号appsecret
	 * @param mch_id  微信商户id
	 * @param op_user_id  就是MCHID
	 * @param partnerkey  商户平台上的那个KEY
	 */
	public static boolean wechatRefundAPP(String out_refund_no, String out_trade_no, BigDecimal total_fee, BigDecimal refund_fee,
			String appid, String appsecret, String mch_id, String op_user_id, String partnerkey) {
//		out_refund_no = "4004212001201605206058441529";// 退款单号
//		out_trade_no = "1463706822810";// 订单号系统内部
//		total_fee = "2";// 总金额
//		refund_fee = "1";// 退款金额
//		 appid = "wx45aa420e9c08ac54"; //微信公众号appid
//		 appsecret = "82fa1efa97e4eeb83304ad1f6b346cf9"; //微信公众号appsecret
//		 mch_id = "1271874301";  //微信商户id
//		 op_user_id = "1271874301";//就是MCHID
//		 partnerkey = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00";//商户平台上的那个KEY
		String finalmoney = String.format("%.2f", total_fee);
		finalmoney = finalmoney.replace(".", "");
		int intMoney = Integer.parseInt(finalmoney);
		String refundmoney = String.format("%.2f", refund_fee);
		refundmoney = refundmoney.replace(".", "");
		int intReMoney = Integer.parseInt(refundmoney);
//		int intMoney = (int) 1;
		String nonce_str = getRandomString(16);// 随机字符串
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("out_refund_no", out_refund_no);
		packageParams.put("total_fee", intMoney+"");
		packageParams.put("refund_fee", intReMoney+"");
		packageParams.put("op_user_id", op_user_id);
		//packageParams.put("refund_account", "REFUND_SOURCE_RECHARGE_FUNDS");

		RequestHandler reqHandler = new RequestHandler(
				null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		packageParams.put("sign", sign);
		String xml = XmlUtil.toXml(packageParams);
		String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		try {
			String result= ClientCustomSSL.doRefundApp(createOrderURL, xml);
			System.out.println(result);
			Document doc = XmlUtil.parseXml(result);  //转化为xml
			Element root = doc.getRootElement();
			String str = XmlUtil.getElementText(root, "return_code", "FAIL");
			String resultCode = XmlUtil.getElementText(root, "result_code", "FAIL");
			if(str.equals("SUCCESS")&&resultCode.equals("SUCCESS")){
				return true;  //判断返回值，是否执行成功
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 *  * 微信查询
	 
	 * @param out_trade_no 订单号系统内部
	 
	 * @param appid   微信公众号appid
	 
	 * @param mch_id  微信商户id
	 * @param op_user_id  就是MCHID
	 * @param partnerkey  商户平台上的那个KEY
	 */
	public static int wechatRefundInquiry(String out_refund_no, String appid,String appsecret, String mch_id, String op_user_id, String partnerkey){
		String nonce_str = getRandomString(16);// 随机字符串
		//SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		SortedMap<String, String> parameters = new TreeMap<String, String>();
	       parameters.put("appid", appid );
	       parameters.put("mch_id", mch_id);
	       //parameters.put("device_info","");//微信支付分配的终端设备号
	       parameters.put("nonce_str", nonce_str );
	       //下列四个单号同时存在优先级refund_id>out_refund_no>transaction_id>out_trade_no
	     //parameters.put("transaction_id", out_refund_no);//微信交易单号
	       parameters.put("out_trade_no", out_refund_no);//商户交易单号
	       //parameters.put("out_refund_no",out_refund_no);//商户退款单号
	       //parameters.put("refund_id", out_refund_no);//微信退款单号
	       
	       RequestHandler reqHandler = new RequestHandler(
					null, null);
			reqHandler.init(appid, appsecret, partnerkey);

			String sign = reqHandler.createSign(parameters);
			parameters.put("sign", sign);
	       //String reuqestXml = PayCommonUtil.getRequestXml(parameters);
	       String xml = XmlUtil.toXml(parameters);
	       //String createOrderURL = "https://api.mch.weixin.qq.com/pay/refundquery";
	       String result = HttpUtil.requestForString(
					"https://api.mch.weixin.qq.com/pay/refundquery", xml,mch_id,
					false, false);
	       /*System.out.println(result);*/
	       
	       Document doc = XmlUtil.parseXml(result);  //转化为xml
	       Element root = doc.getRootElement();
		   String str = XmlUtil.getElementText(root, "return_code", "FAIL");
		   String resultCode = XmlUtil.getElementText(root, "result_code", "FAIL");
		   if(str.equals("SUCCESS")){
			   if( resultCode.equals("SUCCESS")){
				   //判断返回值，是否执行成功
				   String x = XmlUtil.getElementText(root, "refund_status_0", "FAIL");
				   //System.out.println("SUCCESS");
				   if(x.equals("SUCCESS")){
					   return 1;  //退款成功
				   }
				   
				   if(x.equals("REFUNDCLOSE")){
					   return 2;  //退款关闭
				   }
				   if(x.equals("NOTSURE")){
					   return 3;  //退款未确定
				   }
				   if(x.equals("PROCESSING")){
					   return 4;   //退款处理中
				   }
				   if(x.equals("CHANGE")){
					   return 5;   //退款异常
				   }
			   }
		   }
	       
	      /* Map<String, String> map = XmlUtil.parseXml(result);
	       String returnCode = map.get("return_code");
	       String resultCode = map.get("result_code");
	       if(returnCode.equalsIgnoreCase("SUCCESS")&&resultCode.equalsIgnoreCase("SUCCESS")){
	          String refundCount = map.get("refund_count");
	          Integer count = new Integer(refundCount);
	          for (int i = 0; i < count; i++) {
	            String refundStatus = (String) map.get("refund_status_"+i);
	            //refudnStatus有如下状态:
	            //SUCCES—退款成功，FAIL—退款失败，PROCESSING—退款处理中NOTSURE—未确定，需要商户原退款单号重新发起
	            //CHANGE—转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号，需要商户人工干
	            //预，通过线下或者财付通转账的方式进行退款。
	            if(refundStatus.equalsIgnoreCase("SUCCESS")){
	                //TODO退款成功操作
	                System.out.println("退款状态为已成功退款");
	            }
	           
	          }
	       }*/
		return 0; //未退款
	}
	/*public static void main(String[] args) {
		wechatRefundInquiry("16231499771843","wx058b00481ed6372b","158afe0fb4ae946e64feb91731619011","1416999702","1416999702","AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00");
		//wechatRefundInquiry("4004842001201709163525296789","wx058b00481ed6372b","158afe0fb4ae946e64feb91731619011","1416999702","1416999702","AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00");
		//wechatRefundInquiry("101493004067","wx06690124355bd659","be29a92e16439c92a6457f443b3ba14d","1453011702","1453011702","AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00");
  }*/
}
