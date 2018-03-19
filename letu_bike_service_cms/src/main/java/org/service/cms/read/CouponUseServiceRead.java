package org.service.cms.read;

import org.entity.dto.CouponUse;

import java.util.Date;
import java.util.List;

public interface CouponUseServiceRead {
    /**
     * 查询记录
     * @param pageIndex
     * @param pageSize
     * @param name
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public List<CouponUse> findAllCouponUse(Integer pageIndex, Integer pageSize, String name, Date startTime, Date endTime, String userTel, String redeemCode)throws Exception;

    /**
     * 计算数量
     * @param redeemPlanName
     * @param startTime
     * @param endTime
     * @return
     * @throws Exception
     */
    public Integer countAllCouponUse(String redeemPlanName, Date startTime, Date endTime,String userTel,String redeemCode)throws Exception;
}
