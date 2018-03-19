package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Bike;
import org.entity.dto.LatLng;

public interface BikeWxServiceRead {
	/**
	 * 
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public Bike findByBikeId(Long bikeId) throws Exception;
	/**
	 * 根据区域方格ids查找车辆
	 * @param blockIds
	 * @return
	 * @throws Exception
	 */
	public List<LatLng> findByBlockIds(List<Long> blockIds) throws Exception;
	/**
	 * 根据区域方格和渠道ids查找车辆
	 * @param blockIds
	 * @param modelsList 
	 * @return
	 * @throws Exception
	 */
	public List<LatLng> findByBlockIdsAndChannel(List<Long> blockIds, List<Long> modelsList) throws Exception;
	
	/**
	 * 根据车Code查找车辆
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public Bike findByBikeCode(String bikeCode) throws Exception;
	

}
