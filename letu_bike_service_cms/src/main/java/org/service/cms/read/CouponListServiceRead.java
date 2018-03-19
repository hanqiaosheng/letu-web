package org.service.cms.read;

import org.entity.dto.Coupon;
import java.util.List;

public interface CouponListServiceRead {
    /**
     * 查询所有的骑行券
     * @param pageIndex
     * @param page_size_web
     * @param title
     * @return
     * @throws Exception
     */
    public List<Coupon> findAll(Integer pageIndex, Integer page_size_web, String title)throws Exception;

    /**
     * 计算数量
     * @param title
     * @return
     * @throws Exception
     */
    public Integer countAll(String title)throws Exception;

    /**
     * 通过CouponId查询骑行券
     * @param CouponId
     * @return
     * @throws Exception
     */
    public Coupon findById(Long CouponId)throws Exception;

    /**
     * 查询所有骑行券
     * @return
     * @throws Exception
     */
    public List<Coupon> findAllCoupon()throws Exception;
}
