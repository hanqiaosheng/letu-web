package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.ScenicSpotMapper;
import org.entity.dto.ScenicSpot;
import org.entity.dto.ScenicSpotExample;
import org.service.cms.read.ScenicSpotServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("scenicSpotServiceRead")
public class ScenicSpotServiceReadImpl implements ScenicSpotServiceRead {
	@Resource
	ScenicSpotMapper scenicSpotMapper;


	@Override
	public ScenicSpot findById(Long scenicSpotId) throws Exception {
		// TODO Auto-generated method stub
		return scenicSpotMapper.selectByPrimaryKey(scenicSpotId);
	}

	@Override
	public List<ScenicSpot> findScenicByCondition(Integer pageIndex, Integer pageSize, Long cityId, String name,List<Long> cityIds)
			throws Exception {
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
        if(null!=cityId&&-1!=cityId){
        	criteria.andScenicSpotCityIdEqualTo(cityId);
		}
        if(null!=cityIds&&cityIds.size()>0){
        	criteria.andScenicSpotCityIdIn(cityIds);
		}
        if(null!=name&&!name.equals("")){
        	criteria.andScenicSpotNameLike("%"+name+"%");
		}
        criteria.andScenicSpotStateNotEqualTo(-1);//存在
        example.setLimitStart(PageUtil.getStart(pageIndex));
        example.setLimitEnd(pageSize);
        example.setOrderByClause("scenic_spot_top_num desc");
		return scenicSpotMapper.selectByExample(example);
	}

	@Override
	public Integer countScenicByCondition(Long cityId, String name,List<Long> cityIds) throws Exception {
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
		if(null!=cityId&&-1!=cityId){
	        criteria.andScenicSpotCityIdEqualTo(cityId);
		}
		if(null!=cityIds&&cityIds.size()>0){
        	criteria.andScenicSpotCityIdIn(cityIds);
		}
        if(null!=name&&!name.equals("")){
        	criteria.andScenicSpotNameLike("%"+name+"%");
		}
        criteria.andScenicSpotStateNotEqualTo(-1);//存在
   
		return scenicSpotMapper.countByExample(example);
	}

	@Override
	public List<ScenicSpot> findAllScenic() throws Exception {
		// TODO Auto-generated method stub
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
		 criteria.andScenicSpotStateNotEqualTo(-1);//存在
		return scenicSpotMapper.selectByExample(example);
	}

	
}
