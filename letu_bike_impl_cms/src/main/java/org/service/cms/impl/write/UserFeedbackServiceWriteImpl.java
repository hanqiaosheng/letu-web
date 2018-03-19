package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.FeedbackMapper;
import org.entity.dto.Feedback;
import org.springframework.stereotype.Service;
@Service("userFeedbackServiceWrite")
public class UserFeedbackServiceWriteImpl implements org.service.cms.write.UserFeedbackServiceWrite {
	@Resource
	FeedbackMapper feedbackMapper;

	@Override
	public void updateFeedbackState(Feedback feedback) throws Exception {
		// TODO Auto-generated method stub
		feedbackMapper.updateByPrimaryKeySelective(feedback);
	}

}
