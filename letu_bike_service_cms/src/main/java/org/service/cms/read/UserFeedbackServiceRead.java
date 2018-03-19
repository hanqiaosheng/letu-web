package org.service.cms.read;

import java.util.List;

import org.entity.dto.Feedback;

public interface UserFeedbackServiceRead {
	/**
	 * 根据条件查询用户反馈（分页）
	 * @param feedback
	 * @param pageIndex
	 * @param page_size
	 * @return
	 * @throws Exception
	 */
	public List<Feedback> findFeedbackByCondition(Feedback feedback,Integer pageIndex, Integer pageSize,List<Long> channelIds) throws Exception;

	/**
	 * 通过id查找用户反馈
	 * 
	 * @param feedbackId
	 * @return
	 * @throws Exception
	 */
	public Feedback findById(Long feedbackId) throws Exception;

	


	
	/**
	 * 根据查询条件 查找所有的用户反馈条数
	 * 
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	public Integer countAllFeedback(Feedback feedback,List<Long> channelIds) throws Exception;
	
	/**
	 * 查询所有反馈信息
	 * @return
	 * @throws Exception
	 */
	public List<Feedback> findAllFeedback(Integer pageIndex,Integer pageSize, List<Long> channelIds) throws Exception;
	
	public Integer countAllFeedbacks(List<Long> channelIds) throws Exception;
	
	/**
	 * 根据feedbackId查询反馈信息
	 * @return
	 * @throws Exception
	 */
	public Feedback findFeedback(Long feedbackId) throws Exception;

	/**
	 * 查出不是车辆反馈的列表
	 * @param feedback
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Feedback> findFeedbackByNoBike(Feedback feedback, Integer pageIndex, Integer pageSize)throws Exception;

	/**
	 * 获取不是车辆反馈的数量
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	public Integer countAllFeedbackNoBike(Feedback feedback)throws Exception;
	
	/**
	 * 获取未处理的反馈
	 * @return
	 * @throws Exception
	 */
	public List<Feedback> findUntreated()throws Exception;
	
	/**
	 * 通过渠道查未处理的反馈数量
	 * @return
	 * @throws Exception
	 */
	public int findUntreatedByChannelId(List<Long> channelIds)throws Exception;
	
	/**
	 * 获取不是车辆反馈未处理的数量
	 * @param feedback
	 * @return
	 * @throws Exception
	 */
	public Integer countAllUntreatedFeedbackNoBike()throws Exception;
	
	
	
}
