package org.service.cms.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.AccountMapper;
import org.entity.dto.Account;
import org.entity.dto.AccountExample;
import org.service.cms.read.AccountServiceRead;
import org.springframework.stereotype.Service;
@Service("accountServiceRead")
public class AccountServiceReadImpl implements AccountServiceRead {
	
	@Resource
	AccountMapper accountMapper;

	@Override
	public Account findPlanByaccountId(Long accountId) throws Exception {
		// TODO Auto-generated method stub
		Account account = accountMapper.selectByPrimaryKey(accountId);
		return account;
	}

	@Override
	public List<Account> findAllUserful() throws Exception {
		// TODO Auto-generated method stub
		AccountExample accountExample = new AccountExample();
		AccountExample.Criteria criteria = accountExample.createCriteria();
		criteria.andAccountIsFreezeEqualTo(1);
		return accountMapper.selectByExample(accountExample);
	}

	@Override
	public Account findByuserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		AccountExample accountExample = new AccountExample();
		AccountExample.Criteria criteria = accountExample.createCriteria();
		criteria.andAccountUserIdEqualTo(userId);
		return null;
	}

	
	

	
}
