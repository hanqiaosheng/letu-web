package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.BikeRepairMapper;
import org.entity.dto.BikeRepair;
import org.service.cms.write.BikeRepairServiceWrite;
import org.springframework.stereotype.Service;

@Service("bikeRepairServiceWrite")
public class BikeRepairServiceWriteImpl implements BikeRepairServiceWrite {

	@Resource
	BikeRepairMapper bikeRepairMapper;
	
	@Override
	public boolean updateBikeRepair(BikeRepair bikeRepair) throws Exception {
		// TODO Auto-generated method stub
		Integer row = bikeRepairMapper.updateByPrimaryKeySelective(bikeRepair);
		return 1==row;
	}

	
	
}
