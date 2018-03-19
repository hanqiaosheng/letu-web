package org.controller.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.entity.dto.Bike;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.FixedReturn;
import org.entity.dto.LatLng;
import org.entity.dto.Models;
import org.entity.dto.RentPrice;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.service.weixin.read.BikeRentInfoWxServiceRead;
import org.service.weixin.read.BikeWxServiceRead;
import org.service.weixin.read.BlockWxServiceRead;
import org.service.weixin.read.FixedReturnWxServiceRead;
import org.service.weixin.read.ModelsWxServiceRead;
import org.service.weixin.read.RentPriceWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.BlockUtil;
import org.util.MessageUtil;
import org.util.SplitUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping(value = "/fixedReturn", method = RequestMethod.POST)
public class FixedReturnWxController {

	@Resource
	FixedReturnWxServiceRead fixedReturnWxServiceRead;
	@Resource
	ModelsWxServiceRead modelsWxServiceRead;
	@Resource
	BlockWxServiceRead blockWxServiceRead;
	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;
	@Resource
	BikeWxServiceRead bikeWxServiceRead;
	@Resource
	RentPriceWxServiceRead rentPriceWxServiceRead;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(FixedReturnWxController.class);

	/**
	 * 查询站点信息
	 * @param fixedReturnId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fixedReturnInfos", method = RequestMethod.POST)
	public @ResponseBody MessageUtil fixedReturnInfos(@RequestHeader HttpHeaders header,Long fixedReturnId,HttpSession session) throws Exception {
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		if("3".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
		}else if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
		}
		if(null==userLogin){
			messageUtil.setCode(2);
			messageUtil.setMessage("未登录");
			return messageUtil;
		}
		User nowUser = userServiceWeixinRead.findByUId(userLogin.getUserId());
		loggers.info("用户账号为"+nowUser.getUserTel());
		loggers.info("用户渠道为"+nowUser.getUserChannelId());
		Map<String, Object> data = new HashMap<String, Object>();
		FixedReturn fixedReturn = fixedReturnWxServiceRead.findByFixedId(fixedReturnId);
		if(null!=fixedReturn.getFixedReturnModelsId()&&!fixedReturn.getFixedReturnModelsId().equals("")){
			List<Models> modelsList = new ArrayList<Models>();
			String[] split = SplitUtil.toSplit(fixedReturn.getFixedReturnModelsId());
			for(int i=0;i<split.length;i++){
				Models models = modelsWxServiceRead.findModelsById(Long.valueOf(split[i]));
				if(null!=models){
					if(null!=models.getModelsChannelId()){
						loggers.info("车型渠道为"+models.getModelsChannelId());
						if(models.getModelsChannelId().equals(nowUser.getUserChannelId())){
							List<RentPrice> prices = rentPriceWxServiceRead.findRentPriceByModelsId(models.getModelsId());
							if(prices.size()>0){
								for(RentPrice rp : prices){
									List<JSONObject> strList = new ArrayList<JSONObject>();//租赁费用列表
									JSONArray jsonArray = JSONArray.fromObject(rp.getRentPrice());
									for (int j = 0; j < jsonArray.size(); j++) {
										JSONObject jsonObject = (JSONObject) jsonArray.get(j);
										if(jsonObject.optDouble("fromTime",-1)!=-1){
											strList.add(jsonObject);
										}else{
											rp.setLastPrice(jsonObject.getDouble("rentPrice"));//最后时间段
										}
									}
									rp.setPriceList(strList);
								}
								models.setModelRentPriceList(prices);
								modelsList.add(models);
							}
						}else{
							RentPrice price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
							findRentPrice(price,models,modelsList);
						}
					}else{
						RentPrice price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
						findRentPrice(price,models,modelsList);
					}
					 
					 
					  		 		
				}
			}
			fixedReturn.setModels(modelsList);
		}
		loggers.info("查询站点信息成功");
		data.put("fixedReturn", fixedReturn);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		messageUtil.setData(data);
		return messageUtil;
	}
	
	//查询站点
	@RequestMapping(value = "/findFixeds", method = RequestMethod.POST)
	public @ResponseBody MessageUtil findFixeds(String latLng) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		List<String> blockCodes = BlockUtil.getBlockCodeFor9(latLng);
		//List<Long> bIds = blockWxServiceRead.findBlockIdsByCodes(blockCodes);
		List<LatLng> fixedList = new ArrayList<LatLng>();
		if(null!=blockCodes&&0!=blockCodes.size()){
			fixedList = fixedReturnWxServiceRead.findByBlockIds(blockCodes);
		}
		data.put("fixedList", fixedList);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	//当前用车查询站点
	@RequestMapping(value = "/findFixedsNow", method = RequestMethod.POST)
	public @ResponseBody List<LatLng> findFixedsNow(String latLng,Long rentId) throws Exception {
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentId);
		Bike bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
		Models models = modelsWxServiceRead.findModelsById(bike.getBikeModelsId());
		List<Long> fixedReturnIds = new ArrayList<Long>();
		if(null!=models.getModelsFixedReturn()&&!models.getModelsFixedReturn().equals("")){
			String[] split = SplitUtil.toSplit(models.getModelsFixedReturn());
			for(int i=0;i<split.length;i++){
				FixedReturn fixedReturn = fixedReturnWxServiceRead.findByFixedId(Long.valueOf(split[i]));
				fixedReturnIds.add(fixedReturn.getFixedReturnId());
			}
			
		}
		List<String> blockCodes = BlockUtil.getBlockCodeFor9(latLng);
		//List<Long> bIds = blockWxServiceRead.findBlockIdsByCodes(blockCodes);
		List<LatLng> fixedList = new ArrayList<LatLng>();
		if(null!=blockCodes&&0!=blockCodes.size()){
			if(fixedReturnIds.size()>0){
				fixedList = fixedReturnWxServiceRead.findByBlockIdsAndFixed(blockCodes,fixedReturnIds);
			}
		}
		return fixedList;
	}
	
	//当前用车查询所有站点
	@RequestMapping(value = "/findAllFixedsNow", method = RequestMethod.POST)
	public @ResponseBody MessageUtil findFixedsNow(Long rentId) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentId);
		Bike bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
		Models models = modelsWxServiceRead.findModelsById(bike.getBikeModelsId());
		List<Long> fixedReturnIds = new ArrayList<Long>();
		if(null!=models.getModelsFixedReturn()&&!models.getModelsFixedReturn().equals("")){
			String[] split = SplitUtil.toSplit(models.getModelsFixedReturn());
			for(int i=0;i<split.length;i++){
				FixedReturn fixedReturn = fixedReturnWxServiceRead.findByFixedId(Long.valueOf(split[i]));
				fixedReturnIds.add(fixedReturn.getFixedReturnId());
			}
			
		}
		List<LatLng> fixedList = new ArrayList<LatLng>();
		if(fixedReturnIds.size()>0){
			fixedList = fixedReturnWxServiceRead.findByFixed(fixedReturnIds);
		}
		data.put("fixedList", fixedList);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}	
	
	//查询租赁价格
	public void findRentPrice(RentPrice price,Models models,List<Models> modelsList) throws Exception{
		if(null!=price){
			 List<JSONObject> strList = new ArrayList<JSONObject>();//租赁费用列表
			 JSONArray jsonArray = JSONArray.fromObject(price.getRentPrice());//将字符串转化为jsonArray
			 for (int j = 0; j < jsonArray.size(); j++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(j);
				if(jsonObject.optDouble("fromTime",-1)!=-1){
					strList.add(jsonObject);
				}else{
					price.setLastPrice(jsonObject.getDouble("rentPrice"));//最后时间段
				}
			 }
			 price.setPriceList(strList);
			 List<RentPrice> rentpriceList = new ArrayList<RentPrice>();
			 rentpriceList.add(price);
			 models.setModelRentPriceList(rentpriceList);
			 modelsList.add(models);
		 }
	}

}
