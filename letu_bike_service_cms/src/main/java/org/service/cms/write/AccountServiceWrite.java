package org.service.cms.write;

import java.math.BigDecimal;

import org.entity.dto.Account;
import org.entity.dto.RechargeRecord;

public interface AccountServiceWrite {
	/**
	 * 更新账户
	 * @param account
	 * @throws Exception
	 */
	public void updateAccount(Account account) throws Exception;
	
	
	/**
	 * 更新账户及日志
	 * @param account
	 * @throws Exception
	 */
	public void updateAccountAndLog(Account account,BigDecimal refundMoney,RechargeRecord rechargeRecord,Long adminId) throws Exception;

}
