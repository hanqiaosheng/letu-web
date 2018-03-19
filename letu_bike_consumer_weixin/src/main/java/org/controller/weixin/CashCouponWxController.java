package org.controller.weixin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.CashCoupon;
import org.entity.dto.CouponPlan;
import org.entity.dto.RedeemPlan;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.entity.dto.UserCoupon;
import org.entity.dto.UserRedeemPlan;
import org.service.weixin.read.RedeemPlanServiceWxRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.UserCouponServiceWxRead;
import org.service.weixin.read.UserRedeemPlanServiceWxRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.service.weixin.write.RedeemPlanServiceWxWrite;
import org.service.weixin.write.UserCouponServiceWxWrite;
import org.service.weixin.write.UserRedeemPlanServiceWxWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.MessageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping(value = "/coupon", method = RequestMethod.POST)
public class CashCouponWxController {

	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	UserCouponServiceWxRead userCouponServiceWxRead;
	@Resource
	UserCouponServiceWxWrite userCouponServiceWxWrite;
	@Resource
	RedeemPlanServiceWxRead redeemPlanServiceWxRead;
	@Resource
	RedeemPlanServiceWxWrite redeemPlanServiceWxWrite;
	@Resource
	UserRedeemPlanServiceWxRead userRedeemPlanServiceWxRead;
	@Resource
	UserRedeemPlanServiceWxWrite userRedeemPlanServiceWxWrite;
	@Resource
	AppConfig appConfig;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(CashCouponWxController.class);

