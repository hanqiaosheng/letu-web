package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RechargeRecord implements Serializable {
    private Long rechargeId;

    private Long rechargeAccountId;

    private BigDecimal rechargeMoney;

    private Integer rechargePayType;

    private Integer rechargeState;

    private Date rechargeTime;

    private String rechargeOrderId;

    private BigDecimal rechargeAvailableBalance;

    private Integer rechargeType;
    
    private String rechargeOutTradeNo;
    
    private Long rechargeChannelId;
    
    private static final long serialVersionUID = 1L;

    public Long getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Long rechargeId) {
        this.rechargeId = rechargeId;
    }

    public Long getRechargeAccountId() {
        return rechargeAccountId;
    }

    public void setRechargeAccountId(Long rechargeAccountId) {
        this.rechargeAccountId = rechargeAccountId;
    }

    public BigDecimal getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(BigDecimal rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public Integer getRechargePayType() {
        return rechargePayType;
    }

    public void setRechargePayType(Integer rechargePayType) {
        this.rechargePayType = rechargePayType;
    }

    public Integer getRechargeState() {
        return rechargeState;
    }

    public void setRechargeState(Integer rechargeState) {
        this.rechargeState = rechargeState;
    }

    public Date getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public String getRechargeOrderId() {
        return rechargeOrderId;
    }

    public void setRechargeOrderId(String rechargeOrderId) {
        this.rechargeOrderId = rechargeOrderId == null ? null : rechargeOrderId.trim();
    }

    public BigDecimal getRechargeAvailableBalance() {
        return rechargeAvailableBalance;
    }

    public void setRechargeAvailableBalance(BigDecimal rechargeAvailableBalance) {
        this.rechargeAvailableBalance = rechargeAvailableBalance;
    }

    public Integer getRechargeType() {
        return rechargeType;
    }

    public void setRechargeType(Integer rechargeType) {
        this.rechargeType = rechargeType;
    }

	public String getRechargeOutTradeNo() {
		return rechargeOutTradeNo;
	}

	public void setRechargeOutTradeNo(String rechargeOutTradeNo) {
		this.rechargeOutTradeNo = rechargeOutTradeNo;
	}

	public Long getRechargeChannelId() {
		return rechargeChannelId;
	}

	public void setRechargeChannelId(Long rechargeChannelId) {
		this.rechargeChannelId = rechargeChannelId;
	}
}