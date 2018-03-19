package org.service.cms.impl.write;


import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.dao.AccountMapper;
import org.dao.MoneyLogMapper;
import org.entity.dto.MoneyLog;
import org.service.cms.write.MoneyLogServiceWrite;
import org.springframework.stereotype.Service;

@Service("moneyLogServiceWrite")
public class MoneyLogServiceWriteImpl implements MoneyLogServiceWrite {

	@Resource
	MoneyLogMapper moneyLogMapper;
	
	@Resource
	AccountMapper accountMapper;

	@Override
	public void addMoneyLog(Long accountId, int i, BigDecimal accountTotalmoney, BigDecimal accountBbin,
			BigDecimal accountAvailableBalance, BigDecimal accountDeposit, Date date, String ip, int j)
			throws Exception {
		// TODO Auto-generated method stub
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(accountId);
		moneyLog.setMoneyLogStreamType(i);
		moneyLog.setMoneyLogOpreateMoney(accountBbin);
		moneyLog.setMoneyLogDeposit(accountDeposit);
		moneyLog.setMoneyLogCreateTime(date);
		moneyLog.setMoneyLogIp(ip);
		moneyLogMapper.insertSelective(moneyLog);
	}

	
	
	@Override
	public int updateState(MoneyLog MoneyLog) throws Exception {
		// TODO Auto-generated method stub
		return moneyLogMapper.updateByPrimaryKeySelective(MoneyLog);
		
	}



	@Override
	public void add(MoneyLog moneyLog) throws Exception {
		// TODO Auto-generated method stub
		moneyLogMapper.insertSelective(moneyLog);
	}
	

}
