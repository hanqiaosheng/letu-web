package org.controller.cms;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import javax.servlet.http.HttpSession;

import org.entity.dto.Admin;
import org.entity.dto.Channel;
import org.service.cms.read.BikeLockInfoServiceRead;
import org.service.cms.read.ChannelServiceRead;
import org.service.cms.read.FixedReturnServiceRead;
import org.service.cms.read.InvoiceServiceRead;
import org.service.cms.read.RefundServiceRead;
import org.service.cms.read.UserFeedbackServiceRead;
import org.service.cms.read.UserInsuranceServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;







@Controller
@Scope("prototype")
@RequestMapping("cms/webSocket")
public class MessageReminder {
	
	

    @Resource
    UserFeedbackServiceRead userFeedbackServiceRead;

    @Resource
    ChannelServiceRead channelServiceRead;
    
    @Resource
    UserInsuranceServiceRead userInsuranceServiceRead;
    
    @Resource
    RefundServiceRead refundServiceRead;
    
    @Resource
    BikeLockInfoServiceRead bikeLockInfoServiceRead;
    
    @Resource
    InvoiceServiceRead invoiceServiceRead;
    
    @Resource
    FixedReturnServiceRead fixedReturnServiceRead;
    
    /**
     * 查询未处理的车辆反馈数量
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/auditing")
    @ResponseBody
    public int auditing(HttpSession session)throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");
    	if(admin.getAdminChannelId()!=null){
    		Channel channel =channelServiceRead.findById(admin.getAdminChannelId());
			List<Channel> channels1 = channelServiceRead.findSonChannels(admin.getAdminChannelId());
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			int feedbackBike = userFeedbackServiceRead.findUntreatedByChannelId(currChannelIds);
			if(feedbackBike>0){
	    		return feedbackBike;
	    	}
    	}
    	int feedbacknum = userFeedbackServiceRead.findUntreatedByChannelId(null);
    	if(feedbacknum>0){
    		return feedbacknum;
    	}
        return 0;
    }
    
    /**
     * 查询未处理的用户反馈数量
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/feedback")
    @ResponseBody
    public int feedback(HttpSession session)throws Exception{
    	
    	int feedbacknum = userFeedbackServiceRead.countAllUntreatedFeedbackNoBike();
    	if(feedbacknum>0){
    		return feedbacknum;
    	}
        return 0;
    }
    
    /**
     * 查询未处理的保险申请数量
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/insurance")
    @ResponseBody
    public int insurance(HttpSession session)throws Exception{
    	
    	Integer insurancecount = userInsuranceServiceRead.countUntreated();
    	if(insurancecount>0){
    		return insurancecount;
    	}
        return 0;
    }
    
    /**
     * 查询未成功的退款数量
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/refund")
    @ResponseBody
    public int refund(HttpSession session)throws Exception{
    	
    	Integer refundcount = refundServiceRead.findUnsuccess();
    	if(refundcount>0){
    		return refundcount;
    	}
        return 0;
    }
    
    /**
     * 查询未在线的车锁数量
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/lock1")
    @ResponseBody
    public int lock1(HttpSession session)throws Exception{
    	
    	Integer lockcount = bikeLockInfoServiceRead.countuntreated();
    	if(lockcount>0){
    		return lockcount;
    	}
        return 0;
    }
    
    /**
     * 查询低电量的车锁数量
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/lock2")
    @ResponseBody
    public int lock2(HttpSession session)throws Exception{
    	
    	Integer lockcount1 = bikeLockInfoServiceRead.countLowBattery();
    	if(lockcount1>0){
    		return lockcount1;
    	}
        return 0;
    }
    
    /**
     * 查询未处理和未发货的发票数量
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/invoice")
    @ResponseBody
    public int invoice(HttpSession session)throws Exception{
    	
    	Integer invoicecount = invoiceServiceRead.countUntreated();
    	if(invoicecount>0){
    		return invoicecount;
    	}
        return 0;
    }
    
    /**
     * 查询未审核的还车点数量
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/return")
    @ResponseBody
    public int fixReturn(HttpSession session)throws Exception{
    	Admin admin = (Admin) session.getAttribute("admin");
    	if(admin.getAdminChannelId()!=null){
    		Channel channel =channelServiceRead.findById(admin.getAdminChannelId());
			List<Channel> channels1 = channelServiceRead.findSonChannels(admin.getAdminChannelId());
			channels1.add(channel);
			List<Long> currChannelIds = new ArrayList<Long>();
			for(Channel c:channels1){
				currChannelIds.add(c.getChannelId());
			}
			Integer fixReturncount = fixedReturnServiceRead.countUntreated(currChannelIds);
	    	if(fixReturncount>0){
	    		return fixReturncount;
	    	
	    	}
    	}
    	else{
    		Integer fixReturncount = fixedReturnServiceRead.countUntreated(null);
        	if(fixReturncount>0){
        		return fixReturncount;
        	
        	}
    	}
    	
        return 0;
    }




}
