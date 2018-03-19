package org.service.cms.read;

import java.util.List;
import org.entity.dto.CouponToUser;
import org.springframework.stereotype.Service;


public interface CouponToUserServiceRead {
    /**
     * 根据券方案id获取用户id列表
     * @param couponId
     * @return
     * @throws Exception
     */
    public List<Long> findByCouponId(Long couponId) throws Exception;


    /**
     * 根据用户id 获取 这个用户的方案id
     * @param userId
     * @return
     * @throws Exception
     */
    public List<Long> findByUserId(Long userId) throws Exception;


    public CouponToUser findByIds(Long couponId,Long userId) throws Exception;
}
