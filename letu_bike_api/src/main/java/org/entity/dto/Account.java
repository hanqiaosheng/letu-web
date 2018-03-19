package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Account implements Serializable {
    private Long accountId;

    private Long accountUserId;

    private BigDecimal accountTotalmoney;

    private BigDecimal accountDeposit;

    private BigDecimal accountBbin;

    private BigDecimal accountFreezemoney;

    private BigDecimal accountAvailableBalance;

    private Date accountCreatetime;

    private Date accountUpdatetime;

    private Integer accountIsFreeze;

    private Date accountFinalRechargeTime;

    private Date accountFinalConsumeTime;
    
    private Date accountFinalRefundTime;
    
    private BigDecimal accountGiveMoney;
    
    private User aUser;
    
    private String aUserTel;//搜索账户
    
    private List<Long> aUserIds;
    
    private String fromTime;
    
    private String toTime;
    
    private String fromTimeB;
    
    private String toTimeB;

    private static final long serialVersionUID = 1L;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getAccountUserId() {
        return accountUserId;
    }

    public void setAccountUserId(Long accountUserId) {
        this.accountUserId = accountUserId;
    }


    public BigDecimal getAccountTotalmoney() {
		return accountTotalmoney;
	}

	public void setAccountTotalmoney(BigDecimal accountTotalmoney) {
		this.accountTotalmoney = accountTotalmoney;
	}

	public BigDecimal getAccountDeposit() {
		return accountDeposit;
	}

	public void setAccountDeposit(BigDecimal accountDeposit) {
		this.accountDeposit = accountDeposit;
	}

	public BigDecimal getAccountBbin() {
		return accountBbin;
	}

	public void setAccountBbin(BigDecimal accountBbin) {
		this.accountBbin = accountBbin;
	}

	public BigDecimal getAccountFreezemoney() {
		return accountFreezemoney;
	}

	public void setAccountFreezemoney(BigDecimal accountFreezemoney) {
		this.accountFreezemoney = accountFreezemoney;
	}

	public BigDecimal getAccountAvailableBalance() {
		return accountAvailableBalance;
	}

	public void setAccountAvailableBalance(BigDecimal accountAvailableBalance) {
		this.accountAvailableBalance = accountAvailableBalance;
	}

	public Date getAccountCreatetime() {
        return accountCreatetime;
    }

    public void setAccountCreatetime(Date accountCreatetime) {
        this.accountCreatetime = accountCreatetime;
    }

    public Date getAccountUpdatetime() {
        return accountUpdatetime;
    }

    public void setAccountUpdatetime(Date accountUpdatetime) {
        this.accountUpdatetime = accountUpdatetime;
    }

    public Integer getAccountIsFreeze() {
        return accountIsFreeze;
    }

    public void setAccountIsFreeze(Integer accountIsFreeze) {
        this.accountIsFreeze = accountIsFreeze;
    }

    public Date getAccountFinalRechargeTime() {
        return accountFinalRechargeTime;
    }

    public void setAccountFinalRechargeTime(Date accountFinalRechargeTime) {
        this.accountFinalRechargeTime = accountFinalRechargeTime;
    }

    public Date getAccountFinalConsumeTime() {
        return accountFinalConsumeTime;
    }

    public void setAccountFinalConsumeTime(Date accountFinalConsumeTime) {
        this.accountFinalConsumeTime = accountFinalConsumeTime;
    }

	public Date getAccountFinalRefundTime() {
		return accountFinalRefundTime;
	}

	public void setAccountFinalRefundTime(Date accountFinalRefundTime) {
		this.accountFinalRefundTime = accountFinalRefundTime;
	}

	public User getaUser() {
		return aUser;
	}

	public void setaUser(User aUser) {
		this.aUser = aUser;
	}

	public String getaUserTel() {
		return aUserTel;
	}

	public void setaUserTel(String aUserTel) {
		this.aUserTel = aUserTel;
	}

	public List<Long> getaUserIds() {
		return aUserIds;
	}

	public void setaUserIds(List<Long> aUserIds) {
		this.aUserIds = aUserIds;
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

	public BigDecimal getAccountGiveMoney() {
		return accountGiveMoney;
	}

	public void setAccountGiveMoney(BigDecimal accountGiveMoney) {
		this.accountGiveMoney = accountGiveMoney;
	}
	
	
    
}