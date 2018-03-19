package org.controller.cms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.Bike;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.Channel;
import org.entity.dto.FixedReturn;
import org.entity.dto.Models;
import org.entity.dto.OperateLog;
import org.entity.dto.RentPrice;
import org.entity.dto.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeLockInfoServiceRead;
import org.service.cms.read.BikeRentInfoServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.BlockServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.FixedReturnServiceRead;
import org.service.cms.read.ModelsServiceRead;
import org.service.cms.read.RentPriceServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.BikeRentInfoServiceWrite;
import org.service.cms.write.BikeServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.UserServiceWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.OperateUtil;
import org.util.PageUtil;
import org.util.redis.RedisService;

/**
 * 租赁订单管理
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping("cms/order")
public class RentOrderController {
	
	@Resource
	BikeRentInfoServiceRead bikeRentInfoServiceRead;
	@Resource
	BikeRentInfoServiceWrite bikeRentInfoServiceWrite;
	@Resource
	AppConfig AppConfig;
	@Resource
	BikeServiceRead bikeServiceRead;
	@Resource	
	UserServiceRead userServiceRead;	
	@Resource
	ChannelServiceRead channelServiceRead;
	@Resource
	BlockServiceRead blockServiceRead;
	@Resource
	BikeServiceWrite bikeServiceWrite;
	@Resource
	UserServiceWrite userServiceWrite;
	@Resource
	BikeLockInfoServiceRead bikeLockInfoServiceRead;
	@Resource
	ModelsServiceRead modelsServiceRead;
	@Resource
	AdminServiceRead adminServiceRead;
	@Resource
	OperateServiceWrite operateServiceWrite;
	@Resource
	RentPriceServiceRead rentPriceServiceRead;
	@Resource
	FixedReturnServiceRead fixedReturnServiceRead;
	/**封装之后，实行单例模式，不用每次开关*/
	@Autowired  
    private RedisService redisService;
	/**
	 * 列表
	 * @param pageIndex
	 * @param model
	 * @param rentInfoId
	 * @param bikeId
	 * @param flag  0 租赁订单管理   1借车列表  2还车列表
	 * @param returnAdr
	 * @param rentState
	 * @param rentAdr
	 * @param rentTime
	 * @param returnTime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("orderList")
	public String orderList(@RequestParam(defaultValue = "1") Integer pageIndex, Model model,Long rentInfoId, HttpSession session,
			String bikeCode,Long rentStartFixedId,Integer rentState,Long rentEndFixedId,Date rentTime,Date rentTime2,Date returnTime,Date returnTime2,String userTel,Integer flag,Long channelid) throws Exception{
		//List<Long> returnBlockIds = null;
		//List<Long> rentBlockIds = null;
		//归还地点
		/*if(null!=returnLatLng&&!"".equals(returnLatLng)){
			String returnBlockCode=BlockUtil.getBlockCode5(returnLatLng);
			 returnBlockIds=blockServiceRead.findBlockIds(returnBlockCode);
			//如果有地址 但是blockid是null
			 if(null==returnBlockIds){
				 returnBlockIds=new ArrayList<Long>();
				 returnBlockIds.add((long) -1);
			 }
		}
		//租用地点
		if(null!=rentLatLng&&!"".equals(rentLatLng)){
			String rentBlockCode=BlockUtil.getBlockCode5(rentLatLng);
			 rentBlockIds = blockServiceRead.findBlockIds(rentBlockCode);
			 //如果有地址 但是blockid是null
			 if(null==rentBlockIds){
				 rentBlockIds=new ArrayList<Long>();
				 rentBlockIds.add((long) -1);
			 }
		}*/
		List<BikeRentInfo> rentInfoList = new ArrayList<BikeRentInfo>();
		//查渠道id
		Long channelId = (Long) session.getAttribute("currChannelId");
		List<Channel> channelList = new ArrayList<Channel>();
		Integer totalPage = 1;
		Integer totalCount = 0;
		if(channelId==null){
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
			//租赁 列表
			 rentInfoList= bikeRentInfoServiceRead.findAllBikeRentInfos(null,pageIndex,AppConfig.getPage_size_web(),userTel,rentInfoId,bikeCode,rentState,rentTime,rentTime2,returnTime,returnTime2,rentStartFixedId,rentEndFixedId,channelid,flag);
			//条数
			totalCount  = bikeRentInfoServiceRead.findAllBikeInfoCount(null,userTel,rentInfoId,bikeCode,rentState,rentTime,rentTime2,returnTime,returnTime2,rentStartFixedId,rentEndFixedId,channelid);
			 totalPage = PageUtil.getTotalPage(totalCount);
			if(0==totalPage){
				totalPage = 1;
			}
    	}
    	else{
    		Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
			rentInfoList = bikeRentInfoServiceRead.findAllBikeRentInfos(currChannelIds,pageIndex,AppConfig.getPage_size_web(),userTel,rentInfoId,bikeCode,rentState,rentTime,rentTime2,returnTime,returnTime2,rentStartFixedId,rentEndFixedId,channelid,flag);
			 totalCount  = bikeRentInfoServiceRead.findAllBikeInfoCount(currChannelIds,userTel,rentInfoId,bikeCode,rentState,rentTime,rentTime2,returnTime,returnTime2,rentStartFixedId,rentEndFixedId,channelid);
			 totalPage = PageUtil.getTotalPage(totalCount);
			if(0==totalPage){
				totalPage = 1;
			}
    	}
		
		for(BikeRentInfo bri : rentInfoList){
			if(null!=bri.getRentStartFixedId()&&0!=bri.getRentStartFixedId()){
				FixedReturn fiStartReturn = fixedReturnServiceRead.findFixedById(bri.getRentStartFixedId());
				bri.setStartFixedName(fiStartReturn.getFixedReturnName());
			}
			if(null!=bri.getRentEndFixedId()&&0!=bri.getRentEndFixedId()){
				FixedReturn fiEndReturn = fixedReturnServiceRead.findFixedById(bri.getRentEndFixedId());
				bri.setEndFixedName(fiEndReturn.getFixedReturnName());
			}
			
		}
		
		model.addAttribute("rentStartFixedId", rentStartFixedId);
		model.addAttribute("rentEndFixedId", rentEndFixedId);
		model.addAttribute("channelList", channelList);
		model.addAttribute("rentState", rentState);
		model.addAttribute("userTel", userTel);
		model.addAttribute("rentInfoList", rentInfoList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("rentTime", rentTime);
		model.addAttribute("rentTime2", rentTime2);
		model.addAttribute("returnTime", returnTime);
		model.addAttribute("returnTime2", returnTime2);
		model.addAttribute("rentInfoId", rentInfoId);
		model.addAttribute("bikeCode", bikeCode);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("flag", flag);
		model.addAttribute("channelid", channelid);
		switch (flag) {
		//租赁订单列表
		case 0:
			return "rentOrder_list";
		//借车列表
		case 1:
			return "rentBike_Info";
		//还车列表
		case 2:
			return "returnBike_Info";
		}
		return "";
	}
	
	/**
	 * 批量删除租赁信息
	 * @param model
	 * @param user
	 * @param rentInfoIds
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteOrderList")
	public String deleteOrderList(Model model,BikeRentInfo bikeRentInfo, Long[] rentInfoIds, HttpSession session,Integer flag,Integer rentState)
			throws Exception {
		if (null != rentInfoIds && 0 != rentInfoIds.length) {
			for (int i = 0; i < rentInfoIds.length; i++) {
				bikeRentInfo.setRentInfoId(rentInfoIds[i]);
				//软删除
				bikeRentInfo.setRentIsdel(1);
				bikeRentInfoServiceWrite.updateBikeRentInfo(bikeRentInfo);
			}
		} else
			//软删除
			bikeRentInfo.setRentIsdel(1);
			bikeRentInfoServiceWrite.updateBikeRentInfo(bikeRentInfo);
		if(null==rentState){
			return "redirect:orderList.action?flag="+flag;
		}
		return "redirect:orderList.action?flag="+flag+"&rentState="+rentState;
	}
	
	
	/**
	 * 删除订单
	 * @param rentInfoId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteOrder")
    public @ResponseBody Long deleteOrder(Long rentInfoId,HttpSession session) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");//获取admin
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		
		BikeRentInfo bikeRentInfo = bikeRentInfoServiceRead.findBikeRentInfoById(rentInfoId);
		bikeRentInfo.setRentIsdel(1);
		bikeRentInfoServiceWrite.updateBikeRentInfo(bikeRentInfo);
		
		OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.operateOrder(nowAdmin.getAdminRealname(), 2, rentInfoId.toString());
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
		
    	return rentInfoId;
    }
	/**
	 * 删除选中订单
	 * @param rentInfoId
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("deleteAllOrder")
    public @ResponseBody String deleteAllOrder(Long[] rentInfoIds,HttpSession session) throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");//获取admin
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
    	if(rentInfoIds!=null){
    		OperateLog operateLog = new OperateLog();
    		String rentInfoIdstr = "";
    		for(Long rentInfoId : rentInfoIds){
    			BikeRentInfo bikeRentInfo = bikeRentInfoServiceRead.findBikeRentInfoById(rentInfoId);
    			bikeRentInfo.setRentIsdel(1);
    			bikeRentInfoServiceWrite.updateBikeRentInfo(bikeRentInfo);
    			rentInfoIdstr = rentInfoIdstr +" "+rentInfoId;
    		}
    		
    		String remark = OperateUtil.operateOrder(nowAdmin.getAdminRealname(), 3, rentInfoIdstr);
    		operateLog.setOperateRemark(remark);
    		operateLog.setOperateTime(new Date());
    		operateLog.setOperateAdminId(nowAdmin.getAdminId());
    		operateServiceWrite.addOperateLogs(operateLog);
    	}
    	return "success";
    }
	
	
	/**
	 * 租赁详情
	 * @param rentId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rentDetail")
	public String  rentDetail(Long rentInfoId,Model model,Integer flag,Integer gotoFlag) throws Exception{
		BikeRentInfo bikeRentInfo = bikeRentInfoServiceRead.findBikeRentInfoById(rentInfoId);
		User user=userServiceRead.findById(bikeRentInfo.getRentInfoUserId());
		Bike bike = bikeServiceRead.findBikeByBikeId(bikeRentInfo.getRentInfoBikeId());
		BikeLockInfo bikeLockInfo = null;
		if(null!=bike.getBikeLockId()){
			bikeLockInfo = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
		}
		model.addAttribute("user", user);
		model.addAttribute("bikeLockInfo", bikeLockInfo);
		model.addAttribute("bike", bike);
		model.addAttribute("bikeRentInfo", bikeRentInfo);
		model.addAttribute("gotoFlag", gotoFlag);
		if(flag==1){
			//查看是用哪个租赁方案
//			RentPlan plan = rentPlanServiceRead.findPlanByRentPlanId(bikeRentInfo.getRentPlanId());
//			model.addAttribute("user", user);
//			model.addAttribute("rentPlan", plan);
			return "detail/rentOrder_detail";	
		}
		return "detail/bikeRentState_edit";
	}
		
	
	/**
	 * 1：上车位置 2：还车位置
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bikePosition")
	public String bikePosition(Model model,Integer flag,Long rentInfoId) throws Exception{
		BikeRentInfo bikeRentInfo = bikeRentInfoServiceRead.findBikeRentInfoById(rentInfoId);
		Bike bike = bikeServiceRead.findBikeByBikeId(bikeRentInfo.getRentInfoBikeId());
		model.addAttribute("bike", bike);
		model.addAttribute("bikeRentInfo", bikeRentInfo);
		model.addAttribute("flag", flag);
		return "detail/order_position";
	}

	
	
	
	
	/**
	 * 租赁费用
	 * @return
	 * @throws Exception
	 */
