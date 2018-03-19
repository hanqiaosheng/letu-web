package org.service.cms.impl.read;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.UserRedeemPlanMapper;
import org.entity.dto.UserRedeemPlan;
import org.entity.dto.UserRedeemPlanExample;
import org.service.cms.read.UserRedeemPlanServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;
@Service("userRedeemPlanServiceRead")
public class UserRedeemPlanServiceReadImpl implements UserRedeemPlanServiceRead {
	@Resource
	UserRedeemPlanMapper userRedeemPlanMapper;

	@Override
	public List<UserRedeemPlan> findAllUserRedeemPlan(Integer pageIndex, Integer pageSize, String name, Date startTime,
			Date endTime,String userTel,String redeemCode) throws Exception {
		UserRedeemPlanExample example = new UserRedeemPlanExample();
		UserRedeemPlanExample.Criteria criteria = example.createCriteria();
		if(null!=name&&!name.equals("")){
			criteria.andRedeemPlanNameLike("%"+name+"%");
		}
		if(null!=userTel&&!userTel.equals("")){
			criteria.andUserTelLike("%"+userTel+"%");
		}
		if(null!=redeemCode&&!redeemCode.equals("")){
			criteria.andRedeemCodeLike("%"+redeemCode+"%");
		}
		if(null!=startTime){
			criteria.andUrpCreateTimeGreaterThanOrEqualTo(startTime);
		}
		if(null!=endTime){
			criteria.andUrpCreateTimeLessThan(DateUtil.plusDateNum(endTime, 1));
		}
		if(null!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(pageSize);
		}
		example.setOrderByClause("urp_create_time desc");
		return userRedeemPlanMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countAllUserRedeemPlan(String name, Date startTime, Date endTime,String userTel,String redeemCode) throws Exception {
		UserRedeemPlanExample example = new UserRedeemPlanExample();
		UserRedeemPlanExample.Criteria criteria = example.createCriteria();
		if(null!=name&&!name.equals("")){
			criteria.andRedeemPlanNameLike("%"+name+"%");
		}
		if(null!=userTel&&!userTel.equals("")){
			criteria.andUserTelLike("%"+userTel+"%");
		}
		if(null!=redeemCode&&!redeemCode.equals("")){
			criteria.andRedeemCodeLike("%"+redeemCode+"%");
		}
		if(null!=startTime){
			criteria.andUrpCreateTimeGreaterThanOrEqualTo(startTime);
		}
		if(null!=endTime){
			criteria.andUrpCreateTimeLessThan(DateUtil.plusDateNum(endTime, 1));
		}
		return userRedeemPlanMapper.countUnionByExample(example);
	}

	
}
