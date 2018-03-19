package org.service.cms.impl.read;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.dao.FixedReturnMapper;
import org.entity.dto.FixedReturn;
import org.entity.dto.FixedReturnExample;
import org.entity.dto.LatLng;
import org.service.cms.read.FixedReturnServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("fixedReturnServiceRead")
public class FixedReturnServiceReadImpl implements FixedReturnServiceRead {
	
	@Resource
	FixedReturnMapper fixedReturnMapper;

	@Override
	public List<FixedReturn> findFixedByModelsId(Long modelsId) throws Exception {
		// TODO Auto-generated method stub
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		//criteria.andFixedReturnModelsIdEqualTo(modelsId);
		criteria.andFixedReturnStateEqualTo(1);
		List<FixedReturn> fixedReturns = fixedReturnMapper.selectByExample(example);
		if(fixedReturns.size()>0){
			return fixedReturns;
		}
		return null;
	}

	@Override
	public FixedReturn findFixedById(Long fixedReturnId) throws Exception {
		// TODO Auto-generated method stub
		return fixedReturnMapper.selectByPrimaryKey(fixedReturnId);
	}

	@Override
	public List<FixedReturn> findAllFixed(Integer pageIndex, Integer pageSize, String name,String channelName,List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		if(null!=name&&!name.equals("")){
			criteria.andFixedReturnNameLike("%"+name.trim()+"%");
		}
		if(null!=channelName&&!channelName.equals("")){
			criteria.andChannelNameLike("%"+channelName.trim()+"%");
		}
		if(null!=channelIds){
			criteria.andFixedReturnChannelIdIn(channelIds);
		}
		criteria.andFixedReturnStateEqualTo(1);
		if(null!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(pageSize);
		}
		example.setOrderByClause("fixed_return_createtime desc");
		return fixedReturnMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countAllFixed(String name,String channelName,List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		if(null!=name&&!name.equals("")){
			criteria.andFixedReturnNameLike("%"+name.trim()+"%");
		}
		if(null!=channelName&&!channelName.equals("")){
			criteria.andChannelNameLike("%"+channelName.trim()+"%");
		}
		if(null!=channelIds){
			criteria.andFixedReturnChannelIdIn(channelIds);
		}
		criteria.andFixedReturnStateEqualTo(1);
		Integer totalCount =  fixedReturnMapper.countUnionByExample(example);
		return PageUtil.getTotalPage(totalCount);
	}

	@Override
	public List<FixedReturn> findByChannelIds(List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		if(null!=channelIds){
			criteria.andFixedReturnChannelIdIn(channelIds);
		}
		criteria.andFixedReturnStateEqualTo(1);
		return fixedReturnMapper.selectUnionByExample(example);
	}
	
	@Override
	public List<LatLng> findByBlockIdsAndChannel(List<String> blocks,List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		criteria.andFixedReturnBlockIn(blocks);
		criteria.andFixedReturnStateEqualTo(1);
		if(null!=channelIds){
			criteria.andFixedReturnChannelIdIn(channelIds);
		}
		example.setLimitStart(0);
		example.setLimitEnd(100);
		return fixedReturnMapper.selectFixedsLatLng(example);
	}

	@Override
	public List<FixedReturn> findByChannelIdsAndName(Integer pageIndex,Integer pageSize,String name, List<Long> channelIds) throws Exception {
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		if(null!=channelIds){
			criteria.andFixedReturnChannelIdIn(channelIds);
		}
		if(null!=name&&!name.equals("")){
			criteria.andFixedReturnNameLike("%"+name.trim()+"%");
		}
		if(null!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(pageSize);
		}
		example.setOrderByClause("fixed_return_id asc");
		criteria.andFixedReturnStateEqualTo(1);
		return fixedReturnMapper.selectUnionByExample(example);
	}

	@Override
	public Integer findAllFixedCount(String name, List<Long> channelIds) throws Exception {
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		if(null!=channelIds){
			criteria.andFixedReturnChannelIdIn(channelIds);
		}
		if(null!=name&&!name.equals("")){
			criteria.andFixedReturnNameLike("%"+name.trim()+"%");
		}
		criteria.andFixedReturnStateEqualTo(1);
		return fixedReturnMapper.countByExample(example);
	}

	@Override
	public List<FixedReturn> findAllCheckFixed(Integer pageIndex, Integer pageSize, String name,
			List<Long> channelIds) throws Exception {
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		if(null!=name&&!name.equals("")){
			criteria.andAdminNameLike("%"+name.trim()+"%");
		}
		if(null!=channelIds){
			criteria.andFixedReturnChannelIdIn(channelIds);
		}
		criteria.andFixedReturnStateIn(list);
		if(null!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(pageSize);
		}
		example.setOrderByClause("fixed_return_fixed_time desc");
		return fixedReturnMapper.selectUnionByExample(example);
	}

	@Override
	public Integer countAllCheckFixed(String name, List<Long> channelIds) throws Exception {
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		List<Integer> list = new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		if(null!=name&&!name.equals("")){
			criteria.andAdminNameLike("%"+name.trim()+"%");
		}
		if(null!=channelIds){
			criteria.andFixedReturnChannelIdIn(channelIds);
		}
		criteria.andFixedReturnStateIn(list);
		Integer totalCount =  fixedReturnMapper.countUnionByExample(example);
		return PageUtil.getTotalPage(totalCount);
	}

	@Override
	public List<FixedReturn> findAllMyFixed(Integer pageIndex, Integer pageSize, Integer fixedState, Long adminId)
			throws Exception {
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		if(null!=fixedState){
			criteria.andFixedReturnStateEqualTo(fixedState);
		}
		if(null!=adminId&&!adminId.equals("")){
			criteria.andFixedReturnOriginatorIdEqualTo(adminId);
		}
		criteria.andFixedReturnStateNotEqualTo(0);
		if(null!=pageIndex){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(pageSize);
		}
		example.setOrderByClause("fixed_return_fixed_time desc");
		return  fixedReturnMapper.selectUnionByExample(example);
	}

	@Override
	public Integer findAllFixedCount(Integer fixedState, Long adminId) throws Exception {
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		if(null!=fixedState){
			criteria.andFixedReturnStateEqualTo(fixedState);
		}
		if(null!=adminId&&!adminId.equals("")){
			criteria.andFixedReturnOriginatorIdEqualTo(adminId);
		}
		criteria.andFixedReturnStateNotEqualTo(0);
		return fixedReturnMapper.countUnionByExample(example);
	}

	@Override
	public Integer countUntreated(List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		if (channelIds != null) {
			criteria.andFixedReturnChannelIdIn(channelIds);
		}
		criteria.andFixedReturnStateEqualTo(2);
		return fixedReturnMapper.countUnionByExample(example);
	}

	@Override
	public List<FixedReturn> findFixedByChannelId(Long channelId) throws Exception {
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		criteria.andFixedReturnStateEqualTo(1);
		criteria.andFixedReturnChannelIdEqualTo(channelId);
		return fixedReturnMapper.selectByExample(example);
	}
}
