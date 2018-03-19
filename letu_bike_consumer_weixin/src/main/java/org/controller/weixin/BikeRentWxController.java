package org.controller.weixin;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.*;
import org.entity.weixin.pojo.JSSDK;
import org.service.lock.LockService;
import org.service.weixin.read.*;
import org.service.weixin.write.AccountWxServiceWrite;
import org.service.weixin.write.BikeLockInfoWxServiceWrite;
import org.service.weixin.write.BikeRentInfoWxServiceWrite;
import org.service.weixin.write.BikeWxServiceWrite;
import org.service.weixin.write.FixedReturnWxServiceWrite;
import org.service.weixin.write.GradeWxServiceWrite;
import org.service.weixin.write.MessageWxServiceWrite;
import org.service.weixin.write.MoneyLogWxServiceWrite;
import org.service.weixin.write.OrbitServiceWxWrite;
import org.service.weixin.write.TrOrderWxServiceWrite;
import org.service.weixin.write.UserCouponServiceWxWrite;
import org.service.weixin.write.UserServiceWeixinWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.BlockUtil;
import org.util.DateUtil;
import org.util.DistanceUtil;
import org.util.GetLocationUtil;
import org.util.LevelUtil;
import org.util.MessageUtil;
import org.util.SplitUtil;
import org.util.redis.RedisService;
import org.util.redis.ThreadMapUtil;
import org.util.weixin.MinWXHttpApi;
import org.util.weixin.WXHttpApi;
import org.util.weixin.WxConfigUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 车辆租赁
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/bikeRent", method = RequestMethod.POST)
public class BikeRentWxController {

	@Resource
	UserServiceWeixinRead userServiceWeixinRead;

	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;

	@Resource
	BikeRentInfoWxServiceWrite bikeRentInfoWxServiceWrite;

	@Resource
	BikeWxServiceRead bikeWxServiceRead;

	@Resource
	BlockWxServiceRead blockWxServiceRead;

	@Resource
	BikeLockInfoWxServiceRead bikeLockInfoWxServiceRead;
	
	@Resource
	OrbitServiceWxWrite orbitServiceWxWrite;
	
	@Resource
	BikeLockInfoWxServiceWrite bikeLockInfoWxServiceWrite;

	@Resource
	AppConfig AppConfig;

	@Resource
	AccountWxServiceRead accountWxServiceRead;

	@Resource
	AccountWxServiceWrite accountWxServiceWrite;

	@Resource
	MoneyLogWxServiceWrite moneyLogWxServiceWrite;

	@Resource
	BikeWxServiceWrite bikeWxServiceWrite;

	@Resource
	UserServiceWeixinWrite userServiceWeixinWrite;

	@Resource
	ModelsWxServiceRead modelsWxServiceRead;

	@Resource
	LockService lockService;

	@Resource
	MessageWxServiceWrite messageWxServiceWrite;
	
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	
	@Resource
	FixedReturnWxServiceRead fixedReturnWxServiceRead;
	
	@Resource
	FixedReturnWxServiceWrite fixedReturnWxServiceWrite;
	
	@Resource
	InsurancePriceWxServiceRead insurancePriceWxServiceRead;
	
	@Resource
	DataDetWxServiceRead dataDetWxServiceRead;
	
	@Resource
	RentPriceWxServiceRead rentPriceWxServiceRead;
	
	@Resource
	TrOrderWxServiceWrite trOrderWxServiceWrite;
	
	@Resource
	UserCouponServiceWxRead userCouponServiceWxRead;
	
	@Resource
	UserCouponServiceWxWrite userCouponServiceWxWrite;
	@Resource
	GradeWxServiceWrite gradeWxServiceWrite;
	@Resource
	CouponSchemeWxServiceRead couponSchemeWxServiceRead;
	@Resource
	CouponListWxServiceRead couponListWxServiceRead;
	@Resource
	GuideGroupWxServiceRead guideGroupWxServiceRead;
	@Resource
	GuideGroupUserWxRead guideGroupUserWxRead;

	/**封装之后，实行单例模式，不用每次开关*/
	@Autowired  
    private RedisService redisService;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
    private static Logger loggers = Logger.getLogger(BikeRentWxController.class);
    

