package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DiscountPackage implements Serializable {
    private Long discountPackageId;

    private String discountPackageName;

    private BigDecimal discountPackagePrice;

    private BigDecimal discountPackagePreferentialPrice;

    private String discountPackageTag;

    private Integer discountPackageState;

    private Date discountPackageCreateTime;

    private Integer discountPackageBuyLimit;

    private Long discountPackageChannelId;

    private Long discountPackageScenicSpotId;

    private Date discountPackageUpdateTime;

    private Integer discountPackageTopNum;

    private String discountPackageDetail;
    
    private String scenicName;

    private static final long serialVersionUID = 1L;

    public Long getDiscountPackageId() {
        return discountPackageId;
    }

    public void setDiscountPackageId(Long discountPackageId) {
        this.discountPackageId = discountPackageId;
    }

    public String getDiscountPackageName() {
        return discountPackageName;
    }

    public void setDiscountPackageName(String discountPackageName) {
        this.discountPackageName = discountPackageName == null ? null : discountPackageName.trim();
    }

    public BigDecimal getDiscountPackagePrice() {
        return discountPackagePrice;
    }

    public void setDiscountPackagePrice(BigDecimal discountPackagePrice) {
        this.discountPackagePrice = discountPackagePrice;
    }

    public BigDecimal getDiscountPackagePreferentialPrice() {
        return discountPackagePreferentialPrice;
    }

    public void setDiscountPackagePreferentialPrice(BigDecimal discountPackagePreferentialPrice) {
        this.discountPackagePreferentialPrice = discountPackagePreferentialPrice;
    }

    public String getDiscountPackageTag() {
        return discountPackageTag;
    }

    public void setDiscountPackageTag(String discountPackageTag) {
        this.discountPackageTag = discountPackageTag == null ? null : discountPackageTag.trim();
    }

    public Integer getDiscountPackageState() {
        return discountPackageState;
    }

    public void setDiscountPackageState(Integer discountPackageState) {
        this.discountPackageState = discountPackageState;
    }

    public Date getDiscountPackageCreateTime() {
        return discountPackageCreateTime;
    }

    public void setDiscountPackageCreateTime(Date discountPackageCreateTime) {
        this.discountPackageCreateTime = discountPackageCreateTime;
    }

    public Integer getDiscountPackageBuyLimit() {
        return discountPackageBuyLimit;
    }

    public void setDiscountPackageBuyLimit(Integer discountPackageBuyLimit) {
        this.discountPackageBuyLimit = discountPackageBuyLimit;
    }

    public Long getDiscountPackageChannelId() {
        return discountPackageChannelId;
    }

    public void setDiscountPackageChannelId(Long discountPackageChannelId) {
        this.discountPackageChannelId = discountPackageChannelId;
    }

    public Long getDiscountPackageScenicSpotId() {
        return discountPackageScenicSpotId;
    }

    public void setDiscountPackageScenicSpotId(Long discountPackageScenicSpotId) {
        this.discountPackageScenicSpotId = discountPackageScenicSpotId;
    }

    public Date getDiscountPackageUpdateTime() {
        return discountPackageUpdateTime;
    }

    public void setDiscountPackageUpdateTime(Date discountPackageUpdateTime) {
        this.discountPackageUpdateTime = discountPackageUpdateTime;
    }

    public Integer getDiscountPackageTopNum() {
        return discountPackageTopNum;
    }

    public void setDiscountPackageTopNum(Integer discountPackageTopNum) {
        this.discountPackageTopNum = discountPackageTopNum;
    }

    public String getDiscountPackageDetail() {
        return discountPackageDetail;
    }

    public void setDiscountPackageDetail(String discountPackageDetail) {
        this.discountPackageDetail = discountPackageDetail == null ? null : discountPackageDetail.trim();
    }

	public String getScenicName() {
		return scenicName;
	}

	public void setScenicName(String scenicName) {
		this.scenicName = scenicName;
	}
}