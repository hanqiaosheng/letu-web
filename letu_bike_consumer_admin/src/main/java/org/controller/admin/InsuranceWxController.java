package org.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.Insurance;
import org.entity.dto.Message;
import org.entity.dto.User;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.MessageServiceWrite;
import org.service.cms.write.UserInsuranceServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.MessageUtil;


/**
 * 保险
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/api/insurance", method = RequestMethod.POST)
public class InsuranceWxController{
	
	@Resource
	UserServiceRead userServiceRead;
	
	@Resource
	UserInsuranceServiceWrite userInsuranceServiceWrite;
	
	@Resource
	MessageServiceWrite messageServiceWrite;
	
	@Resource
	AppConfig appConfig;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	/**
	 * 保险提交
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/subInsurance", method = RequestMethod.POST)
	public @ResponseBody MessageUtil subInsurance(HttpSession session,String phone,String name,String idCard,String reason) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		/*name = CharSetUtil.change(name);
		reason = CharSetUtil.change(reason);*/
		Map<String,Object> data = new HashMap<String,Object>();
		User user = userServiceRead.findUserByPhone(phone);
		if(null==user){
			messageUtil.setMessage("nouser");
			return messageUtil;
		}
		if(!name.equals(user.getUserRealname())){
			messageUtil.setMessage("name");
			return messageUtil;
		}
		if(!idCard.equals(user.getUserIdcard())){
			messageUtil.setMessage("idCard");
			return messageUtil;
		}
		
		Insurance insurance  = new Insurance();
		insurance.setInsuranceUserId(user.getUserId());
		insurance.setInsuranceContent(reason);
		insurance.setInsuranceApplyTime(new Date());
		insurance.setInsuranceAdminId(admin.getAdminId());
		String oddNum = "B" + DateUtil.format03(new Date())+ user.getUserId();
		insurance.setInsuranceOddNum(oddNum);
		Long insuranceId = userInsuranceServiceWrite.addInsurance(insurance);
		
		Message message = new Message();
		message.setMessageAdminId(admin.getAdminId());
		message.setTempletCode("2");
		message.setMessageContent("由管理员"+admin.getAdminRealname()+"已提交意外保险申请");
		message.setMessageSendTime(new Date());
		message.setMessageUserId(user.getUserId());
		message.setMessageInsurance(insuranceId);
		messageServiceWrite.addMessage(message);
		
		
		Message message2 = new Message();
		message2.setMessageAdminId(admin.getAdminId());
		message2.setTempletCode("2");
		message2.setMessageContent("您的保险申请已提交至后台，等待审核");
		message2.setMessageSendTime(new Date());
		message2.setMessageUserId(user.getUserId());
		message2.setMessageInsurance(insuranceId);
		messageServiceWrite.addMessage(message2);
		
		data.put("insuranceId", insuranceId);
		messageUtil.setMessage("success");
		messageUtil.setData(data);
		return messageUtil;
	}
	
	
	/**
	 * 保险修改
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/editInsurance", method = RequestMethod.POST)
	public @ResponseBody MessageUtil editInsurance(HttpSession session,String phone,String name,String idCard,String reason,Long insuranceId) throws Exception{
		Admin admin = (Admin) session.getAttribute("admin");
		/*name = CharSetUtil.change(name);//转码
		reason = CharSetUtil.change(reason);*/
		User user = userServiceRead.findUserByPhone(phone);
		if(null==user){
			messageUtil.setMessage("nouser");
			return messageUtil;
		}
		if(!name.equals(user.getUserRealname())){
			messageUtil.setMessage("name");
			return messageUtil;
		}
		if(!idCard.equals(user.getUserIdcard())){
			messageUtil.setMessage("idCard");
			return messageUtil;
		}
		
		Insurance insurance  = new Insurance();
		insurance.setInsuranceId(insuranceId);
		insurance.setInsuranceUserId(user.getUserId());
		insurance.setInsuranceContent(reason);
		insurance.setInsuranceAdminId(admin.getAdminId());
		userInsuranceServiceWrite.updateInsuranceState(insurance);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 上传事故照片
	 * @param session
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/subAccidentPhoto", method = RequestMethod.POST)
	public @ResponseBody MessageUtil subAccidentPhoto(HttpSession session, String[] panoramaFiles,String[] partFiles, HttpServletRequest request,
			Long insuranceId) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Insurance insurance = new Insurance();
        if(insuranceId==null){
        	messageUtil.setCode(1);
    		messageUtil.setMessage("fail");
    		return messageUtil;
		}
        insurance.setInsuranceId(insuranceId);
		String panoramaImageNames = "";
		String partImageNames = "";
		if (null!=panoramaFiles&&panoramaFiles.length!=0) {//事故全景照片
			for(int i=0;i<panoramaFiles.length;i++){
			if(panoramaFiles[i].length()>23){
				panoramaFiles[i] = panoramaFiles[i].replace("data:image/jpeg;base64,", ""); 
				/*BASE64Decoder decoder = new BASE64Decoder();  
		        byte[] data = decoder.decodeBuffer(panoramaFiles[i]);*/
				byte[] data = Base64.decodeBase64(panoramaFiles[i]);
		        String panoramaImageName = "pan"+System.currentTimeMillis()+i+".jpg";
				
				if (data != null) {// 判断文件类型是否为空
					
					    FileOutputStream outputStream = new FileOutputStream(new File(appConfig.getUpload_path(),panoramaImageName));
						outputStream.write(data);  
				        outputStream.flush();  
				        outputStream.close(); 
				        panoramaImageNames = panoramaImageNames+";"+panoramaImageName;

				} 
			}
			}
			if(panoramaImageNames!=""){
				panoramaImageNames=panoramaImageNames.substring(1);
				insurance.setInsuranceAccidentImgs(panoramaImageNames);
			}
		}
		
		if (null!=partFiles&&partFiles.length!=0) {//事故全景照片
			for(int i=0;i<partFiles.length;i++){
			if(partFiles[i].length()>23){
				partFiles[i] = partFiles[i].replace("data:image/jpeg;base64,", ""); 
				byte[] data = Base64.decodeBase64(partFiles[i]);
		        String partImageName = "part"+System.currentTimeMillis()+i+".jpg";
				if (data != null) {// 判断文件类型是否为空
					    FileOutputStream outputStream = new FileOutputStream(new File(appConfig.getUpload_path(),partImageName));
						outputStream.write(data);  
				        outputStream.flush();  
				        outputStream.close(); 
				        partImageNames = partImageNames+";"+partImageName;

				} 
			}
			}
			if(partImageNames!=""){
				partImageNames=partImageNames.substring(1);
				insurance.setInsuranceDetailImgs(partImageNames);
			}
		}
		insurance.setInsuranceAdminId(admin.getAdminId());
		userInsuranceServiceWrite.updateInsuranceState(insurance);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
	/**
	 * 上传处理照片
	 * @param session
	 * @param identityFiles
	 * @param compensateFile
	 * @param resultProveFile
	 * @param request
	 * @param insuranceId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/subDealWithPhoto", method = RequestMethod.POST)
	public @ResponseBody MessageUtil subDealWithPhoto(HttpSession session, String[] identityFiles,String compensateFile,String resultProveFile, HttpServletRequest request,
			Long insuranceId) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Insurance insurance = new Insurance();
        if(insuranceId==null){
        	messageUtil.setCode(1);
    		messageUtil.setMessage("fail");
    		return messageUtil;
		}
        insurance.setInsuranceId(insuranceId);
		String identityImageNames = "";
		if (null!=identityFiles&&identityFiles.length!=0) {//身份证照片
			for(int i=0;i<identityFiles.length;i++){
			if(identityFiles[i].length()>23){
				identityFiles[i] = identityFiles[i].replace("data:image/jpeg;base64,", ""); 
				byte[] data = Base64.decodeBase64(identityFiles[i]);
		        String identityImageName = "ide"+System.currentTimeMillis()+i+".jpg";
				
				if (data != null) {// 判断文件类型是否为空
					
					    FileOutputStream outputStream = new FileOutputStream(new File(appConfig.getUpload_path(),identityImageName));
						outputStream.write(data);  
				        outputStream.flush();  
				        outputStream.close(); 
				        identityImageNames = identityImageNames+";"+identityImageName;

				} 
			}
		  }
		}
			if(identityImageNames!=""){
				identityImageNames=identityImageNames.substring(1);
				insurance.setInsuranceIdentityContact(identityImageNames);
			}
		
		
		 if (null!=compensateFile&&!compensateFile.equals("")) {//赔偿协议书
			    compensateFile = compensateFile.replace("data:image/jpeg;base64,", "");  
			    byte[] data = Base64.decodeBase64(compensateFile);
		        String compensateImageName = "com"+System.currentTimeMillis()+".jpg";
				if (data != null) {// 判断文件类型是否为空
					    FileOutputStream outputStream = new FileOutputStream(new File(appConfig.getUpload_path(),compensateImageName));
						outputStream.write(data);  
				        outputStream.flush();  
				        outputStream.close(); 
				        insurance.setInsuranceCompensate(compensateImageName);
			} 
		 }
		 
		 if (null!=resultProveFile&&!resultProveFile.equals("")) {//赔偿处理结果证明文件
			 resultProveFile = resultProveFile.replace("data:image/jpeg;base64,", ""); 
			 byte[] data = Base64.decodeBase64(resultProveFile);
		        String resultProveImageName = "pro"+System.currentTimeMillis()+".jpg";
				if (data != null) {// 判断文件类型是否为空
					    FileOutputStream outputStream = new FileOutputStream(new File(appConfig.getUpload_path(),resultProveImageName));
						outputStream.write(data);  
				        outputStream.flush();  
				        outputStream.close(); 
				        insurance.setInsuranceResultProve(resultProveImageName);
			} 
		 }
		insurance.setInsuranceAdminId(admin.getAdminId());
		userInsuranceServiceWrite.updateInsuranceState(insurance);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}

}
