package org.service.cms.impl.write;

import java.util.Date;

import javax.annotation.Resource;

import org.dao.DefriendRecordMapper;
import org.dao.UserGuideMapper;
import org.dao.UserMapper;
import org.entity.dto.DefriendRecord;
import org.entity.dto.DefriendRecordExample;
import org.entity.dto.User;
import org.entity.dto.UserGuide;
import org.service.cms.write.UserServiceWrite;
import org.springframework.stereotype.Service;

@Service("userServiceWrite")
public class UserServiceWriteImpl implements UserServiceWrite {
	@Resource
	UserMapper userMapper;
	@Resource
	UserGuideMapper userGuideMapper;


	@Resource
	DefriendRecordMapper defriendRecordMapper;

	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public void updateUserBlackList(User user, Long adminId,String defriendReason) throws Exception {
		// TODO Auto-generated method stub

		DefriendRecord defriendRecord = new DefriendRecord();
		DefriendRecordExample example = new DefriendRecordExample();
		DefriendRecordExample.Criteria criteria = example.createCriteria();
		if (0 == user.getUserIsblacklist()) {// 拉黑用户
			defriendRecord.setDefriendUserId(user.getUserId());
			defriendRecord.setDefriendTime(new Date());
			defriendRecord.setDefriendAdminId(adminId);
			defriendRecord.setDefriendReason(defriendReason);
			user.setUserDefriendTime(new Date());
			defriendRecordMapper.insertSelective(defriendRecord);
		} else if (1 == user.getUserIsblacklist()) {// 恢复用户
			defriendRecord.setDefriendRegainTime(new Date());
			defriendRecord.setDefriendRegainAdminId(adminId);
			criteria.andDefriendUserIdEqualTo(user.getUserId());
			defriendRecordMapper.updateByExampleSelective(defriendRecord, example);
		}
		userMapper.updateByPrimaryKeySelective(user);// 用户拉黑状态更新

	}

	@Override
	public void deleteUser(User user) throws Exception {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(user);

	}

	@Override
	public void updateUserGuide(UserGuide userGuide) throws Exception {
		// TODO Auto-generated method stub
		userGuideMapper.updateByPrimaryKeySelective(userGuide);
	}

	@Override
	public void updateUserstate(User user) throws Exception {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKey(user);
	}

	@Override
	public Long addUser(User user) throws Exception {
		userMapper.insertSelective(user);
		return user.getUserId();
	}

}
