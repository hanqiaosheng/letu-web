package org.service.cms.write;

import org.entity.dto.User;
import org.entity.dto.UserGuide;

public interface UserServiceWrite {
	/**
	 * 编辑用户
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public void updateUser(User user) throws Exception;
	
	/**
	 * 拉黑/恢复用户
	 * @param user
	 * @return
	 * @throws Exception
	 */

	public void updateUserBlackList(User user,Long adminId,String defriendReason) throws Exception;
	
	
	/**
	 * 软删除用户
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public void deleteUser(User user) throws Exception;
	
	
	/**
	 * 用户指南
	 * @param userGuide
	 * @throws Exception
	 */
	public void updateUserGuide(UserGuide userGuide)throws Exception;
	
	/**
	 * 修改会员到期时间
	 * @param user
	 * @throws Exception
	 */
	public void updateUserstate(User user) throws Exception;

	/**
	 * 添加新用户
	 * @param user
	 * @throws Exception
	 */
	public Long addUser(User user) throws Exception;
}
