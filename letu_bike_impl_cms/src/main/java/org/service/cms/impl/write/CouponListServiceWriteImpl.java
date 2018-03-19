package org.service.cms.impl.write;

import org.dao.CouponMapper;
import org.entity.dto.Coupon;
import org.service.cms.write.CouponListServiceWrite;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("couponListServiceWrite")
public class CouponListServiceWriteImpl implements CouponListServiceWrite{
    @Resource
    CouponMapper CouponMapper;

    @Override
    public void addCoupon(Coupon Coupon) throws Exception {
        CouponMapper.insertSelective(Coupon);
    }

    @Override
    public void updateCoupon(Coupon Coupon) throws Exception {
        CouponMapper.updateByPrimaryKeySelective(Coupon);
    }
}
