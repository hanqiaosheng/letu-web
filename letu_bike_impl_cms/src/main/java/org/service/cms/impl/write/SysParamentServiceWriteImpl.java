package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.SysParamentMapper;
import org.entity.dto.SysParament;
import org.service.cms.write.SysParamentServiceWrite;
import org.springframework.stereotype.Service;
@Service("sysParamentServiceWrite")
public class SysParamentServiceWriteImpl implements SysParamentServiceWrite {

	@Resource
	SysParamentMapper sysParamentMapper;
	
	@Override
	public void update(SysParament sysParament) throws Exception {
		// TODO Auto-generated method stub
		sysParamentMapper.updateByPrimaryKeySelective(sysParament);
	}

	

}
