package org.service.cms.read;


import java.util.List;

import org.entity.dto.UserCoupon;


public interface UserCouponServiceRead {

	public List<UserCoupon> findAllUserCoupon()throws Exception;


}
