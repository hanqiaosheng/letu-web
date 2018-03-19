package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BannerMapper;
import org.entity.dto.Banner;
import org.entity.dto.BannerExample;
import org.service.cms.read.BannerServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("bannerServiceRead")
public class BannerServiceReadImpl implements BannerServiceRead {
	@Resource
	BannerMapper bannerMapper;

	@Override
	public List<Banner> findByCondition(Integer pageIndex, Integer pageSize, List<Long> cityIds, Long cityId,
			String bannerName) throws Exception {
		// TODO Auto-generated method stub
		BannerExample example = new BannerExample();
		BannerExample.Criteria criteria = example.createCriteria();
		if(null!=cityIds&&cityIds.size()>0){
			criteria.andBannerCityIdIn(cityIds);
		}
        if(null!=cityId&&-1!=cityId){
        	criteria.andBannerCityIdEqualTo(cityId);
		}
        if(null!=bannerName&&!bannerName.equals("")){
        	criteria.andBannerNameLike("%"+bannerName+"%");
		}
        criteria.andBannerStateEqualTo(1);
        example.setLimitStart(PageUtil.getStart(pageIndex));
        example.setLimitEnd(pageSize);
        example.setOrderByClause("banner_top_num desc");
		return bannerMapper.selectByExample(example);
	}

	@Override
	public Integer countByCondition(List<Long> cityIds, Long cityId, String bannerName) throws Exception {
		// TODO Auto-generated method stub
		BannerExample example = new BannerExample();
		BannerExample.Criteria criteria = example.createCriteria();
		if(null!=cityIds&&cityIds.size()>0){
			criteria.andBannerCityIdIn(cityIds);
		}
        if(null!=cityId&&-1!=cityId){
        	criteria.andBannerCityIdEqualTo(cityId);
		}
        if(null!=bannerName&&!bannerName.equals("")){
        	criteria.andBannerNameLike("%"+bannerName+"%");
		}
        criteria.andBannerStateEqualTo(1);
   
		return bannerMapper.countByExample(example);
	}

	@Override
	public Banner findById(Long bannerId) throws Exception {
		// TODO Auto-generated method stub
		return bannerMapper.selectByPrimaryKey(bannerId);
	}
}
