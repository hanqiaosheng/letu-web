package org.entity.dto;

import java.io.Serializable;
import java.util.List;

import net.sf.json.JSONObject;

public class RentPrice implements Serializable {
    private Long rentPriceId;

    private Integer rentPriceType;

    private Integer rentFreeTime;

    private Integer rentPriceOption;

    private Long rentPriceModelsId;

    private String rentPrice;
    
    private List<JSONObject> priceList;//租赁费用列表
    
    private Double lastPrice;//最后时间段费用
    
    private Double rentPriceMax;

    private static final long serialVersionUID = 1L;

    public Long getRentPriceId() {
        return rentPriceId;
    }

    public void setRentPriceId(Long rentPriceId) {
        this.rentPriceId = rentPriceId;
    }

    public Integer getRentPriceType() {
        return rentPriceType;
    }

    public void setRentPriceType(Integer rentPriceType) {
        this.rentPriceType = rentPriceType;
    }

    public Integer getRentFreeTime() {
        return rentFreeTime;
    }

    public void setRentFreeTime(Integer rentFreeTime) {
        this.rentFreeTime = rentFreeTime;
    }

    public Integer getRentPriceOption() {
        return rentPriceOption;
    }

    public void setRentPriceOption(Integer rentPriceOption) {
        this.rentPriceOption = rentPriceOption;
    }

    public Long getRentPriceModelsId() {
        return rentPriceModelsId;
    }

    public void setRentPriceModelsId(Long rentPriceModelsId) {
        this.rentPriceModelsId = rentPriceModelsId;
    }

    public String getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice == null ? null : rentPrice.trim();
    }

	public List<JSONObject> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<JSONObject> priceList) {
		this.priceList = priceList;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Double getRentPriceMax() {
		return rentPriceMax;
	}

	public void setRentPriceMax(Double rentPriceMax) {
		this.rentPriceMax = rentPriceMax;
	}
}