/*	@RequestMapping("rentMoney")
	public String rentMoney(@RequestParam(defaultValue = "1") Integer pageIndex,Model model,String channelname,HttpSession session) throws Exception{
		Long channelid = (Long) session.getAttribute("currChannelId");
		List<RentPlan> rentPlans = rentPlanServiceRead.findAllPlans(pageIndex,AppConfig.getPage_size_web(),channelid,channelname);
		List<Channel> channelList = channelServiceRead.findAllChannelNotfreeze(channelid);
		Integer totalCount = rentPlanServiceRead.findAllPlansCount(channelid,channelname);
		// 总页数
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		if(0==totalPage){
			totalPage = 1;
		} 
		
		model.addAttribute("rentPlans", rentPlans);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("channelList", channelList);
		model.addAttribute("channelid", channelid);
		model.addAttribute("channelname", channelname);
		return "rentPlan_list";
	}
*/
	
	/**
	 * 
	 * 新增租赁费用方案页面
	 * @return
	 * @throws Exception
	 */
	
/*	@RequestMapping("addRentPlanJsp")
	public String addRentPlanJsp(Model model,HttpSession session) throws Exception{
		Long channelId = (Long) session.getAttribute("currChannelId");
		List<Channel> channelList = channelServiceRead.findAllChannelNotfreeze(channelId);
		model.addAttribute("channelList", channelList);
		return "detail/rentPlan_add";
	}*/
	
	/**
	 * 新增租赁费用方案
	 * @return
	 * @throws Exception
	 */
