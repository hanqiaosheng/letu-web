package org.service.weixin.read;

import org.entity.dto.BikeLockInfo;

public interface BikeLockInfoWxServiceRead {
	/**
	 * 通过bikeId查寻锁信息
	 * @param bikeId
	 * @return
	 * @throws Exception
	 */
	public BikeLockInfo findLockInfoById(Long bikeId) throws Exception;
}
