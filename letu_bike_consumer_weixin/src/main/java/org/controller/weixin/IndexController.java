package org.controller.weixin;




import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.entity.dto.User;
import org.service.weixin.read.UserServiceWeixinRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("index")
public class IndexController {

	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(IndexController.class);
	
	@RequestMapping("goIndex")
	public String goIndex(HttpServletRequest request,String path_url,String otherFlag,Model model,HttpSession session
			,String tourl,String groupId,String couponSchemeId) throws Exception{
		User user = (User)session.getAttribute("curruser");
		User user2 = null;
		if (null != user && null != user.getUserTel()) {
			user2 = userServiceWeixinRead.findByUId(user.getUserId());
			session.setAttribute("curruser", user2);
		}
		
		loggers.info("tourl"+tourl);
		loggers.info("user2"+user2);
		loggers.info("otherFlag"+otherFlag);
		model.addAttribute("user", user2);
		model.addAttribute("tourl", tourl);
		model.addAttribute("groupId", groupId);
		model.addAttribute("couponSchemeId", couponSchemeId);
		if(null!=otherFlag&&!"".equals(otherFlag)){
			model.addAttribute("otherFlag", otherFlag);
		}else{
			model.addAttribute("otherFlag", "");
		}
		return "index";
	}

}