	/**
	 * 能用的代金券
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/canUseCode", method = RequestMethod.POST)
	public @ResponseBody MessageUtil canUseCode(@RequestHeader HttpHeaders header,HttpSession session,BigDecimal payMoney) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		//获取登录信息
			User userLogin = new User();
			String fromFlag = header.getFirst("fromFlag");
			if("1".equals(fromFlag)){//app
				String token = header.getFirst("token");
				SysParament sysParament = sysParamentServiceRead.findByName("token");
				userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
				if(null==userLogin){
					//用户未登陆/token已失效
					messageUtil.setCode(2);
					messageUtil.setMessage("用户未登陆/token已失效");
					return messageUtil;
				}
			}else if("2".equals(fromFlag)){//微信
				userLogin = (User) session.getAttribute("curruser");
				if(null==userLogin){
					messageUtil.setCode(2);
					messageUtil.setMessage("停留时间过长");
					return messageUtil;
				}
			}else{
				//小程序
			}
		User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
		List<CashCoupon> canUseList = new ArrayList<CashCoupon>();
		List<UserCoupon> list = userCouponServiceWxRead.findCouponByUserId(null, user.getUserId());
		if(null!=list){
			for(UserCoupon uc : list){
				CashCoupon cashCoupon = userCouponServiceWxRead.findCouponById(uc.getUcouponId());
				if(cashCoupon.getCouponIsValidity()==2){//有效时长
					 String endTimeStr = DateUtil.formatDay(DateUtil.plusDateNum(cashCoupon.getCouponCreateTime(), cashCoupon.getCouponValidityTime()));
					 Date endTime = DateUtil.changStringDate03(endTimeStr);
					 cashCoupon.setCouponEndTime(endTime);
				}
				cashCoupon.setUserToCouponId(uc.getUserToCouponId());
				if(1==cashCoupon.getCouponIsCondition()){
					if(1!=cashCoupon.getCouponStartMoney().compareTo(payMoney)){
						if(null!=uc.getuRedeemCodeEndTime()){
							Date lastTime = DateUtil.getDateFormat(new Date());
							if(uc.getuRedeemCodeEndTime().getTime()>=lastTime.getTime()){
								canUseList.add(cashCoupon);
							}
						}else{
							canUseList.add(cashCoupon);
						}
					}
				}else {
					if(null!=uc.getuRedeemCodeEndTime()){
						Date lastTime = DateUtil.getDateFormat(new Date());
						if(uc.getuRedeemCodeEndTime().getTime()>=lastTime.getTime()){
							canUseList.add(cashCoupon);
						}
					}else{
						canUseList.add(cashCoupon);
					}
				}
		    }
		}
		/*List<CashCoupon> newUseList = new ArrayList<CashCoupon>();
	    for(CashCoupon cc : canUseList){
	    	if(cc.getCouponMoney().compareTo(payMoney)==1){
	    		newUseList.add(0, cc);
	    	}else if(cc.getCouponMoney().compareTo(payMoney)==0){
	    		newUseList.add(0, cc);
	    	}else if(cc.getCouponMoney().compareTo(payMoney)==-1){
	    		newUseList.add(cc);
	    	}
	    }*/
		data.put("totalCount", canUseList.size());
		data.put("cashCouponList", canUseList);
		messageUtil.setData(data);
		messageUtil.setCode(1);
		return messageUtil;
	}
	
	/**
	 * 查询代金券使用条件
	 * @param header
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkCondition", method = RequestMethod.POST)
	public @ResponseBody MessageUtil checkCondition(@RequestHeader HttpHeaders header,Long cashCouponId,HttpSession session,BigDecimal payMoney) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		CashCoupon cashCoupon = userCouponServiceWxRead.findCouponById(cashCouponId);
		//获取登录信息
		User userLogin = new User();
		String fromFlag = header.getFirst("fromFlag");
		if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				//用户未登陆/token已失效
				messageUtil.setCode(2);
				messageUtil.setMessage("用户未登陆/token已失效");
				return messageUtil;
			}
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("停留时间过长");
				return messageUtil;
			}
		}else{
			//小程序
		}
		BigDecimal needMoney = new BigDecimal(0);
		if(cashCoupon.getCouponIsCondition()==0){//无条件
			if(cashCoupon.getCouponMoney().compareTo(payMoney)<1){
				needMoney = payMoney.subtract(cashCoupon.getCouponMoney());
			}
			data.put("needMoney", needMoney);
			data.put("couponMoney", cashCoupon.getCouponMoney());
			messageUtil.setData(data);
			messageUtil.setMessage("success");
			messageUtil.setCode(1);
			return messageUtil;
		}else if(cashCoupon.getCouponIsCondition()==1){//有条件
			if(cashCoupon.getCouponStartMoney().compareTo(payMoney)>0){
				messageUtil.setCode(0);
				messageUtil.setMessage("条件不足,该骑行券无法使用");
				return messageUtil;
			}else{
				needMoney = payMoney.subtract(cashCoupon.getCouponMoney());
				data.put("needMoney", needMoney);
				data.put("couponMoney", cashCoupon.getCouponMoney());
				messageUtil.setData(data);
				messageUtil.setMessage("success");
				messageUtil.setCode(1);
			}
		}
		return messageUtil;
	}
	
	
	/**
	 * 我的代金券
	 * @param header
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userRedeemCode", method = RequestMethod.POST)
	public @ResponseBody MessageUtil userRedeemCode(@RequestHeader HttpHeaders header,HttpSession session,@RequestParam(defaultValue="1")Integer pageIndex) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		User userLogin = new User();
		String fromFlag = header.getFirst("fromFlag");
		if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				//用户未登陆/token已失效
				messageUtil.setCode(2);
				messageUtil.setMessage("用户未登陆/token已失效");
				return messageUtil;
			}
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("停留时间过长");
				return messageUtil;
			}
		}else{
			//小程序
		}

		List<UserCoupon> userCouponList  = userCouponServiceWxRead.findCouponByUserId(pageIndex,userLogin.getUserId());
		if(null==userCouponList){
			data.put("cashCouponList", new ArrayList<UserCoupon>());
			data.put("pageIndex", pageIndex);
			data.put("totalCount", 0);			
			messageUtil.setData(data);
			messageUtil.setMessage("无可使用代金券");
			messageUtil.setCode(1);
			return messageUtil;
		}else{
			List<CashCoupon> cashCoupons =  new ArrayList<CashCoupon>();
			for(UserCoupon uc:userCouponList){
				CashCoupon cashCoupon  = userCouponServiceWxRead.findCouponById(uc.getUcouponId());
				if(cashCoupon.getCouponIsValidity()==2){//有效时长
					 String endTimeStr = DateUtil.formatDay(DateUtil.plusDateNum(cashCoupon.getCouponCreateTime(), cashCoupon.getCouponValidityTime()));
					 Date endTime = DateUtil.changStringDate03(endTimeStr);
					 cashCoupon.setCouponEndTime(endTime);
				}
				if(null!=uc.getuRedeemCodeEndTime()){
					Date lastTime = DateUtil.getDateFormat(new Date());
					if(uc.getuRedeemCodeEndTime().getTime()>=lastTime.getTime()){
						cashCoupons.add(cashCoupon);
					}
				}else{
					cashCoupons.add(cashCoupon);
				}
				
			}
			data.put("cashCouponList", cashCoupons);
			data.put("totalCount", userCouponList.size());
			data.put("pageIndex", pageIndex);
			messageUtil.setData(data);
			messageUtil.setMessage("success");
			messageUtil.setCode(1);
			return messageUtil;
		}
		
	}
	
	
	/**
	 * 兑换代金券
	 * @param header
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exchange", method = RequestMethod.POST)
	public @ResponseBody MessageUtil exchange(@RequestHeader HttpHeaders header,HttpSession session,String redeemCode) throws Exception{
		
		if(null==redeemCode){
			messageUtil.setCode(1);
			messageUtil.setMessage("兑换码不得为空");
			return messageUtil;
		}
		Map<String,Object> data = new HashMap<String,Object>();
		User userLogin = new User();
		String fromFlag = header.getFirst("fromFlag");
		if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				//用户未登陆/token已失效
				messageUtil.setCode(2);
				messageUtil.setMessage("用户未登陆/token已失效");
				return messageUtil;
			}
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("停留时间过长");
				return messageUtil;
			}
		}else{
			//小程序
		}
		RedeemPlan redeemPlan = redeemPlanServiceWxRead.findRedeemPlanByCode(redeemCode);//查找兑换方案
		if(null!=redeemPlan){//用户已使用过的兑换方案
			UserRedeemPlan userRedeemPlan = userRedeemPlanServiceWxRead.findByUserAndPlan(redeemPlan.getPlanId(),userLogin.getUserId());
			if(null!=userRedeemPlan){
				messageUtil.setCode(0);
				messageUtil.setMessage("您已兑换过该兑换码");
				return messageUtil;
			}
			if(redeemPlan.getPlanSurplusNums()<1){//兑换次数不足
				messageUtil.setCode(0);
				messageUtil.setMessage("该代金券已兑换完");
				return messageUtil;
			}
			/*if(redeemPlan.getPlanReserveOfflineTime().getTime()<new Date().getTime()){
				messageUtil.setCode(0);
				messageUtil.setMessage("该兑换码已过期");
				return messageUtil;
			}*/
			CouponPlan couponPlan = userCouponServiceWxRead.findCouponPlanById(redeemPlan.getPlanCouponPlanId());
			if(null!=couponPlan.getCouponPlanJson()){
				JSONArray myJsonArray = JSONArray.fromObject(couponPlan.getCouponPlanJson());
				Integer redeemNum = 0;
				Integer validityNum = 0;
				for(int i=0 ; i < myJsonArray.size() ;i++){
					 JSONObject myjObject = myJsonArray.getJSONObject(i);
					 Long couponId = Long.valueOf(myjObject.getString("id"));
					 Integer num = Integer.parseInt(myjObject.getString("num"));
					 CashCoupon cashCoupon = userCouponServiceWxRead.findCouponById(couponId);
					 
					 UserCoupon userCoupon = new UserCoupon();
					 userCoupon.setuCreateTime(new Date());
					 if(1==cashCoupon.getCouponIsValidity()){ //如果不是一直能用
						 userCoupon.setuRedeemCodeEndTime(cashCoupon.getCouponEndTime());
					 }else if(2==cashCoupon.getCouponIsValidity()){//几天后
						 String endTimeStr = DateUtil.formatDay(DateUtil.plusDateNum(cashCoupon.getCouponCreateTime(), cashCoupon.getCouponValidityTime()));
						 Date endTime = DateUtil.changStringDate03(endTimeStr);
						 loggers.info("结束时间"+endTime);
						 userCoupon.setuRedeemCodeEndTime(endTime);
					 }
					 if(0!=cashCoupon.getCouponIsValidity()){
						 if(null!=userCoupon.getuRedeemCodeEndTime()){
							 if(new Date().getTime()<userCoupon.getuRedeemCodeEndTime().getTime()){//未过期
								 userCoupon.setUcouponId(couponId);
								 userCoupon.setUuserId(userLogin.getUserId());
								 redeemNum = redeemNum+num;
								 for(int j=0;j<num;j++){
									 userCouponServiceWxWrite.addUserCoupon(userCoupon);
								 }
							 }else{//过期
								 validityNum++;
							 }
						 }
					 }else{
						 userCoupon.setUcouponId(couponId);
						 userCoupon.setUuserId(userLogin.getUserId());
						 redeemNum = redeemNum+num;
						 for(int j=0;j<num;j++){
							 userCouponServiceWxWrite.addUserCoupon(userCoupon);
						 }
					 }
					
				}
				if(validityNum>0){
					if(validityNum == myJsonArray.size()){
						messageUtil.setCode(0);
						messageUtil.setMessage("兑换的代金券已过期");
						return messageUtil;
					}
				}
				UserRedeemPlan urp = new UserRedeemPlan();
				urp.setUrpCreateTime(new Date());
				urp.setUrpUserId(userLogin.getUserId());
				urp.setUrpRedeemPlanId(redeemPlan.getPlanId());
				userRedeemPlanServiceWxWrite.addUserRedeemPlan(urp);
				//兑换方案的次数减一
				redeemPlan.setPlanSurplusNums(redeemPlan.getPlanSurplusNums()-1);
				redeemPlanServiceWxWrite.updateRedeemPlan(redeemPlan);
				
				data.put("userId", userLogin.getUserId());
				messageUtil.setData(data);
				messageUtil.setCode(1);
				messageUtil.setMessage("恭喜您，兑换了"+redeemNum+"张代金券");
				return messageUtil;
			}else{
				messageUtil.setCode(0);
				messageUtil.setMessage("您的兑换码错误");
				return messageUtil;
			}
			
			
			
		}else{
			messageUtil.setCode(0);
			messageUtil.setMessage("该兑换码错误");
			return messageUtil;
		}
	}
	
	/**
	 * 优惠券说明
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/couponInfo", method = RequestMethod.POST)
	public @ResponseBody MessageUtil couponInfo() throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("url", appConfig.getBase_path_weixin()+"html/couponInfo.html");
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}
}
