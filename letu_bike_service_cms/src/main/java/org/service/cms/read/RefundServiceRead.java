package org.service.cms.read;

import java.util.Date;
import java.util.List;

import org.entity.dto.Refund;


public interface RefundServiceRead {
	
	/**
	 * 查询所有退款信息
	 * @param pageIndex
	 * @param userPhone
	 * @param refundStarttime
	 * @param refundEndtime
	 * @param operateStarttime
	 * @param operateEndtime
	 * @param refundState
	 * @param adminName
	 * @return
	 * @throws Exception
	 */
	public List<Refund> findAllRefundList(Integer pageIndex,String userPhone,
            Date refundStarttime,Date refundEndtime,Date operateStarttime,Date operateEndtime,
            Integer refundState,String adminName,String refundOrderId) throws Exception;
	
	/**
	 * 所有退款信息数量
	 * @param userPhone
	 * @param refundStarttime
	 * @param refundEndtime
	 * @param operateStarttime
	 * @param operateEndtime
	 * @param refundState
	 * @param adminName
	 * @return
	 * @throws Exception
	 */
	public Integer countAllRefundList(String userPhone,
            Date refundStarttime,Date refundEndtime,Date operateStarttime,Date operateEndtime,
            Integer refundState,String adminName,String refundOrderId) throws Exception;
	
	/**
	 * 根据id查询退款信息
	 * @param refundId
	 * @return
	 * @throws Exception
	 */
	public Refund findRefundById(Long refundId) throws Exception;

	/**
	 * 根据moneyLogOutTrade查询
	 * @param moneyLogOutTrade
	 * @return
	 * @throws Exception
	 */
	public Refund findRefundByOutTrade(String moneyLogOutTrade)throws Exception;
	
	/**
	 * 除退款成功的
	 * @return
	 * @throws Exception
	 */
	public Integer findUnsuccess()throws Exception;
	
}
