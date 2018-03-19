package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.Admin;
import org.entity.dto.AdminExample;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Long adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Long adminId);
    
    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    //连表
    List<Admin> selectUnionByExample(AdminExample example);
    //查admin
    Admin selectUnionRoleByPrimaryKey(Long adminId);
    //openid查询
    Admin selectByOpenId(String openId);
}