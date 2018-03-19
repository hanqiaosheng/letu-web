package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.UserToTaskMapper;
import org.entity.dto.UserToTask;
import org.service.cms.write.UserToTaskServiceWrite;
import org.springframework.stereotype.Service;


@Service("userToTaskServiceWrite")
public class UserToTaskServiceWriteImpl implements UserToTaskServiceWrite {
	
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