	/**
	 * 扫码所需参数
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scanCode", method = RequestMethod.POST)
	public @ResponseBody JSSDK scanCode(String url) throws Exception {
		JSSDK jssdk = new JSSDK();
		jssdk.setAppId(AppConfig.getApp_id());
		jssdk.setNonceStr(WxConfigUtil.getRandomString(16));
		jssdk.setAppUrl(url);
		loggers.info(new Date().getTime()/1000);
		jssdk.setCurrentTime(new Date().getTime()/1000);
		jssdk.setSignature(WxConfigUtil.getSignature(url, jssdk.getCurrentTime(), AppConfig.getApp_id(),jssdk.getNonceStr(),
				AppConfig.getApp_secret()));
		jssdk.setTimestamp(WxConfigUtil.cacheAddTime);// 时间更新为缓存中存储的时间
		loggers.info(WxConfigUtil.cacheAddTime);
		return jssdk;
	}

	/**
	 * 车子状态 (重写)
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkBike", method = RequestMethod.POST)
	public @ResponseBody MessageUtil checkBike(@RequestHeader HttpHeaders header,String bikeCode) throws Exception {
		String fromFlag = header.getFirst("fromFlag");
		Map<String, Object> data = new HashMap<String, Object>();
		User userLogin = new User();
	    if(null!=fromFlag&&"1".equals(fromFlag)){//app端
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
			
		}
		Bike bike = null;
		if (null != bikeCode && !"".equals(bikeCode))
			bike = bikeWxServiceRead.findByBikeCode(bikeCode);
		if (null == bike) { // 为null
			messageUtil.setCode(0);
			data.put("judgeState", 0);
			messageUtil.setData(data);
			messageUtil.setMessage("没有该车辆信息");
			return messageUtil;
		}
		User nowuser = userServiceWeixinRead.findByUId(userLogin.getUserId());
	    if(null!=fromFlag&&"1".equals(fromFlag)){//app端
			if (0 != userLogin.getUserState()) { // 若用户不是在空闲状态
				if (1 == userLogin.getUserState()) { // 如果是租借状态
					BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findNotFinishByUserId(userLogin.getUserId());// 查找未结束的租赁
					messageUtil.setMessage("还有租赁未完成");
					data.put("bike", bike);
					data.put("bikeRentInfo", bikeRentInfo);
					if (null == bikeRentInfo.getRentPayTime()) {// 没有支付
						messageUtil.setCode(3);
						data.put("judgeState", 3);
						messageUtil.setData(data);
						return messageUtil;
					}

					messageUtil.setCode(4);
					data.put("judgeState", 4);
					messageUtil.setData(data);
					return messageUtil;

				} 
			}
			
			if(nowuser.getUserIsblacklist()==0){
				messageUtil.setCode(0);
				data.put("judgeState", 0);
				messageUtil.setMessage("您已被拉黑，请联系客服，联系电话：0571-56231981");
				messageUtil.setData(data);
				return messageUtil;
			}
		}
		switch (bike.getBikeState()) {
		case 1:
			messageUtil.setCode(0);
			data.put("judgeState", 0);
			messageUtil.setData(data);
			messageUtil.setMessage("该车已被租借，请您选择其他的车辆出行");
			return messageUtil;
		case 2:
			messageUtil.setCode(0);
			data.put("judgeState", 0);
			messageUtil.setData(data);
			messageUtil.setMessage("该车已被租借，请您选择其他的车辆出行");
			return messageUtil;
		case 3:
			messageUtil.setCode(0);
			data.put("judgeState", 0);
			messageUtil.setData(data);
			messageUtil.setMessage("该车正在维护中，请您选择其他的车辆出行");
			return messageUtil;
		case 4:
			messageUtil.setCode(0);
			data.put("judgeState", 0);
			messageUtil.setData(data);
			messageUtil.setMessage("该车已被锁定，请您选择其他的车辆出行");
			return messageUtil;
		}
		messageUtil.setMessage("可以租借");
		Long modelsId = bike.getBikeModelsId();
		Models models = modelsWxServiceRead.findModelsById(modelsId);
		if(null!=fromFlag&&"1".equals(fromFlag)){
			if(null!=models){
				RentPrice price = null;
				if(null!=nowuser.getUserChannelId()){
					if(nowuser.getUserChannelId().equals(models.getModelsChannelId())){
						if(models.getModelsRentType()==1){
							price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
						}else if(models.getModelsRentType()==2){
							price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 2);//会员计费
						}
						
					}else{
						price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
					}
				}else{
					price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
				}
				
				if(null!=price){
					List<JSONObject> strList = new ArrayList<JSONObject>();//租赁费用列表
					JSONArray jsonArray = JSONArray.fromObject(price.getRentPrice());//将字符串转化为jsonArray
					for (int j = 0; j < jsonArray.size(); j++) {
						JSONObject jsonObject = (JSONObject) jsonArray.get(j);
						if(jsonObject.optDouble("fromTime",-1)!=-1){
							strList.add(jsonObject);
						}else{
							price.setLastPrice(jsonObject.getDouble("rentPrice"));//最后时间段
						}
					}
					price.setPriceList(strList);
					models.setModelRentPrice(price);
				}
					
			}
		}
		
		BigDecimal bikeDeposit = models.getModelsDeposit();
		data.put("bike", bike);
		data.put("models", models);
		data.put("bikeDeposit", bikeDeposit);
		data.put("judgeState", 1);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		return messageUtil;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public  void main(String[] args,HttpSession session) throws Exception {
//		GuideGroupInfos groupInfos = new GuideGroupInfos();
//		CouponScheme couponScheme = new CouponScheme();
//		Coupon coupon = new Coupon();
//		int needDeposit = 1;
//
//		session.setAttribute("couponSchemeId",1);
//		session.setAttribute("groupId",1);
//		if(session.getAttribute("couponSchemeId") != null && session.getAttribute("groupId") != null){//导游端优惠券开锁
//
//			Long couponSchemeId =  Long.parseLong(session.getAttribute("couponSchemeId").toString());
//			Long groupId =  Long.parseLong(session.getAttribute("groupId").toString());
//			groupInfos = guideGroupWxServiceRead.findByGroupId(groupId);
//			List<Long> userIdsBelongToGroup = guideGroupUserWxRead.findByGuideGroupId(groupId);//查询该团下游客 对比
//			boolean belongToGroup = false;
//			for (Long userIdBelongToGroup : userIdsBelongToGroup) {
//				if(userIdBelongToGroup == 3222){
//					belongToGroup = true;
//				}
//			}
//
//			//如果团进行中  则可以使用优惠券
//			if(groupInfos!=null&&(groupInfos.getGuideGroupStartTime().compareTo(new Date())==-1)
//					&& (groupInfos.getGuideGroupEndTime().compareTo(new Date())==1) && belongToGroup){//团持续时间内
//				couponScheme = couponSchemeWxServiceRead.findUnionById(couponSchemeId);
//				if(couponScheme != null) {
//					coupon = couponListWxServiceRead.findById(couponScheme.getPlanCouponId());
//					if(coupon != null&& coupon.getCouponNeedDeposit()!=null) {
//						needDeposit = coupon.getCouponNeedDeposit();
//					}
//				}
//			}
//			loggers.info("===持有导游端优惠券开锁 券方案ID==="+session.getAttribute("couponSchemeId")+"团ID==="
//					+session.getAttribute("groupId")+"用户是否属于团==="
//					+belongToGroup+"最终是否需要押金===" +needDeposit);
//		}
//		//查找用户名下可用的骑行券
//		List<Long> groupIds = guideGroupUserWxRead.findByUserId(Long.valueOf(132));//游客所在的团
//		List<GuideGroupInfos> groups = guideGroupWxServiceRead.findGroupingByGroupIds(groupIds,new Date());//游客所在的进行中的团
////			List<Long> couponSchemeIds = new ArrayList<>();
////			for (GuideGroupInfos group : groups) {
////				couponSchemeIds.add(group.getGuideGroupCouponId());
////			}
////			List<CouponScheme> couponSchemes = couponSchemeWxServiceRead.findUnionByIds(couponSchemeIds);
//		if(groups.size()>0){//暂时取一个  以后要做成由用户页面选择优惠券骑行
//			couponScheme = couponSchemeWxServiceRead.findUnionById(groups.get(0).getGuideGroupCouponId());
//			coupon = couponScheme.getCoupon();
//			needDeposit = coupon.getCouponNeedDeposit()!=null&&coupon.getCouponNeedDeposit()==0;//不需要押金？
//			loggers.info("本次骑行所属团id==="+groups.get(0).getGuideGroupId()+"方案id"+couponScheme.getPlanId()+"券id"+coupon.getCouponId());
//		}
	}
	/**
	 * 点击租借
	 * fromFlag 1 app  2wechat 3small
	 * @param
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rentBtn", method = RequestMethod.POST)
	public @ResponseBody MessageUtil rentBtn(@RequestHeader HttpHeaders header,HttpSession session, String bikeCode,Double userLongitude,Double userAtitude) throws Exception {
		loggers.info("车辆编号为==="+bikeCode);
		checkBike(header,bikeCode);
		
		String fromFlag = header.getFirst("fromFlag");
		String message = messageUtil.getMessage();
		Integer code = messageUtil.getCode();
		// 车子不能租借
		loggers.info("===车辆状态Code"+code);
		if (1 != code) {
			messageUtil.setCode(code);
			messageUtil.setMessage(message);
			return messageUtil;
		}
		Bike bike = (Bike) messageUtil.getData().get("bike");
		Models models = (Models) messageUtil.getData().get("models"); // 车的押金和租赁费用
		loggers.info("===获取车型信息==="+models.getModelsName());
		loggers.info("===获取车型渠道==="+models.getModelsChannelId());
		Map<String, Object> data = new HashMap<String, Object>();
		User userLogin = new User();

		GuideGroupInfos groupInfos = new GuideGroupInfos();
		CouponScheme couponScheme = new CouponScheme();
		Coupon coupon = new Coupon();
		int needDeposit = 1;//需要押金

		if("3".equals(fromFlag)){//小程序
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){//weixin端
			userLogin = (User) session.getAttribute("curruser");//导游优惠券验证

			if(session.getAttribute("couponSchemeId") != null && session.getAttribute("groupId") != null){//导游端优惠券开锁
				Long couponSchemeId =  Long.parseLong(session.getAttribute("couponSchemeId").toString());
				Long groupId =  Long.parseLong(session.getAttribute("groupId").toString());
				groupInfos = guideGroupWxServiceRead.findByGroupId(groupId);
				List<Long> userIdsBelongToGroup = guideGroupUserWxRead.findByGuideGroupId(groupId);//查询该团下游客 对比
				boolean belongToGroup = false;
				for (Long userIdBelongToGroup : userIdsBelongToGroup) {
					if(userIdBelongToGroup.equals(userLogin.getUserId())){
						belongToGroup = true;
					}
				}
				if(groupInfos!=null&&(groupInfos.getGuideGroupStartTime().compareTo(new Date())==-1)
						&& (groupInfos.getGuideGroupEndTime().compareTo(new Date())==1) && belongToGroup){//团持续时间内
					couponScheme = couponSchemeWxServiceRead.findUnionById(couponSchemeId);
					if(couponScheme != null&&couponScheme.getCoupon()!=null) {
						coupon = couponListWxServiceRead.findById(couponScheme.getCoupon().getCouponId());
						if(coupon != null&& coupon.getCouponNeedDeposit()!=null) {
							needDeposit = coupon.getCouponNeedDeposit();
						}
					}
				}
				loggers.info("===持有导游端优惠券开锁 券方案ID==="+session.getAttribute("couponSchemeId")+"团ID==="
						+session.getAttribute("groupId")+"用户id===" +userLogin.getUserId()+"用户是否属于团==="
						+belongToGroup+"最终是否需要押金===" +needDeposit);
			}
		}else if("1".equals(fromFlag)){//app端
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		//Thread.sleep(1000);//延时一秒

		
		User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
		loggers.info("===获取用户Id==="+user.getUserId());
		loggers.info("===获取用户账号==="+user.getUserTel());
		
		/*String qrTime  =  redisService.get(user.getUserId()+"qrTime");//用户扫码时间
		if(null!=qrTime){
			loggers.info("用户:"+user.getUserRealname()+"上次扫码时间---"+qrTime);
			Long diffSeconds = DateUtil.secondDiff(DateUtil.changStringDate(qrTime), new Date());
			if(diffSeconds<5){  //小于5秒
				messageUtil.setCode(0);
				messageUtil.setMessage("扫码过于频繁");
				return messageUtil;
			}
		}
		redisService.set(user.getUserId()+"qrTime", DateUtil.format(new Date()));
		//关闭jedis
		redisService.closeJedis();*/
		
		if(null==user.getUserIdcard()||user.getUserIdcard().equals("")){
			data.put("user", user);
			data.put("judgeState", 13);
			messageUtil.setData(data);
			messageUtil.setCode(13);
			messageUtil.setMessage("请先实名");
			return messageUtil;
		}
		loggers.info("===获取用户状态==="+user.getUserState());
		if (0 != user.getUserState()) { // 若用户不是在空闲状态
			if (1 == user.getUserState()) { // 如果是租借状态
				BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findNotFinishByUserId(user.getUserId());// 查找未结束的租赁
				loggers.info("===获取用户未结束订单===");
				data.put("bikeRentInfo", bikeRentInfo);
				if (null == bikeRentInfo.getRentEndtime()) {// 行程未结束
					messageUtil.setMessage("还有租赁未完成");
					messageUtil.setCode(3);
					data.put("judgeState", 3);
					messageUtil.setData(data);
					return messageUtil;
				}
				messageUtil.setMessage("还有订单未完成");
				messageUtil.setCode(4);
				data.put("judgeState", 4);
				messageUtil.setData(data);
				return messageUtil;

			} else { // 用户被删除删除
				messageUtil.setCode(5);
				data.put("judgeState", 5);
				messageUtil.setMessage("账号已被删除");
			}
		}
		
