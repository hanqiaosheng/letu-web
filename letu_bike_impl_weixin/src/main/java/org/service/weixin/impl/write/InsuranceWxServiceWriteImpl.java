package org.service.weixin.impl.write;


import javax.annotation.Resource;

import org.dao.InsuranceMapper;
import org.entity.dto.Insurance;
import org.service.weixin.write.InsuranceWxServiceWrite;
import org.springframework.stereotype.Service;

@Service("insuranceWxServiceWrite")
public class InsuranceWxServiceWriteImpl implements InsuranceWxServiceWrite{
	
	@Resource
	InsuranceMapper insuranceMapper;


	@Override
	public void addInsurance(Insurance insurance) throws Exception {
		// TODO Auto-generated method stub
		insuranceMapper.insertSelective(insurance);
	}


	@Override
	public void updateInsurance(Insurance insurance) throws Exception {
		// TODO Auto-generated method stub
		insuranceMapper.updateByPrimaryKeySelective(insurance);
	}

	
}
