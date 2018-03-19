package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.OperateLog;
import org.entity.dto.OperateLogExample;

public interface OperateLogMapper {
    int countByExample(OperateLogExample example);

    int deleteByExample(OperateLogExample example);

    int deleteByPrimaryKey(Long operateId);

    int insert(OperateLog record);

    int insertSelective(OperateLog record);

    List<OperateLog> selectByExampleWithBLOBs(OperateLogExample example);

    List<OperateLog> selectByExample(OperateLogExample example);

    OperateLog selectByPrimaryKey(Long operateId);

    int updateByExampleSelective(@Param("record") OperateLog record, @Param("example") OperateLogExample example);

    int updateByExampleWithBLOBs(@Param("record") OperateLog record, @Param("example") OperateLogExample example);

    int updateByExample(@Param("record") OperateLog record, @Param("example") OperateLogExample example);

    int updateByPrimaryKeySelective(OperateLog record);

    int updateByPrimaryKeyWithBLOBs(OperateLog record);

    int updateByPrimaryKey(OperateLog record);
}