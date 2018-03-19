package org.controller.cms;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.entity.dto.Admin;
import org.entity.dto.Bike;
import org.entity.dto.Channel;
import org.entity.dto.FixedReturn;
import org.entity.dto.InsurancePrice;
import org.entity.dto.Models;
import org.entity.dto.OperateLog;
import org.entity.dto.RentPrice;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.FixedReturnServiceRead;
import org.service.cms.read.InsurancePriceServiceRead;
import org.service.cms.read.ModelsServiceRead;
import org.service.cms.read.RentPriceServiceRead;
import org.service.cms.write.FixedReturnServiceWrite;
import org.service.cms.write.ModelsServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.RentPriceServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.OperateUtil;
import org.util.SplitUtil;
import org.util.redis.RedisService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@Scope("prototype")
@RequestMapping("cms/models")
public class ModelsController {
	
	@Resource
	ModelsServiceRead modelsServiceRead;
	
	@Resource
	ModelsServiceWrite modelsServiceWrite;
	
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	FixedReturnServiceWrite fixedReturnServiceWrite;
	
	@Resource
	FixedReturnServiceRead fixedReturnServiceRead;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	@Resource
	InsurancePriceServiceRead insurancePriceServiceRead;
	
	@Resource
	RentPriceServiceRead rentPriceServiceRead;
	
	@Resource
	RentPriceServiceWrite rentPriceServiceWrite;
	
	@Resource
	BikeServiceRead bikeServiceRead;
	
	@Resource
	RedisService redisService;
	
