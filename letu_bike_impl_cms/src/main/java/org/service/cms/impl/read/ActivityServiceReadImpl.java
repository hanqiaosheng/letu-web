package org.service.cms.impl.read;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.dao.ActivityMapper;
import org.entity.dto.Activity;
import org.entity.dto.ActivityExample;
import org.service.cms.read.ActivityServiceRead;
import org.springframework.stereotype.Service;
import org.util.DateUtil;
import org.util.PageUtil;

@Service("activityServiceRead")
public class ActivityServiceReadImpl implements ActivityServiceRead {
	@Resource
	ActivityMapper activityMapper;

	@Override
	public List<Activity> findAllList(Integer pageIndex, Integer page_size_web, String name,Date startTime,Date endTime) throws Exception {
		ActivityExample example = new ActivityExample();
		ActivityExample.Criteria criteria = example.createCriteria();
		criteria.andActivityStateEqualTo(1);//使用中
		if(null!=name&&!name.equals("")){//活动名称
			criteria.andActivityNameLike("%"+name.trim()+"%");
		}
		if(startTime!=null){
			criteria.andActivityCreateTimeGreaterThanOrEqualTo(startTime);
		}
		if(null!=endTime){
			criteria.andActivityCreateTimeLessThan(DateUtil.plusDateNum(endTime, 1));
		}
		if(null!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(page_size_web);
		}
		example.setOrderByClause("activity_topnum desc,activity_create_time desc");
		return activityMapper.selectByExample(example);
	}

	@Override
	public Integer getCounts(String name,Date startTime,Date endTime) throws Exception {
		ActivityExample example = new ActivityExample();
		ActivityExample.Criteria criteria = example.createCriteria();
		criteria.andActivityStateEqualTo(1);//使用中
		if(null!=name&&!name.equals("")){//活动名称
			criteria.andActivityNameLike("%"+name.trim()+"%");
		}
		if(startTime!=null){
			criteria.andActivityCreateTimeGreaterThanOrEqualTo(startTime);
		}
		if(null!=endTime){
			criteria.andActivityCreateTimeLessThan(DateUtil.plusDateNum(endTime, 1));
		}
		return activityMapper.countByExample(example);
	}

	@Override
	public Activity findById(Long activityId) throws Exception {
		// TODO Auto-generated method stub
		return activityMapper.selectByPrimaryKey(activityId);
	}
	
}
