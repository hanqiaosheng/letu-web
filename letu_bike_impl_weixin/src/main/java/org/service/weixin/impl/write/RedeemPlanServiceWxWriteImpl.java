package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.RedeemPlanMapper;
import org.entity.dto.RedeemPlan;
import org.service.weixin.write.RedeemPlanServiceWxWrite;
import org.springframework.stereotype.Service;

@Service("redeemPlanServiceWxWrite")
public class RedeemPlanServiceWxWriteImpl implements RedeemPlanServiceWxWrite {
	@Resource
	RedeemPlanMapper redeemPlanMapper;

	@Override
	public void updateRedeemPlan(RedeemPlan redeemPlan) throws Exception {
		redeemPlanMapper.updateByPrimaryKeySelective(redeemPlan);
	}

	
}
