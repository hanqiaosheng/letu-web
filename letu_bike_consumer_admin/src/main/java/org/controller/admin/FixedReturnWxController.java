package org.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.Bike;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.Channel;
import org.entity.dto.FixedReturn;
import org.entity.dto.LatLng;
import org.entity.dto.Models;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeLockInfoServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.BlockServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.FixedReturnServiceRead;
import org.service.cms.read.ModelsServiceRead;
import org.service.cms.write.BikeLockInfoServiceWrite;
import org.service.cms.write.BikeServiceWrite;
import org.service.cms.write.FixedReturnServiceWrite;
import org.service.lock.LockService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.BlockUtil;
import org.util.MessageUtil;
import org.util.PageUtil;
import org.util.SplitUtil;

@Controller
@Scope("prototype")
@RequestMapping(value = "/api/fixedReturn", method = RequestMethod.POST)
public class FixedReturnWxController {
	@Resource
	BlockServiceRead blockServiceRead;
	@Resource
	AdminServiceRead adminServiceRead;
	@Resource
	ChannelServiceRead channelServiceRead;
	@Resource
	FixedReturnServiceRead fixedReturnServiceRead;
	@Resource
	ModelsServiceRead modelsServiceRead;
	@Resource
	LockService lockService;
	@Resource
	BikeLockInfoServiceRead bikeLockInfoServiceRead;
	@Resource
	BikeLockInfoServiceWrite bikeLockInfoServiceWrite;
	@Resource
	FixedReturnServiceWrite fixedReturnServiceWrite;
	@Resource
	BikeServiceRead bikeServiceRead;
	@Resource
	BikeServiceWrite bikeServiceWrite;
	@Resource
	AppConfig appConfig;
	
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	
	//查询站点
	@RequestMapping(value = "/findFixeds", method = RequestMethod.POST)
	public @ResponseBody List<LatLng> findFixeds(String latLng,HttpSession session) throws Exception {
		List<String> blockCodes = BlockUtil.getBlockCodeFor9(latLng);
		/*List<Long> bIds = blockServiceRead.findBlockIdsByCodes(blockCodes);
		List<LatLng> fixedList = new ArrayList<LatLng>();
		if(null==bIds||0==bIds.size()){
			return fixedList;
		}*/
		List<LatLng> fixedList = new ArrayList<LatLng>();
		Admin admin_session = (Admin) session.getAttribute("admin");
		Admin admin = adminServiceRead.findAdminId(admin_session.getAdminId());
		if(null==admin.getAdminChannelId()){  //超级管理员
			fixedList = fixedReturnServiceRead.findByBlockIdsAndChannel(blockCodes,null);
		}else{
			Channel channel =channelServiceRead.findById(admin.getAdminChannelId());
			List<Channel> channels1 = channelServiceRead.findSonChannels(admin.getAdminChannelId());
		    channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}	
			fixedList = fixedReturnServiceRead.findByBlockIdsAndChannel(blockCodes,currChannelIds);	
		}
		return fixedList;
	}	
	
	/**
	 * 查询站点信息
	 * @param fixedReturnId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fixedReturnInfos", method = RequestMethod.POST)
	public @ResponseBody MessageUtil fixedReturnInfos(Long fixedReturnId) throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(fixedReturnId);
		if(null!=fixedReturn.getFixedReturnModelsId()&&!fixedReturn.getFixedReturnModelsId().equals("")){
			List<Models> modelsList = new ArrayList<Models>();
			String[] split = SplitUtil.toSplit(fixedReturn.getFixedReturnModelsId());
			for(int i=0;i<split.length;i++){
				Models models = modelsServiceRead.findModelsById(Long.valueOf(split[i]));
				if(null!=models){
					modelsList.add(models);
				}
			}
			fixedReturn.setModels(modelsList);
		}
		data.put("fixedReturn", fixedReturn);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		messageUtil.setData(data);
		return messageUtil;
	}
	
	/**
	 * 查询站点信息
	 * @param fixedReturnId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fixedInfos", method = RequestMethod.POST)
	public @ResponseBody MessageUtil fixedInfos(HttpSession session,String fixedName,Integer pageIndex) throws Exception {
		Admin admin_session = (Admin) session.getAttribute("admin");
		Admin admin = adminServiceRead.findAdminId(admin_session.getAdminId());
		Map<String, Object> data = new HashMap<String, Object>();
		List<FixedReturn> fixedList = new ArrayList<FixedReturn>();
		Integer totalPage = 1;
		Integer totalCount = 0;
		if(null==admin.getAdminChannelId()){  //超级管理员
			fixedList = fixedReturnServiceRead.findByChannelIdsAndName(pageIndex,appConfig.getPage_size_weixin(),fixedName,null);
			totalCount = fixedReturnServiceRead.findAllFixedCount(fixedName,null);
		}else{
			Channel channel =channelServiceRead.findById(admin.getAdminChannelId());
			List<Channel> channels1 = channelServiceRead.findSonChannels(admin.getAdminChannelId());
		    channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}	
			fixedList = fixedReturnServiceRead.findByChannelIdsAndName(pageIndex,appConfig.getPage_size_weixin(),fixedName,currChannelIds);	
			totalCount = fixedReturnServiceRead.findAllFixedCount(fixedName,currChannelIds);
		}
		for(FixedReturn fixedReturn : fixedList){
			if(null!=fixedReturn.getFixedReturnModelsId()&&!fixedReturn.getFixedReturnModelsId().equals("")){
				List<Models> modelsList = new ArrayList<Models>();
				String[] split = SplitUtil.toSplit(fixedReturn.getFixedReturnModelsId());
				for(int i=0;i<split.length;i++){
					Models models = modelsServiceRead.findModelsById(Long.valueOf(split[i]));
					if(null!=models){
						modelsList.add(models);
					}
				}
				fixedReturn.setModels(modelsList);
			}
		}
		if(totalCount>0){
			totalPage=PageUtil.getWxTotalPage(totalCount);
		}
		data.put("fixedList", fixedList);
		data.put("totalCount", totalCount);
		data.put("totalPage", totalPage);
		data.put("pageIndex", pageIndex);
		data.put("fixedName", fixedName);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		messageUtil.setData(data);
		return messageUtil;
	}
	
	/**
	 * 定位
	 * @param bikeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/fixedPosition", method = RequestMethod.POST)
	public @ResponseBody MessageUtil fixedPosition(Long fixedReturnId) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(fixedReturnId);
		data.put("fixedReturn", fixedReturn);
		messageUtil.setData(data);
		return messageUtil;
	}
	
	 //校准车辆站点信息
    @RequestMapping("checkBikeOfFixed")
    public @ResponseBody MessageUtil checkBikeOfFixed(HttpSession session) throws Exception{
    	//取admin的渠道id
    	Long channelId  = (Long) session.getAttribute("currChannelId");
    	//查渠道下的所有车辆
    	List<Bike> bikeList = new ArrayList<Bike>();
    	//List<FixedReturn> fixedReturns = new ArrayList<FixedReturn>();
    	if(channelId==null){
    		//fixedReturns = fixedReturnServiceRead.findByChannelIds(null);
    		bikeList = bikeServiceRead.findByChannels(null);
    		for(Bike b: bikeList){
    			if(null!=b.getBikeLockId()){
    				BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(b.getBikeLockId());
    				if(lockService.judgeLockConnet(bikeLockInfo.getBikeLockCode())){
    					//发送定位
    					lockService.sendLocationMessage(bikeLockInfo.getBikeLockCode());
    				}
    			}else{
    				b.setBikeFixedReturnId((long)0);
    				bikeServiceWrite.editBike(b);
    			}
    			
    		}
    	}else{
    		Channel channel =channelServiceRead.findById(channelId);//该渠道
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);//获取子渠道
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			
			bikeList = bikeServiceRead.findByChannels(currChannelIds);
			//fixedReturns = fixedReturnServiceRead.findByChannelIds(currChannelIds);
			for(Bike b: bikeList){
    			if(null!=b.getBikeLockId()){
    				BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(b.getBikeLockId());
    				if(lockService.judgeLockConnet(bikeLockInfo.getBikeLockCode())){
    					//发送定位
    					lockService.sendLocationMessage(bikeLockInfo.getBikeLockCode());
    				}
    			}else{
    				b.setBikeFixedReturnId((long)0);
    				bikeServiceWrite.editBike(b);
    			}
    			
    		}
    	}
    	/*Thread.sleep(5000);
    	
    	for(FixedReturn fr : fixedReturns){
    		List<Bike> bike = bikeServiceRead.findByFixedReturnId(fr.getFixedReturnId());//查询站点下的所有车
    		fr.setFixedReturnBikeNum(bike.size());
    		fixedReturnServiceWrite.editFixedReturn(fr);
    	}*/
    	messageUtil.setMessage("success");
    	return messageUtil;
    }
    
    
    /**
	 * 添加要审核的站点信息
	 * @param bikeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/addFixed", method = RequestMethod.POST)
	public @ResponseBody MessageUtil addFixed(FixedReturn fixedReturn) throws Exception{
		if(fixedReturn.getFixedReturnLng()==null||fixedReturn.getFixedReturnLng().equals("")||fixedReturn.getFixedReturnLat()==null||fixedReturn.getFixedReturnLat().equals("")){
			 messageUtil.setMessage("fail");
			 return messageUtil;
		}
		fixedReturn.setFixedReturnState(2);//未审核
		fixedReturn.setFixedReturnFixedTime(new Date());
	    fixedReturnServiceWrite.addFixedReturn(fixedReturn);
	    messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 我设置的站点信息
	 * @param session
	 * @param pageIndex
	 * @param fixedState
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/myFixedList", method = RequestMethod.POST)
	public @ResponseBody MessageUtil myFixedList(HttpSession session,Integer pageIndex,Integer fixedState) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		Admin admin_session = (Admin) session.getAttribute("admin");
		List<FixedReturn> fixedList = fixedReturnServiceRead.findAllMyFixed(pageIndex, appConfig.getPage_size_weixin(), fixedState,admin_session.getAdminId());
		Integer totalCount = fixedReturnServiceRead.findAllFixedCount(fixedState,admin_session.getAdminId());
		Integer totalPage=PageUtil.getWxTotalPage(totalCount);
		data.put("fixedList", fixedList);
		data.put("totalCount", totalCount);
		data.put("totalPage", totalPage);
		data.put("pageIndex", pageIndex);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
}
