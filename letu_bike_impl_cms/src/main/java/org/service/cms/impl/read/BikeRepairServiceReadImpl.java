package org.service.cms.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeRepairMapper;
import org.entity.dto.BikeRepair;
import org.entity.dto.BikeRepairExample;
import org.service.cms.read.BikeRepairServiceRead;
import org.springframework.stereotype.Service;

@Service("bikeRepairServiceRead")
public class BikeRepairServiceReadImpl implements BikeRepairServiceRead  {

	@Resource
	BikeRepairMapper bikeRepairMapper;
	
	@Override
	public List<BikeRepair> findAllBikeRepair() throws Exception {
		BikeRepairExample bikeRepairExample = new BikeRepairExample();
		List<BikeRepair> bikeRepairList = bikeRepairMapper.selectByExample(bikeRepairExample);
		return bikeRepairList;
	}

	@Override
	public BikeRepair findBikeRepairById(Long managerId) throws Exception {
		BikeRepair bikeRepair = bikeRepairMapper.selectByPrimaryKey(managerId);
		return bikeRepair;
	}
	
	

}
