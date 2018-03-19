package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BannerMapper;
import org.entity.dto.Banner;
import org.entity.dto.BannerExample;
import org.service.weixin.read.BannerWxServiceRead;
import org.springframework.stereotype.Service;

@Service("bannerWxServiceRead")
public class BannerWxServiceReadImpl implements BannerWxServiceRead{

	@Resource
	BannerMapper bannerMapper;

	@Override
	public List<Banner> findAllBanner(String cityCode) throws Exception {
		BannerExample example = new BannerExample();
		BannerExample.Criteria criteria = example.createCriteria();
		BannerExample.Criteria criteria1 = example.createCriteria();
		if(null!=cityCode&&!cityCode.equals("")){
			criteria.andCityCodeEqualTo(cityCode);
		}
		criteria.andBannerStateEqualTo(1);
		criteria1.andBannerStateEqualTo(1);
		criteria1.andBannerIsAllEqualTo(1);
		example.or(criteria1);
		return bannerMapper.selectUnionByExample(example);
	}

}
