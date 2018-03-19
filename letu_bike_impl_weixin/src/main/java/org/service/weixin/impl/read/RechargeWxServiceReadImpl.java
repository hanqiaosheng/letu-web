package org.service.weixin.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.RechargeRecordMapper;
import org.entity.dto.RechargeRecord;
import org.entity.dto.RechargeRecordExample;
import org.service.weixin.read.RechargeWxServiceRead;
import org.springframework.stereotype.Service;

@Service("rechargeWxServiceRead")
public class RechargeWxServiceReadImpl implements RechargeWxServiceRead{
	
	@Resource
	RechargeRecordMapper rechargeRecordMapper;
	

	@Override
	public List<RechargeRecord> findRecharge(Long accountId) throws Exception {
		RechargeRecordExample rechargeRecordExample = new RechargeRecordExample();
		RechargeRecordExample.Criteria criteria = rechargeRecordExample.createCriteria();
		criteria.andRechargeAccountIdEqualTo(accountId);
		//criteria.andRechargePayTypeEqualTo(1);//微信支付
		criteria.andRechargeTypeEqualTo(1);//押金补充
		criteria.andRechargeStateEqualTo(1);
		rechargeRecordExample.setOrderByClause("recharge_time desc");
		List<RechargeRecord> rechargeRecords = rechargeRecordMapper.selectByExample(rechargeRecordExample);
		if(rechargeRecords.size()>0){
			return rechargeRecords;
		}
		return null;
	}

	@Override
	public RechargeRecord findRechargeById(String rechargeOrderId) throws Exception {
		// TODO Auto-generated method stub
		RechargeRecordExample rechargeRecordExample = new RechargeRecordExample();
		RechargeRecordExample.Criteria criteria = rechargeRecordExample.createCriteria();
		criteria.andRechargeOrderIdEqualTo(rechargeOrderId);
		List<RechargeRecord> rechargeRecords = rechargeRecordMapper.selectByExample(rechargeRecordExample);
		if(rechargeRecords.size()>0){
			return rechargeRecords.get(0);
		}
		return null;
	}

	@Override
	public List<RechargeRecord> findRechargeByAccountId(Long accountId, Integer rechargeType) throws Exception {
		// TODO Auto-generated method stub
		RechargeRecordExample rechargeRecordExample = new RechargeRecordExample();
		RechargeRecordExample.Criteria criteria = rechargeRecordExample.createCriteria();
		criteria.andRechargeAccountIdEqualTo(accountId);
		criteria.andRechargeTypeEqualTo(rechargeType);
		List<RechargeRecord> rechargeRecords = rechargeRecordMapper.selectByExample(rechargeRecordExample);
		return rechargeRecords;
	}


}
