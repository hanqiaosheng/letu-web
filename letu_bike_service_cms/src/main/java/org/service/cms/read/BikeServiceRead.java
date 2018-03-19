package org.service.cms.read;

import java.util.Date;
import java.util.List;

import org.entity.dto.Bike;
import org.entity.dto.LatLng;

public interface BikeServiceRead {
	/**
	 * 查询所有车辆
	 * @param modelsState
	 * @param channelId
	 * @return
	 * @throws Exception
	 */

     public List<Bike> findAllBike(Integer pageIndex,Integer pageSize,Bike bike,Date bikePutStartTime, Date bikePutEndTime,String blockCode, List<Long> channelIds,String bikeLockCode,Long channelid, Long modelsId) throws Exception;

     /**
      * 所有车辆数量
     * @param modelsState 
     * @param channelId 
      * @return
      * @throws Exception
      */
     public Integer countAllBike(Bike bike,Date bikePutStartTime, Date bikePutEndTime,String blockCode, List<Long> channelIds,String bikeLockCode,Long channelid, Long modelsId) throws Exception;
     
     /**
      * 根据bikeId查询车辆信息
      * @param bike
      * @return
      * @throws Exception
      */
     public Bike findBikeByBikeId(Long bikeId) throws Exception;
     
     /**
      * 根据bikeCode查询车辆信息
      * @param bike
      * @return
      * @throws Exception
      */
     public Bike findBikeByBikeCode(String bikeCode) throws Exception;
/**
      * 根据渠道id 查找所有状态的车辆
	 * @param pageSize 
	 * @param pageIndex 
      * @param channelId
      * @return
      * @throws Exception
      */
	 public List<Bike> findByChannel(Integer pageIndex, Integer pageSize, Long channelId) throws Exception;
	 /**
	  * 根据渠道id查所有车的辆数
	  * @param channelId
	  * @return
	  * @throws Exception
	  */
	 public Integer findByChannelCount(Long channelId) throws Exception;
	 
	 /**
	  * 查询批次号
	  * @return
	  * @throws Exception
	  */
	 public List<String> findBikeBatchNumber() throws Exception;

	 public List<LatLng> findByBlockIds(List<String> blocks) throws Exception;
	 /**
	  * 查询最大批次号
	  * @return
	  * @throws Exception
	  */
	 public String findMaxBikeBatchNumber() throws Exception;
	 
	 /**
	  * 根据型号id 查车
	  * @param modelIds
	  * @return
	  * @throws Exception
	  */
	public Integer findByModelsId(List<Long> modelIds) throws Exception;

	/**
	 * 根据车型查车
	 * @param pageIndex
	 * @param base_path_web
	 * @param modelsIds
	 * @return
	 * @throws Exception
	 */
	public List<Bike> findBikeByModels(Integer pageIndex, Integer base_path_web, List<Long> modelsIds) throws Exception;
	 
	
	/**
     * 根据bikeCode查询车辆信息
     * @param bike
     * @return
     * @throws Exception
     */
    public Bike findBikeByEqualBikeCode(String bikeCode) throws Exception;
    
    /**
     * 根据方格查车
     * @param bIds
     * @param modelsList
     * @return
     * @throws Exception
     */
	public List<LatLng> findByBlockIdsAndChannel(List<String> blocks, List<Long> modelsList) throws Exception;

	/**
	 * 根据条件查找车
	 * @param pageIndex
	 * @param pageSize 
	 * @param bikeCode
	 * @return
	 * @throws Exception
	 */
	public List<Bike> findAllBikes(Integer pageIndex, Integer pageSize, String bikeCode) throws Exception;
	/**
	 * 根据条件查总数
	 * @param bikeCode
	 * @return
	 * @throws Exception
	 */
	public Integer findAllBikeCount(String bikeCode) throws Exception;

