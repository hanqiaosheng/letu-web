package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Long messageId;

    private Long messageUserId;

    private Long messageAdminId;

    private String templetCode;

    private String messageContent;

    private Integer messageIsdel;

    private Date messageSendTime;

    private String messageTitle;

    private Long messageInsurance;

    private static final long serialVersionUID = 1L;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageUserId() {
        return messageUserId;
    }

    public void setMessageUserId(Long messageUserId) {
        this.messageUserId = messageUserId;
    }

    public Long getMessageAdminId() {
        return messageAdminId;
    }

    public void setMessageAdminId(Long messageAdminId) {
        this.messageAdminId = messageAdminId;
    }

    public String getTempletCode() {
        return templetCode;
    }

    public void setTempletCode(String templetCode) {
        this.templetCode = templetCode == null ? null : templetCode.trim();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Integer getMessageIsdel() {
        return messageIsdel;
    }

    public void setMessageIsdel(Integer messageIsdel) {
        this.messageIsdel = messageIsdel;
    }

    public Date getMessageSendTime() {
        return messageSendTime;
    }

    public void setMessageSendTime(Date messageSendTime) {
        this.messageSendTime = messageSendTime;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle == null ? null : messageTitle.trim();
    }

    public Long getMessageInsurance() {
        return messageInsurance;
    }

    public void setMessageInsurance(Long messageInsurance) {
        this.messageInsurance = messageInsurance;
    }
}