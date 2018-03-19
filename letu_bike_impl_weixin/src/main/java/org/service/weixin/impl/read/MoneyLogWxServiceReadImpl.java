package org.service.weixin.impl.read;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dao.MoneyLogMapper;
import org.entity.dto.MoneyLog;
import org.entity.dto.MoneyLogExample;
import org.service.weixin.read.MoneyLogWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("moneyLogWxServiceRead")
public class MoneyLogWxServiceReadImpl implements MoneyLogWxServiceRead {

	@Resource
	MoneyLogMapper moneyLogMapper;

	@Override
	public List<MoneyLog> findByAccountId(Long accountId,Integer pageIndex, Integer page_size_weixin) throws Exception {
		// TODO Auto-generated method stub
		MoneyLogExample logExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = logExample.createCriteria();
		criteria.andMoneyLogAccountIdEqualTo(accountId);
		//正常
		criteria.andMoneyLogStateEqualTo(1);
		logExample.setLimitStart(PageUtil.getWeixinStart(pageIndex));
		logExample.setLimitEnd(page_size_weixin);
		logExample.setOrderByClause("money_log_create_time DESC,money_log_id DESC");
		return moneyLogMapper.selectByExample(logExample);
	}

	@Override
	public Integer findCountByAccountId(Long accountId) throws Exception {
		// TODO Auto-generated method stub
		MoneyLogExample logExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = logExample.createCriteria();
		criteria.andMoneyLogAccountIdEqualTo(accountId);
		//正常
		criteria.andMoneyLogStateEqualTo(1);
		return moneyLogMapper.countByExample(logExample);
	}

	@Override
	public MoneyLog findMoneyLogByOrder(String order) throws Exception {
		MoneyLogExample logExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = logExample.createCriteria();
		criteria.andMoneyLogOrderEqualTo(order);
		List<MoneyLog> moneyLogs = moneyLogMapper.selectByExample(logExample);
		if(moneyLogs.size()>0){
			return moneyLogs.get(0);
		}
		return null;
	}

	@Override
	public List<MoneyLog> findDetailByAccountId(Long accountId, Integer pageIndex, Integer page_size_weixin)
			throws Exception {
		// TODO Auto-generated method stub
		MoneyLogExample logExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = logExample.createCriteria();
		criteria.andMoneyLogAccountIdEqualTo(accountId);
		criteria.andMoneyLogStateEqualTo(1);
		//criteria.andMoneyLogStreamTypeNotEqualTo(1);
		logExample.setLimitStart(PageUtil.getWeixinStart(pageIndex));
		logExample.setLimitEnd(page_size_weixin);
		logExample.setOrderByClause("money_log_create_time DESC,money_log_id DESC");
		return moneyLogMapper.selectByExample(logExample);
		
	}

	@Override
	public Integer findDetailCountByAccountId(Long accountId) throws Exception {
		// TODO Auto-generated method stub
		MoneyLogExample logExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = logExample.createCriteria();
		criteria.andMoneyLogAccountIdEqualTo(accountId);
		//正常
		criteria.andMoneyLogStateEqualTo(1);
		//criteria.andMoneyLogStreamTypeNotEqualTo(3);
		return moneyLogMapper.countByExample(logExample);
	}

	@Override
	public List<MoneyLog> findConsumeByAccountId(Long accountId, Integer pageIndex, Integer page_size_weixin)
			throws Exception {
		MoneyLogExample logExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = logExample.createCriteria();
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		criteria.andMoneyLogAccountIdEqualTo(accountId);
		criteria.andMoneyLogStateEqualTo(1);
		criteria.andMoneyLogStreamTypeIn(arr);
		logExample.setLimitStart(PageUtil.getWeixinStart(pageIndex));
		logExample.setLimitEnd(page_size_weixin);
		logExample.setOrderByClause("money_log_create_time DESC,money_log_id DESC");
		return moneyLogMapper.selectByExample(logExample);
	}

	@Override
	public Integer findConsumeNumByAccountId(Long accountId) throws Exception {
		MoneyLogExample logExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = logExample.createCriteria();
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		criteria.andMoneyLogAccountIdEqualTo(accountId);
		//正常
		criteria.andMoneyLogStateEqualTo(1);
		criteria.andMoneyLogStreamTypeIn(arr);
		return moneyLogMapper.countByExample(logExample);
	}

	@Override
	public List<MoneyLog> findRechargeByAccountId(Long accountId, Integer pageIndex, Integer page_size_weixin)
			throws Exception {
		MoneyLogExample logExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = logExample.createCriteria();
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(2);
		arr.add(3);
		arr.add(4);
		criteria.andMoneyLogAccountIdEqualTo(accountId);
		criteria.andMoneyLogStateEqualTo(1);
		criteria.andMoneyLogStreamTypeIn(arr);
		logExample.setLimitStart(PageUtil.getWeixinStart(pageIndex));
		logExample.setLimitEnd(page_size_weixin);
		logExample.setOrderByClause("money_log_create_time DESC,money_log_id DESC");
		return moneyLogMapper.selectByExample(logExample);
	}

	@Override
	public Integer findRechargeNumByAccountId(Long accountId) throws Exception {
		MoneyLogExample logExample = new MoneyLogExample();
		MoneyLogExample.Criteria criteria = logExample.createCriteria();
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(2);
		arr.add(3);
		arr.add(4);
		criteria.andMoneyLogAccountIdEqualTo(accountId);
		//正常
		criteria.andMoneyLogStateEqualTo(1);
		criteria.andMoneyLogStreamTypeIn(arr);
		return moneyLogMapper.countByExample(logExample);
	}
	
	
}
