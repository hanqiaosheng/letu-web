package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.OperateLogMapper;
import org.entity.dto.OperateLog;
import org.service.cms.write.OperateServiceWrite;
import org.springframework.stereotype.Service;


@Service("operateServiceWrite")
public class OperateServiceWriteImpl implements OperateServiceWrite {
	@Resource
	OperateLogMapper operateLogMapper;

	@Override
	public void addOperateLogs(OperateLog operateLog) throws Exception {
		operateLogMapper.insertSelective(operateLog);
	}

	
}
