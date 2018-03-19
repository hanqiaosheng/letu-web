package org.service.weixin.write;

import java.math.BigDecimal;
import java.util.Date;

import org.entity.dto.Account;

public interface MoneyLogWxServiceWrite {


	public void addMoneyLog(Long accountId, int i, BigDecimal add, BigDecimal accountDeposit, Date date, String ip,
			int j, String rentOrderCode);
	/**
	 * 一代接口
	 * 添加日志
	 * @throws Exception
	 */
	public void addMoneyLog(Account account,BigDecimal money,String outTradeNo,String transactionId) throws Exception;
	/**
	 * 二代接口
	 * 添加日志
	 * @param account
	 * @param money
	 * @param outTradeNo
	 * @param transactionId
	 * @param channelId
	 * @throws Exception
	 */
	public void addMoneyLog_2(Account account,BigDecimal money,String outTradeNo,String transactionId,Long channelId) throws Exception;
	
	/**
	 * 添加日志
	 * @param payMoneys
	 */
	public void addNewMoneyLog(Double money,Long accountId,Long channelId)throws Exception;
	
	/**
	 * 会员消费日志
	 * @param payMoney
	 * @param account
	 * @throws Exception
	 */
	public void addVipMoneyLog(BigDecimal payMoney, Account account)throws Exception;
	
	
	/**
	 * 添加邮费日志
	 * @param money
	 * @param outTradeNo
	 * @param transactionId
	 * @throws Exception
	 */
	public void addPosMoneyLog(Account account,Double money, String outTradeNo, String transactionId)throws Exception;

}
