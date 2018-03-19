package org.controller.cms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.component.AppConfig;
import org.entity.dto.InsurancePrice;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.InsurancePriceServiceRead;
import org.service.cms.write.InsurancePriceServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.PageUtil;
import org.util.redis.RedisService;

@Controller
@Scope("prototype")
@RequestMapping("cms/insurancePrice")
public class InsurancePriceController {

	@Resource
	AppConfig appConfig;
	@Resource
	AdminServiceRead adminServiceRead;
	@Resource
	InsurancePriceServiceRead insurancePriceServiceRead;
	@Resource
	InsurancePriceServiceWrite insurancePriceServiceWrite;
	@Resource
	RedisService redisService;


	/**
	 * 保险费用列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param sysMsg
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model, @RequestParam(defaultValue = "1") Integer pageIndex,String name)throws Exception {
		List<InsurancePrice> insurancePriceList = insurancePriceServiceRead.findPriceByCondition(pageIndex,appConfig.getPage_size_web(),name);
		Integer totalPage = 1;
		Integer totalCount = insurancePriceServiceRead.countAllPrice(name);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("insurancePriceList", insurancePriceList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("name", name);
		//model.addAttribute("sysMsg", sysMsg);
		return "insurance_price_list";
	}

	/**
	 * 跳转添加页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addJsp")
	public String addJsp(Model model)throws Exception{
		return "detail/insurance_price_add";
	}
	
	/**
	 * 添加
	 * @param insurancePrice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String add(InsurancePrice insurancePrice)throws Exception{
		insurancePrice.setInUpdateTime(new Date());
		insurancePriceServiceWrite.add(insurancePrice);
		return "redirect:list.action";
	}
	
	/**
	 * 跳转编辑页面
	 * @param model
	 * @param insurancePriceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editJsp")
	public String editJsp(Model model,Long insurancePriceId)throws Exception{
		InsurancePrice insurancePrice = insurancePriceServiceRead.findById(insurancePriceId);
		model.addAttribute("insurancePrice", insurancePrice);
		return "detail/insurance_price_edit";
	}
	
	/**
	 * 编辑
	 * @param insurancePrice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	public String edit(InsurancePrice insurancePrice)throws Exception{
		insurancePrice.setInUpdateTime(new Date());
		insurancePriceServiceWrite.update(insurancePrice);
		return "redirect:list.action";
	}
	
	/**
	 * 删除
	 * @param insurancePrice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete")
	public @ResponseBody String delete(InsurancePrice insurancePrice)throws Exception{
		insurancePrice.setInPriceState(0);//删除
		insurancePriceServiceWrite.update(insurancePrice);
		return "success";
	}
	
	/** 查询
	 * @param insurancePrice
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getInPrice")
	public @ResponseBody InsurancePrice getInPrice(Long inPriceId)throws Exception{
		InsurancePrice insurancePrice = insurancePriceServiceRead.findById(inPriceId);
		return insurancePrice;
	}
	

}
