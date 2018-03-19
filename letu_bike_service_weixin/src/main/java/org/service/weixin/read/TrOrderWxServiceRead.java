package org.service.weixin.read;


import org.entity.dto.Tradeorder;

public interface TrOrderWxServiceRead {
	public Tradeorder findByTransactionNumber(String transactionNumber) throws Exception;
}
