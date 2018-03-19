package org.controller.cms;

import java.io.InputStream;
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
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeLockInfoServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.BlockServiceRead;
import org.service.cms.read.ModelsServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.BikeLockInfoServiceWrite;
import org.service.cms.write.BikeServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.service.lock.LockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.util.MessageUtil;
import org.util.OperateUtil;
import org.util.PageUtil;
import org.util.ReadWriteExcelUtil;
import org.util.ShellUtil;
import org.util.UploadImageUtil;
import org.util.redis.RedisService;

@Controller
@Scope("prototype")
@RequestMapping("cms/lock")
public class BikeLockController {

	@Resource
	BikeLockInfoServiceRead bikeLockInfoServiceRead;

	@Resource
	BikeLockInfoServiceWrite bikeLockInfoServiceWrite;

	@Resource
	BlockServiceRead blockServiceRead;

	@Resource
	BikeServiceRead bikeServiceRead;
	
	@Resource
	BikeServiceWrite bikeServiceWrite;
	
	@Resource
	ModelsServiceRead modelsServiceRead;

	@Resource
	AppConfig appConfig;

	@Resource
	LockService lockService;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	UserServiceRead userServiceRead;
	
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	/**封装之后，实行单例模式，不用每次开关*/
	@Autowired  
    private RedisService redisService;

