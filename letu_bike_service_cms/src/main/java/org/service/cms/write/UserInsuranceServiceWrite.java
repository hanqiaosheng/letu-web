package org.service.cms.write;

import org.entity.dto.Insurance;

public interface UserInsuranceServiceWrite {
	/**
	 * 编辑保险申请信息（处理，删除）
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public void updateInsuranceState(Insurance insurance) throws Exception;
	
	/**
	 * 添加保险信息
	 * @param refund
	 * @throws Exception
	 */
	public Long addInsurance(Insurance insurance) throws Exception;
	


}
