package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.SysMsgMapper;
import org.entity.dto.SysMsg;
import org.service.cms.write.SysMsgServiceWrite;
import org.springframework.stereotype.Service;
@Service("sysMsgServiceWrite")
public class SysMsgServiceWriteImpl implements SysMsgServiceWrite {

	
	@Resource
	SysMsgMapper sysMsgMapper;
	@Override
	public void updateSysMsg(SysMsg sysMsg) throws Exception {
		// TODO Auto-generated method stub
		sysMsgMapper.updateByPrimaryKeySelective(sysMsg);
	}
	@Override
	public Long addSysMsg(SysMsg sysMsg) throws Exception {
		// TODO Auto-generated method stub
		sysMsgMapper.insertSelective(sysMsg);
		return sysMsg.getSysMsgId();
	}

}
