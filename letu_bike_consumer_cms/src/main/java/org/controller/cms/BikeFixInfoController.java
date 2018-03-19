package org.controller.cms;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.entity.dto.Admin;
import org.entity.dto.Bike;
import org.entity.dto.BikeFixInfo;
import org.entity.dto.Channel;
import org.entity.dto.OperateLog;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeFixInfoServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.BlockServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.write.BikeFixInfoServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.util.BlockUtil;
import org.util.OperateUtil;
import org.util.ReadWriteExcelUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/bikeFixInfo")
public class BikeFixInfoController {
	
	@Resource
	BikeFixInfoServiceRead bikeFixInfoServiceRead;
	
	@Resource
	BikeFixInfoServiceWrite bikeFixInfoServiceWrite;
	
	@Resource
	BikeServiceRead bikeServiceRead;
	
	@Resource
	BlockServiceRead blockServiceRead;
	
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	/**
	 * 维护列表
	 * @param model
	 * @param pageIndex
	 * @param fixBatch
	 * @param bikeFixStartTime
	 * @param bikeFixStartTimeB
	 * @param bikeFixEndTime
	 * @param bikeFixEndTimeB
	 * @param bikeFixPerson
	 * @param bikecode
	 * @param bikeFixAddress
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bikeFixInfoList")
	public String bikeFixInfoList(HttpSession session , Model model,@RequestParam(defaultValue="1")Integer pageIndex,String fixBatch,
			                      Date bikeFixStartTime,Date bikeFixStartTimeB,Date bikeFixEndTime,Date bikeFixEndTimeB,String bikeFixPerson,String bikecode,
			                      String bikeFixAddress) throws Exception{
		
		//获取admin的渠道id
		Long channelId = (Long) session.getAttribute("currChannelId");
		/*List<Long> blockFixIds = null;
    	if(null!=bikeFixAddress&&!"".equals(bikeFixAddress)){
			String blockCode = BlockUtil.getBlockCode5(bikeFixAddress);
			blockFixIds = blockServiceRead.findBlockIds(blockCode);
			if(blockFixIds==null){
				blockFixIds = new ArrayList<Long>();
				blockFixIds.add((long)0);
			}
			
		}*/
		String blockCode = null;
    	if(null!=bikeFixAddress&&!"".equals(bikeFixAddress)){
			blockCode=BlockUtil.getBlockCode5(bikeFixAddress);
		}
    	
