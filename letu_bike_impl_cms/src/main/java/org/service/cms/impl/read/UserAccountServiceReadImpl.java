package org.service.cms.impl.read;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.AccountMapper;
import org.dao.MoneyLogMapper;
import org.entity.dto.Account;
import org.entity.dto.AccountExample;
import org.entity.dto.MoneyLog;
import org.entity.dto.MoneyLogExample;
import org.service.cms.read.UserAccountServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;
@Service("userAccountServiceRead")
public class UserAccountServiceReadImpl implements UserAccountServiceRead {
	
	@Resource
	AccountMapper accountMapper;
	@Resource
	MoneyLogMapper moneyLogMapper;

	@Override
	public List<Account> findAccountByCondition(Account account, Integer pageIndex, Integer pageSize,String aUserName) throws Exception {
		// TODO Auto-generated method stub
		AccountExample example=new AccountExample();
		AccountExample.Criteria criteria=example.createCriteria();
		if (null != account.getaUserTel() && !"".equals(account.getaUserTel())) {
			criteria.andUserTelLike("%"+account.getaUserTel()+"%");
		}
		if(null != aUserName && !"".equals(aUserName)){
			criteria.andUserNameLike("%"+aUserName+"%");
		}
		if(null!=account.getAccountAvailableBalance())//查询的可用金额
			criteria.andAccountAvailableBalanceEqualTo(account.getAccountAvailableBalance());
		if(null!=account.getAccountDeposit())//查询的押金金额
			criteria.andAccountDepositEqualTo(account.getAccountDeposit());
		if (null != account.getFromTime() && !"".equals(account.getFromTime())) {//查询的充值时间
			if (null != account.getToTime() && !"".equals(account.getToTime()))
				criteria.andAccountFinalRechargeTimeBetween(DateUtil.changStringDate03(account.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(account.getToTime()),1));
			else {
				criteria.andAccountFinalRechargeTimeBetween(DateUtil.changStringDate03(account.getFromTime()), new Date());
			}
		} else if (null != account.getToTime() && !"".equals(account.getToTime()))
			criteria.andAccountFinalRechargeTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(account.getToTime()),1));
		if (null != account.getFromTimeB() && !"".equals(account.getFromTimeB())) {//查询的消费时间
			if (null != account.getToTimeB() && !"".equals(account.getToTimeB()))
				criteria.andAccountFinalConsumeTimeBetween(DateUtil.changStringDate03(account.getFromTimeB()),
						DateUtil.plusDate(DateUtil.changStringDate03(account.getToTimeB()),1));
			else {
				criteria.andAccountFinalConsumeTimeBetween(DateUtil.changStringDate03(account.getFromTimeB()), new Date());
			}
		} else if (null != account.getToTimeB() && !"".equals(account.getToTimeB()))
			criteria.andAccountFinalConsumeTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(account.getToTimeB()),1));
		example.setOrderByClause("FIELD(account_final_consume_time,'') asc,account_final_consume_time DESC,account_id asc");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return accountMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countAllAccount(Account account,String aUserName) throws Exception {
		// TODO Auto-generated method stub
		AccountExample example=new AccountExample();
		AccountExample.Criteria criteria=example.createCriteria();
		if (null != account.getaUserTel() && !"".equals(account.getaUserTel())) {
			criteria.andUserTelLike("%"+account.getaUserTel()+"%");
		}
		if(null != aUserName && !"".equals(aUserName)){
			criteria.andUserNameLike("%"+aUserName+"%");
		}
		if(null!=account.getAccountAvailableBalance())
			criteria.andAccountAvailableBalanceEqualTo(account.getAccountAvailableBalance());
		if(null!=account.getAccountDeposit())//查询的押金金额
			criteria.andAccountDepositEqualTo(account.getAccountDeposit());
		if (null != account.getFromTime() && !"".equals(account.getFromTime())) {//查询的充值时间
			if (null != account.getToTime() && !"".equals(account.getToTime()))
				criteria.andAccountFinalRechargeTimeBetween(DateUtil.changStringDate03(account.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(account.getToTime()),1));
			else {
				criteria.andAccountFinalRechargeTimeBetween(DateUtil.changStringDate03(account.getFromTime()), new Date());
			}
		} else if (null != account.getToTime() && !"".equals(account.getToTime()))
			criteria.andAccountFinalRechargeTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(account.getToTime()),1));
		if (null != account.getFromTimeB() && !"".equals(account.getFromTimeB())) {//查询的消费时间
			if (null != account.getToTimeB() && !"".equals(account.getToTimeB()))
				criteria.andAccountFinalRechargeTimeBetween(DateUtil.changStringDate03(account.getFromTimeB()),
						DateUtil.plusDate(DateUtil.changStringDate03(account.getToTimeB()),1));
			else {
				criteria.andAccountFinalRechargeTimeBetween(DateUtil.changStringDate03(account.getFromTimeB()), new Date());
			}
		} else if (null != account.getToTimeB() && !"".equals(account.getToTimeB()))
			criteria.andAccountFinalRechargeTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(account.getToTimeB()),1));
		return accountMapper.countUnionByExample(example);
	}
	
	
	@Override
	public Account findAccountById(Long accountId) throws Exception {
		// TODO Auto-generated method stub
		return accountMapper.selectByPrimaryKey(accountId);
	}

	@Override
	public List<MoneyLog> findAccountDetailByCondition(MoneyLog moneyLog, Integer pageIndex, Integer pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		MoneyLogExample example=new MoneyLogExample();
		MoneyLogExample.Criteria criteria=example.createCriteria();
		if(null!=moneyLog.getMoneyLogAccountId()&&0!=moneyLog.getMoneyLogAccountId())
			criteria.andMoneyLogAccountIdEqualTo(moneyLog.getMoneyLogAccountId());
		if(null!=moneyLog.getMoneyLogStreamType()&&0!=moneyLog.getMoneyLogStreamType())
			criteria.andMoneyLogStreamTypeEqualTo(moneyLog.getMoneyLogStreamType());
		if (null != moneyLog.getFromTime() && !"".equals(moneyLog.getFromTime())) {//查询的充值时间
			if (null != moneyLog.getToTime() && !"".equals(moneyLog.getToTime()))
				criteria.andMoneyLogCreateTimeBetween(DateUtil.changStringDate03(moneyLog.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(moneyLog.getToTime()),1));
			else {
				criteria.andMoneyLogCreateTimeBetween(DateUtil.changStringDate03(moneyLog.getFromTime()), new Date());
			}
		} else if (null != moneyLog.getToTime() && !"".equals(moneyLog.getToTime()))
			criteria.andMoneyLogCreateTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(moneyLog.getToTime()),1));
		criteria.andMoneyLogStateEqualTo(1);
		example.setOrderByClause("money_log_create_time DESC,money_log_id DESC");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return moneyLogMapper.selectByExample(example);
	}

	@Override
	public Integer countAllAccountDetail(MoneyLog moneyLog) throws Exception {
		// TODO Auto-generated method stub
		MoneyLogExample example=new MoneyLogExample();
		MoneyLogExample.Criteria criteria=example.createCriteria();
		if(null!=moneyLog.getMoneyLogAccountId()&&0!=moneyLog.getMoneyLogAccountId())
			criteria.andMoneyLogAccountIdEqualTo(moneyLog.getMoneyLogAccountId());
		if(null!=moneyLog.getMoneyLogStreamType()&&0!=moneyLog.getMoneyLogStreamType())
			criteria.andMoneyLogStreamTypeEqualTo(moneyLog.getMoneyLogStreamType());
		if (null != moneyLog.getFromTime() && !"".equals(moneyLog.getFromTime())) {//查询的充值时间
			if (null != moneyLog.getToTime() && !"".equals(moneyLog.getToTime()))
				criteria.andMoneyLogCreateTimeBetween(DateUtil.changStringDate03(moneyLog.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(moneyLog.getToTime()),1));
			else {
				criteria.andMoneyLogCreateTimeBetween(DateUtil.changStringDate03(moneyLog.getFromTime()), new Date());
			}
		} else if (null != moneyLog.getToTime() && !"".equals(moneyLog.getToTime()))
			criteria.andMoneyLogCreateTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(moneyLog.getToTime()),1));
		criteria.andMoneyLogStateEqualTo(1);
		return moneyLogMapper.countByExample(example);
	}

	@Override
	public List<MoneyLog> findVillagerAccountDetailByCondition(MoneyLog moneyLog, Integer pageIndex, Integer pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		MoneyLogExample example=new MoneyLogExample();
		MoneyLogExample.Criteria criteria=example.createCriteria();
		if(null!=moneyLog.getMoneyLogAccountId()&&0!=moneyLog.getMoneyLogAccountId())
			criteria.andMoneyLogAccountIdEqualTo(moneyLog.getMoneyLogAccountId());
		if(null!=moneyLog.getMoneyLogStreamType()&&0!=moneyLog.getMoneyLogStreamType())
			criteria.andMoneyLogStreamTypeEqualTo(moneyLog.getMoneyLogStreamType());
		if (null != moneyLog.getFromTime() && !"".equals(moneyLog.getFromTime())) {//查询的充值时间
			if (null != moneyLog.getToTime() && !"".equals(moneyLog.getToTime()))
				criteria.andMoneyLogCreateTimeBetween(DateUtil.changStringDate03(moneyLog.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(moneyLog.getToTime()),1));
			else {
				criteria.andMoneyLogCreateTimeBetween(DateUtil.changStringDate03(moneyLog.getFromTime()), new Date());
			}
		} else if (null != moneyLog.getToTime() && !"".equals(moneyLog.getToTime()))
			criteria.andMoneyLogCreateTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(moneyLog.getToTime()),1));
		criteria.andMoneyLogStateEqualTo(1);
		criteria.andMoneyLogIsvillagerEqualTo(1);
		example.setOrderByClause("money_log_create_time DESC");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return moneyLogMapper.selectByExample(example);
		
	}

	@Override
	public Integer countAllVillagerAccountDetail(MoneyLog moneyLog) throws Exception {
		// TODO Auto-generated method stub
		MoneyLogExample example=new MoneyLogExample();
		MoneyLogExample.Criteria criteria=example.createCriteria();
		if(null!=moneyLog.getMoneyLogAccountId()&&0!=moneyLog.getMoneyLogAccountId())
			criteria.andMoneyLogAccountIdEqualTo(moneyLog.getMoneyLogAccountId());
		if(null!=moneyLog.getMoneyLogStreamType()&&0!=moneyLog.getMoneyLogStreamType())
			criteria.andMoneyLogStreamTypeEqualTo(moneyLog.getMoneyLogStreamType());
		if (null != moneyLog.getFromTime() && !"".equals(moneyLog.getFromTime())) {//查询的充值时间
			if (null != moneyLog.getToTime() && !"".equals(moneyLog.getToTime()))
				criteria.andMoneyLogCreateTimeBetween(DateUtil.changStringDate03(moneyLog.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(moneyLog.getToTime()),1));
			else {
				criteria.andMoneyLogCreateTimeBetween(DateUtil.changStringDate03(moneyLog.getFromTime()), new Date());
			}
		} else if (null != moneyLog.getToTime() && !"".equals(moneyLog.getToTime()))
			criteria.andMoneyLogCreateTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(moneyLog.getToTime()),1));
		criteria.andMoneyLogStateEqualTo(1);
		criteria.andMoneyLogIsvillagerEqualTo(1);
		return moneyLogMapper.countByExample(example);
		
	}

	

}
