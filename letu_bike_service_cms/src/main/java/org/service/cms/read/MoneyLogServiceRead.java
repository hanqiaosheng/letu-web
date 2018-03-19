package org.service.cms.read;

import java.util.Date;
import java.util.List;

import org.entity.dto.MoneyLog;

public interface MoneyLogServiceRead {
	/**
	 * 查询资金日志
	 * @param pageIndex
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public List<MoneyLog> moneyLogList(Integer pageIndex,Integer item,Date FromTime,Date ToTime) throws Exception;
	
	public Integer countMoneyLog(Integer item,Date FromTime,Date ToTime) throws Exception;
	
	/**
	 * 查询退款记录
	 * @return
	 * @throws Exception
	 */
	public List<MoneyLog> refundList() throws Exception;
	
	
}
