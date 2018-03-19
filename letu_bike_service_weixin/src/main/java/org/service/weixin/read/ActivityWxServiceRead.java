package org.service.weixin.read;

import java.util.List;

import org.entity.dto.Activity;

public interface ActivityWxServiceRead {
	/**
	 * 查找所有上线的资讯
	 * @param pageIndex
	 * @param page_size_weixin
	 * @param cityName 
	 * @return
	 * @throws Exception
	 */
	public List<Activity> findAllActivity(Integer pageIndex, Integer page_size_weixin, String cityName) throws Exception;

	/**
	 * 超找所有资讯条数
	 * @return
	 * @throws Exception
	 */
	public Integer findAllActivityCount(String cityName) throws Exception;


}
