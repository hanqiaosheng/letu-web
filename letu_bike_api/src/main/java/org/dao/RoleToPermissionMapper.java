package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.RoleToPermissionExample;
import org.entity.dto.RoleToPermissionKey;

public interface RoleToPermissionMapper {
    int countByExample(RoleToPermissionExample example);

    int deleteByExample(RoleToPermissionExample example);

    int deleteByPrimaryKey(RoleToPermissionKey key);

    int insert(RoleToPermissionKey record);

    int insertSelective(RoleToPermissionKey record);

    List<RoleToPermissionKey> selectByExample(RoleToPermissionExample example);

    int updateByExampleSelective(@Param("record") RoleToPermissionKey record, @Param("example") RoleToPermissionExample example);

    int updateByExample(@Param("record") RoleToPermissionKey record, @Param("example") RoleToPermissionExample example);
}