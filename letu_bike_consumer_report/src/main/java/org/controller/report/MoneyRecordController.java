package org.controller.report;

import java.math.BigDecimal;
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
import org.entity.dto.BikeRentInfo;
import org.entity.dto.Channel;
import org.entity.dto.MoneyLog;
import org.service.report.BikeRentInfoServiceReport;
import org.service.report.ChannelServiceReport;
import org.service.report.MoneyLogServiceReport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.PageUtil;

import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.SimpleDateFormat;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping("report/moneyWater")
public class MoneyRecordController {

	@Resource
	BikeRentInfoServiceReport bikeRentInfoServiceReport;
	
	@Resource
	ChannelServiceReport channelServiceReport;
	
	@Resource
	MoneyLogServiceReport moneyLogServiceReport;
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(MoneyRecordController.class);
	
	/**
	 * 租赁消费
	 * @param session
	 * @param response
	 * @param flag
	 * @param channelId
	 * @param model
	 * @param returnFromTime
	 * @param returnTotime
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rentMoneyWater")
	public String rentMoneyWater(HttpSession session,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,Long[] channelId,Model model,Date fromtime,Date totime,@RequestParam(defaultValue="1")Integer pageIndex,String tel,String name,String bikeCode,String str) throws Exception{
		try {
			Long currChannelId = (Long) session.getAttribute("currChannelId");
			if(1 == flag){
				pageIndex = null;
			}
			List<Long> channelIdList = new ArrayList<Long>();
			if(null!=channelId&&channelId.length>0){
				for(int i = 0;i<channelId.length;i++){
					channelIdList.add(channelId[i]);
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
			List<BikeRentInfo> bikeRentInfos = new ArrayList<BikeRentInfo>();
			Integer totalPage = 1;
			List<Channel> channelList = new ArrayList<Channel>();
			List<BikeRentInfo> bikeRentInfoList = new ArrayList<BikeRentInfo>();
	    	if(currChannelId==null){
	    		bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,pageIndex,fromtime,totime,tel,name,bikeCode,null);
	    		totalPage = bikeRentInfoServiceReport.countBikeRentInfos(channelIdList,fromtime,totime,tel,name,bikeCode,null);
	    		channelList = channelServiceReport.findAllChannelByChannelIds(null);
	    		bikeRentInfoList = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,fromtime,totime,tel,name,bikeCode,null);
			}else{
				Channel channel =channelServiceReport.findById(currChannelId);
				List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
				channels.add(channel);
				List<Long> currChannelIds = new ArrayList<Long>();
				for(Channel c:channels){
					currChannelIds.add(c.getChannelId());
				}
				bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,pageIndex,fromtime,totime,tel,name,bikeCode,currChannelIds);
				totalPage = bikeRentInfoServiceReport.countBikeRentInfos(channelIdList,fromtime,totime,tel,name,bikeCode,currChannelIds);
				channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
				bikeRentInfoList = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,fromtime,totime,tel,name,bikeCode,currChannelIds);
			}
	    	if(totalPage==0){
	        	totalPage = 1;
	        }
	    	BigDecimal sumMoney = new BigDecimal("0.00");
			for(BikeRentInfo bikeRentInfo :bikeRentInfoList){
				if(bikeRentInfo.getRentPrice()!=null&&!bikeRentInfo.getRentPrice().equals("")){
					sumMoney = sumMoney.add(bikeRentInfo.getRentPrice());
				}
				
			}
			model.addAttribute("bikeRentInfos", bikeRentInfos);
			model.addAttribute("sumMoney", sumMoney);
			model.addAttribute("channelList", channelList);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("bikeRentInfoList", bikeRentInfoList);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("fromtime", fromtime);
			model.addAttribute("totime", totime);
			model.addAttribute("channelId", channelId);
			model.addAttribute("tel", tel);
			model.addAttribute("name", name);
			model.addAttribute("bikeCode", bikeCode);
			model.addAttribute("channelIdList", channelIdList);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=rent_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "rent_account_excel_list";
		}
		return "rent_account_list";
	}
	
	/**
	 * 预付款退款报表
	 * @param response
	 * @param flag
	 * @param item
	 * @param model
	 * @param FromTime
	 * @param ToTime
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("moneyLogList")
	public String moneyLogList(HttpSession session,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,String name,String tel,Model model,Date fromtime,Date totime,@RequestParam(defaultValue="1")Integer pageIndex,Long[] channelId,String str) throws Exception{
		try {
			if(1 == flag){
				pageIndex = null;
			}
			List<Long> channelIdList = new ArrayList<Long>();
			if(null!=channelId&&channelId.length>0){
				for(int i = 0;i<channelId.length;i++){
					channelIdList.add(channelId[i]);
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
			Long currChannelId = (Long) session.getAttribute("currChannelId");
			if(1 == flag){
				pageIndex = null;
			}
			List<MoneyLog> moneyLogs = new ArrayList<MoneyLog>();
			Integer totalPage = 1;
			List<Channel> channelList = new ArrayList<Channel>();
			List<MoneyLog> summoneyLogs = new ArrayList<MoneyLog>();
	    	if(currChannelId==null){
	    		moneyLogs = moneyLogServiceReport.moneyLogList(pageIndex, name, tel, fromtime, totime,null,channelIdList);
	    		totalPage = moneyLogServiceReport.countMoneyLog(name, tel, fromtime, totime,null,channelIdList);
	    		channelList = channelServiceReport.findAllChannelByChannelIds(null);
	    		summoneyLogs = moneyLogServiceReport.moneyLogList(null, name, tel, fromtime, totime,null,channelIdList);
			}else{
				Channel channel =channelServiceReport.findById(currChannelId);
				List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
				channels.add(channel);
				List<Long> currChannelIds = new ArrayList<Long>();
				for(Channel c:channels){
					currChannelIds.add(c.getChannelId());
				}
				moneyLogs = moneyLogServiceReport.moneyLogList(pageIndex, name, tel, fromtime, totime, currChannelIds,channelIdList);
				totalPage = moneyLogServiceReport.countMoneyLog(name, tel, fromtime, totime, currChannelIds,channelIdList);
				channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
				summoneyLogs = moneyLogServiceReport.moneyLogList(null, name, tel, fromtime, totime,currChannelIds,channelIdList);
			}
			if(totalPage==0){
	        	totalPage = 1;
	        }
			for(MoneyLog moneyLog :moneyLogs){
				if(null!=moneyLog.getMoneyLogChannelId()){
					Channel channel = channelServiceReport.findById(moneyLog.getMoneyLogChannelId());
					moneyLog.setChannelName(channel.getChannelName());
				}
			}
			BigDecimal sumRefundMoney = new BigDecimal("0.00");
			for(MoneyLog moneyLog :summoneyLogs){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
						sumRefundMoney = sumRefundMoney.add(moneyLog.getMoneyLogOpreateMoney());
				}
				
			}
			model.addAttribute("sumRefundMoney", sumRefundMoney);
			model.addAttribute("moneyLogs", moneyLogs);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("channelList", channelList);
			model.addAttribute("name", name);
			model.addAttribute("tel", tel);
			model.addAttribute("fromtime", fromtime);
			model.addAttribute("totime", totime);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("channelIdList", channelIdList);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=moneylog_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "money_log_excel_list";
		}
		return "money_log_list";
	}
	
	/**
	 * 保险费用
	 * @param session
	 * @param response
	 * @param flag
	 * @param model
	 * @param FromTime
	 * @param ToTime
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("insuranceLogList")
	public String insuranceLogList(HttpSession session,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,Long[] channelId,Model model,Date fromtime,Date totime,@RequestParam(defaultValue="1")Integer pageIndex,String tel,String name,String bikeCode,String str) throws Exception{
	
			loggers.info("进入保险费用列表");
			Long currChannelId = (Long) session.getAttribute("currChannelId");
			List<Long> channelIdList = new ArrayList<Long>();
			if(null!=channelId&&channelId.length>0){
				for(int i = 0;i<channelId.length;i++){
					channelIdList.add(channelId[i]);
				}
			}
			loggers.info("渠道列表");
			if(str!=null&&!str.equals("")){
				String[] channelIdStr = str.split(",");
				if(channelIdStr.length>0){
					for(int i=0;i<channelIdStr.length;i++){
						channelIdList.add(Long.valueOf(channelIdStr[i]));
					}
					
				}
			}
			if(1 == flag){
				pageIndex = null;
			}
			List<BikeRentInfo> bikeRentInfos = new ArrayList<BikeRentInfo>();
			List<BikeRentInfo> bikeRentInfoList = new ArrayList<BikeRentInfo>();
			Integer totalPage = 1;
			List<Channel> channelList = new ArrayList<Channel>();
	    	if(currChannelId==null){
	    		bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,pageIndex,fromtime,totime,tel,name,bikeCode,null);
	    		loggers.info("查询租赁信息");
	    		totalPage = bikeRentInfoServiceReport.countBikeRentInfos(channelIdList,fromtime,totime,tel,name,bikeCode,null);
	    		channelList = channelServiceReport.findAllChannelByChannelIds(null);
	    		bikeRentInfoList = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,fromtime,totime,tel,name,bikeCode,null);
			}else{
				Channel channel =channelServiceReport.findById(currChannelId);
				List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
				channels.add(channel);
				List<Long> currChannelIds = new ArrayList<Long>();
				for(Channel c:channels){
					currChannelIds.add(c.getChannelId());
				}
				bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,pageIndex,fromtime,totime,tel,name,bikeCode,currChannelIds);
				totalPage = bikeRentInfoServiceReport.countBikeRentInfos(channelIdList,fromtime,totime,tel,name,bikeCode,currChannelIds);
				channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
				bikeRentInfoList = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,fromtime,totime,tel,name,bikeCode,currChannelIds);
			}
	    	if(totalPage==0){
	        	totalPage = 1;
	        }
	    	BigDecimal sumMoney = new BigDecimal("0.00") ;
			for(BikeRentInfo bikeRentInfo :bikeRentInfoList){
				if(bikeRentInfo.getRentInsurancePrice()!=null&&!bikeRentInfo.getRentInsurancePrice().equals("")){
					sumMoney = sumMoney.add(bikeRentInfo.getRentInsurancePrice());
				}
				
			}
			loggers.info("计算保险费总费用");
			model.addAttribute("bikeRentInfos", bikeRentInfos);
			model.addAttribute("sumMoney", sumMoney);
			model.addAttribute("channelList", channelList);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("fromtime", fromtime);
			model.addAttribute("totime", totime);
			model.addAttribute("tel", tel);
			model.addAttribute("name", name);
			model.addAttribute("bikeCode", bikeCode);
			model.addAttribute("channelId", channelId);
			model.addAttribute("channelIdList", channelIdList);
		
		
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=insurance_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "insurance_log_excel_list";
		}
		return "insurance_log_list";
	}
	
	
	/**
	 * 总收益报表
	 * @param model
	 * @param pageIndex
	 * @param type
	 * @param name
	 * @param tel
	 * @param FromTime
	 * @param ToTime
	 * @param session
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("totalMoneyList")
	public String totalMoneyList(Model model,@RequestParam(defaultValue="1")Integer pageIndex,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,Integer type,String name,String tel,Date fromtime,Date totime,
            HttpSession session,Long [] channelId,String str) throws Exception{
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
			List<MoneyLog> moneyLogList = new ArrayList<MoneyLog>();
			List<MoneyLog> summoneyLogList = new ArrayList<MoneyLog>();
			Integer totalPage = 1;
			Integer totalCount = 0;
			if(currChannelId==null){
				moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, type, name, tel, fromtime, totime, pageIndex);
				totalCount = moneyLogServiceReport.countMoneyLognum(null,channelIdList, type, name, tel, fromtime, totime);
				channelList = channelServiceReport.findAllChannelByChannelIds(null);
				summoneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, type, name, tel, fromtime, totime, null);
			}
			else{
				Channel channel =channelServiceReport.findById(currChannelId);
				List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
				channels.add(channel);
				List<Long> currChannelIds = new ArrayList<Long>();
				for(Channel c:channels){
					currChannelIds.add(c.getChannelId());
				}
				moneyLogList = moneyLogServiceReport.findMoneyLog(currChannelIds,channelIdList, type, name, tel, fromtime, totime, pageIndex);
				totalCount = moneyLogServiceReport.countMoneyLognum(currChannelIds,channelIdList, type, name, tel, fromtime, totime);
				channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
				summoneyLogList = moneyLogServiceReport.findMoneyLog(currChannelIds,channelIdList, type, name, tel, fromtime, totime, null);
			}
			if(totalCount>0){
				 totalPage = PageUtil.getTotalPage(totalCount);
		    }
			for(MoneyLog moneyLog :moneyLogList){
				if(null!=moneyLog.getMoneyLogChannelId()){
					Channel channel = channelServiceReport.findById(moneyLog.getMoneyLogChannelId());
					moneyLog.setChannelName(channel.getChannelName());
				}
			}
			BigDecimal sumBuchongMoney = new BigDecimal("0.00");
			BigDecimal sumBalanceMoney = new BigDecimal("0.00");
			BigDecimal totalMoney = new BigDecimal("0.00");
			for(MoneyLog moneyLog :summoneyLogList){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					 if(moneyLog.getMoneyLogStreamType()==3){
						sumBuchongMoney = sumBuchongMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}else if(moneyLog.getMoneyLogStreamType()==4){
						sumBalanceMoney = sumBalanceMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}
				}
				
			}
			
			totalMoney = totalMoney.add(sumBuchongMoney);
			totalMoney = totalMoney.add(sumBalanceMoney);
			model.addAttribute("moneyLogList", moneyLogList);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("channelList", channelList);
			model.addAttribute("type", type);
			model.addAttribute("name", name);
			model.addAttribute("tel", tel);
			model.addAttribute("fromtime", fromtime);
			model.addAttribute("totime", totime);
			model.addAttribute("sumBuchongMoney", sumBuchongMoney);
			model.addAttribute("sumBalanceMoney", sumBalanceMoney);
			model.addAttribute("totalMoney", totalMoney);
			model.addAttribute("channelId", channelId);
			model.addAttribute("channelIdList", channelIdList);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=totalMoney_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "totalMoney_excel_list";
		}
		return "total_money_list";
	}
	
	/**
	 * 预付款充值报表
	 * @param model
	 * @param pageIndex
	 * @param name
	 * @param tel
	 * @param FromTime
	 * @param ToTime
	 * @param session
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("depositList")
	public String depositList(Model model,@RequestParam(defaultValue="1")Integer pageIndex,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,String name,String tel,Date fromtime,Date totime,
            HttpSession session,Long [] channelId,String str) throws Exception{
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
				 channelList = channelServiceReport.findAllChannelByChannelIds(channelIdList);
				
			}
			if(str!=null&&!str.equals("")){
				String[] channelIdStr = str.split(",");
				if(channelIdStr.length>0){
					for(int i=0;i<channelIdStr.length;i++){
						channelIdList.add(Long.valueOf(channelIdStr[i]));
					}
					
				}
			}
			List<MoneyLog> moneyLogList = new ArrayList<MoneyLog>();
			List<MoneyLog> summoneyLogList = new ArrayList<MoneyLog>();
			Integer totalPage = 1;
			Integer totalCount = 0;
			if(currChannelId==null){
				moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 3, name, tel, fromtime, totime, pageIndex);
				totalCount = moneyLogServiceReport.countMoneyLognum(null,channelIdList, 3, name, tel, fromtime, totime);
				channelList = channelServiceReport.findAllChannelByChannelIds(null);
				summoneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 3, name, tel, fromtime, totime, null);
			}
			else{
				Channel channel =channelServiceReport.findById(currChannelId);
				List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
				channels.add(channel);
				List<Long> currChannelIds = new ArrayList<Long>();
				for(Channel c:channels){
					currChannelIds.add(c.getChannelId());
				}
				moneyLogList = moneyLogServiceReport.findMoneyLog(currChannelIds,channelIdList, 3, name, tel, fromtime, totime, pageIndex);
				totalCount = moneyLogServiceReport.countMoneyLognum(currChannelIds,channelIdList, 3, name, tel, fromtime, totime);
				channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
				summoneyLogList = moneyLogServiceReport.findMoneyLog(currChannelIds,channelIdList, 3, name, tel, fromtime, totime, null);
			}
			if(totalCount>0){
				 totalPage = PageUtil.getTotalPage(totalCount);
		}
			for(MoneyLog moneyLog :moneyLogList){
				if(null!=moneyLog.getMoneyLogChannelId()){
					Channel channel = channelServiceReport.findById(moneyLog.getMoneyLogChannelId());
					moneyLog.setChannelName(channel.getChannelName());
				}
			}
			BigDecimal sumBuchongMoney = new BigDecimal("0.00");
			
			for(MoneyLog moneyLog :summoneyLogList){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					 if(moneyLog.getMoneyLogStreamType()==3){
						sumBuchongMoney = sumBuchongMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}
				}
				
			}
			
			model.addAttribute("moneyLogList", moneyLogList);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("channelList", channelList);
			model.addAttribute("channelIdList", channelIdList);
			model.addAttribute("name", name);
			model.addAttribute("tel", tel);
			model.addAttribute("fromtime", fromtime);
			model.addAttribute("totime", totime);
			model.addAttribute("sumBuchongMoney", sumBuchongMoney);
			model.addAttribute("channelId", channelId);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=deposit_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "deposit_excel_list";
		}
		return "deposit_list";
	}
	
	@RequestMapping("balanceList")
	public String balanceList(Model model,@RequestParam(defaultValue="1")Integer pageIndex,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,String name,String tel,Date fromtime,Date totime,
            HttpSession session,Long [] channelId,String str) throws Exception{
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
				 channelList = channelServiceReport.findAllChannelByChannelIds(channelIdList);
				
			}
			if(str!=null&&!str.equals("")){
				String[] channelIdStr = str.split(",");
				if(channelIdStr.length>0){
					for(int i=0;i<channelIdStr.length;i++){
						channelIdList.add(Long.valueOf(channelIdStr[i]));
					}
					
				}
			}
			List<MoneyLog> moneyLogList = new ArrayList<MoneyLog>();
			List<MoneyLog> summoneyLogList = new ArrayList<MoneyLog>();
			List<MoneyLog> moneyLogList1 = new ArrayList<MoneyLog>();
			Integer totalPage = 1;
			Integer totalCount = 0;
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			String date1 = format.format(date); 
			Date today = format.parse(date1);
			if(currChannelId==null){
				moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 4, name, tel, fromtime, totime, pageIndex);
				totalCount = moneyLogServiceReport.countMoneyLognum(null,channelIdList, 4, name, tel, fromtime, totime);
				channelList = channelServiceReport.findAllChannelByChannelIds(null);
				summoneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 4, name, tel, fromtime, totime, null);
				moneyLogList1 = moneyLogServiceReport.findMoneyLog(null,channelIdList, 4, name, tel, today, today, pageIndex);
			}
			else{
				Channel channel =channelServiceReport.findById(currChannelId);
				List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
				channels.add(channel);
				List<Long> currChannelIds = new ArrayList<Long>();
				for(Channel c:channels){
					currChannelIds.add(c.getChannelId());
				}
				moneyLogList = moneyLogServiceReport.findMoneyLog(currChannelIds,channelIdList, 4, name, tel, fromtime, totime, pageIndex);
				totalCount = moneyLogServiceReport.countMoneyLognum(currChannelIds,channelIdList, 4, name, tel, fromtime, totime);
				channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
				summoneyLogList = moneyLogServiceReport.findMoneyLog(currChannelIds,channelIdList, 4, name, tel, fromtime, totime, null);
				moneyLogList1 = moneyLogServiceReport.findMoneyLog(currChannelIds,channelIdList, 4, name, tel, today, today, pageIndex);
			}
			if(totalCount>0){
				 totalPage = PageUtil.getTotalPage(totalCount);
		}
			for(MoneyLog moneyLog :moneyLogList){
				if(null!=moneyLog.getMoneyLogChannelId()){
					Channel channel = channelServiceReport.findById(moneyLog.getMoneyLogChannelId());
					moneyLog.setChannelName(channel.getChannelName());
				}
			}
			BigDecimal sumBalanceMoney = new BigDecimal("0.00");
			for(MoneyLog moneyLog :summoneyLogList){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					 if(moneyLog.getMoneyLogStreamType()==4){
						 sumBalanceMoney = sumBalanceMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}
				}
				
			}
			
			
			BigDecimal todayBalanceMoney = new BigDecimal("0.00");
			for(MoneyLog moneyLog :moneyLogList1){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					 if(moneyLog.getMoneyLogStreamType()==4){
						 todayBalanceMoney = todayBalanceMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}
				}
				
			}
			String result = "0";
			if(sumBalanceMoney.doubleValue()>0){
				Double num1 = todayBalanceMoney.doubleValue();
				Double num2 = sumBalanceMoney.doubleValue();
				NumberFormat numberFormat = NumberFormat.getInstance();

				// 设置精确到小数点后2位

				numberFormat.setMaximumFractionDigits(2);

				result = numberFormat.format((Double) num1 / (Double) num2 * 100);
			}
			model.addAttribute("moneyLogList", moneyLogList);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("channelList", channelList);
			model.addAttribute("channelIdList", channelIdList);
			model.addAttribute("name", name);
			model.addAttribute("tel", tel);
			model.addAttribute("fromtime", fromtime);
			model.addAttribute("totime", totime);
			model.addAttribute("sumBalanceMoney", sumBalanceMoney);
			model.addAttribute("todayBalanceMoney", todayBalanceMoney);
			model.addAttribute("result", result);
			model.addAttribute("channelId", channelId);
		} catch (Exception e) {
			loggers.error("异常信息",e);
		}
		
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=balance_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "balance_excel_list";
		}
		return "balance_list";
	}
	
	/**
	 * 总收益
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("total")
	public @ResponseBody Map<String, Object> total(String fromtime,String totime,Integer time,String str)throws Exception {
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
			
			List<MoneyLog> moneyLogList = new ArrayList<MoneyLog>();
			List<MoneyLog> moneyLogList1 = new ArrayList<MoneyLog>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			String date1 = format.format(date); 
			Date today = format.parse(date1);
			
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
				        
				        
						for(int i=0;i<=x;i++){
							
					        
							   BigDecimal increase =  new BigDecimal("0.00");
							    time1 = c.getTime();
							    
							    moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time1, time1, null);
							    for(MoneyLog moneyLog :moneyLogList){
					    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					    				 if(moneyLog.getMoneyLogStreamType()==3){
					    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
					    					 
					    					 
					    				}else if(moneyLog.getMoneyLogStreamType()==4){
					    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
					    					
					    				}
					    			}
					    			
					    		}
					        	c.add(Calendar.DATE, +1);
					        	
					        	
					        	JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period",format.format(time1));
								jsonObject.put("increase", increase);
			    				jsonArray.add(jsonObject);//添加json
					            
					        
				        	
				        }
						map.put("jsonArray", jsonArray);
						
				
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
			        BigDecimal totalbuchong =  new BigDecimal("0.00");
			        BigDecimal totalbalance =  new BigDecimal("0.00");
			        
			        for(int i=0;i<8;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	time2 = d.getTime();
			        	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time2, null);
			        	c.add(Calendar.DATE, +1);
			        	
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==3){
			    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					 totalbuchong = totalbuchong.add(moneyLog.getMoneyLogOpreateMoney());
			    					 
			    				}else if(moneyLog.getMoneyLogStreamType()==4){
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					totalbalance = totalbalance.add(moneyLog.getMoneyLogOpreateMoney());
			    				}
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbuchong", totalbuchong);
						jsonObject.put("totalbalance", totalbalance);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
					
			        
		        }
		        if(time==2){
		        	//过去一月
		        	
		        	
		        	SimpleDateFormat format2 = new SimpleDateFormat("MM-dd"); 
		        	
		        	c.setTime(format.parse(format.format(new Date())));
		        	c.add(Calendar.DATE, -30);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        BigDecimal totalbuchong =  new BigDecimal("0.00");
			        BigDecimal totalbalance =  new BigDecimal("0.00");
			        for(int i=0;i<31;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	
			        	
			        	time2 = d.getTime();
			        	
			        	c.add(Calendar.DATE, +1);
			        	
			        	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time2, null);
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==3){
			    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					 totalbuchong = totalbuchong.add(moneyLog.getMoneyLogOpreateMoney());
			    					 
			    				}else if(moneyLog.getMoneyLogStreamType()==4){
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					totalbalance = totalbalance.add(moneyLog.getMoneyLogOpreateMoney());
			    				}
			    			}
			    			
			    		}
			        	
			        	
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format2.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbuchong", totalbuchong);
						jsonObject.put("totalbalance", totalbalance);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
					
			        
		            
			        
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
			        BigDecimal totalbuchong =  new BigDecimal("0.00");
			        BigDecimal totalbalance =  new BigDecimal("0.00");
			        
			        for(int i=0;i<4;i++){
			        	
			        	
			        	BigDecimal increase =  new BigDecimal("0.00");
						d.setTime(c.getTime());
				    	d.add(Calendar.MONTH, +1);
				    	d.add(Calendar.DATE, -1);
				    	e.setTime(d.getTime());
				    	time2 = c.getTime();
				    	time3 = e.getTime();
				    	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time3, null);
			        	
			        	c.add(Calendar.MONTH, +1);
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==3){
			    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					 totalbuchong = totalbuchong.add(moneyLog.getMoneyLogOpreateMoney());
			    					 
			    				}else if(moneyLog.getMoneyLogStreamType()==4){
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					totalbalance = totalbalance.add(moneyLog.getMoneyLogOpreateMoney());
			    				}
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbuchong", totalbuchong);
						jsonObject.put("totalbalance", totalbalance);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
					
		            
			        
		        }
		        if(time==4){
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		        	Date time3 = null;
		            c.setTime(format1.parse(format1.format(new Date())));
		            c.add(Calendar.MONTH, -12);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        Calendar e = Calendar.getInstance();
			        BigDecimal totalbuchong =  new BigDecimal("0.00");
			        BigDecimal totalbalance =  new BigDecimal("0.00");
			        for(int i=0;i<13;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	d.add(Calendar.MONTH, +1);
			        	e.setTime(d.getTime());
			        	time2 = c.getTime();
			        	time3 = e.getTime();
			        	c.add(Calendar.MONTH, +1);
			        	d.add(Calendar.DATE, -1);
			        	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time3, null);
			        	
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==3){
			    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					 totalbuchong = totalbuchong.add(moneyLog.getMoneyLogOpreateMoney());
			    					 
			    				}else if(moneyLog.getMoneyLogStreamType()==4){
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					totalbalance = totalbalance.add(moneyLog.getMoneyLogOpreateMoney());
			    				}
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbuchong", totalbuchong);
						jsonObject.put("totalbalance", totalbalance);
	    				jsonArray.add(jsonObject);//添加json
						
			        }
			        map.put("jsonArray", jsonArray);
					
			        
		            
			        
		        }
		        moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, null, null, null);
		        
			}
			else{
				moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, null, null, null);
				
			}
			moneyLogList1 = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, today, today, null);
			BigDecimal sumBuchongMoney = new BigDecimal("0.00");
			BigDecimal sumBalanceMoney = new BigDecimal("0.00");
			BigDecimal totalMoney = new BigDecimal("0.00");
			BigDecimal todaysumBuchongMoney = new BigDecimal("0.00");
			BigDecimal todaysumBalanceMoney = new BigDecimal("0.00");
			BigDecimal todaytotalMoney = new BigDecimal("0.00");
			for(MoneyLog moneyLog :moneyLogList){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					 if(moneyLog.getMoneyLogStreamType()==3){
						sumBuchongMoney = sumBuchongMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}else if(moneyLog.getMoneyLogStreamType()==4){
						sumBalanceMoney = sumBalanceMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}
				}
				
			}
			for(MoneyLog moneyLog :moneyLogList1){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					 if(moneyLog.getMoneyLogStreamType()==3){
						 todaysumBuchongMoney = todaysumBuchongMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}else if(moneyLog.getMoneyLogStreamType()==4){
						todaysumBalanceMoney = todaysumBalanceMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}
				}
				
			}
			totalMoney = totalMoney.add(sumBuchongMoney);
			totalMoney = totalMoney.add(sumBalanceMoney);
			todaytotalMoney = todaytotalMoney.add(todaysumBuchongMoney);
			todaytotalMoney = todaytotalMoney.add(todaysumBalanceMoney);
			map.put("sumBuchongMoney", sumBuchongMoney);
			map.put("sumBalanceMoney", sumBalanceMoney);
			map.put("totalMoney", totalMoney);
			map.put("todaysumBuchongMoney", todaysumBuchongMoney);
			map.put("todaysumBalanceMoney", todaysumBalanceMoney);
			map.put("todaytotalMoney", todaytotalMoney);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return map;
	}
	
	/**
	 * 保险
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("insurance")
	public @ResponseBody Map<String, Object> insurance(String fromtime,String totime,Integer time,String str)throws Exception {
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
			
			List<BikeRentInfo> bikeRentInfos = new ArrayList<BikeRentInfo>();
			List<BikeRentInfo> todaybikeRentInfos = new ArrayList<BikeRentInfo>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			String date1 = format.format(date); 
			Date today = format.parse(date1);
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
				        
						BigDecimal sumMoney =  new BigDecimal("0.00");
						for(int i=0;i<=x;i++){
							
					        
							   BigDecimal increase =  new BigDecimal("0.00");
							    time1 = c.getTime();
							    bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,time1,time1,null,null,null,null);
							    for(BikeRentInfo bikeRentInfo :bikeRentInfos){
					    			if(bikeRentInfo.getRentInsurancePrice()!=null&&!bikeRentInfo.getRentInsurancePrice().equals("")){
					    				sumMoney = sumMoney.add(bikeRentInfo.getRentInsurancePrice());
					    				increase = increase.add(bikeRentInfo.getRentInsurancePrice());
					    			}
					    			
					    		}
					        	c.add(Calendar.DATE, +1);
					        	
					        	
					        	JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period",format.format(time1));
								jsonObject.put("increase", increase);
								jsonObject.put("sumMoney", sumMoney);
			    				jsonArray.add(jsonObject);//添加json
					            
					        
				        	
				        }
						map.put("jsonArray", jsonArray);
				
				
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
			        BigDecimal sumMoney =  new BigDecimal("0.00");
			        
			        
			        for(int i=0;i<8;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	time2 = d.getTime();
			        	bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,time2,time2,null,null,null,null);
			        	c.add(Calendar.DATE, +1);
			        	
			        	for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			    			if(bikeRentInfo.getRentInsurancePrice()!=null&&!bikeRentInfo.getRentInsurancePrice().equals("")){
			    				sumMoney = sumMoney.add(bikeRentInfo.getRentInsurancePrice());
			    				increase = increase.add(bikeRentInfo.getRentInsurancePrice());
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumMoney", sumMoney);
						
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
			        
		        }
		        if(time==2){
		        	//过去一月
	                SimpleDateFormat format2 = new SimpleDateFormat("MM-dd"); 
		        	c.setTime(format.parse(format.format(new Date())));
		        	c.add(Calendar.DATE, -30);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
		            BigDecimal sumMoney =  new BigDecimal("0.00");
		            for(int i=0;i<31;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	
			        	
			        	time2 = d.getTime();
			        	
			        	c.add(Calendar.DATE, +1);
			        	
			        	bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,time2,time2,null,null,null,null);
			        	for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			    			if(bikeRentInfo.getRentInsurancePrice()!=null&&!bikeRentInfo.getRentInsurancePrice().equals("")){
			    				sumMoney = sumMoney.add(bikeRentInfo.getRentInsurancePrice());
			    				increase = increase.add(bikeRentInfo.getRentInsurancePrice());
			    			}
			    			
			    		}
			        	
			        	
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format2.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumMoney", sumMoney);
						
	    				jsonArray.add(jsonObject);//添加json
			        }
		            map.put("jsonArray", jsonArray);
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
			        BigDecimal sumMoney =  new BigDecimal("0.00");
			       
			        
			        for(int i=0;i<4;i++){
			        	
			        	
			        	BigDecimal increase =  new BigDecimal("0.00");
						d.setTime(c.getTime());
				    	d.add(Calendar.MONTH, +1);
				    	d.add(Calendar.DATE, -1);
				    	e.setTime(d.getTime());
				    	time2 = c.getTime();
				    	time3 = e.getTime();
				    	bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,time2,time3,null,null,null,null);
			        	
			        	c.add(Calendar.MONTH, +1);
			        	for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			    			if(bikeRentInfo.getRentInsurancePrice()!=null&&!bikeRentInfo.getRentInsurancePrice().equals("")){
			    				sumMoney = sumMoney.add(bikeRentInfo.getRentInsurancePrice());
			    				increase = increase.add(bikeRentInfo.getRentInsurancePrice());
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumMoney", sumMoney);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
		            
		            
			        
		        }
		        if(time==4){
		        	//过去一年
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		        	Date time3 = null;
		            c.setTime(format1.parse(format1.format(new Date())));
		            c.add(Calendar.MONTH, -12);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        Calendar e = Calendar.getInstance();
			        BigDecimal sumMoney =  new BigDecimal("0.00");
			        
			        for(int i=0;i<13;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	d.add(Calendar.MONTH, +1);
			        	d.add(Calendar.DATE, -1);
			        	e.setTime(d.getTime());
			        	time2 = c.getTime();
			        	time3 = e.getTime();
			        	c.add(Calendar.MONTH, +1);
			        	bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,time2,time3,null,null,null,null);
			        	
			        	for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			    			if(bikeRentInfo.getRentInsurancePrice()!=null&&!bikeRentInfo.getRentInsurancePrice().equals("")){
			    				sumMoney = sumMoney.add(bikeRentInfo.getRentInsurancePrice());
			    				increase = increase.add(bikeRentInfo.getRentInsurancePrice());
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumMoney", sumMoney);
	    				jsonArray.add(jsonObject);//添加json
						
			        }
			        map.put("jsonArray", jsonArray);
			        
		            
			        
		        }
		        bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,null,null,null,null,null,null);
		        
			}
			else{
				bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,null,null,null,null,null,null);
				
			}
			todaybikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,today,today,null,null,null,null);
			BigDecimal sumMoney = new BigDecimal("0.00") ;
			for(BikeRentInfo bikeRentInfo :bikeRentInfos){
				if(bikeRentInfo.getRentInsurancePrice()!=null&&!bikeRentInfo.getRentInsurancePrice().equals("")){
					sumMoney = sumMoney.add(bikeRentInfo.getRentInsurancePrice());
				}
				
			}
			BigDecimal todaysumMoney = new BigDecimal("0.00") ;
			for(BikeRentInfo bikeRentInfo :todaybikeRentInfos){
				if(bikeRentInfo.getRentInsurancePrice()!=null&&!bikeRentInfo.getRentInsurancePrice().equals("")){
					todaysumMoney = todaysumMoney.add(bikeRentInfo.getRentInsurancePrice());
				}
				
			}
			map.put("todaysumMoney", todaysumMoney);
			map.put("sumMoney", sumMoney);
		} catch (Exception e) {
			loggers.error("异常信息",e);
		}
		
		return map;
	}
	
	/**
	 * 租赁消费
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rent")
	public @ResponseBody Map<String, Object> rent(String fromtime,String totime,Integer time,String str)throws Exception {
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
			
			List<BikeRentInfo> bikeRentInfos = new ArrayList<BikeRentInfo>();
			List<BikeRentInfo> todaybikeRentInfos = new ArrayList<BikeRentInfo>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			String date1 = format.format(date); 
			Date today = format.parse(date1);
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
				        
						BigDecimal sumMoney =  new BigDecimal("0.00");
						for(int i=0;i<=x;i++){
							
					        
							   BigDecimal increase =  new BigDecimal("0.00");
							    time1 = c.getTime();
							    bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,time1,time1,null,null,null,null);
							    for(BikeRentInfo bikeRentInfo :bikeRentInfos){
					    			if(bikeRentInfo.getRentPrice()!=null&&!bikeRentInfo.getRentPrice().equals("")){
					    				sumMoney = sumMoney.add(bikeRentInfo.getRentPrice());
					    				increase = increase.add(bikeRentInfo.getRentPrice());
					    			}
					    			
					    		}
					        	c.add(Calendar.DATE, +1);
					        	
					        	
					        	JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period",format.format(time1));
								jsonObject.put("increase", increase);
								jsonObject.put("sumMoney", sumMoney);
			    				jsonArray.add(jsonObject);//添加json
					            
					        
				        	
				        }
						map.put("jsonArray", jsonArray);
						
				
				
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
			        BigDecimal sumMoney =  new BigDecimal("0.00");
			        
			        
			        for(int i=0;i<8;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	time2 = d.getTime();
			        	bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,time2,time2,null,null,null,null);
			        	c.add(Calendar.DATE, +1);
			        	
			        	for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			    			if(bikeRentInfo.getRentPrice()!=null&&!bikeRentInfo.getRentPrice().equals("")){
			    				sumMoney = sumMoney.add(bikeRentInfo.getRentPrice());
			    				increase = increase.add(bikeRentInfo.getRentPrice());
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumMoney", sumMoney);
						
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
			        
			        
		        }
		        if(time==2){
		        	//过去一月
		        	SimpleDateFormat format2 = new SimpleDateFormat("MM-dd"); 
		        	c.setTime(format.parse(format.format(new Date())));
		        	c.add(Calendar.DATE, -30);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
		            BigDecimal sumMoney =  new BigDecimal("0.00");
		            for(int i=0;i<31;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	
			        	
			        	time2 = d.getTime();
			        	
			        	c.add(Calendar.DATE, +1);
			        	
			        	bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,time2,time2,null,null,null,null);
			        	for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			    			if(bikeRentInfo.getRentPrice()!=null&&!bikeRentInfo.getRentPrice().equals("")){
			    				sumMoney = sumMoney.add(bikeRentInfo.getRentPrice());
			    				increase = increase.add(bikeRentInfo.getRentPrice());
			    			}
			    			
			    		}
			        	
			        	
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format2.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumMoney", sumMoney);
						
	    				jsonArray.add(jsonObject);//添加json
			        }
		            map.put("jsonArray", jsonArray);
		            
			        
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
			        BigDecimal sumMoney =  new BigDecimal("0.00");
			       
			        
			        for(int i=0;i<4;i++){
			        	
			        	
			        	BigDecimal increase =  new BigDecimal("0.00");
						d.setTime(c.getTime());
				    	d.add(Calendar.MONTH, +1);
				    	d.add(Calendar.DATE, -1);
				    	e.setTime(d.getTime());
				    	time2 = c.getTime();
				    	time3 = e.getTime();
				    	bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,time2,time3,null,null,null,null);
			        	
			        	c.add(Calendar.MONTH, +1);
			        	for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			    			if(bikeRentInfo.getRentPrice()!=null&&!bikeRentInfo.getRentPrice().equals("")){
			    				sumMoney = sumMoney.add(bikeRentInfo.getRentPrice());
			    				increase = increase.add(bikeRentInfo.getRentPrice());
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumMoney", sumMoney);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
		            
			        
		        }
		        if(time==4){
		        	//过去一年
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		        	Date time3 = null;
		            c.setTime(format1.parse(format1.format(new Date())));
		            c.add(Calendar.MONTH, -12);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        Calendar e = Calendar.getInstance();
			        BigDecimal sumMoney =  new BigDecimal("0.00");
			        
			        for(int i=0;i<13;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	d.add(Calendar.MONTH, +1);
			        	d.add(Calendar.DATE, -1);
			        	e.setTime(d.getTime());
			        	time2 = c.getTime();
			        	time3 = e.getTime();
			        	c.add(Calendar.MONTH, +1);
			        	bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,time2,time3,null,null,null,null);
			        	
			        	for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			    			if(bikeRentInfo.getRentPrice()!=null&&!bikeRentInfo.getRentPrice().equals("")){
			    				sumMoney = sumMoney.add(bikeRentInfo.getRentPrice());
			    				increase = increase.add(bikeRentInfo.getRentPrice());
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumMoney", sumMoney);
	    				jsonArray.add(jsonObject);//添加json
						
			        }
			        map.put("jsonArray", jsonArray);
			        
		            
			        
		        }
		        bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,null,null,null,null,null,null);
		        
			}
			else{
				bikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,null,null,null,null,null,null);
				
			}
			todaybikeRentInfos = bikeRentInfoServiceReport.findNewBikeRentInfos(channelIdList,null,today,today,null,null,null,null);
			BigDecimal sumMoney = new BigDecimal("0.00") ;
			for(BikeRentInfo bikeRentInfo :bikeRentInfos){
				if(bikeRentInfo.getRentPrice()!=null&&!bikeRentInfo.getRentPrice().equals("")){
					sumMoney = sumMoney.add(bikeRentInfo.getRentPrice());
				}
				
			}
			BigDecimal todaysumMoney = new BigDecimal("0.00") ;
			for(BikeRentInfo bikeRentInfo :todaybikeRentInfos){
				if(bikeRentInfo.getRentPrice()!=null&&!bikeRentInfo.getRentPrice().equals("")){
					sumMoney = sumMoney.add(bikeRentInfo.getRentPrice());
				}
				
			}
			map.put("todaysumMoney", todaysumMoney);
			map.put("sumMoney", sumMoney);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return map;
	}
	
	/**
	 * 预付款充值
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deposit")
	public @ResponseBody Map<String, Object> deposit(String fromtime,String totime,Integer time,String str)throws Exception {
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
			
			List<MoneyLog> moneyLogList = new ArrayList<MoneyLog>();
			List<MoneyLog> todaymoneyLogList = new ArrayList<MoneyLog>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			String date1 = format.format(date); 
			Date today = format.parse(date1);
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
				        
						BigDecimal totalbuchong =  new BigDecimal("0.00");
						for(int i=0;i<=x;i++){
							
					        
							   BigDecimal increase =  new BigDecimal("0.00");
							    time1 = c.getTime();
							    
							    moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 3, null, null, time1, time1, null);
							    for(MoneyLog moneyLog :moneyLogList){
					    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					    				 if(moneyLog.getMoneyLogStreamType()==3){
					    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
					    					 totalbuchong = totalbuchong.add(moneyLog.getMoneyLogOpreateMoney());
					    					 
					    				}
					    			}
					    			
					    		}
					        	c.add(Calendar.DATE, +1);
					        	
					        	
					        	JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period",format.format(time1));
								jsonObject.put("increase", increase);
								jsonObject.put("totalbuchong", totalbuchong);
			    				jsonArray.add(jsonObject);//添加json
					            
					        
				        	
				        }
						map.put("jsonArray", jsonArray);
						
				
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
			        BigDecimal totalbuchong =  new BigDecimal("0.00");
			        
			        
			        for(int i=0;i<8;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	time2 = d.getTime();
			        	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time2, null);
			        	c.add(Calendar.DATE, +1);
			        	
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==3){
			    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					 totalbuchong = totalbuchong.add(moneyLog.getMoneyLogOpreateMoney());
			    					 
			    				}
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbuchong", totalbuchong);
						
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
			        
			        
			        
		        }
		        if(time==2){
		        	//过去一月
	                SimpleDateFormat format2 = new SimpleDateFormat("MM-dd"); 
		        	c.setTime(format.parse(format.format(new Date())));
		        	c.add(Calendar.DATE, -30);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        BigDecimal totalbuchong =  new BigDecimal("0.00");
			        for(int i=0;i<31;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	
			        	
			        	time2 = d.getTime();
			        	
			        	c.add(Calendar.DATE, +1);
			        	
			        	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time2, null);
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==3){
			    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					 totalbuchong = totalbuchong.add(moneyLog.getMoneyLogOpreateMoney());
			    					 
			    				}
			    			}
			    			
			    		}
			        	
			        	
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format2.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbuchong", totalbuchong);
						
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
		            
			        
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
			        BigDecimal totalbuchong =  new BigDecimal("0.00");
			        
			        
			        for(int i=0;i<4;i++){
			        	
			        	
			        	BigDecimal increase =  new BigDecimal("0.00");
						d.setTime(c.getTime());
				    	d.add(Calendar.MONTH, +1);
				    	d.add(Calendar.DATE, -1);
				    	e.setTime(d.getTime());
				    	time2 = c.getTime();
				    	time3 = e.getTime();
				    	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time3, null);
			        	
			        	c.add(Calendar.MONTH, +1);
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==3){
			    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					 totalbuchong = totalbuchong.add(moneyLog.getMoneyLogOpreateMoney());
			    					 
			    				}
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbuchong", totalbuchong);
						
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
		            
		            
			        
		        }
		        if(time==4){
		        	//过去一年
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		        	Date time3 = null;
		            c.setTime(format1.parse(format1.format(new Date())));
		            c.add(Calendar.MONTH, -12);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        Calendar e = Calendar.getInstance();
			        BigDecimal totalbuchong =  new BigDecimal("0.00");
			        for(int i=0;i<13;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	d.add(Calendar.MONTH, +1);
			        	d.add(Calendar.DATE, -1);
			        	e.setTime(d.getTime());
			        	time2 = c.getTime();
			        	time3 = e.getTime();
			        	c.add(Calendar.MONTH, +1);
			        	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time3, null);
			        	
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==3){
			    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					 totalbuchong = totalbuchong.add(moneyLog.getMoneyLogOpreateMoney());
			    					 
			    				}
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbuchong", totalbuchong);
	    				jsonArray.add(jsonObject);//添加json
						
			        }
			        map.put("jsonArray", jsonArray);
		            
			        
		        }
		        moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 3, null, null, null, null, null);
		        
			}
			else{
				moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 3, null, null, null, null, null);
				
			}
			todaymoneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 3, null, null, today, today, null);
			BigDecimal sumBuchongMoney = new BigDecimal("0.00");
	        for(MoneyLog moneyLog :moneyLogList){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					 if(moneyLog.getMoneyLogStreamType()==3){
						sumBuchongMoney = sumBuchongMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}
				}
				
			}
	        BigDecimal toadysumBuchongMoney = new BigDecimal("0.00");
	        for(MoneyLog moneyLog :todaymoneyLogList){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					 if(moneyLog.getMoneyLogStreamType()==3){
						 toadysumBuchongMoney = toadysumBuchongMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}
				}
				
			}
			map.put("sumBuchongMoney", sumBuchongMoney);
			map.put("toadysumBuchongMoney", toadysumBuchongMoney);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return map;
	}
	
	/**
	 * 余额充值
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("balance")
	public @ResponseBody Map<String, Object> balance(String fromtime,String totime,Integer time,String str)throws Exception {
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
			
			List<MoneyLog> moneyLogList = new ArrayList<MoneyLog>();
			List<MoneyLog> todaymoneyLogList = new ArrayList<MoneyLog>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			String date1 = format.format(date); 
			Date today = format.parse(date1);
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
				        
						BigDecimal totalbalance =  new BigDecimal("0.00");
						for(int i=0;i<=x;i++){
							
					        
							   BigDecimal increase =  new BigDecimal("0.00");
							    time1 = c.getTime();
							    
							    moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 4, null, null, time1, time1, null);
							    for(MoneyLog moneyLog :moneyLogList){
					    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					    				 if(moneyLog.getMoneyLogStreamType()==4){
					    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
					    					 totalbalance = totalbalance.add(moneyLog.getMoneyLogOpreateMoney());
					    					 
					    				}
					    			}
					    			
					    		}
					        	c.add(Calendar.DATE, +1);
					        	
					        	
					        	JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period",format.format(time1));
								jsonObject.put("increase", increase);
								jsonObject.put("totalbalance", totalbalance);
			    				jsonArray.add(jsonObject);//添加json
					            
					        
				        	
				        }
						map.put("jsonArray", jsonArray);
						
				
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
			        BigDecimal totalbalance =  new BigDecimal("0.00");
			        
			        
			        for(int i=0;i<8;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	time2 = d.getTime();
			        	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time2, null);
			        	c.add(Calendar.DATE, +1);
			        	
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==4){
			    					 increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					 totalbalance = totalbalance.add(moneyLog.getMoneyLogOpreateMoney());
			    					 
			    				}
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbalance", totalbalance);
						
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
			        
			        
			        
		        }
		        if(time==2){
		        	//过去一月
		        	SimpleDateFormat format2 = new SimpleDateFormat("MM-dd"); 
		        	c.setTime(format.parse(format.format(new Date())));
		        	c.add(Calendar.DATE, -30);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        BigDecimal totalbalance =  new BigDecimal("0.00");
			        for(int i=0;i<31;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	
			        	
			        	time2 = d.getTime();
			        	
			        	c.add(Calendar.DATE, +1);
			        	
			        	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time2, null);
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==4){
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					totalbalance = totalbalance.add(moneyLog.getMoneyLogOpreateMoney());
			    				}
			    			}
			    			
			    		}
			        	
			        	
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format2.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbalance", totalbalance);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
		            
			        
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
			        
			        BigDecimal totalbalance =  new BigDecimal("0.00");
			        
			        for(int i=0;i<4;i++){
			        	
			        	
			        	BigDecimal increase =  new BigDecimal("0.00");
						d.setTime(c.getTime());
				    	d.add(Calendar.MONTH, +1);
				    	d.add(Calendar.DATE, -1);
				    	e.setTime(d.getTime());
				    	time2 = c.getTime();
				    	time3 = e.getTime();
				    	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time3, null);
			        	
			        	c.add(Calendar.MONTH, +1);
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				  if(moneyLog.getMoneyLogStreamType()==4){
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					totalbalance = totalbalance.add(moneyLog.getMoneyLogOpreateMoney());
			    				}
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbalance", totalbalance);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
		            
		            
			        
		        }
		        if(time==4){
		        	//过去一年
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		        	Date time3 = null;
		            c.setTime(format1.parse(format1.format(new Date())));
		            c.add(Calendar.MONTH, -12);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        Calendar e = Calendar.getInstance();
			        BigDecimal totalbalance =  new BigDecimal("0.00");
			        for(int i=0;i<13;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	d.add(Calendar.MONTH, +1);
			        	d.add(Calendar.DATE, -1);
			        	e.setTime(d.getTime());
			        	time2 = c.getTime();
			        	time3 = e.getTime();
			        	c.add(Calendar.MONTH, +1);
			        	moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, null, null, null, time2, time3, null);
			        	
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    				 if(moneyLog.getMoneyLogStreamType()==4){
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    					totalbalance = totalbalance.add(moneyLog.getMoneyLogOpreateMoney());
			    				}
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("totalbalance", totalbalance);
	    				jsonArray.add(jsonObject);//添加json
						
			        }
			        map.put("jsonArray", jsonArray);
		            
			        
		        }
		        moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 4, null, null, null, null, null);
		        
			}
			else{
				moneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 4, null, null, null, null, null);
				
			}
			todaymoneyLogList = moneyLogServiceReport.findMoneyLog(null,channelIdList, 4, null, null, today, today, null);
			BigDecimal sumBalanceMoney = new BigDecimal("0.00");
	        for(MoneyLog moneyLog :moneyLogList){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					 if(moneyLog.getMoneyLogStreamType()==4){
						 sumBalanceMoney = sumBalanceMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}
				}
				
			}
	        BigDecimal toadysumBalanceMoney = new BigDecimal("0.00");
	        for(MoneyLog moneyLog :todaymoneyLogList){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					 if(moneyLog.getMoneyLogStreamType()==4){
						 toadysumBalanceMoney = toadysumBalanceMoney.add(moneyLog.getMoneyLogOpreateMoney());
					}
				}
				
			}
			map.put("sumBalanceMoney", sumBalanceMoney);
			map.put("toadysumBalanceMoney", toadysumBalanceMoney);
		} catch (Exception e) {
			loggers.error("异常信息",e);
		}
		
		return map;
	}
	
	/**
	 * 预付款退款
	 * @param fromtime
	 * @param totime
	 * @param time
	 * @param str
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("refund")
	public @ResponseBody Map<String, Object> rdfund(String fromtime,String totime,Integer time,String str)throws Exception {
		
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
			
			List<MoneyLog> moneyLogList = new ArrayList<MoneyLog>();
			List<MoneyLog> todaymoneyLogList = new ArrayList<MoneyLog>();
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			String date1 = format.format(date); 
			Date today = format.parse(date1);
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
				        
						BigDecimal sumRefundMoney =  new BigDecimal("0.00");
						for(int i=0;i<=x;i++){
							
					        
							   BigDecimal increase =  new BigDecimal("0.00");
							    time1 = c.getTime();
							    
							    moneyLogList = moneyLogServiceReport.moneyLogList(null, null, null, time1, time1,null,channelIdList);
							    for(MoneyLog moneyLog :moneyLogList){
					    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
					    					sumRefundMoney = sumRefundMoney.add(moneyLog.getMoneyLogOpreateMoney());
					    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
					    			}
					    			
					    		}
					        	c.add(Calendar.DATE, +1);
					        	
					        	
					        	JSONObject jsonObject = new JSONObject();
					        	jsonObject.put("period",format.format(time1));
								jsonObject.put("increase", increase);
								jsonObject.put("sumRefundMoney", sumRefundMoney);
			    				jsonArray.add(jsonObject);//添加json
					            
					        
				        	
				        }
						map.put("jsonArray", jsonArray);
						
				
				
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
			        BigDecimal sumRefundMoney = new BigDecimal("0.00");
			        
			        
			        for(int i=0;i<8;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	time2 = d.getTime();
			        	 moneyLogList = moneyLogServiceReport.moneyLogList(null, null, null, time2, time2,null,channelIdList);
			        	c.add(Calendar.DATE, +1);
			        	
			        	
			    		for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    					sumRefundMoney = sumRefundMoney.add(moneyLog.getMoneyLogOpreateMoney());
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumRefundMoney", sumRefundMoney);
						
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
			        
			        
			        
		        }
		        if(time==2){
		        	//过去一月
		        	SimpleDateFormat format2 = new SimpleDateFormat("MM-dd"); 
		        	c.setTime(format.parse(format.format(new Date())));
		        	c.add(Calendar.DATE, -30);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        BigDecimal sumRefundMoney =  new BigDecimal("0.00");
			        for(int i=0;i<31;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	
			        	
			        	time2 = d.getTime();
			        	
			        	c.add(Calendar.DATE, +1);
			        	
			        	moneyLogList = moneyLogServiceReport.moneyLogList(null, null, null, time2, time2,null,channelIdList);
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    					sumRefundMoney = sumRefundMoney.add(moneyLog.getMoneyLogOpreateMoney());
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    			}
			    			
			    		}
			        	
			        	
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format2.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumRefundMoney", sumRefundMoney);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
		            
		            
			        
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
			        
			        BigDecimal sumRefundMoney =  new BigDecimal("0.00");
			        
			        for(int i=0;i<4;i++){
			        	
			        	
			        	BigDecimal increase =  new BigDecimal("0.00");
						d.setTime(c.getTime());
				    	d.add(Calendar.MONTH, +1);
				    	e.setTime(d.getTime());
				    	time2 = c.getTime();
				    	time3 = e.getTime();
				    	moneyLogList = moneyLogServiceReport.moneyLogList(null, null, null, time2, time3,null,channelIdList);
			        	
			        	c.add(Calendar.MONTH, +1);
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    					sumRefundMoney = sumRefundMoney.add(moneyLog.getMoneyLogOpreateMoney());
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumRefundMoney", sumRefundMoney);
	    				jsonArray.add(jsonObject);//添加json
			        }
			        map.put("jsonArray", jsonArray);
		            
			        
		        }
		        if(time==4){
		        	//过去一年
		        	SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM");
		        	Date time3 = null;
		            c.setTime(format1.parse(format1.format(new Date())));
		            c.add(Calendar.MONTH, -12);
		            time1 = c.getTime();
			        Calendar d = Calendar.getInstance();
			        Calendar e = Calendar.getInstance();
			        BigDecimal sumRefundMoney =  new BigDecimal("0.00");
			        for(int i=0;i<13;i++){
			        	BigDecimal increase =  new BigDecimal("0.00");
			        	d.setTime(c.getTime());
			        	d.add(Calendar.MONTH, +1);
			        	e.setTime(d.getTime());
			        	time2 = c.getTime();
			        	time3 = e.getTime();
			        	c.add(Calendar.MONTH, +1);
			        	moneyLogList = moneyLogServiceReport.moneyLogList(null, null, null, time2, time3,null,channelIdList);
			        	
			        	for(MoneyLog moneyLog :moneyLogList){
			    			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
			    					sumRefundMoney = sumRefundMoney.add(moneyLog.getMoneyLogOpreateMoney());
			    					increase = increase.add(moneyLog.getMoneyLogOpreateMoney());
			    			}
			    			
			    		}
			        	
			        	JSONObject jsonObject = new JSONObject();
			        	jsonObject.put("period",format1.format(time2));
						jsonObject.put("increase", increase);
						jsonObject.put("sumRefundMoney", sumRefundMoney);
	    				jsonArray.add(jsonObject);//添加json
						
			        }
			        map.put("jsonArray", jsonArray);
		            
		            
			        
		        }
		        moneyLogList = moneyLogServiceReport.moneyLogList(null, null, null, null, null,null,channelIdList);
		        
			}
			else{
				moneyLogList = moneyLogServiceReport.moneyLogList(null, null, null, null, null,null,channelIdList);
				
			}
			todaymoneyLogList = moneyLogServiceReport.moneyLogList(null, null, null, today, today,null,channelIdList);
			BigDecimal sumRefundMoney = new BigDecimal("0.00");
			for(MoneyLog moneyLog :moneyLogList){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
						sumRefundMoney = sumRefundMoney.add(moneyLog.getMoneyLogOpreateMoney());
				}
				
			}
			BigDecimal todaysumRefundMoney = new BigDecimal("0.00");
			for(MoneyLog moneyLog :todaymoneyLogList){
				if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
						sumRefundMoney = sumRefundMoney.add(moneyLog.getMoneyLogOpreateMoney());
				}
				
			}
			map.put("sumRefundMoney", sumRefundMoney);
			map.put("todaysumRefundMoney", todaysumRefundMoney);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		return map;
	}


}
