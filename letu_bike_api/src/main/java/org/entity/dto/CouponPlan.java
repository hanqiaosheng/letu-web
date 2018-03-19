package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class CouponPlan implements Serializable {
    private Long couponPlanId;

    private String couponPlanName;

    private Date couponPlanCreateTime;

    private Integer couponPlanState;

    private String couponPlanJson;
    
    private Integer num;
    
    private CashCoupon cashCoupon;

    private static final long serialVersionUID = 1L;

    public Long getCouponPlanId() {
        return couponPlanId;
    }

    public void setCouponPlanId(Long couponPlanId) {
        this.couponPlanId = couponPlanId;
    }

    public String getCouponPlanName() {
        return couponPlanName;
    }

    public void setCouponPlanName(String couponPlanName) {
        this.couponPlanName = couponPlanName == null ? null : couponPlanName.trim();
    }

    public Date getCouponPlanCreateTime() {
        return couponPlanCreateTime;
    }

    public void setCouponPlanCreateTime(Date couponPlanCreateTime) {
        this.couponPlanCreateTime = couponPlanCreateTime;
    }

    public Integer getCouponPlanState() {
        return couponPlanState;
    }

    public void setCouponPlanState(Integer couponPlanState) {
        this.couponPlanState = couponPlanState;
    }

    public String getCouponPlanJson() {
        return couponPlanJson;
    }

    public void setCouponPlanJson(String couponPlanJson) {
        this.couponPlanJson = couponPlanJson == null ? null : couponPlanJson.trim();
    }

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public CashCoupon getCashCoupon() {
		return cashCoupon;
	}

	public void setCashCoupon(CashCoupon cashCoupon) {
		this.cashCoupon = cashCoupon;
	}
}