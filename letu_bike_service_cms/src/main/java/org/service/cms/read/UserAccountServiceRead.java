package org.service.cms.read;

import java.util.List;

import org.entity.dto.Account;
import org.entity.dto.MoneyLog;

public interface UserAccountServiceRead {
	/**
	 * 根据条件查询用户账户（分页）
	 * @param account
	 * @param pageIndex
	 * @param page_size
	 * @return
	 * @throws Exception
	 */
	public List<Account> findAccountByCondition(Account account,Integer pageIndex, Integer pageSize,String aUserName) throws Exception;
	
	/**
	 * 根据查询条件 查找所有的用户账户条数
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public Integer countAllAccount(Account account,String aUserName) throws Exception;
	/**
	 * 根据id查找账户
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public Account findAccountById(Long accountId) throws Exception;
	
	
	/**
	 * 根据条件查询用户账户详情（分页）
	 * @param moneyLog
	 * @param pageIndex
	 * @param page_size
	 * @return
	 * @throws Exception
	 */
	public List<MoneyLog> findAccountDetailByCondition(MoneyLog moneyLog,Integer pageIndex, Integer pageSize) throws Exception;

	
	/**
	 * 根据查询条件 查找所有的用户账户详情条数
	 * 
	 * @param moneyLog
	 * @return
	 * @throws Exception
	 */
	public Integer countAllAccountDetail(MoneyLog moneyLog) throws Exception;
	
	/**
	 * 根据条件查询村民账户详情（分页）
	 * @param moneyLog
	 * @param pageIndex
	 * @param page_size
	 * @return
	 * @throws Exception
	 */
	public List<MoneyLog> findVillagerAccountDetailByCondition(MoneyLog moneyLog,Integer pageIndex, Integer pageSize) throws Exception;
	
	/**
	 * 根据查询条件 查找村民账户详情条数
	 * 
	 * @param moneyLog
	 * @return
	 * @throws Exception
	 */
	public Integer countAllVillagerAccountDetail(MoneyLog moneyLog) throws Exception;
}
