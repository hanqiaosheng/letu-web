package org.entity.dto;

import java.io.Serializable;

public class City implements Serializable {
    private Long cityId;

    private String cityName;

    private Long cityOfProvinceId;

    private String cityCode;

    private Double cityCenterLng;

    private Double cityCenterLat;

    private static final long serialVersionUID = 1L;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Long getCityOfProvinceId() {
        return cityOfProvinceId;
    }

    public void setCityOfProvinceId(Long cityOfProvinceId) {
        this.cityOfProvinceId = cityOfProvinceId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }

    public Double getCityCenterLng() {
        return cityCenterLng;
    }

    public void setCityCenterLng(Double cityCenterLng) {
        this.cityCenterLng = cityCenterLng;
    }

    public Double getCityCenterLat() {
        return cityCenterLat;
    }

    public void setCityCenterLat(Double cityCenterLat) {
        this.cityCenterLat = cityCenterLat;
    }
}