package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.ScenicSpotMapper;
import org.entity.dto.ScenicSpot;
import org.service.cms.write.ScenicSpotServiceWrite;
import org.springframework.stereotype.Service;
@Service("scenicSpotServiceWrite")
public class ScenicSpotServiceWriteImpl implements ScenicSpotServiceWrite {

	@Resource
	ScenicSpotMapper scenicSpotMapper;
	
	@Override
	public void add(ScenicSpot scenicSpot) throws Exception {
		// TODO Auto-generated method stub
		scenicSpotMapper.insertSelective(scenicSpot);
	}

	@Override
	public void update(ScenicSpot scenicSpot) throws Exception {
		// TODO Auto-generated method stub
		scenicSpotMapper.updateByPrimaryKeySelective(scenicSpot);
	}
	
}
