package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.AdminToRoleExample;
import org.entity.dto.AdminToRoleKey;

public interface AdminToRoleMapper {
    int countByExample(AdminToRoleExample example);

    int deleteByExample(AdminToRoleExample example);

    int deleteByPrimaryKey(AdminToRoleKey key);

    int insert(AdminToRoleKey record);

    int insertSelective(AdminToRoleKey record);

    List<AdminToRoleKey> selectByExample(AdminToRoleExample example);

    int updateByExampleSelective(@Param("record") AdminToRoleKey record, @Param("example") AdminToRoleExample example);

    int updateByExample(@Param("record") AdminToRoleKey record, @Param("example") AdminToRoleExample example);
}