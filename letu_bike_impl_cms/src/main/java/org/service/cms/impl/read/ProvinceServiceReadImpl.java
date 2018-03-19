package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.ProvinceMapper;
import org.entity.dto.Province;
import org.entity.dto.ProvinceExample;
import org.service.cms.read.ProvinceServiceRead;
import org.springframework.stereotype.Service;
@Service("provinceServiceRead")
public class ProvinceServiceReadImpl implements ProvinceServiceRead {

	@Resource
	ProvinceMapper provinceMapper;
	
	@Override
	public List<Province> findAll() throws Exception {
		// TODO Auto-generated method stub
		ProvinceExample example = new ProvinceExample();
		return provinceMapper.selectByExample(example);
	}

	@Override
	public Province findById(Long provinceId) throws Exception {
		// TODO Auto-generated method stub
		return provinceMapper.selectByPrimaryKey(provinceId);
	}
}
