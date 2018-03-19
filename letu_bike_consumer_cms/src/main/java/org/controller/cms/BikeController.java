package org.controller.cms;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.Bike;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.LatLng;
import org.entity.dto.Models;
import org.entity.dto.OperateLog;
import org.entity.dto.BikeRepair;
import org.entity.dto.Channel;
import org.entity.dto.FixedReturn;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeLockInfoServiceRead;
import org.service.cms.read.BikeRentInfoServiceRead;
import org.service.cms.read.BikeRepairServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.BlockServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.FixedReturnServiceRead;
import org.service.cms.read.ModelsServiceRead;
import org.service.cms.write.BikeLockInfoServiceWrite;
import org.service.cms.write.BikeRepairServiceWrite;
import org.service.cms.write.BikeServiceWrite;
import org.service.cms.write.BlockServiceWrite;
import org.service.cms.write.FixedReturnServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.service.lock.LockService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.util.BlockUtil;
import org.util.MessageUtil;
import org.util.OperateUtil;
import org.util.PageUtil;
import org.util.QRCodeUtil;
import org.util.ReadWriteExcelUtil;
import org.util.ShellUtil;


@Controller
@Scope("prototype")
@RequestMapping("cms/bike")
public class BikeController {
	
	@Resource
	BikeServiceRead bikeServiceRead;
	
	@Resource
	BikeServiceWrite bikeServiceWrite;
	
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	BikeRentInfoServiceRead bikeRentInfoServiceRead;
	
	@Resource
	BikeRepairServiceRead bikeRepairServiceRead;
	
	@Resource
	BikeRepairServiceWrite bikeRepairServiceWrite;
	
	@Resource
	BlockServiceRead blockServiceRead;
	
	@Resource
	ModelsServiceRead modelsServiceRead;
	
	@Resource
	BlockServiceWrite blockServiceWrite;
	
	@Resource
	BikeLockInfoServiceRead bikeLockInfoServiceRead;
	
	@Resource
	BikeLockInfoServiceWrite bikeLockInfoServiceWrite;
	
	@Resource
	LockService lockService;
	
	@Resource
	FixedReturnServiceRead fixedReturnServiceRead;
	
	@Resource
	FixedReturnServiceWrite fixedReturnServiceWrite;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	@Resource
	AppConfig appConfig;
	
	
	/**
	 * 车辆列表
	 * @param model
	 * @param pageIndex
	 * @param bikePutStartTime
	 * @param bikePutEndTime
	 * @param bikeputAddress
	 * @param rentaddress
	 * @param bike
	 * @param session
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("bikeList")
    public String bikeList(Model model,@RequestParam(defaultValue="1")Integer pageIndex,
    		              Date bikePutStartTime, Date bikePutEndTime, String bikeputAddress,
    		              String rentaddress,Bike bike,HttpSession session,Long channelid,
    		              Long modelsId,Integer batchNumber,Integer failFlag) throws Exception{
    	
    	//取admin的渠道id
    	Long channelId  = (Long) session.getAttribute("currChannelId");
    	/*List<Long> blockputIds = null;
    	if(null!=bikeputAddress&&!"".equals(bikeputAddress)){
			String blockCode=BlockUtil.getBlockCode5(bikeputAddress);
			blockputIds =blockServiceRead.findBlockIds(blockCode);
			if(blockputIds==null){
				blockputIds = new ArrayList<Long>();
				blockputIds.add((long) 0);
			}
		}*/
    	String blockCode = null;
    	if(null!=bikeputAddress&&!"".equals(bikeputAddress)){
			blockCode=BlockUtil.getBlockCode5(bikeputAddress);
		}
    	
    	List<Models> modelList = modelsServiceRead.findModelsByChannelId(channelId);
    	//查渠道下的所有车辆
    	List<Bike> bikeList = new ArrayList<Bike>();
    	Integer totalPage = 1;
    	List<Channel> channels = new ArrayList<Channel>();
    	List<FixedReturn> fixedReturnList = new ArrayList<FixedReturn>();
    	if(channelId==null){
    		 bikeList = bikeServiceRead.findAllBike(pageIndex,appConfig.getPage_size_web(),bike, bikePutStartTime, bikePutEndTime,blockCode,null,null,channelid,modelsId);
    	     totalPage = bikeServiceRead.countAllBike(bike,bikePutStartTime, bikePutEndTime,blockCode,null,null,channelid,modelsId);
    	     channels = channelServiceRead.findAllChannelNotfreeze(null);
    	     fixedReturnList = fixedReturnServiceRead.findAllFixed(null, null, null, null, null);
    	}else{
    		Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
    		bikeList = bikeServiceRead.findAllBike(pageIndex,appConfig.getPage_size_web(),bike, bikePutStartTime, bikePutEndTime,blockCode,currChannelIds,null,channelid,modelsId);
    	    totalPage = bikeServiceRead.countAllBike(bike,bikePutStartTime, bikePutEndTime,blockCode,currChannelIds,null,channelid,modelsId);
    	    channels = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
    	    fixedReturnList = fixedReturnServiceRead.findAllFixed(null, null, null, null, currChannelIds);
    	}
        if(totalPage==0){
        	totalPage = 1;
        }

