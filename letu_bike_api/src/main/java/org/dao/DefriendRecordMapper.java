package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.DefriendRecord;
import org.entity.dto.DefriendRecordExample;

public interface DefriendRecordMapper {
    int countByExample(DefriendRecordExample example);

    int deleteByExample(DefriendRecordExample example);

    int deleteByPrimaryKey(Long defriendId);

    int insert(DefriendRecord record);

    int insertSelective(DefriendRecord record);

    List<DefriendRecord> selectByExample(DefriendRecordExample example);

    DefriendRecord selectByPrimaryKey(Long defriendId);

    int updateByExampleSelective(@Param("record") DefriendRecord record, @Param("example") DefriendRecordExample example);

    int updateByExample(@Param("record") DefriendRecord record, @Param("example") DefriendRecordExample example);

    int updateByPrimaryKeySelective(DefriendRecord record);

    int updateByPrimaryKey(DefriendRecord record);
}