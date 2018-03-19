package org.service.cms.write;

import org.entity.dto.BikeLockInfo;

public interface BikeLockInfoServiceWrite {
	/**
	 * 编辑锁信息（处理，删除，修改）
	 * 
	 * @param bikeLockInfo
	 * @return
	 * @throws Exception
	 */
	public void updateBikeLockInfo(BikeLockInfo bikeLockInfo) throws Exception;
	
	/**关联锁信息可以为null
	 * 
	 * @param bikeLockInfo
	 * @return
	 * @throws Exception
	 */
	public void updateLockInfo(BikeLockInfo bikeLockInfo) throws Exception;
	
	/**
	 * 添加锁
	 * @param bikeLockInfo
	 * @throws Exception
	 */
	public void adddBikeLockInfo(BikeLockInfo bikeLockInfo) throws Exception;
	
	


}
