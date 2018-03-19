package org.controller.cms;

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
import org.entity.dto.Channel;
import org.entity.dto.City;
import org.entity.dto.Models;
import org.entity.dto.OperateLog;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.CityServiceRead;
import org.service.cms.read.ModelsServiceRead;
import org.service.cms.write.AdminServiceWrite;
import org.service.cms.write.BikeServiceWrite;
import org.service.cms.write.ChannelServiceWrite;
import org.service.cms.write.ModelsServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
import org.util.OperateUtil;
import org.util.PageUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/channel")
public class ChannelController {
	
	
	@Resource
	ChannelServiceRead channelServiceRead;
	@Resource
	ChannelServiceWrite channelServiceWrite;
	@Resource
	BikeServiceRead bikeServiceRead;
	@Resource
	BikeServiceWrite bikeServiceWrite;
	@Resource
	AdminServiceRead adminServiceRead;
	@Resource
	AdminServiceWrite adminServiceWrite;
	@Resource
	ModelsServiceRead modelsServiceRead;
	@Resource
	ModelsServiceWrite modelsServiceWrite;
	@Resource
	OperateServiceWrite operateServiceWrite;
	@Resource
	CityServiceRead cityServiceRead;
	@Resource
	AppConfig AppConfig;
	/**
	 * 渠道列表
	 * @param channelId
	 * @param charger
	 * @param channelName
	 * @param model
	 * @return
	 * @throws Exception
	 */
	
