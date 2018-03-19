package org.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.component.AlipayConfig;
import org.component.AppConfig;
import org.component.WXPayConfig;
import org.entity.dto.Account;
import org.entity.dto.Admin;
import org.entity.dto.AdminToRoleKey;
import org.entity.dto.Bike;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.Channel;
import org.entity.dto.FixedReturn;
import org.entity.dto.InsurancePrice;
import org.entity.dto.Models;
import org.entity.dto.MoneyLog;
import org.entity.dto.RedeemPlan;
import org.entity.dto.Refund;
import org.entity.dto.RentPrice;
import org.entity.dto.Role;
import org.entity.dto.User;
import org.entity.dto.UserCoupon;
import org.entity.dto.UserToTask;
import org.json.JSONArray;
import org.json.JSONObject;
import org.service.cms.read.AccountServiceRead;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeLockInfoServiceRead;
import org.service.cms.read.BikeRentInfoServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.FixedReturnServiceRead;
import org.service.cms.read.InsurancePriceServiceRead;
import org.service.cms.read.ModelsServiceRead;
import org.service.cms.read.MoneyLogServiceRead;
import org.service.cms.read.RedeemPlanServiceRead;
import org.service.cms.read.RefundServiceRead;
import org.service.cms.read.RentPriceServiceRead;
import org.service.cms.read.RoleServiceRead;
import org.service.cms.read.UserCouponServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.read.UserToTaskServiceRead;
import org.service.cms.write.BikeRentInfoServiceWrite;
import org.service.cms.write.FixedReturnServiceWrite;
import org.service.cms.write.MoneyLogServiceWrite;
import org.service.cms.write.RedeemPlanServiceWrite;
import org.service.cms.write.RefundServiceWrite;
import org.service.cms.write.UserCouponServiceWrite;
import org.service.cms.write.UserServiceWrite;
import org.service.cms.write.UserToTaskServiceWrite;
import org.service.lock.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.util.DateUtil;
import org.util.ShortYunMessageUtil;
import org.util.redis.RedisService;
import org.util.weixin.WXHttpApi;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;

/**
 * 定时任务
 * @author Administrator
 *
 */
@Component
public class Task {

	
	@Resource
	AppConfig appConfig;
	
	@Resource
	BikeLockInfoServiceRead bikeLockInfoServiceRead;
	
	@Resource
	LockService lockService;
	
	@Resource
	FixedReturnServiceRead fixedReturnServiceRead;
	
	@Resource
	BikeServiceRead bikeServiceRead;
	
	@Resource
	FixedReturnServiceWrite fixedReturnServiceWrite;
	
	@Resource
	MoneyLogServiceRead moneyLogServiceRead;
	
	@Resource
	MoneyLogServiceWrite moneyLogServiceWrite;
	
	@Resource
	ModelsServiceRead modelsServiceRead;
	
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	RoleServiceRead roleServiceRead;
	
	@Resource
	RefundServiceRead refundServiceRead;
	
	@Resource
	RefundServiceWrite refundServiceWrite;
	
	@Resource
	BikeRentInfoServiceRead bikeRentInfoServiceRead;
	
	@Resource
	RentPriceServiceRead rentPriceServiceRead;
	
	@Resource
	UserServiceRead userServiceRead;
	
	@Resource
	AccountServiceRead accountServiceRead;
	
	@Resource
	InsurancePriceServiceRead insurancePriceServiceRead;
	
	@Resource
	UserCouponServiceRead userCouponServiceRead;
	
	@Resource
	UserCouponServiceWrite userCouponServiceWrite;
	
	@Resource
	BikeRentInfoServiceWrite bikeRentInfoServiceWrite;
	
	@Resource
	RedeemPlanServiceRead redeemPlanServiceRead;
	
	@Resource
	RedeemPlanServiceWrite redeemPlanServiceWrite;
	
	@Resource
	UserToTaskServiceRead userToTaskServiceRead;

	@Resource
	UserToTaskServiceWrite userToTaskServiceWrite;
	
