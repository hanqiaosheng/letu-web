package org.service.cms.impl.write;


import javax.annotation.Resource;

import org.dao.RedeemPlanMapper;
import org.entity.dto.RedeemPlan;
import org.service.cms.write.RedeemPlanServiceWrite;
import org.springframework.stereotype.Service;


@Service("redeemPlanServiceWrite")
public class RedeemPlanServiceWriteImpl implements RedeemPlanServiceWrite {
	@Resource
	RedeemPlanMapper redeemPlanMapper;

	@Override
	public void addRedeemPlan(RedeemPlan redeemPlan) throws Exception {
		// TODO Auto-generated method stub
		redeemPlanMapper.insertSelective(redeemPlan);
	}

	@Override
	public void updateRedeemPlan(RedeemPlan redeemPlan) throws Exception {
		// TODO Auto-generated method stub
		redeemPlanMapper.updateByPrimaryKeySelective(redeemPlan);
	}

	
}
