package org.service.weixin.read;


import java.util.List;

import org.entity.dto.RentPrice;

public interface RentPriceWxServiceRead {
	/**
	 * 通过modelsId和是哪种租赁计费模式查询
	 * @param modelsId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public RentPrice findDelPrice(Long modelsId,Integer type)throws Exception;
	
	/**
	 * 通过modelsId查询
	 * @param modelsId
	 * @return
	 * @throws Exception
	 */
	public List<RentPrice> findRentPriceByModelsId(Long modelsId)throws Exception;
}
