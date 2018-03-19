package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.BikeRentInfoMapper;
import org.entity.dto.BikeRentInfo;
import org.service.weixin.write.BikeRentInfoWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("bikeRentInfoWxServiceWrite")
public class BikeRentInfoWxServiceWriteImpl implements BikeRentInfoWxServiceWrite {

	@Resource
	BikeRentInfoMapper bikeRentInfoMapper;

	@Override
	public void updateRentInfo(BikeRentInfo bikeRentInfo) throws Exception {
		// TODO Auto-generated method stub
		bikeRentInfoMapper.updateByPrimaryKeyWithBLOBs(bikeRentInfo);
	}

	@Override
	public Long addRentInfo(BikeRentInfo bikeRentInfo) throws Exception {
		// TODO Auto-generated method stub
		bikeRentInfoMapper.insertSelective(bikeRentInfo);
		return bikeRentInfo.getRentInfoId();
		
	}
	
	
	
}
