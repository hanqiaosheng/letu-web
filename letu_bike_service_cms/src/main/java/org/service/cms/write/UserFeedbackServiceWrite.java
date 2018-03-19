package org.service.cms.write;

import org.entity.dto.Feedback;

public interface UserFeedbackServiceWrite {
	/**
	 * 编辑反馈信息（处理，删除）
	 * 
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	public void updateFeedbackState(Feedback feedback) throws Exception;
	
	


}
