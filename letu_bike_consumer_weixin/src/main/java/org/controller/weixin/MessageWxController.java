package org.controller.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.Message;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.service.weixin.read.MessageWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 系统公告
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
@Controller
@Scope("prototype")
@RequestMapping(value = "/message", method = RequestMethod.POST)
public class MessageWxController {

	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	MessageWxServiceRead messageWxServiceRead;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	AppConfig AppConfig;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(MessageWxController.class);
		
	/**
	 * 用户消息列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userMessageList", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil userMessageList(@RequestHeader HttpHeaders header,@RequestParam(defaultValue = "1")Integer pageIndex,HttpSession session) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
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
		//查询用户所有的消息
		List<Message> messageList = messageWxServiceRead.findMessageByUserId(userLogin.getUserId(),"1", pageIndex, AppConfig.getPage_size_weixin(),null);
		Integer totalCount = messageWxServiceRead.findUserMessageCount(userLogin.getUserId(),"1",null);
		data.put("pageSize", AppConfig.getPage_size_weixin());
		data.put("messageList", messageList);
		data.put("totalCount", totalCount);
		data.put("pageIndex", pageIndex);
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * 用户消息详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public @ResponseBody MessageUtil userMessageDetail(Long messageId) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		Message message = messageWxServiceRead.findByMId(messageId);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		data.put("message", message);
		messageUtil.setData(data);
		return messageUtil;
	}
	
	
	
	
}
