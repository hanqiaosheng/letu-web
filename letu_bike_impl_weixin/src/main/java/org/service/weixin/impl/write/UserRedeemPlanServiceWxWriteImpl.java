package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.UserRedeemPlanMapper;
import org.entity.dto.UserRedeemPlan;
import org.service.weixin.write.UserRedeemPlanServiceWxWrite;
import org.springframework.stereotype.Service;

@Service("userRedeemPlanServiceWxWrite")
public class UserRedeemPlanServiceWxWriteImpl implements UserRedeemPlanServiceWxWrite {
	@Resource
	UserRedeemPlanMapper userRedeemPlanMapper;

	@Override
	public void addUserRedeemPlan(UserRedeemPlan urp) throws Exception{
		userRedeemPlanMapper.insertSelective(urp);
	}
	
}
