package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.CashCoupon;
import org.entity.dto.CashCouponExample;

public interface CashCouponMapper {
    int countByExample(CashCouponExample example);

    int deleteByExample(CashCouponExample example);

    int deleteByPrimaryKey(Long couponId);

    int insert(CashCoupon record);

    int insertSelective(CashCoupon record);

    List<CashCoupon> selectByExample(CashCouponExample example);

    CashCoupon selectByPrimaryKey(Long couponId);

    int updateByExampleSelective(@Param("record") CashCoupon record, @Param("example") CashCouponExample example);

    int updateByExample(@Param("record") CashCoupon record, @Param("example") CashCouponExample example);

    int updateByPrimaryKeySelective(CashCoupon record);

    int updateByPrimaryKey(CashCoupon record);
}