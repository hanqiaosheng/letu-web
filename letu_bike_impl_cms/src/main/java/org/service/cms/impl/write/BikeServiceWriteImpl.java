package org.service.cms.impl.write;


import javax.annotation.Resource;

import org.dao.BikeMapper;
import org.entity.dto.Bike;
import org.service.cms.write.BikeServiceWrite;
import org.springframework.stereotype.Service;
@Service("bikeServiceWrite")
public class BikeServiceWriteImpl implements BikeServiceWrite  {
	
	@Resource 
	BikeMapper bikeMapper;


	@Override
	public int deleteBikeById(Bike bike) throws Exception {
		bike.setBikeDel(1);//删除标志1为删除
		return  bikeMapper.updateByPrimaryKeySelective(bike);
	}

	@Override
	public Long addBike(Bike bike) throws Exception {
		 bikeMapper.insertSelective(bike);
		 return bike.getBikeId();
	}

	@Override
	public int editBike(Bike bike) throws Exception {
		// TODO Auto-generated method stub
		return bikeMapper.updateByPrimaryKeySelective(bike);
	}

	@Override
	public int updateBike(Bike bike) throws Exception {
		// TODO Auto-generated method stub
		return bikeMapper.updateByPrimaryKey(bike);
	}

	
	

}
