package org.controller.cms;

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
import org.service.cms.write.CashCouponServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.PageUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



@Controller
@Scope("prototype")
@RequestMapping("cms/cashcoupon")
public class CashCouponController {

	@Resource
	CashCouponServiceRead cashCouponServiceRead;
	@Resource
	CashCouponServiceWrite cashCouponServiceWrite;
	@Resource
	RedeemPlanServiceRead redeemPlanServiceRead;
	@Resource
	CouponPlanServiceRead couponPlanServiceRead;
	@Resource
	AppConfig AppConfig;
	
	/**
	 * 代金券列表
	 * @param session
	 * @param pageIndex
	 * @param model
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping("cashCouponList")
	public String cashCouponList(@RequestParam(defaultValue = "1") Integer pageIndex, Model model,String couponName) throws Exception{
		List<CashCoupon> cashCouponList  = cashCouponServiceRead.findAll(pageIndex,AppConfig.getPage_size_web(),couponName);
		Integer totalCount = cashCouponServiceRead.countAll(couponName);
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		if(0==totalPage){
			totalPage =1;
		}
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("cashCouponList", cashCouponList);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("couponName", couponName);
		return "cashCoupon_list";
	}
	
	//跳转添加代金券页面
	@RequestMapping("cashCouponJsp")
	public String cashCouponJsp(Model model) throws Exception{
		return "detail/cashCoupon_add";
	}
	
	//添加代金券
	@RequestMapping("addCashCoupon")
	public String addCashCoupon(CashCoupon cashCoupon) throws Exception{
		cashCoupon.setCouponCreateTime(new Date());
		cashCouponServiceWrite.addCashCoupon(cashCoupon);
		return "redirect:cashCouponList.action";
	}
	
	
	//跳转编辑代金券页面
	@RequestMapping("cashCouponEditJsp")
	public String cashCouponEditJsp(Model model,Long couponId) throws Exception{
		CashCoupon cashCoupon = cashCouponServiceRead.findById(couponId);
		if(cashCoupon.getCouponStartTime()!=null&&cashCoupon.getCouponEndTime()!=null){
			cashCoupon.setCouponEndTimeStr(DateUtil.formatDay(cashCoupon.getCouponEndTime()));
			cashCoupon.setCouponStartTimeStr(DateUtil.formatDay(cashCoupon.getCouponStartTime()));
		}
		model.addAttribute("cashCoupon", cashCoupon);
		return "detail/cashCoupon_edit";
	}
	
	//修改代金券
	@RequestMapping("editCashCoupon")
	public String editCashCoupon(CashCoupon cashCoupon) throws Exception{
		cashCouponServiceWrite.updateCashCoupon(cashCoupon);
		return "redirect:cashCouponList.action";
	}
	
	/**
	 * 详情
	 * @param redeemCodeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("cashCouponDetail")
	public String cashCouponDetail(Long couponId,Model model) throws Exception{
		CashCoupon cashCoupon = cashCouponServiceRead.findById(couponId);
		model.addAttribute("cashCoupon", cashCoupon);
		return "detail/cashCoupon_detail";
	}
	
	/**
	 * 软删除代金券
	 * @param redeemCodeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delCashCoupon")
	public @ResponseBody String delCashCoupon(Long cashCouponId) throws Exception{
		List<RedeemPlan> redeemPlans = redeemPlanServiceRead.findAllPlan();
		for(RedeemPlan rp : redeemPlans){
			CouponPlan couponPlan = couponPlanServiceRead.findByCouponPlanId(rp.getPlanCouponPlanId());
			if(couponPlan.getCouponPlanJson()!=null&&!couponPlan.getCouponPlanJson().equals("")){
				JSONArray jsonArray = JSONArray.fromObject(couponPlan.getCouponPlanJson());
				for(int i=0 ; i < jsonArray.size() ;i++){
					 JSONObject myjObject = jsonArray.getJSONObject(i);
					 Long couponId = Long.valueOf(myjObject.getString("id"));
					 if(couponId.equals(cashCouponId)){
							return "fail";
					 }
				}
			}
		}
		CashCoupon cashCoupon = cashCouponServiceRead.findById(cashCouponId);
		cashCoupon.setCouponState(0);//删除
		cashCouponServiceWrite.updateCashCoupon(cashCoupon);
		return "success";
	}
	
	
	/**
	 * 检验代金券
	 * @param redeemCodeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkCoupon")
	public @ResponseBody String checkCoupon(Long couponId) throws Exception{
		List<RedeemPlan> redeemPlans = redeemPlanServiceRead.findAllOnline();
		for(RedeemPlan rp : redeemPlans){
			CouponPlan couponPlan = couponPlanServiceRead.findByCouponPlanId(rp.getPlanCouponPlanId());
			if(couponPlan.getCouponPlanJson()!=null&&!couponPlan.getCouponPlanJson().equals("")){
				JSONArray jsonArray = JSONArray.fromObject(couponPlan.getCouponPlanJson());
				for(int i=0 ; i < jsonArray.size() ;i++){
					 JSONObject myjObject = jsonArray.getJSONObject(i);
					 Long cashCouponId = Long.valueOf(myjObject.getString("id"));
					 if(couponId.equals(cashCouponId)){
							return "fail";
					 }
				}
			}
		}
		return "success";
	}
	
}