		Account account = accountWxServiceRead.findByAccountId(user.getUserAccountId());// 用户账户
		loggers.info("===获取用户账户Id==="+account.getAccountId());
		loggers.info("===是否仅限会员使用==="+models.getModelsRentLimit());
		loggers.info("===用户渠道==="+user.getUserChannelId());
		loggers.info("导游发券是否需要押金==="+needDeposit);
		if((null!=user.getUserChannelId()) && needDeposit!=0){
			if(!user.getUserChannelId().equals(models.getModelsChannelId())){
				if(models.getModelsRentLimit()==2){//仅限会员使用
					loggers.info("===仅限会员使用===");
					messageUtil.setCode(0);
					data.put("judgeState", 0);
					messageUtil.setMessage("此车无法使用");
					return messageUtil;
				}
				if (-1 == account.getAccountDeposit().compareTo(models.getModelsDeposit())) {
					loggers.info("===请补全预付款===");
					messageUtil.setCode(6);
					data.put("bikeDeposit", models.getModelsDeposit());
					data.put("deposit", account.getAccountDeposit());
					data.put("models", models);
					data.put("account", account);
					data.put("judgeState", 6);
					messageUtil.setData(data);
					messageUtil.setMessage("请补全预付款");
					return messageUtil;
				}
			}else{
				
                if(models.getModelsRentType()==1){
                	if(models.getModelsRentLimit()==2){//仅限会员使用
                		loggers.info("===仅限会员使用===");
						messageUtil.setCode(0);
						data.put("judgeState", 0);
						messageUtil.setMessage("此车无法使用");
						return messageUtil;
					}
                	if (-1 == account.getAccountDeposit().compareTo(models.getModelsDeposit())) {
                		loggers.info("===请补全预付款===");
    					messageUtil.setCode(6);
    					data.put("bikeDeposit", models.getModelsDeposit());
    					data.put("deposit", account.getAccountDeposit());
    					data.put("models", models);
    					data.put("account", account);
    					data.put("judgeState", 6);
    					messageUtil.setData(data);
    					messageUtil.setMessage("请补全预付款");
    					return messageUtil;
    				}
				}else if(models.getModelsRentType()==2){
					if (1 != account.getAccountAvailableBalance().compareTo(new BigDecimal(10))) {//账户余额
						loggers.info("===请充值余额===");
						messageUtil.setCode(9);
						data.put("judgeState", 9);
						data.put("availableBalance", account.getAccountAvailableBalance());
						messageUtil.setData(data);
						messageUtil.setMessage("请充值余额");
						return messageUtil;
					}
				}
				
				
			}		
		}else if((null==user.getUserChannelId()) && needDeposit != 0){
			if(models.getModelsRentLimit()==2){//仅限会员使用
				loggers.info("===仅限会员使用===");
				messageUtil.setCode(0);
				data.put("judgeState", 0);
				messageUtil.setMessage("此车无法使用");
				return messageUtil;
			}
			if (-1 == account.getAccountDeposit().compareTo(models.getModelsDeposit())) {
				loggers.info("===请补全预付款===");
				messageUtil.setCode(6);
				data.put("bikeDeposit", models.getModelsDeposit());
				data.put("deposit", account.getAccountDeposit());
				data.put("models", models);
				data.put("account", account);
				data.put("judgeState", 6);
				messageUtil.setData(data);
				messageUtil.setMessage("请补全预付款");
				return messageUtil;
			}
		}
		
		
		BikeLockInfo lockInfo = bikeLockInfoWxServiceRead.findLockInfoById(bike.getBikeId());
		Double lowpower = Double.valueOf(sysParamentServiceRead.findByName("lowpower").getSysParamentValue());
		//低电量提示
		loggers.info("===获取锁编号==="+lockInfo.getBikeLockCode());
		loggers.info("===获取锁电量==="+lockInfo.getBikeLockVoltage());
		if(null!=lockInfo.getBikeLockVoltage()&&!"".equals(lockInfo.getBikeLockVoltage())&&lockInfo.getBikeLockVoltage()<lowpower){
			loggers.info("低电量");
			messageUtil.setCode(7);
			data.put("judgeState", 7);
			messageUtil.setData(data);
			messageUtil.setMessage("电量不足");
			return messageUtil;
		} 
		if(!lockService.judgeLockConnet(lockInfo.getBikeLockCode())){
			loggers.info("锁未连接上");
			messageUtil.setCode(0);
			data.put("judgeState", 0);
			messageUtil.setData(data);
			messageUtil.setMessage("该车车锁故障，请更换车辆出行或稍后再试");
			return messageUtil;
		}
		
		
		
		// 更改人物状态
		
		user.setUserState(1);// 租借
		if(null==user.getUserFistusebikeid()){
			user.setUserFistusebikeid(bike.getBikeId());
			user.setUserFistusebiketime(new Date());
		}
		user.setUserLastusebiketime(new Date());
		userServiceWeixinWrite.updateUser(user);
		loggers.info("更改人物状态====");
		
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setRentInfoBikeId(bike.getBikeId());
		bikeRentInfo.setRentBikeChannelId(models.getModelsChannelId());
		if(bike.getBikeFixedReturnId()!=null&&0!=bike.getBikeFixedReturnId()){
			bikeRentInfo.setRentStartFixedId(bike.getBikeFixedReturnId());//起始还车点
		}else{
			BikeRentInfo recentBikeRenInfo = bikeRentInfoWxServiceRead.findRecentRentInfo(bike.getBikeId());
			if(recentBikeRenInfo!=null){
				if(recentBikeRenInfo.getRentEndFixedId()!=null){
					bikeRentInfo.setRentStartFixedId(recentBikeRenInfo.getRentEndFixedId());//起始还车点
				}
			}
			
		}
		loggers.info("添加起始还车点====");
		
		if(null!=user.getUserChannelId()){
            if(!user.getUserChannelId().equals(models.getModelsChannelId())){
            	bikeRentInfo.setRentIsvillager(0);//游客
			}else{
				if(models.getModelsRentType()==1){
					bikeRentInfo.setRentIsvillager(0);//游客
				}else if(models.getModelsRentType()==2){
					bikeRentInfo.setRentIsvillager(1);//是会员
				}
				
			}
		}else{
			bikeRentInfo.setRentIsvillager(0);//游客
		}
			
		bikeRentInfo.setRentInfoUserId(user.getUserId());
		bikeRentInfo.setRentStarttime(new Date());
		if(null!=models.getModelsInpriceId()){
			InsurancePrice insurancePrice = insurancePriceWxServiceRead.findById(models.getModelsInpriceId());//查询保险费用
			bikeRentInfo.setRentInsurancePrice(insurancePrice.getInPrice());
		}
		bikeRentInfo.setRentStartlat(bike.getBikeAtitude());
		bikeRentInfo.setRentStartlng(bike.getBikeLongitude());
		bikeRentInfo.setRentState(2); // 未完成
		// 订单号 = "标志"+时间+车辆id+用户id
		String rentOrderCode = "Z" + DateUtil.format03(new Date()) + bike.getBikeId() + userLogin.getUserId();
		bikeRentInfo.setRentOrderCode(rentOrderCode);
		//bikeRentInfo.setRentStartBlockId(bike.getBikeBlockId()); // 方格
		bikeRentInfo.setRentStartBlock(bike.getBikeBlock()); // 方格
		if(coupon.getCouponId()!=null){ //使用导游端优惠券的订单
			loggers.info("订单加入优惠券id====="+coupon.getCouponId());
			bikeRentInfo.setRentCouponId(coupon.getCouponId());//优惠券
			bikeRentInfo.setRentUnlockWay(0);//代开锁
			bikeRentInfo.setRentGuideGroupId(groupInfos.getGuideGroupId());
		}
		bikeRentInfo.setRentInfoId(bikeRentInfoWxServiceWrite.addRentInfo(bikeRentInfo));
		loggers.info("生成订单====="+bikeRentInfo.getRentInfoId());
		session.setAttribute("curruser", user);// 更新 session
		bike.setBikeState(2); // 更改 bike 状态
		bike.setBikeRentNum(bike.getBikeRentNum() + 1);// 租借次数
		bikeWxServiceWrite.updateBike(bike);// 更新
		
		/*if(!lockService.sendStateMessage(lockInfo.getBikeLockCode())){//获取锁电压
			messageUtil.setCode(0);
			messageUtil.setMessage("fail");
			return messageUtil;
		}
		Thread.sleep(3000);*/
		//BikeLockInfo nowLockInfo = bikeLockInfoWxServiceRead.findLockInfoById(bike.getBikeId());//查询现在车辆电压

		
		//默认开锁成功将数据存入redis 功能标志/用户ID/车ID/订单ID
		redisService.set(lockInfo.getBikeLockCode(), "1,"+user.getUserId()+","+bike.getBikeId()+","+bikeRentInfo.getRentInfoId(),60000);
		//关闭jedis
		redisService.closeJedis();
		
		ThreadMapUtil.threadMap.put(lockInfo.getBikeLockCode(), this);
		synchronized (this) {
			if (!sendCommand(bike, 1, 0)) {
				messageUtil.setCode(0);
				data.put("judgeState", 0);
				messageUtil.setData(data);
				messageUtil.setMessage("该车车锁故障，请更换车辆出行或稍后再试");
				return messageUtil;
			}
			loggers.info("发送开锁命令成功 等待15秒开始=====");
			//15s超时
			this.wait(15000);
			loggers.info("发送开锁命令成功 等待15秒结束=====");
		}
		try {
			if(""!=redisService.get("r"+lockInfo.getBikeLockCode())&&null!=redisService.get("r"+lockInfo.getBikeLockCode())){
				//锁返回信息
				String rdata = redisService.get("r"+lockInfo.getBikeLockCode());
				loggers.info("锁返回信息=====");
				if("success".equals(rdata)){
					loggers.info("开锁成功=====");
					//如果是定点还车
					if(models.getModelsIsfixedPoint()==1){
						Bike nowBike = bikeWxServiceRead.findByBikeId(bike.getBikeId());
						FixedReturn nowFixedReturn = fixedReturnWxServiceRead.findByFixedId(nowBike.getBikeFixedReturnId());
						if(null!=nowFixedReturn){
							fixedReturnWxServiceWrite.update(nowFixedReturn,1);
						}
						nowBike.setBikeFixedReturnId((long)0);//离开站点为0
						bikeWxServiceWrite.updateBike(nowBike);// 更新
					}
					data.put("judgeState", 1);
					messageUtil.setCode(1);
					messageUtil.setData(data);
					messageUtil.setMessage("success");
				}else if("4".equals(rdata)||"5".equals(rdata)){
					loggers.info("开锁失败=====");
					//开锁受阻
					messageUtil.setCode(0);
					data.put("judgeState", 0);
					messageUtil.setData(data);
					messageUtil.setMessage("开锁失败，车锁受阻，请确保车辆插销未被阻挡再开锁");
				}else if("1".equals(rdata)||"3".equals(rdata)){
					//开锁超时
					loggers.info("开锁失败=====");
					messageUtil.setCode(0);
					data.put("judgeState", 0);
					messageUtil.setData(data);
					messageUtil.setMessage("开锁超时，该车可能有故障，请选择其它车租赁或稍后再试");
				}
			}else{
				loggers.info("开锁失败=====");
				messageUtil.setCode(0);
				data.put("judgeState", 0);
				messageUtil.setData(data);
				messageUtil.setMessage("开锁超时，该车可能有故障，请选择其它车租赁或稍后再试");
//				messageUtil.setMessage("fail");
			}
		} catch (Exception e) {
			// TODO: handle exception
			loggers.error("异常信息",e);
		}finally {
			redisService.del("r"+lockInfo.getBikeLockCode());
			redisService.closeJedis();
		}
		
