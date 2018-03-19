package org.service.cms.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.UserCouponMapper;
import org.entity.dto.UserCoupon;
import org.entity.dto.UserCouponExample;
import org.service.cms.read.UserCouponServiceRead;
import org.springframework.stereotype.Service;
@Service("userCouponServiceRead")
public class UserCouponServiceReadImpl implements UserCouponServiceRead {
	@Resource
	UserCouponMapper userCouponMapper;

	@Override
	public List<UserCoupon> findAllUserCoupon() throws Exception {
		UserCouponExample example = new UserCouponExample();
		UserCouponExample.Criteria criteria = example.createCriteria();
		criteria.andUstateEqualTo(1);//未使用
		return userCouponMapper.selectByExample(example);
	}
	
	
}
