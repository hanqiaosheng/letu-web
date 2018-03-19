package org.service.weixin.write;

import org.entity.dto.BikeRentInfo;

public interface BikeRentInfoWxServiceWrite {
	
	/**
	 * 更新租赁信息
	 * @param bikeRentInfo
	 * @throws Exception
	 */
	public void updateRentInfo(BikeRentInfo bikeRentInfo) throws Exception;
	
	/**
	 * 新增租赁信息
	 * @param bikeRentInfo
	 * @throws Exception
	 */
	public Long addRentInfo(BikeRentInfo bikeRentInfo) throws Exception;

}
