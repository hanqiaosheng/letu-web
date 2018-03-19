package org.controller.weixin;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.component.AlipayConfig;
import org.component.AppConfig;
import org.component.WXPayConfig;
import org.entity.dto.Account;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.BizContent;
import org.entity.dto.CashCoupon;
import org.entity.dto.DataDet;
import org.entity.dto.GradeRecord;
import org.entity.dto.Invoice;
import org.entity.dto.Message;
import org.entity.dto.RechargeRecord;
import org.entity.dto.SysParament;
import org.entity.dto.Tradeorder;
import org.entity.dto.User;
import org.entity.dto.UserCoupon;
import org.entity.dto.UserToTask;
import org.service.weixin.read.AccountWxServiceRead;
import org.service.weixin.read.BikeRentInfoWxServiceRead;
import org.service.weixin.read.DataDetWxServiceRead;
import org.service.weixin.read.InvoiceWxServiceRead;
import org.service.weixin.read.RechargeWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.TrOrderWxServiceRead;
import org.service.weixin.read.UserCouponServiceWxRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.service.weixin.write.AccountWxServiceWrite;
import org.service.weixin.write.BikeRentInfoWxServiceWrite;
import org.service.weixin.write.GradeWxServiceWrite;
import org.service.weixin.write.InvoiceWxServiceWrite;
import org.service.weixin.write.MessageWxServiceWrite;
import org.service.weixin.write.MoneyLogWxServiceWrite;
import org.service.weixin.write.RechargeWxServiceWrite;
import org.service.weixin.write.TrOrderWxServiceWrite;
import org.service.weixin.write.UserCouponServiceWxWrite;
import org.service.weixin.write.UserServiceWeixinWrite;
import org.service.weixin.write.UserToTaskWxServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.IPUtil;
import org.util.LevelUtil;
import org.util.MessageUtil;
import org.util.SplitUtil;
import org.util.alipay.RequestUtils;
import org.util.weixin.GetWxOrderno;
import org.util.weixin.PayUtil;
import org.util.weixin.Sha1Util;

import com.alipay.api.internal.util.AlipaySignature;


@Controller                                                                            
@Scope("prototype")
@RequestMapping(value = "/ali")
public class AliPayController {


	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	UserServiceWeixinWrite userServiceWeixinWrite;
	@Resource
	RechargeWxServiceRead rechargeWxServiceRead;
	@Resource
	RechargeWxServiceWrite rechargeWxServiceWrite;
	@Resource
	TrOrderWxServiceRead trOrderWxServiceRead;
	@Resource
	AccountWxServiceRead accountWxServiceRead;
	@Resource
	MoneyLogWxServiceWrite moneyLogWxServiceWrite;
	@Resource
	TrOrderWxServiceWrite trOrderWxServiceWrite;
	@Resource
	AccountWxServiceWrite accountWxServiceWrite;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	MessageWxServiceWrite messageWxServiceWrite;
	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;
	@Resource
	BikeRentInfoWxServiceWrite bikeRentInfoWxServiceWrite;
	@Resource
	AppConfig AppConfig;
	@Resource
	InvoiceWxServiceRead invoiceWxServiceRead;
	@Resource
	InvoiceWxServiceWrite invoiceWxServiceWrite;
	@Resource
	UserCouponServiceWxRead userCouponServiceWxRead;
	@Resource
	UserCouponServiceWxWrite userCouponServiceWxWrite;
	@Resource
	DataDetWxServiceRead dataDetWxServiceRead;
	@Resource
	GradeWxServiceWrite gradeWxServiceWrite;
	@Resource
	UserToTaskWxServiceWrite userToTaskWxServiceWrite;
	
	
	protected final MessageUtil messageUtil = new MessageUtil();   
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(AliPayController.class);
	
