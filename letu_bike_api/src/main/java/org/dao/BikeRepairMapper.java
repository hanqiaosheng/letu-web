package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.BikeRepair;
import org.entity.dto.BikeRepairExample;

public interface BikeRepairMapper {
    int countByExample(BikeRepairExample example);

    int deleteByExample(BikeRepairExample example);

    int deleteByPrimaryKey(Long managerId);

    int insert(BikeRepair record);

    int insertSelective(BikeRepair record);

    List<BikeRepair> selectByExample(BikeRepairExample example);

    BikeRepair selectByPrimaryKey(Long managerId);

    int updateByExampleSelective(@Param("record") BikeRepair record, @Param("example") BikeRepairExample example);

    int updateByExample(@Param("record") BikeRepair record, @Param("example") BikeRepairExample example);

    int updateByPrimaryKeySelective(BikeRepair record);

    int updateByPrimaryKey(BikeRepair record);
}