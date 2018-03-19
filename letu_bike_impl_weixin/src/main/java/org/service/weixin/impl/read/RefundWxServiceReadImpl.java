package org.service.weixin.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.RefundMapper;
import org.entity.dto.Refund;
import org.entity.dto.RefundExample;
import org.service.weixin.read.RefundWxServiceRead;
import org.springframework.stereotype.Service;

@Service("refundWxServiceRead")
public class RefundWxServiceReadImpl implements RefundWxServiceRead{
	
	@Resource
	RefundMapper refundMapper;

	@Override
	public Refund findRefundByorderId(String orderId) throws Exception {
		// TODO Auto-generated method stub
		RefundExample refundExample = new RefundExample();
		RefundExample.Criteria criteria = refundExample.createCriteria();
		criteria.andRefundOrderIdEqualTo(orderId);
		criteria.andRefundStateEqualTo(0);
		List<Refund> refunds = refundMapper.selectByExample(refundExample);
		if(refunds.size()>0){
			return refunds.get(0);
		}
		return null;
	}


}
