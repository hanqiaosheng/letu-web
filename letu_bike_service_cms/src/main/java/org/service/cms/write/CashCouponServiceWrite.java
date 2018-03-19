package org.service.cms.write;


import org.entity.dto.CashCoupon;

public interface CashCouponServiceWrite {

	/**
	 * 添加代金券
	 * @param cashCoupon
	 * @throws Exception
	 */
	public void addCashCoupon(CashCoupon cashCoupon)throws Exception;

	/**
	 * 更新代金券
	 * @param cashCoupon
	 * @throws Exception
	 */
	public void updateCashCoupon(CashCoupon cashCoupon)throws Exception;

}
