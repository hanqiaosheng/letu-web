package org.entity.dto;

import java.io.Serializable;
import java.util.Date;

public class Banner implements Serializable {
    private Long bannerId;

    private String bannerName;

    private String bannerImageUrl;

    private Integer bannerState;

    private Integer bannerTopNum;

    private Date bannerCreateTime;

    private Date bannerUpdateTime;

    private Long bannerCityId;
    
    private String provinceName;//省份名称
    
    private String cityName;//城市名称
    
    private City city;
    
    private Integer bannerIsAll;

    private static final long serialVersionUID = 1L;

    public Long getBannerId() {
        return bannerId;
    }

    public void setBannerId(Long bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName == null ? null : bannerName.trim();
    }

    public String getBannerImageUrl() {
        return bannerImageUrl;
    }

    public void setBannerImageUrl(String bannerImageUrl) {
        this.bannerImageUrl = bannerImageUrl == null ? null : bannerImageUrl.trim();
    }

    public Integer getBannerState() {
        return bannerState;
    }

    public void setBannerState(Integer bannerState) {
        this.bannerState = bannerState;
    }

    public Integer getBannerTopNum() {
        return bannerTopNum;
    }

    public void setBannerTopNum(Integer bannerTopNum) {
        this.bannerTopNum = bannerTopNum;
    }

    public Date getBannerCreateTime() {
        return bannerCreateTime;
    }

    public void setBannerCreateTime(Date bannerCreateTime) {
        this.bannerCreateTime = bannerCreateTime;
    }

    public Date getBannerUpdateTime() {
        return bannerUpdateTime;
    }

    public void setBannerUpdateTime(Date bannerUpdateTime) {
        this.bannerUpdateTime = bannerUpdateTime;
    }

    public Long getBannerCityId() {
        return bannerCityId;
    }

    public void setBannerCityId(Long bannerCityId) {
        this.bannerCityId = bannerCityId;
    }

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
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

	public Integer getBannerIsAll() {
		return bannerIsAll;
	}

	public void setBannerIsAll(Integer bannerIsAll) {
		this.bannerIsAll = bannerIsAll;
	}
}