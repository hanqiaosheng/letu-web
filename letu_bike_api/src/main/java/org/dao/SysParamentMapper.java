package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.SysParament;
import org.entity.dto.SysParamentExample;

public interface SysParamentMapper {
    int countByExample(SysParamentExample example);

    int deleteByExample(SysParamentExample example);

    int deleteByPrimaryKey(Long sysParamentId);

    int insert(SysParament record);

    int insertSelective(SysParament record);

    List<SysParament> selectByExample(SysParamentExample example);

    SysParament selectByPrimaryKey(Long sysParamentId);

    int updateByExampleSelective(@Param("record") SysParament record, @Param("example") SysParamentExample example);

    int updateByExample(@Param("record") SysParament record, @Param("example") SysParamentExample example);

    int updateByPrimaryKeySelective(SysParament record);

    int updateByPrimaryKey(SysParament record);
}