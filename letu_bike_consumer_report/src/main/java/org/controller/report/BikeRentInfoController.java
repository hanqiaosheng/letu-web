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

import org.entity.dto.BikeRentInfo;
import org.entity.dto.Channel;
import org.entity.dto.FixedReturn;
import org.service.report.BikeRentInfoServiceReport;
import org.service.report.ChannelServiceReport;
import org.service.report.FixedReturnServiceReport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.PageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping("report/bikeRentInfo")
public class BikeRentInfoController {
	
	
	@Resource
	AppConfig appConfig;
	
	@Resource
	BikeRentInfoServiceReport bikeRentInfoServiceReport;
	
	@Resource
	ChannelServiceReport channelServiceReport;
	
	@Resource
	FixedReturnServiceReport fixedReturnServiceReport;
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(BikeRentInfoController.class);
	
	@RequestMapping("bikeRentInfoList")
	public String bikeRentInfoList(Model model,@RequestParam(defaultValue="1")Integer pageIndex,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,Long bikeId,
			                       Date fromtime,Date totime,
			                       HttpSession session,Integer x,Long [] channelId,String str
			                       ) throws Exception{
		
		try {
			Long currChannelId = (Long) session.getAttribute("currChannelId");
			loggers.info("获取登录账户渠道"+currChannelId);
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
			//loggers.info("渠道字符串"+str);
			if(str!=null&&!str.equals("")){
				String[] channelIdStr = str.split(",");
				if(channelIdStr.length>0){
					for(int i=0;i<channelIdStr.length;i++){
						channelIdList.add(Long.valueOf(channelIdStr[i]));
					}
					
				}
			}
			
			List<BikeRentInfo> bikeRentInfoList = new ArrayList<BikeRentInfo>();
			Integer totalPage = 1;
			Integer totalCount = 0;
			Integer starttime= null;
			Integer endtime= null;
			if(x!=null){
				switch (x) {
				case 0:
					starttime=5;
					endtime=10;
					break;
				case 1:
					starttime=5;
					endtime=30;
					break;	
				case 2:
					starttime=10;
					endtime=20;
					break;
				case 3:
					starttime=20;
					endtime=30;
					break;
				case 4:
					starttime=30;
					endtime=40;
					break;	
				case 5:
					starttime=30;
					endtime=60;
					break;
				case 6:
					starttime=40;
					endtime=50;
					break;	
				case 7:
					starttime=50;
					endtime=60;
					break;	
				case 8:
					starttime=60;
					endtime=90;
					break;
				case 9:
					starttime=90;
					endtime=120;
					break;
				case 10:
					starttime=60;
					break;
				case 11:
					starttime=120;
					break;	
				default:
					starttime=5;
					endtime=10;
					break;
				}
			}
			
			if(currChannelId==null){
				bikeRentInfoList = bikeRentInfoServiceReport.findBikeRentInfo(null,channelIdList, bikeId, fromtime, totime, pageIndex,starttime,endtime);
				totalCount = bikeRentInfoServiceReport.CountBikeRentInfo(null,channelIdList, bikeId, fromtime, totime, starttime,endtime);
				channelList = channelServiceReport.findAllChannelByChannelIds(null);
			}
			else{
				Channel channel =channelServiceReport.findById(currChannelId);
				List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
				channels.add(channel);
				List<Long> currChannelIds = new ArrayList<Long>();
				for(Channel c:channels){
					currChannelIds.add(c.getChannelId());
				}
				bikeRentInfoList = bikeRentInfoServiceReport.findBikeRentInfo(currChannelIds,channelIdList, bikeId, fromtime, totime, pageIndex,starttime,endtime);
				totalCount = bikeRentInfoServiceReport.CountBikeRentInfo(currChannelIds,channelIdList, bikeId, fromtime, totime,starttime,endtime);
				channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
			}
			for(BikeRentInfo bri : bikeRentInfoList){
				if(null!=bri.getRentStartFixedId()&&!bri.getRentStartFixedId().equals("")){
					FixedReturn fixedReturn = fixedReturnServiceReport.findFixedById(bri.getRentStartFixedId());
					if(null!=fixedReturn){
						bri.setStartFixedName(fixedReturn.getFixedReturnName());
					}
				}
				if(null!=bri.getRentEndFixedId()&&!bri.getRentEndFixedId().equals("")){
					FixedReturn fixedReturn = fixedReturnServiceReport.findFixedById(bri.getRentEndFixedId());
					if(null!=fixedReturn){
						bri.setEndFixedName(fixedReturn.getFixedReturnName());
					}
				}
			}
			if(totalCount>0){
					 totalPage = PageUtil.getTotalPage(totalCount);
			}
				 
			
			model.addAttribute("bikeRentInfoList", bikeRentInfoList);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("bikeId", bikeId);
			model.addAttribute("fromtime", fromtime);
			model.addAttribute("totime", totime);
			model.addAttribute("x", x);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("channelList", channelList);
			model.addAttribute("channelIdList", channelIdList);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		//model.addAttribute("channels", channels);
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=bike_report_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "bike_report_excel_list";
		}
		return "bike_report_list";
	}
	
