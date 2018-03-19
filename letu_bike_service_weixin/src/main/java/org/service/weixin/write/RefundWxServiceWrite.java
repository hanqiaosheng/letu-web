package org.service.weixin.write;

import org.entity.dto.Refund;

public interface RefundWxServiceWrite {
	
	public void addRefundApplication(Refund refund) throws Exception;
	
	public void updateRefundApplication(Refund refund) throws Exception;
}
