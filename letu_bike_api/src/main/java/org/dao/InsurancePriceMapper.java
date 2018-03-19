package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.InsurancePrice;
import org.entity.dto.InsurancePriceExample;

public interface InsurancePriceMapper {
    int countByExample(InsurancePriceExample example);

    int deleteByExample(InsurancePriceExample example);

    int deleteByPrimaryKey(Long inPriceId);

    int insert(InsurancePrice record);

    int insertSelective(InsurancePrice record);

    List<InsurancePrice> selectByExample(InsurancePriceExample example);

    InsurancePrice selectByPrimaryKey(Long inPriceId);

    int updateByExampleSelective(@Param("record") InsurancePrice record, @Param("example") InsurancePriceExample example);

    int updateByExample(@Param("record") InsurancePrice record, @Param("example") InsurancePriceExample example);

    int updateByPrimaryKeySelective(InsurancePrice record);

    int updateByPrimaryKey(InsurancePrice record);
}