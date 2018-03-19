package org.service.cms.write;

import org.entity.dto.CouponScheme;

public interface CouponSchemeServiceWrite {
    /**
     * 添加骑行券方案
     * @param CouponScheme
     * @throws Exception
     */
    public void addCouponScheme(CouponScheme CouponScheme)throws Exception;

    /**
     * 更新骑行券方案
     * @param CouponScheme
     * @throws Exception
     */
    public void updateCouponScheme(CouponScheme CouponScheme)throws Exception;
}
