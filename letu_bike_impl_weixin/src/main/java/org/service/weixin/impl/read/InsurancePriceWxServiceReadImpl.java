package org.service.weixin.impl.read;


import javax.annotation.Resource;

import org.dao.InsurancePriceMapper;
import org.entity.dto.InsurancePrice;
import org.service.weixin.read.InsurancePriceWxServiceRead;
import org.springframework.stereotype.Service;
@Service("insurancePriceWxServiceRead")
public class InsurancePriceWxServiceReadImpl implements InsurancePriceWxServiceRead {
	
	@Resource
	InsurancePriceMapper insurancePriceMapper;

	@Override
	public InsurancePrice findById(Long inPriceId) throws Exception {
		// TODO Auto-generated method stub
		return insurancePriceMapper.selectByPrimaryKey(inPriceId);
	}

	



}
