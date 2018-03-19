package org.service.weixin.read;

import java.util.List;

import org.entity.dto.MoneyLog;

public interface MoneyLogWxServiceRead {
	/**
	 * 根据账户id来查找 资金日志
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public List<MoneyLog> findByAccountId(Long accountId,Integer pageIndex, Integer page_size_weixin) throws Exception;
	
	/**
	 * 根据账户id来查找 资金日志条数
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public Integer findCountByAccountId(Long accountId) throws Exception;
	
	/**
	 * 根据交易单号获取资金日志
	 * @param order
	 * @return
	 * @throws Exception
	 */
	public MoneyLog findMoneyLogByOrder(String order) throws Exception;
	
	/**
	 * 根据账户id查询充值退款明细
	 * @param accountId
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<MoneyLog> findDetailByAccountId(Long accountId,Integer pageIndex, Integer page_size_weixin) throws Exception;
	
	/**
	 * 通过账户id查询充值退款条数
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	public Integer findDetailCountByAccountId(Long accountId) throws Exception;

	/**
	 * 消费明细
	 * @param userAccountId
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<MoneyLog> findConsumeByAccountId(Long userAccountId, Integer pageIndex, Integer page_size_weixin)throws Exception;

	public Integer findConsumeNumByAccountId(Long userAccountId)throws Exception;

	/**
	 * 充值退款明细
	 * @param userAccountId
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<MoneyLog> findRechargeByAccountId(Long userAccountId, Integer pageIndex, Integer page_size_weixin)throws Exception;

	public Integer findRechargeNumByAccountId(Long userAccountId)throws Exception;
}