	/**
	 * 锁列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param session
	 * @param bikeLockInfo
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String bikeLockList(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, HttpSession session,String bikeCode,Long modelsId,
			BikeLockInfo bikeLockInfo, Integer flag,Integer failFlag) throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		if (null != channelId) {
			return "main";
		}
		List<Models> modelsList = modelsServiceRead.findModelsByChannelId(null);
		List<BikeLockInfo> bikeLockInfoList = bikeLockInfoServiceRead.findBikeLockInfoByCondition(bikeLockInfo,
				pageIndex, appConfig.getPage_size_web(),bikeCode,modelsId, flag);
		for (BikeLockInfo b : bikeLockInfoList) {
			if (null != b.getBikeLockBikeId()){
				Bike bike = bikeServiceRead.findBikeByBikeId(b.getBikeLockBikeId());
				Models models = modelsServiceRead.findModelsById(bike.getBikeModelsId());
				bike.setModels(models);
				b.setBike(bike);
			}
		}
		Integer totalPage = 1;
		Integer totalCount = bikeLockInfoServiceRead.countAllBikeLockInfo(bikeLockInfo,bikeCode,modelsId, flag);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("bikeLockInfoList", bikeLockInfoList);
		model.addAttribute("bikeLockInfo", bikeLockInfo);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("bikeCode", bikeCode);
		model.addAttribute("modelsList", modelsList);
		model.addAttribute("modelsId", modelsId);
		if (0 == flag)
			return "bikeLock_list";
		else if (1 == flag){
			model.addAttribute("failFlag", failFlag);
			return "bikeLock_check_list";
		}
		else
			return "main";
	}
	
	
	/**
	 * 按锁列表升级
	 * 
	 * @param model
	 * @param pageIndex
	 * @param session
	 * @param bikeLockInfo
	 * @param flag
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("uplock")
	public @ResponseBody MessageUtil uplock(Model model, HttpSession session,String bikeCode,Long modelsId,
			BikeLockInfo bikeLockInfo, Integer flag,Integer failFlag,@RequestParam("upfile")MultipartFile upfile,Integer upversion) throws Exception {
		List<BikeLockInfo> bikeLockInfoList = bikeLockInfoServiceRead.findBikeLockInfoByCondition(bikeLockInfo, appConfig.getPage_size_web(),bikeCode,modelsId, flag);
		String upload = null ;
		String originalFilename = null;
		if(null != upfile){
			 originalFilename = upfile.getOriginalFilename();
			 upload = UploadImageUtil.uploadUpFile(appConfig.getUpload_path(), upfile);
			 ShellUtil.runShell("/home/backserver/scpFile");
		}
		String file_crc = originalFilename.substring(originalFilename.indexOf("0x")+2, originalFilename.indexOf(".bin"));
		for (BikeLockInfo b : bikeLockInfoList) {
			b.setBikeLockUpfile(appConfig.getUpload_path()+"/"+upload);
			b.setBikeLockUpversion("0.0.0."+upversion);
			//升级中
			b.setBikeLockUpstate(0);
			bikeLockInfoServiceWrite.updateBikeLockInfo(b);
			lockService.sendUpLock(b.getBikeLockCode(), upversion, (int) upfile.getSize(), file_crc);
		}
		MessageUtil messageUtil = new MessageUtil();
		messageUtil.setMessage("success");
		return messageUtil;
	}
	

	/**
	 * 改状态
	 * @param bikeLockInfo
	 * @param bikeLockIdS
	 * @param session
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("update")
	public @ResponseBody String updateBikeLockInfo(BikeLockInfo bikeLockInfo, Long[] bikeLockIdS, HttpSession session)
			throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String lockCode = "";
		if (null != channelId) {
			return "";
		}
		if (null != bikeLockIdS && 0 != bikeLockIdS.length) {
			for (int i = 0; i < bikeLockIdS.length; i++) {
				bikeLockInfo.setBikeLockId(bikeLockIdS[i]);
				bikeLockInfoServiceWrite.updateBikeLockInfo(bikeLockInfo);
				BikeLockInfo bikeLockInfo1 = bikeLockInfoServiceRead.findById(bikeLockInfo.getBikeLockId());
				lockCode = lockCode +" "+bikeLockInfo1.getBikeLockCode();
			}
			String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 4, null, lockCode);
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
		} else if (null != bikeLockInfo.getBikeLockId() && 0 != bikeLockInfo.getBikeLockId()){
			bikeLockInfoServiceWrite.updateBikeLockInfo(bikeLockInfo);
		BikeLockInfo bikeLockInfo1 = bikeLockInfoServiceRead.findById(bikeLockInfo.getBikeLockId());
		lockCode = bikeLockInfo1.getBikeLockCode();
		String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 3, null, lockCode);
		operateLog.setOperateRemark(remark);
        operateServiceWrite.addOperateLogs(operateLog);
        }
		return "bikeLock_check_list";
	}

	/**
	 * 修改关联车编号
	 * @param bikeLockInfo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	public String editBikeLockInfo(Long bikeLockId,String bikeCode, HttpSession session,Integer bikeLockIsFence) throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		if (null != channelId) {
			return "main";
		}
		BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockId);
		if(null!=bikeCode&&!bikeCode.equals("")){
			Bike bike=bikeServiceRead.findBikeByEqualBikeCode(bikeCode);
			  if(null!=bike){
		        	bike.setBikeLockId(bikeLockInfo.getBikeLockId());
		    		bikeServiceWrite.editBike(bike);
		    		bikeLockInfo.setBikeLockBikeId(bike.getBikeId());
				}else{
					bikeLockInfo.setBikeLockBikeId(null);//默认不存在的车
				}
		}else{
			bikeLockInfo.setBikeLockBikeId(null);//默认不存在的车
		}
		
		bikeLockInfo.setBikeLockIsFence(bikeLockIsFence);
        bikeLockInfoServiceWrite.updateLockInfo(bikeLockInfo);
        String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 2, bikeCode,bikeLockInfo.getBikeLockCode());
        operateLog.setOperateRemark(remark);
        operateServiceWrite.addOperateLogs(operateLog);
		return "redirect:list.action?flag=1";
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param model
	 * @param bikeLockInfoId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toEdit")
	public String toUpdateBikeLockInfo(Model model, Long bikeLockId, HttpSession session) throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		if (null != channelId) {
			return "main";
		}
		BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockId);
		if (null != bikeLockInfo.getBikeLockBikeId())
			bikeLockInfo.setBikeCode(bikeServiceRead.findBikeByBikeId(bikeLockInfo.getBikeLockBikeId()).getBikeCode());
		model.addAttribute("bikeLockInfo", bikeLockInfo);
		return "detail/bikeLock_edit";
	}

	/**
	 * 调试页面跳转
	 * @param model
	 * @param bikeLockId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("toCheck")
	public String toCheck(Model model, Long bikeLockId, HttpSession session) throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		if (null != channelId) {
			return "main";
		}
		BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockId);
		if (null != bikeLockInfo.getBikeLockBikeId())
			bikeLockInfo.setBikeCode(bikeServiceRead.findBikeByBikeId(bikeLockInfo.getBikeLockBikeId()).getBikeCode());
		model.addAttribute("bikeLockInfo", bikeLockInfo);
		return "detail/bikeLock_check";
	}
	/**
	 * 调试，上锁
	 * 
	 * @param bikeLockId
	 * @param bikeLockIdS
	 * @param session
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("lock")
	public @ResponseBody String lock(BikeLockInfo bikeLockInfo, Long[] bikeLockIdS, HttpSession session)
			throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String lockCode = "";
		if (null != channelId) {
			return "";
		}
		if (null != bikeLockIdS && 0 != bikeLockIdS.length) {
			for (int i = 0; i < bikeLockIdS.length; i++) {
				bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockIdS[i]);
				redisService.del(bikeLockInfo.getBikeLockCode());
				redisService.closeJedis();
				
				// 发送指令上锁
				lockService.sendLockMessage(bikeLockInfo.getBikeLockCode());
				lockCode = lockCode +" "+bikeLockInfo.getBikeLockCode();
			}
			String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 5, null, lockCode);
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
		} else if (null != bikeLockInfo.getBikeLockId() && 0 != bikeLockInfo.getBikeLockId()) {
			bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockInfo.getBikeLockId());
			redisService.del(bikeLockInfo.getBikeLockCode());
			redisService.closeJedis();
			// 发送指令上锁
			lockService.sendLockMessage(bikeLockInfo.getBikeLockCode());
			String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 10, null, bikeLockInfo.getBikeLockCode());
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
		}
		return "success";
	}

	/**
	 * 调试，开锁
	 * 
	 * @param bikeLockId
	 * @param bikeLockIdS
	 * @param session
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("unlock")
	public @ResponseBody String unlock(BikeLockInfo bikeLockInfo, Long[] bikeLockIdS, HttpSession session)
			throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String lockCode = "";
		if (null != channelId) {
			return "";
		}
		if (null != bikeLockIdS && 0 != bikeLockIdS.length) {
			for (int i = 0; i < bikeLockIdS.length; i++) {
				bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockIdS[i]);
				/*if(null!=bikeLockInfo.getBikeLockType()&&!"".equals(bikeLockInfo.getBikeLockType())&&bikeLockInfo.getBikeLockType()==1){
					redisService.set("admin"+bikeLockInfo.getBikeLockCode(), "open",60000);
				}*/
				redisService.del(bikeLockInfo.getBikeLockCode());
				redisService.closeJedis();
				// 发送指令开锁
				lockService.sendUnlockMessage(bikeLockInfo.getBikeLockCode());
				lockCode = lockCode +" "+bikeLockInfo.getBikeLockCode();
			}
			String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 6, null, lockCode);
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
		} else if (null != bikeLockInfo.getBikeLockId() && 0 != bikeLockInfo.getBikeLockId()) {
			// 发送指令开锁
			bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockInfo.getBikeLockId());
			/*if(null!=bikeLockInfo.getBikeLockType()&&!"".equals(bikeLockInfo.getBikeLockType())&&bikeLockInfo.getBikeLockType()==1){
				redisService.set("admin"+bikeLockInfo.getBikeLockCode(), "open",60000);
			}*/
			redisService.del(bikeLockInfo.getBikeLockCode());
			redisService.closeJedis();
			lockService.sendUnlockMessage(bikeLockInfo.getBikeLockCode());
			String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 9, null, bikeLockInfo.getBikeLockCode());
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
		}
		return "success";
	}

	/**
	 * 获取当前锁的位置
	 * 
	 * @param model
	 * @param bikeLockId
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("location")
	public String location(Model model, Long bikeLockId, HttpSession session) throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		if (null != channelId) {
			return "main";
		}
		BikeLockInfo bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockId);
		if (null != bikeLockInfo.getBikeLockBikeId())
			bikeLockInfo.setBikeCode(bikeServiceRead.findBikeByBikeId(bikeLockInfo.getBikeLockBikeId()).getBikeCode());
		redisService.del("gps"+bikeLockInfo.getBikeLockCode());
		redisService.closeJedis();
//		lockService.sendLocationMessage(bikeLockInfo.getBikeLockCode());// 通过指令立即获取锁的定位信息
		LatLng latLng = new LatLng();
		latLng.setLng(bikeLockInfo.getBikeLockLng());
		latLng.setLat(bikeLockInfo.getBikeLockLat());
		model.addAttribute("bikeLockInfo", bikeLockInfo);
		model.addAttribute("latLng", latLng);
		String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 11, null, bikeLockInfo.getBikeLockCode());
		operateLog.setOperateRemark(remark);
        operateServiceWrite.addOperateLogs(operateLog);
		return "detail/bikeLock_location";
	}
	
	/**
	 * 批量发送gps 获取定位
	 * @param bikeLockInfo
	 * @param bikeLockIdS
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("gpss")
	public @ResponseBody String gpss(BikeLockInfo bikeLockInfo, Long[] bikeLockIdS, HttpSession session)
			throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String lockCode = "";
		if (null != channelId) {
			return "";
		}
		if (null != bikeLockIdS && 0 != bikeLockIdS.length) {
			for (int i = 0; i < bikeLockIdS.length; i++) {
				bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockIdS[i]);
				redisService.del(bikeLockInfo.getBikeLockCode());
				redisService.closeJedis();
				lockCode = lockCode +" "+bikeLockInfo.getBikeLockCode();
				//判断是否存在当前连接
				if(lockService.judgeLockConnet(bikeLockInfo.getBikeLockCode())){
					//发送定位
					lockService.sendLocationMessage(bikeLockInfo.getBikeLockCode());
				}
			}
			String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 8, null, lockCode);
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
		}
		return "success";
	}
	
	/**
	 * 发送GPS定位请求
	 * @param model
	 * @param bikeLockCode
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("sendGPS")
	public @ResponseBody String sendGPS(Model model, String bikeLockCode, HttpSession session) throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		if (null != channelId) {
			return "main";
		}
		String msg= null;
		if(lockService.sendLocationMessage(bikeLockCode)){
			msg= "success";
		};// 通过指令立即获取锁的定位信息
		return msg;
	}
	
	/**
	 * 获取车锁版本信息
	 * @param model
	 * @param bikeLockCode
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getversion")
	public @ResponseBody String getversion(Model model, String bikeLockCode, HttpSession session) throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		if (null != channelId) {
			return "main";
		}
		String msg= null;
		if(lockService.getVersion(bikeLockCode)){
			msg= "success";
		};// 通过指令立即获取锁的定位信息
		return msg;
	}
	
	/**
	 * 锁状态信息
	 * @param bikeLockInfo
	 * @param bikeLockIdS
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("state")
	public @ResponseBody String state(BikeLockInfo bikeLockInfo, Long[] bikeLockIdS, HttpSession session)
			throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		String lockCode = "";
		if (null != channelId) {
			return "";
		}
		if (null != bikeLockIdS && 0 != bikeLockIdS.length) {
			for (int i = 0; i < bikeLockIdS.length; i++) {
				bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockIdS[i]);
				// 发送获取状态指令
				lockService.sendStateMessage(bikeLockInfo.getBikeLockCode());
				lockCode = lockCode +" "+bikeLockInfo.getBikeLockCode();
			}
			String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 7, null, lockCode);
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
		} else if (null != bikeLockInfo.getBikeLockId() && 0 != bikeLockInfo.getBikeLockId()) {
			bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockInfo.getBikeLockId());
			lockService.sendStateMessage(bikeLockInfo.getBikeLockCode());
			String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 12, null, bikeLockInfo.getBikeLockCode());
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
		}
		return "success";
	}
	/**
	 * 获取锁的域名配置握手密码
	 * @param bikeLockInfo
	 * @param bikeLockIdS
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getDomainPwd")
	public @ResponseBody String getDomainPwd(BikeLockInfo bikeLockInfo, Long[] bikeLockIdS, HttpSession session)
			throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		if (null != channelId) {
			return "";
		}
		if (null != bikeLockIdS && 0 != bikeLockIdS.length) {
			for (int i = 0; i < bikeLockIdS.length; i++) {
				bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockIdS[i]);
				// 发送指令开锁
				lockService.sendGetDomainPwdMessage(bikeLockInfo.getBikeLockCode());
			}
		} else if (null != bikeLockInfo.getBikeLockId() && 0 != bikeLockInfo.getBikeLockId()) {
			// 发送指令开锁
			bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockInfo.getBikeLockId());
			lockService.sendGetDomainPwdMessage(bikeLockInfo.getBikeLockCode());
			String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 13, null, bikeLockInfo.getBikeLockCode());
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
		}
		return "success";
	}
	/**
	 * 发送关机指令
	 * @param bikeLockInfo
	 * @param bikeLockIdS
	 * @param session
	 * @param time
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("powerOff")
	public @ResponseBody String powerOff(BikeLockInfo bikeLockInfo, Long[] bikeLockIdS, HttpSession session,Integer time)
			throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		if (null != channelId) {
			return "";
		}
		if (null != bikeLockIdS && 0 != bikeLockIdS.length) {
			for (int i = 0; i < bikeLockIdS.length; i++) {
				bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockIdS[i]);
				// 发送指令开锁
				lockService.sendPowerOffMessage(bikeLockInfo.getBikeLockCode(),time);
			}
		} else if (null != bikeLockInfo.getBikeLockId() && 0 != bikeLockInfo.getBikeLockId()) {
			// 发送指令开锁
			bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockInfo.getBikeLockId());
			lockService.sendPowerOffMessage(bikeLockInfo.getBikeLockCode(),time);
		}
		return "success";
	}
	/**
	 * 开启域名配置功能
	 * @param bikeLockInfo
	 * @param bikeLockIdS
	 * @param session
	 * @param time
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("shakeHands")
	public @ResponseBody String shakeHands(BikeLockInfo bikeLockInfo, Long[] bikeLockIdS, HttpSession session)
			throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		if (null != channelId) {
			return "";
		}
		if (null != bikeLockIdS && 0 != bikeLockIdS.length) {
			for (int i = 0; i < bikeLockIdS.length; i++) {
				bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockIdS[i]);
				// 发送指令开锁
				lockService.sendDomainPwdMessage(bikeLockInfo.getBikeLockCode(),bikeLockInfo.getBikeLockDomainPwd());
			}
		} else if (null != bikeLockInfo.getBikeLockId() && 0 != bikeLockInfo.getBikeLockId()) {
			// 发送指令开锁
			bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockInfo.getBikeLockId());
			lockService.sendDomainPwdMessage(bikeLockInfo.getBikeLockCode(),bikeLockInfo.getBikeLockDomainPwd());
			String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 14, null, bikeLockInfo.getBikeLockCode());
			operateLog.setOperateRemark(remark);
	        operateServiceWrite.addOperateLogs(operateLog);
		}
		return "success";
	}
	
	/**
	 * 修改握手密码
	 * @param bikeLockInfo
	 * @param session
	 * @param npwd
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setDomainPwd")
	public String setDomainPwd(BikeLockInfo bikeLockInfo, HttpSession session,String npwd) throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		if (null != channelId) {
			return "main";
		}
		bikeLockInfo = bikeLockInfoServiceRead.findById((bikeLockInfo.getBikeLockId()));
		lockService.sendSetDomianPwdMessage(bikeLockInfo.getBikeLockCode(),bikeLockInfo.getBikeLockDomainPwd(),npwd);
		bikeLockInfo.setBikeLockDomainPwd(npwd);
		bikeLockInfo.setBikeLockDomainPwdState(0);
		bikeLockInfoServiceWrite.updateBikeLockInfo(bikeLockInfo);
		return "redirect:toCheck.action?bikeLockId="+bikeLockInfo.getBikeLockId();
	}
	
	/**
	 * 修改域名、端口
	 * @param bikeLockInfo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("setDomain")
	public String setDomain(BikeLockInfo bikeLockInfo, HttpSession session) throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		if (null != channelId) {
			return "main";
		}
		String domain=bikeLockInfo.getBikeLockDomain();
		String port=bikeLockInfo.getBikeLockPort();
		bikeLockInfo = bikeLockInfoServiceRead.findById((bikeLockInfo.getBikeLockId()));
		bikeLockInfo.setBikeLockDomain(domain);
		bikeLockInfo.setBikeLockPort(port);
		bikeLockInfo.setBikeLockDominState(1);
		lockService.sendSetDomianMessage(bikeLockInfo.getBikeLockCode(),domain,port);
		bikeLockInfoServiceWrite.updateBikeLockInfo(bikeLockInfo);
		return "redirect:toCheck.action?bikeLockId="+bikeLockInfo.getBikeLockId();
	}
	
	
	

	/**
	 * 导入锁
	 * 
	 * @param model
	 * @param session
	 * @param excel
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("importLock")
	public String importStudent(Model model, HttpSession session, MultipartFile excel) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		if (!excel.isEmpty()) {
			InputStream inputStream = excel.getInputStream();
			String filename = excel.getOriginalFilename();
			String extensionName = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
			List<BikeLockInfo> list = ReadWriteExcelUtil.exportLockListFromExcel(inputStream, extensionName, 0);
			
			for (BikeLockInfo bl : list) {
				BikeLockInfo lock = bikeLockInfoServiceRead.findByCode(bl.getBikeLockCode().trim());
				if(lock!=null){
					return "redirect:list.action?flag=1&failFlag=1";
				}
			}
			for (BikeLockInfo bikeLockInfo : list) {
				bikeLockInfoServiceWrite.adddBikeLockInfo(bikeLockInfo);
			}

		}
		
		String remark = OperateUtil.operateLock(nowAdmin.getAdminRealname(), 1, null, null);
			operateLog.setOperateRemark(remark);
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			operateServiceWrite.addOperateLogs(operateLog);
		
		
		return "redirect:list.action?flag=1";
	}
	
	/**
	 * 锁状态信息
	 * @param bikeLockInfo
	 * @param bikeLockIdS
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("version")
	public @ResponseBody String version(BikeLockInfo bikeLockInfo, Long[] bikeLockIdS, HttpSession session)
			throws Exception {
		Long channelId = (Long) session.getAttribute("currChannelId");
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		OperateLog operateLog = new OperateLog();
		operateLog.setOperateTime(new Date());
		operateLog.setOperateAdminId(nowAdmin.getAdminId());
		if (null != channelId) {
			return "";
		}
		if (null != bikeLockIdS && 0 != bikeLockIdS.length) {
			for (int i = 0; i < bikeLockIdS.length; i++) {
				bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockIdS[i]);
				lockService.getVersion(bikeLockInfo.getBikeLockCode());
			}
		} else if (null != bikeLockInfo.getBikeLockId() && 0 != bikeLockInfo.getBikeLockId()) {
			bikeLockInfo = bikeLockInfoServiceRead.findById(bikeLockInfo.getBikeLockId());
			lockService.getVersion(bikeLockInfo.getBikeLockCode());
		}
		return "success";
	}

}
