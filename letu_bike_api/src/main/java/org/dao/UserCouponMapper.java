package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.UserCoupon;
import org.entity.dto.UserCouponExample;

public interface UserCouponMapper {
    int countByExample(UserCouponExample example);

    int deleteByExample(UserCouponExample example);

    int deleteByPrimaryKey(Long userToCouponId);

    int insert(UserCoupon record);

    int insertSelective(UserCoupon record);

    List<UserCoupon> selectByExample(UserCouponExample example);

    UserCoupon selectByPrimaryKey(Long userToCouponId);

    int updateByExampleSelective(@Param("record") UserCoupon record, @Param("example") UserCouponExample example);

    int updateByExample(@Param("record") UserCoupon record, @Param("example") UserCouponExample example);

    int updateByPrimaryKeySelective(UserCoupon record);

    int updateByPrimaryKey(UserCoupon record);
    //连表
    List<UserCoupon> selectUnionByExample(UserCouponExample example);
}