package org.controller.weixin;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.component.AppConfig;

import org.entity.dto.Bike;
import org.entity.dto.DataDet;
import org.entity.dto.Feedback;
import org.entity.dto.Message;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.service.weixin.read.BikeWxServiceRead;
import org.service.weixin.read.DataDetWxServiceRead;
import org.service.weixin.read.FeedBackWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.service.weixin.write.FeedBackWxServiceWrite;
import org.service.weixin.write.MessageWxServiceWrite;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.util.MessageUtil;
import org.util.ShortYunMessageUtil;
import org.util.StringUtil;




/**
 * 用户反馈
 * 
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/feedBack", method = RequestMethod.POST)
public class FeedBackWxController {

	@Resource
	FeedBackWxServiceRead feedBackWxServiceRead;
	@Resource
	FeedBackWxServiceWrite FeedBackWxServiceWrite;
	@Resource
	DataDetWxServiceRead dataDetWxServiceRead;
	@Resource
	BikeWxServiceRead bikeWxServiceRead;
	@Resource
	MessageWxServiceWrite messageWxServiceWrite;
	@Resource
	AppConfig appConfig;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(FeedBackWxController.class);
	
	
	
	
	@RequestMapping(value = "/subFeedBack", method = RequestMethod.POST)
	public @ResponseBody MessageUtil subFeedBack(@RequestHeader HttpHeaders header,HttpSession session, String[] file, HttpServletRequest request,
			String bikeCode, Feedback feedback) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
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
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("登录超时");
				return messageUtil;
			}
		}
		Bike bike = null;
		if (null != bikeCode && !"".equals(bikeCode)) {
			bike = bikeWxServiceRead.findByBikeCode(bikeCode);
			loggers.info("传入的车辆编号为："+bikeCode);
			if(null==bike){
				messageUtil.setCode(0);
				messageUtil.setMessage("未查到该辆车");
				return messageUtil;
			}
		}
		String imageNames = "";
		if (null!=file&&file.length!=0) {
			for(int i=0;i<file.length;i++){
			if(file[i].length()>23){
				file[i] = file[i].replace("data:image/jpeg;base64,", ""); 
				byte[] data = Base64.decodeBase64(file[i]);
		        String imageName = ""+System.currentTimeMillis()+i+".jpg";
				if (data != null) {// 判断文件类型是否为空
					    FileOutputStream outputStream = new FileOutputStream(new File(appConfig.getUpload_path(),imageName));
						outputStream.write(data);  
				        outputStream.flush();  
				        outputStream.close(); 
				        imageNames = imageNames+";"+imageName;
				
				} else {
					loggers.info("没有找到相对应的文件");
					messageUtil.setCode(0);
					messageUtil.setMessage("没有找到相对应的文件");
					Message message = new Message();
					message.setMessageUserId(userLogin.getUserId());
					message.setMessageContent("反馈失败,原因为‘没有找到相对应的文件’");
					message.setMessageSendTime(new Date());
					message.setMessageTitle("反馈提交失败");
					messageWxServiceWrite.addMessage(message);
					return messageUtil;
				}
			}
			}
			if(imageNames!=""){
				imageNames=imageNames.substring(1);
				feedback.setFeedbackPicaddress(imageNames);
			}
		}
		feedback.setUserId(userLogin.getUserId());
		Message message = new Message();
		if (null != bike) {
			feedback.setBikeId(bike.getBikeId());
			message.setMessageContent("您的反馈信息已提交，车牌号为"+bike.getBikeCode()+"，客服人员会尽快为您处理");
		}else{
			message.setMessageContent("您的反馈信息已提交，客服人员会尽快为您处理");
		}
		// 无处理
		feedback.setFeedbackState(0);
		feedback.setFeedbackTime(new Date());

		FeedBackWxServiceWrite.addFeedBack(feedback);
		
		map.put("gotoaction", appConfig.getBase_path_weixin());
		messageUtil .setData(map);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		
		
		message.setMessageUserId(userLogin.getUserId());
		
		message.setMessageSendTime(new Date());
		message.setMessageTitle("反馈提交完成");
		messageWxServiceWrite.addMessage(message);
		if("wxf6a747b5a4e78b31".equals(appConfig.getApp_id())){
			ShortYunMessageUtil.sendMassageFeed(userLogin.getUserTel());
			loggers.info("发送提醒短信");
		}
		loggers.info("反馈提交完成");
		
		return messageUtil;
	}
	
	
	
	@RequestMapping(value = "/subFeedBackApp", method = RequestMethod.POST)
	public @ResponseBody MessageUtil subFeedBack(@RequestHeader HttpHeaders header,MultipartFile file1,MultipartFile file2,MultipartFile file3,MultipartFile file4, HttpServletRequest request,
			String bikeCode, Feedback feedback) throws Exception {
		Map<String ,Object> data = new HashMap<String ,Object>();
		User userLogin = new User();
		String fromFlag = header.getFirst("fromFlag");
		if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				//用户未登陆/token已失效
				messageUtil.setCode(2);
				messageUtil.setMessage("用户未登陆/token已失效");
				return messageUtil;
			}
		}
		
		Bike bike = null;
		if (null != bikeCode && !"".equals(bikeCode)) {
			bike = bikeWxServiceRead.findByBikeCode(bikeCode);
			loggers.info("传入的车辆编号为："+bikeCode);
			if(null==bike){
				messageUtil.setCode(0);
				messageUtil.setMessage("未查到该辆车");
				return messageUtil;
			}
		}
		List<MultipartFile> files = new ArrayList<MultipartFile>(); 
		files.add(file1);
		files.add(file2);
		files.add(file3);
		files.add(file4);
		String imagePathNames = "";
		if (null!=files&&files.size()!=0) {
			for(MultipartFile file : files){
					if(null!=file){
						String fileName = file.getOriginalFilename();
						String imagePathName = ""+System.currentTimeMillis()+ fileName;
						// 判断文件类型
						String type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length())
								: null;  
						if (type != null) {// 判断文件类型是否为空
							if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
									|| "JPG".equals(type.toUpperCase())) {
								
								File targetFile = new File(appConfig.getUpload_path(), imagePathName);
								if (!targetFile.exists()) {
									targetFile.mkdirs();
								}
								// 保存
								try {
									file.transferTo(targetFile);
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								System.out.println("不是我们想要的文件类型,请按要求重新上传");
								messageUtil.setCode(0);
								messageUtil.setMessage("不是我们想要的文件类型,请按要求重新上传");
								return messageUtil;
							}
						} else {
							System.out.println("没有找到相对应的文件");
							messageUtil.setCode(0);
							messageUtil.setMessage("没有找到相对应的文件");
							return messageUtil;
						}
						
						imagePathNames = imagePathNames+";"+imagePathName;
					}
		    }
			if(imagePathNames!=""){
				imagePathNames=imagePathNames.substring(1);
				feedback.setFeedbackPicaddress(imagePathNames);
			}
			
		}
		feedback.setUserId(userLogin.getUserId());
		Message message = new Message();
		if (null != bike) {
			feedback.setBikeId(bike.getBikeId());
			message.setMessageContent("您的反馈信息已提交，车牌号为"+bike.getBikeCode()+"，客服人员会尽快为您处理");
		}else{
			message.setMessageContent("您的反馈信息已提交，客服人员会尽快为您处理");
		}
		// 无处理
		feedback.setFeedbackState(0);
		feedback.setFeedbackTime(new Date());

		/*Jedis jedis = new Jedis(AppConfig.getRedis_host(), AppConfig.getRedis_port());
		jedis.auth(AppConfig.getRedis_password());
		String feedBackTime  =  jedis.get(userLogin.getUserId()+"feedBackTime");//用户扫码时间
		if(null!=feedBackTime){
			System.out.println("用户:"+userLogin.getUserRealname()+"上次反馈时间---"+feedBackTime);
			Long diffSeconds = DateUtil.secondDiff(DateUtil.changStringDate(feedBackTime), new Date());
			if(diffSeconds<30){  //小于10秒
				messageUtil.setCode(30);
				messageUtil.setMessage("扫码过于频繁");
				jedis.close();
				return messageUtil;
			}
		}*/
		if(null!=feedback.getFeedbackContent()){
			feedback.setFeedbackContent(StringUtil.filterEmoji(feedback.getFeedbackContent()));
		}
		FeedBackWxServiceWrite.addFeedBack(feedback);
		
		/*jedis.set(userLogin.getUserId()+"feedBackTime", DateUtil.format(new Date()));
		jedis.close();*/
		
		
		
		
		data.put("imagePathNames", imagePathNames);
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("反馈成功");
		
		if("wxf6a747b5a4e78b31".equals(appConfig.getApp_id())){
			ShortYunMessageUtil.sendMassageFeed(userLogin.getUserTel());
			loggers.info("发送提醒短信");
		}
		loggers.info("反馈提交完成");
		message.setMessageUserId(userLogin.getUserId());
		
		message.setMessageSendTime(new Date());
		message.setMessageTitle("完成反馈");
		messageWxServiceWrite.addMessage(message);
		
		return messageUtil;
	}

	
	/**
	 * 类型
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/types", method = RequestMethod.POST)
	public @ResponseBody MessageUtil feedBackTypes() throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();
		List<DataDet> list = dataDetWxServiceRead.findDataDetByDataId((long) 6);
		data.put("types", list);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		return messageUtil;
	}
	
	/**
	 * 反馈车辆编号判断
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkBikeCode", method = RequestMethod.POST)
	public @ResponseBody MessageUtil checkBikeCode(String bikeCode) throws Exception {
		Bike bike = bikeWxServiceRead.findByBikeCode(bikeCode);
		if(bike==null){
			messageUtil.setMessage("fail");
			return messageUtil;
		}
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	
	/**
	 * 客服中心页面背景
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/feedBackUrl", method = RequestMethod.POST)
	public @ResponseBody MessageUtil feedBackUrl() throws Exception {
		Map<String,Object> data = new HashMap<String,Object>();
		String url = appConfig.getBase_path_weixin()+"html/service-center.html";
		data.put("url", url);
	    messageUtil.setData(data);
	    messageUtil.setMessage("success");
	    messageUtil.setCode(1);
	    return messageUtil;
	}
	
	
}
