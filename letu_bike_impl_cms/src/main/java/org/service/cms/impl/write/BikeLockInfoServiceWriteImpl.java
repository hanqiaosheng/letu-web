package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.BikeLockInfoMapper;
import org.entity.dto.BikeLockInfo;
import org.service.cms.write.BikeLockInfoServiceWrite;
import org.springframework.stereotype.Service;
@Service("bikeLockInfoServiceWrite")
public class BikeLockInfoServiceWriteImpl implements BikeLockInfoServiceWrite {
	@Resource
	BikeLockInfoMapper bikeLockInfoMapper;
	@Override
	public void updateBikeLockInfo(BikeLockInfo bikeLockInfo) throws Exception {
		// TODO Auto-generated method stub
		bikeLockInfoMapper.updateByPrimaryKeySelective(bikeLockInfo);
	}
	@Override
	public void adddBikeLockInfo(BikeLockInfo bikeLockInfo) throws Exception {
		// TODO Auto-generated method stub
		bikeLockInfoMapper.insertSelective(bikeLockInfo);
	}
	@Override
	public void updateLockInfo(BikeLockInfo bikeLockInfo) throws Exception {
		// TODO Auto-generated method stub
		bikeLockInfoMapper.updateByPrimaryKey(bikeLockInfo);
	}

}