	/**
	 * 车辆骑行
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bikereport")
	public @ResponseBody Map<String, Object> bikereport(String fromtime,String totime,Integer time,String str)throws Exception {
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
			totalCount = bikeRentInfoServiceReport.CountBikeRentInfo(null, channelIdList, null, null, null, null, null);
			todayCount =bikeRentInfoServiceReport.CountBikeRentInfo(null, channelIdList, null, today, today, null, null);
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
							    increase =  bikeRentInfoServiceReport.CountBikeRentInfo(null, channelIdList, null, time1, time1, null, null);
					        	
					        	
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
			        	increase = bikeRentInfoServiceReport.CountBikeRentInfo(null,channelIdList, null, time2, time2, null, null);
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
		        	c.add(Calendar.DATE, -30);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        
			        Integer increase = null;
			        for(int i=0;i<31;i++){
			        	
			        	d.setTime(c.getTime());
			        	
			        	
			        	time2 = d.getTime();
			        	
			        	
			        	increase = bikeRentInfoServiceReport.CountBikeRentInfo(null,channelIdList, null, time2, time2, null, null);
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
			        	increase = bikeRentInfoServiceReport.CountBikeRentInfo(null,channelIdList, null, time2, time3, null,null);
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
			        	increase = bikeRentInfoServiceReport.CountBikeRentInfo(null,channelIdList, null, time2, time3, null,null);
			        	arr.add(increase);
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
					return map;
			        
		            
			        
		        }
		        totalCount = bikeRentInfoServiceReport.CountBikeRentInfo(null, channelIdList, null, time1, today, null, null);
			}
			else{
				totalCount = bikeRentInfoServiceReport.CountBikeRentInfo(null, channelIdList, null, null, null, null, null);
				
			}
			todayCount =bikeRentInfoServiceReport.CountBikeRentInfo(null, channelIdList, null, today, today, null, null);
			map.put("totalCount", totalCount);
			map.put("todayCount", todayCount);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return null;
	}
	
	
	/**
	 * 不同还车点租赁订单数量
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rentNumByFixed")
	public @ResponseBody Map<String, Object> rentNumByFixed(HttpSession session, String fromtime,String totime,Integer time,String str)throws Exception {
		Long currChannelId = (Long) session.getAttribute("currChannelId");
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
			List<FixedReturn> fixedReturnList = new ArrayList<FixedReturn>();
			if(channelIdList.size()>0){
				totalCount = bikeRentInfoServiceReport.countByChannelIds(channelIdList);
				fixedReturnList = fixedReturnServiceReport.findByChannels(channelIdList);
			}else{
				if(currChannelId==null){
					totalCount = bikeRentInfoServiceReport.countByChannelIds(null);
					fixedReturnList = fixedReturnServiceReport.findByChannels(null);
				}
				else{
					Channel channel =channelServiceReport.findById(currChannelId);
					List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
					channels.add(channel);
					List<Long> currChannelIds = new ArrayList<Long>();
					for(Channel c:channels){
						currChannelIds.add(c.getChannelId());
					}
					totalCount = bikeRentInfoServiceReport.countByChannelIds(currChannelIds);
					fixedReturnList = fixedReturnServiceReport.findByChannels(currChannelIds);
				}
				
			}
			Integer todayCount = 0;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	
			if(fromtime!=null && fromtime!=null){
				Date fromTime = format.parse(fromtime);
			    Date toTime = format.parse(totime);
			    for(FixedReturn fr : fixedReturnList){
					Integer rentNum = bikeRentInfoServiceReport.countRentInfoByFixed(fr.getFixedReturnId(),fromTime,toTime);
					todayCount += rentNum;
					JSONObject jsonObject = new JSONObject();
		        	jsonObject.put("period",fr.getFixedReturnName());
					jsonObject.put("increase", rentNum);
					jsonArray.add(jsonObject);//添加json
				}
			    map.put("totalCount", totalCount);
			    map.put("todayCount", todayCount);
				map.put("jsonArray", jsonArray);
				return map;
						
			}else if(time!=null){
				Calendar c = Calendar.getInstance();
				Date time1 = null;
				Date time2 = null;
				if(time==1){
		        	//过去七天
			        c.setTime(DateUtil.getCurrentDay());
			        time1 = c.getTime();
			        c.add(Calendar.DATE, - 7);
			        time2 = c.getTime();
			        for(FixedReturn fr : fixedReturnList){
						Integer rentNum = bikeRentInfoServiceReport.countRentInfoByFixed(fr.getFixedReturnId(),time2,time1);
						todayCount += rentNum;
						JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",fr.getFixedReturnName());
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
					}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
			        
		        }
		        if(time==2){
		        	//过去一月
		        	
		        	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM—dd"); 
		        	c.setTime(format2.parse(format2.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -1);
		        	time2 = c.getTime();
		        	for(FixedReturn fr : fixedReturnList){
						Integer rentNum = bikeRentInfoServiceReport.countRentInfoByFixed(fr.getFixedReturnId(),time2,time1);
						todayCount += rentNum;
						JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",fr.getFixedReturnName());
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
					}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
			        
		            
			        
		        }
		        if(time==3){
		        	//过去三个月
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM—dd");
		        	c.setTime(format1.parse(format1.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -3);
			    	time2 = c.getTime();
			    	//time3 = e.getTime();
			        for(FixedReturn fr : fixedReturnList){
						Integer rentNum = bikeRentInfoServiceReport.countRentInfoByFixed(fr.getFixedReturnId(),time2,time1);
						todayCount += rentNum;
						JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",fr.getFixedReturnName());
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
					}
				    map.put("todayCount", todayCount);
				    map.put("totalCount", totalCount);
			        map.put("jsonArray", jsonArray);
					return map;
		            
			        
		        }
		        if(time==4){
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		            c.setTime(format1.parse(format1.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -12);
		        	time2 = c.getTime();
			        for(FixedReturn fr : fixedReturnList){
						Integer rentNum = bikeRentInfoServiceReport.countRentInfoByFixed(fr.getFixedReturnId(),time2,time1);
						todayCount += rentNum;
						JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",fr.getFixedReturnName());
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
					}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
		        }else{
		        	for(FixedReturn fr : fixedReturnList){
						Integer rentNum = bikeRentInfoServiceReport.countRentInfoByFixed(fr.getFixedReturnId(),null,null);
						todayCount += rentNum;
						JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",fr.getFixedReturnName());
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
					}
		        	map.put("jsonArray", jsonArray);
					map.put("todayCount", todayCount);
					map.put("totalCount", totalCount);
					return map;
		        }
			}else{
				for(FixedReturn fr : fixedReturnList){
					Integer rentNum = bikeRentInfoServiceReport.countRentInfoByFixed(fr.getFixedReturnId(),null,null);
					todayCount += rentNum;
					JSONObject jsonObject = new JSONObject();
		        	jsonObject.put("period",fr.getFixedReturnName());
					jsonObject.put("increase", rentNum);
					jsonArray.add(jsonObject);//添加json
				}
				map.put("jsonArray", jsonArray);
				map.put("todayCount", todayCount);
				map.put("totalCount", totalCount);
				return map;
			}
			

		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return null;
	}
	
	@RequestMapping("rentInfoList")
	public String rentInfoList(Model model,@RequestParam(defaultValue="1")Integer pageIndex,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,Long bikeId,
			                       Date fromtime,Date totime,
			                       HttpSession session,Long [] channelId,String str
			                       ) throws Exception{
		
		try {
			Long currChannelId = (Long) session.getAttribute("currChannelId");
			loggers.info("获取登录账户渠道"+currChannelId);
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
			//loggers.info("渠道字符串"+str);
			if(str!=null&&!str.equals("")){
				String[] channelIdStr = str.split(",");
				if(channelIdStr.length>0){
					for(int i=0;i<channelIdStr.length;i++){
						channelIdList.add(Long.valueOf(channelIdStr[i]));
					}
					
				}
			}
			
			List<BikeRentInfo> bikeRentInfoList = new ArrayList<BikeRentInfo>();
			Integer totalPage = 1;
			Integer totalCount = 0;
			Integer starttime= null;
			Integer endtime= null;
			
			if(currChannelId==null){
				bikeRentInfoList = bikeRentInfoServiceReport.findBikeRentInfo(null,channelIdList, bikeId, fromtime, totime, pageIndex,starttime,endtime);
				totalCount = bikeRentInfoServiceReport.CountBikeRentInfo(null,channelIdList, bikeId, fromtime, totime, starttime,endtime);
				channelList = channelServiceReport.findAllChannelByChannelIds(null);
			}
			else{
				Channel channel =channelServiceReport.findById(currChannelId);
				List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
				channels.add(channel);
				List<Long> currChannelIds = new ArrayList<Long>();
				for(Channel c:channels){
					currChannelIds.add(c.getChannelId());
				}
				bikeRentInfoList = bikeRentInfoServiceReport.findBikeRentInfo(currChannelIds,channelIdList, bikeId, fromtime, totime, pageIndex,starttime,endtime);
				totalCount = bikeRentInfoServiceReport.CountBikeRentInfo(currChannelIds,channelIdList, bikeId, fromtime, totime,starttime,endtime);
				channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
			}
			for(BikeRentInfo bri : bikeRentInfoList){
				if(null!=bri.getRentStartFixedId()&&!bri.getRentStartFixedId().equals("")){
					FixedReturn startFixedReturn = fixedReturnServiceReport.findFixedById(bri.getRentStartFixedId());
					if(null!=startFixedReturn){
						bri.setStartFixedName(startFixedReturn.getFixedReturnName());
					}
				}
				if(null!=bri.getRentEndFixedId()&&!bri.getRentEndFixedId().equals("")){
					FixedReturn endFixedReturn = fixedReturnServiceReport.findFixedById(bri.getRentEndFixedId());
					if(null!=endFixedReturn){
						bri.setEndFixedName(endFixedReturn.getFixedReturnName());
					}
				}
				Date startDate = DateUtil.getDateFormat(bri.getRentStarttime());
				bri.setWeekName(DateUtil.formatWeek(startDate));
				
			}
			if(totalCount>0){
					 totalPage = PageUtil.getTotalPage(totalCount);
			}
				 
			
			model.addAttribute("bikeRentInfoList", bikeRentInfoList);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("bikeId", bikeId);
			model.addAttribute("fromtime", fromtime);
			model.addAttribute("totime", totime);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("channelList", channelList);
			model.addAttribute("channelIdList", channelIdList);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		//model.addAttribute("channels", channels);
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=rent_bike_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "rent_bike_excel_list";
		}
		return "rent_bike_list";
	}
	
	
	
	/**
	 * 还车数
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("returnNumByFixed")
	public @ResponseBody Map<String, Object> returnNumByFixed(HttpSession session, String fromtime,String totime,Integer time,String str)throws Exception {
		Long currChannelId = (Long) session.getAttribute("currChannelId");
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
			List<FixedReturn> fixedReturnList = new ArrayList<FixedReturn>();
			if(channelIdList.size()>0){
				totalCount = bikeRentInfoServiceReport.countReturnByChannelIds(channelIdList);
				fixedReturnList = fixedReturnServiceReport.findByChannels(channelIdList);
			}else{
				if(currChannelId==null){
					totalCount = bikeRentInfoServiceReport.countReturnByChannelIds(null);
					fixedReturnList = fixedReturnServiceReport.findByChannels(null);
				}
				else{
					Channel channel =channelServiceReport.findById(currChannelId);
					List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
					channels.add(channel);
					List<Long> currChannelIds = new ArrayList<Long>();
					for(Channel c:channels){
						currChannelIds.add(c.getChannelId());
					}
					totalCount = bikeRentInfoServiceReport.countReturnByChannelIds(currChannelIds);
					fixedReturnList = fixedReturnServiceReport.findByChannels(currChannelIds);
				}
				
			}
			Integer todayCount = 0;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	
			if(fromtime!=null && fromtime!=null){
				Date fromTime = format.parse(fromtime);
			    Date toTime = format.parse(totime);
			    for(FixedReturn fr : fixedReturnList){
					Integer rentNum = bikeRentInfoServiceReport.countRentInfoByEndFixed(fr.getFixedReturnId(),fromTime,toTime);
					todayCount += rentNum;
					JSONObject jsonObject = new JSONObject();
		        	jsonObject.put("period",fr.getFixedReturnName());
					jsonObject.put("increase", rentNum);
					jsonArray.add(jsonObject);//添加json
				}
			    map.put("totalCount", totalCount);
			    map.put("todayCount", todayCount);
				map.put("jsonArray", jsonArray);
				return map;
						
			}else if(time!=null){
				Calendar c = Calendar.getInstance();
				Date time1 = null;
				Date time2 = null;
				if(time==1){
		        	//过去七天
			        c.setTime(DateUtil.getCurrentDay());
			        time1 = c.getTime();
			        c.add(Calendar.DATE, - 7);
			        time2 = c.getTime();
			        for(FixedReturn fr : fixedReturnList){
						Integer rentNum = bikeRentInfoServiceReport.countRentInfoByEndFixed(fr.getFixedReturnId(),time2,time1);
						todayCount += rentNum;
						JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",fr.getFixedReturnName());
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
					}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
			        
		        }
		        if(time==2){
		        	//过去一月
		        	
		        	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM—dd"); 
		        	c.setTime(format2.parse(format2.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -1);
		        	time2 = c.getTime();
		        	for(FixedReturn fr : fixedReturnList){
						Integer rentNum = bikeRentInfoServiceReport.countRentInfoByEndFixed(fr.getFixedReturnId(),time2,time1);
						todayCount += rentNum;
						JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",fr.getFixedReturnName());
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
					}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
			        
		            
			        
		        }
		        if(time==3){
		        	//过去三个月
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM—dd");
		        	c.setTime(format1.parse(format1.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -3);
			    	time2 = c.getTime();
			    	//time3 = e.getTime();
			        for(FixedReturn fr : fixedReturnList){
						Integer rentNum = bikeRentInfoServiceReport.countRentInfoByEndFixed(fr.getFixedReturnId(),time2,time1);
						todayCount += rentNum;
						JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",fr.getFixedReturnName());
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
					}
				    map.put("todayCount", todayCount);
				    map.put("totalCount", totalCount);
			        map.put("jsonArray", jsonArray);
					return map;
		            
			        
		        }
		        if(time==4){
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		            c.setTime(format1.parse(format1.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -12);
		        	time2 = c.getTime();
			        for(FixedReturn fr : fixedReturnList){
						Integer rentNum = bikeRentInfoServiceReport.countRentInfoByEndFixed(fr.getFixedReturnId(),time2,time1);
						todayCount += rentNum;
						JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",fr.getFixedReturnName());
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
					}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
		        }else{
		        	for(FixedReturn fr : fixedReturnList){
						Integer rentNum = bikeRentInfoServiceReport.countRentInfoByEndFixed(fr.getFixedReturnId(),null,null);
						todayCount += rentNum;
						JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",fr.getFixedReturnName());
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
					}
		        	map.put("jsonArray", jsonArray);
					map.put("todayCount", todayCount);
					map.put("totalCount", totalCount);
					return map;
		        }
			}else{
				for(FixedReturn fr : fixedReturnList){
					Integer rentNum = bikeRentInfoServiceReport.countRentInfoByEndFixed(fr.getFixedReturnId(),null,null);
					todayCount += rentNum;
					JSONObject jsonObject = new JSONObject();
		        	jsonObject.put("period",fr.getFixedReturnName());
					jsonObject.put("increase", rentNum);
					jsonArray.add(jsonObject);//添加json
				}
				map.put("jsonArray", jsonArray);
				map.put("todayCount", todayCount);
				map.put("totalCount", totalCount);
				return map;
			}
			

		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return null;
	}
	
	
	/**
	 * 骑行时长订单数
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rentNumByRideTime")
	public @ResponseBody Map<String, Object> rentNumByRideTime(HttpSession session, String fromtime,String totime,Integer time,String str)throws Exception {
		Long currChannelId = (Long) session.getAttribute("currChannelId");
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
			if(channelIdList.size()>0){
				totalCount = bikeRentInfoServiceReport.countReturnByChannelIds(channelIdList);
			}else{
				if(currChannelId==null){
					totalCount = bikeRentInfoServiceReport.countReturnByChannelIds(null);
				}else{
					Channel channel =channelServiceReport.findById(currChannelId);
					List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
					channels.add(channel);
					List<Long> currChannelIds = new ArrayList<Long>();
					for(Channel c:channels){
						currChannelIds.add(c.getChannelId());
					}
					totalCount = bikeRentInfoServiceReport.countReturnByChannelIds(currChannelIds);
				}
				
			}
			Integer todayCount = 0;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	
			if(fromtime!=null && fromtime!=null){
				Date fromTime = format.parse(fromtime);
			    Date toTime = format.parse(totime);
			    for(int i =0;i<3;i++){
			    	if(i==0){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(5,30,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","5-30");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}else if(i==1){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(30,60,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","30-60");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}else if(i==2){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(60,null,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","60分钟及以上");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}
				}
			    map.put("totalCount", totalCount);
			    map.put("todayCount", todayCount);
				map.put("jsonArray", jsonArray);
				return map;
						
			}else if(time!=null){
				Calendar c = Calendar.getInstance();
				Date time1 = null;
				Date time2 = null;
				if(time==1){
		        	//过去七天
			        c.setTime(DateUtil.getCurrentDay());
			        time1 = c.getTime();
			        c.add(Calendar.DATE, - 7);
			        time2 = c.getTime();
			        for(int i =0;i<3;i++){
				    	if(i==0){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(5,30,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","5-30");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==1){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(30,60,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","30-60");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==2){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(60,null,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","60分钟及以上");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}
					}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
			        
		        }
		        if(time==2){
		        	//过去一月
		        	
		        	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM—dd"); 
		        	c.setTime(format2.parse(format2.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -1);
		        	time2 = c.getTime();
		        	 for(int i =0;i<3;i++){
					    	if(i==0){
					    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(5,30,time2,time1);
					    		todayCount += rentNum;
					    		JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period","5-30");
								jsonObject.put("increase", rentNum);
								jsonArray.add(jsonObject);//添加json
					    	}else if(i==1){
					    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(30,60,time2,time1);
					    		todayCount += rentNum;
					    		JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period","30-60");
								jsonObject.put("increase", rentNum);
								jsonArray.add(jsonObject);//添加json
					    	}else if(i==2){
					    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(60,null,time2,time1);
					    		todayCount += rentNum;
					    		JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period","60分钟及以上");
								jsonObject.put("increase", rentNum);
								jsonArray.add(jsonObject);//添加json
					    	}
						}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
			        
		            
			        
		        }
		        if(time==3){
		        	//过去三个月
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM—dd");
		        	c.setTime(format1.parse(format1.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -3);
			    	time2 = c.getTime();
			    	//time3 = e.getTime();
			    	 for(int i =0;i<3;i++){
					    	if(i==0){
					    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(5,30,time2,time1);
					    		todayCount += rentNum;
					    		JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period","5-30");
								jsonObject.put("increase", rentNum);
								jsonArray.add(jsonObject);//添加json
					    	}else if(i==1){
					    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(30,60,time2,time1);
					    		todayCount += rentNum;
					    		JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period","30-60");
								jsonObject.put("increase", rentNum);
								jsonArray.add(jsonObject);//添加json
					    	}else if(i==2){
					    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(60,null,time2,time1);
					    		todayCount += rentNum;
					    		JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period","60分钟及以上");
								jsonObject.put("increase", rentNum);
								jsonArray.add(jsonObject);//添加json
					    	}
						}
				    map.put("todayCount", todayCount);
				    map.put("totalCount", totalCount);
			        map.put("jsonArray", jsonArray);
					return map;
		            
			        
		        }
		        if(time==4){
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		            c.setTime(format1.parse(format1.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -12);
		        	time2 = c.getTime();
		        	 for(int i =0;i<3;i++){
					    	if(i==0){
					    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(5,30,time2,time1);
					    		todayCount += rentNum;
					    		JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period","5-30");
								jsonObject.put("increase", rentNum);
								jsonArray.add(jsonObject);//添加json
					    	}else if(i==1){
					    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(30,60,time2,time1);
					    		todayCount += rentNum;
					    		JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period","30-60");
								jsonObject.put("increase", rentNum);
								jsonArray.add(jsonObject);//添加json
					    	}else if(i==2){
					    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(60,null,time2,time1);
					    		todayCount += rentNum;
					    		JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period","60分钟及以上");
								jsonObject.put("increase", rentNum);
								jsonArray.add(jsonObject);//添加json
					    	}
						}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
		        }
			}
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return null;
	}
	
	
	/**
	 * 骑行时长订单数（细）
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rentDetailNumByRideTime")
	public @ResponseBody Map<String, Object> rentDetailNumByRideTime(HttpSession session, String fromtime,String totime,Integer time,String str)throws Exception {
		Long currChannelId = (Long) session.getAttribute("currChannelId");
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
			if(channelIdList.size()>0){
				totalCount = bikeRentInfoServiceReport.countReturnByChannelIds(channelIdList);
			}else{
				if(currChannelId==null){
					totalCount = bikeRentInfoServiceReport.countReturnByChannelIds(null);
				}else{
					Channel channel =channelServiceReport.findById(currChannelId);
					List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
					channels.add(channel);
					List<Long> currChannelIds = new ArrayList<Long>();
					for(Channel c:channels){
						currChannelIds.add(c.getChannelId());
					}
					totalCount = bikeRentInfoServiceReport.countReturnByChannelIds(currChannelIds);
				}
				
			}
			Integer todayCount = 0;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	
			if(fromtime!=null && fromtime!=null){
				Date fromTime = format.parse(fromtime);
			    Date toTime = format.parse(totime);
			    for(int i =0;i<9;i++){
			     	if(i==0){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(5,10,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","5-10分钟");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}else if(i==1){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(10,20,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","10-20分钟");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}else if(i==2){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(20,30,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","20-30分钟");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}else if(i==3){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(30,40,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","30-40分钟");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}else if(i==4){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(40,50,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","40-50分钟");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}else if(i==5){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(50,60,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","50-60分钟");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}else if(i==6){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(60,90,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","60-90分钟");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}else if(i==7){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(90,120,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","90-120分钟");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}else if(i==8){
			    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(120,null,fromTime,toTime);
			    		todayCount += rentNum;
			    		JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period","120分钟及以上");
						jsonObject.put("increase", rentNum);
						jsonArray.add(jsonObject);//添加json
			    	}
				}
			    map.put("totalCount", totalCount);
			    map.put("todayCount", todayCount);
				map.put("jsonArray", jsonArray);
				return map;
						
			}else if(time!=null){
				Calendar c = Calendar.getInstance();
				Date time1 = null;
				Date time2 = null;
				if(time==1){
		        	//过去七天
			        c.setTime(DateUtil.getCurrentDay());
			        time1 = c.getTime();
			        c.add(Calendar.DATE, - 7);
			        time2 = c.getTime();
			        for(int i =0;i<9;i++){
				     	if(i==0){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(5,10,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","5-10分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==1){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(10,20,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","10-20分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==2){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(20,30,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","20-30分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==3){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(30,40,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","30-40分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==4){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(40,50,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","40-50分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==5){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(50,60,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","50-60分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==6){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(60,90,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","60-90分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==7){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(90,120,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","90-120分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==8){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(120,null,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","120分钟及以上");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}
					}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
			        
		        }
		        if(time==2){
		        	//过去一月
		        	
		        	SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM—dd"); 
		        	c.setTime(format2.parse(format2.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -1);
		        	time2 = c.getTime();
	        	    for(int i =0;i<9;i++){
				     	if(i==0){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(5,10,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","5-10分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==1){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(10,20,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","10-20分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==2){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(20,30,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","20-30分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==3){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(30,40,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","30-40分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==4){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(40,50,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","40-50分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==5){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(50,60,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","50-60分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==6){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(60,90,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","60-90分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==7){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(90,120,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","90-120分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==8){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(120,null,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","120分钟及以上");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}
					}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
			        
		            
			        
		        }
		        if(time==3){
		        	//过去三个月
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM—dd");
		        	c.setTime(format1.parse(format1.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -3);
			    	time2 = c.getTime();
			    	//time3 = e.getTime();
			    	for(int i =0;i<9;i++){
				     	if(i==0){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(5,10,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","5-10分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==1){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(10,20,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","10-20分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==2){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(20,30,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","20-30分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==3){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(30,40,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","30-40分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==4){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(40,50,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","40-50分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==5){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(50,60,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","50-60分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==6){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(60,90,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","60-90分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==7){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(90,120,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","90-120分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==8){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(120,null,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","120分钟及以上");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}
					}
				    map.put("todayCount", todayCount);
				    map.put("totalCount", totalCount);
			        map.put("jsonArray", jsonArray);
					return map;
		            
			        
		        }
		        if(time==4){
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		            c.setTime(format1.parse(format1.format(new Date())));
		            time1 = c.getTime();
		            c.add(Calendar.MONTH, -12);
		        	time2 = c.getTime();
		        	for(int i =0;i<9;i++){
				     	if(i==0){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(5,10,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","5-10分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==1){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(10,20,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","10-20分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==2){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(20,30,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","20-30分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==3){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(30,40,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","30-40分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==4){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(40,50,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","40-50分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==5){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(50,60,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","50-60分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==6){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(60,90,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","60-90分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==7){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(90,120,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","90-120分钟");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}else if(i==8){
				    		Integer rentNum = bikeRentInfoServiceReport.countRentInfoByRideTime(120,null,time2,time1);
				    		todayCount += rentNum;
				    		JSONObject jsonObject = new JSONObject();
				        	jsonObject.put("period","120分钟及以上");
							jsonObject.put("increase", rentNum);
							jsonArray.add(jsonObject);//添加json
				    	}
					}
				    map.put("totalCount", totalCount);
				    map.put("todayCount", todayCount);
			        map.put("jsonArray", jsonArray);
					return map;
		        }
			}
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return null;
	}
	
}
