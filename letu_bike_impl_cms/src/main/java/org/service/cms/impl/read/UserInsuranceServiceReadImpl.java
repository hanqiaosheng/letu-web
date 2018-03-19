package org.service.cms.impl.read;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.InsuranceMapper;
import org.entity.dto.Insurance;
import org.entity.dto.InsuranceExample;
import org.service.cms.read.UserInsuranceServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;

@Service("userInsuranceServiceRead")
public class UserInsuranceServiceReadImpl implements UserInsuranceServiceRead {

	@Resource
	InsuranceMapper insuranceMaooer;

	@Override
	public List<Insurance> findInsuranceByCondition(Insurance insurance, Integer pageIndex, Integer pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		InsuranceExample example = new InsuranceExample();
		InsuranceExample.Criteria criteria = example.createCriteria();
		if (null != insurance.getiUserTel()&&!"".equals(insurance.getiUserTel())){
			if (null!=insurance.getiUserIds()&&0 != insurance.getiUserIds().size())
				criteria.andInsuranceUserIdIn(insurance.getiUserIds());
			else
				return null;
		}
		if (null != insurance.getiUserName()&&!"".equals(insurance.getiUserName())){
			if (null!=insurance.getiUserIds()&&0 != insurance.getiUserIds().size())
				criteria.andInsuranceUserIdIn(insurance.getiUserIds());
			else
				return null;
		}
		if (null != insurance.getInsuranceState())
			criteria.andInsuranceStateEqualTo(insurance.getInsuranceState());
		if (null != insurance.getFromTime() && !"".equals(insurance.getFromTime())) {
			if (null != insurance.getToTime() && !"".equals(insurance.getToTime()))
				criteria.andInsuranceApplyTimeBetween(DateUtil.changStringDate03(insurance.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(insurance.getToTime()), 1));
			else {
				criteria.andInsuranceApplyTimeBetween(DateUtil.changStringDate03(insurance.getFromTime()), new Date());
			}
		} else if (null != insurance.getToTime() && !"".equals(insurance.getToTime()))
			criteria.andInsuranceApplyTimeLessThan(
					DateUtil.plusDate(DateUtil.changStringDate03(insurance.getToTime()), 1));
		criteria.andInsuranceStateNotEqualTo(3);// 排除已删除
		example.setOrderByClause("insurance_state=0 desc, insurance_apply_time desc");
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return insuranceMaooer.selectByExample(example);
	}

	@Override
	public Insurance findById(Long insuranceId) throws Exception {
		// TODO Auto-generated method stub
		return insuranceMaooer.selectByPrimaryKey(insuranceId);
	}

	@Override
	public Integer countAllInsurance(Insurance insurance) throws Exception {
		// TODO Auto-generated method stub
		InsuranceExample example = new InsuranceExample();
		InsuranceExample.Criteria criteria = example.createCriteria();
		if (null != insurance.getiUserTel()&&!"".equals(insurance.getiUserTel())){
			if (null!=insurance.getiUserIds()&&0 != insurance.getiUserIds().size())
				criteria.andInsuranceUserIdIn(insurance.getiUserIds());
			else
				return 0;
		}
		if (null != insurance.getiUserName()&&!"".equals(insurance.getiUserName())){
			if (null!=insurance.getiUserIds()&&0 != insurance.getiUserIds().size())
				criteria.andInsuranceUserIdIn(insurance.getiUserIds());
			else
				return 0;
		}
		if (null != insurance.getInsuranceState())
			criteria.andInsuranceStateEqualTo(insurance.getInsuranceState());
		if (null != insurance.getFromTime() && !"".equals(insurance.getFromTime())) {
			if (null != insurance.getToTime() && !"".equals(insurance.getToTime()))
				criteria.andInsuranceApplyTimeBetween(DateUtil.changStringDate03(insurance.getFromTime()),
						DateUtil.plusDate(DateUtil.changStringDate03(insurance.getToTime()), 1));
			else {
				criteria.andInsuranceApplyTimeBetween(DateUtil.changStringDate03(insurance.getFromTime()), new Date());
			}
		} else if (null != insurance.getToTime() && !"".equals(insurance.getToTime()))
			criteria.andInsuranceApplyTimeLessThan(
					DateUtil.plusDate(DateUtil.changStringDate03(insurance.getToTime()), 1));
		criteria.andInsuranceStateNotEqualTo(3);
		return insuranceMaooer.countByExample(example);
	}

	@Override
	public Integer countUntreated() throws Exception {
		// TODO Auto-generated method stub
		InsuranceExample example = new InsuranceExample();
		InsuranceExample.Criteria criteria = example.createCriteria();
		criteria.andInsuranceStateEqualTo(0);
		return insuranceMaooer.countByExample(example);
		
	}

}
