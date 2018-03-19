package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.InsuranceMapper;
import org.entity.dto.Insurance;
import org.entity.dto.InsuranceExample;
import org.service.weixin.read.InsuranceWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("insuranceWxServiceRead")
public class InsuranceWxServiceReadImpl implements InsuranceWxServiceRead {

	@Resource
	InsuranceMapper insuranceMapper;

	@Override
	public List<Insurance> findInsuranceByUserId(Long userId, Integer pageIndex, Integer page_size_weixin)
			throws Exception {
		// TODO Auto-generated method stub
		InsuranceExample insuranceExample = new InsuranceExample();
		InsuranceExample.Criteria criteria = insuranceExample.createCriteria();
		criteria.andInsuranceUserIdEqualTo(userId);
		criteria.andInsuranceStateNotEqualTo(3);
		insuranceExample.setLimitStart(PageUtil.getWeixinStart(pageIndex));
		insuranceExample.setLimitEnd(page_size_weixin);
		insuranceExample.setOrderByClause("insurance_apply_time desc");
		return insuranceMapper.selectByExample(insuranceExample);
	}

	@Override
	public Integer findUserInsuranceCount(Long userId) throws Exception {
		// TODO Auto-generated method stub
		InsuranceExample insuranceExample = new InsuranceExample();
		InsuranceExample.Criteria criteria = insuranceExample.createCriteria();
		criteria.andInsuranceUserIdEqualTo(userId);
		criteria.andInsuranceStateNotEqualTo(3);
		return insuranceMapper.countByExample(insuranceExample);
	}

	@Override
	public Insurance findByInsuranceId(Long insuranceId) throws Exception {
		// TODO Auto-generated method stub
		return insuranceMapper.selectByPrimaryKey(insuranceId);
	}
	
	
}
