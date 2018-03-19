package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.OrbitMapper;
import org.entity.dto.Orbit;
import org.entity.dto.OrbitExample;
import org.service.weixin.read.OrbitWxServiceRead;
import org.springframework.stereotype.Service;

@Service("orbitWxServiceRead")
public class OrbitWxServiceReadImpl implements OrbitWxServiceRead {
	@Resource
	OrbitMapper orbitMapper;

	@Override
	public Orbit findByRentInfoId(Long rentInfoId) throws Exception {
		OrbitExample example = new OrbitExample();
		OrbitExample.Criteria criteria = example.createCriteria();
		criteria.andOrbitRentInfoIdEqualTo(rentInfoId);
		List<Orbit> orbits = orbitMapper.selectByExampleWithBLOBs(example);
		if(orbits.size()>0){
			return orbits.get(0);
		}
		return null;
	}

}
