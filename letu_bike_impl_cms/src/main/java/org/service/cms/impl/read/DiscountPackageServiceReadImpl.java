package org.service.cms.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.DiscountPackageMapper;
import org.entity.dto.DiscountPackage;
import org.entity.dto.DiscountPackageExample;
import org.service.cms.read.DiscountPackageServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("discountPackageServiceRead")
public class DiscountPackageServiceReadImpl implements DiscountPackageServiceRead {
	@Resource
	DiscountPackageMapper discountPackageMapper;


	@Override
	public DiscountPackage findById(Long packageId) throws Exception {
		// TODO Auto-generated method stub
		return discountPackageMapper.selectByPrimaryKey(packageId);
	}


	@Override
	public List<DiscountPackage> findByCondition(Integer pageIndex, Integer pageSize, String packageName,Integer state,Long scenicId)
			throws Exception {
		DiscountPackageExample example = new DiscountPackageExample();
		DiscountPackageExample.Criteria criteria = example.createCriteria();
		if(null!=packageName&&!packageName.equals("")){
			criteria.andDiscountPackageNameLike("%"+packageName+"%");
		}
		if(null!=state&&-2!=state){
			criteria.andDiscountPackageStateEqualTo(state);
		}
        if(null!=scenicId&&-1!=scenicId){
        	criteria.andDiscountPackageScenicSpotIdEqualTo(scenicId);
		}
		criteria.andDiscountPackageStateNotEqualTo(-1);
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		example.setOrderByClause("discount_package_create_time");
		return discountPackageMapper.selectByExample(example);
	}


	@Override
	public Integer countByCondition(String packageName,Integer state,Long scenicId) throws Exception {
		DiscountPackageExample example = new DiscountPackageExample();
		DiscountPackageExample.Criteria criteria = example.createCriteria();
		if(null!=packageName&&!packageName.equals("")){
			criteria.andDiscountPackageNameLike("%"+packageName+"%");
		}
		if(null!=state&&-2!=state){
			criteria.andDiscountPackageStateEqualTo(state);
		}
        if(null!=scenicId&&-1!=scenicId){
        	criteria.andDiscountPackageScenicSpotIdEqualTo(scenicId);
		}
		criteria.andDiscountPackageStateNotEqualTo(-1);
		return discountPackageMapper.countByExample(example);
	}
}
