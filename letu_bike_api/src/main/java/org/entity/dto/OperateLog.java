package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class OperateLog implements Serializable {
    private Long operateId;

    private Long operateAdminId;

    private Date operateTime;

    private String operateRemark;
    
    private Admin admin;

    private static final long serialVersionUID = 1L;
    
    private String fromTime;//创建时间
    
    private String toTime;
    
    

    public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public Long getOperateId() {
        return operateId;
    }

    public void setOperateId(Long operateId) {
        this.operateId = operateId;
    }

    public Long getOperateAdminId() {
        return operateAdminId;
    }

    public void setOperateAdminId(Long operateAdminId) {
        this.operateAdminId = operateAdminId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark == null ? null : operateRemark.trim();
    }

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
    
}