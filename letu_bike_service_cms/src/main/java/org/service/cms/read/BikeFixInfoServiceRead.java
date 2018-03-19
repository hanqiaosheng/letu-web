package org.service.cms.read;

import java.util.Date;
import java.util.List;

import org.entity.dto.BikeFixInfo;


public interface BikeFixInfoServiceRead {
	/**
	 * 查询维护信息
	 * @return
	 * @throws Exception
	 */
	public List<BikeFixInfo> findAllBikeFixInfo(List<Long> channelIds,Integer pageIndex,String fixBatch,String bikecode,Date bikeFixStartTime,Date bikeFixStartTimeB,Date bikeFixEndTime,Date bikeFixEndTimeB,String bikeFixPerson,String blockCode) throws Exception;
	
	/**
	 * 维护信息数量
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public Integer countAllBikeFixInfo(List<Long> channelIds,String fixBatch,String bikecode,Date bikeFixStartTime,Date bikeFixStartTimeB,Date bikeFixEndTime,Date bikeFixEndTimeB,String bikeFixPerson,String blockCode) throws Exception;
	
	/**
	 * 根据车辆id查询维护信息
	 * @return
	 * @throws Exception
	 */
	public List<BikeFixInfo> findBikeFixInfoBybikeId(Long bikeId,String fixBatch,Integer pageIndex,Date bikeFixStartTime,Date bikeFixStartTimeB,Date bikeFixEndTime,Date bikeFixEndTimeB,String bikeFixPerson,String blockCode) throws Exception;

	/**
	 * 维护信息数量
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public Integer countBikeFixInfoBybikeId(Long bikeId,String fixBatch,Date bikeFixStartTime,Date bikeFixStartTimeB,Date bikeFixEndTime,Date bikeFixEndTimeB,String bikeFixPerson,String blockCode) throws Exception;
	
	/**
	 * 根据bikeFixId查询维护信息
	 * @param bikeFixId
	 * @return
	 * @throws Exception
	 */
	public BikeFixInfo findBikeFixInfoByFixId(Long bikeFixId) throws Exception;
	
	/**
	 * 最大批次号
	 * @param bikeFixId
	 * @return
	 * @throws Exception
	 */
	public String findMaxBatchNumber() throws Exception;
	
	public List<String> findBatchNumbers() throws Exception;

	/**
	 * 根据车辆id查对应车的维护列表
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public List<BikeFixInfo> findByBikeId(Long bikeId,Integer pageIndex,Integer page_size_weixin) throws Exception;
	/**
	 * 根据车辆id查对应车的维护列表数目
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public Integer countByBikeId(Long bikeId) throws Exception;
	
	/**
	 * 最近结束日期
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public String findMaxDate(Long bikeId)throws Exception;
	
	/**
	 * 根据id 查询未结束维护的维护记录
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public BikeFixInfo findBikeFixInfoByBikeIdAndNotFinish(Long bikeId) throws Exception;
	
}
