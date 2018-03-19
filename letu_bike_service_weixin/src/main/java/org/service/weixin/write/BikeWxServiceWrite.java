package org.service.weixin.write;

import org.entity.dto.Bike;

public interface BikeWxServiceWrite {
	
	/**
	 * 更新车信息
	 * @param bike
	 * @throws Exception
	 */
	public void updateBike(Bike bike) throws Exception;
	
	/**
	 * 更新车信息
	 * @param bike
	 * @throws Exception
	 */
	public void updateBikeCanNull(Bike bike) throws Exception;

}
