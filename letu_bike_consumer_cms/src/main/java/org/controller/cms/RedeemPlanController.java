package org.controller.cms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.component.AppConfig;
import org.entity.dto.CouponPlan;
import org.entity.dto.RedeemPlan;
import org.service.cms.read.CouponPlanServiceRead;
import org.service.cms.read.RedeemPlanServiceRead;
import org.service.cms.write.RedeemPlanServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.PageUtil;
import org.util.UuidUtil;



@Controller
@Scope("prototype")
@RequestMapping("cms/redeemplan")
public class RedeemPlanController {

	@Resource
	RedeemPlanServiceRead redeemPlanServiceRead;
	@Resource
	RedeemPlanServiceWrite redeemPlanServiceWrite;
	@Resource
	CouponPlanServiceRead couponPlanServiceRead;
	@Resource
	AppConfig AppConfig;
	
	//兑换方案列表
	@RequestMapping("redeemPlanList")
	public String redeemPlanList(@RequestParam(defaultValue = "1")Integer pageIndex,String redeemPlanName,Model model) throws Exception{
		List<RedeemPlan> redeemPlanList = redeemPlanServiceRead.findAll(pageIndex, AppConfig.getPage_size_web(), redeemPlanName);
		Integer totalCount = redeemPlanServiceRead.countAll(redeemPlanName);
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		if(totalPage==0){
			totalPage=1;
		}
		model.addAttribute("redeemPlanList", redeemPlanList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("redeemPlanName", redeemPlanName);
		return "redeemPlan_list";
	}
	//跳转添加兑换方案页面
	@RequestMapping("redeemPlanJsp")
	public String redeemCodeJsp(Model model) throws Exception{
		List<CouponPlan> couponPlanList = couponPlanServiceRead.findCanUsePlan();
		model.addAttribute("couponPlanList", couponPlanList);
		return "detail/redeemPlan_add";
	}
	//添加兑换方案
	@RequestMapping("addRedeemPlan")
	public String addRedeemcode(RedeemPlan redeemPlan) throws Exception{
		redeemPlan.setPlanCreateTime(new Date());
		String redeemCode = UuidUtil.generateRedeem();
		redeemPlan.setPlanRedeemCode(redeemCode);
		redeemPlan.setPlanSurplusNums(redeemPlan.getPlanRedeemNums());
		redeemPlanServiceWrite.addRedeemPlan(redeemPlan);
		return "redirect:redeemPlanList.action";
	}
	
	//跳转编辑兑换方案页面
	@RequestMapping("redeemPlanEditJsp")
	public String redeemPlanEditJsp(Model model,Long redeemPlanId) throws Exception{
		List<CouponPlan> couponPlanList = couponPlanServiceRead.findCanUsePlan();
		RedeemPlan redeemPlan = redeemPlanServiceRead.findRedeemPlanById(redeemPlanId);
		model.addAttribute("couponPlanList", couponPlanList);
		model.addAttribute("redeemPlan", redeemPlan);
		return "detail/redeemPlan_edit";
	}
	//编辑兑换方案
	@RequestMapping("editRedeemPlan")
	public String editRedeemPlan(RedeemPlan redeemPlan) throws Exception{
		redeemPlanServiceWrite.updateRedeemPlan(redeemPlan);
		return "redirect:redeemPlanList.action";
	}	
	
	
	
	//更新状态
	@RequestMapping("updateState")
	public @ResponseBody String updateState(RedeemPlan redeemPlan) throws Exception{
		if(redeemPlan.getPlanState()!=null){
			if(redeemPlan.getPlanState()==2){
				redeemPlan.setPlanOfflineTime(new Date());
			}else if(redeemPlan.getPlanState()==1){
				redeemPlan.setPlanOnlineTime(new Date());
			}else if(redeemPlan.getPlanState()==0){
				RedeemPlan nowredeemPlan = redeemPlanServiceRead.findRedeemPlanById(redeemPlan.getPlanId());
				if(nowredeemPlan.getPlanState()==1){
					return "fail";
				}
			}
		}
		redeemPlanServiceWrite.updateRedeemPlan(redeemPlan);
		return "success";
	}	
	
	/**
	 * 检查状态
	 * @param rpPlanId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkState")
	public @ResponseBody String checkState(Long planId) throws Exception{
		RedeemPlan redeemPlan = redeemPlanServiceRead.findRedeemPlanById(planId);
		if(redeemPlan.getPlanState()==1){
			return "fail";
		}
		return "success";
	}
	

	//兑换方案详情面
	@RequestMapping("redeemPlanDetail")
	public String redeemPlanDetail(Model model,Long redeemPlanId) throws Exception{
		List<CouponPlan> couponPlanList = couponPlanServiceRead.findCanUsePlan();
		RedeemPlan redeemPlan = redeemPlanServiceRead.findRedeemPlanById(redeemPlanId);
		model.addAttribute("couponPlanList", couponPlanList);
		model.addAttribute("redeemPlan", redeemPlan);
		return "detail/redeemPlan_detail";
	}
}
