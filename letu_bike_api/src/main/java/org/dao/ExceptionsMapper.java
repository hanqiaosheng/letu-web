package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.Exceptions;
import org.entity.dto.ExceptionsExample;

public interface ExceptionsMapper {
    int countByExample(ExceptionsExample example);

    int deleteByExample(ExceptionsExample example);

    int deleteByPrimaryKey(Long exceptionId);

    int insert(Exceptions record);

    int insertSelective(Exceptions record);

    List<Exceptions> selectByExample(ExceptionsExample example);

    Exceptions selectByPrimaryKey(Long exceptionId);

    int updateByExampleSelective(@Param("record") Exceptions record, @Param("example") ExceptionsExample example);

    int updateByExample(@Param("record") Exceptions record, @Param("example") ExceptionsExample example);

    int updateByPrimaryKeySelective(Exceptions record);

    int updateByPrimaryKey(Exceptions record);
}