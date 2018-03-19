package org.service.cms.impl.write;


import javax.annotation.Resource;

import org.dao.BikeRentInfoMapper;
import org.entity.dto.BikeRentInfo;
import org.service.cms.write.BikeRentInfoServiceWrite;
import org.springframework.stereotype.Service;
@Service("bikeRentInfoServiceWrite")
public class BikeRentInfoServiceWriteImpl implements BikeRentInfoServiceWrite  {
	
	@Resource 
	BikeRentInfoMapper bikeRentInfoMapper;


	@Override
	public int deleteBikeRentInfoById(BikeRentInfo bikeRentInfo) throws Exception {
		bikeRentInfo.setRentIsdel(1);
		return bikeRentInfoMapper.updateByPrimaryKeySelective(bikeRentInfo);
	}


	@Override
	public void updateBikeRentInfo(BikeRentInfo bikeRentInfo) throws Exception {
		// TODO Auto-generated method stub
		bikeRentInfoMapper.updateByPrimaryKeySelective(bikeRentInfo);
		
	}


	@Override
	public long addRentInfo(BikeRentInfo bikeRentInfo) throws Exception {
		// TODO Auto-generated method stub
		return bikeRentInfoMapper.insertSelective(bikeRentInfo);
	}

	

	

}
