package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.Channel;
import org.entity.dto.ChannelExample;

public interface ChannelMapper {
    int countByExample(ChannelExample example);

    int deleteByExample(ChannelExample example);

    int deleteByPrimaryKey(Long channelId);

    int insert(Channel record);

    int insertSelective(Channel record);

    List<Channel> selectByExampleWithBLOBs(ChannelExample example);

    List<Channel> selectByExample(ChannelExample example);

    Channel selectByPrimaryKey(Long channelId);

    int updateByExampleSelective(@Param("record") Channel record, @Param("example") ChannelExample example);

    int updateByExampleWithBLOBs(@Param("record") Channel record, @Param("example") ChannelExample example);

    int updateByExample(@Param("record") Channel record, @Param("example") ChannelExample example);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKeyWithBLOBs(Channel record);

    int updateByPrimaryKey(Channel record);
}