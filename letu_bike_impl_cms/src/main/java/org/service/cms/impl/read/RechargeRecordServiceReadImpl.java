package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.RechargeRecordMapper;
import org.entity.dto.RechargeRecord;
import org.entity.dto.RechargeRecordExample;
import org.service.cms.read.RechargeRecordServiceRead;
import org.springframework.stereotype.Service;


@Service("rechargeRecordServiceRead")
public class RechargeRecordServiceReadImpl implements RechargeRecordServiceRead {

	@Resource
	RechargeRecordMapper rechargeRecordMapper;
	
	@Override
	public RechargeRecord findRechargeRecordById(String rechargeOrderId) throws Exception {
		// TODO Auto-generated method stub
		RechargeRecordExample example = new RechargeRecordExample();
		RechargeRecordExample.Criteria criteria = example.createCriteria();
		criteria.andRechargeOrderIdEqualTo(rechargeOrderId);
		List<RechargeRecord> rechargeRecords = rechargeRecordMapper.selectByExample(example);
		if(rechargeRecords.size()>0){
			return rechargeRecords.get(0);
		}
		return null;
	}

	@Override
	public RechargeRecord findRechargeRecordByAccountId(Long rechargeAccountId) throws Exception {
		// TODO Auto-generated method stub
		RechargeRecordExample example = new RechargeRecordExample();
		RechargeRecordExample.Criteria criteria = example.createCriteria();
		criteria.andRechargeAccountIdEqualTo(rechargeAccountId);
		criteria.andRechargeStateEqualTo(1);
		List<RechargeRecord> rechargeRecords = rechargeRecordMapper.selectByExample(example);
		if(rechargeRecords.size()>0){
			return rechargeRecords.get(0);
		}
		return null;
	}

	@Override
	public RechargeRecord findRechargeRecordByTradeNo(String refundCode) throws Exception {
		RechargeRecordExample example = new RechargeRecordExample();
		RechargeRecordExample.Criteria criteria = example.createCriteria();
		criteria.andRechargeOutTradeNoEqualTo(refundCode);
		List<RechargeRecord> rechargeRecords = rechargeRecordMapper.selectByExample(example);
		if(rechargeRecords.size()>0){
			return rechargeRecords.get(0);
		}
		return null;
	}


}
