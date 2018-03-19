package org.service.cms.impl.write;


import javax.annotation.Resource;

import org.dao.CouponPlanMapper;
import org.entity.dto.CouponPlan;
import org.service.cms.write.CouponPlanServiceWrite;
import org.springframework.stereotype.Service;


@Service("couponPlanServiceWrite")
public class CouponPlanServiceWriteImpl implements CouponPlanServiceWrite {
	@Resource
	CouponPlanMapper couponPlanMapper;

	@Override
	public void addCouponPlan(CouponPlan couponPlan) throws Exception {
		couponPlanMapper.insertSelective(couponPlan);
	}

	@Override
	public void updateCouponPlan(CouponPlan couponPlan) throws Exception {
		couponPlanMapper.updateByPrimaryKeyWithBLOBs(couponPlan);
	}

	
}
