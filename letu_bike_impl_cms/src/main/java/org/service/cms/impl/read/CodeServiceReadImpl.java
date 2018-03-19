package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.CodeMapper;
import org.entity.dto.Code;
import org.entity.dto.CodeExample;
import org.service.cms.read.CodeServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;
@Service("codeServiceRead")
public class CodeServiceReadImpl implements CodeServiceRead {
	
	@Resource
	CodeMapper codeMapper;

	@Override
	public List<Code> findAllShotMessage(Integer pageIndex, Integer pageSize, String tel,Integer codeState) throws Exception {
		CodeExample example = new CodeExample();
		CodeExample.Criteria criteria = example.createCriteria();
		criteria.andCodeTypeEqualTo(1);//注册
		if(null!=tel&&!tel.equals("")){
			criteria.andCodePhoneLike("%"+tel+"%");
		}
		if(null!=codeState&&-1!=codeState){
			criteria.andCodeStateEqualTo(codeState);
		}
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		example.setOrderByClause("code_createtime desc");
		return codeMapper.selectByExample(example);
	}

	@Override
	public Integer getCountShort(String tel,Integer codeState) throws Exception {
		CodeExample example = new CodeExample();
		CodeExample.Criteria criteria = example.createCriteria();
		criteria.andCodeTypeEqualTo(1);//注册
		if(null!=tel&&!tel.equals("")){
			criteria.andCodePhoneLike("%"+tel+"%");
		}
		if(null!=codeState&&-1!=codeState){
			criteria.andCodeStateEqualTo(codeState);
		}
		return codeMapper.countByExample(example);
	}


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
