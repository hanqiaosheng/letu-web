package org.entity.dto;

import java.io.Serializable;

public class UserTicket implements Serializable {
    private Long userTicketId;

    private Long utUserId;

    private Long utTicketId;
    
    private Ticket ticket;

    private static final long serialVersionUID = 1L;

    public Long getUserTicketId() {
        return userTicketId;
    }

    public void setUserTicketId(Long userTicketId) {
        this.userTicketId = userTicketId;
    }

    public Long getUtUserId() {
        return utUserId;
    }

    public void setUtUserId(Long utUserId) {
        this.utUserId = utUserId;
    }

    public Long getUtTicketId() {
        return utTicketId;
    }

    public void setUtTicketId(Long utTicketId) {
        this.utTicketId = utTicketId;
    }

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
}