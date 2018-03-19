package org.controller.weixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.Account;
import org.entity.dto.DataDet;
import org.entity.dto.GradeRecord;
import org.entity.dto.SysParament;
import org.entity.dto.User;
import org.entity.dto.UserTask;
import org.entity.dto.UserToTask;
import org.service.weixin.read.AccountWxServiceRead;
import org.service.weixin.read.DataDetWxServiceRead;
import org.service.weixin.read.GradeWxServiceRead;
import org.service.weixin.read.SysParamentServiceRead;
import org.service.weixin.read.UserServiceWeixinRead;
import org.service.weixin.read.UserToTaskWxServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.LevelUtil;
import org.util.MessageUtil;
import org.util.PageUtil;


@Controller
@Scope("prototype")
@RequestMapping(value = "/gradeRecord", method = RequestMethod.POST)
public class GradeRecordWxController {

	@Resource
	SysParamentServiceRead sysParamentServiceRead;
	@Resource
	UserServiceWeixinRead userServiceWeixinRead;
	@Resource
	GradeWxServiceRead gradeWxServiceRead;
	@Resource
	DataDetWxServiceRead dataDetWxServiceRead;
	@Resource
	UserToTaskWxServiceRead userToTaskWxServiceRead;
	@Resource
	AccountWxServiceRead accountWxServiceRead;
	@Resource
	AppConfig appConfig;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(GradeRecordWxController.class);
		

	/**
	 * 用户积分记录
	 * @param header
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userGradeRecord", method = RequestMethod.POST)
	public @ResponseBody MessageUtil userGradeRecord(@RequestHeader HttpHeaders header,HttpSession session,@RequestParam(defaultValue="1")Integer pageIndex,String tName) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
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
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("停留时间过长");
				return messageUtil;
			}
		}else{
			//小程序
		}
		List<String> strList = new ArrayList<String>();
		strList.add("全部");
		strList.add("获取");
		strList.add("使用");
		Integer intValue = -1;
		if(null!=tName){
			if(tName.equals("获取")){
				intValue = 0;
			}else if(tName.equals("使用")){
				intValue = 1;
			}
		}
		User userInfo = userServiceWeixinRead.findByUId(userLogin.getUserId());
		List<GradeRecord> gradeRecordList = gradeWxServiceRead.findByUserId(pageIndex,appConfig.getPage_size_weixin(),userLogin.getUserId(),intValue);
		Integer totalPage = 1;
		Integer totalCount = gradeWxServiceRead.countAllRecord(userLogin.getUserId(),intValue);
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		data.put("user", userInfo);
		data.put("totalPage", totalPage);
		data.put("tName", tName);
		data.put("pageIndex", pageIndex);
		data.put("strList", strList);
		data.put("pageSize", appConfig.getPage_size_weixin());
		data.put("gradeRecordList", gradeRecordList);
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}

	/**
	 * 赚积分页面
	 * @param header
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getGrade", method = RequestMethod.POST)
	public @ResponseBody MessageUtil getGrade(@RequestHeader HttpHeaders header,HttpSession session) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
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
		}else if("2".equals(fromFlag)){
			userLogin = (User) session.getAttribute("curruser");
			if(null==userLogin){
				messageUtil.setCode(2);
				messageUtil.setMessage("停留时间过长");
				return messageUtil;
			}
		}else{
			//小程序
		}
		User userInfo = userServiceWeixinRead.findByUId(userLogin.getUserId());
		Account account = accountWxServiceRead.findByAccountId(userInfo.getUserAccountId());
		Integer needLevel = userInfo.getUserLevel()+1;
		Integer minGrade = LevelUtil.getMinExp(needLevel);
		Integer needGrade = minGrade-userInfo.getUserGrade();
		List<DataDet> dataDets = dataDetWxServiceRead.findDataDetByDataId((long)10);
		for(int i=0;i<dataDets.size();i++){
			UserToTask userToTask = userToTaskWxServiceRead.findByUserIdAndDataId(userLogin.getUserId(), dataDets.get(i).getDataDetId());
			
			if(dataDets.get(i).getDataDetId()==37){
				UserTask userTask = new UserTask();
				userTask.setUserTaskName("邀请好友");
				userTask.setUserTaskBrief("邀请一位好友下载并注册获取20分");
				userTask.setUserTaskMessage("去邀请");
				userTask.setUserTaskImageName("inviteImage");
				dataDets.get(i).setUserTask(userTask);
			}else if(dataDets.get(i).getDataDetId()==38){
				UserTask userTask = new UserTask();
				userTask.setUserTaskName("日常骑行");
				userTask.setUserTaskBrief("完成超过5分钟的骑行获得10分");
				userTask.setUserTaskMessage("去骑行");
				userTask.setUserTaskImageName("rentImage");
				dataDets.get(i).setUserTask(userTask);
			}else if(dataDets.get(i).getDataDetId()==39){
				UserTask userTask = new UserTask();
				if(null==userToTask){
					userTask.setUserTaskSate(0);
				}else{
					userTask.setUserTaskSate(userToTask.getUserToTaskState());
				}
				userTask.setUserTaskImageName("shareImage");
				userTask.setUserTaskName("分享骑行");
				userTask.setUserTaskBrief("分享一次骑行记录获取2分");
				if(userTask.getUserTaskSate()==2){
					userTask.setUserTaskMessage("已完成");
				}else{
					userTask.setUserTaskMessage("去分享");
				}
				dataDets.get(i).setUserTask(userTask);
			}else if(dataDets.get(i).getDataDetId()==40){
				if(null!=userInfo.getUserChannelId()&&userInfo.getUserChannelId()>0){
					UserTask userTask = new UserTask();
					if(null==userToTask){
						userTask.setUserTaskSate(0);
					}else{
						userTask.setUserTaskSate(userToTask.getUserToTaskState());
					}
					userTask.setUserTaskImageName("rechargeImage");
					userTask.setUserTaskName("首次充值");
					userTask.setUserTaskBrief("完成首次充值赠送50分");
					if(userTask.getUserTaskSate()==2){
						userTask.setUserTaskMessage("已完成");
					}else{
						userTask.setUserTaskMessage("去充值");
					}
					dataDets.get(i).setUserTask(userTask);
				}else{
					dataDets.remove(i);
					i--;
				}
			}else if(dataDets.get(i).getDataDetId()==41){
				UserTask userTask = new UserTask();
				userTask.setUserTaskImageName("loginImage");
				userTask.setUserTaskName("每日登录");
				userTask.setUserTaskBrief("每日登录APP赠送2分");
				userTask.setUserTaskMessage("已完成");
				dataDets.get(i).setUserTask(userTask);
			}
		}
		data.put("userTotalGrade", userInfo.getUserTotalGrade());
		data.put("needGrade", needGrade);
		data.put("needLevel", needLevel);
		data.put("nowLevel", userInfo.getUserLevel());
		data.put("taskList", dataDets);
		data.put("account", account);
		messageUtil.setData(data);
		messageUtil.setCode(1);
		messageUtil.setMessage("success");
		return messageUtil;
	}

}
