package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.UserRedeemPlan;
import org.entity.dto.UserRedeemPlanExample;

public interface UserRedeemPlanMapper {
    int countByExample(UserRedeemPlanExample example);

    int deleteByExample(UserRedeemPlanExample example);

    int deleteByPrimaryKey(Long userRedeemPlanId);

    int insert(UserRedeemPlan record);

    int insertSelective(UserRedeemPlan record);

    List<UserRedeemPlan> selectByExample(UserRedeemPlanExample example);

    UserRedeemPlan selectByPrimaryKey(Long userRedeemPlanId);

    int updateByExampleSelective(@Param("record") UserRedeemPlan record, @Param("example") UserRedeemPlanExample example);

    int updateByExample(@Param("record") UserRedeemPlan record, @Param("example") UserRedeemPlanExample example);

    int updateByPrimaryKeySelective(UserRedeemPlan record);

    int updateByPrimaryKey(UserRedeemPlan record);
    
    //连表
    List<UserRedeemPlan> selectUnionByExample(UserRedeemPlanExample example);
    int countUnionByExample(UserRedeemPlanExample example);
}