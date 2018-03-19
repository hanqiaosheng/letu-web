package org.service.cms.read;


import java.util.List;

import org.entity.dto.DiscountPackage;

public interface DiscountPackageServiceRead {
	/**
	 * 根据id查询信息
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	public DiscountPackage findById(Long packageId)throws Exception;

	/**
	 * 根据条件查询套餐
	 * @param pageIndex
	 * @param page_size_web
	 * @param packageName
	 * @return
	 * @throws Exception
	 */
	public List<DiscountPackage> findByCondition(Integer pageIndex, Integer pageSize, String packageName,Integer state,Long scenicId)throws Exception;
	/**
	 * 根据条件查询数量
	 * @param packageName
	 * @return
	 * @throws Exception
	 */
	public Integer countByCondition(String packageName,Integer state,Long scenicId)throws Exception;
}
