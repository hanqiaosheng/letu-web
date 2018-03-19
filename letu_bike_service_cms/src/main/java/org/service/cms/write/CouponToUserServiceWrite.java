package org.service.cms.write;

import org.entity.dto.CouponToUser;

public interface CouponToUserServiceWrite {

    /**
     * 添加
     * @param couponToUser
     * @throws Exception
     */
    public void add(CouponToUser couponToUser) throws Exception;

    /**
     * 更新
     * @param couponToUser
     * @throws Exception
     */
    public void update(CouponToUser couponToUser) throws Exception;

    /**
     * 删除
     * @param couponToUser
     * @throws Exception
     */
    public void delete(CouponToUser couponToUser) throws Exception;
}
