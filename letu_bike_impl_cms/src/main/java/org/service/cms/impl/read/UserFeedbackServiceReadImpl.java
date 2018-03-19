package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.FeedbackMapper;
import org.entity.dto.Feedback;
import org.entity.dto.FeedbackExample;
import org.service.cms.read.UserFeedbackServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;
@Service("userFeedbackServiceRead")
public class UserFeedbackServiceReadImpl implements UserFeedbackServiceRead {
	@Resource
	FeedbackMapper feedbackMapper;

	@Override
	public List<Feedback> findFeedbackByCondition(Feedback feedback, Integer pageIndex, Integer pageSize,List<Long> channelIds)
			throws Exception {
		// TODO Auto-generated method stub
		FeedbackExample example = new FeedbackExample();
		FeedbackExample.Criteria criteria = example.createCriteria();
		if (null != feedback.getfUserTel() && !"".equals(feedback.getfUserTel())) {
			criteria.andUserTelLike("%"+feedback.getfUserTel()+"%");
		}
		if(null != feedback.getfUserName() && !"".equals(feedback.getfUserName())){
			criteria.andUserNameLike("%"+feedback.getfUserName()+"%");
		}
		if (channelIds != null) {
			criteria.andModelsChannelIdIn(channelIds);
		}
		if (null != feedback.getFeedbackState())
			criteria.andFeedbackStateEqualTo(feedback.getFeedbackState());
		if (null != feedback.getFeedbackTypeId() && 0!=feedback.getFeedbackTypeId())
			criteria.andFeedbackTypeIdEqualTo(feedback.getFeedbackTypeId());
		
		if(null != feedback.getFromTime() && !"".equals(feedback.getFromTime())){
			criteria.andFeedbackTimeGreaterThanOrEqualTo(feedback.getFromTime());
		}
		
		if(null != feedback.getToTime() && !"".equals(feedback.getToTime())){
			criteria.andFeedbackTimeLessThan(DateUtil.plusDate(feedback.getToTime(),1));
		}
		criteria.andBikeIdIsNotNull();//车辆反馈
		criteria.andFeedbackStateNotEqualTo(3);//排除已删除
		example.setOrderByClause("feedback_state asc, feedback_time desc");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return feedbackMapper.selectUnionByExample(example);
	}

	@Override
	public Feedback findById(Long feedbackId) throws Exception {
		// TODO Auto-generated method stub
		return feedbackMapper.selectByPrimaryKey(feedbackId);
	}

	@Override
	public Integer countAllFeedback(Feedback feedback,List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		FeedbackExample example = new FeedbackExample();
		FeedbackExample.Criteria criteria = example.createCriteria();
		if (null != feedback.getfUserTel() && !"".equals(feedback.getfUserTel())) {
			criteria.andUserTelLike("%"+feedback.getfUserTel()+"%");
		}
		if(null != feedback.getfUserName() && !"".equals(feedback.getfUserName())){
			criteria.andUserNameLike("%"+feedback.getfUserName()+"%");
		}
		if (null != feedback.getFeedbackState() && 0!=feedback.getFeedbackState())
			criteria.andFeedbackStateEqualTo(feedback.getFeedbackState());
		if (null != feedback.getFeedbackTypeId() && 0!=feedback.getFeedbackTypeId())
			criteria.andFeedbackTypeIdEqualTo(feedback.getFeedbackTypeId());
		
		if (channelIds != null) {
			criteria.andModelsChannelIdIn(channelIds);
		}
		if(null != feedback.getFromTime() && !"".equals(feedback.getFromTime())){
			criteria.andFeedbackTimeGreaterThanOrEqualTo(feedback.getFromTime());
		}
		
		if(null != feedback.getToTime() && !"".equals(feedback.getToTime())){
			criteria.andFeedbackTimeLessThan(DateUtil.plusDate(feedback.getToTime(),1));
		}
		criteria.andBikeIdIsNotNull();//车辆反馈
		criteria.andFeedbackStateNotEqualTo(3);//排除已删除
		return feedbackMapper.countUnionByExample(example);
	}
	
	@Override
	public List<Feedback> findAllFeedback(Integer pageIndex, Integer pageSize, List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		FeedbackExample feedbackExample = new FeedbackExample();
		FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
		if (channelIds != null) {
			criteria.andModelsChannelIdIn(channelIds);
		}
		criteria.andBikeIdIsNotNull();
		criteria.andFeedbackStateNotEqualTo(3);
		feedbackExample.setOrderByClause("feedback_time desc");
		feedbackExample.setLimitStart(PageUtil.getStart(pageIndex));
		feedbackExample.setLimitEnd(pageSize);
		List<Feedback> feedbackList = feedbackMapper.selectUnionByExample(feedbackExample);
		return feedbackList;
	}

	@Override
	public Feedback findFeedback(Long feedbackId) throws Exception {
		// TODO Auto-generated method stub
		Feedback feedback = feedbackMapper.selectByPrimaryKey(feedbackId);
		return feedback;
	}

