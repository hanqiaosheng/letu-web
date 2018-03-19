package org.service.weixin.read;

import org.entity.dto.Admin;

public interface AdminWxServiceRead {
	
	/**
	 * 根据id查admin
	 * @param insuranceAdminId
	 * @return
	 * @throws Exception
	 */
	public Admin findById(Long insuranceAdminId) throws Exception;

}
