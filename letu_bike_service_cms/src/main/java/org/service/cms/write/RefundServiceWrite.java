package org.service.cms.write;

import org.entity.dto.Refund;

public interface RefundServiceWrite {
	
	public int updateState(Refund refund)throws Exception;
	
}