		if(null!=bikeRentInfo){
			if(null!=bikeRentInfo.getRentStartlng()&&null!=bikeRentInfo.getRentStartlat()){
				String startName = GetLocationUtil.getAdd(bikeRentInfo.getRentStartlng().toString(), bikeRentInfo.getRentStartlat().toString());
				if(null==startName){
					startName = "暂无信息";
				}
				bikeRentInfo.setStartFixedName(startName);
			}
		}
		data.put("bike", bike);
		data.put("models", models);
		data.put("account", account);
		data.put("bikeRentInfo", bikeRentInfo);
		messageUtil.setData(data);
//		messageUtil.setCode(1);
		return messageUtil;
	}

	/**
	 * 临时还车
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/temporary", method = RequestMethod.POST)
	public @ResponseBody MessageUtil temporary(@RequestHeader HttpHeaders header,Long rentInfoId,HttpSession session) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
		String fromFlag = header.getFirst("fromFlag");
		User user = new User();
		if("3".equals(fromFlag)){//小程序
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			user = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){//weixin端
			user = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){//app端
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			user = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==user){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		if(null==user||!user.getUserId().equals(bikeRentInfo.getRentInfoUserId())){
			data.put("judgeState", -1);
			messageUtil.setData(data);
			messageUtil.setCode(-1);
			messageUtil.setMessage("该订单出错,不允许替他人操作");
			return messageUtil;
		}
		data.put("rentInfoId", rentInfoId);
		messageUtil.setData(data);
		if (1 == bikeRentInfo.getRentState()) { // 租赁已完成
			data.put("judgeState",0);
			messageUtil.setData(data);
			messageUtil.setCode(0);
			messageUtil.setMessage("租赁已完成");
			return messageUtil;
		}
		Bike bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
		/*if (0 == bike.getBikeState()) {// 如果车子是空闲状态
			messageUtil.setCode(4);
			messageUtil.setMessage("该行程已结束");
			return messageUtil;
		}*/
		if (null != bikeRentInfo.getRentEndtime()) { // 租赁已完成
			data.put("judgeState", 4);
			messageUtil.setData(data);
			messageUtil.setCode(4);
			messageUtil.setMessage("该行程已结束");
			return messageUtil;
		}
		BikeLockInfo lockInfo = bikeLockInfoWxServiceRead.findLockInfoById(bike.getBikeId());
		if(!"".equals(lockInfo.getBikeLockType())&&null!=lockInfo.getBikeLockType()&&1==lockInfo.getBikeLockType()&&lockInfo.getBikeLockStatus()==1){
			//半自动锁，未上锁直接点击临时上锁
			data.put("judgeState", 12);
			messageUtil.setData(data);
			messageUtil.setCode(12);
			return messageUtil;
		}
		if(!lockService.judgeLockConnet(lockInfo.getBikeLockCode())){//检查锁是否在线
			data.put("judgeState", 0);
			messageUtil.setData(data);
			messageUtil.setCode(0);
			messageUtil.setMessage("fail");
			return messageUtil;
		}
		//全自动锁发送关锁命令，并且线程等待
		 if(lockInfo.getBikeLockType()==null||lockInfo.getBikeLockType()==0){
			//功能标志/车ID/订单ID
			redisService.set(lockInfo.getBikeLockCode(), "2,"+bike.getBikeId()+","+rentInfoId,60000);
			//关闭jedis
			redisService.closeJedis();
			if (!sendCommand(bike, 0, 1)) {// 关锁
				data.put("judgeState", 0);
				messageUtil.setData(data);
				messageUtil.setCode(0);
				messageUtil.setMessage("fail");
				return messageUtil;
			}
			
			ThreadMapUtil.threadMap.put(lockInfo.getBikeLockCode(), this);
			synchronized (this) {
				//15s超时
				this.wait(15000);
			}
			
			try {
				if(""!=redisService.get("r"+lockInfo.getBikeLockCode())&&null!=redisService.get("r"+lockInfo.getBikeLockCode())){
					//锁返回信息
					String rdata = redisService.get("r"+lockInfo.getBikeLockCode());
					if("success".equals(rdata)){
						//半自动锁，查询车锁状态是否已关锁
//						if(!"".equals(lockInfo.getBikeLockType()) && null != lockInfo.getBikeLockType()&&lockInfo.getBikeLockType()==1){
//							Bike rbike = new Bike();
//							rbike.setBikeId(bike.getBikeId());
//							rbike.setBikeState(1);
//							bikeWxServiceWrite.updateBike(rbike);
//						}
						data.put("judgeState", 1);
						messageUtil.setCode(1);
						messageUtil.setMessage("success");
					}else if("4".equals(rdata)||"5".equals(rdata)){
						//关锁受阻
						data.put("judgeState", 10);
						messageUtil.setCode(10);
					}else if("1".equals(rdata)||"3".equals(rdata)){
						//关锁超时
						data.put("judgeState", 11);
						messageUtil.setCode(11);
					}
				}else{
					data.put("judgeState", 11);
					messageUtil.setCode(11);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				redisService.del("r"+lockInfo.getBikeLockCode());
				redisService.closeJedis();
			}
			
		}else if(lockInfo.getBikeLockType()==1){
			if(lockInfo.getBikeLockStatus()==0){
				Bike rbike = new Bike();
				rbike.setBikeId(bike.getBikeId());
				rbike.setBikeState(1);
				bikeWxServiceWrite.updateBike(rbike);
				data.put("judgeState", 1);
				messageUtil.setCode(1);
				messageUtil.setMessage("success");
			}
		}
		
		
		messageUtil.setData(data);
		return messageUtil;
	}

	/**
	 * 继续骑行
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/continueBike", method = RequestMethod.POST)
	public @ResponseBody MessageUtil continueBike(@RequestHeader HttpHeaders header,Long rentInfoId,HttpSession session) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
		String fromFlag = header.getFirst("fromFlag");
		User user = new User();
		if("3".equals(fromFlag)){//小程序
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			user = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){//weixin端
			user = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){//app端
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			user = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==user){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		loggers.info("租赁订单为"+rentInfoId);
		if(null==user||!user.getUserId().equals(bikeRentInfo.getRentInfoUserId())){
			data.put("judgeState", -1);
			messageUtil.setData(data);
			messageUtil.setCode(-1);
			messageUtil.setMessage("该订单出错,不允许替他人操作");
			return messageUtil;
		}
		Bike bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
		loggers.info("车辆编号为"+bike.getBikeCode());
		if (1 == bikeRentInfo.getRentState()) { // 租赁已完成
			loggers.info("订单已完成");
			data.put("judgeState", 0);
			messageUtil.setData(data);
			messageUtil.setCode(0);
			messageUtil.setMessage("订单已完成");
			return messageUtil;
		}
		/*if (0 == bike.getBikeState()) {// 如果车子是空闲状态
			messageUtil.setCode(4);
			messageUtil.setMessage("该行程已结束");
			return messageUtil;
		}*/
		if (null != bikeRentInfo.getRentEndtime()) { // 租赁已完成
			loggers.info("租赁已完成");
			data.put("judgeState", 4);
			messageUtil.setData(data);
			messageUtil.setCode(4);
			messageUtil.setMessage("该行程已结束");
			return messageUtil;
		}
		BikeLockInfo lockInfo = bikeLockInfoWxServiceRead.findLockInfoById(bike.getBikeId());
		if(!lockService.judgeLockConnet(lockInfo.getBikeLockCode())){
			loggers.info("锁未连接");
			data.put("judgeState", 0);
			messageUtil.setData(data);
			messageUtil.setCode(0);
			messageUtil.setMessage("fail");
			return messageUtil;
		}
		
