package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.DataDet;
import org.entity.dto.DataDetExample;

public interface DataDetMapper {
    int countByExample(DataDetExample example);

    int deleteByExample(DataDetExample example);

    int deleteByPrimaryKey(Long dataDetId);

    int insert(DataDet record);

    int insertSelective(DataDet record);

    List<DataDet> selectByExample(DataDetExample example);

    DataDet selectByPrimaryKey(Long dataDetId);

    int updateByExampleSelective(@Param("record") DataDet record, @Param("example") DataDetExample example);

    int updateByExample(@Param("record") DataDet record, @Param("example") DataDetExample example);

    int updateByPrimaryKeySelective(DataDet record);

    int updateByPrimaryKey(DataDet record);
}