package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Invoice implements Serializable {
    private Long invoiceId;

    private String invoiceName;

    private Long invoiceUserId;

    private String invoiceContent;

    private BigDecimal invoiceMoney;

    private String invoiceMark;

    private String invoiceTaxpayerNumber;

    private String invoiceAddrePhone;

    private String invoiceBankAccount;

    private String invoiceEmail;

    private String invoiceReceiveName;

    private String invoiceReceiveTel;

    private String invoiceReceiveCity;

    private String invoiceReceiveAddress;

    private Date invoiceApplicationTime;

    private Integer invoiceType;

    private Integer invoiceState;

    private Long invoiceDeliverTypeId;
    
    private User user;
    
    private String fromTime;
    
    private String toTime;
    
    private String invoiceUrl;
    
    private String invoiceTravelUrl;
    
    private static final long serialVersionUID = 1L;

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName == null ? null : invoiceName.trim();
    }

    public Long getInvoiceUserId() {
        return invoiceUserId;
    }

    public void setInvoiceUserId(Long invoiceUserId) {
        this.invoiceUserId = invoiceUserId;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent == null ? null : invoiceContent.trim();
    }

    public BigDecimal getInvoiceMoney() {
        return invoiceMoney;
    }

    public void setInvoiceMoney(BigDecimal invoiceMoney) {
        this.invoiceMoney = invoiceMoney;
    }

    public String getInvoiceMark() {
        return invoiceMark;
    }

    public void setInvoiceMark(String invoiceMark) {
        this.invoiceMark = invoiceMark == null ? null : invoiceMark.trim();
    }

    public String getInvoiceTaxpayerNumber() {
        return invoiceTaxpayerNumber;
    }

    public void setInvoiceTaxpayerNumber(String invoiceTaxpayerNumber) {
        this.invoiceTaxpayerNumber = invoiceTaxpayerNumber == null ? null : invoiceTaxpayerNumber.trim();
    }

    public String getInvoiceAddrePhone() {
        return invoiceAddrePhone;
    }

    public void setInvoiceAddrePhone(String invoiceAddrePhone) {
        this.invoiceAddrePhone = invoiceAddrePhone == null ? null : invoiceAddrePhone.trim();
    }

    public String getInvoiceBankAccount() {
        return invoiceBankAccount;
    }

    public void setInvoiceBankAccount(String invoiceBankAccount) {
        this.invoiceBankAccount = invoiceBankAccount == null ? null : invoiceBankAccount.trim();
    }

    public String getInvoiceEmail() {
        return invoiceEmail;
    }

    public void setInvoiceEmail(String invoiceEmail) {
        this.invoiceEmail = invoiceEmail == null ? null : invoiceEmail.trim();
    }

    public String getInvoiceReceiveName() {
        return invoiceReceiveName;
    }

    public void setInvoiceReceiveName(String invoiceReceiveName) {
        this.invoiceReceiveName = invoiceReceiveName == null ? null : invoiceReceiveName.trim();
    }

    public String getInvoiceReceiveTel() {
        return invoiceReceiveTel;
    }

    public void setInvoiceReceiveTel(String invoiceReceiveTel) {
        this.invoiceReceiveTel = invoiceReceiveTel == null ? null : invoiceReceiveTel.trim();
    }

    public String getInvoiceReceiveCity() {
        return invoiceReceiveCity;
    }

    public void setInvoiceReceiveCity(String invoiceReceiveCity) {
        this.invoiceReceiveCity = invoiceReceiveCity == null ? null : invoiceReceiveCity.trim();
    }

    public String getInvoiceReceiveAddress() {
        return invoiceReceiveAddress;
    }

    public void setInvoiceReceiveAddress(String invoiceReceiveAddress) {
        this.invoiceReceiveAddress = invoiceReceiveAddress == null ? null : invoiceReceiveAddress.trim();
    }

    public Date getInvoiceApplicationTime() {
        return invoiceApplicationTime;
    }

    public void setInvoiceApplicationTime(Date invoiceApplicationTime) {
        this.invoiceApplicationTime = invoiceApplicationTime;
    }

    public Integer getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Integer invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getInvoiceState() {
        return invoiceState;
    }

    public void setInvoiceState(Integer invoiceState) {
        this.invoiceState = invoiceState;
    }

    public Long getInvoiceDeliverTypeId() {
        return invoiceDeliverTypeId;
    }

    public void setInvoiceDeliverTypeId(Long invoiceDeliverTypeId) {
        this.invoiceDeliverTypeId = invoiceDeliverTypeId;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getInvoiceUrl() {
		return invoiceUrl;
	}

	public void setInvoiceUrl(String invoiceUrl) {
		this.invoiceUrl = invoiceUrl;
	}

	public String getInvoiceTravelUrl() {
		return invoiceTravelUrl;
	}

	public void setInvoiceTravelUrl(String invoiceTravelUrl) {
		this.invoiceTravelUrl = invoiceTravelUrl;
	}
}