package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.GradeRecord;
import org.entity.dto.GradeRecordExample;

public interface GradeRecordMapper {
    int countByExample(GradeRecordExample example);

    int deleteByExample(GradeRecordExample example);

    int deleteByPrimaryKey(Long gradeId);

    int insert(GradeRecord record);

    int insertSelective(GradeRecord record);

    List<GradeRecord> selectByExample(GradeRecordExample example);

    GradeRecord selectByPrimaryKey(Long gradeId);

    int updateByExampleSelective(@Param("record") GradeRecord record, @Param("example") GradeRecordExample example);

    int updateByExample(@Param("record") GradeRecord record, @Param("example") GradeRecordExample example);

    int updateByPrimaryKeySelective(GradeRecord record);

    int updateByPrimaryKey(GradeRecord record);
}