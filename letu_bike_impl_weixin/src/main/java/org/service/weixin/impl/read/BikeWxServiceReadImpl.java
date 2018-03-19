package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.BikeMapper;
import org.entity.dto.Bike;
import org.entity.dto.BikeExample;
import org.entity.dto.LatLng;
import org.service.weixin.read.BikeWxServiceRead;
import org.springframework.stereotype.Service;

@Service("bikeWxServiceRead")
public class BikeWxServiceReadImpl implements BikeWxServiceRead {
	@Resource
	BikeMapper bikeMapper;
	
	
	@Override
	public Bike findByBikeId(Long bikeId) throws Exception {
		return bikeMapper.selectByPrimaryKey(bikeId);
	}


	@Override
	public List<LatLng> findByBlockIds(List<Long> blockIds) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeBlockIdIn(blockIds);
		criteria.andBikeStateEqualTo(0);
		bikeExample.setLimitStart(0);
		bikeExample.setLimitEnd(100);
		return bikeMapper.selectBikesLatLng(bikeExample);
	}


	@Override
	public Bike findByBikeCode(String bikeCode) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeCodeEqualTo(bikeCode);
		criteria.andBikeDelEqualTo(0);//存在
		List<Bike> list=bikeMapper.selectByExample(bikeExample);
		if(0!=list.size()){
			return list.get(0);
		}
		return null;
	}


	@Override
	public List<LatLng> findByBlockIdsAndChannel(List<Long> blockIds, List<Long> modelsList) throws Exception {
		// TODO Auto-generated method stub
		BikeExample bikeExample = new BikeExample();
		BikeExample.Criteria criteria = bikeExample.createCriteria();
		criteria.andBikeBlockIdIn(blockIds);
		criteria.andBikeModelsIdIn(modelsList);
		criteria.andBikeStateEqualTo(0);
		bikeExample.setLimitStart(0);
		bikeExample.setLimitEnd(100);
		return bikeMapper.selectBikesLatLng(bikeExample);
	}



}
