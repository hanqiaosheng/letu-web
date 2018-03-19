package org.controller.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.component.AppConfig;
import org.entity.dto.City;
import org.entity.dto.Province;
import org.entity.dto.ScenicSpot;
import org.service.cms.read.CityServiceRead;
import org.service.cms.read.ProvinceServiceRead;
import org.service.cms.read.ScenicSpotServiceRead;
import org.service.cms.write.ScenicSpotServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.PageUtil;
import org.util.SplitUtil;
import org.util.UploadImageUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/scenicspot")
public class ScenicSpotController {

	@Resource
	AppConfig appConfig;
	@Resource
	ScenicSpotServiceRead scenicSpotServiceRead;
	@Resource
	CityServiceRead cityServiceRead;
	@Resource
	ScenicSpotServiceWrite scenicSpotServiceWrite;
	@Resource
	ProvinceServiceRead provinceServiceRead;


	/**
	 * 景点列表
	 * @param model
	 * @param pageIndex
	 * @param scenicSpot
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sceniclist")
	public String sceniclist(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, ScenicSpot scenicSpot,Long cityId,String scenicName,Long provinceId)
			throws Exception {
		List<Long> cityIds = new ArrayList<Long>();
		if(null!=provinceId&&-1!=provinceId){
			List<City> cities = cityServiceRead.findByProvinceId(provinceId);
			for(City c : cities){
				cityIds.add(c.getCityId());
			}
		}
		List<ScenicSpot> scenicSpotList = scenicSpotServiceRead.findScenicByCondition(pageIndex, appConfig.getPage_size_web(), cityId, scenicName,cityIds);
		for(ScenicSpot ss : scenicSpotList){
			City city = cityServiceRead.findById(ss.getScenicSpotCityId());
			Province province = provinceServiceRead.findById(city.getCityOfProvinceId());
			ss.setCityName(city.getCityName());
			ss.setProvinceName(province.getProvinceName());
		}
		Integer totalPage = 1;
		Integer totalCount = scenicSpotServiceRead.countScenicByCondition(cityId, scenicName,cityIds);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		List<City> cityList = cityServiceRead.findAll();
		List<Province> provinceList = provinceServiceRead.findAll();
		model.addAttribute("cityList", cityList);
		model.addAttribute("provinceList", provinceList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("scenicSpotList", scenicSpotList);
		model.addAttribute("cityId", cityId);
		model.addAttribute("provinceId", provinceId);
		model.addAttribute("scenicName", scenicName);
		return "scenicspot/scenic_spot_list";
	}
	
	
	/**
	 * 景区/骑游攻略详情
	 * @param model
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String bannerDetail(Model model, Long scenicSpotId) throws Exception {
		ScenicSpot scenicSpot = scenicSpotServiceRead.findById(scenicSpotId);
		City city = cityServiceRead.findById(scenicSpot.getScenicSpotCityId());
		model.addAttribute("city", city);
		model.addAttribute("scenicSpot", scenicSpot);
		return "scenicspot/scenic_spot_detail";
	}

	/**
	 * 跳转添加页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addJsp")
	public String addJsp(Model model) throws Exception {
		List<City> cityList = cityServiceRead.findAll();
		model.addAttribute("cityList", cityList);
		return "scenicspot/scenic_spot_add";
	}

	/**
	 * 新增
	 * @param banner
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String addBanner(ScenicSpot scenicSpot, String fileStr,String detailFileStr,String lngLat) throws Exception {
		scenicSpot.setScenicSpotCreateTime(new Date());
		if(null!=lngLat&&!lngLat.equals("")){
			String[] str = SplitUtil.toSplit(lngLat);
			scenicSpot.setScenicSpotLng(Double.valueOf(str[0]));
			scenicSpot.setScenicSpotLat(Double.valueOf(str[1]));
		}
		if(null!=scenicSpot.getScenicSpotTag()){
			String tag = scenicSpot.getScenicSpotTag().replace("，",",");
			scenicSpot.setScenicSpotTag(tag);
		}
		if (null!=fileStr&&!fileStr.equals("")) {
			String imgurl = UploadImageUtil.uploadStr(fileStr,appConfig.getUpload_path(),"scenic");
			scenicSpot.setScenicSpotImage("upload/"+imgurl);
		}
		if (null!=detailFileStr&&!detailFileStr.equals("")) {
			String imgurl = UploadImageUtil.uploadStr(detailFileStr,appConfig.getUpload_path(),"scenicDetail");
			scenicSpot.setScenicSpotDetailImage("upload/"+imgurl);
		}
	    scenicSpotServiceWrite.add(scenicSpot);
		return "redirect:sceniclist.action";
	}
	
	/**
	 * 编辑页面
	 * @param bannerId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editJsp")
	public String editJsp(Long scenicSpotId,Model model) throws Exception {
		ScenicSpot scenicSpot = scenicSpotServiceRead.findById(scenicSpotId);
		String latLng = scenicSpot.getScenicSpotLng()+","+scenicSpot.getScenicSpotLat();
		List<City> cityList = cityServiceRead.findAll();
		model.addAttribute("cityList", cityList);
		model.addAttribute("scenicSpot", scenicSpot);
		model.addAttribute("latLng", latLng);
		return "scenicspot/scenic_spot_edit";
	}
	
	/**
	 * 景点编辑
	 * @param banner
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	public String edit(ScenicSpot scenicSpot, String fileStr,String detailFileStr) throws Exception {
		if (null!=fileStr&&!fileStr.equals("")) {
			String imgurl = UploadImageUtil.uploadStr(fileStr,appConfig.getUpload_path(),"scenic");
			scenicSpot.setScenicSpotImage("upload/"+imgurl);
		}
		if (null!=detailFileStr&&!detailFileStr.equals("")) {
			String imgurl = UploadImageUtil.uploadStr(detailFileStr,appConfig.getUpload_path(),"scenicDetail");
			scenicSpot.setScenicSpotDetailImage("upload/"+imgurl);
		}
		if(null!=scenicSpot.getScenicSpotTag()){
			String tag = scenicSpot.getScenicSpotTag().replace("，",",");
			scenicSpot.setScenicSpotTag(tag);
		}
		scenicSpot.setScenicSpotUpdateTime(new Date());
		scenicSpotServiceWrite.update(scenicSpot);
		return "redirect:sceniclist.action";
	}
	
	/**
	 * 景点上线/下线/删除
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateState")
	public @ResponseBody String updateState(Long scenicSpotId,Integer state) throws Exception{
		ScenicSpot scenicSpot = scenicSpotServiceRead.findById(scenicSpotId);
		if(state==-1){
			if(scenicSpot.getScenicSpotState()==2){
				return "fail";
			}
		}
		scenicSpot.setScenicSpotState(state);
		scenicSpotServiceWrite.update(scenicSpot);
		return "success";
	}
	
	
	/**
	 * 景点检验
	 * @param hotWordId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkState")
	public @ResponseBody String checkState(Long scenicSpotId) throws Exception{
		ScenicSpot scenicSpot = scenicSpotServiceRead.findById(scenicSpotId);
		if(scenicSpot.getScenicSpotState()==2){
			return "fail";
		}
		return "success";
	}

}
