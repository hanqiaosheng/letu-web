package org.service.cms.read;

import java.util.List;

import org.entity.dto.Banner;

public interface BannerServiceRead {
	/**
	 * 条件查询
	 * @param pageIndex
	 * @param pageSize
	 * @param cityIds
	 * @param cityId
	 * @param bannerName
	 * @return
	 * @throws Exception
	 */
	public List<Banner> findByCondition(Integer pageIndex,Integer pageSize,List<Long> cityIds,Long cityId,String bannerName)throws Exception;
	/**
	 * 条件查询数量
	 * @param cityIds
	 * @param cityId
	 * @param bannerName
	 * @return
	 * @throws Exception
	 */
	public Integer countByCondition(List<Long> cityIds,Long cityId,String bannerName)throws Exception;
	/**
	 * 根据id查询信息
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	public Banner findById(Long bannerId)throws Exception;
}
