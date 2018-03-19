package org.service.cms.write;


import org.entity.dto.CouponPlan;

public interface CouponPlanServiceWrite {
	
	/**
	 * 添加代金券方案
	 * @param couponPlan
	 * @throws Exception
	 */
	public void addCouponPlan(CouponPlan couponPlan)throws Exception;

	/**
	 * 更新代金券方案
	 * @param couponPlan
	 * @throws Exception
	 */
	public void updateCouponPlan(CouponPlan couponPlan)throws Exception;

}
