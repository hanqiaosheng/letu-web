package org.service.cms.read;


import java.util.List;

import org.entity.dto.RedeemPlan;


public interface RedeemPlanServiceRead {

	/**
	 * 查询所有兑换方案
	 * @param pageIndex
	 * @param page_size_web
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public List<RedeemPlan> findAll(Integer pageIndex, Integer page_size_web, String title)throws Exception;

	/**
	 * 计算数量
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public Integer countAll(String title)throws Exception;

	/**
	 * 通过redeemPlanId查询信息
	 * @param redeemPlanId
	 * @return
	 * @throws Exception
	 */
	public RedeemPlan findRedeemPlanById(Long redeemPlanId)throws Exception;

	/**
	 * 查询所有上线的方案
	 * @return
	 * @throws Exception
	 */
	public List<RedeemPlan> findAllOnline()throws Exception;

	/**
	 * 查询所有方案
	 * @return
	 * @throws Exception
	 */
	public List<RedeemPlan> findAllPlan()throws Exception;

	/**
	 * 查询所有注册方案
	 * @param pageIndex
	 * @param page_size_web
	 * @param redeemPlanName
	 * @return
	 * @throws Exception
	 */
	public List<RedeemPlan> findAllRegistPlan(Integer pageIndex, Integer page_size_web, String redeemPlanName)throws Exception;

	/**
	 * 计算所有注册方案
	 * @param redeemPlanName
	 * @return
	 * @throws Exception
	 */
	public Integer countAllRegistPlan(String redeemPlanName)throws Exception;

}
