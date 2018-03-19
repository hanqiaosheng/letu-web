package org.controller.cms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.component.AppConfig;
import org.entity.dto.Admin;
import org.entity.dto.Bike;
import org.entity.dto.Channel;
import org.entity.dto.Feedback;
import org.entity.dto.Message;
import org.entity.dto.OperateLog;
import org.entity.dto.User;
import org.service.cms.read.AdminServiceRead;
import org.service.cms.read.BikeServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.DataDetServiceRead;
import org.service.cms.read.UserFeedbackServiceRead;
import org.service.cms.read.UserServiceRead;
import org.service.cms.write.MessageServiceWrite;
import org.service.cms.write.OperateServiceWrite;
import org.service.cms.write.UserFeedbackServiceWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.util.OperateUtil;
import org.util.PageUtil;
import org.util.redis.RedisService;

@Controller
@Scope("prototype")
@RequestMapping("cms/user/feedback")
public class UserFeedbackController {

	@Resource
	AppConfig appConfig;

	@Resource
	UserServiceRead userServiceRead;

	@Resource
	DataDetServiceRead dataDetServiceRead;

	@Resource
	UserFeedbackServiceRead userFeedbackServiceRead;

	@Resource
	UserFeedbackServiceWrite userFeedbackServiceWrite;
	
	@Resource
	BikeServiceRead bikeServiceRead;

	@Resource
	MessageServiceWrite messageServiceWrite;
	
	@Resource
	ChannelServiceRead channelServiceRead;
	
	@Resource
	AdminServiceRead adminServiceRead;
	
	@Resource
	OperateServiceWrite operateServiceWrite;
	
	@Autowired
	RedisService redisService;
	
	/**
	 * 用户使用车辆反馈列表
	 * 
	 * @param model
	 * @param pageIndex
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("list")
	public String feedbackList(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, Feedback feedback,HttpSession session)
			throws Exception {
		//取admin的渠道id
    	Long channelId  = (Long) session.getAttribute("currChannelId");
		Integer totalCount = 1;
		List<Feedback> feedbackList = new ArrayList<Feedback>();
		if(null==channelId){
			feedbackList = userFeedbackServiceRead.findFeedbackByCondition(feedback, pageIndex,
					appConfig.getPage_size_web(),null);
			totalCount = userFeedbackServiceRead.countAllFeedback(feedback,null);
		}else{
			Channel channel =channelServiceRead.findById(channelId);
			List<Channel> channels1 = channelServiceRead.findSonChannels(channelId);
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			feedbackList = userFeedbackServiceRead.findFeedbackByCondition(feedback, pageIndex,
					appConfig.getPage_size_web(),currChannelIds);
			totalCount = userFeedbackServiceRead.countAllFeedback(feedback,currChannelIds);
		}
		
		if(null!=feedbackList&&0!=feedbackList.size()){
			for (Feedback f : feedbackList) {
				User user2 = userServiceRead.findById(f.getUserId());
				f.setfUserTel(user2.getUserTel());
				f.setfUserName(user2.getUserRealname());
				if(f.getFeedbackTypeId()!=null){
					f.setfDataDetVal(dataDetServiceRead.findDataDetByDataDetId(f.getFeedbackTypeId()).getDataDetVal());
				}
			}
		}
		//通过redis获取数据字典信息
		/*ListTranscoder<DataDet> transcoder=new ListTranscoder<>();//序列化、反序列化工具
		List<DataDet> typeList = transcoder.deserialize(redisService.get("feedbackType".getBytes()));
		redisService.closeJedis();*/
		Integer totalPage = 1;
		
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		//model.addAttribute("typeList", typeList);
		model.addAttribute("feedbackList", feedbackList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("feedback", feedback);
		return "user_feedback_list";
	}
	
