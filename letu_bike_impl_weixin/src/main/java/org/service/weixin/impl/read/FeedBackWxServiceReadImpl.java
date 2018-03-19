package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.ChannelMapper;
import org.dao.FeedbackMapper;
import org.entity.dto.Feedback;
import org.entity.dto.FeedbackExample;
import org.service.weixin.read.FeedBackWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("feedBackWxServiceRead")
public class FeedBackWxServiceReadImpl implements FeedBackWxServiceRead {

	@Resource
	FeedbackMapper feedbackMapper;

	@Resource
	ChannelMapper channelMapper;

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
	public Integer countAllFeedback(List<Long> channelIds) throws Exception {
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

}
