package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.UserToTask;
import org.entity.dto.UserToTaskExample;

public interface UserToTaskMapper {
    int countByExample(UserToTaskExample example);

    int deleteByExample(UserToTaskExample example);

    int deleteByPrimaryKey(Long userToTaskId);

    int insert(UserToTask record);

    int insertSelective(UserToTask record);

    List<UserToTask> selectByExample(UserToTaskExample example);

    UserToTask selectByPrimaryKey(Long userToTaskId);

    int updateByExampleSelective(@Param("record") UserToTask record, @Param("example") UserToTaskExample example);

    int updateByExample(@Param("record") UserToTask record, @Param("example") UserToTaskExample example);

    int updateByPrimaryKeySelective(UserToTask record);

    int updateByPrimaryKey(UserToTask record);
}