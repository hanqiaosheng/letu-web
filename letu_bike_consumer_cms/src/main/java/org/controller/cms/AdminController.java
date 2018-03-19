package org.controller.cms;


import java.util.ArrayList;
import java.util.Date;
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
import org.entity.dto.*;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.RoleServiceRead;
import org.service.cms.write.AdminServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.RoleServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
import org.util.OperateUtil;
import org.util.PageUtil;
import org.util.PasswordUtil;

/**管理员
 *
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping("cms/admin")
public class AdminController {
	
	
	
	@Resource
	AdminServiceRead adminServiceRead;
	@Resource
	AdminServiceWrite adminServiceWrite;
	@Resource
	RoleServiceWrite roleServiceWrite;
	@Resource
	RoleServiceRead roleServiceRead;
	@Resource
	ChannelServiceRead channelServiceRead;
	@Resource
	OperateServiceWrite operateServiceWrite;
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
		Admin adminsession = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(adminsession.getAdminId());
		OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.logOutOption(nowAdmin.getAdminRealname(), 2);
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
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
		Admin admin = adminServiceRead.findByName(username);
		//账号为空
		if(null==admin){
			model.addAttribute("message", "loginfail");
			return "login";
		}
		
		//如果渠道不为空 查找对应的渠道  
		if(null!=admin.getAdminChannelId()){
		//查找对应的渠道
		Channel channel = channelServiceRead.findById(admin.getAdminChannelId());
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
			OperateLog operateLog = new OperateLog();
			String remark = OperateUtil.logOutOption(admin.getAdminRealname(), 1);
			operateLog.setOperateRemark(remark);
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(admin.getAdminId());
			operateServiceWrite.addOperateLogs(operateLog);
			return "main";
		} catch (AuthenticationException e) {
			token.clear();
			model.addAttribute("message", "loginfail");
			return "login";
		}
	}
	
	/**
	 * 管理员列表
	 * @param pageIndex
	 * @param model
	 * @param adminname
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("adminList")
	public String adminList(@RequestParam(defaultValue = "1") Integer pageIndex, Model model,String adminname,Long roleId ,HttpSession session) throws Exception{
		List<Admin> adminList = new ArrayList<Admin>();
		//取admin的渠道id
    	Long channelId  = (Long) session.getAttribute("currChannelId");
    	Integer totalCount = 1;
    	List<Role> roleList = new ArrayList<Role>();
    	if(channelId==null){
    		//有用的角色列表
    		roleList = roleServiceRead.findUsefulRole(null); 
    		adminList = adminServiceRead.findAllAdmin(pageIndex,AppConfig.getPage_size_web(), adminname,roleId,null);
    		// 总条数
    		totalCount = adminServiceRead.pageCount(adminname, roleId,null);
    	}else{
    		Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			adminList = adminServiceRead.findAllAdmin(pageIndex,AppConfig.getPage_size_web(), adminname,roleId,currChannelIds);
			// 总条数
    		totalCount = adminServiceRead.pageCount(adminname, roleId,currChannelIds);
    		//有用的角色列表
    		roleList = roleServiceRead.findUsefulRole(channel.getChannelLevel()); 
    	}
		for(Admin a:adminList){
			if(null!=a.getAdminChannelId()){
				Channel channel = channelServiceRead.findById(a.getAdminChannelId());
				a.setChannel(channel);
			}
			
		}
		
		
		
		// 总页数
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		if(0==totalPage){
			totalPage = 1;
		}
		
		
		model.addAttribute("roleList", roleList);
		model.addAttribute("roleId", roleId);
		model.addAttribute("adminname", adminname);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("adminList", adminList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		return "admin_list";
	}
	
	
	/**
	 * 跳转到添加管理员页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("gotoAdminAdd")
	public String gotoAdminAdd(Model model,HttpSession session) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		List<Role> roleList = new ArrayList<Role>();
		List<Channel> channelList = new ArrayList<Channel>();
		if(admin.getAdminChannelId()==null){
			//查找所有 有用的角色不含冻结
			roleList = roleServiceRead.findUsefulRole(null);
			
			//查找所有 存在的渠道
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
		}else{
			Channel channel = channelServiceRead.findById(admin.getAdminChannelId());
			//查找所有 有用的角色不含冻结
			roleList = roleServiceRead.findUsefulRole(channel.getChannelLevel());
			List<Channel> channels = channelServiceRead.findSonChannels(admin.getAdminChannelId());//查所有子渠道
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			//查找所有 存在的渠道
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
			
		}
		
		
		
		model.addAttribute("level", admin.getAdminChannelId());
		model.addAttribute("roleList", roleList);
		model.addAttribute("channelList", channelList);
		return "detail/admin_detail_add";
	}
	
	
	
	/**
	 * 新增管理员
	 * @param admin
	 * @param roleId
	 * @param
	 * @param
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("adminAdd")
	public String adminAdd(HttpSession session , Admin admin,Long[] roleId,String adminPwd2,String adminTel,String adminCreateEmail) throws Exception{
		if(!admin.getAdminPwd().equals(adminPwd2)){
            throw new Exception("密码输入不一致");
        }else{
        	Admin adminsession = (Admin) session.getAttribute("admin");
    		Admin nowAdmin = adminServiceRead.findAdminId(adminsession.getAdminId());
    		OperateLog operateLog = new OperateLog();
    		operateLog.setOperateTime(new Date());
    		operateLog.setOperateAdminId(nowAdmin.getAdminId());
        	//后期用加盐的方法
        	String salt = PasswordUtil.getSalt();   //生成随机盐
        	admin.setAdminSalt(salt);
        	String password = PasswordUtil.generate(adminPwd2, salt);  //生成混合的密码
        	admin.setAdminPwd(password);
        	if(null!=adminsession){
        		admin.setAdminCreateAdminid(adminsession.getAdminId());
        	}
			//state
			admin.setAdminState(1);
			//新增admin 返回 主键
			Long adminId = adminServiceWrite.addAminCanNull(admin);
			if(null!=roleId&&roleId.length>0){
				for (Long id : roleId) {
					AdminToRoleKey adminToRoleKey = new AdminToRoleKey();
					adminToRoleKey.setRoleId(id);
					adminToRoleKey.setAdminId(adminId);
					//新增角色和管理员关联
					roleServiceWrite.addAdminToRole(adminToRoleKey);
				}
			
			}
			String remark = OperateUtil.operateMng(nowAdmin.getAdminRealname(), 1, null, admin.getAdminRealname());
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
			return "redirect:adminList.action";
        }
	}
	
	
	/**
	 * admin详情页面
	 * @param adminId
	 * @param model
	 * @param flag 1 编辑 2 查看
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("adminDetail")
	public String adminDetail(Long adminId, Model model, Integer flag,HttpSession session) throws Exception{
		Admin adminsession = (Admin) session.getAttribute("admin");
		Admin admin = adminServiceRead.findAdminId(adminId);//取出来没有渠道channel信息
		Channel adminChannel = channelServiceRead.findById(admin.getAdminChannelId());
		admin.setChannel(adminChannel);//设置channel信息前端展示
		List<Role> roleList = new ArrayList<Role>();
		List<Channel> channelList = new ArrayList<>();
		if(adminsession.getAdminChannelId()==null){
			roleList = roleServiceRead.findUsefulRole(null);

			//查找所有 存在的渠道
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
		}else{
			Channel adminsessionChannel = channelServiceRead.findById(adminsession.getAdminChannelId());
			roleList = roleServiceRead.findUsefulRole(adminsessionChannel.getChannelLevel());
			List<Channel> channels = channelServiceRead.findSonChannels(adminsession.getAdminChannelId());//查所有子渠道
			channels.add(adminsessionChannel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
		}
		
		model.addAttribute("roleList", roleList);
		model.addAttribute("admin", admin);
		model.addAttribute("channelList", channelList);
        model.addAttribute("level", adminsession.getAdminChannelId());
		if(1 == flag){
			return "detail/admin_detail_edit";
		}else{
			return "detail/admin_detail_watch";
		}
	}
	
	
	/**
	 * 修改admin 
	 * @param admin
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("update")
	public String update(HttpSession session ,Admin admin,String adminPassword ,Long[] roleId) throws Exception{
		Admin admin2 = adminServiceRead.findAdminId(admin.getAdminId());
		Admin adminsession = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(adminsession.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		if(null!=adminPassword&&!"".equals(adminPassword)&&!admin2.getAdminPwd().equals(adminPassword)){
			//取盐
			String salt = admin2.getAdminSalt();
			String password = PasswordUtil.generate(adminPassword, salt);  //生成混合的密码
			admin.setAdminPwd(password);
		}
		
		if(!adminServiceWrite.updateAdmin(admin)){
			throw new Exception("修改admin出错");
		}
		List<AdminToRoleKey> adminToRoleKeys = roleServiceRead.findKeyByAId(admin.getAdminId());
		if(adminToRoleKeys.size()>0){
			roleServiceWrite.deleteAtoRyAId(admin.getAdminId());//删除关联数据
		}
		//新增 关联数据
		if(null != roleId){
			 for (Long id : roleId) {
				 AdminToRoleKey adminToRoleKey = new AdminToRoleKey();
				 adminToRoleKey.setAdminId(admin.getAdminId());
				 adminToRoleKey.setRoleId(id);
				 roleServiceWrite.addAdminToRole(adminToRoleKey);
			 }
		}
		String remark = OperateUtil.operateMng(nowAdmin.getAdminRealname(), 2, null, admin2.getAdminRealname());
		operateLog.setOperateRemark(remark);
        operateServiceWrite.addOperateLogs(operateLog);
		return "redirect:adminList.action";
	}
	
	
	/**
	 * 冻结管理员
	 * @return
	 * 0 冻结 1解冻-1删除
	 * @throws Exception
	 */
	@RequestMapping("frozenAdmin")
	public @ResponseBody String frozenAdmin(HttpSession session ,Long adminId,Integer adminState) throws Exception{
		Admin adminsession = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(adminsession.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		Admin admin = adminServiceRead.findAdminId(adminId);
		admin.setAdminState(adminState);
		adminServiceWrite.updateAdmin(admin);
		if(adminState==0){
			String remark = OperateUtil.operateMng(nowAdmin.getAdminRealname(), 3, null, admin.getAdminRealname());
			operateLog.setOperateRemark(remark);
		}
		if(adminState==1){
			String remark = OperateUtil.operateMng(nowAdmin.getAdminRealname(), 5, null, admin.getAdminRealname());
			operateLog.setOperateRemark(remark);
		}
		if(adminState==-1){
			String remark = OperateUtil.operateMng(nowAdmin.getAdminRealname(), 4, null, admin.getAdminRealname());
			operateLog.setOperateRemark(remark);
		}
		
        operateServiceWrite.addOperateLogs(operateLog);
		return "success";
	}
	
	/**
	 * 跳转修改密码页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("gotoPwd")
	public String gotoPwd(Model model) throws Exception {
		return "detail/password_modify";
	}
	
	
	/**
	 * 检验密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkPwd")
	public @ResponseBody MessageUtil checkPwd(HttpSession session,String oldpassword) throws Exception{
		MessageUtil messageUtil = new MessageUtil();
		Admin admin = (Admin) session.getAttribute("admin");
		//取盐
		String salt = admin.getAdminSalt(); 
		String password = PasswordUtil.generate(oldpassword, salt);
	    if(password.equals(admin.getAdminPwd())){
            messageUtil.setMessage("success");
            return messageUtil;
        }else{
        	messageUtil.setMessage("fail");
        	return messageUtil;
        }
	    
	}
	
	/**
	 * 修改密码
	 * @param model
	 * @param oldpassword 原密码
	 * @param adminPassword 新密码
	 * @param adminPassword2 再次输入的新密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyPwd")
	public String modifyPwd(Model model, String oldpassword, String adminPassword, String adminPassword2) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Admin admin = (Admin) subject.getSession().getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		//取盐
		String salt = admin.getAdminSalt();
		//旧密码
		String pwd = PasswordUtil.generate(oldpassword, salt);
		//新密码 
		String newPwd =  PasswordUtil.generate(adminPassword, salt);
		if (!admin.getAdminPwd().equals(pwd)) {
			throw new Exception("旧密码错误");
		}
		if (adminPassword.equals(adminPassword2)) {
			admin.setAdminPwd(newPwd);
		} else {
			throw new Exception("新密码输入不一致");
		}
		if (adminServiceWrite.updateAdmin(admin)) {
			model.addAttribute("message", "修改成功");
			String remark = OperateUtil.operateRole(nowAdmin.getAdminRealname(), 4, null,null );
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
			return "main";
		} else {
			throw new Exception("密码修改失败");
		}
	}
	
	
	/**
	 * 检查是否重复管理员
	 * @param adminUsername
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkAdminName")
	public @ResponseBody MessageUtil checkAdminName(String adminUsername) throws Exception{
		MessageUtil messageUtil = new MessageUtil();
		Admin admin = adminServiceRead.findByName(adminUsername);
		if(null!=admin){
			messageUtil.setMessage("fail"); 
			return messageUtil;
		}
		messageUtil.setMessage("success"); 
		return messageUtil;
	}
	
	
}
