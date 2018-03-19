package org.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据字典类别存储工具
 * @author Administrator
 *
 */
public class TypedataUtil {
	private static Map<String, Map<Long, String>> typedata=new HashMap<String, Map<Long,String>>();

	public static Map<String, Map<Long, String>> getTypedata() {
		return typedata;
	}

	public static void setTypedata(Map<String, Map<Long, String>> typedata) {
		TypedataUtil.typedata = typedata;
	}
	
	//得到渠道类别
	public static Map<Long, String> getChannelId(){
		return typedata.get("channels");
	}
	
	//得到投放地点类别
	public static Map<Long, String> getPutAddress(){
		return typedata.get("putAddress");
	}
	
	//得到投放时间类别
	public static Map<Long, String> getPutTime(){
		return typedata.get("putTime");
	}
	
	
	//得到权限类别
	public static Map<Long, String> getPermission(){
		return typedata.get("permission");
	}

	
	//得到用户类型类别
	public static Map<Long, String> getUsertype(){
		return typedata.get("usertype");
	}

}
