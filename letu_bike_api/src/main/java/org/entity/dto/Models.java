package org.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class Models implements Serializable {
    private Long modelsId;

    private String modelsCode;

    private String modelsName;

    private BigDecimal modelsDeposit;

    private String modelsContent;

    private Integer modelsState;

    private Long modelsChannelId;

    private Integer modelsRentLimit;

    private Integer modelsIsfixedPoint;

    private String modelsFixedReturn;
    
    private Channel channel;
    
    private String modelsFixedReturnName;
    
    private List<FixedReturn> fixedReturnList;
    
    private Integer modelsFreeTime;
    
    private Integer modelsRentType;
    
    private Long modelsInpriceId;
    
    private RentPrice modelRentPrice;
    
    private List<RentPrice> modelRentPriceList;
    
    private static final long serialVersionUID = 1L;

    public Long getModelsId() {
        return modelsId;
    }

    public void setModelsId(Long modelsId) {
        this.modelsId = modelsId;
    }

    public String getModelsCode() {
        return modelsCode;
    }

    public void setModelsCode(String modelsCode) {
        this.modelsCode = modelsCode == null ? null : modelsCode.trim();
    }

    public String getModelsName() {
        return modelsName;
    }

    public void setModelsName(String modelsName) {
        this.modelsName = modelsName == null ? null : modelsName.trim();
    }

    public BigDecimal getModelsDeposit() {
        return modelsDeposit;
    }

    public void setModelsDeposit(BigDecimal modelsDeposit) {
        this.modelsDeposit = modelsDeposit;
    }

    public String getModelsContent() {
        return modelsContent;
    }

    public void setModelsContent(String modelsContent) {
        this.modelsContent = modelsContent == null ? null : modelsContent.trim();
    }

    public Integer getModelsState() {
        return modelsState;
    }

    public void setModelsState(Integer modelsState) {
        this.modelsState = modelsState;
    }

    public Long getModelsChannelId() {
        return modelsChannelId;
    }

    public void setModelsChannelId(Long modelsChannelId) {
        this.modelsChannelId = modelsChannelId;
    }

    public Integer getModelsRentLimit() {
		return modelsRentLimit;
	}

	public void setModelsRentLimit(Integer modelsRentLimit) {
		this.modelsRentLimit = modelsRentLimit;
	}

	public Integer getModelsIsfixedPoint() {
        return modelsIsfixedPoint;
    }

    public void setModelsIsfixedPoint(Integer modelsIsfixedPoint) {
        this.modelsIsfixedPoint = modelsIsfixedPoint;
    }

    public String getModelsFixedReturn() {
        return modelsFixedReturn;
    }

    public void setModelsFixedReturn(String modelsFixedReturn) {
        this.modelsFixedReturn = modelsFixedReturn == null ? null : modelsFixedReturn.trim();
    }

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getModelsFixedReturnName() {
		return modelsFixedReturnName;
	}

	public void setModelsFixedReturnName(String modelsFixedReturnName) {
		this.modelsFixedReturnName = modelsFixedReturnName;
	}

	public List<FixedReturn> getFixedReturnList() {
		return fixedReturnList;
	}

	public void setFixedReturnList(List<FixedReturn> fixedReturnList) {
		this.fixedReturnList = fixedReturnList;
	}

	public Integer getModelsFreeTime() {
		return modelsFreeTime;
	}

	public void setModelsFreeTime(Integer modelsFreeTime) {
		this.modelsFreeTime = modelsFreeTime;
	}

	public Integer getModelsRentType() {
		return modelsRentType;
	}

	public void setModelsRentType(Integer modelsRentType) {
		this.modelsRentType = modelsRentType;
	}

	public Long getModelsInpriceId() {
		return modelsInpriceId;
	}

	public void setModelsInpriceId(Long modelsInpriceId) {
		this.modelsInpriceId = modelsInpriceId;
	}

	public RentPrice getModelRentPrice() {
		return modelRentPrice;
	}

	public void setModelRentPrice(RentPrice modelRentPrice) {
		this.modelRentPrice = modelRentPrice;
	}

	public List<RentPrice> getModelRentPriceList() {
		return modelRentPriceList;
	}

	public void setModelRentPriceList(List<RentPrice> modelRentPriceList) {
		this.modelRentPriceList = modelRentPriceList;
	}
}