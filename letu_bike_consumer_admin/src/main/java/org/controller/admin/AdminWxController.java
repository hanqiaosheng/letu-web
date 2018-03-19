package org.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationException;
import org.entity.dto.Admin;
import org.entity.dto.AdminToRoleKey;
import org.entity.dto.Channel;
import org.entity.dto.LatLng;
import org.entity.dto.Models;
import org.entity.dto.Permission;
import org.entity.dto.Role;
import org.entity.dto.RoleToPermissionKey;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.BlockServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.ModelsServiceRead;
import org.service.cms.read.PermissionServiceRead;
import org.service.cms.read.RoleServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.BlockUtil;
import org.util.MessageUtil;
import org.util.PasswordUtil;

@Controller
@Scope("prototype") 
@RequestMapping(value = "/api/admin", method = RequestMethod.POST)
public class AdminWxController {

	
	@Resource
	AdminServiceRead adminServiceRead;
	@Resource
	ChannelServiceRead channelServiceRead;
	@Resource
	BlockServiceRead blockServiceRead;
	@Resource
	BikeServiceRead bikeServiceRead;
	@Resource
	ModelsServiceRead modelsServiceRead;
	@Resource
	RoleServiceRead roleServiceRead;
	@Resource
	PermissionServiceRead permissionServiceRead;
	
	protected final MessageUtil messageUtil = new MessageUtil();
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
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody MessageUtil login(String username, String password, HttpServletRequest request, Model model, HttpSession session,
			HttpServletResponse response) throws Exception {
		Admin admin = adminServiceRead.findByName(username);
		//账号为空
		if(null==admin){
			messageUtil.setMessage("noAccount");
			return messageUtil;
		}
		
		//查当前拥有的角色
		Integer isLogin = 0;//(0不能登陆1能登陆)
		List<AdminToRoleKey> rolecontact = roleServiceRead.findKeyByAId(admin.getAdminId());
		if(rolecontact!=null && rolecontact.size()>0){
			for (AdminToRoleKey ro : rolecontact) {
				Role role = roleServiceRead.findById(ro.getRoleId());;
				if(null!=role&&role.getRoleState()!=-1&&role.getRoleState()!=0){
					List<RoleToPermissionKey> rpList = permissionServiceRead.findByRId(role.getRoleId());
					if(rpList!=null && rpList.size()>0){
						for (RoleToPermissionKey rp : rpList) {
							Permission permission = permissionServiceRead.findByPId(rp.getPermissionId());
							if(permission.getPermissionValue().equals("adminLogin")){
								isLogin = 1;
							}
						}
					}
				}
			}
		}
		
		if(isLogin==0){
			messageUtil.setMessage("noPermission");
			return messageUtil;
		}
		//如果渠道不为空 查找对应的渠道  
		if(null!=admin.getAdminChannelId()){
		//查找对应的渠道
		Channel channel = channelServiceRead.findById(admin.getAdminChannelId());
		//如果已经冻结
		if(0==channel.getChannelState()){
			messageUtil.setMessage("channelFail");
			return messageUtil;
		}
		}
		String pwd = PasswordUtil.generate(password, admin.getAdminSalt());  //生成混合的密码
		if(!admin.getAdminPwd().equals(pwd)){
			messageUtil.setMessage("pwdError");
			return messageUtil;
		}
		try {
			//把admin的渠道id放到session中
			session.setAttribute("currChannelId", admin.getAdminChannelId());
			session.setAttribute("admin", admin);
			messageUtil.setMessage("success");
			return messageUtil;
		} catch (AuthenticationException e) {
			messageUtil.setMessage("loginFail");
			return messageUtil;
		}
	}
	
	
	
	/**
	 * 根据渠道取车
	 * @param latLng
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findBikes", method = RequestMethod.POST)
	public @ResponseBody List<LatLng> findBikes(String latLng,HttpSession session) throws Exception {
		List<String> blockCodes = BlockUtil.getBlockCodeFor9(latLng);
		/*List<Long> bIds = blockServiceRead.findBlockIdsByCodes(blockCodes);
		List<LatLng> bikeList = new ArrayList<LatLng>();
		if(null==bIds||0==bIds.size()){
			return bikeList;
		}*/
		List<LatLng> bikeList = new ArrayList<LatLng>();
		List<Long> modelsList = new ArrayList<Long>();
		Admin admin_session = (Admin) session.getAttribute("admin");
		Admin admin = adminServiceRead.findAdminId(admin_session.getAdminId());
		if(null==admin.getAdminChannelId()){  //超级管理员
			bikeList = bikeServiceRead.findByBlockIds(blockCodes);
		}else{
			Channel channel = channelServiceRead.findById(admin.getAdminChannelId());
			modelsList = modelsServiceRead.findByChannelId(channel.getChannelId());
			if(null!=modelsList){
			if(null!=channel.getChannelParentId()){
				bikeList = bikeServiceRead.findByBlockIdsAndChannel(blockCodes,modelsList);
			}else{
				List<Channel> channels = channelServiceRead.findByParentChannelId(channel.getChannelId());
				if(channels.size()>0){
					List<Long> currChannelIds = new ArrayList<Long>();
					for(Channel c:channels){
						currChannelIds.add(c.getChannelId());
					}
					List<Models> modelsList2 = modelsServiceRead.findChannelIds(currChannelIds);
					List<Long> modelsList3 = new ArrayList<Long>();
					for(Models m:modelsList2){
						modelsList3.add(m.getModelsId());
					}
					modelsList.addAll(modelsList3);
				}
			
				
				bikeList = bikeServiceRead.findByBlockIdsAndChannel(blockCodes,modelsList);
			}
			}
		}
		return bikeList;
	}
	

	/**
	 * session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmin", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getAdmin(HttpSession session) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		Map<String, Object> data = new HashMap<String, Object>();
		if(null==admin){
			messageUtil.setMessage("unlogin");
			return messageUtil;
		}
		List<Channel> channels = new ArrayList<Channel>();
		if(null==admin.getAdminChannelId()){  //超级管理员
			channels = channelServiceRead.findAllChannelNotfreeze(null);
		}
		data.put("admin", admin);
		data.put("channels", channels);
		messageUtil.setData(data);
		messageUtil.setMessage("islogin");
		return messageUtil;
	}
	
	
}
