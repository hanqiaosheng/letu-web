package org.service.weixin.read;

import java.util.List;

import org.entity.dto.ScenicSpot;

public interface ScenicTicketWxServiceRead {
	/**
	 * 查找所有门票
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<ScenicSpot> findAllTicket(Integer pageIndex, Integer page_size_weixin, String cityName) throws Exception;

	public Integer countAllTicket(String cityName)throws Exception;
	
	/**
	 * 查找所有攻略
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<ScenicSpot> findAllGuide(Integer pageIndex, Integer page_size_weixin, String cityName) throws Exception;

	public Integer countAllGuide(String cityName)throws Exception;

	public ScenicSpot findByScenicId(Long scenicId)throws Exception;
	
	
	/**
	 * 搜索所有门票
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<ScenicSpot> findScenicSelect(Integer pageIndex, Integer page_size_weixin, String scenicName) throws Exception;

	public Integer countScenicSelect(String scenicName)throws Exception;
	
	/**
	 * 搜索所有攻略
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<ScenicSpot> findGuideSelect(Integer pageIndex, Integer page_size_weixin, String scenicName) throws Exception;

	public Integer countGuideSelect(String scenicName)throws Exception;

}
