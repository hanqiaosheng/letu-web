package org.controller.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.component.AppConfig;
import org.entity.dto.Banner;
import org.entity.dto.City;
import org.entity.dto.Province;
import org.service.cms.read.BannerServiceRead;
import org.service.cms.read.CityServiceRead;
import org.service.cms.read.ProvinceServiceRead;
import org.service.cms.write.BannerServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.PageUtil;
import org.util.UploadImageUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/banner")
public class BannerController {

	@Resource
	AppConfig appConfig;
	@Resource
	BannerServiceRead bannerServiceRead;
	@Resource
	BannerServiceWrite bannerServiceWrite;
	@Resource
	CityServiceRead cityServiceRead;
	@Resource
	ProvinceServiceRead provinceServiceRead;


	/**
	 * 横幅列表
	 * @param model
	 * @param pageIndex
	 * @param banner
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, Banner banner,Long provinceId,Long cityId,String bannerName)
			throws Exception {
		List<Long> cityIds = new ArrayList<Long>();
		if(null!=provinceId&&-1!=provinceId){
			List<City> cities = cityServiceRead.findByProvinceId(provinceId);
			for(City c : cities){
				cityIds.add(c.getCityId());
			}
		}
		List<Banner> bannerList = bannerServiceRead.findByCondition(pageIndex, appConfig.getPage_size_web(), cityIds, cityId, bannerName);
		for(Banner b : bannerList){
			City city = cityServiceRead.findById(b.getBannerCityId());
			Province province = provinceServiceRead.findById(city.getCityOfProvinceId());
			b.setCityName(city.getCityName());
			b.setProvinceName(province.getProvinceName());
		}
		Integer totalPage = 1;
		Integer totalCount = bannerServiceRead.countByCondition(cityIds, cityId, bannerName);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		List<City> cityList = cityServiceRead.findAll();
		List<Province> provinceList = provinceServiceRead.findAll();
		model.addAttribute("cityList", cityList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("bannerList", bannerList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("provinceId", provinceId);
		model.addAttribute("cityId", cityId);
		model.addAttribute("bannerName", bannerName);
		return "banner/banner_list";
	}

	
	/**
	 * 横幅详情
	 * @param model
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String bannerDetail(Model model, Long bannerId) throws Exception {
		Banner banner = bannerServiceRead.findById(bannerId);
		City city = cityServiceRead.findById(banner.getBannerCityId());
		Province province = provinceServiceRead.findById(city.getCityOfProvinceId());
		model.addAttribute("city", city);
		model.addAttribute("province", province);
		model.addAttribute("banner", banner);
		return "banner/banner_detail";
	}

	/**
	 * 跳转添加页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addJsp")
	public String addJsp(Model model) throws Exception {
		List<City> cityList = cityServiceRead.findAll();
		List<Province> provinceList = provinceServiceRead.findAll();
		model.addAttribute("cityList", cityList);
		model.addAttribute("provinceList", provinceList);
		return "banner/banner_add";
	}

	/**
	 * 新增
	 * @param banner
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String addBanner(Banner banner, String bannerImg) throws Exception {
	    banner.setBannerCreateTime(new Date());
	    if (null!=bannerImg&&!bannerImg.equals("")) {
			String imgurl = UploadImageUtil.uploadStr(bannerImg,appConfig.getUpload_path(),"banner");
			 banner.setBannerImageUrl("upload/"+imgurl);
		}
	    bannerServiceWrite.add(banner);
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
	public String editJsp(Long bannerId,Model model) throws Exception {
		Banner banner = bannerServiceRead.findById(bannerId);
		List<City> cityList = cityServiceRead.findAll();
		List<Province> provinceList = provinceServiceRead.findAll();
		model.addAttribute("cityList", cityList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("banner", banner);
		return "banner/banner_edit";
	}
	
	/**
	 * 横幅编辑
	 * @param banner
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editBanner")
	public String editBanner(Banner banner, String bannerImg) throws Exception {
		if (null!=bannerImg&&!bannerImg.equals("")) {
			String imgurl = UploadImageUtil.uploadStr(bannerImg,appConfig.getUpload_path(),"banner");
			banner.setBannerImageUrl("upload/"+imgurl);
		}
		banner.setBannerUpdateTime(new Date());
		bannerServiceWrite.update(banner);
		return "redirect:list.action";
	}
	
	/**
	 * 横幅删除
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete")
	public @ResponseBody String delete(Long bannerId) throws Exception{
		Banner banner = bannerServiceRead.findById(bannerId);
		banner.setBannerState(-1);
		bannerServiceWrite.update(banner);
		return "success";
	}
	

}
