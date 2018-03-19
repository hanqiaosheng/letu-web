package org.service.cms.read;


import java.util.List;

import org.entity.dto.Account;


public interface AccountServiceRead {


	
	/**
	 * 通过accountId查询信息
	 * @param rentPlanId
	 * @return
	 * @throws Exception
	 */
	public Account findPlanByaccountId(Long accountId) throws Exception;
	
	/**
	 * 查找所有有用的账户信息
	 * @return
	 * @throws Exception
	 */
	public List<Account> findAllUserful() throws Exception;
	
	public Account findByuserId(Long userId) throws Exception;

}
