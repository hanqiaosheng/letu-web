package org.controller.weixin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.Account;
import org.entity.dto.Bike;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.DataDet;
import org.entity.dto.GradeRecord;
import org.entity.dto.Invoice;
import org.entity.dto.Message;
import org.entity.dto.MoneyLog;
import org.entity.dto.RechargeRecord;
import org.entity.dto.Tradeorder;
import org.entity.dto.User;
import org.entity.dto.UserToTask;
import org.service.weixin.read.AccountWxServiceRead;
import org.service.weixin.read.BikeRentInfoWxServiceRead;
import org.service.weixin.read.BikeWxServiceRead;
import org.service.weixin.read.DataDetWxServiceRead;
import org.service.weixin.read.InvoiceWxServiceRead;
import org.service.weixin.read.MoneyLogWxServiceRead;
import org.service.weixin.read.RechargeWxServiceRead;
import org.service.weixin.read.TrOrderWxServiceRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.service.weixin.write.AccountWxServiceWrite;
import org.service.weixin.write.BikeRentInfoWxServiceWrite;
import org.service.weixin.write.GradeWxServiceWrite;
import org.service.weixin.write.InvoiceWxServiceWrite;
import org.service.weixin.write.MessageWxServiceWrite;
import org.service.weixin.write.MoneyLogWxServiceWrite;
import org.service.weixin.write.RechargeWxServiceWrite;
import org.service.weixin.write.TrOrderWxServiceWrite;
import org.service.weixin.write.UserServiceWeixinWrite;
import org.service.weixin.write.UserToTaskWxServiceWrite;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.util.DateUtil;
import org.util.LevelUtil;
import org.util.SplitUtil;
import org.util.XmlUtil;
import org.util.weixin.GetWxOrderno;
import org.util.weixin.PayUtil;

@Controller
@RequestMapping("payfeedback")
public class PayFeedBack {
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	AccountWxServiceWrite accountWxServiceWrite;
	@Resource
	AccountWxServiceRead accountWxServiceRead;
	@Resource
	RechargeWxServiceRead rechargeWxServiceRead;
	@Resource
	UserServiceWeixinWrite userServiceWeixinWrite;
	@Resource
	MoneyLogWxServiceRead moneyLogWxServiceRead;
	@Resource
	MoneyLogWxServiceWrite moneyLogWxServiceWrite;
	@Resource
	MessageWxServiceWrite messageWxServiceWrite;
	@Resource
	BikeRentInfoWxServiceWrite bikeRentInfoWxServiceWrite;
	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;
	@Resource
	BikeWxServiceRead bikeWxServiceRead;
	@Resource
	RechargeWxServiceWrite rechargeWxServiceWrite;
	@Resource
	InvoiceWxServiceRead invoiceWxServiceRead;
	@Resource
	InvoiceWxServiceWrite invoiceWxServiceWrite;
	@Resource
	TrOrderWxServiceWrite trOrderWxServiceWrite;
	@Resource
	TrOrderWxServiceRead trOrderWxServiceRead;
	@Resource
	DataDetWxServiceRead dataDetWxServiceRead;
	@Resource
	UserToTaskWxServiceWrite userToTaskWxServiceWrite;
	@Resource
	GradeWxServiceWrite gradeWxServiceWrite;
	@Resource
	AppConfig appConfig;
	
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(PayFeedBack.class);
	
