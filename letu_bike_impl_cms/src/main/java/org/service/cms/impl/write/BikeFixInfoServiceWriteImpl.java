package org.service.cms.impl.write;



import javax.annotation.Resource;

import org.dao.BikeFixInfoMapper;
import org.entity.dto.BikeFixInfo;
import org.service.cms.write.BikeFixInfoServiceWrite;
import org.springframework.stereotype.Service;
@Service("bikeServiceRead")
public class BikeFixInfoServiceWriteImpl implements BikeFixInfoServiceWrite  {
	
	@Resource 
	BikeFixInfoMapper bikeFixInfoMapper;

	@Override
	public int deleteBikeFixInfoById(BikeFixInfo bikeFixInfo) throws Exception {
		bikeFixInfo.setFixDel(1);
		return bikeFixInfoMapper.updateByPrimaryKeySelective(bikeFixInfo);
	}

	@Override
	public int addBikeFixInfo(BikeFixInfo bikeFixInfo) throws Exception {
		// TODO Auto-generated method stub
		return bikeFixInfoMapper.insertSelective(bikeFixInfo);
	}

	@Override
	public void updateBikeFix(BikeFixInfo bf) throws Exception {
		// TODO Auto-generated method stub
		bikeFixInfoMapper.updateByPrimaryKeySelective(bf);
	}

	

}
