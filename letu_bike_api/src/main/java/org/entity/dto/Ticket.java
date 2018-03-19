package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
    private Long ticketId;

    private String ticketName;

    private Date ticketValidityStartTime;

    private Date ticketValidityEndTime;

    private Date ticketCreateTime;

    private Integer ticketState;

    private String ticketRedeemCode;

    private String ticketRedeemImage;

    private Long ticketChannelId;

    private static final long serialVersionUID = 1L;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName == null ? null : ticketName.trim();
    }

    public Date getTicketValidityStartTime() {
        return ticketValidityStartTime;
    }

    public void setTicketValidityStartTime(Date ticketValidityStartTime) {
        this.ticketValidityStartTime = ticketValidityStartTime;
    }

    public Date getTicketValidityEndTime() {
        return ticketValidityEndTime;
    }

    public void setTicketValidityEndTime(Date ticketValidityEndTime) {
        this.ticketValidityEndTime = ticketValidityEndTime;
    }

    public Date getTicketCreateTime() {
        return ticketCreateTime;
    }

    public void setTicketCreateTime(Date ticketCreateTime) {
        this.ticketCreateTime = ticketCreateTime;
    }

    public Integer getTicketState() {
        return ticketState;
    }

    public void setTicketState(Integer ticketState) {
        this.ticketState = ticketState;
    }

    public String getTicketRedeemCode() {
        return ticketRedeemCode;
    }

    public void setTicketRedeemCode(String ticketRedeemCode) {
        this.ticketRedeemCode = ticketRedeemCode == null ? null : ticketRedeemCode.trim();
    }

    public String getTicketRedeemImage() {
        return ticketRedeemImage;
    }

    public void setTicketRedeemImage(String ticketRedeemImage) {
        this.ticketRedeemImage = ticketRedeemImage == null ? null : ticketRedeemImage.trim();
    }

    public Long getTicketChannelId() {
        return ticketChannelId;
    }

    public void setTicketChannelId(Long ticketChannelId) {
        this.ticketChannelId = ticketChannelId;
    }
}