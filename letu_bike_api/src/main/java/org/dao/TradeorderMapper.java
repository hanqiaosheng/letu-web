package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.Tradeorder;
import org.entity.dto.TradeorderExample;

public interface TradeorderMapper {
    int countByExample(TradeorderExample example);

    int deleteByExample(TradeorderExample example);

    int deleteByPrimaryKey(Long trOrderId);

    int insert(Tradeorder record);

    int insertSelective(Tradeorder record);

    List<Tradeorder> selectByExample(TradeorderExample example);

    Tradeorder selectByPrimaryKey(Long trOrderId);

    int updateByExampleSelective(@Param("record") Tradeorder record, @Param("example") TradeorderExample example);

    int updateByExample(@Param("record") Tradeorder record, @Param("example") TradeorderExample example);

    int updateByPrimaryKeySelective(Tradeorder record);

    int updateByPrimaryKey(Tradeorder record);
}