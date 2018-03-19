package org.service.weixin.write;

import org.entity.dto.UserCoupon;

public interface UserCouponServiceWxWrite {
	
	public void addUserCoupon(UserCoupon userCoupon)throws Exception;

	public void update(UserCoupon userCoupon)throws Exception;

}
