package org.service.cms.impl.write;

import javax.annotation.Resource;

import org.dao.BannerMapper;
import org.entity.dto.Banner;
import org.service.cms.write.BannerServiceWrite;
import org.springframework.stereotype.Service;
@Service("bannerServiceWrite")
public class BannerServiceWriteImpl implements BannerServiceWrite {

	@Resource
	BannerMapper bannerMapper;
	
	@Override
	public void add(Banner banner) throws Exception {
		// TODO Auto-generated method stub
		bannerMapper.insertSelective(banner);
	}

	@Override
	public void update(Banner banner) throws Exception {
		// TODO Auto-generated method stub
		bannerMapper.updateByPrimaryKeySelective(banner);
	}
	
}
