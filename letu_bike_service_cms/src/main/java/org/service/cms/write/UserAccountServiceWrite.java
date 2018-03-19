package org.service.cms.write;

import org.entity.dto.Account;
import org.entity.dto.MoneyLog;

public interface UserAccountServiceWrite {
	/**
	 * 编辑用户账户（冻结，解冻）
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public void updateUserAccount(Account account) throws Exception;
	
	/**
	 * 软删除用户账户详情记录
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public void updateUserAccountMoneyLog(MoneyLog moneyLog) throws Exception;


}
