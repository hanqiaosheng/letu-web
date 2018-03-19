package org.service.weixin.write;

import org.entity.dto.BikeLockInfo;

public interface BikeLockInfoWxServiceWrite {
	
	/**
	 * 更改锁的信息
	 * @param bikeLockInfo
	 * @throws Exception
	 */
	public void updateBikeLock(BikeLockInfo bikeLockInfo) throws Exception;

}
