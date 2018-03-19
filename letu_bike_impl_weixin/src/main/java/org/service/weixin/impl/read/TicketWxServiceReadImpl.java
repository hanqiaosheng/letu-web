package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.TicketMapper;
import org.dao.UserTicketMapper;
import org.entity.dto.Ticket;
import org.entity.dto.UserTicket;
import org.entity.dto.UserTicketExample;
import org.service.weixin.read.TicketWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("ticketWxServiceRead")
public class TicketWxServiceReadImpl implements TicketWxServiceRead{

	@Resource
	TicketMapper ticketMapper;
	@Resource
	UserTicketMapper userTicketMapper;

	@Override
	public List<UserTicket> findAllTicket(Integer pageIndex,Integer pageSize,Long userId) throws Exception {
		UserTicketExample example = new UserTicketExample();
		UserTicketExample.Criteria criteria = example.createCriteria();
		criteria.andUtUserIdEqualTo(userId);
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return userTicketMapper.selectByExample(example);
	}

	@Override
	public Ticket findByTicketId(Long ticketId) throws Exception {
		// TODO Auto-generated method stub
		return ticketMapper.selectByPrimaryKey(ticketId);
	}

	@Override
	public Integer countAllTicket(Long userId) throws Exception {
		UserTicketExample example = new UserTicketExample();
		UserTicketExample.Criteria criteria = example.createCriteria();
		criteria.andUtUserIdEqualTo(userId);
		return userTicketMapper.countByExample(example);
	}

}
