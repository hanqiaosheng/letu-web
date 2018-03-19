package org.service.cms.write;

import org.entity.dto.UserToTask;

public interface UserToTaskServiceWrite {
	/**
	 * 更新任务状态
	 * @param userToTask
	 * @throws Exception
	 */
	public void updateUserToTask(UserToTask userToTask) throws Exception;
	
	/**
	 * 添加任务
	 * @param userToTask
	 * @throws Exception
	 */
	public void addUserToTask(UserToTask userToTask) throws Exception;

}
