package org.service.weixin.read;

import org.entity.dto.SysParament;

public interface SysParamentServiceRead {
	
	/**
	 * 根据名字查value
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public SysParament findByName(String name) throws Exception;

}
