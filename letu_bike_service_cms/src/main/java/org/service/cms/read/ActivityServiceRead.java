package org.service.cms.read;

import java.util.Date;
import java.util.List;

import org.entity.dto.Activity;

public interface ActivityServiceRead {

	/**
	 * 查询所有活动
	 * @param pageIndex
	 * @param page_size_web
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Activity> findAllList(Integer pageIndex, Integer page_size_web, String name,Date startTime,Date endTime) throws Exception;

	/**
	 * 获取数量
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Integer getCounts(String name,Date startTime,Date endTime) throws Exception;

	/**
	 * 根据activityId查询信息
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Activity findById(Long activityId)throws Exception;
	

}
