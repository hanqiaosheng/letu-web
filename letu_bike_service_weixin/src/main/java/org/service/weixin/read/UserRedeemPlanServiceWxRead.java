package org.service.weixin.read;



import org.entity.dto.UserRedeemPlan;

public interface UserRedeemPlanServiceWxRead {

	/**
	 * 通过兑换方案id和用户id查询方案
	 * @param planId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserRedeemPlan findByUserAndPlan(Long planId, Long userId)throws Exception;

}
