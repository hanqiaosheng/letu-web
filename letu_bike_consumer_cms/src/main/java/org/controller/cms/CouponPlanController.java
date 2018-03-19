package org.controller.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.component.AppConfig;
import org.entity.dto.CashCoupon;
import org.entity.dto.CouponPlan;
import org.entity.dto.RedeemPlan;
import org.service.cms.read.CashCouponServiceRead;
import org.service.cms.read.CouponPlanServiceRead;
import org.service.cms.read.RedeemPlanServiceRead;
import org.service.cms.write.CouponPlanServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.PageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Controller
@Scope("prototype")
@RequestMapping("cms/couponplan")
public class CouponPlanController {

	@Resource
	CouponPlanServiceRead couponPlanServiceRead;
	@Resource
	CouponPlanServiceWrite couponPlanServiceWrite;
	@Resource
	CashCouponServiceRead cashCouponServiceRead;
	@Resource
	RedeemPlanServiceRead redeemPlanServiceRead;
	@Resource
	AppConfig AppConfig;
	
	/**
	 * 代金券方案列表
	 * @param session
	 * @param pageIndex
	 * @param model
	 * @param title
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("couponPlanList")
	public String couponPlanList(@RequestParam(defaultValue = "1") Integer pageIndex,Model model,String couponPlanName) throws Exception{
		List<CouponPlan> couponPlanList = couponPlanServiceRead.findAllCouponPlan(pageIndex,AppConfig.getPage_size_web(),couponPlanName);
		Integer totalCount = couponPlanServiceRead.findAllCouponPlanCount(couponPlanName);
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("couponPlanList", couponPlanList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("couponPlanName", couponPlanName);
		return "coupon_plan_list";
	}
	
	/**
	 * 新增代金券方案的页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("couponPlanAddJsp")
	public String couponPlanAddJsp(Model model) throws Exception{
		List<CashCoupon> cashCouponList = cashCouponServiceRead.findAllCashCoupon();
		model.addAttribute("cashCouponList", cashCouponList);
		return "detail/coupon_plan_add";
	}
	
	/**
	 * 新增代金券方案
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addCouponPlan")
	public String addCouponPlan(String cashCouponIds,String couponSendNums,String couponPlanName) throws Exception{
		CouponPlan couponPlan = new CouponPlan();
		couponPlan.setCouponPlanName(couponPlanName);
		
		JSONArray jsonArray = new JSONArray();
		if(cashCouponIds.length()>1){
			String[] cashCouponArr = cashCouponIds.split(",");
			String[] couponSendNumArr = couponSendNums.split(",");
			for (int i=0;i<cashCouponArr.length;i++){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", cashCouponArr[i]);
				jsonObject.put("num", couponSendNumArr[i]);
				jsonArray.add(jsonObject);
			}
			
		}else{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", cashCouponIds);
			jsonObject.put("num", couponSendNums);
			jsonArray.add(jsonObject);
		}
		couponPlan.setCouponPlanCreateTime(new Date());
		couponPlan.setCouponPlanJson(jsonArray.toString());
		couponPlanServiceWrite.addCouponPlan(couponPlan);
		return "redirect:couponPlanList.action";
	}
	
	/**
	 * 修改代金券方案的页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("couponPlanEditJsp")
	public String couponPlanEditJsp(Model model,Long couponPlanId) throws Exception{
		CouponPlan couponPlan = couponPlanServiceRead.findByCouponPlanId(couponPlanId);
		List<CashCoupon> cashCouponList = cashCouponServiceRead.findAllCashCoupon();
		List<CouponPlan> couponPlansList = new  ArrayList<CouponPlan>();
		String couponJson = couponPlan.getCouponPlanJson();
		if(null!=couponJson&&!couponJson.equals("")){
			JSONArray myJsonArray = JSONArray.fromObject(couponJson);
			for(int i=0 ; i < myJsonArray.size() ;i++){
				 CouponPlan couponPlans = new CouponPlan();
				 JSONObject myjObject = myJsonArray.getJSONObject(i);
				 Long cashCouponId = Long.valueOf(myjObject.getString("id"));
				 Integer num = Integer.parseInt(myjObject.getString("num"));
				 CashCoupon cashCoupon = cashCouponServiceRead.findById(cashCouponId);
				 couponPlans.setNum(num);
				 couponPlans.setCashCoupon(cashCoupon);
				 couponPlansList.add(i, couponPlans);
			}
		}
		model.addAttribute("couponPlansList", couponPlansList);
		model.addAttribute("cashCouponList", cashCouponList);
		model.addAttribute("couponPlan", couponPlan);
		return "detail/coupon_plan_edit";
	}
	
	/**
	 * 修改代金券方案
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editCouponPlan")
	public String editCouponPlan(String cashCouponIds,String couponSendNums,String couponPlanName,Long couponPlanId) throws Exception{
		CouponPlan couponPlan = couponPlanServiceRead.findByCouponPlanId(couponPlanId);
		couponPlan.setCouponPlanName(couponPlanName);
		
		JSONArray jsonArray = new JSONArray();
		if(cashCouponIds.length()>1){
			String[] cashCouponArr = cashCouponIds.split(",");
			String[] couponSendNumArr = couponSendNums.split(",");
			for (int i=0;i<cashCouponArr.length;i++){
				net.sf.json.JSONObject jsonObject = new net.sf.json.JSONObject();
				jsonObject.put("id", cashCouponArr[i]);
				jsonObject.put("num", couponSendNumArr[i]);
				jsonArray.add(jsonObject);
			}
			
		}else{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", cashCouponIds);
			jsonObject.put("num", couponSendNums);
			jsonArray.add(jsonObject);
		}
		couponPlan.setCouponPlanJson(jsonArray.toString());
		couponPlanServiceWrite.updateCouponPlan(couponPlan);
		return "redirect:couponPlanList.action";
	}
	
	/**
	 * 方案详情
	 * @param redeemCodeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("planDetail")
	public String planDetail(Long couponPlanId,Model model) throws Exception{
		CouponPlan couponPlan = couponPlanServiceRead.findByCouponPlanId(couponPlanId);
		List<CouponPlan> couponPlansList = new  ArrayList<CouponPlan>();
		String couponJson = couponPlan.getCouponPlanJson();
		if(null!=couponJson&&!couponJson.equals("")){
			JSONArray myJsonArray = JSONArray.fromObject(couponJson);
			for(int i=0 ; i < myJsonArray.size() ;i++){
				 CouponPlan couponPlans = new CouponPlan();
				 JSONObject myjObject = myJsonArray.getJSONObject(i);
				 Long cashCouponId = Long.valueOf(myjObject.getString("id"));
				 Integer num = Integer.parseInt(myjObject.getString("num"));
				 CashCoupon cashCoupon = cashCouponServiceRead.findById(cashCouponId);
				 couponPlans.setNum(num);
				 couponPlans.setCashCoupon(cashCoupon);
				 couponPlansList.add(i, couponPlans);
			}
		}
		model.addAttribute("couponPlansList", couponPlansList);
		model.addAttribute("couponPlan", couponPlan);
		return "detail/couponPlan_detail";
	}
	
	
	/**
	 * 软删除代金券方案
	 * @param redeemCodeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delCouponPlan")
	public @ResponseBody String delCouponPlan(Long couponPlanId) throws Exception{
		List<RedeemPlan> redeemPlans = redeemPlanServiceRead.findAllPlan();
		for(RedeemPlan rp : redeemPlans){
			if(couponPlanId.equals(rp.getPlanCouponPlanId())){
				return "fail";
			}
		}
		CouponPlan couponPlan = couponPlanServiceRead.findByCouponPlanId(couponPlanId);
		couponPlan.setCouponPlanState(0);//删除
		couponPlanServiceWrite.updateCouponPlan(couponPlan);
		return "success";
	}
	
	/**
	 * 检验代金券方案 
	 * @param redeemCodeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkCouponPlan")
	public @ResponseBody String checkCouponPlan(Long couponPlanId) throws Exception{
		List<RedeemPlan> redeemPlans = redeemPlanServiceRead.findAllOnline();
		for(RedeemPlan rp : redeemPlans){
			if(couponPlanId.equals(rp.getPlanCouponPlanId())){
				return "fail";
			}
		}
		return "success";
	}
	
}
