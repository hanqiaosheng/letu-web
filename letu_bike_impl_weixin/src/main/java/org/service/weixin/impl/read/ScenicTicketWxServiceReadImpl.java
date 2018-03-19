package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.ScenicSpotMapper;
import org.entity.dto.ScenicSpot;
import org.entity.dto.ScenicSpotExample;
import org.service.weixin.read.ScenicTicketWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("scenicTicketWxServiceRead")
public class ScenicTicketWxServiceReadImpl implements ScenicTicketWxServiceRead{

	@Resource
	ScenicSpotMapper scenicSpotMapper;

	@Override
	public List<ScenicSpot> findAllTicket(Integer pageIndex, Integer page_size_weixin, String cityCode) throws Exception {
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
		ScenicSpotExample.Criteria criteria1 = example.createCriteria();
		if(null!=cityCode&&!cityCode.equals("")){
			criteria.andCityCodeEqualTo(cityCode);
		}
		criteria.andScenicSpotStateEqualTo(2);
		criteria.andScenicSpotTypeEqualTo(0);//景区
		criteria1.andScenicSpotIsAllEqualTo(1);
		criteria1.andScenicSpotStateEqualTo(2);
		criteria1.andScenicSpotTypeEqualTo(0);//景区
		example.or(criteria1);
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(page_size_weixin);
		example.setOrderByClause("scenic_spot_top_num desc");
		return scenicSpotMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countAllTicket(String cityCode) throws Exception {
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
		ScenicSpotExample.Criteria criteria1 = example.createCriteria();
		if(null!=cityCode&&!cityCode.equals("")){
			criteria.andCityCodeEqualTo(cityCode);
		}
		criteria.andScenicSpotStateEqualTo(2);
		criteria.andScenicSpotTypeEqualTo(0);//景区
		criteria1.andScenicSpotIsAllEqualTo(1);
		criteria1.andScenicSpotStateEqualTo(2);
		criteria1.andScenicSpotTypeEqualTo(0);//景区
		example.or(criteria1);
		return scenicSpotMapper.countUnionByExample(example);
	}
	
	
	@Override
	public List<ScenicSpot> findAllGuide(Integer pageIndex, Integer page_size_weixin,String cityCode) throws Exception {
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
		ScenicSpotExample.Criteria criteria1 = example.createCriteria();
		if(null!=cityCode&&!cityCode.equals("")){
			criteria.andCityCodeEqualTo(cityCode);
		}
		criteria.andScenicSpotStateEqualTo(2);
		criteria.andScenicSpotTypeEqualTo(1);//攻略
		criteria1.andScenicSpotIsAllEqualTo(1);
		criteria1.andScenicSpotStateEqualTo(2);
		criteria1.andScenicSpotTypeEqualTo(1);//攻略
		example.or(criteria1);
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(page_size_weixin);
		example.setOrderByClause("scenic_spot_top_num desc");
		return scenicSpotMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countAllGuide(String cityCode) throws Exception {
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
		ScenicSpotExample.Criteria criteria1 = example.createCriteria();
		if(null!=cityCode&&!cityCode.equals("")){
			criteria.andCityCodeEqualTo(cityCode);
		}
		criteria.andScenicSpotStateEqualTo(2);
		criteria.andScenicSpotTypeEqualTo(1);//攻略
		criteria1.andScenicSpotIsAllEqualTo(1);
		criteria1.andScenicSpotStateEqualTo(2);
		criteria1.andScenicSpotTypeEqualTo(1);//攻略
		example.or(criteria1);
		return scenicSpotMapper.countUnionByExample(example);
	}

	@Override
	public ScenicSpot findByScenicId(Long scenicId) throws Exception {
		// TODO Auto-generated method stub
		return scenicSpotMapper.selectByPrimaryKey(scenicId);
	}
	
	
	@Override
	public List<ScenicSpot> findScenicSelect(Integer pageIndex, Integer page_size_weixin, String scenicName) throws Exception {
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
		ScenicSpotExample.Criteria criteria2 = example.createCriteria();
		if(null!=scenicName&&!scenicName.equals("")){
			criteria.andCityNameLike("%"+scenicName+"%");
		}else{
			criteria.andCityNameEqualTo("");
		}
		criteria.andScenicSpotStateEqualTo(2);
		
		if(null!=scenicName&&!scenicName.equals("")){
			criteria2.andScenicSpotNameLike("%"+scenicName+"%");
		}else{
			criteria2.andScenicSpotNameEqualTo("");
		}
		criteria2.andScenicSpotStateEqualTo(2);
		example.or(criteria2);
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(page_size_weixin);
		example.setOrderByClause("scenic_spot_top_num desc");
		return scenicSpotMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countScenicSelect(String scenicName) throws Exception {
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
		ScenicSpotExample.Criteria criteria2 = example.createCriteria();
		if(null!=scenicName&&!scenicName.equals("")){
			criteria.andCityNameLike("%"+scenicName+"%");
		}else{
			criteria.andCityNameEqualTo("");
		}
		criteria.andScenicSpotStateEqualTo(2);
		
		if(null!=scenicName&&!scenicName.equals("")){
			criteria2.andScenicSpotNameLike("%"+scenicName+"%");
		}else{
			criteria2.andScenicSpotNameEqualTo("");
		}
		criteria2.andScenicSpotStateEqualTo(2);
		example.or(criteria2);
		return scenicSpotMapper.countUnionByExample(example);
	}
	
	
	@Override
	public List<ScenicSpot> findGuideSelect(Integer pageIndex, Integer page_size_weixin,String scenicName) throws Exception {
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
		ScenicSpotExample.Criteria criteria2 = example.createCriteria();
		if(null!=scenicName&&!scenicName.equals("")){
			criteria.andCityNameLike("%"+scenicName+"%");
		}
		criteria.andScenicSpotStateEqualTo(2);
		criteria.andScenicSpotTypeEqualTo(1);//攻略
		
		if(null!=scenicName&&!scenicName.equals("")){
			criteria2.andScenicSpotNameLike("%"+scenicName+"%");
		}
		criteria2.andScenicSpotStateEqualTo(2);
		criteria2.andScenicSpotTypeEqualTo(1);//攻略
		example.or(criteria2);
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(page_size_weixin);
		example.setOrderByClause("scenic_spot_top_num desc");
		return scenicSpotMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countGuideSelect(String scenicName) throws Exception {
		ScenicSpotExample example = new ScenicSpotExample();
		ScenicSpotExample.Criteria criteria = example.createCriteria();
		ScenicSpotExample.Criteria criteria2 = example.createCriteria();
		if(null!=scenicName&&!scenicName.equals("")){
			criteria.andCityNameLike("%"+scenicName+"%");
		}
		criteria.andScenicSpotStateEqualTo(2);
		criteria.andScenicSpotTypeEqualTo(1);//攻略
		
		if(null!=scenicName&&!scenicName.equals("")){
			criteria2.andScenicSpotNameLike("%"+scenicName+"%");
		}
		criteria2.andScenicSpotStateEqualTo(2);
		criteria2.andScenicSpotTypeEqualTo(1);//攻略
		example.or(criteria2);
		return scenicSpotMapper.countUnionByExample(example);
	}
	
}
