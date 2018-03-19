package org.service.weixin.read;

import java.util.List;

import org.entity.dto.RechargeRecord;

public interface RechargeWxServiceRead {
    /**
     * 查询押金缴纳记录
     * @param accountId
     * @return
     * @throws Exception
     */
	public List<RechargeRecord> findRecharge(Long accountId) throws Exception;
	
	/**
	 * 通过id查询充值记录
	 * @return
	 * @throws Exception
	 */
	public RechargeRecord findRechargeById(String rechargeOrderId) throws Exception;
	
	
	/**
	 * 根据accountId，rechargeType查询记录
	 * @param accountId
	 * @param rechargeType
	 * @return
	 * @throws Exception
	 */
	public List<RechargeRecord> findRechargeByAccountId(Long accountId,Integer rechargeType) throws Exception;
}
