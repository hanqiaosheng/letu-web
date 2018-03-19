package org.service.weixin.write;

import org.entity.dto.Feedback;

public interface FeedBackWxServiceWrite {
	
	/**
	 * 新增用户反馈
	 * @param feedback
	 * @throws Exception
	 */
	public void addFeedBack(Feedback feedback) throws Exception;
	/**
	 * 修改用户反馈
	 * @param feedback
	 * @throws Exception
	 */
	public void updateFeedBack(Feedback feedback) throws Exception;

}
