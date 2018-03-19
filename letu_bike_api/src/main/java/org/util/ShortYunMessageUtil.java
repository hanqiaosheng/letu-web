package org.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * 云片短信工具
 * @author sun
 *
 */
public class ShortYunMessageUtil {
    //查账户信息的http地址
    private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v1/user/get.json";

    //智能匹配模板发送接口的http地址
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v1/sms/send.json";

    //模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "https://sms.yunpian.com/v1/sms/tpl_send.json";

    //发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "https://voice.yunpian.com/v1/voice/send.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";
    //乐途 apikey
    private static String apikey = "6aab545daa2a098aecef6b0c0f504f6c";
    
    
    
    /**
     * 发送验证码
     * @param code
     * @param mobile
     * @throws IOException
     */
    public static String sendCode(String code, String mobile) throws IOException {
    	Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);//【乐途共享单车】感谢您注册乐途共享单车，您的验证码是#code#。如非本人操作，请忽略本短信。
//        params.put("text", "【"+channelMessageKey+"】验证码为："+String.valueOf(code)+"，有效时间为5分钟，如非本人操作请忽略");
        params.put("text", "【全域骑游】手机端租还车操作，并在指定区域内还车，视为有效。您的验证码是"+code+"。如非本人操作，请联系0571-56231981");
        params.put("mobile", mobile);
        
		String message = post(URI_SEND_SMS, params);
		JSONObject json = new JSONObject();
		json = JSONObject.fromObject(message);
        System.out.println(message);
        //获取返回码
        return json.getString("code");
    }
    
    
    /**
     * 发送低电量提醒
     * @param mobile
     * @return
     * @throws IOException
     */
    public static void sendMassage( String mobile) throws IOException {
    	Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);//【乐途共享单车】感谢您注册乐途共享单车，您的验证码是#code#。如非本人操作，请忽略本短信。
//        params.put("text", "【"+channelMessageKey+"】验证码为："+String.valueOf(code)+"，有效时间为5分钟，如非本人操作请忽略");
        params.put("text", "【全域骑游】系统检测到您管辖的景区存在电量过低的车辆，请及时检查并充电！");
        params.put("mobile", mobile);
        
		String message = post(URI_SEND_SMS, params);
		//JSONObject json = new JSONObject();
		//json = JSONObject.fromObject(message);
        System.out.println(message);
        //获取返回码
        //return json.getString("code");
    }
    
    /*public static void main(String[] args) throws IOException{
    	sendMassage1("13587714878");
    }*/
    
    /**
     * 客服临时还车时间过久发送提醒
     * @param mobile
     * @return
     * @throws IOException
     */
    public static void sendMassage1( String mobile) throws IOException {
    	Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);//【乐途共享单车】感谢您注册乐途共享单车，您的验证码是#code#。如非本人操作，请忽略本短信。
//        params.put("text", "【"+channelMessageKey+"】验证码为："+String.valueOf(code)+"，有效时间为5分钟，如非本人操作请忽略");
        params.put("text", "【全域骑游】 手机号为"+mobile+"的用户处于临时还车状态已超过30分钟，请相关工作人员进行关注，做好及时的通知工作。");
        params.put("mobile", "18072829162");
        
		String message = post(URI_SEND_SMS, params);
		//JSONObject json = new JSONObject();
		//json = JSONObject.fromObject(message);
        System.out.println(message);
        //获取返回码
        //return json.getString("code");
    }
    
    /**
     * 用户临时还车时间过久发送提醒
     * @param mobile
     * @return
     * @throws IOException
     */
    public static void sendMassage2( String mobile) throws IOException {
    	Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);//【乐途共享单车】感谢您注册乐途共享单车，您的验证码是#code#。如非本人操作，请忽略本短信。
//        params.put("text", "【"+channelMessageKey+"】验证码为："+String.valueOf(code)+"，有效时间为5分钟，如非本人操作请忽略");
        params.put("text", "【全域骑游】 亲爱的骑友，用车太久注意休息哦~如继续用车，结算时费用超出部分需另外支付，如不再续用，请尽快归还单车，避免继续产生费用!");
        params.put("mobile", mobile);
		String message = post(URI_SEND_SMS, params);
		//JSONObject json = new JSONObject();
		//json = JSONObject.fromObject(message);
        System.out.println(message);
        //获取返回码
        //return json.getString("code");
    }
    /**
     * 客服余额低于10元发送提醒
     * @param mobile
     * @return
     * @throws IOException
     */
    public static void sendMassage3( String mobile) throws IOException {
    	Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);//【乐途共享单车】感谢您注册乐途共享单车，您的验证码是#code#。如非本人操作，请忽略本短信。
//        params.put("text", "【"+channelMessageKey+"】验证码为："+String.valueOf(code)+"，有效时间为5分钟，如非本人操作请忽略");
        params.put("text", "【全域骑游】 手机号为"+mobile+"的用户余额即将不足，请联系并提醒该用户。");
        params.put("mobile", "18072829162");
        
		String message = post(URI_SEND_SMS, params);
		//JSONObject json = new JSONObject();
		//json = JSONObject.fromObject(message);
        System.out.println(message);
        //获取返回码
        //return json.getString("code");
    }
    
    /**
     * 用户余额低于10元发送提醒
     * @param mobile
     * @return
     * @throws IOException
     */
    public static void sendMassage4( String mobile) throws IOException {
    	Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);//【乐途共享单车】感谢您注册乐途共享单车，您的验证码是#code#。如非本人操作，请忽略本短信。
//        params.put("text", "【"+channelMessageKey+"】验证码为："+String.valueOf(code)+"，有效时间为5分钟，如非本人操作请忽略");
        params.put("text", "【全域骑游】 亲爱的骑行者，您的账户余额即将不足，为了您更便捷的出行，请及时充值。");
        params.put("mobile", mobile);
        
		String message = post(URI_SEND_SMS, params);
		//JSONObject json = new JSONObject();
		//json = JSONObject.fromObject(message);
        System.out.println(message);
        //获取返回码
        //return json.getString("code");
    }
    /**
     * 用户反馈发送短信
     * @throws IOException
     */
    public static void sendMassageFeed(String phone) throws IOException{
		Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", "【全域骑游】 尊敬的贾相颖，您有一个来自"+phone+"的待处理反馈，请您及时处理！");
        params.put("mobile", "18072829162");
        
		String message = post(URI_SEND_SMS, params);
        System.out.println(message);
	}
    
    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */
	private static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }


	

}