/*	@RequestMapping("addRentPlan")
	public String addRentPlan(RentPlan rentPlan,String activityStart,String activityEnd) throws Exception{
		rentPlan.setRentActivityStart(DateUtil.changStringDate02(activityStart));
		rentPlan.setRentActivityEnd(DateUtil.changStringDate02(activityEnd));
		String startTime = DateUtil.formatHourAndMinute(rentPlan.getRentActivityStart());
		String endTime = DateUtil.formatHourAndMinute(rentPlan.getRentActivityEnd());
		String rentPlanQuantum = startTime+"-"+endTime;
		rentPlan.setRentPlanCreatetime(new Date());
		rentPlan.setRentPlanQuantum(rentPlanQuantum);
		rentPlanServiceWrite.addRentPlan(rentPlan);
		return "redirect:rentMoney.action";
	}*/
	
	/**
	 * 新增 默认的租赁费用页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
/*	@RequestMapping("addDefaultJsp")
	public String addDefaultJsp(Model model,HttpSession session) throws Exception{
		Long channelId = (Long) session.getAttribute("currChannelId");
		List<Channel> channelList = channelServiceRead.findAllChannelNotfreeze(channelId);
		model.addAttribute("channelList", channelList);
		return "detail/defaultPlan_add";
	}*/
	/**
	 * 新增默认
	 * @return
	 * @throws Exception
	 */
