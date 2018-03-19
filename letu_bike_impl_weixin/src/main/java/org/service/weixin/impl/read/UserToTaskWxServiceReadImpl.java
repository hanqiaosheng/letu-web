package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.UserToTaskMapper;
import org.entity.dto.UserToTask;
import org.entity.dto.UserToTaskExample;
import org.service.weixin.read.UserToTaskWxServiceRead;
import org.springframework.stereotype.Service;
@Service("userToTaskWxServiceRead")
public class UserToTaskWxServiceReadImpl implements UserToTaskWxServiceRead {
	
	@Resource
	UserToTaskMapper userToTaskMapper;

	@Override
	public UserToTask findByUserIdAndDataId(Long userId, Long dataDetId) throws Exception {
		UserToTaskExample example = new UserToTaskExample();
		UserToTaskExample.Criteria criteria = example.createCriteria();
		criteria.andDataDetIdEqualTo(dataDetId);
		criteria.andUserIdEqualTo(userId);
		List<UserToTask> list = userToTaskMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
