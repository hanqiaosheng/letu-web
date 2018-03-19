package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.RentPrice;
import org.entity.dto.RentPriceExample;

public interface RentPriceMapper {
    int countByExample(RentPriceExample example);

    int deleteByExample(RentPriceExample example);

    int deleteByPrimaryKey(Long rentPriceId);

    int insert(RentPrice record);

    int insertSelective(RentPrice record);

    List<RentPrice> selectByExampleWithBLOBs(RentPriceExample example);

    List<RentPrice> selectByExample(RentPriceExample example);

    RentPrice selectByPrimaryKey(Long rentPriceId);

    int updateByExampleSelective(@Param("record") RentPrice record, @Param("example") RentPriceExample example);

    int updateByExampleWithBLOBs(@Param("record") RentPrice record, @Param("example") RentPriceExample example);

    int updateByExample(@Param("record") RentPrice record, @Param("example") RentPriceExample example);

    int updateByPrimaryKeySelective(RentPrice record);

    int updateByPrimaryKeyWithBLOBs(RentPrice record);

    int updateByPrimaryKey(RentPrice record);
}