/*	@RequestMapping("addDefault")
	public String addDefault(RentPlan rentPlan) throws Exception{
		RentPlan rentPlan2 =rentPlanServiceRead.findByChannelAndFlag(rentPlan.getRentPlanChannelId(),1);
		if(null!=rentPlan2){
			rentPlanServiceWrite.deleteRentplan(rentPlan2);
		}
		rentPlan.setRentPlanEffectFlag((short) 1);
		rentPlan.setRentPlanCreatetime(new Date());
		rentPlanServiceWrite.addRentPlan(rentPlan);
		return "redirect:rentMoney.action";
	}*/
	/**
	 * 修改租赁费用页面
	 * @return
	 * @throws Exception
	 */
/*	@RequestMapping("editRentPlanJsp")
	public String editRentPlanJsp(Long rentPlanId,Model model,Integer flag) throws Exception{
		RentPlan rentPlan = rentPlanServiceRead.findPlanByRentPlanId(rentPlanId);
		Channel channel = channelServiceRead.findById(rentPlan.getRentPlanChannelId());
		String startTime =rentPlan.getRentPlanQuantum().substring(0, 5);
		String endTime = rentPlan.getRentPlanQuantum().substring(6, 11);
		model.addAttribute("rentPlan", rentPlan);
		model.addAttribute("channel", channel);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		return "detail/rentPlan_edit";
	}*/
	
	/**
	 * 修改租赁费用
	 * @return
	 * @throws Exception
	 */
