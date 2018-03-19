package org.controller.weixin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.entity.dto.Bike;
import org.entity.dto.LatLng;
import org.entity.dto.Models;
import org.entity.dto.RentPrice;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.service.weixin.read.BikeWxServiceRead;
import org.service.weixin.read.BlockWxServiceRead;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
@RequestMapping(value = "/bike", method = RequestMethod.POST)
public class BikeWxController {

	@Resource
	BikeWxServiceRead bikeWxServiceRead;
	@Resource
	BlockWxServiceRead blockWxServiceRead;
	@Resource
	ModelsWxServiceRead modelsWxServiceRead;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	RentPriceWxServiceRead rentPriceWxServiceRead;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(BikeWxController.class);

	/**
	 * 寻找车辆
	 * @param latLng
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findBikes", method = RequestMethod.POST)
	public @ResponseBody List<LatLng> findBikes(String latLng) throws Exception {
		List<String> blockCodes = BlockUtil.getBlockCodeFor9(latLng);
		List<Long> bIds = blockWxServiceRead.findBlockIdsByCodes(blockCodes);
		List<LatLng> bikeList=new ArrayList<LatLng>();
		if(null!=bIds&&0!=bIds.size()){
			bikeList = bikeWxServiceRead.findByBlockIds(bIds);
		}
		return bikeList;
	}

	
	/**
	 * 获取车型
	 * fromFlag 1 app  2wechat 3small
	 * @param bikeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBikeModels", method = RequestMethod.POST)
	public @ResponseBody Models getBikeModels(@RequestHeader HttpHeaders header,String bikeCode,HttpSession session) throws Exception {
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
		User nowuser = userServiceWeixinRead.findByUId(userLogin.getUserId());
		Bike bike = bikeWxServiceRead.findByBikeCode(bikeCode);
		Models models = modelsWxServiceRead.findModelsById(bike.getBikeModelsId());
		if(null!=models){
			RentPrice price = null;
			if(null!=nowuser.getUserChannelId()){
				if(nowuser.getUserChannelId().equals(models.getModelsChannelId())){
					if(models.getModelsRentType()==1){
						price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
					}else if(models.getModelsRentType()==2){
						price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 2);//会员计费
					}
					
				}else{
					price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
				}
			}else{
				price = rentPriceWxServiceRead.findDelPrice(models.getModelsId(), 1);//游客计费
			}
			
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
				models.setModelRentPrice(price);
			}
				
		}
		return models;
	}
	
}
