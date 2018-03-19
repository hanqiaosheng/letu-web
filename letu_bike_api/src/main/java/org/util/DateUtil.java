package org.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.utils.http.HttpUtil;

import net.sf.json.JSONObject;


/**
 * 日期工具类
 * @author Administrator
 *
 */
public class DateUtil {

	private static Date date_1000 = null;

	private static String url= "http://api.jisuapi.com/calendar/query?appkey=64b5723491ac009f&date=";
	
	static {
		try {
			date_1000 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			.parse("1000-01-01 00:00:00");
		} catch (ParseException e) {
		}
	}

	public static Date get1000Date() {
		return date_1000;
	}
	
	public static String format(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	/**
	 * 将Date 转换为 Sring类型     yyyy-MM-dd HH:mm
	 * @param date
	 * @return
	 */
	public static String format02(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}
	
	public static String format03(Date date){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
	}
	
	
	public static String formatDay(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public static String formatDay02(Date date) {
		return new SimpleDateFormat("yy-MM-dd").format(date);
	}
	
	public static String formatDayHour(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH").format(date);
	}
	
	public static String formatHour(Date date) {
		return new SimpleDateFormat("HH").format(date);
	}
	
	public static String formatYear(Date date){
		return new SimpleDateFormat("YYYY").format(date);
	}
	
	public static String formatMonth(Date date){
		return new SimpleDateFormat("MM").format(date);
	}
	
	public static String formatHourAndMinute(Date date){
		return new SimpleDateFormat("HH:mm").format(date);
	}
	
	public static String format(String pattern, Date date) {
		if(null == date) {
			return null;
		}
		return new SimpleDateFormat(pattern).format(date);
	}
	public static String formatWeek(Date date){
		SimpleDateFormat dateFm = new SimpleDateFormat("EEEE",Locale.CHINA);
		return dateFm.format(date);
	}
	   
	
	/**
	 * 取得下一个更新统计缓存时间，即明日0点
	 * @return
	 */
	public static Date nextTaskTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	/**
	 * 取得当前月份
	 * @return
	 */
	public static Date getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取 某个时间为起点  n个月后的 日期
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date getAfterMonth(Date date,int month){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		//获取当前时间的n个月后的时间
        calendar.add(Calendar.MONTH,month);
		return calendar.getTime();
	}
	
	/**
	 * 取得当日0点
	 * @return
	 */
	public static Date getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
     * 获取当年的第一天
     * @param year
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }
     
    /**
     * 获取当年的最后一天
     * @param year
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();  
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }
    
    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }
     
    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();
         
        return currYearLast;
    }
	
	public static Date addMinute(Date date, int min) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, min);
		return calendar.getTime();
	}
	
	public static int getDayWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	
	public static Date threeDaysLater(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 3);
		return calendar.getTime();
	}
	
	public static int getHourOfDay() {
		return getHourOfDay(new Date());
	}
	
	public static int getHourOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static int getDayHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	public static Date getDate(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}
	
	public static Date getPreDate(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, n);
		return calendar.getTime();
	}
	
	public static Date getNextDayDate(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	public static Date twoHoursLater(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 2);
		return calendar.getTime();
	}
	
	public static Date oneHoursLater(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, 1);
		return calendar.getTime();
	}
	
	public static Date addHours(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}
	
	public static Date getMonthOneDate(int next) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MONTH, next);
		return calendar.getTime();
	}
	
	public static Date get0Dian(Date date, int next) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DATE, next);
		return calendar.getTime();
	}
	
//	public static void main(String[] args) {
//		String a = formatDD(new Date());
//		System.out.println(a);
//	}
	
	/**
	 * 传入一个日期和天数，计算该日期加num天后的日期
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date plusDate(Date date, Integer num){
		Date calculDate = new Date(date.getTime() + num*1000*60*60*24);
		return calculDate;
	}
	
	/**
	 * 传入一个日期和天数，计算该日期加num天后的日期（这个方式避免了数组越界，采用这个方式）
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date plusDateNum(Date date, Integer num){
		int oneDay = 1000*60*60*24;
		BigDecimal day = new BigDecimal(oneDay).multiply(new BigDecimal(num));
		Date calculDate = new Date(date.getTime() + day.longValue());
		return calculDate;
	}
	
	/**
	 * 传入一个日期和天数，计算该日期减num天后的日期
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date minusDate(Date date, Integer num){
		Date calculDate = new Date(date.getTime() - num*1000*60*60*24);
		return calculDate;
	}
	
	/**
	 * 传入一个日期和天数，计算该日期减num天后的日期
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date minusDateNum(Date date, Integer num){
		int oneDay = 1000*60*60*24;
		BigDecimal day = new BigDecimal(oneDay).multiply(new BigDecimal(num));
		Date calculDate = new Date(date.getTime() - day.longValue());
		return calculDate;
	}
	
	/**
	 * 将String类型的日期 转换为 Date类型  yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date changStringDate(String date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date creatTime = sdf.parse(date);
		return creatTime;
	}
	/**
	 * 将String类型的日期 转换为 Date类型  yyyy/MM/dd HH:mm:ss
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date changStringDate2(String date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date creatTime = sdf.parse(date);
		return creatTime;
	}
	/**
	 * 将String类型的日期 转换为 Date类型  yyyy-MM-dd HH:mm
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date changStringDate02(String date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date creatTime = sdf.parse(date);
		return creatTime;
	}
	/**
	 * 将String类型的日期 转换为 Date类型  yyyy-MM-dd 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date changStringDate03(String date) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date creatTime = sdf.parse(date);
		return creatTime;
	}
	/**
	 * 获取当前时间  Date类型  yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	public static Date getNowTime() throws ParseException{
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		return changStringDate(time);
	}
	
	/**
	 * 获取当前年份 Date类型  yyyy
	 * @return
	 * @throws ParseException
	 */
	public static Date getNowYear() throws ParseException{
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy");
		String time=format.format(date);
		return changStringDate(time);
	}
	
	/**
	 * 将Date类型 转换为 yyyy-MM-dd 格式的Date
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date getDateFormat(Date date) throws ParseException {  
	    Date currentTime = date;  
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
	    String dateString = formatter.format(currentTime);  
	    return changStringDate03(dateString);  
	}  
	
	/**
	 * 获取当前时间  Date类型  yyyyMMddHHmmss
	 * @return
	 * @throws ParseException
	 */
	public static Date getNowTime2() throws ParseException{
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		return changStringDate(time);
	}
	
	/**
	 * 获取传入时间的当天开始时间
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getTodaystart(Date date)throws ParseException{
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	     
	    Date start = calendar.getTime();
		return start;
	}
	
	/**
	 * 获取传入时间的当天结束时间（修改之后）
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getTodayfinish(Date date)throws ParseException{
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	     
	    Date finish = calendar.getTime();
		return finish;
	}
	
	/**
	 * 获取传入时间的当天结束时间
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getTodayend(Date date)throws ParseException{
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DAY_OF_MONTH, 1);
	    calendar.add(Calendar.SECOND, -1);
	     
	    Date end = calendar.getTime();
		return end;
	}
	
	/**  
	 * 计算出两个日期之间相差的天数  
	 * 建议date1 大于 date2 这样计算的值为正数  
	 * @param date1 日期1  
	 * @param date2 日期2  
	 * @return date1 - date2  
	 */  
	public static int dateInterval(long date1, long date2) { 
	    if(date2 > date1){ 
	        date2 = date2 + date1; 
	        date1 = date2 - date1; 
	        date2 = date2 - date1; 
	    } 
	 
	     // Canlendar 该类是一个抽象类   
	     // 提供了丰富的日历字段  
	     // 本程序中使用到了  
	     // Calendar.YEAR    日期中的年份  
	     // Calendar.DAY_OF_YEAR     当前年中的天数  
	     // getActualMaximum(Calendar.DAY_OF_YEAR) 返回今年是 365 天还是366天  
	    Calendar calendar1 = Calendar.getInstance(); // 获得一个日历   
	    calendar1.setTimeInMillis(date1); // 用给定的 long 值设置此 Calendar 的当前时间值。   
	        
	    Calendar calendar2 = Calendar.getInstance(); 
	    calendar2.setTimeInMillis(date2); 
	    // 先判断是否同年   
	    int y1 = calendar1.get(Calendar.YEAR); 
	    int y2 = calendar2.get(Calendar.YEAR); 
	        
	    int d1 = calendar1.get(Calendar.DAY_OF_YEAR); 
	    int d2 = calendar2.get(Calendar.DAY_OF_YEAR); 
	    int maxDays = 0; 
	    int day = 0; 
	    if(y1 - y2 > 0){ 
	        day = numerical(maxDays, d1, d2, y1, y2, calendar2); 
	    }else{ 
	        day = d1 - d2; 
	    } 
	    return day; 
	} 
	
	/**  
	 * 日期间隔计算  
	 * 计算公式(示例):  
	 *      20121201- 20121212 
	 *      取出20121201这一年过了多少天 d1 = 天数  取出20121212这一年过了多少天 d2 = 天数 
	 *      如果2012年这一年有366天就要让间隔的天数+1，因为2月份有29日。  
	 * @param maxDays   用于记录一年中有365天还是366天  
	 * @param d1    表示在这年中过了多少天  
	 * @param d2    表示在这年中过了多少天  
	 * @param y1    当前为2012年  
	 * @param y2    当前为2012年  
	 * @param calendar  根据日历对象来获取一年中有多少天  
	 * @return  计算后日期间隔的天数  
	 */  
	public static int numerical(int maxDays, int d1, int d2, int y1, int y2, Calendar calendar){ 
	    int day = d1 - d2; 
	    int betweenYears = y1 - y2; 
	    List<Integer> d366 = new ArrayList<Integer>(); 
	        
	    if(calendar.getActualMaximum(Calendar.DAY_OF_YEAR) == 366){ 
	        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_YEAR)); 
	        day += 1; 
	    } 
	        
