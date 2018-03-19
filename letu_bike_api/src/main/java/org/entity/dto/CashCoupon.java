package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CashCoupon implements Serializable {
    private Long couponId;

    private String couponName;

    private BigDecimal couponMoney;

    private Date couponStartTime;

    private Date couponCreateTime;

    private Integer couponIsValidity;

    private Integer couponValidityTime;

    private Integer couponState;

    private Date couponEndTime;

    private Integer couponIsCondition;

    private BigDecimal couponStartMoney;
    
    private String couponStartTimeStr;
    
    private String couponEndTimeStr;
    
    private Long userToCouponId;

    private static final long serialVersionUID = 1L;

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName == null ? null : couponName.trim();
    }

    public BigDecimal getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(BigDecimal couponMoney) {
        this.couponMoney = couponMoney;
    }

    public Date getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(Date couponStartTime) {
        this.couponStartTime = couponStartTime;
    }

    public Date getCouponCreateTime() {
        return couponCreateTime;
    }

    public void setCouponCreateTime(Date couponCreateTime) {
        this.couponCreateTime = couponCreateTime;
    }

    public Integer getCouponIsValidity() {
        return couponIsValidity;
    }

    public void setCouponIsValidity(Integer couponIsValidity) {
        this.couponIsValidity = couponIsValidity;
    }

    public Integer getCouponValidityTime() {
        return couponValidityTime;
    }

    public void setCouponValidityTime(Integer couponValidityTime) {
        this.couponValidityTime = couponValidityTime;
    }

    public Integer getCouponState() {
        return couponState;
    }

    public void setCouponState(Integer couponState) {
        this.couponState = couponState;
    }

    public Date getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(Date couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public Integer getCouponIsCondition() {
        return couponIsCondition;
    }

    public void setCouponIsCondition(Integer couponIsCondition) {
        this.couponIsCondition = couponIsCondition;
    }

    public BigDecimal getCouponStartMoney() {
        return couponStartMoney;
    }

    public void setCouponStartMoney(BigDecimal couponStartMoney) {
        this.couponStartMoney = couponStartMoney;
    }

	public String getCouponStartTimeStr() {
		return couponStartTimeStr;
	}

	public void setCouponStartTimeStr(String couponStartTimeStr) {
		this.couponStartTimeStr = couponStartTimeStr;
	}

	public String getCouponEndTimeStr() {
		return couponEndTimeStr;
	}

	public void setCouponEndTimeStr(String couponEndTimeStr) {
		this.couponEndTimeStr = couponEndTimeStr;
	}

	public Long getUserToCouponId() {
		return userToCouponId;
	}

	public void setUserToCouponId(Long userToCouponId) {
		this.userToCouponId = userToCouponId;
	}
}