	@Resource
	UserServiceWrite userServiceWrite;
	/**封装之后，实行单例模式，不用每次开关*/
	@Autowired  
    private RedisService redisService;
	/**
	 * 定时任务扫描发送状态指令
	 * 每天下午扫描一次
	 */
	@Scheduled(cron = "0 00 17 * * ?")//每天下午5点
	public void updateLockState()throws Exception{
		List<BikeLockInfo> allLock = bikeLockInfoServiceRead.findAllLock();
		//找出所有的锁 给锁发获取状态的指令
		for (BikeLockInfo bikeLockInfo : allLock) {
			if(lockService.judgeLockConnet(bikeLockInfo.getBikeLockCode())){
				lockService.sendStateMessage(bikeLockInfo.getBikeLockCode());
				
			}
		}
		
			
	}
	
	
	/**
	 * 定时任务查询锁信息 电量低于3.5V的给该渠道的管理员发送短信
	 * 每天下午扫描一次
	 */
	@Scheduled(cron = "0 30 17 * * ?")//每天下午5点
	public void selectVoltage()throws Exception{
		Integer flag = 1;//（0不可以添加1可以）
		//查询锁信息 电量低于3.5V的给该渠道的管理员发送短信
		List<BikeLockInfo> Lock = bikeLockInfoServiceRead.findAllLock();
		List<Channel> Channels = new ArrayList<Channel>();
		for(BikeLockInfo bikeLockInfo : Lock){
			if(bikeLockInfo.getBikeLockVoltage()!=null&&!("").equals(bikeLockInfo.getBikeLockVoltage())&&bikeLockInfo.getBikeLockVoltage()<3.5&&bikeLockInfo.getBikeLockDel()==0){
				Bike bike = null;
				if(null!=bikeLockInfo.getBikeLockBikeId()&&!bikeLockInfo.getBikeLockBikeId().equals("")){
					bike = bikeServiceRead.findBikeByBikeId(bikeLockInfo.getBikeLockBikeId());
				}
				if(bike!=null&&bike.getBikeDel()==0){
					Models model = modelsServiceRead.findModelsById(bike.getBikeModelsId());
					if(model!=null&&model.getModelsState()==0){
						Channel Channel = channelServiceRead.findById(model.getModelsChannelId()); 
						if(Channel!=null&&Channel.getChannelState()==1){
							
						if(Channels.size()>0){
							for(int i=0;i<Channels.size();i++){
								if(Channel.getChannelId().equals(Channels.get(i))){
									flag = 0;
								}
							}
							if(flag!=0){
								Channels.add(Channel);
							}
							
						}else{
							Channels.add(Channel);
						}
						
						}
					}
				}
			}
		}
		for(Channel channel : Channels){
			if(channel.getChannelId()!=null&&!"".equals(channel.getChannelId())){
				List<Admin> admins = adminServiceRead.findByChannelId(channel.getChannelId());
				Admin admin1 =null;
				for(Admin admin : admins){
					List<AdminToRoleKey> rolecontact = roleServiceRead.findKeyByAId(admin.getAdminId());
					if(rolecontact!=null && rolecontact.size()>0){
						for (AdminToRoleKey ro : rolecontact) {
							Role role = roleServiceRead.findById(ro.getRoleId());
							if(null!=role&&role.getRoleState()!=-1&&role.getRoleState()!=0&&"管理员".equals(role.getRoleName())){
								admin1=admin;
								ShortYunMessageUtil.sendMassage(admin1.getAdminTel());
							}
				}
			}
				}
			}
		}
			
	}
	
	   
//	/**
//	 * 定时任务批量发送gps指令
//	 * 每天下午扫描一次
//	 */
//	@Scheduled(cron = "0 00 22 * * ?")//每天下午10点
//	public void updateLockGps()throws Exception{
//		List<BikeLockInfo> allLock = bikeLockInfoServiceRead.findAllLock();
//		for (BikeLockInfo bikeLockInfo : allLock) {
//			if(lockService.judgeLockConnet(bikeLockInfo.getBikeLockCode())){
//				lockService.sendLocationMessage(bikeLockInfo.getBikeLockCode());
//			}
//		}
//	}
	
	
	/**
	 * 定时任务查询站点车辆数量
	 * 每半小时扫描一次
	 */
	@Scheduled(cron = "0 */30 * * * ?")//每半小时
	public void getBikeNum()throws Exception{
		List<FixedReturn> fixedReturns = fixedReturnServiceRead.findAllFixed(null, null, null, null, null);//查询所有站点信息
		for(FixedReturn fr : fixedReturns){
			List<Bike> bike = bikeServiceRead.findByFixedReturnId(fr.getFixedReturnId());
			fr.setFixedReturnBikeNum(bike.size());
			fixedReturnServiceWrite.editFixedReturn(fr);
		}
	}
	
