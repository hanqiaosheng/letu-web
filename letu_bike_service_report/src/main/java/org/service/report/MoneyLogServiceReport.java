package org.service.report;

import java.util.Date;
import java.util.List;

import org.entity.dto.MoneyLog;

public interface MoneyLogServiceReport {
	/**
	 * 查询资金日志
	 * @param pageIndex
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public List<MoneyLog> moneyLogList(Integer pageIndex,String name,String tel,Date FromTime,Date ToTime,List<Long> channelIds, List<Long> channelIdList) throws Exception;
	
	public Integer countMoneyLog(String name,String tel,Date FromTime,Date ToTime,List<Long> channelIds, List<Long> channelIdList) throws Exception;
	
	/**
	 * 查询余额充值和预付款充值记录
	 * @return
	 * @throws Exception
	 */
	public List<MoneyLog> findMoneyLog(List<Long>channelIdList,List<Long>channels,Integer type,String name,String tel,Date FromTime,Date ToTime,Integer pageIndex) throws Exception; 
	
	/**
	 * 查询余额充值和预付款充值记录数量
	 * @param channels
	 * @param type
	 * @param user
	 * @param FromTime
	 * @param ToTime
	 * @return
	 * @throws Exception
	 */
	public Integer countMoneyLognum(List<Long>channelIdList,List<Long>channels,Integer type,String name,String tel,Date FromTime,Date ToTime) throws Exception; }
