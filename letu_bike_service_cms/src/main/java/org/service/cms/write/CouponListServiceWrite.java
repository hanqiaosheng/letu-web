package org.service.cms.write;

import org.entity.dto.Coupon;

public interface CouponListServiceWrite {
    /**
     * 添加骑行券
     * @param Coupon
     * @throws Exception
     */
    public void addCoupon(Coupon Coupon)throws Exception;

    /**
     * 更新骑行券
     * @param Coupon
     * @throws Exception
     */
    public void updateCoupon(Coupon Coupon)throws Exception;
}
