package org.service.weixin.read;


import org.entity.dto.City;

public interface CityWxServiceRead {

	public City findCityByCode(String cityCode)throws Exception;

}
