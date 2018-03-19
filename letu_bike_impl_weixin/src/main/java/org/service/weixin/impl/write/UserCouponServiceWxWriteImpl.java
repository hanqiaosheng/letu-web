package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.UserCouponMapper;
import org.entity.dto.UserCoupon;
import org.service.weixin.write.UserCouponServiceWxWrite;
import org.springframework.stereotype.Service;

@Service("userCouponServiceWxWrite")
public class UserCouponServiceWxWriteImpl implements UserCouponServiceWxWrite {
	@Resource
	UserCouponMapper userCouponMapper;

	@Override
	public void addUserCoupon(UserCoupon userCoupon) throws Exception {
		userCouponMapper.insertSelective(userCoupon);
	}

	@Override
	public void update(UserCoupon userCoupon) throws Exception {
		// TODO Auto-generated method stub
		userCouponMapper.updateByPrimaryKeySelective(userCoupon);
	}

	
}
