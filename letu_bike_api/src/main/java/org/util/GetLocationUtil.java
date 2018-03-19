package org.util;

import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetLocationUtil {
	
	private static String APPAK = "VGBfP5N9u8HY7Za0YDj73PD21hzjzYan";//ios
	private static String ANZHUOAK = "27SQU9VG2GNGs1gjTRNUM4YuaEtULYD1";//安卓
	private static Integer APPSERVICEID = 146384;
	private static String APPMCODE = "com.quanyu.quanyuBike";//ios
	private static String ANZHUOMCODE = "F5:E9:BE:95:30:6B:46:EA:CD:A9:2B:DD:66:8B:56:30:2C:B3:65:C0;com.qs.letubicycle";//安卓
	private static String APPGETURL = "http://yingyan.baidu.com/api/v3/track/gettrack";
	private static String  APPTYPE= "gcj02";
	private static String APPDISTANCEURL="http://yingyan.baidu.com/api/v3/track/getdistance";
	public static void main(String[] args) {  
		getPonts((long)1500964898,(long)1500965498,(long)48,0);
		//getAdd("121.210708", "28.233017");
		//getDistance((long)1500531779,(long)1500531969,(long)66);
    }  
	
    public static String getAdd(String log, String lat ){  
        //lat 小  log  大  
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)  
    	String lnglat = log+","+lat;
        String urlString = "http://restapi.amap.com/v3/geocode/regeo?location="+lnglat+"&key=c38d53e053fa758c39cc85af8fa5db7d";  
        String res = "";     
        try {     
            URL url = new URL(urlString);    
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();    
            conn.setDoOutput(true);    
            conn.setRequestMethod("POST");    
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));    
            String line;    
           while ((line = in.readLine()) != null) {    
               res += line+"\n";    
         }    
            in.close();    
        } catch (Exception e) {    
            System.out.println("error in wapaction,and e is " + e.getMessage());    
        }   
        System.out.println(res);  
        JSONObject jsonObject = JSONObject.fromObject(res);  
        JSONObject j_2 = jsonObject.getJSONObject("regeocode");
        
        String addr = j_2.getString("formatted_address");
        System.out.println(addr);
        if(null!=addr&&!addr.equals("")){
        	return addr;
        }
       return null;
        
    }  
    
    public static StringBuffer getPonts(Long startTime,Long endTime,Long userId,Integer IsIOS){ 
    	String url = "";
    	if(IsIOS==1){//ios
    		url = APPGETURL+"?ak="+APPAK+"&service_id="+APPSERVICEID+"&entity_name="+userId+"&start_time="+startTime+"&end_time="+endTime+"&mcode="+APPMCODE+"&coord_type_output="+APPTYPE;
    	}else{//anzhuo
    		url = APPGETURL+"?ak="+ANZHUOAK+"&service_id="+APPSERVICEID+"&entity_name="+userId+"&start_time="+startTime+"&end_time="+endTime+"&mcode="+ANZHUOMCODE+"&coord_type_output="+APPTYPE;
    	}
    	if(url.equals("")){
    		return null;
    	}
    	String message = getMe(url);
    	JSONObject jsonObject = JSONObject.fromObject(message); 
    	System.out.println(jsonObject.getInt("status")+jsonObject.getString("message"));
    	if(jsonObject.getInt("status")==0){
    		JSONArray jsonArray = JSONArray.fromObject(jsonObject.getString("points")); 
    		StringBuffer sb = new StringBuffer(jsonArray.toString());
    		System.out.println(sb);
    		return sb;
    	}
    	return null;
    }
    
    public static String getDistance(Long startTime,Long endTime,Long userId,Integer IsIOS){
    	String url = "";
    	if(IsIOS==1){//ios
    		url = APPDISTANCEURL+"?ak="+APPAK+"&service_id="+APPSERVICEID+"&entity_name="+userId+"&start_time="+startTime+"&end_time="+endTime+"&mcode="+APPMCODE+"&coord_type_output="+APPTYPE;
    	}else{
    		url = APPDISTANCEURL+"?ak="+ANZHUOAK+"&service_id="+APPSERVICEID+"&entity_name="+userId+"&start_time="+startTime+"&end_time="+endTime+"&mcode="+ANZHUOMCODE+"&coord_type_output="+APPTYPE;
    	}
    	
    	String message = getMe(url);
    	JSONObject jsonObject = JSONObject.fromObject(message); 
    	System.out.println(jsonObject.getInt("status")+jsonObject.getString("message"));
    	if(jsonObject.getInt("status")==0){
    		jsonObject.getString("distance"); 
    		System.out.println(jsonObject.getString("distance"));
    		return jsonObject.getString("distance");
    	}
    	return null;
    }
    
    /**
     * 基于HttpClient 4.3的通用GET方法
     *
     * @param url       提交的URL
     * @return 提交响应
     */
	private static String getMe(String newUrl){
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
        	HttpGet get = new HttpGet(newUrl);
            response = client.execute(get);
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
