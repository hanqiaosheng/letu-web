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
 * 小程序微信支付、退款
 * @author Administrator
 *
 */
public class MinWXHttpApi {
	

	/**
	 * 微信支付
	 * @param uid
	 * @param openId
	 * @param order
	 * @param ip
	 * @return
	 */
	public static Map<String, Object> minPayMoney(Long uid,String openId,String appid,String domain,String partner,String partner_key,String ip,Double money,Long rentId) {
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
						+ "payfeedback/minnotify.action");
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
	public static Map<String, Object> minUnifiedOrder(Long uid,String openId,String appid,String domain,String partner,String partner_key,String ip,Double money,Integer flag) {
		/*openId = "oqzy6v3sbsQ6k9rCO2SuGR6Zf-tY";
		appid = "wxb415326aeccd228d";
		partner = "1416999702";
		partner_key = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00";*/
		Map<String, String> params = new HashMap<String, String>();
		// 公众账号ID
		params.put("appid", appid);
		// 附加数据
		params.put("attach", flag.toString());
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
						+ "payfeedback/mincnotify.action");
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
		params.put("desc", "爱蹭课提现");
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
		String nonce_str = getRandomString(16);// 随机字符串
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("out_refund_no", out_refund_no);
		packageParams.put("total_fee", intMoney+"");
		packageParams.put("refund_fee", intMoney+"");
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
			if(str.equals("SUCCESS")){
				return resultCode.equals("SUCCESS");  //判断返回值，是否执行成功
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/*public static void main(String[] args) {
		wechatRefund("4004842001201612021535743077","151480670595",new BigDecimal(0.01),new BigDecimal(0.01),"wx058b00481ed6372b","158afe0fb4ae946e64feb91731619011","1416999702","1416999702","AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00");
	}*/
	 
}