	/**
	 * 支付
	 * payType(1支付宝2app微信支付)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pay", method = RequestMethod.POST)
	public @ResponseBody MessageUtil pay(@RequestHeader HttpHeaders header,BigDecimal money,Integer orderType,Long rentInfoId,Long invoiceId,String rentIdstr,Integer payType,HttpServletRequest request,Long userCouponId) throws Exception{
		String token = header.getFirst("token");
		SysParament sysParament = sysParamentServiceRead.findByName("token");
		User userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		if(null==userLogin){
			messageUtil.setCode(2);
			messageUtil.setMessage("登录超时");
			return messageUtil;
		}
		Map<String,Object> data = new HashMap<String,Object>();
		if(payType==2){
			String out_trade_no = userLogin.getUserId()+Sha1Util.getTimeStamp();
			loggers.info("传入的out_trade_no为"+out_trade_no);
			//账单金额 单位：分
			Integer total_fee = money.multiply(new BigDecimal("100")).intValue();
			loggers.info("total_fee为"+total_fee);
			//回调地址
			String notify_url =	AppConfig.getApp_domain()+ "ali/appverify.action";
			//1.调用统一下单接口      获取返回正常的prepay_id
			String prepay_id = PayUtil.getPrepay_idApp(out_trade_no, WXPayConfig.appid, notify_url, WXPayConfig.mch_id,  WXPayConfig.key, IPUtil.getClientIpAddr(request), total_fee);
			//2.按签名规范重新生成签名后，将数据传输给APP。
			//参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package。
			SortedMap<Object, Object> wxData = PayUtil.generateNewOrderApp(WXPayConfig.appid, WXPayConfig.mch_id, prepay_id, WXPayConfig.key);
			//*************生成订单信息*****************
			Tradeorder trOrder = new Tradeorder();
			trOrder.setTrOrderCreatetime(new Date());
			trOrder.setTrOrderMoney(money);
			trOrder.setTrOrderState((short)0);//未支付
			trOrder.setTrOrderUserid(userLogin.getUserId());
			trOrder.setTrOrderType(orderType);
			trOrder.setTrOrderOption(3);//微信app支付
			trOrder.setTrOrderTransactionNumber(out_trade_no);
			if(null!=userCouponId){
				trOrder.setTrOrderUserCouponId(userCouponId);
			}
			if(orderType==1){//消费
				trOrder.setTrOrderRentId(rentInfoId);
				loggers.info("租赁订单为"+rentInfoId);
			}
			if(orderType==2){//邮费支付
				trOrder.setTrOrderRentidstr(rentIdstr);
				trOrder.setTrOrderInvoiceid(invoiceId);
			}
			trOrderWxServiceWrite.addTrOrder(trOrder);
			loggers.info("添加支付订单");
			data.put("wxData", wxData);
			messageUtil.setData(data);
		}else if(payType==1){
			String app_id = AlipayConfig.app_id;
			String biz_content = "";
			String charset = AlipayConfig.input_charset;
			String method = "alipay.trade.app.pay";
			String notify_url =AppConfig.getApp_domain()+"ali/verify.action";
			String sign_type = "RSA2";
			Date createTime = new Date();
			String timestamp = DateUtil.format(createTime);
			String version = "1.0";

			//商户交易号
			String trade_no = generateTransactionNumber(userLogin.getUserId());
			loggers.info("传入的out_trade_no为"+trade_no);
			//****** 创建订单信息 ****
			Tradeorder aliOrder = new Tradeorder();
			aliOrder.setTrOrderTransactionNumber(trade_no);
			aliOrder.setTrOrderUserid(userLogin.getUserId());
			aliOrder.setTrOrderCreatetime(createTime);
			aliOrder.setTrOrderMoney(money);
			aliOrder.setTrOrderState((short)0);//未支付
			aliOrder.setTrOrderType(orderType);
			if(null!=userCouponId){
				aliOrder.setTrOrderUserCouponId(userCouponId);
			}
			if(orderType==1){//消费
				aliOrder.setTrOrderRentId(rentInfoId);
				loggers.info("租赁订单为"+rentInfoId);
			}
			if(orderType==2){//邮费支付
				aliOrder.setTrOrderRentidstr(rentIdstr);
				aliOrder.setTrOrderInvoiceid(invoiceId);
			}
			trOrderWxServiceWrite.addTrOrder(aliOrder);
			loggers.info("添加支付订单");

			BizContent bizContent = new BizContent();
			if(orderType==0){//预付款充值
				bizContent.setBody("预付款充值:"+money.setScale(2, BigDecimal.ROUND_DOWN).toString()+"元");
				bizContent.setSubject("预付款充值");
			}else if(orderType==1){//消费
				bizContent.setBody("消费支付:"+money.setScale(2, BigDecimal.ROUND_DOWN).toString()+"元");
				bizContent.setSubject("消费支付");
			}else if(orderType==2){//邮费
				bizContent.setBody("邮费支付:"+money.setScale(2, BigDecimal.ROUND_DOWN).toString()+"元");
				bizContent.setSubject("邮费支付");
			}else if(orderType==3){//余额充值
				bizContent.setBody("余额充值:"+money.setScale(2, BigDecimal.ROUND_DOWN).toString()+"元");
				bizContent.setSubject("余额充值");
			}
			
			bizContent.setOut_trade_no(trade_no);
			bizContent.setProduct_code("QUICK_MSECURITY_PAY");
			bizContent.setTotal_amount(money.setScale(2, BigDecimal.ROUND_DOWN).toString());
			//该笔订单允许的最晚付款时间 1.5h
			bizContent.setTimeout_express("2m");
			//将bizContent 转换成 json 形式
			biz_content = bizContenttoJson(bizContent);
			//未签名原始字符串
			String content = "app_id=" + app_id + "&biz_content=" + biz_content + "&charset=" + charset + "&method=" + method  
	                + "&notify_url=" + notify_url + "&sign_type=" + sign_type + "&timestamp=" + timestamp + "&version="  
	                + version;
			System.out.println("待签名字符串："+content);
			//签名
			String sign = AlipaySignature.rsaSign(content, AlipayConfig.private_key, AlipayConfig.input_charset, sign_type);
			//encode 后的字符串
			String result = "app_id=" + encode(app_id) + "&biz_content=" + encode(biz_content) + "&charset="  
	                + encode(charset) + "&method=" + encode(method) + "&notify_url=" + encode(notify_url) + "&sign_type="  
	                + encode(sign_type) + "&timestamp=" + encode(timestamp) + "&version=" + encode(version) + "&sign="  
	                + encode(sign);
			System.out.println("encode 后的字符串:"+result);
			data.put("aliSigned", result);
			messageUtil.setData(data);
		}
		
		
		messageUtil.setCode(1);
		messageUtil.setMessage("获取签名后的信息成功");
		return messageUtil;
	}

	//生成商户交易号
	private String generateTransactionNumber(Long userId){
		UUID uuid = UUID.randomUUID();
		String num = uuid.toString()+userId;
		return num;
	}


	//将BizContent 转换为 json
	private String bizContenttoJson(BizContent content) {  
		String context = "";  
		context += "{" + "\"timeout_express\":\"" + content.getTimeout_express() + "\"," + "\"product_code\":\"" + content.getProduct_code() + "\","  
				+ "\"total_amount\":\"" + content.getTotal_amount()+ "\"," + "\"subject\":\"" + content.getSubject() + "\","  
				+ "\"body\":\"" + content.getBody() + "\"," + "\"out_trade_no\":\"" + content.getOut_trade_no() + "\"}";  

		return context;  
	}  

	//encode
	private String encode(String content) throws UnsupportedEncodingException {  
		return URLEncoder.encode(content, "utf-8").replace("+", "%20");  
	}  

	/**
	 * 异步返回结果的验签
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public  void verify(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//通知返回来的参数数组
		Map<String, String> params = RequestUtils.getStringParams(request);
		//1.验证签名  支付宝公钥
		boolean signVerfied = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.input_charset,AlipayConfig.sign_type);

		try{
			String resultResponse = "success";
			PrintWriter printWriter = null;

			try{
				printWriter = response.getWriter();
				//调用SDK验证签名
				if(signVerfied){
					String description = "";
					Boolean result = true;
					// TODO 验签成功后
					//按照支付结果异步通知中的描述，对支付结果中的业务内容进行1\2\3\4二次校验，校验成功后在response中返回success，校验失败返回failure
					//1.验证 商户订单号 out_trade_no
					String out_trade_no = params.get("out_trade_no");
					Tradeorder aliOrder = trOrderWxServiceRead.findByTransactionNumber(out_trade_no);
					if(null == aliOrder){
						System.out.println("1.out_trade_no 验证不通过 ,获取的为："+out_trade_no);
						description += "1.out_trade_no 验证不通过 ,获取的为："+out_trade_no;
						result = false;
					}

					//2.验证   total_amount   是否确实为该订单的实际金额（即商户订单创建时的金额）
					String total_amount = params.get("total_amount");
					if(!result||!aliOrder.getTrOrderMoney().setScale(2).toString().equals(total_amount)){
						System.out.println("2.total_amount 验证不通过，获取的为："+total_amount);
						description += "2.total_amount 验证不通过，获取的为："+total_amount;
						result = false;
					}

					//4.验证app_id是否为该商户本身
					String app_id = params.get("app_id");
					if(!app_id.equals(AlipayConfig.app_id)){
						System.out.println("4.app_id验证不通过，获取的为："+app_id);
						description += "4.app_id验证不通过，获取的为："+app_id;
						result = false;
					}
					
					//5.验证 交易状态
					String trade_status = params.get("trade_status");
					if("TRADE_CLOSED".equals(trade_status)){
						System.out.println("5.验证 交易状态 交易关闭");
						description += "5.验证 交易状态  交易关闭";
						result = false;
					}
					if(result){
						//通过全部的验证
						if((aliOrder.getTrOrderState()==(short)0||aliOrder.getTrOrderState()==(short)2)){
							//订单未处理过
							//用户的账户
							//用户
							User user = userServiceWeixinRead.findByUId(aliOrder.getTrOrderUserid());
							Account account = accountWxServiceRead.findByAccountId(user.getUserAccountId());
							//************ 资金日志表    money_log*************
							System.out.println("增加  资金日志表    money_log...");
							if(0==aliOrder.getTrOrderType())	{//押金充值
	    						accountWxServiceWrite.addAccountMoney(aliOrder.getTrOrderMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), account ,params.get("trade_no"),out_trade_no,1,2);
	    					    loggers.info("添加日志表");
	    						//****************充值记录   recharge_record********************
	    						/*RechargeRecord rechargeRecord = new RechargeRecord();
	    						rechargeRecord.setRechargeAccountId(account.getAccountId());
	    						rechargeRecord.setRechargeMoney(aliOrder.getTrOrderMoney());
	    						rechargeRecord.setRechargePayType(2);
	    						rechargeRecord.setRechargeType(1);
	    						rechargeRecord.setRechargeState(1);
	    						rechargeRecord.setRechargeTime(new Date());
	    						rechargeRecord.setRechargeOutTradeNo(out_trade_no);
	    						rechargeWxServiceWrite.addRechargeRecord(rechargeRecord);*/
	    						
