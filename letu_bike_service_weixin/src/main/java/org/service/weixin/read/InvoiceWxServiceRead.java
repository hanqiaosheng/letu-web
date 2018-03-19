package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Invoice;


public interface InvoiceWxServiceRead {

	/**
	 * 通过id查询发票
	 * @param invoiceId
	 * @return
	 * @throws Exception
	 */
	public Invoice findById(Long invoiceId)throws Exception;

	/**
	 * 通过用户查询发票
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<Invoice> findByUserId(Long userId,Integer pageIndex,Integer pageSize)throws Exception;

	/**
	 * 通过用户计算发票数量
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Integer findCountByUserId(Long userId)throws Exception;

	public List<Invoice> findAllByUserId(Long userId)throws Exception;
}
