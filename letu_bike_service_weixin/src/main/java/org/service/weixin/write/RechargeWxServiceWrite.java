package org.service.weixin.write;

import org.entity.dto.RechargeRecord;

public interface RechargeWxServiceWrite {
	
	public void updateRechargeRecord(RechargeRecord rechargeRecord) throws Exception;
	
	public void addRechargeRecord(RechargeRecord rechargeRecord) throws Exception;
	
}
