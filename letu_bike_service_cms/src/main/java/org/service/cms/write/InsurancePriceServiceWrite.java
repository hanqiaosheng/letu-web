package org.service.cms.write;

import org.entity.dto.InsurancePrice;

public interface InsurancePriceServiceWrite {

	/**
	 * 添加
	 * @param insurancePrice
	 * @throws Exception
	 */
	public void add(InsurancePrice insurancePrice)throws Exception;

	/**
	 * 更新
	 * @param insurancePrice
	 * @throws Exception
	 */
	public void update(InsurancePrice insurancePrice)throws Exception;
}