	@RequestMapping("list")
	public String list(HttpSession session,@RequestParam(defaultValue = "1") Integer pageIndex,String channelNum, Long channelId,String charger,String channelName,Model model) throws Exception{
		//查渠道parentId
		Long cId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin)session.getAttribute("admin");
		if(null!=cId){  //如果不是超级管理员
			Channel channel= channelServiceRead.findById(cId);
			String num = channel.getChannelNum(); 		//渠道编号
			List<Channel> sonChannels =  channelServiceRead.findByParentId(pageIndex,AppConfig.getPage_size_web(),channelNum,num,charger,channelName);//查找子级渠道
			Integer totalCount = channelServiceRead.findAllCountByParentId(channelNum,num,charger,channelName);
			// 总页数
			Integer totalPage = PageUtil.getTotalPage(totalCount);
			if(0==totalPage){
				totalPage = 1;
			}
			
			for(Channel channel1 : sonChannels){
				String channelnum = channel1.getChannelNum();
				channelnum = channelnum.substring(channelnum.indexOf("-")+1);
				if(channelnum.equals("")){
					channelnum = channel1.getChannelNum().replace("-", "");
					channel1.setChannelNum(channelnum);
				}
				channel1.setAdminName(admin.getAdminRealname());
			}
			
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("sonChannels", sonChannels);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("channelId", channelId);
			model.addAttribute("charger", charger);
			model.addAttribute("channelName", channelName);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("channelNum", channelNum);
			return "channel_list";
			
			
		}else{				//如果为超级管理员
			List<Channel> sonChannels = channelServiceRead.findAllChannel(pageIndex, AppConfig.getPage_size_web(), channelNum,null, charger, channelName, null);
			Integer totalCount = channelServiceRead.findAllChannelCount(channelNum,channelId, charger, channelName);
			Integer totalPage = PageUtil.getTotalPage(totalCount);
			if(0==totalPage){
				totalPage = 1;
			}
			
			for(Channel channel1 : sonChannels){
				String channelnum = channel1.getChannelNum();
				channelnum = channelnum.substring(channelnum.indexOf("-")+1);
				if(channelnum.equals("")){
					channelnum = channel1.getChannelNum().replace("-", "");
					channel1.setChannelNum(channelnum);
				}
				channel1.setAdminName(admin.getAdminRealname());
			}
			
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("sonChannels", sonChannels);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("channelId", channelId);
			model.addAttribute("charger", charger);
			model.addAttribute("channelName", channelName);
			model.addAttribute("pageIndex", pageIndex);
			model.addAttribute("channelNum", channelNum);
			return "channel_list";
		}
		
		
	}
	
	/**
	 * 跳转新增渠道
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("gotoAdd")
	public String gotoAdd(Model model,HttpSession session) throws Exception{
		//查渠道parentId
		Long cId = (Long) session.getAttribute("currChannelId");
		Channel channel = null ;
		if(cId!=null){
			channel= channelServiceRead.findById(cId);
		}
		List<City> cityList = cityServiceRead.findAll();
		model.addAttribute("cityList", cityList);
		model.addAttribute("channel", channel);
		return "detail/channel_add";
	}
	
	/**
	 * 新增渠道
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("channelAdd")
	public String channelAdd(Channel channel,HttpSession session) throws Exception{
		Admin admin  = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		channel.setChannelState(1);
		channel.setChannelChargerAdminid(admin.getAdminId());
		if(1==channel.getChannelLevel()){  //1级渠道
			List<Channel> pChannels = channelServiceRead.findLevelOne(null);
			if(null!=pChannels){
				channel.setChannelNum((pChannels.size())+1+"-");
			}else{
				channel.setChannelNum("1-");
			}
		}else{					//2级渠道
			Integer channelsCount = channelServiceRead.getCountByParentId(channel.getChannelParentId());
			Channel pChannel = channelServiceRead.findById(channel.getChannelParentId());
			String channelNum = pChannel.getChannelNum()+(channelsCount+1);
			channel.setChannelNum(channelNum);
		}
		Long channelId = channelServiceWrite.addChannel(channel);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateChannel(nowAdmin.getAdminRealname(), 1, channelId);
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "redirect:list.action";
	}
	
	/**
	 * 渠道详情
	 * @param channelId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("channelDetail")
	public String channelDetail(@RequestParam(defaultValue = "1") Integer pageIndex,Long channelId,Model model) throws Exception{
		Channel channel = channelServiceRead.findById(channelId);
		List<Channel> channels = channelServiceRead.findSonChannels(channelId);
		List<Models> modelIdsList = new ArrayList<Models>();
		if(channels.size()>0){
			channels.add(channel);
			List<Long> channelIds = new ArrayList<Long>();
			for(Channel c:channels){
				channelIds.add(c.getChannelId());
			}
			modelIdsList = modelsServiceRead.findChannelIds(channelIds);
		}else{
			modelIdsList = modelsServiceRead.findModelsByChannelId(channelId);
		}
		
		List<Long> modelsIds = new ArrayList<Long>();
		if(modelIdsList.size()>0){
			for(Models m :modelIdsList){
				modelsIds.add(m.getModelsId());
			}
		List<Bike> bikeList = bikeServiceRead.findBikeByModels(pageIndex,AppConfig.getPage_size_web(),modelsIds);
		
		Integer totalCount = bikeServiceRead.findByModelsId(modelsIds);
		
		City city = cityServiceRead.findById(channel.getChannelId());
		// 总页数
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		if(0==totalPage){
			totalPage = 1;
		}
		
		model.addAttribute("city", city);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("bikeList", bikeList);
		model.addAttribute("channel", channel);
		return "detail/channel_detail";
		}
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalPage", 1);
		model.addAttribute("totalCount", 0);
		model.addAttribute("channel", channel);
		return "detail/channel_detail";
	}
	
	/**
	 * 渠道冻结
	 * @param flag
	 * @return 0 冻结 1使用中 -1删除
	 * @throws Exception
	 */
	@RequestMapping("frozenChannel")
	public @ResponseBody String frozenChannel(Long channelId,Integer flag,HttpSession session) throws Exception{
		Admin admin  = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		Channel channel = channelServiceRead.findById(channelId);
		channel.setChannelState(flag);
		//更新 channel
		channelServiceWrite.update(channel);
		List<Channel> sonChannels = channelServiceRead.findByParentChannelId(channel.getChannelId());//查找子级的渠道
		if(sonChannels.size()>0){
			for(Channel c:sonChannels){
				c.setChannelState(flag);
				//更新 channel
				channelServiceWrite.update(c);
			}
		}
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		if(flag==0){//冻结
			String remark = OperateUtil.operateChannel(nowAdmin.getAdminRealname(), 3, channelId);
			operateLog.setOperateRemark(remark);
		}else if(flag==1){//解冻
			String remark = OperateUtil.operateChannel(nowAdmin.getAdminRealname(), 5, channelId);
			operateLog.setOperateRemark(remark);
		}else if(flag==-1){//删除
			String remark = OperateUtil.operateChannel(nowAdmin.getAdminRealname(), 4, channelId);
			operateLog.setOperateRemark(remark);
		}
		
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		return "success";
	}
	
	/**
	 * 检查是否重复渠道名
	 * @param adminUsername
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkChannelName")
	public @ResponseBody MessageUtil checkChannelName(String channelName) throws Exception{
		MessageUtil messageUtil = new MessageUtil();
		Channel channel = channelServiceRead.findByName(channelName);
		if(null!=channel){
			messageUtil.setMessage("fail"); 
			return messageUtil;
		}
		messageUtil.setMessage("success"); 
		return messageUtil;
	}
	
	/**
	 * 获取1级渠道
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getParentChannel")
	public @ResponseBody Map<String, Object> getParentChannel(HttpSession session) throws Exception{
		Map<String, Object> map = new HashMap<String,Object>();
		//查渠道parentId
		Long cId = (Long) session.getAttribute("currChannelId");
		List<Channel> pChannels = new ArrayList<Channel>();
		if(null!=cId){  //如果不是超级管理员
			Channel channel =channelServiceRead.findById(cId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(cId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			pChannels = channelServiceRead.findLevelOne(currChannelIds);
		}else{
			pChannels = channelServiceRead.findLevelOne(null);
		}
		
		if(null!=pChannels){
			map.put("message", "success");
		}
		map.put("pChannels", pChannels);
		return map;
	}
	
	/**
	 * 租赁费用页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("rentMoney")
	public String rentMoney(Model model,@RequestParam(defaultValue = "1") Integer pageIndex,HttpSession session,Long channelId,String channelName) throws Exception{
		//查渠道parentId
		Long cId = (Long) session.getAttribute("currChannelId");
		if(null==cId){    //超级管理员
			List<Models> modelList = modelsServiceRead.findAllModels2(pageIndex,AppConfig.getPage_size_web(),channelId,channelName);
			Integer totalCount =  modelsServiceRead.findAllCountModels(channelId, channelName);
			Integer totalPage = PageUtil.getTotalPage(totalCount);
			if(0==totalPage){
				totalPage =1;
			}
			model.addAttribute("modelList", modelList);
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("channelId", channelId);
			model.addAttribute("channelName", channelName);
		}else{     	//普通渠道
			Channel channel =channelServiceRead.findById(cId);
				List<Channel> channels = channelServiceRead.findSonChannels(cId);
				channels.add(channel);
				List<Long> channelIds = new ArrayList<Long>();
				for(Channel c:channels){
					channelIds.add(c.getChannelId());
				}
				List<Models> modelIdsList = modelsServiceRead.findChannelIdsPage(pageIndex,AppConfig.getPage_size_web(),channelId,channelName,channelIds);
				Integer totalCount =  modelsServiceRead.findAllCountModelsPage(channelId, channelName,channelIds);
				Integer totalPage = PageUtil.getTotalPage(totalCount);
				if(0==totalPage){
					totalPage =1;
				}
				model.addAttribute("modelList", modelIdsList);
				model.addAttribute("totalCount", totalCount);
				model.addAttribute("totalPage", totalPage);
				model.addAttribute("channelId", channelId);
				model.addAttribute("channelName", channelName);
		}
		
		
		return "rentPlan_list";
	}
	
	/**
	 * 新增租赁费用
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addRentPlanJsp")
	public String addRentPlanJsp(Model model,HttpSession session) throws Exception{
		//查渠道parentId
		Long cId = (Long) session.getAttribute("currChannelId");
		List<Channel> channelList = new ArrayList<Channel>();
		if(null==cId){    //超级管理员
			channelList = channelServiceRead.findAllChannelNotfreeze(null);
		}else{
			Channel channel =channelServiceRead.findById(cId);
			channelList = channelServiceRead.findSonChannels(cId);
			channelList.add(channel);
		}
		model.addAttribute("channelList", channelList);
		return "detail/rentPlan_add";
	}
	
	/**
	 * 选择渠道
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
	 * 同个渠道  型号是否重复
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkCode")
	public @ResponseBody MessageUtil checkCode(String checkCode,Long modelsChannelId) throws Exception{
		MessageUtil messageUtil = new MessageUtil();
		Models models = modelsServiceRead.findByChannelIdAndCode(checkCode,modelsChannelId);
		if(null!=models){
			messageUtil.setMessage("该渠道下已有该车型价格");
			return messageUtil;
		}
			
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 新增租赁费用
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addRentPlan")
	public String addRentPlan(Models models) throws Exception{
		modelsServiceWrite.addModels(models);
		return "redirect:rentMoney.action";
	}
	
	
	/**
	 * 编辑渠道页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("channelUpdateJsp")
	public String channelUpdateJsp(Model model,Long channelId) throws Exception{
		Channel channel = channelServiceRead.findById(channelId);
		
		if(null!=channel.getChannelParentId()){
			Channel pChannel = channelServiceRead.findById(channel.getChannelParentId());
			model.addAttribute("pChannel", pChannel);
		}
		List<City> cityList = cityServiceRead.findAll();
		model.addAttribute("cityList", cityList);
		model.addAttribute("channel", channel);
		return "detail/channel_edit";
	}
	
	/**
	 * 更新渠道
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("channelUpdate")
	public String channelUpdate(Channel channel,HttpSession session) throws Exception{
		Admin admin  = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		
		channelServiceWrite.update(channel);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateChannel(nowAdmin.getAdminRealname(), 2, channel.getChannelId());
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "redirect:list.action";
	}
	
	
}
