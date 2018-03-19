package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.FixedReturn;
import org.entity.dto.FixedReturnExample;
import org.entity.dto.LatLng;

public interface FixedReturnMapper {
	int countByExample(FixedReturnExample example);

    int deleteByExample(FixedReturnExample example);

    int deleteByPrimaryKey(Long fixedReturnId);

    int insert(FixedReturn record);

    int insertSelective(FixedReturn record);

    List<FixedReturn> selectByExampleWithBLOBs(FixedReturnExample example);

    List<FixedReturn> selectByExample(FixedReturnExample example);

    FixedReturn selectByPrimaryKey(Long fixedReturnId);

    int updateByExampleSelective(@Param("record") FixedReturn record, @Param("example") FixedReturnExample example);

    int updateByExampleWithBLOBs(@Param("record") FixedReturn record, @Param("example") FixedReturnExample example);

    int updateByExample(@Param("record") FixedReturn record, @Param("example") FixedReturnExample example);

    int updateByPrimaryKeySelective(FixedReturn record);

    int updateByPrimaryKeyWithBLOBs(FixedReturn record);

    int updateByPrimaryKey(FixedReturn record);
    
    List<LatLng> selectFixedsLatLng(FixedReturnExample example);
    
    int countUnionByExample(FixedReturnExample example);
    
    List<FixedReturn> selectUnionByExample(FixedReturnExample example);
}