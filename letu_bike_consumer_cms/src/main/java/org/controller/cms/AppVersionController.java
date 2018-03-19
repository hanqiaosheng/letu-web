package org.controller.cms;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.entity.dto.Admin;
import org.entity.dto.SysParament;
import org.service.cms.read.SysParamentServiceRead;
import org.service.cms.write.SysParamentServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
@RequestMapping("cms/app")
public class AppVersionController {

	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	
	@Resource
	SysParamentServiceWrite sysParamentServiceWrite;
	
	/**
	 * app版本管理页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("version")
	public String version(Model model) throws Exception{
		SysParament androidApp = sysParamentServiceRead.findByName("android_version");
		SysParament iosApp = sysParamentServiceRead.findByName("ios_version");
		model.addAttribute("androidApp", androidApp);
		model.addAttribute("iosApp", iosApp);
		return "app_version";
	}
	
	/**
	 * 跳转编辑页
	 * @param sysParamentId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editJsp")
	public String editJsp(Long sysParamentId,Model model) throws Exception{
		SysParament sysParament = sysParamentServiceRead.findById(sysParamentId);
		model.addAttribute("sysParament", sysParament);
		return "detail/version_edit";
	}
	
	/**
	 * 编辑
	 * @param sysParament
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editVersion")
	public String editVersion(SysParament sysParament,HttpSession session) throws Exception{
		Admin admin  = (Admin) session.getAttribute("admin");
		sysParament.setSysParamentAdminId(admin.getAdminId());
		sysParamentServiceWrite.update(sysParament);
		return "redirect:version.action";
	}
}
