package org.service.cms.impl.write;


import javax.annotation.Resource;

import org.dao.UserCouponMapper;
import org.entity.dto.UserCoupon;
import org.service.cms.write.UserCouponServiceWrite;
import org.springframework.stereotype.Service;


@Service("userCouponServiceWrite")
public class UserCouponServiceWriteImpl implements UserCouponServiceWrite {
	@Resource
	UserCouponMapper userCouponMapper;

	@Override
	public void updateUserCoupon(UserCoupon userCoupon) throws Exception {
		// TODO Auto-generated method stub
		userCouponMapper.updateByPrimaryKeySelective(userCoupon);
	}
	
	
	
}
