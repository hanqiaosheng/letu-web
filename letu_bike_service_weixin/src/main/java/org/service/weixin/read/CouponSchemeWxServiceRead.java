package org.service.weixin.read;

import org.entity.dto.CouponScheme;

import java.util.List;

public interface CouponSchemeWxServiceRead {
    /**
     * 查询所有兑换方案
     * @param pageIndex
     * @param page_size_web
     * @param title
     * @return
     * @throws Exception
     */
    public List<CouponScheme> findAll(Integer pageIndex, Integer page_size_web, String title)throws Exception;

    /**
     * 计算数量
     * @param title
     * @return
     * @throws Exception
     */
    public Integer countAll(String title)throws Exception;

    /**
     * 通过CouponSchemeId查询信息
     * @param couponSchemeId
     * @return
     * @throws Exception
     */
    public CouponScheme findCouponSchemeById(Long couponSchemeId)throws Exception;

    /**
     * 通过CouponSchemeIds查询信息
     * @param couponSchemeIds
     * @return
     * @throws Exception
     */
    public List<CouponScheme> findUnionByIds(List<Long> couponSchemeIds)throws Exception;

    public CouponScheme findUnionById(Long couponSchemeId) throws Exception;
    /**
     * 查询所有上线的方案
     * @return
     * @throws Exception
     */
    public List<CouponScheme> findAllOnline()throws Exception;

    /**
     * 查询所有方案
     * @return
     * @throws Exception
     */
    public List<CouponScheme> findAllPlan()throws Exception;
}
