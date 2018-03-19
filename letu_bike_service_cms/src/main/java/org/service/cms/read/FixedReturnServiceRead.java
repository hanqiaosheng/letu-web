package org.service.cms.read;

import java.util.List;

import org.entity.dto.FixedReturn;
import org.entity.dto.LatLng;

public interface FixedReturnServiceRead {

	//根据车型查站点
	public List<FixedReturn> findFixedByModelsId(Long modelsId) throws Exception;
	//根据fixedReturnId查站点
	public FixedReturn findFixedById(Long fixedReturnId) throws Exception;

	public List<FixedReturn> findAllFixed(Integer pageIndex, Integer pageSize, String name,String channelName,List<Long> channelIds)throws Exception;

	public Integer countAllFixed(String name,String channelName,List<Long> channelIds)throws Exception;

	//根据渠道查站点
	public List<FixedReturn> findByChannelIds(List<Long> channelIds)throws Exception;
	
	//根据渠道和方格站点
	public List<LatLng> findByBlockIdsAndChannel(List<String> blocks,List<Long> channelIds) throws Exception;
	
	//根据名称和渠道查站点
	public List<FixedReturn> findByChannelIdsAndName(Integer pageIndex,Integer pageSize,String name, List<Long> channelIds) throws Exception;
	//数量
	public Integer findAllFixedCount(String name,List<Long> channelIds)throws Exception;
	
	//审核列表
	public List<FixedReturn> findAllCheckFixed(Integer pageIndex, Integer pageSize, String name,
			List<Long> channelIds)throws Exception;
	//审核列表数量
	public Integer countAllCheckFixed(String name, List<Long> channelIds)throws Exception;
	//我设定的站点
	public List<FixedReturn> findAllMyFixed(Integer pageIndex, Integer pageSize, Integer fixedState,
			Long adminId) throws Exception;
	//获取我设定的站点数量
	public Integer findAllFixedCount(Integer fixedState, Long adminId)throws Exception;
	
	/**
	 * 获取未审核的还车点数量
	 * @return
	 * @throws Exception
	 */
	public Integer countUntreated(List<Long> channelIds)throws Exception;
	
	/**
	 * 通过渠道查询站点
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	public List<FixedReturn> findFixedByChannelId(Long channelId)throws Exception;


}
