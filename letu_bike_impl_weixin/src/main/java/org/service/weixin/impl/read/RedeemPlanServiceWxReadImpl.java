package org.service.weixin.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.RedeemPlanMapper;
import org.entity.dto.RedeemPlan;
import org.entity.dto.RedeemPlanExample;
import org.service.weixin.read.RedeemPlanServiceWxRead;
import org.springframework.stereotype.Service;

@Service("redeemPlanServiceWxRead")
public class RedeemPlanServiceWxReadImpl implements RedeemPlanServiceWxRead {
	@Resource
	RedeemPlanMapper redeemPlanMapper;
 

	@Override
	public RedeemPlan findRedeemPlanByCode(String redeemCode) throws Exception {
		RedeemPlanExample example = new RedeemPlanExample();
		RedeemPlanExample.Criteria criteria = example.createCriteria();
		criteria.andPlanRedeemCodeEqualTo(redeemCode);
		criteria.andPlanStateEqualTo(1);//上线中
		criteria.andPlanTypeEqualTo(0);//兑换方案
		List<RedeemPlan> redeemPlans = redeemPlanMapper.selectByExample(example);
		if(redeemPlans.size()>0){
			return redeemPlans.get(0);
		}
		return null;
	}
	
	@Override
	public List<RedeemPlan> findRegistPlan() throws Exception {
		RedeemPlanExample example = new RedeemPlanExample();
		RedeemPlanExample.Criteria criteria = example.createCriteria();
		criteria.andPlanStateEqualTo(1);//上线中
		criteria.andPlanTypeEqualTo(1);//注册方案
		List<RedeemPlan> redeemPlans = redeemPlanMapper.selectByExample(example);
		if(redeemPlans.size()>0){
			return redeemPlans;
		}
		return null;
	}

}
