package org.service.weixin.impl.read;


import java.util.List;

import javax.annotation.Resource;

import org.dao.ModelsMapper;
import org.entity.dto.Models;
import org.entity.dto.ModelsExample;
import org.service.weixin.read.ModelsWxServiceRead;
import org.springframework.stereotype.Service;

@Service("modelsServiceRead")
public class ModelsWxServiceReadImpl implements ModelsWxServiceRead {

	@Resource
	ModelsMapper modelsMapper;
	
	public Models findModelsById(Long modelsId) throws Exception{
		Models models = modelsMapper.selectByPrimaryKey(modelsId);
		return models;
	}

	@Override
	public List<Long> findByChannelId(Long channelId) throws Exception {
		// TODO Auto-generated method stub
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		criteria.andModelsChannelIdEqualTo(channelId);
		return modelsMapper.selectModelIdByExample(example);
	}
}
