package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeLockInfoMapper;
import org.entity.dto.BikeLockInfo;
import org.entity.dto.BikeLockInfoExample;
import org.service.weixin.read.BikeLockInfoWxServiceRead;
import org.springframework.stereotype.Service;


@Service("bikeLockInfoWxServiceRead")
public class BikeLockInfoWxServiceReadImpl implements BikeLockInfoWxServiceRead {

	@Resource
	BikeLockInfoMapper bikeLockInfoMapper;
	
	@Override
	public BikeLockInfo findLockInfoById(Long bikeId) throws Exception {
		// TODO Auto-generated method stub
		BikeLockInfoExample example = new BikeLockInfoExample();
		BikeLockInfoExample.Criteria criteria = example.createCriteria();
		criteria.andBikeLockBikeIdEqualTo(bikeId);
		List<BikeLockInfo> bikeLockInfos = bikeLockInfoMapper.selectByExample(example);
		if(bikeLockInfos.size()>0){
			return bikeLockInfos.get(0);
		}
		return null;
	}

}
