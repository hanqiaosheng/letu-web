package org.service.cms.write;

import org.entity.dto.BikeFixInfo;

public interface BikeFixInfoServiceWrite {
	
	/**
	 * 删除车辆维护信息
	 * @param bikeFixInfo
	 * @return
	 * @throws Exception
	 */
	 public int deleteBikeFixInfoById(BikeFixInfo bikeFixInfo) throws Exception;
	 
	 /**
	  * 添加维护信息
	  * @param bikeFixInfo
	  * @throws Exception
	  */
	 public int addBikeFixInfo(BikeFixInfo bikeFixInfo)throws Exception;
	 
	 /**
	  * 跟新车辆维护信息
	  * @param bf
	  * @throws Exception
	  */
	public void updateBikeFix(BikeFixInfo bf) throws Exception;
}
