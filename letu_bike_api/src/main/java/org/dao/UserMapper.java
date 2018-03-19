package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.User;
import org.entity.dto.UserExample;

public interface UserMapper {
    int countByExample(UserExample example);
    
    int countUnionByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);
    
    List<User> selectUnionByExample(UserExample example);

    User selectByPrimaryKey(Long userId);

    List<User> selectByIds(@Param("userIds") List<Long> userIds);

    List<User> selectUserAndRentByIds(@Param("userIds") List<Long> userIds);
    
    User selectUnionByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}