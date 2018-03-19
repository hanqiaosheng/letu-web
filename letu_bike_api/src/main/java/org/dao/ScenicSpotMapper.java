package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.ScenicSpot;
import org.entity.dto.ScenicSpotExample;

public interface ScenicSpotMapper {
    int countByExample(ScenicSpotExample example);

    int deleteByExample(ScenicSpotExample example);

    int deleteByPrimaryKey(Long scenicSpotId);

    int insert(ScenicSpot record);

    int insertSelective(ScenicSpot record);

    List<ScenicSpot> selectByExampleWithBLOBs(ScenicSpotExample example);

    List<ScenicSpot> selectByExample(ScenicSpotExample example);

    ScenicSpot selectByPrimaryKey(Long scenicSpotId);

    int updateByExampleSelective(@Param("record") ScenicSpot record, @Param("example") ScenicSpotExample example);

    int updateByExampleWithBLOBs(@Param("record") ScenicSpot record, @Param("example") ScenicSpotExample example);

    int updateByExample(@Param("record") ScenicSpot record, @Param("example") ScenicSpotExample example);

    int updateByPrimaryKeySelective(ScenicSpot record);

    int updateByPrimaryKeyWithBLOBs(ScenicSpot record);

    int updateByPrimaryKey(ScenicSpot record);
    
    //连表
    List<ScenicSpot> selectUnionByExample(ScenicSpotExample example);
    
    int countUnionByExample(ScenicSpotExample example);
}