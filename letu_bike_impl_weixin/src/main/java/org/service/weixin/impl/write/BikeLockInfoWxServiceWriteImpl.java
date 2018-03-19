package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.BikeLockInfoMapper;
import org.entity.dto.BikeLockInfo;
import org.service.weixin.write.BikeLockInfoWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("bikeLockInfoWxServiceWrite")
public class BikeLockInfoWxServiceWriteImpl implements BikeLockInfoWxServiceWrite {

	@Resource
	BikeLockInfoMapper bikeLockInfoMapper;

	@Override
	public void updateBikeLock(BikeLockInfo bikeLockInfo) throws Exception {
		// TODO Auto-generated method stub
		bikeLockInfoMapper.updateByPrimaryKey(bikeLockInfo);
	}
	
}
