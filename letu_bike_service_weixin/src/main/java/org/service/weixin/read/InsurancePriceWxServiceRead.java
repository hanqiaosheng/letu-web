package org.service.weixin.read;


import org.entity.dto.InsurancePrice;

public interface InsurancePriceWxServiceRead {

	/**
	 * 根据id查询
	 * @param inModelName
	 * @return
	 * @throws Exception
	 */
	public InsurancePrice findById(Long inPriceId)throws Exception;

	
}
