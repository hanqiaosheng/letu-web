package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class Activity implements Serializable {
    private Long activityId;

    private String activityName;

    private String activityInstruction;

    private Integer activityTopnum;

    private String activityUrl;

    private String activityImage;

    private Date activityCreateTime;

    private Integer activityState;

    private Long activityCityId;
    
    private String cityName;
    
    private City city;

    private static final long serialVersionUID = 1L;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getActivityInstruction() {
        return activityInstruction;
    }

    public void setActivityInstruction(String activityInstruction) {
        this.activityInstruction = activityInstruction == null ? null : activityInstruction.trim();
    }

    public Integer getActivityTopnum() {
        return activityTopnum;
    }

    public void setActivityTopnum(Integer activityTopnum) {
        this.activityTopnum = activityTopnum;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl == null ? null : activityUrl.trim();
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage == null ? null : activityImage.trim();
    }

    public Date getActivityCreateTime() {
        return activityCreateTime;
    }

    public void setActivityCreateTime(Date activityCreateTime) {
        this.activityCreateTime = activityCreateTime;
    }

    public Integer getActivityState() {
        return activityState;
    }

    public void setActivityState(Integer activityState) {
        this.activityState = activityState;
    }

    public Long getActivityCityId() {
        return activityCityId;
    }

    public void setActivityCityId(Long activityCityId) {
        this.activityCityId = activityCityId;
    }

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}