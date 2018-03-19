package org.controller.cms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.component.AppConfig;
import org.entity.dto.UserRedeemPlan;
import org.service.cms.read.CouponPlanServiceRead;
import org.service.cms.read.RedeemPlanServiceRead;
import org.service.cms.read.UserRedeemPlanServiceRead;
import org.service.cms.write.RedeemPlanServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.util.DateUtil;
import org.util.PageUtil;



@Controller
@Scope("prototype")
@RequestMapping("cms/redeemrecord")
public class RedeemRecordController {

	@Resource
	RedeemPlanServiceRead redeemPlanServiceRead;
	@Resource
	RedeemPlanServiceWrite redeemPlanServiceWrite;
	@Resource
	CouponPlanServiceRead couponPlanServiceRead;
	@Resource
	UserRedeemPlanServiceRead userRedeemPlanServiceRead;
	@Resource
	AppConfig AppConfig;
	
	/**
	 * 兑换记录列表
	 * @param pageIndex
	 * @param redeemPlanName
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("redeemRecordList")
	public String redeemRecordList(@RequestParam(defaultValue = "1")Integer pageIndex,HttpServletResponse response,String redeemPlanName,Model model,Date startTime,Date endTime,String userTel,String redeemCode,@RequestParam(defaultValue="0")Integer flag) throws Exception{
		if(flag==1){
			pageIndex=null;
		}
		List<UserRedeemPlan> userRedeemPlanList = userRedeemPlanServiceRead.findAllUserRedeemPlan(pageIndex, AppConfig.getPage_size_web(), redeemPlanName,startTime,endTime,userTel, redeemCode);
		Integer totalCount = userRedeemPlanServiceRead.countAllUserRedeemPlan(redeemPlanName,startTime,endTime,userTel, redeemCode);
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		if(totalPage==0){
			totalPage=1;
		}
		model.addAttribute("userRedeemPlanList", userRedeemPlanList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("userTel", userTel);
		model.addAttribute("redeemCode", redeemCode);
		model.addAttribute("redeemPlanName", redeemPlanName);
		if(1 == flag){
			response.setHeader("Content-disposition","attachment; filename=redeemRecord_excel_"+DateUtil.formatDay(new Date())+".xls");
			return "redeemRecord_excel_list";
		}
		return "redeemRecord_list";
	}
	
}
