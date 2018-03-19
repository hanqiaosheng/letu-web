package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.CouponPlan;
import org.entity.dto.CouponPlanExample;

public interface CouponPlanMapper {
    int countByExample(CouponPlanExample example);

    int deleteByExample(CouponPlanExample example);

    int deleteByPrimaryKey(Long couponPlanId);

    int insert(CouponPlan record);

    int insertSelective(CouponPlan record);

    List<CouponPlan> selectByExampleWithBLOBs(CouponPlanExample example);

    List<CouponPlan> selectByExample(CouponPlanExample example);

    CouponPlan selectByPrimaryKey(Long couponPlanId);

    int updateByExampleSelective(@Param("record") CouponPlan record, @Param("example") CouponPlanExample example);

    int updateByExampleWithBLOBs(@Param("record") CouponPlan record, @Param("example") CouponPlanExample example);

    int updateByExample(@Param("record") CouponPlan record, @Param("example") CouponPlanExample example);

    int updateByPrimaryKeySelective(CouponPlan record);

    int updateByPrimaryKeyWithBLOBs(CouponPlan record);

    int updateByPrimaryKey(CouponPlan record);
}