	    for (int i = 0; i < betweenYears; i++) { 
	        // 当年 + 1 设置下一年中有多少天   
	        calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR)) + 1); 
	        maxDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR); 
	        // 第一个 366 天不用 + 1 将所有366记录，先不进行加入然后再少加一个  
	        if(maxDays != 366){ 
	            day += maxDays; 
	        }else{ 
	            d366.add(maxDays); 
	        } 
	        // 如果最后一个 maxDays 等于366 day - 1   
	        if(i == betweenYears-1 && betweenYears > 1 && maxDays == 366){ 
	            day -= 1; 
	        } 
	    } 
	        
	    for(int i = 0; i < d366.size(); i++){ 
	        // 一个或一个以上的366天   
	        if(d366.size() >= 1){ 
	            day += d366.get(i); 
	        }  
	    }   
	    return day; 
	} 
	
	/** 
     * 计算两个日期之间相差的天数 
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
  * @throws ParseException 
     */  
    public static int daysBetween(Date smdate,Date bdate) throws ParseException  
    {  
     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
     smdate=sdf.parse(sdf.format(smdate));
     bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();  
        cal.setTime(smdate);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(bdate);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);
          
       return Integer.parseInt(String.valueOf(between_days));         
    }
    
    /**
     * 判断日期是否是休息
     * @param date
     * @return
     * @throws Exception
     */
    public static boolean judgeHoliday(Date date)throws Exception{
    	String datestring = formatDay(date);
    	String string = HttpUtil.requestForString(url+datestring, null, null, false, false);
    	JSONObject jsonObject = JSONObject.fromObject(string);
    	JSONObject result = (JSONObject)jsonObject.get("result");
    	String workholiday = (String)result.get("workholiday");
    	String week = (String)result.get("week");
    	if(null!=workholiday && workholiday.equals("0")){
    		return true;
    	}else if(null!=workholiday && workholiday.equals("1")){
    		return false;
    	}else if(week.equals("六")||week.equals("日")){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    /**
     * 获取传入日期的情况
     * @param date
     * @return
     * @throws Exception
     */
    public static JSONObject getTime(Date date)throws Exception{
    	String datestring = formatDay(date);
    	String string = HttpUtil.requestForString(url+datestring, null, null, false, false);
    	JSONObject jsonObject = JSONObject.fromObject(string);
    	JSONObject result = (JSONObject)jsonObject.get("result");
    	return result;
    }
    /**
     * 比较两个日期的大小
     * @param date1
     * @param date2
     * @return
     * @throws Exception
     */
    public  static Integer compare(Date date1,Date date2)throws Exception{
    	if(date1.getTime()>date2.getTime()){
    		return 1;
    	}else if(date1.getTime()<date2.getTime()){
    		return -1;
    	}
    	return 0;
    }
    
    /**
     * 获取当前年份总共有多少天
     * @return
     * @throws ParseException
     */
    public static int getNowYearDays() throws Exception{
    	int nowYearDays = 0;
    	Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy");
		String time=format.format(date);
		int nowYear = Integer.valueOf(time);
		if (nowYear % 4 == 0) {//年份可以整除4
			nowYearDays = 366;
		}else{//年份不能整除4
			nowYearDays = 365;
		}
		return nowYearDays;
    }
    
    /**
     * 获取当前月份总共有多少天
     * @param year
     * @param month
     * @return
     */
    public static int getDayNumByYearandMonth(String year, String month) throws Exception{
		int num = 0;
		if ("1".equals(month) || "3".equals(month) || "5".equals(month) || "7".equals(month)
				|| "8".equals(month) || "10".equals(month) || "12".equals(month) 
				|| "01".equals(month) || "03".equals(month) || "05".equals(month)
				|| "07".equals(month) || "08".equals(month)) {
			num = 31;
		}else if("4".equals(month) || "6".equals(month) || "9".equals(month) || "11".equals(month)
				|| "04".equals(month) || "06".equals(month) || "09".equals(month)){
			num = 30;
		}else if(("2".equals(month) || "02".equals(month)) && Integer.valueOf(year)%4 == 0) {//闰年2月
			num = 29;
		}else if(("2".equals(month) || "02".equals(month)) && Integer.valueOf(year)%4 != 0) {//平年2月
			num = 28;
		}else{
			
		}
		return num;
	}
    
    /**
     * 返回指定年季的季的最后一天  23时
     * @param year
     * @param quarter
     * @return
     */
     public static Date getLastDayOfQuarter(Integer year, Integer quarter) {
         Calendar calendar = Calendar.getInstance();
         Integer month = new Integer(0);
         if (quarter == 1) {
             month = 3 - 1;
         } else if (quarter == 2) {
             month = 6 - 1;
         } else if (quarter == 3) {
             month = 9 - 1;
         } else if (quarter == 4) {
             month = 12 - 1;
         } else {
             month = calendar.get(Calendar.MONTH);
         }
         
         Calendar calendar2 = Calendar.getInstance();
         calendar2.set(Calendar.DAY_OF_MONTH, 1);
         calendar2.set(Calendar.HOUR_OF_DAY, 23);
         calendar2.set(Calendar.MINUTE, 0);
         calendar2.set(Calendar.SECOND, 0);
         calendar2.set(Calendar.MILLISECOND, 0);
         calendar2.set(year, month, 1);
         if (year == null) {
             year = calendar.get(Calendar.YEAR);
         }
         if (month == null) {
             month = calendar.get(Calendar.MONTH);
         }
         calendar2.set(year, month, 1);
         calendar2.roll(Calendar.DATE, -1);
     	return calendar2.getTime();
     }
     /**
      * 获取当前年份
      * @return
      */
     public static  Integer getYear(){
    	 Calendar calendar = Calendar.getInstance();
    	 calendar.setTimeInMillis(System.currentTimeMillis());
    	 int currenYear = calendar.get(Calendar.YEAR);
    	 return currenYear;
     }
     
     /**
      * 获取当前季度
      * @return
      */
     public static Integer getQuarter(){
    	 Calendar calendar = Calendar.getInstance();
    	 calendar.setTimeInMillis(System.currentTimeMillis());
    	 int currenMonth = calendar.get(Calendar.MONTH);
    	 int currentQuarter = 1;
    	 if(4==currenMonth||5==currenMonth||6==currenMonth){
    		 currentQuarter = 2;
    	 }else if(7==currenMonth||8==currenMonth||9==currenMonth){
    		 currentQuarter = 3;
    	 }else if(10==currenMonth||11==currenMonth||12==currenMonth){
    		 currentQuarter = 4;
    	 }
    	 return currentQuarter;
     }
     /**
      * 获取当前月份
      * @return
      */
     public static Integer getMonth(){
    	 Calendar calendar = Calendar.getInstance();
    	 calendar.setTimeInMillis(System.currentTimeMillis());
    	 int currenMonth = calendar.get(Calendar.MONTH);
    	 return currenMonth;
     }
 	/**
 	 *  返回指定年月的月的最后一天  23时
 	 * @param year
 	 * @param month
 	 * @return
 	 */
 	 public static Date getLastDayOfMonth(Integer year, Integer month) {
 		 Calendar calendar = Calendar.getInstance();
 	     calendar.set(Calendar.DAY_OF_MONTH, 1);
 			calendar.set(Calendar.HOUR_OF_DAY, 23);
 			calendar.set(Calendar.MINUTE, 0);
 			calendar.set(Calendar.SECOND, 0);
 			calendar.set(Calendar.MILLISECOND, 0);
 	        if (year == null) {
 	            year = calendar.get(Calendar.YEAR);
 	        }
 	        if (month == null) {
 	            month = calendar.get(Calendar.MONTH);
 	        }
 	        calendar.set(year, month-1, 1);
 	        calendar.roll(Calendar.DATE, -1);
 		 return calendar.getTime();
 	 }
 	 
 	 /**
 	  * 获取当前时间的前n个小时
 	  * @return
 	  */
 	 public static Date getHourBefore(Integer hour){
 		Calendar calendar = Calendar.getInstance();
 		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
 		 return calendar.getTime();
 	 }
 	 
 	 /**
 	  * 判断当前时间是否为早8点
 	  * @return
 	  */
 	 public static Boolean ifEight(){
 		Calendar calendar = Calendar.getInstance();
 		int hour = calendar.get(Calendar.HOUR_OF_DAY);
 		if(8==hour){
 			return true;
 		}
 		return false;
 	 }
 	 
	 /**
 	  * 计算两个时间差 ，不足半小时按半小时
 	  */
 	 public static Long halfHourDiff(Date startTime,Date endTime){
 		 
 		 //毫秒ms
         Long diff =endTime.getTime() - startTime.getTime();
         Long halfHourCount = diff / (60 * 1000*30) ;
 		 
         //秒
         Long diffSeconds = diff / 1000 % 60;
         //分
         Long diffMinutes = diff / (1000*30) % 60;
         //不足一分钟按一分钟算
         if(0!=diffSeconds||0!=diffMinutes){
        	 halfHourCount++;
         }
 		 return halfHourCount;
 	 }
 	 
	 /**
 	  * 计算两个时间差 ，不足1小时按1小时
 	  */
 	 public static Long hourDiff(Date startTime,Date endTime){
 		 
 		 //毫秒ms
         Long diff =endTime.getTime() - startTime.getTime();
         Long hourCount = diff / (60 * 1000*60) ;
 		 
         //秒
         Long diffSeconds = diff / 1000 % 60;
         //分
         Long diffMinutes = diff / (1000*60) % 60;
         //不足一分钟按一分钟算
         if(0!=diffSeconds||0!=diffMinutes){
        	 hourCount++;
         }
 		 return hourCount;
 	 }
 	 
 	 
 	/**
 	  * 计算两个时间差 ，小时
 	  */
 	 public static Long hourDiff1(Date startTime,Date endTime){
 		 
 		 //毫秒ms
         Long diff =endTime.getTime() - startTime.getTime();
         Long hourCount = diff / (60 * 1000*60) ;
 		 
        
 		 return hourCount;
 	 }
 	 
 	 /**
 	  * 计算两个时间差 
 	  */
 	 public static Long minuteDiff(Date startTime,Date endTime){
 		 
 		 //毫秒ms
         Long diff =endTime.getTime() - startTime.getTime();
         Long minutes = diff / (60 * 1000) ;
 		 
         //秒
         Long diffSeconds = diff / 1000 % 60;
         //不足一分钟按一分钟算
         if(0!=diffSeconds){
        	 minutes++;
         }
 		 return minutes;
 	 }
 	 
 	/**
 	  * 计算两个时间差 
 	  */
 	 public static Long minuteDiff1(Date startTime,Date endTime){
 		 
 		 //毫秒ms
         Long diff =endTime.getTime() - startTime.getTime();
         Long minutes = diff / (60 * 1000) ;
 		 
         
 		 return minutes;
 	 }
 	 
 	 /**
 	  * 计算两个时间差 
 	  */
 	 public static Long secondDiff(Date startTime,Date endTime){
 		 
 		 //毫秒ms
         Long diff =endTime.getTime() - startTime.getTime();
         Long seconds = diff /1000;
 		 return seconds;
 	 }

	/**
 	  *当前时间 减去 n分钟 后的时间
 	  * @param second
 	  * @return
 	  */
 	 public static Date dateMinusMinute(Integer minute){
	    Calendar calendar = Calendar.getInstance();
        calendar.add (Calendar.MINUTE, -minute);
        return calendar.getTime();
 	 }
 	/**
  	 * 将String 转换为 date yyyyMMddHHmmss
  	 * @param date
  	 * @return
  	 * @throws ParseException
  	 */
  	public static Date stringToDate03(String date) throws ParseException{
  		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
  		return sdf.parse(date);
  	}
	/**
	 * 将传入的日期减少n年
	 */
	public static Date dateMinusYear(Date date,int year){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR,-year);
		return calendar.getTime();
	}
}
