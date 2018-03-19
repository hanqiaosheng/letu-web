package org.service.report.impl;


import java.util.List;

import javax.annotation.Resource;

import org.dao.FixedReturnMapper;
import org.entity.dto.FixedReturn;
import org.entity.dto.FixedReturnExample;
import org.service.report.FixedReturnServiceReport;
import org.springframework.stereotype.Service;

@Service("fixedReturnServiceReport")
public class FixedReturnServiceReportImpl implements FixedReturnServiceReport {
	@Resource
	FixedReturnMapper fixedReturnMapper;

	@Override
	public FixedReturn findFixedById(Long fixedId) throws Exception {
		return fixedReturnMapper.selectByPrimaryKey(fixedId);
	}

	@Override
	public List<FixedReturn> findByChannels(List<Long> channelIds) throws Exception {
		FixedReturnExample example = new FixedReturnExample();
		FixedReturnExample.Criteria criteria = example.createCriteria();
		if(null!=channelIds){
			criteria.andFixedReturnChannelIdIn(channelIds);
		}
		return fixedReturnMapper.selectByExample(example);
	}
	

}
