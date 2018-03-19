package org.service.weixin.read;

import org.entity.dto.Refund;

public interface RefundWxServiceRead {

	/**
	 * 根据微信支付订单号查询信息
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public Refund findRefundByorderId(String orderId) throws Exception;
}
