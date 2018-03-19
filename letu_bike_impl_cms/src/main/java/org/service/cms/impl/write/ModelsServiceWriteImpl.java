package org.service.cms.impl.write;


import javax.annotation.Resource;

import org.dao.ModelsMapper;
import org.entity.dto.Models;
import org.service.cms.write.ModelsServiceWrite;
import org.springframework.stereotype.Service;
@Service("modelsServiceWrite")
public class ModelsServiceWriteImpl implements ModelsServiceWrite  {
	
	@Resource 
	ModelsMapper modelsMapper;


	@Override
	public int deleteModelsById(Models models) throws Exception {
		// TODO Auto-generated method stub
		
		return modelsMapper.updateByPrimaryKeySelective(models);
	}

	@Override
	public Long addModels(Models models) throws Exception {
		// TODO Auto-generated method stub
		modelsMapper.insertSelective(models);
		Long modelsId = models.getModelsId();
		return modelsId;
	}

	@Override
	public int updateModels(Models models) throws Exception {
		// TODO Auto-generated method stub
		return modelsMapper.updateByPrimaryKeySelective(models);
	}

}
