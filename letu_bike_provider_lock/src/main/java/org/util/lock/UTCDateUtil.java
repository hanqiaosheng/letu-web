package org.util.lock;

import java.util.Date;

public class UTCDateUtil {
	public static String tranDate(Date date){
		String UTCDate=new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(date);
		return UTCDate;
	}
	
}