//		if(lockInfo.getBikeLockStatus()==1){
//			Bike openbike = new Bike();
//			openbike.setBikeId(bike.getBikeId());
//			openbike.setBikeState(2);
//			bikeWxServiceWrite.updateBike(openbike);
//			messageUtil.setCode(1);
//			messageUtil.setMessage("success");
//			data.put("rentInfoId", rentInfoId);
//			messageUtil.setData(data);
//			return messageUtil;
//		}
		
		//默认开锁成功将数据存入redis 功能标志/车ID/订单ID
		redisService.set(lockInfo.getBikeLockCode(), "3,"+bike.getBikeId()+","+rentInfoId,60000);
		//关闭jedis
		redisService.closeJedis();
		ThreadMapUtil.threadMap.put(lockInfo.getBikeLockCode(), this);
		synchronized (this) {
			if (!sendCommand(bike, 1, 2)) {// 是否开锁
				data.put("judgeState", 0);
				messageUtil.setData(data);
				messageUtil.setCode(0);
				messageUtil.setMessage("fail");
				return messageUtil;
			}
			loggers.info("发送开锁命令");
			//40s超时
			this.wait(15000);
		}
		try {
			if(""!=redisService.get("r"+lockInfo.getBikeLockCode())&&null!=redisService.get("r"+lockInfo.getBikeLockCode())){
				//锁返回信息
				loggers.info("锁返回信息");
				String rdata = redisService.get("r"+lockInfo.getBikeLockCode());
				if("success".equals(rdata)){
					loggers.info("开锁成功");
					BikeRentInfo nowbikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
					Bike nowbike = bikeWxServiceRead.findByBikeId(nowbikeRentInfo.getRentInfoBikeId());
					nowbike.setBikeTemporarylocktime(null);
					bikeWxServiceWrite.updateBikeCanNull(nowbike);
					data.put("judgeState", 1);
					messageUtil.setCode(1);
					messageUtil.setMessage("success");
				}else if("4".equals(rdata)||"5".equals(rdata)){
					loggers.info("开锁失败");
					//开锁受阻
					data.put("judgeState", 0);
					messageUtil.setCode(0);
					messageUtil.setMessage("开锁失败，车锁受阻，请确保车辆插销未被阻挡再开锁");
				}else if("1".equals(rdata)||"3".equals(rdata)){
					loggers.info("开锁失败");
					//开锁超时
					data.put("judgeState", 0);
					messageUtil.setCode(0);
					messageUtil.setMessage("开锁超时，该车可能有故障，请选择其它车租赁或稍后再试");
				}
				redisService.del("r"+lockInfo.getBikeLockCode());
			}else{
				loggers.info("开锁失败"+rentInfoId);
				data.put("judgeState", 0);
				messageUtil.setCode(0);
				messageUtil.setMessage("开锁超时，该车可能有故障，请选择其它车租赁或稍后再试");
			}
		} catch (Exception e) {
			loggers.error("异常信息==", e);
		}finally {
			redisService.closeJedis();
		}
		
		
		
		data.put("rentInfoId", rentInfoId);
		messageUtil.setData(data);
		return messageUtil;
	}

	/**
	 * 用车结束
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rentBike", method = RequestMethod.POST)
	public @ResponseBody MessageUtil rentBike(@RequestHeader HttpHeaders header,@RequestParam(defaultValue = "0") Integer returnFlag, Long rentInfoId,Double userLongitude,Double userAtitude,HttpSession session,@RequestParam(defaultValue = "0")Integer isIOS)
			throws Exception {
		FixedReturn fixedReturn1 = null;
		Map<String, Object> data = new HashMap<String, Object>();
		BikeRentInfo rentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
		loggers.info("租赁订单为："+rentInfoId);
		String fromFlag = header.getFirst("fromFlag");
		User user = new User();
		if("3".equals(fromFlag)){//小程序
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			user = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){//weixin端
			user = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){//app端
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			user = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==user){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		if(null==user||!user.getUserId().equals(rentInfo.getRentInfoUserId())){
			data.put("judgeState", -1);
			messageUtil.setData(data);
			messageUtil.setCode(-1);
			messageUtil.setMessage("该订单出错,不允许替他人操作");
			return messageUtil;
		}
		Bike bike = bikeWxServiceRead.findByBikeId(rentInfo.getRentInfoBikeId());
		data.put("bikeRentInfo", rentInfo);
		messageUtil.setData(data);
		if (1 == rentInfo.getRentState()) { //订单已完成
			loggers.info("订单已完成");
			data.put("judgeState", 0);
			messageUtil.setData(data);
			messageUtil.setMessage("关锁失败");
			return messageUtil;
		}
		Account account = accountWxServiceRead.findByUserId(user.getUserId());
		/*if (0 == bike.getBikeState()) {// 如果车子是空闲状态
			messageUtil.setCode(1);
			messageUtil.setMessage("该行程已结束");
			return messageUtil;
		}*/
