package org.service.cms.read;


import java.util.List;

import org.entity.dto.CashCoupon;


public interface CashCouponServiceRead {

	/**
	 * 查询所有的代金券
	 * @param pageIndex
	 * @param page_size_web
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public List<CashCoupon> findAll(Integer pageIndex, Integer page_size_web, String title)throws Exception;

	/**
	 * 计算数量
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public Integer countAll(String title)throws Exception;

	/**
	 * 通过cashCouponId查询代金券
	 * @param cashCouponId
	 * @return
	 * @throws Exception
	 */
	public CashCoupon findById(Long cashCouponId)throws Exception;

	/**
	 * 查询所有代金券
	 * @return
	 * @throws Exception
	 */
	public List<CashCoupon> findAllCashCoupon()throws Exception;

}
