package org.service.weixin.write;

import org.entity.dto.Idcard;
import org.entity.dto.User;
import org.entity.weixin.pojo.SNSUserInfo;

public interface UserServiceWeixinWrite {

	public User updateUser(User user) throws Exception;

	public User regiest(SNSUserInfo snsUserInfo) throws Exception;

	public void addUser(User user) throws Exception;

	public User regiest2(User iUser) throws Exception;
	
	/**
	 * 新增验证次数
	 * @param idcard
	 * @throws Exception
	 */
	public void addIdCard(Idcard idcard) throws Exception;
	
	/**
	 * 更新身份证
	 * @param idcard
	 * @throws Exception
	 */
	public void updateIdcard(Idcard idcard) throws Exception;
}
