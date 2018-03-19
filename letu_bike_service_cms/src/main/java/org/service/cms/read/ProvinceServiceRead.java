package org.service.cms.read;

import java.util.List;

import org.entity.dto.Province;

public interface ProvinceServiceRead {
	
	public List<Province> findAll()throws Exception;

	public Province findById(Long provinceId)throws Exception;

}
