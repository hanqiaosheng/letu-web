package org.service.cms.read;

import java.util.List;

import org.entity.dto.Insurance;

public interface UserInsuranceServiceRead {
	/**
	 * 根据条件查询保险申请（分页）
	 * @param insurance
	 * @param pageIndex
	 * @param page_size
	 * @return
	 * @throws Exception
	 */
	public List<Insurance> findInsuranceByCondition(Insurance insurance,Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 通过id查找保险申请
	 * 
	 * @param insuranceId
	 * @return
	 * @throws Exception
	 */
	public Insurance findById(Long insuranceId) throws Exception;

	


	
	/**
	 * 根据查询条件 查找所有的保险申请条数
	 * 
	 * @param insurance
	 * @return
	 * @throws Exception
	 */
	public Integer countAllInsurance(Insurance insurance) throws Exception;
	
	/**
	 * 查询未处理的保险数量
	 * @return
	 * @throws Exception
	 */
	public Integer countUntreated()throws Exception;
	
}