	/**
	 * 定时任务发送位置
	 * 每一小时扫描一次
	 */
	@Scheduled(cron = "0 0 0/60 * * ?")//每一小时
	public void getLocation()throws Exception{
		List<BikeLockInfo> allLock = bikeLockInfoServiceRead.findAllLock();
		for (BikeLockInfo bikeLockInfo : allLock) {
			if(lockService.judgeLockConnet(bikeLockInfo.getBikeLockCode())){
				lockService.sendLocationMessage(bikeLockInfo.getBikeLockCode());
			}
		}
	}
	/**
	 * 定时查询退款信息
	 * 每1分钟查询一次
	 */

	@Scheduled(cron = "0 */1 * * * ?")//每1分钟
	public void Inquiry()throws Exception{
		List<MoneyLog> refundList=moneyLogServiceRead.refundList();
		for(MoneyLog moneyLog:refundList){
			if(null!=moneyLog.getMoneyLogOutTrade()&&!moneyLog.getMoneyLogOutTrade().equals("")){
				Refund refund = refundServiceRead.findRefundByOutTrade(moneyLog.getMoneyLogOutTrade());
				if(null!=refund){
				 if(null!=refund.getRefundSource()){
					 if(refund.getRefundSource()==1){//微信公众平台
						 if(WXHttpApi.wechatRefundInquiry(moneyLog.getMoneyLogOutTrade(), appConfig.getApp_id(), appConfig.getApp_secret(), appConfig.getApp_partner(), appConfig.getApp_partner(), appConfig.getApp_partnerkey())==1){
								moneyLog.setMoneyLogRefundState(2);
								refund.setRefundState(2);
							}
							if(WXHttpApi.wechatRefundInquiry(moneyLog.getMoneyLogOutTrade(), appConfig.getApp_id(), appConfig.getApp_secret(), appConfig.getApp_partner(), appConfig.getApp_partner(), appConfig.getApp_partnerkey())==2){
								moneyLog.setMoneyLogRefundState(3);
								refund.setRefundState(3);
							}
							if(WXHttpApi.wechatRefundInquiry(moneyLog.getMoneyLogOutTrade(), appConfig.getApp_id(), appConfig.getApp_secret(), appConfig.getApp_partner(), appConfig.getApp_partner(), appConfig.getApp_partnerkey())==3){
								moneyLog.setMoneyLogRefundState(4);
								refund.setRefundState(4);
							}
							if(WXHttpApi.wechatRefundInquiry(moneyLog.getMoneyLogOutTrade(), appConfig.getApp_id(), appConfig.getApp_secret(), appConfig.getApp_partner(), appConfig.getApp_partner(), appConfig.getApp_partnerkey())==5){
								moneyLog.setMoneyLogRefundState(6);
								refund.setRefundState(6);
							}
					 }else if(refund.getRefundSource()==3){//支付宝
						 AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.app_id,AlipayConfig.private_key,"json",AlipayConfig.input_charset,AlipayConfig.alipay_public_key,"RSA2");
						 AlipayTradeFastpayRefundQueryRequest  alirequest = new AlipayTradeFastpayRefundQueryRequest ();
						 alirequest.setBizContent("{" +
							"    \"out_trade_no\":\""+refund.getRefundCode()+"\"," +
							"    \"out_request_no\":\""+refund.getRefundCode()+"\"" +
							"  }");
							AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(alirequest);
							if(response.isSuccess()){
							System.out.println("调用成功");
							System.out.println(response.getCode());
								if("10000".equals(response.getCode())&&null!=response.getOutTradeNo()&&""!=response.getOutTradeNo()){
									moneyLog.setMoneyLogRefundState(2);
									refund.setRefundState(2);
								}
							}
					 }else if(refund.getRefundSource()==4){//微信app
						 if(WXHttpApi.wechatRefundInquiry(moneyLog.getMoneyLogOutTrade(), WXPayConfig.appid, WXPayConfig.appsecret, WXPayConfig.mch_id, WXPayConfig.mch_id, WXPayConfig.key)==1){
								moneyLog.setMoneyLogRefundState(2);
								refund.setRefundState(2);
							}
							if(WXHttpApi.wechatRefundInquiry(moneyLog.getMoneyLogOutTrade(), WXPayConfig.appid, WXPayConfig.appsecret, WXPayConfig.mch_id, WXPayConfig.mch_id, WXPayConfig.key)==2){
								moneyLog.setMoneyLogRefundState(3);
								refund.setRefundState(3);
							}
							if(WXHttpApi.wechatRefundInquiry(moneyLog.getMoneyLogOutTrade(), WXPayConfig.appid, WXPayConfig.appsecret, WXPayConfig.mch_id, WXPayConfig.mch_id, WXPayConfig.key)==3){
								moneyLog.setMoneyLogRefundState(4);
								refund.setRefundState(4);
							}
							if(WXHttpApi.wechatRefundInquiry(moneyLog.getMoneyLogOutTrade(), WXPayConfig.appid, WXPayConfig.appsecret, WXPayConfig.mch_id, WXPayConfig.mch_id, WXPayConfig.key)==5){
								moneyLog.setMoneyLogRefundState(6);
								refund.setRefundState(6);
							}
					 }
					
				 }
				}
				refundServiceWrite.updateState(refund);
			}
			
			moneyLogServiceWrite.updateState(moneyLog);
			
		}
	}
	
	/**
	 * 定时查询临时上锁时间 时间大于 30fenzhong  短信提醒
	 * 
	 */
	@Scheduled(cron = "0 */30 * * * ?")//每30分钟
	public void getTemporarylocktime()throws Exception{
		//if("wxf118a49013285790".equals(appConfig.getApp_id())){ //测式网
		if("wxf6a747b5a4e78b31".equals(appConfig.getApp_id())){ //正式网
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoServiceRead.findNotRent();
		if(bikeRentInfos!=null){
			
			for(BikeRentInfo bikeRentInfo:bikeRentInfos){
				if(bikeRentInfo.getBike().getBikeState()==1){
					if(bikeRentInfo.getRentBikeChannelId()!=15){
						Date date = new Date();
						if(bikeRentInfo.getBike().getBikeTemporarylocktime()!=null){
							User user = userServiceRead.findById(bikeRentInfo.getRentInfoUserId());
						Long time = DateUtil.minuteDiff1(bikeRentInfo.getBike().getBikeTemporarylocktime(),date);
						//System.out.println(time);
						if((time>30&&time<=60) || ((48*30)<time&&time<=(49*30))){//半小时提醒一次,一天提醒一次
							//System.out.println(user.getUserTel());
							ShortYunMessageUtil.sendMassage1(user.getUserTel());
							ShortYunMessageUtil.sendMassage2(user.getUserTel());
						}
					}
					
					}
					
				}
			}
		}
		}
	}
	
	/**
	 * 定时查询余额小于  短信提醒
	 * 
	 */
	@Scheduled(cron = "0 */60 * * * ?")//每一小时
	public void getAccount()throws Exception{
		//if("wxf118a49013285790".equals(appConfig.getApp_id())){ //测式网
		if("wxf6a747b5a4e78b31".equals(appConfig.getApp_id())){ //正式网
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoServiceRead.findNotRent();
		if(bikeRentInfos!=null){
			
			for(BikeRentInfo bikeRentInfo:bikeRentInfos){
				if(bikeRentInfo.getRentEndtime()==null){
					if(bikeRentInfo.getRentBikeChannelId()!=15){
					User user = userServiceRead.findById(bikeRentInfo.getRentInfoUserId());
					Bike bike = bikeServiceRead.findBikeByBikeId(bikeRentInfo.getRentInfoBikeId());
					Models models = modelsServiceRead.findModelsById(bike.getBikeModelsId());
					Date nowDay = new Date();
					Double rentMoney = getRentMoney(user,models,bikeRentInfo,nowDay);
					Account account = accountServiceRead.findPlanByaccountId(user.getUserAccountId());
					Double AccountAvailableBalance = account.getAccountAvailableBalance().doubleValue();
					Double AccountDeposit = account.getAccountDeposit().doubleValue();
					if(null!=user.getUserChannelId()){
						if(user.getUserChannelId().equals(models.getModelsChannelId())){
							if(AccountAvailableBalance-rentMoney<10){
								//System.out.println(user.getUserTel());
								ShortYunMessageUtil.sendMassage3(user.getUserTel());//会员计费
								ShortYunMessageUtil.sendMassage4(user.getUserTel());
								//ShortYunMessageUtil.sendMassage4("13587714878");
							}
						}else if(AccountDeposit-rentMoney<10){
							//System.out.println("2"+bikeRentInfo.getRentInfoId());
							//ShortYunMessageUtil.sendMassage2("1111");//游客计费
							ShortYunMessageUtil.sendMassage3(user.getUserTel());
							ShortYunMessageUtil.sendMassage4(user.getUserTel());
						}
					}else if(AccountDeposit-rentMoney<10){
						//System.out.println("3"+bikeRentInfo.getRentInfoId());
						//ShortYunMessageUtil.sendMassage2("1111");//游客计费
						ShortYunMessageUtil.sendMassage3(user.getUserTel());
						ShortYunMessageUtil.sendMassage4(user.getUserTel());
					}
					}
				}
			}
		}
		}
	}
	
	public Double getRentMoney(User user,Models models,BikeRentInfo rentInfo,Date nowDay) throws Exception {
		Double nowMoney = 0.0;
		RentPrice price = null;
		if(null!=user.getUserChannelId()){
			if(user.getUserChannelId().equals(models.getModelsChannelId())){
				price = rentPriceServiceRead.findDelPrice(models.getModelsId(), 2);//会员计费
			}else{
				price = rentPriceServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
			}
		}else{
			price = rentPriceServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
		}
		
		if(null!=price){
				Long diffMinutes = DateUtil.minuteDiff(rentInfo.getRentStarttime(), nowDay);
				if(price.getRentPriceOption()==1){//一小时计费
					Long diffHours = DateUtil.hourDiff(rentInfo.getRentStarttime(), nowDay);
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
					Long halfHours = DateUtil.halfHourDiff(rentInfo.getRentStarttime(), nowDay);
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
		
		return nowMoney;

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
	
	/**
	 * 定时生成订单
	 * 
	 */
	@Scheduled(cron = "0 0/60 8-17 * * ?")
	//@Scheduled(cron = "0 */1 * * * ?")
	public void rentInfoRandom()throws Exception{
		//if("wx91c483af9abf197e".equals(appConfig.getApp_id())){ //本地
		//if("wxf9a2ad9a042a2cfa".equals(appConfig.getApp_id())){
		if("wxf118a49013285790".equals(appConfig.getApp_id())){ //测试网
			
			
			
			
			
				Double totalmoney = 0.0;
				do{
				BikeRentInfo bikeRentInfo = new BikeRentInfo();
				Date date1 = new Date();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 8:00:00");  
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd 17:00:00");
		        String start = format.format(date1);
		       // System.out.println(start);
			    String end = format1.format(date1);
			    //System.out.println(end);
			    
			    //System.out.println(starttime);
			     Date starttime = randomDate(start,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1));
				 SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				    String start1 = format2.format(starttime);
				    Date endtime = randomDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(starttime),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1));
			    //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(starttime));
			    //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endtime));
			    bikeRentInfo.setRentStarttime(starttime);
			    bikeRentInfo.setRentEndtime(endtime);
				Long rideTime = DateUtil.minuteDiff(starttime, endtime);
			    bikeRentInfo.setRentLongtime(Integer.valueOf(rideTime.toString()));
			    List<User> userList = userServiceRead.findAllUser();
			    int [] arr = new int[userList.size()];
			    for(int i =0 ;i<userList.size();i++){
			    	int x = userList.get(i).getUserId().intValue();
			    	arr[i]=x;
			    }
			    int userid = arr[(int) (Math.random() * arr.length)];// 随机数乘以数组长度，那么它的取值就在0-length之间
			    bikeRentInfo.setRentInfoUserId((long)userid);
			    User user = userServiceRead.findById((long)userid);
			    //List<Channel> channelList = channelServiceRead.findAllChannels();
			    List<String> liststr = new ArrayList<>();
			    liststr.add("赵家镇");
			    liststr.add("鄣吴镇");
			    List<Channel> channelList = channelServiceRead.findChannel(liststr);
			    int [] arr1 = new int[channelList.size()];
			    for(int i =0 ;i<channelList.size();i++){
			    	int x = channelList.get(i).getChannelId().intValue();
			    	arr1[i]=x;
			    }
			    int channelid = arr1[(int) (Math.random() * arr1.length)];// 随机数乘以数组长度，那么它的取值就在0-length之间
			    Channel channel = channelServiceRead.findById((long)channelid);
			    if(null!=channel){
			    	bikeRentInfo.setRentBikeChannelId(channel.getChannelId());
			    }
			    List<Models> modelList = modelsServiceRead.findModelsByChannelId((long)channelid);
			    int modelsid = 0;
			    if(modelList!=null&&modelList.size()>0){
			    int [] arr2 = new int[modelList.size()];
			    for(int i =0 ;i<modelList.size();i++){
			    	int x = modelList.get(i).getModelsId().intValue();
			    	arr2[i]=x;
			    	 modelsid = arr2[(int) (Math.random() * arr2.length)];// 随机数乘以数组长度，那么它的取值就在0-length之间
			    }
			    }else{
			    	List<Models> modelList1 = modelsServiceRead.findAllModels();
			    	int [] arr2 = new int[modelList1.size()];
				    for(int i =0 ;i<modelList1.size();i++){
				    	int x = modelList1.get(i).getModelsId().intValue();
				    	arr2[i]=x;
				    	 modelsid = arr2[(int) (Math.random() * arr2.length)];// 随机数乘以数组长度，那么它的取值就在0-length之间
			    }
			    }
			    
			    int bikeid = 0;
			    List<Bike> bikeList = bikeServiceRead.findBikeByModelsId((long)modelsid);
			    if(bikeList!=null&&bikeList.size()>0){
			    	 int [] arr3 = new int[bikeList.size()];
					    for(int i =0 ;i<bikeList.size();i++){
					    	int x = bikeList.get(i).getBikeId().intValue();
					    	arr3[i]=x;
					    	 bikeid = arr3[(int) (Math.random() * arr3.length)];// 随机数乘以数组长度，那么它的取值就在0-length之间
					    }
			    }else{
			    	List<Bike> bikeList1 = bikeServiceRead.findAllBike();
			    int [] arr3 = new int[bikeList1.size()];
			    for(int i =0 ;i<bikeList1.size();i++){
			    	int x = bikeList1.get(i).getBikeId().intValue();
			    	arr3[i]=x;
			    	 bikeid = arr3[(int) (Math.random() * arr3.length)];// 随机数乘以数组长度，那么它的取值就在0-length之间
			    }
			    }
			    
			    bikeRentInfo.setRentInfoBikeId((long)bikeid);
			    Bike bike = bikeServiceRead.findBikeByBikeId((long)bikeid);
			    Models models = modelsServiceRead.findModelsById(bike.getBikeModelsId());
			    Double nowMoney = 0.0;
				RentPrice price = null;
				if(null!=user.getUserChannelId()){
					if(user.getUserChannelId().equals(models.getModelsChannelId())){
						price = rentPriceServiceRead.findDelPrice(models.getModelsId(), 2);//会员计费
					    bikeRentInfo.setRentIsvillager(1);
					}else{
						price = rentPriceServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
						
					}
				}else{
					price = rentPriceServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
					
				}
				
				if(null!=price){
						Long diffMinutes = DateUtil.minuteDiff(bikeRentInfo.getRentStarttime(), endtime);
						if(price.getRentPriceOption()==1){//一小时计费
							Long diffHours = DateUtil.hourDiff(bikeRentInfo.getRentStarttime(), endtime);
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
							Long halfHours = DateUtil.halfHourDiff(bikeRentInfo.getRentStarttime(), endtime);
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
				bikeRentInfo.setRentPrice(new BigDecimal(nowMoney));
				if(null!=models.getModelsInpriceId()){
					InsurancePrice insurancePrice = insurancePriceServiceRead.findById(models.getModelsInpriceId());//查询保险费用
					bikeRentInfo.setRentInsurancePrice(insurancePrice.getInPrice());
				}
				bikeRentInfo.setRentPayTime(endtime);
				bikeRentInfo.setRentState(1);
				bikeRentInfo.setRentPayType(1);
				// 订单号 = "标志"+时间+车辆id+用户id
				String rentOrderCode = "Z" + DateUtil.format03(endtime) + bike.getBikeId() + user.getUserId();
				bikeRentInfo.setRentOrderCode(rentOrderCode);
				bikeRentInfoServiceWrite.addRentInfo(bikeRentInfo);
				
				totalmoney = totalmoney+ nowMoney;
				System.out.println(totalmoney);
				}
				while(totalmoney<33);
			
			 
		}
	    
	}
	/*public  static void main(String[] args){  
		Date date1 = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 8:00:00");  
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd 19:00:00");
        String start = format.format(date1);
       // System.out.println(start);
	    String end = format1.format(date1);
	    //System.out.println(end);
	    
	    //System.out.println(starttime);
	     Date starttime = randomDate(start,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1));
		 SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		    String start1 = format2.format(starttime);
		    Date endtime = randomDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(starttime),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1));
	    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(starttime));
	    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endtime));
		String dateString = "2017-5-15 11:00:00";  
		try  
		{  
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    Date date = sdf.parse(dateString);  
		    Calendar c1 = Calendar.getInstance();    
		    Calendar c2 = Calendar.getInstance();  
		    
		    c1.setTime(new Date());    
		    c2.setTime(date);    
		    
		    int result = c1.compareTo(c2);    
		    if (result >= 0)    
		       System.out.println(1);    
		    else    
		       System.out.println(2); 
		}  
		catch (ParseException e)  
		{  
		    System.out.println(e.getMessage());  
		}  
		  
				
			
		
		}
      */
	
	/**
	 * 随机生成时间
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	private static Date randomDate(String beginDate,String  endDate ){  

		try {  

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  

		Date start = format.parse(beginDate);//构造开始日期  

		Date end = format.parse(endDate);//构造结束日期  

		//getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。  

		if(start.getTime() >= end.getTime()){  

		return null;  

		}  

		long date = random(start.getTime(),end.getTime());  

		return new Date(date);  

		} catch (Exception e) {  

		e.printStackTrace();  

		}  

		return null;  

		}  

		private static long random(long begin,long end){  

		long rtn = begin + (long)(Math.random() * (end - begin));  

		//如果返回的是开始时间和结束时间，则递归调用本函数查找随机值  

		if(rtn == begin || rtn == end){  

		return random(begin,end);  

		}  

		return rtn;  

		}  
		/**
		 * 12点
		 * @throws Exception
		 */
		@Scheduled(cron = "0 0 12 * * ?")
		public void selectvipexpirationdate()throws Exception{
			List<User> userList = userServiceRead.findAllUser();
			for(User user:userList){
				if(user.getUserVipexpirationdate()!=null){
					Date date = new Date();
					Calendar c1 = Calendar.getInstance();    
				    Calendar c2 = Calendar.getInstance();  
				    
				    c1.setTime(date);    
				    c2.setTime(user.getUserVipexpirationdate());    
				    
				    int result = c1.compareTo(c2);    
				    if (result >= 0) { //当前时间大于等于到期时间
				    	user.setUserVipexpirationdate(null);
				    	user.setUserChannelId((long)0);
				    	userServiceWrite.updateUserstate(user);
				    }
				       
				    
				}
				
			}
		}
		/**
		 * 每20秒
		 * @throws Exception
		 */
		@Scheduled(cron = "0/20 * * * * ?")
		public void getUpdateLockFile()throws Exception{
			List<BikeLockInfo> allLock = bikeLockInfoServiceRead.findAllUpLock();//查询升级中的锁
			for (BikeLockInfo bikeLockInfo : allLock) {
				String dateFileStr = redisService.get("upfile"+bikeLockInfo.getBikeLockCode());
				if(!"".equals(dateFileStr)&&null!=dateFileStr){
					String[] split = dateFileStr.split(",");
					Date redisDate = DateUtil.changStringDate(split[0]);
					String name = split[1];
					if(DateUtil.secondDiff(redisDate,new Date())>=20&&!name.equals("firmware")){
						lockService.sendUpLockFile(bikeLockInfo.getBikeLockCode(), Integer.valueOf(split[2]), bikeLockInfo.getBikeLockUpfile(), Integer.valueOf(split[3]));
					}
				}
			}
			
		}

		/**
		 * 每晚11点58定时更新用户代金券
		 * @throws Exception
		 */
		@Scheduled(cron = "0 58 23 * * ?")
		public void updateUserCoupon() throws Exception{
			List<UserCoupon> userCouponList = userCouponServiceRead.findAllUserCoupon();//查用户可用的代金券
			String lastTimeStr = DateUtil.formatDay(DateUtil.plusDate(new Date(), 1));
			Date lastTime = DateUtil.changStringDate03(lastTimeStr);
			for(UserCoupon uc:userCouponList){
				if(null!=uc.getuRedeemCodeEndTime()){
					String endTimeStr = DateUtil.formatDay(uc.getuRedeemCodeEndTime());
					Date endTime = DateUtil.changStringDate03(endTimeStr);
					if(!lastTime.before(endTime)){//当明天大于或等于代金券最后时间时，返回false
						uc.setUstate(2);//过期
					}
					userCouponServiceWrite.updateUserCoupon(uc);
				}
			}
			
		}
		
		/**
		 * 定时查询兑换方案信息
		 * 每1分钟查询一次
		 */
		@Scheduled(cron = "0 */1 * * * ?")//每1分钟
		public void updateRedeemPlanState() throws Exception{
			List<RedeemPlan> redeemPlanList = redeemPlanServiceRead.findAllOnline();
			Date nowDay = new Date();
			for(RedeemPlan rp:redeemPlanList){
				if(null!=rp.getPlanReserveOfflineTime()){
					if(!nowDay.before(rp.getPlanReserveOfflineTime())){//当明天大于或等于兑换方案下线时间时，返回false
						rp.setPlanState(2);//下线
					}
					redeemPlanServiceWrite.updateRedeemPlan(rp);
				}
			}
			
		}
		
		
		/**
		 * 每晚11点58定时更新用户每日登录任务
		 * @throws Exception
		 */
		@Scheduled(cron = "0 58 23 * * ?")
		public void updateUserTask() throws Exception{
			List<User> userList = userServiceRead.findAllUser();
			for(User u : userList){
				//查询每日登录任务
				UserToTask userToTask = userToTaskServiceRead.findByUserIdAndDataId(u.getUserId(), (long)41);
				//UserToTask shareUserToTask = userToTaskServiceRead.findByUserIdAndDataId(u.getUserId(), (long)39);
				if(null!=userToTask){
					userToTask.setUserToTaskState(0);
					userToTaskServiceWrite.updateUserToTask(userToTask);
				}
				/*if(null!=shareUserToTask){
					shareUserToTask.setUserToTaskState(0);
					userToTaskServiceWrite.updateUserToTask(shareUserToTask);
				}*/
			}
		}
		
}