    	List<BikeFixInfo> bikeFixInfoList = new ArrayList<BikeFixInfo>();
    	Integer totalPage = 1;
    	if(channelId==null){
    		bikeFixInfoList = bikeFixInfoServiceRead.findAllBikeFixInfo(null,pageIndex, fixBatch, bikecode, bikeFixStartTime,bikeFixStartTimeB, bikeFixEndTime, bikeFixEndTimeB, bikeFixPerson,blockCode);
    		totalPage = bikeFixInfoServiceRead.countAllBikeFixInfo(null,fixBatch, bikecode,bikeFixStartTime,bikeFixStartTimeB, bikeFixEndTime, bikeFixEndTimeB, bikeFixPerson,blockCode);
   	    }else{
   		    Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			bikeFixInfoList = bikeFixInfoServiceRead.findAllBikeFixInfo(currChannelIds,pageIndex, fixBatch, bikecode, bikeFixStartTime,bikeFixStartTimeB, bikeFixEndTime, bikeFixEndTimeB, bikeFixPerson,blockCode);
			totalPage = bikeFixInfoServiceRead.countAllBikeFixInfo(currChannelIds,fixBatch, bikecode,bikeFixStartTime,bikeFixStartTimeB, bikeFixEndTime, bikeFixEndTimeB, bikeFixPerson,blockCode);
   	    }
    	if(totalPage==0){
        	totalPage = 1;
        }
		model.addAttribute("bikeFixInfoList", bikeFixInfoList);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("bikeFixStartTime", bikeFixStartTime);
		model.addAttribute("bikeFixEndTime", bikeFixEndTime);
		model.addAttribute("bikeFixStartTimeB", bikeFixStartTimeB);
		model.addAttribute("bikeFixEndTimeB", bikeFixEndTimeB);
		model.addAttribute("bikeFixPerson", bikeFixPerson);
		model.addAttribute("bikeFixAddress", bikeFixAddress);
		model.addAttribute("bikecode", bikecode);
		model.addAttribute("fixBatch", fixBatch);
		return "bikeFixInfo_list";
	}
	/**
	 * 维护详情列表
	 * @param model
	 * @param pageIndex
	 * @param bikeId
	 * @param fixBatch
	 * @param bikeFixStartTime
	 * @param bikeFixStartTimeB
	 * @param bikeFixEndTime
	 * @param bikeFixEndTimeB
	 * @param bikeFixPerson
	 * @param bikeFixAddress
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bikeFixInfoDetailList")
	public String bikeFixInfoDetailList(Model model,@RequestParam(defaultValue="1")Integer pageIndex,Long bikeId,
			                      String fixBatch,Date bikeFixStartTime,Date bikeFixStartTimeB,Date bikeFixEndTime,Date bikeFixEndTimeB,String bikeFixPerson,
			                      String bikeFixAddress) throws Exception{
		/*List<Long> blockFixIds = null;
		if(null!=bikeFixAddress&&!"".equals(bikeFixAddress)){
			String blockCode=BlockUtil.getBlockCode5(bikeFixAddress);
			blockFixIds=blockServiceRead.findBlockIds(blockCode);
			if(blockFixIds==null){
				blockFixIds = new ArrayList<Long>();
				blockFixIds.add((long)0);
			}
		}*/
		
		String blockCode = null;
    	if(null!=bikeFixAddress&&!"".equals(bikeFixAddress)){
			blockCode=BlockUtil.getBlockCode5(bikeFixAddress);
		}
		List<BikeFixInfo> bikeFixInfoList = bikeFixInfoServiceRead.findBikeFixInfoBybikeId( bikeId,fixBatch,pageIndex, bikeFixStartTime,bikeFixStartTimeB, bikeFixEndTime, bikeFixEndTimeB, bikeFixPerson, blockCode);
		Integer totalPage = bikeFixInfoServiceRead.countBikeFixInfoBybikeId(bikeId,fixBatch, bikeFixStartTime,bikeFixStartTimeB, bikeFixEndTime, bikeFixEndTimeB, bikeFixPerson,blockCode);
		if(totalPage==0){
        	totalPage = 1;
        }
		Bike bike = bikeServiceRead.findBikeByBikeId(bikeId);
		model.addAttribute("bikeFixInfoList", bikeFixInfoList);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("bike", bike);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("fixBatch", fixBatch);
		model.addAttribute("bikeFixStartTime", bikeFixStartTime);
		model.addAttribute("bikeFixEndTime", bikeFixEndTime);
		model.addAttribute("bikeFixStartTimeB", bikeFixStartTimeB);
		model.addAttribute("bikeFixEndTimeB", bikeFixEndTimeB);
		model.addAttribute("bikeFixPerson", bikeFixPerson);
		model.addAttribute("bikeFixAddress", bikeFixAddress);
		return "bikeFixInfo_detail_list";
	}
	
	/**
	 * 跳转添加页
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addJsp")
	public String addJsp(Model model) throws Exception{
		List<String> bikeBatchNumberList = bikeFixInfoServiceRead.findBatchNumbers();
		model.addAttribute("bikeBatchNumberList", bikeBatchNumberList);
		return "detail/bikeFixInfo_add";
	}
	
	/**
	 * 添加
	 * @param model
	 * @param bikeCode
	 * @param bikeFixInfo
	 * @param fixAddress
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addBikeFixInfo")
	public String addBikeFixInfo(Model model,String bikeCode,BikeFixInfo bikeFixInfo,String fixAddress) throws Exception{
		Bike bike = bikeServiceRead.findBikeByEqualBikeCode(bikeCode);
		String batchNumber = bikeFixInfoServiceRead.findMaxBatchNumber();
		Integer max = 0;
		if(batchNumber!=null){
			max = Integer.valueOf(batchNumber);
		}
		if(null!=fixAddress&&!fixAddress.equals("")){
			String blockCode=BlockUtil.getBlockCode(fixAddress);//获取地址的区域方格
			bikeFixInfo.setFixBlock(blockCode);
			String [] splitLatLng=fixAddress.split(",");
			double lng =Double.parseDouble(splitLatLng[0]);
			double lat =Double.parseDouble(splitLatLng[1]);
			bikeFixInfo.setFixLongitude(lng);
			bikeFixInfo.setFixAtitude(lat);
		}
		
		bikeFixInfo.setFixBikeId(bike.getBikeId());
		bikeFixInfo.setFixBatchNumber(max+1+"");
		bikeFixInfoServiceWrite.addBikeFixInfo(bikeFixInfo);
		return "redirect:bikeFixInfoList.action";
	}
	
	/**
	 * 还车点位置
	 * @param bikeFixId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("bikeFixPosition")
	public String bikeFixPosition(Long bikeFixId,Model model)throws Exception{
		BikeFixInfo bikeFixInfo = bikeFixInfoServiceRead.findBikeFixInfoByFixId(bikeFixId);
		model.addAttribute("bikeFixInfo", bikeFixInfo);
		return "detail/bikeFix_position";
	}
	
	/**
	 * 删除维护信息
	 * @param bikeFixInfoId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteBikeFixInfo")
	public @ResponseBody String deleteBikeFixInfo(Long bikeFixInfoId,HttpSession session) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		BikeFixInfo nowbikeFixInfo = bikeFixInfoServiceRead.findBikeFixInfoByFixId(bikeFixInfoId);
		Bike bike = bikeServiceRead.findBikeByBikeId(nowbikeFixInfo.getFixBikeId());
		BikeFixInfo bikeFixInfo = new BikeFixInfo();
		bikeFixInfo.setFixId(bikeFixInfoId);
		bikeFixInfoServiceWrite.deleteBikeFixInfoById(bikeFixInfo);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 7, bike.getBikeCode(), null, null);
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		
		return "success";
	}
	
	/**
	 * 维护详情的删除
	 * @param bikeFixInfoId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteBikeFixInfoDetail")
	public @ResponseBody String deleteBikeFixInfoDetail(Long bikeFixInfoId,HttpSession session) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		BikeFixInfo nowbikeFixInfo = bikeFixInfoServiceRead.findBikeFixInfoByFixId(bikeFixInfoId);
		Bike bike = bikeServiceRead.findBikeByBikeId(nowbikeFixInfo.getFixBikeId());
		BikeFixInfo bikeFixInfo = bikeFixInfoServiceRead.findBikeFixInfoByFixId(bikeFixInfoId);
		bikeFixInfo.setFixId(bikeFixInfoId);
		bikeFixInfoServiceWrite.deleteBikeFixInfoById(bikeFixInfo);
		
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 7, bike.getBikeCode(), null, null);
		operateLog.setOperateRemark(remark);
		operateServiceWrite.addOperateLogs(operateLog);//操作日志
		return "success";
	}
	
	/**
	 * 批量删除
	 * @param bikeFixInfoIds
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("deleteAllBikeFixInfoDetail")
	public @ResponseBody String deleteAllBikeFixInfoDetail(Long[] bikeFixInfoIds,HttpSession session) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		if(bikeFixInfoIds!=null){
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			String bikeCodes = "";
			for(Long bikeFixId : bikeFixInfoIds){
				BikeFixInfo nowbikeFixInfo = bikeFixInfoServiceRead.findBikeFixInfoByFixId(bikeFixId);
				Bike bike = bikeServiceRead.findBikeByBikeId(nowbikeFixInfo.getFixBikeId());
				BikeFixInfo bikeFixInfo = new BikeFixInfo();
				bikeFixInfo.setFixId(bikeFixId);
				bikeFixInfoServiceWrite.deleteBikeFixInfoById(bikeFixInfo);
				bikeCodes = bikeCodes + "  " + bike.getBikeCode();
			}
			
			String remark = OperateUtil.operateBike(nowAdmin.getAdminRealname(), 8, bikeCodes, null, null);
			operateLog.setOperateRemark(remark);
			operateServiceWrite.addOperateLogs(operateLog);//操作日志
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
	@RequestMapping("importBikeFixInfo")
	public String importStudent(Model model,HttpSession session, MultipartFile excel) throws Exception{
		Integer maxNumber = 0;
		String maxBatchNumber = bikeFixInfoServiceRead.findMaxBatchNumber();
		if(maxBatchNumber!=null){
			maxNumber = Integer.valueOf(maxBatchNumber);
		}
		if (!excel.isEmpty()) {
			InputStream inputStream = excel.getInputStream();
			String filename = excel.getOriginalFilename();
			String extensionName = filename.substring(filename.lastIndexOf(".")+1, filename.length());
			List<BikeFixInfo> list = ReadWriteExcelUtil.exportListExcel(inputStream, extensionName, 0);
			for (BikeFixInfo bikeFixInfo : list) {
				//Bike bike = bikeServiceRead.findBikeByEqualBikeCode(bikeFixInfo.getBikecode());
				bikeFixInfo.setFixBatchNumber(maxNumber+1+"");
				//bikeFixInfo.setFixBikeId(bike.getBikeId());
				bikeFixInfoServiceWrite.addBikeFixInfo(bikeFixInfo);
			}
		}
		return "redirect:bikeList.action";
	}
	
	/**
	 * 最大批次号
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/maxBatchNumber", method = RequestMethod.POST)
	public @ResponseBody Integer maxBatchNumber() throws Exception{
		String batchNumber = bikeFixInfoServiceRead.findMaxBatchNumber();
		Integer max = 0;
		if(batchNumber!=null){
			max = Integer.valueOf(batchNumber);
		}
		
		return max+1;
	}
}
