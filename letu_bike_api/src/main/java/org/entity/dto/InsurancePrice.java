package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InsurancePrice implements Serializable {
    private Long inPriceId;

    private String inPriceName;

    private Date inUpdateTime;

    private BigDecimal inPrice;

    private Integer inPriceState;

    private static final long serialVersionUID = 1L;

    public Long getInPriceId() {
        return inPriceId;
    }

    public void setInPriceId(Long inPriceId) {
        this.inPriceId = inPriceId;
    }

    public String getInPriceName() {
        return inPriceName;
    }

    public void setInPriceName(String inPriceName) {
        this.inPriceName = inPriceName == null ? null : inPriceName.trim();
    }

    public Date getInUpdateTime() {
        return inUpdateTime;
    }

    public void setInUpdateTime(Date inUpdateTime) {
        this.inUpdateTime = inUpdateTime;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public Integer getInPriceState() {
        return inPriceState;
    }

    public void setInPriceState(Integer inPriceState) {
        this.inPriceState = inPriceState;
    }
}