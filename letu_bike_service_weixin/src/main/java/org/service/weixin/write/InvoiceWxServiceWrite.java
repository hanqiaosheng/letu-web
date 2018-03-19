package org.service.weixin.write;

import org.entity.dto.Invoice;

public interface InvoiceWxServiceWrite {
	/**
	 * 添加发票
	 * @param invoice
	 * @return
	 * @throws Exception
	 */
	public Long addInvoice(Invoice invoice)throws Exception;

	/**
	 * 更新发票
	 * @param invoice
	 * @throws Exception
	 */
	public void updateInvoice(Invoice invoice)throws Exception;
}
