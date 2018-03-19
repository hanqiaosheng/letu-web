package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.InsurancePriceMapper;
import org.entity.dto.InsurancePrice;
import org.entity.dto.InsurancePriceExample;
import org.service.cms.read.InsurancePriceServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;
@Service("insurancePriceServiceRead")
public class InsurancePriceServiceReadImpl implements InsurancePriceServiceRead {
	
	@Resource
	InsurancePriceMapper insurancePriceMapper;

	@Override
	public List<InsurancePrice> findPriceByCondition(Integer pageIndex, Integer page_size_web, String name)
			throws Exception {
		InsurancePriceExample example = new InsurancePriceExample();
		InsurancePriceExample.Criteria criteria = example.createCriteria();
		if(null!=name&&!name.equals("")){
			criteria.andInPriceNameLike("%"+name.trim()+"%");
		}
		criteria.andInPriceStateNotEqualTo(0);
		if(pageIndex!=null){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(page_size_web);
		}
		example.setOrderByClause("in_update_time desc");
		return insurancePriceMapper.selectByExample(example);
	}

	@Override
	public Integer countAllPrice(String name) throws Exception {
		InsurancePriceExample example = new InsurancePriceExample();
		InsurancePriceExample.Criteria criteria = example.createCriteria();
		if(null!=name&&!name.equals("")){
			criteria.andInPriceNameLike("%"+name.trim()+"%");
		}
		criteria.andInPriceStateNotEqualTo(0);
		return insurancePriceMapper.countByExample(example);
	}

	@Override
	public InsurancePrice findById(Long inPriceId) throws Exception {
		// TODO Auto-generated method stub
		return insurancePriceMapper.selectByPrimaryKey(inPriceId);
	}

	@Override
	public InsurancePrice findByModelName(String inModelName) throws Exception {
		InsurancePriceExample example = new InsurancePriceExample();
		InsurancePriceExample.Criteria criteria = example.createCriteria();
		criteria.andInPriceNameLike(inModelName);
		criteria.andInPriceStateNotEqualTo(0);
		List<InsurancePrice> insurancePrices = insurancePriceMapper.selectByExample(example);
		if(insurancePrices.size()>0){
			return insurancePrices.get(0);
		}
		return null;
	}
	



}
