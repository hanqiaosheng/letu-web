package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.InsuranceMapper;
import org.entity.dto.Insurance;
import org.service.cms.write.UserInsuranceServiceWrite;
import org.springframework.stereotype.Service;
@Service("userInsuranceServiceWrite")
public class UserInsuranceServiceWriteImpl implements UserInsuranceServiceWrite {
	@Resource
	InsuranceMapper insuranceMapper;

	@Override
	public void updateInsuranceState(Insurance insurance) throws Exception {
		// TODO Auto-generated method stub
		insuranceMapper.updateByPrimaryKeySelective(insurance);
	}
	
	@Override
	public Long addInsurance(Insurance insurance) throws Exception {
		// TODO Auto-generated method stub
		insuranceMapper.insertSelective(insurance);
		Long insuranceId = insurance.getInsuranceId();
		return insuranceId;
	}

}
