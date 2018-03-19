package org.controller.cms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.entity.dto.Admin;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.Channel;
import org.entity.dto.MoneyLog;
import org.entity.dto.OperateLog;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeRentInfoServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.MoneyLogServiceRead;
import org.service.cms.write.OperateServiceWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.OperateUtil;
import org.util.redis.RedisService;

@Controller
@Scope("prototype")
@RequestMapping("cms/moneyWater")
public class MoneyRecordController {

	@Resource
	BikeRentInfoServiceRead bikeRentInfoServiceRead;
	
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	MoneyLogServiceRead moneyLogServiceRead;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	@Autowired
	RedisService redisService;
	
	/**
	 * 租赁流水
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
	public String rentMoneyWater(HttpSession session,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,Long channelId,Model model,Date returnFromTime,Date returnTotime,@RequestParam(defaultValue="1")Integer pageIndex,Integer moneyNum) throws Exception{
		Admin admin  = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		
		Long currChannelId = (Long) session.getAttribute("currChannelId");
		if(1 == flag){
			pageIndex = null;
		}
		List<BikeRentInfo> bikeRentInfos = new ArrayList<BikeRentInfo>();
		Integer totalPage = 1;
		List<Channel> channelList = new ArrayList<Channel>();
    	if(currChannelId==null){
    		bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,pageIndex,returnFromTime,returnTotime,moneyNum,null);
    		totalPage = bikeRentInfoServiceRead.countBikeRentInfos(channelId,returnFromTime,returnTotime,moneyNum,null);
    		channelList = channelServiceRead.findAllChannelByChannelIds(null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,pageIndex,returnFromTime,returnTotime,moneyNum,currChannelIds);
			totalPage = bikeRentInfoServiceRead.countBikeRentInfos(channelId,returnFromTime,returnTotime,moneyNum,currChannelIds);
			channelList = channelServiceRead.findAllChannelByChannelIds(currChannelIds);
		}
    	if(totalPage==0){
        	totalPage = 1;
        }
		model.addAttribute("bikeRentInfos", bikeRentInfos);
		model.addAttribute("channelList", channelList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("returnFromTime", returnFromTime);
		model.addAttribute("returnTotime", returnTotime);
		model.addAttribute("channelId", channelId);
		model.addAttribute("moneyNum", moneyNum);
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=rent_excel_"+DateUtil.formatDay(new Date())+".xls");
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			String remark = OperateUtil.operateMoneyWater(nowAdmin.getAdminRealname(), 1);
			operateLog.setOperateRemark(remark);
			operateServiceWrite.addOperateLogs(operateLog);//操作日志
			return "rent_account_excel_list";
		}
		return "rent_account_list";
	}
	
	/**
	 * 充值退款流水
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
	public String moneyLogList(HttpSession session,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,Integer item,Model model,Date FromTime,Date ToTime,@RequestParam(defaultValue="1")Integer pageIndex) throws Exception{
		Admin adminsession  = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(adminsession.getAdminId());
		
		if(1 == flag){
			pageIndex = null;
		}
		List<MoneyLog> moneyLogs = moneyLogServiceRead.moneyLogList(pageIndex, item, FromTime, ToTime);
		
		for(MoneyLog m : moneyLogs){
			Admin admin = adminServiceRead.findAdminId(m.getMoneyLogRefundOpreate());
			m.setAdmin(admin);
		}
		
		Integer totalPage = moneyLogServiceRead.countMoneyLog(item, FromTime, ToTime);
		if(totalPage==0){
        	totalPage = 1;
        }
		model.addAttribute("moneyLogs", moneyLogs);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("item", item);
		model.addAttribute("FromTime", FromTime);
		model.addAttribute("ToTime", ToTime);
		model.addAttribute("pageIndex", pageIndex);
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=moneylog_excel_"+DateUtil.formatDay(new Date())+".xls");
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			String remark = OperateUtil.operateMoneyWater(nowAdmin.getAdminRealname(), 2);
			operateLog.setOperateRemark(remark);
			operateServiceWrite.addOperateLogs(operateLog);//操作日志
			return "money_log_excel_list";
		}
		return "money_log_list";
	}
	
	/**
	 * 保险费用流水
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
	public String insuranceLogList(HttpSession session,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,Long channelId,Model model,Date FromTime,Date ToTime,@RequestParam(defaultValue="1")Integer pageIndex) throws Exception{
		Admin admin  = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		
		Long currChannelId = (Long) session.getAttribute("currChannelId");
		
		if(1 == flag){
			pageIndex = null;
		}
		List<BikeRentInfo> bikeRentInfos = new ArrayList<BikeRentInfo>();
		Integer totalPage = 1;
		List<Channel> channelList = new ArrayList<Channel>();
    	if(currChannelId==null){
    		bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,pageIndex,FromTime,ToTime,null,null);
    		totalPage = bikeRentInfoServiceRead.countBikeRentInfos(channelId,FromTime,ToTime,null,null);
    		channelList = channelServiceRead.findAllChannelByChannelIds(null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,pageIndex,FromTime,ToTime,null,currChannelIds);
			totalPage = bikeRentInfoServiceRead.countBikeRentInfos(channelId,FromTime,ToTime,null,currChannelIds);
			channelList = channelServiceRead.findAllChannelByChannelIds(currChannelIds);
		}
    	if(totalPage==0){
        	totalPage = 1;
        }
		model.addAttribute("bikeRentInfos", bikeRentInfos);
		model.addAttribute("channelList", channelList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("FromTime", FromTime);
		model.addAttribute("ToTime", ToTime);
		model.addAttribute("channelId", channelId);
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=insurance_excel_"+DateUtil.formatDay(new Date())+".xls");
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			String remark = OperateUtil.operateMoneyWater(nowAdmin.getAdminRealname(), 3);
			operateLog.setOperateRemark(remark);
			operateServiceWrite.addOperateLogs(operateLog);//操作日志
			return "insurance_log_excel_list";
		}
		return "insurance_log_list";
	}
	
	/**
	 * 租赁总结算流水
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
	@RequestMapping("rentCountMoneyWater")
	public String rentCountMoneyWater(HttpSession session,HttpServletResponse response,@RequestParam(defaultValue="0")Integer flag,Long channelId,Model model,Date returnFromTime,Date returnTotime,@RequestParam(defaultValue="1")Integer pageIndex) throws Exception{
		Admin admin  = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		
		Long currChannelId = (Long) session.getAttribute("currChannelId");
		if(1 == flag){
			pageIndex = null;
		}
		List<BikeRentInfo> bikeRentInfos = new ArrayList<BikeRentInfo>();
		Integer totalPage = 1;
		List<Channel> channelList = new ArrayList<Channel>();
    	if(currChannelId==null){
    		bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,pageIndex,returnFromTime,returnTotime,null,null);
    		totalPage = bikeRentInfoServiceRead.countBikeRentInfos(channelId,returnFromTime,returnTotime,null,null);
    		channelList = channelServiceRead.findAllChannelByChannelIds(null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,pageIndex,returnFromTime,returnTotime,null,currChannelIds);
			totalPage = bikeRentInfoServiceRead.countBikeRentInfos(channelId,returnFromTime,returnTotime,null,currChannelIds);
			channelList = channelServiceRead.findAllChannelByChannelIds(currChannelIds);
		}
    	if(totalPage==0){
        	totalPage = 1;
        }
		model.addAttribute("bikeRentInfos", bikeRentInfos);
		model.addAttribute("channelList", channelList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("returnFromTime", returnFromTime);
		model.addAttribute("returnTotime", returnTotime);
		model.addAttribute("channelId", channelId);
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=rent_excel_"+DateUtil.formatDay(new Date())+".xls");
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			String remark = OperateUtil.operateMoneyWater(nowAdmin.getAdminRealname(), 1);
			operateLog.setOperateRemark(remark);
			operateServiceWrite.addOperateLogs(operateLog);//操作日志
			return "count_rent_money_excel_list";
		}
		return "count_rent_money_list";
	}
	
	/**
	 * 租赁费用合计
	 * @param session
	 * @param channelId
	 * @param returnFromTime
	 * @param returnTotime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sumRentMoney")
    public @ResponseBody BigDecimal sumRentMoney(HttpSession session,Long channelId,Date returnFromTime,Date returnTotime,Integer moneyNum) throws Exception{
		Long currChannelId = (Long) session.getAttribute("currChannelId");
		List<BikeRentInfo> bikeRentInfos = new ArrayList<BikeRentInfo>();
		if(currChannelId==null){
			bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,null,returnFromTime,returnTotime,moneyNum,null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,null,returnFromTime,returnTotime,moneyNum,currChannelIds);
		}
		BigDecimal sumMoney = new BigDecimal("0.00") ;
		for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			if(bikeRentInfo.getRentPrice()!=null&&!bikeRentInfo.getRentPrice().equals("")){
				sumMoney = sumMoney.add(bikeRentInfo.getRentPrice());
			}
			
		}
		return sumMoney;
	}
	
	/**
	 * 充值退款合计
	 * @param item
	 * @param FromTime
	 * @param ToTime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sumTotalMoney")
    public @ResponseBody String sumTotalMoney(Integer item,Date FromTime,Date ToTime) throws Exception{
		List<MoneyLog> moneyLogs = moneyLogServiceRead.moneyLogList(null, item, FromTime, ToTime);
		BigDecimal sumBuchongMoney = new BigDecimal("0.00");
		BigDecimal sumRefundMoney = new BigDecimal("0.00");
		BigDecimal sumBalanceMoney = new BigDecimal("0.00");
		for(MoneyLog moneyLog :moneyLogs){
			if(moneyLog.getMoneyLogOpreateMoney()!=null&&!moneyLog.getMoneyLogOpreateMoney().equals("")){
				if(moneyLog.getMoneyLogStreamType()==2){
					sumRefundMoney = sumRefundMoney.subtract(moneyLog.getMoneyLogOpreateMoney());
				}else if(moneyLog.getMoneyLogStreamType()==3){
					sumBuchongMoney = sumBuchongMoney.add(moneyLog.getMoneyLogOpreateMoney());
				}else if(moneyLog.getMoneyLogStreamType()==4){
					sumBalanceMoney = sumBalanceMoney.add(moneyLog.getMoneyLogOpreateMoney());
				}
			}
			
		}
		return sumBuchongMoney+","+sumRefundMoney+","+sumBalanceMoney;
	}
	
	/**
	 * 保险费用合计
	 * @param session
	 * @param channelId
	 * @param returnFromTime
	 * @param returnTotime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sumInsuranceMoney")
    public @ResponseBody BigDecimal sumInsuranceMoney(HttpSession session,Long channelId,Date returnFromTime,Date returnTotime) throws Exception{
		Long currChannelId = (Long) session.getAttribute("currChannelId");
		List<BikeRentInfo> bikeRentInfos = new ArrayList<BikeRentInfo>();
		if(currChannelId==null){
			bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,null,returnFromTime,returnTotime,null,null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,null,returnFromTime,returnTotime,null,currChannelIds);
		}
		BigDecimal sumMoney = new BigDecimal("0.00") ;
		for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			if(bikeRentInfo.getRentInsurancePrice()!=null&&!bikeRentInfo.getRentInsurancePrice().equals("")){
				sumMoney = sumMoney.add(bikeRentInfo.getRentInsurancePrice());
			}
			
		}
		return sumMoney;
	}
	
	/**
	 * 租赁费用结算合计
	 * @param session
	 * @param channelId
	 * @param returnFromTime
	 * @param returnTotime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sumRentCountMoney")
    public @ResponseBody Map<String,Object> sumRentCountMoney(HttpSession session,Long channelId,Date returnFromTime,Date returnTotime) throws Exception{
		Long currChannelId = (Long) session.getAttribute("currChannelId");
		Map<String,Object> data = new HashMap<String,Object>();
		List<BikeRentInfo> bikeRentInfos = new ArrayList<BikeRentInfo>();
		if(currChannelId==null){
			bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,null,returnFromTime,returnTotime,null,null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfos(channelId,null,returnFromTime,returnTotime,null,currChannelIds);
		}
		BigDecimal sumRentMoney = new BigDecimal("0.00") ;//租赁费用
		BigDecimal sumInPriceMoney = new BigDecimal("0.00") ;//保险费用
		BigDecimal sumCouponMoney = new BigDecimal("0.00") ;//优惠金额
		for(BikeRentInfo bikeRentInfo :bikeRentInfos){
			if(bikeRentInfo.getRentPrice()!=null&&!bikeRentInfo.getRentPrice().equals("")){
				sumRentMoney = sumRentMoney.add(bikeRentInfo.getRentPrice());
			}
			if(bikeRentInfo.getRentInsurancePrice()!=null&&!bikeRentInfo.getRentInsurancePrice().equals("")){
				sumInPriceMoney = sumInPriceMoney.add(bikeRentInfo.getRentInsurancePrice());
			}
			if(bikeRentInfo.getRentCouponMoney()!=null&&!bikeRentInfo.getRentCouponMoney().equals("")){
				sumCouponMoney = sumCouponMoney.add(bikeRentInfo.getRentCouponMoney());
			}
			
		}
		data.put("sumRentMoney", sumRentMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		data.put("sumInPriceMoney", sumInPriceMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		data.put("sumCouponMoney", sumCouponMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		return data;
	}
	
	
}