//		Bike bike = bikeWxServiceRead.findByBikeId(rentInfo.getRentInfoBikeId());

		Models models = modelsWxServiceRead.findModelsById(bike.getBikeModelsId()); // 查车的型号
		User nowUser = userServiceWeixinRead.findByUId(user.getUserId());
		loggers.info("车辆编号为："+bike.getBikeCode());	
		BikeLockInfo lockInfo = bikeLockInfoWxServiceRead.findLockInfoById(bike.getBikeId());
		loggers.info("锁编号为："+lockInfo.getBikeLockCode());
		if (1 == returnFlag) { // 结束行程
			if (null != rentInfo.getRentEndtime()) { // 租赁已完成
				loggers.info("租赁已完成");
				data.put("judgeState", 4);
				messageUtil.setData(data);
				messageUtil.setCode(4);
				messageUtil.setMessage("该行程已结束");
				return messageUtil;
			}
			if(!"".equals(lockInfo.getBikeLockType())&&null!=lockInfo.getBikeLockType()&&1==lockInfo.getBikeLockType()&&lockInfo.getBikeLockStatus()==1){
				//半自动锁，未上锁直接点击临时上锁
				loggers.info("未上锁=====");
				data.put("judgeState", 12);
				messageUtil.setData(data);
				messageUtil.setCode(12);
				return messageUtil;
			}
			
			Integer isfar = 1;//判断距离结果(0代表可以1代表距离太远)
			if(models.getModelsIsfixedPoint()==1){//如果是为定点还车
				
//				Integer isfar = 1;//判断距离结果(0代表可以1代表距离太远)
				List<DataDet> typeList = dataDetWxServiceRead.findDataDetByDataId((long) 7);//获取误差距离
				Double errorDistance = 0.0;
				List<FixedReturn> fixedReturns = new ArrayList<FixedReturn>();
				if(typeList.size()>0){
					errorDistance = Double.valueOf(typeList.get(0).getDataDetVal());
				}
				
				if(null!=models.getModelsFixedReturn()&&!models.getModelsFixedReturn().equals("")){
					String[] split = SplitUtil.toSplit(models.getModelsFixedReturn());
					for(int i=0;i<split.length;i++){
						FixedReturn fixedReturn = fixedReturnWxServiceRead.findByFixedId(Long.valueOf(split[i]));
						fixedReturns.add(fixedReturn);
					}
					
				}
				Long fixedReturnId = null;
				if(null!=fixedReturns){
					loggers.info("纬度"+userAtitude);
					loggers.info("经度"+userLongitude);
					for(FixedReturn fr : fixedReturns){
						double distance=DistanceUtil.LantitudeLongitudeDist(fr.getFixedReturnLng(), fr.getFixedReturnLat(), userLongitude, userAtitude);
						loggers.info("相差的距离"+distance);
						loggers.info(fr.getFixedReturnName()+":"+fr.getFixedReturnDistance());
						if(distance<(fr.getFixedReturnDistance()+errorDistance)){
							isfar = 0;
							loggers.info("是在还车点内=====");
							break;
						}
					}
					
					SysParament sparament = sysParamentServiceRead.findByName("fixed_distance");
					for(FixedReturn fr : fixedReturns){
						double distance=DistanceUtil.LantitudeLongitudeDist(fr.getFixedReturnLng(), fr.getFixedReturnLat(), userLongitude, userAtitude);
						if(distance<Double.valueOf(sparament.getSysParamentValue())){
							fixedReturnId = fr.getFixedReturnId();
							break;
						}
					}
					
					if(lockInfo.getBikeLockIsFence()==1){//是使用电子围栏
						//电子围栏
						try {
							if(""!=redisService.get("r2"+lockInfo.getBikeLockCode())&&null!=redisService.get("r2"+lockInfo.getBikeLockCode())){
								//电子围栏内还车
								isfar = 0;
								redisService.del("r2"+lockInfo.getBikeLockCode());
								loggers.info(lockInfo.getBikeLockCode()+"电子围栏还车成功");
							}
						} catch (Exception e) {
							// TODO: handle exception
						}finally {
							redisService.closeJedis();
						}
						
					}
					
					
					if(isfar==1){
						data.put("judgeState", 8);
						messageUtil.setData(data);
						messageUtil.setCode(8);
						messageUtil.setMessage("抱歉，当前位置距离租赁点太远，请到租赁点附近再尝试结束行程...");
//						BikeLockInfo lockInfo = bikeLockInfoWxServiceRead.findLockInfoById(bike.getBikeId());
						//车锁状态为已锁并且车锁类型为半自动锁
						if(!"".equals(lockInfo.getBikeLockType())&&null!=lockInfo.getBikeLockType()&&1==lockInfo.getBikeLockType()&&lockInfo.getBikeLockStatus()==0){
							Bike farBike = new Bike();
							farBike.setBikeId(bike.getBikeId());
							//车辆状态为租借中
							farBike.setBikeState(2);
							bikeWxServiceWrite.updateBike(farBike);
							//半自动锁，未在定点直接打开锁
							lockService.sendUnlockMessage(lockInfo.getBikeLockCode());
							
						}
						return messageUtil;
					}
				}
				if(null!=fixedReturnId){
					fixedReturn1 = fixedReturnWxServiceRead.findByFixedId(fixedReturnId);
				}
			}
			
			
			if(!lockService.judgeLockConnet(lockInfo.getBikeLockCode())){
				loggers.info("锁未连接上");
				data.put("judgeState", 0);
				messageUtil.setData(data);
				messageUtil.setCode(0);
				messageUtil.setMessage("关锁失败");
				return messageUtil;
			}
			//全自动锁，结束行程时发送关锁命令，并线程等待
			if(lockInfo.getBikeLockType()==null||lockInfo.getBikeLockType()==0){
				//将数据存入redis 功能标志/车ID/订单ID
				redisService.set(lockInfo.getBikeLockCode(), "4,"+bike.getBikeId()+","+rentInfoId+","+userLongitude+","+userAtitude,60000);
				//关闭jedis
				redisService.closeJedis();
				if (!sendCommand(bike, 0, 2)) {
					messageUtil.setCode(0);
					data.put("judgeState", 0);
					messageUtil.setData(data);
					messageUtil.setMessage("关锁失败");
					return messageUtil;
				}
				
				ThreadMapUtil.threadMap.put(lockInfo.getBikeLockCode(), this);
				synchronized (this) {
					//15s超时
					this.wait(15000);
				}
				
				try {
					if(""!=redisService.get("r"+lockInfo.getBikeLockCode())&&null!=redisService.get("r"+lockInfo.getBikeLockCode())){
						//锁返回信息
						String rdata = redisService.get("r"+lockInfo.getBikeLockCode());
						if("success".equals(rdata)){
							//如果是定点还车
							if(models.getModelsIsfixedPoint()==1){
								if(null!=fixedReturn1){
									Bike nowBike = bikeWxServiceRead.findByBikeId(rentInfo.getRentInfoBikeId());
									fixedReturnWxServiceWrite.update(fixedReturn1,2);//更新站点车辆数量
									nowBike.setBikeFixedReturnId(fixedReturn1.getFixedReturnId());//更新车所在站点
									bikeWxServiceWrite.updateBike(nowBike);// 更新
									BikeRentInfo nowRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
									nowRentInfo.setRentEndFixedId(fixedReturn1.getFixedReturnId());//结束所在还车点
									bikeRentInfoWxServiceWrite.updateRentInfo(rentInfo);
								}
							}
							//还车成功删除用户开锁记录
							redisService.del("userOpen"+lockInfo.getBikeLockCode());
							messageUtil.setCode(1);
							data.put("judgeState", 1);
							messageUtil.setMessage("success");
						}else if("4".equals(rdata)||"5".equals(rdata)){
							//关锁受阻
							data.put("judgeState", 10);
							messageUtil.setCode(10);
						}else if("1".equals(rdata)||"3".equals(rdata)){
							//关锁超时
							data.put("judgeState", 11);
							messageUtil.setCode(11);
						}
					}else{
						data.put("judgeState", 11);
						messageUtil.setCode(11);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					redisService.del("r"+lockInfo.getBikeLockCode());
					redisService.closeJedis();
				}
			}else if(!"".equals(lockInfo.getBikeLockType())&&lockInfo.getBikeLockType()==1){
				try {
					if(lockInfo.getBikeLockStatus()==0){
						//半自动锁
						User nowuser = userServiceWeixinRead.findByUId(user.getUserId()); 
						Date nowDay = new Date();
						loggers.info("开始时间"+rentInfo.getRentStarttime());
						loggers.info("结束时间"+nowDay);
						Double nowMoney = getRentMoney(nowuser,models,rentInfo,nowDay);
						loggers.info("费用为"+nowMoney);
						Long rideTime = DateUtil.minuteDiff1(rentInfo.getRentStarttime(), nowDay);
						rentInfo.setRentEndtime(nowDay); // 还车时间
						rentInfo.setRentLongtime(Integer.parseInt(rideTime.toString()));
						rentInfo.setRentPrice(new BigDecimal(nowMoney));
						// 还车方格id
						String latLng = userLongitude + "," + userAtitude;
						String blockCode = BlockUtil.getBlockCode(latLng);
						//Long rentEndBlockId = blockWxServiceRead.findBlockId(blockCode);
						rentInfo.setRentEndBlock(blockCode);
						rentInfo.setRentEndlat(userAtitude);
						rentInfo.setRentEndlng(userLongitude);
						if(models.getModelsIsfixedPoint()==1){
							if(null!=fixedReturn1){
								rentInfo.setRentEndFixedId(fixedReturn1.getFixedReturnId());//结束所在还车点
							}
						}
						
						bikeRentInfoWxServiceWrite.updateRentInfo(rentInfo);
						loggers.info("更新订单："+rentInfo.getRentInfoId());
						//更改车辆经纬度
						bike.setBikeLongitude(userLongitude);
						bike.setBikeAtitude(userAtitude);
						bike.setBikeState(0);// 空闲中
						//bike.setBikeBlockId(rentEndBlockId);
						bike.setBikeBlock(blockCode);
						bike.setBikeLastRentTime(new Date());
						bikeWxServiceWrite.updateBike(bike);
						loggers.info("更新车辆："+bike.getBikeCode());
						//锁的定位
						lockInfo.setBikeLockLng(userLongitude);
						lockInfo.setBikeLockLat(userAtitude);
						bikeLockInfoWxServiceWrite.updateBikeLock(lockInfo);
						loggers.info("更新锁："+lockInfo.getBikeLockCode());
						lockService.sendLocationMessage(lockInfo.getBikeLockCode());
						//如果是定点还车
						if(models.getModelsIsfixedPoint()==1){
							if(null!=fixedReturn1){
								Bike nowBike = bikeWxServiceRead.findByBikeId(rentInfo.getRentInfoBikeId());
								fixedReturnWxServiceWrite.update(fixedReturn1,2);
								nowBike.setBikeFixedReturnId(fixedReturn1.getFixedReturnId());//更新车所在站点
								bikeWxServiceWrite.updateBike(nowBike);// 更新
							}
						}
						Bike nowBike = bikeWxServiceRead.findByBikeId(rentInfo.getRentInfoBikeId());
						nowBike.setBikeTemporarylocktime(null);
						bikeWxServiceWrite.updateBikeCanNull(nowBike);
						if("1".equals(fromFlag)){//app端
							if(null!=rentInfo.getRentStarttime()&&null!=rentInfo.getRentEndtime()){
								loggers.info("开始时间："+rentInfo.getRentStarttime().getTime()/1000);
								loggers.info("结束时间："+rentInfo.getRentEndtime().getTime()/1000);
								StringBuffer pointsJson = GetLocationUtil.getPonts(rentInfo.getRentStarttime().getTime()/1000, rentInfo.getRentEndtime().getTime()/1000, user.getUserId(),isIOS);//获取轨迹点
								String distance = GetLocationUtil.getDistance(rentInfo.getRentStarttime().getTime()/1000, rentInfo.getRentEndtime().getTime()/1000, user.getUserId(),isIOS);//米
								Long time = DateUtil.minuteDiff1(rentInfo.getRentStarttime(), rentInfo.getRentEndtime());//骑行时间 分钟
								loggers.info("骑行时间："+(time/60.0));
								nowuser.setUserTotalRideTime(nowuser.getUserTotalRideTime()+(time/60.0));
								if(null!=pointsJson){
									Orbit orbit = new Orbit();
									orbit.setOrbitLatlngJson(pointsJson.toString());
									orbit.setOrbitRentInfoId(rentInfo.getRentInfoId());
									if(null!=distance){
										orbit.setOrbitDistance(distance);
										nowuser.setUserTotalDistance(nowuser.getUserTotalDistance()+(Double.valueOf(distance)/1000));
										if(null!=nowuser.getUserGender()){
											if(nowuser.getUserGender()==1){//男
												Double calorie = 0.13*66.2*9.7*(Double.valueOf(distance)/1000);//摩擦系数*男性平均体重*重力加速度*骑行距离
												nowuser.setUserTotalCalorie(nowuser.getUserTotalCalorie()+calorie);
											}else if(nowuser.getUserGender()==2){//女
												Double calorie = 0.13*57.3*9.7*(Double.valueOf(distance)/1000);//摩擦系数*女性平均体重*重力加速度*骑行距离
												nowuser.setUserTotalCalorie(nowuser.getUserTotalCalorie()+calorie);
											}
										}else{//默认男
											Double calorie = 0.13*66.2*9.7*(Double.valueOf(distance)/1000);
											nowuser.setUserTotalCalorie(nowuser.getUserTotalCalorie()+calorie);
										}
										
										
									}
									orbitServiceWxWrite.add(orbit);//添加轨迹点记录
									loggers.info("添加轨迹点");
								}
								userServiceWeixinWrite.updateUser(nowuser);
							}
						}
						
						messageUtil.setCode(1);
						redisService.del("userOpen"+lockInfo.getBikeLockCode());
						data.put("judgeState",1);
						messageUtil.setMessage("success");
					}
				} catch (Exception e) {
					loggers.error("异常信息",e);
				}
				
			}
			
		}else{
			if(null!=nowUser.getUserChannelId()){
	            if(nowUser.getUserChannelId().equals(models.getModelsChannelId())){//会员模式
	            	if(models.getModelsRentType()==1){
	            		data.put("isVip",0);
	            		messageUtil.setMessage("normal");//游客模式
					}else if(models.getModelsRentType()==2){
						data.put("isVip",1);;
						messageUtil.setMessage("vip");
					}
	            	
				}else{
					data.put("isVip",0);
					messageUtil.setMessage("normal");//游客模式
				}
			}else{
				data.put("isVip",0);
				messageUtil.setMessage("normal");//游客模式
			}
			messageUtil.setCode(1);
			data.put("judgeState",1);
		}
		
		if(null!=rentInfo){
			if(null!=rentInfo.getRentStartlng()&&null!=rentInfo.getRentStartlat()){
				String startName = GetLocationUtil.getAdd(rentInfo.getRentStartlng().toString(), rentInfo.getRentStartlat().toString());
				if(null==startName){
					startName = "暂无信息";
				}
				rentInfo.setStartFixedName(startName);
			}
			if(null!=rentInfo.getRentEndlng()&&null!=rentInfo.getRentEndlat()){
				String endName = GetLocationUtil.getAdd(rentInfo.getRentEndlng().toString(), rentInfo.getRentEndlat().toString());
				if(null==endName){
					endName = "暂无信息";
				}
				rentInfo.setEndFixedName(endName);
			}
		}
		data.put("bikeRentInfo", rentInfo);
		data.put("account", account);
		messageUtil.setData(data);
		return messageUtil;
	}

