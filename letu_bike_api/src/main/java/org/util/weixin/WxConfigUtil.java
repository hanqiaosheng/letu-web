package org.util.weixin;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.entity.weixin.pojo.AccessToken;

import net.sf.json.JSONObject;



/**
 * 公众平台通用接口工具类
 * 
 * @author james
 * @date 2015-02-27
 */
public class WxConfigUtil {
	// 生成签名的随机串
	//public final static String noncestr = getRandomString(16);
	// 获取access_token的接口地址（GET） 限2000（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 获取jsapi_ticket的接口地址（GET） 限2000（次/天）
	public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	// 缓存添加的时间
	public static String cacheAddTime = null;
	// token,ticket缓存
	public static Map<String, AccessToken> TOKEN_TICKET_CACHE = new HashMap<String, AccessToken>();
	// token对应的key
	private static final String TOKEN = "token";
	// ticket对应的key
	private static final String TICKET = "ticket";

	/**
	 * 外部获取签名入口类
	 * 
	 * @param appUrl
	 *            应用的url
	 * @param timestamp
	 *            访问页面的时间
	 * @return
	 */
	public static String getSignature(String appUrl, long currentTime,String appId ,String noncestr, String secret) {
		if (appUrl == null || "".equals(appUrl)) {
			return null;
		}
		String signature = null;
		AccessToken accessTocken = getAccessToken(appId, secret, currentTime);
		AccessToken accessTicket = getTicket(accessTocken.getToken(), currentTime);
		signature = signature(accessTicket.getTicket(), cacheAddTime, noncestr, appUrl);
		System.out.println("-=-=-=-=-=-=-=-=appUrl:" + appUrl);
		System.out.println("-=-=-=-=-=-=-=-=token:" + accessTocken.getToken());
		System.out.println("-=-=-=-=-=-=-=-=ticket:" + accessTicket.getTicket());
		System.out.println("-=-=-=-=-=-=-=-=signature:" + signature);
		System.out.println("-=-=-=-=-=-=-=-=timestamp:" + cacheAddTime);
		System.out.println("-=-=-=-=-=-=-=-=noncestr:" + noncestr);
		return signature;
	}

	/**
	 * 签名
	 * 
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	private static String signature(String jsapi_ticket, String timestamp, String noncestr, String url) {
		jsapi_ticket = "jsapi_ticket=" + jsapi_ticket;
		timestamp = "timestamp=" + timestamp;
		noncestr = "noncestr=" + noncestr;
		url = "url=" + url;
		String[] arr = new String[] { jsapi_ticket, noncestr, timestamp, url };
		// 将token、timestamp、nonce,url参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
			if (i != arr.length - 1) {
				content.append("&");
			}
		}
		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		content = null;
		return tmpStr;
	}
	
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
	 * 获取access_token
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret, long currentTime) {
		AccessToken tockenTicketCache = getTokenTicket(TOKEN);
		AccessToken accessToken = null;

		if (tockenTicketCache != null && (currentTime - tockenTicketCache.getAddTime() <= tockenTicketCache.getExpiresIn() )) {// 缓存存在并且没过期
			System.out.println("==========缓存中token已获取时长为：" + (currentTime - tockenTicketCache.getAddTime()) + "秒，可以重新使用");
			return tockenTicketCache;
		}
		System.out.println("==========缓存中token不存在或已过期===============");
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		System.out.println("返回值"+jsonObject.toString()+"APPID:APPSECRET"+appid+":"+appsecret);
		// 如果请求成功
		if (null != jsonObject) {
			accessToken = new AccessToken();
			accessToken.setToken(jsonObject.getString("access_token"));
			accessToken.setExpiresIn(jsonObject.getInt("expires_in") / 2);// 正常过期时间是7200秒，此处设置3600秒读取一次
			System.out.println("==========tocket缓存过期时间为:" + accessToken.getExpiresIn()  + "秒");
			accessToken.setAddTime(currentTime);
			updateAccessToken(TOKEN, accessToken);
		}
		return accessToken;
	}

	/**
	 * 获取ticket
	 * 
	 * @param token
	 * 
	 * @return
	 */
	private static AccessToken getTicket(String token, long currentTime) {
		AccessToken tockenTicketCache = getTokenTicket(TICKET);
		AccessToken accessToken = null;
		if (tockenTicketCache != null && (currentTime - tockenTicketCache.getAddTime() <= tockenTicketCache.getExpiresIn())) {// 缓存中有ticket
			System.out.println("==========缓存中ticket已获取时长为：" + (currentTime - tockenTicketCache.getAddTime()) + "秒，可以重新使用");
			return tockenTicketCache;
		}
		System.out.println("==========缓存中ticket不存在或已过期===============");
		String requestUrl = jsapi_ticket_url.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			accessToken = new AccessToken();
			accessToken.setTicket(jsonObject.getString("ticket"));
			accessToken.setExpiresIn(jsonObject.getInt("expires_in") / 2);// 正常过期时间是7200秒，此处设置3600秒读取一次
			System.out.println("==========ticket缓存过期时间为:" + accessToken.getExpiresIn()  + "秒");
			accessToken.setAddTime(currentTime);
			updateAccessToken(TICKET, accessToken);
		}
		return accessToken;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	private static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect(); 
			jsonObject = JSONObject.fromObject(buffer.toString());
			// jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println("Weixin server connection timed out.");
		} catch (Exception e) {
			System.out.println("https request error:{}" + e.getMessage());
		}
		return jsonObject;
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {

		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}

	/**
	 * 从缓存中读取token或者ticket
	 * 
	 * @return
	 */
	private static AccessToken getTokenTicket(String key) {
		if (TOKEN_TICKET_CACHE != null && TOKEN_TICKET_CACHE.get(key) != null) {
			System.out.println("==========从缓存中获取到了" + key + "成功===============");
			return TOKEN_TICKET_CACHE.get(key);
		}
		return null;
	}

	/**
	 * 更新缓存中token或者ticket
	 * 
	 * @return
	 */
	private static void updateAccessToken(String key, AccessToken accessTocken) {
		if (TOKEN_TICKET_CACHE != null && TOKEN_TICKET_CACHE.get(key) != null) {
			TOKEN_TICKET_CACHE.remove(key);
			System.out.println("==========从缓存中删除" + key + "成功===============");
		}
		TOKEN_TICKET_CACHE.put(key, accessTocken);
		cacheAddTime = String.valueOf(accessTocken.getAddTime());// 更新缓存修改的时间
		System.out.println("==========更新缓存中" + key + "成功===============");
	}

}