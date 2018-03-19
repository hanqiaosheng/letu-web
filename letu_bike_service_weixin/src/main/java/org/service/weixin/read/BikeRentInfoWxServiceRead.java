package org.service.weixin.read;

import java.util.List;

import org.entity.dto.BikeRentInfo;

public interface BikeRentInfoWxServiceRead {
	
	/**
	 * 根据用户id查行程
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findByUserId(Long userId,Integer pageIndex,Integer page_size_weixin) throws Exception;
	
	/**
	 * 根据用户id查行程条数
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer findCountByUserId(Long userId) throws Exception;
	
	/**
	 * 根据账单号查账单信息
	 * @param bikeRentId
	 * @return
	 * @throws Exception
	 */
	public BikeRentInfo findByBikeRentId(Long bikeRentId) throws Exception;
	
	/**
	 * 超找用户 未完成的订单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public BikeRentInfo findNotFinishByUserId(Long userId) throws Exception;
	
	/**
	 * 查找用户未支付的订单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public BikeRentInfo findNotPayByUserId(Long userId) throws Exception;
	/**
	 * 查出个人未开发票的行程
	 * @param userId
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findByUserIdAndNoInvoiceId(Long userId, Integer pageIndex, Integer page_size_weixin)throws Exception;
	/**
	 * 计算个人未开发票的行程数量
	 * @param userId
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public Integer findCountByUserIdAndNoInvoiceId(Long userId)throws Exception;

	/**
	 * 通过发票id查询行程
	 * @param invoiceId
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findByInvoiceId(Long invoiceId)throws Exception;

	/**
	 * 通过车辆查找最新租赁订单
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public BikeRentInfo findRecentRentInfo(Long bikeId)throws Exception;

	/**
	 * 通过用户id查询完成的租赁订单
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findFinishByUserId(Long userId)throws Exception;

	
}
