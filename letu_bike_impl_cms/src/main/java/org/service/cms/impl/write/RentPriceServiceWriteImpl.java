package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.RentPriceMapper;
import org.entity.dto.RentPrice;
import org.service.cms.write.RentPriceServiceWrite;
import org.springframework.stereotype.Service;


@Service("rentPriceServiceWrite")
public class RentPriceServiceWriteImpl implements RentPriceServiceWrite {
	@Resource
	RentPriceMapper rentPriceMapper;

	@Override
	public void addRentPrice(RentPrice rentPrice) throws Exception {
		// TODO Auto-generated method stub
		rentPriceMapper.insertSelective(rentPrice);
	}

	@Override
	public void updateRentPrice(RentPrice price) throws Exception {
		// TODO Auto-generated method stub
		rentPriceMapper.updateByPrimaryKeySelective(price);
	}

	@Override
	public void deleteRentPrice(RentPrice delPrice) throws Exception {
		// TODO Auto-generated method stub
		rentPriceMapper.deleteByPrimaryKey(delPrice.getRentPriceId());
	}
	
}
