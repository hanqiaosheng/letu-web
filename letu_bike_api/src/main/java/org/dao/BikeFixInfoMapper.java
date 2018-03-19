package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.BikeFixInfo;
import org.entity.dto.BikeFixInfoExample;

public interface BikeFixInfoMapper {
    int countByExample(BikeFixInfoExample example);
    
    int countUnionByExample(BikeFixInfoExample example);

    int deleteByExample(BikeFixInfoExample example);

    int deleteByPrimaryKey(Long fixId);

    int insert(BikeFixInfo record);

    int insertSelective(BikeFixInfo record);

    List<BikeFixInfo> selectByExample(BikeFixInfoExample example);
    
    List<BikeFixInfo> selectUnionByExample(BikeFixInfoExample example);
    
    BikeFixInfo selectByPrimaryKey(Long fixId);

    int updateByExampleSelective(@Param("record") BikeFixInfo record, @Param("example") BikeFixInfoExample example);

    int updateByExample(@Param("record") BikeFixInfo record, @Param("example") BikeFixInfoExample example);

    int updateByPrimaryKeySelective(BikeFixInfo record);

    int updateByPrimaryKey(BikeFixInfo record);
    
    //最大批次
    String selectMaxBatch();
    //最近维护结束日期
    String selectMaxDate(Long bikeId);
    
    List<String> selectBatchNumber(); 
}