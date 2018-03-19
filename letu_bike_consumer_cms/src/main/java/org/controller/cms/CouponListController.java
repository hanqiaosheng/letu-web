package org.controller.cms;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.xpath.operations.Mod;
import org.component.AppConfig;
import org.entity.dto.*;
import org.service.cms.read.*;
import org.service.cms.write.CouponListServiceWrite;
import org.service.cms.write.GuideGroupServiceWrite;
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
 * 券列表controller
 */
@Controller
@Scope("prototype")
@RequestMapping("cms/couponList")
public class CouponListController {
    @Resource
    CouponListServiceRead couponListServiceRead;
    @Resource
    CouponListServiceWrite CouponListServiceWrite;
    @Resource
    CouponSchemeServiceRead couponSchemeServiceRead;
    @Resource
    AppConfig AppConfig;

    /**
     * 骑行券列表
     * @param
     * @param pageIndex
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("list")
    public String list(@RequestParam(defaultValue = "1") Integer pageIndex, Model model,String couponName) throws Exception{
        List<Coupon> couponList  = couponListServiceRead.findAll(pageIndex,AppConfig.getPage_size_web(),couponName);
        Integer totalCount = couponListServiceRead.countAll(couponName);
        Integer totalPage = PageUtil.getTotalPage(totalCount);
        if(0==totalPage){
            totalPage =1;
        }
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("couponList", couponList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("couponName", couponName);
        return "coupon_list";
    }

    //跳转添加骑行券页面
    @RequestMapping("addJsp")
    public String addJsp(Model model) throws Exception{
        modelInit(model,"add");
        return "detail/coupon_add";
    }

    //添加骑行券
    @RequestMapping("add")
    public String add(Coupon coupon) throws Exception{
        coupon.setCouponCreateTime(new Date());
        CouponListServiceWrite.addCoupon(coupon);
        return "redirect:list.action";
    }


    //跳转编辑骑行券页面
    @RequestMapping("editJsp")
    public String editJsp(Model model,Long couponId) throws Exception{
        modelInit(model,"edit");
        return "detail/coupon_add";
    }

    //修改骑行券
    @RequestMapping("edit")
    public String edit(Coupon coupon) throws Exception{
        CouponListServiceWrite.updateCoupon(coupon);
        return "redirect:list.action";
    }

    /**
     * 详情
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("detail")
    public String detail(Long couponId,Model model) throws Exception{
        Coupon Coupon = couponListServiceRead.findById(couponId);
        model.addAttribute("Coupon", Coupon);
        return "detail/coupon_detail";
    }

    /**
     * 软删除骑行券
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("delCoupon")
    public @ResponseBody String delCoupon(Long CouponId) throws Exception{
        List<CouponScheme> CouponSchemes = couponSchemeServiceRead.findAllPlan();

        Coupon Coupon = couponListServiceRead.findById(CouponId);
        Coupon.setCouponState(0);//删除
        CouponListServiceWrite.updateCoupon(Coupon);
        return "success";
    }


    /**
     * 检验骑行券
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping("checkCoupon")
    public @ResponseBody String checkCoupon(Long couponId) throws Exception{
        List<CouponScheme> couponSchemes = couponSchemeServiceRead.findAllOnline();
        return "success";
    }


    private void modelInit(Model model,String actionName){
        Coupon coupon = new Coupon();
        model.addAttribute("actionName",actionName);
        model.addAttribute("coupon",coupon);
    }
}
