package org.controller.report;



import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.Channel;
import org.service.report.AdminServiceReport;
import org.service.report.ChannelServiceReport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.util.PasswordUtil;

/**管理员
 *
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping("report/admin")
public class AdminController {
	
	
	
	@Resource
	AdminServiceReport adminServiceReport;
	
	@Resource
	ChannelServiceReport channelServiceReport;

	@Resource
	AppConfig AppConfig;
	
	
	
	/**
	 * 跳转到后台登陆页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("loginJsp")
	public String loginJsp() throws Exception{
		
		return "login";
	}
	
	/**
	 * 退出登录
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) throws Exception {
		Subject admin = SecurityUtils.getSubject();
		admin.logout();
		return "redirect:loginJsp.action";
	}
	
	/**
	 * 后台登陆
	 * 
	 * @param username
	 * @param password
	 * @param request
	 * @param model
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login")
	public String login(String username, String password, HttpServletRequest request, Model model, HttpSession session,
			HttpServletResponse response) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Admin admin = adminServiceReport.findByName(username);
		//账号为空
		if(null==admin){
			model.addAttribute("message", "该账号不可用");
			return "login";
		}
		
		//如果渠道不为空 查找对应的渠道  
		if(null!=admin.getAdminChannelId()){
		//查找对应的渠道
		Channel channel = channelServiceReport.findById(admin.getAdminChannelId());
		//如果已经冻结
		if(0==channel.getChannelState()){
			model.addAttribute("message", "该渠道已冻结");
			return "login";
		}
		}
		String pwd = PasswordUtil.generate(password, admin.getAdminSalt());  //生成混合的密码
		System.out.println(pwd);
		UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
		token.setRememberMe(true);
		try {
			subject.login(token);
			//把admin的渠道id放到session中
			session.setAttribute("currChannelId", admin.getAdminChannelId());
			session.setAttribute("admin", admin);
			List<Channel> channelList = new ArrayList<Channel>();
			if(admin.getAdminChannelId()==null){
				channelList = channelServiceReport.findAllChannelByChannelIds(null);
				
			}
			else{
				Channel channel =channelServiceReport.findById(admin.getAdminChannelId());
				List<Channel> channels = channelServiceReport.findSonChannels(admin.getAdminChannelId());
				channels.add(channel);
				List<Long> currChannelIds = new ArrayList<Long>();
				for(Channel c:channels){
					currChannelIds.add(c.getChannelId());
				}
				channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
			}
			model.addAttribute("channelList", channelList);
			
			return "main";
		} catch (AuthenticationException e) {
			token.clear();
			model.addAttribute("message", "loginfail");
			return "login";
		}
	}
	
	
	/**
	 * 首页
	 * @param session
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("main")
	public String main(HttpSession session,Model model) throws Exception {
		Long currChannelId = (Long) session.getAttribute("currChannelId");
		List<Channel> channelList = new ArrayList<Channel>();
		if(currChannelId==null){
			channelList = channelServiceReport.findAllChannelByChannelIds(null);
			
		}
		else{
			Channel channel =channelServiceReport.findById(currChannelId);
			List<Channel> channels = channelServiceReport.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			channelList = channelServiceReport.findAllChannelByChannelIds(currChannelIds);
		}
		model.addAttribute("channelList", channelList);
			return "main";
	
	}
	
	/**
	 * 可视化数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("myiframe")
	public String myiframe() throws Exception {
		return "myiframe";
	}
	
	
}
