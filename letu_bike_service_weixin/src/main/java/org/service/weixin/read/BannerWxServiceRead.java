package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Banner;

public interface BannerWxServiceRead {
	/**
	 * 查找所有banner
	 * @param pageIndex
	 * @param page_size_weixin
	 * @return
	 * @throws Exception
	 */
	public List<Banner> findAllBanner(String cityName) throws Exception;


}
