package org.service.weixin.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.FixedReturnMapper;
import org.entity.dto.FixedReturn;
import org.entity.dto.FixedReturnExample;
import org.entity.dto.LatLng;
import org.service.weixin.read.FixedReturnWxServiceRead;
import org.springframework.stereotype.Service;

@Service("fixedReturnWxServiceRead")
public class FixedReturnWxServiceReadImpl implements FixedReturnWxServiceRead {

	@Resource
	FixedReturnMapper fixedReturnMapper;
	

	@Override
	public FixedReturn findByFixedId(Long fixedReturnId) throws Exception {
		// TODO Auto-generated method stub
		return fixedReturnMapper.selectByPrimaryKey(fixedReturnId);
	}
	
	@Override
	public List<LatLng> findByBlockIds(List<String> blocks) throws Exception {
		// TODO Auto-generated method stub
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		criteria.andFixedReturnBlockIn(blocks);
		criteria.andFixedReturnStateEqualTo(1);
		example.setLimitStart(0);
		example.setLimitEnd(100);
		return fixedReturnMapper.selectFixedsLatLng(example);
	}

	@Override
	public List<LatLng> findByBlockIdsAndFixed(List<String> blocks, List<Long> fixedReturns) throws Exception {
		// TODO Auto-generated method stub
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		criteria.andFixedReturnBlockIn(blocks);
		criteria.andFixedReturnStateEqualTo(1);
		criteria.andFixedReturnIdIn(fixedReturns);
		example.setLimitStart(0);
		example.setLimitEnd(100);
		return fixedReturnMapper.selectFixedsLatLng(example);
	}

	@Override
	public List<LatLng> findByFixed(List<Long> fixedReturnIds) throws Exception {
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		criteria.andFixedReturnStateEqualTo(1);
		criteria.andFixedReturnIdIn(fixedReturnIds);
		example.setLimitStart(0);
		example.setLimitEnd(100);
		return fixedReturnMapper.selectFixedsLatLng(example);
	}

}
