package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class SysMsg implements Serializable {
    private Long sysMsgId;

    private Long adminId;

    private String sysMsgContent;

    private Date sysMsgCreatetime;

    private Short sysMsgIsonline;

    private Date sysMsgOnlinetime;

    private Date sysMsgOfflinetime;

    private String sysMsgTitle;
    
    private String operationAdminName;
    
    private String fromTime;//创建时间
    
    private String toTime;
    
    private String fromTimeB;//上线时间
    
    private String toTimeB;
    
    private String fromTimeC;//下线时间
    
    private String toTimeC;

    private static final long serialVersionUID = 1L;

    public Long getSysMsgId() {
        return sysMsgId;
    }

    public void setSysMsgId(Long sysMsgId) {
        this.sysMsgId = sysMsgId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getSysMsgContent() {
        return sysMsgContent;
    }

    public void setSysMsgContent(String sysMsgContent) {
        this.sysMsgContent = sysMsgContent == null ? null : sysMsgContent.trim();
    }

    public Date getSysMsgCreatetime() {
        return sysMsgCreatetime;
    }

    public void setSysMsgCreatetime(Date sysMsgCreatetime) {
        this.sysMsgCreatetime = sysMsgCreatetime;
    }

    public Short getSysMsgIsonline() {
        return sysMsgIsonline;
    }

    public void setSysMsgIsonline(Short sysMsgIsonline) {
        this.sysMsgIsonline = sysMsgIsonline;
    }

    public Date getSysMsgOnlinetime() {
        return sysMsgOnlinetime;
    }

    public void setSysMsgOnlinetime(Date sysMsgOnlinetime) {
        this.sysMsgOnlinetime = sysMsgOnlinetime;
    }

    public Date getSysMsgOfflinetime() {
        return sysMsgOfflinetime;
    }

    public void setSysMsgOfflinetime(Date sysMsgOfflinetime) {
        this.sysMsgOfflinetime = sysMsgOfflinetime;
    }

    public String getSysMsgTitle() {
        return sysMsgTitle;
    }

    public void setSysMsgTitle(String sysMsgTitle) {
        this.sysMsgTitle = sysMsgTitle == null ? null : sysMsgTitle.trim();
    }

	public String getOperationAdminName() {
		return operationAdminName;
	}

	public void setOperationAdminName(String operationAdminName) {
		this.operationAdminName = operationAdminName;
	}

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

	public String getFromTimeB() {
		return fromTimeB;
	}

	public void setFromTimeB(String fromTimeB) {
		this.fromTimeB = fromTimeB;
	}

	public String getToTimeB() {
		return toTimeB;
	}

	public void setToTimeB(String toTimeB) {
		this.toTimeB = toTimeB;
	}

	public String getFromTimeC() {
		return fromTimeC;
	}

	public void setFromTimeC(String fromTimeC) {
		this.fromTimeC = fromTimeC;
	}

	public String getToTimeC() {
		return toTimeC;
	}

	public void setToTimeC(String toTimeC) {
		this.toTimeC = toTimeC;
	}
    
}