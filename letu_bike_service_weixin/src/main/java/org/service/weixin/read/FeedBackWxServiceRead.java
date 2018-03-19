package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Feedback;

public interface FeedBackWxServiceRead {
	
	/**
	 * 查询所有反馈信息
	 * @return
	 * @throws Exception
	 */
	public List<Feedback> findAllFeedback(Integer pageIndex,Integer pageSize, List<Long> channelIds) throws Exception;
	
	public Integer countAllFeedback(List<Long> channelIds) throws Exception;
	
	/**
	 * 根据feedbackId查询反馈信息
	 * @return
	 * @throws Exception
	 */
	public Feedback findFeedback(Long feedbackId) throws Exception;

}
