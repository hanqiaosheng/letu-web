package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.HotWord;
import org.entity.dto.HotWordExample;

public interface HotWordMapper {
    int countByExample(HotWordExample example);

    int deleteByExample(HotWordExample example);

    int deleteByPrimaryKey(Long hotWordId);

    int insert(HotWord record);

    int insertSelective(HotWord record);

    List<HotWord> selectByExample(HotWordExample example);

    HotWord selectByPrimaryKey(Long hotWordId);

    int updateByExampleSelective(@Param("record") HotWord record, @Param("example") HotWordExample example);

    int updateByExample(@Param("record") HotWord record, @Param("example") HotWordExample example);

    int updateByPrimaryKeySelective(HotWord record);

    int updateByPrimaryKey(HotWord record);
}