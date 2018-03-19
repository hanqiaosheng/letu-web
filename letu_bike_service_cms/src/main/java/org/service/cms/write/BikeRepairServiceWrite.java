package org.service.cms.write;

import org.entity.dto.BikeRepair;

/**
 * bikeRepair 写的接口
 * @author Administrator
 *
 */
public interface BikeRepairServiceWrite {
	

	/**
	 * 修改bikeRepair
	 * @param bikeRepair
	 * @return
	 * @throws Exception
	 */
	public boolean updateBikeRepair(BikeRepair bikeRepair) throws Exception;

}
