package org.service.weixin.impl.write;


import javax.annotation.Resource;

import org.dao.RefundMapper;
import org.entity.dto.Refund;
import org.service.weixin.write.RefundWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("refundWxServiceWrite")
public class RefundWxServiceWriteImpl implements RefundWxServiceWrite{
	
	@Resource
	RefundMapper refundMapper;

	@Override
	public void addRefundApplication(Refund refund) throws Exception{
		// TODO Auto-generated method stub
		refundMapper.insertSelective(refund);
	}

	@Override
	public void updateRefundApplication(Refund refund) throws Exception {
		// TODO Auto-generated method stub
		refundMapper.updateByPrimaryKeySelective(refund);
	}

	
}
