package org.service.weixin.read;

import java.util.List;

import org.entity.dto.BikeFixInfo;

public interface BikeFixInfoWxServiceRead {
	
	
	/**
	 * 查询所有维护信息
	 * @return
	 * @throws Exception
	 */
	public List<BikeFixInfo> findAllInfo(Integer pageIndex,Integer pageSize,Long bikeId,String bikeCode,List<Long> channelIds) throws Exception;
	/**
	 *  查询所有维护信息条数
	 * @param bikeId
	 * @param channelIds
	 * @return
	 * @throws Exception
	 */
	public Integer countAllInfo(Long bikeId,String bikeCode,List<Long> channelIds) throws Exception;


}
