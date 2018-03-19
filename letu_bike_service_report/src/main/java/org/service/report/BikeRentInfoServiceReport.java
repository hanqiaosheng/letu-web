package org.service.report;

import java.util.Date;
import java.util.List;

import org.entity.dto.BikeRentInfo;

public interface BikeRentInfoServiceReport {

	/**
	 * 查询用户的租赁记录
	 * 
	 * @param bikeRentInfo
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findUserBikeRentinfo(BikeRentInfo bikeRentInfo, Integer pageIndex, Integer pageSize)
			throws Exception;

	/**
	 * 查询用户的租赁记录条数
	 * 
	 * @param bikeRentInfo
	 * @return
	 * @throws Exception
	 */
	public Integer findUserBikeRentinfoCount(BikeRentInfo bikeRentInfo) throws Exception;

	/**
	 * 租赁信息流水查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findNewBikeRentInfos(List<Long> channelIdList, Integer pageIndex, Date returnFromTime,
			Date returnTotime,String tel,String name,String bikeCode, List<Long> channels) throws Exception;

	public Integer countBikeRentInfos(List<Long> channelIdList, Date returnFromTime, Date returnTotime,String tel,String name,String bikeCode, List<Long> channels)
			throws Exception;


	
	public List<BikeRentInfo> findBikeRentInfo(List<Long>channelIdList,List<Long> channelIds,Long bikeId,Date rentstarttime,Date rentendtime, Integer pageIndex,Integer starttime,Integer endtime) throws Exception;
	
	public Integer CountBikeRentInfo(List<Long>channelIdList,List<Long> channelIds,Long bikeId,Date rentstarttime,Date rentendtime,Integer starttime,Integer endtime) throws Exception;
    /**
     * 通过startFixedReturnId查询租赁信息
     * @param fixedReturnId
     * @return
     * @throws Exception
     */
	public Integer countRentInfoByFixed(Long fixedReturnId,Date fromtime,Date totime)throws Exception;

	public Integer countByChannelIds(List<Long> channelIds)throws Exception;

	/**
	 * 通过endFixedReturnId查询租赁信息
	 * @param fixedReturnId
	 * @param fromTime
	 * @param toTime
	 * @return
	 * @throws Exception
	 */
	public Integer countRentInfoByEndFixed(Long fixedReturnId, Date fromTime, Date toTime)throws Exception;

	/**
	 * 还车数
	 * @param channelIds
	 * @return
	 * @throws Exception
	 */
	public Integer countReturnByChannelIds(List<Long> channelIds)throws Exception;
    /**
     * 根据骑行时长查询
     * @param longTime1
     * @param longTime2
     * @param fromTime
     * @param toTime
     * @return
     * @throws Exception
     */
	public Integer countRentInfoByRideTime(Integer longTime1, Integer longTime2, Date fromTime, Date toTime)throws Exception;


}