	@Override
	public Integer countAllFeedbacks(List<Long> channelIds) throws Exception {
		FeedbackExample feedbackExample = new FeedbackExample();
		FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
		if (channelIds != null) {
			criteria.andModelsChannelIdIn(channelIds);
		}
		criteria.andBikeIdIsNotNull();
		criteria.andFeedbackStateNotEqualTo(3);
		Integer pageCount = feedbackMapper.countUnionByExample(feedbackExample);
		Integer totalPage = PageUtil.getWxTotalPage(pageCount);
		return totalPage;
	}

	@Override
	public List<Feedback> findFeedbackByNoBike(Feedback feedback, Integer pageIndex, Integer pageSize)
			throws Exception {
		FeedbackExample example = new FeedbackExample();
		FeedbackExample.Criteria criteria = example.createCriteria();
		if (null != feedback.getfUserTel() && !"".equals(feedback.getfUserTel())) {
			criteria.andUserTelLike("%"+feedback.getfUserTel()+"%");
		}
		if(null != feedback.getfUserName() && !"".equals(feedback.getfUserName())){
			criteria.andUserNameLike("%"+feedback.getfUserName()+"%");
		}
		if (null != feedback.getFeedbackState())
			criteria.andFeedbackStateEqualTo(feedback.getFeedbackState());
		if (null != feedback.getFeedbackTypeId() && 0!=feedback.getFeedbackTypeId())
			criteria.andFeedbackTypeIdEqualTo(feedback.getFeedbackTypeId());
		
		if(null != feedback.getFromTime() && !"".equals(feedback.getFromTime())){
			criteria.andFeedbackTimeGreaterThanOrEqualTo(feedback.getFromTime());
		}
		
		if(null != feedback.getToTime() && !"".equals(feedback.getToTime())){
			criteria.andFeedbackTimeLessThan(DateUtil.plusDate(feedback.getToTime(),1));
		}
		criteria.andBikeIdIsNull();//车辆反馈
		criteria.andFeedbackStateNotEqualTo(3);//排除已删除
		example.setOrderByClause("feedback_state asc, feedback_time desc");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return feedbackMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countAllFeedbackNoBike(Feedback feedback) throws Exception {
		FeedbackExample example = new FeedbackExample();
		FeedbackExample.Criteria criteria = example.createCriteria();
		if (null != feedback.getfUserTel() && !"".equals(feedback.getfUserTel())) {
			criteria.andUserTelLike("%"+feedback.getfUserTel()+"%");
		}
		if(null != feedback.getfUserName() && !"".equals(feedback.getfUserName())){
			criteria.andUserNameLike("%"+feedback.getfUserName()+"%");
		}
		if (null != feedback.getFeedbackState() && 0!=feedback.getFeedbackState())
			criteria.andFeedbackStateEqualTo(feedback.getFeedbackState());
		if (null != feedback.getFeedbackTypeId() && 0!=feedback.getFeedbackTypeId())
			criteria.andFeedbackTypeIdEqualTo(feedback.getFeedbackTypeId());
		
		if(null != feedback.getFromTime() && !"".equals(feedback.getFromTime())){
			criteria.andFeedbackTimeGreaterThanOrEqualTo(feedback.getFromTime());
		}
		
		if(null != feedback.getToTime() && !"".equals(feedback.getToTime())){
			criteria.andFeedbackTimeLessThan(DateUtil.plusDate(feedback.getToTime(),1));
		}
		criteria.andBikeIdIsNull();//车辆反馈
		criteria.andFeedbackStateNotEqualTo(3);//排除已删除
		return feedbackMapper.countUnionByExample(example);
	}

	@Override
	public List<Feedback> findUntreated() throws Exception {
		// TODO Auto-generated method stub
		FeedbackExample feedbackExample = new FeedbackExample();
		FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
		criteria.andFeedbackStateEqualTo(0);
		return feedbackMapper.selectUnionByExample(feedbackExample);
	}

	@Override
	public int findUntreatedByChannelId(List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		FeedbackExample feedbackExample = new FeedbackExample();
		FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
		if (channelIds != null) {
			criteria.andModelsChannelIdIn(channelIds);
		}
		criteria.andBikeIdIsNotNull();
		criteria.andFeedbackStateEqualTo(0);
		Integer pageCount = feedbackMapper.countUnionByExample(feedbackExample);
		
		return pageCount;
	}

	@Override
	public Integer countAllUntreatedFeedbackNoBike() throws Exception {
		// TODO Auto-generated method stub
		FeedbackExample feedbackExample = new FeedbackExample();
		FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
		criteria.andBikeIdIsNull();
		criteria.andFeedbackStateEqualTo(0);
		Integer pageCount = feedbackMapper.countUnionByExample(feedbackExample);
		
		return pageCount;
		
	}

}