	/**
	 * 根据 渠道查车辆
	 * @param bikeCode 
	 * @param pageSize 
	 * @param pageIndex 
	 * @param modelsList
	 * @return
	 */
	public List<Bike> findByChannel(Integer pageIndex, Integer pageSize, String bikeCode, List<Long> modelsList);

	/**
	 * 根据 渠道查车辆Count
	 * @param bikeCode 
	 * @param pageSize 
	 * @param pageIndex 
	 * @param modelsList
	 * @return
	 */
	public Integer findByChannelCounts(String bikeCode, List<Long> modelsList);
	
	/**
	 * 查找所有能维护的车辆
	 * @param pageIndex
	 * @param page_size_weixin
	 * @param bikeCode
	 * @return
	 * @throws Exception
	 */
	public List<Bike> findAllFixBikes(Integer pageIndex, Integer page_size_weixin, String bikeCode) throws Exception;
	/**
	 * 查找所有能维护的车辆总数
	 * @param bikeCode
	 * @return
	 * @throws Exception
	 */
	public Integer findAllFixBikeCount(String bikeCode) throws Exception;
	/**
	 * 根据 渠道查能维护的车辆
	 * @param bikeCode 
	 * @param pageSize 
	 * @param pageIndex 
	 * @param modelsList
	 * @return
	 */
	public List<Bike> findByFixChannel(Integer pageIndex, Integer page_size_weixin, String bikeCode,
			List<Long> modelsList) throws Exception;

	/**
	 * 查找渠道下所有能维护的车辆总数
	 * @param bikeCode
	 * @param modelsList
	 * @return
	 * @throws Exception
	 */
	public Integer findByFixChannelCounts(String bikeCode, List<Long> modelsList) throws Exception;

	/**
	 * 查找渠道下所有空闲车辆
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public List<Bike> findByChannels(List<Long> channelIds)throws Exception;

	/**
	 * 通过fixedReturnId查找信息
	 * @param fixedReturnId
	 * @return
	 * @throws Exception
	 */
	public List<Bike> findByFixedReturnId(Long fixedReturnId) throws Exception;

	/**
	 * 查询二维码车辆列表
	 * @param pageIndex
	 * @param page_size_web
	 * @param bike
	 * @param bikeLockCode
	 * @param channelid
	 * @return
	 * @throws Exception
	 */
	public List<Bike> findBikeQrList(Integer pageIndex, Integer page_size_web, Bike bike, String bikeLockCode,
			Long channelid,List<Long> channelIds)throws Exception;
 
	/**
	 * 统计数量
	 * @param bike
	 * @param bikeLockCode
	 * @param channelid
	 * @return
	 * @throws Exception
	 */
	public Integer countBikeQrList(Bike bike, String bikeLockCode, Long channelid,List<Long> channelIds)throws Exception;

	/**
	 * 通过编号模糊查询包括删除的
	 * @param getbBikeCode
	 * @return
	 * @throws Exception
	 */
	public List<Bike> findBikeByCode(String bikeCode)throws Exception;

	/**
	 * 通过id查询未被删除的车辆
	 * @param bikeLockBikeId
	 * @return
	 * @throws Exception
	 */
	public Bike findBikeByIdAndDel(Long bikeLockBikeId)throws Exception;

	/**
	 * 通过车型id查询车辆
	 * @param modelsId
	 * @return
	 * @throws Exception
	 */
	public List<Bike> findBikeByModelsId(Long modelsId)throws Exception;
	
	public List<Bike> findAllBike()throws Exception;


	/**
	 * 查询站点附近的车
	 * @param fixedReturnId
	 * @return
	 * @throws Exception
	 */
	public List<LatLng> findByfixedReturnId(Long fixedReturnId)throws Exception;

	/**
	 * 不分页查询
	 * @return
	 * @throws Exception
	 */
	public List<Bike> findAllBikeNoPage(Bike bike,Date bikePutStartTime, Date bikePutEndTime,String blockCode,List<Long> channelIds,Long channelid, Long modelsId)throws Exception;

}		
