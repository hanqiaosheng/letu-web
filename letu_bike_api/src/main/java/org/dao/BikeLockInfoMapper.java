package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.BikeLockInfoExample;

public interface BikeLockInfoMapper {
    int countByExample(BikeLockInfoExample example);

    int deleteByExample(BikeLockInfoExample example);

    int deleteByPrimaryKey(Long bikeLockId);

    int insert(BikeLockInfo record);

    int insertSelective(BikeLockInfo record);

    List<BikeLockInfo> selectByExample(BikeLockInfoExample example);

    BikeLockInfo selectByPrimaryKey(Long bikeLockId);

    int updateByExampleSelective(@Param("record") BikeLockInfo record, @Param("example") BikeLockInfoExample example);

    int updateByExample(@Param("record") BikeLockInfo record, @Param("example") BikeLockInfoExample example);

    int updateByPrimaryKeySelective(BikeLockInfo record);

    int updateByPrimaryKey(BikeLockInfo record);
    
    //联表
    List<BikeLockInfo> selectUnionByExample(BikeLockInfoExample example);
    int countUnionByExample(BikeLockInfoExample example);
}