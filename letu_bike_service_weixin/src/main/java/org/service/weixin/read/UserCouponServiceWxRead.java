package org.service.weixin.read;

import java.util.List;

import org.entity.dto.CashCoupon;
import org.entity.dto.CouponPlan;
import org.entity.dto.UserCoupon;

public interface UserCouponServiceWxRead {
	/**
	 * 通过userId查询用户代金券信息
	 * @param pageIndex
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<UserCoupon> findCouponByUserId(Integer pageIndex, Long userId)throws Exception;

	/**
	 * 通过ucouponId查询代金券
	 * @param ucouponId
	 * @return
	 * @throws Exception
	 */
	public CashCoupon findCouponById(Long ucouponId)throws Exception;

	/**
	 * 通过代金券方案Id查询
	 * @param planCouponPlanId
	 * @return
	 * @throws Exception
	 */
	public CouponPlan findCouponPlanById(Long planCouponPlanId)throws Exception;

	/**
	 * 通过用户代金券id查询用户代金券
	 * @param userCouponId
	 * @return
	 * @throws Exception
	 */
	public UserCoupon findUserCouponId(Long userCouponId)throws Exception;

	public Integer countCouponByUserId(Long userId)throws Exception;


}