/*
	@RequestMapping(value = "/testPay", method = RequestMethod.POST)
	public @ResponseBody MessageUtil testPay(Long bikeRentId, HttpSession session) throws Exception {
		User user_session = (User) session.getAttribute("curruser");
		User user = userServiceWeixinRead.findByUId(user_session.getUserId());
		Account account = accountWxServiceRead.findByAccountId(user.getUserAccountId());
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(bikeRentId);
		if (1 == bikeRentInfo.getRentState()) {
			messageUtil.setMessage("该租赁已完成");
			return messageUtil;
		}
		String ip = "192.108.1.1";
		// 新增资金日志
		moneyLogWxServiceWrite.addMoneyLog(account.getAccountId(), 2,
				bikeRentInfo.getRentPrice().add(bikeRentInfo.getRentInsurancePrice()), account.getAccountDeposit(),
				new Date(), ip, 2, bikeRentInfo.getRentOrderCode());

		returnBike(bikeRentId, session);
		messageUtil.setMessage("success");
		return messageUtil;
	}*/

	/**
	 * fromFlag(2wechar3small)
	 * 支付
	 */

	@RequestMapping(value = "/payMoney", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> payMoney(@RequestHeader HttpHeaders header,Double payMoneys, HttpSession session, Long rentInfoId,HttpServletRequest request)
			throws Exception {
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		Map<String, Object> map = new HashMap<String, Object>();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			map = MinWXHttpApi.minPayMoney(userLogin.getUserId(),userLogin.getUserSmallOpenid(), AppConfig.getMin_app_id(),
					AppConfig.getApp_domain(), AppConfig.getApp_partner(), AppConfig.getApp_partnerkey(),
					InetAddress.getLocalHost().getHostAddress(), payMoneys, rentInfoId);
		}else if("2".equals(fromFlag)){
			loggers.info("进入微信支付");
			userLogin = (User) session.getAttribute("curruser");
			loggers.info("用户Id为："+userLogin.getUserId());
			Account accout = accountWxServiceRead.findByUserId(userLogin.getUserId());
			if(accout.getAccountDeposit().compareTo(new BigDecimal(payMoneys))<0){
				payMoneys = payMoneys-accout.getAccountDeposit().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				loggers.info("支付费用为："+payMoneys);
				map = WXHttpApi.payMoney(userLogin.getUserId(), userLogin.getUserOpenid(), AppConfig.getApp_id(),
								AppConfig.getApp_domain(), AppConfig.getApp_partner(), AppConfig.getApp_partnerkey(),
								InetAddress.getLocalHost().getHostAddress(), payMoneys, rentInfoId);
										
		    }
		
		}
		
		
		return map;
	}
	
