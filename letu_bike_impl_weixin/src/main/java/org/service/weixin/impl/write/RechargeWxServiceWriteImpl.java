package org.service.weixin.impl.write;


import javax.annotation.Resource;

import org.dao.RechargeRecordMapper;
import org.entity.dto.RechargeRecord;
import org.service.weixin.write.RechargeWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("rechargeServiceWxWrite")
public class RechargeWxServiceWriteImpl implements RechargeWxServiceWrite{
	
	@Resource
	RechargeRecordMapper rechargeRecordMapper;

	@Override
	public void updateRechargeRecord(RechargeRecord rechargeRecord)throws Exception {
		// TODO Auto-generated method stub
		rechargeRecordMapper.updateByPrimaryKeySelective(rechargeRecord);
	}

	@Override
	public void addRechargeRecord(RechargeRecord rechargeRecord) throws Exception {
		// TODO Auto-generated method stub
		rechargeRecordMapper.insertSelective(rechargeRecord);
	}

	
}
