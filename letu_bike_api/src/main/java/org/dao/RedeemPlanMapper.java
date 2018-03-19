package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.RedeemPlan;
import org.entity.dto.RedeemPlanExample;

public interface RedeemPlanMapper {
    int countByExample(RedeemPlanExample example);

    int deleteByExample(RedeemPlanExample example);

    int deleteByPrimaryKey(Long planId);

    int insert(RedeemPlan record);

    int insertSelective(RedeemPlan record);

    List<RedeemPlan> selectByExample(RedeemPlanExample example);

    RedeemPlan selectByPrimaryKey(Long planId);

    int updateByExampleSelective(@Param("record") RedeemPlan record, @Param("example") RedeemPlanExample example);

    int updateByExample(@Param("record") RedeemPlan record, @Param("example") RedeemPlanExample example);

    int updateByPrimaryKeySelective(RedeemPlan record);

    int updateByPrimaryKey(RedeemPlan record);
    //连表
    List<RedeemPlan> selectUnionByExample(RedeemPlanExample example);
    int countUnionByExample(RedeemPlanExample example);
}