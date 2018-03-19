package org.controller.weixin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.entity.dto.Bike;
import org.entity.dto.BikeRentInfo;
import org.entity.dto.DataDet;
import org.entity.dto.GradeRecord;
import org.entity.dto.Orbit;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.entity.dto.UserToTask;
import org.service.weixin.read.BikeRentInfoWxServiceRead;
import org.service.weixin.read.BikeWxServiceRead;
import org.service.weixin.read.DataDetWxServiceRead;
import org.service.weixin.read.OrbitWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.service.weixin.read.UserToTaskWxServiceRead;
import org.service.weixin.write.GradeWxServiceWrite;
import org.service.weixin.write.OrbitServiceWxWrite;
import org.service.weixin.write.UserServiceWeixinWrite;
import org.service.weixin.write.UserToTaskWxServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.LevelUtil;
import org.util.MessageUtil;

import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;

/**
 * 轨迹
 * @author cbx
 *
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/orbit", method = RequestMethod.POST)
public class OrbitWxController {

	@Resource
	OrbitWxServiceRead orbitWxServiceRead;
	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	OrbitServiceWxWrite orbitServiceWxWrite;
	@Resource
	BikeRentInfoWxServiceRead bikeRentInfoWxServiceRead;
	@Resource
	BikeWxServiceRead bikeWxServiceRead;
	@Resource
	DataDetWxServiceRead dataDetWxServiceRead;
	@Resource
	GradeWxServiceWrite gradeWxServiceWrite;
	@Resource
	UserToTaskWxServiceWrite userToTaskWxServiceWrite;
	@Resource
	UserServiceWeixinWrite userServiceWeixinWrite;
	@Resource
	UserToTaskWxServiceRead userToTaskWxServiceRead;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(OrbitWxController.class);
		
	/**
	 * 根据租赁订单获取json
	 * @param rentInfoId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPointJson", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getPointJson(HttpServletResponse response,Long rentInfoId) throws Exception{
		/*response.setHeader("Access-Control-Allow-Origin", "*");*/
		if(null==rentInfoId){
			messageUtil.setCode(1);
			messageUtil.setMessage("不能为null");
			return messageUtil;
		}
		Map<String, Object> data = new HashMap<String, Object>();
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
		User user = userServiceWeixinRead.findByUId(bikeRentInfo.getRentInfoUserId());
		Bike bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
		data.put("bikeRentInfo", bikeRentInfo);
		data.put("user", user);
		data.put("bike", bike);
		Orbit orbit = orbitWxServiceRead.findByRentInfoId(rentInfoId);
		if(null!=orbit){
			data.put("distance", orbit.getOrbitDistance());
			if(null!=orbit.getOrbitLatlngJson()){
				JSONArray jsonArray = JSONArray.fromObject(orbit.getOrbitLatlngJson());
				data.put("jsonArray", jsonArray);
				
			}
		}
		
		
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("获得轨迹点成功");
		return messageUtil;
	}
	
	@RequestMapping(value = "/getPointJson_2", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getPointJson_2(@RequestHeader HttpHeaders header,HttpServletResponse response,Long rentInfoId) throws Exception{
		/*response.setHeader("Access-Control-Allow-Origin", "*");*/
		String fromFlag = header.getFirst("fromFlag");
		User userLogin = new User();
		Map<String, Object> data = new HashMap<String, Object>();
		BikeRentInfo bikeRentInfo = bikeRentInfoWxServiceRead.findByBikeRentId(rentInfoId);
		User user = userServiceWeixinRead.findByUId(bikeRentInfo.getRentInfoUserId());
		if(null==rentInfoId){
			messageUtil.setCode(1);
			messageUtil.setMessage("不能为null");
			return messageUtil;
		}
		if("1".equals(fromFlag)){
			String token = header.getFirst("token");
			SysParament sysParament = sysParamentServiceRead.findByName("token");
			userLogin = userServiceWeixinRead.findUserByToken(token,Integer.parseInt(sysParament.getSysParamentValue()));
			if(null==userLogin){
				UserToTask userToTask = userToTaskWxServiceRead.findByUserIdAndDataId(user.getUserId(), (long)39);
				if(null==userToTask){
					loggers.info("获取积分任务");
					DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)39);//实名获取积分
					Integer grade = Integer.valueOf(datadet.getDataDetVal());
					user.setUserTotalGrade(user.getUserTotalGrade()+grade);
					user.setUserGrade(user.getUserGrade()+grade);
					user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
					userServiceWeixinWrite.updateUser(user);
					loggers.info("更新用户积分");
					
					UserToTask newuserToTask = new UserToTask();
					newuserToTask.setUserToTaskState(1);
					newuserToTask.setUserId(user.getUserId());
					newuserToTask.setDataDetId((long)39);
					userToTaskWxServiceWrite.addUserToTask(newuserToTask);
					loggers.info("更新用户任务状态");
					//积分记录
					loggers.info("积分记录");
					GradeRecord gradeRecord = new GradeRecord();
					gradeRecord.setGradeCount(grade);
					gradeRecord.setGradeRemark("分享骑行");
					gradeRecord.setGradeUserId(user.getUserId());
					gradeRecord.setGradeCreateTime(new Date());
					gradeWxServiceWrite.addGradeRecord(gradeRecord);
				}else{
					if(userToTask.getUserToTaskState()==0){
						loggers.info("获取积分任务");
						DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)39);//实名获取积分
						Integer grade = Integer.valueOf(datadet.getDataDetVal());
						user.setUserTotalGrade(user.getUserTotalGrade()+grade);
						user.setUserGrade(user.getUserGrade()+grade);
						user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
						userServiceWeixinWrite.updateUser(user);
						loggers.info("更新用户积分");
						
						userToTask.setUserToTaskState(1);
						userToTaskWxServiceWrite.updateUserToTask(userToTask);
						loggers.info("更新用户任务状态");
						//积分记录
						loggers.info("积分记录");
						GradeRecord gradeRecord = new GradeRecord();
						gradeRecord.setGradeCount(grade);
						gradeRecord.setGradeRemark("分享骑行");
						gradeRecord.setGradeUserId(user.getUserId());
						gradeRecord.setGradeCreateTime(new Date());
						gradeWxServiceWrite.addGradeRecord(gradeRecord);
					}else if(userToTask.getUserToTaskState()==1){
						loggers.info("获取积分任务");
						DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)39);//实名获取积分
						Integer grade = Integer.valueOf(datadet.getDataDetVal());
						user.setUserTotalGrade(user.getUserTotalGrade()+grade);
						user.setUserGrade(user.getUserGrade()+grade);
						user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
						userServiceWeixinWrite.updateUser(user);
						loggers.info("更新用户积分");
						
						userToTask.setUserToTaskState(2);
						userToTaskWxServiceWrite.updateUserToTask(userToTask);
						loggers.info("更新用户任务状态");
						//积分记录
						loggers.info("积分记录");
						GradeRecord gradeRecord = new GradeRecord();
						gradeRecord.setGradeCount(grade);
						gradeRecord.setGradeRemark("分享骑行");
						gradeRecord.setGradeUserId(user.getUserId());
						gradeRecord.setGradeCreateTime(new Date());
						gradeWxServiceWrite.addGradeRecord(gradeRecord);
					}
				}
				
			}else{
				if(!userLogin.getUserId().equals(user.getUserId())){
					UserToTask userToTask = userToTaskWxServiceRead.findByUserIdAndDataId(user.getUserId(), (long)39);
					if(null==userToTask){
						loggers.info("获取积分任务");
						DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)39);//实名获取积分
						Integer grade = Integer.valueOf(datadet.getDataDetVal());
						user.setUserTotalGrade(user.getUserTotalGrade()+grade);
						user.setUserGrade(user.getUserGrade()+grade);
						user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
						userServiceWeixinWrite.updateUser(user);
						loggers.info("更新用户积分");
						
						UserToTask newuserToTask = new UserToTask();
						newuserToTask.setUserToTaskState(1);
						newuserToTask.setUserId(user.getUserId());
						newuserToTask.setDataDetId((long)39);
						userToTaskWxServiceWrite.addUserToTask(newuserToTask);
						loggers.info("更新用户任务状态");
						//积分记录
						loggers.info("积分记录");
						GradeRecord gradeRecord = new GradeRecord();
						gradeRecord.setGradeCount(grade);
						gradeRecord.setGradeRemark("分享骑行");
						gradeRecord.setGradeUserId(user.getUserId());
						gradeRecord.setGradeCreateTime(new Date());
						gradeWxServiceWrite.addGradeRecord(gradeRecord);
					}else{
						if(userToTask.getUserToTaskState()==0){
							loggers.info("获取积分任务");
							DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)39);//实名获取积分
							Integer grade = Integer.valueOf(datadet.getDataDetVal());
							user.setUserTotalGrade(user.getUserTotalGrade()+grade);
							user.setUserGrade(user.getUserGrade()+grade);
							user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
							userServiceWeixinWrite.updateUser(user);
							loggers.info("更新用户积分");
							
							userToTask.setUserToTaskState(1);
							userToTaskWxServiceWrite.updateUserToTask(userToTask);
							loggers.info("更新用户任务状态");
							//积分记录
							loggers.info("积分记录");
							GradeRecord gradeRecord = new GradeRecord();
							gradeRecord.setGradeCount(grade);
							gradeRecord.setGradeRemark("分享骑行");
							gradeRecord.setGradeUserId(user.getUserId());
							gradeRecord.setGradeCreateTime(new Date());
							gradeWxServiceWrite.addGradeRecord(gradeRecord);
						}else if(userToTask.getUserToTaskState()==1){
							loggers.info("获取积分任务");
							DataDet datadet = dataDetWxServiceRead.findDataDetByDataDetId((long)39);//实名获取积分
							Integer grade = Integer.valueOf(datadet.getDataDetVal());
							user.setUserTotalGrade(user.getUserTotalGrade()+grade);
							user.setUserGrade(user.getUserGrade()+grade);
							user.setUserLevel(LevelUtil.getLevel(user.getUserTotalGrade()));
							userServiceWeixinWrite.updateUser(user);
							loggers.info("更新用户积分");
							
							userToTask.setUserToTaskState(2);
							userToTaskWxServiceWrite.updateUserToTask(userToTask);
							loggers.info("更新用户任务状态");
							//积分记录
							loggers.info("积分记录");
							GradeRecord gradeRecord = new GradeRecord();
							gradeRecord.setGradeCount(grade);
							gradeRecord.setGradeRemark("分享骑行");
							gradeRecord.setGradeUserId(user.getUserId());
							gradeRecord.setGradeCreateTime(new Date());
							gradeWxServiceWrite.addGradeRecord(gradeRecord);
						}
					}
				}
			}
		}
	
		Bike bike = bikeWxServiceRead.findByBikeId(bikeRentInfo.getRentInfoBikeId());
		data.put("bikeRentInfo", bikeRentInfo);
		data.put("user", user);
		data.put("bike", bike);
		Orbit orbit = orbitWxServiceRead.findByRentInfoId(rentInfoId);
		if(null!=orbit){
			data.put("distance", orbit.getOrbitDistance());
			if(null!=orbit.getOrbitLatlngJson()){
				JSONArray jsonArray = JSONArray.fromObject(orbit.getOrbitLatlngJson());
				data.put("jsonArray", jsonArray);
				
			}
		}
		
		
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("获得轨迹点成功");
		return messageUtil;
	}
	
	
}
