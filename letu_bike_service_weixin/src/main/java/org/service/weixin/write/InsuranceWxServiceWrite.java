package org.service.weixin.write;

import org.entity.dto.Insurance;

public interface InsuranceWxServiceWrite {
	/**
	 * 添加保险信息
	 * @param refund
	 * @throws Exception
	 */
	public void addInsurance(Insurance insurance) throws Exception;
	
	/**
	 * 更新保险信息
	 * @param refund
	 * @throws Exception
	 */
	public void updateInsurance(Insurance insurance) throws Exception;
}
