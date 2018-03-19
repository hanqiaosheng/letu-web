package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.InvoiceMapper;
import org.entity.dto.Invoice;
import org.entity.dto.InvoiceExample;
import org.service.cms.read.InvoiceServiceRead;
import org.util.DateUtil;
import org.util.PageUtil;

public class InvoiceServiceReadImpl implements InvoiceServiceRead{
	
	@Resource InvoiceMapper invoiceMapper;

	@Override
	public List<Invoice> findInvoiceByCondition(Invoice invoice, Integer pageIndex, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub
		InvoiceExample invoiceExample = new InvoiceExample();
		InvoiceExample.Criteria criteria = invoiceExample.createCriteria();
		criteria.andInvoiceStateNotEqualTo(-1);
		if(invoice.getInvoiceType()!=null&&invoice.getInvoiceType()!=-1){
			criteria.andInvoiceTypeEqualTo(invoice.getInvoiceType());
		}
		if(null!=invoice.getInvoiceState()&&invoice.getInvoiceState()!=-2){
			criteria.andInvoiceStateEqualTo(invoice.getInvoiceState());
		}
		if(null!=invoice.getFromTime()&&!invoice.getFromTime().equals("")){
			criteria.andInvoiceApplicationTimeGreaterThanOrEqualTo(DateUtil.changStringDate03(invoice.getFromTime()));
		}
		if(invoice.getToTime()!=null&&!invoice.getToTime().equals("")){
			criteria.andInvoiceApplicationTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(invoice.getToTime()), 1));
		}
		invoiceExample.setOrderByClause("invoice_application_time DESC");
		if(pageIndex!=null){
			invoiceExample.setLimitStart(PageUtil.getStart(pageIndex));
			invoiceExample.setLimitEnd(pageSize);
		}
		 List<Invoice> Invoices = invoiceMapper.selectByExample(invoiceExample);
		 return Invoices;
		
		
	}

	@Override
	public Integer countInvoiceByCondition(Invoice invoice) throws Exception {
		// TODO Auto-generated method stub
		InvoiceExample invoiceExample = new InvoiceExample();
		InvoiceExample.Criteria criteria = invoiceExample.createCriteria();
		criteria.andInvoiceStateNotEqualTo(-1);
		if(invoice.getInvoiceType()!=null&&invoice.getInvoiceType()!=-1){
			criteria.andInvoiceTypeEqualTo(invoice.getInvoiceType());
		}
		if(null!=invoice.getInvoiceState()&&invoice.getInvoiceState()!=-2){
			criteria.andInvoiceStateEqualTo(invoice.getInvoiceState());
		}
		if(null!=invoice.getFromTime()&&!invoice.getFromTime().equals("")){
			criteria.andInvoiceApplicationTimeGreaterThanOrEqualTo(DateUtil.changStringDate03(invoice.getFromTime()));
		}
		if(invoice.getToTime()!=null&&!invoice.getToTime().equals("")){
			criteria.andInvoiceApplicationTimeLessThan(DateUtil.plusDate(DateUtil.changStringDate03(invoice.getToTime()), 1));
		}
		
		
		Integer pageCount = invoiceMapper.countByExample(invoiceExample);
		return pageCount;
	}

	@Override
	public Invoice findInvoiceById(Long invoiceId) throws Exception {
		// TODO Auto-generated method stub
		return invoiceMapper.selectByPrimaryKey(invoiceId);
		
	}

	@Override
	public Integer countUntreated() throws Exception {
		// TODO Auto-generated method stub
		InvoiceExample invoiceExample = new InvoiceExample();
		InvoiceExample.Criteria criteria = invoiceExample.createCriteria();
		/*criteria.andInvoiceStateEqualTo(0);*/
		/*criteria.andInvoiceStateEqualTo(1);*/
		criteria.andInvoiceStateBetween(0, 1);
		return invoiceMapper.countByExample(invoiceExample);
	}

	

}
