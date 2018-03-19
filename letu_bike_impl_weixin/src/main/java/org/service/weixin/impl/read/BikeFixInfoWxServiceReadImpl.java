package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeFixInfoMapper;
import org.entity.dto.BikeFixInfo;
import org.entity.dto.BikeFixInfoExample;
import org.service.weixin.read.BikeFixInfoWxServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;


@Service("bikeFixInfoWxServiceRead")
public class BikeFixInfoWxServiceReadImpl implements BikeFixInfoWxServiceRead {

	
	@Resource
	BikeFixInfoMapper bikeFixInfoMapper;

	@Override
	public List<BikeFixInfo> findAllInfo(Integer pageIndex,Integer pageSize,Long bikeId,String bikeCode,List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		BikeFixInfoExample bikeFixInfoExample = new BikeFixInfoExample();
		BikeFixInfoExample.Criteria criteria  = bikeFixInfoExample.createCriteria();
		if(bikeId!=null){
			criteria.andFixBikeIdEqualTo(bikeId);
		}
		if(channelIds!=null){
			criteria.andChannelIdIn(channelIds);
		}
		if(bikeCode!=null&&!bikeCode.equals("")){
			criteria.andBikeCodeLike(bikeCode);
		}
		criteria.andFixDelEqualTo(0);//存在
		bikeFixInfoExample.setOrderByClause("fix_endtime desc");
		bikeFixInfoExample.setLimitStart(PageUtil.getStart(pageIndex));
		bikeFixInfoExample.setLimitEnd(pageSize);
		return bikeFixInfoMapper.selectUnionByExample(bikeFixInfoExample);
	}

	@Override
	public Integer countAllInfo(Long bikeId,String bikeCode, List<Long> channelIds) throws Exception {
		BikeFixInfoExample bikeFixInfoExample = new BikeFixInfoExample();
		BikeFixInfoExample.Criteria criteria  = bikeFixInfoExample.createCriteria();
		if(bikeId!=null){
			criteria.andFixBikeIdEqualTo(bikeId);
		}
		if(channelIds!=null){
			criteria.andChannelIdIn(channelIds);
		}
		if(bikeCode!=null&&!bikeCode.equals("")){
			criteria.andBikeCodeLike(bikeCode);
		}
		criteria.andFixDelEqualTo(0);//存在
		Integer pageCount = bikeFixInfoMapper.countUnionByExample(bikeFixInfoExample);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}



	
}
