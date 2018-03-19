package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.ActivityMapper;
import org.entity.dto.Activity;
import org.service.cms.write.ActivityServiceWrite;
import org.springframework.stereotype.Service;


@Service("activityServiceWrite")
public class ActivityServiceWriteImpl implements ActivityServiceWrite {
	@Resource
	ActivityMapper activityMapper;
	
	@Override
	public void addActivity(Activity activity) throws Exception {
		// TODO Auto-generated method stub
		activityMapper.insertSelective(activity);
	}

	@Override
	public void updateActivity(Activity activity) throws Exception {
		// TODO Auto-generated method stub
		activityMapper.updateByPrimaryKeySelective(activity);
	}

}
