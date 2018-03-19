package org.service.weixin.impl.write;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.dao.AccountMapper;
import org.dao.MoneyLogMapper;
import org.dao.RechargeRecordMapper;
import org.entity.dto.Account;
import org.entity.dto.MoneyLog;
import org.entity.dto.RechargeRecord;
import org.service.weixin.write.AccountWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("accountWxServiceWrite")
public class AccountWxServiceWriteImpl implements AccountWxServiceWrite {

	@Resource
	AccountMapper accountMapper;
	
	@Resource
	RechargeRecordMapper rechargeRecordMapper;
	
	@Resource
	MoneyLogMapper moneyLogMapper;
	
	
	@Override
	public void updateAccount(Account account) throws Exception {
		// TODO Auto-generated method stub
		account.setAccountUpdatetime(new Date());
		accountMapper.updateByPrimaryKeySelective(account);
	}
	
	public void addAccountMoney(Double rechargeMoney,Account account,String transactionId,String outTradeNo,Integer rechargeType,Integer payType) throws Exception{
		BigDecimal totalmoney = account.getAccountTotalmoney().add( new BigDecimal(rechargeMoney));
		if(rechargeType==1){
			BigDecimal depositMoney = account.getAccountDeposit().add( new BigDecimal(rechargeMoney));
			account.setAccountDeposit(depositMoney);
			account.setAccountFinalRechargeTime(new Date());
			account.setAccountTotalmoney(totalmoney);
			accountMapper.updateByPrimaryKeySelective(account);
		}else if(rechargeType==2){
			BigDecimal balance = account.getAccountAvailableBalance().add( new BigDecimal(rechargeMoney));
			account.setAccountAvailableBalance(balance);
			account.setAccountFinalRechargeTime(new Date());
			account.setAccountTotalmoney(totalmoney);
			accountMapper.updateByPrimaryKeySelective(account);
		}
		
		RechargeRecord rechargeRecord = new RechargeRecord();
		rechargeRecord.setRechargeAccountId(account.getAccountId());
		rechargeRecord.setRechargeMoney(new BigDecimal(rechargeMoney));
		rechargeRecord.setRechargePayType(payType);
		rechargeRecord.setRechargeType(rechargeType);
		rechargeRecord.setRechargeState(1);
		rechargeRecord.setRechargeTime(new Date());
		rechargeRecord.setRechargeOutTradeNo(outTradeNo);
		rechargeRecord.setRechargeOrderId(transactionId);
		rechargeRecordMapper.insertSelective(rechargeRecord);
		
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(account.getAccountId());
		if(rechargeType==1){
			moneyLog.setMoneyLogStreamType(3);//预付款充值
			moneyLog.setMoneyLogIsvillager(0);//游客
		}
		if(rechargeType==2){
			moneyLog.setMoneyLogStreamType(4);//余额充值
			moneyLog.setMoneyLogIsvillager(1);//会员
		}
		moneyLog.setMoneyLogOpreateMoney(new BigDecimal(rechargeMoney));
		moneyLog.setMoneyLogDeposit(account.getAccountDeposit());
		moneyLog.setMoneyLogCreateTime(new Date());
		if(payType==1){//微信
			moneyLog.setMoneyLogOpreateId((long)1);
		}else if(payType==2){//支付宝
			moneyLog.setMoneyLogOpreateId((long)6);
		}else if(payType==3){//微信app
			moneyLog.setMoneyLogOpreateId((long)1);
		}
		
		moneyLog.setMoneyLogRemark("充值流水");
		moneyLog.setMoneyLogRefundState(0);
		moneyLog.setMoneyLogOrder(transactionId);
		moneyLog.setMoneyLogOutTrade(outTradeNo);
		//moneyLog.setMoneyLogIp("");
		moneyLogMapper.insertSelective(moneyLog);
	}
	
	
	public void addAccountMoney_2(Double rechargeMoney,Account account,String transactionId,String outTradeNo,Integer rechargeType,Integer payType,Long channelId) throws Exception{
		BigDecimal totalmoney = account.getAccountTotalmoney().add( new BigDecimal(rechargeMoney));
		if(rechargeType==1){
			BigDecimal depositMoney = account.getAccountDeposit().add( new BigDecimal(rechargeMoney));
			account.setAccountDeposit(depositMoney);
			account.setAccountFinalRechargeTime(new Date());
			account.setAccountTotalmoney(totalmoney);
			accountMapper.updateByPrimaryKeySelective(account);
		}else if(rechargeType==2){
			BigDecimal balance = account.getAccountAvailableBalance().add( new BigDecimal(rechargeMoney));
			account.setAccountAvailableBalance(balance);
			account.setAccountFinalRechargeTime(new Date());
			account.setAccountTotalmoney(totalmoney);
			accountMapper.updateByPrimaryKeySelective(account);
		}
		
		RechargeRecord rechargeRecord = new RechargeRecord();
		rechargeRecord.setRechargeAccountId(account.getAccountId());
		rechargeRecord.setRechargeMoney(new BigDecimal(rechargeMoney));
		rechargeRecord.setRechargePayType(payType);
		rechargeRecord.setRechargeType(rechargeType);
		rechargeRecord.setRechargeState(1);
		rechargeRecord.setRechargeTime(new Date());
		rechargeRecord.setRechargeOutTradeNo(outTradeNo);
		rechargeRecord.setRechargeOrderId(transactionId);
		if(null!=channelId&&channelId!=0){
			rechargeRecord.setRechargeChannelId(channelId);
		}
		
		rechargeRecordMapper.insertSelective(rechargeRecord);
		
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(account.getAccountId());
		if(rechargeType==1){
			moneyLog.setMoneyLogStreamType(3);//预付款充值
			moneyLog.setMoneyLogIsvillager(0);//游客
		}
		if(rechargeType==2){
			moneyLog.setMoneyLogStreamType(4);//余额充值
			moneyLog.setMoneyLogIsvillager(1);//会员
		}
		moneyLog.setMoneyLogOpreateMoney(new BigDecimal(rechargeMoney));
		moneyLog.setMoneyLogDeposit(account.getAccountDeposit());
		moneyLog.setMoneyLogCreateTime(new Date());
		if(payType==1){//微信
			moneyLog.setMoneyLogOpreateId((long)1);
		}else if(payType==2){//支付宝
			moneyLog.setMoneyLogOpreateId((long)6);
		}else if(payType==3){//微信app
			moneyLog.setMoneyLogOpreateId((long)1);
		}
		
		moneyLog.setMoneyLogRemark("充值流水");
		moneyLog.setMoneyLogRefundState(0);
		moneyLog.setMoneyLogOrder(transactionId);
		moneyLog.setMoneyLogOutTrade(outTradeNo);
		if(null!=channelId&&channelId!=0){
			moneyLog.setMoneyLogChannelId(channelId);
		}
		
		//moneyLog.setMoneyLogIp("");
		moneyLogMapper.insertSelective(moneyLog);
	}