/*	@RequestMapping("editRentPlan")
	public String editRentPlan(RentPlan rentPlan,String activityStart,String activityEnd,Date rentActivityStart,Date rentActivityEnd) throws Exception{
		if(2==rentPlan.getRentDefault()){ //不为活动
			rentPlan.setRentActivityStart(rentActivityStart);
			rentPlan.setRentActivityEnd(rentActivityEnd);
			String startTime = DateUtil.formatHourAndMinute(rentPlan.getRentActivityStart());
			String endTime = DateUtil.formatHourAndMinute(rentPlan.getRentActivityEnd());
			String rentPlanQuantum = startTime+"-"+endTime;
			rentPlan.setRentPlanQuantum(rentPlanQuantum);
		}else if(0==rentPlan.getRentDefault()){
			String rentPlanQuantum = activityStart+"-"+activityEnd;
			rentPlan.setRentPlanQuantum(rentPlanQuantum);
		}
		rentPlanServiceWrite.editRentPlan(rentPlan);
		return "redirect:rentMoney.action";
	}
	*/
	
	
	

	/**
	 * 修改租赁订单状态
	 * @param bikeRentInfo
	 * @param bike
	 * @param user
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editBikeRentState")
	public String editBikeRentState(BikeRentInfo bikeRentInfo,Bike bike,User user,Integer flag,HttpSession session) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");//获取admin
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		BikeRentInfo newbikeRentInfo = bikeRentInfoServiceRead.findBikeRentInfoById(bikeRentInfo.getRentInfoId());
		if(bikeRentInfo.getRentState()==1){
			bikeRentInfo.setRentPayTime(new Date());
		}
		User nowUser = userServiceRead.findById(user.getUserId());
		Bike newBike = bikeServiceRead.findBikeByBikeId(bike.getBikeId());
		Models models = modelsServiceRead.findModelsById(newBike.getBikeModelsId());
		RentPrice price = null;
		Double nowMoney = 0.0;
		if(null!=nowUser.getUserChannelId()){
			if(nowUser.getUserChannelId().equals(models.getModelsChannelId())){
				if(models.getModelsRentType()==1){
					price = rentPriceServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
				}else if(models.getModelsRentType()==2){
					price = rentPriceServiceRead.findDelPrice(models.getModelsId(), 2);//会员计费
				}
			}else{
				price = rentPriceServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
			}
		}else{
			price = rentPriceServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
		}
		
		
		if(null!=price){
			if(null!=bikeRentInfo.getRentEndtime()){
				Long diffMinutes = DateUtil.minuteDiff(newbikeRentInfo.getRentStarttime(), bikeRentInfo.getRentEndtime());
				if(price.getRentPriceOption()==1){//一小时计费
					Long diffHours = DateUtil.hourDiff(newbikeRentInfo.getRentStarttime(), bikeRentInfo.getRentEndtime());
					Integer diffSeconds = (int)(diffHours / 24);
					if(diffSeconds>0){//大于24小时
						if(diffMinutes>price.getRentFreeTime()){
							diffHours = diffHours - diffSeconds*24;
							nowMoney = nowMoney + getDiffNowMoney(price.getRentPrice(),diffHours,diffMinutes);
							if(nowMoney>price.getRentPriceMax()){
								nowMoney = price.getRentPriceMax();
							}
							nowMoney = nowMoney + diffSeconds*price.getRentPriceMax();
						}
						
						
					}else{//小于24小时
						if(diffMinutes>price.getRentFreeTime()){
							nowMoney = nowMoney + getDiffNowMoney(price.getRentPrice(),diffHours,diffMinutes);
							if(nowMoney>price.getRentPriceMax()){
								nowMoney = price.getRentPriceMax();
							}
						}
					}
					
				}else if(price.getRentPriceOption()==2){//半小时计费
					Long halfHours = DateUtil.halfHourDiff(newbikeRentInfo.getRentStarttime(), bikeRentInfo.getRentEndtime());
					Integer haffSeconds = (int)(halfHours *0.5 / 24);
					if(haffSeconds>0){//大于24小时
						if(diffMinutes>price.getRentFreeTime()){
							halfHours = halfHours - haffSeconds*48;
							nowMoney = nowMoney + getHalfNowMoney(price.getRentPrice(),halfHours,diffMinutes);
							if(nowMoney>price.getRentPriceMax()){
								nowMoney = price.getRentPriceMax();
							}
							nowMoney = nowMoney + haffSeconds*price.getRentPriceMax();
						}
					}else{//小于24小时
						if(diffMinutes>price.getRentFreeTime()){
							nowMoney = nowMoney + getHalfNowMoney(price.getRentPrice(),halfHours,diffMinutes);
							if(nowMoney>price.getRentPriceMax()){
								nowMoney = price.getRentPriceMax();
							}
						}
						
					}
					
				}
				
			}
		}
		
	
		bikeRentInfo.setRentPrice(new BigDecimal(nowMoney));
		bikeRentInfoServiceWrite.updateBikeRentInfo(bikeRentInfo);
		if(null!=newBike.getBikeLockId()){
			BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(newBike.getBikeLockId());
			if(bike.getBikeState()==0){
				redisService.del("userOpen"+bikeLockInfo.getBikeLockCode());
			}
		}
		bikeServiceWrite.editBike(bike);
		userServiceWrite.updateUser(user);
		
		OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.operateOrder(nowAdmin.getAdminRealname(), 1, bikeRentInfo.getRentInfoId().toString());
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
		
    	return "redirect:orderList.action?flag="+flag;
	}
	
	/**
	 *一小时计费
	 * @param modelsRentPrice
	 * @param diffHours
	 * @param diffMinutes
	 * @throws Exception
	 */
	public Double getDiffNowMoney(String modelsRentPrice,Long diffHours,Long diffMinutes) throws Exception{
		Double nowMoney = 0.0;
		JSONArray priceArray = new JSONArray(modelsRentPrice);//将字符串转化为jsonArray
		for(int i=0 ; i < priceArray.length() ;i++){
			//获取每一个JsonObject对象
		    JSONObject myjObject = priceArray.getJSONObject(i);
		    Double fromTime = myjObject.optDouble("fromTime",-1);
		    Double toTime = myjObject.optDouble("toTime",-1);
		    Double rentPrice = myjObject.getDouble("rentPrice");
		    if(toTime==-1){//-1为最后时间段
				nowMoney = nowMoney+rentPrice*diffHours;
				break;
		    }else{
		    	if (diffMinutes > toTime*60) { // 大于当前时间段最大时间
		    		nowMoney = nowMoney+rentPrice;
		    		diffHours = (long)(diffHours - (toTime-fromTime));
					continue;
				}else{
					nowMoney = nowMoney+rentPrice*diffHours;
					break;
				}
		    }
		}
		return nowMoney;
	}
	
	/**
	 *半小时计费
	 * @param modelsRentPrice
	 * @param diffHours
	 * @param diffMinutes
	 * @throws Exception
	 */
	public Double getHalfNowMoney(String modelsRentPrice,Long halfHours,Long halfMinutes) throws Exception{
		Double nowMoney = 0.0;
		JSONArray priceArray = new JSONArray(modelsRentPrice);//将字符串转化为jsonArray
		for(int i=0 ; i < priceArray.length() ;i++){
			//获取每一个JsonObject对象
		    JSONObject myjObject = priceArray.getJSONObject(i);
		    Double fromTime = myjObject.optDouble("fromTime",-1);
		    Double toTime = myjObject.optDouble("toTime",-1);
		    Double rentPrice = myjObject.getDouble("rentPrice");
		    if(toTime==-1){//-1为最后时间段
				nowMoney = nowMoney+rentPrice*halfHours;
				break;
		    }else{
		    	if (halfMinutes > toTime*30) { // 大于当前时间段最大时间
		    		nowMoney = nowMoney+rentPrice;
		    		halfHours = (long)(halfHours - (toTime-fromTime));
					continue;
				}else{
					nowMoney = nowMoney+rentPrice*halfHours;
					break;
				}
		    }
		}
		return nowMoney;
	}
	
}
