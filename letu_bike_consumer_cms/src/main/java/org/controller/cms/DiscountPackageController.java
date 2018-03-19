package org.controller.cms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.DiscountPackage;
import org.entity.dto.ScenicSpot;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.DiscountPackageServiceRead;
import org.service.cms.read.ScenicSpotServiceRead;
import org.service.cms.write.DiscountPackageServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/package")
public class DiscountPackageController {

	@Resource
	AppConfig appConfig;
	@Resource
	DiscountPackageServiceRead discountPackageServiceRead;
	@Resource
	DiscountPackageServiceWrite discountPackageServiceWrite;
	@Resource
	ChannelServiceRead channelServiceRead;
	@Resource
	ScenicSpotServiceRead scenicSpotServiceRead;


	/**
	 * 套餐列表
	 * @param model
	 * @param pageIndex
	 * @param banner
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, DiscountPackage discountPackage,String packageName,Integer state,Long scenicId)
			throws Exception {
		List<DiscountPackage> packageList = discountPackageServiceRead.findByCondition(pageIndex, appConfig.getPage_size_web(),packageName,state,scenicId);
		for(DiscountPackage dp : packageList){
			ScenicSpot scenicSpot = scenicSpotServiceRead.findById(dp.getDiscountPackageScenicSpotId());
			dp.setScenicName(scenicSpot.getScenicSpotName());
		}
		Integer totalPage = 1;
		Integer totalCount = discountPackageServiceRead.countByCondition(packageName,state,scenicId);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		List<ScenicSpot> scenicSpotList = scenicSpotServiceRead.findAllScenic();
		model.addAttribute("packageList", packageList);
		model.addAttribute("scenicSpotList", scenicSpotList);
		model.addAttribute("state", state);
		model.addAttribute("scenicId", scenicId);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("packageName", packageName);
		return "discountpackage/package_list";
	}

	
	/**
	 * 套餐详情
	 * @param model
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String detail(Model model, Long packageId) throws Exception {
		DiscountPackage discountPackage = discountPackageServiceRead.findById(packageId);
		ScenicSpot scenicSpot = scenicSpotServiceRead.findById(discountPackage.getDiscountPackageScenicSpotId());
		model.addAttribute("discountPackage", discountPackage);
		model.addAttribute("scenicSpot", scenicSpot);
		return "discountpackage/package_detail";
	}

	/**
	 * 跳转添加页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addJsp")
	public String addJsp(Model model,HttpSession session) throws Exception {
		List<ScenicSpot> scenicSpotList = scenicSpotServiceRead.findAllScenic();
	    model.addAttribute("scenicSpotList", scenicSpotList);
		return "discountpackage/package_add";
	}

	/**
	 * 新增
	 * @param banner
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String add(DiscountPackage discountPackage) throws Exception {
		discountPackage.setDiscountPackageCreateTime(new Date());
		if(null!=discountPackage.getDiscountPackageTag()){
			String tag = discountPackage.getDiscountPackageTag().replace("，",",");
			discountPackage.setDiscountPackageTag(tag);
		}
	    discountPackageServiceWrite.add(discountPackage);
		return "redirect:list.action";
	}
	
	/**
	 * 编辑页面
	 * @param bannerId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editJsp")
	public String editJsp(Long packageId,Model model) throws Exception {
		DiscountPackage discountPackage = discountPackageServiceRead.findById(packageId);
		ScenicSpot scenicSpot = scenicSpotServiceRead.findById(discountPackage.getDiscountPackageScenicSpotId());
		model.addAttribute("discountPackage", discountPackage);
		model.addAttribute("scenicSpot", scenicSpot);
		return "discountpackage/package_edit";
	}
	
	/**
	 * 编辑
	 * @param banner
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	public String edit(DiscountPackage discountPackage) throws Exception {
		discountPackage.setDiscountPackageUpdateTime(new Date());
		if(null!=discountPackage.getDiscountPackageTag()){
			String tag = discountPackage.getDiscountPackageTag().replace("，",",");
			discountPackage.setDiscountPackageTag(tag);
		}
		discountPackageServiceWrite.update(discountPackage);
		return "redirect:list.action";
	}
	
	/**
	 * 删除
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateState")
	public @ResponseBody String updateState(Long packageId,Integer state) throws Exception{
		DiscountPackage discountPackage = discountPackageServiceRead.findById(packageId);
		if(state==-1){
			if(discountPackage.getDiscountPackageState()==2){
				return "fail";
			}
		}
		discountPackage.setDiscountPackageState(state);
		discountPackageServiceWrite.update(discountPackage);
		return "success";
	}
	
	/**
	 * 检验
	 * @param hotWordId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkState")
	public @ResponseBody String checkState(Long packageId) throws Exception{
		DiscountPackage discountPackage = discountPackageServiceRead.findById(packageId);
		if(discountPackage.getDiscountPackageState()==2){
			return "fail";
		}
		return "success";
	}

}
