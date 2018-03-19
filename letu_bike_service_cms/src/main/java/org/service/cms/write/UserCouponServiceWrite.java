package org.service.cms.write;


import org.entity.dto.UserCoupon;

public interface UserCouponServiceWrite {
	

	/**
	 * 更新用户代金券
	 * @param couponPlan
	 * @throws Exception
	 */
	public void updateUserCoupon(UserCoupon userCoupon)throws Exception;

}
