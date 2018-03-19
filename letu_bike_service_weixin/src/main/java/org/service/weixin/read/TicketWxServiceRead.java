package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Ticket;
import org.entity.dto.UserTicket;

public interface TicketWxServiceRead {
	/**
	 * 查找所有Ticket
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<UserTicket> findAllTicket(Integer pageIndex,Integer pageSize,Long userId) throws Exception;
	
	public Ticket findByTicketId(Long ticketId) throws Exception;

	public Integer countAllTicket(Long userId)throws Exception;


}
