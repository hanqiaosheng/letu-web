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
import org.entity.dto.BikeFixInfo;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.Channel;
import org.entity.dto.FixedReturn;
import org.entity.dto.Models;
import org.entity.dto.User;
import org.entity.weixin.pojo.JSSDK;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeFixInfoServiceRead;
import org.service.cms.read.BikeLockInfoServiceRead;
import org.service.cms.read.BikeRentInfoServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.FixedReturnServiceRead;
import org.service.cms.read.ModelsServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.BikeFixInfoServiceWrite;
import org.service.cms.write.BikeLockInfoServiceWrite;
import org.service.cms.write.BikeServiceWrite;
import org.service.lock.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.MessageUtil;
import org.util.PageUtil;
import org.util.redis.RedisService;
import org.util.weixin.WxConfigUtil;
/**
 * 车辆信息
 * @author zucc
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/api/bikeMessage", method = RequestMethod.POST)
public class BikeMessageController {
	
	@Resource
	BikeServiceRead bikeServiceRead;
	
	@Resource
	BikeServiceWrite bikeServiceWrite;
	
	@Resource
	BikeRentInfoServiceRead bikeRentInfoServiceRead;
	
	@Resource
	BikeFixInfoServiceRead bikeFixInfoServiceRead;
	
	@Resource
	BikeFixInfoServiceWrite bikeFixInfoServiceWrite;
	
	@Resource
	AppConfig AppConfig;
	
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	ModelsServiceRead modelsServiceRead;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	UserServiceRead userServiceRead;
	
	@Resource
	LockService lockService;
	
	@Resource
	FixedReturnServiceRead fixedReturnServiceRead;
	
	@Resource
	BikeLockInfoServiceRead bikeLockInfoServiceRead;
	
	@Resource
	BikeLockInfoServiceWrite bikeLockInfoServiceWrite;
	
	/**封装之后，实行单例模式，不用每次开关*/
	@Autowired  
    private RedisService redisService;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	
	/**
	 * 扫码所需参数
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scanCode", method = RequestMethod.POST)
	public @ResponseBody JSSDK scanCode(String url) throws Exception {
		JSSDK jssdk = new JSSDK();
		jssdk.setAppId(AppConfig.getApp_id());
		jssdk.setNonceStr(WxConfigUtil.getRandomString(16));
		jssdk.setAppUrl(url);
		jssdk.setCurrentTime(new Date().getTime()/1000);
		jssdk.setSignature(WxConfigUtil.getSignature(url, jssdk.getCurrentTime(), AppConfig.getApp_id(),jssdk.getNonceStr(),
				AppConfig.getApp_secret()));
		jssdk.setTimestamp(WxConfigUtil.cacheAddTime);// 时间更新为缓存中存储的时间
		return jssdk;
	}
	/**
	 * 车辆信息
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/bikeMessage", method = RequestMethod.POST)
	public @ResponseBody MessageUtil bikeMessage(String bikeCode) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		Bike bike = bikeServiceRead.findBikeByEqualBikeCode(bikeCode);
		List<BikeRentInfo> bikeRentInfos = bikeRentInfoServiceRead.findBikeRentInfoByBikeId(bike.getBikeId());
		if(bikeRentInfos.size()>0){
			data.put("bikeRentInfo", bikeRentInfos.get(0));
			User user =userServiceRead.findById(bikeRentInfos.get(0).getRentInfoUserId());
			data.put("user", user);
		}
		String lastFixTime = bikeFixInfoServiceRead.findMaxDate(bike.getBikeId());
		if(null!=lastFixTime){
			bike.setFixLasttime(DateUtil.changStringDate(lastFixTime));
		}
		BikeLockInfo bikeLockInfo = new BikeLockInfo();
		if(null!=bike.getBikeLockId()){
			bikeLockInfo = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
		}
		
		FixedReturn fixedReturn = new FixedReturn();
		if(null!=bike.getBikeFixedReturnId()&&0!=bike.getBikeFixedReturnId()){
			fixedReturn = fixedReturnServiceRead.findFixedById(bike.getBikeFixedReturnId());
		}
		data.put("fixedReturn", fixedReturn);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		data.put("bike", bike);
		data.put("bikeLockInfo", bikeLockInfo);
		messageUtil.setData(data);
		return messageUtil;
	}
	/**
	 * 车辆租赁、维护信息查看
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkMessage", method = RequestMethod.POST)
	public @ResponseBody MessageUtil checkMessage(Long bikeId) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
		List<BikeRentInfo> bikeRentInfo = bikeRentInfoServiceRead.findByBikeId(bikeId,1,3);
		List<BikeFixInfo> bikeFixInfoList = bikeFixInfoServiceRead.findByBikeId(bikeId,1,3);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		data.put("bikeRentInfo", bikeRentInfo);
		data.put("bikeCode", bike.getBikeCode());
		data.put("bikeFixInfoList", bikeFixInfoList);
		messageUtil.setData(data);
		return messageUtil;
	}
	
	
	/**
	 * 车辆租赁信息列表
	 * @param bikeId
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/rentInfoList", method = RequestMethod.POST)
	public @ResponseBody MessageUtil fixInfoList(Long bikeId,@RequestParam(defaultValue="1")Integer pageIndex) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
		List<BikeRentInfo> bikeRentInfoList = bikeRentInfoServiceRead.findByBikeId(bikeId,pageIndex,AppConfig.getPage_size_weixin());
		Integer totalCount=bikeRentInfoServiceRead.findCountByBikeId(bikeId);
		Integer totalPage=1;
		if(totalCount>0){
			totalPage=PageUtil.getWxTotalPage(totalCount);
		}
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		data.put("bikeRentInfoList", bikeRentInfoList);
		data.put("totalPage", totalPage);
		data.put("bikeCode", bike.getBikeCode());
		messageUtil.setData(data);
		return messageUtil;
	}
	
	/**
	 * 车辆维护信息列表
	 * @param bikeId
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fixInfoList", method = RequestMethod.POST)
	public @ResponseBody MessageUtil rentInfoList(Long bikeId,@RequestParam(defaultValue="1")Integer pageIndex) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
		List<BikeFixInfo> bikeFixInfoList = bikeFixInfoServiceRead.findByBikeId(bikeId,pageIndex,AppConfig.getPage_size_weixin());
		Integer totalCount=bikeFixInfoServiceRead.countByBikeId(bikeId);
		Integer totalPage=1;
		if(totalCount>0){
			totalPage=PageUtil.getWxTotalPage(totalCount);
		}
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		data.put("bikeFixInfoList", bikeFixInfoList);
		data.put("totalPage", totalPage);
		data.put("bikeCode", bike.getBikeCode());
		messageUtil.setData(data);
		return messageUtil;
	}
	
	/**
	 * 车辆状态修改
	 * @param bikeId
	 * @param bikeState
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateBikeState", method = RequestMethod.POST)
	public @ResponseBody MessageUtil updateBikeState(Long bikeId,Integer bikeState) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
		bike.setBikeState(bikeState);
		bikeServiceWrite.editBike(bike);
		messageUtil.setMessage("success");
		data.put("bike", bike);
		messageUtil.setData(data);
		return messageUtil;
	}
	
	
	/**
	 * 车辆搜索
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="bikeRentDetailSearch", method = RequestMethod.POST)
	public @ResponseBody MessageUtil bikeRentDetailSearch(String bikeCode,Integer pageIndex,HttpSession session) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		Admin admin_session = (Admin) session.getAttribute("admin");
		Admin admin = adminServiceRead.findAdminId(admin_session.getAdminId());
		List<Bike> bikeList = new ArrayList<Bike>();
		Integer totalCount = 0;
		Integer totalPage= 1;
		
		if(null==admin.getAdminChannelId()){  //超级管理员
			bikeList = bikeServiceRead.findAllBikes(pageIndex,AppConfig.getPage_size_weixin(),bikeCode);
			totalCount = bikeServiceRead.findAllBikeCount(bikeCode);
		}else{
			Channel channel = channelServiceRead.findById(admin.getAdminChannelId());
			List<Long> modelsList = modelsServiceRead.findByChannelId(channel.getChannelId());
			if(null!=modelsList){
			if(null!=channel.getChannelParentId()){
						bikeList = bikeServiceRead.findByChannel(pageIndex,AppConfig.getPage_size_weixin(),bikeCode,modelsList);
						totalCount = bikeServiceRead.findByChannelCounts(bikeCode, modelsList);
					}else{
						List<Channel> channels = channelServiceRead.findByParentChannelId(channel.getChannelId());
						if(channels.size()>0){
							List<Long> currChannelIds = new ArrayList<Long>();
							for(Channel c:channels){
								currChannelIds.add(c.getChannelId());
							}
							List<Models> modelsList2 = modelsServiceRead.findChannelIds(currChannelIds);
							List<Long> modelsList3 = new ArrayList<Long>();
							for(Models m:modelsList2){
								modelsList3.add(m.getModelsId());
							}
							modelsList.addAll(modelsList3);
						}
						
						bikeList = bikeServiceRead.findByChannel(pageIndex,AppConfig.getPage_size_weixin(),bikeCode,modelsList);
						totalCount = bikeServiceRead.findByChannelCounts(bikeCode, modelsList);
					}
			}
		}
		if(totalCount>0){
			totalPage=PageUtil.getWxTotalPage(totalCount);
		}
		for(Bike bike : bikeList){
			if(null!=bike.getBikeLockId()){
				BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
				bike.setLockState(bikeLockInfo.getBikeLockState());
			}
			
		}
		data.put("bikeList", bikeList);
		data.put("totalCount", totalCount);
		data.put("totalPage", totalPage);
		data.put("pageIndex", pageIndex);
		messageUtil.setData(data);
		return messageUtil;
	}
	
	
	
	/**
	 * 车辆维护搜索
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="bikeDetailSearch", method = RequestMethod.POST)
	public @ResponseBody MessageUtil bikeDetailSearch(String bikeCode,Integer pageIndex,HttpSession session) throws Exception{
		Map<String, Object> data = new HashMap<String, Object>();
		Admin admin_session = (Admin) session.getAttribute("admin");
		Admin admin = adminServiceRead.findAdminId(admin_session.getAdminId());
		List<Bike> bikeList = new ArrayList<Bike>();
		Integer totalCount = 0;
		Integer totalPage= 1;
		
		if(null==admin.getAdminChannelId()){  //超级管理员
			bikeList = bikeServiceRead.findAllFixBikes(pageIndex,AppConfig.getPage_size_weixin(),bikeCode);
			totalCount = bikeServiceRead.findAllFixBikeCount(bikeCode);
		}else{
			Channel channel = channelServiceRead.findById(admin.getAdminChannelId());
			List<Long> modelsList = modelsServiceRead.findByChannelId(channel.getChannelId());
			if(null!=modelsList){
			if(null!=channel.getChannelParentId()){
				bikeList = bikeServiceRead.findByFixChannel(pageIndex,AppConfig.getPage_size_weixin(),bikeCode,modelsList);
				totalCount = bikeServiceRead.findByFixChannelCounts(bikeCode, modelsList);
					}else{
						List<Channel> channels = channelServiceRead.findByParentChannelId(channel.getChannelId());
						if(channels.size()>0){
							List<Long> currChannelIds = new ArrayList<Long>();
							for(Channel c:channels){
								currChannelIds.add(c.getChannelId());
							}
							List<Models> modelsList2 = modelsServiceRead.findChannelIds(currChannelIds);
							List<Long> modelsList3 = new ArrayList<Long>();
							for(Models m:modelsList2){
								modelsList3.add(m.getModelsId());
							}
							modelsList.addAll(modelsList3);
						}
						
						bikeList = bikeServiceRead.findByFixChannel(pageIndex,AppConfig.getPage_size_weixin(),bikeCode,modelsList);
						totalCount = bikeServiceRead.findByFixChannelCounts(bikeCode, modelsList);
					}
		}
		}
		for(Bike bike:bikeList){
			String times = bikeFixInfoServiceRead.findMaxDate(bike.getBikeId());
			if(times!=null){
				Date lasttime = DateUtil.changStringDate02(times);
				bike.setFixLasttime(lasttime);
			}
		}
		
		if(totalCount>0){
			totalPage=PageUtil.getWxTotalPage(totalCount);
		}
		data.put("bikeList", bikeList);
		data.put("totalCount", totalCount);
		data.put("totalPage", totalPage);
		data.put("pageIndex", pageIndex);
		messageUtil.setData(data);
		return messageUtil;
	}
	
	/**
	 * 定位
	 * @param bikeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="bikePosition", method = RequestMethod.POST)
	public @ResponseBody MessageUtil bikePosition(Long bikeId) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
		data.put("bike", bike);
		messageUtil.setData(data);
		return messageUtil;
	}
	/**
	 * 开始维护
	 * @param session
	 * @param bikeId
	 * @param bikeState
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="fixBike", method = RequestMethod.POST)
	public @ResponseBody MessageUtil fixBike(HttpSession session,Long bikeId,Integer bikeState) throws Exception{
		Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
		bike.setBikeState(bikeState);
		
		Admin admin=(Admin) session.getAttribute("admin");
		BikeFixInfo bf=new BikeFixInfo();
		bf.setFixBikeId(bikeId);
		bf.setFixMan(admin.getAdminRealname());
		bf.setFixStarttime(new Date());
		bf.setFixAtitude(bike.getBikeAtitude());
		bf.setFixLongitude(bike.getBikeLongitude());
		//bf.setFixBlockId(bike.getBikeBlockId());
		bf .setFixBlock(bike.getBikeBlock());
		// 批次号 = "标志"+时间+车辆id
		String batchNumber = "X" + DateUtil.format03(new Date()) + bike.getBikeId();
		bf.setFixBatchNumber(batchNumber);
		bikeFixInfoServiceWrite.addBikeFixInfo(bf);
		bikeServiceWrite.editBike(bike);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	
	
	/**
	 * 结束维护
	 * @param session
	 * @param bikeId
	 * @param bikeState
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="finishFixBike", method = RequestMethod.POST)
	public @ResponseBody MessageUtil finishFixBike(Long bikeId,Integer bikeState) throws Exception{
		Bike bike = new Bike();
		bike.setBikeId(bikeId);
		bike.setBikeState(bikeState);
		BikeFixInfo bf= bikeFixInfoServiceRead.findBikeFixInfoByBikeIdAndNotFinish(bikeId);
		bf.setFixEndtime(new Date());
		bikeFixInfoServiceWrite.updateBikeFix(bf);
		bikeServiceWrite.editBike(bike);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 一键维护开始
	 * @param session
	 * @param bikeIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="fixAllBike", method = RequestMethod.POST)
	public @ResponseBody MessageUtil fixBikes(HttpSession session,Long[] bikeIds) throws Exception{
		
		Admin admin = (Admin) session.getAttribute("admin");
		for(int i=0;i<bikeIds.length;i++){
			Bike bike = bikeServiceRead.findBikeByBikeId(bikeIds[i]);
			if(bike.getBikeState()!=3){
				BikeFixInfo bf=new BikeFixInfo();
				bf.setFixBikeId(bikeIds[i]);
				bf.setFixMan(admin.getAdminRealname());
				bf.setFixStarttime(new Date());
				bf.setFixAtitude(bike.getBikeAtitude());
				bf.setFixLongitude(bike.getBikeLongitude());
				//bf.setFixBlockId(bike.getBikeBlockId());
				bf .setFixBlock(bike.getBikeBlock());
				// 批次号 = "标志"+时间+车辆id
				String batchNumber = "X" + DateUtil.format03(new Date()) + bike.getBikeId();
				bf.setFixBatchNumber(batchNumber);
				bikeFixInfoServiceWrite.addBikeFixInfo(bf);
			}
			
			bike.setBikeState(3);
			bikeServiceWrite.editBike(bike);
		}
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * 一键维护结束
	 * @param session
	 * @param bikeIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="finishFixAllBike", method = RequestMethod.POST)
	public @ResponseBody MessageUtil finishFixAllBike(HttpSession session,Long[] bikeIds) throws Exception{
		for(int i=0;i<bikeIds.length;i++){
			Bike bike = bikeServiceRead.findBikeByBikeId(bikeIds[i]);
			if(bike.getBikeState()==3){
				BikeFixInfo bf= bikeFixInfoServiceRead.findBikeFixInfoByBikeIdAndNotFinish(bikeIds[i]);
				if(null!=bf){
					bf.setFixEndtime(new Date());
					bikeFixInfoServiceWrite.updateBikeFix(bf);
				}
			}
			bike.setBikeState(0);
			bikeServiceWrite.editBike(bike);
		}
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 开关锁
	 * @param bikeCode
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="useLock", method = RequestMethod.POST)
	public @ResponseBody MessageUtil useLock(String bikeCode,Integer flag) throws Exception{
		Bike bike = bikeServiceRead.findBikeByEqualBikeCode(bikeCode);
		if (!sendCommand(bike,flag)) {// 开关锁
			messageUtil.setCode(0);
			messageUtil.setMessage("fail");
			return messageUtil;
		}
		BikeLockInfo lockInfo = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
		if(flag==0){
			lockInfo.setBikeLockStatus(0);
			messageUtil.setCode(1);//关锁
		}else if(flag==1){
			lockInfo.setBikeLockStatus(1);
			messageUtil.setCode(2);//开锁
		}
		bikeLockInfoServiceWrite.updateBikeLockInfo(lockInfo);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	public boolean sendCommand(Bike bike,Integer flag) throws Exception {
		BikeLockInfo lockInfo = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
		redisService.del(lockInfo.getBikeLockCode());//清除redis里的锁的lockCode
		redisService.closeJedis();
		if (0 == flag) {
			if (lockService.sendLockMessage(lockInfo.getBikeLockCode())) {// 发送关锁指令
				return true;
			}else{
				return false;
			}
		} else if (1 == flag) {
			if (lockService.sendUnlockMessage(lockInfo.getBikeLockCode())) {// 发送开锁指令
				/*if(null!=lockInfo.getBikeLockType()&&!"".equals(lockInfo.getBikeLockType())&&lockInfo.getBikeLockType()==1){
					redisService.set("admin"+lockInfo.getBikeLockCode(), "open",60000);
					redisService.closeJedis();
				}*/
				return true;
			}else{
				return false;
			}
		}
		return true;

	}
	
	/**
	 * 低电压处理
	 * @param session
	 * @param bikeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="bikeAlert", method = RequestMethod.POST)
	public @ResponseBody MessageUtil bikeAlert(HttpSession session,String bikeCode) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		Bike bike = bikeServiceRead.findBikeByEqualBikeCode(bikeCode);
		bike.setBikeAlert(3);//已处理
		bikeServiceWrite.editBike(bike);
		data.put("bike", bike);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 获取低电压
	 * @param session
	 * @param bikeCode
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="getBikeAlert", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getBikeAlert(HttpSession session,String bikeCode) throws Exception{
		Map<String, Object> data = new HashMap<String,Object>();
		Bike bike = bikeServiceRead.findBikeByEqualBikeCode(bikeCode);
		if(null!=bike.getBikeLockId()){
			BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
			lockService.sendStateMessage(bikeLockInfo.getBikeLockCode());
		}
		Thread.sleep(3000);
		Bike nowBike = bikeServiceRead.findBikeByEqualBikeCode(bikeCode);
		data.put("nowBike", nowBike);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
}
