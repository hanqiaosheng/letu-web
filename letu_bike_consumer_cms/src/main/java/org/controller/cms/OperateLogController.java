package org.controller.cms;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.OperateLog;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.OperateLogServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.util.PageUtil;


@Controller
@Scope("prototype")
@RequestMapping("cms/operate")
public class OperateLogController {

	@Resource
	OperateLogServiceRead operateLogServiceRead;
	@Resource
	AppConfig appConfig;
	@Resource
	AdminServiceRead adminServiceRead;


	/**
	 * 操作日志列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param sysMsg
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model, @RequestParam(defaultValue = "1") Integer pageIndex,OperateLog operateLog,String adminRealname)
			throws Exception {
		List<Long> arr = new ArrayList<Long>();
		if(null!=adminRealname&&!"".equals(adminRealname)){
			List<Admin> adminlist = adminServiceRead.findByRealName(adminRealname);
			if(null!=adminlist&&0!=adminlist.size()){
				for (Admin admin : adminlist) {
					arr.add(admin.getAdminId());
			    }
			}
			else arr.add((long)0);
		}
		
	
		
		
		List<OperateLog> operateLogList = operateLogServiceRead.findOperateByCondition(arr,operateLog,pageIndex,appConfig.getPage_size_web());
		if(null!=operateLogList&&0!=operateLogList.size()){
			for (OperateLog o : operateLogList) {
				o.setAdmin(adminServiceRead.findAdminId(o.getOperateAdminId()));
			}
		}
		Integer totalPage = 1;
		Integer totalCount = operateLogServiceRead.countAllOperate(arr,operateLog);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("operateLogList", operateLogList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("adminRealname", adminRealname);
		model.addAttribute("operateLog", operateLog);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		//model.addAttribute("sysMsg", sysMsg);
		return "operate_list";
	}


}
