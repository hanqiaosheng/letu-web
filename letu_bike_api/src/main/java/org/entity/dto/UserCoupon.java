package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class UserCoupon implements Serializable {
    private Long userToCouponId;

    private Long uuserId;

    private Long ucouponId;

    private Date uCreateTime;

    private Integer ustate;

    private Date uRedeemCodeEndTime;
    
    private CashCoupon cashCoupon;

    private static final long serialVersionUID = 1L;

    public Long getUserToCouponId() {
        return userToCouponId;
    }

    public void setUserToCouponId(Long userToCouponId) {
        this.userToCouponId = userToCouponId;
    }

    public Long getUuserId() {
        return uuserId;
    }

    public void setUuserId(Long uuserId) {
        this.uuserId = uuserId;
    }

    public Long getUcouponId() {
        return ucouponId;
    }

    public void setUcouponId(Long ucouponId) {
        this.ucouponId = ucouponId;
    }

    public Date getuCreateTime() {
        return uCreateTime;
    }

    public void setuCreateTime(Date uCreateTime) {
        this.uCreateTime = uCreateTime;
    }

    public Integer getUstate() {
        return ustate;
    }

    public void setUstate(Integer ustate) {
        this.ustate = ustate;
    }

    public Date getuRedeemCodeEndTime() {
        return uRedeemCodeEndTime;
    }

    public void setuRedeemCodeEndTime(Date uRedeemCodeEndTime) {
        this.uRedeemCodeEndTime = uRedeemCodeEndTime;
    }

	public CashCoupon getCashCoupon() {
		return cashCoupon;
	}

	public void setCashCoupon(CashCoupon cashCoupon) {
		this.cashCoupon = cashCoupon;
	}
}