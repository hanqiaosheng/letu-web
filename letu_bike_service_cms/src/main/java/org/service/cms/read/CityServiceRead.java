package org.service.cms.read;

import java.util.List;

import org.entity.dto.City;

public interface CityServiceRead {
	
	public List<City> findAll()throws Exception; 
	
	public City findById(Long cityId) throws Exception;

	public List<City> findByProvinceId(Long provinceId) throws Exception;
}
