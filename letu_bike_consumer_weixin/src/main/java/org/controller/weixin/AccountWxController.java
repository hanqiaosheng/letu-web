package org.controller.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.Account;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.MoneyLog;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.service.weixin.read.AccountWxServiceRead;
import org.service.weixin.read.BikeRentInfoWxServiceRead;
import org.service.weixin.read.MoneyLogWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
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

/**
 * 账单明细
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/account", method = RequestMethod.POST)
public class AccountWxController {

	
	@Resource
	AccountWxServiceRead accountWxServiceRead;
	@Resource
	MoneyLogWxServiceRead moneyLogWxServiceRead;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	AppConfig AppConfig;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(AccountWxController.class);
	
	/**	  账户总览
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/myAccount", method = RequestMethod.POST)
	public @ResponseBody MessageUtil myAccount(@RequestHeader HttpHeaders header,HttpSession session) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			//获取登录信息
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
			User loginUser = userServiceWeixinRead.findByUId(userLogin.getUserId());
			//账户信息
			Account account = accountWxServiceRead.findByUserId(loginUser.getUserId());
			data.put("loginUser", loginUser);
			data.put("account", account);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		
		messageUtil.setData(data);
		messageUtil.setCode(1);
		return messageUtil;
	
	}
	
	/**
	 * 账单明细
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> billDetail(Long accountId, HttpSession session,Integer pageIndex) throws Exception {
		Map<String,Object> datamap = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			List<MoneyLog> moneyLogList = moneyLogWxServiceRead.findByAccountId(accountId, pageIndex, AppConfig.getPage_size_weixin());
			Integer totalCount = moneyLogWxServiceRead.findCountByAccountId(accountId);
			Integer totalPage = PageUtil.getWxTotalPage(totalCount);
			datamap.put("totalCount", totalCount);
			datamap.put("totalPage", totalPage);
			datamap.put("pageIndex", pageIndex);
			datamap.put("moneyLogList", moneyLogList);
		} catch (Exception e) {
			loggers.error("异常信息", e);
		}
		
		map.put("data", datamap);
		return map;
		
	}
	/**
	 * 用户账单明细
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/Mydetail", method = RequestMethod.POST)
	public @ResponseBody MessageUtil Detail(@RequestHeader HttpHeaders header,HttpSession session,Integer pageIndex) throws Exception {
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
				List<MoneyLog> moneyLogList = moneyLogWxServiceRead.findDetailByAccountId(nowUser.getUserAccountId(), pageIndex, AppConfig.getPage_size_weixin());
				Integer totalCount = moneyLogWxServiceRead.findDetailCountByAccountId(nowUser.getUserAccountId());
				Integer totalPage = PageUtil.getWxTotalPage(totalCount);
				datamap.put("totalCount", totalCount);
				datamap.put("totalPage", totalPage);
				datamap.put("pageIndex", pageIndex);
				datamap.put("moneyLogList", moneyLogList);
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
	 * 退押金按钮
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/returnDepositBtn", method = RequestMethod.POST)
	public @ResponseBody MessageUtil returnDepositBtn(@RequestHeader HttpHeaders header,HttpSession session) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
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
		User user = userServiceWeixinRead.findByUId(userLogin.getUserId());
		if(0!=user.getUserState()){		//如果用户不在空闲状态
			BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findNotFinishByUserId(userLogin.getUserId());
			if(1!=bikeRentInfo.getRentState()){//未完成订单
				if(null==bikeRentInfo.getRentEndtime()){  //结束行程
					messageUtil.setMessage("有租赁没结束");
					messageUtil.setCode(0);
					data.put("bikeRentInfo", bikeRentInfo);
					messageUtil.setData(data);
					return messageUtil;
				}else {
					messageUtil.setMessage("有订单未支付");
					messageUtil.setCode(0);
					data.put("bikeRentInfo", bikeRentInfo);
					messageUtil.setData(data);
					return messageUtil;
				}
				
				
			}
			
		}
		
		messageUtil.setMessage("可以退款");
		messageUtil.setCode(1);
		return messageUtil;
	}
	
	
	
}
