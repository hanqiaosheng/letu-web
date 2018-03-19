package org.service.cms.read;

import java.util.List;

import org.entity.dto.BikeLockInfo;


public interface BikeLockInfoServiceRead {
	/**
	 * 所有的锁
	 * @return
	 * @throws Exception
	 */
	public List<BikeLockInfo> findAllLock() throws Exception;
	
	/**
	 * 根据条件查询锁（分页）
	 * @param bikeLockInfo
	 * @param pageIndex
	 * @param page_size
	 * @return
	 * @throws Exception
	 */
	public List<BikeLockInfo> findBikeLockInfoByCondition(BikeLockInfo bikeLockInfo,Integer pageIndex, Integer pageSize,String bikeCode,Long modelsId,Integer flag) throws Exception;

	/**
	 * 根据条件查询锁
	 * @param bikeLockInfo
	 * @param pageIndex
	 * @param page_size
	 * @return
	 * @throws Exception
	 */
	public List<BikeLockInfo> findBikeLockInfoByCondition(BikeLockInfo bikeLockInfo, Integer pageSize,String bikeCode,Long modelsId,Integer flag) throws Exception;

	
	
	/**
	 * 通过id查找锁
	 * 
	 * @param bikeLockInfoId
	 * @return
	 * @throws Exception
	 */
	public BikeLockInfo findById(Long bikeLockInfoId) throws Exception;

	


	
	/**
	 * 根据查询条件 查找所有的锁条数
	 * 
	 * @param bikeLockInfo
	 * @return
	 * @throws Exception
	 */
	public Integer countAllBikeLockInfo(BikeLockInfo bikeLockInfo,String bikeCode,Long modelsId,Integer flag) throws Exception;

	/**
	 * 根据锁编号查询信息
	 * @param lockCode
	 * @return
	 * @throws Exception
	 */
	public BikeLockInfo findByCode(String lockCode) throws Exception;
	
	/**
	 * 未在线
	 * @return
	 * @throws Exception
	 */
	public Integer countuntreated()throws Exception;
	
	/**
	 * 低电量
	 * @return
	 * @throws Exception
	 */
	public Integer countLowBattery()throws Exception;

	/**
	 * 查询所有升级中的锁
	 * @return
	 * @throws Exception
	 */
	public List<BikeLockInfo> findAllUpLock()throws Exception;
	
}
