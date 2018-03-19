package org.controller.cms;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Account;
import org.entity.dto.Admin;
import org.entity.dto.Message;
import org.entity.dto.OperateLog;
import org.entity.dto.RechargeRecord;
import org.entity.dto.Refund;
import org.entity.dto.User;
import org.service.cms.read.AccountServiceRead;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.RechargeRecordServiceRead;
import org.service.cms.read.RefundServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.AccountServiceWrite;
import org.service.cms.write.MessageServiceWrite;
import org.service.cms.write.MoneyLogServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.RefundServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.OperateUtil;
import org.util.weixin.WXHttpApi;

@Controller
@Scope("prototype")
@RequestMapping("cms/refund")
public class RefundController {
	
	@Resource
	RefundServiceRead refundServiceRead;
	
	@Resource
	RefundServiceWrite refundServiceWrite;
	
	@Resource
	UserServiceRead userServiceRead;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	AccountServiceRead accountServiceRead;
	
	@Resource
	MoneyLogServiceWrite moneyLogServiceWrite;
	
	@Resource
	RechargeRecordServiceRead rechargeRecordServiceRead;
	
	@Resource
	MessageServiceWrite messageServiceWrite;
	
	@Resource
	AccountServiceWrite accountServiceWrite;
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	@Resource
	AppConfig appConfig;
	
