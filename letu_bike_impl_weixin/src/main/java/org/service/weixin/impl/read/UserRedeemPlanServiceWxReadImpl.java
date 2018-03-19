package org.service.weixin.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.UserRedeemPlanMapper;
import org.entity.dto.UserRedeemPlan;
import org.entity.dto.UserRedeemPlanExample;
import org.service.weixin.read.UserRedeemPlanServiceWxRead;
import org.springframework.stereotype.Service;

@Service("userRedeemPlanServiceWxRead")
public class UserRedeemPlanServiceWxReadImpl implements UserRedeemPlanServiceWxRead {
	@Resource
	UserRedeemPlanMapper userRedeemPlanMapper;

	@Override
	public UserRedeemPlan findByUserAndPlan(Long planId, Long userId) throws Exception {
		UserRedeemPlanExample example = new UserRedeemPlanExample();
		UserRedeemPlanExample.Criteria criteria = example.createCriteria();
		criteria.andUrpRedeemPlanIdEqualTo(planId);
		criteria.andUrpUserIdEqualTo(userId);
		List<UserRedeemPlan> userRedeemPlan = userRedeemPlanMapper.selectByExample(example);
		if(userRedeemPlan.size()>0){
			return userRedeemPlan.get(0);
		}
		return null;
	}
}
