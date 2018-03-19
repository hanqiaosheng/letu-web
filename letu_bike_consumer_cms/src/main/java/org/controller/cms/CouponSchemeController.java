package org.controller.cms;


import org.component.AppConfig;
import org.entity.dto.*;
import org.service.cms.read.CouponListServiceRead;
import org.service.cms.read.CouponPlanServiceRead;
import org.service.cms.read.CouponSchemeServiceRead;
import org.service.cms.read.CouponSchemeServiceRead;
import org.service.cms.write.CouponSchemeServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 券方案controller
 */
@Controller
@Scope("prototype")
@RequestMapping("cms/couponScheme")
public class CouponSchemeController {
    @Resource
    CouponSchemeServiceRead couponSchemeServiceRead;
    @Resource
    CouponSchemeServiceWrite couponSchemeServiceWrite;
    @Resource
    CouponListServiceRead couponListServiceRead;
    @Resource
    AppConfig AppConfig;

    //骑行券方案列表
    @RequestMapping("list")
    public String list(@RequestParam(defaultValue = "1")Integer pageIndex,String couponSchemeName,Model model) throws Exception{
        List<CouponScheme> couponSchemeList = couponSchemeServiceRead.findAll(pageIndex, AppConfig.getPage_size_web(), couponSchemeName);
        Integer totalCount = couponSchemeServiceRead.countAll(couponSchemeName);
        Integer totalPage = PageUtil.getTotalPage(totalCount);
        if(totalPage==0){
            totalPage=1;
        }
        model.addAttribute("couponSchemeList", couponSchemeList);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("couponSchemeName", couponSchemeName);
        return "coupon_scheme_list";
    }
    //跳转添加骑行券方案页面
    @RequestMapping("addJsp")
    public String addJsp(Model model) throws Exception{
        modelInit(model,"add",null);
        return "detail/coupon_scheme_add";
    }
    //添加骑行券方案
    @RequestMapping("add")
    public String add(CouponScheme couponScheme) throws Exception{
        couponScheme.setPlanCreateTime(new Date());
        String redeemCode = UuidUtil.generateRedeem();
        couponScheme.setPlanRedeemCode(redeemCode);
        couponScheme.setPlanSurplusNums(couponScheme.getPlanRedeemNums());
        couponSchemeServiceWrite.addCouponScheme(couponScheme);
        return "redirect:list.action";
    }

    //跳转编辑骑行券方案页面
    @RequestMapping("editJsp")
    public String editJsp(Model model,Long couponSchemeId) throws Exception{
        modelInit(model,"edit",couponSchemeId);
        return "detail/coupon_scheme_add";
    }
    //编辑骑行券方案
    @RequestMapping("edit")
    public String edit(CouponScheme couponScheme) throws Exception{
        couponSchemeServiceWrite.updateCouponScheme(couponScheme);
        return "redirect:list.action";
    }



    //更新状态
    @RequestMapping("updateState")
    public @ResponseBody String updateState(CouponScheme couponScheme) throws Exception{
        if(couponScheme.getPlanState()!=null){
            if(couponScheme.getPlanState()==2){
                couponScheme.setPlanOfflineTime(new Date());
            }else if(couponScheme.getPlanState()==1){
                couponScheme.setPlanOnlineTime(new Date());
            }else if(couponScheme.getPlanState()==0){
                CouponScheme nowCouponScheme = couponSchemeServiceRead.findCouponSchemeById(couponScheme.getPlanId());
                if(nowCouponScheme.getPlanState()==1){
                    return "fail";
                }
            }
        }
        couponSchemeServiceWrite.updateCouponScheme(couponScheme);
        return "success";
    }

    /**
     * 检查状态
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("checkState")
    public @ResponseBody String checkState(Long planId) throws Exception{
        CouponScheme CouponScheme = couponSchemeServiceRead.findCouponSchemeById(planId);
        if(CouponScheme.getPlanState()==1){
            return "fail";
        }
        return "success";
    }


    //骑行券方案详情面
    @RequestMapping("detail")
    public String detail(Model model,Long couponSchemeId) throws Exception{
        return "detail/coupon_scheme_detail";
    }


    private void modelInit(Model model,String actionName,Long couponSchemeId) throws Exception{
        List<Coupon> couponList = couponListServiceRead.findAllCoupon();
        CouponScheme couponScheme = new CouponScheme();
        if(couponSchemeId!=null){
            couponScheme = couponSchemeServiceRead.findUnionById(couponSchemeId);
        }
        model.addAttribute("actionName",actionName);
        model.addAttribute("couponScheme",couponScheme);
        model.addAttribute("couponList",couponList);
        model.addAttribute("curCoupon",couponScheme.getCoupon());
    }
}
