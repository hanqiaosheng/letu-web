package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.ModelsMapper;
import org.entity.dto.Models;
import org.entity.dto.ModelsExample;
import org.service.cms.read.ModelsServiceRead;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.util.PageUtil;

@Service("modelsServiceRead")
public class ModelsServiceReadImpl implements ModelsServiceRead {

	@Resource
	ModelsMapper modelsMapper;


	public Models findModelsById(Long modelsId) throws Exception{
		Models models = modelsMapper.selectByPrimaryKey(modelsId);
		return models;
	}


	@Override
	public List<Models> findModelsByChannelId(Long channelId) throws Exception {
		
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		if(null!=channelId&&!"".equals(channelId)){
			criteria.andModelsChannelIdEqualTo(channelId);
		}
		criteria.andModelsStateEqualTo(0);//使用中
		return modelsMapper.selectByExample(example);
	}


	@Override
	public List<Models> findChannelIds(List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		criteria.andModelsStateEqualTo(0);//使用中
		criteria.andModelsChannelIdIn(channelIds);
		return modelsMapper.selectByExample(example);
	}


	@Override
	public List<Models> findAllModels2(Integer pageIndex, Integer pageSize, Long channelId, String channelName)
			throws Exception {
		// TODO Auto-generated method stub
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria  = example.createCriteria();
		criteria.andModelsStateEqualTo(0);//使用中
		if(null!=channelId&&!"".equals(channelId)){			//渠道id
			criteria.andModelsChannelIdEqualTo(channelId);
		}
		if(null!=channelName&&!"".equals(channelName)){		//渠道名字
			criteria.andChannelNameLike("%"+channelName+"%");
		}
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(pageSize);
		return modelsMapper.selectUnionByExample(example);
	}


	@Override
	public Integer findAllCountModels(Long channelId, String channelName) throws Exception {
		// TODO Auto-generated method stub
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria  = example.createCriteria();
		criteria.andModelsStateEqualTo(0);//使用中
		if(null!=channelId&&!"".equals(channelId)){			//渠道id
			criteria.andModelsChannelIdEqualTo(channelId);
		}
		if(null!=channelName&&!"".equals(channelName)){		//渠道名字
			criteria.andChannelNameLike("%"+channelName+"%");
		}
		return modelsMapper.selectUnionByExample(example).size();
	}


	@Override
	public List<Models> findChannelIdsPage(Integer pageIndex, Integer page_size_web, Long channelId, String channelName,
			List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		criteria.andModelsStateEqualTo(0);//使用中
		criteria.andModelsChannelIdIn(channelIds);
		if(null!=channelId&&!"".equals(channelId)){			//渠道id
			criteria.andModelsChannelIdEqualTo(channelId);
		}
		if(null!=channelName&&!"".equals(channelName)){		//渠道名字
			criteria.andChannelNameLike("%"+channelName+"%");
		}
		example.setLimitStart(PageUtil.getStart(pageIndex));
		example.setLimitEnd(page_size_web);
		return modelsMapper.selectUnionByExample(example);
	}


	@Override
	public Integer findAllCountModelsPage(Long channelId, String channelName, List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		criteria.andModelsStateEqualTo(0);//使用中
		criteria.andModelsChannelIdIn(channelIds);
		if(null!=channelId&&!"".equals(channelId)){			//渠道id
			criteria.andModelsChannelIdEqualTo(channelId);
		}
		if(null!=channelName&&!"".equals(channelName)){		//渠道名字
			criteria.andChannelNameLike("%"+channelName+"%");
		}
		return modelsMapper.selectUnionByExample(example).size();
	}


	@Override
	public Models findByChannelIdAndCode(String checkCode, Long modelsChannelId) throws Exception {
		// TODO Auto-generated method stub
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		criteria.andModelsCodeEqualTo(checkCode);
		criteria.andModelsChannelIdEqualTo(modelsChannelId);
		List<Models> list = modelsMapper.selectByExample(example);
		if(list.size()>0){
			return list.get(0);
			
		}
		return null;
	}





	@Override
	public List<Models> findAllModels(@RequestParam(defaultValue="1")Integer pageIndex,String modelsCode,String modelsName,Long channelId,List<Long> currChannelIds) throws Exception {
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		criteria.andModelsStateEqualTo(0);//使用
		if(modelsCode!=null&&!modelsCode.equals("")){
			criteria.andModelsCodeEqualTo(modelsCode.trim());
		}
		if(modelsName!=null&&!modelsName.equals("")){
			criteria.andModelsNameLike("%"+modelsName.trim()+"%");
		}
		if(channelId!=null&&channelId!=-1){
			criteria.andModelsChannelIdEqualTo(channelId);
		}
		if(currChannelIds!=null){
			criteria.andModelsChannelIdIn(currChannelIds);
		}
		if(pageIndex!=null){
			example.setLimitStart(PageUtil.getStart(pageIndex));
			example.setLimitEnd(PageUtil.size);
		}
		
		return modelsMapper.selectUnionByExample(example);
	}


	@Override
	public Integer countAllModels(String modelsCode, String modelsName, Long channelId,List<Long> currChannelIds) throws Exception {
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		criteria.andModelsStateEqualTo(0);//使用
		if(modelsCode!=null&&!modelsCode.equals("")){
			criteria.andModelsCodeEqualTo(modelsCode.trim());
		}
		if(modelsName!=null&&!modelsName.equals("")){
			criteria.andModelsNameLike("%"+modelsName.trim()+"%");
		}
		if(channelId!=null&&channelId!=-1){
			criteria.andModelsChannelIdEqualTo(channelId);
		}
		if(currChannelIds!=null){
			criteria.andModelsChannelIdIn(currChannelIds);
		}
		Integer pageCount = modelsMapper.countUnionByExample(example);
		Integer totalPage = PageUtil.getTotalPage(pageCount);
		return totalPage;
	}


	@Override
	public List<Long> findByChannelId(Long channelId) throws Exception {
		// TODO Auto-generated method stub
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		criteria.andModelsChannelIdEqualTo(channelId);
		List<Long> longs = modelsMapper.selectModelIdByExample(example);
		criteria.andModelsStateEqualTo(0);
		if(longs.size()>0){
			return longs;
		}
		return null;
	}


	@Override
	public Models findModelsByName(String modelsName) throws Exception {
		// TODO Auto-generated method stub
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		criteria.andModelsNameEqualTo(modelsName);
		List<Models> modelsList = modelsMapper.selectByExample(example);
		criteria.andModelsStateEqualTo(0);
		if(modelsList.size()>0){
			return modelsList.get(0);
		}
		return null;
	}


	@Override
	public List<Models> findAllModels() throws Exception {
		// TODO Auto-generated method stub
		ModelsExample example = new ModelsExample();
		ModelsExample.Criteria criteria = example.createCriteria();
		criteria.andModelsStateEqualTo(0);//使用中
		List<Models> modelsList = modelsMapper.selectByExample(example);
		return modelsList;
	}
}
