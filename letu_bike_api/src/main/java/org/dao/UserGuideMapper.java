package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.UserGuide;
import org.entity.dto.UserGuideExample;

public interface UserGuideMapper {
    int countByExample(UserGuideExample example);

    int deleteByExample(UserGuideExample example);

    int deleteByPrimaryKey(Long userGuideId);

    int insert(UserGuide record);

    int insertSelective(UserGuide record);

    List<UserGuide> selectByExampleWithBLOBs(UserGuideExample example);

    List<UserGuide> selectByExample(UserGuideExample example);

    UserGuide selectByPrimaryKey(Long userGuideId);

    int updateByExampleSelective(@Param("record") UserGuide record, @Param("example") UserGuideExample example);

    int updateByExampleWithBLOBs(@Param("record") UserGuide record, @Param("example") UserGuideExample example);

    int updateByExample(@Param("record") UserGuide record, @Param("example") UserGuideExample example);

    int updateByPrimaryKeySelective(UserGuide record);

    int updateByPrimaryKeyWithBLOBs(UserGuide record);

    int updateByPrimaryKey(UserGuide record);
}