package org.service.cms.read;

import java.util.Date;
import java.util.List;

import org.entity.dto.BikeRentInfo;

public interface BikeRentInfoServiceRead {
	/**
	 * 查询所有车辆租赁记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findAllBikeRentInfo(Integer pageIndex,List<Long> channelIds, Long bikeId, Long bikerentId, String userPhone,
			Date rentFromTime,Date rentTotime,Date returnFromTime,
            Date returnTotime, Long rentStartFixedId,Long rentEndFixedId,Long channelid) throws Exception;

	/**
	 * 所有租赁记录数量
	 * 
	 * @param bikeId
	 * @param bikerentId
	 * @param
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Integer countAllBikeRentInfo(List<Long> channelIds,Long bikeId, Long bikerentId, String userPhone, Date rentFromTime,Date rentTotime,Date returnFromTime,
            Date returnTotime, Long rentStartFixedId,Long rentEndFixedId,Long channelid) throws Exception;

	/**
	 * 根据id查询租赁信息
	 * 
	 * @param bikeRentId
	 * @return
	 * @throws Exception
	 */
	public BikeRentInfo findBikeRentInfoById(Long bikeRentId) throws Exception;

	/**
	 * 根据车辆编号id查询租赁信息
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findBikeRentInfoByBikeId(Long bikeId) throws Exception;

	/**
	 * 租赁订单列表
	 * @param
	 * 
	 * @param pageSize
	 * @param pageIndex
	 * @param userTel
	 * @param rentInfoId
	 * @param
	 * @param rentState
	 * @param rentTime
	 * @param returnTime
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findAllBikeRentInfos(List<Long> channelIds, Integer pageIndex, Integer pageSize, String userTel, Long rentInfoId,
			String bikeCode, Integer rentState, Date rentTime,  Date rentTime2,Date returnTime,Date returnTime2, Long rentStartFixedId,Long rentEndFixedId,Long channelid,Integer flag) throws Exception;

	public Integer findAllBikeInfoCount(List<Long> channelIds,String userTel,Long rentInfoId, String bikeCode, Integer rentState, Date rentTime,Date rentTime2, Date returnTime,Date returnTime2, Long rentStartFixedId,Long rentEndFixedId,Long channelid)
			throws Exception;
	/**
	 * 查询用户的租赁记录
	 * @param bikeRentInfo
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findUserBikeRentinfo(BikeRentInfo bikeRentInfo,Integer pageIndex, Integer pageSize) throws Exception;
	/**
	 * 查询用户的租赁记录条数
	 * @param bikeRentInfo
	 * @return
	 * @throws Exception
	 */
	public Integer findUserBikeRentinfoCount(BikeRentInfo bikeRentInfo) throws Exception;

	/**
	 * 租赁信息流水查询
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findBikeRentInfos(Long channelId,Integer pageIndex,Date returnFromTime,Date returnTotime,Integer moneyNum,List<Long> channels) throws Exception;
	
	public Integer countBikeRentInfos(Long channelId,Date returnFromTime,Date returnTotime,Integer moneyNum,List<Long> channels) throws Exception;

	/**
	 * 根据车辆id查他的租赁记录
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findByBikeId(Long bikeId,Integer pageIndex,Integer page_size_weixin) throws Exception;

	/**
	 * 根据车id查行程条数
	 * @param
	 * @return
	 * @throws Exception
	 */
	public Integer findCountByBikeId(Long bikeId) throws Exception;
	
	/**
	 * 查询会员的租赁记录
	 * @param bikeRentInfo
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findVipUserBikeRentinfo(BikeRentInfo bikeRentInfo,Integer pageIndex, Integer pageSize) throws Exception;
	/**
	 * 查询会员的租赁记录条数
	 * @param bikeRentInfo
	 * @return
	 * @throws Exception
	 */
	public Integer findVipUserBikeRentinfoCount(BikeRentInfo bikeRentInfo) throws Exception;
	
	/**
	 * 查询订单状态未完成的订单
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findNotRent() throws Exception;
	
	/**
	 * 通过发票Id查询订单
	 * @param invoiceId
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findByInvoiceId(Long invoiceId,Integer pageIndex, Integer pageSize) throws Exception;
	
	public Integer findfindByInvoiceIdCount(Long invoiceId,Integer pageIndex, Integer pageSize) throws Exception;

	/**
	 * 查询用户是否有未完成租赁订单
	 */
	public BikeRentInfo findNotFinishByUserId(Long userId) throws Exception;

	/**
	 * 批量查询用户租赁订单
	 */
	public List<BikeRentInfo> findByUserIdsAndState(List<Long> userIds, int state) throws Exception;

	/**
	 * 查询用户最近的租赁情况
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public BikeRentInfo findByUserId(Long userId,String phone,String idCard,String name) throws Exception;

	/**
	 * 查询该团中某用户的租赁记录
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<BikeRentInfo> findByUserIdAndGroupId(Long userId,Long groupId) throws Exception;

}