	    						Message message = new Message();
								message.setMessageUserId(user.getUserId());
								message.setMessageContent("恭喜您充值成功，充值金额为"+aliOrder.getTrOrderMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
								message.setMessageSendTime(new Date());
								message.setMessageTitle("充值");
								messageWxServiceWrite.addMessage(message);
	    					
							}else if(1==aliOrder.getTrOrderType()){//消费支付
	    						loggers.info("租赁订单"+aliOrder.getTrOrderRentId());
								account.setAccountDeposit(new BigDecimal(0));
								account.setAccountTotalmoney(account.getAccountAvailableBalance());
								accountWxServiceWrite.updateAccount(account);
								loggers.info("更新账户"+account.getAccountId());
								moneyLogWxServiceWrite.addMoneyLog(account,aliOrder.getTrOrderMoney(), out_trade_no,params.get("trade_no"));
								BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(aliOrder.getTrOrderRentId());
								user.setUserState(0);
								userServiceWeixinWrite.updateUser(user);
								loggers.info("更新用户"+user.getUserId());
								//更新优惠券
								loggers.info("优惠券为"+aliOrder.getTrOrderUserCouponId());
								if(null!=aliOrder.getTrOrderUserCouponId()){
									UserCoupon userCoupon = userCouponServiceWxRead.findUserCouponId(aliOrder.getTrOrderUserCouponId());
									userCoupon.setUstate(3);//已使用
									userCouponServiceWxWrite.update(userCoupon);
									loggers.info("更新优惠券");
									CashCoupon cashCoupon = userCouponServiceWxRead.findCouponById(userCoupon.getUcouponId());
									bikeRentInfo.setRentCouponMoney(cashCoupon.getCouponMoney());
								}
								bikeRentInfo.setRentState(1);
								bikeRentInfo.setRentPayTime(new Date());
								bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
								
	        					DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)38);
	            				Integer grade = Integer.valueOf(datadet.getDataDetVal());
	            				user.setUserTotalGrade(user.getUserTotalGrade()+grade);
	            				user.setUserGrade(user.getUserGrade()+grade);
	            				user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
	            				userServiceWeixinWrite.updateUser(user);//更新用户
	            				loggers.info("更新用户积分");
	            				//积分记录
	            				GradeRecord gradeRecord = new GradeRecord();
	            				gradeRecord.setGradeCount(grade);
	            				gradeRecord.setGradeRemark("正常骑行");
	            				gradeRecord.setGradeUserId(user.getUserId());
	            				gradeRecord.setGradeState(0);//获取的
	            				gradeRecord.setGradeCreateTime(new Date());
	            				gradeWxServiceWrite.addGradeRecord(gradeRecord);
	            				loggers.info("添加积分记录");
								//更新充值记录
								List<RechargeRecord> rechargeRecords = rechargeWxServiceRead.findRecharge(account.getAccountId());//查询账户充值记录
								if(null!=rechargeRecords){
									for(RechargeRecord rechargeRecord : rechargeRecords){
										rechargeRecord.setRechargeState(3);//已退款
										rechargeWxServiceWrite.updateRechargeRecord(rechargeRecord);
									}
								}
							}else if(2==aliOrder.getTrOrderType()){//邮费支付
								moneyLogWxServiceWrite.addPosMoneyLog(account, aliOrder.getTrOrderMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), out_trade_no,params.get("trade_no"));
								String[] newStr = SplitUtil.toSplit(aliOrder.getTrOrderRentidstr());
								if(newStr.length>0){
									for(int i = 0;i<newStr.length;i++){
										BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(Long.valueOf(newStr[i]));
										bikeRentInfo.setRentInvoiceId(aliOrder.getTrOrderInvoiceid());
										bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
									}
								}
								Invoice invoice = invoiceWxServiceRead.findById(aliOrder.getTrOrderInvoiceid());
								invoice.setInvoiceState(0);
								invoiceWxServiceWrite.updateInvoice(invoice);
							}else if(3==aliOrder.getTrOrderType()){//余额充值
								accountWxServiceWrite.addAccountMoney(aliOrder.getTrOrderMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), account ,params.get("trade_no"),out_trade_no,2,2);
								List<RechargeRecord> oldRechargeRecord = rechargeWxServiceRead.findRechargeByAccountId(account.getAccountId(), 2);//会员余额充值记录
								if(oldRechargeRecord.size()==0){
									DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)40);
									Integer grade = Integer.valueOf(datadet.getDataDetVal());
									user.setUserTotalGrade(user.getUserTotalGrade()+grade);
									user.setUserGrade(user.getUserGrade()+grade);
									user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
									userServiceWeixinWrite.updateUser(user);
									loggers.info("更新用户积分");
									UserToTask newuserToTask = new UserToTask();
									newuserToTask.setUserToTaskState(2);
									newuserToTask.setUserId(user.getUserId());
									newuserToTask.setDataDetId((long)40);
									userToTaskWxServiceWrite.addUserToTask(newuserToTask);
									loggers.info("添加积分任务");
									//积分记录
									GradeRecord gradeRecord = new GradeRecord();
									gradeRecord.setGradeCount(grade);
									gradeRecord.setGradeRemark("首次充值");
									gradeRecord.setGradeUserId(user.getUserId());
									gradeRecord.setGradeCreateTime(new Date());
									gradeRecord.setGradeState(0);//获取的
									gradeWxServiceWrite.addGradeRecord(gradeRecord);
									loggers.info("添加积分记录");
								}
								Message message = new Message();
								message.setMessageUserId(user.getUserId());
								message.setMessageContent("恭喜您充值成功，充值金额为"+aliOrder.getTrOrderMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
								message.setMessageSendTime(new Date());
								message.setMessageTitle("充值");
								messageWxServiceWrite.addMessage(message);
							}
							//************* 更新 支付宝订单  ali_order ***************
							aliOrder.setTrOrderState((short)1);
							aliOrder.setTrOrderPaytime(DateUtil.changStringDate(params.get("gmt_payment")));
							aliOrder.setTrOrderBuyerLogonId(params.get("buyer_logon_id"));
							trOrderWxServiceWrite.updateTrOrder(aliOrder);
							
						}
					}else{
						// TODO 验签失败则记录异常日志，并在response中返回failure.
						//************* 更新 支付宝订单  ali_order ***************
						if(null!=aliOrder){
							aliOrder.setTrOrderState((short)2);
							aliOrder.setTrOrderDescription(description);
							trOrderWxServiceWrite.updateTrOrder(aliOrder);
						}
						resultResponse = "failure";
					}
				}else {
					resultResponse = "failure";
				}
			}catch(Exception e){
				resultResponse = "failure";
				printWriter.close();

			}
			if (printWriter != null) {
				printWriter.print(resultResponse);

			}
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
	
	/**
	 * 异步通知回调 APP
	 * @throws Exception
	 */
	@RequestMapping("/appverify")
	public void cnotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 try{  
	            BufferedReader reader = request.getReader();  
	  
	            String line = "";  
	            StringBuffer inputString = new StringBuffer();  
	  
	            try{  
	                PrintWriter writer = response.getWriter();  
	  
	                while ((line = reader.readLine()) != null) {  
	                    inputString.append(line);  
	                }  
	  
	                if(reader!=null){  
	                    reader.close();  
	                }  
	  
	                System.out.println("----[微信回调]接收到的报文---"+inputString.toString());  
	                if(null!=inputString.toString()&&!"".equals(inputString.toString())){  
	                	Map<String, String> map = new GetWxOrderno().doXMLParse(inputString.toString());
	                    if("SUCCESS".equalsIgnoreCase(map.get("return_code"))){  
	                        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();  
	                        parameters.put("appid", map.get("appid"));  
	                        parameters.put("attach", map.get("attach"));  
	                        parameters.put("bank_type", map.get("bank_type"));  
	                        parameters.put("cash_fee", map.get("cash_fee"));  
	                        parameters.put("fee_type", map.get("fee_type"));  
	                        parameters.put("is_subscribe", map.get("is_subscribe"));
	                        parameters.put("mch_id", map.get("mch_id"));  
	                        parameters.put("nonce_str", map.get("nonce_str"));  
	                        parameters.put("openid", map.get("openid"));  
	                        parameters.put("out_trade_no", map.get("out_trade_no"));  
	                        parameters.put("result_code", map.get("result_code"));  
	                        parameters.put("return_code", map.get("return_code"));  
	                        parameters.put("time_end", map.get("time_end"));  
	                        parameters.put("total_fee", map.get("total_fee"));  
	                        parameters.put("trade_type", map.get("trade_type"));  
	                        parameters.put("transaction_id", map.get("transaction_id"));  
	                       
	                        //反校验签名  
	                        String sign = PayUtil.createSign("UTF-8", parameters,WXPayConfig.key);  
	  
	                        if(sign.equals(map.get("sign"))){  
	                        	Boolean result = true;
	                        	String description = "";
	                        	 //签名验证成功
	                        	//1.验证 商户订单号 out_trade_no
	                        	Tradeorder wxOrder = trOrderWxServiceRead.findByTransactionNumber(map.get("out_trade_no"));
	                        	if(null == wxOrder){
	                        		result = false;
	                        		description += "1.out_trade_no 验证不通过";
	                        	}
	                        	//2.验证   total_fee   是否确实为该订单的实际金额（即商户订单创建时的金额）
	                        	if(!result||wxOrder.getTrOrderMoney().multiply(new BigDecimal("100")).intValue()!=Integer.valueOf(map.get("total_fee"))){
	                        		result = false;
	                        		description += "2.total_fee 验证不通过";
	                        	}
	                        	//3.验证app_id是否为该商户本身
	                        	if(!map.get("appid").equals(WXPayConfig.appid)){
	                        		result = false;
	                        		description += "3.app_id 验证不通过";
	                        	}
	                        	if(result){
	                        		
	                        		//其他参数验证<通过>
	                        		if(wxOrder.getTrOrderState() == 0||wxOrder.getTrOrderState() == 2){
	                        			//该账单未验证过
	                        			//修改订单的状态  
	        	    					//用户的账户
	        	    					User user =  userServiceWeixinRead.findByUId(wxOrder.getTrOrderUserid());
	        	    					Account account = accountWxServiceRead.findByAccountId(user.getUserAccountId());
	        	    					//InetAddress address = InetAddress.getLocalHost();
	        	    					if(0==wxOrder.getTrOrderType())	{//押金充值
	        	    						accountWxServiceWrite.addAccountMoney(wxOrder.getTrOrderMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), account ,map.get("transaction_id"),map.get("out_trade_no"),1,3);
	        	    						loggers.info("添加日志表");
	        	    						//****************充值记录   recharge_record********************
	        	    						Message message = new Message();
	        								message.setMessageUserId(user.getUserId());
	        								message.setMessageContent("恭喜您充值成功，充值金额为"+wxOrder.getTrOrderMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
	        								message.setMessageSendTime(new Date());
	        								message.setMessageTitle("充值");
	        								messageWxServiceWrite.addMessage(message);
	        	    					}else if(1==wxOrder.getTrOrderType()){//消费支付
	        								account.setAccountDeposit(new BigDecimal(0));
	        								account.setAccountTotalmoney(account.getAccountAvailableBalance());
	        								accountWxServiceWrite.updateAccount(account);
	        								moneyLogWxServiceWrite.addMoneyLog(account,wxOrder.getTrOrderMoney(), map.get("out_trade_no"),map.get("transaction_id"));
	        								BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(wxOrder.getTrOrderRentId());
	        								user.setUserState(0);
	        								userServiceWeixinWrite.updateUser(user);
	        								loggers.info("更新用户状态"+user.getUserId());
	        								//更新优惠券
	        								if(null!=wxOrder.getTrOrderUserCouponId()){
	        									UserCoupon userCoupon = userCouponServiceWxRead.findUserCouponId(wxOrder.getTrOrderUserCouponId());
	        									userCoupon.setUstate(3);//已使用
	        									userCouponServiceWxWrite.update(userCoupon);
	        									loggers.info("更新优惠券"+wxOrder.getTrOrderUserCouponId());
	        									CashCoupon cashCoupon = userCouponServiceWxRead.findCouponById(userCoupon.getUcouponId());
	        									bikeRentInfo.setRentCouponMoney(cashCoupon.getCouponMoney());
	        								}
	        								
	        								bikeRentInfo.setRentState(1);
	        								bikeRentInfo.setRentPayTime(new Date());
	        								bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
	        								loggers.info("更新租赁订单"+bikeRentInfo.getRentInfoId());
	    		        					DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)38);
	    		            				Integer grade = Integer.valueOf(datadet.getDataDetVal());
	    		            				user.setUserTotalGrade(user.getUserTotalGrade()+grade);
	    		            				user.setUserGrade(user.getUserGrade()+grade);
	    		            				user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
	    		            				userServiceWeixinWrite.updateUser(user);//更新用户
	    		            				loggers.info("更新用户积分");
	    		            				
	    		            				//积分记录
	    		            				GradeRecord gradeRecord = new GradeRecord();
	    		            				gradeRecord.setGradeCount(grade);
	    		            				gradeRecord.setGradeRemark("正常骑行");
	    		            				gradeRecord.setGradeUserId(user.getUserId());
	    		            				gradeRecord.setGradeState(0);//获取的
	    		            				gradeRecord.setGradeCreateTime(new Date());
	    		            				gradeWxServiceWrite.addGradeRecord(gradeRecord);
	    		            				loggers.info("添加用户积分记录");
	        								
	        								//更新充值记录
	        								List<RechargeRecord> rechargeRecords = rechargeWxServiceRead.findRecharge(account.getAccountId());//查询账户充值记录
	        								if(null!=rechargeRecords){
	        									for(RechargeRecord rechargeRecord : rechargeRecords){
	        										rechargeRecord.setRechargeState(3);//已退款
	        										rechargeWxServiceWrite.updateRechargeRecord(rechargeRecord);
	        									}
	        								}
	        							}else if(2==wxOrder.getTrOrderType()){//邮费支付
	        								moneyLogWxServiceWrite.addPosMoneyLog(account, wxOrder.getTrOrderMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), map.get("out_trade_no"),map.get("transaction_id"));
	        								String[] newStr = SplitUtil.toSplit(wxOrder.getTrOrderRentidstr());
	        								if(newStr.length>0){
	        									for(int i = 0;i<newStr.length;i++){
	        										BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(Long.valueOf(newStr[i]));
	        										bikeRentInfo.setRentInvoiceId(wxOrder.getTrOrderInvoiceid());
	        										bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
	        									}
	        								}
	        								Invoice invoice = invoiceWxServiceRead.findById(wxOrder.getTrOrderInvoiceid());
	        								invoice.setInvoiceState(0);
	        								invoiceWxServiceWrite.updateInvoice(invoice);
	        							}else if(3==wxOrder.getTrOrderType()){//余额充值
	        								accountWxServiceWrite.addAccountMoney(wxOrder.getTrOrderMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), account ,map.get("transaction_id"),map.get("out_trade_no"),2,2);
        									List<RechargeRecord> oldRechargeRecord = rechargeWxServiceRead.findRechargeByAccountId(account.getAccountId(), 2);//会员余额充值记录
        									if(oldRechargeRecord.size()==0){
        										DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)40);
        										Integer grade = Integer.valueOf(datadet.getDataDetVal());
        										user.setUserTotalGrade(user.getUserTotalGrade()+grade);
        										user.setUserGrade(user.getUserGrade()+grade);
        										user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
        										userServiceWeixinWrite.updateUser(user);
        										loggers.info("更新用户积分");
        										UserToTask newuserToTask = new UserToTask();
        										newuserToTask.setUserToTaskState(2);
        										newuserToTask.setUserId(user.getUserId());
        										newuserToTask.setDataDetId((long)40);
        										userToTaskWxServiceWrite.addUserToTask(newuserToTask);
        										loggers.info("添加用户积分任务");
        										//积分记录
        										GradeRecord gradeRecord = new GradeRecord();
        										gradeRecord.setGradeCount(grade);
        										gradeRecord.setGradeRemark("首次充值");
        										gradeRecord.setGradeUserId(user.getUserId());
        										gradeRecord.setGradeState(0);//获取的
        										gradeRecord.setGradeCreateTime(new Date());
        										gradeWxServiceWrite.addGradeRecord(gradeRecord);
        										loggers.info("添加用户积分记录");
        									}
	        								Message message = new Message();
	        								message.setMessageUserId(user.getUserId());
	        								message.setMessageContent("恭喜您充值成功，充值金额为"+wxOrder.getTrOrderMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
	        								message.setMessageSendTime(new Date());
	        								message.setMessageTitle("充值");
	        								messageWxServiceWrite.addMessage(message);
	        							}
	        	    					//************* 更新 微信订单  wx_order ***************
	        	    					if(null!=map.get("total_fee")&&"".equals(map.get("total_fee"))){
	        	    						wxOrder.setTrOrderPaytime(DateUtil.stringToDate03(map.get("time_end")));
	        	    					}
	        	    					wxOrder.setTrOrderState((short)1);//已支付
	        	    					trOrderWxServiceWrite.updateTrOrder(wxOrder);
	                        		}
	                        		writer.write(PayUtil.backWeixin("SUCCESS","OK")); 
	                        	}else {
	                        		//其他参数验证<不>通过
	                        	   System.out.println("验证不通过："+description);
	                        	   writer.write(PayUtil.backWeixin("FAIL","INCORRECT params"));  
								}
	                        }else{  
	                        	System.out.println("签名验证不通过，生成的sign:"+sign+"  获取的sign:"+map.get("sign"));
	                            writer.write(PayUtil.backWeixin("FAIL","INCORRECT SIGN"));  
	                        }  
	                    }else{  
	                        writer.write(PayUtil.backWeixin("FAIL",map.get("return_msg")));  
	                          
	                        System.out.println("---------微信支付返回Fail----------"+map.get("return_msg"));  
	                    }  
	  
	                    if(writer!=null){  
	                        writer.close();  
	                    }  
	                }else{  
	                    writer.write(PayUtil.backWeixin("FAIL","NO RETURN"));  
	                }  
	            }catch(Exception e){  
	                e.printStackTrace();  
	            }  
	        }catch(Exception ex){  
	            ex.printStackTrace();  
	        }
		
	}		
}