/*	@RequestMapping(value = "/payMoneytest", method = RequestMethod.POST)
	public @ResponseBody MessageUtil payMoneytest(@RequestHeader HttpHeaders header,Double payMoneys, HttpSession session, Long rentInfoId,HttpServletRequest request)
			throws Exception {
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
			user.setUserState(0);
			userServiceWeixinWrite.updateUser(user);
			BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
			bikeRentInfo.setRentState(1);
			bikeRentInfo.setRentPayTime(new Date());
			bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
		}
		
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}*/

	/**
	 * 检验订单
	 * @param
	 * @param
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkRentState", method = RequestMethod.POST)
	public @ResponseBody MessageUtil checkRentState(@RequestHeader HttpHeaders header,Long rentInfoId,HttpSession session,Long userToCouponId) throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		String fromFlag = header.getFirst("fromFlag");
		User user = new User();
		if("3".equals(fromFlag)){//小程序
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			user = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){//weixin端
			user = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){//app端
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			user = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==user){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
		BigDecimal payMoney = bikeRentInfo.getRentInsurancePrice().add(bikeRentInfo.getRentPrice());
		Long rideTime =  DateUtil.minuteDiff(bikeRentInfo.getRentStarttime(), bikeRentInfo.getRentEndtime());
		data.put("rideTime", rideTime);
		data.put("payMoney", payMoney);
		Bike bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
		loggers.info("租赁订单为："+rentInfoId);
		loggers.info("车辆为："+bike.getBikeCode());
		Account nowAccount = accountWxServiceRead.findByUserId(user.getUserId());
		Models models = modelsWxServiceRead.findModelsById(bike.getBikeModelsId()); // 查车的型号
		loggers.info("车型为："+models.getModelsName());
		User nowUser = userServiceWeixinRead.findByUId(user.getUserId());
		loggers.info("用户为："+nowUser.getUserTel());
		if(bikeRentInfo.getRentCouponId() != null){//导游发券模式  目前为免押金免费骑
			Coupon coupon = couponListWxServiceRead.findById(bikeRentInfo.getRentCouponId());
			if(coupon!=null && coupon.getCouponType()==2){//免费骑类型的券
				loggers.info("导游发券免费骑" );
				user.setUserState(0);
				userServiceWeixinWrite.updateUser(user);
				bikeRentInfo.setRentState(1);
				bikeRentInfo.setRentPayTime(new Date());
				bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
				messageUtil.setCode(1);
				data.put("checkState", 0);
				messageUtil.setMessage("导游发券免费骑");
			}
		}
		if(null!=nowUser.getUserChannelId()){
            if(nowUser.getUserChannelId().equals(models.getModelsChannelId())){//会员模式
            	if(models.getModelsRentType()==2){
            		loggers.info("会员模式");
            		nowUser.setUserState(0);
        			userServiceWeixinWrite.updateUser(nowUser);//更新用户
        			
        			if(bikeRentInfo.getRentState()!=1){
        				if(bikeRentInfo.getRentPrice().compareTo(BigDecimal.ZERO)==1){
        					DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)38);
            				Integer grade = Integer.valueOf(datadet.getDataDetVal());
            				nowUser.setUserTotalGrade(nowUser.getUserTotalGrade()+grade);
            				nowUser.setUserGrade(nowUser.getUserGrade()+grade);
            				nowUser.setUserLevel(LevelUtil.getLevel(nowUser.getUserTotalGrade()));
            				userServiceWeixinWrite.updateUser(nowUser);//更新用户
            				//积分记录
            				GradeRecord gradeRecord = new GradeRecord();
            				gradeRecord.setGradeCount(grade);
            				gradeRecord.setGradeRemark("正常骑行");
            				gradeRecord.setGradeUserId(nowUser.getUserId());
            				gradeRecord.setGradeState(0);//获取的
            				gradeRecord.setGradeCreateTime(new Date());
            				gradeWxServiceWrite.addGradeRecord(gradeRecord);
        				}
        				
        				bikeRentInfo.setRentState(1);
            			bikeRentInfo.setRentPayTime(new Date());
            			bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);//更新租赁订单
            			loggers.info("更新订单："+bikeRentInfo.getRentInfoId());
            			moneyLogWxServiceWrite.addVipMoneyLog(payMoney,nowAccount);//消费日志
            			loggers.info("添加消费日志");
        			}
        			
                	messageUtil.setCode(1);
                	data.put("checkState", 3);
                	messageUtil.setData(data);
                	messageUtil.setMessage("vip");
                	return messageUtil;
            	}
            	
            }
		}
		if(null!=userToCouponId){
			UserCoupon userCoupon = userCouponServiceWxRead.findUserCouponId(userToCouponId);
			CashCoupon cashCoupon = userCouponServiceWxRead.findCouponById(userCoupon.getUcouponId());
			if(null!=userCoupon.getuRedeemCodeEndTime()){
				Date lastTime = DateUtil.getDateFormat(new Date());
				if(userCoupon.getuRedeemCodeEndTime().getTime()<lastTime.getTime()){
					loggers.info("优惠券过期");
					messageUtil.setCode(0);
					messageUtil.setMessage("该优惠券过期，无法使用");
					return messageUtil;
				}
			}
			BigDecimal needMoney = payMoney;
			if(null!=models.getModelsInpriceId()){
				InsurancePrice insurancePrice = insurancePriceWxServiceRead.findById(models.getModelsInpriceId());//查询保险费用
				BigDecimal allFee = insurancePrice.getInPrice().add(models.getModelsDeposit());
				if(allFee.compareTo(BigDecimal.ZERO)>0){
					if(cashCoupon.getCouponIsCondition()==0){//无条件
						needMoney = payMoney.subtract(cashCoupon.getCouponMoney());
						if(needMoney.compareTo(BigDecimal.ZERO)<1){
							needMoney = new BigDecimal(0);
						}
						data.put("payMoney", needMoney);
					}else if(cashCoupon.getCouponIsCondition()==1){//有条件
						if(cashCoupon.getCouponStartMoney().compareTo(payMoney)>0){
							loggers.info("条件不足，无法使用");
							messageUtil.setCode(0);
							messageUtil.setMessage("条件不足,该骑行券无法使用");
							return messageUtil;
						}else{
							needMoney = payMoney.subtract(cashCoupon.getCouponMoney());
							if(needMoney.compareTo(BigDecimal.ZERO)<1){
								needMoney = new BigDecimal(0);
							}
							data.put("payMoney", needMoney);
						}
					}
				}
			}
			if (bikeRentInfo.getRentState() == 1) {
				messageUtil.setCode(1);
				data.put("checkState", 0);
				messageUtil.setMessage("已完成");
			}else{
				if(nowAccount.getAccountDeposit().compareTo(needMoney)>=0) {//账户押金大于等于支付的钱
					loggers.info("账户的预付款为" + nowAccount.getAccountDeposit());
					loggers.info("需要的费用" + needMoney);
					messageUtil.setCode(1);//去退款并支付
					data.put("checkState", 1);
				}else{
					needMoney = needMoney.subtract(nowAccount.getAccountDeposit());
					data.put("payMoney", needMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					loggers.info("账户的预付款为"+nowAccount.getAccountDeposit());
					loggers.info("需要的费用"+needMoney);
					messageUtil.setCode(1);//去支付
					data.put("checkState", 2);
				}
				messageUtil.setMessage("成功");
			}
			messageUtil.setData(data);
		}else{
			if (bikeRentInfo.getRentState() == 1) {
				messageUtil.setCode(1);
				data.put("checkState", 0);
				messageUtil.setMessage("已完成");
			}else{
				if(nowAccount.getAccountDeposit().compareTo(payMoney)>=0){//账户押金大于等于支付的钱
					loggers.info("账户的预付款为"+nowAccount.getAccountDeposit());
					loggers.info("需要的费用"+payMoney);
					messageUtil.setCode(1);//去退款并支付
					data.put("checkState", 1);
				}else{
					payMoney = payMoney.subtract(nowAccount.getAccountDeposit());
					data.put("payMoney", payMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
					loggers.info("账户的预付款为"+nowAccount.getAccountDeposit());
					loggers.info("需要的费用"+payMoney);
					messageUtil.setCode(1);//去支付
					data.put("checkState", 2);
				}
				messageUtil.setMessage("成功");
			}
			messageUtil.setData(data);
		}
		
		
		
		
		return messageUtil;
	}
	


	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/renting", method = RequestMethod.POST)
	public @ResponseBody MessageUtil renting(@RequestHeader HttpHeaders header,Long rentInfoId,HttpSession session) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User user = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			user = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			user = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			user = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==user){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		BikeRentInfo rentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
		if(null==user||!user.getUserId().equals(rentInfo.getRentInfoUserId())){
			messageUtil.setMessage("该订单出错,不允许替他人操作");
			return messageUtil;
		}
		if(rentInfo.getRentState()==1){
			messageUtil.setMessage("订单已支付");
			return messageUtil;
		}
		if(rentInfo.getRentEndtime()!=null){
			data.put("rentInfo", rentInfo);
			messageUtil.setMessage("行程已结束");
			return messageUtil;
		}
		User nowuser = userServiceWeixinRead.findByUId(user.getUserId());
		Bike bike = bikeWxServiceRead.findByBikeId(rentInfo.getRentInfoBikeId());
		Models models = modelsWxServiceRead.findModelsById(bike.getBikeModelsId());
		BikeLockInfo lockInfo = bikeLockInfoWxServiceRead.findLockInfoById(bike.getBikeId());
		Date nowDay = new Date();
		Double rentMoney = getRentMoneyAjax(nowuser,models,rentInfo,nowDay);
		data.put("rentInfo", rentInfo);
		data.put("rentMoney", rentMoney);
		data.put("bike", bike);
		data.put("lockType", lockInfo.getBikeLockType());
		messageUtil.setMessage("success");
		messageUtil.setData(data);
		return messageUtil;
	}
	
	
	
	/**
	 * 获取租赁行程费用
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTotalMoney", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getTotalMoney(@RequestHeader HttpHeaders header,Long rentInfoId,HttpSession session) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User userSession = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userSession = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userSession = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userSession = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userSession){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		User user = userServiceWeixinRead.findByUId(userSession.getUserId());
		BikeRentInfo rentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
		Bike bike = bikeWxServiceRead.findByBikeId(rentInfo.getRentInfoBikeId());
		Models models = modelsWxServiceRead.findModelsById(bike.getBikeModelsId());
		Date nowDay = new Date();
		Double rentMoney = getRentMoneyAjax(user,models,rentInfo,nowDay);//计算费用
		data.put("rentMoney", rentMoney);
		messageUtil.setMessage("success");
		messageUtil.setData(data);
		messageUtil.setCode(1);
		return messageUtil;
	}

	public boolean sendCommand(Bike bike, Integer flag, Integer isTemporary) throws Exception {
		BikeLockInfo lockInfo = bikeLockInfoWxServiceRead.findLockInfoById(bike.getBikeId());
		if (0 == flag) {
			if (lockService.sendLockMessage(lockInfo.getBikeLockCode())) {// 发送关锁指令
				return true;
			}else{
				return false;
			}
		} else if (1 == flag) {
			if (lockService.sendUnlockMessage(lockInfo.getBikeLockCode())) {// 发送开锁指令
				if(null!=lockInfo.getBikeLockType()&&!"".equals(lockInfo.getBikeLockType())&&lockInfo.getBikeLockType()==1){
				redisService.set("userOpen"+lockInfo.getBikeLockCode(), "open");
				}
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	
	public Double getRentMoney(User user,Models models,BikeRentInfo rentInfo,Date nowDay) throws Exception {
		loggers.info("租赁订单为："+rentInfo.getRentInfoId());
		Double nowMoney = 0.0;
		RentPrice price = null;
		if(null!=user.getUserChannelId()){
			if(user.getUserChannelId().equals(models.getModelsChannelId())){
				if(models.getModelsRentType()==1){
					price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
				}else if(models.getModelsRentType()==2){
					price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 2);//会员计费
				}
			}else{
				price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
			}
		}else{
			price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
		}
		
		if(null!=price){
			loggers.info("有计费模式");
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
		loggers.info("计算出来的费用："+nowMoney);
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
		JSONArray priceArray = JSONArray.fromObject(modelsRentPrice);//将字符串转化为jsonArray
		for(int i=0;i < priceArray.size();i++){
			//获取每一个JsonObject对象
		    JSONObject myjObject = (JSONObject) priceArray.get(i);
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
		loggers.info("一小时计费计算出来的费用："+nowMoney);
		return nowMoney;
	}
	
	/**
	 *半小时计费
	 * @param modelsRentPrice
	 * @param
	 * @param
	 * @throws Exception
	 */
	public Double getHalfNowMoney(String modelsRentPrice,Long halfHours,Long halfMinutes) throws Exception{
		Double nowMoney = 0.0;
		JSONArray priceArray = JSONArray.fromObject(modelsRentPrice);//将字符串转化为jsonArrays
		for(int i=0;i < priceArray.size();i++){
			//获取每一个JsonObject对象
			JSONObject myjObject = (JSONObject) priceArray.get(i);
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
		loggers.info("半小时计费计算出来的费用："+nowMoney);
		return nowMoney;
	}
	
	/**
	 * 页面定时查询费用调用方法
	 * @param user
	 * @param models
	 * @param rentInfo
	 * @param nowDay
	 * @return
	 * @throws Exception
	 */
	public Double getRentMoneyAjax(User user,Models models,BikeRentInfo rentInfo,Date nowDay) throws Exception {
		loggers.info("租赁订单为："+rentInfo.getRentInfoId());
		Double nowMoney = 0.0;
		RentPrice price = null;
		if(null!=user.getUserChannelId()){
			if(user.getUserChannelId().equals(models.getModelsChannelId())){
				if(models.getModelsRentType()==1){
					price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
				}else if(models.getModelsRentType()==2){
					price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 2);//会员计费
				}
			}else{
				price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
			}
		}else{
			price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
		}
		loggers.info("计费模式："+price);
		if(null!=price){
				Long diffMinutes = DateUtil.minuteDiff(rentInfo.getRentStarttime(), nowDay);
				if(price.getRentPriceOption()==1){//一小时计费
					Long diffHours = DateUtil.hourDiff(rentInfo.getRentStarttime(), nowDay);
					Integer diffSeconds = (int)(diffHours / 24);
					if(diffSeconds>0){//大于24小时
						if(diffMinutes>price.getRentFreeTime()){
							diffHours = diffHours - diffSeconds*24;
							nowMoney = nowMoney + getDiffNowMoneyAjax(price.getRentPrice(),diffHours,diffMinutes);
							if(nowMoney>price.getRentPriceMax()){
								nowMoney = price.getRentPriceMax();
							}
							nowMoney = nowMoney + diffSeconds*price.getRentPriceMax();
						}
						
						
					}else{//小于24小时
						if(diffMinutes>price.getRentFreeTime()){
							nowMoney = nowMoney + getDiffNowMoneyAjax(price.getRentPrice(),diffHours,diffMinutes);
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
							nowMoney = nowMoney + getHalfNowMoneyAjax(price.getRentPrice(),halfHours,diffMinutes);
							if(nowMoney>price.getRentPriceMax()){
								nowMoney = price.getRentPriceMax();
							}
							nowMoney = nowMoney + haffSeconds*price.getRentPriceMax();
						}
					}else{//小于24小时
						if(diffMinutes>price.getRentFreeTime()){
							nowMoney = nowMoney + getHalfNowMoneyAjax(price.getRentPrice(),halfHours,diffMinutes);
							if(nowMoney>price.getRentPriceMax()){
								nowMoney = price.getRentPriceMax();
							}
						}
						
					}
				}
				
		}
		loggers.info("计算出来的费用："+nowMoney);
		return nowMoney;

	}
	
	/**
	 *一小时计费
	 *页面定时查询费用调用方法
	 * @param modelsRentPrice
	 * @param diffHours
	 * @param diffMinutes
	 * @throws Exception
	 */
	public Double getDiffNowMoneyAjax(String modelsRentPrice,Long diffHours,Long diffMinutes) throws Exception{
		Double nowMoney = 0.0;
		JSONArray priceArray = JSONArray.fromObject(modelsRentPrice);//将字符串转化为jsonArray
		for(int i=0;i < priceArray.size();i++){
			//获取每一个JsonObject对象
		    JSONObject myjObject = (JSONObject) priceArray.get(i);
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
		loggers.info("一小时计费计算出来的费用："+nowMoney);
		return nowMoney;
	}
	
	/**
	 *半小时计费
	 *页面定时查询费用调用方法
	 * @param modelsRentPrice
	 * @param
	 * @param
	 * @throws Exception
	 */
	public Double getHalfNowMoneyAjax(String modelsRentPrice,Long halfHours,Long halfMinutes) throws Exception{
		Double nowMoney = 0.0;
		JSONArray priceArray = JSONArray.fromObject(modelsRentPrice);//将字符串转化为jsonArrays
		for(int i=0;i < priceArray.size();i++){
			//获取每一个JsonObject对象
			JSONObject myjObject = (JSONObject) priceArray.get(i);
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
		loggers.info("半小时计费计算出来的费用："+nowMoney);
		return nowMoney;
	}
}
