package org.service.cms.read;


import java.util.List;

import org.entity.dto.Invoice;

public interface InvoiceServiceRead {
	
	public List<Invoice> findInvoiceByCondition (Invoice invoice,Integer pageIndex, Integer pageSize) throws Exception;
	
	public Integer countInvoiceByCondition(Invoice invoice) throws Exception;
	
	public Invoice findInvoiceById(Long invoiceId) throws Exception;
	
	/**
	 *查询未处理的发票数量
	 * @return
	 * @throws Exception
	 */
	public Integer countUntreated()throws Exception;

}
