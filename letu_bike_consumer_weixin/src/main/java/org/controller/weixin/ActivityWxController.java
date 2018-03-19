package org.controller.weixin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.component.AppConfig;
import org.entity.dto.Activity;

import org.service.weixin.read.ActivityWxServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
import org.util.PageUtil;

/**
 * 活动
 * @author Administrator
 *
 */

@Controller
@Scope("prototype")
@RequestMapping(value = "/activity", method = RequestMethod.POST)
public class ActivityWxController {
	
	@Resource
	AppConfig AppConfig;
	@Resource
	ActivityWxServiceRead activityWxServiceRead;
	
	protected final MessageUtil messageUtil = new MessageUtil();
	
	//当前类的logger日志
	private static Logger loggers = Logger.getLogger(ActivityWxController.class);
	
	/**
	 * app活动列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/activityListApp", method = RequestMethod.POST)
	public 	@ResponseBody MessageUtil activityListApp(@RequestParam(defaultValue = "1")Integer pageIndex,String cityCode) throws Exception{
		Map<String,Object> data = new HashMap<String,Object>();
		//查询所有的活动列表
		List<Activity> activityList = activityWxServiceRead.findAllActivity(pageIndex,5,cityCode);//每页显示5个
		for(Activity activity : activityList){
			activity.setActivityImage(AppConfig.getBase_path_weixin()+"upload/"+activity.getActivityImage());
		}
		Integer totalCount = activityWxServiceRead.findAllActivityCount(cityCode);
		Integer totalPage = PageUtil.getTotalPage(totalCount);
		data.put("pageSize", AppConfig.getPage_size_weixin());
		data.put("activityList", activityList);
		data.put("totalCount", totalCount);
		data.put("pageIndex", pageIndex);
		data.put("totalPage", totalPage);
		messageUtil.setCode(1);
		messageUtil.setData(data);
		messageUtil.setMessage("success");
		return messageUtil;
	}
}
