package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.BikeMapper;
import org.entity.dto.Bike;
import org.service.weixin.write.BikeWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("bikeWxServiceWrite")
public class BikeWxServiceWriteImpl implements BikeWxServiceWrite {

	@Resource
	BikeMapper bikeMapper;
	
	
	@Override
	public void updateBike(Bike bike) throws Exception {
		// TODO Auto-generated method stub
		bikeMapper.updateByPrimaryKeySelective(bike);
	}


	@Override
	public void updateBikeCanNull(Bike bike) throws Exception {
		// TODO Auto-generated method stub
		bikeMapper.updateByPrimaryKey(bike);
	}

}
