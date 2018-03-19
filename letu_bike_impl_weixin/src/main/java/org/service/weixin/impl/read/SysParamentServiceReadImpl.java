package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.SysParamentMapper;
import org.entity.dto.SysParament;
import org.entity.dto.SysParamentExample;
import org.service.weixin.read.SysParamentServiceRead;
import org.springframework.stereotype.Service;


@Service("sysParamentServiceRead")
public class SysParamentServiceReadImpl implements SysParamentServiceRead {

	
	@Resource
	SysParamentMapper sysParamentMapper;

	@Override
	public SysParament findByName(String name) throws Exception {
		// TODO Auto-generated method stub
		SysParamentExample example = new SysParamentExample();
		SysParamentExample.Criteria criteria = example.createCriteria();
		criteria.andSysParamentNameEqualTo(name);
		List<SysParament> paramentList = sysParamentMapper.selectByExample(example);
		if(paramentList.size()>0){
			return paramentList.get(0);
		}
		return null;
	}
	
}
