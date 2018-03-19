package org.service.cms.read;

import java.util.List;

import org.entity.dto.ScenicSpot;

public interface ScenicSpotServiceRead {
	/**
	 * 景区条件查询
	 * @param pageIndex
	 * @param pageSize
	 * @param cityIds
	 * @param cityId
	 * @param bannerName
	 * @return
	 * @throws Exception
	 */
	public List<ScenicSpot> findScenicByCondition(Integer pageIndex,Integer pageSize,Long cityId,String name,List<Long> cityIds)throws Exception;
	/**
	 * 景区条件查询数量
	 * @param cityIds
	 * @param cityId
	 * @param bannerName
	 * @return
	 * @throws Exception
	 */
	public Integer countScenicByCondition(Long cityId,String name,List<Long> cityIds)throws Exception;
	
	/**
	 * 根据id查询信息
	 * @param bannerId
	 * @return
	 * @throws Exception
	 */
	public ScenicSpot findById(Long scenicSpotId)throws Exception;
	
	/**
	 * 查询所有景点
	 * @return
	 * @throws Exception
	 */
	public List<ScenicSpot> findAllScenic()throws Exception;
	
}