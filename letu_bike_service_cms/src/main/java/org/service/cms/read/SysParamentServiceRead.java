package org.service.cms.read;


import org.entity.dto.SysParament;

public interface SysParamentServiceRead {
 
	/**
	 * 通过系数名获取信息
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public SysParament findByName(String string)throws Exception;

	/**
	 * 通过id获取信息
	 * @param sysParamentId
	 * @return
	 * @throws Exception
	 */
	public SysParament findById(Long sysParamentId)throws Exception;
	
}
