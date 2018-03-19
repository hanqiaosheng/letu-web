package org.service.weixin.write;

import org.entity.dto.Tradeorder;

public interface TrOrderWxServiceWrite {
	
	public void updateTrOrder(Tradeorder tradeorder) throws Exception;
	
	public void addTrOrder(Tradeorder tradeorder) throws Exception;
	
}
