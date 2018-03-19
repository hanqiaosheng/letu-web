package org.service.cms.read;


import java.util.List;

import org.entity.dto.CouponPlan;


public interface CouponPlanServiceRead {

	/**
	 * 查询能使用的代金券方案
	 * @return
	 * @throws Exception
	 */
	public List<CouponPlan> findCanUsePlan()throws Exception;

	/**
	 * 查询所有的方案
	 * @param pageIndex
	 * @param page_size_web
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public List<CouponPlan> findAllCouponPlan(Integer pageIndex, Integer page_size_web, String title)throws Exception;

	/**
	 * 计算数量
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public Integer findAllCouponPlanCount(String title)throws Exception;

	/**
	 * 通过代金券方案id查询信息
	 * @param couponPlanId
	 * @return
	 * @throws Exception
	 */
	public CouponPlan findByCouponPlanId(Long couponPlanId)throws Exception;

}
