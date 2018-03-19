package org.service.cms.impl.write;


import javax.annotation.Resource;

import org.dao.InvoiceMapper;
import org.entity.dto.Invoice;
import org.service.cms.write.InvoiceServiceWrite;

public class InvoiceServiceWriteImpl implements InvoiceServiceWrite{
	
	@Resource InvoiceMapper invoiceMapper;
	
	@Override
	public void updateInvoice(Invoice invoice) throws Exception {
		// TODO Auto-generated method stub
		invoiceMapper.updateByPrimaryKeySelective(invoice);
	}

	

}
