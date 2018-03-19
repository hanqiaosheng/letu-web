package org.util;

public class CDATAUtil {
	
	public static String htmlSpecialchars(String str){
		str = str.replaceAll("'","").replaceAll("\r", "").replaceAll("\n", "");
		str = "<![CDATA[" + str + "]]>";
		return str;
	}
	
	public static String removeSpecialchars(String str){
		str = str.replace("<![CDATA[","").replace("]]>", "");
		return str;
	}
}
