package org.service.cms.impl.read;

import java.util.List;

import javax.annotation.Resource;

import org.dao.ChannelMapper;
import org.entity.dto.Channel;
import org.entity.dto.ChannelExample;
import org.service.cms.read.ChannelServiceRead;
import org.springframework.stereotype.Service;
import org.util.PageUtil;

@Service("channelServiceRead")
public class ChannelServiceReadImpl implements ChannelServiceRead {
	
	
	@Resource
	ChannelMapper channelMapper;


	@Override
	public List<Channel> findAllChannel(Integer pageIndex, Integer pageSize,String channelNum,Long channelId, String charger, String channelName,Integer flag)
			throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria = channelExample.createCriteria();
		//渠道编号
		if(null!=channelId&&!"".equals(channelId)){
			criteria.andChannelIdEqualTo(channelId);
		}
		//负责人姓名
		if(null!=charger&&!"".equals(charger)){
			criteria.andChannelChargerNameLike("%"+charger+"%");
		}
		//渠道名
		if(null!=channelName&&!"".equals(channelName)){
			criteria.andChannelNameLike("%"+channelName+"%");
		}
		if(null!=channelNum&&!"".equals(channelNum)){
			criteria.andChannelNumLike(channelNum+"%");	
		}
		if(null==flag){
			//-1删除标志
			criteria.andChannelStateNotEqualTo(-1);
		}else{
			criteria.andChannelStateEqualTo(flag);
		}
		if(null!=pageIndex){
			channelExample.setLimitStart(PageUtil.getStart(pageIndex));
			channelExample.setLimitEnd(pageSize);
		}
		channelExample.setOrderByClause("CAST(channel_num AS SIGNED) asc");
		
		return channelMapper.selectByExample(channelExample);
	}




	@Override
	public Integer findAllChannelCount(String channelNum,Long channelId, String charger, String channelName) throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria = channelExample.createCriteria();
		//渠道编号
		if(null!=channelId&&!"".equals(channelId)){
			criteria.andChannelIdEqualTo(channelId);
		}
		//负责人姓名
		if(null!=charger&&!"".equals(charger)){
			criteria.andChannelChargerNameLike("%"+charger+"%");
		}
		//渠道名
		if(null!=channelName&&!"".equals(channelName)){
			criteria.andChannelNameEqualTo("%"+channelName+"%");
		}
		if(null!=channelNum&&!"".equals(channelNum)){
			criteria.andChannelNumLike(channelNum+"%");	
		}
		criteria.andChannelStateNotEqualTo(-1);//不为删除
		return channelMapper.countByExample(channelExample);
	}


	@Override
	public Channel findByName(String channelName) throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria  = channelExample.createCriteria();
		//渠道名
		criteria.andChannelNameEqualTo(channelName);
		criteria.andChannelStateNotEqualTo(-1);
		List<Channel> channels = channelMapper.selectByExample(channelExample);
		if(channels.size()>0){
			return channels.get(0);
		}
		return null;
	}


	@Override
	public List<Channel> findAllChannelNotfreeze(List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria  = channelExample.createCriteria();
		if(null!=channelIds){
			criteria.andChannelIdIn(channelIds);
		}
		criteria.andChannelStateEqualTo(1);
		List<Channel> channels = channelMapper.selectByExample(channelExample);
		return channels;
	}
	
	
	@Override
	public List<Channel> findAllChannelByChannelIds(List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria  = channelExample.createCriteria();
		if(null!=channelIds){
			criteria.andChannelIdIn(channelIds);
		}
		criteria.andChannelStateNotEqualTo(-1);
		List<Channel> channels = channelMapper.selectByExample(channelExample);
		return channels;
	}


	@Override
	public List<Channel> findByParentId(Integer pageIndex,Integer pageSize, String channelNum,String num, String charger, String channelName) throws Exception {
		// TODO Auto-generated method stub
		
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria = channelExample.createCriteria();
		criteria.andChannelNumLike(num+"%");		//编号
		criteria.andChannelStateNotEqualTo(-1);		//
		if(null!=charger&&!"".equals(charger)){	//负责人
			criteria.andChannelChargerNameLike("%"+charger.trim()+"%");
		}
		if(null!=channelName&&!"".equals(channelName)){	//渠道名
			criteria.andChannelNameLike("%"+channelName.trim()+"%");
		}
		if(null!=channelNum&&!"".equals(channelNum)){
			criteria.andChannelNumLike(channelNum+"%");	
		}
		
		channelExample.setLimitStart(PageUtil.getStart(pageIndex));
		channelExample.setLimitEnd(pageSize);
		channelExample.setOrderByClause("channel_num asc");
		return channelMapper.selectByExample(channelExample);
	}


	@Override
	public Integer findAllCountByParentId(String channelNum,String num, String charger, String channelName) throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria = channelExample.createCriteria();
		criteria.andChannelNumLike(num+"%");		//编号
		criteria.andChannelStateNotEqualTo(-1);
		if(null!=charger&&!"".equals(charger)){	//负责人
			criteria.andChannelChargerNameLike("%"+charger.trim()+"%");
		}
		if(null!=channelName&&!"".equals(channelName)){	//渠道名
			criteria.andChannelNameLike("%"+channelName.trim()+"%");
		}
		if(null!=channelNum&&!"".equals(channelNum)){
			criteria.andChannelNumLike(channelNum+"%");	
		}
		return channelMapper.countByExample(channelExample);
	}


	@Override
	public Channel findById(Long channelId) throws Exception {
		// TODO Auto-generated method stub
		return channelMapper.selectByPrimaryKey(channelId);
	}




	@Override
	public List<Channel> findSonChannels(Long channelId) throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria = channelExample.createCriteria();
		criteria.andChannelParentIdEqualTo(channelId);
		criteria.andChannelStateEqualTo(1);//使用中
		return channelMapper.selectByExample(channelExample);
	}




	@Override
	public List<Channel> findLevelOne(List<Long> channelIds) throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample(); 
		ChannelExample.Criteria criteria  = channelExample.createCriteria();
		criteria.andChannelStateEqualTo(1);//使用中
		criteria.andChannelLevelEqualTo(1);
		if(channelIds!=null){
			criteria.andChannelIdIn(channelIds);
		}
		List<Channel> channelList = channelMapper.selectByExample(channelExample);
		if(channelList.size()>0){
			return channelList;
			
		}
		return null;
	}




	@Override
	public List<Channel> findByParentChannelId(Long channelId) throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria = channelExample.createCriteria();
		criteria.andChannelParentIdEqualTo(channelId);
		criteria.andChannelStateEqualTo(1);//使用中
		return channelMapper.selectByExample(channelExample);
	}




	@Override
	public Integer getCountByParentId(Long channelParentId) throws Exception {
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria = channelExample.createCriteria();
		criteria.andChannelParentIdEqualTo(channelParentId);
		return channelMapper.countByExample(channelExample);
	}




	@Override
	public List<Channel> findAllChannels() throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria  = channelExample.createCriteria();
		criteria.andChannelStateEqualTo(1);
		return channelMapper.selectByExample(channelExample);
	}




	@Override
	public List<Channel> findChannel(List<String> str) throws Exception {
		// TODO Auto-generated method stub
		ChannelExample channelExample = new ChannelExample();
		ChannelExample.Criteria criteria  = channelExample.createCriteria();
		criteria.andChannelStateEqualTo(1);
		
		
		criteria.andChannelNameIn(str);
		return channelMapper.selectByExample(channelExample);
	}
	

}
