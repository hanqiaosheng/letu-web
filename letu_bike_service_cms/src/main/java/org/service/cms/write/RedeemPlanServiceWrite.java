package org.service.cms.write;


import org.entity.dto.RedeemPlan;

public interface RedeemPlanServiceWrite {

	/**
	 * 添加兑换方案
	 * @param redeemPlan
	 * @throws Exception
	 */
	public void addRedeemPlan(RedeemPlan redeemPlan)throws Exception;

	/**
	 * 更新兑换方案
	 * @param redeemPlan
	 * @throws Exception
	 */
	public void updateRedeemPlan(RedeemPlan redeemPlan)throws Exception;

}