	@Override
	public Long addNewAccount(Account acount) throws Exception {
		// TODO Auto-generated method stub
		accountMapper.insertSelective(acount);
		return acount.getAccountId();
	}

	@Override
	public void updateAccountAndLog(Account account, BigDecimal refundMoney, RechargeRecord rechargeRecord) {
		//BigDecimal freezemoney = account.getAccountFreezemoney().subtract(refundMoney);
		BigDecimal totalmoney = account.getAccountTotalmoney().subtract(rechargeRecord.getRechargeMoney());
		BigDecimal depositmoney = account.getAccountDeposit().subtract(rechargeRecord.getRechargeMoney());
		account.setAccountDeposit(depositmoney);
		account.setAccountTotalmoney(totalmoney);
		//account.setAccountFreezemoney(freezemoney);
		account.setAccountFinalRefundTime(new Date());
		accountMapper.updateByPrimaryKeySelective(account);
		rechargeRecord.setRechargeState(3);
		rechargeRecordMapper.updateByPrimaryKeySelective(rechargeRecord);
		MoneyLog moneyLog = new MoneyLog();
		moneyLog.setMoneyLogAccountId(account.getAccountId());
		moneyLog.setMoneyLogStreamType(2);
		moneyLog.setMoneyLogOpreateId((long)4);
		moneyLog.setMoneyLogOpreateMoney(refundMoney);
		moneyLog.setMoneyLogDeposit(account.getAccountDeposit());
		moneyLog.setMoneyLogCreateTime(new Date());
		moneyLog.setMoneyLogRemark("退款流水");
		moneyLog.setMoneyLogRefundState(1);
		if(null!=rechargeRecord.getRechargeChannelId()){
			moneyLog.setMoneyLogChannelId(rechargeRecord.getRechargeChannelId());
		}
		if(null!=rechargeRecord.getRechargeOrderId()&&!rechargeRecord.getRechargeOrderId().equals("")){
			moneyLog.setMoneyLogOrder(rechargeRecord.getRechargeOrderId());
		}
		
		moneyLog.setMoneyLogOutTrade(rechargeRecord.getRechargeOutTradeNo());
		//moneyLog.setMoneyLogIp("");
		moneyLogMapper.insertSelective(moneyLog);
		
	}


}
