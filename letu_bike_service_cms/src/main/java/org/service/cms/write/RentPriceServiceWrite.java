package org.service.cms.write;

import org.entity.dto.RentPrice;

public interface RentPriceServiceWrite {
	/**
	 * 添加租赁费用
	 * @param operateLog
	 * @throws Exception
	 */
    public void addRentPrice(RentPrice rentPrice)throws Exception;

    /**
	 * 更新租赁费用
	 * @param operateLog
	 * @throws Exception
	 */
	public void updateRentPrice(RentPrice price)throws Exception;

	/**
	 * 删除
	 * @param delPrice
	 * @throws Exception
	 */
	public void deleteRentPrice(RentPrice delPrice)throws Exception;
}
