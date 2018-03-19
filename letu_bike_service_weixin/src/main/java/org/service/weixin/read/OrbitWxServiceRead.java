package org.service.weixin.read;


import org.entity.dto.Orbit;

public interface OrbitWxServiceRead {

	/**
	 * 根据订单id查询轨迹信息
	 * @param rentInfoId
	 * @return
	 * @throws Exception
	 */
	public Orbit findByRentInfoId(Long rentInfoId)throws Exception;

}
