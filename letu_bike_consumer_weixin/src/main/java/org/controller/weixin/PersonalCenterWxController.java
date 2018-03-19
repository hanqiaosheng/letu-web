package org.controller.weixin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.User;
import org.service.weixin.read.BikeRentInfoWxServiceRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;

/**
 * 个人中心
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/proCenter", method = RequestMethod.POST)
public class PersonalCenterWxController {

	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	AppConfig AppConfig;
	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;
	protected final MessageUtil messageUtil = new MessageUtil();
	/**
	 * 个人首页的数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public @ResponseBody MessageUtil home(HttpSession session) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		User user_session = (User) session.getAttribute("curruser");
		User loginUser = userServiceWeixinRead.findByUId(user_session.getUserId());
		data.put("loginUser", loginUser);
		messageUtil.setMessage("success");
		messageUtil.setData(data);
		return messageUtil;
	}
	
	
//	/**
//	 * 个人行程
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/proTrip", method = RequestMethod.POST)
//	public @ResponseBody MessageUtil proTrip(HttpSession session) throws Exception{
//		User user_session = (User) session.getAttribute("user");
//		Map<String, Object> data = new HashMap<>();
//		//根据用户id查租赁 列表
//		List<BikeRentInfo> bikeRentInfoList = bikeRentInfoWxServiceRead.findByUserId(user_session.getUserId());
//		
//		data.put("bikeRentInfoList", bikeRentInfoList);
//		messageUtil.setCode(1);
//		messageUtil.setMessage("success");
//		return messageUtil;
//	}
	
	
	
	
	
	
	
	
}
