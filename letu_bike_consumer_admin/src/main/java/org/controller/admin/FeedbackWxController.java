package org.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.Channel;
import org.entity.dto.Feedback;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.UserFeedbackServiceRead;
import org.service.cms.write.UserFeedbackServiceWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.MessageUtil;
import org.util.redis.RedisService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/api/feedback", method = RequestMethod.POST)
public class FeedbackWxController {
	
	@Resource
	UserFeedbackServiceRead userFeedbackServiceRead;
	
	@Resource
	UserFeedbackServiceWrite userFeedbackServiceWrite;
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	BikeServiceRead bikeServiceRead;
	
	@Resource
	AppConfig appConfig;
	
	@Autowired
	RedisService redisService;

	protected final MessageUtil messageUtil = new MessageUtil();
	
	
	
	/**
	 * 反馈列表
	 * @param session
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="list", method = RequestMethod.POST)
	public @ResponseBody MessageUtil list(HttpSession session,@RequestParam(defaultValue="1")Integer pageIndex) throws Exception{
		Admin admin_session = (Admin) session.getAttribute("admin");
		Admin admin = adminServiceRead.findAdminId(admin_session.getAdminId());
		List<Feedback> feedbackList = new ArrayList<Feedback>();
		Integer totalPage = 1;
		if(admin.getAdminChannelId()==null){
			feedbackList = userFeedbackServiceRead.findAllFeedback(pageIndex,appConfig.getPage_size_weixin(),null);
			totalPage = userFeedbackServiceRead.countAllFeedbacks(null);
   	    }else{
   		    Channel channel =channelServiceRead.findById(admin.getAdminChannelId());
			List<Channel> channels1 = channelServiceRead.findSonChannels(admin.getAdminChannelId());
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			feedbackList = userFeedbackServiceRead.findAllFeedback(pageIndex,appConfig.getPage_size_weixin(),currChannelIds);
			
			totalPage = userFeedbackServiceRead.countAllFeedbacks(currChannelIds);
       	}
		if(null!=feedbackList&&0!=feedbackList.size()){
			for(Feedback f:feedbackList){
				f.setBike(bikeServiceRead.findBikeByBikeId(f.getBikeId()));
				f.setfDataDetVal(redisService.get("det"+f.getFeedbackTypeId()));
			}
		}
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("feedbackList", feedbackList);
		data.put("totalPage", totalPage);
		messageUtil.setData(data);
		return messageUtil;
	}
	
	
	/**
	 * 
	 * @param feedbackId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="detail", method = RequestMethod.POST)
	public @ResponseBody MessageUtil detail(Long feedbackId)throws Exception{
		Feedback feedback = userFeedbackServiceRead.findFeedback(feedbackId);
		Map<String,Object> data = new HashMap<String,Object>();
		feedback.setBike(bikeServiceRead.findBikeByBikeId(feedback.getBikeId()));
		feedback.setfDataDetVal(redisService.get("det"+feedback.getFeedbackTypeId()));
		data.put("feedback", feedback);
		messageUtil.setData(data);
		return messageUtil;
	}
	/**
	 * 
	 * @param feedbackId
	 * @param feedbackState
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="update", method = RequestMethod.POST)
	public @ResponseBody MessageUtil update(Long feedbackId,Integer feedbackState)throws Exception{
		Feedback feedback = new Feedback();
		feedback.setFeedbackId(feedbackId);
		feedback.setFeedbackState(feedbackState);
		feedback.setFeedbackDealtime(new Date());
		userFeedbackServiceWrite.updateFeedbackState(feedback);
		messageUtil.setMessage("success");
		return messageUtil;
	}
	
}