        for(Bike b : bikeList){
        	FixedReturn fixedReturn = fixedReturnServiceRead.findFixedById(b.getBikeFixedReturnId());
        	b.setFixedReturn(fixedReturn);
        }
		model.addAttribute("channels", channels);
		model.addAttribute("fixedReturnList", fixedReturnList);
    	model.addAttribute("bikeList",bikeList);
    	model.addAttribute("pageIndex",pageIndex);
    	model.addAttribute("totalPage",totalPage);
    	model.addAttribute("bikePutStartTime",bikePutStartTime);
    	model.addAttribute("bikePutEndTime",bikePutEndTime);
    	model.addAttribute("bikeputAddress",bikeputAddress);
    	model.addAttribute("rentaddress",rentaddress);
    	model.addAttribute("bike",bike);
    	model.addAttribute("channelid",channelid);
    	model.addAttribute("modelList",modelList);
    	model.addAttribute("modelsId",modelsId);
    	model.addAttribute("batchNumber", batchNumber);
    	model.addAttribute("failFlag", failFlag);
    	return "bike_list";
    }
    
    /**
     * 删除车辆
     * @param bikeId
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteBike")
    public @ResponseBody String deleteBike(Long bikeId,HttpSession session) throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");
    	Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
    	Bike bike1 = bikeServiceRead.findBikeByBikeId(bikeId);
    	if(bike1.getBikeLockId()!=null&&!bike1.getBikeLockId().equals("")){
    		return "fail";
    	}
    	Bike bike = new Bike();
    	bike.setBikeId(bikeId);
    	bikeServiceWrite.deleteBikeById(bike);
    	
    	OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 6, bike1.getBikeCode(), null, null);
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
    	return "success";
    }
    
    /**
     * 删除所有车辆
     * @param bikeIds
     * @return
     * @throws Exception
     */
    @RequestMapping("deleteAllBike")
    public @ResponseBody String deleteAllBike(Long[] bikeIds,HttpSession session) throws Exception{
    	if(bikeIds!=null){
    		Admin admin = (Admin) session.getAttribute("admin");
        	Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
        	OperateLog operateLog = new OperateLog();
    		operateLog.setOperateTime(new Date());
    		operateLog.setOperateAdminId(nowAdmin.getAdminId());
    		String bikeCode = " ";
    		for(Long bikeId : bikeIds){
        		Bike bike = new Bike();
            	bike.setBikeId(bikeId);
            	bikeServiceWrite.deleteBikeById(bike);
            	Bike bike1 = bikeServiceRead.findBikeByBikeId(bikeId);
            	bikeCode = bikeCode + " "+bike1.getBikeCode();
    		}
    		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 20, bikeCode, null, null);
    		operateLog.setOperateRemark(remark);
    		operateServiceWrite.addOperateLogs(operateLog);
    	}
    	
    	
    	return "success";
    }
    
    /**
     * 添加页面
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("addJsp")
    public String addJsp(Model model,HttpSession session) throws Exception{
    	Long currChannelId  = (Long) session.getAttribute("currChannelId");
    	List<BikeLockInfo> bikeLockInfoList = bikeLockInfoServiceRead.findAllLock();
    	List<String> bikeBatchNumberList = bikeServiceRead.findBikeBatchNumber();
    	List<BikeRepair> bikeRepairList = bikeRepairServiceRead.findAllBikeRepair();
    	List<Models> modelsList = new ArrayList<Models>();
    	List<FixedReturn> fixedReturnList = new ArrayList<FixedReturn>();
    	if(currChannelId==null){
    		modelsList = modelsServiceRead.findAllModels(null,null,null,null,null);
    		fixedReturnList = fixedReturnServiceRead.findAllFixed(null, null, null, null, null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			modelsList = modelsServiceRead.findAllModels(null,null,null,null,currChannelIds);
			fixedReturnList = fixedReturnServiceRead.findAllFixed(null, null, null, null, currChannelIds);
		}
    	
    	model.addAttribute("modelsList", modelsList);
    	model.addAttribute("fixedReturnList", fixedReturnList);
    	model.addAttribute("bikeLockInfoList", bikeLockInfoList);
    	model.addAttribute("bikeBatchNumberList", bikeBatchNumberList);
    	model.addAttribute("bikeRepairList", bikeRepairList);
    	return "detail/bike_add";
    }
    
    /**
     * 添加
     * @param bike
     * @param putAddress
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("addBike")
    public String addBike(Bike bike,String putAddress,HttpSession session,String simCode) throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");
    	Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
    	BikeLockInfo lock = bikeLockInfoServiceRead.findByCode(bike.getLockCode());
    	if(null!=putAddress&&!"".equals(putAddress)){
			String blockCode=BlockUtil.getBlockCode(putAddress);//获取地址的区域方格
			/*Long blockputId = blockServiceRead.findBlockId(blockCode);
			if(blockputId!=null){
				bike.setBikeBlockId(blockputId);
			}*/
			bike.setBikeBlock(blockCode);
			String [] splitLatLng=putAddress.split(",");
			double lng =Double.parseDouble(splitLatLng[0]);
			double lat =Double.parseDouble(splitLatLng[1]);
			bike.setBikeLongitude(lng);
			bike.setBikeAtitude(lat);
		}
    	bike.setBikeAdminId(admin.getAdminId());
    	bike.setBikeCreatetime(new Date());
    	if(lock!=null){
    		bike.setBikeLockId(lock.getBikeLockId());
    	}
    	Long bikeId = bikeServiceWrite.addBike(bike);
    	if(lock!=null){
    		lock.setBikeLockBikeId(bikeId);
    		lock.setBikeLockSimCode(simCode);
    		bikeLockInfoServiceWrite.updateBikeLockInfo(lock);
    	}
    	
		OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 1, bike.getBikeCode(), null, null);
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
    	return "redirect:bikeList.action?batchNumber="+bike.getBikeBatchNumber();
    }
    
    /**
     * 跳转编辑页面
     * @param bikeId
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("editJsp")
    public String editJsp(Long bikeId,Model model,HttpSession session) throws Exception{
    	Long currChannelId  = (Long) session.getAttribute("currChannelId");
    	Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
    	BikeLockInfo lock = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
    	List<Models> modelsList = new ArrayList<Models>();
    	List<FixedReturn> fixedReturnList = new ArrayList<FixedReturn>();
    	if(currChannelId==null){
    		modelsList = modelsServiceRead.findAllModels(null,null,null,null,null);
    		fixedReturnList = fixedReturnServiceRead.findAllFixed(null, null, null, null, null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			modelsList = modelsServiceRead.findAllModels(null,null,null,null,currChannelIds);
			fixedReturnList = fixedReturnServiceRead.findAllFixed(null, null, null, null, currChannelIds);
		}
    	model.addAttribute("bike", bike);
    	model.addAttribute("lock", lock);
    	model.addAttribute("modelsList", modelsList);
    	model.addAttribute("fixedReturnList", fixedReturnList);
    	return "detail/bike_edit";
    }
    
    /**
     * 编辑车辆
     * @param bike
     * @param managerTel
     * @return
     * @throws Exception
     */
    @RequestMapping("editBike")
    public String editBike(Bike bike,String managerTel,HttpSession session,String simCode) throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");
    	Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
    	Bike bike2 = bikeServiceRead.findBikeByBikeId(bike.getBikeId());
    	if(null!=bike.getLockCode()&&!bike.getLockCode().equals("")){
    		BikeLockInfo lock = bikeLockInfoServiceRead.findByCode(bike.getLockCode());
    		if(lock!=null){
    			bike2.setBikeLockId(lock.getBikeLockId());
        		lock.setBikeLockBikeId(bike.getBikeId());
        		lock.setBikeLockSimCode(simCode);
        		bikeLockInfoServiceWrite.updateBikeLockInfo(lock);
        	}else{
        		bike2.setBikeLockId(null);
        	}
    	}else{
    		bike2.setBikeLockId(null);
    	}
    	bike2.setBikeModelsId(bike.getBikeModelsId());
    	bike2.setBikeManagerName(bike.getBikeManagerName());
    	bike2.setBikeManagerTel(bike.getBikeManagerTel());
    	bike2.setBikeFixedReturnId(bike.getBikeFixedReturnId());
    	bikeServiceWrite.updateBike(bike2);
    	
    	OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 4, bike2.getBikeCode(), null, null);
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
    	return "redirect:bikeList.action";
    }
    
    /**
     * 跳转修改所有车辆页面
     * @param bikeIds
     * @param model
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("editAllJsp")
    public String editAllJsp(Long[] bikeIds,Model model,HttpSession session) throws Exception{
    	Long currChannelId  = (Long) session.getAttribute("currChannelId");
    	List<Models> modelsList = new ArrayList<Models>();
    	if(currChannelId==null){
    		modelsList = modelsServiceRead.findAllModels(null,null,null,null,null);
		}else{
			Channel channel =channelServiceRead.findById(currChannelId);
			List<Channel> channels = channelServiceRead.findSonChannels(currChannelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
			modelsList = modelsServiceRead.findAllModels(null,null,null,null,currChannelIds);
		}
    	List<Long> bikeArray = new ArrayList<Long>();
    	if(bikeIds!=null){
    		for(int i=0;i<bikeIds.length;i++){
    			bikeArray.add(bikeIds[i]);
    		}
    	}
    	model.addAttribute("bikeArray", bikeArray);
    	model.addAttribute("modelsList", modelsList);
    	return "detail/bike_all_edit";
    }
    
    /**
     * 编辑所有车
     * @param bikeIds
     * @param bike
     * @return
     * @throws Exception
     */
    @RequestMapping("editAllBike")
    public @ResponseBody String editAllBike(Long[] bikeIds,Bike bike,HttpSession session) throws Exception{
    	if(bikeIds!=null){
    		Admin admin = (Admin) session.getAttribute("admin");
        	Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
        	OperateLog operateLog = new OperateLog();
    		operateLog.setOperateTime(new Date());
    		operateLog.setOperateAdminId(nowAdmin.getAdminId());
    		String bikeCode = " ";
    		for(Long bikeId : bikeIds){
    			bike.setBikeId(bikeId);
    	        bikeServiceWrite.editBike(bike);
    	        Bike bike1 = bikeServiceRead.findBikeByBikeId(bikeId);
    	        bikeCode = bikeCode + " " +bike1.getBikeCode();
    	    	
    		}
    		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 21, bikeCode, null, null);
    		operateLog.setOperateRemark(remark);
    		operateServiceWrite.addOperateLogs(operateLog);
    	}
    	return "success";
    }
    
    /**
     * 车辆详情
     * @param bikeId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("bikeDetail")
    public String bikeDetail(Long bikeId,Model model) throws Exception{
    	Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
    	if(null!=bike.getBikeLockId()){
    		BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
    		if(null!=bikeLockInfo){
    			model.addAttribute("lockStatus", bikeLockInfo.getBikeLockStatus());
    		}
    	}
    	model.addAttribute("bike", bike);
    	return "detail/bike_detail";
    }
    /**
     * 车辆位置
     * @param bikeId
     * @param model
     * @param positionx
     * @param positiony
     * @return
     * @throws Exception
     */
    @RequestMapping("bikePosition")
    public String bikePosition(Long bikeId,Model model) throws Exception{
    	Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
    	model.addAttribute("bike", bike);
    	model.addAttribute("positionx", bike.getBikeLongitude());
    	model.addAttribute("positiony", bike.getBikeAtitude());
    	return "detail/bike_position";
    }
    
    /**
     *立即定位
     * @param bikeId
     * @param model
     * @param positionx
     * @param positiony
     * @return
     * @throws Exception
     */
    @RequestMapping("nowPosition")
    public String nowPosition(Long bikeId,Model model,HttpSession session) throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");
    	Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
    	Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
    	if(null!=bike.getBikeLockId()){
    		BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
    		if(lockService.judgeLockConnet(bikeLockInfo.getBikeLockCode())){
				//发送定位
				lockService.sendLocationMessage(bikeLockInfo.getBikeLockCode());
			}
    	}
    	Thread.sleep(2000);//2秒
    	Bike nowBike = bikeServiceRead.findBikeByBikeId(bikeId);
    	Double positionx = nowBike.getBikeLongitude();
    	Double positiony = nowBike.getBikeAtitude();		
    	model.addAttribute("bike", nowBike);
    	model.addAttribute("positionx", positionx);
    	model.addAttribute("positiony", positiony);
    	
    	OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 5, bike.getBikeCode(), null, null);
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
    	
    	return "detail/bike_position";
    }
    /**
     * 更新车辆状态
     * @param bikeId
     * @param bikeState
     * @return
     * @throws Exception
     */
    @RequestMapping("updateBikeState")
    public String updateBikeState(Long bikeId,Integer bikeState,HttpSession session) throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");
    	Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
    	OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
    	Bike nowBike = bikeServiceRead.findBikeByBikeId(bikeId);
    	Bike bike = new Bike();
    	bike.setBikeId(bikeId);
    	bike.setBikeState(bikeState);
    	bikeServiceWrite.editBike(bike);
    	String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 18, nowBike.getBikeCode(), null, null);
		operateLog.setOperateRemark(remark);
		 operateServiceWrite.addOperateLogs(operateLog);
    	return "redirect:bikeList.action";
    }
    
    @RequestMapping("updateBikeJsp")
    public String updateBikeJsp(Long bikeId,Model model,Integer bikeState) throws Exception{
    	
    	Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
    	model.addAttribute("bike", bike);
    	model.addAttribute("bikeState", bikeState);
    	return "detail/bikeState_edit";
    }
    
    /**
     * 更新所有车辆状态
     * @param bikeIds
     * @param bikeState
     * @return
     * @throws Exception
     */
    @RequestMapping("updateAllBike")
    public @ResponseBody String updateAllBike(Long[] bikeIds,Integer bikeState,HttpSession session) throws Exception{
    	if(bikeIds!=null){
    		Admin admin = (Admin) session.getAttribute("admin");
        	Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
        	OperateLog operateLog = new OperateLog();
    		operateLog.setOperateTime(new Date());
    		operateLog.setOperateAdminId(nowAdmin.getAdminId());
    		String bikeCode = " ";
    		for(Long bikeId : bikeIds){
        		Bike bike = new Bike();
            	bike.setBikeId(bikeId);
            	bike.setBikeState(bikeState);
            	bikeServiceWrite.editBike(bike);
            	Bike bike1 = bikeServiceRead.findBikeByBikeId(bikeId);
            	bikeCode = bikeCode +" "+ bike1.getBikeCode();
        	}
    		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 19, bikeCode, null, null);
        		operateLog.setOperateRemark(remark);
        		operateServiceWrite.addOperateLogs(operateLog);
    	}
    	
    	return "success";
    }
    
    /**
	 * 导入excel
	 * @param model
	 * @return
	 * @throws Exception
	 */
	//@RequiresPermissions("stu_importBtn")
	@RequestMapping("importBike")
	public String importBike(Model model,HttpSession session, MultipartFile excel,Long bikeChannelId,String bikeAddressText, String bikePutAddress, Date bikePutTime,Long bikeModelsId,Long fixedReturnId) throws Exception{
		
		if (!excel.isEmpty()) {//不为空
			Admin admin = (Admin) session.getAttribute("admin");//获取admin
			Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
			String maxBatchNumber = bikeServiceRead.findMaxBikeBatchNumber();//查询最大批次号
			Integer maxNumber = 0;
			if(maxBatchNumber!=null){
				maxNumber = Integer.valueOf(maxBatchNumber);
			}
			String blockCode=BlockUtil.getBlockCode(bikePutAddress);//获取区域方格
			/*Block block = new Block();
			block.setBlockCode(blockCode);
			Long blockputId = blockServiceWrite.addBlock(block);*/
			String [] splitLatLng=bikePutAddress.split(",");
			double lng =Double.parseDouble(splitLatLng[0]);
			double lat =Double.parseDouble(splitLatLng[1]);
			InputStream inputStream = excel.getInputStream();//获取流
			String filename = excel.getOriginalFilename();//文件名
			String extensionName = filename.substring(filename.lastIndexOf(".")+1, filename.length());
			List<Bike> list = ReadWriteExcelUtil.exportListFromExcel(inputStream, extensionName, 0);//读取车辆列表
			for(Bike b : list){
				Bike bike2 = bikeServiceRead.findBikeByEqualBikeCode(b.getBikeCode());
				if(bike2!=null){
					return "redirect:bikeList.action?failFlag=1";
				}
			}
			for (Bike bike : list) {
				BikeLockInfo lock = bikeLockInfoServiceRead.findByCode(bike.getLockCode().trim());
				//bike.setBikeChannelId(bikeChannelId);
				if(lock!=null){
					bike.setBikeLockId(lock.getBikeLockId());
				}
				bike.setBikePutTime(bikePutTime);
				//bike.setBikeBlockId(blockputId);
				bike.setBikeBlock(blockCode);
				bike.setBikeCreatetime(new Date());
				bike.setBikeFixedReturnId(fixedReturnId);
				bike.setBikeLongitude(lng);
				bike.setBikeAtitude(lat);
				bike.setBikeAddress(bikeAddressText);
				bike.setBikeModelsId(bikeModelsId);
				bike.setBikeAdminId(admin.getAdminId());
				bike.setBikeBatchNumber(maxNumber+1+"");
				Long bikeId = bikeServiceWrite.addBike(bike);
				if(lock!=null){
					lock.setBikeLockBikeId(bikeId);
					lock.setBikeLockSimCode(bike.getSimCode().trim());
					bikeLockInfoServiceWrite.updateBikeLockInfo(lock);
				}
				
			}
			OperateLog operateLog = new OperateLog();
			String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(),2, null, null, null);
			operateLog.setOperateRemark(remark);
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			operateServiceWrite.addOperateLogs(operateLog);
			
			return "redirect:bikeList.action?batchNumber="+(maxNumber+1);
		}
		return "redirect:bikeList.action";
	}
	
	@RequestMapping(value = "/findBikes", method = RequestMethod.POST)
	public @ResponseBody List<LatLng> findBikes(String latLng) throws Exception{
		List<String> blockCodes=BlockUtil.getBlockCodeFor9(latLng);
		//List<Long> bIds =blockServiceRead.findBlockIdsByCodes(blockCodes);
		List<LatLng> bikeList=bikeServiceRead.findByBlockIds(blockCodes);
		return bikeList;
	}
	
	/**
	 * 查询站点附近的车
	 * @param latLng
	 * @param fixedReturnId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findFixedBikes", method = RequestMethod.POST)
	public @ResponseBody List<LatLng> findBikes(String latLng,Long fixedReturnId) throws Exception{
		/*List<String> blockCodes=BlockUtil.getBlockCodeFor9(latLng);
		List<Long> bIds =blockServiceRead.findBlockIdsByCodes(blockCodes);
		List<LatLng> bikeList=bikeServiceRead.findByBlockIdsAndFixed(bIds,fixedReturnId);*/
		List<LatLng> bikeList=bikeServiceRead.findByfixedReturnId(fixedReturnId);
		return bikeList;
	}
	
	/**
	 * 最大批次号
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/maxBatchNumber", method = RequestMethod.POST)
	public @ResponseBody Integer maxBatchNumber(Long bikeId) throws Exception{
		String batchNumber = bikeServiceRead.findMaxBikeBatchNumber();
		Integer max = 0;
		if(batchNumber!=null){
			max = Integer.valueOf(batchNumber);
		}
		
		return max+1;
	}
	
	/**
	 * 检验车型名是否重复
	 * @param bikeModelsName
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("checkModelName")
	public @ResponseBody String checkModelName(String bikeModelsName,HttpSession session) throws Exception{
		//取admin的渠道id
    	Long channelId  = (Long) session.getAttribute("currChannelId");
    	List<Models> modelList = new ArrayList<>();
    	if(channelId==null){
    		modelList = modelsServiceRead.findAllModels(null, null, null, null, null);
    	}else{
    		Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels = channelServiceRead.findSonChannels(channelId);
			channels.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels){
				currChannelIds.add(c.getChannelId());
			}
    		modelList = modelsServiceRead.findAllModels(null, null, null, null, currChannelIds); 
    	}
		List<String> arr = new ArrayList<String>();
		
		for(Models models : modelList){
			arr.add(models.getModelsName());
		}
		if(arr.contains(bikeModelsName)){
			return "success";
		}
		return "fail";
	}
	
	
	/**
	 * 车辆列表
	 * @param model
	 * @param pageIndex
	 * @param bikePutStartTime
	 * @param bikePutEndTime
	 * @param bikeputAddress
	 * @param rentaddress
	 * @param bike
	 * @param session
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("bikeQrList")
    public String bikeQrList(String bikeLockCode,Model model,@RequestParam(defaultValue="1")Integer pageIndex,Bike bike,HttpSession session,Long channelid) throws Exception{
    	
    	//取admin的渠道id
    	Long channelId  = (Long) session.getAttribute("currChannelId");
    	Integer totalCount = 0;
    	List<Models> modelList = modelsServiceRead.findModelsByChannelId(channelId);
    	//查渠道下的所有车辆
    	List<Bike> bikeList = new ArrayList<Bike>();
    	Integer totalPage = 1;
    	List<Channel> channels = new ArrayList<Channel>();
    	if(channelId==null){
    		 bikeList = bikeServiceRead.findBikeQrList(pageIndex,appConfig.getPage_size_web(),bike,bikeLockCode,channelid,null);
    		 totalCount = bikeServiceRead.countBikeQrList(bike,bikeLockCode,channelid,null);
    	     channels = channelServiceRead.findAllChannelNotfreeze(null);
    	}else{
    		Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			 bikeList = bikeServiceRead.findBikeQrList(pageIndex,appConfig.getPage_size_web(),bike,bikeLockCode,channelid,currChannelIds);
			 totalCount = bikeServiceRead.countBikeQrList(bike,bikeLockCode,channelid,currChannelIds);
    	    channels = channelServiceRead.findAllChannelNotfreeze(currChannelIds);
    	}
    	if(totalCount>0){
    		totalPage = PageUtil.getTotalPage(totalCount);
    	}
    	model.addAttribute("bikeLockCode", bikeLockCode);
		model.addAttribute("channels", channels);
    	model.addAttribute("bikeList",bikeList);
    	model.addAttribute("pageIndex",pageIndex);
    	model.addAttribute("totalPage",totalPage);
    	model.addAttribute("bike",bike);
    	model.addAttribute("channelid",channelid);
    	model.addAttribute("modelList",modelList);
    	return "bike_qr_list";
    }
    
 
    /**
     * 导出二维码
     * @param model
     * @param pageIndex
     * @param bikePutStartTime
     * @param bikePutEndTime
     * @param bikeputAddress
     * @param rentaddress
     * @param bike
     * @param session
     * @param channelid
     * @param modelsState
     * @param batchNumber
     * @return
     * @throws Exception
     */
    @RequestMapping("importQr")
    public @ResponseBody MessageUtil importQr(String bikeLockCode,Model model,@RequestParam(defaultValue="1")Integer pageIndex,Bike bike,HttpSession session,Long channelid,String batchNumber) throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");//获取admin
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
    	MessageUtil messageUtil = new MessageUtil();
    	//取admin的渠道id
    	Long channelId  = (Long) session.getAttribute("currChannelId");
    	//查渠道下的所有车辆
    	List<Bike> bikeList = new ArrayList<Bike>();
    	if(channelId==null){
    		 bikeList = bikeServiceRead.findBikeQrList(pageIndex,appConfig.getPage_size_web(),bike,bikeLockCode,channelid,null);
    	}else{
    		Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			 bikeList = bikeServiceRead.findBikeQrList(pageIndex,appConfig.getPage_size_web(),bike,bikeLockCode,channelid,currChannelIds);
    	}
    	File file =new File("/home/backserver/qrcode");    
    	//如果文件夹存在则删除 
    	if  (file .exists()  && file .isDirectory())  {    
    		//调用shell脚本
    		ShellUtil.runShell("/home/backserver/removeQRcode");
    	}  
    	String targetPath = "/home/backserver/qrcode";
    	Thread.sleep(5000);
    	if(bikeList.size()>0){
       //循环生成二维码
    	for(Bike b:bikeList){
    		//String text = "http://weixin.qq.com/r/fUTg_GDEgD3-rSSb9xFv";
    		String text = "https://wechat.letulife.com/html/watchbike.html";
    		System.out.println("==============================生成图片");
			//获取二维码对象
			BufferedImage image = QRCodeUtil.toBufferedImage(text
					+ "?bike=" + b.getBikeCode(),260,260);
			System.out.println("==============================加文字");
			//生成含背景图+二维码的立牌的图
			 QRCodeUtil.markImageByCode(image, "/home/backserver/quanyu.png",
					 targetPath + "/" + b.getBikeCode() + ".png", 70, 70);
			//立牌的图加上code编号
			 QRCodeUtil.pressText(b.getBikeCode(), targetPath + "/" + b.getBikeCode() + ".png", targetPath
					+ "/" + b.getBikeCode() + ".png", 420, 310, 1f);
    		//ZXingCode.getLogoQRCode("http://wechat.letulife.com/html/bike.html?bike="+b.getBikeCode(), b.getBikeCode(),b.getBikeCode());
    	}
			ShellUtil.runShell("/home/backserver/zipQRcode");
    	}
    	messageUtil.setMessage("success");
    	OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(),17, null, null, null);
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
		
    	return messageUtil;
    }
   
    /**
     * 检验车和锁
     * @param bikeCode
     * @param lockCode
     * @param flag
     * @param bikeId
     * @return
     * @throws Exception
     */
    @RequestMapping("checkBikeCode")
    public @ResponseBody String checkBikeCode(String bikeCode,String lockCode,Integer flag,Long bikeId) throws Exception{
    	if(flag==1){//添加车
    		Bike bike = bikeServiceRead.findBikeByEqualBikeCode(bikeCode);
    		if(lockCode!=null&&!lockCode.equals("")){
    			BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findByCode(lockCode);
            	if(bikeLockInfo==null){
            		return "failLock";
            	}else{
            		if(null!=bikeLockInfo.getBikeLockBikeId()){
            			Bike bike1 = bikeServiceRead.findBikeByIdAndDel(bikeLockInfo.getBikeLockBikeId());
                    	if(bike1!=null){
                    		return "failLockBike";
                    	}
            		}
            		
            	}
    		}
        	
        	if(bike!=null){
        		return "fail";
        	}
    	}else if(flag==2){//编辑车
    		Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
    		BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(bike.getBikeLockId());
    		if(!lockCode.equals("")){
    			if(bikeLockInfo!=null){
        			if(!bikeLockInfo.getBikeLockCode().equals(lockCode)){
        				BikeLockInfo bikeLockInfo1 = bikeLockInfoServiceRead.findByCode(lockCode);
        				if(bikeLockInfo1==null){//不存在该锁
        					return "failLock";
        				}
        				Bike bike1 = bikeServiceRead.findBikeByIdAndDel(bikeLockInfo1.getBikeLockBikeId());
        				if(bike1!=null){//锁已关联其他车
                    		return "failLockBike";
                    	}
        			}
        		}else{
        			BikeLockInfo bikeLockInfo1 = bikeLockInfoServiceRead.findByCode(lockCode);
    				if(bikeLockInfo1==null){//不存在该锁
    					return "failLock";
    				}
    				if(null!=bikeLockInfo1.getBikeLockBikeId()){
    					Bike bike1 = bikeServiceRead.findBikeByIdAndDel(bikeLockInfo1.getBikeLockBikeId());
        				if(bike1!=null){//锁已关联其他车
                    		return "failLockBike";
                    	}
    				}
    				
        		}
    		}
    		
    	}
    	return "success";
    }
    
    //校准车辆站点信息
    @RequestMapping("checkBikeOfFixed")
    public @ResponseBody String checkBikeOfFixed(HttpSession session, Date bikePutStartTime, Date bikePutEndTime, String bikeputAddress,
            String rentaddress,Bike bike,Long channelid, Long modelsId) throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");
    	Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
    	//取admin的渠道id
    	Long channelId  = (Long) session.getAttribute("currChannelId");
    	//查渠道下的所有车辆
    	List<Bike> bikeList = new ArrayList<Bike>();
    	String blockCode = null;
    	if(null!=bikeputAddress&&!"".equals(bikeputAddress)){
			blockCode=BlockUtil.getBlockCode5(bikeputAddress);
		}
    	//List<FixedReturn> fixedReturns = new ArrayList<FixedReturn>();
    	if(channelId==null){
    		//fixedReturns = fixedReturnServiceRead.findByChannelIds(null);
    		bikeList = bikeServiceRead.findAllBikeNoPage(bike, bikePutStartTime, bikePutEndTime, blockCode, null, channelid, modelsId);
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
			
			bikeList = bikeServiceRead.findAllBikeNoPage(bike, bikePutStartTime, bikePutEndTime, blockCode, currChannelIds, channelid, modelsId);
			//fixedReturns = fixedReturnServiceRead.findByChannelIds(currChannelIds);
			for(Bike b: bikeList){
    			if(null!=b.getBikeLockId()){
    				BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(b.getBikeLockId());
    				if(lockService.judgeLockConnet(bikeLockInfo.getBikeLockCode())){
    					lockService.sendLocationMessage(bikeLockInfo.getBikeLockCode());
    				}
    			}else{
    				b.setBikeFixedReturnId((long)0);
    				bikeServiceWrite.editBike(b);
    			}
    			
    		}
    	}
    	
    	/*for(FixedReturn fr : fixedReturns){
    		List<Bike> bike = bikeServiceRead.findByFixedReturnId(fr.getFixedReturnId());//查询站点下的所有车
    		fr.setFixedReturnBikeNum(bike.size());
    		fixedReturnServiceWrite.editFixedReturn(fr);
    	}*/
    	
    	OperateLog operateLog = new OperateLog();
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(),3, null, null, null);
		operateLog.setOperateRemark(remark);
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		operateServiceWrite.addOperateLogs(operateLog);
    	return "success";
    }
}
