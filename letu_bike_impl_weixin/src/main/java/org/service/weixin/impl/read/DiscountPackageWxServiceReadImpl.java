package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.DiscountPackageMapper;
import org.entity.dto.DiscountPackage;
import org.entity.dto.DiscountPackageExample;
import org.service.weixin.read.DiscountPackageWxServiceRead;
import org.springframework.stereotype.Service;

@Service("discountPackageWxServiceRead")
public class DiscountPackageWxServiceReadImpl implements DiscountPackageWxServiceRead{

	@Resource
	DiscountPackageMapper discountPackageMapper;

	@Override
	public DiscountPackage findByScenicId(Long scenicId) throws Exception {
		DiscountPackageExample example = new DiscountPackageExample();
		DiscountPackageExample.Criteria criteria = example.createCriteria();
		criteria.andDiscountPackageScenicSpotIdEqualTo(scenicId);
		criteria.andDiscountPackageStateEqualTo(2);
		example.setOrderByClause("discount_package_preferential_price asc");
		List<DiscountPackage> discountPackages = discountPackageMapper.selectByExample(example);
		if(discountPackages.size()>0){
			return discountPackages.get(0);
		}
		return null;
	}

	@Override
	public List<DiscountPackage> findAllByScenicId(Long scenicSpotId) {
		DiscountPackageExample example = new DiscountPackageExample();
		DiscountPackageExample.Criteria criteria = example.createCriteria();
		criteria.andDiscountPackageScenicSpotIdEqualTo(scenicSpotId);
		criteria.andDiscountPackageStateEqualTo(2);
		example.setOrderByClause("discount_package_top_num asc");
		return discountPackageMapper.selectByExample(example);
	}

	@Override
	public Integer countAllByScenicId(Long scenicSpotId) throws Exception {
		DiscountPackageExample example = new DiscountPackageExample();
		DiscountPackageExample.Criteria criteria = example.createCriteria();
		criteria.andDiscountPackageScenicSpotIdEqualTo(scenicSpotId);
		criteria.andDiscountPackageStateEqualTo(2);
		return discountPackageMapper.countByExample(example);
	}

	@Override
	public DiscountPackage findByPackageId(Long packageId) throws Exception {
		// TODO Auto-generated method stub
		return discountPackageMapper.selectByPrimaryKey(packageId);
	}

}
