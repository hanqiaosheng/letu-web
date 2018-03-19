package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.InsurancePriceMapper;
import org.entity.dto.InsurancePrice;
import org.service.cms.write.InsurancePriceServiceWrite;
import org.springframework.stereotype.Service;


@Service("insurancePriceServiceWrite")
public class InsurancePriceServiceWriteImpl implements InsurancePriceServiceWrite {
	@Resource
	InsurancePriceMapper insurancePriceMapper;

	@Override
	public void add(InsurancePrice insurancePrice) throws Exception {
		// TODO Auto-generated method stub
		insurancePriceMapper.insertSelective(insurancePrice);
	}

	@Override
	public void update(InsurancePrice insurancePrice) throws Exception {
		// TODO Auto-generated method stub
		insurancePriceMapper.updateByPrimaryKeySelective(insurancePrice);
	}


	
}
