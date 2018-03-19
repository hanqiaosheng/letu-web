package org.service.weixin.impl.write;


import javax.annotation.Resource;

import org.dao.OrbitMapper;
import org.entity.dto.Orbit;
import org.service.weixin.write.OrbitServiceWxWrite;
import org.springframework.stereotype.Service;

@Service("orbitServiceWxWrite")
public class OrbitServiceWxWriteImpl implements OrbitServiceWxWrite {
	@Resource
	OrbitMapper orbitMapper;

	@Override
	public void add(Orbit orbit) throws Exception {
		// TODO Auto-generated method stub
		orbitMapper.insertSelective(orbit);
	}

	@Override
	public void update(Orbit orbit) throws Exception {
		// TODO Auto-generated method stub
		orbitMapper.updateByPrimaryKeyWithBLOBs(orbit);
	}
	
}
