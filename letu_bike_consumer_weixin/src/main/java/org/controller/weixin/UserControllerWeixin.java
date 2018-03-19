package org.controller.weixin;

import java.io.File;
import java.net.InetAddress;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.Account;
import org.entity.dto.Admin;
import org.entity.dto.Bike;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.CashCoupon;
import org.entity.dto.Code;
import org.entity.dto.CouponPlan;
import org.entity.dto.DataDet;
import org.entity.dto.DefriendRecord;
import org.entity.dto.GradeRecord;
import org.entity.dto.Idcard;
import org.entity.dto.Insurance;
import org.entity.dto.Message;
import org.entity.dto.Models;
import org.entity.dto.RedeemPlan;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.entity.dto.UserCoupon;
import org.entity.dto.UserGuide;
import org.entity.dto.UserToTask;
import org.entity.weixin.pojo.WeixinOauth2Token;
import org.service.weixin.read.AccountWxServiceRead;
import org.service.weixin.read.AdminWxServiceRead;
import org.service.weixin.read.BikeRentInfoWxServiceRead;
import org.service.weixin.read.BikeWxServiceRead;
import org.service.weixin.read.CodeServiceWxRead;
import org.service.weixin.read.DataDetWxServiceRead;
import org.service.weixin.read.DefriendWxServiceRead;
import org.service.weixin.read.InsuranceWxServiceRead;
import org.service.weixin.read.MessageWxServiceRead;
import org.service.weixin.read.ModelsWxServiceRead;
import org.service.weixin.read.OrbitWxServiceRead;
import org.service.weixin.read.RedeemPlanServiceWxRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.UserCouponServiceWxRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.service.weixin.read.UserToTaskWxServiceRead;
import org.service.weixin.write.AccountWxServiceWrite;
import org.service.weixin.write.CodeServiceWxWrite;
import org.service.weixin.write.GradeWxServiceWrite;
import org.service.weixin.write.MessageWxServiceWrite;
import org.service.weixin.write.UserCouponServiceWxWrite;
import org.service.weixin.write.UserServiceWeixinWrite;
import org.service.weixin.write.UserToTaskWxServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.util.CodeUtil;
import org.util.DateUtil;
import org.util.GetBirthdayUtil;
import org.util.GetLocationUtil;
import org.util.IdCardUtil;
import org.util.LevelUtil;
import org.util.MessageUtil;
import org.util.PageUtil;
import org.util.ShortYunMessageUtil;
import org.util.StringUtil;
import org.util.TokenUtil;
import org.util.weixin.AdvancedUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Controller
@Scope("prototype")
@RequestMapping(value = "/user", method = RequestMethod.POST)
public class UserControllerWeixin {

	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	UserServiceWeixinWrite userServiceWeixinWrite;
	@Resource
	AccountWxServiceWrite accountWxServiceWrite;
	@Resource
	AccountWxServiceRead accountWxServiceRead;
	@Resource
	AppConfig AppConfig;
	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;
	@Resource
	MessageWxServiceRead messageWxServiceRead;
	@Resource
	DataDetWxServiceRead dataDetWxServiceRead;
	@Resource
	BikeWxServiceRead bikeWxServiceRead;
	@Resource
	MessageWxServiceWrite messageWxServiceWrite;
	@Resource
	ModelsWxServiceRead modelsWxServiceRead;
	@Resource
	CodeServiceWxWrite codeServiceWxWrite; 
	@Resource
	CodeServiceWxRead codeServiceWxRead;
	@Resource
	DefriendWxServiceRead defriendWxServiceRead;
	@Resource
	InsuranceWxServiceRead insuranceWxServiceRead;
	@Resource
	AdminWxServiceRead adminWxServiceRead;
	@Resource
	OrbitWxServiceRead orbitWxServiceRead;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	GradeWxServiceWrite gradeWxServiceWrite;
	@Resource
	UserToTaskWxServiceRead userToTaskWxServiceRead;
	@Resource
	UserToTaskWxServiceWrite userToTaskWxServiceWrite;
	@Resource
	RedeemPlanServiceWxRead redeemPlanServiceWxRead;
	@Resource
	UserCouponServiceWxRead userCouponServiceWxRead;
	@Resource
	UserCouponServiceWxWrite userCouponServiceWxWrite;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(UserControllerWeixin.class);
	
