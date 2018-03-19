package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.RentPriceMapper;
import org.entity.dto.RentPrice;
import org.entity.dto.RentPriceExample;
import org.service.cms.read.RentPriceServiceRead;
import org.springframework.stereotype.Service;
@Service("rentPriceServiceRead")
public class RentPriceServiceReadImpl implements RentPriceServiceRead {
	@Resource
	RentPriceMapper rentPriceMapper;

	@Override
	public List<RentPrice> findRentPriceByModelsId(Long modelsId) throws Exception {
		// TODO Auto-generated method stub
		RentPriceExample example = new RentPriceExample();
		RentPriceExample.Criteria criteria = example.createCriteria();
		criteria.andRentPriceModelsIdEqualTo(modelsId);
		return rentPriceMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public RentPrice findDelPrice(Long modelsId,Integer type) throws Exception {
		RentPriceExample example = new RentPriceExample();
		RentPriceExample.Criteria criteria = example.createCriteria();
		criteria.andRentPriceModelsIdEqualTo(modelsId);
		criteria.andRentPriceTypeEqualTo(type);
		List<RentPrice> list = rentPriceMapper.selectByExampleWithBLOBs(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
