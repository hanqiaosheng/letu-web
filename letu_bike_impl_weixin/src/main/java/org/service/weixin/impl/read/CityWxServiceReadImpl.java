package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.CityMapper;
import org.entity.dto.City;
import org.entity.dto.CityExample;
import org.service.weixin.read.CityWxServiceRead;
import org.springframework.stereotype.Service;

@Service("cityWxServiceRead")
public class CityWxServiceReadImpl implements CityWxServiceRead{

	@Resource
	CityMapper cityMapper;

	@Override
	public City findCityByCode(String cityCode) throws Exception {
		// TODO Auto-generated method stub
		CityExample example = new CityExample();
		CityExample.Criteria criteria = example.createCriteria();
		criteria.andCityCodeEqualTo(cityCode);
		List<City> cityList = cityMapper.selectByExample(example);
		if(cityList.size()>0){
			return cityList.get(0);
		}
		return null;
	}

	
}
