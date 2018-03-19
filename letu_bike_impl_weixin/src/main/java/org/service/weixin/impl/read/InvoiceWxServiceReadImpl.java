package org.service.weixin.impl.read;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dao.InvoiceMapper;
import org.entity.dto.Invoice;
import org.entity.dto.InvoiceExample;
import org.service.weixin.read.InvoiceWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("invoiceWxServiceRead")
public class InvoiceWxServiceReadImpl implements InvoiceWxServiceRead {

	@Resource
	InvoiceMapper invoiceMapper;
	
	@Override
	public Invoice findById(Long invoiceId) throws Exception {
		return invoiceMapper.selectByPrimaryKey(invoiceId);
	}

	@Override
	public List<Invoice> findByUserId(Long userId,Integer pageIndex,Integer pageSize) throws Exception {
		InvoiceExample example = new InvoiceExample();
		InvoiceExample.Criteria criteria = example.createCriteria();
		criteria.andInvoiceUserIdEqualTo(userId);
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(-1);
		arr.add(5);
		criteria.andInvoiceStateNotIn(arr);
		example.setLimitStart(PageUtil.getWeixinStart(pageIndex));
		example.setLimitEnd(pageSize);
		example.setOrderByClause("invoice_state=3,invoice_application_time DESC");
		return invoiceMapper.selectByExample(example);
	}

	@Override
	public Integer findCountByUserId(Long userId) throws Exception {
		InvoiceExample example = new InvoiceExample();
		InvoiceExample.Criteria criteria = example.createCriteria();
		criteria.andInvoiceUserIdEqualTo(userId);
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(-1);
		arr.add(5);
		criteria.andInvoiceStateNotIn(arr);
		return invoiceMapper.countByExample(example);
	}

	@Override
	public List<Invoice> findAllByUserId(Long userId) throws Exception {
		InvoiceExample example = new InvoiceExample();
		InvoiceExample.Criteria criteria = example.createCriteria();
		criteria.andInvoiceUserIdEqualTo(userId);
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(-1);
		arr.add(5);
		criteria.andInvoiceStateNotIn(arr);;
		return invoiceMapper.selectByExample(example);
	}


}
