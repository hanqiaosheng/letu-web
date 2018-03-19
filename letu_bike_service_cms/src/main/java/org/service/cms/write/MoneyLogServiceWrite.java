package org.service.cms.write;

import java.math.BigDecimal;
import java.util.Date;

import org.entity.dto.MoneyLog;


public interface MoneyLogServiceWrite {
	
	/**
	 * 新增资金日志
	 * @param accountId
	 * @param i
	 * @param accountTotalmoney
	 * @param accountBbin
	 * @param accountAvailableBalance
	 * @param accountDeposit
	 * @param date
	 * @param ip
	 * @param j
	 * @throws Exception
	 */
	void addMoneyLog(Long accountId, int i, BigDecimal accountTotalmoney, BigDecimal accountBbin,
			BigDecimal accountAvailableBalance, BigDecimal accountDeposit, Date date, String ip, int j) throws Exception;

	public int updateState(MoneyLog MoneyLog)throws Exception;
	
	public void add(MoneyLog moneyLog) throws Exception;
}
