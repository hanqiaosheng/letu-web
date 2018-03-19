package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.MoneyLog;
import org.entity.dto.MoneyLogExample;

public interface MoneyLogMapper {
    int countByExample(MoneyLogExample example);

    int deleteByExample(MoneyLogExample example);

    int deleteByPrimaryKey(Long moneyLogId);

    int insert(MoneyLog record);

    int insertSelective(MoneyLog record);

    List<MoneyLog> selectByExampleWithBLOBs(MoneyLogExample example);

    List<MoneyLog> selectByExample(MoneyLogExample example);

    MoneyLog selectByPrimaryKey(Long moneyLogId);

    int updateByExampleSelective(@Param("record") MoneyLog record, @Param("example") MoneyLogExample example);

    int updateByExampleWithBLOBs(@Param("record") MoneyLog record, @Param("example") MoneyLogExample example);

    int updateByExample(@Param("record") MoneyLog record, @Param("example") MoneyLogExample example);

    int updateByPrimaryKeySelective(MoneyLog record);

    int updateByPrimaryKeyWithBLOBs(MoneyLog record);

    int updateByPrimaryKey(MoneyLog record);
    
    List<MoneyLog> selectUnionByExample(MoneyLogExample example);
    
    int countUnionByExample(MoneyLogExample example);
}