package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.CityMapper;
import org.entity.dto.City;
import org.entity.dto.CityExample;
import org.service.cms.read.CityServiceRead;
import org.springframework.stereotype.Service;
@Service("cityServiceRead")
public class CityServiceReadImpl implements CityServiceRead {
	
	@Resource
	CityMapper cityMapper;

	@Override
	public List<City> findAll() throws Exception {
		// TODO Auto-generated method stub
		CityExample example = new CityExample();
		return cityMapper.selectByExample(example);
	}

	@Override
	public City findById(Long cityId) throws Exception {
		// TODO Auto-generated method stub
		return cityMapper.selectByPrimaryKey(cityId);
	}

	@Override
	public List<City> findByProvinceId(Long provinceId) throws Exception {
		// TODO Auto-generated method stub
		CityExample example = new CityExample();
		CityExample.Criteria criteria = example.createCriteria();
		criteria.andCityOfProvinceIdEqualTo(provinceId);
		return cityMapper.selectByExample(example);
	}
	

}
