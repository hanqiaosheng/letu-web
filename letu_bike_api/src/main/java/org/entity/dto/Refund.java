package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Refund implements Serializable {
    private Long refundId;

    private Long refundAccountId;

    private Long refundAdminId;

    private BigDecimal refundMoney;

    private Date refundOperatetime;

    private Integer refundState;

    private Date refundCreatetime;

    private Integer refundType;

    private String refundCode;

    private String refundOrderId;
    
    private Admin admin;
    
    private Account account;
    
    private String refundRefuseReason;
    
    private Integer refundSource;
    
    private static final long serialVersionUID = 1L;

    public Long getRefundId() {
        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public Long getRefundAccountId() {
        return refundAccountId;
    }

    public void setRefundAccountId(Long refundAccountId) {
        this.refundAccountId = refundAccountId;
    }

    public Long getRefundAdminId() {
        return refundAdminId;
    }

    public void setRefundAdminId(Long refundAdminId) {
        this.refundAdminId = refundAdminId;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Date getRefundOperatetime() {
        return refundOperatetime;
    }

    public void setRefundOperatetime(Date refundOperatetime) {
        this.refundOperatetime = refundOperatetime;
    }
    
    public Integer getRefundState() {
		return refundState;
	}

	public void setRefundState(Integer refundState) {
		this.refundState = refundState;
	}

	public Date getRefundCreatetime() {
        return refundCreatetime;
    }

    public void setRefundCreatetime(Date refundCreatetime) {
        this.refundCreatetime = refundCreatetime;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode == null ? null : refundCode.trim();
    }

    public String getRefundOrderId() {
        return refundOrderId;
    }

    public void setRefundOrderId(String refundOrderId) {
        this.refundOrderId = refundOrderId;
    }

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getRefundRefuseReason() {
		return refundRefuseReason;
	}

	public void setRefundRefuseReason(String refundRefuseReason) {
		this.refundRefuseReason = refundRefuseReason;
	}

	public Integer getRefundSource() {
		return refundSource;
	}

	public void setRefundSource(Integer refundSource) {
		this.refundSource = refundSource;
	}

}