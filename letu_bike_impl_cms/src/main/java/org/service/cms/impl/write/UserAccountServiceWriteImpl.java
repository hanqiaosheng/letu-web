package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.AccountMapper;
import org.dao.MoneyLogMapper;
import org.entity.dto.Account;
import org.entity.dto.MoneyLog;
import org.service.cms.write.UserAccountServiceWrite;
import org.springframework.stereotype.Service;
@Service("userAccountServiceWrite")
public class UserAccountServiceWriteImpl implements UserAccountServiceWrite {
	@Resource
	AccountMapper accountMapper;
	
	@Resource
	MoneyLogMapper moneyLogMapper;

	@Override
	public void updateUserAccount(Account account) throws Exception {
		// TODO Auto-generated method stub
		accountMapper.updateByPrimaryKeySelective(account);
	}

	@Override
	public void updateUserAccountMoneyLog(MoneyLog moneyLog) throws Exception {
		// TODO Auto-generated method stub
		moneyLogMapper.updateByPrimaryKeySelective(moneyLog);
	}

}
