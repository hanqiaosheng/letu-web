package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.CodeMapper;
import org.entity.dto.Code;
import org.entity.dto.CodeExample;
import org.service.weixin.read.CodeServiceWxRead;
import org.springframework.stereotype.Service;

@Service("codeServiceWxRead")
public class CodeServiceWxReadImpl implements CodeServiceWxRead {

	@Resource
	CodeMapper codeMapper;

	@Override
	public Code findByPhone(String telphone, int i) throws Exception {
		// TODO Auto-generated method stub
		CodeExample  codeExample = new CodeExample();
		CodeExample.Criteria criteria = codeExample.createCriteria();
		criteria.andCodePhoneEqualTo(telphone);
		criteria.andCodeTypeEqualTo(i);
		criteria.andCodeStateEqualTo(0);
		codeExample.setOrderByClause("code_createtime desc");
		List<Code> codeList = codeMapper.selectByExample(codeExample);
		if(codeList.size()>0){
			return codeList.get(0);
		}
		return null;
	}
	
	
	
}
