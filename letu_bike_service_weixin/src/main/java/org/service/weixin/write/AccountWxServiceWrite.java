package org.service.weixin.write;


import java.math.BigDecimal;

import org.entity.dto.Account;
import org.entity.dto.RechargeRecord;

public interface AccountWxServiceWrite {
	
	/**
	 * 更新账户
	 * @param account
	 * @throws Exception
	 */
	public void updateAccount(Account account) throws Exception;
	
	/**
	 * 一代接口
	 * 充值账户
	 * @param rechargeMoney
	 * @param account
	 * @param transactionId
	 * @throws Exception
	 */
	public void addAccountMoney(Double rechargeMoney,Account account ,String transactionId,String outTradeNo,Integer rechargeType, Integer payType) throws Exception;
	/**
	 * 二代接口
	 * 充值账户
	 * @param rechargeMoney
	 * @param account
	 * @param transactionId
	 * @throws Exception
	 */
	public void addAccountMoney_2(Double rechargeMoney,Account account ,String transactionId,String outTradeNo,Integer rechargeType, Integer payType,Long channelId) throws Exception;

	public Long addNewAccount(Account acount) throws Exception;

	/**
	 * 退款日志
	 * @param account
	 * @param rechargeMoney
	 * @param rechargeRecord
	 */
	public void updateAccountAndLog(Account account, BigDecimal refundMoney, RechargeRecord rechargeRecord);
	

}
