package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MoneyLog implements Serializable {
    private Long moneyLogId;

    private Long moneyLogAccountId;

    private Integer moneyLogStreamType;

    private BigDecimal moneyLogOpreateMoney;

    private String fromTime;//搜索时间
    
    private String toTime;//
    
    private Account account;   
    
    private BigDecimal moneyLogDeposit;

    private Date moneyLogCreateTime;

    private String moneyLogRemark;

    private String moneyLogIp;

    private Long moneyLogOpreateId;

    private Integer moneyLogState;

    private String moneyLogOrder;
    
    private String moneyLogOutTrade;
    
    private Long moneyLogRefundOpreate;
    
    private Integer moneyLogRefundState;
    
    private Integer moneyLogIsvillager;
    
    private Long moneyLogChannelId;
    
    private String channelName;
    
    public Integer getMoneyLogIsvillager() {
		return moneyLogIsvillager;
	}

	public void setMoneyLogIsvillager(Integer moneyLogIsvillager) {
		this.moneyLogIsvillager = moneyLogIsvillager;
	}

	public Integer getMoneyLogRefundState() {
		return moneyLogRefundState;
	}

	public void setMoneyLogRefundState(Integer moneyLogRefundState) {
		this.moneyLogRefundState = moneyLogRefundState;
	}

	private Admin admin;

    private static final long serialVersionUID = 1L;

    public Long getMoneyLogId() {
        return moneyLogId;
    }

    public void setMoneyLogId(Long moneyLogId) {
        this.moneyLogId = moneyLogId;
    }

    public Long getMoneyLogAccountId() {
        return moneyLogAccountId;
    }

    public void setMoneyLogAccountId(Long moneyLogAccountId) {
        this.moneyLogAccountId = moneyLogAccountId;
    }

    public Integer getMoneyLogStreamType() {
        return moneyLogStreamType;
    }

    public void setMoneyLogStreamType(Integer moneyLogStreamType) {
        this.moneyLogStreamType = moneyLogStreamType;
    }

    public BigDecimal getMoneyLogOpreateMoney() {
        return moneyLogOpreateMoney;
    }

    public void setMoneyLogOpreateMoney(BigDecimal moneyLogOpreateMoney) {
        this.moneyLogOpreateMoney = moneyLogOpreateMoney;
    }

    public BigDecimal getMoneyLogDeposit() {
        return moneyLogDeposit;
    }

    public void setMoneyLogDeposit(BigDecimal moneyLogDeposit) {
        this.moneyLogDeposit = moneyLogDeposit;
    }

    public Date getMoneyLogCreateTime() {
        return moneyLogCreateTime;
    }

    public void setMoneyLogCreateTime(Date moneyLogCreateTime) {
        this.moneyLogCreateTime = moneyLogCreateTime;
    }

    public String getMoneyLogRemark() {
        return moneyLogRemark;
    }

    public void setMoneyLogRemark(String moneyLogRemark) {
        this.moneyLogRemark = moneyLogRemark == null ? null : moneyLogRemark.trim();
    }

    public String getMoneyLogIp() {
        return moneyLogIp;
    }

    public void setMoneyLogIp(String moneyLogIp) {
        this.moneyLogIp = moneyLogIp == null ? null : moneyLogIp.trim();
    }

    public Long getMoneyLogOpreateId() {
        return moneyLogOpreateId;
    }

    public void setMoneyLogOpreateId(Long moneyLogOpreateId) {
        this.moneyLogOpreateId = moneyLogOpreateId;
    }

    public Integer getMoneyLogState() {
        return moneyLogState;
    }

    public void setMoneyLogState(Integer moneyLogState) {
        this.moneyLogState = moneyLogState;
    }

    public String getMoneyLogOrder() {
        return moneyLogOrder;
    }

    public void setMoneyLogOrder(String moneyLogOrder) {
        this.moneyLogOrder = moneyLogOrder == null ? null : moneyLogOrder.trim();
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
	}	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getMoneyLogOutTrade() {
		return moneyLogOutTrade;
	}

	public void setMoneyLogOutTrade(String moneyLogOutTrade) {
		this.moneyLogOutTrade = moneyLogOutTrade;
	}

	public Long getMoneyLogRefundOpreate() {
		return moneyLogRefundOpreate;
	}

	public void setMoneyLogRefundOpreate(Long moneyLogRefundOpreate) {
		this.moneyLogRefundOpreate = moneyLogRefundOpreate;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Long getMoneyLogChannelId() {
		return moneyLogChannelId;
	}

	public void setMoneyLogChannelId(Long moneyLogChannelId) {
		this.moneyLogChannelId = moneyLogChannelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
}