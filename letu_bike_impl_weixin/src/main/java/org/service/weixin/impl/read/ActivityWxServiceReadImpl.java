package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.ActivityMapper;
import org.entity.dto.Activity;
import org.entity.dto.ActivityExample;
import org.service.weixin.read.ActivityWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("activityWxServiceRead")
public class ActivityWxServiceReadImpl implements ActivityWxServiceRead {

	@Resource
	ActivityMapper activityMapper;
	
	
	@Override
	public List<Activity> findAllActivity(Integer pageIndex, Integer page_size_weixin, String cityCode) throws Exception {
		// TODO Auto-generated method stub
		ActivityExample example = new ActivityExample();
		ActivityExample.Criteria criteria = example.createCriteria();
		//使用中
		criteria.andActivityStateEqualTo(1);
		if(null!=cityCode&&!cityCode.equals("")){
			criteria.andCityCodeEqualTo(cityCode);
		}
		if(null!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(page_size_weixin);
		}
		
		example.setOrderByClause("activity_create_time desc");
		return activityMapper.selectUnionByExample(example);
	}

	@Override
	public Integer findAllActivityCount(String cityCode) throws Exception {
		// TODO Auto-generated method stub\
		ActivityExample example = new ActivityExample();
		ActivityExample.Criteria criteria = example.createCriteria();
		//使用中
		if(null!=cityCode&&!cityCode.equals("")){
			criteria.andCityCodeEqualTo(cityCode);
		}
		criteria.andActivityStateEqualTo(1);
		return activityMapper.countUnionByExample(example);
	}


}
