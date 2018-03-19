package org.controller.weixin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.service.weixin.read.BikeRentInfoWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.DateUtil;
import org.util.MessageUtil;
import org.util.redis.RedisService;

/**
 * 前台用户控制层
 * 
 * @author morning
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/version")
public class VersionWxController {

	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;
	@Resource
	RedisService redisService;
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(VersionWxController.class);
	
	/**
	 * 获取当前版本号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getVersion")
	@ResponseBody
	public MessageUtil getVersion(String appFlag,String appVsersion) throws Exception{
		MessageUtil messageUtil = new MessageUtil();
		Map<String, Object> data = new HashMap<String, Object>();
		SysParament parament = null;
		if("android".equals(appFlag)){
			 parament = sysParamentServiceRead.findByName("android_version");
			
		}else if("ios".equals(appFlag)){
			 parament = sysParamentServiceRead.findByName("ios_version");
		}
		loggers.info("传来的app版本"+appVsersion);
		if(null != parament){
			loggers.info("最新的app版本"+parament.getSysParamentValue());
			if(null!=appVsersion&&!appVsersion.equals("")){
				Integer flag = 0;//(0不需要更新1需要更新)
				if(null!=parament.getSysParamentValue()&&!parament.getSysParamentValue().equals("")){
					String[] upVersion = parament.getSysParamentValue().split("\\.");
					String[] nowVersion = appVsersion.split("\\.");
					if(upVersion.length>nowVersion.length){
						for(int i=0;i<nowVersion.length;i++){
							if(Integer.valueOf(upVersion[i])>Integer.valueOf(nowVersion[i])){
									flag = 1;
									break;
							}else if(Integer.valueOf(upVersion[i])==Integer.valueOf(nowVersion[i])){
								flag = 1;
							}else if(Integer.valueOf(upVersion[i])<Integer.valueOf(nowVersion[i])){
								flag = 0;
								break;
							}
						}
					}else {
						for(int i=0;i<upVersion.length;i++){
							if(Integer.valueOf(upVersion[i])>Integer.valueOf(nowVersion[i])){
									flag = 1;
									break;
							}else if(Integer.valueOf(upVersion[i])<Integer.valueOf(nowVersion[i])){
								flag = 0;
								break;
							}
						}
					}
				}else{
					data.put("state", 0);//0不能更新1可更新
				}
				
				if(flag==1){
					data.put("version", parament.getSysParamentValue());
					data.put("isUpdate", parament.getSysParamentIsUpdate());//0不强制更新1强制更新
					data.put("content", parament.getSysParamentUpdateContent());
					data.put("state", 1);
					if("android".equals(appFlag)){
						data.put("gotoUrl", "http://a.app.qq.com/o/simple.jsp?pkgname=com.qs.letubicycle");
					}
				}else{
					data.put("state", 0);
				}
			}else{
				data.put("state", 0);
			}
		}else{
			data.put("state", 0);
		}
		messageUtil.setCode(1);
		messageUtil.setData(data);
		return messageUtil;
	}
	
	/**
	 * 获取当前版本号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getVersion2")
	@ResponseBody
	public MessageUtil getVersion2(@RequestHeader HttpHeaders header,String appFlag,String appVsersion) throws Exception{
		MessageUtil messageUtil = new MessageUtil();
		String fromFlag = header.getFirst("fromFlag");
		Map<String, Object> data = new HashMap<String, Object>();
		SysParament parament = null;
		if("android".equals(appFlag)){
			 parament = sysParamentServiceRead.findByName("android_version");
			
		}else if("ios".equals(appFlag)){
			 parament = sysParamentServiceRead.findByName("ios_version");
		}
		loggers.info("传来的app版本"+appVsersion);
		if(null != parament){
			loggers.info("最新的app版本"+parament.getSysParamentValue());
			if(null!=appVsersion&&!appVsersion.equals("")){
				Integer flag = 0;//(0不需要更新1需要更新)
				if(null!=parament.getSysParamentValue()&&!parament.getSysParamentValue().equals("")){
					String[] upVersion = parament.getSysParamentValue().split("\\.");
					String[] nowVersion = appVsersion.split("\\.");
					if(upVersion.length>nowVersion.length){
						for(int i=0;i<nowVersion.length;i++){
							if(Integer.valueOf(upVersion[i])>Integer.valueOf(nowVersion[i])){
									flag = 1;
									break;
							}else if(Integer.valueOf(upVersion[i])==Integer.valueOf(nowVersion[i])){
								flag = 1;
							}else if(Integer.valueOf(upVersion[i])<Integer.valueOf(nowVersion[i])){
								flag = 0;
								break;
							}
						}
					}else {
						for(int i=0;i<upVersion.length;i++){
							if(Integer.valueOf(upVersion[i])>Integer.valueOf(nowVersion[i])){
									flag = 1;
									break;
							}else if(Integer.valueOf(upVersion[i])<Integer.valueOf(nowVersion[i])){
								flag = 0;
								break;
							}
						}
					}
				}else{
					data.put("state", 0);//0不能更新1可更新
				}
				
				if(flag==1){
					data.put("version", parament.getSysParamentValue());
					data.put("isUpdate", parament.getSysParamentIsUpdate());//0不强制更新1强制更新
					data.put("content", parament.getSysParamentUpdateContent());
					data.put("state", 1);
					if("android".equals(appFlag)){
						data.put("gotoUrl", "http://a.app.qq.com/o/simple.jsp?pkgname=com.qs.letubicycle");
					}
				}else{
					data.put("state", 0);
				}
			}else{
				data.put("state", 0);
			}
		}else{
			data.put("state", 0);
		}
		Object state = data.get("state");
		data.put("notFinish", 0);
		if(state.toString().equals("0")){
			User userLogin = new User();
			if("1".equals(fromFlag)){//app端
				String token = header.getFirst("token");
				SysParament sysParament = sysParamentServiceRead.findByName("token");
				userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
				if(null==userLogin){
					data.put("toPop", 1);
				}else{
					if (1 == userLogin.getUserState()) { // 如果是租借状态
						data.put("toPop", 0);
						data.put("notFinish", 1);
					}else{
						String qrTime  =  redisService.get(userLogin.getUserId()+"qrTime");//用户扫码时间
						if(null!=qrTime){
							loggers.info("用户:"+userLogin.getUserTel()+"上次弹框时间---"+qrTime);
							Long diffSeconds = DateUtil.secondDiff(DateUtil.changStringDate(qrTime), new Date());
							if(diffSeconds<3600){  //小于3600秒
								data.put("toPop", 0);
							}else{
								data.put("toPop", 1);
								redisService.set(userLogin.getUserId()+"qrTime", DateUtil.format(new Date()),4000);
								//关闭jedis
								redisService.closeJedis();
							}
						}else{
							data.put("toPop", 1);
							redisService.set(userLogin.getUserId()+"qrTime", DateUtil.format(new Date()),4000);
							//关闭jedis
							redisService.closeJedis();
						}
					}
					
					
				}
			}
			
		}
		messageUtil.setCode(1);
		messageUtil.setData(data);
		return messageUtil;
	}
	
	
}