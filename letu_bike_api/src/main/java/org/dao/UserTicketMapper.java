package org.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.entity.dto.UserTicket;
import org.entity.dto.UserTicketExample;

public interface UserTicketMapper {
    int countByExample(UserTicketExample example);

    int deleteByExample(UserTicketExample example);

    int deleteByPrimaryKey(Long userTicketId);

    int insert(UserTicket record);

    int insertSelective(UserTicket record);

    List<UserTicket> selectByExample(UserTicketExample example);

    UserTicket selectByPrimaryKey(Long userTicketId);

    int updateByExampleSelective(@Param("record") UserTicket record, @Param("example") UserTicketExample example);

    int updateByExample(@Param("record") UserTicket record, @Param("example") UserTicketExample example);

    int updateByPrimaryKeySelective(UserTicket record);

    int updateByPrimaryKey(UserTicket record);
}