package org.controller.cms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.Code;
import org.entity.dto.OperateLog;
import org.entity.dto.SysMsg;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.CodeServiceRead;
import org.service.cms.read.SysMsgServiceRead;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.SysMsgServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
import org.util.OperateUtil;
import org.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/sysMsg")
public class SysMsgController {

	@Resource
	AppConfig appConfig;

	@Resource
	SysMsgServiceRead sysMsgServiceRead;

	@Resource
	SysMsgServiceWrite sysMsgServiceWrite;

	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	CodeServiceRead codeServiceRead;
	
	@Resource
	OperateServiceWrite operateServiceWrite;

	/**
	 * 系统消息列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param sysMsg
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String sysMsgList(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, SysMsg sysMsg)
			throws Exception {
		List<SysMsg> sysMsgList = sysMsgServiceRead.findSysMsgByCondition(sysMsg, pageIndex,
				appConfig.getPage_size_web());
		if(null!=sysMsgList&&0!=sysMsgList.size()){
			for (SysMsg s : sysMsgList) {
				s.setOperationAdminName(adminServiceRead.findAdminId(s.getAdminId()).getAdminRealname());
			}
		}
		Integer totalPage = 1;
		Integer totalCount = sysMsgServiceRead.countAllSysMsg(sysMsg);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("sysMsgList", sysMsgList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("sysMsg", sysMsg);
		return "sys_msg_list";
	}

	/**
	 * 上下线、删除系统消息（含批量）
	 * 
	 * @param sysMsg
	 * @param sysMsgIds
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("update")
	public @ResponseBody String updateSysMsg(SysMsg sysMsg, Long[] sysMsgIds, HttpSession session) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		
		sysMsg.setAdminId(admin.getAdminId());
		if (null != sysMsg.getSysMsgIsonline()) {
			if (0 == sysMsg.getSysMsgIsonline())
				sysMsg.setSysMsgOfflinetime(new Date());
			if (1 == sysMsg.getSysMsgIsonline())
				sysMsg.setSysMsgOnlinetime(new Date());
		}
		if (null != sysMsgIds && 0 != sysMsgIds.length) {
			String sysMsgIdStr = "";
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			for (int i = 0; i < sysMsgIds.length; i++) {
				sysMsg.setSysMsgId(sysMsgIds[i]);
				sysMsgServiceWrite.updateSysMsg(sysMsg);
				sysMsgIdStr = sysMsgIdStr +" " + sysMsgIds[i];
			}
			if (null != sysMsg.getSysMsgIsonline()) {
				if (-1 == sysMsg.getSysMsgIsonline()){
					String remark = OperateUtil.operateMsg(nowAdmin.getAdminRealname(), 6, sysMsgIdStr);//批量删除
					operateLog.setOperateRemark(remark);
				}
			}
			operateServiceWrite.addOperateLogs(operateLog);//操作日志
		} else if (null != sysMsg.getSysMsgId() && 0 != sysMsg.getSysMsgId()) {
			sysMsgServiceWrite.updateSysMsg(sysMsg);
			
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			if (null != sysMsg.getSysMsgIsonline()) {
				if (-1 == sysMsg.getSysMsgIsonline()){
					String remark = OperateUtil.operateMsg(nowAdmin.getAdminRealname(), 5, sysMsg.getSysMsgId().toString());//删除
					operateLog.setOperateRemark(remark);
				}
				if (0 == sysMsg.getSysMsgIsonline()){
					String remark = OperateUtil.operateMsg(nowAdmin.getAdminRealname(), 4, sysMsg.getSysMsgId().toString());//下线
					operateLog.setOperateRemark(remark);
				}
				if (1 == sysMsg.getSysMsgIsonline()){
					String remark = OperateUtil.operateMsg(nowAdmin.getAdminRealname(), 3, sysMsg.getSysMsgId().toString());//上线
					operateLog.setOperateRemark(remark);
				}
			}
			operateServiceWrite.addOperateLogs(operateLog);//操作日志
		}
		
		
		
		return "redirect:list.action";
	}

	/**
	 * 系统消息详情
	 * 
	 * @param model
	 * @param sysMsgId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String sysMsgDetail(Model model, Long sysMsgId) throws Exception {
		SysMsg sysMsg = sysMsgServiceRead.findById(sysMsgId);
		model.addAttribute("sysMsg", sysMsg);
		return "detail/sysMsg_detail";
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addJsp")
	public String toAddSysMsg() throws Exception {
		return "detail/sysMsg_add";
	}

	/**
	 * 新增系统消息
	 * 
	 * @param sysMsg
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String addSysMsg(SysMsg sysMsg, HttpSession session) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		sysMsg.setAdminId(admin.getAdminId());
		sysMsg.setSysMsgCreatetime(new Date());
		sysMsg.setSysMsgIsonline((short) 0);
		Long sysMsgId = sysMsgServiceWrite.addSysMsg(sysMsg);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateMsg(nowAdmin.getAdminRealname(), 1, sysMsgId.toString());
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "redirect:list.action";
	}
	
	/**
	 * 编辑页面
	 * @param sysMsgId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editJsp")
	public String editJsp(Long sysMsgId,Model model) throws Exception {
		SysMsg sysMsg = sysMsgServiceRead.findById(sysMsgId);
		model.addAttribute("sysMsg", sysMsg);
		return "detail/sysMsg_edit";
	}
	
	/**
	 * 消息编辑
	 * @param sysMsg
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editSysMsg")
	public String editSysMsg(SysMsg sysMsg,Model model, HttpSession session) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		sysMsgServiceWrite.updateSysMsg(sysMsg);
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateMsg(nowAdmin.getAdminRealname(), 2, sysMsg.getSysMsgId().toString());
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		return "redirect:list.action";
	}
	
	
	@RequestMapping(value = "/checkSysMsg", method = RequestMethod.POST)
	public @ResponseBody MessageUtil checkSysMsg(Long sysMsgId,Model model) throws Exception {
		SysMsg sysMsg = sysMsgServiceRead.findById(sysMsgId);
		MessageUtil messageUtil = new MessageUtil();
 		if(sysMsg.getSysMsgIsonline()==1){
 			messageUtil.setMessage("fail");
		}else if(sysMsg.getSysMsgIsonline()==0){
			messageUtil.setMessage("success");
		}
		return messageUtil;
	}
	
	/**
	 * 短信列表
	 * @param pageIndex
	 * @param tel
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("shortMessageList")
	public String shortMessageList(@RequestParam(defaultValue = "1") Integer pageIndex,String tel,Model model,Integer codeState) throws Exception {
	  List<Code> codeList = codeServiceRead.findAllShotMessage(pageIndex,appConfig.getPage_size_web(),tel,codeState);
	  Integer totalCount = codeServiceRead.getCountShort(tel,codeState);
	  Integer totalPage = 1;
	  if(totalCount>0){
		  totalPage = PageUtil.getTotalPage(totalCount);
	  }
	  model.addAttribute("pageIndex", pageIndex);
	  model.addAttribute("codeList", codeList);
	  model.addAttribute("codeState", codeState);
	  model.addAttribute("totalPage", totalPage);
	  model.addAttribute("tel", tel);
	  return "short_msg_list";	
	}

}
