package org.service.weixin.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.DefriendRecordMapper;
import org.entity.dto.DefriendRecord;
import org.entity.dto.DefriendRecordExample;
import org.service.weixin.read.DefriendWxServiceRead;
import org.springframework.stereotype.Service;

@Service("defriendWxServiceRead")
public class DefriendWxServiceReadImpl implements DefriendWxServiceRead {
 
	@Resource
	DefriendRecordMapper defriendRecordMapper;
	
	@Override
	public DefriendRecord findDefriendByUserId(Long userId) throws Exception {
		// TODO Auto-generated method stub
		DefriendRecordExample example = new DefriendRecordExample();
		DefriendRecordExample.Criteria criteria = example.createCriteria();
		criteria.andDefriendUserIdEqualTo(userId);
		criteria.andDefriendRegainAdminIdIsNull();
		List<DefriendRecord> defriendRecords = defriendRecordMapper.selectByExample(example);
		if(defriendRecords.size()>0){
			return defriendRecords.get(0);
		}
		return null;
	}
	
}
