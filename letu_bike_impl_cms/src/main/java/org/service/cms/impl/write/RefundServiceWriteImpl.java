package org.service.cms.impl.write;


import javax.annotation.Resource;

import org.dao.RefundMapper;
import org.entity.dto.Refund;
import org.service.cms.write.RefundServiceWrite;
import org.springframework.stereotype.Service;
@Service("refundServiceWrite")
public class RefundServiceWriteImpl implements RefundServiceWrite {

	@Resource
	RefundMapper refundMapper;

	@Override
	public int updateState(Refund refund) throws Exception {
		// TODO Auto-generated method stub
		return refundMapper.updateByPrimaryKeySelective(refund);
	}


}
