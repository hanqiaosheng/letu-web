package org.service.weixin.impl.write;

import javax.annotation.Resource;

import org.dao.UserToTaskMapper;
import org.entity.dto.UserToTask;
import org.service.weixin.write.UserToTaskWxServiceWrite;
import org.springframework.stereotype.Service;


@Service("userToTaskWxServiceWrite")
public class UserToTaskWxServiceWriteImpl implements UserToTaskWxServiceWrite {

	@Resource
	UserToTaskMapper userToTaskMapper;

	@Override
	public void updateUserToTask(UserToTask userToTask) throws Exception {
		userToTaskMapper.updateByPrimaryKeySelective(userToTask);
	}

	@Override
	public void addUserToTask(UserToTask userToTask) throws Exception {
		userToTaskMapper.insertSelective(userToTask);
	}
}
