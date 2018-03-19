package org.service.cms.impl.write;


import javax.annotation.Resource;

import org.dao.CashCouponMapper;
import org.entity.dto.CashCoupon;
import org.service.cms.write.CashCouponServiceWrite;
import org.springframework.stereotype.Service;


@Service("cashCouponServiceWrite")
public class CashCouponServiceWriteImpl implements CashCouponServiceWrite {
	@Resource
	CashCouponMapper cashCouponMapper;

	@Override
	public void addCashCoupon(CashCoupon cashCoupon) throws Exception {
		cashCouponMapper.insertSelective(cashCoupon);
	}

	@Override
	public void updateCashCoupon(CashCoupon cashCoupon) throws Exception {
		cashCouponMapper.updateByPrimaryKeySelective(cashCoupon);
	}

	
}
