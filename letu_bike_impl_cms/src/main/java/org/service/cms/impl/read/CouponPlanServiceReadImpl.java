package org.service.cms.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.CouponPlanMapper;
import org.entity.dto.CouponPlan;
import org.entity.dto.CouponPlanExample;
import org.service.cms.read.CouponPlanServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;
@Service("couponPlanServiceRead")
public class CouponPlanServiceReadImpl implements CouponPlanServiceRead {
	@Resource
	CouponPlanMapper couponPlanMapper;
 
	@Override
	public List<CouponPlan> findCanUsePlan() throws Exception {
		CouponPlanExample example = new CouponPlanExample();
		CouponPlanExample.Criteria criteria = example.createCriteria();
		criteria.andCouponPlanStateEqualTo(1);//使用中
		return couponPlanMapper.selectByExample(example);
	}

	@Override
	public List<CouponPlan> findAllCouponPlan(Integer pageIndex, Integer page_size_web, String title) throws Exception {
		CouponPlanExample example = new CouponPlanExample();
		CouponPlanExample.Criteria criteria = example.createCriteria();
		if(!"".equals(title)&&null!=title){
			criteria.andCouponPlanNameLike("%"+title.trim()+"%");
		}
		criteria.andCouponPlanStateEqualTo(1);//使用中
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(page_size_web);
		example.setOrderByClause("coupon_plan_create_time desc");
		return couponPlanMapper.selectByExample(example);
	}

	@Override
	public Integer findAllCouponPlanCount(String title) throws Exception {
		CouponPlanExample example = new CouponPlanExample();
		CouponPlanExample.Criteria criteria = example.createCriteria();
		if(!"".equals(title)&&null!=title){
			criteria.andCouponPlanNameLike("%"+title.trim()+"%");
		}
		criteria.andCouponPlanStateEqualTo(1);//使用中
		return couponPlanMapper.countByExample(example);
	}

	@Override
	public CouponPlan findByCouponPlanId(Long couponPlanId) throws Exception {
		return couponPlanMapper.selectByPrimaryKey(couponPlanId);
	}
	
}
