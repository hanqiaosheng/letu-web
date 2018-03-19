package org.service.weixin.write;

import org.entity.dto.Orbit;

public interface OrbitServiceWxWrite {
	/**
	 * 添加轨迹
	 * @param orbit
	 * @throws Exception
	 */
    public void add(Orbit orbit)throws Exception;
    /**
     * 更新轨迹
     * @param orbit
     * @throws Exception
     */
    public void update(Orbit orbit)throws Exception;
}
