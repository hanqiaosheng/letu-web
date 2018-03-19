package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.Refund;
import org.entity.dto.RefundExample;

public interface RefundMapper {
    int countByExample(RefundExample example);
    
    int countUnionByExample(RefundExample example);

    int deleteByExample(RefundExample example);

    int deleteByPrimaryKey(Long refundId);

    int insert(Refund record);

    int insertSelective(Refund record);

    List<Refund> selectByExample(RefundExample example);
    
    List<Refund> selectUnionByExample(RefundExample example);

    Refund selectByPrimaryKey(Long refundId);

    int updateByExampleSelective(@Param("record") Refund record, @Param("example") RefundExample example);

    int updateByExample(@Param("record") Refund record, @Param("example") RefundExample example);

    int updateByPrimaryKeySelective(Refund record);

    int updateByPrimaryKey(Refund record);
}