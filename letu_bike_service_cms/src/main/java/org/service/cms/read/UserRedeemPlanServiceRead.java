package org.service.cms.read;


import java.util.Date;
import java.util.List;

import org.entity.dto.UserRedeemPlan;


public interface UserRedeemPlanServiceRead {

	/**
	 * 查询记录
	 * @param pageIndex
	 * @param pageSize
	 * @param name
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public List<UserRedeemPlan> findAllUserRedeemPlan(Integer pageIndex,Integer pageSize,String name,Date startTime,Date endTime,String userTel,String redeemCode)throws Exception;

	/**
	 * 计算数量
	 * @param redeemPlanName
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public Integer countAllUserRedeemPlan(String redeemPlanName, Date startTime, Date endTime,String userTel,String redeemCode)throws Exception;


}
