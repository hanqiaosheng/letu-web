package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.CashCouponMapper;
import org.dao.CouponPlanMapper;
import org.dao.UserCouponMapper;
import org.entity.dto.CashCoupon;
import org.entity.dto.CouponPlan;
import org.entity.dto.UserCoupon;
import org.entity.dto.UserCouponExample;
import org.service.weixin.read.UserCouponServiceWxRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("userCouponServiceWxRead")
public class UserCouponServiceWxReadImpl implements UserCouponServiceWxRead {
	@Resource
	UserCouponMapper userCouponMapper;
	@Resource
    CashCouponMapper cashCouponMapper;
	@Resource
	CouponPlanMapper couponPlanMapper;
 
	@Override
	public List<UserCoupon> findCouponByUserId(Integer pageIndex, Long userId) {
		UserCouponExample example = new UserCouponExample();
		UserCouponExample.Criteria criteria = example.createCriteria();
		criteria.andUuserIdEqualTo(userId);//对应用户
		criteria.andUstateEqualTo(1);//未使用
/*		try {
			criteria.andURedeemCodeEndTimeGreaterThanOrEqualTo(DateUtil.getDateFormat(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		if(null!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(PageUtil.weixinsize);
		}
		example.setOrderByClause("coupon_money desc,u_create_time asc");
		List<UserCoupon> list = userCouponMapper.selectUnionByExample(example);
		if(list.size()>0){
			return list;
		}
		return null;
	}

	@Override
	public CashCoupon findCouponById(Long ucouponId) throws Exception {
		return cashCouponMapper.selectByPrimaryKey(ucouponId);
	}

	@Override
	public CouponPlan findCouponPlanById(Long planCouponPlanId) throws Exception {
		return couponPlanMapper.selectByPrimaryKey(planCouponPlanId);
	}

	@Override
	public UserCoupon findUserCouponId(Long userCouponId) throws Exception {
		return userCouponMapper.selectByPrimaryKey(userCouponId);
	}

	@Override
	public Integer countCouponByUserId(Long userId) throws Exception {
		UserCouponExample example = new UserCouponExample();
		UserCouponExample.Criteria criteria = example.createCriteria();
		criteria.andUuserIdEqualTo(userId);//对应用户
		criteria.andUstateEqualTo(1);//未使用
		return userCouponMapper.countByExample(example);
	}

}
