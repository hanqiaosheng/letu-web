package org.controller.weixin;


import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.component.AlipayConfig;
import org.component.AppConfig;
import org.component.WXPayConfig;
import org.entity.dto.*;
import org.service.weixin.read.*;
import org.service.weixin.write.AccountWxServiceWrite;
import org.service.weixin.write.BikeRentInfoWxServiceWrite;
import org.service.weixin.write.GradeWxServiceWrite;
import org.service.weixin.write.MessageWxServiceWrite;
import org.service.weixin.write.MoneyLogWxServiceWrite;
import org.service.weixin.write.RechargeWxServiceWrite;
import org.service.weixin.write.RefundWxServiceWrite;
import org.service.weixin.write.TrOrderWxServiceWrite;
import org.service.weixin.write.UserCouponServiceWxWrite;
import org.service.weixin.write.UserServiceWeixinWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.LevelUtil;
import org.util.MessageUtil;
import org.util.redis.RedisService;
import org.util.weixin.MinWXHttpApi;
import org.util.weixin.WXHttpApi;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;

@Controller
@Scope("prototype")
@RequestMapping(value = "/pay", method = RequestMethod.POST)
public class PayWxController {
	
	@Resource
	AppConfig appConfig;
	
	@Resource
	RefundWxServiceWrite refundWxServiceWrite;
	
	@Resource
	RefundWxServiceRead refundWxServiceRead;
	
	@Resource
	AccountWxServiceRead accountWxServiceRead;
	
	@Resource
	RechargeWxServiceRead rechargeWxServiceRead;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	UserServiceWeixinWrite userServiceWeixinWrite;
	@Resource
	AccountWxServiceWrite accountWxServiceWrite;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	MessageWxServiceWrite messageWxServiceWrite;
	@Resource
	MoneyLogWxServiceWrite moneyLogWxServiceWrite;
	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;
	@Resource
	BikeRentInfoWxServiceWrite bikeRentInfoWxServiceWrite;
	@Resource
	RechargeWxServiceWrite rechargeWxServiceWrite;
	@Resource
	TrOrderWxServiceWrite trOrderWxServiceWrite;
	@Resource
	UserCouponServiceWxRead userCouponServiceWxRead;
	@Resource
	UserCouponServiceWxWrite userCouponServiceWxWrite;
	@Resource
	DataDetWxServiceRead dataDetWxServiceRead;
	@Resource
	GradeWxServiceWrite gradeWxServiceWrite;
	@Resource
	RedisService redisService;
	@Resource
	CouponSchemeWxServiceRead couponSchemeWxServiceRead;
	@Resource
	CouponListWxServiceRead couponListWxServiceRead;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(PayWxController.class);
	
	
	/**
	 * 充值金额
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addRecharge", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object>  addRecharge(@RequestHeader HttpHeaders header,Double rechargeMoney,HttpSession session,Integer flag,HttpServletRequest request,Long channelId)throws Exception{
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		loggers.info("渠道"+channelId);
		loggers.info("充值类型"+flag);
		loggers.info("充值金额"+rechargeMoney);
		Map<String, Object> map = new HashMap<String, Object>();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			map = MinWXHttpApi.minUnifiedOrder(userLogin.getUserId(),userLogin.getUserSmallOpenid() ,appConfig.getMin_app_id(),appConfig.getApp_domain(),appConfig.getApp_partner(),appConfig.getApp_partnerkey(),InetAddress.getLocalHost().getHostAddress(),rechargeMoney, flag);
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
			if(null!=channelId){
				map = WXHttpApi.unifiedOrder(userLogin.getUserId(),userLogin.getUserOpenid() ,appConfig.getApp_id(),appConfig.getApp_domain(),appConfig.getApp_partner(),appConfig.getApp_partnerkey(),InetAddress.getLocalHost().getHostAddress(),rechargeMoney, flag,channelId);
			}else{
				map = WXHttpApi.unifiedOrder(userLogin.getUserId(),userLogin.getUserOpenid() ,appConfig.getApp_id(),appConfig.getApp_domain(),appConfig.getApp_partner(),appConfig.getApp_partnerkey(),InetAddress.getLocalHost().getHostAddress(),rechargeMoney, flag,(long)0);
			}
		}
	   
		return map;
	}
	
	/**
	 * 充值金额  测试
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/addRecharge2", method = RequestMethod.POST)
	public @ResponseBody MessageUtil  addRecharge2(Double rechargeMoney,HttpSession session,Integer flag)throws Exception{
		User user_session = (User)session.getAttribute("curruser");
		User user = userServiceWeixinRead.findByUId(user_session.getUserId());
		Account account = accountWxServiceRead.findByAccountId(user.getUserAccountId());
		
		account.setAccountDeposit(account.getAccountDeposit().add(new BigDecimal(rechargeMoney)));
		account.setAccountUpdatetime(new Date());
		//总资产
		account.setAccountTotalmoney(account.getAccountBbin().add(account.getAccountAvailableBalance()).add(account.getAccountDeposit()));
		accountWxServiceWrite.updateAccount(account);
		messageUtil.setMessage("success");
		return messageUtil;
	}*/
	
	
	
