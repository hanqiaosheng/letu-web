package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.AccountMapper;
import org.entity.dto.Account;
import org.entity.dto.AccountExample;
import org.service.weixin.read.AccountWxServiceRead;
import org.springframework.stereotype.Service;

@Service("accountWxServiceRead")
public class AccountWxServiceReadImpl implements AccountWxServiceRead {

	@Resource
	AccountMapper accountMapper;

	@Override
	public Account findByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		AccountExample accountExample = new AccountExample();
		AccountExample.Criteria criteria = accountExample.createCriteria();
		criteria.andAccountUserIdEqualTo(userId);
		List<Account> accounts = accountMapper.selectByExample(accountExample);
		if(accounts.size()>0){
			return accounts.get(0);
		}
		
		return null;
	}

	@Override
	public Account findByAccountId(Long userAccountId) throws Exception {
		// TODO Auto-generated method stub
		return accountMapper.selectByPrimaryKey(userAccountId);
	}
	
	
}
