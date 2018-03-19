package org.controller.cms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.OperateLog;
import org.entity.dto.UserGuide;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.UserServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.util.OperateUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/user/guide")
public class UserGuideController {

	@Resource
	UserServiceWrite userServiceWrite;
	
	@Resource
	UserServiceRead userServiceRead;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	@Resource
	AppConfig appConfig;

	/**
	 * 用户指南列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String userGuideList(Model model,HttpSession session)
			throws Exception {
		List<UserGuide> userGuideList = userServiceRead.findAllGuideInfo();
		for(UserGuide u : userGuideList){
			Admin admin = adminServiceRead.findAdminId(u.getUserGuideAdminid());
			if(null!=admin){
				u.setEditAdminName(admin.getAdminUsername());
			}
		}
		model.addAttribute("userGuideList", userGuideList);
		return "user_guide";
	}

	/**
	 * 修改页面
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editJsp")
	public String editJsp(Long userGuideId, Model model) throws Exception {
		UserGuide userGuide = userServiceRead.findGuideInfoById(userGuideId);
		model.addAttribute("userGuide", userGuide);
		model.addAttribute("url", appConfig.getUpload_path());
		return "detail/userGuide_edit";
	}

	/**
	 * 修改
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	public String edit(UserGuide userGuide,HttpSession session) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		userGuide.setUserGuideAdminid(admin.getAdminId());
		userServiceWrite.updateUserGuide(userGuide);
		UserGuide nowuserGuide = userServiceRead.findGuideInfoById(userGuide.getUserGuideId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateFBAndInsurance(nowAdmin.getAdminRealname(), 8, null, null, nowuserGuide.getUserGuideType());
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		return "redirect:list.action";
	}


}
