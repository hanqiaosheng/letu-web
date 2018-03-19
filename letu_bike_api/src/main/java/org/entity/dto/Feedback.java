package org.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Feedback implements Serializable {
    private Long feedbackId;

    private Long bikeId;

    private Long userId;

    private Long adminId;

    private Long feedbackTypeId;

    private Date feedbackTime;

    private String feedbackContent;

    private Integer feedbackState;

    private Date feedbackDealtime;

    private String feedbackPicaddress;
    
    private String feedbackRefuseReason;
    
    private String fUserTel;//搜索
    
    private String fUserName;
    
    private Date fromTime;
    
    private Date toTime;
    
    private List<Long> fUserIds;
    
    private String fDataDetVal;
    
    private Bike bike;
    
    private String[] feedbackPics;//

    private static final long serialVersionUID = 1L;
    
    private String feedbackRemark;
    
    private User user;

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getBikeId() {
        return bikeId;
    }

    public void setBikeId(Long bikeId) {
        this.bikeId = bikeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getFeedbackTypeId() {
        return feedbackTypeId;
    }

    public void setFeedbackTypeId(Long feedbackTypeId) {
        this.feedbackTypeId = feedbackTypeId;
    }

    public Date getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Date feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent == null ? null : feedbackContent.trim();
    }

    public Integer getFeedbackState() {
        return feedbackState;
    }

    public void setFeedbackState(Integer feedbackState) {
        this.feedbackState = feedbackState;
    }

    public Date getFeedbackDealtime() {
        return feedbackDealtime;
    }

    public void setFeedbackDealtime(Date feedbackDealtime) {
        this.feedbackDealtime = feedbackDealtime;
    }

    public String getFeedbackPicaddress() {
        return feedbackPicaddress;
    }

    public void setFeedbackPicaddress(String feedbackPicaddress) {
        this.feedbackPicaddress = feedbackPicaddress == null ? null : feedbackPicaddress.trim();
    }

	public String getFeedbackRefuseReason() {
		return feedbackRefuseReason;
	}

	public void setFeedbackRefuseReason(String feedbackRefuseReason) {
		this.feedbackRefuseReason = feedbackRefuseReason;
	}

	public String getfUserTel() {
		return fUserTel;
	}

	public void setfUserTel(String fUserTel) {
		this.fUserTel = fUserTel;
	}

	public String getfUserName() {
		return fUserName;
	}

	public void setfUserName(String fUserName) {
		this.fUserName = fUserName;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public List<Long> getfUserIds() {
		return fUserIds;
	}

	public void setfUserIds(List<Long> fUserIds) {
		this.fUserIds = fUserIds;
	}

	public String getfDataDetVal() {
		return fDataDetVal;
	}

	public void setfDataDetVal(String fDataDetVal) {
		this.fDataDetVal = fDataDetVal;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public String[] getFeedbackPics() {
		return feedbackPics;
	}

	public void setFeedbackPics(String[] feedbackPics) {
		this.feedbackPics = feedbackPics;
	}

	public String getFeedbackRemark() {
		return feedbackRemark;
	}

	public void setFeedbackRemark(String feedbackRemark) {
		this.feedbackRemark = feedbackRemark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}