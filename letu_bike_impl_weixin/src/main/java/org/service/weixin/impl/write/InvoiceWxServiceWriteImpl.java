package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.InvoiceMapper;
import org.entity.dto.Invoice;
import org.service.weixin.write.InvoiceWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("invoiceWxServiceWrite")
public class InvoiceWxServiceWriteImpl implements InvoiceWxServiceWrite {

	@Resource
	InvoiceMapper invoiceMapper;
	
	@Override
	public Long addInvoice(Invoice invoice) throws Exception {
		invoiceMapper.insertSelective(invoice);
		return invoice.getInvoiceId();
	}

	@Override
	public void updateInvoice(Invoice invoice) throws Exception {
		invoiceMapper.updateByPrimaryKeySelective(invoice);
	}
}
