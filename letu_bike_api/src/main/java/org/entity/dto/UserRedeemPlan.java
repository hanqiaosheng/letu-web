package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class UserRedeemPlan implements Serializable {
    private Long userRedeemPlanId;

    private Long urpUserId;

    private Long urpRedeemPlanId;

    private Date urpCreateTime;
    
    private User user;
    
    private RedeemPlan redeemPlan;

    private static final long serialVersionUID = 1L;

    public Long getUserRedeemPlanId() {
        return userRedeemPlanId;
    }

    public void setUserRedeemPlanId(Long userRedeemPlanId) {
        this.userRedeemPlanId = userRedeemPlanId;
    }

    public Long getUrpUserId() {
        return urpUserId;
    }

    public void setUrpUserId(Long urpUserId) {
        this.urpUserId = urpUserId;
    }

    public Long getUrpRedeemPlanId() {
        return urpRedeemPlanId;
    }

    public void setUrpRedeemPlanId(Long urpRedeemPlanId) {
        this.urpRedeemPlanId = urpRedeemPlanId;
    }

    public Date getUrpCreateTime() {
        return urpCreateTime;
    }

    public void setUrpCreateTime(Date urpCreateTime) {
        this.urpCreateTime = urpCreateTime;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RedeemPlan getRedeemPlan() {
		return redeemPlan;
	}

	public void setRedeemPlan(RedeemPlan redeemPlan) {
		this.redeemPlan = redeemPlan;
	}
}