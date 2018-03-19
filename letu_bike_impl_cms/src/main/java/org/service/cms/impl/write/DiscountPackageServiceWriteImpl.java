package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.DiscountPackageMapper;
import org.entity.dto.DiscountPackage;
import org.service.cms.write.DiscountPackageServiceWrite;
import org.springframework.stereotype.Service;
@Service("discountPackageServiceWrite")
public class DiscountPackageServiceWriteImpl implements DiscountPackageServiceWrite {

	@Resource
	DiscountPackageMapper discountPackageMapper;
	
	@Override
	public void add(DiscountPackage discountPackage) throws Exception {
		// TODO Auto-generated method stub
		discountPackageMapper.insertSelective(discountPackage);
	}

	@Override
	public void update(DiscountPackage discountPackage) throws Exception {
		// TODO Auto-generated method stub
		discountPackageMapper.updateByPrimaryKeySelective(discountPackage);
	}
	
}
