package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class ScenicSpot implements Serializable {
    private Long scenicSpotId;

    private String scenicSpotName;

    private String scenicSpotImage;

    private Long scenicSpotCityId;

    private String scenicSpotTag;

    private Double scenicSpotDistance;

    private Integer scenicSpotState;

    private Date scenicSpotCreateTime;

    private Integer scenicSpotTopNum;

    private Integer scenicSpotType;

    private Double scenicSpotLng;

    private Double scenicSpotLat;

    private Long scenicSpotChannelId;

    private Date scenicSpotUpdateTime;
    
    private String scenicSpotDetailImage;
    
    private String scenicSpotMsg;

    private String scenicSpotContent;

    private String scenicSpotPolicy;

    private String scenicSpotOpenTime;
    
    private String cityName;
    
    private String provinceName;
    
    private Double ticketPrice;
    
    private City city;
    
    private Integer scenicSpotIsAll;

    private static final long serialVersionUID = 1L;

    public Long getScenicSpotId() {
        return scenicSpotId;
    }

    public void setScenicSpotId(Long scenicSpotId) {
        this.scenicSpotId = scenicSpotId;
    }

    public String getScenicSpotName() {
        return scenicSpotName;
    }

    public void setScenicSpotName(String scenicSpotName) {
        this.scenicSpotName = scenicSpotName == null ? null : scenicSpotName.trim();
    }

    public String getScenicSpotImage() {
        return scenicSpotImage;
    }

    public void setScenicSpotImage(String scenicSpotImage) {
        this.scenicSpotImage = scenicSpotImage == null ? null : scenicSpotImage.trim();
    }

    public Long getScenicSpotCityId() {
        return scenicSpotCityId;
    }

    public void setScenicSpotCityId(Long scenicSpotCityId) {
        this.scenicSpotCityId = scenicSpotCityId;
    }

    public String getScenicSpotTag() {
        return scenicSpotTag;
    }

    public void setScenicSpotTag(String scenicSpotTag) {
        this.scenicSpotTag = scenicSpotTag == null ? null : scenicSpotTag.trim();
    }

    public Double getScenicSpotDistance() {
        return scenicSpotDistance;
    }

    public void setScenicSpotDistance(Double scenicSpotDistance) {
        this.scenicSpotDistance = scenicSpotDistance;
    }

    public Integer getScenicSpotState() {
        return scenicSpotState;
    }

    public void setScenicSpotState(Integer scenicSpotState) {
        this.scenicSpotState = scenicSpotState;
    }

    public Date getScenicSpotCreateTime() {
        return scenicSpotCreateTime;
    }

    public void setScenicSpotCreateTime(Date scenicSpotCreateTime) {
        this.scenicSpotCreateTime = scenicSpotCreateTime;
    }

    public Integer getScenicSpotTopNum() {
        return scenicSpotTopNum;
    }

    public void setScenicSpotTopNum(Integer scenicSpotTopNum) {
        this.scenicSpotTopNum = scenicSpotTopNum;
    }

    public Integer getScenicSpotType() {
        return scenicSpotType;
    }

    public void setScenicSpotType(Integer scenicSpotType) {
        this.scenicSpotType = scenicSpotType;
    }

    public Double getScenicSpotLng() {
        return scenicSpotLng;
    }

    public void setScenicSpotLng(Double scenicSpotLng) {
        this.scenicSpotLng = scenicSpotLng;
    }

    public Double getScenicSpotLat() {
        return scenicSpotLat;
    }

    public void setScenicSpotLat(Double scenicSpotLat) {
        this.scenicSpotLat = scenicSpotLat;
    }

    public Long getScenicSpotChannelId() {
        return scenicSpotChannelId;
    }

    public void setScenicSpotChannelId(Long scenicSpotChannelId) {
        this.scenicSpotChannelId = scenicSpotChannelId;
    }

    public Date getScenicSpotUpdateTime() {
        return scenicSpotUpdateTime;
    }

    public void setScenicSpotUpdateTime(Date scenicSpotUpdateTime) {
        this.scenicSpotUpdateTime = scenicSpotUpdateTime;
    }

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public String getScenicSpotMsg() {
		return scenicSpotMsg;
	}

	public void setScenicSpotMsg(String scenicSpotMsg) {
		this.scenicSpotMsg = scenicSpotMsg;
	}

	public String getScenicSpotContent() {
		return scenicSpotContent;
	}

	public void setScenicSpotContent(String scenicSpotContent) {
		this.scenicSpotContent = scenicSpotContent;
	}

	public String getScenicSpotPolicy() {
		return scenicSpotPolicy;
	}

	public void setScenicSpotPolicy(String scenicSpotPolicy) {
		this.scenicSpotPolicy = scenicSpotPolicy;
	}

	public String getScenicSpotOpenTime() {
		return scenicSpotOpenTime;
	}

	public void setScenicSpotOpenTime(String scenicSpotOpenTime) {
		this.scenicSpotOpenTime = scenicSpotOpenTime;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getScenicSpotDetailImage() {
		return scenicSpotDetailImage;
	}

	public void setScenicSpotDetailImage(String scenicSpotDetailImage) {
		this.scenicSpotDetailImage = scenicSpotDetailImage;
	}

	public Integer getScenicSpotIsAll() {
		return scenicSpotIsAll;
	}

	public void setScenicSpotIsAll(Integer scenicSpotIsAll) {
		this.scenicSpotIsAll = scenicSpotIsAll;
	}
}