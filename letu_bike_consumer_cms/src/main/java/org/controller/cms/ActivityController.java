package org.controller.cms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.component.AppConfig;
import org.entity.dto.Activity;
import org.entity.dto.City;
import org.service.cms.read.ActivityServiceRead;
import org.service.cms.read.CityServiceRead;
import org.service.cms.write.ActivityServiceWrite;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.util.PageUtil;
import org.util.UploadImageUtil;

@Controller
@Scope("prototype")
@RequestMapping("cms/activity")
public class ActivityController {
	@Resource
	ActivityServiceRead activityServiceRead;
	@Resource
	ActivityServiceWrite activityServiceWrite;
	@Resource
	CityServiceRead cityServiceRead;
	@Resource
	AppConfig appConfig;
	
	/**
	 * 活动列表
	 * @param model
	 * @param pageIndex
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String list(Model model,@RequestParam(defaultValue="1")Integer pageIndex,String name,Date startTime,Date endTime)throws Exception{
		List<Activity> activities = activityServiceRead.findAllList(pageIndex,appConfig.getPage_size_web(),name,startTime,endTime);
		for(Activity a : activities){
			if(null!=a.getActivityCityId()){
				City city = cityServiceRead.findById(a.getActivityCityId());
				a.setCityName(city.getCityName());
			}
		}
		Integer pageCount = activityServiceRead.getCounts(name,startTime,endTime);
		Integer totalPage = 1;
		if(pageCount>0){
			totalPage = PageUtil.getTotalPage(pageCount);
		}
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("activities", activities);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("name", name);
		return "activity_list";
	}
	
	/**
	 * 跳转添加页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addJsp")
	public String addJsp(Model model)throws Exception{
		List<City> cityList = cityServiceRead.findAll();
		model.addAttribute("cityList", cityList);
		return "detail/activity_add";
	}
	
	/**
	 * 添加活动
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("add")
	public String add(Activity activity,MultipartFile file)throws Exception{
		activity.setActivityCreateTime(new Date());
		if (!file.isEmpty()) {
			String imgurl = UploadImageUtil.upload(appConfig.getUpload_path(),file);
			activity.setActivityImage(imgurl);
		}
		activityServiceWrite.addActivity(activity);
		return "redirect:list.action";
	}
	
	/**
	 * 跳转编辑页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("editJsp")
	public String editJsp(Long activityId,Model model)throws Exception{
		List<City> cityList = cityServiceRead.findAll();
		Activity activity = activityServiceRead.findById(activityId);
		City city = cityServiceRead.findById(activity.getActivityCityId());
		model.addAttribute("activity", activity);
		model.addAttribute("cityList", cityList);
		model.addAttribute("city", city);
		return "detail/activity_edit";
	}
	
	/**
	 * 编辑
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("edit")
	public String edit(Activity activity,MultipartFile file)throws Exception{
		if (!file.isEmpty()) {
			String imgurl = UploadImageUtil.upload(appConfig.getUpload_path(),file);
			activity.setActivityImage(imgurl);;
		}
		activityServiceWrite.updateActivity(activity);
		return "redirect:list.action";
	}
	
	/**
	 * 删除
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete")
	public @ResponseBody String delete(Activity activity)throws Exception{
		activity.setActivityState(0);//删除
		activityServiceWrite.updateActivity(activity);
		return "success";
	}
	
	/**
	 * 活动详情
	 * @param activityId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String detail(Long activityId,Model model)throws Exception{
		Activity activity = activityServiceRead.findById(activityId);
		City city = cityServiceRead.findById(activity.getActivityCityId());
		model.addAttribute("activity", activity);
		model.addAttribute("city", city);
		return "detail/activity_detail";
	}
	
}