	/**
	 * 退款
	 * @param session
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/refundApplication", method = RequestMethod.POST)
	public @ResponseBody MessageUtil refundApplication(HttpSession session) throws Exception{
		User user = (User)session.getAttribute("curruser");
		Account account = accountWxServiceRead.findByUserId(user.getUserId());
		User user1 = userServiceWeixinRead.findByUId(user.getUserId());
		if(user1.getUserState()!=0){
			messageUtil.setMessage("renting");
			return messageUtil;
		}
		if(account.getAccountDeposit().compareTo(BigDecimal.ZERO)<=0){
			messageUtil.setMessage("fail");
			return messageUtil;
		}
		Refund refund = new Refund();
		List<RechargeRecord> rechargeRecords = rechargeWxServiceRead.findRecharge(account.getAccountId());
		if(rechargeRecords!=null){
			for(RechargeRecord rechargeRecord : rechargeRecords){
				Refund refund2 = refundWxServiceRead.findRefundByorderId(rechargeRecord.getRechargeOrderId());
				if(refund2==null){
					refund.setRefundMoney(rechargeRecord.getRechargeMoney());
					refund.setRefundCreatetime(new Date());
					refund.setRefundType(1);
					refund.setRefundState(0);
					refund.setRefundAccountId(account.getAccountId());
					refund.setRefundOrderId(rechargeRecord.getRechargeOrderId());
					refund.setRefundCode(rechargeRecord.getRechargeOutTradeNo());
					refundWxServiceWrite.addRefundApplication(refund);
					account.setAccountDeposit(new BigDecimal(0));
					account.setAccountTotalmoney(account.getAccountTotalmoney().subtract(rechargeRecord.getRechargeMoney()));
					account.setAccountFreezemoney(account.getAccountFreezemoney().add(rechargeRecord.getRechargeMoney()));
					accountWxServiceWrite.updateAccount(account);
				}else{
					refund2.setRefundCreatetime(new Date());
					refundWxServiceWrite.updateRefundApplication(refund2);
					
				}
			}
		}else{
			messageUtil.setMessage("noRecharge");
			return messageUtil;
		}
		messageUtil.setMessage("退款成功");
		messageUtil.setCode(1);
		return messageUtil;
	}
	*/
	/**
	 * 支付并退款
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/refundApplication", method = RequestMethod.POST)
	public @ResponseBody MessageUtil refundApplication(@RequestHeader HttpHeaders header,HttpSession session,Double payMoneys,Long rentInfoId,Long userCouponId) throws Exception{
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
		loggers.info("用户id"+userLogin.getUserId());
		loggers.info("租赁订单id"+rentInfoId);
		Account account = accountWxServiceRead.findByUserId(userLogin.getUserId());
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
		User nowUser = userServiceWeixinRead.findByUId(userLogin.getUserId());
		/*if(account.getAccountDeposit().compareTo(BigDecimal.ZERO)<=0){
			messageUtil.setMessage("fail");
			messageUtil.setCode(0);
			return messageUtil;
		}*/
		String payTime  =  redisService.get(nowUser.getUserId()+"payTime");//用户退款时间
		if(null!=payTime){
			loggers.info("用户:"+nowUser.getUserRealname()+"上次支付时间---"+payTime);
			Long diffSeconds = DateUtil.secondDiff(DateUtil.changStringDate(payTime), new Date());
			if(diffSeconds<5){  //小于5秒
				messageUtil.setCode(0);
				messageUtil.setMessage("已点击支付");
				return messageUtil;
			}
		}
		redisService.set(nowUser.getUserId()+"payTime", DateUtil.format(new Date()),30);
		//关闭jedis
		redisService.closeJedis();
		List<RechargeRecord> rechargeRecords = rechargeWxServiceRead.findRecharge(account.getAccountId());//查询账户充值记录
		BigDecimal rechargeAllMoney = new BigDecimal(0);
		if(rechargeRecords!=null){
			for(RechargeRecord rechargeRecord : rechargeRecords){
				rechargeAllMoney = rechargeAllMoney.add(rechargeRecord.getRechargeMoney());
			}
		}
		BigDecimal refundFee = rechargeAllMoney.subtract(new BigDecimal(payMoneys).setScale(2, BigDecimal.ROUND_HALF_UP));//实际退款金额
		loggers.info("实际退款金额"+refundFee);
		Double refundFeeDou = refundFee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		if(refundFeeDou==0){ //优惠券不需要押金时
			BigDecimal totalMoney = account.getAccountTotalmoney().subtract(new BigDecimal(payMoneys));//账户剩余金额
			account.setAccountFinalConsumeTime(new Date());//最后消费时间
			account.setAccountDeposit(new BigDecimal(0));
			account.setAccountTotalmoney(totalMoney);
			accountWxServiceWrite.updateAccount(account);
			moneyLogWxServiceWrite.addNewMoneyLog(payMoneys,account.getAccountId(),bikeRentInfo.getRentBikeChannelId());//消费日志
			
			//更新充值记录
			if(rechargeRecords!=null){
				for(RechargeRecord rechargeRecord : rechargeRecords){
					rechargeRecord.setRechargeState(3);//已退款
					rechargeWxServiceWrite.updateRechargeRecord(rechargeRecord);
				}
			}
			loggers.info("更新充值记录"+refundFee);
			//更改状态
			nowUser.setUserState(0);
			userServiceWeixinWrite.updateUser(nowUser);
			loggers.info("用户账号"+nowUser.getUserTel());
			loggers.info("更新用户状态"+refundFee);
			if(null!=userCouponId){
				UserCoupon userCoupon = userCouponServiceWxRead.findUserCouponId(userCouponId);
				userCoupon.setUstate(3);//已使用
				userCouponServiceWxWrite.update(userCoupon);
				loggers.info("更新优惠券");
				CashCoupon cashCoupon = userCouponServiceWxRead.findCouponById(userCoupon.getUcouponId());
				bikeRentInfo.setRentCouponMoney(cashCoupon.getCouponMoney());
			}
			bikeRentInfo.setRentState(1);
			bikeRentInfo.setRentPayTime(new Date());
			bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
			loggers.info("更新订单状态");
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
			
		}else{
			if(rechargeRecords!=null){
				RechargeRecord needrechargeRecord = null;
				if(rechargeRecords.size()>1){
					for(RechargeRecord rechargeRecord : rechargeRecords){
						if(rechargeRecord.getRechargeMoney().compareTo(new BigDecimal(payMoneys))>0){//充值金额大于支付金额
							needrechargeRecord = rechargeRecord;
						}
					}
					if(null!=needrechargeRecord){//存在充值金额大于支付金额的充值订单
						refundFee = needrechargeRecord.getRechargeMoney().subtract(new BigDecimal(payMoneys));
						if(needrechargeRecord.getRechargePayType()==1){//微信
							if(WXHttpApi.wechatRefund(needrechargeRecord.getRechargeOrderId(), 
									needrechargeRecord.getRechargeOutTradeNo(), needrechargeRecord.getRechargeMoney(),refundFee,
									appConfig.getApp_id(), appConfig.getApp_secret(), 
									appConfig.getApp_partner(), appConfig.getApp_partner(), appConfig.getApp_partnerkey(),appConfig.getApp_certificate())){//退款接口
								
								record(account,userLogin,bikeRentInfo,payMoneys,refundFee,needrechargeRecord,1,userCouponId);
								loggers.info("退款成功");
							}
						}else if(needrechargeRecord.getRechargePayType()==2){//支付宝
							AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.app_id,AlipayConfig.private_key,"json",AlipayConfig.input_charset,AlipayConfig.alipay_public_key,"RSA2");
							AlipayTradeRefundRequest alirequest = new AlipayTradeRefundRequest();
							alirequest.setBizContent("{" +
							"    \"out_trade_no\":\""+needrechargeRecord.getRechargeOutTradeNo()+"\"," +
							"    \"refund_amount\":"+refundFee.setScale(2, BigDecimal.ROUND_DOWN).toString()+"," +
							"    \"refund_reason\":\"正常退款\"," +
							"    \"out_request_no\":\""+needrechargeRecord.getRechargeOutTradeNo()+"\"" +
							"  }");
							AlipayTradeRefundResponse response = alipayClient.execute(alirequest);
							if(response.isSuccess()){
							System.out.println("调用成功");
							System.out.println(response.getCode());
								if("10000".equals(response.getCode())){
									record(account,userLogin,bikeRentInfo,payMoneys,refundFee,needrechargeRecord,3,userCouponId);
									loggers.info("退款成功");
								}
							}
						}else if(needrechargeRecord.getRechargePayType()==3){//微信app
							if(WXHttpApi.wechatRefundAPP(needrechargeRecord.getRechargeOrderId(), needrechargeRecord.getRechargeOutTradeNo(), needrechargeRecord.getRechargeMoney(),
									refundFee,WXPayConfig.appid, WXPayConfig.appsecret, WXPayConfig.mch_id, 
									WXPayConfig.mch_id, WXPayConfig.key)){
								record(account,userLogin,bikeRentInfo,payMoneys,refundFee,needrechargeRecord,4,userCouponId);
								loggers.info("退款成功");
							}
						}
						
						rechargeRecords.remove(needrechargeRecord);
						for(RechargeRecord rechargeRecord : rechargeRecords){
							refund(rechargeRecord,account,userLogin);
						}
					}else{//不存在充值金额大于支付金额的充值订单
						BigDecimal m = new BigDecimal(payMoneys);
						for(RechargeRecord rechargeRecord : rechargeRecords){
							if(m.compareTo(rechargeRecord.getRechargeMoney())>-1){
								m = new BigDecimal(payMoneys).subtract(rechargeRecord.getRechargeMoney());
								rechargeRecord.setRechargeState(3);//已退款
								rechargeWxServiceWrite.updateRechargeRecord(rechargeRecord);
								account.setAccountDeposit(account.getAccountDeposit().subtract(rechargeRecord.getRechargeMoney()));
								account.setAccountTotalmoney(account.getAccountTotalmoney().subtract(rechargeRecord.getRechargeMoney()));
								accountWxServiceWrite.updateAccount(account);
							}else{
								if(rechargeRecord.getRechargePayType()==1){//微信
									if(WXHttpApi.wechatRefund(rechargeRecord.getRechargeOrderId(), 
											rechargeRecord.getRechargeOutTradeNo(), rechargeRecord.getRechargeMoney(),rechargeRecord.getRechargeMoney().subtract(m),
											appConfig.getApp_id(), appConfig.getApp_secret(), 
											appConfig.getApp_partner(), appConfig.getApp_partner(), appConfig.getApp_partnerkey(),appConfig.getApp_certificate())){//退款接口
										
										record(account,userLogin,bikeRentInfo,payMoneys,refundFee,rechargeRecord,1,userCouponId);
										loggers.info("退款成功");
										m = new BigDecimal(0);
									}
								}else if(rechargeRecord.getRechargePayType()==2){//支付宝
									BigDecimal refundFees = rechargeRecord.getRechargeMoney().subtract(m);
									AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.app_id,AlipayConfig.private_key,"json",AlipayConfig.input_charset,AlipayConfig.alipay_public_key,"RSA2");
									AlipayTradeRefundRequest alirequest = new AlipayTradeRefundRequest();
									alirequest.setBizContent("{" +
									"    \"out_trade_no\":\""+rechargeRecord.getRechargeOutTradeNo()+"\"," +
									"    \"refund_amount\":"+refundFees.setScale(2, BigDecimal.ROUND_DOWN).toString()+"," +
									"    \"refund_reason\":\"正常退款\"," +
									"    \"out_request_no\":\""+rechargeRecord.getRechargeOutTradeNo()+"\"" +
									"  }");
									AlipayTradeRefundResponse response = alipayClient.execute(alirequest);
									if(response.isSuccess()){
									System.out.println("调用成功");
									System.out.println(response.getCode());
										if("10000".equals(response.getCode())){
											record(account,userLogin,bikeRentInfo,payMoneys,refundFee,rechargeRecord,3,userCouponId);
											loggers.info("退款成功");
										}
									}
									m = new BigDecimal(0);
								}else if(rechargeRecord.getRechargePayType()==3){//微信app
									if(WXHttpApi.wechatRefundAPP(rechargeRecord.getRechargeOrderId(), rechargeRecord.getRechargeOutTradeNo(), rechargeRecord.getRechargeMoney(),
											rechargeRecord.getRechargeMoney().subtract(m),WXPayConfig.appid, WXPayConfig.appsecret, WXPayConfig.mch_id, 
											WXPayConfig.mch_id, WXPayConfig.key)){
										record(account,userLogin,bikeRentInfo,payMoneys,refundFee,rechargeRecord,4,userCouponId);
										loggers.info("退款成功");
									}
									
									m = new BigDecimal(0);
								}
								
							}
							
						}
					}
				}else{
					needrechargeRecord = rechargeRecords.get(0);
					if(needrechargeRecord.getRechargePayType()==1){//微信
						loggers.info("微信支付实际退款金额"+refundFee);
						if(WXHttpApi.wechatRefund(needrechargeRecord.getRechargeOrderId(), 
								needrechargeRecord.getRechargeOutTradeNo(), needrechargeRecord.getRechargeMoney(),refundFee,
								appConfig.getApp_id(), appConfig.getApp_secret(), 
								appConfig.getApp_partner(), appConfig.getApp_partner(), appConfig.getApp_partnerkey(),appConfig.getApp_certificate())){//退款接口
							
							record(account,userLogin,bikeRentInfo,payMoneys,refundFee,needrechargeRecord,1,userCouponId);
							loggers.info("退款成功");
						}else{
							messageUtil.setMessage("noRecharge");
							return messageUtil;
						}
					}else if(needrechargeRecord.getRechargePayType()==2){//支付宝
						loggers.info("支付宝实际退款金额"+refundFee);
						AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.app_id,AlipayConfig.private_key,"json",AlipayConfig.input_charset,AlipayConfig.alipay_public_key,"RSA2");
						AlipayTradeRefundRequest alirequest = new AlipayTradeRefundRequest();
						alirequest.setBizContent("{" +
						"    \"out_trade_no\":\""+needrechargeRecord.getRechargeOutTradeNo()+"\"," +
						"    \"refund_amount\":"+refundFee.setScale(2, BigDecimal.ROUND_DOWN).toString()+"," +
						"    \"refund_reason\":\"正常退款\"," +
						"    \"out_request_no\":\""+needrechargeRecord.getRechargeOutTradeNo()+"\"" +
						"  }");
						AlipayTradeRefundResponse response = alipayClient.execute(alirequest);
						if(response.isSuccess()){
						System.out.println("调用成功");
						System.out.println(response.getCode());
							if("10000".equals(response.getCode())){
								record(account,userLogin,bikeRentInfo,payMoneys,refundFee,needrechargeRecord,3,userCouponId);
								loggers.info("退款成功");
							}else{
								messageUtil.setMessage("noRecharge");
								return messageUtil;
							}
						}else{
							messageUtil.setMessage("fail");
							messageUtil.setCode(0);
							return messageUtil;
						}
					}else if(needrechargeRecord.getRechargePayType()==3){//微信app
						loggers.info("微信app支付实际退款金额"+refundFee);
						if(WXHttpApi.wechatRefundAPP(needrechargeRecord.getRechargeOrderId(), needrechargeRecord.getRechargeOutTradeNo(), needrechargeRecord.getRechargeMoney(),
								refundFee,WXPayConfig.appid, WXPayConfig.appsecret, WXPayConfig.mch_id, 
								WXPayConfig.mch_id, WXPayConfig.key)){
							record(account,userLogin,bikeRentInfo,payMoneys,refundFee,needrechargeRecord,4,userCouponId);
							loggers.info("退款成功");
						}
					}
					
				}
			
			}
		}
		
		messageUtil.setMessage("退款成功");
		messageUtil.setCode(1);
		return messageUtil;
	}
	
	/**
	 * 退款
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toRefund", method = RequestMethod.POST)
	public @ResponseBody MessageUtil toRefund(@RequestHeader HttpHeaders header,HttpSession session) throws Exception{
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
		loggers.info("用户Id为:"+userLogin.getUserId());
		Account account = accountWxServiceRead.findByUserId(userLogin.getUserId());
		User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findNotPayByUserId(userLogin.getUserId());
		if(user.getUserState()!=0){
			if(bikeRentInfo!=null){
				loggers.info("订单未支付，无法退款");
				messageUtil.setMessage("订单未支付，无法退款");
				messageUtil.setCode(0);
				return messageUtil;
			}else { 
				loggers.info("租赁中，无法退款");
				messageUtil.setMessage("租赁中，无法退款");
				messageUtil.setCode(0);
				return messageUtil;
			}
		
		}
		if(account.getAccountDeposit().compareTo(BigDecimal.ZERO)<=0){
			loggers.info("账户为0，无法退款");
			messageUtil.setMessage("退款失败");
			messageUtil.setCode(0);
			return messageUtil;
		}
		String refundTime  =  redisService.get(user.getUserId()+"refundTime");//用户退款时间
		if(null!=refundTime){
			loggers.info("用户:"+user.getUserRealname()+"上次退款时间---"+refundTime);
			Long diffSeconds = DateUtil.secondDiff(DateUtil.changStringDate(refundTime), new Date());
			if(diffSeconds<5){  //小于5秒
				messageUtil.setCode(0);
				messageUtil.setMessage("已点击退款，请稍后等待");
				return messageUtil;
			}
		}
		redisService.set(user.getUserId()+"refundTime", DateUtil.format(new Date()),30);
		//关闭jedis
		redisService.closeJedis();
		List<RechargeRecord> rechargeRecords = rechargeWxServiceRead.findRecharge(account.getAccountId());
		if(rechargeRecords!=null){
			for(RechargeRecord rechargeRecord : rechargeRecords){
				refund(rechargeRecord,account,userLogin);
			}
		}else{
			loggers.info("不存在充值订单，无法退款");
			messageUtil.setMessage("退款失败!");
			return messageUtil;
		}
		messageUtil.setMessage("退款成功");
		messageUtil.setCode(1);
		return messageUtil;
	}
	
	
	public void refund(RechargeRecord rechargeRecord, Account account, User user) throws Exception{
		if(rechargeRecord.getRechargePayType()==1){//微信
			if(WXHttpApi.wechatRefund(rechargeRecord.getRechargeOrderId(), 
					rechargeRecord.getRechargeOutTradeNo(), rechargeRecord.getRechargeMoney(),rechargeRecord.getRechargeMoney(),
					appConfig.getApp_id(), appConfig.getApp_secret(), 
					appConfig.getApp_partner(), appConfig.getApp_partner(), appConfig.getApp_partnerkey(),appConfig.getApp_certificate())){//退款接口
				Refund nowRefund = refundWxServiceRead.findRefundByorderId(rechargeRecord.getRechargeOrderId());
				if(nowRefund==null){
					Refund refund = new Refund();
					refund.setRefundMoney(rechargeRecord.getRechargeMoney());
					refund.setRefundCreatetime(new Date());
					refund.setRefundType(1);
					refund.setRefundState(1);//退款中
					refund.setRefundAccountId(account.getAccountId());
					refund.setRefundOrderId(rechargeRecord.getRechargeOrderId());
					refund.setRefundCode(rechargeRecord.getRechargeOutTradeNo());
					refund.setRefundSource(1);
					refundWxServiceWrite.addRefundApplication(refund);//添加退款记录
					
					Account nowAccount = accountWxServiceRead.findByUserId(user.getUserId());
					Message message = new Message();
				    message.setMessageUserId(user.getUserId());
					message.setMessageContent("您的预付款已退还，退还金额为"+rechargeRecord.getRechargeMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
					message.setMessageSendTime(new Date());
					message.setMessageTitle("退款申请成功");
					messageWxServiceWrite.addMessage(message);
					accountWxServiceWrite.updateAccountAndLog(nowAccount, rechargeRecord.getRechargeMoney(),rechargeRecord);//退款日志
				}
				
			}
		}else if(rechargeRecord.getRechargePayType()==2){//支付宝
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",AlipayConfig.app_id,AlipayConfig.private_key,"json",AlipayConfig.input_charset,AlipayConfig.alipay_public_key,"RSA2");
			AlipayTradeRefundRequest alirequest = new AlipayTradeRefundRequest();
			alirequest.setBizContent("{" +
			"    \"out_trade_no\":\""+rechargeRecord.getRechargeOutTradeNo()+"\"," +
			"    \"refund_amount\":"+rechargeRecord.getRechargeMoney().setScale(2, BigDecimal.ROUND_DOWN).toString()+"," +
			"    \"refund_reason\":\"正常退款\"," +
			"    \"out_request_no\":\""+rechargeRecord.getRechargeOutTradeNo()+"\"" +
			"  }");
			AlipayTradeRefundResponse response = alipayClient.execute(alirequest);
			if(response.isSuccess()){
			System.out.println("调用成功");
			System.out.println(response.getCode());
			if("10000".equals(response.getCode())){
				System.out.println();
				Refund nowRefund = refundWxServiceRead.findRefundByorderId(rechargeRecord.getRechargeOrderId());
				if(nowRefund==null){
					Refund refund = new Refund();
					refund.setRefundMoney(rechargeRecord.getRechargeMoney());
					refund.setRefundCreatetime(new Date());
					refund.setRefundType(1);
					refund.setRefundState(1);//退款中
					refund.setRefundSource(3);
					refund.setRefundAccountId(account.getAccountId());
					if(null!=rechargeRecord.getRechargeOrderId()&&!rechargeRecord.getRechargeOrderId().equals("")){
						refund.setRefundOrderId(rechargeRecord.getRechargeOrderId());
					}
					refund.setRefundCode(rechargeRecord.getRechargeOutTradeNo());
					refundWxServiceWrite.addRefundApplication(refund);//添加退款记录
					loggers.info("添加退款记录");
					Account nowAccount = accountWxServiceRead.findByUserId(user.getUserId());
					Message message = new Message();
				    message.setMessageUserId(user.getUserId());
					message.setMessageContent("您的预付款已退还，退还金额为"+rechargeRecord.getRechargeMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
					message.setMessageSendTime(new Date());
					message.setMessageTitle("退款申请成功");
					messageWxServiceWrite.addMessage(message);
					accountWxServiceWrite.updateAccountAndLog(nowAccount, rechargeRecord.getRechargeMoney(),rechargeRecord);//退款日志
					loggers.info("更新账户并添加资金日志");
				}
				
			}
			}
		}else if(rechargeRecord.getRechargePayType()==3){//微信app
			if(WXHttpApi.wechatRefundAPP(rechargeRecord.getRechargeOrderId(), rechargeRecord.getRechargeOutTradeNo(), rechargeRecord.getRechargeMoney(),
					rechargeRecord.getRechargeMoney(),WXPayConfig.appid, WXPayConfig.appsecret, WXPayConfig.mch_id, 
					WXPayConfig.mch_id, WXPayConfig.key)){
				Refund nowRefund = refundWxServiceRead.findRefundByorderId(rechargeRecord.getRechargeOrderId());
				if(nowRefund==null){
					Refund refund = new Refund();
					refund.setRefundMoney(rechargeRecord.getRechargeMoney());
					refund.setRefundCreatetime(new Date());
					refund.setRefundType(1);
					refund.setRefundState(1);//退款中
					refund.setRefundAccountId(account.getAccountId());
					refund.setRefundOrderId(rechargeRecord.getRechargeOrderId());
					refund.setRefundSource(4);
					refund.setRefundCode(rechargeRecord.getRechargeOutTradeNo());
					refundWxServiceWrite.addRefundApplication(refund);//添加退款记录
					loggers.info("添加退款记录");
					Account nowAccount = accountWxServiceRead.findByUserId(user.getUserId());
					Message message = new Message();
				    message.setMessageUserId(user.getUserId());
					message.setMessageContent("您的预付款已退还，退还金额为"+rechargeRecord.getRechargeMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
					message.setMessageSendTime(new Date());
					message.setMessageTitle("退款申请成功");
					messageWxServiceWrite.addMessage(message);
					accountWxServiceWrite.updateAccountAndLog(nowAccount, rechargeRecord.getRechargeMoney(),rechargeRecord);//退款日志
					loggers.info("更新账户并添加资金日志");
				}
				
			}
		}
	}
	
	public void record(Account account,User userLogin,BikeRentInfo bikeRentInfo,Double payMoneys,BigDecimal refundFee,RechargeRecord rechargeRecord,Integer resource,Long userCouponId) throws Exception{
		Refund nowRefund = refundWxServiceRead.findRefundByorderId(rechargeRecord.getRechargeOrderId());
		if(nowRefund==null){
			account.setAccountFinalConsumeTime(new Date());
			accountWxServiceWrite.updateAccount(account);
			moneyLogWxServiceWrite.addNewMoneyLog(payMoneys,account.getAccountId(),bikeRentInfo.getRentBikeChannelId());//消费日志
			
			User nowUser = userServiceWeixinRead.findByUId(userLogin.getUserId());
			nowUser.setUserState(0);
			userServiceWeixinWrite.updateUser(nowUser);
			loggers.info("优惠券Id"+userCouponId);
			if(null!=userCouponId){
				UserCoupon userCoupon = userCouponServiceWxRead.findUserCouponId(userCouponId);
				userCoupon.setUstate(3);//已使用
				userCouponServiceWxWrite.update(userCoupon);
				loggers.info("更新优惠券");
				CashCoupon cashCoupon = userCouponServiceWxRead.findCouponById(userCoupon.getUcouponId());
				bikeRentInfo.setRentCouponMoney(cashCoupon.getCouponMoney());
			}
			bikeRentInfo.setRentState(1);
			bikeRentInfo.setRentPayTime(new Date());
			bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
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
			
			Refund refund = new Refund();
			refund.setRefundMoney(refundFee);
			refund.setRefundCreatetime(new Date());
			refund.setRefundType(1);
			refund.setRefundState(1);//退款中
			refund.setRefundSource(resource);
			refund.setRefundAccountId(account.getAccountId());
			if(null!=rechargeRecord.getRechargeOrderId()&&!rechargeRecord.getRechargeOrderId().equals("")){
				refund.setRefundOrderId(rechargeRecord.getRechargeOrderId());
			}
			refund.setRefundCode(rechargeRecord.getRechargeOutTradeNo());
			refundWxServiceWrite.addRefundApplication(refund);//添加退款记录
			loggers.info("添加退款记录");
			Account nowAccount = accountWxServiceRead.findByUserId(userLogin.getUserId());
			Message message = new Message();
		    message.setMessageUserId(userLogin.getUserId());
			message.setMessageContent("您的预付款已退还，退还金额为"+refundFee.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
			message.setMessageSendTime(new Date());
			message.setMessageTitle("退款申请成功");
			messageWxServiceWrite.addMessage(message);
			accountWxServiceWrite.updateAccountAndLog(nowAccount, refundFee,rechargeRecord);//退款日志
			loggers.info("更新账户并添加资金日志");
		}
		
	}
	
}
