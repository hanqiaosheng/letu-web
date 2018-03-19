package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.FeedbackMapper;
import org.entity.dto.Feedback;
import org.service.weixin.write.FeedBackWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("feedBackWxServiceWrite")
public class FeedBackWxServiceWriteImpl implements FeedBackWxServiceWrite {

	@Resource
	FeedbackMapper feedbackMapper;
	
	
	@Override
	public void addFeedBack(Feedback feedback) throws Exception {
		// TODO Auto-generated method stub
		feedbackMapper.insertSelective(feedback);
	}


	@Override
	public void updateFeedBack(Feedback feedback) throws Exception {
		// TODO Auto-generated method stub
		feedbackMapper.updateByPrimaryKeySelective(feedback);
	}

}
