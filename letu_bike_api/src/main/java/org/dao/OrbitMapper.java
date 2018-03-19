package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.Orbit;
import org.entity.dto.OrbitExample;

public interface OrbitMapper {
    int countByExample(OrbitExample example);

    int deleteByExample(OrbitExample example);

    int deleteByPrimaryKey(Long orbitId);

    int insert(Orbit record);

    int insertSelective(Orbit record);

    List<Orbit> selectByExampleWithBLOBs(OrbitExample example);

    List<Orbit> selectByExample(OrbitExample example);

    Orbit selectByPrimaryKey(Long orbitId);

    int updateByExampleSelective(@Param("record") Orbit record, @Param("example") OrbitExample example);

    int updateByExampleWithBLOBs(@Param("record") Orbit record, @Param("example") OrbitExample example);

    int updateByExample(@Param("record") Orbit record, @Param("example") OrbitExample example);

    int updateByPrimaryKeySelective(Orbit record);

    int updateByPrimaryKeyWithBLOBs(Orbit record);

    int updateByPrimaryKey(Orbit record);
}