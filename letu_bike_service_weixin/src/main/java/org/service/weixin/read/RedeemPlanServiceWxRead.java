package org.service.weixin.read;

import java.util.List;

import org.entity.dto.RedeemPlan;

public interface RedeemPlanServiceWxRead {
	
	public RedeemPlan findRedeemPlanByCode(String redeemCode)throws Exception;
	
	public List<RedeemPlan> findRegistPlan()throws Exception;

}
