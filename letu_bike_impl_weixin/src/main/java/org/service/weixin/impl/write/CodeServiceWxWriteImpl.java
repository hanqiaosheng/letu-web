package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.CodeMapper;
import org.entity.dto.Code;
import org.service.weixin.write.CodeServiceWxWrite;
import org.springframework.stereotype.Service;

@Service("codeServiceWxWrite")
public class CodeServiceWxWriteImpl implements CodeServiceWxWrite {

	
	
	@Resource
	CodeMapper codeMapper;

	@Override
	public void add(Code code) throws Exception {
		// TODO Auto-generated method stub
		codeMapper.insertSelective(code);
	}

	@Override
	public void updateCode(Code code) throws Exception {
		// TODO Auto-generated method stub
		codeMapper.updateByPrimaryKeySelective(code);
	}
}
