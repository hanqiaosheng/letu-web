package org.controller.report;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.Channel;
import org.entity.dto.User;
import org.service.report.ChannelServiceReport;
import org.service.report.UserServiceReport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.PageUtil;

import com.ibm.icu.text.NumberFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping("report/user")
public class UserController {

	@Resource
	AppConfig appConfig;

	@Resource
	UserServiceReport userServiceReport;

	@Resource
	ChannelServiceReport channelServiceReport;
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(UserController.class);

	/**
	 * 用户列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String userList(HttpSession session,Model model, @RequestParam(defaultValue = "1") Integer pageIndex,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag, String tel, String name, String idcard,Date fromtime, Date totime )
			throws Exception {
		try {
			if(1 == flag){
				pageIndex = null;
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			
			List<User> userList = userServiceReport.findUserList(tel, name, idcard, fromtime, totime, pageIndex);
			Integer totalPage = 1;
			Integer totalCount = 0;
			totalCount = userServiceReport.countUserList(tel, name, idcard, fromtime, totime);
			if(totalCount>0){
					 totalPage = PageUtil.getTotalPage(totalCount);
			}
			Date date = new Date();
			String date1 = format.format(date); 
			Date today = format.parse(date1);
			Integer todayCount =userServiceReport.countUserList(null, null, null, today, today);

			// 创建一个数值格式化对象

			NumberFormat numberFormat = NumberFormat.getInstance();

			// 设置精确到小数点后2位

			numberFormat.setMaximumFractionDigits(2);
			String result = "0";
			if(totalCount>0){
				 result = numberFormat.format((float) todayCount / (float) totalCount * 100);
			}

			

			
			model.addAttribute("tel", tel);
			model.addAttribute("name", name);
			model.addAttribute("idcard", idcard);
			model.addAttribute("fromtime", fromtime);
			model.addAttribute("totime", totime);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("userList", userList);
			model.addAttribute("todayCount", todayCount);
			model.addAttribute("result", result);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=user_report_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "user_report_excel_list";
		}
		return "user_report_list";
	}
	
	@RequestMapping("newUserList")
	public String newUserList(HttpSession session,Model model, @RequestParam(defaultValue = "1") Integer pageIndex,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag, String tel, String bikeCode,Date fromtime, Date totime,Long [] channelId,String str )
			throws Exception {
		try {
			Long currChannelId = (Long) session.getAttribute("currChannelId");
			if(1 == flag){
				pageIndex = null;
			}
			
			List<Long> channelIdList = new ArrayList<Long>();
			List<Channel> channelList = new ArrayList<Channel>();
			if(channelId!=null){
				for(Long c:channelId){
					channelIdList.add(c);
				}
				 
			}
			if(str!=null&&!str.equals("")){
				String[] channelIdStr = str.split(",");
				if(channelIdStr.length>0){
					for(int i=0;i<channelIdStr.length;i++){
						channelIdList.add(Long.valueOf(channelIdStr[i]));
					}
					
				}
			}
			List<User> userList = new ArrayList<User>();
			Integer totalPage = 1;
			Integer totalCount = 0;
			Integer todayCount = 0;
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			String date1 = format.format(date); 
			Date today = format.parse(date1);
		     if(currChannelId==null){
		    	 userList = userServiceReport.findNewUser(null,tel, bikeCode, fromtime, totime, channelIdList, pageIndex);
		    	 totalCount = userServiceReport.countNewUser(null,tel, bikeCode, fromtime, totime, channelIdList);
		    	 channelList = channelServiceReport.findAllChannelByChannelIds(null);
		    	 todayCount =userServiceReport.countNewUser(null,tel, bikeCode, today, today, channelIdList);
		     }
		     else{
		    	 Channel channel =channelServiceReport.findById(currChannelId);
					List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
					channels.add(channel);
					List<Long> currChannelIds = new ArrayList<Long>();
					for(Channel c:channels){
						currChannelIds.add(c.getChannelId());
					}
		    	 userList = userServiceReport.findNewUser(currChannelIds,tel, bikeCode, fromtime, totime, channelIdList, pageIndex);
		    	 totalCount = userServiceReport.countNewUser(currChannelIds,tel, bikeCode, fromtime, totime, channelIdList);
		    	 channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
		    	 todayCount =userServiceReport.countNewUser(currChannelIds,tel, bikeCode, today, today, channelIdList);
		     }
		     if(totalCount>0){
				 totalPage = PageUtil.getTotalPage(totalCount);
		     }
		       
				
				model.addAttribute("tel", tel);
				model.addAttribute("fromtime", fromtime);
				model.addAttribute("totime", totime);
				model.addAttribute("pageIndex", pageIndex);
				model.addAttribute("totalPage", totalPage);
				model.addAttribute("totalCount", totalCount);
				model.addAttribute("userList", userList);
				model.addAttribute("todayCount", todayCount);
				model.addAttribute("channelList", channelList);
				model.addAttribute("bikeCode", bikeCode);
				model.addAttribute("channelId", channelId);
				model.addAttribute("channelIdList", channelIdList);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=newuser_report_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "newuser_excel_list";
		}
		return "newuser_list";
	}
	
	
	/**
	 * 新注册用户
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("userreport")
	public @ResponseBody Map<String, Object> userreport(String fromtime,String totime,Integer time,String str)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONArray jsonArray = new JSONArray();
		List<Long> channelIdList = new ArrayList<Long>();
		try {
			if(str!=null&&!str.equals("")){
				String[] channelIdStr = str.split(",");
				if(channelIdStr.length>0){
					for(int i=0;i<channelIdStr.length;i++){
						channelIdList.add(Long.valueOf(channelIdStr[i]));
					}
					
				}
			}
			
			
			Integer totalCount = 0;
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			String date1 = format.format(date); 
			Date today = format.parse(date1);
			Integer todayCount =0;
			totalCount = userServiceReport.countUserList(null, null, null, null, null);
			todayCount = userServiceReport.countUserList(null, null, null, today, today);
			map.put("todayCount", todayCount);
	        map.put("totalCount", totalCount);
	        Date fromTime = null;
			Date toTime = null;
			if(fromtime!=null && totime!=null){
				
				fromTime = format.parse(fromtime);
			    toTime = format.parse(totime);
			    int x = 0;
			    if(fromTime.getTime()<toTime.getTime()){
			    	x=DateUtil.daysBetween(fromTime,toTime);
			    }else{
			    	x=DateUtil.daysBetween(fromTime,toTime);
			    }
			    
			    	 Calendar c = Calendar.getInstance();
						Date time1 = null;
						
						c.setTime(format.parse(format.format(fromTime)));
				        
				        Integer increase = null;
						for(int i=0;i<=x;i++){
							
					        
					        	
							    time1 = c.getTime();
							    increase = userServiceReport.countUserList(null, null, null, time1, time1);
					        	
					        	
					        	c.add(Calendar.DATE, +1);
					        	
					        	
					        	JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period",format.format(time1));
								jsonObject.put("increase", increase);
			    				jsonArray.add(jsonObject);//添加json
					            
					        
				        	
				        }
						map.put("jsonArray", jsonArray);
						map.put("fromTime", fromTime);
				        map.put("toTime", toTime);
						return map;
						
			    
			    
			   
				
				
			}
			else if(time!=null){
				Calendar c = Calendar.getInstance();
				Date time1 = null;
				Date time2 = null;
				if(time==1){
		        	//过去七天
			        c.setTime(DateUtil.getCurrentDay());
			        c.add(Calendar.DATE, - 7);
			        time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        Integer increase = null;
			        for(int i=0;i<8;i++){
			        	
			        	d.setTime(c.getTime());
			        	time2 = d.getTime();
			        	increase = userServiceReport.countUserList(null,null, null, time2, time2);
			        	c.add(Calendar.DATE, +1);
			        	
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format.format(time2));
						jsonObject.put("increase", increase);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
			        map.put("fromTime", fromTime);
			        map.put("toTime", toTime);
					return map;
			        
		        }
		        if(time==2){
		        	//过去一月
		        	
		        	
		        	SimpleDateFormat format2 = new SimpleDateFormat("MM-dd"); 
		        	c.add(Calendar.DATE, -30);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        
			        Integer increase = null;
			        for(int i=0;i<31;i++){
			        	
			        	d.setTime(c.getTime());
			        	
			        	
			        	time2 = d.getTime();
			        	
			        	
			        	increase = userServiceReport.countUserList(null,null, null, time2, time2);
			        	c.add(Calendar.DATE, +1);
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format2.format(time2));
						jsonObject.put("increase", increase);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
			        map.put("fromTime", fromTime);
			        map.put("toTime", toTime);
					return map;
			        
		            
			        
		        }
		        if(time==3){
		        	//过去三个月
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		        	c.setTime(format1.parse(format1.format(new Date())));
		        	c.add(Calendar.MONTH, -3);
		            time1 = c.getTime();
		            Date time3 = null;
			        Calendar d = Calendar.getInstance();
			        Calendar e = Calendar.getInstance();
			        Integer increase = null;
			        for(int i=0;i<4;i++){
			        	
			        	
			        	
						d.setTime(c.getTime());
				    	d.add(Calendar.MONTH, +1);
				    	e.setTime(d.getTime());
				    	time2 = c.getTime();
				    	time3 = e.getTime();
			        	increase = userServiceReport.countUserList(null,null, null, time2, time3);
			        	c.add(Calendar.MONTH, +1);
			        	
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
			        map.put("fromTime", fromTime);
			        map.put("toTime", toTime);
					return map;
		            
			        
		        }
		        if(time==4){
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		        	Date time3 = null;
		            c.setTime(format1.parse(format1.format(new Date())));
		            c.add(Calendar.MONTH, -12);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        Calendar e = Calendar.getInstance();
			        Integer increase = null;
			        
					
			        for(int i=0;i<13;i++){
			        	
			        	d.setTime(c.getTime());
			        	d.add(Calendar.MONTH, +1);
			        	e.setTime(d.getTime());
			        	time2 = c.getTime();
			        	time3 = e.getTime();
			        	c.add(Calendar.MONTH, +1);
			        	increase = userServiceReport.countUserList(null,null, null, time2, time3);
			        	
			        	
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        
			        map.put("jsonArray", jsonArray);
			        map.put("fromTime", fromTime);
			        map.put("toTime", toTime);
					return map;
			        
			        
		        }
		        totalCount = userServiceReport.countUserList(null, null, null, time1, today);
				todayCount = userServiceReport.countUserList(null, null, null, today, today);
			}
			else{
				totalCount = userServiceReport.countUserList(null, null, null, null, null);
				todayCount = userServiceReport.countUserList(null, null, null, today, today);
			}
			map.put("totalCount", totalCount);
			map.put("todayCount", todayCount);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return null;
	}
	
	/**
	 * 新增租车用户
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("newuserreport")
	public @ResponseBody Map<String, Object> newuserreport(String fromtime,String totime,Integer time,String str)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();  
		JSONArray jsonArray = new JSONArray();
		List<Long> channelIdList = new ArrayList<Long>();
		try {
			if(str!=null&&!str.equals("")){
				String[] channelIdStr = str.split(",");
				if(channelIdStr.length>0){
					for(int i=0;i<channelIdStr.length;i++){
						channelIdList.add(Long.valueOf(channelIdStr[i]));
					}
					
				}
			}
			
			
			Integer totalCount = 0;
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			String date1 = format.format(date); 
			Date today = format.parse(date1);
			Integer todayCount =0;
			List<Integer> arr = new ArrayList<Integer>();
			totalCount = userServiceReport.countNewUser(null,null, null, null, null, channelIdList);
			todayCount =userServiceReport.countNewUser(null,null, null, today, today, channelIdList);
			map.put("totalCount", totalCount);
			map.put("todayCount", todayCount);
			if(fromtime!=null && totime!=null){
				
				Date fromTime = null;
				Date toTime = null;
				fromTime = format.parse(fromtime);
			    toTime = format.parse(totime);
			    int x = 0;
			    if(fromTime.getTime()<toTime.getTime()){
			    	x=DateUtil.daysBetween(fromTime,toTime);
			    }else{
			    	x=DateUtil.daysBetween(fromTime,toTime);
			    }
			    
			    	 Calendar c = Calendar.getInstance();
						Date time1 = null;
						
						c.setTime(format.parse(format.format(fromTime)));
				        
				        Integer increase = null;
						for(int i=0;i<=x;i++){
							
					        
					        	
							    time1 = c.getTime();
							    increase =  userServiceReport.countNewUser(null,null, null, time1, time1, channelIdList);
					        	
					        	
					        	c.add(Calendar.DATE, +1);
					        	arr.add(increase);
					        	
					        	JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period",format.format(time1));
								jsonObject.put("increase", increase);
			    				jsonArray.add(jsonObject);//添加json
					            
					        
				        	
				        }
						map.put("jsonArray", jsonArray);
						return map;
						
			    
				
			}
			
			else if(time!=null){
				Calendar c = Calendar.getInstance();
				Date time1 = null;
				Date time2 = null;
				
		        if(time==1){
		        	//过去七天
			        c.setTime(DateUtil.getCurrentDay());
			        c.add(Calendar.DATE, - 7);
			        time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        Integer increase = null;
			        for(int i=0;i<8;i++){
			        	
			        	d.setTime(c.getTime());
			        	time2 = d.getTime();
			        	increase = userServiceReport.countNewUser(null,null, null, time2, time2, channelIdList);
			        	c.add(Calendar.DATE, +1);
			        	arr.add(increase);
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format.format(time2));
						jsonObject.put("increase", increase);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
					return map;
			        
		        }
		        if(time==2){
		        	//过去一月
		        	
		        	
		        	SimpleDateFormat format2 = new SimpleDateFormat("MM-dd"); 
		        	c.setTime(format.parse(format.format(new Date())));
		        	c.add(Calendar.DATE, -30);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        
			        Integer increase = null;
			        for(int i=0;i<31;i++){
			        	
			        	d.setTime(c.getTime());
			        	
			        	
			        	time2 = d.getTime();
			        	
			        	
			        	increase = userServiceReport.countNewUser(null,null, null, time2, time2, channelIdList);
			        	c.add(Calendar.DATE, +1);
			        	arr.add(increase);
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format2.format(time2));
						jsonObject.put("increase", increase);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
					return map;
			        
		            
			        
		        }
		        if(time==3){
		        	//过去三个月
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		        	c.setTime(format1.parse(format1.format(new Date())));
		        	c.add(Calendar.MONTH, -3);
		            time1 = c.getTime();
		            Date time3 = null;
			        Calendar d = Calendar.getInstance();
			        Calendar e = Calendar.getInstance();
			        Integer increase = null;
			        for(int i=0;i<4;i++){
			        	
			        	
			        	
						d.setTime(c.getTime());
				    	d.add(Calendar.MONTH, +1);
				    	e.setTime(d.getTime());
				    	time2 = c.getTime();
				    	time3 = e.getTime();
			        	increase = userServiceReport.countNewUser(null,null, null, time2, time3, channelIdList);
			        	c.add(Calendar.MONTH, +1);
			        	arr.add(increase);
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
					return map;
		            
			        
		        }
		        if(time==4){
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		        	Date time3 = null;
		            c.setTime(format1.parse(format1.format(new Date())));
		            c.add(Calendar.MONTH, -12);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        Calendar e = Calendar.getInstance();
			        Integer increase = null;
			        for(int i=0;i<13;i++){
			        	
			        	d.setTime(c.getTime());
			        	d.add(Calendar.MONTH, +1);
			        	e.setTime(d.getTime());
			        	time2 = c.getTime();
			        	time3 = e.getTime();
			        	c.add(Calendar.MONTH, +1);
			        	increase = userServiceReport.countNewUser(null,null, null, time2, time3, channelIdList);
			        	arr.add(increase);
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
					return map;
			        
		            
			        
		        }
		        totalCount = userServiceReport.countNewUser(null,null, null, time1, today, channelIdList);
		        
			}
			else{
				totalCount = userServiceReport.countNewUser(null,null, null, null, null, channelIdList);
				
			}
			todayCount =userServiceReport.countNewUser(null,null, null, today, today, channelIdList);
			map.put("totalCount", totalCount);
			map.put("todayCount", todayCount);
			map.put("arr", arr);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return null;
				
	}
}
