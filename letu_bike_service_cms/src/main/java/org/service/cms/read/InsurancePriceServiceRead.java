package org.service.cms.read;

import java.util.List;

import org.entity.dto.InsurancePrice;

public interface InsurancePriceServiceRead {
	/**
	 * 根据条件查询保险费用
	 * @param pageIndex
	 * @param page_size_web
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<InsurancePrice> findPriceByCondition(Integer pageIndex, Integer page_size_web, String name)throws Exception;

	/**
	 * 分页数量
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Integer countAllPrice(String name)throws Exception;

	/**
	 * 根据id查询
	 * @return
	 * @throws Exception
	 */
	public InsurancePrice findById(Long inPriceId)throws Exception;

	/**
	 * 根据车型名查询
	 * @param inModelName
	 * @return
	 * @throws Exception
	 */
	public InsurancePrice findByModelName(String inModelName)throws Exception;

	
}
