package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class DefriendRecord implements Serializable {
    private Long defriendId;

    private Long defriendAdminId;

    private Date defriendTime;

    private Long defriendRegainAdminId;

    private Date defriendRegainTime;

    private Long defriendUserId;
    
    private String defriendReason;

    private static final long serialVersionUID = 1L;

    public Long getDefriendId() {
        return defriendId;
    }

    public void setDefriendId(Long defriendId) {
        this.defriendId = defriendId;
    }

    public Long getDefriendAdminId() {
        return defriendAdminId;
    }

    public void setDefriendAdminId(Long defriendAdminId) {
        this.defriendAdminId = defriendAdminId;
    }

    public Date getDefriendTime() {
        return defriendTime;
    }

    public void setDefriendTime(Date defriendTime) {
        this.defriendTime = defriendTime;
    }

    public Long getDefriendRegainAdminId() {
        return defriendRegainAdminId;
    }

    public void setDefriendRegainAdminId(Long defriendRegainAdminId) {
        this.defriendRegainAdminId = defriendRegainAdminId;
    }

    public Date getDefriendRegainTime() {
        return defriendRegainTime;
    }

    public void setDefriendRegainTime(Date defriendRegainTime) {
        this.defriendRegainTime = defriendRegainTime;
    }

    public Long getDefriendUserId() {
        return defriendUserId;
    }

    public void setDefriendUserId(Long defriendUserId) {
        this.defriendUserId = defriendUserId;
    }

	public String getDefriendReason() {
		return defriendReason;
	}

	public void setDefriendReason(String defriendReason) {
		this.defriendReason = defriendReason;
	}
}