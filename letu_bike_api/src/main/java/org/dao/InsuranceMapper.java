package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.Insurance;
import org.entity.dto.InsuranceExample;

public interface InsuranceMapper {
    int countByExample(InsuranceExample example);

    int deleteByExample(InsuranceExample example);

    int deleteByPrimaryKey(Long insuranceId);

    int insert(Insurance record);

    int insertSelective(Insurance record);

    List<Insurance> selectByExample(InsuranceExample example);

    Insurance selectByPrimaryKey(Long insuranceId);

    int updateByExampleSelective(@Param("record") Insurance record, @Param("example") InsuranceExample example);

    int updateByExample(@Param("record") Insurance record, @Param("example") InsuranceExample example);

    int updateByPrimaryKeySelective(Insurance record);

    int updateByPrimaryKey(Insurance record);
}