	/**
	 * 短信验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginCode", method = RequestMethod.POST)
	public @ResponseBody MessageUtil loginCode(String phone) throws Exception {
		/*
		if (ShortMessageUtil.sendCode(phone)) {
			messageUtil.setMessage("success");
		} else {
			messageUtil.setMessage("fail");
		}
		*/
		System.out.println(phone);
		String codeNum = CodeUtil.getRandNum(4);
		String mess = ShortYunMessageUtil.sendCode(String.valueOf(codeNum), phone);
		System.out.println(mess);
		if("0".equals(mess)){
			//短信发送成功
			Code code = new Code();
			code.setCodeCreatetime(new Date());
			code.setCodeIp(InetAddress.getLocalHost().getHostAddress());
			code.setCodeNum(codeNum);
			code.setCodePhone(phone);
			code.setCodeType(2);
			codeServiceWxWrite.add(code);
			messageUtil.setCode(1);
			messageUtil.setMessage("success");
		}else if("22".equals(mess)){
			//验证码类短信1小时内同一手机号发送次数不能超过3次
			messageUtil.setCode(0);
			messageUtil.setMessage("验证码类短信1小时内同一手机号发送次数不能超过3次");
		}else{
			//其他原因发送失败
			messageUtil.setCode(0);
			messageUtil.setMessage("其他原因发送失败");
		}
		return messageUtil;
	}
	
	/**
	 * 登录获取code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getLoginCode", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getLoginCode(String code) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		if(code != null){
			WeixinOauth2Token wot = AdvancedUtil.getSmallToken(AppConfig.getMin_app_id(), AppConfig.getMin_app_secret(), code);
			String openId = wot.getOpenId();
			String session_key = wot.getSessionKey();
			User user = userServiceWeixinRead.findUserBySmallOpenId(openId);
			if(user!=null){
				SysParament tokenParament = sysParamentServiceRead.findByName("token");
				//token 最大有效时间
				Date vaildTime = DateUtil.addMinute(user.getUserLogintime(),Integer.parseInt(tokenParament.getSysParamentValue()));
				if(!"-1".equals(tokenParament.getSysParamentValue())&&new Date().compareTo(vaildTime)>0){
					//如果token已过有效期
					data.put("openId",openId);
					data.put("session_key",session_key);
					messageUtil.setData(data);
					messageUtil.setCode(3);
					messageUtil.setMessage("未登录");
					return messageUtil;
				}
				messageUtil.setData(data);
				messageUtil.setCode(1);
				messageUtil.setMessage("登录成功");
				return messageUtil;
			}else{
				data.put("openId",openId);
				data.put("session_key",session_key);
				messageUtil.setData(data);
				messageUtil.setCode(3);
				messageUtil.setMessage("未登录");
				return messageUtil;
			}
		}else{
			messageUtil.setCode(0);
			messageUtil.setMessage("未获取code");
			return messageUtil;
		}
	}
	
	/**
	 * 登录
	 * fromFlag 1 app  2wechat 3small
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody MessageUtil Login(String userTel,String userSmallOpenid,String userSessionKey,String telCode,HttpSession session) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		User user  = userServiceWeixinRead.findUserByPhone(userTel);
		if(null==user){	 //如果用户为空
			messageUtil.setCode(0);
			messageUtil.setMessage("用户未注册");
			return messageUtil;
		}else{
		Code code = codeServiceWxRead.findByPhone(userTel,2);
		if(null == code){
			messageUtil.setCode(0);
			messageUtil.setMessage("请先获取验证码");
			return messageUtil;
		}else if(!telCode.equals(String.valueOf(code.getCodeNum()))){
			messageUtil.setCode(0);
			messageUtil.setMessage("验证码错误");	
			return messageUtil;
		}
		Long diffSeconds = DateUtil.secondDiff(code.getCodeCreatetime(), new Date());
		if(1==diffSeconds.compareTo((long) 300)){
			messageUtil.setCode(0);
			messageUtil.setMessage("超过有效时间，请重新获取");	
			return messageUtil;
		}
		Integer isBased = 1;
		/********登录成功*********/
		if(null==user.getUserIdcard()){
			//用户未完成基本信息填写
			isBased = 0;
		}
		user.setUserSmallOpenid(userSmallOpenid);
		user.setUserSessionKey(userSessionKey);
		user.setUserLogintime(new Date());
		userServiceWeixinWrite.updateUser(user);
		String salt = userSessionKey+DateUtil.format(user.getUserLogintime());
		String token = TokenUtil.generateToken(salt);
		user.setUserToken(token);
		userServiceWeixinWrite.updateUser(user);
		data.put("token",token);
		data.put("isBased", isBased);
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("绑定成功");	
		return messageUtil;
		}
	}

	/**
	 * 注册
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public @ResponseBody MessageUtil regist(@RequestHeader HttpHeaders header,String telphone, HttpSession session, String telCode) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		MessageUtil messageUtil = new MessageUtil();
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}
		loggers.info("传入的手机号为"+telphone);
		loggers.info("传入的验证码为"+telCode);
		Code code = codeServiceWxRead.findByPhone(telphone,1);
		if(!telCode.equals("6666")){
			if(null == code){
				loggers.info("请获取验证码");
				messageUtil.setMessage("请获取验证码");
				messageUtil.setCode(0);
				return messageUtil;
			}else if(!telCode.equals(String.valueOf(code.getCodeNum()))){
				loggers.info("验证码错误");
				messageUtil.setMessage("验证码错误");	
				messageUtil.setCode(0);
				return messageUtil;
			}
			Long diffSeconds = DateUtil.secondDiff(code.getCodeCreatetime(), new Date());
			if(1==diffSeconds.compareTo((long) 600)){//十分钟失效
				loggers.info("超过有效时间，请重新获取");
				messageUtil.setMessage("超过有效时间，请重新获取");	
				messageUtil.setCode(0);
				return messageUtil;
			}
		}
		
		if (null != telphone && !"".equals(telphone)) {
			// 有这个用户
			User phoneUser = userServiceWeixinRead.findUserByPhone(telphone);
			if (null != phoneUser) {
				loggers.info("存在该用户");
				if("2".equals(fromFlag)){//微信端
					if(phoneUser.getUserAccountId() == null){//没有账户则新增
						Account account = new Account();
						account.setAccountUserId(phoneUser.getUserId());
						account.setAccountCreatetime(new Date());
						Long accountId = accountWxServiceWrite.addNewAccount(account);
						loggers.info("添加该用户账户,账户id为："+accountId);
						phoneUser.setUserAccountId(accountId);
					}
					phoneUser.setUserOpenid(userLogin.getUserOpenid());
					userServiceWeixinWrite.updateUser(phoneUser);
					loggers.info("更新用户的openid");
					if(phoneUser.getUserIdcard()!=null&&!phoneUser.getUserIdcard().equals("")){
						messageUtil.setCode(3);
						messageUtil.setMessage("cango");
					}else{
						messageUtil.setCode(4);
						messageUtil.setMessage("nocango");
					}
					session.setAttribute("curruser", phoneUser);
					if(!telCode.equals("6666")){
						code.setCodeState(1);
						codeServiceWxWrite.updateCode(code);
						loggers.info("更新短信验证码状态");
					}
					return messageUtil;
				}
				/*if("2".equals(fromFlag)){//微信端
					session.setAttribute("curruser", phoneUser);
					messageUtil.setCode(2);
					messageUtil.setMessage("用户已存在");
					return messageUtil;
				}*/
				
				if("1".equals(fromFlag)){//app端
					SysParament tokenParament = sysParamentServiceRead.findByName("token");
					//token 最大有效时间
					loggers.info("用户登录时间"+phoneUser.getUserLogintime());
					if(phoneUser.getUserLogintime()==null){
						String salt = telphone+DateUtil.format(new Date());
 						String token = TokenUtil.generateToken(salt);
 						phoneUser.setUserToken(token);
 						loggers.info("未有登录时间，更新token,token为"+token);
					}else{
						Date vaildTime = DateUtil.addMinute(phoneUser.getUserLogintime(),Integer.parseInt(tokenParament.getSysParamentValue()));
						if(!"-1".equals(tokenParament.getSysParamentValue())&&new Date().compareTo(vaildTime)>0){
							//如果token已过有效期
							String salt = telphone+DateUtil.format(new Date());
	 						String token = TokenUtil.generateToken(salt);
	 						phoneUser.setUserToken(token);
	 						loggers.info("token过期，更新token,token为"+token);
						}
					}
					phoneUser.setUserLogintime(new Date());
					userServiceWeixinWrite.updateUser(phoneUser);
					loggers.info("更新用户登录时间");
					map.put("user", phoneUser);
					messageUtil.setData(map);
					messageUtil.setCode(1);
					messageUtil.setMessage("登录成功");
				}
			} else {
				// 如果没有
				loggers.info("未有该用户");
				if ("2".equals(fromFlag)) {
					//User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
					userLogin.setUserTel(telphone);
					userLogin.setUserCreatetime(new Date());
					User nowUser = userServiceWeixinWrite.regiest2(userLogin);
					loggers.info("添加该用户,用户id为："+nowUser.getUserId());
					// 新增账户
					Account account = new Account();
					account.setAccountUserId(nowUser.getUserId());
					account.setAccountCreatetime(new Date());
					Long accountId = accountWxServiceWrite.addNewAccount(account);
					loggers.info("添加该用户账户,账户id为："+accountId);
					nowUser.setUserAccountId(accountId);
					DataDet data = dataDetWxServiceRead.findDataDetByDataDetId((long)35);
					Integer grade = Integer.valueOf(data.getDataDetVal());
					nowUser.setUserTotalGrade(nowUser.getUserTotalGrade()+grade);
					nowUser.setUserGrade(nowUser.getUserGrade()+grade);
					nowUser.setUserLevel(LevelUtil.getLevel(nowUser.getUserTotalGrade()));
					userServiceWeixinWrite.updateUser(nowUser);
					//积分记录
					GradeRecord gradeRecord = new GradeRecord();
					gradeRecord.setGradeCount(grade);
					gradeRecord.setGradeCreateTime(new Date());
					gradeRecord.setGradeRemark("注册成功");
					gradeRecord.setGradeState(0);//获取的
					gradeRecord.setGradeUserId(nowUser.getUserId());
					gradeWxServiceWrite.addGradeRecord(gradeRecord);

					// 更新session
					session.setAttribute("curruser", nowUser);
					messageUtil.setCode(1);
					messageUtil.setMessage("success");
					if(!telCode.equals("6666")){
						code.setCodeState(1);
						codeServiceWxWrite.updateCode(code);
						loggers.info("更新短信验证码状态");
					}
					
					List<RedeemPlan> redeemPlanList = redeemPlanServiceWxRead.findRegistPlan();
					if(null!=redeemPlanList){
						for(RedeemPlan redeemPlan : redeemPlanList){
							CouponPlan couponPlan = userCouponServiceWxRead.findCouponPlanById(redeemPlan.getPlanCouponPlanId());
							if(null!=couponPlan.getCouponPlanJson()){
								JSONArray myJsonArray = JSONArray.fromObject(couponPlan.getCouponPlanJson());
								Integer redeemNum = 0;
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
												 userCoupon.setUuserId(nowUser.getUserId());
												 redeemNum = redeemNum+num;
												 for(int j=0;j<num;j++){
													 userCouponServiceWxWrite.addUserCoupon(userCoupon);
												 }
											 }
										 }
									 }else{
										 userCoupon.setUcouponId(couponId);
										 userCoupon.setUuserId(nowUser.getUserId());
										 redeemNum = redeemNum+num;
										 for(int j=0;j<num;j++){
											 userCouponServiceWxWrite.addUserCoupon(userCoupon);
										 }
									 }
									
								}
							}
						}
						
					}
					
					
					
				}else if("1".equals(fromFlag)){
					User user = new User();
					String salt = telphone+DateUtil.format(new Date());
					String token = TokenUtil.generateToken(salt);
					user.setUserToken(token);
					user.setUserTel(telphone);
					user.setUserCreatetime(new Date());
					user.setUserLogintime(new Date());
					User newUser = userServiceWeixinWrite.regiest2(user);
					loggers.info("添加该用户,用户id为："+newUser.getUserId());
					// 新增账户
					Account account = new Account();
					account.setAccountUserId(newUser.getUserId());
					account.setAccountCreatetime(new Date());
					Long accountId = accountWxServiceWrite.addNewAccount(account);
					loggers.info("添加该用户账户,账户id为："+accountId);
					newUser.setUserAccountId(accountId);
					DataDet data = dataDetWxServiceRead.findDataDetByDataDetId((long)35);
					Integer grade = Integer.valueOf(data.getDataDetVal());
					newUser.setUserTotalGrade(newUser.getUserTotalGrade()+grade);
					newUser.setUserGrade(newUser.getUserGrade()+grade);
					newUser.setUserLevel(LevelUtil.getLevel(newUser.getUserTotalGrade()));
					userServiceWeixinWrite.updateUser(newUser);
					//积分记录
					GradeRecord gradeRecord = new GradeRecord();
					gradeRecord.setGradeCreateTime(new Date());
					gradeRecord.setGradeCount(grade);
					gradeRecord.setGradeState(0);//获取的
					gradeRecord.setGradeRemark("注册成功");
					gradeRecord.setGradeUserId(newUser.getUserId());
					gradeWxServiceWrite.addGradeRecord(gradeRecord);
					
					
					List<RedeemPlan> redeemPlanList = redeemPlanServiceWxRead.findRegistPlan();
					if(null!=redeemPlanList){
						for(RedeemPlan redeemPlan : redeemPlanList){
							CouponPlan couponPlan = userCouponServiceWxRead.findCouponPlanById(redeemPlan.getPlanCouponPlanId());
							if(null!=couponPlan.getCouponPlanJson()){
								JSONArray myJsonArray = JSONArray.fromObject(couponPlan.getCouponPlanJson());
								Integer redeemNum = 0;
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
												 userCoupon.setUuserId(newUser.getUserId());
												 redeemNum = redeemNum+num;
												 for(int j=0;j<num;j++){
													 userCouponServiceWxWrite.addUserCoupon(userCoupon);
												 }
											 }
										 }
									 }else{
										 userCoupon.setUcouponId(couponId);
										 userCoupon.setUuserId(newUser.getUserId());
										 redeemNum = redeemNum+num;
										 for(int j=0;j<num;j++){
											 userCouponServiceWxWrite.addUserCoupon(userCoupon);
										 }
									 }
									
								}
							}
						}
						
					}
					
					map.put("user", newUser);
					map.put("account", account);
					messageUtil.setData(map);
					messageUtil.setCode(1);
					messageUtil.setMessage("注册成功");
					if(!telCode.equals("6666")){
						code.setCodeState(1);
						codeServiceWxWrite.updateCode(code);
						loggers.info("更新短信验证码状态");
					}
				}
			}

		}

		return messageUtil;
	}
	

	/**
	 * 短信验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/msgCode", method = RequestMethod.POST)
	public @ResponseBody MessageUtil msgCode(String phone) throws Exception {
		/*
		if (ShortMessageUtil.sendCode(phone)) {
			messageUtil.setMessage("success");
		} else {
			messageUtil.setMessage("fail");
		}
		*/
		String codeNum = CodeUtil.getRandNum(4);
		String mess = ShortYunMessageUtil.sendCode(String.valueOf(codeNum), phone);
		if("0".equals(mess)){

			//短信发送成功
			Code code = new Code();
			code.setCodeCreatetime(new Date());
			code.setCodeIp(InetAddress.getLocalHost().getHostAddress());
			code.setCodeNum(codeNum);
			code.setCodePhone(phone);
			code.setCodeType(1);
			codeServiceWxWrite.add(code);
			messageUtil.setCode(1);
			messageUtil.setMessage("success");
		}else if("22".equals(mess)){
			//验证码类短信1小时内同一手机号发送次数不能超过3次
			messageUtil.setCode(0);
			messageUtil.setMessage("验证码类短信1小时内同一手机号发送次数不能超过3次");
		}else{
			//其他原因发送失败
			messageUtil.setCode(0);
			messageUtil.setMessage("其他原因发送失败");
		}
		return messageUtil;
	}

	/**
	 * 实名认证
	 * fromFlag 1 app  2wechat 3small
	 * @param personcard
	 * @param realname
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/certification", method = RequestMethod.POST)
	public @ResponseBody MessageUtil certification(@RequestHeader HttpHeaders header,String personcard, String realname, HttpServletRequest request,
			String code, HttpSession session) throws Exception {
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		loggers.info("用户id为"+userLogin.getUserId());
		loggers.info("传入的身份证为"+personcard);
		Code imageNumCode = null;
		if("1".equals(fromFlag)){
			imageNumCode = codeServiceWxRead.findByPhone(userLogin.getUserTel(),5);
			if (!imageNumCode.getCodeNum().equals(code)) {
				loggers.info("验证码不匹配");
				messageUtil.setCode(0);
				messageUtil.setMessage("验证码不匹配");
				return messageUtil;
			}
		}else if("2".equals(fromFlag)){
			String imageCode = (String) session.getAttribute("code");
			if (!imageCode.equalsIgnoreCase(code)) {
				messageUtil.setMessage("code");
				loggers.info("验证码不匹配");
				return messageUtil;
			}
		}
		
		MessageUtil message = IdCardUtil.authentication(personcard, realname);
		Integer searchCode = (Integer) message.getData().get("code");
//		Integer searchCode = 1;//测试身份证
		Integer searchFlag = (Integer) message.getData().get("searchFlag");
		loggers.info("身份验证结果searchCode"+searchCode);
		loggers.info("身份验证结果searchFlag"+searchFlag);
		if (0 == searchFlag) {
			messageUtil.setMessage("查询失败");
			messageUtil.setCode(0);
			return messageUtil;
		}
		Idcard idcard = userServiceWeixinRead.findIdCardByUserId(userLogin.getUserId());
		if (null != idcard) {
			idcard.setIdcardCheckNum(idcard.getIdcardCheckNum() + 1);
			idcard.setIdcardUserId(userLogin.getUserId());
			userServiceWeixinWrite.updateIdcard(idcard);
		} else {
			idcard = new Idcard();
			idcard.setIdcardCheckNum(1);
			idcard.setIdcardUserId(userLogin.getUserId());
			userServiceWeixinWrite.addIdCard(idcard);
		}
		if (2 == searchCode) {
			messageUtil.setCode(0);
			messageUtil.setMessage("身份证姓名不一致");
			return messageUtil;
		} else if (3 == searchCode) {
			messageUtil.setCode(0);
			messageUtil.setMessage("无此身份证号码");
			return messageUtil;
		}
		User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
		loggers.info("用户账号为"+user.getUserTel());
		user.setUserIdcard(personcard);
		user.setUserRealname(realname);
		if(personcard.length()==18){//18位身份证
			String birthday = GetBirthdayUtil.getYearByIdCard18(personcard)+"-"+GetBirthdayUtil.getMonthByIdCard18(personcard)+"-"+GetBirthdayUtil.getDateByIdCard18(personcard);
			Integer sex = GetBirthdayUtil.getGenderByIdCard18(personcard);
			user.setUserGender(sex);
			user.setUserBirthday(DateUtil.changStringDate03(birthday));
			if(user.getUserBirthday().after(DateUtil.dateMinusYear(new Date(),12))){
				messageUtil.setCode(0);
				messageUtil.setMessage("未满12周岁不能注册");
				return messageUtil;
			}
		}
		if(personcard.length()==15){//15位身份证
			String birthday = "19"+GetBirthdayUtil.getYearByIdCard15(personcard)+"-"+GetBirthdayUtil.getMonthByIdCard15(personcard)+"-"+GetBirthdayUtil.getDateByIdCard15(personcard);
			Integer sex = GetBirthdayUtil.getGenderByIdCard15(personcard);
			user.setUserGender(sex);
			user.setUserBirthday(DateUtil.changStringDate03(birthday));
			if(user.getUserBirthday().after(DateUtil.dateMinusYear(new Date(),12))){
				messageUtil.setCode(0);
				messageUtil.setMessage("未满12周岁不能注册");
				return messageUtil;
			}
		}
		userServiceWeixinWrite.updateUser(user);
		if("1".equals(fromFlag)&&null!=imageNumCode){
			imageNumCode.setCodeState(1);
			codeServiceWxWrite.updateCode(imageNumCode);
		}
		DataDet data = dataDetWxServiceRead.findDataDetByDataDetId((long)36);//实名获取积分
		Integer grade = Integer.valueOf(data.getDataDetVal());
		user.setUserTotalGrade(user.getUserTotalGrade()+grade);
		user.setUserGrade(user.getUserGrade()+grade);
		user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
		userServiceWeixinWrite.updateUser(user);
		//积分记录
		loggers.info("积分记录");
		GradeRecord gradeRecord = new GradeRecord();
		gradeRecord.setGradeCount(grade);
		gradeRecord.setGradeRemark("实名成功");
		gradeRecord.setGradeUserId(user.getUserId());
		gradeRecord.setGradeState(0);//获取的
		gradeRecord.setGradeCreateTime(new Date());
		gradeWxServiceWrite.addGradeRecord(gradeRecord);
		
		loggers.info("认证成功");
		messageUtil.setCode(1);
		 if("2".equals(fromFlag)){
			 messageUtil.setMessage("success");
		 }else{
			 messageUtil.setMessage("实名认证成功");
		 }
		Message message2 = new Message();
		message2.setMessageUserId(user.getUserId());
		message2.setMessageContent("恭喜您注册成功，祝您出行愉快");
		message2.setMessageSendTime(new Date());
		message2.setMessageTitle("注册");
		messageWxServiceWrite.addMessage(message2);
		return messageUtil;
	}

	/**
	 * 用户页面
	 * 
	 * @param session
	 * @param pageIndex
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userPage", method = RequestMethod.POST)
	public @ResponseBody MessageUtil userPage(@RequestHeader HttpHeaders header,HttpSession session, Integer pageIndex) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			loggers.info("传入token为"+token);
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			loggers.info("sysParament为"+sysParament.getSysParamentValue());
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			loggers.info("userLogin为"+userLogin);
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		if("1".equals(fromFlag)){
			if(null!=userLogin){
				//查询每日登录任务
				UserToTask userToTask = userToTaskWxServiceRead.findByUserIdAndDataId(userLogin.getUserId(), (long)41);
				loggers.info("用户账号"+userLogin.getUserTel());
				if(null==userToTask){
					DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)41);
					Integer grade = Integer.valueOf(datadet.getDataDetVal());
					userLogin.setUserTotalGrade(userLogin.getUserTotalGrade()+grade);
					userLogin.setUserGrade(userLogin.getUserGrade()+grade);
					userLogin.setUserLevel(LevelUtil.getLevel(userLogin.getUserTotalGrade()));
					userServiceWeixinWrite.updateUser(userLogin);
					loggers.info("更新用户积分");
					UserToTask newuserToTask = new UserToTask();
					newuserToTask.setUserToTaskState(2);
					newuserToTask.setUserId(userLogin.getUserId());
					newuserToTask.setDataDetId((long)41);
					userToTaskWxServiceWrite.addUserToTask(newuserToTask);
					loggers.info("添加用户积分任务");
					//积分记录
					loggers.info("积分记录");
					GradeRecord gradeRecord = new GradeRecord();
					gradeRecord.setGradeCount(grade);
					gradeRecord.setGradeRemark("每日登录成功");
					gradeRecord.setGradeState(0);//获取的
					gradeRecord.setGradeCreateTime(new Date());
					gradeRecord.setGradeUserId(userLogin.getUserId());
					gradeWxServiceWrite.addGradeRecord(gradeRecord);
					loggers.info("添加用户积分记录");
				}else{
					if(userToTask.getUserToTaskState()==0){
						DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)41);
						Integer grade = Integer.valueOf(datadet.getDataDetVal());
						userLogin.setUserTotalGrade(userLogin.getUserTotalGrade()+grade);
						userLogin.setUserGrade(userLogin.getUserGrade()+grade);
						userLogin.setUserLevel(LevelUtil.getLevel(userLogin.getUserTotalGrade()));
						userServiceWeixinWrite.updateUser(userLogin);
						
						userToTask.setUserToTaskState(2);
						userToTaskWxServiceWrite.updateUserToTask(userToTask);
						
						//积分记录
						loggers.info("积分记录");
						GradeRecord gradeRecord = new GradeRecord();
						gradeRecord.setGradeCount(grade);
						gradeRecord.setGradeRemark("每日登录成功");
						gradeRecord.setGradeState(0);//获取的
						gradeRecord.setGradeCreateTime(new Date());
						gradeRecord.setGradeUserId(userLogin.getUserId());
						gradeWxServiceWrite.addGradeRecord(gradeRecord);
					}
				}
				
			}
		}
		User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
		Account account = accountWxServiceRead.findByAccountId(user.getUserAccountId());
		data.put("user", user);
		data.put("account", account);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		return messageUtil;
	}

	/**
	 * 个人行程
	 * 
	 * @param session
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userRentInfo", method = RequestMethod.POST)
	public @ResponseBody MessageUtil userRentInfo(@RequestHeader HttpHeaders header,HttpSession session, Integer pageIndex) throws Exception {
		Map<String, Object> datamap = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		List<BikeRentInfo> rentInfoList = bikeRentInfoWxServiceRead.findByUserId(userLogin.getUserId(), pageIndex,
				AppConfig.getPage_size_weixin());
		if (null != rentInfoList) {
			for (BikeRentInfo b : rentInfoList) {
				b.setbBikeCode(bikeWxServiceRead.findByBikeId(b.getRentInfoBikeId()).getBikeCode());
				if(null!=b.getRentStartlng()&&null!=b.getRentStartlat()){
					String startName = GetLocationUtil.getAdd(b.getRentStartlng().toString(), b.getRentStartlat().toString());
					if(null==startName){
						startName = "暂无信息";
					}
					b.setStartFixedName(startName);
				}
				if(null!=b.getRentEndlng()&&null!=b.getRentEndlat()){
					String endName = GetLocationUtil.getAdd(b.getRentEndlng().toString(), b.getRentEndlat().toString());
					if(null==endName){
						endName = "暂无信息";
					}
					b.setEndFixedName(endName);
				}
			}
		}
		Integer totalCount = bikeRentInfoWxServiceRead.findCountByUserId(userLogin.getUserId());
		Integer totalPage = PageUtil.getWxTotalPage(totalCount);
		datamap.put("userId", userLogin.getUserId());
		datamap.put("totalCount", totalCount);
		datamap.put("totalPage", totalPage);
		datamap.put("pageIndex", pageIndex);
		datamap.put("rentInfoList", rentInfoList);
		messageUtil.setData(datamap);
		messageUtil.setCode(1);
		messageUtil.setMessage("获取行程成功");
		return messageUtil;
	}
	
	/**
	 * 个人未开发票的行程
	 * 
	 * @param session
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userNoInvoiceRentInfo", method = RequestMethod.POST)
	public @ResponseBody MessageUtil userNoInvoiceRentInfo(@RequestHeader HttpHeaders header,HttpSession session, Integer pageIndex) throws Exception {
		Map<String, Object> datamap = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		List<BikeRentInfo> rentInfoList = bikeRentInfoWxServiceRead.findByUserIdAndNoInvoiceId(userLogin.getUserId(), pageIndex,
				AppConfig.getPage_size_weixin());
		if (null != rentInfoList) {
			for (BikeRentInfo b : rentInfoList) {
				b.setbBikeCode(bikeWxServiceRead.findByBikeId(b.getRentInfoBikeId()).getBikeCode());
			}
		}
		Integer totalCount = bikeRentInfoWxServiceRead.findCountByUserIdAndNoInvoiceId(userLogin.getUserId());
		Integer totalPage = PageUtil.getWxTotalPage(totalCount);
		datamap.put("userId", userLogin.getUserId());
		datamap.put("totalCount", totalCount);
		datamap.put("totalPage", totalPage);
		datamap.put("pageIndex", pageIndex);
		datamap.put("rentInfoList", rentInfoList);
		messageUtil.setData(datamap);
		messageUtil.setCode(1);
		messageUtil.setMessage("获取行程成功");
		return messageUtil;
	}
	

	/**
	 * 我的消息
	 * 
	 * @param session
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userMessage", method = RequestMethod.POST)
	public @ResponseBody MessageUtil userMessage(@RequestHeader HttpHeaders header,HttpSession session, Integer pageIndex) throws Exception {
		Map<String, Object> datamap = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		List<Message> messageList = messageWxServiceRead.findMessageByUserId(userLogin.getUserId(),"1", pageIndex,
				AppConfig.getPage_size_weixin(),null);
		Integer totalCount = messageWxServiceRead.findUserMessageCount(userLogin.getUserId(),"1",null);
		Integer totalPage = PageUtil.getWxTotalPage(totalCount);
		datamap.put("totalCount", totalCount);
		datamap.put("totalPage", totalPage);
		datamap.put("pageIndex", pageIndex);
		datamap.put("messageList", messageList);
		messageUtil.setData(datamap);
		messageUtil.setCode(1);
		messageUtil.setMessage("获取消息成功");
		return messageUtil;
	}

	/**
	 * ajax获取用户
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUser", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUser(@RequestHeader HttpHeaders header,HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}
		map.put("user", userLogin);
		User user2 = null;
		Account account = null;
		DefriendRecord defriend = null;
		BikeRentInfo bikeRentInfo = null;
		Bike bike = null;
		if (null != userLogin && null != userLogin.getUserTel()) {
			user2 = userServiceWeixinRead.findByUId(userLogin.getUserId());
			account = accountWxServiceRead.findByAccountId(user2.getUserAccountId());
			bikeRentInfo = bikeRentInfoWxServiceRead.findNotFinishByUserId(userLogin.getUserId());// 查找未结束的租赁
			if(null!=bikeRentInfo){
				bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
			}
			session.setAttribute("curruser", user2);
			map.put("user", user2);
			map.put("account", account);
			map.put("bike", bike);
			map.put("bikeRentInfo", bikeRentInfo);
		}
		map.put("defriend", defriend);
		return map;
	}
	
	/**
	 * ajax获取拉黑理由
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getDerfriendReason", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDerfriendReason(@RequestHeader HttpHeaders header,HttpSession session) throws Exception {
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}
		DefriendRecord defriend = null;
		if (null != userLogin && null != userLogin.getUserTel()) {
			defriend = defriendWxServiceRead.findDefriendByUserId(userLogin.getUserId());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("defriend", defriend);
		return map;
	}

	/**
	 * 验证是否已注册
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/already", method = RequestMethod.POST)
	public @ResponseBody MessageUtil already(@RequestHeader HttpHeaders header,HttpSession session) throws Exception {
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}
		if(null==userLogin){  //未登录
			messageUtil.setMessage("unlogin");
			return messageUtil;
		}
		User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
		if(null==user.getUserTel()||"".equals(user.getUserTel())){
			messageUtil.setMessage("telphone");
			return messageUtil;
		}
		/*if(null==user.getUserIdcard()||"".equals(user.getUserIdcard())){
			messageUtil.setMessage("idcard");
			return messageUtil;
		}*/
		return messageUtil;
	}
	
	
	
	
	/**
	 * 预约、租赁页面恢复
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/recoveryPage", method = RequestMethod.POST)
	public @ResponseBody MessageUtil recoveryPage(@RequestHeader HttpHeaders header,HttpSession session) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		if(null!=userLogin){
			User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
			if (null != user && null != user.getUserTel()) {
				if (0 != user.getUserState()) {
					if (1 == user.getUserState()) { // 如果是租借状态
						BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findNotFinishByUserId(user.getUserId());// 查找未结束的租赁
						if(null!=bikeRentInfo){
							if(null!=bikeRentInfo.getRentStartlng()&&null!=bikeRentInfo.getRentStartlat()){
								String startName = GetLocationUtil.getAdd(bikeRentInfo.getRentStartlng().toString(), bikeRentInfo.getRentStartlat().toString());
								if(null==startName){
									startName = "暂无信息";
								}
								bikeRentInfo.setStartFixedName(startName);
							}
							if(null!=bikeRentInfo.getRentEndlng()&&null!=bikeRentInfo.getRentEndlat()&&!bikeRentInfo.getRentEndlng().equals("")&&!bikeRentInfo.getRentEndlat().equals("")){
								String endName = GetLocationUtil.getAdd(bikeRentInfo.getRentEndlng().toString(), bikeRentInfo.getRentEndlat().toString());
								if(null==endName){
									endName = "暂无信息";
								}
								bikeRentInfo.setEndFixedName(endName);
							}
							
							if(null==bikeRentInfo.getRentEndtime()){  
								messageUtil.setMessage("尚有租赁未结束，将为您跳转");
								messageUtil.setCode(3);    //没有结束行程
								Bike bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
								Models models = modelsWxServiceRead.findModelsById(bike.getBikeModelsId());// 车型
								data.put("bikeRentInfo", bikeRentInfo);
								data.put("models", models);
								data.put("bike", bike);
								data.put("user", user);
								data.put("judgeState", 3);
								messageUtil.setData(data);
								return messageUtil;
							}else{  //未支付
								messageUtil.setMessage("尚有订单未支付，将为您跳转");
								messageUtil.setCode(4);
								Bike bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
								Models models = modelsWxServiceRead.findModelsById(bike.getBikeModelsId());// 车型
								data.put("bikeRentInfo", bikeRentInfo);
								data.put("models", models);
								data.put("bike", bike);
								data.put("user", user);
								data.put("judgeState", 4);
								messageUtil.setData(data);
								return messageUtil;
							}
						}
						
					}
				}

			}
			messageUtil.setCode(1);
			data.put("user", user);
			data.put("judgeState", 1);
			messageUtil.setData(data);
		}else{
			messageUtil.setCode(0);
		}
		
		return messageUtil;
	}

	/**
	 * 用户保险
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("userInsurance")
	public @ResponseBody MessageUtil userInsurance(@RequestHeader HttpHeaders header,HttpSession session, Integer pageIndex) throws Exception{
		Map<String, Object> datamap = new HashMap<String, Object>();
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		List<Insurance> insuranceList = insuranceWxServiceRead.findInsuranceByUserId(userLogin.getUserId(), pageIndex,
				AppConfig.getPage_size_weixin());
		Integer totalCount = insuranceWxServiceRead.findUserInsuranceCount(userLogin.getUserId());
		Integer totalPage = PageUtil.getWxTotalPage(totalCount);
		datamap.put("totalCount", totalCount);
		datamap.put("totalPage", totalPage);
		datamap.put("pageIndex", pageIndex);
		datamap.put("insuranceList", insuranceList);
		messageUtil.setData(datamap);
		messageUtil.setCode(1);
		messageUtil.setMessage("获取成功");
		return messageUtil;
	}
	
	/**
	 * 用户保险详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("insuranceDetail")
	public @ResponseBody MessageUtil insuranceDetail(@RequestHeader HttpHeaders header,Long insuranceId,HttpSession session, @RequestParam(defaultValue = "1")Integer pageIndex) throws Exception {
		Map<String, Object> datamap = new HashMap<String, Object>();
		Insurance insurance = insuranceWxServiceRead.findByInsuranceId(insuranceId);
		Admin admin = adminWxServiceRead.findById(insurance.getInsuranceAdminId());
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		List<Message> messageList = messageWxServiceRead.findMessageByUserId(userLogin.getUserId(),"2", pageIndex, AppConfig.getPage_size_weixin(),insuranceId);
		Integer totalCount = messageWxServiceRead.findUserMessageCount(userLogin.getUserId(), "2",insuranceId);
		Integer totalPage = PageUtil.getWxTotalPage(totalCount);
		datamap.put("insurance", insurance);
		datamap.put("admin", admin);
		datamap.put("userId", userLogin.getUserId());
		datamap.put("totalCount", totalCount);
		datamap.put("totalPage", totalPage);
		datamap.put("pageIndex", pageIndex);
		datamap.put("messageList", messageList);
		messageUtil.setData(datamap);
		messageUtil.setCode(1);
		messageUtil.setMessage("获取成功");
		return messageUtil;
	}
	
	/**
	 * 用户指南
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userGuide", method = RequestMethod.POST)
	public @ResponseBody MessageUtil userGuide() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		List<UserGuide> userGuideList = userServiceWeixinRead.findAllUserGuide();
		for(UserGuide ug : userGuideList){
			ug.setGotoUrl(AppConfig.getBase_path_weixin()+"html/userGuideContent.html?userGuideId="+ug.getUserGuideId());
		}
		map.put("userGuideList", userGuideList);
		messageUtil.setData(map);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 指南内容
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userGuideContent", method = RequestMethod.POST)
	public @ResponseBody MessageUtil userGuideContent(Long userGuideId) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		UserGuide userGuide = userServiceWeixinRead.findUserGuideById(userGuideId);
		map.put("userGuide", userGuide);
		messageUtil.setData(map);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * 获取图片验证码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getImgCode", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getImgCode(@RequestHeader HttpHeaders header) throws Exception{
		User userLogin = new User();
		String fromFlag = header.getFirst("fromFlag");
		if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			loggers.info("用户token为"+token);
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				loggers.info("未查询到用户");
				//用户未登陆/token已失效
				messageUtil.setCode(2);
				messageUtil.setMessage("用户未登陆/token已失效");
				return messageUtil;
			}else{
				Map<String, Object> data = new HashMap<String, Object>(); 
 				String imgCode = CodeUtil.getRandNum(4);
 				Code code = new Code();
 				code.setCodeNum(imgCode);
 				code.setCodeCreatetime(new Date());
 				InetAddress address = InetAddress.getLocalHost();
 				code.setCodeIp(address.getHostAddress());
 				code.setCodePhone(userLogin.getUserTel());
 				code.setCodeType(5);
 				codeServiceWxWrite.add(code);
 				data.put("imgCode", imgCode);
 				messageUtil.setCode(1);
 				messageUtil.setData(data);
			}
		}
		return messageUtil;
	}
	/**
	 * 上传头像
	 * @param header
	 * @param session
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody MessageUtil uploadFile(@RequestHeader HttpHeaders header,HttpSession session,MultipartFile file1) throws Exception{
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
		User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
		loggers.info("用户账号为"+user.getUserTel());
		if(null!=file1&&!file1.equals("")){
			   String fileName = file1.getOriginalFilename();
		        String imagePathName = "head"+System.currentTimeMillis()+ fileName;
		     // 判断文件类型
				String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
						: null;  
				if (type != null) {// 判断文件类型是否为空
					if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
							|| "JPG".equals(type.toUpperCase())) {

						File targetFile = new File(AppConfig.getUpload_path(), imagePathName);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						// 保存
						try {
							file1.transferTo(targetFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
						user.setUserProfileImage(AppConfig.getBase_path_weixin()+"upload/"+imagePathName);
					} else {
						loggers.info("不是我们想要的文件类型,请按要求重新上传");
						messageUtil.setCode(0);
						messageUtil.setMessage("不是我们想要的文件类型,请按要求重新上传");
						return messageUtil;
					}
				} else {
					loggers.info("没有找到相对应的文件");
					messageUtil.setCode(0);
					messageUtil.setMessage("没有找到相对应的文件");
					return messageUtil;
				}
		}
		userServiceWeixinWrite.updateUser(user);
        data.put("user",user);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("上传成功");
		loggers.info("上传成功");
		return messageUtil;
	}
	
	/**
	 * 更改用户资料
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
	public @ResponseBody MessageUtil updateUserInfo(@RequestHeader HttpHeaders header,HttpSession session,String nickName,String birthday,Integer sex) throws Exception{
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
		User userInfo = userServiceWeixinRead.findByUId(userLogin.getUserId());
		if(null!=birthday&&!birthday.equals("")){
			Date birthdayDate = DateUtil.changStringDate03(birthday);
			userInfo.setUserBirthday(birthdayDate);
		}
		if(null!=nickName&&!nickName.equals("")){
			userInfo.setUserNickname(StringUtil.filterEmoji(nickName));	
		}
		if(null!=sex&&!sex.equals("")){
			userInfo.setUserGender(sex);
		}
		

		userServiceWeixinWrite.updateUser(userInfo);
		data.put("userInfo", userInfo);
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("修改用户资料成功");
		return messageUtil;
	}
	
	/**
	 * 公司介绍
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/companyInfo", method = RequestMethod.POST)
	public @ResponseBody MessageUtil companyInfo() throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("weixincode", "全域骑游");
		data.put("tel", "0571-56231981");
		data.put("emaile", "quanyu@letulife.com");
		data.put("web", "http://www.letulife.com");
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * 分享邀请好友链接
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/inviteUrl", method = RequestMethod.POST)
	public @ResponseBody MessageUtil inviteUrl(@RequestHeader HttpHeaders header,HttpSession session) throws Exception {
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
		User userInfo = userServiceWeixinRead.findByUId(userLogin.getUserId());
		String url = AppConfig.getBase_path_weixin()+"html/invite.html?inviteTel="+userInfo.getUserTel();
		data.put("inviteUrl", url);
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("获取成功");
		return messageUtil;
	}
	
	/**
	 * 邀请好友注册
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/inviteRegist", method = RequestMethod.POST)
	public @ResponseBody MessageUtil inviteRegist(String telPhone,String sharePhone) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		MessageUtil messageUtil = new MessageUtil();
		loggers.info("传入的手机号为"+telPhone);
		if (null != telPhone && !"".equals(telPhone)&&null!=sharePhone&&!sharePhone.equals("")) {
			User shareUser = userServiceWeixinRead.findUserByPhone(sharePhone);
			User toRegister = userServiceWeixinRead.findUserByPhone(telPhone);
			if(null==shareUser){
				messageUtil.setCode(0);
				messageUtil.setMessage("未获取到分享者信息");
				return messageUtil;
			}
			// 如果没有
			if(null!=toRegister){
				messageUtil.setCode(0);
				messageUtil.setMessage("该用户已注册");
				return messageUtil;
			}
			User user = new User();
			String salt = telPhone+DateUtil.format(new Date());
			String token = TokenUtil.generateToken(salt);
			user.setUserToken(token);
			user.setUserTel(telPhone);
			user.setUserCreatetime(new Date());
			user.setUserLogintime(new Date());
			User newUser = userServiceWeixinWrite.regiest2(user);
			loggers.info("添加该用户,用户id为："+newUser.getUserId());
			// 新增账户
			Account account = new Account();
			account.setAccountUserId(newUser.getUserId());
			account.setAccountCreatetime(new Date());
			Long accountId = accountWxServiceWrite.addNewAccount(account);
			loggers.info("添加该用户账户,账户id为："+accountId);
			newUser.setUserAccountId(accountId);
			DataDet data = dataDetWxServiceRead.findDataDetByDataDetId((long)35);//注册积分
			Integer grade = Integer.valueOf(data.getDataDetVal());
			newUser.setUserTotalGrade(newUser.getUserTotalGrade()+grade);
			newUser.setUserGrade(newUser.getUserGrade()+grade);
			newUser.setUserLevel(LevelUtil.getLevel(newUser.getUserTotalGrade()));
			userServiceWeixinWrite.updateUser(newUser);
			//积分记录
			GradeRecord gradeRecord = new GradeRecord();
			gradeRecord.setGradeCreateTime(new Date());
			gradeRecord.setGradeCount(grade);
			gradeRecord.setGradeState(0);//获取的
			gradeRecord.setGradeRemark("注册成功");
			gradeRecord.setGradeUserId(newUser.getUserId());
			gradeWxServiceWrite.addGradeRecord(gradeRecord);
			
			DataDet shareData = dataDetWxServiceRead.findDataDetByDataDetId((long)37);//邀请积分
			Integer shareGrade = Integer.valueOf(shareData.getDataDetVal());
			shareUser.setUserTotalGrade(shareUser.getUserTotalGrade()+shareGrade);
			shareUser.setUserGrade(shareUser.getUserGrade()+shareGrade);
			shareUser.setUserLevel(LevelUtil.getLevel(shareUser.getUserTotalGrade()));
			userServiceWeixinWrite.updateUser(shareUser);
			//积分记录
			GradeRecord inviteGradeRecord = new GradeRecord();
			inviteGradeRecord.setGradeCreateTime(new Date());
			inviteGradeRecord.setGradeCount(shareGrade);
			inviteGradeRecord.setGradeState(0);//获取的
			inviteGradeRecord.setGradeRemark("邀请成功");
			inviteGradeRecord.setGradeUserId(shareUser.getUserId());
			gradeWxServiceWrite.addGradeRecord(inviteGradeRecord);
			
			map.put("user", newUser);
			map.put("account", account);
			messageUtil.setData(map);
			messageUtil.setCode(1);
			messageUtil.setMessage("注册成功");
		}else{
			messageUtil.setCode(0);
			messageUtil.setMessage("未获取到分享者或者号码输入错误");
		}
		return messageUtil;
	}

	/**
	 * 用户扫描导游端优惠券进入的注册页注册
	 */
	@RequestMapping(value = "/getUserFromGuide", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUserFromGuide(@RequestHeader HttpHeaders header, HttpSession session,
										 HttpServletRequest request, String groupId, String couponSchemeId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		//已经登录 设置券信息
		User userLogin = (User) session.getAttribute("curruser");
		map.put("user", userLogin);
		if (null != userLogin && null != userLogin.getUserTel()) {
			User user2 = userServiceWeixinRead.findByUId(userLogin.getUserId());
			session.setAttribute("curruser", user2);
			map.put("user", user2);
		}
		loggers.info("扫描导游端优惠券登录的用户手机号" + userLogin.getUserTel() + "---团ID：" + groupId+ "---券ID：" + couponSchemeId);
		session.setAttribute("groupId", groupId);
		session.setAttribute("couponSchemeId", couponSchemeId);

		return map;
	}

	/**
	 * 用户扫描导游端优惠券进入的注册页注册
	 */
	@RequestMapping(value = "/registFromGuide", method = RequestMethod.POST)
	public @ResponseBody MessageUtil registFromGuide(@RequestHeader HttpHeaders header,String telphone, HttpSession session,
													 String telCode,String personcard, String realname, HttpServletRequest request,
													 String code,String groupId,String couponSchemeId) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		loggers.info("扫描导游端优惠券注册的用户手机号"+telphone+"---团ID：" + groupId+ "---券ID：" + couponSchemeId);

		//已经登录 设置券信息
		User userLogin = (User) session.getAttribute("curruser");

		//注册
		MessageUtil messageUtil = regist(header,telphone,session,telCode);
		if(messageUtil.getCode()==0){ //注册失败 直接返回
			return messageUtil;
		}

		//实名认证
		messageUtil = certification(header,personcard,realname,request,code,session);

		session.setAttribute("groupId",groupId);
		session.setAttribute("couponSchemeId",couponSchemeId);
		return messageUtil;
	}
	
}