	/**
	 * 用户其它问题反馈列表
	 * @param model
	 * @param pageIndex
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("questionList")
	public String questionList(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, Feedback feedback)
			throws Exception {
		//取admin的渠道id
		
		List<Feedback> feedbackList = userFeedbackServiceRead.findFeedbackByNoBike(feedback, pageIndex,
				appConfig.getPage_size_web());
		Integer totalCount = userFeedbackServiceRead.countAllFeedbackNoBike(feedback);
		if(null!=feedbackList&&0!=feedbackList.size()){
			for (Feedback f : feedbackList) {
				User user2 = userServiceRead.findById(f.getUserId());
				f.setfUserTel(user2.getUserTel());
				f.setfUserName(user2.getUserRealname());
				if(f.getFeedbackTypeId()!=null){
					f.setfDataDetVal(dataDetServiceRead.findDataDetByDataDetId(f.getFeedbackTypeId()).getDataDetVal());
				}
				
			}
		}
		Integer totalPage = 1;
		
		if (totalCount > 0) {
			totalPage = PageUtil.getTotalPage(totalCount);
		}
		model.addAttribute("feedbackList", feedbackList);
		model.addAttribute("pageIndex", pageIndex);
		model.addAttribute("pageSize", appConfig.getPage_size_web());
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("feedback", feedback);
		return "user_question_list";
	}

	/**
	 * 接受、拒绝、删除用户反馈（含批量）
	 * 
	 * @param feedback
	 * @param feedbackIds
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("update")
	public @ResponseBody String updateFeedbackState(Feedback feedback, Long[] feedbackIds, HttpSession session) throws Exception {
		Admin admin = (Admin) session.getAttribute("admin");
		Admin nowAdmin = adminServiceRead.findAdminId(admin.getAdminId());
		Message message = new Message();
		
		feedback.setAdminId(admin.getAdminId());
		feedback.setFeedbackDealtime(new Date());
		if (null != feedbackIds && 0 != feedbackIds.length) {
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			String feedBackIdStr = "";
			for (int i = 0; i < feedbackIds.length; i++) {
				Feedback feedback1 = userFeedbackServiceRead.findById(feedbackIds[i]);
				feedback.setFeedbackId(feedbackIds[i]);
				
				if(1==feedback.getFeedbackState()){  //拒绝
					message.setMessageUserId(feedback1.getUserId());
					message.setMessageAdminId(admin.getAdminId());
					message.setMessageContent("您的反馈信息已被拒绝，如您有疑问，请拨打客服热线0571-56231981");
					message.setMessageSendTime(new Date());
					message.setMessageTitle("反馈结果");
					messageServiceWrite.addMessage(message);
				}
				if(2==feedback.getFeedbackState()){ //接受
					message.setMessageUserId(feedback1.getUserId());
					message.setMessageAdminId(admin.getAdminId());
					message.setMessageContent("您的反馈信息已被接受，相关人员会尽快进行处理");
					message.setMessageSendTime(new Date());
					message.setMessageTitle("反馈结果");
					messageServiceWrite.addMessage(message);
				}
				userFeedbackServiceWrite.updateFeedbackState(feedback);
				feedBackIdStr = feedBackIdStr + "  " +feedbackIds[i];
			}
			if(1==feedback.getFeedbackState()){  //拒绝
				String remark = OperateUtil.operateFBAndInsurance(nowAdmin.getAdminRealname(), 4, feedBackIdStr, null, null);
				operateLog.setOperateRemark(remark);
			}
			if(2==feedback.getFeedbackState()){ //接受
				String remark = OperateUtil.operateFBAndInsurance(nowAdmin.getAdminRealname(), 2, feedBackIdStr, null, null);
				operateLog.setOperateRemark(remark);
			}
			if(3==feedback.getFeedbackState()){ //删除
				String remark = OperateUtil.operateFBAndInsurance(nowAdmin.getAdminRealname(), 9, feedBackIdStr, null, null);
				operateLog.setOperateRemark(remark);
			}
			operateServiceWrite.addOperateLogs(operateLog);	

		} else if (null != feedback.getFeedbackId() && 0 != feedback.getFeedbackId()){
			Feedback feedback1 = userFeedbackServiceRead.findById(feedback.getFeedbackId());
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateTime(new Date());
			operateLog.setOperateAdminId(nowAdmin.getAdminId());
			if(1==feedback.getFeedbackState()){  //拒绝
				message.setMessageUserId(feedback1.getUserId());
				message.setMessageAdminId(admin.getAdminId());
				message.setMessageContent("您的反馈信息已被拒绝，如您有疑问，请拨打客服热线0571-56231981");
				message.setMessageSendTime(new Date());
				message.setMessageTitle("反馈结果");
				messageServiceWrite.addMessage(message);
				
				String remark = OperateUtil.operateFBAndInsurance(nowAdmin.getAdminRealname(), 3, feedback.getFeedbackId().toString(), null, null);
				operateLog.setOperateRemark(remark);
			}
			if(2==feedback.getFeedbackState()){ //接受
				message.setMessageUserId(feedback1.getUserId());
				message.setMessageAdminId(admin.getAdminId());
				message.setMessageContent("您的反馈信息已被接受，相关人员会尽快进行处理");
				message.setMessageSendTime(new Date());
				message.setMessageTitle("反馈结果");
				messageServiceWrite.addMessage(message);
				
				String remark = OperateUtil.operateFBAndInsurance(nowAdmin.getAdminRealname(), 1, feedback.getFeedbackId().toString(), null, null);
				operateLog.setOperateRemark(remark);
			}
			if(3==feedback.getFeedbackState()){ //删除
				String remark = OperateUtil.operateFBAndInsurance(nowAdmin.getAdminRealname(), 5, feedback.getFeedbackId().toString(), null, null);
				operateLog.setOperateRemark(remark);
			}
			if(4==feedback.getFeedbackState()){ //处理
				
				
				String remark = OperateUtil.operateFBAndInsurance(nowAdmin.getAdminRealname(), 10, feedback.getFeedbackId().toString(), null, null);
				operateLog.setOperateRemark(remark);
			}
			userFeedbackServiceWrite.updateFeedbackState(feedback);
			operateServiceWrite.addOperateLogs(operateLog);	
		}
		
		return "redirect:list.action";
	}

	/**
	 * 用户反馈详情
	 * 
	 * @param model
	 * @param pageIndex
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("detail")
	public String feedbackDetail(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, Feedback feedback)
			throws Exception {
		feedback = userFeedbackServiceRead.findById(feedback.getFeedbackId());
		Bike bike=bikeServiceRead.findBikeByBikeId(feedback.getBikeId());
		feedback.setfDataDetVal(
				dataDetServiceRead.findDataDetByDataDetId(feedback.getFeedbackTypeId()).getDataDetVal());
		feedback.setfUserTel(userServiceRead.findById(feedback.getUserId()).getUserTel());
		if (null != feedback.getFeedbackPicaddress() && !"".equals(feedback.getFeedbackPicaddress()))
			feedback.setFeedbackPics(feedback.getFeedbackPicaddress().split(";"));
		model.addAttribute("feedback", feedback);
		model.addAttribute("bike", bike);
		return "detail/user_feedback_detail";
	}
	
	/**
	 * 用户反馈处理
	 * 
	 * @param model
	 * @param pageIndex
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("remarkDetail")
	public String feedbackRemarkDetail(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, Feedback feedback)
			throws Exception {
		feedback = userFeedbackServiceRead.findById(feedback.getFeedbackId());
		Bike bike=bikeServiceRead.findBikeByBikeId(feedback.getBikeId());
		feedback.setfDataDetVal(
				dataDetServiceRead.findDataDetByDataDetId(feedback.getFeedbackTypeId()).getDataDetVal());
		feedback.setfUserTel(userServiceRead.findById(feedback.getUserId()).getUserTel());
		if (null != feedback.getFeedbackPicaddress() && !"".equals(feedback.getFeedbackPicaddress()))
			feedback.setFeedbackPics(feedback.getFeedbackPicaddress().split(";"));
		model.addAttribute("feedback", feedback);
		model.addAttribute("bike", bike);
		return "detail/user_feedback_handle";
	}
	
	/**
	 * 用户反馈处理
	 * 
	 * @param model
	 * @param pageIndex
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("remarkDetail1")
	public String feedbackRemarkDetail1(Model model, @RequestParam(defaultValue = "1") Integer pageIndex, Feedback feedback)
			throws Exception {
		feedback = userFeedbackServiceRead.findById(feedback.getFeedbackId());
		Bike bike=bikeServiceRead.findBikeByBikeId(feedback.getBikeId());
		feedback.setfDataDetVal(
				dataDetServiceRead.findDataDetByDataDetId(feedback.getFeedbackTypeId()).getDataDetVal());
		feedback.setfUserTel(userServiceRead.findById(feedback.getUserId()).getUserTel());
		if (null != feedback.getFeedbackPicaddress() && !"".equals(feedback.getFeedbackPicaddress()))
			feedback.setFeedbackPics(feedback.getFeedbackPicaddress().split(";"));
		model.addAttribute("feedback", feedback);
		model.addAttribute("bike", bike);
		return "detail/user_feedback_handle1";
	}


}
