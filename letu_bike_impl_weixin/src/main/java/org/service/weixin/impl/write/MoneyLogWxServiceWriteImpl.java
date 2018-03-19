package org.service.weixin.impl.write;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.Date;

import javax.annotation.Resource;

import org.dao.AccountMapper;
import org.dao.MoneyLogMapper;
import org.entity.dto.Account;
import org.entity.dto.MoneyLog;
import org.service.weixin.write.MoneyLogWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("moneyLogWxServiceWrite")
public class MoneyLogWxServiceWriteImpl implements MoneyLogWxServiceWrite {

	@Resource
	MoneyLogMapper moneyLogMapper;
	@Resource
	AccountMapper accountMapper;

	@Override
	public void addMoneyLog(Long accountId, int i, BigDecimal add, BigDecimal accountDeposit, Date date, String ip,
			int j, String rentOrderCode) {
		// TODO Auto-generated method stub
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(accountId);
		moneyLog.setMoneyLogStreamType(i);
		moneyLog.setMoneyLogOpreateMoney(add);
		moneyLog.setMoneyLogDeposit(accountDeposit);
		moneyLog.setMoneyLogCreateTime(date);
		moneyLog.setMoneyLogIp(ip);
		moneyLog.setMoneyLogOpreateId((long) j);
		moneyLog.setMoneyLogOrder(rentOrderCode);
		moneyLogMapper.insertSelective(moneyLog);
		
		
	}

	@Override
	public void addMoneyLog(Account account, BigDecimal money, String outTradeNo, String transactionId) throws Exception {
		// TODO Auto-generated method stub
		account.setAccountFinalConsumeTime(new Date());
		accountMapper.updateByPrimaryKeySelective(account);
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(account.getAccountId());
		moneyLog.setMoneyLogStreamType(1);
		moneyLog.setMoneyLogOpreateMoney(money);
		moneyLog.setMoneyLogDeposit(money);
		moneyLog.setMoneyLogCreateTime(new Date());
		moneyLog.setMoneyLogRemark("骑行支付");
		moneyLog.setMoneyLogOpreateId((long)2);
		moneyLog.setMoneyLogOrder(transactionId);
		moneyLog.setMoneyLogOutTrade(outTradeNo);
		moneyLog.setMoneyLogRefundState(7);//消费
		moneyLog.setMoneyLogIsvillager(0);//游客
		moneyLogMapper.insertSelective(moneyLog);
	}
	
	@Override
	public void addMoneyLog_2(Account account, BigDecimal money, String outTradeNo, String transactionId,Long channelId) throws Exception {
		// TODO Auto-generated method stub
		account.setAccountFinalConsumeTime(new Date());
		accountMapper.updateByPrimaryKeySelective(account);
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(account.getAccountId());
		moneyLog.setMoneyLogStreamType(1);
		moneyLog.setMoneyLogOpreateMoney(money);
		moneyLog.setMoneyLogDeposit(money);
		moneyLog.setMoneyLogCreateTime(new Date());
		moneyLog.setMoneyLogRemark("骑行支付");
		moneyLog.setMoneyLogOpreateId((long)2);
		moneyLog.setMoneyLogOrder(transactionId);
		moneyLog.setMoneyLogOutTrade(outTradeNo);
		moneyLog.setMoneyLogRefundState(7);//消费
		moneyLog.setMoneyLogIsvillager(0);//游客
		moneyLog.setMoneyLogChannelId(channelId);
		moneyLogMapper.insertSelective(moneyLog);
	}

	@Override
	public void addNewMoneyLog(Double money,Long accountId,Long channelId) throws Exception {
		// TODO Auto-generated method stub
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(accountId);
		moneyLog.setMoneyLogStreamType(1);
		moneyLog.setMoneyLogOpreateMoney(new BigDecimal(money));
		moneyLog.setMoneyLogDeposit(new BigDecimal(money));
		moneyLog.setMoneyLogCreateTime(new Date());
		moneyLog.setMoneyLogRemark("骑行支付");
		moneyLog.setMoneyLogIp(InetAddress.getLocalHost().getHostAddress());
		moneyLog.setMoneyLogOpreateId((long)2);
		if(null!=channelId){
			moneyLog.setMoneyLogChannelId(channelId);
		}
		moneyLog.setMoneyLogRefundState(7);//消费
		moneyLog.setMoneyLogIsvillager(0);//游客
		moneyLogMapper.insertSelective(moneyLog);
	}

	@Override
	public void addVipMoneyLog(BigDecimal payMoney, Account account) throws Exception {
		// TODO Auto-generated method stub
		BigDecimal balance = account.getAccountAvailableBalance().subtract(payMoney);
		BigDecimal totalmoney = account.getAccountTotalmoney().subtract(payMoney);
		account.setAccountAvailableBalance(balance);
		account.setAccountTotalmoney(totalmoney);
		account.setAccountFinalConsumeTime(new Date());
		accountMapper.updateByPrimaryKeySelective(account);
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(account.getAccountId());
		moneyLog.setMoneyLogStreamType(1);
		moneyLog.setMoneyLogOpreateMoney(payMoney);
		//moneyLog.setMoneyLogDeposit(payMoney);
		moneyLog.setMoneyLogCreateTime(new Date());
		moneyLog.setMoneyLogRemark("骑行支付");
		moneyLog.setMoneyLogRefundState(7);//消费
		moneyLog.setMoneyLogOpreateId((long)3);
		moneyLog.setMoneyLogIsvillager(1);//是会员
		moneyLogMapper.insertSelective(moneyLog);
	}

	@Override
	public void addPosMoneyLog(Account account, Double money, String outTradeNo, String transactionId) throws Exception {
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(account.getAccountId());
		moneyLog.setMoneyLogStreamType(5);//邮费
		moneyLog.setMoneyLogOpreateMoney(new BigDecimal(money));
		moneyLog.setMoneyLogDeposit(new BigDecimal(money));
		moneyLog.setMoneyLogCreateTime(new Date());
		moneyLog.setMoneyLogRemark("邮费支付");
		moneyLog.setMoneyLogOpreateId((long)1);
		moneyLog.setMoneyLogOrder(transactionId);
		moneyLog.setMoneyLogOutTrade(outTradeNo);
		moneyLog.setMoneyLogRefundState(8);//邮费消费
		moneyLog.setMoneyLogIsvillager(0);//游客
		moneyLogMapper.insertSelective(moneyLog);
	}
	
}
