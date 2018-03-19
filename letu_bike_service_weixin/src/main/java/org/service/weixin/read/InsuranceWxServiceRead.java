package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Insurance;

public interface InsuranceWxServiceRead {
	
	/**
	 * 根据用户id查找他申报的保险
	 * @param userId
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<Insurance> findInsuranceByUserId(Long userId, Integer pageIndex, Integer page_size_weixin) throws Exception;
	
	/**
	 * 根据用户id 查找他申报保险的条数
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer findUserInsuranceCount(Long userId) throws Exception;
	
	/**
	 * 根据id查保险
	 * @param insuranceId
	 * @return
	 * @throws Exception
	 */
	public Insurance findByInsuranceId(Long insuranceId) throws Exception;

}
