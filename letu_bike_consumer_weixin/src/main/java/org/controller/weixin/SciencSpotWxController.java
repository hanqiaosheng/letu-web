package org.controller.weixin;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.City;
import org.entity.dto.DiscountPackage;
import org.entity.dto.ScenicSpot;
import org.service.weixin.read.BannerWxServiceRead;
import org.service.weixin.read.CityWxServiceRead;
import org.service.weixin.read.DiscountPackageWxServiceRead;
import org.service.weixin.read.ScenicTicketWxServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DistanceUtil;
import org.util.MessageUtil;
import org.util.PageUtil;
@Controller
@Scope("prototype")
@RequestMapping(value = "/scenicspot", method = RequestMethod.POST)
public class SciencSpotWxController {

	
	@Resource
	ScenicTicketWxServiceRead scenicTicketWxServiceRead;
	@Resource
	AppConfig AppConfig;
	@Resource
	BannerWxServiceRead bannerWxServiceRead;
	@Resource
	DiscountPackageWxServiceRead discountPackageWxServiceRead;
	@Resource
	CityWxServiceRead cityWxServiceRead;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(SciencSpotWxController.class);
		
	/**
	 * 首页景点列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scenicList", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil scenicList(@RequestParam(defaultValue = "1")Integer pageIndex,String scenicName,@RequestParam(defaultValue = "110")String cityCode,Double lng,Double lat) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		loggers.info("scenicName为"+scenicName);
		loggers.info("cityCode为"+cityCode);
		loggers.info("lng为"+lng);
		loggers.info("lat为"+lat);
	    //查询所有景点
		List<ScenicSpot> scenicSpotList = scenicTicketWxServiceRead.findAllTicket(pageIndex,AppConfig.getPage_size_weixin(),cityCode);
		for(ScenicSpot ss : scenicSpotList){
			DiscountPackage discountPackage = discountPackageWxServiceRead.findByScenicId(ss.getScenicSpotId());
			if(null!=discountPackage){
				BigDecimal ticketPrice = discountPackage.getDiscountPackagePreferentialPrice();
				ss.setTicketPrice(ticketPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
			ScenicSpot scenicSpot = scenicTicketWxServiceRead.findByScenicId(ss.getScenicSpotId());
			if(null!=lng&&null!=lat){
				Double distance = DistanceUtil.LantitudeLongitudeDist(scenicSpot.getScenicSpotLng(), scenicSpot.getScenicSpotLat(), lng, lat);
				loggers.info("distance为"+distance);
				ss.setScenicSpotDistance(distance/1000.0);
			}else{
				City city = cityWxServiceRead.findCityByCode(cityCode);
				if(null!=city.getCityCenterLat()&&null!=city.getCityCenterLng()){
					Double distance = DistanceUtil.LantitudeLongitudeDist(scenicSpot.getScenicSpotLng(), scenicSpot.getScenicSpotLat(), city.getCityCenterLng(), city.getCityCenterLat());
					ss.setScenicSpotDistance(distance/1000.0);
					loggers.info("distance为"+distance);
				}else{
					ss.setScenicSpotDistance(-1.0);
				}
			}
			ss.setScenicSpotContent(scenicSpot.getScenicSpotContent());
		}
		Integer totalCount = scenicTicketWxServiceRead.countAllTicket(cityCode);
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		data.put("pageSize", AppConfig.getPage_size_weixin());
		data.put("scenicSpotList", scenicSpotList);
		data.put("totalPage",totalPage);
		data.put("totalCount", totalCount);
		data.put("pageIndex", pageIndex);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * 首页景点/攻略详情
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scenicDetail", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil scenicDetail(@RequestParam(defaultValue = "1")Integer pageIndex,Long scenicId) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		ScenicSpot scenicSpot = scenicTicketWxServiceRead.findByScenicId(scenicId);
		List<DiscountPackage> discountPackage = discountPackageWxServiceRead.findAllByScenicId(scenicSpot.getScenicSpotId());
		Integer totalCount = discountPackageWxServiceRead.countAllByScenicId(scenicSpot.getScenicSpotId());
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		data.put("pageSize", AppConfig.getPage_size_weixin());
		data.put("discountPackage", discountPackage);
		data.put("scenicSpot", scenicSpot);
		data.put("totalPage",totalPage);
		data.put("totalCount", totalCount);
		data.put("pageIndex", pageIndex);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * 首页攻略列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/guideList", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil guideList(@RequestParam(defaultValue = "1")Integer pageIndex,String scenicName,@RequestParam(defaultValue = "110")String cityCode,Double lng,Double lat) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
	    //查询所有攻略
		List<ScenicSpot> scenicSpotList = scenicTicketWxServiceRead.findAllGuide(pageIndex,AppConfig.getPage_size_weixin(),cityCode);
		for(ScenicSpot ss : scenicSpotList){
			DiscountPackage discountPackage = discountPackageWxServiceRead.findByScenicId(ss.getScenicSpotId());
			if(null!=discountPackage){
				BigDecimal ticketPrice = discountPackage.getDiscountPackagePreferentialPrice();
				ss.setTicketPrice(ticketPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
				
			ScenicSpot scenicSpot = scenicTicketWxServiceRead.findByScenicId(ss.getScenicSpotId());
			if(null!=lng&&null!=lat){
				Double distance = DistanceUtil.LantitudeLongitudeDist(scenicSpot.getScenicSpotLng(), scenicSpot.getScenicSpotLat(), lng, lat);
				ss.setScenicSpotDistance(distance/1000.0);
			}else{
				City city = cityWxServiceRead.findCityByCode(cityCode);
				if(null!=city.getCityCenterLat()&&null!=city.getCityCenterLng()){
					Double distance = DistanceUtil.LantitudeLongitudeDist(scenicSpot.getScenicSpotLng(), scenicSpot.getScenicSpotLat(), city.getCityCenterLng(), city.getCityCenterLat());
					ss.setScenicSpotDistance(distance/1000.0);
				}else{
					ss.setScenicSpotDistance(-1.0);
				}
			}
			ss.setScenicSpotContent(scenicSpot.getScenicSpotContent());
		}
		Integer totalCount = scenicTicketWxServiceRead.countAllGuide(cityCode);
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		data.put("pageSize", AppConfig.getPage_size_weixin());
		data.put("scenicSpotList", scenicSpotList);
		data.put("totalPage",totalPage);
		data.put("totalCount", totalCount);
		data.put("pageIndex", pageIndex);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 景点搜索
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scenicSelect", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil scenicSelect(@RequestParam(defaultValue = "1")Integer pageIndex,String scenicName) throws Exception{
		loggers.info("scenicName为"+scenicName);
		Map<String,Object> data = new HashMap<String,Object>();
	    //查询所有景点
		List<ScenicSpot> scenicSpotList = scenicTicketWxServiceRead.findScenicSelect(pageIndex,AppConfig.getPage_size_weixin(),scenicName);
		for(ScenicSpot ss : scenicSpotList){
			DiscountPackage discountPackage = discountPackageWxServiceRead.findByScenicId(ss.getScenicSpotId());
			if(null!=discountPackage){
				BigDecimal ticketPrice = discountPackage.getDiscountPackagePreferentialPrice();
				ss.setTicketPrice(ticketPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		Integer totalCount = scenicTicketWxServiceRead.countScenicSelect(scenicName);
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		data.put("pageSize", AppConfig.getPage_size_weixin());
		data.put("scenicSpotList", scenicSpotList);
		data.put("totalPage",totalPage);
		data.put("totalCount", totalCount);
		data.put("pageIndex", pageIndex);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * 攻略搜索
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/guideSelect", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil guideSelect(@RequestParam(defaultValue = "1")Integer pageIndex,String scenicName) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
	    //查询所有攻略
		List<ScenicSpot> scenicSpotList = scenicTicketWxServiceRead.findGuideSelect(pageIndex,AppConfig.getPage_size_weixin(),scenicName);
		for(ScenicSpot ss : scenicSpotList){
			DiscountPackage discountPackage = discountPackageWxServiceRead.findByScenicId(ss.getScenicSpotId());
			if(null!=discountPackage){
				BigDecimal ticketPrice = discountPackage.getDiscountPackagePreferentialPrice();
				ss.setTicketPrice(ticketPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}
		Integer totalCount = scenicTicketWxServiceRead.countGuideSelect(scenicName);
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		data.put("pageSize", AppConfig.getPage_size_weixin());
		data.put("scenicSpotList", scenicSpotList);
		data.put("totalPage",totalPage);
		data.put("totalCount", totalCount);
		data.put("pageIndex", pageIndex);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}*/
	
	/**
	 * 信息查看
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scenicMsg", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil scenicSelect(Long scenicId) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		ScenicSpot scenicSpot = scenicTicketWxServiceRead.findByScenicId(scenicId);
		data.put("scenicSpot", scenicSpot);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * 详细说明
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/packageContent", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil packageContent(Long packageId) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		DiscountPackage discountPackage = discountPackageWxServiceRead.findByPackageId(packageId);
		data.put("discountPackage", discountPackage);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
}
