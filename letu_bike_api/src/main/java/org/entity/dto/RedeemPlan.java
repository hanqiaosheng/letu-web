package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class RedeemPlan implements Serializable {
    private Long planId;

    private String planName;

    private Date planCreateTime;

    private String planRedeemCode;

    private Integer planRedeemNums;

    private Long planCouponPlanId;

    private Date planOnlineTime;

    private Date planOfflineTime;

    private Integer planState;

    private Integer planSurplusNums;
    
    private CouponPlan couponPlan;
    
    private Date planReserveOnlineTime;

    private Date planReserveOfflineTime;
    
    private Integer planType;

    private static final long serialVersionUID = 1L;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public Date getPlanCreateTime() {
        return planCreateTime;
    }

    public void setPlanCreateTime(Date planCreateTime) {
        this.planCreateTime = planCreateTime;
    }

    public String getPlanRedeemCode() {
        return planRedeemCode;
    }

    public void setPlanRedeemCode(String planRedeemCode) {
        this.planRedeemCode = planRedeemCode == null ? null : planRedeemCode.trim();
    }

    public Integer getPlanRedeemNums() {
        return planRedeemNums;
    }

    public void setPlanRedeemNums(Integer planRedeemNums) {
        this.planRedeemNums = planRedeemNums;
    }

    public Long getPlanCouponPlanId() {
		return planCouponPlanId;
	}

	public void setPlanCouponPlanId(Long planCouponPlanId) {
		this.planCouponPlanId = planCouponPlanId;
	}

	public Date getPlanOnlineTime() {
        return planOnlineTime;
    }

    public void setPlanOnlineTime(Date planOnlineTime) {
        this.planOnlineTime = planOnlineTime;
    }

    public Date getPlanOfflineTime() {
        return planOfflineTime;
    }

    public void setPlanOfflineTime(Date planOfflineTime) {
        this.planOfflineTime = planOfflineTime;
    }

    public Integer getPlanState() {
        return planState;
    }

    public void setPlanState(Integer planState) {
        this.planState = planState;
    }

    public Integer getPlanSurplusNums() {
        return planSurplusNums;
    }

    public void setPlanSurplusNums(Integer planSurplusNums) {
        this.planSurplusNums = planSurplusNums;
    }

	public CouponPlan getCouponPlan() {
		return couponPlan;
	}

	public void setCouponPlan(CouponPlan couponPlan) {
		this.couponPlan = couponPlan;
	}

	public Date getPlanReserveOnlineTime() {
		return planReserveOnlineTime;
	}

	public void setPlanReserveOnlineTime(Date planReserveOnlineTime) {
		this.planReserveOnlineTime = planReserveOnlineTime;
	}

	public Date getPlanReserveOfflineTime() {
		return planReserveOfflineTime;
	}

	public void setPlanReserveOfflineTime(Date planReserveOfflineTime) {
		this.planReserveOfflineTime = planReserveOfflineTime;
	}

	public Integer getPlanType() {
		return planType;
	}

	public void setPlanType(Integer planType) {
		this.planType = planType;
	}
	
	
}