	/**
	 * 微信充值
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("cnotify")
	public void cnotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		Map<String, String> map = new GetWxOrderno().doXMLParse(sb.toString());
		SortedMap<String, String> returnParams = new TreeMap<String, String>();
		String returnCode = map.get("return_code");
		returnParams.put("return_code", returnCode);
		String xml = XmlUtil.toXml(returnParams);
		Double rechargeMoney = Double.valueOf(map.get("total_fee"));
		rechargeMoney = rechargeMoney/100;
		//User user = userServiceWeixinRead.findUserByOpenId("o2jMMuJuL2NYX6Y1YYYMd278-O1E");
		User user = userServiceWeixinRead.findUserByOpenId(String.valueOf(map.get("openid")));
		String transactionId = map.get("transaction_id");
		String outTradeNo = map.get("out_trade_no");
		String typeAndChannelId = map.get("attach");
		Integer rechargeType = 1;
		Long channelId = null;
		loggers.info("typeAndChannelId "+typeAndChannelId);
		loggers.info("transactionId "+transactionId);
		loggers.info("outTradeNo "+outTradeNo);
		if(null!=typeAndChannelId&&!typeAndChannelId.equals("")){
			String[] arr = SplitUtil.toSplit(typeAndChannelId);
			if(arr.length>0){
				if(arr.length==1){
					rechargeType = Integer.parseInt(arr[0]);
				}else{
					rechargeType = Integer.parseInt(arr[0]);
					channelId = Long.valueOf(arr[1]);
				}
				
			}
			
		}
		loggers.info("rechargeType "+rechargeType);
		loggers.info("channelId "+channelId);
		Account account = accountWxServiceRead.findByUserId(user.getUserId());
		RechargeRecord rechargeRecord = rechargeWxServiceRead.findRechargeById(transactionId);
		if(rechargeRecord==null){
			if(null!=rechargeType&&rechargeType==2){
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
					loggers.info("更新用户任务");
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
			}
			
			accountWxServiceWrite.addAccountMoney_2(rechargeMoney, account ,transactionId,outTradeNo,rechargeType,1,channelId);
			loggers.info("更新用户账户并记录日志");
			Message message = new Message();
			message.setMessageUserId(user.getUserId());
			message.setMessageContent("恭喜您充值成功，充值金额为"+rechargeMoney+"元");
			message.setMessageSendTime(new Date());
			message.setMessageTitle("充值");
			messageWxServiceWrite.addMessage(message);
		}
		response.getWriter().write(xml);
		response.getWriter().flush();
	}
	
	/**
	 * 微信支付
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("notify")
	public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		Map<String, String> map = new GetWxOrderno().doXMLParse(sb.toString());
		SortedMap<String, String> returnParams = new TreeMap<String, String>();
		String returnCode = map.get("return_code");
		returnParams.put("return_code", returnCode);
		String xml = XmlUtil.toXml(returnParams);
		Double money = Double.valueOf(map.get("total_fee"));
		money = money/100;
		User user = userServiceWeixinRead.findUserByOpenId(String.valueOf(map.get("openid")));
		String transactionId = map.get("transaction_id");
		String outTradeNo = map.get("out_trade_no");
		Long rentId = Long.valueOf(map.get("attach"));
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentId);
		Account account = accountWxServiceRead.findByUserId(user.getUserId());
		MoneyLog moneyLog = moneyLogWxServiceRead.findMoneyLogByOrder(transactionId);
		loggers.info("rentId "+rentId);
		loggers.info("transactionId "+transactionId);
		loggers.info("outTradeNo "+outTradeNo);
		if(moneyLog==null){
			account.setAccountDeposit(new BigDecimal(0));
			account.setAccountTotalmoney(account.getAccountAvailableBalance());
			accountWxServiceWrite.updateAccount(account);
			moneyLogWxServiceWrite.addMoneyLog(account, new BigDecimal(money), outTradeNo,transactionId);
			loggers.info("更新用户账户");
			DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)38);
			Integer grade = Integer.valueOf(datadet.getDataDetVal());
			user.setUserTotalGrade(user.getUserTotalGrade()+grade);
			user.setUserGrade(user.getUserGrade()+grade);
			user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
			user.setUserState(0);
			userServiceWeixinWrite.updateUser(user);
			bikeRentInfo.setRentState(1);
			bikeRentInfo.setRentPayTime(new Date());
			bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
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
			
			/*Message message = new Message();
			message.setMessageUserId(user.getUserId());
			message.setMessageContent("您的租车行程已结束，行程共消费"+money+"元，车牌号为"+bike.getBikeCode());
			message.setMessageSendTime(new Date());
			message.setMessageTitle("完成租赁");
			messageWxServiceWrite.addMessage(message);*/
		}
		response.getWriter().write(xml);
		response.getWriter().flush();
	}
	
	/**
	 * 微信app充值
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("appcnotify")
	public void appcnotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
	                        String sign = PayUtil.createSign("UTF-8", parameters,appConfig.getApp_partnerkey());  
	  
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
	                        	if(!map.get("appid").equals(appConfig.getApp_id())){
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
	        	    					//****************充值记录   recharge_record********************
	        	    						
	        	    						RechargeRecord rechargeRecord = new RechargeRecord();
	        	    						rechargeRecord.setRechargeAccountId(account.getAccountId());
	        	    						rechargeRecord.setRechargeMoney(wxOrder.getTrOrderMoney());
	        	    						rechargeRecord.setRechargePayType(3);
	        	    						rechargeRecord.setRechargeType(1);
	        	    						rechargeRecord.setRechargeState(1);
	        	    						rechargeRecord.setRechargeTime(new Date());
	        	    						rechargeRecord.setRechargeOutTradeNo(map.get("out_trade_no"));
	        	    						rechargeRecord.setRechargeOrderId(map.get("transaction_id"));
	        	    						rechargeWxServiceWrite.addRechargeRecord(rechargeRecord);
	        	    						
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
	        	    					//更新账户
	        							account.setAccountTotalmoney(account.getAccountTotalmoney().add(wxOrder.getTrOrderMoney()));
	        							accountWxServiceWrite.updateAccount(account);
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
	
	/**
	 * 微信app支付
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("appnotify")
	public void appnotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
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
	                        String sign = PayUtil.createSign("UTF-8", parameters,appConfig.getApp_partnerkey());  
	  
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
	                        	if(!map.get("appid").equals(appConfig.getApp_id())){
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
	        	    					if(1==wxOrder.getTrOrderType())	{//消费支付
	        	    						account.setAccountDeposit(new BigDecimal(0));
	        	    						account.setAccountTotalmoney(account.getAccountAvailableBalance());
	        	    						accountWxServiceWrite.updateAccount(account);
	        	    						moneyLogWxServiceWrite.addMoneyLog(account, wxOrder.getTrOrderMoney(), map.get("out_trade_no"),map.get("transaction_id"));
	        	    						
	        	    						user.setUserState(0);
	        	    						userServiceWeixinWrite.updateUser(user);
	        	    						BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(wxOrder.getTrOrderRentId());
	        	    						bikeRentInfo.setRentState(1);
	        	    						bikeRentInfo.setRentPayTime(new Date());
	        	    						bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
	        	    						
	        	    						//更新充值记录
	        	    						List<RechargeRecord> rechargeRecords = rechargeWxServiceRead.findRecharge(account.getAccountId());//查询账户充值记录
	        	    						if(null!=rechargeRecords){
	        	    							for(RechargeRecord rechargeRecord : rechargeRecords){
	        	    								rechargeRecord.setRechargeState(3);//已退款
	        	    								rechargeWxServiceWrite.updateRechargeRecord(rechargeRecord);
	        	    							}
	        	    						}
	        	    					}
	        	    					//************* 更新 微信订单  wx_order ***************
	        	    					if(null!=map.get("total_fee")&&"".equals(map.get("total_fee"))){
	        	    						wxOrder.setTrOrderPaytime(DateUtil.stringToDate03(map.get("time_end")));
	        	    					}
	        	    					wxOrder.setTrOrderState((short)1);//已支付
	        	    					trOrderWxServiceWrite.updateTrOrder(wxOrder);
	        	    					//更新账户
	        							account.setAccountTotalmoney(account.getAccountTotalmoney().add(wxOrder.getTrOrderMoney()));
	        							accountWxServiceWrite.updateAccount(account);
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
	
	
	/**
	 * 微信支付邮费
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("payNotify")
	public void payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		Map<String, String> map = new GetWxOrderno().doXMLParse(sb.toString());
		SortedMap<String, String> returnParams = new TreeMap<String, String>();
		String returnCode = map.get("return_code");
		returnParams.put("return_code", returnCode);
		String xml = XmlUtil.toXml(returnParams);
		Double money = Double.valueOf(map.get("total_fee"));
		money = money/100;
		User user = userServiceWeixinRead.findUserByOpenId(String.valueOf(map.get("openid")));
		String transactionId = map.get("transaction_id");
		String outTradeNo = map.get("out_trade_no");
		String rentIdAndInvoiceId = map.get("attach");
		Account account = accountWxServiceRead.findByUserId(user.getUserId());
		MoneyLog moneyLog = moneyLogWxServiceRead.findMoneyLogByOrder(transactionId);
		if(moneyLog==null){
			moneyLogWxServiceWrite.addPosMoneyLog(account, money, outTradeNo,transactionId);
			String[] newStr = SplitUtil.toSplit(rentIdAndInvoiceId);
			Long invoiceId = null;
			if(newStr.length>0){
				invoiceId = Long.valueOf(newStr[newStr.length-1]);
				for(int i = 0;i<newStr.length-1;i++){
					BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(Long.valueOf(newStr[i]));
					bikeRentInfo.setRentInvoiceId(invoiceId);
					bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
				}
			}
			if(null!=invoiceId){
				Invoice invoice = invoiceWxServiceRead.findById(invoiceId);
				invoice.setInvoiceState(0);
				invoiceWxServiceWrite.updateInvoice(invoice);
			}
			
		}
		response.getWriter().write(xml);
		response.getWriter().flush();
	}
	
	/**
	 * 微信小程序支付
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("minnotify")
	public void minnotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		Map<String, String> map = new GetWxOrderno().doXMLParse(sb.toString());
		SortedMap<String, String> returnParams = new TreeMap<String, String>();
		String returnCode = map.get("return_code");
		returnParams.put("return_code", returnCode);
		String xml = XmlUtil.toXml(returnParams);
		Double money = Double.valueOf(map.get("total_fee"));
		money = money/100;
		User user = userServiceWeixinRead.findUserBySmallOpenId(String.valueOf(map.get("openid")));
		String transactionId = map.get("transaction_id");
		String outTradeNo = map.get("out_trade_no");
		Long rentId = Long.valueOf(map.get("attach"));
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentId);
		Bike bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
		Account account = accountWxServiceRead.findByUserId(user.getUserId());
		MoneyLog moneyLog = moneyLogWxServiceRead.findMoneyLogByOrder(transactionId);
		if(moneyLog==null){
			moneyLogWxServiceWrite.addMoneyLog(account, new BigDecimal(money), outTradeNo,transactionId);
			user.setUserState(0);
			userServiceWeixinWrite.updateUser(user);
			bikeRentInfo.setRentState(1);
			bikeRentInfo.setRentPayTime(new Date());
			bikeRentInfoWxServiceWrite.updateRentInfo(bikeRentInfo);
			/*Message message = new Message();
			message.setMessageUserId(user.getUserId());
			message.setMessageContent("您的租车行程已结束，行程共消费"+money+"元，车牌号为"+bike.getBikeCode());
			message.setMessageSendTime(new Date());
			message.setMessageTitle("完成租赁");
			messageWxServiceWrite.addMessage(message);*/
		}
		response.getWriter().write(xml);
		response.getWriter().flush();
	}
	
	
	/**
	 * 微信小程序充值
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("mincnotify")
	public void mincnotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		Map<String, String> map = new GetWxOrderno().doXMLParse(sb.toString());
		SortedMap<String, String> returnParams = new TreeMap<String, String>();
		String returnCode = map.get("return_code");
		returnParams.put("return_code", returnCode);
		String xml = XmlUtil.toXml(returnParams);
		Double rechargeMoney = Double.valueOf(map.get("total_fee"));
		rechargeMoney = rechargeMoney/100;
		User user = userServiceWeixinRead.findUserBySmallOpenId(String.valueOf(map.get("openid")));
		String transactionId = map.get("transaction_id");
		String outTradeNo = map.get("out_trade_no");
		Integer rechargeType = Integer.parseInt(map.get("attach"));
		Account account = accountWxServiceRead.findByUserId(user.getUserId());
		RechargeRecord rechargeRecord = rechargeWxServiceRead.findRechargeById(transactionId);
		if(rechargeRecord==null){
			accountWxServiceWrite.addAccountMoney(rechargeMoney, account ,transactionId,outTradeNo,rechargeType,1);
			Message message = new Message();
			message.setMessageUserId(user.getUserId());
			message.setMessageContent("恭喜您充值成功，充值金额为"+rechargeMoney+"元");
			message.setMessageSendTime(new Date());
			message.setMessageTitle("充值");
			messageWxServiceWrite.addMessage(message);
		}
		response.getWriter().write(xml);
		response.getWriter().flush();
	}
}
