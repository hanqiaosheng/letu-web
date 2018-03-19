package org.controller.weixin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.Account;
import org.entity.dto.Invoice;
import org.entity.dto.MoneyLog;
import org.entity.dto.SysParament;
import org.entity.dto.Ticket;
import org.entity.dto.User;
import org.entity.dto.UserTicket;
import org.service.weixin.read.AccountWxServiceRead;
import org.service.weixin.read.InvoiceWxServiceRead;
import org.service.weixin.read.MoneyLogWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.TicketWxServiceRead;
import org.service.weixin.read.UserCouponServiceWxRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
import org.util.PageUtil;
@Controller
@Scope("prototype")
@RequestMapping(value = "/wallet", method = RequestMethod.POST)
public class UserWalletWxController {

	
	@Resource
	UserCouponServiceWxRead userCouponServiceWxRead;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	AccountWxServiceRead accountWxServiceRead;
	@Resource
	InvoiceWxServiceRead invoiceWxServiceRead;
	@Resource
	MoneyLogWxServiceRead moneyLogWxServiceRead;
	@Resource
	TicketWxServiceRead ticketWxServiceRead;
	@Resource
	AppConfig AppConfig;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(UserWalletWxController.class);
		
	/**
	 * 卡包
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cardHold", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil cardHold(@RequestHeader HttpHeaders header,HttpSession session) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		User userLogin = new User();
		String fromFlag = header.getFirst("fromFlag");
		if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			loggers.info("cardHold传入token为"+token);
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			loggers.info("userLogin为"+userLogin);
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
		Integer couponNum  = userCouponServiceWxRead.countCouponByUserId(userLogin.getUserId());
		data.put("couponNum", couponNum);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 钱包
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userWallet", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil userWallet(@RequestHeader HttpHeaders header,HttpSession session) throws Exception{
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
		Integer allNum = 0;
		BigDecimal allInvoiceMoney = new BigDecimal(0);
		User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
		Account account = accountWxServiceRead.findByAccountId(user.getUserAccountId());
		Integer couponNum  = userCouponServiceWxRead.countCouponByUserId(user.getUserId());
		List<Invoice> invoiceList = invoiceWxServiceRead.findAllByUserId(user.getUserId());
		for(Invoice invoice : invoiceList){
			allInvoiceMoney = allInvoiceMoney.add(invoice.getInvoiceMoney());
		}
		allNum = allNum + couponNum;
		data.put("allNum", allNum);
		data.put("account", account);
		data.put("allInvoiceMoney", allInvoiceMoney.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	
	/**
	 * 我的余额
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userBalance", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil userBalance(@RequestHeader HttpHeaders header,HttpSession session) throws Exception{
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
		Account account = accountWxServiceRead.findByAccountId(user.getUserAccountId());
		data.put("account", account);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 用户账单明细
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userDetail", method = RequestMethod.POST)
	public @ResponseBody MessageUtil userDetail(@RequestHeader HttpHeaders header,HttpSession session,Integer pageIndex,Integer logType) throws Exception {
		Map<String,Object> datamap = new HashMap<String,Object>();
		try {
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
			
			if(null!=userLogin){
				User nowUser = userServiceWeixinRead.findByUId(userLogin.getUserId());
				if(logType==1){//消费明细
					List<MoneyLog> moneyLogList = moneyLogWxServiceRead.findConsumeByAccountId(nowUser.getUserAccountId(), pageIndex, AppConfig.getPage_size_weixin());
					Integer totalCount = moneyLogWxServiceRead.findConsumeNumByAccountId(nowUser.getUserAccountId());
					Integer totalPage = PageUtil.getWxTotalPage(totalCount);
					datamap.put("totalCount", totalCount);
					datamap.put("totalPage", totalPage);
					datamap.put("pageIndex", pageIndex);
					datamap.put("moneyLogList", moneyLogList);
				}else if(logType==2){//充值明细
					List<MoneyLog> moneyLogList = moneyLogWxServiceRead.findRechargeByAccountId(nowUser.getUserAccountId(), pageIndex, AppConfig.getPage_size_weixin());
					Integer totalCount = moneyLogWxServiceRead.findRechargeNumByAccountId(nowUser.getUserAccountId());
					Integer totalPage = PageUtil.getWxTotalPage(totalCount);
					datamap.put("totalCount", totalCount);
					datamap.put("totalPage", totalPage);
					datamap.put("pageIndex", pageIndex);
					datamap.put("moneyLogList", moneyLogList);
				}
			}else{
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		messageUtil.setCode(1);
		messageUtil.setData(datamap);
		messageUtil.setMessage("success");
		return messageUtil;
		
	}
	
	/**
	 * 门票
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userTicket", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil userTicket(@RequestHeader HttpHeaders header,HttpSession session,Integer pageIndex) throws Exception{
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
		List<UserTicket> ticketList = ticketWxServiceRead.findAllTicket(pageIndex,AppConfig.getPage_size_weixin(),user.getUserId());
		for(UserTicket ut : ticketList){
			Ticket ticket = ticketWxServiceRead.findByTicketId(ut.getUtTicketId());
			ut.setTicket(ticket);
		}
		Integer totalCount = ticketWxServiceRead.countAllTicket(user.getUserId());
		Integer totalPage = PageUtil.getWxTotalPage(totalCount);
		data.put("ticketList", ticketList);
		data.put("pageIndex", pageIndex);
		data.put("totalPage", totalPage);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 门票详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userTicketDetail", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil userTicketDetail(Long ticketId) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		Ticket ticket = ticketWxServiceRead.findByTicketId(ticketId);
		data.put("ticket", ticket);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
}
