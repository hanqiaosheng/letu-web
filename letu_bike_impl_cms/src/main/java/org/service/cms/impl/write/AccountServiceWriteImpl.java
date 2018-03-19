package org.service.cms.impl.write;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.dao.AccountMapper;
import org.dao.MessageMapper;
import org.dao.MoneyLogMapper;
import org.dao.RechargeRecordMapper;
import org.entity.dto.Account;
import org.entity.dto.MoneyLog;
import org.entity.dto.RechargeRecord;
import org.service.cms.write.AccountServiceWrite;
import org.springframework.stereotype.Service;


@Service("accountServiceWrite")
public class AccountServiceWriteImpl implements AccountServiceWrite {

	
	@Resource
	AccountMapper accountMapper;
	
	@Resource
	RechargeRecordMapper rechargeRecordMapper;
	
	@Resource
	MoneyLogMapper moneyLogMapper;
	
	@Resource
	MessageMapper messageMapper;

	@Override
	public void updateAccount(Account account) throws Exception {
		// TODO Auto-generated method stub
		accountMapper.updateByPrimaryKeySelective(account);
	}

	@Override
	public void updateAccountAndLog(Account account, BigDecimal refundMoney, RechargeRecord rechargeRecord,Long adminId)
			throws Exception {
		// TODO Auto-generated method stub
		//BigDecimal totalmoney = account.getAccountTotalmoney().subtract(refundMoney);
		//BigDecimal depositmoney = account.getAccountDeposit().subtract(refundMoney);
		BigDecimal freezemoney = account.getAccountFreezemoney().subtract(refundMoney);
		//account.setAccountDeposit(depositmoney);
		//account.setAccountTotalmoney(totalmoney);
		account.setAccountFreezemoney(freezemoney);
		account.setAccountFinalRefundTime(new Date());
		accountMapper.updateByPrimaryKeySelective(account);
		rechargeRecord.setRechargeState(3);
		rechargeRecordMapper.updateByPrimaryKeySelective(rechargeRecord);
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(account.getAccountId());
		moneyLog.setMoneyLogStreamType(2);
		moneyLog.setMoneyLogOpreateId((long)4);
		moneyLog.setMoneyLogOpreateMoney(refundMoney);
		moneyLog.setMoneyLogRefundOpreate(adminId);
		moneyLog.setMoneyLogDeposit(account.getAccountDeposit());
		moneyLog.setMoneyLogCreateTime(new Date());
		moneyLog.setMoneyLogRemark("退款流水");
		//moneyLog.setMoneyLogIp("");
		moneyLogMapper.insertSelective(moneyLog);
	}
	
	
}
