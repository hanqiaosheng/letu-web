package org.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


/**
 * 身份证验证
 * @author Administrator
 *
 */
public class IdCardUtil {

		private static String host = "http://aliyun.id98.cn";
		private static String path = "/idcard";
		private static String method = "GET";
		private static String APPCODE = "APPCODE 8c270464c7f844d9931db2360e23864f";
		
		
		public static MessageUtil authentication(String idCard,String name) throws Exception{
				MessageUtil messageUtil = new MessageUtil();
				Map<String, Object> data = new HashMap<String,Object>();
			 Map<String, String> headers = new HashMap<String, String>();
			 headers.put("Authorization", APPCODE);
			 Map<String, String> querys = new HashMap<String, String>();
			 querys.put("cardno", idCard);
			 querys.put("name", name);
			 
			 HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			 System.out.println(response.toString());
			 String resultStr = EntityUtils.toString(response.getEntity());
			 JSONObject jsonobject = new JSONObject(resultStr);  
			 Integer code = (Integer) jsonobject.get("code");
			 Integer searchFlag = (Integer) jsonobject.get("isok");
			 data.put("code", code);
			 data.put("searchFlag", searchFlag);
			 messageUtil.setData(data);
			 return messageUtil;
		}
		
		
}
