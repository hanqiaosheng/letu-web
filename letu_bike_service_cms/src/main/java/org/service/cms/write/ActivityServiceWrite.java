package org.service.cms.write;

import org.entity.dto.Activity;

public interface ActivityServiceWrite {

	/**
	 * 添加活动
	 * @param activity
	 * @throws Exception
	 */
	public void addActivity(Activity activity)throws Exception;

	/**
	 * 更新活动信息
	 * @param activity
	 * @throws Exception
	 */
	public void updateActivity(Activity activity)throws Exception;

}
