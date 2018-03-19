package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Insurance implements Serializable {
    private Long insuranceId;

    private String insuranceOddNum;

    private Long insuranceUserId;

    private String insuranceContent;

    private BigDecimal insuranceMoney;

    private Integer insuranceState;
    
    private Long insuranceAdminId;

    private Date insuranceDealTime;

    private Date insuranceApplyTime;

    private String insuranceResult;
    
    private String insuranceAccidentImgs;

    private String insuranceDetailImgs;

    private String insuranceCompensate;

    private String insuranceIdentityContact;

    private String insuranceResultProve;
    
    private List<Long> iUserIds;
    
    private String fromTime;
    
    private String toTime;
    
    private String iUserTel;
    
    private String iUserName;
    
    private String iAdminName;
    
    private String[] accidentImgs;
    
    private String[] detailImgs;
    
    private String[] identityContact;

    

    private static final long serialVersionUID = 1L;

    

	

	

    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsuranceOddNum() {
        return insuranceOddNum;
    }

    public void setInsuranceOddNum(String insuranceOddNum) {
        this.insuranceOddNum = insuranceOddNum == null ? null : insuranceOddNum.trim();
    }

    public Long getInsuranceUserId() {
        return insuranceUserId;
    }

    public void setInsuranceUserId(Long insuranceUserId) {
        this.insuranceUserId = insuranceUserId;
    }

    public String getInsuranceContent() {
        return insuranceContent;
    }

    public void setInsuranceContent(String insuranceContent) {
        this.insuranceContent = insuranceContent == null ? null : insuranceContent.trim();
    }

    public BigDecimal getInsuranceMoney() {
        return insuranceMoney;
    }

    public void setInsuranceMoney(BigDecimal insuranceMoney) {
        this.insuranceMoney = insuranceMoney;
    }

    public Integer getInsuranceState() {
        return insuranceState;
    }

    public void setInsuranceState(Integer insuranceState) {
        this.insuranceState = insuranceState;
    }

    public Long getInsuranceAdminId() {
        return insuranceAdminId;
    }

    public void setInsuranceAdminId(Long insuranceAdminId) {
        this.insuranceAdminId = insuranceAdminId;
    }

    public Date getInsuranceDealTime() {
        return insuranceDealTime;
    }

    public void setInsuranceDealTime(Date insuranceDealTime) {
        this.insuranceDealTime = insuranceDealTime;
    }

    public Date getInsuranceApplyTime() {
        return insuranceApplyTime;
    }

    public void setInsuranceApplyTime(Date insuranceApplyTime) {
        this.insuranceApplyTime = insuranceApplyTime;
    }

    public String getInsuranceResult() {
        return insuranceResult;
    }

    public void setInsuranceResult(String insuranceResult) {
        this.insuranceResult = insuranceResult == null ? null : insuranceResult.trim();
    }

    public String getInsuranceAccidentImgs() {
        return insuranceAccidentImgs;
    }

    public void setInsuranceAccidentImgs(String insuranceAccidentImgs) {
        this.insuranceAccidentImgs = insuranceAccidentImgs == null ? null : insuranceAccidentImgs.trim();
    }

    public String getInsuranceDetailImgs() {
        return insuranceDetailImgs;
    }

    public void setInsuranceDetailImgs(String insuranceDetailImgs) {
        this.insuranceDetailImgs = insuranceDetailImgs == null ? null : insuranceDetailImgs.trim();
    }

    public String getInsuranceCompensate() {
        return insuranceCompensate;
    }

    public void setInsuranceCompensate(String insuranceCompensate) {
        this.insuranceCompensate = insuranceCompensate == null ? null : insuranceCompensate.trim();
    }

    public String getInsuranceIdentityContact() {
        return insuranceIdentityContact;
    }

    public void setInsuranceIdentityContact(String insuranceIdentityContact) {
        this.insuranceIdentityContact = insuranceIdentityContact == null ? null : insuranceIdentityContact.trim();
    }

    public String getInsuranceResultProve() {
        return insuranceResultProve;
    }

    public void setInsuranceResultProve(String insuranceResultProve) {
        this.insuranceResultProve = insuranceResultProve == null ? null : insuranceResultProve.trim();
    }
    public List<Long> getiUserIds() {
		return iUserIds;
	}

	public void setiUserIds(List<Long> iUserIds) {
		this.iUserIds = iUserIds;
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

	public String getiUserTel() {
		return iUserTel;
	}

	public void setiUserTel(String iUserTel) {
		this.iUserTel = iUserTel;
	}

	public String getiUserName() {
		return iUserName;
	}

	public void setiUserName(String iUserName) {
		this.iUserName = iUserName;
	}

	public String getiAdminName() {
		return iAdminName;
	}

	public void setiAdminName(String iAdminName) {
		this.iAdminName = iAdminName;
	}

	public String[] getAccidentImgs() {
		return accidentImgs;
	}

	public void setAccidentImgs(String[] accidentImgs) {
		this.accidentImgs = accidentImgs;
	}

	public String[] getDetailImgs() {
		return detailImgs;
	}

	public void setDetailImgs(String[] detailImgs) {
		this.detailImgs = detailImgs;
	}

	public String[] getIdentityContact() {
		return identityContact;
	}

	public void setIdentityContact(String[] identityContact) {
		this.identityContact = identityContact;
	}
	
}