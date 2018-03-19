package org.service.cms.read;


import org.entity.dto.RechargeRecord;

public interface RechargeRecordServiceRead {

	/**
	 * 根据流水号查询充值记录
	 * @param rechargeOrderId
	 * @return
	 * @throws Exception
	 */
	public RechargeRecord findRechargeRecordById(String rechargeOrderId) throws Exception;
	
	/**
	 * 根据用户编号查询充值记录
	 * @param rechargeAccountId
	 * @return
	 * @throws Exception
	 */
	public RechargeRecord findRechargeRecordByAccountId(Long rechargeAccountId) throws Exception;

	
	/**
	 * 
	 * @param refundCode
	 * @return
	 */
	public RechargeRecord findRechargeRecordByTradeNo(String refundCode)throws Exception;
}