	/**
	 * 退款列表
	 * @param model
	 * @param pageIndex
	 * @param userPhone
	 * @param refundStarttime
	 * @param refundEndtime
	 * @param operateStarttime
	 * @param operateEndtime
	 * @param refundState
	 * @param adminName
	 * @param flag
	 * @param response
	 * @param refundOrderId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("refundList")
	public String refundList(Model model,@RequestParam(defaultValue="1")Integer pageIndex,String userPhone,
			                 Date refundStarttime,Date refundEndtime,Date operateStarttime,Date operateEndtime,HttpSession session,
			                 Integer refundState,String adminName,@RequestParam(defaultValue="0")Integer flag,HttpServletResponse response,String refundOrderId) throws Exception{
		
		Admin admin  = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		
		if(flag==1){
			pageIndex=null;
		}
		List<Refund> refundList = refundServiceRead.findAllRefundList(pageIndex, userPhone, refundStarttime, refundEndtime,
				                                                      operateStarttime, operateEndtime, refundState, adminName,refundOrderId);
		Integer totalPage = refundServiceRead.countAllRefundList(userPhone, refundStarttime, refundEndtime, operateStarttime, 
                                                 operateEndtime, refundState, adminName,refundOrderId);
		if(totalPage==0){
        	totalPage = 1;
        }
		model.addAttribute("refundList", refundList);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("userPhone", userPhone);
		model.addAttribute("refundStarttime", refundStarttime);
		model.addAttribute("refundEndtime", refundEndtime);
		model.addAttribute("operateStarttime", operateStarttime);
		model.addAttribute("operateEndtime", operateEndtime);
		model.addAttribute("refundState", refundState);
		model.addAttribute("refundOrderId", refundOrderId);
		model.addAttribute("adminName", adminName);
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=refund_excel_"+DateUtil.formatDay(new Date())+".xls");
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			String remark = OperateUtil.operateRefund(nowAdmin.getAdminRealname(), 1, null);
			operateLog.setOperateRemark(remark);
			operateServiceWrite.addOperateLogs(operateLog);//操作日志
			return "refund_excel_list";
		}
		return "refund_money_list";
	}
	
	/**
	 * 退款详情
	 * @param refundId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("refundDetail")
	public String refundDetail(Long refundId,Model model) throws Exception{
		Refund refund = refundServiceRead.findRefundById(refundId);
		Account account = accountServiceRead.findPlanByaccountId(refund.getRefundAccountId());
		User user = userServiceRead.findById(account.getAccountUserId());
		Admin admin = adminServiceRead.findAdminId(refund.getRefundAdminId());
		model.addAttribute("refund", refund);
		model.addAttribute("user", user);
		model.addAttribute("admin", admin);
		return "detail/refund_detail";
	}
	
	/**
	 * 退款操作
	 * @param refundId
	 * @param refundState
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("refundState")
	public @ResponseBody String refundState(HttpSession session,Long refundId, Integer refundState,String refundRefuseReason) throws Exception{
		Refund refund = refundServiceRead.findRefundById(refundId);
		Account account = accountServiceRead.findPlanByaccountId(refund.getRefundAccountId());
		User user = userServiceRead.findById(account.getAccountUserId());
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		Message message = new Message();
		refund.setRefundId(refundId);
		refund.setRefundState(refundState);
		refund.setRefundOperatetime(new Date());
		refund.setRefundAdminId(admin.getAdminId());
		if(refundRefuseReason!=null&&!refundRefuseReason.equals("")){
			refund.setRefundRefuseReason(refundRefuseReason);
		}
		if(refundState==1){
			RechargeRecord rechargeRecord = rechargeRecordServiceRead.findRechargeRecordById(refund.getRefundOrderId());
		   if(DateUtil.daysBetween(rechargeRecord.getRechargeTime(), new Date())>365){
			   if(WXHttpApi.refundOrder(user.getUserOpenid(), 
						 appConfig.getApp_id(), 
						appConfig.getApp_partner(), appConfig.getApp_partnerkey(),InetAddress.getLocalHost().getHostAddress(),refund.getRefundMoney(),refund.getRefundCode())){
				    message.setMessageUserId(user.getUserId());
					message.setMessageContent("您的押金已退还，退还金额为"+refund.getRefundMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
					message.setMessageSendTime(new Date());
					message.setMessageTitle("退款成功");
					messageServiceWrite.addMessage(message);
				   
					accountServiceWrite.updateAccountAndLog(account, refund.getRefundMoney(),rechargeRecord,admin.getAdminId());
				}else{
					return "fail";
				}
		   }else{
			   if(WXHttpApi.wechatRefund(refund.getRefundOrderId(), 
						refund.getRefundCode(), refund.getRefundMoney(),refund.getRefundMoney(),
						 appConfig.getApp_id(), appConfig.getApp_secret(), 
						appConfig.getApp_partner(), appConfig.getApp_partner(), appConfig.getApp_partnerkey(),appConfig.getApp_certificate())){
			     /*if(WXHttpApi.wechatRefund("4004842001201612021559993492","241480681246",new BigDecimal(0.01),new BigDecimal(0.01),"wx058b00481ed6372b","158afe0fb4ae946e64feb91731619011","1416999702","1416999702","AaBbCcDdEeFfGgHhIiJjKkLlMmNnOo00")){*/
				    message.setMessageUserId(user.getUserId());
					message.setMessageContent("您的押金已退还，退还金额为"+refund.getRefundMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
					message.setMessageSendTime(new Date());
					message.setMessageTitle("退款成功");
					messageServiceWrite.addMessage(message);
					accountServiceWrite.updateAccountAndLog(account, refund.getRefundMoney(),rechargeRecord,admin.getAdminId());
				}else{
					return "fail";
				}
		   }
		  
		}else if(refundState==2){
			account.setAccountTotalmoney(account.getAccountTotalmoney().add(refund.getRefundMoney()));
			account.setAccountDeposit(account.getAccountDeposit().add(refund.getRefundMoney()));	
			account.setAccountFreezemoney(account.getAccountFreezemoney().subtract(refund.getRefundMoney()));
			accountServiceWrite.updateAccount(account);
			message.setMessageUserId(user.getUserId());
			message.setMessageContent("您的退款已被拒绝，拒绝理由为："+refundRefuseReason+"，<br>如有疑问请联系客服0571-56231981");
			message.setMessageSendTime(new Date());
			message.setMessageTitle("退款失败");
			messageServiceWrite.addMessage(message);
		}
		
		if(refundState==-1){//删除操作日志
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			String remark = OperateUtil.operateRefund(nowAdmin.getAdminRealname(), 2, refundId.toString());
			operateLog.setOperateRemark(remark);
			operateServiceWrite.addOperateLogs(operateLog);//操作日志
		}
		refundServiceWrite.updateState(refund);
		return "success";
	}
	
	/**
	 * 批量设置退款状态
	 * @param refundId
	 * @param refundState
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("refundAllState")
	public @ResponseBody String refundAllState(HttpSession session,Long[] refundIds, Integer refundState,String refundRefuseReason) throws Exception{
		if(refundIds!=null){
			Admin admin = (Admin) session.getAttribute("admin");
			Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
			String refundIdstr = "";
			for(Long refundId : refundIds){
				Refund refund = refundServiceRead.findRefundById(refundId);
				if(refund.getRefundState()==0){
					Account account = accountServiceRead.findPlanByaccountId(refund.getRefundAccountId());
					User user = userServiceRead.findById(account.getAccountUserId());
					
					Message message = new Message();
					refund.setRefundId(refundId);
					refund.setRefundState(refundState);
					refund.setRefundOperatetime(new Date());
					refund.setRefundAdminId(admin.getAdminId());
					if(refundRefuseReason!=null&&!refundRefuseReason.equals("")){
						refund.setRefundRefuseReason(refundRefuseReason);
					}
					if(refundState==1){
						RechargeRecord rechargeRecord = rechargeRecordServiceRead.findRechargeRecordById(refund.getRefundOrderId());
					   if(DateUtil.daysBetween(rechargeRecord.getRechargeTime(), new Date())>365){
						   if(WXHttpApi.refundOrder(user.getUserOpenid(), 
									 appConfig.getApp_id(), 
									appConfig.getApp_partner(), appConfig.getApp_partnerkey(),InetAddress.getLocalHost().getHostAddress(),refund.getRefundMoney(),refund.getRefundCode())){
						 
							    message.setMessageUserId(user.getUserId());
								message.setMessageContent("您的押金已退还，退还金额为"+refund.getRefundMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
								message.setMessageSendTime(new Date());
								message.setMessageTitle("退款成功");
								messageServiceWrite.addMessage(message);
								accountServiceWrite.updateAccountAndLog(account, refund.getRefundMoney(),rechargeRecord,admin.getAdminId());
								refundServiceWrite.updateState(refund);
							}
					   }else{
						   if(WXHttpApi.wechatRefund(refund.getRefundOrderId(), 
									refund.getRefundCode(), refund.getRefundMoney(),refund.getRefundMoney(),
									 appConfig.getApp_id(), appConfig.getApp_secret(), 
									appConfig.getApp_partner(), appConfig.getApp_partner(), appConfig.getApp_partnerkey(),appConfig.getApp_certificate())){
						   
							    message.setMessageUserId(user.getUserId());
								message.setMessageContent("您的押金已退还，退还金额为"+refund.getRefundMoney().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()+"元");
								message.setMessageSendTime(new Date());
								message.setMessageTitle("退款成功");
								messageServiceWrite.addMessage(message);
								accountServiceWrite.updateAccountAndLog(account, refund.getRefundMoney(),rechargeRecord,admin.getAdminId());
								refundServiceWrite.updateState(refund);
							}
					   }
					  
					}else if(refundState==2){
						message.setMessageUserId(user.getUserId());
						message.setMessageContent("您的退款已被拒绝，拒绝理由为："+refundRefuseReason+"，<br>如有疑问请联系客服0571-56231981");
						message.setMessageSendTime(new Date());
						message.setMessageTitle("退款失败");
						messageServiceWrite.addMessage(message);
						refundServiceWrite.updateState(refund);
						account.setAccountTotalmoney(account.getAccountTotalmoney().add(refund.getRefundMoney()));
						account.setAccountDeposit(account.getAccountDeposit().add(refund.getRefundMoney()));	
						account.setAccountFreezemoney(account.getAccountFreezemoney().subtract(refund.getRefundMoney()));
						accountServiceWrite.updateAccount(account);
					}else if(refundState==-1){//删除操作
						refundServiceWrite.updateState(refund);
						refundIdstr = refundIdstr + " " + refundId;
					}
					
				}else{
					refund.setRefundState(refundState);
					refundServiceWrite.updateState(refund);
				}
			}
			if(refundState==-1){//删除操作
				OperateLog operateLog = new OperateLog();
				operateLog.setOperateTime(new Date());
				operateLog.setOperateAdminId(nowAdmin.getAdminId());
				String remark = OperateUtil.operateRefund(nowAdmin.getAdminRealname(), 3, refundIdstr);
				operateLog.setOperateRemark(remark);
				operateServiceWrite.addOperateLogs(operateLog);//操作日志
			}
		}
		
		return "success";
	}
	
	/**
	 * 获取充值记录
	 * @param model
	 * @param refundCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rechargeRecord")
	public @ResponseBody RechargeRecord RechargeRecord(Model model,String refundCode) throws Exception{
		
		RechargeRecord rechargeRecord = rechargeRecordServiceRead.findRechargeRecordByTradeNo(refundCode);
		if(rechargeRecord!=null){
			return rechargeRecord;
		}
		return null;
	}

}
