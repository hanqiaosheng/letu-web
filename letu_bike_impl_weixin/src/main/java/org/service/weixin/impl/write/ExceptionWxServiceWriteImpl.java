package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.ExceptionsMapper;
import org.entity.dto.Exceptions;
import org.service.weixin.write.ExceptionWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("exceptionWxServiceWrite")
public class ExceptionWxServiceWriteImpl implements ExceptionWxServiceWrite {

	@Resource
	ExceptionsMapper exceptionsMapper;
	
	@Override
	public void addException(Exceptions exceptions) throws java.lang.Exception {
		// TODO Auto-generated method stub
		exceptionsMapper.insertSelective(exceptions);
		
	}

	

}
