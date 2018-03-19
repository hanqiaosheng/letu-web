package org.controller.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.entity.dto.Bike;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.Channel;
import org.entity.dto.FixedReturn;
import org.service.cms.read.BikeRentInfoServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.BlockServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.FixedReturnServiceRead;
import org.service.cms.write.BikeRentInfoServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
@RequestMapping("cms/bikeRentInfo")
public class BikeRentInfoController {
	
	@Resource
	BikeRentInfoServiceRead bikeRentInfoServiceRead;
	
	@Resource
	BikeServiceRead bikeServiceRead;
	
	@Resource
	BikeRentInfoServiceWrite bikeRentInfoServiceWrite;
	
	@Resource
	BlockServiceRead blockServiceRead;
	
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	FixedReturnServiceRead fixedReturnServiceRead;
	
	/**
	 * 车辆租赁列表
	 * @param model
	 * @param pageIndex
	 * @param bikeId
	 * @param bikerentId
	 * @param userPhone
	 * @param rentFromTime
	 * @param rentTotime
	 * @param returnFromTime
	 * @param returnTotime
	 * @param rentLatLng
	 * @param returnLatLng
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bikeRentInfoList")
	public String bikeRentInfoList(Model model,@RequestParam(defaultValue="1")Integer pageIndex,Long bikeId,
			                       Long bikerentId,String userPhone,Date rentFromTime,Date rentTotime,Date returnFromTime,
			                       Date returnTotime, Long rentStartFixedId,Long rentEndFixedId,HttpSession session ,Long channelid
			                       ) throws Exception{
		//获取admin的渠道id
		Long channelId = (Long) session.getAttribute("currChannelId");
    	/*List<Long> blockreturnIds = null;
    	List<Long> blockrentIds = null;
    	if(null!=rentLatLng&&!"".equals(rentLatLng)){
			String blockCode=BlockUtil.getBlockCode5(rentLatLng);
			blockrentIds =blockServiceRead.findBlockIds(blockCode);
			if(blockrentIds==null){
				blockrentIds = new ArrayList<Long>();
				blockrentIds.add((long) 0);
			}
		}
    	if(null!=returnLatLng&&!"".equals(returnLatLng)){
			String blockCode=BlockUtil.getBlockCode5(returnLatLng);
			blockreturnIds =blockServiceRead.findBlockIds(blockCode);
			if(blockreturnIds==null){
				blockreturnIds = new ArrayList<Long>();
				blockreturnIds.add((long) 0);
			}
		}*/
		Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
		//查渠道下的租赁记录
		List<BikeRentInfo> bikeRentInfoList = new ArrayList<BikeRentInfo>();
		List<Channel> channelList = new ArrayList<Channel>();
		Integer totalPage = 1;
		if(channelId==null){
			bikeRentInfoList = bikeRentInfoServiceRead.findAllBikeRentInfo(pageIndex, null, bikeId, bikerentId,
					userPhone, rentFromTime, rentTotime, returnFromTime, returnTotime, rentStartFixedId, rentEndFixedId,channelid);
			totalPage = bikeRentInfoServiceRead.countAllBikeRentInfo(null, bikeId, bikerentId, userPhone,
					rentFromTime, rentTotime, returnFromTime, returnTotime, rentStartFixedId, rentEndFixedId,channelid);
			
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
   	    }else{
   		    Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			bikeRentInfoList = bikeRentInfoServiceRead.findAllBikeRentInfo(pageIndex, currChannelIds, bikeId, bikerentId,
					userPhone, rentFromTime, rentTotime, returnFromTime, returnTotime, rentStartFixedId, rentEndFixedId,channelid);
			totalPage = bikeRentInfoServiceRead.countAllBikeRentInfo(currChannelIds, bikeId, bikerentId, userPhone,
					rentFromTime, rentTotime, returnFromTime, returnTotime, rentStartFixedId, rentEndFixedId,channelid);
			
			channelList = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
   	    }
		for(BikeRentInfo bri : bikeRentInfoList){
			if(null!=bri.getRentStartFixedId()&&0!=bri.getRentStartFixedId()){
				FixedReturn fiStartReturn = fixedReturnServiceRead.findFixedById(bri.getRentStartFixedId());
				bri.setStartFixedName(fiStartReturn.getFixedReturnName());
			}
			if(null!=bri.getRentEndFixedId()&&0!=bri.getRentEndFixedId()){
				FixedReturn fiEndReturn = fixedReturnServiceRead.findFixedById(bri.getRentEndFixedId());
				bri.setEndFixedName(fiEndReturn.getFixedReturnName());
			}
			
		}
		if(totalPage==0){
        	totalPage = 1;
        }
		model.addAttribute("bikeRentInfoList", bikeRentInfoList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("bike", bike);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("channelList", channelList);
		model.addAttribute("bikerentId", bikerentId);
		model.addAttribute("userPhone", userPhone);
		model.addAttribute("rentFromTime", rentFromTime);
		model.addAttribute("rentTotime", rentTotime);
		model.addAttribute("returnFromTime", returnFromTime);
		model.addAttribute("returnTotime", returnTotime);
		model.addAttribute("rentStartFixedId", rentStartFixedId);
		model.addAttribute("rentEndFixedId", rentEndFixedId);
		model.addAttribute("channelid", channelid);
		return "bikeRentInfo_list";
	}
	
	/**
	 * 租赁详情
	 * @param model
	 * @param bikeRentId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bikeRentInfoDetail")
	public String bikeRentInfoDetail(Model model,Long bikeRentId) throws Exception{
		BikeRentInfo bikeRentInfo = bikeRentInfoServiceRead.findBikeRentInfoById(bikeRentId);
		model.addAttribute("bikeRentInfo", bikeRentInfo);
		return "detail/bikeRentInfo_detail";
	}
	
	/**
	 * 删除租赁
	 * @param bikeRentId
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteBikeRentInfo")
	public @ResponseBody String deleteBikeRentInfo(Long bikeRentId,Long bikeId) throws Exception{
		BikeRentInfo bikeRentInfo = new BikeRentInfo();
		bikeRentInfo.setRentInfoId(bikeRentId);
    	bikeRentInfoServiceWrite.deleteBikeRentInfoById(bikeRentInfo);
    	return "success";
	}
	
}