	/**
	 * 车型列表
	 * @param session
	 * @param model
	 * @param pageIndex
	 * @param modelsCode
	 * @param modelsName
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modelsList")
	public String modelsList(HttpSession session,Model model,@RequestParam(defaultValue="1")Integer pageIndex,String modelsCode,String modelsName,Long channelId) throws Exception{
		Long currChannelId  = (Long) session.getAttribute("currChannelId");
		List<Models> modelsList = new ArrayList<Models>();
		List<Models> modelsList1 = new ArrayList<Models>();
		List<Channel> channelList = new ArrayList<Channel>();
		Integer totalPage = 1;
		//查渠道下的所有车型
		if(currChannelId==null){
			modelsList = modelsServiceRead.findAllModels(pageIndex, modelsCode, modelsName, channelId,null);
			totalPage = modelsServiceRead.countAllModels(modelsCode, modelsName, channelId,null);
			modelsList1 = modelsServiceRead.findAllModels(null, null, null, null,null);
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);//查所有子渠道
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			modelsList = modelsServiceRead.findAllModels(pageIndex, modelsCode, modelsName, channelId,currChannelIds);
			totalPage = modelsServiceRead.countAllModels(modelsCode, modelsName, channelId,currChannelIds);
			modelsList1 = modelsServiceRead.findAllModels(null, null, null, null,currChannelIds);//查询渠道下的所有车型
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
		}
		if(totalPage==0){
        	totalPage = 1;
        }
		for(Models m : modelsList){
			String str = "";
			if(null!=m.getModelsFixedReturn()&&!m.getModelsFixedReturn().equals("")){
				String[] splits = SplitUtil.toSplit(m.getModelsFixedReturn());
				for(int i=0;i<splits.length;i++ ){
					FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(Long.valueOf(splits[i]));
					str = str +'，' + fixedReturn.getFixedReturnName();
				}
			}
			if(!str.equals("")){
				str = str.substring(1);
			}
			m.setModelsFixedReturnName(str);
		}
		
		model.addAttribute("channelList", channelList);
		model.addAttribute("modelsList", modelsList);
		model.addAttribute("modelsList1", modelsList1);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("modelsCode", modelsCode);
		model.addAttribute("modelsName", modelsName);
		model.addAttribute("channelId", channelId);
		return "models_list";
	}
	
	//添加页面
	@RequestMapping("addModelsJsp")
	public String addModelsJsp(HttpSession session,Model model) throws Exception{
		Long currChannelId  = (Long) session.getAttribute("currChannelId");
		List<Channel> channelList = new ArrayList<Channel>();
		List<FixedReturn> fixedReturnList = new ArrayList<FixedReturn>();
		if(currChannelId==null){   //如果是管理员
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
			fixedReturnList = fixedReturnServiceRead.findAllFixed(null, null, null,null,null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
			fixedReturnList = fixedReturnServiceRead.findAllFixed(null, null, null,null,currChannelIds);
		}
		List<InsurancePrice> insurancePrices = insurancePriceServiceRead.findPriceByCondition(null, null, null);//保险费用列表
		model.addAttribute("insurancePrices", insurancePrices);		
		model.addAttribute("channelList", channelList);
		model.addAttribute("fixedReturnList", fixedReturnList);
		return "detail/models_add";
	}
	
	//添加
	@RequestMapping("addModels")
	public String addModels(Models models,HttpSession session,Long[] fixedReturnId,Double[] fromTime,Double[] toTime,Double[] rentPrice,
			Double[] fromTime2,Double[] toTime2,Double[] rentPrice2,Integer rentoption2,Integer rentoption,Integer rentFreeTime2,Integer rentFreeTime,
			Double rentPriceMax,Double rentPriceMax2) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		if(models.getModelsIsfixedPoint()==1){
			String modelsFixedReturn = "";
			if(fixedReturnId.length>0){
				Set<Long> set = new HashSet<>();  
		        for(int i=0;i<fixedReturnId.length;i++){  
		            set.add(fixedReturnId[i]);  
		        } 
		        List<Long> setList = new ArrayList<Long>(set);  
				for(int i=0;i<setList.size();i++){
					modelsFixedReturn = modelsFixedReturn+','+fixedReturnId[i];
				}
			}
			if(!modelsFixedReturn.equals("")){
				modelsFixedReturn = modelsFixedReturn.substring(1);
				models.setModelsFixedReturn(modelsFixedReturn);
			}
		}
		
		Long modelsId = modelsServiceWrite.addModels(models);
		
		
		JSONArray jsonArray = new JSONArray();
		if(null!=rentPrice&&rentPrice.length>0){
			for(int i=0;i<rentPrice.length;i++){
				JSONObject jsonObject = new JSONObject();
				if(null!=fromTime&&i<fromTime.length){
					jsonObject.put("fromTime", fromTime[i]);
					jsonObject.put("toTime", toTime[i]);
				}
				jsonObject.put("rentPrice", rentPrice[i]);
				jsonArray.add(jsonObject);//添加json
			}
		}
		RentPrice price = new RentPrice();//游客
		price.setRentPriceModelsId(modelsId);
		price.setRentFreeTime(rentFreeTime);
		price.setRentPrice(jsonArray.toString());
		price.setRentPriceOption(rentoption);
		price.setRentPriceMax(rentPriceMax);
		price.setRentPriceType(1);//游客
		rentPriceServiceWrite.addRentPrice(price);
		
		
		if(null!=rentPrice2&&rentPrice2.length>0){
			JSONArray jsonArrayVip = new JSONArray();
			for(int i=0;i<rentPrice2.length;i++){
				JSONObject jsonObject = new JSONObject();
				if(null!=fromTime2&&i<fromTime2.length){
					jsonObject.put("fromTime", fromTime2[i]);
					jsonObject.put("toTime", toTime2[i]);
				}
				jsonObject.put("rentPrice", rentPrice2[i]);
				jsonArrayVip.add(jsonObject);//添加json
			}
			RentPrice priceVip = new RentPrice();//会员
			priceVip.setRentPriceModelsId(modelsId);
			priceVip.setRentFreeTime(rentFreeTime2);
			priceVip.setRentPrice(jsonArrayVip.toString());
			priceVip.setRentPriceOption(rentoption2);
			priceVip.setRentPriceMax(rentPriceMax2);
			priceVip.setRentPriceType(2);//会员
			rentPriceServiceWrite.addRentPrice(priceVip);
		}
		
		if(models.getModelsIsfixedPoint()==1){
			if(fixedReturnId.length>0){
				Set<Long> set = new HashSet<>();  
		        for(int i=0;i<fixedReturnId.length;i++){  
		            set.add(fixedReturnId[i]);  
		        } 
		        List<Long> setList = new ArrayList<Long>(set); 
				for(int i=0;i<setList.size();i++){
					FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(fixedReturnId[i]);
					if(null!=fixedReturn.getFixedReturnModelsId()&&!fixedReturn.getFixedReturnModelsId().equals("")){
						String[] splits = SplitUtil.toSplit(fixedReturn.getFixedReturnModelsId());//分割车型str
						Integer flag = 1;//默认0不可拼接,1可拼接
						for(int j=0;j<splits.length;j++){
							if(Long.valueOf(splits[j]).equals(modelsId)){//判断是否相等
								flag = 0;
							}
						}
						if(flag==1){
							fixedReturn.setFixedReturnModelsId(fixedReturn.getFixedReturnModelsId()+","+modelsId);//可拼接车型id
						}
						
					}else{
						fixedReturn.setFixedReturnModelsId(modelsId+"");
					}
					fixedReturnServiceWrite.editFixedReturn(fixedReturn);
				}
			}
		}
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 9, null, modelsId, null);
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		return "redirect:modelsList.action";
	}
	
	//删除车型
	@RequestMapping("deleteModels")
	public @ResponseBody String deleteModels(Long modelsId,HttpSession session) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		Models models = modelsServiceRead.findModelsById(modelsId);
		List<Bike> bikeList = bikeServiceRead.findBikeByModelsId(modelsId);
		if(bikeList.size()>0){
			return "fail";
		}
		if(null!=models.getModelsFixedReturn()&&!models.getModelsFixedReturn().equals("")){
			String[] fixedReturnIds = SplitUtil.toSplit(models.getModelsFixedReturn());
			for(int k=0;k<fixedReturnIds.length;k++){
				FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(Long.valueOf(fixedReturnIds[k]));
				String modelsStr = "";
				String[] split = SplitUtil.toSplit(fixedReturn.getFixedReturnModelsId());
				List<String> list = new ArrayList<String>();
				for(int i=0;i<split.length;i++){
					list.add(split[i]);
				}
				for(int j=0;j<list.size();j++){
					if(Long.valueOf(list.get(j)).equals(models.getModelsId())){//判断是否相等
						list.remove(j);
						j--; 
					}
				}
				for(String str : list){
					modelsStr = modelsStr +","+ str;
				}
				if(!modelsStr.equals("")){
					modelsStr = modelsStr.substring(1);
				}
				fixedReturn.setFixedReturnModelsId(modelsStr);
				fixedReturnServiceWrite.editFixedReturn(fixedReturn);//更新站点车型id
			}
		}
		models.setModelsState(1);//删除
		modelsServiceWrite.deleteModelsById(models);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 11, null, modelsId, null);
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "success";
	}
	//编辑页面
	@RequestMapping("editModelsJsp")
	public String editModelsJsp(HttpSession session ,Long modelsId,Model model) throws Exception{
		Models models = modelsServiceRead.findModelsById(modelsId);
		if(null!=models.getModelsFixedReturn()&&!models.getModelsFixedReturn().equals("")){
			List<FixedReturn> fixedReturns1 = new ArrayList<FixedReturn>();
			String[] splits = SplitUtil.toSplit(models.getModelsFixedReturn());
			for(int i=0;i<splits.length;i++ ){
				FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(Long.valueOf(splits[i]));
				fixedReturns1.add(fixedReturn);
			}
			models.setFixedReturnList(fixedReturns1);
		}
		
		List<RentPrice> prices = rentPriceServiceRead.findRentPriceByModelsId(modelsId);
		if(prices.size()>0){
			for(RentPrice rp : prices){
				List<JSONObject> strList = new ArrayList<JSONObject>();//租赁费用列表
				JSONArray jsonArray = JSONArray.fromObject(rp.getRentPrice());
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					if(jsonObject.optDouble("fromTime",-1)!=-1){
						strList.add(jsonObject);
					}else{
						rp.setLastPrice(jsonObject.getDouble("rentPrice"));//最后时间段
					}
				}
				rp.setPriceList(strList);
				
			}
		}
		
		
		
		Long currChannelId  = (Long) session.getAttribute("currChannelId");
		List<Channel> channelList = new ArrayList<Channel>();
		List<FixedReturn> fixedReturnList = new ArrayList<FixedReturn>();
		if(currChannelId==null){   //如果是管理员
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
			fixedReturnList = fixedReturnServiceRead.findAllFixed(null, null, null,null,null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
			fixedReturnList = fixedReturnServiceRead.findAllFixed(null, null, null,null,currChannelIds);
		}
		List<InsurancePrice> insurancePrices = insurancePriceServiceRead.findPriceByCondition(null, null, null);//保险费用列表
		model.addAttribute("insurancePrices", insurancePrices);			
		model.addAttribute("models", models);
		model.addAttribute("prices", prices);
		model.addAttribute("channelList", channelList);
		model.addAttribute("fixedReturns", fixedReturnList);
		return "detail/models_edit";
	}
	
	//编辑
	@RequestMapping("editModels")
	public String editModels(HttpSession session,Models models,Long[] fixedReturnId,Double[] fromTime,Double[] toTime,Double[] rentPrice,
			Double[] fromTime2,Double[] toTime2,Double[] rentPrice2,Integer rentoption2,Integer rentoption,Integer rentFreeTime,Integer rentFreeTime2,
			Double rentPriceMax,Double rentPriceMax2) throws Exception{
		
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		if(models.getModelsIsfixedPoint()==1){//是站点
			String modelsFixedReturn = "";
			if(null!=fixedReturnId){
				if(fixedReturnId.length>0){
					Set<Long> set = new HashSet<>();  
			        for(int i=0;i<fixedReturnId.length;i++){  
			            set.add(fixedReturnId[i]);  
			        } 
			        List<Long> setList = new ArrayList<Long>(set); 
					for(int i=0;i<setList.size();i++){
						modelsFixedReturn = modelsFixedReturn+','+fixedReturnId[i];
					}
				}
				if(!modelsFixedReturn.equals("")){
					modelsFixedReturn = modelsFixedReturn.substring(1);
					models.setModelsFixedReturn(modelsFixedReturn);
				}
				if(fixedReturnId.length>0){
					Set<Long> set = new HashSet<>();  
			        for(int i=0;i<fixedReturnId.length;i++){  
			            set.add(fixedReturnId[i]);  
			        } 
			        List<Long> setList = new ArrayList<Long>(set); 
					for(int i=0;i<setList.size();i++){
						FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(fixedReturnId[i]);
						if(null!=fixedReturn.getFixedReturnModelsId()&&!fixedReturn.getFixedReturnModelsId().equals("")){
							String[] splits = SplitUtil.toSplit(fixedReturn.getFixedReturnModelsId());
							Integer flag = 1;//默认0可添加,1不可
							for(int j=0;j<splits.length;j++){
								if(Long.valueOf(splits[j]).equals(models.getModelsId())){
									flag = 0;
								}
							}
							if(flag==1){
								fixedReturn.setFixedReturnModelsId(fixedReturn.getFixedReturnModelsId()+","+models.getModelsId());
							}
						}else{
							fixedReturn.setFixedReturnModelsId(models.getModelsId()+"");
						}
						fixedReturnServiceWrite.editFixedReturn(fixedReturn);
					}
				}
					
			}
		}else{//不是站点
			Models nowModels = modelsServiceRead.findModelsById(models.getModelsId());
			if(null!=nowModels.getModelsFixedReturn()&&!nowModels.getModelsFixedReturn().equals("")){
				String[] fixedReturnIds = SplitUtil.toSplit(nowModels.getModelsFixedReturn());
				for(int k=0;k<fixedReturnIds.length;k++){
					FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(Long.valueOf(fixedReturnIds[k]));
					String modelsStr = "";
					String[] split = SplitUtil.toSplit(fixedReturn.getFixedReturnModelsId());
					List<String> list = new ArrayList<String>();
					for(int i=0;i<split.length;i++){
						list.add(split[i]);
					}
					for(int j=0;j<list.size();j++){
						if(Long.valueOf(list.get(j)).equals(models.getModelsId())){//判断是否相等
							list.remove(j);
							j--; 
						}
					}
					for(String str : list){
						modelsStr = modelsStr +","+ str;
					}
					if(!modelsStr.equals("")){
						modelsStr = modelsStr.substring(1);
					}
					fixedReturn.setFixedReturnModelsId(modelsStr);
					fixedReturnServiceWrite.editFixedReturn(fixedReturn);//更新站点车型id
				}
			}
			
			models.setModelsFixedReturn("");
		}
		
		modelsServiceWrite.updateModels(models);
		
		List<RentPrice> prices = rentPriceServiceRead.findRentPriceByModelsId(models.getModelsId());//费用列表
		if(prices.size()>0){
			if(prices.size()==1){
				RentPrice price = prices.get(0);
				JSONArray jsonArray = new JSONArray();
				if(null!=rentPrice&&rentPrice.length>0){
					for(int i=0;i<rentPrice.length;i++){
						JSONObject jsonObject = new JSONObject();
						if(null!=fromTime&&i<fromTime.length){
							jsonObject.put("fromTime", fromTime[i]);
							jsonObject.put("toTime", toTime[i]);
						}
						jsonObject.put("rentPrice", rentPrice[i]);
						jsonArray.add(jsonObject);//添加json
					}
				}
				price.setRentFreeTime(rentFreeTime);
				price.setRentPrice(jsonArray.toString());
				price.setRentPriceOption(rentoption);
				price.setRentPriceMax(rentPriceMax);
				price.setRentPriceType(1);//游客
				rentPriceServiceWrite.updateRentPrice(price);
				
				if(models.getModelsRentType()==2){//需要会员计费
					if(null!=rentPrice2&&rentPrice2.length>0){
						JSONArray jsonArrayVip = new JSONArray();
						for(int i=0;i<rentPrice2.length;i++){
							JSONObject jsonObject = new JSONObject();
							if(null!=fromTime2&&i<fromTime2.length){
								jsonObject.put("fromTime", fromTime2[i]);
								jsonObject.put("toTime", toTime2[i]);
							}
							jsonObject.put("rentPrice", rentPrice2[i]);
							jsonArrayVip.add(jsonObject);//添加json
						}
						RentPrice priceVip = new RentPrice();//会员
						priceVip.setRentPriceModelsId(models.getModelsId());
						priceVip.setRentFreeTime(rentFreeTime2);
						priceVip.setRentPrice(jsonArrayVip.toString());
						priceVip.setRentPriceOption(rentoption2);
						priceVip.setRentPriceType(2);//会员
						priceVip.setRentPriceMax(rentPriceMax2);
						rentPriceServiceWrite.addRentPrice(priceVip);
					}
				}
			}else{
				if(models.getModelsRentType()==1){//不需要会员计费
					RentPrice delPrice = rentPriceServiceRead.findDelPrice(models.getModelsId(),2);//查出是会员计费模式
					rentPriceServiceWrite.deleteRentPrice(delPrice);
				}else if(models.getModelsRentType()==2){//会员计费
					for(RentPrice rp:prices){
						if(rp.getRentPriceType()==1){//游客计费模式
							JSONArray jsonArray = new JSONArray();
							if(null!=rentPrice&&rentPrice.length>0){
								for(int i=0;i<rentPrice.length;i++){
									JSONObject jsonObject = new JSONObject();
									if(null!=fromTime&&i<fromTime.length){
										jsonObject.put("fromTime", fromTime[i]);
										jsonObject.put("toTime", toTime[i]);
									}
									jsonObject.put("rentPrice", rentPrice[i]);
									jsonArray.add(jsonObject);//添加json
								}
							}
							rp.setRentFreeTime(rentFreeTime);
							rp.setRentPrice(jsonArray.toString());
							rp.setRentPriceOption(rentoption);
							rp.setRentPriceMax(rentPriceMax);
							rp.setRentPriceType(1);//会员
							rentPriceServiceWrite.updateRentPrice(rp);
						}else if(rp.getRentPriceType()==2){//会员计费模式
							JSONArray jsonArrayVip = new JSONArray();
							for(int i=0;i<rentPrice2.length;i++){
								JSONObject jsonObject = new JSONObject();
								if(null!=fromTime2&&i<fromTime2.length){
									jsonObject.put("fromTime", fromTime2[i]);
									jsonObject.put("toTime", toTime2[i]);
								}
								jsonObject.put("rentPrice", rentPrice2[i]);
								jsonArrayVip.add(jsonObject);//添加json
							}
							rp.setRentFreeTime(rentFreeTime2);
							rp.setRentPrice(jsonArrayVip.toString());
							rp.setRentPriceOption(rentoption2);
							rp.setRentPriceMax(rentPriceMax2);
							rp.setRentPriceType(2);//会员
							rentPriceServiceWrite.updateRentPrice(rp);
						}
						
					}
				}
				
			}
		}
		
		
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 10, null, models.getModelsId(), null);
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "redirect:modelsList.action";
	}
	
	/**
	 * 获取渠道
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("changeChannelName")
	public @ResponseBody Channel changeChannelName(Long channelId)throws Exception{
		Channel channel = channelServiceRead.findById(channelId);
		return channel;
	}
	
	/**
	 * 删除定点
	 * @param fixedReturnId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteFixed")
	public @ResponseBody String deleteFixed(Long fixedReturnId,Models models)throws Exception{
		Models newmodels = modelsServiceRead.findModelsById(models.getModelsId());//获取models
		FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(fixedReturnId);
		String fixedStr = "";
		String[] split = SplitUtil.toSplit(newmodels.getModelsFixedReturn());//分割车型中的站点
		List<String> list = new ArrayList<String>();//新建站点list
		for(int i=0;i<split.length;i++){
			list.add(split[i]);
		}
		for(int j=0;j<list.size();j++){
			if(Long.valueOf(list.get(j)).equals(fixedReturn.getFixedReturnId())){
				list.remove(j);//移除存在的站点
				j--; 
			}
		}
		for(String str : list){
			fixedStr = fixedStr +","+ str;
		}
		if(!fixedStr.equals("")){
			fixedStr = fixedStr.substring(1);
		}
		
		
		
		String modelsStr = "";
		String[] split1 = SplitUtil.toSplit(fixedReturn.getFixedReturnModelsId());
		List<String> list1 = new ArrayList<String>();
		for(int i=0;i<split1.length;i++){
			list1.add(split1[i]);
		}
		for(int k=0;k<list1.size();k++){
			if(Long.valueOf(list1.get(k)).equals(models.getModelsId())){
				list1.remove(k);//移除存在的车型
				k--; 
			}
		}
		for(String str : list1){
			modelsStr = modelsStr +","+ str;
		}
		if(!modelsStr.equals("")){
			modelsStr = modelsStr.substring(1);
		}
		fixedReturn.setFixedReturnModelsId(modelsStr);
		fixedReturnServiceWrite.editFixedReturn(fixedReturn);
		models.setModelsFixedReturn(fixedStr);
		modelsServiceWrite.updateModels(models);
		return "success";
	}
	
}
