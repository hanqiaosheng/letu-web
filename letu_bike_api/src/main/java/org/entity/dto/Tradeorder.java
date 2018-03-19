package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Tradeorder implements Serializable {
    private Long trOrderId;

    private String trOrderTransactionNumber;

    private Long trOrderUserid;

    private Date trOrderCreatetime;

    private BigDecimal trOrderMoney;

    private Date trOrderPaytime;

    private Short trOrderState;

    private String trOrderDescription;

    private Integer trOrderType;

    private String trOrderBuyerLogonId;

    private Integer trOrderOption;

    private Long trOrderRentId;

    private Long trOrderChannelId;

    private Long trOrderInvoiceid;

    private String trOrderRentidstr;
    
    private Long trOrderUserCouponId;

    private static final long serialVersionUID = 1L;

    public Long getTrOrderId() {
        return trOrderId;
    }

    public void setTrOrderId(Long trOrderId) {
        this.trOrderId = trOrderId;
    }

    public String getTrOrderTransactionNumber() {
        return trOrderTransactionNumber;
    }

    public void setTrOrderTransactionNumber(String trOrderTransactionNumber) {
        this.trOrderTransactionNumber = trOrderTransactionNumber == null ? null : trOrderTransactionNumber.trim();
    }

    public Long getTrOrderUserid() {
        return trOrderUserid;
    }

    public void setTrOrderUserid(Long trOrderUserid) {
        this.trOrderUserid = trOrderUserid;
    }

    public Date getTrOrderCreatetime() {
        return trOrderCreatetime;
    }

    public void setTrOrderCreatetime(Date trOrderCreatetime) {
        this.trOrderCreatetime = trOrderCreatetime;
    }

    public BigDecimal getTrOrderMoney() {
        return trOrderMoney;
    }

    public void setTrOrderMoney(BigDecimal trOrderMoney) {
        this.trOrderMoney = trOrderMoney;
    }

    public Date getTrOrderPaytime() {
        return trOrderPaytime;
    }

    public void setTrOrderPaytime(Date trOrderPaytime) {
        this.trOrderPaytime = trOrderPaytime;
    }

    public Short getTrOrderState() {
        return trOrderState;
    }

    public void setTrOrderState(Short trOrderState) {
        this.trOrderState = trOrderState;
    }

    public String getTrOrderDescription() {
        return trOrderDescription;
    }

    public void setTrOrderDescription(String trOrderDescription) {
        this.trOrderDescription = trOrderDescription == null ? null : trOrderDescription.trim();
    }

    public Integer getTrOrderType() {
        return trOrderType;
    }

    public void setTrOrderType(Integer trOrderType) {
        this.trOrderType = trOrderType;
    }

    public String getTrOrderBuyerLogonId() {
        return trOrderBuyerLogonId;
    }

    public void setTrOrderBuyerLogonId(String trOrderBuyerLogonId) {
        this.trOrderBuyerLogonId = trOrderBuyerLogonId == null ? null : trOrderBuyerLogonId.trim();
    }

    public Integer getTrOrderOption() {
        return trOrderOption;
    }

    public void setTrOrderOption(Integer trOrderOption) {
        this.trOrderOption = trOrderOption;
    }

    public Long getTrOrderRentId() {
        return trOrderRentId;
    }

    public void setTrOrderRentId(Long trOrderRentId) {
        this.trOrderRentId = trOrderRentId;
    }

    public Long getTrOrderChannelId() {
        return trOrderChannelId;
    }

    public void setTrOrderChannelId(Long trOrderChannelId) {
        this.trOrderChannelId = trOrderChannelId;
    }

    public Long getTrOrderInvoiceid() {
        return trOrderInvoiceid;
    }

    public void setTrOrderInvoiceid(Long trOrderInvoiceid) {
        this.trOrderInvoiceid = trOrderInvoiceid;
    }

    public String getTrOrderRentidstr() {
        return trOrderRentidstr;
    }

    public void setTrOrderRentidstr(String trOrderRentidstr) {
        this.trOrderRentidstr = trOrderRentidstr == null ? null : trOrderRentidstr.trim();
    }

	public Long getTrOrderUserCouponId() {
		return trOrderUserCouponId;
	}

	public void setTrOrderUserCouponId(Long trOrderUserCouponId) {
		this.trOrderUserCouponId = trOrderUserCouponId;
	}
}