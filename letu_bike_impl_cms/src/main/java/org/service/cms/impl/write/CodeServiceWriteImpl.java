package org.service.cms.impl.write;

import org.dao.CodeMapper;
import org.entity.dto.Code;
import org.service.cms.write.CodeServiceWrite;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("codeServiceWrite")
public class CodeServiceWriteImpl implements CodeServiceWrite {



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

