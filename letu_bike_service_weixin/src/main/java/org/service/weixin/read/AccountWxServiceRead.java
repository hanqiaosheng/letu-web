package org.service.weixin.read;

import org.entity.dto.Account;

public interface AccountWxServiceRead {
	
	/**
	 * 根据用户id查账号
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Account findByUserId(Long userId) throws Exception;
	
	/**
	 * 根据账号id查账户
	 * @param userAccountId
	 * @return
	 * @throws Exception
	 */
	public Account findByAccountId(Long userAccountId) throws Exception;

}
