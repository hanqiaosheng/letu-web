package org.entity.dto;

import java.io.Serializable;

public class LatLng implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long fixedReturnId;

	private Double lat;

	private Double lng;
	
	private Long bikeId;
	
	private String fixedReturnContent;

	public Double getLat() {
		return lat;
	}


	public Long getFixedReturnId() {
		return fixedReturnId;
	}


	public void setFixedReturnId(Long fixedReturnId) {
		this.fixedReturnId = fixedReturnId;
	}


	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}


	public String getFixedReturnContent() {
		return fixedReturnContent;
	}


	public void setFixedReturnContent(String fixedReturnContent) {
		this.fixedReturnContent = fixedReturnContent;
	}


	public Long getBikeId() {
		return bikeId;
	}


	public void setBikeId(Long bikeId) {
		this.bikeId = bikeId;
	}

}