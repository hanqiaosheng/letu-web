package org.controller.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.Insurance;
import org.entity.dto.Message;
import org.entity.dto.OperateLog;
import org.entity.dto.User;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.UserInsuranceServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.MessageServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.UserInsuranceServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.util.OperateUtil;
import org.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/user/insurance")
public class UserInsuranceController {

	@Resource
	AppConfig appConfig;

	@Resource
	UserServiceRead userServiceRead;

	@Resource
	UserInsuranceServiceRead userInsuranceServiceRead;

	@Resource
	UserInsuranceServiceWrite userInsuranceServiceWrite;

	@Resource
	AdminServiceRead adminServiceRead;

	@Resource
	MessageServiceWrite messageServiceWrite;
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	/**
	 * 保险申请列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param insurance
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String insuranceList(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, Insurance insurance)
			throws Exception {
		User user = new User();
		if (null != insurance.getiUserTel() && !"".equals(insurance.getiUserTel()))
			user.setUserTel(insurance.getiUserTel());
		if (null != insurance.getiUserName() && !"".equals(insurance.getiUserName()))
			user.setUserRealname(insurance.getiUserName());
		List<User> userList = userServiceRead.findUserByCondition(user, null, null);
		List<Long> userIds = new ArrayList<Long>();
		if (null != userList && 0 != userList.size()) {
			for (User u : userList) {
				userIds.add(u.getUserId());
			}
			insurance.setiUserIds(userIds);
		}
		List<Insurance> insuranceList = userInsuranceServiceRead.findInsuranceByCondition(insurance, pageIndex,
				appConfig.getPage_size_web());
		if (null != insuranceList && 0 != insuranceList.size()) {
			for (Insurance i : insuranceList) {
				User user2 = userServiceRead.findById(i.getInsuranceUserId());
				i.setiUserTel(user2.getUserTel());
				i.setiUserName(user2.getUserRealname());
				if (null != i.getInsuranceAdminId())
					i.setiAdminName(adminServiceRead.findAdminId(i.getInsuranceAdminId()).getAdminRealname());
			}
		}
		Integer totalPage = 1;
		Integer totalCount = userInsuranceServiceRead.countAllInsurance(insurance);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("insuranceList", insuranceList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("insurance", insurance);
		return "user_insurance_list";
	}

	/**
	 * 接受、拒绝、删除保险申请（含批量）
	 * 
	 * @param insurance
	 * @param insuranceIds
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("update")
	public  String updateInsuranceState(Insurance insurance, HttpSession session)
			throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		insurance.setInsuranceAdminId(admin.getAdminId());
		insurance.setInsuranceDealTime(new Date());
		if (null != insurance.getInsuranceId() && 0 != insurance.getInsuranceId())
			userInsuranceServiceWrite.updateInsuranceState(insurance);
		if(1==insurance.getInsuranceState()){ //拒绝
			Message message = new Message();
			message.setMessageAdminId(admin.getAdminId());
			message.setMessageContent("您的保险未通过审核，如有疑问请联系客服800-820-8820");
			message.setMessageSendTime(new Date());
			message.setTempletCode("2");
			message.setMessageInsurance(insurance.getInsuranceId());
			message.setMessageUserId(insurance.getInsuranceUserId());
			messageServiceWrite.addMessage(message);
			
			String remark = OperateUtil.operateFBAndInsurance(nowAdmin.getAdminRealname(), 7, null, insurance.getInsuranceId().toString(), null);
			operateLog.setOperateRemark(remark);
		}else if(2==insurance.getInsuranceState()){
			Message message = new Message();
			message.setMessageAdminId(admin.getAdminId());
			message.setMessageContent("您的保险已通过审核，客服人员会联系您后续操作");
			message.setTempletCode("2");
			message.setMessageSendTime(new Date());
			message.setMessageInsurance(insurance.getInsuranceId());
			message.setMessageUserId(insurance.getInsuranceUserId());
			messageServiceWrite.addMessage(message);
			
			String remark = OperateUtil.operateFBAndInsurance(nowAdmin.getAdminRealname(), 6, null, insurance.getInsuranceId().toString(), null);
			operateLog.setOperateRemark(remark);
		}
		operateServiceWrite.addOperateLogs(operateLog);
		return "redirect:list.action";
	}

	/**
	 * 保险申请详情
	 * 
	 * @param model
	 * @param pageIndex
	 * @param insurance
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("detail")
	public String insuranceDetail(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, Insurance insurance)
			throws Exception {
		insurance = userInsuranceServiceRead.findById(insurance.getInsuranceId());
		if (null != insurance.getInsuranceAccidentImgs() && !"".equals(insurance.getInsuranceAccidentImgs()))
			insurance.setAccidentImgs(insurance.getInsuranceAccidentImgs().split(";"));
		if (null != insurance.getInsuranceDetailImgs() && !"".equals(insurance.getInsuranceDetailImgs()))
			insurance.setDetailImgs(insurance.getInsuranceDetailImgs().split(";"));
		if (null != insurance.getInsuranceIdentityContact() && !"".equals(insurance.getInsuranceIdentityContact()))
			insurance.setIdentityContact(insurance.getInsuranceIdentityContact().split(";"));
		User user = userServiceRead.findById(insurance.getInsuranceUserId());
		model.addAttribute("insurance", insurance);
		model.addAttribute("user", user);
		return "detail/user_insurance_detail";
	}

}
